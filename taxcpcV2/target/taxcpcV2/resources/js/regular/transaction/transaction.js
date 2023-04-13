/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function openBrowseCalendar(id, btnid, left, right) {
    var myCalendar;
    myCalendar = new dhtmlXCalendarObject({input: id, button: btnid});
    myCalendar.setDateFormat("%d-%m-%Y");
    myCalendar.hideTime();

    if (window.dhx4.isIE11) {
        let settings = {
            date_field_id: id,
            calendar: myCalendar,
            left: left,
            right: right
        };
        openGlobalCalendarForIE(settings);
    }
}
function validateDateOnBlur(field) {
    try {
        var value = field.value;
//        var value = document.getElementById(field).value;
//        alert("value.." + value);
        var dateFinal = value;
        if (value !== "" && value !== "null" && value !== null) {
            if (value.length > 6) {
                var dataSplit = value.split(/[\.\-\/]/)[2];
                var len = dataSplit.length;
                if (len > 0) {
//                    alert(len);
                    var yearSplit = dataSplit.split('');
                    if (len === 1) {
                        dateFinal = value.split(/[\.\-\/]/)[0] + "-" + value.split(/[\.\-\/]/)[1] + "-" + "200" + yearSplit[0];
                    } else if (len === 2) {
                        dateFinal = value.split(/[\.\-\/]/)[0] + "-" + value.split(/[\.\-\/]/)[1] + "-" + "20" + yearSplit[0] + yearSplit[1];
                    } else if (len === 3) {
                        dateFinal = value.split(/[\.\-\/]/)[0] + "-" + value.split(/[\.\-\/]/)[1] + "-" + "2" + yearSplit[0] + yearSplit[1] + yearSplit[2];
                    } else if (len === 4) {
                        if ((yearSplit[0] + yearSplit[1]) === '00') {
                            dateFinal = value.split(/[\.\-\/]/)[0] + "-" + value.split(/[\.\-\/]/)[1] + "-" + "20" + yearSplit[2] + yearSplit[3];
                        } else {
                            dateFinal = value.split(/[\.\-\/]/)[0] + "-" + value.split(/[\.\-\/]/)[1] + "-" + value.split(/[\.\-\/]/)[2];
                        }
                    }
                }
                field.value = dateFinal;
//                document.getElementById(field).value = dateFinal;
            } else {
                field.value = "";
//                document.getElementById(field).value = "";
            }
        }
    } catch (e) {
    }
}//end method

function validateDate1(field) {
    var value = field.value;
    if (value !== "" && value !== "null" && value !== null) {
        var Exp = /^([0-9]{2})[\.\-\/]([0-9]{2})[\.\-\/]([0-9]{1,4})$/;
        if (!Exp.test(value)) {
            field.value = "";
        } else {
            validateDateOnBlur(field); //-- global method in template.js for data validation
            getTDSRate("false"); // call getTDSRate to get rate
        }
    }
}//end method

function checkChallanAmount() {

    var flag = true;
    var tds_challan_rowid_seq = document.getElementById("tds_challan_rowid_seq").value;
    if (tds_challan_rowid_seq !== null && tds_challan_rowid_seq !== "null" && tds_challan_rowid_seq !== "") {
        var balanceAmount = document.getElementById("mapBalanceAmountId").value;
        var tds_amt = document.getElementById("totalTdsAmt").value;
        balanceAmount = lhsIsNull(balanceAmount) ? '0' : balanceAmount;
        balanceAmount = parseFloat(balanceAmount);
        tds_amt = lhsIsNull(tds_amt) ? '0' : tds_amt;
        tds_amt = parseFloat(tds_amt);
        var finalAmt = balanceAmount - tds_amt;
        finalAmt = parseFloat(finalAmt);
        if (finalAmt >= 0) {
            flag = true;
        } else {
            flag = false;

        }


    }
    return flag;

}
function updateTDSContentData(field) {// change updateTDSDeducteeDetailsData() whenever this method's valiodation part is changed

    if (checkChallanAmount()) {


        var tds_section = document.getElementById("section").value;
        var tds_deduct_date = document.getElementById("tdsDate").value;
        var tran_ref_date = document.getElementById("tranDate").value;
        var deductee_panno = document.getElementById("panno").value;
        var tran_amt = document.getElementById("tranAmt").value;
        var ID = document.getElementById("rowid_seqid").value;
        var tds_deduct_reason = document.getElementById("deductReason").value;
        var certificate_no = document.getElementById("certificateNo").value;
        var tds_amt = document.getElementById("totalTdsAmt").value;
        var mapTDSChallanAmt = document.getElementById("mapTDSChallanAmt").value;
        var mapAllocatedAmount = document.getElementById("mapAllocatedAmount").value;
        //var ckdValue = document.getElementById("ckdValue").value;
        // var refrence_no = document.getElementById("refrence_no").value;
        // var chk = document.getElementById('checkboxForActionSubmit').checked ? "Y" : "N";
        var hidden_tdsTypeCode = document.getElementById("tdsType").value;
        var UIDREGEX = /^[G-H]{1}[0-9]{9}$/;
        var email = "";
        var phoneno = "";
        var address = "";
        var tin = "";
        var tinInt = "";

        var hprocrssFlag = document.getElementById("hprocrssFlag").value;
        if (hprocrssFlag === "" || hprocrssFlag === "null" || hprocrssFlag === null) {
            hprocrssFlag = "false";
        }

        try {
            email = document.getElementById("email").value;
            phoneno = document.getElementById("phoneno").value;
            address = document.getElementById("address").value;
            tin = document.getElementById("tin").value;
            tinInt = tin;
            try {
                tinInt = parseInt(tin);
            } catch (errr) {
                tinInt = 1;// set value 1 to convert tinInt to number datatype
            }
        } catch (err) {
        }
        var isCondition = checkConditionFor27QHigherDed();

        // ************************** Check Tds Amount is not greater than Transaction Amount
        var isTdsAmtIsGreater = true;

        var Temp_tran_amt = tran_amt;
        var Temp_tds_amt = tds_amt;

        var itax_rate = document.getElementById("itax_rate").value;
        tds_amt = lhsIsNull(tds_amt) ? "0" : tds_amt;

        if (lhsIsNull(Temp_tran_amt)) {
            isTdsAmtIsGreater = true;
            addActionError("error", "Transaction Amount Cannot be left blank");
            document.getElementById("tranAmt").focus();

//    } else if (lhsIsNull(itax_rate)) {
//        isTdsAmtIsGreater = true;
//        addActionError("message","Tax Rate Cannot be left blank");
//        document.getElementById("itax_rate").focus();

        } else if (lhsIsNull(Temp_tds_amt)) {
            isTdsAmtIsGreater = true;
            addActionError("error", "TDS Amount Cannot be left blank");
            document.getElementById("totalTdsAmt").focus();

        } else {
            tds_amt = parseFloat(tds_amt);
            tran_amt = parseFloat(tran_amt);
            if (tran_amt >= tds_amt) {
                isTdsAmtIsGreater = false;
                mapTDSChallanAmt = lhsIsNull(mapTDSChallanAmt) ? '0' : mapTDSChallanAmt;
                mapAllocatedAmount = lhsIsNull(mapAllocatedAmount) ? '0' : mapAllocatedAmount;
                mapTDSChallanAmt = parseFloat(mapTDSChallanAmt);
                mapAllocatedAmount = parseFloat(mapAllocatedAmount);


                var balanceAmount = document.getElementById("mapBalanceAmountId").value;
                balanceAmount = lhsIsNull(balanceAmount) ? '0' : balanceAmount;
                balanceAmount = parseFloat(balanceAmount);
                if (mapTDSChallanAmt > 0) {
                    var hidden_tds_amt = document.getElementById("hidden_tds_amt").value;
                    hidden_tds_amt = lhsIsNull(hidden_tds_amt) ? '0' : hidden_tds_amt;
                    hidden_tds_amt = parseFloat(hidden_tds_amt);
                    var balanceAmtForUpdate = balanceAmount + hidden_tds_amt;
                    if (tds_amt > balanceAmtForUpdate) {
                        addActionError("error", "TDS Amount cannot be greater than " + balanceAmtForUpdate);
                        document.getElementById("totalTdsAmt").focus();
                        isTdsAmtIsGreater = true;
                    }
                }

            } else {
                isTdsAmtIsGreater = true;
                addActionError("error", "TDS Amount is greater than or equals to Transaction Amount");
                document.getElementById("totalTdsAmt").focus();

            }
        }
        //*************************** end Check Tds Amount is not greater than Transaction Amount

        if (ID === 'null' || ID === "" || ID === null) {
            addActionError("message", "ID cannot be left blank");
            document.getElementById("rowid_seqid").focus();
            scrollPage();
        } else if (tds_section === 'null' || tds_section === "" || tds_section === null) {
            addActionError("message", "Select TDS Section");
            document.getElementById("section").focus();
            scrollPage();
        } else if (tds_deduct_date === 'null' || tds_deduct_date === "" || tds_deduct_date === null) {
            addActionError("message", "TDS Deduct Date cannot be empty");
            document.getElementById("tdsDate").focus();
            scrollPage();
        } else if ((deductee_panno === 'null' || deductee_panno === "" || deductee_panno === null)) {
            addActionError("message", "PAN Number cannot be empty");
            document.getElementById("panno").focus();
            scrollPage();
        } else if (tran_ref_date === 'null' || tran_ref_date === "" || tran_ref_date === null) {
            addActionError("message", "Transaction refrence Date cannot be empty");
            document.getElementById("tranDate").focus();
            scrollPage();
        } else if (tds_deduct_date === 'null' || tds_deduct_date === "" || tds_deduct_date === null) {
            addActionError("message", "Date cannot be left blank");
            document.getElementById("tdsDate").focus();
            scrollPage();
        } else if (tran_amt === 'null' || tran_amt === "" || tran_amt === null || tran_amt === 0) {
            addActionError("message", "Transaction Amount cannot be empty");
            document.getElementById("tranAmt").focus();
            scrollPage();
        } else if ((tds_deduct_reason === 'A') && (tds_amt === 'null' || tds_amt === "" || tds_amt === null)) {
            addActionError("message", "TDS Amount cannot be empty");
            document.getElementById("totalTdsAmt").focus();
            scrollPage();
        } else if (isTdsAmtIsGreater === true) {
            scrollPage();

        } else if (!lhsIsNull(tds_deduct_reason) && tds_deduct_reason === 'A' && lhsIsNull(certificate_no)) {
            addActionError("message", "Certificate Number cannot be empty");
            document.getElementById("certificateNo").focus();
            scrollPage();
        }
//    else if ((tds_deduct_reason === 'B' || tds_deduct_reason === 'D') && (!lhsIsNull(hidden_tdsTypeCode) && hidden_tdsTypeCode === '26Q') && (ckdValue === "0") && (chk === 'N') && (refrence_no === "" || refrence_no === "null" || refrence_no === null)) {
//        addActionError("message","Refrence No. Cannot be left blank");
//        scrollPage();
//    }
//    else if ((tds_deduct_reason === 'B' || tds_deduct_reason === 'D') && (!lhsIsNull(hidden_tdsTypeCode) && hidden_tdsTypeCode === '26Q') && (ckdValue === "0") && (chk === 'N') && (!UIDREGEX.test(refrence_no))) {
//        addActionError("message","Certificate not valid");
//        scrollPage();
//    }
        else if (isCondition && lhsIsNull(email)) {
            addActionError("message", "Email cannot be left blank");//CONDITION APPLICABLE in AY>=16,
            document.getElementById("email").focus();
            scrollPage();
        } else if (isCondition && lhsIsNull(phoneno)) {
            addActionError("message", "Phone No cannot be left blank");//CONDITION APPLICABLE in AY>=16,
            document.getElementById("phoneno").focus();
            scrollPage();
        } else if (isCondition && lhsIsNull(address)) {
            addActionError("message", "Address cannot be left blank");//CONDITION APPLICABLE in AY>=16,
            document.getElementById("address").focus();
            scrollPage();
        } else if (isCondition && (lhsIsNull(tin) || tinInt <= 0)) {// TIN NO SHOULD NOT BE ALL ZEROES
            addActionError("message", "TIN / UIN cannot be left blank");//CONDITION APPLICABLE in AY>=16,
            document.getElementById("tin").focus();
            scrollPage();
        } else if (hprocrssFlag === 'true') {//this code is used in error process bugs
            if (validateDeducteePAN(deductee_panno)) {
                if (hidden_tdsTypeCode !== "" && hidden_tdsTypeCode !== "null" && hidden_tdsTypeCode !== null && hidden_tdsTypeCode === '24Q') {
                    var l_deductee_pan_char = '';
                    if (!lhsIsNull(deductee_panno)) {
                        if (deductee_panno === 'PANNOTAVBL') {
                            l_deductee_pan_char = "P";
                        } else {
                            l_deductee_pan_char = deductee_panno.charAt(3);
                        }

                        if (l_deductee_pan_char.toUpperCase() === 'P') {
                            updateManageTdsData(field);
                        } else {
                            document.getElementById("panno").readOnly = false;
                            addActionError("message", "PAN 4th Character Should Be P Only");
                            scrollPage();
                        }
                    }
                } else {
                    updateManageTdsData(field);
                }
            }
        } else {
            updateManageTdsData(field);
        }
    } else {
        addActionError("message", "TDS Amount cannot be greater than balance amount !");
    }
}//end function

