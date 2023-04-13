/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




function onClickPrnCheckBox() {
    var prnCheck = document.getElementById("prnCheck").checked;
    document.getElementById("hiddenPrnCheck").value = "T";
    if (prnCheck) {
//        document.getElementById("prnCheck").disabled = true;
    } else {

    }
}

function fetchFileDashboardDetails() {
    var cp = document.getElementById("globalcontextpath").value;
    $('#countModal3').modal('hide');
    showProcessIndicator();
    var xmlhttp;
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    } else {
// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            var q = xmlhttp.responseText;
            q = q.trim();
            if (!lhsIsNull(q)) {
                document.getElementById("fileDashboardPanelhide").style.display = "none";
                document.getElementById("fileDashboardPanel").innerHTML = q;
                // reload file details
            }
            hideProcessIndicator();
         //   $('#countModal3').modal('hide');
            $('.countModal-btn3').prop('disabled', true);
        }
       
    };
    //    document.getElementById("process_indicator_id1").style.display = "block";
    var url = cp + "/fvuFileDashboard?";
    xmlhttp.open("GET", url);
    xmlhttp.send(null);
   
    // $('#countModal3').modal('hide');
}// end method

function getFileToDownload() {
    showProcessIndicator();
    $.ajax({
        url: "fileToDwnldAction",
        type: 'GET',
        success: function (data, textStatus, jqXHR) {
            $("#dwnldBtnRow").html(data);
            hideProcessIndicator();
        }
    });
}// end method
function getFileDownload() {
    showProcessIndicator();
    $.ajax({
        url: "filetListAction",
        type: 'GET',
        success: function (data, textStatus, jqXHR) {
            $("#downloadFileTable").html(data);
            hideProcessIndicator();

        }
    });
}// end method

function deductorInfoChange() {

    var deductorCheck = document.getElementById("deductorCheck").checked;
    if (deductorCheck) {
// all fields editable
        document.getElementById("hidden_deductor_name").value = document.getElementById("deductor_name").value;
        document.getElementById("hidden_deductor_pan").value = document.getElementById("deductor_pan").value;
        document.getElementById("hidden_deductor_desig").value = document.getElementById("deductor_desig").value;
        document.getElementById("hidden_deductor_mobile").value = document.getElementById("deductor_mobile").value;

        document.getElementById("deductor_name").disabled = false;
        document.getElementById("deductor_pan").disabled = false;
        document.getElementById("deductor_desig").disabled = false;
        document.getElementById("deductor_mobile").disabled = false;
    } else {
// all fields uneditable
        document.getElementById("deductor_name").disabled = true;
        document.getElementById("deductor_pan").disabled = true;
        document.getElementById("deductor_desig").disabled = true;
        document.getElementById("deductor_mobile").disabled = true;

        document.getElementById("deductor_name").value = document.getElementById("hidden_deductor_name").value;
        document.getElementById("deductor_pan").value = document.getElementById("hidden_deductor_pan").value;
        document.getElementById("deductor_desig").value = document.getElementById("hidden_deductor_desig").value;
        document.getElementById("deductor_mobile").value = document.getElementById("hidden_deductor_mobile").value;
    }
}

function getGeneratedFVUFileLoc() {
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
        removeError();
        var hiddenAddCheck = "F";
        if (!deductorCheck) {
            hiddenAddCheck = "T";
        }
        var cp = document.getElementById("globalcontextpath").value;
        var url = cp + "/fvuAjaxAction?action=responsiblePersonDetails&deductorName=" + deductor_name;
        url += "&deductorPan=" + deductor_pan;
        url += "&deductorDesig=" + deductor_desig;
        url += "&deductorMobile=" + deductor_mobile;
        url += "&prn_no=" + encodeURIComponent(prn_no);
        url += "&hiddenPrnCheck=" + encodeURIComponent(hiddenPrnCheck);
        url += "&hiddenAddCheck=" + encodeURIComponent(hiddenAddCheck);
        var formData = new FormData($("form#uploadCSIFiles")[0]);
        $.ajax({
            url: url,
            type: 'POST',
            data: formData,
            async: false,
            success: function (data) {
                if (data === 'success') {
                    //  proccessingWindow();
                    document.getElementById("validateFlag").value = "true";
                    document.getElementById("generate_file").submit();
                } else {
                    addActionError("error", data);
                }
            },
            cache: false,
            contentType: false,
            processData: false
        });
    }
}//end function

function validateResponsiblePersonPANNo(value) {
    if (value === "PANNOTAVBL") {
        removeError();
        return true;
    } else {
        if (!lhsIsNull(value)) {
            var Exp = /^([a-zA-Z]){3}(p|P){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
//            var Exp = /^([a-zA-Z]){3}(g|G|c|C|l|L|a|A|t|T|j|J|b|B|p|P|f|F|h|H){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
            if (!Exp.test(value)) {
                if (value.toUpperCase() === "PANNOTREQD") {
                    removeError();
                    return true;
                } else {
//                    $("#deductor_notification").attr("class", "errorMessage");
                    addActionError("message", "Invalid Responsible Person PAN Number");
                    document.getElementById("deductor_pan").focus();
//                    scrollPage();
                    return false;
                }
            } else {
                removeError();
                return true;
            }
        } else {
            addActionError("message", "Responsible person is mandatory");
            return false;
        }
    }
}//end function

