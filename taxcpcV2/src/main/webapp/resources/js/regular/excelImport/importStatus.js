function openNotificationDiv() {

    var processSeqNo = document.getElementById("processSeqNo").value;
    if (!lhsIsNull(processSeqNo) && processSeqNo.includes("#")) {

        let tokenNo = processSeqNo.split("#")[1].trim();

        try {
            callDedicatedProcedure({
                callingProcName: "ProcInsertTempData",
                tokenNo: tokenNo
            });
        } catch (e) {
            console.log('%cprocedure calling ajax error', 'color: red;');
            console.log(e);
        }

        document.getElementById("dialog-skip-upload-success-p").innerHTML = "File Uploaded Successfully ! Generated Token No. is : " + processSeqNo.split("#")[1];
        document.getElementById("dialog-skip-upload-success").style.display = "block";


        $(function () {
            $("#dialog-skip-upload-success").dialog({
                modal: true,
                buttons: {
                    Ok: function () {
                        $(this).dialog("close");
                        window.location.reload();
                    }
                }
            });
        });
    }
}

function killProcess(id) {
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
                    var tokenNo = $("#" + id).val();
                    var uploadprocessTypeCode = $("#uploadprocessTypeCode").val();
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


function refreshStatus(id) {
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
                        var url = "/getDataValueAction?tokenNo=" + tokenNo + "&action=refresh";
                        ajaxPostUrl(url, refreshResponseFunction);
                },
                Cancel: function () {
                    $(this).dialog("close");
                }
            }
        });
    });
    
}

function refreshResponseFunction(response) {

    if (!lhsIsNull(response) && response !== "error") {
        var json = JSON.parse(response);
        $("#uploadTokenNo").val(json.tokenNo);
        $("#uploadProcessType").val(json.templateName);
        $("#tokenUploadObj_processTypeName").val(json.processTypeName);
        $("#tokenUploadObj_processStartTimestamp").val(json.processStartTimestamp);
        $("#tokenUploadObj_processRemark").val(json.processRemark);
        $("#uploadTemplateCode").val(json.templateCode);
        $("#uploadprocessTypeCode").val(json.processType);
        $("#uploadprocessStatus").val(json.processStatus);
        try {

            if (json.processStatus === "TA") {
                document.getElementById("killBtn").disabled = false;
                document.getElementById("viewBtn").disabled = true;
                document.getElementById("importBtn").disabled = true;

            } else if (json.processStatus === "TB") {
                document.getElementById("killBtn").disabled = true;
                document.getElementById("viewBtn").disabled = true;
                document.getElementById("importBtn").disabled = false;


            } else if (json.processStatus === "TC") {
                document.getElementById("killBtn").disabled = false;
                document.getElementById("importBtn").disabled = true;
                document.getElementById("viewBtn").disabled = false;


            } else if (json.processStatus === "TD") {
                document.getElementById("killBtn").disabled = true;
                document.getElementById("importBtn").disabled = true;
                document.getElementById("viewBtn").disabled = true;

            } else if (json.processStatus === "TE") {
                document.getElementById("killBtn").disabled = true;
                document.getElementById("importBtn").disabled = true;
                document.getElementById("viewBtn").disabled = true;


            } else if (json.processStatus === "TF") {

                document.getElementById("killBtn").disabled = true;
                document.getElementById("importBtn").disabled = false;
                document.getElementById("viewBtn").disabled = true;

            }  else if (json.processStatus === "RA") {
                document.getElementById("killBtn").disabled = false;
                document.getElementById("importBtn").disabled = true;
                document.getElementById("viewBtn").disabled = true;


             } else if (json.processStatus === "RC") {
                document.getElementById("killBtn").disabled = false;
                document.getElementById("importBtn").disabled = true;
                document.getElementById("viewBtn").disabled = false;


             } else if (json.processStatus === "RB") {
                document.getElementById("killBtn").disabled = false;
                document.getElementById("importBtn").disabled = true;
                document.getElementById("viewBtn").disabled = true;

            }else {
                document.getElementById("killBtn").disabled = false;
                document.getElementById("importBtn").disabled = false;
                document.getElementById("viewBtn").disabled = false;
             }
        } catch (Err) {
            console.log("err..." + Err);

        }


        hideProcessIndicator();


    } else {
        openErrorModal("Some Error Occurred ! Couldn't Refresh Records !")
        hideProcessIndicator();
    }

}
function getTextFileDataRefresh() {
//$( document ).ready(function() {
//   $("#fileModal").modal("hide");
//   getTextFileData();
//    });
waitingProcess();

}
function waitingProcess() {
document.getElementById("loader").style.display = "block";
const myVar = setTimeout(showPage, 1500);
}