//$(function () {
//    $('#fromDate').datetimepicker({
//        format: 'DD-MM-YYYY'
//    });
//
//    $("#fromDate").on("dp.change", function (e) {
//        $('#toDate').data("DateTimePicker").minDate(e.date); //to_date date not less than from_date
//    });
//
//    $('#toDate').datetimepicker({
//        format: 'DD-MM-YYYY'
//    });
//
//});
function doOnLoad(id, btnid) {
    let qtr = document.getElementById("sessionQuarterNo").value;
    let year = document.getElementById("sessionAccYear").value;

    var myCalendar;
    myCalendar = new dhtmlXCalendarObject({input: id, button: btnid});
    myCalendar.setDateFormat("%d-%m-%Y");
    myCalendar.hideTime();

    let fromDate;
    let toDate;
    let month;
    let fromYear = year.split('-')[0], toYear = year.split('-')[1];

    if (qtr === '1') {
        month = 3;
        fromDate = '01-04-20' + fromYear;
        toDate = '30-06-20' + fromYear;
    } else if (qtr === '2') {
        month = 6;
        fromDate = '01-07-20' + fromYear;
        toDate = '30-09-20' + fromYear;
    } else if (qtr === '3') {
        month = 9;
        fromDate = '01-10-20' + fromYear;
        toDate = '31-12-20' + fromYear;
    } else if (qtr === '4') {
        month = 0;

        fromDate = '01-01-20' + toYear;
        toDate = '31-03-20' + toYear;
    }
    year = '20' + (month === 0 ? toYear : fromYear);
    myCalendar.setSensitiveRange(fromDate, toDate);
    myCalendar.showMonth(new Date(parseInt(year), month, 1));

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


function callDatePickerOnClick(id) {
//    $('#' + id).datetimepicker({
//        format: 'DD-MM-YYYY'
//    });
    $('#' + id).trigger('focus');
}

function copyDate(field, copyFldId) {
    document.getElementById(copyFldId).value = field.value;
}//end method

function OnloadPanVarification() {

    var cp = document.getElementById("globalcontextpath").value;
    var panno = document.getElementById("panno").value;
    if (panno !== null && panno !== "null" && panno !== "") {
        var VerifyCallingurl = cp + "/getTDSCRUDEAction?action=verifyPAN" + "&l_panno=" + encodeURIComponent(panno);
        $.ajax({
            url: VerifyCallingurl,
            context: document.body,
            success: function (result) {
                    var panno_result = result.split('#');
                    var panno_verified_status = panno_result[0];
                    var panno_verified_name = panno_result[1];
                if (result === null || result === "null" || result === "") {
                    document.getElementById("verifiedBy").value = 'Not Verified';
                } else if (result === 'W') {
                    document.getElementById("verifiedBy").value = 'Verified';
                } else if (result === 'M') {
                    document.getElementById("verifiedBy").value = 'Verified';
                } else if (result === 'S') {
                    document.getElementById("verifiedBy").value = 'Verified';
                } else if (panno_verified_status === 'Y') {
                    document.getElementById("verifiedBy").value = 'Verified';
                } else if (panno_verified_status === 'N') {
                    document.getElementById("verifiedBy").value = 'Not Verified';
                } else if (result === 'X') {
                    document.getElementById("verifiedBy").value = 'Invalid';
                } else {
                    document.getElementById("verifiedBy").value = 'Not Available';
                }
            }
        });
    }
}



function getDeducteeCode(field) {//DEDUCTEE NOT USE here we send panno
    var actionname =   document.getElementById('action').value;
    
    var panno = field.value;
     
    
    if (panno !== "" && panno !== "null" && panno !== null) {
        if(actionname === 'edit' || actionname === 'add'){
        if (lhsValidatePAN(field)) {//if PAN IS VALID

            var cp = document.getElementById("globalcontextpath").value;

            var VerifyCallingurl = cp + "/getTDSCRUDEAction?action=verifyPAN" + "&l_panno=" + encodeURIComponent(panno);
            $.ajax({
                url: VerifyCallingurl,
                context: document.body,
                success: function (result) {
                    var panno_result = result.split('#');
                    var panno_verified_status = panno_result[0];
                    var panno_verified_name = panno_result[1];

                    if (result === null || result === "null" || result === "") {
                        document.getElementById("verifiedBy").value = 'Not Verified';
                    } else if (result === 'W') {//NOT IN USE
                        document.getElementById("verifiedBy").value = 'Verified';
                    } else if (result === 'M') {
                        document.getElementById("verifiedBy").value = 'Verified';
                    } else if (result === 'S') {
                        document.getElementById("verifiedBy").value = 'Verified';
                    } else if (panno_verified_status === 'Y') {
                        document.getElementById("verifiedBy").value = 'Verified';
                          if(actionname === 'add'){
                        document.getElementById("deducteeName").value = panno_verified_name;
                    }
                    } else if (panno_verified_status === 'N') {
                        document.getElementById("verifiedBy").value = 'Not Verified';
                    } else if (result === 'X') {
                        document.getElementById("verifiedBy").value = 'Invalid';
                    } else {
                        document.getElementById("verifiedBy").value = 'Not Available';
                    }
                }
            });

        }
    }
//        setTaxRateByTdsType();
    } else {

//        addEditButtonSettings('add');
    }
}//end method

function disableRateFor24Q(tds_type_code) {
    if (tds_type_code === '24Q') {// readonly rate when 24Q
//        document.getElementById("itax_rate").readOnly = true;
        document.getElementById("tdsRate").readOnly = true;
        document.getElementById("surcharge_rate").readOnly = true;
        document.getElementById("cess_rate").readOnly = true;
    } else {
//        document.getElementById("itax_rate").readOnly = false;
        document.getElementById("tdsRate").readOnly = false;
        document.getElementById("surcharge_rate").readOnly = false;
        document.getElementById("cess_rate").readOnly = false;
    }
}//end function
function remove_comma_from_amt(id, flag) {
    disableRateFor24Q(document.getElementById("tdsType").value);
    var sourceString = document.getElementById(id).value;
    var out = sourceString.replace(/[`~!@#$%^&*()_|+\-=?;:'",<>\{\}\[\]\\\/]/gi, '');
    document.getElementById(id).value = out;
    var action = document.getElementById('action').value;
    if (action !== null && action !== "" && action !== "null" && action === 'add') {
        getTDSRate('false');
    } else {
        getAmount(document.getElementById("itax_rate"), 'totalTdsAmt');
    }
}//end function
function check_one_pan_string(id) {
    var value = id.value;
    if (value === 'PANAPPLIED') {
        return 'true';
    } else {
        return 'false';
    }
}//end function
function check_two_pan_string(id) {
    var value = id.value;
    if (value === 'PANNOTAVBL') {
        return 'true';
    } else {
        return 'false';
    }
}//end function
function check_three_pan_string(id) {
    var value = id.value;
    if (value === 'PANINVALID') {
        return 'true';
    } else {
        return 'false';
    }
}//end function

function validatePAN(field) {
    var checkbox = document.getElementById("checkbox").checked;
    if (checkbox === false) {
        var value = field.value;
        if (value !== "" && value !== "null" && value !== null) {
//            var Exp = /^([a-zA-Z]){3}(a|A|p|P|f|F|c|C|h|H|t|T){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
            var Exp = /^([a-zA-Z]){3}(a|A|b|B|c|C|f|F|g|G|h|H|j|J|k|K|l|L|p|P|t|T){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
            var str = check_one_pan_string(field);
            var str2 = check_two_pan_string(field);
            var str3 = check_three_pan_string(field);
            if (!Exp.test(value)) {
                if (str === 'true') {
                    return true;
                }
                if (str2 === 'true') {
                    return true;
                }
                if (str3 === 'true') {
                    return true;
                }
                    addActionError("error", "Invalid PAN Number");
                    field.focus();
                    return false;
            } else {
                removeError();
                return true;
            }
        } else {
            removeError();
            return false;
        }
    }
}//end function

function callEntryPage() {
    var cp = $("#globalcontextpath").val();
    window.location = cp + "/tdsTransactionEntry?action=add";

}
function callBrowsePage() {
    var cp = $("#globalcontextpath").val();
    window.location = cp + "/tdsTransaction";

}


function changeTdsDeductReason(action) {
//    document.getElementById("certificate_no").value = "";
    var e = document.getElementById("section");
    var strValue = e.options[e.selectedIndex].text;
    var section_code = "";
    if (strValue !== "" && strValue !== "null" && strValue !== null) {
        var split_strValue = strValue.split("(");
        section_code = split_strValue[0];
    }
    var hTdsDeductReason = document.getElementById("hTdsDeductReason").value;
    var cp = document.getElementById("globalcontextpath").value;
    var callingurl = cp + "/getDataAsAjaxAction?action=getTdsDeductReasonFlag" + "&tdsCode=" + encodeURIComponent(section_code);
    $.ajax({
        url: callingurl,
        context: document.body,
        success: function (result) {
            var callingurl = cp + "/getDataAsAjaxAction?action=getDeductReason" + "&tdsCodeStr=" + encodeURIComponent(result) + "&HTdsDeductReason=" + encodeURIComponent(hTdsDeductReason);
            $.ajax({
                url: callingurl,
                context: document.body,
                success: function (result) {
                    document.getElementById("deductReason").innerHTML = result;
                    var checkVal = false;
                    try {
                        checkVal = document.getElementById("checkbox").checked;
                    } catch (err) {
                    }
                    if (checkVal) {
                        document.getElementById("deductReason").value = 'C';
                    }
                    if (action === 'edit') {
                        onchangeDeducteReasonWhileEdit(document.getElementById("deductReason"));
                    }
                }
            });
        }
    });
//    $("#certificate_Label").css("display", "none");
//    $("#certificate_txtbox").css("display", "none");

}//end function


function scrollPage() {
    $("html, body").animate({
        scrollTop: 50
    }, 900);
}//end function
function onchangeDeducteReasonNew(field) {
    // alert("fcvdsg1111111");
    var reason = field.value.trim();
    var deducte_date = document.getElementById("tdsDate").value;
    var deductee_name = document.getElementById("deducteeName").value;
    var deductee_panno = document.getElementById("panno").value;
    var tdsCode = document.getElementById("section").value;
    var tran_amt = document.getElementById("tranAmt").value;
    var flag = false;
    if (lhsIsNull(tdsCode)) {
        addActionError("message", "Please Select TDS Section");
        document.getElementById("section").focus();
        scrollPage();
    } else if (lhsIsNull(deducte_date)) {
        addActionError("message", "Please Enter TDS Deduct Date");
        document.getElementById("tdsDate").focus();
        scrollPage();
    } else if (lhsIsNull(deductee_name)) {
        addActionError("message", "Please Enter Deductee Name");
        document.getElementById("deducteeName").focus();
        scrollPage();
    } else if (lhsIsNull(deductee_panno)) {
        addActionError("message", "Please Enter Pan Number");
        document.getElementById("panno").focus();
        scrollPage();
    } else if (lhsIsNull(tran_amt)) {
        addActionError("message", "Please Enter Transaction Amount");
        document.getElementById("tranAmt").focus();
        scrollPage();
    } else if (!lhsIsNull(reason)) {
        var deductee_panno;
        try {
            var deductee_panno = document.getElementById("panno").value;
        } catch (err) {
            deductee_panno = "";
        }
        if (deductee_panno !== null && deductee_panno !== "" && deductee_panno !== "null" && deductee_panno !== 'PANNOTAVBL' && reason === 'C') {
            document.getElementById("deductReason").value = "";
            showCerfificate(false);

        } else {
            flag = true;
            removeError();
            if (reason === 'A') {
// fetch certificate number

                showCerfificate(true);

                var certificateNo = $("#hiddenCertif").val();
                var tdsDeductReason = $("#deductReason").val();
                if (tdsDeductReason === 'A') {
                    $("#certificateNo").val(certificateNo);
                }
                var actionName = document.getElementById("action").value;
                if (actionName === 'add') {
                    fetchDetailsFromSplclRateMast();
                    var certificateHidden = $("#hiddenCertif").val();
                    if (!lhsIsNull(certificateHidden)) {

                        document.getElementById("addTdsSpclRate").onclick = "";
                        document.getElementById("addTdsSpclRate").style.cursor = "not-allowed";
                        document.getElementById("addTdsSpclRate").title = "You Can't Add Certificate No. Since It Already Exists "


                    }
                }
            } else if (reason !== 'A') {
                if (reason !== 'C') {
                    document.getElementById("itax_rate").value = "0";
                }
                showCerfificate(false);

                document.getElementById("certificateNo").value = "";
                document.getElementById("tdsRate").value = "";
                document.getElementById("certificateNo").placeholder = "Certificate No";
                document.getElementById("certificateNo").title = "";
                try {
                    document.getElementById("addTdsSpclRate").removeAttribute("href");
                    document.getElementById("addTdsSpclRate").title = "";
                } catch (err) {

                }
            }

            // when B,Y,T,S,Z then Tax Rate Should be Zero
            if (reason === 'B' || reason === 'Y' || reason === 'T' || reason === 'S' || reason === 'Z') {
                //document.getElementById("itax_rate").value = "0";
                validateTaxRate("itax_rate");
                document.getElementById("itax_rate").readOnly = true;
//                flag = false;
            } else {
                document.getElementById("itax_rate").readOnly = false;
            }
            if (reason === 'B' || reason === 'D') {
                showCerfificate(false);
            }
        }
    } else {
//        alert("in outer else condition");
        if (reason === "" || reason === null || reason === "null" || reason === "C") {
            showCerfificate(false);

        }
    }
    if (!flag) {
        field.value = "";

//////   when not A
        showCerfificate(false);
        try {
            document.getElementById("addTdsSpclRate").removeAttribute("href");
            document.getElementById("addTdsSpclRate").title = "";
        } catch (err) {

        }
    }

    getCheckBoxOnDropdownChangeNew(field);
    // setTaxRateByTdsType();

}//end method

function changeRateWhenPanNotAvailable(flag) {// when deduct reason is higher deduction--'C'
    if (flag) {
//        document.getElementById("suggested_tds_rate").value = "20";
        document.getElementById("tdsRate").value = "20"; //original

        document.getElementById("tranAmt").setAttribute('onblur', "remove_comma_from_amt(this.id, false);");
        document.getElementById("tranAmt").setAttribute('onkeypress', " return validateNumber(event);remove_comma_from_amt(this.id, false);");
        document.getElementById("tdsDate").setAttribute('onblur', "validateDate(this);");
        document.getElementById("section").setAttribute('onchange', "getTDSRate('false'); changeTdsDeductReason();");
    } else {
//        document.getElementById("suggested_tds_rate").value = "";
        document.getElementById("tdsRate").value = ""; //original

        document.getElementById("tranAmt").setAttribute('onblur', "remove_comma_from_amt(this.id, true);");
        document.getElementById("tranAmt").setAttribute('onkeypress', "return lhsIsNumber(event);remove_comma_from_amt(this.id, true);");
//        document.getElementById("tds_deduct_date").setAttribute('onblur', "validateDate(this); getTDSRate('false');");
        document.getElementById("tdsDate").setAttribute('onblur', "validateDate(this);");
        document.getElementById("section").setAttribute('onchange', "getTDSRate('false'); changeTdsDeductReason();");
//        getTDSRate('false');
    }
    getAmount(document.getElementById("itax_rate"), 'totalTdsAmt');
}//end function




function remove_single_zero(id) {
    var val = document.getElementById(id).value;
    if (val === "0") {
        document.getElementById(id).value = '';
    }
}//end function

function getPanNotAvailble() {

    var checkbox = document.getElementById("checkbox").checked;
    var le_value = "PANNOTAVBL";
//    alert("Check :" + checkbox);
    if (checkbox === true) {
        document.getElementById("panno").value = le_value;
        document.getElementById("panno").readOnly = true;
        $("#tranAmt").val("");
        // set deduct reason as 'C'
        var e = document.getElementById("section");
        var strValue = e.options[e.selectedIndex].text;
        var section_code = "";
        if (strValue !== "" && strValue !== "null" && strValue !== null) {
            var split_strValue = strValue.split("(");
            section_code = split_strValue[0];
        }
        if (section_code !== '194LC') { // 25-01-2016
            document.getElementById("deductReason").value = 'C';
            document.getElementById("deductReason").style.pointerEvents = "none";
            changeRateWhenPanNotAvailable(true);
        } else {
            changeRateWhenPanNotAvailable(false);
        }
    }

    if (checkbox === false) {
//        alert("checkbox === false");
        document.getElementById("panno").value = "";
        document.getElementById("panno").readOnly = false;
        document.getElementById("panno").setAttribute("onblur", "validatePAN(this); getDeducteeCode(this);");

        // remove deduct reason as 'C'
        document.getElementById("deductReason").value = '';
        document.getElementById("deductReason").style.pointerEvents = "all";
        changeRateWhenPanNotAvailable(false);
    }
// call function to enable btn 'create deductee'
//    addEditButtonSettings('add');
//    setDeducteeType(document.getElementById("deductee_panno").value);
//****   onchangeDeducteReason(document.getElementById("deductReason"));  ----  don't delete this comment---bhawna
    showCerfificate(false);
    try {
        document.getElementById("addTdsSpclRate").removeAttribute("href");
        document.getElementById("addTdsSpclRate").title = "";
    } catch (err) {

    }
    // cal function to set mandatory fields in 27Q  when higher deduction
//    setMandatoryWhenPanNotAvblAnd27Q();
    //
//    setTaxRateByTdsType();
}//end function

function getCheckBoxOnDropdownChangeNew(field) {

    var reason = field.value.trim();
    var hidden_tdsTypeCode = document.getElementById("tdsType").value;
    var certificateNo = document.getElementById("hiddenCertif").value;
    var tdsDeductReson = document.getElementById("hTdsDeductReason").value;
    if ((reason === 'B' || reason === 'D') && !lhsIsNull(hidden_tdsTypeCode) && hidden_tdsTypeCode === '26Q')
    {

        show15GHBloack(true);
        //getGeneratedRefranceNo();
//            alert("tdsDeductReson===="+tdsDeductReson);
//            alert("reason===="+reason);
        if (tdsDeductReson === 'D' && reason === 'D') {
            // alert("inside====");
            document.getElementById("refrence_no").value = certificateNo;
        } else if (tdsDeductReson === 'B' && reason === 'B') {
            document.getElementById("refrence_no").value = certificateNo;
        } else {
            document.getElementById("refrence_no").value = "";
        }
    } else {

        show15GHBloack(false);
    }
}//end function
function showCerfificate(flag) {
    if (flag) {
        $("#certificateDiv").css("display", "block");
        $("#certificateNo").css("display", "block");
    } else {
        $("#certificateDiv").css("display", "none");
        $("#certificateNo").css("display", "none");
    }
}
function show15GHBloack(flag) {
    if (flag) {
        $("#refrence_no").css("display", "block");
    } else {
        $("#refrence_no").css("display", "none");
    }
}
function checkConditionFor27QHigherDed() {
    var returnValue = false;
    try {
        var hiddenAccYear = document.getElementById("accYear").value;
        var rate_type = document.getElementById("rate_type").value;
        var deductee_panno = document.getElementById("deductee_panno").value;
        var itax_rate = document.getElementById("itax_rate").value;
        var hidden_tdsTypeCode = document.getElementById("tdsType").value;
        var hiddenAccYearInt = !lhsIsNull(hiddenAccYear) ? parseInt(hiddenAccYear) : 0;
        var itax_rate_int = !lhsIsNull(itax_rate) ? parseFloat(itax_rate) : 0;
        if (hidden_tdsTypeCode === '27Q' &&
                !lhsIsNull(hiddenAccYear) &&
                hiddenAccYearInt >= 16 &&
                !lhsIsNull(deductee_panno) &&
                (deductee_panno === 'PANNOTAVBL' ||
                        deductee_panno === 'pannotavbl' ||
                        deductee_panno === 'PANINVALID' ||
                        deductee_panno === 'paninvalid' ||
                        deductee_panno === 'PANAPPLIED' ||
                        deductee_panno === 'panapplied') &&
                itax_rate_int < 20 &&
                (!lhsIsNull(rate_type) && rate_type === 'D')) {// other 2 conditions will be true by default
            returnValue = true;
        }
    } catch (err) {
    }
    return returnValue;
}// end method
function saveTDSContentData(field) {
    var tds_section = document.getElementById("section").value;
    var tran_ref_date = document.getElementById("tranDate").value;
    var tds_deduct_date = document.getElementById("tdsDate").value;
    var deductee_panno = document.getElementById("panno").value;
    var deductee_name = document.getElementById("deducteeName").value;

    var tran_amt = document.getElementById("tranAmt").value;
    var checkbox = document.getElementById("checkbox").checked;
    var tds_deduct_reason = document.getElementById("deductReason").value;
    var certificate_no = document.getElementById("certificateNo").value;
    var tds_amt = document.getElementById("totalTdsAmt").value;
    var mapFlag = document.getElementById("mapFlag").value;
    var mapTDSChallanAmt = document.getElementById("mapTDSChallanAmt").value;
    var mapAllocatedAmount = document.getElementById("mapAllocatedAmount").value;

    var refrence_no = document.getElementById("refrence_no").value;
    var l_tds_type_code = document.getElementById("tdsType").value;
    // var chk = document.getElementById('checkboxForActionSubmit').checked ? "Y" : "N";
    var hidden_tdsTypeCode = document.getElementById("tdsType").value;
    var UIDREGEX = /^[G-H]{1}[0-9]{9}$/;
    var email = "";
    var phoneno = "";
    var address = "";
    var tin = "";
    var tinInt = "";
    try {
        email = document.getElementById("email").value;
        phoneno = document.getElementById("phoneno").value;
        address = document.getElementById("address").value;
        tin = document.getElementById("tin").value;
        tinInt = tin;
        try {
            tinInt = parseInt(tin);
        } catch (errr) {
            tinInt = 1;// set value 1 to convert tinInt to number datatype
        }
    } catch (err) {
    }
    var isCondition = checkConditionFor27QHigherDed();

    if (tds_section === 'null' || tds_section === "" || tds_section === null) {
        addActionError("message", "Select TDS Section");
        document.getElementById("section").focus();
    } else if ((deductee_panno === 'null' || deductee_panno === "" || deductee_panno === null) && checkbox === false) {
        addActionError("message", "PAN number cannot be empty");
        document.getElementById("panno").focus();
        scrollPage();
    } else if (deductee_name === 'null' || deductee_name === "" || deductee_name === null) {
        addActionError("message", "Deductee Name cannot be empty");
        document.getElementById("deducteeName").focus();
        scrollPage();
    } else if (tran_ref_date === 'null' || tran_ref_date === "" || tran_ref_date === null) {
        addActionError("message", "Transaction refrence date cannot be empty");
        document.getElementById("tranDate").focus();
        scrollPage();
    } else if (tran_amt === 'null' || tran_amt === "" || tran_amt === null || tran_amt === 0) {
        addActionError("message", "Transaction Amount cannot be empty");
        document.getElementById("tranAmt").focus();
        scrollPage();
    } else if (tds_deduct_date === 'null' || tds_deduct_date === "" || tds_deduct_date === null) {
        addActionError("message", "TDS Deduct Date cannot be empty");
        document.getElementById("tdsDate").focus();
        scrollPage();
    } else if (tds_deduct_reason === 'A' && lhsIsNull(tds_amt)) {
        addActionError("message", "TDS Amount Cannot be empty");
        document.getElementById("totalTdsAmt").focus();
        scrollPage();
    } else if (!validateTranAmount(document.getElementById('tranAmt'))) {
        addActionError("message", "Transaction Amount is not Valid");
        document.getElementById("tranAmt").focus();
        scrollPage();
    }
//    else if ((tds_deduct_reason === 'B' || tds_deduct_reason === 'D') && (!lhsIsNull(hidden_tdsTypeCode) && hidden_tdsTypeCode === '26Q') && (ckdValue === "0") && (chk === 'N') && (refrence_no === "" || refrence_no === "null" || refrence_no === null)) {
//        addActionError("message","Refrence No. Cannot be left blank");//CAN BE BLANK  APPLICABLE FROM 01 OCT 2015
//        scrollPage();
//    }
//    else if ((refrence_no !== "" && refrence_no !== 'null' && refrence_no !== null)) {
//        if ((!UIDREGEX.test(refrence_no))) {
//            addActionError("message","Certificate not valid");
//            scrollPage();
//        }
//    }
    else if (mapFlag !== "" && mapFlag !== "null" && mapFlag !== null && mapFlag === 'MAPTDSAMT') {

        if (mapTDSChallanAmt === "" || mapTDSChallanAmt === "null" || mapTDSChallanAmt === null) {
            mapTDSChallanAmt = '0';
        }
        if (mapAllocatedAmount === "" || mapAllocatedAmount === "null" || mapAllocatedAmount === null) {
            mapAllocatedAmount = '0';
        }

        mapTDSChallanAmt = parseFloat(mapTDSChallanAmt);
        mapAllocatedAmount = parseFloat(mapAllocatedAmount);
        var Temp_tran_amt = tran_amt;
        var Temp_tds_amt = tds_amt;
        tran_amt = parseFloat(tran_amt);

        var balanceAmount = document.getElementById("mapBalanceAmountId").value;
        balanceAmount = parseFloat(balanceAmount).toFixed(1);

        var itax_rate = document.getElementById("itax_rate").value;

        tds_amt = lhsIsNull(tds_amt) ? "0" : tds_amt;
        tds_amt = parseFloat(tds_amt);
        if (tds_amt > balanceAmount) {
            addActionError("message", "Amount Should Be less then or equal to " + balanceAmount);
            document.getElementById("totalTdsAmt").focus();
            scrollPage();
        } else {
//            deductee_type = lhsIsNull(deductee_type) ? "P" : deductee_type;
//            if (lhsIsNull(deductee_type)) {
//                if (deductee_panno === 'PANNOTAVBL') {
//                    deductee_type = "P";
//                } else {
//                    deductee_type = deductee_panno.charAt(3);
//                }
//            }
            if (lhsIsNull(Temp_tran_amt)) {
                addActionError("message", "Transaction Amount Cannot be left blank");
                document.getElementById("tranAmt").focus();
                scrollPage();
//            } else if (lhsIsNull(itax_rate)) {
//                addActionError("message","Tax Rate Cannot be left blank");
//                document.getElementById("itax_rate").focus();
//                scrollPage();
            } else if (lhsIsNull(Temp_tds_amt)) {
                addActionError("message", "TDS Amount Cannot be left blank");
                document.getElementById("totalTdsAmt").focus();
                scrollPage();
            } else {
                if (tran_amt >= tds_amt) {
                    if (l_tds_type_code !== "" && l_tds_type_code !== "null" && l_tds_type_code !== null && l_tds_type_code === '24Q') {
                        var l_deductee_pan_char = '';
                        if (!lhsIsNull(deductee_panno)) {
                            if (deductee_panno === 'PANNOTAVBL') {
                                l_deductee_pan_char = "P";
                            } else {
                                l_deductee_pan_char = deductee_panno.charAt(3);
                            }
                        }

                        if (l_deductee_pan_char.toUpperCase() === 'P') {
                            callSaveFunction(field);
                        } else {
                            document.getElementById("panno").readOnly = false;
                            addActionError("message", "PAN 4th Character Should Be P Only");
                            document.getElementById("panno").focus();
                            scrollPage();
                        }
                    } else {
                        callSaveFunction(field);
                    }
                } else {
                    addActionError("message", "TDS Amount is greater than or equals to Transaction Amount");
                    document.getElementById("totalTdsAmt").focus();
                    scrollPage();
                }
            }

        }
    } else if (!lhsIsNull(tds_deduct_reason) && tds_deduct_reason === 'A' && lhsIsNull(certificate_no)) {
        addActionError("message", "Certificate No cannot be left blank");//CONDITION APPLICABLE ONLY FROM  01 APR 2013 ONWARDS,NO REQUIRED BEFORE THIS
        document.getElementById("certificateNo").focus();
        scrollPage();
    } else if (isCondition && lhsIsNull(email)) {
        addActionError("message", "Email cannot be left blank");//CONDITION APPLICABLE in AY>=16,
        document.getElementById("email").focus();
        scrollPage();
    } else if (isCondition && lhsIsNull(phoneno)) {
        addActionError("message", "Phone No cannot be left blank");//CONDITION APPLICABLE in AY>=16,
        document.getElementById("phoneno").focus();
        scrollPage();
    } else if (isCondition && lhsIsNull(address)) {
        addActionError("message", "Address cannot be left blank");//CONDITION APPLICABLE in AY>=16,
        document.getElementById("address").focus();
        scrollPage();
    } else if (isCondition && (lhsIsNull(tin) || tinInt <= 0)) {
        addActionError("message", "TIN / UIN cannot be left blank");//CONDITION APPLICABLE in AY>=16,
        document.getElementById("tin").focus();
        scrollPage();
    } else {
//        deductee_type = lhsIsNull(deductee_type) ? "P" : deductee_type;
//        if (lhsIsNull(deductee_type)) {
//            if (deductee_panno === 'PANNOTAVBL') {
//                deductee_type = "P";
//            } else {
//                deductee_type = deductee_panno.charAt(3);
//            }
//        }


        var Temp_tran_amt = tran_amt;
        var Temp_tds_amt = tds_amt;
        tran_amt = parseFloat(tran_amt);
        var itax_rate = document.getElementById("itax_rate").value;

        tds_amt = lhsIsNull(tds_amt) ? "0" : tds_amt;
        tds_amt = parseFloat(tds_amt);

        if (lhsIsNull(Temp_tran_amt)) {

            addActionError("message", "Transaction Amount Cannot be left blank");
            document.getElementById("tranAmt").focus();
            scrollPage();

//        } else if (lhsIsNull(itax_rate)) {
//
//            addActionError("message","Tax Rate Cannot be left blank");
//            document.getElementById("itax_rate").focus();
//            scrollPage();
        } else if (lhsIsNull(Temp_tds_amt)) {
            addActionError("message", "TDS Amount Cannot be left blank");
            document.getElementById("totalTdsAmt").focus();
            scrollPage();
        } else {
            if (tran_amt >= tds_amt) {
                if (l_tds_type_code !== "" && l_tds_type_code !== "null" && l_tds_type_code !== null && l_tds_type_code === '24Q') {
                    var l_deductee_pan_char = '';
                    if (!lhsIsNull(deductee_panno)) {
                        if (deductee_panno === 'PANNOTAVBL') {
                            l_deductee_pan_char = "P";
                        } else {
                            l_deductee_pan_char = deductee_panno.charAt(3);
                        }
                    }

                    if (l_deductee_pan_char.toUpperCase() === 'P') {
                        callSaveFunction(field);
                    } else {
                        document.getElementById("panno").readOnly = false;
                        addActionError("message", "PAN 4th Character Should Be P Only");
                        document.getElementById("panno").focus();
                        scrollPage();
                    }
                } else {
                    callSaveFunction(field);
                }
            } else {
                addActionError("message", "TDS Amount is greater than or equals to Transaction Amount");
                document.getElementById("totalTdsAmt").focus();
                scrollPage();
            }
        }
    }
}//end function
var xmlHttp = "";
var ajax_call_enabled = false;

function callSaveFunction(field) {
    var cp = document.getElementById("globalcontextpath").value;
    var mapFlag = document.getElementById("mapFlag").value;
    var mapTDSChallanAmt = document.getElementById("mapTDSChallanAmt").value;
    var mapChallanRowSeq = document.getElementById("mapChallanRowSeq").value;

    var btnId = field.id;
    if (btnId === "saveBtn1") {
        document.getElementById("saveAndContinue").value = "true";
    } else {
        document.getElementById("saveAndContinue").value = "false";
    }


    if (!ajax_call_enabled) {
        ajax_call_enabled = true;

        scrollPage();
        showProcessIndicator();

        var btnSave = document.getElementById(field.id);

        $("#tds_entry_form").submit(function (e) {
            var postData = $(this).serializeArray();
            var formURL = $(this).attr("action");
            $.ajax({
                url: formURL,
                type: "POST",
                data: postData,
                success: function (data, textStatus, jqXHR)
                {
                    ajax_call_enabled = false;
                    if (data === "success") {
                        window.location = cp + "/tdsTransaction";
                    } else if (data === "success1") {// for continue
                        if (mapFlag !== "" && mapFlag !== "null" && mapFlag !== null && mapFlag === 'MAPTDSAMT') {
                            window.location = cp + "/tdsTransactionEntry?action=add&mapFlag=MAPTDSAMT" + "&mapTDSChallanAmt=" + encodeURIComponent(mapTDSChallanAmt) + "&mapChallanRowSeq=" + encodeURIComponent(mapChallanRowSeq);
                        } else {
                            //window.location.reload();
                            window.location = cp + "/tdsTransactionEntry?action=add";
                        }
                    } else if (data === 'refDateError') {
                        addActionError("error", "Tds Deduct Date Not In This Quarter");
                        document.getElementById("tdsDate").focus();
                        enableButton(btnSave);
                        scrollPage();
                    } else if (data === 'tranrRefDateError') {
                        addActionError("error", "Transaction Date Should Be Less Than Selected Quarter");
                        document.getElementById("tranDate").focus();
                        enableButton(btnSave);
                        scrollPage();
                    } else if (data === 'tdsError') {
                        addActionError("error", "The selected TDS Section is invalid or closed.");
                        document.getElementById("section").focus();
                        enableButton(btnSave);
                        scrollPage();
                    } else {
                        addActionError("error", "Some Error Occured, Could Not Save");
                        enableButton(btnSave);
                        scrollPage();
                    }

                    hideProcessIndicator();
                },
                error: function (jqXHR, textStatus, errorThrown)
                {
                    ajax_call_enabled = false; //if fails
                    enableButton(btnSave);
                    hideProcessIndicator();
                }
            });
            e.preventDefault(); //STOP default action
//            e.unbind(); //unbind. to stop multiple form submit.
        });
        //    disableButton(btnSave);
        $("#tds_entry_form").submit(); //Submit  the FORM
    } else {
        openErrorModal("Please wait your previous request is in process.");
    }
}//end function



function updateManageTdsData(field) {
    var cp = document.getElementById("globalcontextpath").value;
    var hprocrssFlag = document.getElementById("hprocrssFlag").value;
    if (hprocrssFlag === "" || hprocrssFlag === "null" || hprocrssFlag === null) {
        hprocrssFlag = "false";
    }
    var btnUpdate = document.getElementById(field.id);
    if (!ajax_call_enabled) {
        scrollPage();
        showProcessIndicator();
        $("#tds_entry_form").submit(function (e)
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
//                            alert(data);
                            ajax_call_enabled = false;
//                            var h_deductee_name = "";
//                            var h_from_date = "";
//                            var h_deductee_panno = "";
//                            var h_to_date = "";
//                            var h_tds_section = "";
//                            var h_tdsAmountOperat = "";
//                            var h_tdsAmt_From = "";
//                            var h_tdsAmt_to = "";
//                            var corrStatus = "";
//                            try {
//                                h_deductee_name = document.getElementById("h_deductee_name").value;
//                                h_from_date = document.getElementById("h_from_date").value;
//                                h_deductee_panno = document.getElementById("h_deductee_panno").value;
//                                h_to_date = document.getElementById("h_to_date").value;
//                                h_tds_section = document.getElementById("h_tds_section").value;
//                                h_tdsAmountOperat = document.getElementById("h_tdsAmountOperat").value;
//                                h_tdsAmt_From = document.getElementById("h_tdsAmt_From").value;
//                                h_tdsAmt_to = document.getElementById("h_tdsAmt_to").value;
//                                corrStatus = document.getElementById("corrStatus").value;
//                            } catch (err) {
//                            }
                            if (data === 'success') {
                                if (hprocrssFlag === 'true') {
                                    window.parent.location.reload();
                                } else {
                                    var url = cp + "/tdsTransaction";
//?tranFltrSrch.deductee_name=" + encodeURIComponent(h_deductee_name);
//                                    url += "&tranFltrSrch.from_date=" + encodeURIComponent(h_from_date);
//                                    url += "&tranFltrSrch.deductee_panno=" + encodeURIComponent(h_deductee_panno);
//                                    url += "&tranFltrSrch.to_date=" + encodeURIComponent(h_to_date);
//                                    url += "&tranFltrSrch.tds_section=" + encodeURIComponent(h_tds_section);
//                                    url += "&tranFltrSrch.tdsAmountOperat=" + encodeURIComponent(h_tdsAmountOperat);
//                                    url += "&tranFltrSrch.tdsAmt_From=" + encodeURIComponent(h_tdsAmt_From);
//                                    url += "&tranFltrSrch.tdsAmt_to=" + encodeURIComponent(h_tdsAmt_to);
//                                    url += "&tranFltrSrch.corrStatus=" + encodeURIComponent(corrStatus);

                                    window.location = url;
                                }
                            } else if (data === 'successAlloc') {
//
                                if (hprocrssFlag === 'true') {
//
                                    window.parent.location.reload();
                                } else {
//
                                    var url = cp + "/tdsTransaction";

                                    window.location = url;
                                }
                            } else if (data === 'refDateError') {
                                addActionError("message", "Tds Deduct Date Not In This Quarter");
                                document.getElementById("tranDate").focus();
                                enableButton(btnUpdate);
                                scrollPage();
                            } else if (data === 'tranrRefDateError') {
                                addActionError("message", "Transaction Date Should Be Less Than Selected Quarter");
                                document.getElementById("tranDate").focus();
                                enableButton(btnUpdate);
                                scrollPage();
                            } else {
                                addActionError("error", data);
                                enableButton(btnUpdate);
                            }

                            hideProcessIndicator();
                        },
                        error: function (jqXHR, textStatus, errorThrown)
                        {
                            ajax_call_enabled = false; //if fails
                            enableButton(btnUpdate);
                            hideProcessIndicator();
                        }
                    });
            e.preventDefault(); //STOP default action
            e.unbind(); //unbind. to stop multiple form submit.
        });
        removeError();
        ajax_call_enabled = true;
        disableButton(btnUpdate);
        $("#tds_entry_form").submit(); //Submit  the FORM
    } else {
//            window.alert("Please wait..\nYour previous request is in progress");
        openErrorModal("Please wait..\nYour previous request is in progress");
    }
}//end function

function getTDSRate(onload) {
    var tdsCode = document.getElementById("section").value;
    var deduct_date = document.getElementById("tdsDate").value;

    var tran_Ref_date = document.getElementById("tranDate").value;
    var tran_amt = document.getElementById("tranAmt").value;
    var deductee_panno = document.getElementById("panno").value;

    var rate_type = '';
    var nature_of_remittance = '';
    var country_code = '';

    try {
        rate_type = document.getElementById("rate_type").value;
        nature_of_remittance = document.getElementById("nature_of_remittance").value;
        country_code = document.getElementById("country_code").value;
    } catch (err) {
    }

    var action = document.getElementById("action").value;
    if (action !== null && action !== "" && action !== "null" && action === 'add') {
        if (!lhsIsNull(tdsCode)) {
            if (!lhsIsNull(deduct_date)) {
//                if (!lhsIsNull(jsessionid)) {

                var cp = document.getElementById("globalcontextpath").value;
//                    var callingurl = cp + "/getTDSCRUDEAction?action=getTDSRate" + "&l_tdsCode=" + encodeURIComponent(tdsCode) + "&l_deducteeCode=" + encodeURIComponent(jsessionid) + "&l_deductDate=" + encodeURIComponent(deduct_date);
                var callingurl = cp + "/getTDSCRUDEAction?action=getTDSRate";
                callingurl += "&l_tdsCode=" + encodeURIComponent(tdsCode);
//                    callingurl += "&l_deducteeCode=" + encodeURIComponent(jsessionid);
                callingurl += "&l_deductDate=" + encodeURIComponent(deduct_date);

                callingurl += "&l_tran_Ref_date=" + encodeURIComponent(tran_Ref_date);
                callingurl += "&l_tran_amt=" + encodeURIComponent(tran_amt);
                callingurl += "&l_deductee_panno=" + encodeURIComponent(deductee_panno);
                callingurl += "&l_rate_type=" + encodeURIComponent(rate_type);
                callingurl += "&l_nature_of_remittance=" + encodeURIComponent(nature_of_remittance);
                callingurl += "&l_country_code=" + encodeURIComponent(country_code);
                $.ajax({
                    url: callingurl,
                    context: document.body,
                    success: function (result) {
                        if (result === null || result === "null" || result === "") {
                            // if result is null
                            if (lhsIsNull(onload) || onload === 'false') {
                                document.getElementById("tdsRate").value = ""; //original
                                document.getElementById("cess_rate").value = ""; //original
                                document.getElementById("surcharge_rate").value = ""; //original
                                // check if certificate_no is display then No change in Tax_Rate TextBox
                                var temp_certificate_no = document.getElementById("certificateNo").value;
                                if (lhsIsNull(temp_certificate_no)) {
                                    document.getElementById("itax_rate").value = ""; //original
                                }
                            }
                        } else {
                            var splitRes = result.split("#");
                            // check if certificate_no is display then No change in Tax_Rate TextBox
                            var temp_certificate_no = document.getElementById("certificateNo").value;
                            if (lhsIsNull(temp_certificate_no)) {
                                document.getElementById("itax_rate").value = splitRes[3];
                            }
//                                document.getElementById("itax_rate").value = splitRes[3];

                            if (lhsIsNull(onload) || onload === 'false') {
                                document.getElementById("tdsRate").value = splitRes[0]; //original
                                document.getElementById("cess_rate").value = splitRes[1]; //original
                                document.getElementById("surcharge_rate").value = splitRes[2]; //original

                                // check if certificate_no is display then No change in Tax_Rate TextBox
                                if (lhsIsNull(temp_certificate_no)) {
                                    document.getElementById("itax_rate").value = splitRes[3]; //original
                                }
                                document.getElementById("itax_rate").value = splitRes[3]; //original
                                getAmount(document.getElementById("itax_rate"), 'totalTdsAmt');
                            }
                            document.getElementById("deducteeName").placeholder = 'Deductee Name';
                            $("#deducteeName").removeClass("customfieldError");
                            // get amount
                        }
                    }
                });
//                }
            } else {
// if deduct date is blank
                document.getElementById("tdsRate").value = ""; //original
                document.getElementById("cess_rate").value = ""; //original
                document.getElementById("surcharge_rate").value = ""; //original
                // check if certificate_no is display then No change in Tax_Rate TextBox
                var temp_certificate_no = document.getElementById("certificateNo").value;
                if (lhsIsNull(temp_certificate_no)) {
                    document.getElementById("itax_rate").value = ""; //original
                }
                // get amount
                getAmount(document.getElementById("itax_rate"), 'totalTdsAmt');
            }
        }
    }
    disableRateFor24Q(document.getElementById("tdsType").value);
    //setMandatoryWhenPanNotAvblAnd27Q();
}//end method

function getAmount(field, amtFieldId) {// amount is 10% of rate
//    try {
    var action = document.getElementById("action").value;
    if (action !== null && action !== "" && action !== "null" && action !== 'edit') {
        var amt = document.getElementById("tranAmt").value;
        var rate = field.value;
        var total_result = (Check_null_NaN(parseFloat(amt)) * Check_null_NaN(parseFloat(rate))) / 100;
        if (!isNaN(total_result)) {
            var str = total_result.toFixed(2); // => '10.23'
            var number = Number(str);
//            number = Math.round(number);
//                document.getElementById(amtFieldId).value = Math.ceil(number);
            document.getElementById(amtFieldId).value = number;
            document.getElementById("tds_base_amount").value = number;
            document.getElementById("surcharge_amount").value = "0";
            document.getElementById("cess_amount").value = "0";
        }
    }
//    } catch (err) {
//        alert("1----" + err);
//    }
}//end method

function Check_null_NaN(val) {
    if (isNaN(val))
        return 0;
    else
        return val;
}//end function

function getReverseRateAmount(id) {
//    g_tds_rate = document.getElementById("itax_rate").value;
    var amt = document.getElementById("tranAmt").value;
    var tds_amt = document.getElementById(id).value;
    if (amt !== null && amt !== "" && amt !== "null" && tds_amt !== null && tds_amt !== "" && tds_amt !== "null") {
        var out = tds_amt.replace(/[`~!@#$%^&*()_|+\-=?;:'",<>\{\}\[\]\\\/]/gi, '');
        document.getElementById(id).value = out;
        tds_amt = document.getElementById(id).value;
        tds_amt = parseFloat(tds_amt);
        amt = parseFloat(amt);
//        alert(amt);
        if (amt != 0) {
            if (amt >= tds_amt) {
                var rate = (100 * Check_null_NaN(tds_amt)) / Check_null_NaN(amt);
                if (!isNaN(rate)) {
                    var str = rate.toFixed(2); // => '10.23'
                    var number = Number(str);
                    document.getElementById("itax_rate").value = number;
                    document.getElementById("totalTdsAmt").value = Math.ceil(tds_amt);
                    var action = document.getElementById("action").value;
                    if (lhsIsNull(action) || !action === 'edit') {
                        document.getElementById("tds_base_amount").value = Math.ceil(tds_amt);
                    } else {
                        document.getElementById("tds_base_amount").value = tds_amt;
                    }
                    document.getElementById("surcharge_amount").value = "0";
                    document.getElementById("cess_amount").value = "0";
                }
                removeError();
            } else {
                document.getElementById(id).value = "";
                addActionError("error", "TDS Amount Should Be Less Then Or Equals To Transaction Amount");
                document.getElementById("totalTdsAmt").focus();
                scrollPage();
            }
        } else {
            document.getElementById(id).value = "";
            addActionError("message", "Transaction Amount Cannot Be Zero(0)");
            document.getElementById("totalTdsAmt").focus();
            scrollPage();
        }
    }
}//end method

function validateTdsRateAmount(id) {
    var tran_amt = document.getElementById("tranAmt").value;
//    alert(tran_amt);
    if (tran_amt !== "" && tran_amt !== "null" && tran_amt !== null && parseFloat(tran_amt) !== 0) {
        removeError();
        var tds_base_amount = document.getElementById("tds_base_amount").value;
        var surcharge_amount = document.getElementById("surcharge_amount").value;
        var cess_amount = document.getElementById("cess_amount").value;
        var tds_out = tds_base_amount.replace(/[`~!@#$%^&*()_|+\-=?;:'",<>\{\}\[\]\\\/]/gi, '');
        var surcharge_out = surcharge_amount.replace(/[`~!@#$%^&*()_|+\-=?;:'",<>\{\}\[\]\\\/]/gi, '');
        var cess_out = cess_amount.replace(/[`~!@#$%^&*()_|+\-=?;:'",<>\{\}\[\]\\\/]/gi, '');
        document.getElementById("tds_base_amount").value = tds_out;
        document.getElementById("surcharge_amount").value = surcharge_out;
        document.getElementById("cess_amount").value = cess_out;
        var tds_type = document.getElementById("tds_base_amount").value;
        var surcharge_type = document.getElementById("surcharge_amount").value;
        var cess_type = document.getElementById("cess_amount").value;
        if (tds_type === null || tds_type === "" || tds_type === "null") {
            tds_type = '0';
        }
        if (surcharge_type === null || surcharge_type === "" || surcharge_type === "null") {
            surcharge_type = '0';
        }
        if (cess_type === null || cess_type === "" || cess_type === "null") {
            cess_type = '0';
        }
        tds_type = parseFloat(tds_type);
        surcharge_type = parseFloat(surcharge_type);
        cess_type = parseFloat(cess_type);
        
        var total = tds_type + surcharge_type + cess_type;
        if (total === "" || total === "null" || total === null) {
            total = '0';
        }
        total = parseFloat(total);
//        alert("total" + total);
        tran_amt = parseFloat(tran_amt);
        if (tran_amt >= total) {
//            document.getElementById("tds_amt").value = total.toFixed(2);
            document.getElementById("totalTdsAmt").value = Math.ceil(total);
//            document.getElementById("tds_base_amount").value = Math.ceil(total);
            var rate = (100 * total) / Check_null_NaN(tran_amt);
            if (!isNaN(rate)) {
                var str = rate.toFixed(2); // => '10.23'
                var number = Number(str);
                document.getElementById("itax_rate").value = number;
            }
            removeError();
        } else {
            addActionError("error", "TDS Amount Should Be Less Then Or Equals To Transaction Amount");
            document.getElementById(id).value = '0';
            document.getElementById(id).focus();
        }
    } else {
        addActionError("error", "Transaction amount cannot be null or zero(0)");
        document.getElementById("tds_base_amount").value = '0';
        document.getElementById("surcharge_amount").value = '0';
        document.getElementById("cess_amount").value = '0';
    }

}//end function

function fetchDetailsFromSplclRateMast() {
    var tdsCode = document.getElementById("section").value;
    var l_deductee_panno = document.getElementById("panno").value;
    if (!lhsIsNull(tdsCode)) {
//        if (!lhsIsNull(jsessionid)) {
        var cp = document.getElementById("globalcontextpath").value;
        var callingurl = cp + "/getTDSCRUDEAction?action=getCertificateNo" + "&l_tdsCode=" + encodeURIComponent(tdsCode) + "&l_deductee_panno=" + encodeURIComponent(l_deductee_panno);
//            var callingurl = cp + "/getTDSCRUDEAction?action=getCertificateNo" + "&l_deductee_panno=" + encodeURIComponent(l_deductee_panno);
        console.log('callingurl....getCertificateNo......' + callingurl);
        $.ajax({
            url: callingurl,
            context: document.body,
            success: function (result) {
                result = result.trim();
                //alert(result);
                if (!lhsIsNull(result)) {
                    //   document.getElementById("selectCertificate_no").innerHTML = result;
                    //  changeCertificate_noOnEdit();
                    var result_str = result.split('#');
                    //alert("gcdfhsgf" + result_str[0])
                    document.getElementById("certificateNo").value = result_str[0];
                    document.getElementById("hiddenCertif").value = result_str[0];
                    var certificateHidden = $("#hiddenCertif").val();
                    if (!lhsIsNull(certificateHidden)) {

                        document.getElementById("addTdsSpclRate").onclick = "";
                        document.getElementById("addTdsSpclRate").style.cursor = "not-allowed";
                        document.getElementById("addTdsSpclRate").title = "You Can't Add Certificate No. Since It Already Exists "


                    }

////                        alert("Certificate No :: " + result_str[0]
////                                + "\n Tax Rate :: " + result_str[1]);
//                        document.getElementById("tds_rate").value = result_str[1];
//                        var tempTdsSplRate = result_str[1];
//                        if (!lhsIsNull(tempTdsSplRate)) {
//                            document.getElementById("itax_rate").value = result_str[1]; //  TdsSpl Rate in Tax_rate TextBox
//                        }
//                        document.getElementById("certificate_no").placeholder = "Certificate No";
//                        document.getElementById("certificate_no").title = "Press TAB button for Auto Calculation of TDS Amount According to TDS Rate";
//                        try {
//                            document.getElementById("addTdsSpclRate").removeAttribute("href");
//                            document.getElementById("addTdsSpclRate").title = "";
//                        } catch (err) {
//
//                        }
//                        // Calculate TDS amt for TdsSpl Rate
//                        if (!lhsIsNull(tempTdsSplRate)) {
//                            getAmount(document.getElementById("itax_rate"), 'tds_amt');
//                        }
                } else {
                    document.getElementById("certificateNo").placeholder = "Please Add Special Rate For This Deductee";
                    document.getElementById("certificateNo").title = "Please Add Special Rate For This Deductee";
                    try {
                        document.getElementById("addTdsSpclRate").href = cp + "/tdsSpclRateEntry?action=add";
                        document.getElementById("addTdsSpclRate").title = "Add New TDS Special Rate";
                    } catch (err) {

                    }
                    document.getElementById("tdsRate").value = '';
                    //enable btn to jump to tds spcl rate mast
                }
            }
        });
    }
//    }
}//end function


//function setNewCertificateFields() {
function showNewCertificateFields() {
    removeErrorForSpclMast();

    var hiddenCertNo = document.getElementById("hiddenCertif").value;
    var deducteeName = document.getElementById("deducteeName").value;
    var deducteePanNo = document.getElementById("panno").value;
    var tran_amt = document.getElementById("tranAmt").value;
    var e = document.getElementById("deductReason");
    var tds_deduct_reason = e.options[e.selectedIndex].value;
    document.getElementById("fltrPanNo").value = deducteePanNo;
    document.getElementById("fltrDeducteeName").value = deducteeName;

    var btnDisabled = true;
    if (!lhsIsNull(deducteeName)) {
        if (!lhsIsNull(deducteePanNo)) {
            if (tds_deduct_reason === "A") {
                if (lhsIsNull(tran_amt)) {
                    openErrorModal("Please Enter Transaction Amount");
                    document.getElementById("tranAmt").focus();
                } else {
                    btnDisabled = false;
                }
            }
        }
    }

    if (btnDisabled) {
        try {
            $("#addTdsSpclRate").prop("disabled", true);
        } catch (err) {
        }
    } else {
        try {
            $("#addTdsSpclRate").prop("disabled", false);
        } catch (err) {
        }
        var f_t = document.getElementById("section");
        var f_tds_section = f_t.options[f_t.selectedIndex].value;

        document.getElementById("tds_code").value = f_tds_section;
        document.getElementById("hid_tds_deduct_reason").value = tds_deduct_reason;
        btnDisabled = true;

        if (!lhsIsNull(hiddenCertNo)) {

            document.getElementById("certificate_no").value = hiddenCertNo;
            showSelectedCertificateDetails(hiddenCertNo);
        }

        $("#additionalTDSInfo").modal("show");
    }
}


function allocateSingleCertificate() {
    let fltrPanNo = $("#fltrPanNo").val();
    let tds_code = $("#tds_code").val();
    let certNo = $("#certificate_no").val();
    
       
        if (certNo === 'null' || certNo === "" || certNo === null) {
        // addActionError("message", "Select certificate");
         document.getElementById("certificate_no").focus();
         }else{ 
     
            $(function () { 
                                $("#dialog-confirm_certificate_allo").dialog({
                                    resizable: false,
                                    font: 10,
                                    modal: true,
                                    buttons: {
                                        "All Transaction": function () {
                                             $(this).dialog("close");

                                    $.ajax({
                                        url: './lowDeduCertifiAllocaAJAX?action=allocateCertificate&certificateNo=' + certNo
                                                + '&deductee_panno=' + fltrPanNo
                                                + '&tds_code=' + tds_code,
                                        type: 'POST',
                                        beforeSend: function (xhr) {
                                            showProcessIndicator();
                                        },
                                        success: function (response, textStatus, jqXHR) {

                                            if (lhsIsNull(response)) {
                                                addActionErrorForSpclMast('error', 'No certificate details are available.');
                                            } else {
                                                if (response.search('success') !== -1) {
                                                    $("#certificateNo").val(certNo);
                                                    $("#additionalTDSInfo").modal("hide");
                                                    openSuccessModal('Certificate allocated successfully.');
                                                } else if (response.search('error') !== -1) {//alert(response.split('#')[1]);
                                                    addActionErrorForSpclMast('error', response.split('#')[1]);
                                //                    addActionErrorForSpclMast('error', 'Some error occurred, Could not fetch certificate details.');
                                                }
                                            }
                                        },
                                        complete: function (jqXHR, textStatus) {
                                            hideProcessIndicator();
                                        }
                                    });
    
    
              },  "Current Transaction" : function () { 
                                  $("#certificateNo").val(certNo);
                                  $("#additionalTDSInfo").modal("hide");
                                 // showCerfificate(true);
                                 //$('#deductReason').prop("selectedIndex", 0);
                                  $(this).dialog("close");
                                  openSuccessModal('Please Update Transaction.'); 
                                          
                                        }
                                    }
                                });
                            });   
    
         }
}//end

function unallocateSingleCertificate() {
    let fltrPanNo = $("#fltrPanNo").val();
    let tds_code = $("#tds_code").val();
    let certNo = $("#certificate_no").val();
    if (certNo === 'null' || certNo === "" || certNo === null) {
        // addActionError("message", "Select certificate");
         document.getElementById("certificate_no").focus();
         }else{ 
                $(function () {         
                                $("#dialog-confirm_certificate_unallo").dialog({
                                    resizable: false,
                                    font: 10,
                                    modal: true,
                                    buttons: {
                                        "All Transaction": function () {
                                             $(this).dialog("close");
                                       $.ajax({
                                            url: './lowDeduCertifiAllocaAJAX?action=unallocateCertificate&certificateNo=' + certNo
                                                    + '&deductee_panno=' + fltrPanNo
                                                    + '&tds_code=' + tds_code,
                                            type: 'POST',
                                            beforeSend: function (xhr) {
                                                //showProcessIndicator();
                                            },
                                            success: function (response, textStatus, jqXHR) {

                                                if (lhsIsNull(response)) {
                                                    addActionErrorForSpclMast('error', 'No certificate details are available.');
                                                } else {
                                                    if (response.search('success') !== -1) {
                                                        $("#certificateNo").val('');
                                                        $("#additionalTDSInfo").modal("hide");
                                                        showCerfificate(false);
                                                                       $('#deductReason').prop("selectedIndex", 0);
                                                        openSuccessModal('Certificate unallocated successfully.');
                                                    } else if (response.search('error') !== -1) {
                                                        addActionErrorForSpclMast('error', response.split('#')[1]);
                                                    }
                                                }
                                            },
                                            complete: function (jqXHR, textStatus) {
                                                //hideProcessIndicator();
                                            }
                                        });

                },  "Current Transaction" : function () { 
                                  $("#certificateNo").val("");
                                  $("#additionalTDSInfo").modal("hide");
                                  showCerfificate(false);
                                  $('#deductReason').prop("selectedIndex", 0);
                                  $(this).dialog("close");
                                  openSuccessModal('Please Update Transaction.');        
                                        }
                                    }
                                });
                            }); 
                            
    }
    
}//end

function showSelectedCertificateDetails(certNo) {
    let certDedPanno = $("#fltrPanNo").val();
    let certTdsCode = $("#tds_code").val();

    let postObj = {
        'action': 'getCertificateNoPopup',
        'tdsSplRateMast.deductee_panno': certDedPanno,
        'tdsSplRateMast.tds_code': certTdsCode,
        'tdsSplRateMast.certificate_no': certNo
    };
    $.ajax({
        url: './getTDSCRUDEAction',
        type: 'POST',
        data: postObj,
        beforeSend: function (xhr) {
            //showProcessIndicator();
        },
        success: function (response, textStatus, jqXHR) {
            if (lhsIsNull(response) || response === 'empty') {
                console.log('empty response..');
                addActionErrorForSpclMast('error', 'No certificate details are available.');
            } else if (response === 'error') {
                console.log('error response..');
                addActionErrorForSpclMast('error', 'Unable to fetch certificate details.');
            } else {
                let splRateMastRes;
                try {
                    splRateMastRes = JSON.parse(response);
                } catch (e) {
                }
                if (!lhsIsNull(splRateMastRes) && splRateMastRes.certificate_no == certNo) {
                    let from_date = new Date(splRateMastRes.from_date).toLocaleString().replaceAll('/', '-').split(',')[0];
                    from_date = from_date.split('-');
                    $("#certificate_date").val(from_date[1] + '-' + from_date[0] + '-' + from_date[2]);

                    let upto_date = new Date(splRateMastRes.certificate_valid_upto).toLocaleString().replaceAll('/', '-').split(',')[0];
                    upto_date = upto_date.split('-');
                    $("#certificate_valid_upto").val(upto_date[1] + '-' + upto_date[0] + '-' + upto_date[2]);
                    $("#tds_limit_amt").val(splRateMastRes.tds_limit_amt);
                    $("#amount_consumed").val(splRateMastRes.amount_consumed);
                    $("#tds_rate1").val(splRateMastRes.tds_rate);
                } else {
                    $("#certificate_date").val('');
                    $("#certificate_valid_upto").val('');
                    $("#tds_limit_amt").val('');
                    $("#amount_consumed").val('');
                    $("#tds_rate").val('');
                    console.log('cert number not matched..');
                    addActionErrorForSpclMast('error', 'Unable not fetch certificate details.');
                }
            }
        },
        complete: function (jqXHR, textStatus) {
            //hideProcessIndicator();
        }
    });
}//end

function saveTdsSplRateDetails() {

    var certificate_date = document.getElementById("certificate_date").value;
    var certificate_valid_upto = document.getElementById("certificate_valid_upto").value;
    var tds_limit_amt = document.getElementById("tds_limit_amt").value;
    var tds_code = document.getElementById("tds_code").value;
    var tds_rate = document.getElementById("tds_rate1").value;
    var certificate_no1 = document.getElementById("certificate_no1").value;
    var CertificateREGEX = /^[G-H]{1}[0-9]{9}$/;
    var tds_reason = document.getElementById("hid_tds_deduct_reason").value;

    if (lhsIsNull(certificate_date)) {
        addActionErrorForSpclMast("message", "Certificate Date Cannot Be Blank");
        document.getElementById("certificate_date").focus();

    } else if (lhsIsNull(certificate_valid_upto)) {
        addActionErrorForSpclMast("message", "Certificate Valid Upto Cannot Be Blank");
        document.getElementById("certificate_valid_upto").focus();

    } else if (lhsIsNull(tds_limit_amt)) {
        addActionErrorForSpclMast("message", "TDS Limit Amount Cannot Be Blank");
        document.getElementById("tds_limit_amt").focus();

    } else if (lhsIsNull(tds_rate)) {
        addActionErrorForSpclMast("message", "TDS Rate Cannot Be Blank");
        document.getElementById("tds_rate1").focus();

    } else {

        var tds_rate = lhsIsNull(tds_rate) ? "0" : tds_rate;
        tds_rate = parseFloat(tds_rate);
        var maxTDSRate = 99.99;
        if (tds_rate > maxTDSRate) {
            addActionErrorForSpclMast("error", "TDS Rate should be Between 0 to 100");
            document.getElementById("tds_rate1").focus();

        } else if (lhsIsNull(tds_code) && tds_code === "-1") {
            addActionErrorForSpclMast("Select TDS Code");
            document.getElementById("tds_code").focus();

        } else if (lhsIsNull(certificate_no1)) {
            addActionErrorForSpclMast("message", "Certificate No Cannot Be Blank");
            document.getElementById("certificate_no1").focus();
        } else {
            if (tds_reason === "A") {
                saveCertificateData();
            } else {
                if (!CertificateREGEX.test(certificate_no1)) {
                    addActionErrorForSpclMast("error", "Certificate not valid");
                    document.getElementById("certificate_no1").focus();
                } else {
                    saveCertificateData();
                }
            }
        }
    }
}


function addActionErrorForSpclMast(type, msg) {
    if (!lhsIsNull(type) && !lhsIsNull(msg)) {
        if (type === "success") {
            document.getElementById("successMsgDivSplRate").style.display = "block";
            $("#successMsgSplRate").html(msg);
            try {
                document.getElementById("errorMsgDivSplRate").style.display = "none";
                document.getElementById("notificationMsgDivSplRate").style.display = "none";
            } catch (err) {
                console.log("errr...");
            }
        } else if (type === "error") {
            document.getElementById("errorMsgDivSplRate").style.display = "block";
            $("#errorMsgSplRate").html(msg);
            try {
                document.getElementById("successMsgDivSplRate").style.display = "none";
                document.getElementById("notificationMsgDivSplRate").style.display = "none";
            } catch (err) {
//                console.log("errr...");
            }
        } else if (type === "message") {
            document.getElementById("notificationMsgDivSplRate").style.display = "block";
            $("#notificationMsgSplRate").html(msg);
            try {
                document.getElementById("successMsgDivSplRate").style.display = "none";
                document.getElementById("errorMsgDivSplRate").style.display = "none";
            } catch (err) {
                console.log("errr...");
            }
        }
    }
}

function validateCertificateNo(field) {
    var value = field.value;
    var Exp = /^([0-9]){4}([a-zA-Z]){2}([0-9]){3}([a-zA-Z]){1}?$/;
    if (!lhsIsNull(value)) {
        if (value.length == 10) {
            if (!Exp.test(value)) {
                addActionErrorForSpclMast("error", "Invalid Certificate Number : 1-4 no's, 5-6 alphabets, 7-9 no's, 10 alphabet");
                field.focus();
                return false;
            } else {
                removeErrorForSpclMast();
                return true;
            }
        } else {
            addActionErrorForSpclMast("message", "Certificate number must be of 10 digits : 1-4 no's, 5-6 alphabets, 7-9 no's, 10 alphabet");
            field.focus();
            return false;
        }
    } else {
        removeErrorForSpclMast();
    }
}// end method

function removeErrorForSpclMast() {
    try {
        document.getElementById("successMsgDivSplRate").style.display = "none";

    } catch (Err) {

    }
    try {
        document.getElementById("notificationMsgDivSplRate").style.display = "none";

    } catch (Err) {

    }
    try {
        document.getElementById("errorMsgDivSplRate").style.display = "none";

    } catch (Err) {

    }
    try {
        document.getElementById("session_notificationSplRate").style.display = "none";

    } catch (Err) {

    }
}

function saveCertificateData() {
    var cp = document.getElementById("globalcontextpath").value;
    if (!ajax_call_enabled) {
        $("#tdsSplRatePage").submit(function (e) {
            var postData = $(this).serializeArray();
            var formURL = "/saveSpecialTdsRate?action=save";
            $.ajax({
                url: cp + formURL,
                type: "POST",
                data: postData,
                success: function (data, textStatus, jqXHR)
                {
                    //data: return data from server
                    ajax_call_enabled = false;

                    var res = data.split("#");
                    if (res[0].match("success")) {

                        document.getElementById("certificateNo").value = res[1];
                        document.getElementById("itax_rate").value = res[2];
                        try {
                            getAmount(document.getElementById("itax_rate"), 'totalTdsAmt');
                            clearAddSplRateBox();
                        } catch (err) {
                            console.log(err);
                        }
                        $("#additionalTDSInfo").modal("hide");
                        openSuccessModal('Certificate added successfully.');
                    } else if (res[0].match("error")) {
                        addActionErrorForSpclMast("error", res[0]);
                    }
                },
                error: function (jqXHR, textStatus, errorThrown)
                {
                    ajax_call_enabled = false; //if fails
                }
            });
            e.preventDefault(); //STOP default action
            e.unbind(); //unbind. to stop multiple form submit.
        });
        ajax_call_enabled = true;
        $("#tdsSplRatePage").submit(); //Submit  the FORM
    } else {
        openErrorModal("Please wait..\nYour previous request is in progress");
    }
}

function clearAddSplRateBox() {
//alert("in clearAddSplRateBox");
//    document.getElementById("from_date").value = "";
    document.getElementById("fltrPanNo").value = "";
    document.getElementById("fltrDeducteeName").value = "";
    document.getElementById("tds_code").value = "";
    document.getElementById("tds_rate1").value = "";
    document.getElementById("certificate_no1").value = "";
    document.getElementById("certificate_date").value = "";
    document.getElementById("certificate_valid_upto").value = "";
    document.getElementById("tds_limit_amt").value = "";
}
// Hide the Modal 3

function get_tds_certi_from_date(id) {
    var myCalendar;
    myCalendar = new dhtmlXCalendarObject(id);
    myCalendar.setDateFormat("%d-%m-%Y");
    myCalendar.hideTime();
    var yearBegDate = document.getElementById("yearBegDate").value;
    var yearEndDate = document.getElementById("yearEndDate").value;
//    myCalendar.setSensitiveRange(yearBegDate, yearEndDate);
    var toDate = document.getElementById("certificate_valid_upto").value;
    if (!lhsIsNull(toDate)) {
//        myCalendar.setSensitiveRange(yearBegDate, toDate);
    }
}// end method

/**
 *
 * for tds special rate certificate
 */
function get_tds_certi_to_date(id) {
    var yearBegDate = document.getElementById("yearBegDate").value;
    var yearEndDate = document.getElementById("yearEndDate").value;
    var from_date = document.getElementById("certificate_date").value;
    var myCalendar;
    myCalendar = new dhtmlXCalendarObject(id);
    myCalendar.setDateFormat("%d-%m-%Y");
    myCalendar.hideTime();
//    myCalendar.setSensitiveRange(yearBegDate, yearEndDate);
    if (!lhsIsNull(from_date)) {
//        myCalendar.setSensitiveRange(from_date, yearEndDate);
    }
}// end method



function deleteTDS() {

    var rowidSeq = $("#rowidForDelete").val();
    var cp = document.getElementById("globalcontextpath").value;
//    var result = confirm("Are you sure to delete this record");
//    if (result === true) {
    document.getElementById("dialog-confirm_delete").style.display = "block";
    $(function () {
        $("#dialog-confirm_delete").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                "Ok": function () {
                    $(this).dialog("close");
                    window.location = cp + "/getTDSCRUDEAction?action=delete" + "&rowid_seq=" + encodeURIComponent(rowidSeq);
                },
                Cancel: function () {
                    $(this).dialog("close");
                }
            }
        });
    });
//    }
}//end function


function onchangeDeducteReasonWhileEdit(field) {
    //alert("cdfsf");
    var reason = field.value.trim();

    var deductee_panno = document.getElementById("panno").value;

    var flag = false;
    if (!lhsIsNull(reason)) {
        var deductee_panno;
        try {
            var deductee_panno = document.getElementById("panno").value;
        } catch (err) {
            deductee_panno = "";
        }
        if (deductee_panno !== null && deductee_panno !== "" && deductee_panno !== "null" && deductee_panno !== 'PANNOTAVBL' && reason === 'C') {
            document.getElementById("tds_deduct_reason").value = "";
            showCerfificate(false);
        } else {
            flag = true;
            removeError();
            if (reason === 'A') {
// fetch certificate number

                showCerfificate(true);
                var certificateNo = $("#hiddenCertif").val();
                var tdsDeductReason = $("#tds_deduct_reason").val();
                if (tdsDeductReason === 'A') {
                    $("#certificateNo").val(certificateNo);
                }

                //  fetchDetailsFromSplclRateMast();
            } else if (reason !== 'A') {
                showCerfificate(false);

                document.getElementById("certificateNo").value = "";
                document.getElementById("tdsRate").value = "";
                document.getElementById("certificateNo").placeholder = "Certificate No";
                document.getElementById("certificateNo").title = "";
                try {
                    document.getElementById("addTdsSpclRate").removeAttribute("href");
                    document.getElementById("addTdsSpclRate").title = "";
                } catch (err) {

                }
            }

            // when B,Y,T,S,Z then Tax Rate Should be Zero
            if (reason === 'B' || reason === 'Y' || reason === 'T' || reason === 'S' || reason === 'Z') {
                //document.getElementById("itax_rate").value = "0";
                validateTaxRate("itax_rate");
                //document.getElementById("itax_rate").readOnly = true;
//                flag = false;
            } else {
                //document.getElementById("itax_rate").readOnly = false;
            }
            if (reason === 'B' || reason === 'D') {
                showCerfificate(false);
            }
        }
    } else {
//        alert("in outer else condition");
        if (reason === "" || reason === null || reason === "null" || reason === "C") {
            showCerfificate(false);

        }
    }
    if (!flag) {
        field.value = "";
        showCerfificate(false);
//////   when not A
        document.getElementById("certificateNo").value = "";
        document.getElementById("certificateNo").placeholder = "Certificate No";
        document.getElementById("certificateNo").title = "";
        try {
            document.getElementById("addTdsSpclRate").removeAttribute("href");
            document.getElementById("addTdsSpclRate").title = "";
        } catch (err) {

        }
    }

    getCheckBoxOnDropdownChangeNew(field);
}//end method


function changeTranDateFormat() {
    var htran_ref_date = document.getElementById("htran_ref_date").value;
    var date = htran_ref_date.split("-");
    var months = ['JAN', 'FEB', 'MAR', 'APR', 'MAY', 'JUN', 'JUL', 'AUG', 'SEP', 'OCT', 'NOV', 'DEC'];
    for (var j = 0; j < months.length; j++) {
        if (date[1] === months[j]) {
            date[1] = months.indexOf(months[j]) + 1;
        }
    }
    if (date[1] < 10) {
        date[1] = '0' + date[1];
    }
    var formattedDate = date[0] + "-" + date[1] + "-" + date[2];
    document.getElementById("tranDate").value = formattedDate;


}//end function

function changeDeductDateFormat() {
    var htran_deduct_date = document.getElementById("htran_deduct_date").value;

    if (!lhsIsNull(htran_deduct_date)) {
        var deduct_date = htran_deduct_date.split("-");
        var months = ['JAN', 'FEB', 'MAR', 'APR', 'MAY', 'JUN', 'JUL', 'AUG', 'SEP', 'OCT', 'NOV', 'DEC'];
        for (var j = 0; j < months.length; j++) {
            if (deduct_date[1] === months[j]) {
                deduct_date[1] = months.indexOf(months[j]) + 1;
            }
        }
        if (deduct_date[1] < 10) {
            deduct_date[1] = '0' + deduct_date[1];
        }
        if (!lhsIsNull(deduct_date[0]) && !lhsIsNull(deduct_date[1]) && !lhsIsNull(deduct_date[2])) {
            var formattedDate = deduct_date[0] + "-" + deduct_date[1] + "-" + deduct_date[2];
            document.getElementById("tdsDate").value = formattedDate;
        }
    }
}//end function



function checkDate(id, btnId) {
    var flag = true;
    var dateCheck = document.getElementById("tranDate").value;
    var fromDateqtr = document.getElementById("yearBegDate").value;
    var toDateqtr = document.getElementById("yearEndDate").value;

    if (!lhsIsNull(fromDateqtr) && !lhsIsNull(toDateqtr)) {


        var d1 = fromDateqtr.split("-");
        var d2 = toDateqtr.split("-");
        var c = dateCheck.split("-");

        var yearD1 = parseInt(d1[2]);
        var yearD2 = parseInt(d2[2]);

        var from = new Date(parseInt(yearD1), parseInt(d1[1]) - 1, d1[0]);  // -1 because months are from 0 to 11
        var to = new Date(parseInt(yearD2), parseInt(d2[1]) - 1, d2[0]);
        //var check = new Date(c[2], parseInt(c[1]) - 1, c[0]);

        var myCalendar2 = new dhtmlXCalendarObject({input: "tranDate", button: "tranDateBtn"});
        myCalendar2.setDateFormat("%d-%m-%Y");
        myCalendar2.hideTime();
        myCalendar2.setDate(from);
        myCalendar2.setSensitiveRange(from, to);

        if (window.dhx4.isIE11) {
            let settings = {
                date_field_id: btnId,
                calendar: myCalendar2,
                left: '716px',
                right: '287px'
            };
            openGlobalCalendarForIE(settings);
        }
    } else {
        addError("Please Select Proper Date!");
    }
    return flag;
}

function validateTaxRate(id) {
    var sourceString = document.getElementById(id).value;
    var out = sourceString.replace(/[`~!@#$%^&*()_|+\-=?;:'",<>\{\}\[\]\\\/]/gi, '');
    document.getElementById(id).value = out;
    var itax_rate = document.getElementById(id).value;
    if (itax_rate !== null && itax_rate !== "" && itax_rate !== "null") {
        itax_rate = parseFloat(itax_rate);
        if (itax_rate >= 100) {
            addActionError("message", "TAX Rate Should Be Less Then 100");
            document.getElementById(id).value = "";
            document.getElementById(id).focus();
            scrollPage();
        } else {
            removeError();
            getAmount(document.getElementById("itax_rate"), 'totalTdsAmt');
        }
    }
}//end function

// global btn logic starts here


function setAllChecked(checkbox) {
    
   var recordperpage = document.getElementById("recordsPerPageSelect").value;
   
      var totalRecordsSpan = document.getElementById("totalRecordsSpan").innerHTML.trim();
  
  //if(recordperpage<=100 || totalRecordsSpan<=100){
     var isChecked2 =document.getElementById("checkAll").checked;
    var isChecked = $(checkbox).prop("checked");
    $('#table tbody:has(tr:has(td))').find('input[type="checkbox"]').prop('checked', isChecked);
    if (isChecked2) {
        singleCheckBtnDisplay(false);
        $(".action-section").show(true);
        try {
                
             document.getElementById("deleteAllBtn").style.display = "inline-block";
            } catch (err) {

            }

    } else {
        $(".action-section").hide(true);

    }
    
//  }else{
//      
//        openErrorModal('Can not delete more than 100 records');
//  }
    
    
}

function getMultipleCheckFlag(tableid) {
    var len = 0;
    try {
        var count = $('#table tbody:has(tr:has(td))').find('input[type="checkbox"]:checked');
        var len = count.length;
       

    } catch (err) {
    }
    return len;
}

function openActionDiv(id, rowid) {
    var len = getMultipleCheckFlag(id);
    if(parseInt(len) > 0 && len === 1){
        $(".action-section").show(true);
        singleCheckBtnDisplay(true);
        var splitId = id.split("~")[1];
        $("#editFormId").val("editForm~" + splitId);
        $("#viewFormId").val("viewForm~" + splitId);
        $("#rowidForDelete").val(rowid);
    }else if (len > 1){
        singleCheckBtnDisplay(false);
        $(".action-section").show(true);
        try {
             document.getElementById("deleteAllBtn").style.display = "none";   
             document.getElementById("deleteMultiple").style.display = "inline-block";
            } catch (err) {

            }
    } else {
        $(".action-section").hide(true);
    }
}


function submitForm(action) {
    if (!lhsIsNull(action)) {
        if (action === 'edit') {
            var formid = $("#editFormId").val();
            //alert(formid);
            document.getElementById(formid).submit();
        } else if (action === 'view') {
            var formid = $("#viewFormId").val();
            document.getElementById(formid).submit();

        }
    }
}


function singleCheckBtnDisplay(flag) {
    if (!lhsIsNull(flag)) {
        if (flag) {
            try {
                document.getElementById("editBtn").style.display = "inline-block";
            } catch (err) {

            }
            try {
                document.getElementById("viewBtn").style.display = "inline-block";
            } catch (err) {

            }
            try {
                document.getElementById("viewBtn").style.display = "inline-block";
            } catch (err) {

            }
            try {
                document.getElementById("deleteBtn").style.display = "inline-block";
            } catch (err) {

            }
            try {
                document.getElementById("revertBtn").style.display = "none";
            } catch (err) {

            }
            try {
                document.getElementById("approveBtn").style.display = "none";
            } catch (err) {

            }
            try {
                document.getElementById("deleteAllBtn").style.display = "none";
                
            } catch (err) {

            }
            
            try {
               
                document.getElementById("deleteMultiple").style.display = "none";
            } catch (err) {

            }

        } else {
            try {
                document.getElementById("editBtn").style.display = "none";
            } catch (err) {

            }
            try {
                document.getElementById("viewBtn").style.display = "none";
            } catch (err) {

            }
            try {
                document.getElementById("viewBtn").style.display = "none";
            } catch (err) {

            }
            try {
                document.getElementById("deleteBtn").style.display = "none";
            } catch (err) {

            }
             try {
               
                document.getElementById("deleteMultiple").style.display = "none";
            } catch (err) {

            }
            try {
                document.getElementById("revertBtn").style.display = "inline-block";
            } catch (err) {

            }
            try {
                document.getElementById("approveBtn").style.display = "inline-block";
            } catch (err) {

            }
            try {
//                document.getElementById("deleteAllBtn").style.display = "inline-block";
            } catch (err) {

            }
        }
    }
}
///global btn logic ends here


function validateFilter() {
    var panno = $("#panno").val();
    var deducteeRefNo = $("#deducteeRefNo").val();
    var accountNo = $("#accountNo").val();
    var section = $("#section").val();
    var deducteeName = $("#deducteeName").val();
    var tdsAmountOperat = $("#tdsAmountOperat").val();
    var tdsAmountFrom = $("#tdsAmountFrom").val();
    var bankBranchCode = $("#bankBranchCode").val();
    var corrStatus = $("#corrStatus").val();
    var fromDate = $("#fromDate").val();
    var toDate = $("#toDate").val();
    var allAssessment = $("#allAssessment").val();
    var tran_tds_deduct_reason = $("#tran_tds_deduct_reason").val();
    var challanBankBranchCode = $("#challanBankBranchCode").val();
    var rowIdSeq = $("#rowIdSeq").val();
    
    var flag = true;


    try {
        var assAccYear = $("#assAccYear").val();
        var assQuarter = $("#assQuarter").val();
        var assTdsType = $("#assTdsType").val();
        if (!lhsIsNull(allAssessment) && allAssessment === "T") {
            if (lhsIsNull(assAccYear) && lhsIsNull(assQuarter) && lhsIsNull(assTdsType)) {
                flag = true;
            } else {
                flag = false;
            }

        }
    } catch (err) {

    }
    if (lhsIsNull(panno) && lhsIsNull(deducteeRefNo) && lhsIsNull(accountNo) && lhsIsNull(section) && lhsIsNull(deducteeName) && lhsIsNull(tdsAmountOperat) && lhsIsNull(tdsAmountFrom)
            && lhsIsNull(bankBranchCode) && lhsIsNull(corrStatus) && lhsIsNull(fromDate) && lhsIsNull(toDate) &&
            lhsIsNull(tran_tds_deduct_reason) && lhsIsNull(challanBankBranchCode)&& lhsIsNull(rowIdSeq) && flag) {
        openErrorModal("Please select any filter to see the records !");
    }else if(!lhsIsNull(fromDate) && lhsIsNull(toDate)){
        //openErrorModal("Please select To-Date filter to see the records !" );
        openErrorModal("Please select To-Date<br>If you select From-Date" );
        //openErrorModal("To-Date is mandatory if you select From-Date !" );
    }else if(lhsIsNull(fromDate) && !lhsIsNull(toDate)){    
        //openErrorModal("Please select From-Date filter to see the records !");
        openErrorModal("Please select From-Date<br>If you select To-Date" );
        //openErrorModal("From-Date is mandatory if you select To-Date !" );
    }else {
        showRecordsPaginator();
    }
}

function checkDateBetweenTwoDates() {
    try {
        var yearEndDate = $("#yearEndDate").val();
        var yearBegDate = $("#yearBegDate").val();
        var tranDate = $("#tranDate").val();

        var value_end_date = yearEndDate !== "" && yearEndDate !== "null" && yearEndDate !== null;
        var value_beg_date = yearBegDate !== "" && yearBegDate !== "null" && yearBegDate !== null;
        var value_tran_date = tranDate !== "" && tranDate !== "null" && tranDate !== null;

        if (value_end_date && value_beg_date && value_tran_date) {

            var begDateFinal = yearBegDate.split("-")[1] + "/" + yearBegDate.split("-")[0] + "/" + yearBegDate.split("-")[2];
            var endDateFinal = yearEndDate.split("-")[1] + "/" + yearEndDate.split("-")[0] + "/" + yearEndDate.split("-")[2];
            var tranDateFinal = tranDate.split("-")[1] + "/" + tranDate.split("-")[0] + "/" + tranDate.split("-")[2];

            var checkBegDate = new Date(begDateFinal);
            var checkEndDate = new Date(endDateFinal);
            var checkTranDate = new Date(tranDateFinal);

            if (checkTranDate >= checkBegDate && checkTranDate <= checkEndDate) {
                return true;
            } else {
                $('#tranDate').val("");
                $('#tdsDate').val("");
            }
        }
    } catch (e) {
    }
}

function validateTranAmount(field) {
    var value = field.value;
    if (!lhsIsNull(value)) {
        var Exp = /^\d+(\.\d{1,2})?$/;
        if (!Exp.test(value)) {
            return false;
        } else {
            return true;
        }
    }
}


      
 function deleteAllTDS(){
  
   document.getElementById("dialog-confirm_delete_mul_tran").style.display = "block";
    $(function () {
        $("#dialog-confirm_delete_mul_tran").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                "Yes": function () {
                    $(this).dialog("close");
                     deleteAllTDSbyProcedure();
                },
                No: function () {
                    $(this).dialog("close");
                }
            }
        });
    });    
     
 }    
    
    
 function deleteAllTDSbyProcedure(){
     var getTextArea = document.getElementById("whereClause");
     var whereClause = getTextArea.value;
     document.getElementById("where_Clause").value=whereClause;
     var cp = document.getElementById("globalcontextpath").value;
     var formURL ='tdsTransaction?search=false&action=MultipleDelete';
     var totalRecordsSpan =document.getElementById("totalRecordsSpan").innerHTML;
     document.getElementById("muldelTotRec").value=totalRecordsSpan;
     var count =document.getElementById("no_del_count").value;
     var arr = [];
     var selectedRecordCount = 0;
     var count2=0;
     for(var j=1; j<=count; j++){
     if(document.getElementById("check~"+j).checked){
         var seqid = document.getElementById("checkedfordelete~"+j).value;
         arr.push(seqid);
         selectedRecordCount++;
      }else{
          count2++;
      }
    }
    document.getElementById("selectedRecordCount").value = selectedRecordCount;
    document.getElementById("seqcount").value = count2;
    document.getElementById("seqidarray").value = arr;
      
 
     var formdata = $("#transactionMgmtForm").serialize();
     //console.log('data-' + formdata);
      $.ajax({
            url: formURL,
            data:formdata,
            type: "GET",
            beforeSend: function (xhr) {
               showProcessIndicator();
            },
            success: function (response, textStatus, jqXHR) {
              if(!lhsIsNull(response)){
                var output =response.split("#");
                if (output[0] === "0") {
                    var count = output[1];
                  openSuccessModal("("+count+") Records Deleted Successfully","window.location.reload();");
               }else if (response === "error"){
                         openErrorModal("Some Error Occured.Could not delete Records","window.location.reload();");
               }else {
                         openErrorModal("Some Error Occured.Could not delete Records","window.location.reload();");
               }
               
               }else{
                    openErrorModal("Some Error Occured.Could not delete Records","window.location.reload();");
               }
//                setTimeout(() => {
//                     window.location.reload();
//                  }, 1000);
            },
            error: function (jqXHR, textStatus, errorThrown) {
            },
            complete: function (jqXHR, textStatus) {
            hideProcessIndicator();
            }
        });
    
 
 }//end


    
