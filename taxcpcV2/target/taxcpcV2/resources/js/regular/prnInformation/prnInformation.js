/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function updatePrnNo(formid) {
    var splitId = formid.split("~")[1];
    var file_upload_ack_no = document.getElementById("file_upload_ack_no~" + splitId).value;
    var file_upload_ack_date = document.getElementById("file_upload_ack_date~" + splitId).value;
    var prnFile = document.getElementById("prnFile~" + splitId).value;
    var fvuFileGeneratedFlag = document.getElementById("fvuFileGeneratedFlag~" + splitId).value;

    if (lhsIsNull(file_upload_ack_no)) {
        addActionError("error", "Please enter file upload Ack. No.");

    } else if (lhsIsNull(file_upload_ack_date)) {
        addActionError("error", "Please select file upload Ack. Date");

    } else if (lhsIsNull(prnFile)) {
        addActionError("error", "Please select pdf file");

    } else {
        if (fvuFileGeneratedFlag === "N") {
            document.getElementById("dialog-alert-generate-fvu").style.display = "block";
            $(function () {
                $("#dialog-alert-generate-fvu").dialog({
                    modal: true,
                    buttons: {
                        "Yes": function () {
                            document.getElementById(formid).submit();
                        },
                        "No": function () {
                            $("#dialog-alert-generate-fvu").dialog("close");
                        }
                    }
                });
            });
        } else {
            document.getElementById(formid).submit();
        }
    }
}//end function

function processRecords() {
    
    var cp = document.getElementById("globalcontextpath").value;
    var process_prn = $('input[name="process_prn"]:checked').val();
//    document.getElementById("dialog-confirm-refresh-append-prn").style.display = "block";
    if (process_prn === 'refresh') {
        $("#dialog-confirm-refresh-append-prn").modal('hide');
        var url = cp + "/updatePrnAction?action=refreshRecords&processType=D";
//                    document.getElementById("global-process-indicator").style.visibility = "visible";
        $.ajax({
            url: url,
            type: "GET",
            beforeSend: function (xhr) {
                showProcessIndicator();
            },
            success: function (data) {
                if (data !== null && data !== "" && data !== "null") {
                    if (data === "0") {
                        addActionError("message", "Record refreshed successfully");
                       // $("#dialog-confirm-refresh-append-prn").modal('hide');
                    } else {
                        addActionError("message", "Record refreshed successfully");
                        //$("#dialog-confirm-refresh-append-prn").modal('hide');
                    }
                } else {
                    addActionError("message", "Some error occurred");
                    $("#dialog-repressPrnDialog").modal('hide');
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                hideProcessIndicator();
            },
            complete: function (jqXHR, textStatus) {
                hideProcessIndicator();
            }
        });
    }else if(process_prn === 'append'){
        var url = cp + "/updatePrnAction?action=refreshRecords&processType=A";
        $("#dialog-confirm-refresh-append-prn").modal('hide');
//                    document.getElementById("global-process-indicator").style.visibility = "visible";
        $.ajax({
            url: url,
            type: "GET",
            beforeSend: function (xhr) {
                showProcessIndicator();
            },
            success: function (data) {
                if (data !== null && data !== "" && data !== "null") {
                    if (data === "0") {
                        addActionError("message", "Record appended successfully");
                       // $("#dialog-confirm-refresh-append-prn").modal('hide');
                    } else {
                        addActionError("message", "Some error occurred");
                       // $("#dialog-confirm-refresh-append-prn").modal('hide');
                    }
                } else {
                    addActionError("message", "Some error occurred");
                    $("#dialog-confirm-refresh-append-prn").modal('hide');
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                hideProcessIndicator();
            },
            complete: function (jqXHR, textStatus) {
                hideProcessIndicator();
            }
        });
    }
}

//function refreshRecords() {
//    var cp = document.getElementById("globalcontextpath").value;
////    var processLevel = document.getElementById("processLevel").value;s
//
//    document.getElementById("dialog-confirm-refresh-append-prn").style.display = "block";
//    $(function () {
//        $("#dialog-confirm-refresh-append-prn").dialog({
//            modal: true,
//            buttons: {
//                "Refresh": function () {
//                    var url = cp + "/updatePrnAction?action=refreshRecords&processType=D";
////                    document.getElementById("global-process-indicator").style.visibility = "visible";
//                    $.ajax({
//                        url: url,
//                        type: "GET",
//                        success: function (data) {
//                            if (data !== null && data !== "" && data !== "null") {
//                                if (data === "0") {
//                                    addActionError("message", "Record refreshed successfully");
//                                    $("#dialog-confirm-refresh-append-prn").dialog("close");
//                                } else {
//                                    addActionError("message", "Record refreshed successfully");
//                                    $("#dialog-confirm-refresh-append-prn").dialog("close");
//                                }
//                            } else {
//                                addActionError("message", "Some error occurred");
//                                $("#dialog-repressPrnDialog").dialog("close");
//                            }
//                        }
//                    });
//                },
//                "Append": function () {
//                    var url = cp + "/updatePrnAction?action=refreshRecords&processType=A";
////                    document.getElementById("global-process-indicator").style.visibility = "visible";
//                    $.ajax({
//                        url: url,
//                        type: "GET",
//                        success: function (data) {
//                            if (data !== null && data !== "" && data !== "null") {
//                                if (data === "0") {
//                                    addActionError("message", "Record appended successfully");
//                                    $("#dialog-confirm-refresh-append-prn").dialog("close");
//                                } else {
//                                    addActionError("message", "Some error occurred");
//                                    $("#dialog-confirm-refresh-append-prn").dialog("close");
//                                }
//                            } else {
//                                addActionError("message", "Some error occurred");
//                                $("#dialog-confirm-refresh-append-prn").dialog("close");
//                            }
//                        }
//                    });
//                }
//            }
//        });
//    });
//}//end function

function onloadMsg() {
    var msg = document.getElementById("msg").value;
    if (!lhsIsNull(msg)) {
        if (msg === "S") {
            removeError();
            addActionError("message", "Record Updated Successfully");
        } else if (msg === "DS") {
            removeError();
            addActionError("message", "Record Reverted Successfully");
        } else {
            removeError();
            addActionError("message", "Some Error Occurred");
        }
    }
}//end function

function downloadPrn(formid) {
    document.getElementById(formid).submit();
}//end function

function deletePrn(formid) {
    document.getElementById("dialog-confirm-revert").style.display = "block";
    $(function () {
        $("#dialog-confirm-revert").dialog({
            modal: true,
            buttons: {
                "Yes": function () {
                    document.getElementById(formid).submit();
                },
                "No": function () {
                    $("#dialog-confirm-revert").dialog("close");
                }
            }
        });
    });
}//end function

//function setProcessLevel() {
//    let processLevel = document.getElementById("clientTypeDefault").checked ? "0" : "1";
//
//    try {
//        document.getElementById("processLevel").value = processLevel;
//    } catch (e) {
//    }
//
//}//end function


