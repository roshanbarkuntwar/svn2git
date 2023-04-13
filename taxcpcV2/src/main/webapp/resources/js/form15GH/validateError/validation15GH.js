/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function getvalidationLogModalRefresh15GH(tokenNo, processStatus) {

waitingProcess15GH();

}

function waitingProcess15GH() {
document.getElementById("loader").style.display = "block";
const myVar = setTimeout(showPage15GH, 1500);
}

function showPage15GH() {
    var tokenNo = document.getElementById("tokenNo").value;
    var processStatus = document.getElementById("uploadprocessStatus").value; 
    get15GHValidationProcessLog(tokenNo, processStatus);
 
}
function get15GHValidationProcessLog(tokenNo, processStatus) {

 document.getElementById("loader").style.display = "none";
 $("#validationLogModal").modal("show");
 requestProcessLog15GH(tokenNo, processStatus);
}//end function

function requestProcessLog15GH(tokenNo, processStatus) {

    var actionUrl = "/getProcessLogAction?action=readProcessStatus";
    actionUrl += "&tokenNo=" + tokenNo;
    actionUrl += "&processStatus=" + processStatus;

    ajaxPostUrl(actionUrl, processLogStatusResponse15GH);
}//end function

function processLogStatusResponse15GH(response) {
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
            const text1=document.querySelector('#p_log_status');
            const  changeColor1=(a,b)=> a.innerHTML = a.innerHTML.split(b).join(`<span style="color: blue;">${b}</span>`);
            changeColor1(text1,"---*************---ERROR PROCESSING LOG STARTED---*********---");
            const text2=document.querySelector('#p_log_status');
            const  changeColor2=(a,b)=> a.innerHTML = a.innerHTML.split(b).join(`<span style="color: green;">${b}</span>`);
            changeColor2(text2,"----************----TEXT FILE GENERATION STARTED----************----");
            const text3 = document.querySelector('#p_log_status');
            const  changeColor3 = (a, b) => a.innerHTML = a.innerHTML.split(b).join(`<span style="color: green;">${b}</span>`);
            changeColor3(text3, "----************----TEXT FILE GENERATION COMPLETED----************----");
                                                           
    }
}//end function
function getErrorListDetails15GH() {
    showProcessIndicator();
    var contextPath = document.getElementById("globalcontextpath").value;
    $.ajax({
        url: contextPath + "/getPANDetailsAction15GH",
        type: 'POST',
        success: function (response, textStatus, jqXHR) {
            hideProcessIndicator();
            if (!lhsIsNull(response)) {
                document.getElementById("unverifiedPAN").innerHTML = response;
            } else {
                openErrorModal("No Record Found");
            }
        }
    });
}//end function

function fetchFileDashboardDetails15GH() {
    var xmlhttp;
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    } else {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            var q = xmlhttp.responseText;
//            alert(q);
            document.getElementById("fileDashboardPanel").innerHTML = q;
            hideProcessIndicator();
        }
    };
    showProcessIndicator();
    var cp = document.getElementById("globalcontextpath").value;
    var url = cp + "/dynamicFileDashboardAction15GH";
    xmlhttp.open("GET", url);
    xmlhttp.send(null);
}//end function

function checkFileParameter15GHPart2New() {

    var flag = document.getElementById("processCnfRadioDefault").checked;
    if (!flag) {
        document.getElementById("processCnfChkBx").value = false;
        document.getElementById("reprocessFlag").value = "F";
    } else {
        document.getElementById("processCnfChkBx").value = true;
        document.getElementById("reprocessFlag").value = "T";
    }

    //generating token for process error
    var reprocessFlag = document.getElementById("reprocessFlag").value;
    var validate = document.getElementById("processErrors15GHForm_validate").value;
    var loginLevel = document.getElementById("loginLevel").value;


    var actionUrl = "/generateProcessErrorToken15GH?action=getToken&reprocessFlag=" + encodeURIComponent(reprocessFlag)
            + "&validate=" + encodeURIComponent(validate) + "&loginLevel=" + encodeURIComponent(loginLevel);
    ajaxPostUrl(actionUrl, generateProcessErrorTokenResponse1);

    function generateProcessErrorTokenResponse1(response) {
        if (!lhsIsNull(response)) {
            var res = response.split('##');
            if (res[0].toLowerCase().match('success')) {
                var tokenNo = res[1];
                document.getElementById("tokenNo").value = tokenNo;
                try {
                    callDedicatedProcedure({
                        callingProcName: "ProcLhsTds15ghError",
                        tokenNo: tokenNo,
                        clientLoginLevel: loginLevel,
                        validate: validate,
                        reprocessFlag: reprocessFlag
                    });
                } catch (e) {
                    console.log(e);
                }
                //process error show status.....
                openSuccessModal("Your request is initiated. Use Token No.: " + tokenNo, 'document.getElementById("processErrors15GHForm").submit();');

            } else {
                openErrorModal("Token could not be generated.");
            }
        } else {
            openErrorModal("Some error occurred, please try again.");
        }
    }//end function
}//end function

