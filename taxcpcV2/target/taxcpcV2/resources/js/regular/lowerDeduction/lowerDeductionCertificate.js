/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    $('#lowerDeducTab').tablesorter();
});


//$(function () {
//    $('#from_date').datetimepicker({
//        format: 'DD-MM-YYYY'
//    });
//
//    $("#from_date").on("dp.change", function (e) {
//        $('#certificate_valid_upto').data("DateTimePicker").minDate(e.date); //to_date date not less than from_date
//    });
//
//    $('#certificate_valid_upto').datetimepicker({
//        format: 'DD-MM-YYYY'
//    });
//
//}, false);

//function doOnLoad(id) {
//    var myCalendar;
//    myCalendar = new dhtmlXCalendarObject(id);
//    myCalendar.setDateFormat("%d-%m-%Y");
//    myCalendar.hideTime();
//
//
//}//end function


function remove_comma_from_amt(id) {
    var sourceString = document.getElementById(id).value;
    var out = sourceString.replace(/[`~!@#$%^&*()_|+\-=?;:'",<>\{\}\[\]\\\/]/gi, '');
    document.getElementById(id).value = out;
}//end function

function callEntryPage() {
    var cp = $("#globalcontextpath").val();
    window.location = cp + "/tdsLowerDeductionEntry?action=add";

}
function callBrowsePage() {
    var cp = $("#globalcontextpath").val();
    window.location = cp + "/tdsLowerDeductionBrowse";

}

function updateTdsSplRateDetail() {
    remove_comma_from_amt("tds_limit_amt");
    remove_comma_from_amt("amount_consumed");
    var fltrPanNo = document.getElementById("panno");

    var certificate_no = document.getElementById("certificate_no");
    var from_date = document.getElementById("from_date");
    var tds_code = document.getElementById("tds_code");
    var certificate_valid_upto = document.getElementById("certificate_valid_upto");
    var tds_tran_limit = document.getElementById("tds_limit_amt");
    var tds_rate = document.getElementById("tds_rate");
    var cp = document.getElementById("globalcontextpath").value;

    if (lhsIsNull(fltrPanNo.value)) {
        fltrPanNo.focus();
        addActionError("message", "Enter PAN No.");

    } else if (lhsIsNull(certificate_no.value)) {
        certificate_no.focus();
        addActionError("message", "Enter Certificate No");
    }
//    else if (!validateCertificateNo(certificate_no)) {
//    } 
    else if (lhsIsNull(from_date.value)) {
        from_date.focus();
        addActionError("message", "Enter Certificate From Date");
    } else if (lhsIsNull(tds_code.value) || tds_code.value === '-1') {
        tds_code.focus();
        addActionError("message", "Select TDS Section");
    } else if (lhsIsNull(certificate_valid_upto.value)) {
        certificate_valid_upto.focus();
        addActionError("message", "Enter Certificate Valid upto");
    } else if (!validateCertificateNumber(certificate_no)) {
        certificate_no.focus();
        addActionError("message", "Enter Valid Certificate Number");
    } else if (lhsIsNull(tds_tran_limit.value)) {
        tds_tran_limit.focus();
        addActionError("message", "Enter TDS Tran. Limit");
    } else if (lhsIsNull(tds_rate.value)) {
        tds_rate.focus();
        addActionError("message", "Enter TDS Rate");
    } else {
        if (!ajax_call_enabled) {
            var btnSave = document.getElementById("saveBtn");
            $("#tdsSplRateEntryPage").submit(function (e)
            {
                var postData = $(this).serializeArray();
                var formURL = $(this).attr("action");
                $.ajax(
                        {
                            url: formURL,
                            type: "POST",
                            data: postData,
                            success: function (data, textStatus, jqXHR)
                            {
                                //data: return data from server                            
                                ajax_call_enabled = false;
                                if (!lhsIsNull(data) && data === 'success') {
                                    window.location = cp + "/tdsLowerDeductionBrowse";
                                } else {
                                    addActionError("error", "Some Error Occured, Could Not Update");
                                    enableButton(btnSave);
                                }
                            },
                            error: function (jqXHR, textStatus, errorThrown)
                            {
                                ajax_call_enabled = false;  //if fails    
                                enableButton(btnSave);
                            }
                        });
                e.preventDefault(); //STOP default action
                e.unbind(); //unbind. to stop multiple form submit.
            });
            removeError();
            ajax_call_enabled = true;
            disableButton(btnSave);
            $("#tdsSplRateEntryPage").submit(); //Submit  the FORM
        }
    }
}//end function
function checkCertificateValidUpto(field) {
    try {
        var certificate_date = field.value;
        var from_date = document.getElementById("from_date").value;
        var certificate_valid_upto = document.getElementById("certificate_valid_upto");
        var value_certificate_date = certificate_date !== "" && certificate_date !== "null" && certificate_date !== null;
        var value_from_date = from_date !== "" && from_date !== "null" && from_date !== null;

        if (value_certificate_date && value_from_date) {

            var certificatedateFinal = certificate_date.split("-")[1] + "/" + certificate_date.split("-")[0] + "/" + certificate_date.split("-")[2];
            var fromdateFinal = from_date.split("-")[1] + "/" + from_date.split("-")[0] + "/" + from_date.split("-")[2];

            var checkCertificateDate = new Date(certificatedateFinal);
            var checkFromDate = new Date(fromdateFinal);

            if (checkCertificateDate >= checkFromDate) {
                return true;
            } else {
                certificate_valid_upto.focus();
                certificate_valid_upto.value = "";
                addActionError("message", "Enter Valid Certificate Date");
            }
        }

    } catch (e) {
    }
}// end function

function saveTdsSplRateDetail() {

    remove_comma_from_amt("tds_limit_amt");
    remove_comma_from_amt("amount_consumed");
    var fltrPanNo = document.getElementById("panno");

    var certificate_no = document.getElementById("certificate_no");
    var from_date = document.getElementById("from_date");
    var tds_code = document.getElementById("tds_code");
    var certificate_valid_upto = document.getElementById("certificate_valid_upto");
    var tds_tran_limit = document.getElementById("tds_limit_amt");
    var tds_rate = document.getElementById("tds_rate");

    if (lhsIsNull(fltrPanNo.value)) {
        fltrPanNo.focus();
        addActionError("message", "Enter PAN Number");

    } else if (lhsIsNull(certificate_no.value)) {
        certificate_no.focus();
        addActionError("message", "Enter Certificate Number");
    } else if (lhsIsNull(tds_code.value) || tds_code.value === '-1') {
        tds_code.focus();
        addActionError("message", "Select TDS Section");
    } else if (!validateCertificateNumber(certificate_no)) {
        certificate_no.focus();
        addActionError("message", "Enter Valid Certificate Number");
    } else if (lhsIsNull(from_date.value)) {
        from_date.focus();
        addActionError("message", "Enter Certificate From Date");
    } else if (lhsIsNull(certificate_valid_upto.value)) {
        certificate_valid_upto.focus();
        addActionError("message", "Enter Certificate Valid upto");
    } else if (!checkCertificateValidUpto(certificate_valid_upto)) {
        certificate_valid_upto.focus();
        addActionError("message", "Please Enter Valid Certificate  upto");
    } else if (lhsIsNull(tds_tran_limit.value)) {
        tds_tran_limit.focus();
        addActionError("message", "Enter TDS Tran. Limit");
    } else if (lhsIsNull(tds_rate.value)) {
        tds_rate.focus();
        addActionError("message", "Enter TDS Rate");
    } else {
        checkOldCertificate();
    }


}//end function 


function checkOldCertificate() {
    var cp = document.getElementById("globalcontextpath").value;
    var fromDate = document.getElementById("from_date").value;
    var tdsCode = document.getElementById("tds_code").value;
    var deducteeCode = document.getElementById("deductee_code").value;
    var certificate_no = document.getElementById("certificate_no").value;
    var url = cp + "/getTdsSplRateMasterAjaxAction";
    $.ajax({
        url: url,
        type: "GET",
        data: {
            action: 'getDeducteeCertificateNo',
            fromDate: fromDate,
            tdsCode: tdsCode,
            deducteeCode: deducteeCode,
            certificateNo: certificate_no
        },
        dataType: "text",
        success: function (data) {
            if (data === "NotExist") {
                ajaxSaveForm();
            } else {

                alert("Certificate already Exists");
//                // Certificate No exist then Update Data
//                document.getElementById("dialog-confirm_UpdateTdsSplCertificate").style.display = "block";
//                $("#dialog-confirm_UpdateTdsSplCertificate").dialog({
//                    resizable: false,
//                    font: 10,
//                    modal: true,
//                    buttons: {
//                        "Ok": function () {
//                            $(this).dialog("close");
//                            $("#tdsSplRateEntryPage").attr("action", cp + "/tdsSpclRateEntry?action=update");
//                            ajaxSaveForm();
//                        },
//                        Cancel: function () {
//                            $(this).dialog("close");
////                            alert("Cancel");
////                            document.getElementById("certificate_no").value = data;
////                            $("#tdsSplRateEntryPage").attr("action", cp + "/tdsSpclRateEntry?action=edit&update=true");
////                            document.getElementById("tdsSplRateEntryPage").submit();
//                        }
//                    }
//                });
            }
        },
        error: function () {
        }
    });

}// end method
var xmlHttp = "";
var ajax_call_enabled = false;
function ajaxSaveForm() {

    var cp = document.getElementById("globalcontextpath").value;
    var btnSave = document.getElementById("saveBtn");
//    alert("tdsLowerDeductionManupulation..");
    if (!ajax_call_enabled) {
        $("#tdsSplRateEntryPage").submit(function (e)
        {
            var postData = $(this).serializeArray();
            var formURL = $(this).attr("action");
            $.ajax(
                    {
                        url: formURL,
                        type: "POST",
                        data: postData,
                        success: function (data, textStatus, jqXHR)
                        {

                            //data: return data from server                            
                            ajax_call_enabled = false;
                            if (!lhsIsNull(data) && data === 'success') {
                                window.location = cp + "/tdsLowerDeductionBrowse";
                            } else {
//                                alert("1..............");
                                addActionError("error", "Some Error Occured, Could Not Save");
//                                window.location = cp + "/tdsLowerDeductionEntry?action=add";
                                enableButton(btnSave);
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown)
                        {
                            alert("tdsLowerDeductionManupulation...");
                            ajax_call_enabled = false;  //if fails      
                            enableButton(btnSave);
                        }
                    });
            e.preventDefault(); //STOP default action
//            e.unbind(); //unbind. to stop multiple form submit.
        });
        removeError();
        ajax_call_enabled = true;
        disableButton(btnSave);
        $("#tdsSplRateEntryPage").submit(); //Submit  the FORM
    }
}


function deleteTdsSplRateMastRecord(tds_code, certificate_no, panno, entitycode, accyear, tdsTypeCode, client_code) {
    document.getElementById("dialog-confirm_delete").style.display = "block";
    $(function () {
        $("#dialog-confirm_delete").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                "Ok": function () {
                    $(this).dialog("close");
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
                            removeError();
                            var q = xmlhttp.responseText;
                            q = q.trim();
//                alert(q);
                            if (!lhsIsNull(q) && q !== 'error') {
                                if (q === 'success') {
                                    window.location.reload();
                                } else {
                                    openErrorModal(q);
                                }
                            } else {
                                openErrorModal("Some Error Occured, Could Not Delete");
                            }

                        }
                    };
                    var url = cp + "/tdsDeductionManupulation?action=delete";
//                    url += "&tdsSplRateMastId.from_date=" + from_date;
                    url += "&tdsSplRateMastId.tds_code=" + tds_code;
                    url += "&tdsSplRateMastId.certificate_no=" + certificate_no;
                    url += "&tdsSplRateMastId.deductee_panno=" + panno;
                    url += "&tdsSplRateMastId.entity_code=" + entitycode;
                    url += "&tdsSplRateMastId.acc_year=" + accyear;
                    url += "&tdsSplRateMastId.tds_type_code=" + tdsTypeCode;
                    url += "&tdsSplRateMastId.client_code=" + client_code;
                    xmlhttp.open("GET", url);
                    xmlhttp.send(null);
                },
                Cancel: function () {
                    $(this).dialog("close");
                }
            }
        });
    });
}



function onEditForm(id) {
    document.getElementById("tdsSpclRateEntryFormId~" + id.split("~")[1]).action = "tdsLowerDeductionEntry";
    document.getElementById("tdsSpclRateEntryFormId~" + id.split("~")[1]).submit();
}

function validatePAN(field) {
    let value = field.value;
    let tdsType = $("#tdsType").val();
//    let quarter = $("#quarter").val();
    let Exp;
    if (!lhsIsNull(value)) {
        Exp = /^([a-zA-Z]){3}(a|A|b|B|c|C|f|F|g|G|h|H|j|J|k|K|l|L|p|P|t|T){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
        if (Exp.test(value)) {
            if (tdsType === '24Q') {
                Exp = /^([a-zA-Z]){3}(p|P){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
            }
            if (!Exp.test(value) && tdsType === '24Q') {
                openErrorModal("PAN 4th character should be 'P' only");
                field.value = "";
                return false;
            }
        } else {
            openErrorModal("Invalid PAN");
            field.value = "";
            return false;
        }
    }
}//end function

function OnLoadSetFinancialYear(id, btnid) {
    let year = document.getElementById("sessionAccYear").value;
    var myCalendar;
    myCalendar = new dhtmlXCalendarObject({input: id, button: btnid});
    myCalendar.setDateFormat("%d-%m-%Y");
    myCalendar.hideTime();
    let fromDate;
    let toDate;
    let fromYear = year.split('-')[0], toYear = year.split('-')[1];
    fromDate = '01-04-20' + fromYear;
    toDate = '31-03-20' + toYear;
    myCalendar.setSensitiveRange(fromDate, toDate);
    if (window.dhx4.isIE11) {
        let left, right;
        if (id === 'fromDate') {
            left = '300px';
            right = '232px';
        } else if (id === 'toDate') {
            left = '589px';
            right = '232px';
        } else if (id === 'tdsDate') {
            left = '716px';
            right = '361px';
        }
        let settings = {
            date_field_id: id,
            calendar: myCalendar,
            left: left,
            right: right
        };
        openGlobalCalendarForIE(settings);
    }
}//end function

function checkDatesOfFinancialYear(field) {

    let year = document.getElementById("sessionAccYear").value;
    let actualDate = field.value;
    let begDate;
    let endDate;
    let fromYear = year.split('-')[0], toYear = year.split('-')[1];
    begDate = '04/01/20' + fromYear;
    endDate = '03/31/20' + toYear;
    var actualDateFinal = actualDate.split("-")[1] + "/" + actualDate.split("-")[0] + "/" + actualDate.split("-")[2];
    var checkBegDate = new Date(begDate);
    var checkEndDate = new Date(endDate);
    var checkActualDate = new Date(actualDateFinal);
    if (checkActualDate >= checkBegDate && checkActualDate <= checkEndDate) {
        return true;
    } else {
        field.value = "";
        openErrorModal("Date is not following the financial  year.");
    }
}