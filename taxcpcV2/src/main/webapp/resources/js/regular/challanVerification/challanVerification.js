/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var ajax_call_enabled = false;

function onClickVerifyChallan() {
    showProcessIndicator();
    var cp = document.getElementById("globalcontextpath").value;
    var URLString = cp + "/checkCSIStatusForChallan";// old action - forFVUFileAjaxAction
    if (!ajax_call_enabled) {
        ajax_call_enabled = true;
        $.ajax({
            url: URLString,
            type: 'POST',
            success: function (data) {
//            alert(data)
                if (!lhsIsNull(data) && data === 'true') {
                    verifyChallan();
                } else {
//                showUploadCsiWindow();
                    $('#challanUploadCSIFile').modal('show');
                    hideProcessIndicator();
                }
                ajax_call_enabled = false;
            }, error: function (jqXHR, textStatus, errorThrown) {
                hideProcessIndicator();
                ajax_call_enabled = false;
            }
        });
    }
}// end method

function verifyChallan() {
    showProcessIndicator();
    var cp = document.getElementById("globalcontextpath").value;
    var URLString = cp + "/verifyChallanAction?validateFlag=true";// old action - runBatchFileActionNew
    if (!ajax_call_enabled) {
        ajax_call_enabled = true;
        $.ajax({
            url: URLString,
            type: 'POST',
            data: {
                search: "true"
            },
            success: function (data) {
//                alert(data);
                if (data !== "" && data !== null && data !== "null") {
                    var parsedData = $.parseJSON(data);
//                    alert(parsedData.Verification)
                    if (parsedData !== null) {
                        var verified = parsedData.Verification;
                        if (verified === "success") {
                            var totalChallanCount = parsedData.TotalChallanCount;
                            var matchedChallanCount = parsedData.MatchedChallanCount;
                            var unMatchedChallanCount = parsedData.UnMatchedChallanCount;

                            document.getElementById("totalChallanID").innerHTML = totalChallanCount;
                            document.getElementById("matchedChallanID").innerHTML = matchedChallanCount;
                            document.getElementById("unmatchedChallanID").innerHTML = unMatchedChallanCount;
                            $('#challanVerifyDetails').modal('show');
                        } else if (verified === "nochallan") {
                            window.location.href = cp + "/challanErrorPage?action=NOCHALLANS";
                        } else if (verified === "errorFileGenerated") {
                            window.location.href = cp + "/challanErrorPage?action=errorFileGenerated";
                        } else if (verified === "csiFileNotGenerated") {
                            window.location.href = cp + "/challanErrorPage?action=csiFileNotGenerated";
                        } else if (verified === "deducteeNameNotMatch") {
                            window.location.href = cp + "/challanErrorPage?action=deducteeNameNotMatch";
                        } else if (verified === "driveError") {
                            window.location.href = cp + "/challanErrorPage?action=driveError";
                        } else {
                            window.location.href = cp + "/challanErrorPage?action=ERROR";
                        }
                    }
                    hideProcessIndicator();
                    ajax_call_enabled = false;
                }
            }
        });
    }
}//end function

function uploadCSIFile() {
    var file = document.getElementById("csiTemplate").value;
    var dataSplit = file.split("\.");
    var errMsg = "";
    var errFlag = false;
    if (file === null || file === "") {
        errFlag = true;
        errMsg = "Please select valid .csi file first";
    } else if (dataSplit[1] !== 'csi') {
        errFlag = true;
        errMsg = "Please select valid .csi file";
    } else {
        var cp = document.getElementById("globalcontextpath").value;
        var url = cp + "/challanUploadCsiAction?validate=true";
        var formData = new FormData($("form#uploadCSIFiles")[0]);
        showProcessIndicator();
        if (!ajax_call_enabled) {
            ajax_call_enabled = true;
            $.ajax({
                url: url,
                type: 'POST',
                data: formData,
                async: false,
                success: function (data) {
                    if (!lhsIsNull(data)) {
                        if (data === 'success') {
                            openSuccessModal("Upload File Successfully");
                            $('#challanUploadCSIFile').modal('hide');
//                            closeUploadCsiWindow();
                            verifyChallan();
                        } else {
                            openErrorModal("Could not upload file");
                            $('#challanUploadCSIFile').modal('hide');
                        }
                        hideProcessIndicator();
                        ajax_call_enabled = false;
                    }
                }, error: function (jqXHR, textStatus, errorThrown) {
                    hideProcessIndicator();
                    ajax_call_enabled = false;
                },
                cache: false,
                contentType: false,
                processData: false
            });
        }
    }
    if (errFlag && !lhsIsNull(errMsg)) {
        $('#challanUploadCSIFile').modal('hide');
        openErrorModal(errMsg);
    }
}// end method
