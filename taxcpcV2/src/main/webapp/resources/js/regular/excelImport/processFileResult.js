/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function showBlackProcessIndicator() {
//    alert("show Process..");
    try {
        document.getElementById("global-black-process-indicator").style.display = "flex";
    } catch (err) {
        //alert("err--11-" + err);

    }
}//end method

function hideBlackProcessIndicator() {
//    alert("hide process..");
    try {
        document.getElementById("global-black-process-indicator").style.display = "none";
    } catch (err) {
        //alert("err-22-" + err);
    }
}//end method


//***********************file import*****************************
var globalProgressBarFlag = false; //used to check process indicator


var xmlHttp = "";
var ajax_call_enabled = false;
var currentPageNo = "";
function make_ajax_call(url, resuting_call) {
    if (!ajax_call_enabled) {
        if (typeof XMLHttpRequest !== "undefined") {
            xmlHttp = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        if (xmlHttp === null) {
            document.getElementById("dialog-message_brwsrNtSprt").style.display = "block";
            $(function () {
                $("#dialog-message_brwsrNtSprt").dialog({
                    modal: true,
                    buttons: {
                        Ok: function () {
                            $(this).dialog("close");
                        }
                    }
                });
            });
            return;
        }
        ajax_call_enabled = true;
        xmlHttp.onreadystatechange = resuting_call;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    } else {
        document.getElementById("dialog-message_previousRequest").style.display = "block";
        $(function () {
            $("#dialog-message_previousRequest").dialog({
                modal: true,
                buttons: {
                    Ok: function () {
                        $(this).dialog("close");
                    }
                }
            });
        });
    }
}
;

function geProcessErrorRecord() {
    var cp = document.getElementById("globalcontextpath").value;
    var importSeqNo = document.getElementById("importSeqNo").value;
    var URL = cp;
//    document.getElementById("browse_process_indicator_css1").style.visibility = "visible";
    showBlackProcessIndicator();
    URL += "/getProcessErrorDataAction?importSeqNo=" + importSeqNo;
    //  alert("url..."+URL);
    make_ajax_call(URL, geProcessErrorRecord_response);
}//end function
function geProcessErrorRecord_response() {
    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
        var returnMsg = xmlHttp.responseText;
        try {
            ajax_call_enabled = false;
//            window.//alert(returnMsg);
//            document.getElementById("FileUploadMainDivID").style.display = "block";
            try {
                getNoOFRecordProcess();
            } catch (err) {
                console.log("err1--" + err);
            }
        } catch (err) {
            console.log("err12--" + err);
            ajax_call_enabled = false;
        }
    }
    ajax_call_enabled = false;
}//end function


function getNoOFRecordProcess() {
    var importSeqNo = document.getElementById("importSeqNo").value;
    var cp = document.getElementById("globalcontextpath").value;
    var URL = cp;
    URL += "/getDataValueAction?action=totalRecordImport&importSeqNo=" + importSeqNo;

    // alert("URL---"+URL);
    make_ajax_call(URL, getNoOFRecordProcess_response);
}//end function



function getNoOFRecordProcess_response() {
    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
        var returnMsg = xmlHttp.responseText;
        try {
            ajax_call_enabled = false;
//            document.getElementById("browse_process_indicator_css1").style.visibility = "hidden";
            hideBlackProcessIndicator();

            document.getElementById("NoOfRecordImportID").innerHTML = returnMsg.trim();
            document.getElementById("paginationBlock").style.display = "block";
            document.getElementById("paginator_top").style.display = "block";
            try {
                getNoOFErrorsProcess();
            } catch (err) {
                console.log("err5--" + err);
            }
        } catch (err) {
            console.log("err6--" + err);
            hideBlackProcessIndicator();
//            document.getElementById("browse_process_indicator_css1").style.visibility = "hidden";
            ajax_call_enabled = false;
        }
    }
    ajax_call_enabled = false;
}//end function


