/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var ajax_call_enabled = false;

function bulkFileDownload(downloadFor) {
    if (!lhsIsNull(downloadFor)) {
        var cp = document.getElementById("globalcontextpath").value;
        var actionUrl = cp + "/bulkDownloadAction";

//        var processLevel = document.getElementById("h_processLevel").value;
        var form_id = document.getElementById("fltrformId").value;
        var filter_column_string = getFilterColumnsAndValues(form_id);

        try {
            var processLevel = document.getElementById("h_processLevel").value;
        } catch (e) {
        }


        try {
            var bulk_error_download;
            if (downloadFor.search('error_') !== -1) {
                var split_value = downloadFor.split('_');
                downloadFor = split_value[1];
                bulk_error_download = split_value[0] + '_download';
            }
            bulk_error_download = (bulk_error_download !== undefined) ? bulk_error_download : '';
        } catch (e) {
        }
        //return;

        if (!ajax_call_enabled) {
            ajax_call_enabled = true;
            showProcessIndicator();

            $.ajax({
                url: actionUrl,
                type: 'GET',
                data: {
                    action: 'bulkFileGenerate',
                    bulkDownloadFor: encodeURIComponent(downloadFor),
                    filter_column_string: encodeURIComponent(filter_column_string),
                    bulk_error_download_flag: bulk_error_download,
                    processLevel: processLevel
                },
                success: function (data, textStatus, jqXHR) {
                    if (!lhsIsNull(data)) {
                        ajax_call_enabled = false;
                        hideProcessIndicator();

                        if (data.match("success")) {
                            let token = data.split('#')[1];
                            openSuccessModal('Your process is initiated. Use token number - ' + token);
//                            setTimeout(function () {
//                                window.location = cp + "/downloadStatusBrowse";
//                            }, 1000);
                        } else {
                            openErrorModal("Could not download file, please try again.");
                        }
                    } else {
                        openErrorModal("Some error occurred, could not download bulk file.");
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    ajax_call_enabled = false;
                    hideProcessIndicator();
                }
            });
        } else {
            openErrorModal("Please wait your previous request is in progrss.");
        }
    } else {
        openErrorModal("File not available.");
    }
}//end function

function getFilterColumnsAndValues(form_id) {
    var filter_columns_str = '';
    if (!lhsIsNull(form_id)) {
        var form_element = document.getElementById(form_id);
        var input_elements = $(form_element).find("input[type=text], input[class*=filter_field], select");

        //Iterate for input tags
        $.each(input_elements, function (i, elem) {
            var column_name = $(elem).attr('name');
            var column_value = $(elem).val();

            if (!lhsIsNull(column_value)) {
                if (column_name.indexOf('.') > 0) {
                    column_name = column_name.split('.')[1];
                }
                filter_columns_str += column_name + '~' + column_value + '~~';
            }
        });
    }
    console.log('filter_columns_str: ' + filter_columns_str);
    return filter_columns_str;
}//end function

function requestForBulkData(button, bulkActionName) {

    if (!lhsIsNull(bulkActionName)) {
        button.disabled = true;

        var cp = document.getElementById("globalcontextpath").value;
        var actionUrl = cp + "/allBulkDownloadAction?action=" + encodeURIComponent(bulkActionName);

        if (!ajax_call_enabled) {
            $.ajax({
                url: actionUrl,
                type: 'GET',
                beforeSend: function (xhr) {
                    ajax_call_enabled = true;
                    showBlackProcessIndicator();
                },
                success: function (data, textStatus, jqXHR) {
                    if (!lhsIsNull(data)) {
                        if (data.match("success")) {
                            openSuccessModal("Your process is initiated.");
                            setTimeout(function () {
                                window.location = cp + "/downloadStatusBrowse";
                            }, 1000);
                        } else {
                            openErrorModal("Could not download file, please try again.");
                        }
                    } else {
                        openErrorModal("Some error occurred, could not download bulk file.");
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    //ajax_call_enabled = false;
                    //hideProcessIndicator();
                },
                complete: function (jqXHR, textStatus) {
                    ajax_call_enabled = false;
                    hideBlackProcessIndicator();
                }
            });
        } else {
            openErrorModal("Please wait your previous request is in progrss.");
        }
    } else {
        openErrorModal("Could not download file, please try again.");
    }
}//end function

function downloadBulkFile(index, tokenNo, blobFileDownloadFlag ) {
    var cp = document.getElementById("globalcontextpath").value;
    var fileName = document.getElementById("fileNamePath_" + index).value;
    var zipFileNamePath = document.getElementById("zipFileNamePath_" + index).value;
    var flag = document.getElementById("flag_" + index).value;
    if (!lhsIsNull(flag) && flag === 'j') {
        var zipFileName = zipFileNamePath.split("\\");
        downloadUsingPath(zipFileNamePath, zipFileName[4]);
    } else {
        if(!lhsIsNull(blobFileDownloadFlag) && blobFileDownloadFlag === 'T'){
          var actionUrl= "/downloadDataDriectFromBlob?process_seqno=" + tokenNo; 
          callUrl(actionUrl);
        }else{ 
            var actionUrl = "/bulkDownloadAction?action=bulkFileDownload&token_no=" + encodeURIComponent(tokenNo) + "&fileName=" + encodeURIComponent(fileName);
            callUrl(actionUrl);
       }
    }

}//end function

function killProcess(tokenNo) {
    var url = "/getDataValueAction?tokenNo=" + tokenNo + "&action=killProcess&processType=DA";
    ajaxPostUrl(url, responseFunction);
}//end function


function responseFunction(response) {
    if (!lhsIsNull(response) && response === 'success') {
        openSuccessModal("Task Killed Successfully !");
        showProcessIndicator();
        setTimeout(function () {
            window.location.reload();
        }, 1000);
    } else {
        openErrorModal("Some Error Occurred !");
    }

}//end function

function createZipBulkTextFile() {
    let tokenNo = $("#tokenNo").val();
    var processType = $("#processType").val();
    let clientLoginLevel = $('#clientLoginLevel').val();
    var cp = document.getElementById("globalcontextpath").value;
    if (!lhsIsNull(tokenNo) && !lhsIsNull(processType)) {
        if (clientLoginLevel !== '6') {
            showBlackProcessIndicator();
            showTextMsg("Please wait, While creating bulk ZIP TEXT file... ")
            let url = cp + "/zipBulkTextFile?tokenNo=" + tokenNo;
            $.ajax({
                type: "POST",
                url: url,
                success: function (response) {
                    hideBlackProcessIndicator();
                    hideShowTextMsg();
                    if (!lhsIsNull(response)) {
                        if (!lhsIsNull(response) && response.includes("success")) {
                            openSuccessModalWithCopyText("Your bulk text file zip has been created successfully. Please Take it from this location", response.split("#")[1], "window.location.reload();");
                        } else if (!lhsIsNull(response) && response.includes("error")) {
                            openErrorModal(response.split("#")[1], "window.location.reload();");
                        }
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(errorThrown);
                }
            });
        } else {
            openErrorModal("You can not Create Bulk Zip text File, please contact to your Upper Level");
        }
    } else {
        openErrorModal("Please Select Process Type & Enter Token Number");
    }
}

function downloadBlobFile(blobDownloadFlag,count) {
    var cp = document.getElementById("globalcontextpath").value;
    var process_seq_no = document.getElementById("process_seq_no~" + count).value;
    var fvu_txt_file_name = document.getElementById("fvu_txt_file_name~" + count).value;
   
    if (!lhsIsNull(blobDownloadFlag)) {
        if (blobDownloadFlag === 'T') {
            window.location.href = cp + '/downloadDataDriectFromBlob?process_seqno=' + process_seq_no;
        } else if (blobDownloadFlag === 'F') {
            var fullfilePath = document.getElementById("file_download_from_directory~" + count).value;
            if(!lhsIsNull(fvu_txt_file_name)){
            if (!lhsIsNull(fullfilePath)) {
                 window.location = cp + "/downloadUsingPath?fileNamePath=" + encodeURIComponent(fullfilePath)+"&newFileName=" + encodeURIComponent(fvu_txt_file_name);
            }else{
               openErrorModal("File path is blank");   
            }
           }else{
             openErrorModal("File name is blank");  
           }
        }
    }

}