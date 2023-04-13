/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function downloadErrorDetails(downloadBtn) {
    downloadBtn.disabled = true;

    var contextPath = document.getElementById("globalcontextpath").value;
    var codeLevel = document.getElementById("selectedUserLevelCode").value;

    var url = contextPath + "/refreshErrorProcessTokenStatus?action=downloadError&codeLevel=" + encodeURIComponent(codeLevel);
//    alert(url);
    $.ajax({
        url: url,
        type: 'GET',
        beforeSend: function (xhr) {
            showProcessIndicator();
        },
        success: function (data, textStatus, jqXHR) {
            if (!lhsIsNull(data)) {
                callDedicatedProcedure({
                    callingProcName: "ProcTranLoadErrorDwnld",
                    tokenNo: data.trim(),
                    processLevel: parseInt(codeLevel)
                });

                openSuccessModal("Your process is intitiated. Token No: " + data);

            } else {
                openErrorModal("Some Error Occurred ! Could Not Initiate Process!");
            }
        },
        error: function (xhr) {
            hideProcessIndicator();
        },
        complete: function (jqXHR, textStatus) {
            hideProcessIndicator();
            downloadBtn.disabled = false;
        }
    });
}


function getErrorDetails() {
    var contextPath = document.getElementById("globalcontextpath").value;
    $('#countModal1').modal('hide');
    showProcessIndicator();

    $.ajax({
        url: contextPath + "/getPANDetailsAction",
        type: 'POST',

        success: function (data, textStatus, jqXHR) {
            //         document.getElementById("unverifiedPanBody").style.display = "none";
            //document.getElementById("global-process-indicator1").style.display = "none";
            if (data !== "" && data !== null && data !== "null") {
//                getFileDashboardDetails();
                var str = data.split("#");
                //alert(str);
                if (str[0] !== '0') {
                    document.getElementById("unverifiedPAN").innerHTML = str[0];
                } else {
                    document.getElementById("unverifiedPAN").innerHTML = 0;
//                    document.getElementById("unverifiedPanBody").style.display = "none";
                }
                if (str[1] !== '0') {
                    document.getElementById("challannotverified").innerHTML = str[1];
                } else {
                    document.getElementById("challannotverified").innerHTML = 0;
//                    document.getElementById("unverifiedChallanBody").style.display = "none";
                }

                if (str[2] !== '0') {
                    document.getElementById("lowerdeductionnotverified").innerHTML = str[2];
                } else {
                    document.getElementById("lowerdeductionnotverified").innerHTML = 0;
//                    document.getElementById("lowerDeducteeBody").style.display = "none";
                }

                if (str[4] !== '0') {
                    document.getElementById("deducteenotallocated").innerHTML = str[4];
                } else {
                    document.getElementById("deducteenotallocated").innerHTML = 0;
//                    document.getElementById("deducteeBody").style.display = "none";
                }
            } else {
                openErrorModal("No Record Found.");
            }


        },
        error: function (jqXHR, textStatus, errorThrown) {
            hideProcessIndicator();
            //  $('#countModal1').modal('hide');
        },
        complete: function (jqXHR, textStatus) {
            hideProcessIndicator();
            //   $('#countModal1').modal('hide');
            $('.countModal-btn2').prop('disabled', true);
        }
    });
    hideProcessIndicator();
}//end function

function getFileDashboardDetails() {
    var contextPath = document.getElementById("globalcontextpath").value;
    var url = contextPath + "/tdsProcessData";
//    alert(url);
    $('#countModal').modal('hide');
    showProcessIndicator();
    $.ajax({
        url: url,
        type: 'POST',
        success: function (data, textStatus, jqXHR) {

            document.getElementById("processDataPanelhide").style.display = "none";
            document.getElementById("processDataDashboardPanel").innerHTML = data;

            // $("#countModal").hide(false);

        },
        error: function (jqXHR, textStatus, errorThrown) {
            hideProcessIndicator();
            //$('#countModal').modal('hide');
        },
        complete: function (jqXHR, textStatus) {
            hideProcessIndicator();
            // $('#countModal').modal('hide');
            $('.countModal-btn1').prop('disabled', true);
        }
    });

}// end method

function getPanResponse(url1) {
    if (url1 != "") {
        parent.document.getElementById("PanVerificationLink_ModalIframe").src = url1;
    }
}// end function

function processErrorParameters(btnId) {
    var vauation_right = document.getElementById("h_vauation_right").value;
    if (vauation_right === "" && vauation_right === "null" && vauation_right === null) {
        vauation_right = "0";
    }
    vauation_right = parseInt(vauation_right);
    var clientType = document.getElementById("clientType").checked;
    var processCnfChkBxError = document.getElementById("processCnfChkBxError").checked;


    if (processCnfChkBxError) {
        document.getElementById("reprocessFlag").value = "T";
    } else {
        document.getElementById("reprocessFlag").value = "F";
    }
    if (clientType) {
        document.getElementById("loginLevelFlag").value = "0";
    } else {
        document.getElementById("loginLevelFlag").value = "1";
    }

    //generating token for process error
    var loginLevelFlag = document.getElementById("loginLevelFlag").value;
    var actionUrl = "/generateProcessErrorToken?action=getToken&loginLevelFlag=" + encodeURIComponent(loginLevelFlag);
    ajaxPostUrl(actionUrl, generateProcessErrorTokenResponse);

    function generateProcessErrorTokenResponse(response) {
        if (!lhsIsNull(response)) {
            var res = response.split('##');
            if (res[0].toLowerCase().match('success')) {
                var tokenNo = res[1].trim();
                document.getElementById("tokenNo").value = tokenNo;

                let client_login_level = document.getElementById("loginLevelFlag").value;
                let errorType = document.getElementById("errorTypeProc").value;

                try {
                    callDedicatedProcedure({
                        callingProcName: "ProcessTdsTranLoadError",
                        tokenNo: tokenNo,
                        clientLoginLevel: client_login_level,
                        errorType: errorType
                    });
                } catch (e) {
                    console.log(e);
                }


                //process error show status.....
//                openSuccessModal("Token generated Successfully. Token No.: " + tokenNo, 'document.getElementById("tdsProcessErrorForm").submit();');
                openSuccessModal("Token generated Successfully. Token No.: " + tokenNo, 'callUrl("/errorStatus");');
            } else {
                openErrorModal("Token could not be generated.");
            }
        } else {
            openErrorModal("Some error occurred, please try again.");
        }
    }//end function

}// end method

function reprocessErrorParameters() {

    var cp = document.getElementById("globalcontextpath").value;
    var upload_purpose = document.getElementById("upload_purpose").value;
    var l_error_process_type = document.getElementById("l_error_process_type").value;
    var processCnfChkBx = document.getElementById("processCnfChkBxID").value;
    var loginLevel = document.getElementById("loginLevel");
    var loginLevelFlag = document.getElementById("loginLevelFlag").value;
    var errorTypeProc = document.getElementById("errorTypeProc").value;
    var url = "/tdsProcessError?validate=true"
            + "&upload_purpose=" + encodeURIComponent(upload_purpose)
            + "&l_error_process_type=" + encodeURIComponent(l_error_process_type)
            + "&processCnfChkBx=" + encodeURIComponent(processCnfChkBx)
            + "&loginLevel=" + encodeURIComponent(loginLevel)
            + "&loginLevelFlag=" + encodeURIComponent(loginLevelFlag)
            + "&errorTypeProc=" + encodeURIComponent(errorTypeProc)
            + "&reprocessFlag=T";

    callUrl(url);
}//end function

function getDynamicErrorDetailsAction(processLevel) {
    var actionUrl = "/getDynamicErrorDetails?processLevel=" + processLevel;

    ajaxPostUrl(actionUrl, getFileDashboardDetailsResponse);
}//end function

function getFileDashboardDetailsResponse(response) {
    if (!lhsIsNull(response)) {
        document.getElementById("SectionWiseErrorDetailAndCorrectionGrid").setAttribute("style", "");
        document.getElementById("SectionWiseErrorDetailAndCorrectionGrid").innerHTML = response;
    } else {
        openErrorModal("No Record Found");
    }
}// end method

function openBulkReviewModal(id) {
    if (!lhsIsNull(id)) {
        var splitArr = id.split("~");
        var error_code = document.getElementById("error_code~" + splitArr[1]).value;
        document.getElementById("R_ErrorCodeID").value = error_code;
        document.getElementById("R_ID").value = splitArr[1];
        $('#review_remark_id').val('');
        $('#review').modal({show: true});
    }
}//end function