function showPage() {
    getTextFileData();
 }

function getTextFileData() {
$( document ).ready(function() {
     document.getElementById("loader").style.display = "none";
     $("#fileModal").modal("show", true);
    refereshStatus();
});
  

}

function refereshStatus() {
    var tokenNo = $("#uploadTokenNo").val();
    var templateCode = $("#uploadTemplateCode").val();
    var uploadprocessStatus = $("#uploadprocessStatus").val();
//    var url = "/getDataValueAction?tokenNo=" + tokenNo + "&action=readStatus&processType=" + uploadprocessStatus;
    var url = "/getDataValueAction?tokenNo=" + tokenNo + "&action=readStatus&processType=" + uploadprocessStatus + "&templateCode=" + templateCode;
    ajaxPostUrl(url, getTextFileDataResponse);

}

function getTextFileDataResponse(response) {
//    hideProcessIndicator();
    if (!lhsIsNull(response)) {
        if (response.includes("#")) {
            var data = response.split("#");
            $("#lastModified").html(data[0]);
            $("#size").html(data[1]);
            $("#fileStatus").html(data[2]);
        } else {

            $("#fileStatus").html(response);
//            const text1=document.querySelector('#fileStatus');
//            const  changeColor1=(a,b)=> a.innerHTML = a.innerHTML.split(b).join(`<span style="color: blue;">${b}</span>`);
//            changeColor1(text1,"---*************---ERROR PROCESSING LOG STARTED---*********---");
//            const text2=document.querySelector('#p_log_status');
//            const  changeColor2=(a,b)=> a.innerHTML = a.innerHTML.split(b).join(`<span style="color: green;">${b}</span>`);
//            changeColor2(text2,"----************----TEXT FILE GENERATION STARTED----************----");
        }
    }

}


function getTextFileDataForIndivv(tokenNo, templateCode, uploadProcessStatus) {
    $("#fileModal").modal("show", true);
    var url = "/getDataValueAction?tokenNo=" + tokenNo + "&action=readStatus&processType=" + templateCode + "&templateCode=" + uploadProcessStatus;
    ajaxPostUrl(url, getTextFileDataResponse);
}

function killIndivProcess(tokenNo, uploadprocessTypeCode) {
      document.getElementById("dialog-confirm_process_kill").style.display = "block";
    $(function () {
        $("#dialog-confirm_process_kill").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                "Ok": function () {
                    
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

function changeProcessType() {
    let processType = $("#mainProcessType").val();
    if (!lhsIsNull(processType)) {
        let url = "/getTokenStatus?mainProcessType=" + processType;
        ajaxPostUrl(url, responseProcessType);
    } else {
        window.location.reload();
    }
}
function responseProcessType(response) {
    if (!lhsIsNull(response)) {
        $('#procesType option').remove();
        let dataLength = response.substr(1, response.length - 2)
        let processTypeData = dataLength.split(",");
        $("#tokenNo").prop('disabled', false);
        $('#procesType').append('<option value="">Select Process Type</option>');
        $.each(processTypeData, function (key, value) {
            $('#procesType').append('<option value="' + value.split("=")[0] + '">' + value.split("=")[1] + '</option>');
        });

    }
    
    
}


function getValidationProcessLog(tokenNo, processStatus) {
    $("#validationLogModal").modal("show", true);

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
    }
}//end function






function getTokenStatusRefresh() {
    document.getElementById("loader").style.display = "block";
    const myVar = setTimeout(showPagelog, 1500);
}

function showPagelog() {
   getTextFileDataForIndiv(token_No_, template_Code_,upload_Process_Status_);
}
var token_No_;
var template_Code_;    
var upload_Process_Status_;

function getTextFileDataForIndiv(tokenNo, templateCode, uploadProcessStatus){
 token_No_ =tokenNo;
 template_Code_ =templateCode;    
 upload_Process_Status_ =uploadProcessStatus;
    document.getElementById("loader").style.display = "none";
    $("#fileModal").modal("show");
    requestTokenStatus(tokenNo, templateCode, uploadProcessStatus);
}//end function

function requestTokenStatus(tokenNo, templateCode, uploadProcessStatus) {
    var actionUrl = "/getProcessLogAction?action=readProcessStatus";
    actionUrl += "&tokenNo=" + tokenNo;
    actionUrl += "&processType=" + templateCode;
    actionUrl += "&templateCode=" + uploadProcessStatus;
    ajaxPostUrl(actionUrl, tokenstatusLogResponse);
}//end function

function tokenstatusLogResponse(response) {
    if (!lhsIsNull(response)) {
        $("#token_number").text(token_No_);
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
        
    }
    document.getElementById("loader").style.display = "none";
    
}//end function

