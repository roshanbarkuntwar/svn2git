/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//function uploadReferenceXls() {
//    var fileTemplate = document.getElementById('template').value;
//    if (fileTemplate !== "" && fileTemplate !== null) {
//
//        var cp = parent.document.getElementById("globalcontextpath").value;
////        var url = cp + "/uploadXlsFileReferenceNo?validate=true";
//        var url = cp + "/referenceNoUpload?validate=true";
//        var formData = new FormData($("form#excelReferenceNoFiles")[0]);
//
//        $.ajax({
//            url: url,
//            type: 'POST',
//            data: formData,
//            async: false,
//            cache: false,
//            contentType: false,
//            processData: false,
//            beforeSend: function (xhr) {
//                window.parent.showProcessIndicator();
//            },
//            success: function (data) {
//                console.log(data);
//                if (data === 'uploadSuccess') {
//                    //alert("File Uploaded Successfully");
//                    window.parent.document.getElementById("dispTempFlag").value = "T";
//                    try {
////                        window.parent.document.getElementById("deductee_bflag_notification").style.visibility = "visible";
////                        window.parent.document.getElementById("deductee_bflag_notification").style.display = "block";
////                        window.parent.document.getElementById("deductee_bflag_notification").innerHTML = "File Uploaded Successfully";
//                        openSuccessModal("File Uploaded Successfully");
//                    } catch (err) {
//                    }
//                    try {
//                        window.parent.callNoGenerationTypeTwoAtModal();
//                    } catch (err) {
//                    }
//                } else if (data === 'uploadMaxResult') {
//                    window.parent.location = cp + "/logout?mx_upd_res=TBC";
//
//                } else if (data.search('error') !== -1) {
//                    try {
//                        window.parent.openErrorModal("Some error occurred!!");
//                    } catch (err) {
//                    }
//                } else {
//                    var details = data.split("#");
//                    if (details[0] === 'success') {
//                        window.parent.document.getElementById("genType2TableID").innerHTML = details[1];
//                    } else {
//                        openErrorModal("Some Error Occurred!!");
//                    }
//                }
//                window.parent.hideProcessIndicator();
//                window.parent.$('#uploadReferenceModal').modal('toggle');
//            },
//            error: function (jqXHR, status) {
//                window.parent.hideProcessIndicator();
//            },
//            complete: function (jqXHR, textStatus) {
//                window.parent.hideProcessIndicator();
//            }
//        });
//    } else {
//        window.parent.openErrorModal("Please Select File To Upload");
//    }
//}//end function

//function checkFile(e) {
//    var file_list = e.target.files;
//    for (var i = 0, file; file = file_list[i]; i++) {
//        var sFileName = file.name;
//        var sFileExtension = sFileName.split('.')[sFileName.split('.').length - 1].toLowerCase();
//        var iFileSize = file.size;
//        var iConvert = (file.size / 1048576).toFixed(2);
//        if (!(sFileExtension === "xls" || sFileExtension === "xlsx" || sFileExtension === "XLS" || iFileSize > 10485760)) { /// 10 mb
//            txt = "File type : " + sFileExtension + "\n\n";
//            txt += "Size: " + iConvert + " MB \n\n";
//            txt += "Please make sure your file is in pdf or doc format and less than 10 MB.\n\n";
//            alert(txt);
//            document.getElementById('template').value = "";
//        }
//    }
//}//end function

//function changeType() {
//    $('button:button').attr('disabled', false);
//}//end function