function getNoOFErrorsProcess() {
    var cp = document.getElementById("globalcontextpath").value;
    var importSeqNo = document.getElementById("importSeqNo").value;
    var URL = cp;
    showBlackProcessIndicator();
//    document.getElementById("browse_process_indicator_css1").style.visibility = "visible";
    URL += "/getDataValueAction?action=totalErrorImport&importSeqNo=" + importSeqNo;
    make_ajax_call(URL, getNoOFErrorsProcess_response);
}//end function

function getNoOFErrorsProcess_response() {
    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
        var returnMsg = xmlHttp.responseText;
        ////alert("returnMsg..." + returnMsg);
        try {
            ajax_call_enabled = false;
//            window.//alert(returnMsg);
            document.getElementById("NoOfErrorImportID").innerHTML = returnMsg.trim();
            var errorCount = parseFloat(returnMsg.trim());
            // alert("errorCount..." + errorCount);
            if (errorCount === 0) {
                document.getElementById("refBtn").disabled = true;
                //  DirectSaveTempData();

                getErrorFilter();
            } else {
                document.getElementById("refBtn").disabled = false;
                hideBlackProcessIndicator();
//                document.getElementById("browse_process_indicator_css1").style.visibility = "hidden";
                try {
                    getErrorFilter();
                } catch (err) {
                    console.log("err--" + err);
                }
            }
        } catch (err) {
            console.log("err65--" + err);
            hideBlackProcessIndicator();
//            document.getElementById("browse_process_indicator_css1").style.visibility = "hidden";
            ajax_call_enabled = false;
        }
    }
    ajax_call_enabled = false;
}//end function

function getErrorFilter() {
    var importSeqNo = document.getElementById("importSeqNo").value;
    var cp = document.getElementById("globalcontextpath").value;
    var errorFilter = "";
    try {
        errorFilter = document.getElementById("ErrorTypeListID").value;
    } catch (err) {

    }
    var URL = cp;
    showBlackProcessIndicator();
//    document.getElementById("browse_process_indicator_css1").style.visibility = "visible";
    URL += "/getDataValueAction?action=ErrorFilter&&importSeqNo=" + importSeqNo + "&errorCode=" + encodeURIComponent(errorFilter);
    make_ajax_call(URL, getErrorFilter_response);
}//end function

function getErrorFilter_response() {
    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
        var returnMsg = xmlHttp.responseText;
        try {
            ajax_call_enabled = false;
            hideBlackProcessIndicator();
//            window.//alert(returnMsg);
//            document.getElementById("browse_process_indicator_css1").style.visibility = "hidden";
            document.getElementById("ErrorTypeListID").innerHTML = returnMsg.trim();
//            try {
            defaultValues();
            FilterMasterExcelContentData();
//            } catch (err) {
//                console.log("errr..."+err);
//            }
        } catch (err) {
            console.log("err..." + err);
            hideBlackProcessIndicator();
//            document.getElementById("browse_process_indicator_css1").style.visibility = "hidden";
            ajax_call_enabled = false;
        }
    }
    ajax_call_enabled = false;
}//end function


