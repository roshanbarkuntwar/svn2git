function showFilter() {

    var filterDivId = document.getElementById('filterDivId');
    if (filterDivId.style.display === 'none') {
        filterDivId.style.display = 'block';
    } else {
        filterDivId.style.display = 'none';
    }
}

function selectAllRow() {
    var checkAll = document.getElementById('checkAll');
    var checkBoxList = document.getElementsByClassName('forSelectAll');
    for (var i = 0; i < checkBoxList.length; i++) {
        checkBoxList[i].value = checkAll.checked ? 'true' : '';
        checkBoxList[i].checked = checkAll.checked;
    }
}//End Function

function forSelectAllValidate() {
    var checkBoxList = document.getElementsByClassName('forSelectAll');

    var returnVal = false;
    for (var i = 0; i < checkBoxList.length; i++) {
        if (checkBoxList[i].checked) {
            returnVal = true;
            break;
        }
    }
    return returnVal;
}

function openActionDiv() {
    try {
        var checkedBoxes = $('.forSelectAll:checked').get();
        var allocationStatus = $('#allocatedFlag').val();
    } catch (e) {
    }

    if (allocationStatus === 'allocated') {
        $("#unAllocateBtn").removeClass('d-none');
        $("#allocateBtn").addClass('d-none');
    } else if (allocationStatus === 'notAllocated') {
        $("#unAllocateBtn").addClass('d-none');
        $("#allocateBtn").removeClass('d-none');
    } else {
        $("#allocateBtn").removeClass('d-none');
        $("#unAllocateBtn").removeClass('d-none');
    }

if(allocationStatus === 'notAllocatedAFlag'){
    
    
     if (checkedBoxes.length === 0) {
       $(".action-section").hide(true);
     }else{
       $(".action-section").show(true);
     }
   document.getElementById("viewCertificateBtn").style.display = 'none';
   document.getElementById("unAllocateBtn").style.display = 'none';
   document.getElementById("allocateBtn").style.display = 'none';
   document.getElementById("update-rec").style.display = 'inline-block';
   
   
    
}else{
   document.getElementById("viewCertificateBtn").style.display = 'inline-block';
   document.getElementById("unAllocateBtn").style.display = 'inline-block';
   document.getElementById("allocateBtn").style.display = 'inline-block';
   document.getElementById("update-rec").style.display = 'none';

    if (checkedBoxes.length === 1) {
        let rowIndex = checkedBoxes[0].id.split('~')[1];
        $("#viewCertificateId").val(rowIndex);

        $("#viewCertificateBtn").removeClass('d-none');
        $(".action-section").show(true);
    } else if (forSelectAllValidate()) {
        $(".action-section").show(true);
        $("#viewCertificateBtn").addClass('d-none');
    } else {
        $(".action-section").hide(true);
    }

}

}

function submitAllAllocate() {

    if (!forSelectAllValidate()) {
        addActionError("message", "Please select records.");
    } else {
        var postData = $('#lowDeduCertifiAllocationForm').serialize();
        var cp = document.getElementById("globalcontextpath").value;
        var url = cp + "/lowDeduCertifiAllocaAJAX?action=submitAllAllocate";
        $.ajax({
            url: url,
            type: "POST",
            data: postData,
            success: function (data, textStatus, jqXHR)
            {
                if (data === 'error') {
                    openErrorModal("Some Error Occured, Could Not update");
                } else {
                    openSuccessModal("Record(s) allocated successfully.", 'window.location.reload();');
                }
            },
            error: function (jqXHR, textStatus, errorThrown)
            {

            }
        });
    }

}

function submitAllNotAllocate() {

    if (!forSelectAllValidate()) {
        addActionError("message", "Select Record");

    } else {
        var postData = $('#lowDeduCertifiAllocationForm').serialize();
        var cp = document.getElementById("globalcontextpath").value;
        var url = cp + "/lowDeduCertifiAllocaAJAX?action=submitAllUnAllocate";

        $.ajax({
            url: url,
            type: "POST",
            data: postData,
            success: function (data, textStatus, jqXHR)
            {
                if (data === 'error') {
                    openErrorModal("Some Error Occured, Could Not update");
                } else {
                    openSuccessModal("Record(s) unallocated successfully.", 'window.location.reload()');
                }

                // document.getElementById("global-process-indicator").style.visibility = "hidden";
            },
            error: function (jqXHR, textStatus, errorThrown)
            {
//                ajax_call_enabled = false; //if fails
//                document.getElementById("global-process-indicator").style.visibility = "hidden";
            }
        });
    }

}//End Function

function setCertificate(idNo) {

    var selectedCertificatedRowNo = document.getElementById('selectedCertificatedRowNo').value;

    var certificate_noModal = document.getElementById('certificate_noModal~' + idNo).value;
    var validFromModal = document.getElementById('validFromModal~' + idNo).value;
    var tdsLimitAmtModal = document.getElementById('tdsLimitAmtModal~' + idNo).value;

    document.getElementById('certificate_no~' + selectedCertificatedRowNo).value = certificate_noModal;
    document.getElementById('validFrom~' + selectedCertificatedRowNo).value = validFromModal;
    document.getElementById('crt_amt~' + selectedCertificatedRowNo).value = tdsLimitAmtModal;
    $('#showCertificateListModal').modal('hide');

}

