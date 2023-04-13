/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var globalSaveMessage = "Record(s) saved successfully !";
var globalUpdateMessage = "Record(s) updated successfully !";
var globalDeleteMessage = "Record(s) deleted successfully !";
var globalErrorMessage = "Some error occured !";

function lhsIsNull(value) {
    try {
        value = value.trim();
    } catch (err) {
        value = value;
    }
    if (value !== "" && value !== "null" && value !== null && value !== undefined && value !== 'undefined')
        return false;
    else
        return true;
}//end lhs_isNull function

function collapseOn(th) {
    $(th).toggleClass('expand').nextUntil('tr.header').slideToggle(100);
}

function hideProcessIndicator() {
    try {
        document.getElementById("global-simple-process-indicator").style.display = "none";
    } catch (err) {

    }
}//end method


function showProcessIndicator() {

    try {
        document.getElementById("global-simple-process-indicator").style.display = "flex";
    } catch (err) {
        console.log("err---" + err);

    }
}//end method

function showTextMsg(msg) {

    try {
        document.getElementById("global-text-process-indicator").innerHTML = msg;
        document.getElementById("global-text-process-indicator").style.display = "flex";
    } catch (err) {
        console.log("err---" + err);

    }
}
function hideShowTextMsg() {
    try {
        document.getElementById("global-text-process-indicator").style.display = "none";
    } catch (err) {
        console.log("err---" + err);

    }
}

function showBlackProcessIndicator() {

    try {
        document.getElementById("global-black-process-indicator").style.display = "flex";
    } catch (err) {
        console.log("err---" + err);

    }
}//end method

function hideBlackProcessIndicator() {
    try {
        document.getElementById("global-black-process-indicator").style.display = "none";
    } catch (err) {

    }
}//end method




function lhsModalOpen(id) {
    $('#' + id).modal('show', true);
}//end lhs_modalOpen function


function lhsModalClose(id) {
    $('#' + id).modal('hide');
}//end lhs_modalClose function


function lhsResetform(id, callBackFunctionAfterReset) {
    var $list = $("#" + id).find(":input[type='text'],:input[type='password'],select");

    $list.each(function () {
        var $currentItem = $(this);
//        console.log("$currentItem..."+$currentItem.id);
        $currentItem.val("");
    });

    if (!lhsIsNull(callBackFunctionAfterReset)) {
        if (callBackFunctionAfterReset === 'submit' || callBackFunctionAfterReset === 'SUBMIT') {
            // $("#" + id).submit();
            window.location.reload();
        } else {

            callBackFunctionAfterReset;
        }
    } else {
        $("#" + id).submit();
    }
}//end lhs_resetform function

function disableButton(btnField) {
    var className = btnField.className;
    className += " customDisabled";
    btnField.className = className;
}// end function

function enableButton(btnField) {
    var className = btnField.className;
    if (className.indexOf("customDisabled") > -1) {
        className = className.replace("customDisabled", "");
    }
    btnField.className = className;
}// end function

function openErrorModal(errorMessage, callBackFunction) {
    document.getElementById("errorAlertMsg").innerHTML = errorMessage;
    $("#dangerAlert").modal({show: true});
    if (!lhsIsNull(callBackFunction)) {
        $('#errorBtn').attr('onClick', callBackFunction);
    } else {
        $('#errorBtn').attr('onClick', "");
    }
}//end method

function openSuccessModal(errorMessage, callBackFunction) {
    document.getElementById("successAlertMsg").innerHTML = errorMessage;
    $("#successAlert").modal({show: true});
    if (!lhsIsNull(callBackFunction)) {
        $('#successBtn').attr('onClick', callBackFunction);
    } else {
        $('#successBtn').attr('onClick', "");
    }
}//end method