function FilterMasterExcelContentData() {

    if (!ajax_call_enabled) {
        showBlackProcessIndicator();
//        document.getElementById("browse_process_indicator_css1").style.visibility = "visible";
        var postData = $("#ErrorTypeListID").val();
        var importSeqNo = $("#importSeqNo").val();
        var templateCode = $("#templateCode").val();
        var formURL = "filterFileUploadResult?search=true&ErrorType=" + postData + "&importSeqNo=" + importSeqNo + "&templateCode=" + templateCode;
        $.ajax(
                {
                    url: formURL,
                    type: "POST",
                    success: function (data, textStatus, jqXHR)
                    {
                        //data: return data from server
                        ajax_call_enabled = false;
                        hideBlackProcessIndicator();
//                        document.getElementById("browse_process_indicator_css1").style.visibility = "hidden";
                        if (data !== "" && data !== null && data !== "null") {
                            //alert("data.." + data);
                            var data_split = data.split("#");
                            document.getElementById("totalPagesSpan").innerHTML = data_split[0];
//                            document.getElementById("totalPagesBottomSpan").innerHTML = data_split[0];
                            document.getElementById("totalRecordsSpan").innerHTML = data_split[1];
//                            document.getElementById("totalRecordsSpanBottomF").innerHTML = data_split[1];
//                            document.getElementById("pageNumberBottomSpan").innerHTML = data_split[3];
                            document.getElementById("pageNumberSpan").innerHTML = data_split[3];
                            document.getElementById("recordsPerPage").value = data_split[2];
                            defaultValues();
                            document.getElementById("browseAction").value = "getImportDetailAction";
                            var browseaction = document.getElementById("browseAction").value;
                            if (browseaction !== undefined && browseaction !== "" && browseaction !== "null" && browseaction !== null) {
                                getExcelImportData(document.getElementById("pageNumberSpan").innerHTML);
                            }
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown)
                    {
                        hideBlackProcessIndicator();
//                        document.getElementById("browse_process_indicator_css1").style.visibility = "hidden";
                        ajax_call_enabled = false; //if fails
                    }
                });
        ajax_call_enabled = true;
    } else {
        document.getElementById("dialog-message_previousRequest").style.display = "block";
        $(function () {
            $("#dialog-message_previousRequest").dialog({
                modal: true,
                buttons: {
                    Ok: function () {
                        $(this).dialog("close");
                    }
                }
            });
        });
    }
}//end function


function getExcelImportData(pageNum) {

    var cp = document.getElementById("globalcontextpath").value;
    var ErrorTypeListID = document.getElementById("ErrorTypeListID").value;
    var NoOfColumns = document.getElementById("NoOfColumns").value;
//    document.getElementById("browse_process_indicator_css1").style.visibility = "visible";
    showBlackProcessIndicator();
    var pageNumber = pageNum;
    currentPageNo = pageNum;
    document.getElementById("updateBtnSpan").innerHTML = currentPageNo;
    var recordsPerPage = document.getElementById("recordsPerPageSelect").value;
    var update_data_row = document.getElementById("update_data_row").value;
    var importSeqNo = document.getElementById("importSeqNo").value;
    var templateCode = document.getElementById("templateCode").value;
    var totalRecords = document.getElementById("totalRecordsSpan").innerHTML.trim();
    var url = cp + "/getImportDetailAction?";
    url += "action=showImportDtl";
    url += "&NoOfColumns=" + encodeURIComponent(NoOfColumns);
    url += "&ErrorType=" + encodeURIComponent(ErrorTypeListID);
    url += "&pageNumber=" + encodeURIComponent(pageNumber);
    url += "&update_data_row=" + encodeURIComponent(update_data_row);
    url += "&recordsPerPage=" + encodeURIComponent(recordsPerPage);
    url += "&totalRecords=" + encodeURIComponent(totalRecords);
    url += "&importSeqNo=" + encodeURIComponent(importSeqNo);
    url += "&templateCode=" + encodeURIComponent(templateCode);
    make_ajax_call(url, setCurrentPage);
}//end function
function setCurrentPage() {
    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
        var q = xmlHttp.responseText;
        try {
            q = q.trim();
            document.getElementById("ImportResultDataID").innerHTML = q;
            hideBlackProcessIndicator();

//            document.getElementById("global-black-process-indicator").style.diplay = "none";
//            document.getElementById("browse_process_indicator_css1").style.visibility = "hidden";
            try {
                document.getElementById("pageNumberSpan").innerHTML = currentPageNo;
                document.getElementById("pageNumberBottomSpan").innerHTML = currentPageNo;
            } catch (e) {
                ajax_call_enabled = false;
            }
            ajax_call_enabled = false;
        } catch (err) {
            ajax_call_enabled = false;
        }
    }
}// end method


