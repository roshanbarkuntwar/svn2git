/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function callEntryPage() {
    var cp = $("#globalcontextpath").val();
    window.location = cp + "/miscellaneousCIN";

}
function callBrowsePage() {
    var cp = $("#globalcontextpath").val();
    window.location = cp + "/miscellaneous";

}

function callCheckerMakerPage() {
    var cp = $("#globalcontextpath").val();
    window.location = cp + "/checkerMaker";
}

function validateCheckerMakerFilter() {
    var panno = $("#deductee_panno").val();
    var name = $("#dedcutee_name").val();
    var status = $("#authStatusFlag").val();
    if (lhsIsNull(panno) && lhsIsNull(name) && lhsIsNull(status)) {
        openErrorModal("Please select any filter to see the records!");
    } else {
        showRecordsPaginator();
    }
}

function updateTranLoad(selectedId, rowid, validToApprove) {
    //alert("Id-------------->"+selectedId.value);
    //alert("RowId-------------->"+rowid);
    if (!lhsIsNull(validToApprove) && !validToApprove) {
        openErrorModal("Approved Entry can be UnApproved in the month of approval only. Please Contact TDS Section.");
        return;
    }
    var rowSeq = rowid;
    var selectedVal = selectedId.value;
    //var cp = document.getElementById("globalcontextpath").value;
    document.getElementById("dialog-confirm_update").style.display = "block";
    $(function () {
        $("#dialog-confirm_update").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {

                "Ok": function () {
                    $(this).dialog("close");
                    $.ajax({

                        url: "./updateSingleTranLoadRecord?action=updateSingleTranLoad" + "&rowidSeq=" + encodeURIComponent(rowSeq) + "&flag=" + encodeURIComponent(selectedVal),
                        //data : rowIdSeq,
                        type: "GET",
                        success: function (response, textStatus, jqXHR) {
                            if (response === "updateRecord") {
                                //openSuccessModal("Record Updated Successfully...!","callUrl('/miscellaneous');");
                                openSuccessModal("Record Updated Successfully...!", "validateCheckerMakerFilter();");
                            } else {
                                openErrorModal("Some Error Occured...!");
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                        }
                    });

                },
                Cancel: function () {
                    $(this).dialog("close");
                }
            }
        });
    });

}


function updateBulkRecord(value, status, pan, name) {
    var flagVal = value;
    var status = status;
    var panno = pan;
    var dName = name;
    document.getElementById("dialog-confirm_update").style.display = "block";
    $(function () {
        $("#dialog-confirm_update").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {

                "Ok": function () {
                    $(this).dialog("close");

                    $.ajax({

                        url: "./updateBulkTranLoadRecord?action=updateBulkTranLoad" + "&flag=" + encodeURIComponent(flagVal) + "&panno=" + encodeURIComponent(panno) + "&dName=" + encodeURIComponent(dName) + "&authStatus=" + encodeURIComponent(status),
                        type: "GET",
                        success: function (response, textStatus, jqXHR) {
                            if (response === "updateRecord") {
                                //openSuccessModal("Record Updated Successfully...!","callUrl('/miscellaneous');");
                                openSuccessModal("Record Updated Successfully...!", "validateCheckerMakerFilter();");
                            } else {
                                openErrorModal("Some Error Occured...!");
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                        }
                    });

                },
                Cancel: function () {
                    $(this).dialog("close");
                }
            }
        });
    });
}