function openSuccessModalforReports(errorMessage,tokenno,javadrivname,oracleDriveName,blobdownloadflag) {
    //alert(tokenno);
    var cp = document.getElementById("globalcontextpath").value;
    document.getElementById("successAlertMsgReports").innerHTML = errorMessage;
    var token_no=tokenno;
    var url;
    if (!lhsIsNull(token_no)) {
    $("#successAlertReports").modal({show: true});
    if(!lhsIsNull(blobdownloadflag)){
        if(blobdownloadflag === 'T'){
        url = cp+'/downloadDataDriectFromBlob?process_seqno='+token_no;
         }else if(blobdownloadflag === 'F'){
         // window.location = cp + "/downloadUsingPath?fileNamePath=" + encodeURIComponent('E:\\TEXT_FILE\\12755\\21-22\\Q4\\26Q\\20170393.txt')+"&newFileName=" + encodeURIComponent('20170393.txt');
          url="#";
            }
      }
    // var url = cp+'/downloadDataDriectFromBlob?process_seqno='+token_no;
    //var url = cp+'/downloadDataDriectFromClob?process_seqno='+token_no;
    //var url = cp+'/downloadDataFromBlobToFile?process_seqno='+token_no+'&javadrive_name='+encodeURIComponent(javadrivname);
    //var url = cp+'/downloadDataFromClobToFile?process_seqno='+token_no+'&javadrive_name='+encodeURIComponent(javadrivname);
    
   $('#success_Btn_Rep').attr("onclick", "location.href = '"+ url +"'");
    }else{
     openErrorModal("Token no is null!");    
    }
}//end method

function openSuccessModalWithCopyText(successMessage, copyText, callBackFunction) {
    document.getElementById("successWithCopyTextAlertMsg").innerHTML = successMessage;
    $('#copyClipboard').val(copyText);
    $("#successWithCopyTextAlert").modal({show: true});
    if (!lhsIsNull(callBackFunction)) {
        $('#successCopyBtn').attr('onClick', callBackFunction);
    } else {
        $('#successCopyBtn').attr('onClick', "");
    }
}//end method

/* for copy text form success modal*/
let copyToClipboard = element => {
    let copyText = $(element).val();
    navigator.clipboard.writeText(copyText);
}

function addActionError(type, msg) {
    if (!lhsIsNull(type) && !lhsIsNull(msg)) {
        if (type === "success") {
            document.getElementById("successMsgDiv").style.display = "block";
            $("#successMsg").html(msg);
            try {
                document.getElementById("errorMsgDiv").style.display = "none";
                document.getElementById("notificationMsgDiv").style.display = "none";
            } catch (err) {
                console.log("errr...");
            }
        } else if (type === "error") {
            document.getElementById("errorMsgDiv").style.display = "block";
            $("#errorMsg").html(msg);
            try {
                document.getElementById("successMsgDiv").style.display = "none";
                document.getElementById("notificationMsgDiv").style.display = "none";
            } catch (err) {
                console.log("errr...");
            }
        } else if (type === "message") {
            document.getElementById("notificationMsgDiv").style.display = "block";
            $("#notificationMsg").html(msg);
            try {
                document.getElementById("successMsgDiv").style.display = "none";
                document.getElementById("errorMsgDiv").style.display = "none";
            } catch (err) {
                console.log("errr...");
            }
        }
    }
}

function removeError() {
    try {
        document.getElementById("successMsgDiv").style.display = "none";

    } catch (Err) {

    }
    try {
        document.getElementById("notificationMsgDiv").style.display = "none";

    } catch (Err) {

    }
    try {
        document.getElementById("errorMsgDiv").style.display = "none";

    } catch (Err) {

    }
    try {
        document.getElementById("session_notification").style.display = "none";

    } catch (Err) {

    }
}

function showRecords() { 
    try {
        document.getElementById("filterFlag").value = "true";
        if (parseInt($('#totalRecordsSpan').html()) > 0) {
            $("#g_download").removeAttr("disabled");
            try {
                $("#bulk_download_btn").removeAttr("disabled");
            } catch (e) {
            }
        }
    } catch (err) {
    }
    getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);
}

function togglePasswordFields(fieldId, iconId) {
    var pwdField = document.getElementById(fieldId);
    var icon = document.getElementById(iconId);

    try {
        if (pwdField.type === "password") {
            pwdField.type = "text";
            icon.className = "fa fa-eye-slash";
            icon.title = "Hide Password";
        } else {
            pwdField.type = "password";
            icon.className = "fa fa-eye";
            icon.title = "Show Password";
        }
    } catch (err) {
        console.log(err);
    }
}
function toggle_password_fields(fieldId, iconId) {
    var pwdField = document.getElementById(fieldId);
    var icon = document.getElementById(iconId);

    try {
        if (pwdField.type === "password") {
            pwdField.type = "text";
            icon.className = "fa fa-eye-slash";
            icon.title = "Hide Password";
        } else {
            pwdField.type = "password";
            icon.className = "fa fa-eye";
            icon.title = "Show Password";
        }
    } catch (err) {
        console.log(err);
    }
}


