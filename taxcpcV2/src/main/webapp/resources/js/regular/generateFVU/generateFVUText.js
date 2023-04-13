/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function getGeneratedFVUTextFileLoc() {

    document.getElementById("upload").disabled = true;
    var deductor_name = document.getElementById("deductor_name").value;
    var deductor_pan = document.getElementById("deductor_pan").value;
    var deductor_desig = document.getElementById("deductor_desig").value;
    var deductor_mobile = document.getElementById("deductor_mobile").value;
    var prn_no = document.getElementById("prn_no").value;
    var prnCheck = document.getElementById("prnCheck").checked;
    var deductorCheck = document.getElementById("deductorCheck").checked;
    var deductor_pan_validate = validateResponsiblePersonPANNo(deductor_pan);
    var hiddenPrnCheck = "";

    try {
        hiddenPrnCheck = document.getElementById("hiddenPrnCheck").value;
    } catch (err) {
    }
    if (lhsIsNull(deductor_name)) {
        addActionError("message", "Responsible person name is mandatory");
    } else if (lhsIsNull(deductor_pan)) {
        addActionError("message", "Responsible person pan is mandatory");
    } else if (!deductor_pan_validate) {
        // no message...
    } else if (lhsIsNull(deductor_desig)) {
        addActionError("message", "Responsible person designation is mandatory");
    } else if (lhsIsNull(deductor_mobile)) {
        addActionError("message", "Responsible person mobile no is mandatory");
    } else if (prnCheck && (lhsIsNull(prn_no) || prn_no.length < 15)) {
        addActionError("message", "Enter 15 digit PRN no");
//    } else if (deductorCheck==true || prnCheck==true) {
    } else {
//        removeError();
        var hiddenAddCheck = "F";
        if (!deductorCheck) {
            hiddenAddCheck = "T";
        }
        var cp = document.getElementById("globalcontextpath").value;
        var tokenNo = document.getElementById("tokenNo").value;

        var url = cp + "/fvuGenerateAjaxAction?action=responsiblePersonDetails&deductorName=" + deductor_name;
        url += "&deductorPan=" + deductor_pan;
        url += "&deductorDesig=" + deductor_desig;
        url += "&deductorMobile=" + deductor_mobile;
        url += "&prn_no=" + encodeURIComponent(prn_no);
        url += "&hiddenPrnCheck=" + encodeURIComponent(hiddenPrnCheck);
        url += "&hiddenAddCheck=" + encodeURIComponent(hiddenAddCheck);
        url += "&tokenNo=" + encodeURIComponent(tokenNo);
        var formData = new FormData($("form#uploadCSIFiles")[0]);

        showProcessIndicator();
        $.ajax({
            url: url,
            type: 'POST',
            data: formData,
            async: false,
            success: function (data) {
                if (data === 'success') {
                    document.getElementById("validateFlag").value = "true";
                    setTimeout(function () {
//                        callFvuTextProcedure(tokenNo);
                        callDedicatedProcedure({
                            callingProcName: 'ProcGenerateTdsTextFile',
                            tokenNo: tokenNo});

                        openSuccessModal('Your request is initiated.', "callUrl('/errorStatus');");
                    }, 1000);
//                    document.getElementById("generate_file").submit();
                } else {
//                    addActionError("error", data);
                    openErrorModal("Some error occurred, could not proceed");
                    hideProcessIndicator();
                }
            },
            cache: false,
            contentType: false,
            processData: false
        });
    }
}//end function

//function callFvuTextProcedure(token) {
//    if (!lhsIsNull(token)) {
//        var cp = document.getElementById("globalcontextpath").value;
//        var action = cp + "/fvuGenerateAjaxAction?action=callFVUTextProcedure&tokenNo=" + encodeURIComponent(token);
//        $.ajax({
//            url: action,
//            type: 'POST',
//            success: function (data, textStatus, jqXHR) {
////                alert(data)
//                if (!lhsIsNull(data) && data === 'success') {
//                    openSuccessModal('Your request is initiated.', "callUrl('/errorStatus');");
//                    hideProcessIndicator();
//                } else if (!lhsIsNull(data) && data === 'error') {
//                    openErrorModal('Some error occurred, please try after some time', "callUrl('/errorStatus');");
//                    hideProcessIndicator();
//                }
//            }
//        });
//    }//end if
//}//end function