function getNextPage() {
    if (!ajax_call_enabled) {
//        document.getElementById("pagination_number_bottom").value = "";
        document.getElementById("pagination_number").value = "";
        var page_count = document.getElementById("pageNumberSpan").innerHTML;
        var total_count = document.getElementById("totalPagesSpan").innerHTML;
        page_count = parseInt(page_count);
        total_count = parseInt(total_count);
        if (page_count >= total_count) {
            document.getElementById("dialog-message_nextPg").style.display = "block";
            $(function () {
                $("#dialog-message_nextPg").dialog({
                    modal: true,
                    buttons: {
                        Ok: function () {
                            $(this).dialog("close");
                        }
                    }
                });
            });
        } else {
            var count = page_count + 1;
            getExcelImportData(count);
        }
    } else if (ajax_call_enabled) {
        document.getElementById("dialog-message_previousRequest").style.display = "block";
        $(function () {
            $("#dialog-message_previousRequest").dialog({
                modal: true,
                buttons: {
                    Ok: function () {
                        $(this).dialog("close");
                    }
                }
            });
        });
    }
}//end method


function getPreviousPage() {
    if (!ajax_call_enabled) {

        document.getElementById("pagination_number").value = "";
        var page_count = document.getElementById("pageNumberSpan").innerHTML;
        page_count = parseInt(page_count);
        if (page_count <= 1) {
            document.getElementById("dialog-message_PreviousPg").style.display = "block";
            $(function () {
                $("#dialog-message_PreviousPg").dialog({
                    modal: true,
                    buttons: {
                        Ok: function () {
                            $(this).dialog("close");
                        }
                    }
                });
            });
        } else {
            var count = page_count - 1;
            getExcelImportData(count);
        }
    } else if (ajax_call_enabled) {
        document.getElementById("dialog-message_previousRequest").style.display = "block";
        $(function () {
            $("#dialog-message_previousRequest").dialog({
                modal: true,
                buttons: {
                    Ok: function () {
                        $(this).dialog("close");
                    }
                }
            });
        });
    }
}//end method


function onClickCheckbox(id) {
    var chkid = "checkbox~" + id;
    var row = document.getElementById("row~" + id);
    var val = document.getElementById("recordsPerPageSelect").value;
    var oInput = document.getElementById('row~' + id),
            oChild;
    try {
        var checked = document.getElementById(chkid).checked;
        if (checked) {
            document.getElementById("editCheckBox~" + id).value = "true";
        } else {
            document.getElementById("editCheckBox~" + id).value = "false";
        }
    } catch (e) {
    }
    if (document.getElementById(chkid).checked) {
        for (i = 0; i < oInput.childNodes.length; i++) {
            oChild = oInput.childNodes[i];
            oChild.style.backgroundColor = '#B9D8F7';
        }
    } else {
        for (i = 0; i < oInput.childNodes.length; i++) {
            oChild = oInput.childNodes[i];
            oChild.style.backgroundColor = '';
        }
    }
    var check = false;
    for (var i = 1; i <= val; i++) {
        if (document.getElementById("checkbox~" + i).checked) {
            document.getElementById("updateBtn").disabled = false;
            document.getElementById("updateBtn").opacity = "1";
            check = true;
        }
    }
    if (!check) {
        document.getElementById("updateBtn").disabled = true;
        document.getElementById("updateBtn").opacity = "0.6";
    }
}//end function
function updateCheckedRecords() {
    var val = document.getElementById("recordsPerPageSelect").value;
    var count = false;
    for (var i = 1; i <= val; i++) {
        try {
            var chk = document.getElementById('checkbox~' + i).checked ? "Y" : "N";
            if (chk === "Y") {
                count = true;
                break;
            }
        } catch (err) {
        }
    }
    if (count) {
        updateErrorData();
    } else {
        openErrorModal("Select alteast one record ");
    }
}//end function