function openCalendar(id, btnid, left, top) {
    var myCalendar;
    myCalendar = new dhtmlXCalendarObject({input: id, button: btnid});
    myCalendar.setDateFormat("%d-%m-%Y");
    myCalendar.hideTime();

    if (window.dhx4.isIE11) {
        let settings = {
            date_field_id: id,
            calendar: myCalendar,
            left: left,
            right: top
        };
        openGlobalCalendarForIE(settings);
    }
}//end function


function callUrl(url) {
    showProcessIndicator();
    var cp = document.getElementById("globalcontextpath").value;
    window.location = cp + url;
    hideProcessIndicator();
}

function DownloadTemplate(id, url) {
    showProcessIndicator();
    try {
        var action = document.getElementById(id).getAttribute("action");
        document.getElementById(id).setAttribute("action", url);
        document.getElementById(id).submit();
        document.getElementById(id).setAttribute("action", action);
        hideProcessIndicator();
    } catch (err) {

    }
}//end function

function downloadUsingPath(fileNamePath, newFileName) {
    var cp = document.getElementById("globalcontextpath").value;
    if (!lhsIsNull(fileNamePath)) {
        window.location = cp + "/downloadUsingPath?fileNamePath=" + encodeURIComponent(fileNamePath) + "&newFileName=" + encodeURIComponent(newFileName);
    }
}//End Function

function submitForm(formId, btnObj) {
    btnObj.disabled = true;
    document.getElementById(formId).submit();

}

function downloadStaticExcel(fileheading) {
    var fileName = $('#g_excelFileName').val();
    var fileExt = $('#g_fileExtension').val();

    fileName = lhsIsNull(fileName) ? "TAXCPC_TEMPLATE" : fileName;
//    fileExt = lhsIsNull(fileExt) ? ".xlsx" : fileExt;
    fileName = fileName + ".xls";

    //generating static table for plugin use
    var table = getSimpleTable($('#table'));
    $('div.page-section').append("<div class=\"d-none\" id=\"staticTableContent\"></div>");
    $('#staticTableContent').html(table);

    //call for table2excel plugin to export HTML data into EXCEL
    $("#staticTable").table2excel({//specifying excel file properties
        filename: fileName,
        fileHeading: fileheading,
        preserveColors: false
    });
    $('#staticTableContent').remove();
}//end function

function getSimpleTable(tableElement) {
    var temptable = getTemporaryTable(tableElement);
//    console.log('statictable: ' + $(temptable).html());
    try {
        var table = "<table style=\"font-family:arial;\" id=\"staticTable\"> <thead> <tr>";
        var tableHeader = $(temptable).find("thead tr:first").html();
        tableHeader = tableHeader.replace(/<div[^>]*>|<\/div>/gi, "");
        tableHeader = tableHeader.replace(/<i[^>]*>|<\/i>/gi, "");
        tableHeader = tableHeader.replace(/<hr>/gi, "</th><th>");
//        tableHeader = tableHeader.replace(/class="[^"]*"/gi, "");
        tableHeader = tableHeader.replace(/id="[^"]*"/gi, "");
        tableHeader = tableHeader.replace(/title="[^"]*"/gi, "");
        table += tableHeader + "</tr> </thead> \n\n ";
    } catch (e) {
    }
    try {
        table += "<tbody>";
        var tableBody = $(temptable).find("tbody").html();
        tableBody = tableBody.replace(/<form[^>]*>|<\/form>/gi, "");
//        tableBody = tableBody.replace(/<input[^>]*>|<\/input>/gi, "");
        tableBody = tableBody.replace(/<div[^>]*>|<\/div>/gi, "");
        tableBody = tableBody.replace(/<i[^>]*>|<\/i>/gi, "");
//        tableBody = tableBody.replace(/<s:a[^>]*>|<\/s:a>/gi, "");
        tableBody = tableBody.replace(/<a[^>]*>|<\/a>/gi, "");
        tableBody = tableBody.replace(/<hr>/gi, "</td><td>");
//        tableBody = tableBody.replace(/class="[^"]*"/gi, "");
        tableBody = tableBody.replace(/id="[^"]*"/gi, "");
        tableBody = tableBody.replace(/title="[^"]*"/gi, "");
        table += tableBody + "</tbody>";
    } catch (e) {
    }
    try {
//        table += "<tfoot>";
//        var tableFoot = $(tableElement).find("tfoot").html();
//        tableFoot = tableFoot.replace(/<i[^>]*>|<\/i>/gi, "");
//        tableFoot = tableFoot.replace(/<hr>/gi, "</td><td>");
//        table += tableFoot + "</tfoot>";
    } catch (e) {
    }
//    console.log('statictable: ' + table);
    return table;
}//end function

