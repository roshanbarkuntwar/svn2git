/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function changeFileUploadType() {
    var select = document.getElementById("selectCorrectionTypeId").value;

    var fileTagDivId = document.getElementById("fileTagDivId")
    if (!lhsIsNull(select) && select === 'P') {

        fileTagDivId.style.display = "flex";
        fileTagDivId.setAttribute("accept", ".zip");
    } else {
        blankFile();
        fileTagDivId.style.display = "none";
    }
}// End Function

function blankFile() {
//    document.getElementById("zipfile").value = "";
    document.getElementById("panNOCorrectionFile").value = "";
    document.getElementById("correctionFile").value = "";
    document.getElementById("justificationFile").value = "";
    $('.custom-file-input').find('input').each(function () {
        $(this).val("");
    });
}// End Function

function cleanDataAndDirectory(reloadPage) {
    var cp = document.getElementById("globalcontextpath").value;

    var url = cp + "/uploadZipFileAjax?action=cleanDataAndDirectory";
    document.getElementById('next_prgogress_bar').style.display = 'block';
    $.ajax({
        url: url,
        type: "GET",
        success: function (data) {
            document.getElementById('next_prgogress_bar').style.display = 'none';
//            alert(data);
            if (reloadPage == 'T') {
                var reloadUrl = cp + "/corrImportAction";
                window.location.href = reloadUrl;
            } else if (reloadPage == 'F') {
                getTimeToUploadFile();
            }
        }

    });
}// End Function


function uploadFile() {
    var displayCleanData = document.getElementById("displayCleanData").value;

    if (lhsIsNull(displayCleanData)) {
        document.getElementById("dialog-cleanExistingFile").style.display = "block";
        $(function () {
            $("#dialog-cleanExistingFile").dialog({
                modal: true,
                buttons: {
                    "Clean": function () {
//                    alert('Clean');
                        $(this).dialog("close");
                        cleanDataAndDirectory('F');
                    },
                    "Process": function () {
//                    alert('Process');
                        getTimeToUploadFile();
                        hidden_error();
                        $(this).dialog("close");

                    }
                }
            });
        });

    } else {
        getTimeToUploadFile();
        hidden_error();
    }
}// End Function
function getTimeToUploadFile() {

    //***************** Start Process
    var correctionFile = document.getElementById("correctionFile").value;
    var selectCorrectionTypeId = document.getElementById("selectCorrectionTypeId").value;
    var panNOCorrectionFile = document.getElementById("panNOCorrectionFile").value;

    if (lhsIsNull(panNOCorrectionFile) && selectCorrectionTypeId == 'P') {
        alert('PANNO. Correction File');

    } else if (lhsIsNull(correctionFile)) {
        alert('Select Correction File');
    } else {
        document.getElementById('prgogress_bar').style.visibility = 'visible';

        var fileSize = $("#correctionFile")[0].files[0].size;
        fileSize = fileSize / (1024 * 1024);

        $.ajax({
            url: "getTimeToUpload",
            type: "post",
            data: {
                filesize: fileSize,
                processType: 'U'
            },
            success: function (data) {
                var msg;
                if (data === 'tolarge') {
                    msg = "file is too large it will take time to upload";
                } else {
                    msg = data + " minute(s) will take to upload";
                }
//            document.getElementById('approximatevalue').innerHTML = msg.trim();
                document.getElementById("uploadZipFileForm").submit();

            }
        });
    }
    //***************** End Process

}//End Method

function nextProcessForSave() {
    var cp = document.getElementById("contextPath").value;

    var selectCorrectionType = document.getElementById("selectCorrectionTypeId").value;
    var url = cp + "/uploadZipFileAjax?action=extractAndSave";

    url += "&selectCorrectionType=" + selectCorrectionType;
    document.getElementById('next_prgogress_bar').style.display = 'block';

    $.ajax({
        url: url,
        type: "GET",
        success: function (data) {
            document.getElementById('next_prgogress_bar').style.display = 'none';

            var reloadUrl = cp + "/corrImportAction";
            window.location.href = reloadUrl;

        }

    });
}// End Function


function checkAll() {
    var checkBoxCount = 0;
    var checkCount = 0;
    $("#fileListTable").find('input[type="checkbox"]').each(function () {
        if (this.id !== "checkAll") {
            checkBoxCount++;
            if (this.checked) {
                checkCount++;
            }
        }
    });
    if (checkBoxCount === checkCount) {
        $("#checkAll").attr('checked', 'checked');
        $("#checkAll").checked = true;
    } else {
        $("#checkAll").removeAttr('checked');
        $("#checkAll").checked = false;
    }


    if (checkCount > 0) {
        document.getElementById('processBtnId').disabled = false;
    } else {
        document.getElementById('processBtnId').disabled = true;

    }

}//End Method

function checkUnchekAll() {

    var status = $("#checkAll").is(":checked");
    $("#fileListTable").find('input[type="checkbox"]').each(function () {
        if (this.id !== "checkAll") {
            if (status) {
                this.setAttribute('checked', 'checked');
                this.checked = true;
            } else {
                this.removeAttribute('checked');
                this.checked = false;
            }
        }

    });
    setFileString();
}

function setFileString() {
    checkAll();
}//End Function


function updatedFiles() {
   

    var checkBoxCount = 0;
    var checkCount = 0;
    $("#fileListTable").find('input[type="checkbox"]').each(function () {
        if (this.id !== "checkAll")
        {
            checkBoxCount++;
            if (this.checked) {
                checkCount++;
            }
        }
    });
//    alert(checkCount);
    if (checkCount > 0) {

        var selectCorrectionTypeId = document.getElementById('selectCorrectionTypeId').value;
        document.getElementById('process_typeID').value = selectCorrectionTypeId;
         document.getElementById("processBtnId").disabled = true;
        document.getElementById("processZipFileForm").submit();
//        $("#processZipFileForm").submit();
    } else {
        alert("Select atleast one file.");
    }
}