function callUserLevelPopup(showModal = false) {
    var selectedUserLevelCode = document.getElementById("selectedUserLevelCode").value;
//    alert(selectedUserLevelCode);
    if (lhsIsNull(selectedUserLevelCode) || showModal) {
        $('#userLevelModal').modal('show');
    } else {
        refreshProcessErrorSummary15GH(selectedUserLevelCode);
        getDynamicFileUplodErrorDetailsGrid15GH(selectedUserLevelCode);
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

        refreshProcessErrorSummary15GH(selectedUserLevel);
        getDynamicFileUplodErrorDetailsGrid15GH(selectedUserLevel);

        $('#userLevelModal').modal('hide');
    } else {
        addActionError("message", "Please select User Level");
    }
}

function getDynamicFileUplodErrorDetailsGrid15GH(selectedUserLevel) {

    var actionUrl = "/getDynamicFileUplodErr15GH?processLevel=" + selectedUserLevel;

    ajaxPostUrl(actionUrl, getDynamicFileUplodErrorDetailsGrid15GHResponse);

}//end function

function refreshProcessErrorSummary15GH(selectedUserLevel) {

    var userLevels = {
        0: 'Login Level',
        1: 'All (Login &amp; Branch Level)'
    }; // Values for selected user levels

    var cp = document.getElementById("globalcontextpath").value;
    var url = cp + "/ProcessErrorResultAction15GH?refreshFlag=true&validate=true&reprocessFlag=F&processLevel=" + encodeURIComponent(selectedUserLevel);
//    alert(url);
    $.ajax({
        url: url,
        type: 'GET',
        beforeSend: function (xhr) {
            showProcessIndicator();
        },
        success: function (data, textStatus, jqXHR) {
//            console.log(data);
            document.getElementById("selectedUserLevelText").innerHTML = userLevels[selectedUserLevel];
            document.getElementById("selectedUserLevelCode").value = selectedUserLevel;

            if (!lhsIsNull(data)) {
                document.getElementById("processErrorSummaryDiv15GH").innerHTML = data;
            }
        },
        error: function (jqXHR, status) {
            hideProcessIndicator();
        },
        complete: function (jqXHR, textStatus) {
            hideProcessIndicator();
        }
    });
}//end function

function getDynamicFileUplodErrorDetailsGrid15GHResponse(response) {
    if (!lhsIsNull(response)) {
        document.getElementById("dynamicFileUplodErrorDetailsGrid15GH").setAttribute("style", "");
        document.getElementById("dynamicFileUplodErrorDetailsGrid15GH").innerHTML = response;
    } else {
        openErrorModal("No Record Found");
    }
}//end function

function openBulkReviewModal15GH(id) {
    if (!lhsIsNull(id)) {
        var splitArr = id.split("~");
        var error_code = document.getElementById("error_code~" + splitArr[1]).value;
        document.getElementById("R_ErrorCodeID").value = error_code;
        document.getElementById("R_ID").value = splitArr[1];
        $('#myModalBulkReview').modal({show: true});
        $('#review_remark_id').val('')
    }
}//end function
function updateBulkReview15GH(){
     $('#myModalBulkReview').modal('hide');
    document.getElementById("dialog-confirm_change_review").style.display = "block";

    $(function () {
        $("#dialog-confirm_change_review").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                "Yes": function () {

                    $(this).dialog("close");
                    updateBulkReview15GHupdate();

                },
                "No": function () {

                    $(this).dialog("close");
                }
            }
        });
    });

    
}