function getTemporaryTable(tableElement) {
    var temptable = $(tableElement).clone();
    $.each($(temptable).find("tbody tr"), function () {
        var _row = $(this);
        $.each($(_row).find("td"), function (i) {
            try {
                if ($(this).hasClass("d-none")) {
                    $(this).remove();
                } else if ($(this).find('input[type=text]').length) {
                    var inputvalue = $(this).find('input[type=text]').val();
                    $(this).html(inputvalue);
                }
            } catch (e) {
                console.log(i);
                console.log(e);
            }
        });

        //getting each dropdown field value
        $.each($(_row).find("td select"), function (i) {
            try {
                var text = $(this).find(":selected").html();
                if (!lhsIsNull(text) && !text.toLowerCase().includes("-select-")) {
                    $(this).parent().html(text);
                } else if (!lhsIsNull(text) && text.toLowerCase().includes("-select-")) {
                    $(this).parent().html('');
                } else if (lhsIsNull(text)) {
                    $(this).parent().html('');
                }
            } catch (e) {
                console.log('select ' + i);
                console.log(e);
            }
        });
    });
    return temptable;
}//end function

function setDefaultBtnProperties(button) {

//    try {
//        $('body button.form-btn-clicked').removeClass('form-btn-clicked');
//    } catch (e) {
//    }
    try {
//        $(button).addClass('form-btn-clicked');
        $(button).attr("disabled", "true");
    } catch (e) {
    }

}//end function

function openGlobalCalendarForIE(settings) {alert("call2");
    let calDivs = document.getElementsByClassName("dhtmlxcalendar_dhx_terrace");
    let calDiv = calDivs[calDivs.length - 1];


    $(calDiv).css('left', settings.left);
    $(calDiv).css('top', settings.right);
    $(calDiv).css('display', 'block');

    settings.calendar.attachEvent("onClick", function (e) {

        document.getElementById(settings.date_field_id).value = getDisplayDate(settings.calendar.getDate());

        $('.dhtmlxcalendar_dhx_terrace').remove();

        return;
    });

    function getDisplayDate(calDate) {
        let selected_date = calDate.getDate() < 10 ? 0 + '' + calDate.getDate() : calDate.getDate();
        let selected_month = (calDate.getMonth() + 1) < 10 ? 0 + '' + (calDate.getMonth() + 1) : (calDate.getMonth() + 1);

        return selected_date + '-' + selected_month + '-' + calDate.getFullYear();
    }
}
function showPopupMessage() {
    var global_msg_type = document.getElementById('global_msg_type').value;
    var global_msg_value = document.getElementById('global_msg_value').value;
    try {
        if (!lhsIsNull(global_msg_type) && !lhsIsNull(global_msg_value)) {
            if (global_msg_type === 'error') {
                openErrorModal(global_msg_value);
            } else if (global_msg_type === 'success') {
                openSuccessModal(global_msg_value);
            }
        }
    } catch (err) {
    }

}

function openTokenStatus(action) {
    var cp = document.getElementById("globalcontextpath").value;
    if (lhsIsNull(action)) {
        callUrl("/getTokenStatus?msgFlag=tokenStatus");
    } else {
        callUrl("/getTokenStatus?msgFlag=" + encodeURIComponent(action));
    }
}



function validateTokenFilter() {
    var procesType = $("#procesType").val();
    var templateType = $("#templateType").val();
    var loginType = $("#loginType").val();
    var mainProcessType = $("#mainProcessType").val();
    var tokenNo = $("#tokenNo").val();
    if (lhsIsNull(procesType) && lhsIsNull(templateType) && lhsIsNull(tokenNo) && lhsIsNull(loginType) && lhsIsNull(mainProcessType)) {
        openErrorModal("Please select any filter to see the records !");
    } else {
        showRecordsPaginator();
    }
}
//
//$(document).ready(function() {
//    $('#generate').click(function() {
//        var url = '/taxcpcV2/DownloadFileServlet?process_seqno=1624';
//        var text = 'Techie Delight';
//        $("#container").append(`<a href="${url}" target="_blank">{text}</a>`);
//    })
//});