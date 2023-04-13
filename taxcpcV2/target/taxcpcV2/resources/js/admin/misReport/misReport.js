/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var ajax_call_enabled = false;
var cp = document.getElementById("globalcontextpath").value;

function onloadMisDashboard() {
//    var cp = document.getElementById("globalcontextpath").value;
    var actionUrl = cp + "/reportAction?action=dashboard";

    $.ajax({
        url: actionUrl,
        type: 'GET',
        beforeSend: function (xhr) {
            showProcessIndicator();
        },
        success: function (data, textStatus, jqXHR) {
            document.getElementById("dataSpan").innerHTML = data;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            hideProcessIndicator();
            console.log('inside error jqXHR: ' + jqXHR);
            console.log('inside error textStatus: ' + textStatus);
            console.log('inside error errorThrown: ' + errorThrown);
        },
        complete: function (jqXHR, textStatus) {
            hideProcessIndicator();
        }
    });
}//end function

function showSelectedReport(id) {
    if (!lhsIsNull(id)) {
        removeError();

        var report_header = document.getElementById(id).innerHTML;
        var splitArr = id.split("~");
        var l_sequence_id = document.getElementById("l_seqid_" + splitArr[0] + "~" + splitArr[1]).value;
        var l_rep_group = document.getElementById("l_report_group_" + splitArr[0] + "~" + splitArr[1]).value;
        var l_pagin_flag = document.getElementById("pagin_flag_" + splitArr[0] + "~" + splitArr[1]).value;

        var url = "/showReportDetailAction?action=reportContent";
        url += "&seqId=" + encodeURIComponent(l_sequence_id);
        url += "&req_pagination_flag=" + encodeURIComponent(!lhsIsNull(l_pagin_flag) ? l_pagin_flag : '');
        url += "&reportGroup=" + encodeURIComponent(l_rep_group);
        url += "&report_header_text=" + encodeURIComponent(report_header);
        callUrl(url);
    }
}//end function

function getReportParaDescSelect(id) {
    var index = id.split('~')[1];
//    var cp = document.getElementById("globalcontextpath").value;
    var para_column = "";
    var column_select_list_value = "";

    try {
        para_column = document.getElementById("paraColumn_" + index).value;
        column_select_list_value = document.getElementById("columnSelectListValue_" + index).value;
    } catch (e) {
    }

    var actionUrl = cp + "/reportAction?action=paraDescSelect";
    actionUrl += "&paraColumn=" + encodeURIComponent(para_column);
    actionUrl += "&headCount=" + encodeURIComponent(index);
    actionUrl += "&columnSelectListValue=" + encodeURIComponent(column_select_list_value);

    $.ajax({
        url: actionUrl,
        type: 'GET',
        success: function (data, textStatus, jqXHR) {
            document.getElementById(id).innerHTML = data;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('inside error jqXHR: ' + jqXHR);
            console.log('inside error textStatus: ' + textStatus);
            console.log('inside error errorThrown: ' + errorThrown);
        },
        complete: function (jqXHR, textStatus) {
        }
    });
}//end function

function showReportData() {
//    document.getElementById("h_action").value = "showReportDetail";

    var colCount = "";
    var pagination_flag = "";

    try {
        pagination_flag = document.getElementById("h_reqPagination").value;
        colCount = document.getElementById("paraDescColumnsCount").value;
    } catch (e) {
    }
    var flag = true;
//    for (var i = 1; i <= colCount; i++) {
//        var manFld = document.getElementById("mlbl_" + i).innerHTML;
//        if (manFld === "*") {
//            var fldVal = document.getElementById("field_" + i).value;
//            if (!isNullSp(fldVal)) {
//                flag = true;
//            } else {
//                flag = false;
//                alert("Please enter " + document.getElementById("lbl_" + i).innerHTML.substring(0, (document.getElementById("lbl_" + i).innerHTML.length - 1)));
//                break;
//            }
//        }
//    }
    if (flag) {
        if (pagination_flag === 'T') {
            misTotalRecordsCount();
        } else {
            document.getElementById("h_action").value = "showReportDetail";

            var reportForm = document.reportModelContentForm;
            var actionUrl = $(reportForm).attr('action');

            $(reportForm).on('submit', function (e) {
                var postData = $(this).serializeArray();

                $.ajax({
                    url: actionUrl,
                    type: 'POST',
                    data: postData,
                    beforeSend: function (xhr) {
                        showProcessIndicator();
                    },
                    success: function (response, textStatus, jqXHR) {
                        document.getElementById("dataSpan").innerHTML = response;
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        hideProcessIndicator();
                    },
                    complete: function (jqXHR, textStatus) {
                        hideProcessIndicator();
                    }
                });
                e.preventDefault();
            });
            $(reportForm).submit();
        }
    }
}//end function