function updateErrorData() {
    showProcessIndicator();
    if (!ajax_call_enabled) {
        document.getElementById("browse_process_indicator_css1").style.visibility = "visible";
        var importSeqNo = document.getElementById("importSeqNo").value;
        var templateCode = document.getElementById("templateCode").value;
        $("#ExcelImportDtlID").submit(function (e)
        {
            var postData = $(this).serializeArray();
            var formURL = "getImportDataManupulationAction?action=SaveImportDtl&importSeqNo=" + importSeqNo + "&templateCode=" + templateCode;
            $.ajax(
                    {
                        url: formURL,
                        type: "POST",
                        data: postData,
                        success: function (data, textStatus, jqXHR)
                        {
                            //data: return data from server
                            ajax_call_enabled = false;
                            if (data === "save") {
                                hideProcessIndicator();
                                // scrollPage();
                                openSuccessModal("Record Updated Successfully", "");
                                document.getElementById("saveBtn").disabled = false;
                                getNoOFRecordProcess();
                                // geProcessErrorRecord();
                            } else {
                                document.getElementById("saveBtn").disabled = false;
                                // scrollPage();
                                openErrorModal("Some Error Occured, Could Not Update", "");
                                hideProcessIndicator();
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown)
                        {
                            ajax_call_enabled = false; //if fails
                            hideProcessIndicator();
                        }
                    });
            e.preventDefault(); //STOP default action
            //e.unbind(); //unbind. to stop multiple form submit.
        });
        ajax_call_enabled = true;
        document.getElementById("saveBtn").disabled = false;
        $("#ExcelImportDtlID").submit(); //Submit  the FORM
        hideProcessIndicator();
    } else {
        document.getElementById("saveBtn").disabled = false;
        hideProcessIndicator();
        openErrorModal("Some Error Occurred !", "");
    }
}//end function

var textBoxValue = "";
function getTextValue(id) {
    textBoxValue = document.getElementById(id).value;
}//end function

function getChecked(id) {
    var splitArr = id.split("~");
    var chkid = "checkbox~" + splitArr[0];
    var textValue = document.getElementById("col" + id).value;
    if (textBoxValue !== textValue) {
        document.getElementById(chkid).checked = true;
        try {
            var checked = document.getElementById(chkid).checked;
            if (checked) {
                document.getElementById("editCheckBox~" + splitArr[0]).value = "true";
            } else {
                document.getElementById("editCheckBox~" + splitArr[0]).value = "false";
            }
        } catch (e) {
        }
        var row = document.getElementById("row~" + splitArr[0]);
        var oInput = document.getElementById('row~' + splitArr[0]),
                oChild;
        for (i = 0; i < oInput.childNodes.length; i++) {
            oChild = oInput.childNodes[i];
            oChild.style.backgroundColor = '#B9D8F7';
        }
    }
    document.getElementById("updateBtn").disabled = false;
    document.getElementById("updateBtn").opacity = "1";
}//end function

function DeleteErrorData(id) {
    document.getElementById("dialog-confirm_delete").style.display = "block";
   
    $(function () {
        $("#dialog-confirm_delete").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                "Ok": function () {
                    $(this).dialog("close");
                    var split_data = id.split("~");
                    var count = split_data[1];
                    var col1 = document.getElementById("col" + count + "~11").value;
                    var col2 = document.getElementById("col" + count + "~12").value;
                    var col3 = document.getElementById("col" + count + "~13").value;
                    var col4 = document.getElementById("col" + count + "~14").value;
                    var col5 = document.getElementById("col" + count + "~15").value;
                    var col6 = document.getElementById("col" + count + "~16").value;
                    var col7 = document.getElementById("col" + count + "~17").value;
                    var col8 = document.getElementById("col" + count + "~18").value;
                    var col9 = document.getElementById("col" + count + "~19").value;
                    var col10 = document.getElementById("col" + count + "~20").value;
                    var rowid_seq = document.getElementById("col"+count+"~10").value;
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
                            var q = xmlhttp.responseText;
                            q = q.trim();
                            if (!lhsIsNull(q)) {
                                if (q === 'delete') {
                                    // scrollPage();
                                    openSuccessModal("Record Deleted Successfully", "");
                                    // geProcessErrorRecord();
                                    getNoOFRecordProcess();
                                } else {
//                                    scrollPage();
                                    openErrorModal("Some Error Occured, Could Not Delete", "");
                                    hideBlackProcessIndicator();
                                }
                            } else {
                                //scrollPage();
                                openErrorModal("Some Error Occured, Could Not Delete", "");
                                hideBlackProcessIndicator();
                            }
                        }
                    };
                    showProcessIndicator();
                    var url = cp + "/getImportDataManupulationAction?";
                    url += "action=DeleteImportDtl";
                    url += "&col1=" + encodeURIComponent(col1);
                    url += "&col2=" + encodeURIComponent(col2);
                    url += "&col3=" + encodeURIComponent(col3);
                    url += "&col4=" + encodeURIComponent(col4);
                    url += "&col5=" + encodeURIComponent(col5);
                    url += "&col6=" + encodeURIComponent(col6);
                    url += "&col7=" + encodeURIComponent(col7);
                    url += "&col8=" + encodeURIComponent(col8);
                    url += "&col9=" + encodeURIComponent(col9);
                    url += "&col10=" + encodeURIComponent(col10);
                    url += "&rowid_seq=" + encodeURIComponent(rowid_seq);
                    xmlhttp.open("GET", url, true);
                    xmlhttp.send(null);
                    hideProcessIndicator();
                },
                Cancel: function () {
                    $(this).dialog("close");
                }
            }
        });
    });
}//end function
var textBoxValue = "";
function getTextValue(id) {
    textBoxValue = document.getElementById(id).value;
}//end function