function getShow_error_details(id) {
    if (!lhsIsNull(id)) {
        var splitArr = id.split("~");
        var error_type_code = document.getElementById("error_type_code~" + splitArr[1]).value;
        var error_type_name = document.getElementById("error_type_name~" + splitArr[1]).value;
        var error_name = document.getElementById("error_name~" + splitArr[1]).value;
        var error_code = document.getElementById("error_code~" + splitArr[1]).value;
        var table_name = document.getElementById("table_name~" + splitArr[1]).value;
        var validate = document.getElementById("validate").value;
        var process_level = document.getElementById("selectedUserLevelCode").value;
        

        var E1 = document.getElementById("error_screen2_required~" + splitArr[1]).value;
        var E2 = document.getElementById("error_screen3_required~" + splitArr[1]).value;

        var url = "/errorDetailsSummary?action=showErrSummaryDetails";
        url += "&l_error_type_code=" + encodeURIComponent(error_type_code);
        url += "&l_error_type_name=" + encodeURIComponent(error_type_name);
        url += "&l_error_name=" + encodeURIComponent(error_name);
        url += "&l_error_code=" + encodeURIComponent(error_code);
        url += "&table_name=" + encodeURIComponent(table_name);
        url += "&processLevel=" + process_level;
       


        if (!lhsIsNull(E1)) {
            if (E1 === 'F' && E2 === 'T') {
                callUrl("/deducteeErrorDetails?action=showErrorDetails"
                        + "&l_error_type_code=" + encodeURIComponent(error_type_code)
                        + "&l_error_type_name=" + encodeURIComponent(error_type_name)
                        + "&l_error_name=" + encodeURIComponent(error_name)
                        + "&l_error_code=" + encodeURIComponent(error_code)
                        + "&table_name=" + encodeURIComponent(table_name)
                        + "&tds_code=" + encodeURIComponent("")
                        + "&deductee_code=" + encodeURIComponent("")
                        + "&deductee_panno=" + encodeURIComponent("")
                        + "&h_tran_amount=" + encodeURIComponent("")
                        + "&h_tds_amt=" + encodeURIComponent("")
                        + "&h_calc_tda_amt=" + encodeURIComponent("")
                        + "&h_diff_tds_amt=" + encodeURIComponent("")
                        + "&validate=" + encodeURIComponent(validate)
                        + "&processLevel=" + process_level
                        + "&ReadonlyFlag=false");

            } else if (E1 === 'T') {
                url += "&ReadonlyFlag=false";
                url += "&e2=" + encodeURIComponent(E2);
                callUrl(url);
            }
        } else {
            url += "&ReadonlyFlag=false";
            url += "&e2=" + encodeURIComponent(E2);
            callUrl(url);
        }
    }
}//end function

function updateErrorDetailsData() {
    var tbl = document.getElementById("table");
    var total_record = tbl.rows.length;
    var l_record_count = 0;
    for (var j = 1; j < (total_record - 2); j++) {
        var chk = document.getElementById("check~" + j).checked ? "Y" : "N";
        if (chk === "Y") {
            l_record_count++;
        }
    }
    l_record_count = parseInt(l_record_count);
    if (l_record_count > 0) {
        showProcessIndicator();
        if (!ajax_call_enabled) {
            $("#deducteeErrorBugForm").submit(function (e) {
                var postData = $(this).serializeArray();
                var formURL = $(this).attr("action");
                $.ajax({
                    url: formURL,
                    type: "POST",
                    data: postData,
                    success: function (data, textStatus, jqXHR)
                    {
                        //data: return data from server
                        ajax_call_enabled = false;
                        if (data === 'updateData') {
                            openSuccessModal("Records Updated Successfully");
                            getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);
                            hideProcessIndicator();
                        } else {
                            openErrorModal("Some Error Occured , Please Try Again");
                            hideProcessIndicator();
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown)
                    {
                        ajax_call_enabled = false;  //if fails
                        hideProcessIndicator();
                    }
                });
                e.preventDefault(); //STOP default action
                //e.unbind(); //unbind. to stop multiple form submit.
            });
            ajax_call_enabled = true;
            $("#deducteeErrorBugForm").submit(); //Submit  the FORM
        }
    } else {
        openErrorModal("Please Select Record For Update");
        hideProcessIndicator();
    }
}//end function

var textBoxValue = "";
function getTextValue(id) {
    try {
        textBoxValue = document.getElementById(id).value;
    } catch (err) {
    }
    try {
        getNumberNormalFormat(id);
    } catch (err) {
    }
}//end function

function getCheckedCheckbox(id) {
    var splitArr = id.split("~");
    var count = splitArr[1];
    var textValue = document.getElementById(id).value;
    if (textBoxValue !== textValue) {
        document.getElementById('check~' + count).checked = true;
        try {
            var checked = document.getElementById('check~' + count).checked;
            if (checked) {
                document.getElementById("editCheckBox~" + count).value = "true";
            } else {
                document.getElementById("editCheckBox~" + count).value = "false";
            }
        } catch (e) {
        }

        var readOnlyFlag = document.getElementById('ReadonlyFlag').value;
        if (readOnlyFlag === "false") {

            var oInput = document.getElementById('row~' + (parseInt(count) + 1)), oChild;
            for (var i = 0; i < oInput.childNodes.length; i++)
            {
                oChild = oInput.childNodes[i];
                try {
                    oChild.style.backgroundColor = '#B9D8F7';
                } catch (err) {
                }
//                i++;
            }
        }
    }
}//end function

function onClickCheckbox(id) {
    var idNo = id.split("~");
    var chkid = "row~" + idNo[1];
    try {
        var checked = document.getElementById(id).checked;
        if (checked) {
            document.getElementById("editCheckBox~" + idNo[1]).value = "true";
        } else {
            document.getElementById("editCheckBox~" + idNo[1]).value = "false";
        }
    } catch (e) {
    }
    var row = document.getElementById("row~" + id);
    var val = document.getElementById("recordsPerPageSelect").value;
    var oInput = document.getElementById('row~' + (parseInt(idNo[1]))),
            oChild;
    if (document.getElementById(id).checked) {
        for (i = 0; i < oInput.childNodes.length; i++) {
            oChild = oInput.childNodes[i];
            try {
                oChild.style.backgroundColor = '#B9D8F7';
            } catch (err) {
            }
//            i++;
        }
    } else {
        for (i = 0; i < oInput.childNodes.length; i++) {
            oChild = oInput.childNodes[i];
            try {
                oChild.style.backgroundColor = '';
            } catch (err) {
            }
//            i++;
        }
    }
}//end function
function openAssessment() {
    $('#review').modal('hide');
    document.getElementById("dialog-confirm_change_review").style.display = "block";

    $(function () {
        $("#dialog-confirm_change_review").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                "Yes": function () {

                    $(this).dialog("close");
                    updateBulkReview();

                },
                "No": function () {

                    $(this).dialog("close");
                }
            }
        });
    });

}
function updateBulkReview() {
    var error_code = document.getElementById("R_ErrorCodeID").value;
    var review = document.getElementById("review_remark_id").value;
    var R_ID = document.getElementById("R_ID").value;
    var chkProcessFlag = window.parent.document.getElementById("processCnfChkBxID").value;

    if (!lhsIsNull(review)) {
        var cp = document.getElementById("globalcontextpath").value;
        var xmlhttp;
        if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp = new XMLHttpRequest();
        } else {
            // code for IE6, IE5
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                removeError();
                var q = xmlhttp.responseText;
                q = q.trim();
                if (q === 'updateReview') {
                    if (chkProcessFlag !== null && chkProcessFlag !== "" && chkProcessFlag !== "null" && chkProcessFlag === 'false') {
                        window.location.reload();
                    } else {
                        try {
                            window.parent.document.getElementById("reviewDetails~" + R_ID).style.color = "green";
                        } catch (err) {
                        }
//                        $('#review').modal('hide');
                        openSuccessModal("Record Update Successfully", "window.location.reload();");
                    }
                } else {
                    openErrorModal("Some Error Occured, Could Not Update");
                }
            }
        };
        var url = cp + "/errorManipulationAction?action=BulkReview";
        url += "&ErrorCode=" + encodeURIComponent(error_code);
        url += "&ReviewValue=" + encodeURIComponent(review);
        url += "&processCnfChkBx=" + encodeURIComponent(chkProcessFlag);
        xmlhttp.open("GET", url);
        xmlhttp.send(null);
    } else {
        $('#review').modal('hide');
        openErrorModal("First Insert Review");
    }
}//end function
function openProcedureAssessment(errorType, listValue) {

    document.getElementById("dialog-confirm_change_review").style.display = "block";

    $(function () {
        $("#dialog-confirm_change_review").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                "Yes": function () {

                    $(this).dialog("close");
                    openProcedureModel(errorType, listValue);

                },
                "No": function () {

                    $(this).dialog("close");
                }
            }
        });
    });

}
function openProcedureModel(errorType, listValue) {
    if (!lhsIsNull(errorType) && !lhsIsNull(listValue)) {
        document.getElementById("errortypeBulkProc").value = errorType;
        var list = "";
        try {
            if (listValue.includes("#")) {
                list = listValue.split("#");
            } else {
                list = [listValue];
            }
            if (!lhsIsNull(list)) {
                var docfrag = document.createDocumentFragment();
                for (var i = 0; i < list.length; i++)
                {
                    var key = list[i].split("-")[1];
                    var value = list[i].split("-")[0];
                    docfrag.appendChild(new Option(key, value));
                }
                var select = document.getElementById("errorFlagProc");
                $('#errorFlagProc')
                        .empty();
                select.appendChild(docfrag);
            }
        } catch (err) {
            console.log("errr--" + err);
        }
        $('#modalBulkProcedure').modal({show: true});
    } else if (!lhsIsNull(errorType) && lhsIsNull(listValue)) {
        callProcedure(errorType);
    }
}//end function