function showCertificateList(idNo) {
    var entity_code = document.getElementById('entity_code~' + idNo).value;
    var client_code = document.getElementById('client_code~' + idNo).value;
    var acc_year = document.getElementById('acc_year~' + idNo).value;
    var tds_type_code = document.getElementById('tds_type_code~' + idNo).value;
    var tds_code = document.getElementById('tds_code~' + idNo).value;
    var certificate_no = document.getElementById('certificate_no~' + idNo).value;
    var quarter_no = document.getElementById('quarter_no~' + idNo).value;
    var deductee_panno = document.getElementById('deductee_panno~' + idNo).value;
    document.getElementById('selectedCertificatedRowNo').value = idNo;
    var cp = document.getElementById("globalcontextpath").value;

    var url = cp + "/lowDeduCertifiAllocaAJAX?action=showCertificateList";
    url += "&entity.client_code=" + client_code;
    url += "&entity.entity_code=" + entity_code;
    url += "&entity.acc_year=" + acc_year;
    url += "&entity.tds_type_code=" + tds_type_code;
    url += "&entity.deductee_panno=" + deductee_panno;
    url += "&entity.tds_code=" + tds_code;
    url += "&entity.certificate_no=" + certificate_no;
    url += "&entity.quarter_no=" + quarter_no;

    showProcessIndicator();
    $.ajax({
        url: url,
        type: "POST",
        success: function (data, textStatus, jqXHR) {
            if (data === 'error') {
                document.getElementById('showCertificateListModalDivId').innerHTML =
                        '<table><tr class=\"text-center\"><td colspan=\"12\">No records found.</td></tr></table>';
            } else {
                document.getElementById('showCertificateListModalDivId').innerHTML = data;
                $('#showCertificateListModal').modal({show: true});
            }
            hideProcessIndicator();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            hideProcessIndicator();
        }
    });

}//End Function

function allocatedCertificate(idNo) {

    var entity_code = document.getElementById('entity_code~' + idNo).value;
    var client_code = document.getElementById('client_code~' + idNo).value;
    var acc_year = document.getElementById('acc_year~' + idNo).value;
    var quarter_no = document.getElementById('quarter_no~' + idNo).value;
    var tds_type_code = document.getElementById('tds_type_code~' + idNo).value;
    var tds_code = document.getElementById('tds_code~' + idNo).value;
    var certificate_no = document.getElementById('certificate_no~' + idNo).value;
    var deductee_panno = document.getElementById('deductee_panno~' + idNo).value;

    if (lhsIsNull(certificate_no)) {
        addActionError("Select Certificate");
    } else {

        var cp = document.getElementById("globalcontextpath").value;
        var url = cp + "/lowDeduCertifiAllocaAJAX?action=updateCertificate";
        url += "&entity.certificate_no=" + certificate_no;
        url += "&entity.client_code=" + client_code;
        url += "&entity.entity_code=" + entity_code;
        url += "&entity.acc_year=" + acc_year;
        url += "&entity.quarter_no=" + quarter_no;
        url += "&entity.tds_type_code=" + tds_type_code;
        url += "&entity.deductee_panno=" + deductee_panno;
        url += "&entity.tds_code=" + tds_code;

        showProcessIndicator();
        $.ajax({
            url: url,
            type: "POST",
            success: function (data, textStatus, jqXHR)
            {
                if (data === 'error') {
                    openErrorModal("Some Error Occured, Could Not update");
                } else {
                    openSuccessModal(data + " Record Updated", "window.location.reload();");

                }
                hideProcessIndicator();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                hideProcessIndicator();
            }
        });
    }
}//End Function

function validatePAN(field) {
    var value = field.value;
    if (value === "PANNOTREQD" || value === "pannotreqd") {
        return true;
    } else {
        if (!lhsIsNull(value)) {
//            var Exp = /^([a-zA-Z]){3}(a|A|p|P|f|F|c|C|h|H|t|T){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
            var Exp = /^([a-zA-Z]){3}(a|A|b|B|c|C|f|F|g|G|h|H|j|J|k|K|l|L|p|P|t|T){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
            if (!Exp.test(value)) {
                openErrorModal("Invalid Pan");
                field.value = "";
                return false;
            }
        }
    }
}//end function

function convertToUppercase() {
    var input = document.getElementById('deducteePanNo');
    input.onkeyup = function () {
        this.value = this.value.toUpperCase();
    }
}


function updateNotAflagRecord() {

    if (!forSelectAllValidate()) {
        addActionError("message", "Please select records.");
    } else {
        var postData = $('#lowDeduCertifiAllocationForm').serialize();
        var cp = document.getElementById("globalcontextpath").value;
        var url = cp + "/lowDeduCertifiAllocaAJAX?action=updatenotAllocatedAFlag";
        $.ajax({
            url: url,
            type: "POST",
            data: postData,
            success: function (data, textStatus, jqXHR)
            {
                if (data === 'error') {
                    openErrorModal("Some Error Occured, Could Not update");
                } else {
                    openSuccessModal("Record(s) Updated successfully.", 'window.location.reload();');
                }
            },
            error: function (jqXHR, textStatus, errorThrown)
            {

            }
        });
    }

}


function validateLowDeduCertiFilter() {
    var allocatedFlag = $("#allocatedFlag").val();
    var certificateNo = $("#certificateNo").val();
    var deducteePanNo = $("#deducteePanNo").val();
    
//    if (lhsIsNull(allocatedFlag) && lhsIsNull(certificateNo) && lhsIsNull(deducteePanNo) ) {
//        openErrorModal("Please select any filter to see the records !");
//    } else {
        showRecordsPaginator();
  //  }
}