function getChecked(id) {
    var splitArr = id.split("~");
    var chkid = "checkbox~" + splitArr[0];
    var textValue = document.getElementById("col" + id).value;
    if (textBoxValue !== textValue) {
        document.getElementById(chkid).checked = true;
        try {
            var checked = document.getElementById(chkid).checked;
            if (checked) {
                document.getElementById("editCheckBox~" + splitArr[0]).value = "true";
            } else {
                document.getElementById("editCheckBox~" + splitArr[0]).value = "false";
            }
        } catch (e) {
        }
        var row = document.getElementById("row~" + splitArr[0]);
        var oInput = document.getElementById('row~' + splitArr[0]),
                oChild;
        for (i = 0; i < oInput.childNodes.length; i++) {
            oChild = oInput.childNodes[i];
            oChild.style.backgroundColor = '#B9D8F7';
        }
    }
    document.getElementById("updateBtn").disabled = false;
    document.getElementById("updateBtn").opacity = "1";
}//end function

let callingProcData = {};
function getSaveTempData(obj) {
    obj.disabled = true;
    var val = document.getElementById("recordsPerPageSelect").value;
    var count = false;
    for (var i = 1; i <= val; i++) {
        try {
            var chk = document.getElementById('checkbox~' + i).checked ? "Y" : "N";
            if (chk === "Y") {
                count = true;
                break;
            }
        } catch (err) {
        }
    }
    if (count) {
        document.getElementById("dialog-confirm_updtChkRcrds").style.display = "block";
        $(function () {
            $("#dialog-confirm_updtChkRcrds").dialog({
                resizable: false,
                font: 10,
                modal: true,
                buttons: {
                    "Ok": function () {
                        $(this).dialog("close");
                        updateCheckedRecords();
                    },
                    Cancel: function () {
                        obj.disabled = false;
                        $(this).dialog("close");
                    }
                }
            });
        });
    } else {
        var contextPath = document.getElementById("globalcontextpath").value;
        var noOfErrorImport = document.getElementById("NoOfErrorImportID").innerHTML;
        var importSeqNo = document.getElementById("importSeqNo").value;
        var templateCode = document.getElementById("templateCode").value;
        var processType = document.getElementById("processType").value;

        if (noOfErrorImport === null || noOfErrorImport === "" || noOfErrorImport === "null") {
            noOfErrorImport = '0';
        }

        callingProcData = {
            callingProcName: "ProcLhsTemplateInsert",
            templateCode: templateCode,
            sequenceId: importSeqNo,
            processType: processType
        };

        var noOfErrorImportparse = parseFloat(noOfErrorImport);
        if (noOfErrorImportparse > 0) {
            document.getElementById("dialog-confirm_excludeErr").style.display = "block";
            $(function () {
                $("#dialog-confirm_excludeErr").dialog({
                    resizable: false,
                    font: 10,
                    modal: true,
                    buttons: {
                        "Ok": function () {
                            $(this).dialog("close");
                            document.getElementById("browse_process_indicator_css1").style.visibility = "visible";
                            var no_of_column = document.getElementById("NoOfColumns").value;
                            var url = contextPath + "/getSaveDataAsProcedureAction?ExcFlag=true&templateCode= " +
                                    encodeURIComponent(templateCode) + " &importSeqNo=" + encodeURIComponent(importSeqNo) +
                                    "&no_of_column=" + encodeURIComponent(no_of_column)
                                    + "&processType=" + encodeURIComponent(processType);
                            make_ajax_call(url, getSaveTempDataImpl);
                        },
                        Cancel: function () {
                            obj.disabled = false;
                            $(this).dialog("close");
                        }
                    }
                });
            });
        } else {
            document.getElementById("browse_process_indicator_css1").style.visibility = "visible";
            var url = contextPath + "/getSaveDataAsProcedureAction?ExcFlag=false&templateCode=" + encodeURIComponent(templateCode) + "&importSeqNo=" + encodeURIComponent(importSeqNo) + "&processType=" + encodeURIComponent(processType);
            make_ajax_call(url, getSaveTempDataImpl);
        }
    }
}//end method
function getSaveTempDataImpl() {
    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
        var q = xmlHttp.responseText;
        try {
            q = q.trim();
            if (q !== "" && q !== "null" && q !== null) {
                var res = q.split('#');
                var cp = document.getElementById("globalcontextpath").value;
                if (res[0] === 'save' && !lhsIsNull(res[1])) {
                    try {
                        callingProcData.tokenNo = res[1].trim();
                        callDedicatedProcedure(callingProcData);
                    } catch (e) {
                        console.log(e);
                    }


                    openSuccessModal("Your process is initiated , Check your status and visit after some time !", "");

                    setTimeout(function () {
                        window.location = cp + "/tdsImportStatus";
                    }, 2000);

                } else {
                    document.getElementById("browse_process_indicator_css1").style.visibility = "hidden";
                    //scrollPage();
                    openErrorModal(q, "");
                }
            } else {
                document.getElementById("browse_process_indicator_css1").style.visibility = "hidden";
                // scrollPage();
                openErrorModal("Some Error Occured, Could Not Save", "");
            }
            ajax_call_enabled = false;
        } catch (err) {
            ajax_call_enabled = false;
        }
    }
}// end method

