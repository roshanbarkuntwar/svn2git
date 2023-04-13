/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var cp = document.getElementById("globalcontextpath").value;

$(document).ready(function () {
    try {
        defaultValues();
    } catch (err) {
    }
    try {
//        getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);
        document.getElementById("paginationBlock").style.display = "none";
    } catch (e) {

    }
});

function deleteProcessByProcessId(rowIndex) {
    //var confirmDelete = confirm("Are you sure you want to delete this process?");

    document.getElementById("dialog-confirm_delete_proc_id").style.display = "block";
    $(function () {
        $("#dialog-confirm_delete_proc_id").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                "Ok": function () {
                    $(this).dialog("close");
                    deleteCurrentProcess(rowIndex);
                },
                Cancel: function () {
                    $(this).dialog("close");
                }
            }
        });
    });
}//end function

function deleteCurrentProcess(rowIndex) {

    var cp = document.getElementById("globalcontextpath").value;
    var actionUrl = cp + "/deleteProcessBySeq?action=deleteProcess";

    var processSeqNo = document.getElementById("process_seq~" + rowIndex).value;
    var templateCode = document.getElementById("template_code~" + rowIndex).value;

    if (!lhsIsNull(processSeqNo)) {
        $.ajax({
            url: actionUrl,
            type: 'POST',
            data: {
                processSeqNo: processSeqNo,
                templateCode: templateCode
            },
            beforeSend: function (xhr) {
                showProcessIndicator();
            },
            success: function (data, textStatus, jqXHR) {
             if(!lhsIsNull(data)){
              var output =data.split("#");
              if (output[0] === "success") {
               openSuccessModal("Your request is initiated.", "callUrl('/processDetails');");   
              }else if(output[0] === "error") {
               openErrorModal("Could not delete this process", "callUrl('/processDetails');");
              }else {
               openErrorModal("Some error occurred, could not delete this process", "callUrl('/processDetails');");
              }
                
            }else {
                    openErrorModal("Some error occurred, could not delete this process", "callUrl('/processDetails');");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                hideProcessIndicator();
            },
            complete: function (jqXHR, textStatus) {
                hideProcessIndicator();
            }
        });
    } else {
        openErrorModal("Can not delete this process", "callUrl('/processDetails');");
    }
}//end function

function getProcessSequenceNumbersList(template_code) {
    let actionUrl = cp + "/processSequenceNumbersAjax?templateCode=" + encodeURIComponent(template_code);
    fetch(actionUrl, {
        method: 'GET',
        headers: {
//            'Content-Type': 'application/json',
            'Accept': 'text/html'
        },
//        body: JSON.stringify(params)
    }).then(response => response.text())
            .then(resp => {
                $('#processSeqList').html(resp);
//                console.log('resp', resp);
            }).catch(err => console.log(err));
}//end function

function getUploadedFile(count){
  const filepath =  document.getElementById("import_filepath~"+count).value;
  //alert(filepath);
  if(!lhsIsNull(filepath)){
  const filename = filepath.substring(filepath.lastIndexOf("\\") + 1);
  if(!lhsIsNull(filename)){
       let result = filename.includes(".") ? "Yes" : "No";
       if(result ==='Yes'){
         downloadUsingPath(filepath, filename);
       }else{
            openErrorModal("File Path Not Available");  
       }
  }else{
       openErrorModal("File Path Not Available");  
  }
  }else{
      openErrorModal("File Path Not Available");  
  }
}



function killProcessDetails(tokenNo, uploadprocessTypeCode) {
      document.getElementById("dialog-confirm_process_kill").style.display = "block";
    $(function () {
        $("#dialog-confirm_process_kill").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                "Ok": function () {
                    $(this).dialog("close");
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

var tok_no;
var proc_status;
function processDetailsLogRefresh(tokenNo, processStatus) {
    tok_no  =tokenNo;
    proc_status =processStatus;
    document.getElementById("loader").style.display = "block";
    const myVar = setTimeout(showPage, 1500);
}

function showPage() {
    getProcessDetailsProcessLog(tok_no, proc_status);
}


function getProcessDetailsProcessLog(tokenNo, processStatus){
    $("#span_token_no").text(tokenNo);
    $('#process_log_refresh').attr("onclick", "processDetailsLogRefresh('"+tokenNo+"','"+processStatus+"')");
    $("#processDetailsLogModal").modal("show", true);
    requestProcessLog(tokenNo, processStatus);
}//end function

function requestProcessLog(tokenNo, processStatus){
    var actionUrl = "/getProcessLogAction?action=readProcessStatus";
    actionUrl += "&tokenNo=" + tokenNo;
    actionUrl += "&processStatus=" + processStatus;
    ajaxPostUrl(actionUrl, processLogStatusResponse);
}//end function

function processLogStatusResponse(response){
    if (!lhsIsNull(response)){
        if (response.includes('#')) {
            var results = response.split('#');
            $("#p_last_modify").html(results[0]);
            $("#p_file_size").html(results[1]);
            $("#p_log_status").html(results[2]);
        }else{
            $("#p_last_modify").html('');
            $("#p_file_size").html('');
            $("#p_log_status").html(response);
        }
    }
     document.getElementById("loader").style.display = "none";
}//end