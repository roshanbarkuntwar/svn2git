/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
//            alert("Browser does not support XMLHTTP Request");
            openErrorModal("Browser does not support XMLHTTP Request");
            return;
        }
        ajax_call_enabled = true;
        xmlHttp.onreadystatechange = resuting_call;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    } else {
//        window.alert("Your previous request is in progress");
        openErrorModal("Your previous request is in progress");
    }
}

var paginatorObj = function (callFlag, pageNum) {
    var tempStartVal = 0;
    var tempEndVal = 0;
    if (pageNum === 1 || (pageNum <= 5)) {
        tempStartVal = 1;
        tempEndVal = 5;
    } else if (pageNum > 5) {

        if (callFlag === 'NEXT') {
            tempEndVal = pageNum;
            tempStartVal = tempEndVal - 4
        } else if (callFlag === 'PREV') {
            tempEndVal = pageNum;
            tempStartVal = tempEndVal - 4
        } else if (callFlag === 'JUMP') {
            tempEndVal = pageNum;
            tempStartVal = tempEndVal - 4
        }

    }

    return {
        startVal: tempStartVal,
        endVal: tempEndVal
    };
}//end function


function getCurrentPageData(pageNum, callFlag) {

    var dataGridAction = document.getElementById("dataGridAction").value;
    document.getElementById("global-simple-process-indicator").style.display = "flex";
    var cp = document.getElementById("globalcontextpath").value;
    var pageNumber = pageNum;
    var recordsPerPage = document.getElementById("recordsPerPageSelect").value;
    var totalRecords = document.getElementById("totalRecordsSpan").innerHTML.trim();
    var countCall = document.getElementById("countCall").value;
    var startValue = document.getElementById("startValue").value;
    startValue = parseInt(startValue);
    var endValue = document.getElementById("endValue").value;
    endValue = parseInt(endValue);
    var listValue = document.getElementById("listValue");
    var total_count = document.getElementById("totalPagesSpan").innerHTML;
    total_count = parseInt(total_count);
    pageNum = parseInt(pageNum);
    var filterFormId = document.getElementById("fltrformId").value;
    var pageObj = paginatorObj(callFlag, pageNum);
    startValue = pageObj.startVal;
    endValue = pageObj.endVal;
//    console.log("pageNumber--" + pageNumber);
//    console.log("total_count--" + total_count);
//    console.log("pageNum--" + pageNum);



    var url = "search=true";
    url += "&pageNumber=" + encodeURIComponent(pageNumber);
    url += "&recordsPerPage=" + encodeURIComponent(recordsPerPage);
    url += "&totalRecords=" + encodeURIComponent(totalRecords);

    if (!ajax_call_enabled) {

        var postData = $("#" + filterFormId).serializeArray();
//        var postData = $("#TDSFileteForm").serializeArray();
        var formURL = cp + "/" + dataGridAction + "?" + url;
        console.log("formURL....." + formURL);
        $.ajax(
                {
                    url: formURL,
                    type: "POST",
                    data: postData,
                    success: function (data, textStatus, jqXHR)
                    {
                        console.log("inside ajax success");
                        var q = data;
                        q = q.trim();
                        //// Paginator
                        try {
                            document.getElementById("pageNumberSpan").innerHTML = pageNumber;
                            var datagrid = "<ul class=\"pagination m-0\" style=\"margin-top:0px;\">" +
                                    "                            <li class=\"page-item\" title=\"Go To First Page\"><a class=\"page-link\" onclick=\"getCurrentPageData('1','JUMP');\"><i class=\"fa fa-fast-backward\"></i></a></li>" +
                                    "                            <li class=\" page-item\" title=\"Go To Previous Page\"><a class=\"page-link\"><i class=\"fa fa-chevron-left\" onclick=\"getPreviousPage();\"></i></a></li>";
                            if (total_count > 0) {
                                datagrid += numberGrid(pageNum, startValue, endValue, total_count);
                                document.getElementById("paginator_top").style.display = "block";
                            }
                            datagrid += "     <li class=\"page-item\" title=\"Go To Next Page\"><a class=\"page-link\" onclick=\"getNextPage();\"><i class=\"fa fa-chevron-right\"></i></a>                       </li><li class=\"page-item\" title=\"Go To Last Page\"><a class=\"page-link\" onclick=\"getCurrentPageData('" + total_count + "','JUMP');\" id=\"832\"><i class=\"fa fa-fast-forward\"></i></a></li>" +
                                    "                        </ul>";
                            document.getElementById("paginationUl").innerHTML = datagrid;
                            $("#li-" + pageNum).addClass("page-item active");
                        } catch (e) {
                            ajax_call_enabled = false;
                        }
                        //// End Paginator
                        if (total_count > 0) {
                            try {
                                if (!lhsIsNull($('#bulk_download_btn'))) {
                                    $('#bulk_download_btn').removeClass('d-none');
                                    $('#bulk_download_btn').removeAttr("disabled");
                                }
                            } catch (e) {
                            }
                        }

//// If 'No records found' then Call Again This method
                        countCall = parseFloat(countCall);
                        var result = q.toString();
                        var check = result.search('No records found');
////            alert("check : " + check);
                        if (check != -1) {
//                alert("Search Match");
                            countCall = countCall + 1;
//                alert("countCall : " + countCall);
                            if (countCall < 2) {
//                    alert("countCall : " + countCall);
                                document.getElementById("countCall").value = countCall;
                                getCurrentPageData(pageNum);
                            }
                            if (countCall === 2) {
                                document.getElementById("countCall").value = '0';
                            }
                        } else {
                            document.getElementById("countCall").value = '0';
                        }
//// End

                        document.getElementById("dataSpan").innerHTML = q;
                        try {
                            if (dataGridAction === "tdsChallanAllocationDataGrid") {
                                var total_records = document.getElementById("totalRecordsSpan").innerHTML;
                                var records = parseInt(total_records.trim());
                                if (records > 0) {
                                    $("#actiondiv").show(true);
                                } else {
                                    $("#actiondiv").hide(true);
                                }
                                setAllChecked();
                            }
                        } catch (e) {
                        }
                        try {
                            $('#table').tablesorter({
                                headers: {
                                    0: {sorter: false}
                                }
                            });
//                            $('#table').DataTable({
//                                dom: 'Bfrtip',
//                                buttons: [
//                                    'excel'
//                                ]
//                            });
                        } catch (Err) {

                        }
                        try {
                            if (dataGridAction === "manageNavigationDataAction") {
                                $('tbody').sortable({
                                    items: "> tr",
                                    appendTo: "parent",
                                    helper: "clone",
                                    stop: function () {
                                        var oRows = document.getElementById('NavTable')
                                                .getElementsByTagName('tr');
                                        var iRowCount = oRows.length;
//                                                console.log('rowCount---'+iRowCount);
                                        for (var i = 1; i <= iRowCount; i++) {
//							console.log(i + "rowid---" + oRows[i].id);
                                            var str = oRows[i].id.split("~");
//                                                        console.log('row--'+str.length);
                                            var no = str[1];
//                                                        console.log('slno-'+no);
                                            document.getElementById("slno-" + no).value = i;
                                            document.getElementById("UpdOrdBy").submit();
                                        }
                                    }
                                });
                            }
                        } catch (err) {
                            console.log("err--" + err);
                        }
                        document.getElementById("global-simple-process-indicator").style.display = "none";
                    },
                    error: function (jqXHR, textStatus, errorThrown)
                    {
                        console.log("inside ajax error");
                        ajax_call_enabled = false; //if fails
                    }
                });
    }
    ajax_call_enabled = false;
}//end method


