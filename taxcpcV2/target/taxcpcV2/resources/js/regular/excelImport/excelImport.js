/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function setFilenameForDownload() {
    var templateType = document.getElementById('templateType').value;
    var excelFormat = document.getElementById('excelFormat').value;
    // This code is commented for pan verification upload 
    /*if (templateType === 'PAN_VERIFY#F') {
     $('#upload_button').removeAttr('onclick');
     $('#upload_button').attr('onClick', 'validatePanVerification(event);');
     } else {
     $('#upload_button').removeAttr('onclick');
     $('#upload_button').attr('onClick', 'validateImportFileProcess(event);');
     
     }*/
    ajaxPostUrl("/getTemplateForDownloadAction?templateType=" + encodeURI(templateType) + "&excelFormat=" + encodeURI(excelFormat), setFilenameForDownloadResponse);
}//End Function

function setFilenameForDownloadResponse(response) {
    if (!lhsIsNull(response)) {
        //$('#processType option').remove();
        document.getElementById("fileNamePath").value = response.split("#")[0];
        document.getElementById("newFileName").value = response.split("#")[1];
        try {
            //setting upload file type (file extension)
            if (!lhsIsNull(response.split("#")[2]) && (response.split("#")[2]).match("FUVTXT"))
                document.getElementById("validatedCustomFile").setAttribute("accept", ".txt");
            else if (lhsIsNull(response.split("#")[2]) || (response.split("#")[2]).match("ZIP"))
                document.getElementById("validatedCustomFile").setAttribute("accept", "application/zip");
            else if (lhsIsNull(response.split("#")[2]) || (response.split("#")[2]).match("EXCEL"))
                document.getElementById("validatedCustomFile").setAttribute("accept", document.getElementById("excelFormat").value);

//            if (!lhsIsNull(response.split("#")[3]) || !lhsIsNull(response.split("#")[4])) {
//                if (!lhsIsNull(response.split("#")[3]) && (response.split("#")[3]).match("A"))
//                    $('#processType').append('<option value="' + response.split("#")[3] + '">Append</option>');
//                if (!lhsIsNull(response.split("#")[3]) && (response.split("#")[3]).match("U"))
//                    $('#processType').append('<option value="' + response.split("#")[3] + '">Update</option>');
//                if (!lhsIsNull(response.split("#")[4]) && (response.split("#")[4]).match("D"))
//                    $('#processType').append('<option value="' + response.split("#")[4] + '">Replace</option>');
//            }
        } catch (ex) {
            document.getElementById("validatedCustomFile").setAttribute("accept", document.getElementById("excelFormat").value);
            console.log('Error: ' + ex);
        }
    }
}//End Function

function downloadTemplateMasterExcel() {
    var fileNamePath = document.getElementById('fileNamePath').value;
    var newFileName = document.getElementById("newFileName").value;
//    var g_fileExtension = document.getElementById("g_fileExtension").value;

//    try {
//        newFileName = $('#templateType :selected').text();
//        if (newFileName.includes('(')) {
//            newFileName = newFileName.substring(0, newFileName.indexOf('(')).trim();
//            newFileName = newFileName.replace('-', '');
//        }
//        newFileName = newFileName + g_fileExtension;
//        console.log(newFileName);
//    } catch (e) {
//    }

    downloadUsingPath(fileNamePath, newFileName);
}//End Function

function validateImportFileProcess(evt) {
    evt.preventDefault();
    //showProcessIndicator();                                        
    var session_module = document.getElementById('session_module').value;
    document.getElementById('moduleType').value=session_module;
    //showBlackProcessIndicator();
    let submitFlag = false;
    try {
        let selectedFile = document.getElementById('validatedCustomFile').files[0];
        if (lhsIsNull(selectedFile) || lhsIsNull(selectedFile.name)) {
            openErrorModal('Please select File to upload.');
        } else {
            submitFlag = true;
        }
    } catch (e) {
    }
    if (submitFlag){
        showBlackProcessIndicator();
        submitForm('importExcelMasterFiles', this);
    }
}//End Function

function validatePanVerification(evt) {
    evt.preventDefault();
   
    let submitFlag = false;
    try {
        let selectedFile = document.getElementById('validatedCustomFile').files[0];
        if (lhsIsNull(selectedFile) || lhsIsNull(selectedFile.name)) {
            openErrorModal('Please select Zip File to upload.');
        } else {
            try {
                let formPrevAction = $('form[name=importExcelMasterFiles]').attr("action");
                $('form[name=importExcelMasterFiles]').attr("action", "uploadPanVerification?validate=true");
                ajaxSubmitPostData('/uploadPanVerification?validate=true', 'importExcelMasterFiles',
                        fileResponse);

                function fileResponse(response) {
            //      alert(response);
                  
                    if (!lhsIsNull(response)) {
                        if (response.includes('success')) {
                            document.getElementById("dialog-skip-upload-success-p").innerHTML = "File Uploaded Successfully ! Generated Token No. is : " + response.split("#")[1];
                            document.getElementById("dialog-skip-upload-success").style.display = "block";
                            $(function () {
                                $("#dialog-skip-upload-success").dialog({
                                    modal: true,
                                    buttons: {
                                        Ok: function () {
                                            $(this).dialog("close");
                                            window.location = "tdsImportStatus";
                                        }
                                    }
                                });
                            });
                        } else {
                            openErrorModal('some error Occured.');
                        }
                    }
                    $('form[name=importExcelMasterFiles]').attr("action", formPrevAction);
                }
            } catch (e) {
                console.log(e);
            }
        }
    } catch (e) {
    }
}//End Function