function DownloadUploadedDataList() {
    var NoOfRecordImportID = (document.getElementById("NoOfRecordImportID").innerHTML).trim();
    if (parseInt(NoOfRecordImportID) > 0) {
        var errorFilter = document.getElementById("ErrorTypeListID").value;
        var importSeqNo = document.getElementById("importSeqNo").value;
        var templateCode = document.getElementById("templateCode").value;
        var cp = document.getElementById("globalcontextpath").value;
        var url = "/DownloadImportedExcelDetailAction?";
        url += "&ErrorTypeCode=" + encodeURIComponent(errorFilter) + "&importSeqNo=" + importSeqNo + "&templateCode=" + templateCode;

        window.location = cp + url;
    } else {
        openErrorModal("No Record Available For Download", "");
    }
}//end function
function getCurrentPageOnChange() {

    var contextPath = document.getElementById("globalcontextpath").value;
    var val = document.getElementById("recordsPerPageSelect").value;
    document.getElementById("browse_process_indicator_css1").style.visibility = "visible";
    var xmlhttp;
    if (window.XMLHttpRequest) { //code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    } else {
//code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            var q = xmlhttp.responseText;
            q = q.trim();
            document.getElementById("browse_process_indicator_css1").style.visibility = "hidden";
            if (q === 'success') {
                window.location.reload();
            } else {
                openErrorModal("Some Error Occurred !", "");
            }
        }
    };
    var url = contextPath + "/getImportDetailAction?setRecPerPage=" + val;
    xmlhttp.open("GET", url, true);
    xmlhttp.send(null);
}//end method
function jumpToPage(id) {
    if (!ajax_call_enabled) {
        var page_count = 0;

        page_count = document.getElementById("pagination_number").value;
        document.getElementById("pagination_number").value = "";

        try {
            page_count = parseInt(page_count);
        } catch (error) {
            page_count = 0;
        }
        if (!isNaN(page_count)) {
            var total_count = document.getElementById("totalPagesSpan").innerHTML;
            total_count = parseInt(total_count);
            if (page_count > total_count || page_count === 0) {
                openErrorModal("Invalid Page No.", "");
            } else {
                var count = page_count;
                getExcelImportData(count);
            }
        } else {
            openErrorModal("Invalid Page No.", "");
        }
    } else if (ajax_call_enabled) {
        openErrorModal("Your Previous request is already in process !", "");
    }
}//end method