function updateBulkReview15GHupdate() {

//    alert('1');
    var error_code = document.getElementById("R_ErrorCodeID").value;
//    alert('2');
    var review = document.getElementById("review_remark_id").value;

    var R_ID = document.getElementById("R_ID").value;

//    var chkProcessFlag = window.parent.document.getElementById("processCnfChkBxID").value;
//    alert('4');
    if (review !== "" && review !== "null" && review !== null) {
        var cp = document.getElementById("globalcontextpath").value;
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
                hideProcessIndicator();
                var q = xmlhttp.responseText;
                q = q.trim();
                if (q === 'updateReview') {
//                    if (chkProcessFlag !== null && chkProcessFlag !== "" && chkProcessFlag !== "null" && chkProcessFlag === 'false') {
//                        window.location.reload();
//                    } else {
                        try {
                            window.parent.document.getElementById("reviewDetails~" + R_ID).className="fa fa-eye text-success cursor-pointer mr-1 ml-3";
                                   // style.color = "green";
                        } catch (err) {
                        }
                    openSuccessModal("Review Updated Successfully");
                    $('#myModalBulkReview').modal('hide');
//                    }
                } else {
                    openErrorModal("Some Error Occured, Could Not Update");
                    $('#myModalBulkReview').modal('hide');
                }
            }
        };
        var url = cp + "/errorManipulationAction15GH?action=BulkReview";
        //alert(url)
        url += "&errorCode=" + encodeURIComponent(error_code);
        url += "&reviewValue=" + encodeURIComponent(review);
//        url += "&processCnfChkBx=" + encodeURIComponent(chkProcessFlag);
        xmlhttp.open("GET", url);
        xmlhttp.send(null);
    } else {
        openErrorModal("Please Write Review");
    }
}//end function

function show_error_details15GH(id) {
    if (!lhsIsNull(id)) {
        var splitArr = id.split("~");
        var error_type_code = document.getElementById("error_type_code~" + splitArr[1]).value;
        var error_type_name = document.getElementById("error_type_name~" + splitArr[1]).value;
        var error_name = document.getElementById("error_name~" + splitArr[1]).value;
        var error_code = document.getElementById("error_code~" + splitArr[1]).value;
        var table_name = document.getElementById("table_name~" + splitArr[1]).value;
        var chkProcessFlag = window.parent.document.getElementById("processCnfChkBx").value;
        var upload_purpose = window.parent.document.getElementById("upload_purpose").value;
        var l_error_process_type = window.parent.document.getElementById("l_error_process_type").value;
        var clientLoginLevel = window.parent.document.getElementById("clientLoginLevel").value;
        var selectedValue = window.parent.document.getElementById("selectedValue").value;
        var selectedUserLevel = window.parent.document.getElementById("selectedUserLevelCode").value;

        if (splitArr[0] === 'deductee15ghErrorDetail') {//used to show deductee 15gh error detail
            var url = "/deducteeErrorDetailAction15GH?action=showDeductee15ghErrDetails";
            url += "&l_error_type_code=" + encodeURIComponent(error_type_code);
            url += "&l_error_type_name=" + encodeURIComponent(error_type_name);
            url += "&l_error_name=" + encodeURIComponent(error_name);
            url += "&l_error_code=" + encodeURIComponent(error_code);
            url += "&table_name=" + encodeURIComponent(table_name);
            url += "&processCnfChkBx=" + encodeURIComponent(chkProcessFlag);
            url += "&ReadonlyFlag=false";
            url += "&upload_purpose=" + encodeURIComponent(upload_purpose);
            url += "&l_error_process_type=" + encodeURIComponent(l_error_process_type);
            url += "&clientLoginLevel=" + encodeURIComponent(clientLoginLevel);
            url += "&selectedValue=" + encodeURIComponent(selectedValue);
            url += "&reprocessFlag=F";
            url += "&selectedUserLevel=" + encodeURIComponent(selectedUserLevel);

            callUrl(url);
        }
    }
}//end function

function updateDefaultValueData15GH() {
    var error_code = document.getElementById("error_code").value;
    var column_name = "";
    try {
        column_name = document.getElementById("column_name~1").value;
    } catch (err) {
        column_name = "";
    }
    var defaultColumnValue = document.getElementById("defaultColumnValue").value;
    var chkProcessFlag = window.parent.document.getElementById("processCnfChkBxID").value;
    if (column_name !== "" && column_name !== "null" && column_name !== null) {
        var cp = document.getElementById("globalcontextpath").value;
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
                //removeError();
                var q = xmlhttp.responseText;
                q = q.trim();
                // alert(q);
                hideProcessIndicator();
                if (q === 'update') {
//                    if (chkProcessFlag !== null && chkProcessFlag !== "" && chkProcessFlag !== "null" && chkProcessFlag === 'false') {
//                        window.location.reload();
////                        refreshRecord15GH();
//                    } else {
                    openSuccessModal("Record Update Successfully", "refreshRecord15GH()");
//                    }
                } else {
                    openErrorModal("Some Error Occured, Could Not Update");
                }
            }
        };

        var url = cp + "/errorManipulationAction15GH?action=updateDefaultValue";
        url += "&ErrorCode=" + encodeURIComponent(error_code);
        url += "&defaultColumnName=" + encodeURIComponent(column_name);
        url += "&defaultColumnValue=" + encodeURIComponent(defaultColumnValue);
        url += "&processCnfChkBx=" + encodeURIComponent(chkProcessFlag);
        xmlhttp.open("GET", url);
        xmlhttp.send(null);
    } else {
        openErrorModal("No Record Found For Update");
    }
}//end function