function numberGrid(pageNum, startValue, endValue, total_count) {
    var dataGrid = "";
    if (total_count > 6) {
        dataGrid = "";
        for (var i = startValue; i <= endValue; i++) {



            dataGrid += "      <li class=\"pointer-disabled page-item_no\" id=\"li-" + i + "\"><a class=\"page-link\" onclick=\"getCurrentPageData(this.id,'JUMP');\" id=\"" + i + "\" name=\"10\">" + i + "</a></li>";
            $("#" + i).closest("li").removeClass("active");
        }
    } else {
        dataGrid = "";
        for (var i = 1; i <= total_count; i++) {



            dataGrid += "      <li class=\"pointer-disabled page-item_no\" id=\"li-" + i + "\"><a class=\"page-link\" onclick=\"getCurrentPageData(this.id,'JUMP');\" id=\"" + i + "\" name=\"10\">" + i + "</a></li>";
//            console.log("data grid---" + i);
            $("#" + i).closest("li").removeClass("active");
        }

        $("#li-" + pageNum).addClass("active");
    }


    return dataGrid;
}



function setCurrentPage() {
    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
        var q = xmlHttp.responseText;
        try {
            q = q.trim();
            document.getElementById("dataSpan").innerHTML = q;
            document.getElementById("global-process-indicator").style.visibility = "hidden";
            try {
                document.getElementById("pageNumberSpan").innerHTML = currentPageNo;
                // document.getElementById("pageNumberBottomSpan").innerHTML = currentPageNo;
            } catch (e) {
                ajax_call_enabled = false;
            }
            ajax_call_enabled = false;
        } catch (err) {
            ajax_call_enabled = false;
        }
    }
}// end method
function getJqueryAjaxCall(url) {
//    alert(url+"---url");
    if (!ajax_call_enabled) {

        var postData = $("#filterForm").serializeArray();
        var formURL = url;
        ajax_call_enabled = true;
        $.ajax(
                {
                    url: formURL,
                    type: "POST",
                    data: postData,
                    success: function (data, textStatus, jqXHR)
                    {
                        try {
                            var q = data;
                            q = q.trim();
//                              alert("q---" + q);
                            document.getElementById("dataSpan").innerHTML = q;
                            document.getElementById("global-process-indicator").style.visibility = "hidden";
                            try {
                                document.getElementById("pageNumberSpan").innerHTML = currentPageNo;
                                // document.getElementById("pageNumberBottomSpan").innerHTML = currentPageNo;
                            } catch (e) {
                                ajax_call_enabled = false;
                            }
                            ajax_call_enabled = false;
                        } catch (err) {
                            ajax_call_enabled = false;
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown)
                    {
                        ajax_call_enabled = false; //if fails
                    }
                });
    } else {
//        window.alert("Your previous request is in progress");
        openErrorModal("Your previous request is in progress");
    }
//    ajax_call_enabled = false;
}



function getPreviousPage() {
    if (!ajax_call_enabled) {
        //  document.getElementById("pagination_number_bottom").value = "";
        document.getElementById("pagination_number").value = "";
        var page_count = document.getElementById("pageNumberSpan").innerHTML;
        page_count = parseInt(page_count);
        if (page_count <= 1) {
//            alert("Cannot Move to Previous Page");
            openErrorModal("Cannot Move to Previous Page");
        } else {
            var count = page_count - 1;
            getCurrentPageData(count, "PREV");
        }
    } else if (ajax_call_enabled) {
        openErrorModal("Your previous request is in progress");
    }
}//end method


function getNextPage() {
    //alert("getNextPage");
    if (!ajax_call_enabled) {
        //  document.getElementById("pagination_number_bottom").value = "";
        document.getElementById("pagination_number").value = "";
        var page_count = document.getElementById("pageNumberSpan").innerHTML;
        var total_count = document.getElementById("totalPagesSpan").innerHTML;
        page_count = parseInt(page_count);
        total_count = parseInt(total_count);
        if (page_count >= total_count) {
//            alert("Cannot Move to Next Page");
            openErrorModal("Cannot Move to Next Page");
        } else {


            var count = page_count + 1;
            getCurrentPageData(count, "NEXT");
        }
    } else if (ajax_call_enabled) {
        openErrorModal("Your previous request is in progress");
//        window.alert("Your previous request is in progress");

    }
}//end method

function jumpToPage(id) {
    //alert("calll");
    if (!ajax_call_enabled) {
        var page_count = 0;
        if (id === 'bottomBtn') {

        } else if (id === 'topBtn') {
            page_count = document.getElementById("pagination_number").value;
            document.getElementById("pagination_number").value = "";
        }
        try {
            page_count = parseInt(page_count);
        } catch (error) {
            page_count = 0;
        }
        if (!isNaN(page_count)) {
            var total_count = document.getElementById("totalPagesSpan").innerHTML;
            total_count = parseInt(total_count);
            if (page_count > total_count || page_count === 0) {
//                alert("Not a valid Page No.");
                openErrorModal("Not a valid Page No.");
            } else {
                var count = page_count;
                getCurrentPageData(count, "JUMP");
            }
        } else {
//            window.alert("Please Enter a Valid Page Number to Jump");
            openErrorModal("Please Enter a Valid Page Number to Jump");
        }
    } else if (ajax_call_enabled) {
//        window.alert("Your previous request is in progress");
        openErrorModal("Your previous request is in progress");
    }
}//end method

function getCurrentPageOnChange() {

    var contextPath = document.getElementById("globalcontextpath").value;
    var val = document.getElementById("recordsPerPageSelect").value;
    var fltrformId = document.getElementById("fltrformId").value;
    var dataGridAction = document.getElementById("dataGridAction").value;

    showProcessIndicator();
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
            hideProcessIndicator();
            if (q === 'success') {
                $("#" + fltrformId).submit();
            } else {
                //openErrorModal("Some Error Occured");
               
            }
        }
    };
    var url = contextPath + "/" + dataGridAction + "?setRecPerPage=" + val + "&filterFlag=true";
    xmlhttp.open("GET", url, true);
    xmlhttp.send(null);
}//end method