function misTotalRecordsCount() {
    removeError();

    document.getElementById("h_action").value = "misRecordsCount";
//    var cp = document.getElementById("globalcontextpath").value;

    var reportForm = document.reportModelContentForm;
    var formData = $(reportForm).serializeArray();

    $.ajax({
        url: cp + "/showReportDetailAction",
        type: "POST",
        data: formData,
        beforeSend: function (xhr) {
            showProcessIndicator();
        },
        success: function (data, textStatus, jqXHR) {
            var result = data.trim();
            var paginatorParams = result.split('~');
            if (paginatorParams.length && paginatorParams[0] === 'success') {
                document.getElementById("totalRecordsSpan").innerHTML = paginatorParams[1];
                document.getElementById("totalPagesSpan").innerHTML = paginatorParams[2];
                document.getElementById("pageNumberSpan").innerHTML = paginatorParams[3];
                document.getElementById("paginator_top").style.display = "block";

                setTimeout(function () {
                    document.getElementById("h_action").value = "showReportDetail";
                    showRecords();
                }, 1000);
            } else {
                var no_records_text = "<div class=\"no-record-found-browse col-lg-6 offset-lg-3 my-3 text-center\" > <img class=\"pr-3\" src=\"resources/images/global/empty-box.png\"> No Records Found</div>";
                document.getElementById("dataSpan").innerHTML = "";
                document.getElementById("dataSpan").innerHTML = no_records_text;
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            hideProcessIndicator();
        },
        complete: function (jqXHR, textStatus) {
//            hideProcessIndicator();
        }
    });
}//end function

function resetMisFilters() {
    showProcessIndicator();
    window.location.reload();
}//end function

function customCurrentPageOnChange() {

//    var cp = document.getElementById("globalcontextpath").value;
    var rec_per_page_value = document.getElementById("recordsPerPageSelect").value;
    $.ajax({
        url: cp + "/showReportDetailAction",
        type: "POST",
        data: {
            action: 'recordsPerPage',
            setRecPerPage: rec_per_page_value
        },
        beforeSend: function (xhr) {
            showProcessIndicator();
        },
        success: function (data, textStatus, jqXHR) {
            var result = data.trim();
            if (result === 'success') {
                document.getElementById("recordsPerPage").value = rec_per_page_value;
                $('#settings').removeClass('show');
                showReportData();
            } else {
                openErrorModal("Some error occurred!");
            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            hideProcessIndicator();
            openErrorModal("Some error occurred!", "window.location.reload();");
        },
        complete: function (jqXHR, textStatus) {
//            hideProcessIndicator();
        }
    });
}//end function

function getAutocompleteList(inputElement, elementIndex) {

    document.getElementById("h_action").value = "autocomplete";
//    var cp = document.getElementById("globalcontextpath").value;
    var filterFormId = document.getElementById("fltrformId").value;
    var formData = $($('#' + filterFormId)).serializeArray();
    console.log('formdata: ' + JSON.stringify(formData));

    var autocompleteList = $.ajax({
        url: cp + '/reportAction',
        data: formData,
        dataType: 'jsonp'
    }).done(function (res) {
        alert(res);
    });

}//end function

function misBulkDownloadRequest(rep_seq_id, report_group) {

    var actionUrl = cp + '/reportAction';

    if (!ajax_call_enabled) {
        $.ajax({
            url: actionUrl,
            type: 'POST',
            data: {
                action: "misBulkDownload",
                seqId: rep_seq_id,
                reportGroup: report_group
            },
            beforeSend: function (xhr) {
                ajax_call_enabled = true;
                showProcessIndicator();
                callingProcData = {};
            },
            success: function (data, textStatus, jqXHR) {
                if (!lhsIsNull(data)) {
                    // Required to initialize an object before calling the procedure.
                    // Required properties,
                    // callingProcName, tokenNo - procedure name and token number respectively.
                    // (Optional) Other properties can be added if required to send to the server.
                    openSuccessModal("Your request is initiated. Token No: " + data);

                    callDedicatedProcedure({
                        callingProcName: "ProcMISReportDwnld",
                        tokenNo: data,
                        sequenceId: rep_seq_id
                    });

                } else {
                    openErrorModal("Some error occurred!!");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                ajax_call_enabled = false;
                hideProcessIndicator();
            },
            complete: function (jqXHR, textStatus) {
                ajax_call_enabled = false;
                hideProcessIndicator();
            }
        });
    } else {
        openErrorModal("Please wait your previos request is under process");
        return;
    }
}//end function


function validateMisImportFile(evt) {
    evt.preventDefault();
    var fileName = document.getElementById('misUploadFile').value;
    var docname = fileName.replace(/^.*[\\\/]/, '');
    document.getElementById("docname").value = docname;
    let submitFlag = false;
    try {
        let selectedFile = document.getElementById('misUploadFile').files[0];
        if (lhsIsNull(selectedFile) || lhsIsNull(selectedFile.name)) {
            openErrorModal('Please select File to upload.');
        } else {
            submitFlag = true;
        }
    } catch (e) {
    }
    if (submitFlag) {

        var rep_seq_id = document.getElementById("seq_id").value;
        var report_header_text = document.getElementById("report_header_text").value;
        var rep_upload_template_code = document.getElementById("rep_upload_template_code").value;
        var reportGroup = document.getElementById("reportGroup").value;
        var rep_upload_dir = document.getElementById("rep_upload_dir").value;
        var name = document.getElementById("docname").value;

        var actionUrl = '/showMisUploadTab?action=upload';
        actionUrl += "&seqId=" + encodeURIComponent(rep_seq_id);
        actionUrl += "&reportGroup=" + encodeURIComponent(reportGroup);
        actionUrl += "&report_header_text=" + encodeURIComponent(report_header_text);
        actionUrl += "&rep_upload_template_code=" + encodeURIComponent(rep_upload_template_code);
        actionUrl += "&rep_upload_dir=" + encodeURIComponent(rep_upload_dir);
        actionUrl += "&filename=" + encodeURIComponent(name);
        ajaxSubmitPostData(actionUrl, "misuploadTabFile", temp)

        function temp(data, textStatus, jqXHR) {
            if (!lhsIsNull(data)) {
                var value = data.split("#");
                var token = value[0];
                var filename = value[1];
                // Required to initialize an object before calling the procedure.
                // Required properties,
                // callingProcName, tokenNo - procedure name and token number respectively.
                // (Optional) Other properties can be added if required to send to the server.
                openSuccessModal("Your request is initiated. Token No: " + token,'window.location.reload();');

                callDedicatedProcedure({
                    callingProcName: "ProcMISReportDwnld",
                    tokenNo: token,
                    sequenceId: rep_seq_id,
                    docFileName: filename
                });

            } else {
                openErrorModal("Some error occurred!!");
            }
        }

    }

}//End Function


function showImportTab(id) {
    if (!lhsIsNull(id)) {

        var report_header = document.getElementById(id).innerHTML;
        var splitArr = id.split("~");
        var l_sequence_id = document.getElementById("l_seqid_" + splitArr[0] + "~" + splitArr[1]).value;
        var l_rep_group = document.getElementById("l_report_group_" + splitArr[0] + "~" + splitArr[1]).value;
        var rep_upload_template_code = document.getElementById("rep_upload_template_code_" + splitArr[0] + "~" + splitArr[1]).value;
        var rep_upload_dir = document.getElementById("rep_upload_dir_" + splitArr[0] + "~" + splitArr[1]).value;

        var url = "/showMisUploadTab?action=uploadfile";
        url += "&seqId=" + encodeURIComponent(l_sequence_id);
        url += "&reportGroup=" + encodeURIComponent(l_rep_group);
        url += "&report_header_text=" + encodeURIComponent(report_header);
        url += "&rep_upload_template_code=" + encodeURIComponent(rep_upload_template_code);
        url += "&rep_upload_dir=" + encodeURIComponent(rep_upload_dir);
        callUrl(url);
    }
}//end function

//mis report download servlet function

function onloadrepDwnbyServlet(){
    var x =  document.getElementById("errorResponsefromServlet").value;
      if(!lhsIsNull(x)){
       $("#error_details").text(x);
      document.getElementById("error_div_dwn").style.display = "block"; 
      document.getElementById("errorResponsefromServlet").value=null;
      }else{
    
      }  
}