function callProcedure(errorType) {
    showProcessIndicator();
    $(function () {
        $("#dialog-confirm_call_bulk_update").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                "YES": function () {
                    $(this).dialog("close");
                    var cp = document.getElementById("globalcontextpath").value;
                    var xmlhttp;
                    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
                        xmlhttp = new XMLHttpRequest();
                    } else {
                        // code for IE6, IE5
                        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                    }
                    xmlhttp.onreadystatechange = function () {
                        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                            hideProcessIndicator();
                            var q = xmlhttp.responseText;
                            q = q.trim();
                            if (q === 'success') {
                                openSuccessModal("Process completed Successfully");
                            } else {
                                openErrorModal("Some Error Occured !");
                            }
                        }
                    };
                    var url = cp + "/errorManipulationAction?action=callProcedure&errorType=" + errorType;
                    xmlhttp.open("GET", url);
                    xmlhttp.send(null);

                },
                NO: function () {
                    $(this).dialog("close");
                }
            }
        });
    });
    hideProcessIndicator();
}//end function
function BulkProcedureConfirm() {
    $('#modalBulkProcedure').modal('hide');
    document.getElementById("dialog-confirm_change_review").style.display = "block";

    $(function () {
        $("#dialog-confirm_change_review").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                "Yes": function () {
                    $(this).dialog("close");
                    updateBulkProcedure();
                },
                "No": function () {

                    $(this).dialog("close");
                }
            }
        });
    });

}

function updateBulkProcedure() {
    showProcessIndicator();
    var errorFlagProc = document.getElementById("errorFlagProc").value;
    var errorType = document.getElementById("errortypeBulkProc").value;
    var cp = document.getElementById("globalcontextpath").value;
    var xmlhttp;
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    } else {
        // code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            hideProcessIndicator();
            var q = xmlhttp.responseText;
            q = q.trim();
            if (q === 'success') {
                openSuccessModal("Process completed Successfully");
//                $('#modalBulkProcedure').modal('hide');
            } else {
                openErrorModal("Some Error Occured!");
//                $('#modalBulkProcedure').modal('hide');
            }
        }
    };
    var url = cp + "/errorManipulationAction?action=callProcedure&errorType=" + errorType + "&errorFlagProc=" + errorFlagProc;
    xmlhttp.open("GET", url);
    xmlhttp.send(null);
}//end function


function changeAssessment() {
    $('#modalBulkReviewAll').modal('hide');
    document.getElementById("dialog-confirm_change_review").style.display = "block";

    $(function () {
        $("#dialog-confirm_change_review").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                "Yes": function () {

                    $(this).dialog("close");
                    updateBulkReviewAll();

                },
                "No": function () {

                    $(this).dialog("close");
                }
            }
        });
    });

}
function openBulkReviewAllModal(id) {
    if (!lhsIsNull(id)) {
        var splitArr = id.split("_");
        document.getElementById("errType").value = splitArr[0];
        document.getElementById("myModalLabelAll").innerHTML = "Bulk Review for " + document.getElementById(splitArr[0]).value;
        $('#review_remark_id_All').val('');
        $('#modalBulkReviewAll').modal({show: true});
    }
}//end function

function updateBulkReviewAll() {
    var review = document.getElementById("review_remark_id_All").value;
    var errType = document.getElementById("errType").value;
    var chkProcessFlag = window.parent.document.getElementById("processCnfChkBxID").value;
    if (review !== "" && review !== "null" && review !== null) {
        showProcessIndicator();
        var cp = document.getElementById("globalcontextpath").value;

        var xmlhttp;
        if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp = new XMLHttpRequest();
        } else {
            // code for IE6, IE5
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
//                removeError();
                var q = xmlhttp.responseText;
                q = q.trim();
                hideProcessIndicator();
                if (q === 'updateReview') {
                    if (chkProcessFlag !== null && chkProcessFlag !== "" && chkProcessFlag !== "null" && chkProcessFlag === 'false') {
                        showProcessIndicator();
                        window.location.reload();
                    } else {
                        try {
//                            window.parent.document.getElementById(errType + "_bulkReviewAllSpan").style.color = "green";
                        } catch (err) {
                        }
                        try {
                            var reviewID = document.getElementById("hiddenReviewImageID~" + errType).value;
                            if (reviewID !== null && reviewID !== "" && reviewID !== "null") {
                                var splitReviewID = reviewID.split("#");
                                for (var i = 0; i < splitReviewID.length; i++) {
                                    window.parent.document.getElementById(splitReviewID[i]).style.color = "green";
                                }
                            }
                        } catch (err) {
                        }
                        openSuccessModal("Record Update Successfully", "window.location.reload();");
                        // $('#modalBulkReviewAll').modal('hide');
//                        cancelBulkReviewAll();
                    }
                } else {
                    openErrorModal("Some Error Occured, Could Not Update");
                }
            }
        };
        var url = cp + "/errorManipulationAction?action=BulkReviewAll";
        url += "&errType=" + encodeURIComponent(errType);
        url += "&ReviewValue=" + encodeURIComponent(review);
        url += "&processCnfChkBx=" + encodeURIComponent(chkProcessFlag);
        xmlhttp.open("GET", url);
        xmlhttp.send(null);
    } else {
        openErrorModal("First Insert Review");
    }
}//end function

function getshow_salary_error_details_summary(id) {
    if (!lhsIsNull(id)) {
        showProcessIndicator();
        var splitArr = id.split("~");
        var error_type_code = document.getElementById("error_type_code~" + splitArr[1]).value;
        var error_type_name = document.getElementById("error_type_name~" + splitArr[1]).value;
        var error_name = document.getElementById("error_name~" + splitArr[1]).value;
        var error_code = document.getElementById("error_code~" + splitArr[1]).value;
        var table_name = document.getElementById("table_name~" + splitArr[1]).value;
        var cp = document.getElementById("globalcontextpath").value;
        var selectedUserLevel = document.getElementById("selectedUserLevelCode").value;
        var tokenNo = document.getElementById("tokenNo").value;

        if (error_type_code !== null && error_type_code !== "" && error_type_code !== "null") {
            //if (error_type_code === 'TSE' || error_type_code === 'TVE' || error_type_code === 'TVE' || 'SVE') {
            document.getElementById("hidden_salary_error_type_code").value = error_type_code;
            var url1 = "/getShowSalaryErrorAction?action=showSalaryErrSummaryDetails";
            url1 += "&l_error_type_code=" + encodeURIComponent(error_type_code);
            url1 += "&l_error_type_name=" + encodeURIComponent(error_type_name);
            url1 += "&l_error_name=" + encodeURIComponent(error_name);
            url1 += "&l_error_code=" + encodeURIComponent(error_code);
            url1 += "&table_name=" + encodeURIComponent(table_name);
            url1 += "&processLevel=" + encodeURIComponent(selectedUserLevel);
            url1 += "&tokenNo=" + encodeURIComponent(tokenNo);
            callUrl(url1);
        }
    }
}//end function

function killErrorProcessing(id, btn) {
    document.getElementById("dialog-confirm_process_kill").style.display = "block";
    $(function () {
        $("#dialog-confirm_process_kill").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                "Ok": function () {
                    $(this).dialog("close");
                    showProcessIndicator();
                    btn.disable = true;
                    var tokenNo = $("#" + id).val();
                    var uploadprocessTypeCode = $("#t_processType").val();
                    var url = "/getDataValueAction?tokenNo=" + tokenNo + "&action=killProcess&processType=" + uploadprocessTypeCode;
                    ajaxPostUrl(url, responseFunction);
                },
                Cancel: function () {
                    $(this).dialog("close");
                }
            }
        });
    });
}

function responseFunction(response) {
    if (!lhsIsNull(response) && response === 'success') {
        openSuccessModal("Task Killed Successfully !");

        setTimeout(function () {
            window.location.reload();
        }, 1000);
    } else {
        openErrorModal("Some Error Occurred !");
        setTimeout(function () {
            window.location.reload();
        }, 1000);
    }
    hideProcessIndicator();
}

function refreshTokenStatus(id) {


    document.getElementById("dialog-confirm_for_refresh").style.display = "block";
    $(function () {
        $("#dialog-confirm_for_refresh").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                "Ok": function () {
                    $(this).dialog("close");
                    showProcessIndicator();
                    var processStatus = $("#t_processStatus").val();
                    var refreshFlag = $("#refreshFlag").val();

                    if (!lhsIsNull(processStatus) && (processStatus === 'EC' || processStatus === 'EF' || processStatus === 'EI')) {
                     window.location.reload();
                    } else {
                        var tokenNo = $("#" + id).val();
                        if (!lhsIsNull(tokenNo.trim())) {
                            var url = "/refreshErrorProcessTokenStatus?tokenNo=" + tokenNo + "&action=refresh&refreshFlag="+refreshFlag;
                            ajaxPostUrl(url, refreshResponseFunction);
                        } else {
                            openErrorModal("Some Error Occurred ! Couldn't Refresh Records !");
                            hideProcessIndicator();
                        }
                    }
                },
                Cancel: function () {
                    $(this).dialog("close");
                }
            }
        });
    });

}//end function