function defaultValues() {
    var recordsPerPage = document.getElementById("recordsPerPage").value;
    if (recordsPerPage === '10') {
        document.getElementById("recordsPerPageSelect10").selected = true;
        document.getElementById("recordsPerPageSelect50").selected = false;
        document.getElementById("recordsPerPageSelect100").selected = false;
        document.getElementById("recordsPerPageSelect500").selected = false;
        document.getElementById("recordsPerPageSelect1000").selected = false;
        document.getElementById("recordsPerPageSelect5000").selected = false;
    } else if (recordsPerPage === '50') {
        document.getElementById("recordsPerPageSelect10").selected = false;
        document.getElementById("recordsPerPageSelect50").selected = true;
        document.getElementById("recordsPerPageSelect100").selected = false;
        document.getElementById("recordsPerPageSelect500").selected = false;
        document.getElementById("recordsPerPageSelect1000").selected = false;
        document.getElementById("recordsPerPageSelect5000").selected = false;
    } else if (recordsPerPage === '100') {
        document.getElementById("recordsPerPageSelect10").selected = false;
        document.getElementById("recordsPerPageSelect50").selected = false;
        document.getElementById("recordsPerPageSelect100").selected = true;
        document.getElementById("recordsPerPageSelect500").selected = false;
        document.getElementById("recordsPerPageSelect1000").selected = false;
        document.getElementById("recordsPerPageSelect5000").selected = false;
    } else if (recordsPerPage === '500') {
        document.getElementById("recordsPerPageSelect10").selected = false;
        document.getElementById("recordsPerPageSelect50").selected = false;
        document.getElementById("recordsPerPageSelect100").selected = false;
        document.getElementById("recordsPerPageSelect500").selected = true;
        document.getElementById("recordsPerPageSelect1000").selected = false;
        document.getElementById("recordsPerPageSelect5000").selected = false;
    } else if (recordsPerPage === '1000') {
        document.getElementById("recordsPerPageSelect10").selected = false;
        document.getElementById("recordsPerPageSelect50").selected = false;
        document.getElementById("recordsPerPageSelect100").selected = false;
        document.getElementById("recordsPerPageSelect500").selected = false;
        document.getElementById("recordsPerPageSelect1000").selected = true;
        document.getElementById("recordsPerPageSelect5000").selected = false;
    } else if (recordsPerPage === '5000') {
        document.getElementById("recordsPerPageSelect10").selected = false;
        document.getElementById("recordsPerPageSelect50").selected = false;
        document.getElementById("recordsPerPageSelect100").selected = false;
        document.getElementById("recordsPerPageSelect500").selected = false;
        document.getElementById("recordsPerPageSelect1000").selected = false;
        document.getElementById("recordsPerPageSelect5000").selected = true;
    }
}//end function

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    var target = evt.target || evt.srcElement;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    } else if (charCode === 13) {
        if (target.id === "pagination_number") {
            jumpToPage("topBtn");
        }

    }
    return true;
}