function refreshRecord15GH() {
    showProcessIndicator();
    var upload_purpose = window.parent.document.getElementById("upload_purpose").value;
    var l_error_process_type = window.parent.document.getElementById("l_error_process_type").value;
    var processCnfChkBx = window.parent.document.getElementById("processCnfChkBxID").value;
    var clientLoginLevel = window.parent.document.getElementById("clientLoginLevel").value;
    var selectedValue = window.parent.document.getElementById("selectedValue").value;

    var url = "/ProcessErrorResultAction15GH?validate=true"
            + "&upload_purpose=" + encodeURIComponent(upload_purpose)
            + "&l_error_process_type=" + encodeURIComponent(l_error_process_type)
            + "&processCnfChkBx=" + encodeURIComponent(processCnfChkBx)
            + "&clientLoginLevel=" + encodeURIComponent(clientLoginLevel)
            + "&selectedValue=" + encodeURIComponent(selectedValue)
            + "&reprocessFlag=F";

    callUrl(url);
}//end function

function processErrorCheckProcedure15GH() {
    var upload_purpose = document.getElementById("upload_purpose").value;
    var l_error_process_type = document.getElementById("l_error_process_type").value;
    var processCnfChkBx = document.getElementById("processCnfChkBx").value;
    var clientLoginLevel = document.getElementById("clientLoginLevel").value;
    var loginLevel = document.getElementById("loginLevel").value;
    var url = "/ProcessErrorResultAction15GH?validate=true"
            + "&upload_purpose=" + encodeURIComponent(upload_purpose)
            + "&l_error_process_type=" + encodeURIComponent(l_error_process_type)
            + "&processCnfChkBx=" + encodeURIComponent(processCnfChkBx)
            + "&clientLoginLevel=" + encodeURIComponent(clientLoginLevel)
            + "&reprocessFlag=T&loginLevel=" + encodeURIComponent(loginLevel);

    callUrl(url);
}//end function

function killErrorProcessing15GH(id, btn) {
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
                    ajaxPostUrl(url, responseFunction1);
    
                },
                Cancel: function () {
                    $(this).dialog("close");
                }
            }
        });
    });
}

function responseFunction1(response) {
    if (!lhsIsNull(response) && response === 'success') {
        openSuccessModal("Task Killed Successfully !");
        showProcessIndicator();
        setTimeout(function () {
            window.location.reload();
        }, 1000);
    } else {
        openErrorModal("Some Error Occurred !");
    }
}

function refreshTokenStatus15GH(id) {
    
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
                                 var tokenNo = $("#" + id).val();
                                 if (!lhsIsNull(tokenNo.trim())) {
                                     var url = "/refreshErrorProcessTokenStatus15GH?tokenNo=" + tokenNo + "&action=refresh";
                                     ajaxPostUrl(url, refreshResponseFunction1);
                                 } else {
                                     openErrorModal("Some Error Occurred ! Couldn't Refresh Records !");
                                     hideProcessIndicator();
                                 }
                        },         
                        Cancel: function () {                
                       $(this).dialog("close");             
                }
            }
        });
    });          
}//end function