function DirectSaveTempData() {
    var cp = document.getElementById("globalcontextpath").value;
    var importSeqNo = document.getElementById("importSeqNo").value;
    var templateCode = document.getElementById("templateCode").value;
    var url = cp + "/getSaveDataAsProcedureAction?ExcFlag=false&importSeqNo=" + importSeqNo + "&templateCode=" + templateCode;
    make_ajax_call(url, getDirectSaveTempDataImpl);
}//end function

function getDirectSaveTempDataImpl() {
    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
        var q = xmlHttp.responseText;
        try {
            q = q.trim();
            if (q !== "" && q !== "null" && q !== null) {
                var cp = document.getElementById("globalcontextpath").value;
                if (q === 'save') {
                    window.location = cp + "/tdsImportStatus";
                } else {
                    openErrorModal(q);
                    try {
                        getErrorFilter();
                    } catch (err) {
                    }
                }
            } else {
                openErrorModal("Some Error Occured, Could Not Save");
                try {
                    getErrorFilter();
                } catch (err) {
                }
            }
            ajax_call_enabled = false;
        } catch (err) {
            ajax_call_enabled = false;
        }
    }
}//end function

function refreshError(val){
     val.disabled = true;
       var cp = document.getElementById("globalcontextpath").value;
     var tokenNo = $("#importSeqNo").val();
    var templateCode = $("#templateCode").val();
     callingProcData = {
            callingProcName: "procLhsTemplateError",
            templateCode: templateCode,
            tokenNo: tokenNo
        };
        try {
            callDedicatedProcedure(callingProcData);
       } catch (e) {
        console.log(e);
      }
      openSuccessModal("Your Template-Refresh Error Request Initiated. ", "");

                    setTimeout(function () {
                        window.location = cp + "/tdsImportStatus";
                    }, 2000);
}

//function refreshError(val) {
//    val.disabled = true;
//    showBlackProcessIndicator();
//    var tokenNo = $("#importSeqNo").val();
//    var templateCode = $("#templateCode").val();
//    
//    var url = "/getDataValueAction?tokenNo=" + tokenNo + "&action=refreshError&templateCode=" + templateCode;
//    ajaxPostUrl(url, refreshErrorResponse);
//}

function refreshErrorResponse(response) {
    hideBlackProcessIndicator();
    if (!lhsIsNull(response) && response === "success") {

        openSuccessModal("Error Refreshed Successfully !");
        setTimeout(function () {
            window.location.reload();
        }, 1000);

    } else {
        openErrorModal("Some Error Occurred ! Could Not Refreshed Records !");
        document.getElementById("refBtn").disabled = false;

    }

}