function showRecordsPaginator() {
    showProcessIndicator();
    document.getElementById("filterFlag").value = "true";

    var filterFormId = document.getElementById("fltrformId").value;
    if (!ajax_call_enabled) {

//        var browseAction = document.getElementById("browseAction").value;
        $("#" + filterFormId).submit(function (e) {
            var postData = $(this).serializeArray();
            var formURL = $("#" + filterFormId).attr("action");
//            var formURL = "tdsTransaction?search=true";
            $.ajax(
                    {
                        url: formURL,
                        type: "POST",
                        data: postData,
                        success: function (data, textStatus, jqXHR)
                        {
                            //alert("sucesss");
                            //data: return data from server
                            ajax_call_enabled = false;
                            if (data !== "" && data !== null && data !== "null") {
//                                try {
                                var data_split = data.split("#");
                                document.getElementById("totalPagesSpan").innerHTML = data_split[0];
                                //  document.getElementById("totalPagesBottomSpan").innerHTML = data_split[0];
                                document.getElementById("totalRecordsSpan").innerHTML = data_split[1];
                                //  document.getElementById("totalRecordsSpanBottomF").innerHTML = data_split[1];
                                //  document.getElementById("pageNumberBottomSpan").innerHTML = data_split[3];
                                document.getElementById("pageNumberSpan").innerHTML = data_split[3];
                                document.getElementById("recordsPerPage").value = data_split[2];
                                defaultValues();
                                //   document.getElementById("dataGridAction").value = "transactionDatagridAction";
                                var dataGridAction = document.getElementById("dataGridAction").value;
                                if (dataGridAction !== undefined && dataGridAction !== "" && dataGridAction !== "null" && dataGridAction !== null) {
                                    document.getElementById("paginator_top").style.display = "block";
//                                    document.getElementById("paginationBlock").style.display = "flex";
                                    getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);
                                }
                                if (parseInt($('#totalRecordsSpan').html().trim()) > 0) {
                                    $("#g_download").removeAttr("disabled");
                                    try {
                                        $("#bulk_download_btn").removeAttr("disabled");
                                    } catch (e) {
                                    }
                                }
//                                } catch (err) {
//                                    alert("Error "+err);
//                                }
                            } else {
                                try {
                                    document.getElementById("totalPagesSpan").innerHTML = 0;
                                    document.getElementById("totalRecordsSpan").innerHTML = 0;
                                    document.getElementById("pageNumberSpan").innerHTML = 0;
                                    document.getElementById("recordsPerPage").value = 0;
                                } catch (e) {
                                }
                                hideProcessIndicator();
                                var no_rec_found = "<div class=\"no-record-found-browse col-lg-6 offset-lg-3 my-3 text-center\" > \n\
                                <img class=\"pr-3\" src=\"resources/images/global/empty-box.png\"> No Records Found</div>";
                                document.getElementById("dataSpan").innerHTML = no_rec_found;
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown)
                        {
                            // document.getElementById("global-process-indicator").style.visibility = "hidden";
                            ajax_call_enabled = false; //if fails
                        }
                    });
            e.preventDefault(); //STOP default action
            e.stopImmediatePropagation();// Stops repeating request to be called.
            //e.unbind(); //unbind. to stop multiple form submit.
        });
        ajax_call_enabled = true;
        $("#" + filterFormId).submit(); //Submit  the FORM
    }
}//end function