function refreshResponseFunction(response) {
    if (!lhsIsNull(response) && response !== "error") {
        console.log('response: ' + response);

        var json = JSON.parse(response);
        $("#tokenNo").val(json.tokenNo);
        $("#t_processType").val(json.processType);
        $("#t_processTypeName").val(parseInt(json.processType) === 0 ? "Login Level" : "All (Login & Branch Level)");
        $("#t_processStartTimestamp").val(json.processStartTimestamp);
        $("#t_processEndTimestamp").val(json.processEndTimestamp);
        $("#t_processStatus").val(json.processStatus);
        $("#t_processRemark").val(json.processRemark);

        try {
            if (json.processStatus === "EA") {
//                document.getElementById("killBtn").disabled = false;
//                document.getElementById("viewBtn").disabled = false;
//                document.getElementById("fvuTextBtn").disabled = false;
////                document.getElementById("fvuBulkTxtBtn").disabled = true;
//                document.getElementById("fvuBtn").disabled = false;
//                document.getElementById("fvuDownloadBtn").disabled = false;
//                document.getElementById("processBtn").disabled = false;

            } else if (json.processStatus === "EB") {
//                document.getElementById("killBtn").disabled = false;
//                document.getElementById("viewBtn").disabled = false;
//                document.getElementById("fvuTextBtn").disabled = false;
////                document.getElementById("fvuBulkTxtBtn").disabled = true;
//                document.getElementById("fvuBtn").disabled = false;
//                document.getElementById("fvuDownloadBtn").disabled = false;
//                document.getElementById("processBtn").disabled = false;

            } else if (json.processStatus === "EC") {
//                document.getElementById("killBtn").disabled = true;
//                document.getElementById("viewBtn").disabled = false;
//                document.getElementById("fvuTextBtn").disabled = true;
////                document.getElementById("fvuBulkTxtBtn").disabled = true;
//                document.getElementById("fvuBtn").disabled = true;
//                document.getElementById("fvuDownloadBtn").disabled = true;
//                document.getElementById("processBtn").disabled = true;

            } else if (json.processStatus === "ED") {
//                document.getElementById("killBtn").disabled = false;
//                document.getElementById("viewBtn").disabled = false;
//                document.getElementById("fvuTextBtn").disabled = false;
////                document.getElementById("fvuBulkTxtBtn").disabled = true;
//                document.getElementById("fvuBtn").disabled = false;
//                document.getElementById("fvuDownloadBtn").disabled = false;
//                document.getElementById("processBtn").disabled = false;

            } else if (json.processStatus === "EE") {
//                document.getElementById("killBtn").disabled = false;
//                document.getElementById("viewBtn").disabled = false;
//                document.getElementById("fvuTextBtn").disabled = false;
////                document.getElementById("fvuBulkTxtBtn").disabled = true;
//                document.getElementById("fvuBtn").disabled = false;
//                document.getElementById("fvuDownloadBtn").disabled = false;
//                document.getElementById("processBtn").disabled = false;

            } else if (json.processStatus === "EF") {
//                document.getElementById("killBtn").disabled = false;
//                document.getElementById("viewBtn").disabled = false;
//                document.getElementById("fvuTextBtn").disabled = false;
////                document.getElementById("fvuBulkTxtBtn").disabled = true;
//                document.getElementById("fvuBtn").disabled = false;
//                document.getElementById("fvuDownloadBtn").disabled = false;
//                document.getElementById("processBtn").disabled = false;

            } else if (json.processStatus === "FA") {
//                document.getElementById("killBtn").disabled = false;
//                document.getElementById("viewBtn").disabled = false;
//                document.getElementById("fvuTextBtn").disabled = false;
////                document.getElementById("fvuBulkTxtBtn").disabled = true;
//                document.getElementById("fvuBtn").disabled = false;
//                document.getElementById("fvuDownloadBtn").disabled = false;
//                document.getElementById("processBtn").disabled = false;

            } else if (json.processStatus === "FB") {
//                document.getElementById("killBtn").disabled = false;
//                document.getElementById("viewBtn").disabled = true;
//                document.getElementById("fvuTextBtn").disabled = true;
////                document.getElementById("fvuBulkTxtBtn").disabled = true;
//                document.getElementById("fvuBtn").disabled = true;
//                document.getElementById("fvuDownloadBtn").disabled = true;
//                document.getElementById("processBtn").disabled = false;

            } else if (json.processStatus === "FC") {
//                document.getElementById("killBtn").disabled = true;
//                document.getElementById("viewBtn").disabled = true;
//                document.getElementById("fvuTextBtn").disabled = true;
////                document.getElementById("fvuBulkTxtBtn").disabled = true;
//                document.getElementById("fvuBtn").disabled = true;
//                document.getElementById("fvuDownloadBtn").disabled = false;
//                document.getElementById("processBtn").disabled = true;

            } else if (json.processStatus === "FD") {
//                document.getElementById("killBtn").disabled = true;
//                document.getElementById("viewBtn").disabled = true;
//                document.getElementById("fvuTextBtn").disabled = true;
////                document.getElementById("fvuBulkTxtBtn").disabled = true;
//                document.getElementById("fvuBtn").disabled = true;
//                document.getElementById("fvuDownloadBtn").disabled = false;
//                document.getElementById("processBtn").disabled = false;
            }
        } catch (e) {
        }
    } else {
        openErrorModal("Some Error Occurred ! Couldn't Refresh Records !");
    }
    hideProcessIndicator();
}//end function

function getshow_error_details_model(id) {
    if (!lhsIsNull(id)) {
        var splitArr = id.split("~");
        var error_type_code = document.getElementById("error_type_code~" + splitArr[1]).value;
        var error_type_name = document.getElementById("error_type_name~" + splitArr[1]).value;
        var error_name = document.getElementById("error_name~" + splitArr[1]).value;
        var error_code = document.getElementById("error_code~" + splitArr[1]).value;
        var table_name = document.getElementById("table_name~" + splitArr[1]).value;
        var selectedUserLevel = document.getElementById("selectedUserLevelCode").value;

        if (splitArr[0] === 'viewChallanErrDetailsViewMode') {//used to show challan detail in view mode
            var url = "/getShowChallanErrorDetailAction?action=showChallanErrDetails";
            url += "&l_error_type_code=" + encodeURIComponent(error_type_code);
            url += "&l_error_type_name=" + encodeURIComponent(error_type_name);
            url += "&l_error_name=" + encodeURIComponent(error_name);
            url += "&l_error_code=" + encodeURIComponent(error_code);
            url += "&table_name=" + encodeURIComponent(table_name);
            url += "&ReadonlyFlag=true";

        } else if (splitArr[0] === 'viewDeductorErrDetailsViewMode') {//used to show deductor detail in view mode
            var url = "/getShowDeductorErrorDetailAction?action=showDeductorErrDetails";
            url += "&l_error_type_code=" + encodeURIComponent(error_type_code);
            url += "&l_error_type_name=" + encodeURIComponent(error_type_name);
            url += "&l_error_name=" + encodeURIComponent(error_name);
            url += "&l_error_code=" + encodeURIComponent(error_code);
            url += "&table_name=" + encodeURIComponent(table_name);
            url += "&ReadonlyFlag=true";

        } else if (splitArr[0] === 'viewDeducteeErrDetailsViewMode') {//used to show deductee detail in view mode
            var url = "/getShowDeducteeErrorDetailAction?action=showDeducteeErrDetails";
            url += "&l_error_type_code=" + encodeURIComponent(error_type_code);
            url += "&l_error_type_name=" + encodeURIComponent(error_type_name);
            url += "&l_error_name=" + encodeURIComponent(error_name);
            url += "&l_error_code=" + encodeURIComponent(error_code);
            url += "&table_name=" + encodeURIComponent(table_name);
            url += "&ReadonlyFlag=true";

        } else if (splitArr[0] === 'deductorErrDetails') {//used to show deductor detail
            var url = "/getShowDeductorErrorDetailAction?action=showDeductorErrDetails";
            url += "&l_error_type_code=" + encodeURIComponent(error_type_code);
            url += "&l_error_type_name=" + encodeURIComponent(error_type_name);
            url += "&l_error_name=" + encodeURIComponent(error_name);
            url += "&l_error_code=" + encodeURIComponent(error_code);
            url += "&table_name=" + encodeURIComponent(table_name);
            url += "&ReadonlyFlag=false";

        } else if (splitArr[0] === 'deducteeErrDetails') {//used to show deductee detail
            var url = "/getShowDeducteeErrorDetailAction?action=showDeducteeErrDetails";
            url += "&l_error_type_code=" + encodeURIComponent(error_type_code);
            url += "&l_error_type_name=" + encodeURIComponent(error_type_name);
            url += "&l_error_name=" + encodeURIComponent(error_name);
            url += "&l_error_code=" + encodeURIComponent(error_code);
            url += "&table_name=" + encodeURIComponent(table_name);
            url += "&ReadonlyFlag=false";

        } else {//used to show challan detail
            var url = "/showChallanErrorDetailAction?action=showChallanErrDetails";
            url += "&l_error_type_code=" + encodeURIComponent(error_type_code);
            url += "&l_error_type_name=" + encodeURIComponent(error_type_name);
            url += "&l_error_name=" + encodeURIComponent(error_name);
            url += "&l_error_code=" + encodeURIComponent(error_code);
            url += "&table_name=" + encodeURIComponent(table_name);
            url += "&processLevel=" + encodeURIComponent(selectedUserLevel);
            url += "&ReadonlyFlag=false";
            callUrl(url);
        }
    }
}//end function