function refreshResponseFunction1(response) {
    if (!lhsIsNull(response) && response !== "error") {
//        console.log('response: ' + response);
        var json = JSON.parse(response);
        $("#tokenNo").val(json.tokenNo);
        $("#t_processType").val(json.processType);
        $("#t_processTypeName").val(!lhsIsNull(json.processType) ? "Level-" + json.processType : "");
        $("#t_processStartTimestamp").val(json.processStartTimestamp);
        $("#t_processEndTimestamp").val(json.processEndTimestamp);
        $("#t_processRemark").val(json.processRemark);
        hideProcessIndicator();
        try {
            if (json.processStatus === "EA") {
//                document.getElementById("killBtn").disabled = false;
//                document.getElementById("refreshBtn").disabled = false;
//                document.getElementById("viewBtn").disabled = true;
//                document.getElementById("processBtn").disabled = true;

            } else if (json.processStatus === "EB") {
//                document.getElementById("killBtn").disabled = false;
//                document.getElementById("refreshBtn").disabled = false;
//                document.getElementById("viewBtn").disabled = true;
//                document.getElementById("processBtn").disabled = false;

            } else if (json.processStatus === "EC") {
//                document.getElementById("killBtn").disabled = false;
//                document.getElementById("refreshBtn").disabled = false;
//                document.getElementById("viewBtn").disabled = false;
//                document.getElementById("processBtn").disabled = true;

            } else if (json.processStatus === "EK") {
//                document.getElementById("killBtn").disabled = true;
//                document.getElementById("refreshBtn").disabled = true;
//                document.getElementById("viewBtn").disabled = true;
//                document.getElementById("processBtn").disabled = false;
            }
        } catch (e) {
        }
    } else {
        openErrorModal("Some Error Occurred ! Couldn't Refresh Records !");
        hideProcessIndicator();
    }
}//end function


function getTextFileData() {

    $("#fileModal").modal("show", true);
    refereshStatus();

}

function refereshStatus() {
    var cp = document.getElementById("globalcontextpath").value;
    var tokenNo = $("#tokenNo").val();
    var uploadprocessStatus = $("#uploadprocessStatus").val();
    var url = "/get15GhOtherAjaxData?tokenNo=" + tokenNo + "&action=readStatus&processType=" + uploadprocessStatus;
    ajaxPostUrl(url, getTextFileDataResponse1);
}

function getTextFileDataResponse1(response) {
//    hideProcessIndicator();
    if (!lhsIsNull(response)) {
        if (response.includes("#")) {
            var data = response.split("#");
            $("#lastModified").html(data[0]);
            $("#size").html(data[1]);
            $("#fileStatus").html(data[2]);
        } else {

            $("#fileStatus").html(response);
        }
    }
}

function goToXMLGeneration() {
    var tokenNo = $("#tokenNo").val();
    var loginLevel = $("#t_processType").val();
    var url = "/get15GhOtherAjaxData?action=generateXML&tokenNo=" + tokenNo + "&loginLevel=" + loginLevel;
    ajaxPostUrl(url, goToXMLGenerationResponse);
}


function goToXMLGenerationResponse(response) {
//    hideProcessIndicator();
    if (!lhsIsNull(response)) {
        if (response.includes("success")) {
            openSuccessModal("Your process initiated please visit after some time");
        } else {
            openErrorModal("Some Error Occurred !");
        }
    }
}

function downloadErrorDetails15GH(downloadBtn) {

    var cp = document.getElementById("globalcontextpath").value;
    var codeLevel = document.getElementById("selectedUserLevelCode").value;

    var url = cp + "/refreshErrorProcessTokenStatus15GH?action=downloadError&codeLevel=" + encodeURIComponent(codeLevel);

    $.ajax({
        url: url,
        type: 'GET',
        beforeSend: function (xhr) {
            downloadBtn.disabled = true;
            showProcessIndicator();
        },
        success: function (data, textStatus, jqXHR) {
            if (!lhsIsNull(data) && data.includes("#")) {
                openSuccessModal("Your Process Intitiated ! Check Your Status With Token No. " + data.split("#")[1]);

            } else {
                openErrorModal("Some Error Occurred ! Could Not Initiate Process!");
            }
        },
        error: function (xhr) {
            hideProcessIndicator();
        },
        complete: function (jqXHR, textStatus) {
            hideProcessIndicator();
//            downloadBtn.disabled = false;
        }
    });
}

function goToCSVGeneration() {
    var tokenNo = $("#tokenNo").val();
    var loginLevel = $("#t_processType").val();
    var url = "/get15GhOtherAjaxData?action=generateCSV&tokenNo=" + tokenNo + "&loginLevel=" + loginLevel;
    ajaxPostUrl(url, goToCSVGenerationResponse);
}


function goToCSVGenerationResponse(response) {
    if (!lhsIsNull(response)) {
        if (!lhsIsNull(response) && response.includes("success")) {
            let tokenNo = response.split("#")[1];
            try {
                callDedicatedProcedure({
                    callingProcName: "ProcGen15ghBulkCsvFile",
                    tokenNo: tokenNo
                });
            } catch (e) {
                console.log('%cprocedure calling ajax error', 'color: red;');
                console.log(e);
            }
            openSuccessModal("Generated Token No. is : " + tokenNo);
        } else {
            openErrorModal("Some Error Occurred !");
        }
    }
}