function showPageData() {
    //alert("callll2");
    showProcessIndicator();
    var dataGridAction = document.getElementById("dataGridAction").value;
    var cp = document.getElementById("globalcontextpath").value;
    var deducteePanno = document.getElementById("deducteePanno").value;

    var url = "search=true";
    url += "&deducteePanno=" + encodeURIComponent(deducteePanno);

    if (!ajax_call_enabled) {
        var formURL = cp + "/" + dataGridAction + "?" + url;
        console.log("formURL....." + formURL);
        $.ajax(
                {
                    url: formURL,
                    type: "POST",
                    success: function (data, textStatus, jqXHR)
                    {
                        hideProcessIndicator()
                        console.log("inside ajax success");
                        var q = data;
                        q = q.trim();
                        try {
                            document.getElementById("dataSpan").innerHTML = q;
                        } catch (e) {
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown)
                    {
                        hideProcessIndicator()
                        console.log("inside ajax error");
                        ajax_call_enabled = false; //if fails
                    }
                });
    }
    ajax_call_enabled = false;
}//end method

// add this fucntion for preventing form  global error adn show remaining filter data 
function showFilterData(urlAction) {
    let url = urlAction;
    let cxp = document.getElementById("globalcontextpath").value;
    try {
        $.ajax({
            url: cxp + "/" + url + "?showFilter=true",
            type: 'GET',
            async: true,
            success: function (data, textStatus, jqXHR) {
                if (!lhsIsNull(data)) {
                    try {
                        if (data.split("#")[0] === 'challan') {
                            document.getElementById("f_tds_section").innerHTML = data.split("#")[1].trim();
                        } else if (data.split("#")[0] === 'transaction') {
                            document.getElementById("section").innerHTML = data.split("#")[1].trim();
                            document.getElementById("tran_tds_deduct_reason").innerHTML = data.split("#")[2].trim();
                        } else if (data.split("#")[0] === 'salary') {
                            document.getElementById("f_deductee_catg").innerHTML = data.split("#")[1].trim();
                        }
                    } catch (e) {
                        console.log(e);
                    }
                }
            }
        });
    } catch (e) {
        console.log(e);
    }
}