function updateChallanErrorDetailData() {
    var tbl = document.getElementById("table");
    var total_record = tbl.rows.length;

    var l_record_count = 0;
    for (var j = 1; j < (total_record - 2); j++) {
        var chk = document.getElementById('chk~' + j).checked ? "Y" : "N";
        if (chk === "Y") {
            l_record_count++;
        }
    }
    l_record_count = parseInt(l_record_count);
    if (l_record_count > 0) {
        if (!ajax_call_enabled) {
            $("#challanErrorDetails").submit(function (e) {
                var postData = $(this).serializeArray();
                var formURL = $(this).attr("action");
                $.ajax({
                    url: formURL,
                    type: "POST",
                    data: postData,
                    success: function (data, textStatus, jqXHR) {
                        ajax_call_enabled = false;
                        if (data === 'updateData') {
                            openSuccessModal("Records Updated Successfully");
                            getCurrentPage(document.getElementById("pageNumberSpan").innerHTML);
                        } else {
                            openErrorModal("Some Error Occured, Could Not Update Records");
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        ajax_call_enabled = false;  //if fails
                    }
                });
                e.preventDefault(); //STOP default action
                e.unbind(); //unbind. to stop multiple form submit.
            });
            ajax_call_enabled = true;
            $("#challanErrorDetails").submit(); //Submit  the FORM
        }
    } else {
        openErrorModal("Please Select Record For Update");
    }
}//end function

function onClickCheckboxChallan(id) {

    var idNo = id.split("~");
    var chkid = "row~" + idNo[1];
    try {
        var checked = document.getElementById(id).checked;
        if (checked) {
            document.getElementById("editCheckBox~" + idNo[1]).value = "true";
        } else {
            document.getElementById("editCheckBox~" + idNo[1]).value = "false";
        }
    } catch (e) {
    }
    var row = document.getElementById("row~" + id);
    var val = document.getElementById("recordsPerPageSelect").value;
    var oInput = document.getElementById('row~' + (parseInt(idNo[1]))),
            oChild;
    if (document.getElementById(id).checked) {
        for (i = 0; i < oInput.childNodes.length; i++) {
            oChild = oInput.childNodes[i];
            try {
                oChild.style.backgroundColor = '#B9D8F7';
            } catch (err) {
            }
        }
    } else {
        for (i = 0; i < oInput.childNodes.length; i++) {
            oChild = oInput.childNodes[i];
            try {
                oChild.style.backgroundColor = '';
            } catch (err) {
            }
        }
    }
}//end function

function generateFVUFunction(id) {
    try {
//        document.getElementById("saveBtnGenerateFVU").disabled = true;
        var cp = document.getElementById("globalcontextpath").value;
        showProcessIndicator();
        var callingurl = cp + "/validateFVUToGenerate?action=getValidateGenerateFUV";
        $.ajax({
            url: callingurl,
            context: document.body,
            success: function (result) {
                if (result !== null && result !== "" && result !== "null") {
                    if (result === 'T') {
                        goToFuvValidation(id);
                    } else {
                        window.location = cp + "/getErrorMessageAction";
                    }
                } else {
                    goToFuvValidation(id);
                }
            }
        });
    } catch (err) {
    }
}//end class

function goToFuvValidation(id) {

    var fvuPid_Tflag_count = document.getElementById("fvuPid_Tflag_count").value;
    var fvuPid_Fflag_count = document.getElementById("fvuPid_Fflag_count").value;
    var lhssysLog_listsize = document.getElementById("lhssysLog_listsize").value;
    if (lhssysLog_listsize === '0') {
        checkGeneratedReportStatusOne();
    } else if (fvuPid_Tflag_count === '0') {
        checkGeneratedReportStatusTwo();
    } else if (fvuPid_Fflag_count > 0) {
        checkGeneratedReportStatusThree(id, fvuPid_Tflag_count, fvuPid_Fflag_count);
    } else {
        goToFuvValidationCall(id);
    }





}//end function

function goToFuvValidationCall(id) {
    try {

        var cp = document.getElementById("globalcontextpath").value;
        var tokenNo = document.getElementById("tokenNo").value;
        var btnFVU = document.getElementById(id);

//        showProcessIndicator();
        var callingurl = cp + "/validateFVUToGenerate?action=getReviewFlagCount";
        $.ajax({
            url: callingurl,
            context: document.body,
            success: function (result) {
                if (result !== null && result !== "" && result !== "null") {
                    // alert(result);
                    if (result === 'T') {
//                        document.getElementById("genFilFrm").innerHTML = '<input type="hidden" name="jsessionid" value="ij123kih7896kirtwc5638Nsdsuybn3456"> <input type="hidden" name="allowClick" value="true">';
                        disableButton(btnFVU);
//                        document.getElementById("genFilFrm").submit();
                        callUrl('/tdsGenerateFVU?tokenNo=' + encodeURIComponent(tokenNo));
                        hideProcessIndicator();
                    } else {
                        hideProcessIndicator();
                        document.getElementById("dialog-confirm_review_check").style.display = "block";
                        $(function () {
                            $("#dialog-confirm_review_check").modal({
                                modal: true,
                                buttons: {
                                    Yes: function () {
                                        $(this).dialog("close");

//                                      document.getElementById("genFilFrm").innerHTML = '<input type="hidden" name="jsessionid" value="ij123kih7896kirtwc5638Nsdsuybn3456"> <input type="hidden" name="allowClick" value="true">';
                                        disableButton(btnFVU);
//                                        document.getElementById("genFilFrm").submit();
                                        callUrl('/tdsGenerateFVU?tokenNo=' + encodeURIComponent(tokenNo));


                                    },
                                    No: function () {
                                        $(this).dialog("close");
                                    }
                                }
                            });
                        });
                    }
                } else {
//                    document.getElementById("genFilFrm").innerHTML = '<input type="hidden" name="jsessionid" value="ij123kih7896kirtwc5638Nsdsuybn3456"> <input type="hidden" name="allowClick" value="true">';
                    disableButton(btnFVU);
//                    document.getElementById("genFilFrm").submit();
                    callUrl('/tdsGenerateFVU?tokenNo=' + encodeURIComponent(tokenNo));
                    hideProcessIndicator();
                }
            }
        });
    } catch (err) {
//        document.getElementById("genFilFrm").innerHTML = '<input type="hidden" name="jsessionid" value="ij123kih7896kirtwc5638Nsdsuybn3456"> <input type="hidden" name="allowClick" value="true">';
//        disableButton(btnFVU);
//        document.getElementById("genFilFrm").submit();
        callUrl('/tdsGenerateFVU?tokenNo=' + encodeURIComponent(tokenNo));
        hideProcessIndicator();
    }

}


function tdsGenerateFVUcalling() {
    var cp = document.getElementById("globalcontextpath").value;
    var tokenNo = document.getElementById("tokenNo").value;
    var btnFVU = document.getElementById('fvuTextBtn');
    disableButton(btnFVU);
    callUrl('/tdsGenerateFVU?tokenNo=' + encodeURIComponent(tokenNo));
}

function checkGeneratedReportStatusOne() {
    var errorMessage = "You Have Not Generated The Error Process Report";
    var reportbtnmsg = "Click Here To Generate Report";
    document.getElementById("reporterrorAlertMsg").innerHTML = errorMessage;
    document.getElementById("reportbtnmsg").innerHTML = reportbtnmsg;
    $("#err_report_danger_Alert").modal({show: true});
    hideProcessIndicator();
}

function checkGeneratedReportStatusTwo() {
    var errorMessage = "You Have Not Download The Error Process Report";
    var reportbtnmsg = "Click Here To Download";
    document.getElementById("reporterrorAlertMsg").innerHTML = errorMessage;
    document.getElementById("reportbtnmsg").innerHTML = reportbtnmsg;
    $("#err_report_danger_Alert").modal({show: true});
    hideProcessIndicator();
}


function checkGeneratedReportStatusThree(id, fvuPid_Tflag_count, fvuPid_Fflag_count) {
    hideProcessIndicator();
    var down_repcont ="You have downloaded only "+fvuPid_Tflag_count+" report(s) and "+fvuPid_Fflag_count+" report(s) are pending for download.<br>Do you want to continue without downloading?";
    document.getElementById("down_rep_cont").innerHTML = down_repcont;
    //var down_repcont_msg ="<br>Do you want to continue without downloading?";
   // document.getElementById("dwnl_rep_counting").innerHTML = down_repcont_msg;

    document.getElementById("dialog-confirm_Rep_dwnORnot_alert").style.display = "block";

    $(function () {
        $("#dialog-confirm_Rep_dwnORnot_alert").dialog({
            resizable: false,
            font: 10,
            modal: true,

            buttons: {
                //'class':'btn-orange-ui',
                "Yes": function () {

                    $(this).dialog("close");
                    goToFuvValidationCall(id);

                },
                "No": function () {

                    $(this).dialog("close");
                }
            }
        });
    });

}


function callHigerRateProcedure(flag) {
    var cp = document.getElementById("contextPath").value;
    var url = cp + "/showDeducteeValidationErrors?action=CallProcedure&procedureType=" + flag;
//add process indicator
    $.ajax({
        url: url,
        success: function (result) {
            if (result !== null && result !== "" && result !== "null") {
                if (result === "0") {
                    openSuccessModal("Process Completed Successfully");
                } else {
                    $('#dangerAlert').modal('show');
                }
            }
        }
    });
}//end function

function showErrorDetailContentData(id) {
    if (!lhsIsNull(id)) {
        showProcessIndicator();
        var splitArr = id.split("~");
        var error_type_code = document.getElementById("l_error_type_code").value;
        var error_type_name = document.getElementById("l_error_type_name").value;
        var error_name = document.getElementById("error_name").value;
        var error_code = document.getElementById("error_code").value;
        var table_name = document.getElementById("table_name").value;
        var l_ReadonlyFlag = document.getElementById("l_ReadonlyFlag").value;
        var tds_code = document.getElementById("tds_code~" + splitArr[1]).value;
        var deductee_code = document.getElementById("deductee_code~" + splitArr[1]).value;
        var deductee_panno = document.getElementById("deductee_panno~" + splitArr[1]).value;

        var h_tran_amount = document.getElementById("tran_amt~" + splitArr[1]).value;
        var h_tds_amt = document.getElementById("tds_amt~" + splitArr[1]).value;
        var h_calc_tda_amt = document.getElementById("calc_tds_amt~" + splitArr[1]).value;
        var h_diff_tds_amt = document.getElementById("diff_tds_amt~" + splitArr[1]).value;
        var h_client_code = document.getElementById("client_code~" + splitArr[1]).value;
        var l_e2 = document.getElementById("l_e2").value;

        if (l_e2 === 'T') {
//            var cp = document.getElementById("contextPath").value;
            var url = "/showDeducteeValidationErrors?action=showErrorDetails";
            url += "&l_error_type_code=" + encodeURIComponent(error_type_code);
            url += "&l_error_type_name=" + encodeURIComponent(error_type_name);
            url += "&l_error_name=" + encodeURIComponent(error_name);
            url += "&l_error_code=" + encodeURIComponent(error_code);
            url += "&table_name=" + encodeURIComponent(table_name);
            url += "&tds_code=" + encodeURIComponent(tds_code);
            url += "&deductee_code=" + encodeURIComponent(deductee_code);
            url += "&deductee_panno=" + encodeURIComponent(deductee_panno);
            url += "&h_tran_amount=" + encodeURIComponent(h_tran_amount);
            url += "&h_tds_amt=" + encodeURIComponent(h_tds_amt);
            url += "&h_calc_tda_amt=" + encodeURIComponent(h_calc_tda_amt);
            url += "&h_diff_tds_amt=" + encodeURIComponent(h_diff_tds_amt);
            url += "&h_client_code=" + encodeURIComponent(h_client_code);
            url += "&ReadonlyFlag=" + encodeURIComponent(l_ReadonlyFlag);
            url += "&screenCallFrom=" + encodeURIComponent(2);
            url += "&e2=" + encodeURIComponent(l_e2);
            callUrl(url);

        } else {
            openErrorModal("Rights Not Available");
        }
    }
}//end functiuon

function goToDetailPage() {
    var error_type_code = document.getElementById("l_error_type_code").value;
    var error_type_name = document.getElementById("l_error_type_name").value;
    var error_name = document.getElementById("l_error_name").value;
    var error_code = document.getElementById("l_error_code").value;
    var table_name = document.getElementById("table_name").value;
    var l_ReadonlyFlag = document.getElementById("ReadonlyFlag").value;
    var e2 = document.getElementById("e2").value;

    var url = "/errorDetailsSummary?action=showErrSummaryDetails";
    url += "&l_error_type_code=" + encodeURIComponent(error_type_code);
    url += "&l_error_type_name=" + encodeURIComponent(error_type_name);
    url += "&l_error_name=" + encodeURIComponent(error_name);
    url += "&l_error_code=" + encodeURIComponent(error_code);
    url += "&table_name=" + encodeURIComponent(table_name);
    url += "&ReadonlyFlag=" + encodeURIComponent(l_ReadonlyFlag);
    url += "&e2=" + encodeURIComponent(e2);
    callUrl(url);

}//end functiuon

function validateUpdateBtnCSI() {
    var file = document.getElementById("template").value;
    var dataSplit = file.split("\.");
    if (file === null || file === "") {
        $("#fvu_notification").attr("class", "errorMessage");
        document.getElementById("fvu_notification").style.visibility = 'visible';
        addError("Select valid .csi file first");
    } else if (dataSplit[1] !== 'csi') {
        $("#fvu_notification").attr("class", "errorMessage");
        addError("Select valid .csi file");
    } else {
//        document.getElementById("uploadCSIFiles").submit();
        document.getElementById("dialog-confirm_upload_csi_file").style.display = "block";
        $(function () {
            $("#dialog-confirm_upload_csi_file").dialog({
                resizable: false,
                font: 10,
                modal: true,
                buttons: {
                    "Ok": function () {
                        $(this).dialog("close");
                        showProcessIndicator();

                        var cp = document.getElementById("globalcontextpath").value;
                        var url = cp + "/uploadCSI?validate=true";
                        var formData = new FormData($("form#uploadCSIFiles")[0]);
                        $.ajax({
                            url: url,
                            type: 'POST',
                            data: formData,
                            async: false,
                            success: function (data) {
                                var out = data.split("#");
                                if (out[0] === 'success') {
                                    document.getElementById("csiFileName").value = out[1];
                                    document.getElementById("csiFilePath").value = out[2];
                                    document.getElementById("csiFileFlag").value = out[3];
                                    //divBlockCheck();
                                    document.getElementById("csidwnloadbtn").disabled = false;
                                    document.getElementById("FvuFileCall").disabled = false;
                                    openSuccessModal("File Uploaded Successfully !");

//                     setTimeout(() => {
//                         window.location.reload();
//                        },200);

                                    //            document.getElementById("processBtn").removeAttribute("disabled");
//                    $("#fvu_notification").attr("class", "successMessage");
//                    addError("Upload File Successfully");
//                    var allowClick = document.getElementById("allowClick").value;
//                    if (!isNull(allowClick)|| allowClick === 'true') {
//                        document.getElementById("upload").removeAttribute("disabled");
//                    }
                                } else {
                                    openErrorModal("Some Error Occurred ! Could Not upload File !");
//                    $("#fvu_notification").attr("class", "errorMessage");
//                    addError("Could not upload file");
                                }
                            },
                            complete: function (jqXHR, textStatus) {
                                hideProcessIndicator();
                            },

                            cache: false,
                            contentType: false,
                            processData: false
                        });

                    },
                    Cancel: function () {
                        $(this).dialog("close");
                    }
                }
            });
        });


    }

}

function downloadFVUTextFile() {
    showProcessIndicator();
    var token = document.getElementById("tokenNo").value;
    var action = "downloadFvuTxtFile?action=fvuTextDownload&validateFlag=false&tokenNo=" + encodeURIComponent(token);
    $.ajax({
        url: action,
        type: 'POST',
        success: function (data, textStatus, jqXHR) {
            //alert(data)
            if (!lhsIsNull(data)) {
                var fileName = data.split('##')[0];
                var filePath = data.split('##')[1];
                downloadUsingPath(filePath, fileName);
                hideProcessIndicator();
            } else {
                openErrorModal("Some error occurred, could not download text file")
                hideProcessIndicator();
            }
        }
    });
}//End Function

function callForGenerateFvuFile(button) {
    button.disabled = true;
    showBlackProcessIndicator();
    showTextMsg("Please wait, While generating FVU file...");
    document.getElementById("generateFvuForm").submit();
}//end function

function callForFvuDownload(url) {//TO BE REMOVE
    showProcessIndicator();
    var cp = document.getElementById("globalcontextpath").value;
    window.location = cp + url;
    hideProcessIndicator();
}//end function

function generateFvuBulkTextFile(button) {
//    if (!lhsIsNull(token_no)) {
    button.disabled = true;
    var processLevel = document.getElementById("t_processType").value;
    ajaxPostUrl("/validateFVUToGenerate?action=getBulkTextFileToken", getResponseOfBulkTextToken);
    function getResponseOfBulkTextToken(token_no) {
        console.log('response' + token_no);
        if (!lhsIsNull(token_no)) {
            callDedicatedProcedure({
                callingProcName: "ProcGenerateTdsBulkTxtFile",
                tokenNo: parseInt(token_no),
                processLevel: parseInt(processLevel)
            });

            openSuccessModal("Your request is initiated & Your  Generated Token No. is :" + token_no, "callUrl('/errorStatus');");
        }
    }
//        var cp = document.getElementById("globalcontextpath").value;
//        var actionUrl = cp + "/generateBulkTextFile?action=callBulkProc&tokenNo=" + encodeURIComponent(token_no);
//        actionUrl += "&process_level=" + processLevel;
//
//        showProcessIndicator();
//        $.ajax({
//            url: actionUrl,
//            type: 'POST',
//            success: function (data, textStatus, jqXHR) {
////                alert(data);
//                openSuccessModal("Your request is initiated.", "callUrl('/errorStatus');");
//                hideProcessIndicator();
//            }, error: function (jqXHR, textStatus, errorThrown) {
//                hideProcessIndicator();
//
//            }
//        });
//    }
}//end function

function getvalidationLogModalRefresh(tokenNo, processStatus) {

    waitingProcess();

}

function waitingProcess() {
    document.getElementById("loader").style.display = "block";
    const myVar = setTimeout(showPage, 1500);
}

function showPage() {
    var tokenNo = document.getElementById("tokenNo").value;
    var processStatus = document.getElementById("t_processStatus").value;
    getValidationProcessLog(tokenNo, processStatus);
}
function getValidationProcessLog(tokenNo, processStatus) {

    document.getElementById("loader").style.display = "none";
    $("#validationLogModal").modal("show");
    requestProcessLog(tokenNo, processStatus);
}//end function

function requestProcessLog(tokenNo, processStatus) {

    var actionUrl = "/getProcessLogAction?action=readProcessStatus";
    actionUrl += "&tokenNo=" + tokenNo;
    actionUrl += "&processStatus=" + processStatus;

    ajaxPostUrl(actionUrl, processLogStatusResponse);
}//end function

function processLogStatusResponse(response) {
    if (!lhsIsNull(response)) {
        if (response.includes('#')) {
            var results = response.split('#');
            $("#p_last_modify").html(results[0]);
            $("#p_file_size").html(results[1]);
            $("#p_log_status").html(results[2]);
        } else {
            $("#p_last_modify").html('');
            $("#p_file_size").html('');
            $("#p_log_status").html(response);

        }
            const text1 = document.querySelector('#p_log_status');
            const  changeColor1 = (a, b) => a.innerHTML = a.innerHTML.split(b).join(`<span style="color: blue;">${b}</span>`);
            changeColor1(text1, "---*************---ERROR PROCESSING LOG STARTED---*********---");
            const text2 = document.querySelector('#p_log_status');
            const  changeColor2 = (a, b) => a.innerHTML = a.innerHTML.split(b).join(`<span style="color: green;">${b}</span>`);
            changeColor2(text2, "----************----TEXT FILE GENERATION STARTED----************----");
            const text3 = document.querySelector('#p_log_status');
            const  changeColor3 = (a, b) => a.innerHTML = a.innerHTML.split(b).join(`<span style="color: green;">${b}</span>`);
            changeColor3(text3, "----************----TEXT FILE GENERATION COMPLETED----************----");
            const text4 = document.querySelector('#p_log_status');
            const  changeColor4 = (a, b) => a.innerHTML = a.innerHTML.split(b).join(`<span style="color: green;">${b}</span>`);
            changeColor4(text4, "----************----BULK TEXT FILE GENERATION STARTED----************----");
            const text5 = document.querySelector('#p_log_status');
            const  changeColor5 = (a, b) => a.innerHTML = a.innerHTML.split(b).join(`<span style="color: green;">${b}</span>`);
            changeColor4(text5, "----************----BULK TEXT FILE GENERATION COMPLETED----************----");
            
            
            
        
    }
}//end function

function selectAllPanRecords(mainCheck) {
    var mainIsChecked = mainCheck.checked;

    if (mainIsChecked) {
        displayActionDiv(true);
        document.getElementById("updtAllPanStatus").value = "true";
    } else {
        displayActionDiv(false);
        document.getElementById("updtAllPanStatus").value = "false";
    }

    selectAllRecords(mainIsChecked);
}//end function

function displayActionDiv(flag) {
//    alert(flag);
    try {
        if (flag) {
            $("#actiondiv").show(true);
        } else {
            $("#actiondiv").hide(true);
        }
    } catch (e) {
    }
}//end function

function selectAllRecords(flag) {
    $('#table tbody tr td input[type=checkbox]').each((index, chckbox) => {
        chckbox.checked = flag;
    });
}//end function

function getCheckedRecordsCount() {
    var checkedRecords = 0;
    try {
        checkedRecords = $('#table tbody tr td input[type=checkbox]:checked').length;
    } catch (e) {
    }
    return checkedRecords;
}//end function

function selectIndividualPanRecord(currentRow) {
    if (getCheckedRecordsCount() === 0) {
        displayActionDiv(false);
        $('#selectAll').prop('checked', '');
    } else {
        var actionDivStatus = $("#actiondiv").attr('style');

        if (actionDivStatus === undefined || actionDivStatus.search('none') !== -1) {
            displayActionDiv(true);
        }
    }
}//end function

function getWhereClause() {
    var rowIdSeqArray = [];

    $('#table tbody tr td input[type=checkbox]:checked').each((index, checkbox) => {
        rowIdSeqArray.push($(checkbox).val());
    });

    var whereclause = " AND a.rowid_seq IN('" + (rowIdSeqArray.join("','")) + "')";
//    console.log("whereclause", whereclause);
    return whereclause;
}//end function

function processPANUpdation(e) {
    e.preventDefault();
    if (getCheckedRecordsCount() > 0) {
        let cp = document.getElementById("globalcontextpath").value;
        let actionUrl = cp + "/getPANDetailsAction";

        let updtAllStatus = document.getElementById("updtAllPanStatus").value;
        let rowidWhrClause = (updtAllStatus === "false") ? getWhereClause() : "";

        $(function () {
            $("#dialog-confirm_update").dialog({
                resizable: false,
                font: 10,
                modal: true,
                buttons: {
                    "Ok": function () {
                        $(this).dialog("close");
                        $.ajax({
                            url: actionUrl,
                            type: 'POST',
                            data: {
                                action: "panUpdation",
                                pan_updt_rowid_str: rowidWhrClause
                            },
                            beforeSend: function (xhr) {
                                showProcessIndicator();
                            },
                            success: function (data, textStatus, jqXHR) {
                                let response = data.trim();

                                if (!lhsIsNull(response) && response.search('success') !== -1) {
                                    var panCount = response.split('#')[1];
                                    openSuccessModal("Total updated PAN count: " + panCount, "window.location.reload();");
                                } else {
                                    openErrorModal("Some error occurred, could not update PAN status", "window.location.reload();");
                                }
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                hideProcessIndicator();
                            },
                            complete: function (jqXHR, textStatus) {
                                hideProcessIndicator();
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
}//end function

function getPANDataOnload() {

    var actionUrl = "panUpdation";

    $.ajax({
        url: actionUrl,
        type: 'POST',
        data: {
            action: 'showData'
        },
        success: function (responseText, requestStatus, responseObject) {
//            console.log('jqXHR:' + JSON.stringify(responseObject));
            if (requestStatus) {
                document.getElementById("dataSpan").innerHTML = responseText;
                try {
                    var count = $('#dataSpan > input[type=hidden]').val();
                    $('#totalRecordsSpan').html(count !== undefined ? count : 0);
                    if (Number(count)) {
                        $('.button-section > button[type=button]').css('display', 'inline-block');
                    }
                } catch (e) {
                }
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('error:' + textStatus);
        }
    });

}//end function

function refreshProcessErrorSummary(codeLevel) {

    var userLevels = {
        0: 'Login Level',
        1: 'All (Login &amp; Branch Level)'
    }; // Values for selected user levels

    var cp = document.getElementById("globalcontextpath").value;
    var url = cp + "/ProcessErrorResultAction?summaryRefreshFlag=true&validate=true&loginLevelFlag=" + encodeURIComponent(codeLevel);
//    alert(url);
    $.ajax({
        url: url,
        type: 'GET',
        beforeSend: function (xhr) {
            showProcessIndicator();
        },
        success: function (data, textStatus, jqXHR) {
//            console.log(data);
            document.getElementById("selectedUserLevelText").innerHTML = userLevels[codeLevel];
            document.getElementById("selectedUserLevelCode").value = codeLevel;
            if (!lhsIsNull(data)) {
                document.getElementById("processErrorSummaryDiv").innerHTML = data;
            }
        },
        error: function (jqXHR, status) {
            hideProcessIndicator();
        },
        complete: function (jqXHR, textStatus) {
            hideProcessIndicator();
        }
    });
}

function callUserLevelPopup(showModal = false) {
    var selectedUserLevelCode = document.getElementById("selectedUserLevelCode").value;
//    alert(selectedUserLevelCode);
    if (lhsIsNull(selectedUserLevelCode) || showModal) {
        $('#userLevelModal').modal('show');
    } else {
        refreshProcessErrorSummary(selectedUserLevelCode);
        getDynamicErrorDetailsAction(selectedUserLevelCode);
}

}//end function

function showUserLevelErrorSummaryAndDetails() {
    try {
        removeError();
    } catch (e) {
    }

    let userLevel = $("input[name=r_user_level]:checked");
    if (userLevel.length) {
        let selectedUserLevel = $("input[name=r_user_level]:checked").val();

        refreshProcessErrorSummary(selectedUserLevel);
        getDynamicErrorDetailsAction(selectedUserLevel);

        $('#userLevelModal').modal('hide');
    } else {
        addActionError("message", "Please select User Level");
    }
}//end function


function processt1() {
    getFileDashboardDetails();
}
function processt2() {
    getErrorDetails();
}

function downloadFVUclobTextFile() {

    var filePath = document.getElementById("textFilePath").value;
    var fileName = document.getElementById("textFileName").value;
    if (!isNull(filePath) && !isNull(fileName)) {
        downloadUsingPath(filePath, fileName);
    }

}
function downloadFVUcsiFile() {

    var filePath = document.getElementById("csiFilePath").value;
    var fileName = document.getElementById("csiFileName").value;
    if (!isNull(filePath) && !isNull(fileName)) {
        downloadUsingPath(filePath, fileName);
    }else{
        openErrorModal("Please Get CSI File from OLTAS OR upload CSI File");
    }

}

function textFileDownloadForFvu(blobFileDownloadFlag){
        var cp = document.getElementById("globalcontextpath").value;
        if(!isNull(blobFileDownloadFlag)){
                if(blobFileDownloadFlag === 'F'){
                    var fileName = document.getElementById("textFileName").value;
                    var filePath = document.getElementById("oraTextFilePath").value;
                    if (!isNull(filePath) && !isNull(fileName)) {
                    downloadUsingPath(filePath, fileName);
                }else{
                    openErrorModal("Text File Path OR File Name is blank");    
                }
        }else if(blobFileDownloadFlag === 'T'){
            
                var process_seq_no = document.getElementById("token_No_").value;
                if (!isNull(process_seq_no)){ 
                window.location.href = cp + '/downloadDataDriectFromBlob?process_seqno=' + process_seq_no;
                }else{
                openErrorModal("Token no is null");   
                }
        }
    
    }else{
        openErrorModal("Blob_File_Download_Flag is null.Please check");
    }
}


function callForGenerateFvuFileEE(button) {
    //button.disabled = true;
    // showBlackProcessIndicator();
    // showTextMsg("Please wait, While generating FVU file...");

    var textFileName = document.getElementById("textFileName").value;
    var csiFileName = document.getElementById("csiFileName").value;

    // document.getElementById("generateFvuForm").submit();
}//end function

function divBlockCheck() {
    var textFileName = document.getElementById("textFileName").value;
    var textFilePath = document.getElementById("textFilePath").value;
    var textFileFlag = document.getElementById("textFileFlag").value;
    var csiFileName = document.getElementById("csiFileName").value;
    var csiFilePath = document.getElementById("csiFilePath").value;
    var csiFileFlag = document.getElementById("csiFileFlag").value;

//    if (!isNull(textFileName)) {
//        document.getElementById("textRecreation").style.display = "block";
//
//        if (!isNull(textFileFlag) && (textFileFlag === "T")) {
//            document.getElementById("csidivsection").style.display = "block";
//            document.getElementById("textdwnload").disabled = false;
//        } else if (!isNull(textFileFlag) && (textFileFlag === "F")) {
//            document.getElementById("textdwnload").disabled = true;
//            $("#txtdn").attr("title", "Text file not available in directory.Please press Get Text File");
//            document.getElementById("csidivsection").style.display = "none";
//        } else if (isNull(textFileFlag)) {
//            document.getElementById("textdwnload").disabled = true;
//            $("#txtdn").attr("title", "Text file not available in directory.Please press Get Text File");
//            document.getElementById("csidivsection").style.display = "none";
//        }
//    } else {
//        document.getElementById("notificationMsg").style.display = "block";
//        document.getElementById("textRecreation").style.display = "none";
//    }
//
//
//
//    if (!isNull(textFileFlag) && (textFileFlag === 'T')) {
//        document.getElementById("csidivsection").style.display = "block";
//    } else {
//        document.getElementById("csidivsection").style.display = "none";
//    }
//
//    if (!isNull(csiFileName)) {
//        if (!isNull(csiFileFlag) && (csiFileFlag === 'T')) {
//            document.getElementById("csidwnloadbtn").disabled = false;
//        } else if (!isNull(csiFileFlag) && (csiFileFlag === 'F')) {
//            $("#csidn").attr("title", "CSI file not available in directory.Please press Get CSI File ");
//        }
//    }
//
//

//textFileFlag  ---
//csiFileFlag   ---
    if (!isNull(textFileFlag) && (textFileFlag === 'T')) {
        if (!isNull(csiFileFlag) && (csiFileFlag === 'T')) {
            document.getElementById("FvuFileCall").disabled = false;
        }
    }

}


function isNull(value) {
    value = value.trim();
    if (value !== "" && value !== "null" && value !== null)
        return false;
    else
        return true;
}//end function


function getTextFileConformation() {

    document.getElementById("dialog-confirm_get_text_file").style.display = "block";
    $(function () {
        $("#dialog-confirm_get_text_file").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                "Ok": function () {
                    $(this).dialog("close");
                    showProcessIndicator();
                    document.getElementById("getTextForm").submit();
                },
                Cancel: function () {
                    $(this).dialog("close");
                }
            }
        });
    });

}



function getCSIFileConformation() {

    var csiFileName = document.getElementById("csiFileName").value;
    var dynamicId;
    if (!isNull(csiFileName)) {
        dynamicId = "dialog-confirm_get_regenerate_csi_file";

    } else {
        dynamicId = "dialog-confirm_get_csi_file";
    }

    document.getElementById(dynamicId).style.display = "block";
    $(function () {
        $("#" + dynamicId).dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                "Ok": function () {
                    $(this).dialog("close");
                    showProcessIndicator();
                    document.getElementById("getCSIForm").submit();

                },
                Cancel: function () {
                    $(this).dialog("close");
                }
            }
        });
    });

}


function generateFVUfiledynamicAction(clobflag, tokenno) {
    var url;
    if (!isNull(clobflag) && (clobflag === 'T')) {
        url = "/generateFvuBtnClobAction?tokenNo=" + tokenno;
    } else {
        url = "/generateFvuBtnAction?tokenNo=" + tokenno;
    }
    callUrl(url);
}


function GenerateFvuFileCall() {

    showBlackProcessIndicator();
    showTextMsg("Please wait, While generating FVU file...");
    document.getElementById("generateFvuForm").submit();

}

function generateFvuBulkTextFileAction() {

    var url = "/textFileDashboard?action=textFileView";
    callUrl(url);
}

function textFileDashboardAction() {
   var tokenNo = document.getElementById("tokenNo").value;

    var url = "/bulktextFileDashboard?tokenNo="+tokenNo;
    callUrl(url);
}

function callfvudownload() {

    var processStatus = document.getElementById("t_processStatus").value;
    var cp = document.getElementById("globalcontextpath").value;
    window.location = cp + "/fvuDownloadSection?processStatus=" + processStatus;

}
function downloadsinglefile(id) {
    var filepath = document.getElementById("file_path~" + id).value;
    var filename = document.getElementById("file_name~" + id).value;
    downloadUsingPath(filepath, filename);
}


function openLoginLevelModal() {
    $('#loginLevelModal').modal('show');

}
function generateFvuBulkTextFileNew() {
    var login_level = document.getElementById('r_login_level').checked;
    var all_level = document.getElementById('r_all_level').checked;
    var processLevel = "";
    if (login_level) {
        processLevel = '0';
    }
    if (all_level) {
        processLevel = '1';
    }

    $('#loginLevelModal').modal('hide');
    console.log('call generateFvuBulkTextFileNew');
    console.log('processLevel- ' + processLevel);
    console.log(' calling validateFVUToGenerate?action=getBulkTextFileToken');
    ajaxPostUrl("/validateFVUToGenerate?action=getBulkTextFileToken", getResponseOfBulkTextToken);
    function getResponseOfBulkTextToken(token_no) {
        console.log('response token_no' + token_no);
        if (!lhsIsNull(token_no)) {
            callDedicatedProcedure({
                callingProcName: "ProcGenerateTdsBulkTxtFile",
                tokenNo: parseInt(token_no),
                processLevel: parseInt(processLevel)
            });

            //openSuccessModal("Your request is initiated & Your  Generated Token No. is :" + token_no, "callUrl('/textFileDashboard?action=textFileView');");
         openSuccessModal("Token generated Successfully. Token No. is : " + token_no, "callUrl('/textFileDashboard?action=textFileView');");
      
        }
    }

}





function getErrorProcessReport() {
    var cp = document.getElementById("globalcontextpath").value;
    var tokenNo = document.getElementById("tokenNo").value;
    document.getElementById("err_process_rep_btn").disabled = true;
    callingProcData = {
        callingProcName: "GenerateErrorProcessReport",
        tokenNo: tokenNo
    };
    try {
        callDedicatedProcedure(callingProcData);
    } catch (e) {
        console.log(e);
    }
    openSuccessModal("Your error processing report request is initiated", "callUrl('/errorStatus');");

}

function getGeneratedErrorProcessReport() {
    let cp = document.getElementById("globalcontextpath").value;//alert(cp);
    let actionUrl = cp + "/generateErrorProcessReport";
    var process_seqno =  localStorage.getItem("token_no");//alert(process_seqno);
    
    //var process_seqno = document.getElementById("tokenNo").value;//alert(process_seqno);
    $.ajax({
        url: actionUrl,
        type: 'POST',
        data: {
            action: 'getGeneratedErrorProcessReport',
            process_seqno: process_seqno
        },
        beforeSend: function (xhr) {
            showProcessIndicator();
        },
        success: function (response, textStatus, jqXHR) {

            document.getElementById("errorReport").innerHTML = response;
            $("#errorReportbtndiv").hide(true);

        },
        error: function (jqXHR, textStatus, errorThrown) {
        },
        complete: function (jqXHR, textStatus) {
            hideProcessIndicator();
        }
    });


}

function checkprocedureCallOrNot(){
     try{
        var btn = document.getElementById("err_process_rep_btn"); 
        var status = btn.disabled;
        if (status !== "" && status !== "null" && status !== null){
        if(status){
          document.getElementById("disabledmsg").style.display = "block";
        }else{
          document.getElementById("disabledmsg").style.display = "none";
        }
         console.log(btn.disabled);
     }
    } catch(error){
            
        }
}

function callviewProcessDatapage() {
    document.getElementById("errorStatusForm").submit();
}


function downloadReportSetting(i,blobFileDownloadFlag) {
    var fileNamePath = document.getElementById("reportfilelocation~" + i).value;
    var newFileName = document.getElementById("reportfilename~" + i).value;
    var cp = document.getElementById("globalcontextpath").value;
    // var processSeqno =document.getElementById("tokenNo").value;
    var process_seqno = document.getElementById("process_seqno~" + i).value;
    var parent_process_seqno = document.getElementById("parent_process_seqno~" + i).value;
    if (!lhsIsNull(blobFileDownloadFlag)) {
    if(blobFileDownloadFlag === 'T'){
       if (!lhsIsNull(process_seqno)) {
        window.location.href = cp + '/downloadDataDriectFromBlob?process_seqno=' + process_seqno;   
        }
    }else{
     if (!lhsIsNull(fileNamePath)) {
        window.location = cp + "/downloadUsingPathAndDrive?fileNamePath=" + encodeURIComponent(fileNamePath) + "&newFileName=" + encodeURIComponent(newFileName) + "&process_seqno=" + process_seqno + "&parent_process_seqno=" + parent_process_seqno;
     }else{
        openErrorModal("blob File Download Flag is null");     
     }
    }

   
  }else{
     openErrorModal("File name is blank"); 
  }
}//End Function


function bulkTextFilevalidateFilter(){
    showRecordsPaginator();
}

