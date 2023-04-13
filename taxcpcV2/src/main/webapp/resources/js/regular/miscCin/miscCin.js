/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function callEntryPage() {
    var cp = $("#globalcontextpath").val();
    window.location = cp + "/miscellaneousCIN";

}
function callBrowsePage() {
    var cp = $("#globalcontextpath").val();
    window.location = cp + "/miscellaneous";

}

function callCheckerMakerPage() {
    var cp = $("#globalcontextpath").val();
    window.location = cp + "/checkerMaker";
}

function getMonthFromYear(accYear) {
    $.ajax({
        url: "./miscellaneousCINMonth?action=getMonth" + "&accYear=" + encodeURIComponent(accYear),
        type: "GET",
        success: function (response, textStatus, jqXHR) {
            $("#monthList").html(response);
            console.log("Response----->" + response);
        },
        error: function (jqXHR, textStatus, errorThrown) {

        }
    });
    return JSON.parse(JsonData);
}


function getDefaultSection(value) {
    //alert("TDS Tyepe Code----->"+value);
//    if (value === "27Q") {
//        document.getElementById("lastDiv").style.visibility = "visible";
//        document.getElementById("bottomLine").style.visibility = "visible";
//    } else {
//        document.getElementById("lastDiv").style.visibility = "hidden";
//        document.getElementById("bottomLine").style.visibility = "hidden";
//    }

    $.ajax({
        url: "./miscellaneousCINSection?action=getSection" + "&tdsTypeCode=" + encodeURIComponent(value),
        type: "GET",
        success: function (response, textStatus, jqXHR) {
            $("#tds_code").html(response);
        },
        error: function (jqXHR, textStatus, errorThrown) {
        }
    });
}

function totalOfBasicAndGSTAmts() {
    let basic_payment_field = document.getElementById("tran_amt");
    let cgst_field = document.getElementById("cgst_amt");
    let sgst_field = document.getElementById("sgst_amt");
    let igst_field = document.getElementById("igst_amt");
    let total_amt_field = document.getElementById("total_amt");

    let basic_payment_amt = getSimpleAmount(basic_payment_field.value);
    let cgst_amt = getSimpleAmount(cgst_field.value);
    let sgst_amt = getSimpleAmount(sgst_field.value);
    let igst_amt = getSimpleAmount(igst_field.value);

    let total_amt = 0.0;
    try {
        total_amt = basic_payment_amt + cgst_amt + sgst_amt + igst_amt;
        total_amt = getAmountInINR(total_amt);

        total_amt_field.value = total_amt;

    } catch (e) {
    }
    basic_payment_field.value = (basic_payment_amt === 0) ? '' : getAmountInINR(basic_payment_amt);
    cgst_field.value = (cgst_amt === 0) ? '' : getAmountInINR(cgst_amt);
    sgst_field.value = (sgst_amt === 0) ? '' : getAmountInINR(sgst_amt);


}

function checkGSTValues(field_id) {
    let cgst_field = document.getElementById("cgst_amt");
    let sgst_field = document.getElementById("sgst_amt");
    let igst_field = document.getElementById("igst_amt");

    if (field_id === 'cgst_amt') {
        if (!lhsIsNull(cgst_field.value)) {
            sgst_field.value = cgst_field.value;

            cgst_field.readOnly = false;
            sgst_field.readOnly = false;
            igst_field.readOnly = true;
        }
    } else if (field_id === 'sgst_amt') {
        if (!lhsIsNull(sgst_field.value)) {
            cgst_field.readOnly = false;
            sgst_field.readOnly = false;
            igst_field.readOnly = true;

        } else if (!lhsIsNull(cgst_field.value)) {
            sgst_field.value = cgst_field.value;

            cgst_field.readOnly = false;
            sgst_field.readOnly = false;
            igst_field.readOnly = true;


        } else {
            cgst_field.readOnly = true;
            sgst_field.readOnly = true;
            igst_field.readOnly = false;
        }
    } else if (field_id === 'igst_amt') {
        if (!lhsIsNull(igst_field.value) && (lhsIsNull(cgst_field.value) && lhsIsNull(sgst_field.value))) {
            cgst_field.readOnly = true;
            sgst_field.readOnly = true;
            igst_field.readOnly = false;

        } else if (lhsIsNull(igst_field.value)) {

            cgst_field.readOnly = false;
            sgst_field.readOnly = false;
            igst_field.readOnly = true;


        }
    }

    totalOfBasicAndGSTAmts();
}

function validateMiscForm() {

    var tdsTypeCode = $("#tdsTypeCode").val();
    var accYr = $("#accYr").val();
    var month = $("#monthlist").val();
    var branchCode = $("#client_code").val();
    var deducteeName = $("#deductee_name").val();
    var deducteePan = $("#deductee_panno").val();
    var sectionVal = $("#tds_code").val();
    var dotOfTax = $("#tran_ref_date").val();
    var paymentAmt = $("#tran_amt").val();
    var totalTds = $("#tds_amt").val();
    var certNo = $("#certificate_no").val();
    var certRequiredFlag = $("#certRequiredFlag").val();
//    var tanNo = $("#client_tanno").val();

    var challanCode = $("#tds_challan_bank_bsr_code").val();
    var challanDate = $("#tds_error_status5").val();
    var challanNo = $("#tds_error_status6").val();
    var challanAmt = $("#tds_error_status7").val();
    var tds_error_status8 = $("#tds_error_status8").val();

    var natureRemVal = $("#nature_of_remittance").val();
    var rateTypeVal = $("#rate_type").val();
    var countryCodeVal = $("#country_code").val();
    var bglCode = $("#bglCode").val();
    var tds_deduct_reason = $("#tds_deduct_reason").val();

    if (tdsTypeCode === "") {
        addActionError("error", "Please Select Form Type");
        $("#tdsTypeCode").focus();
        return false;
    } else if (accYr === "") {
        addActionError("error", "Please Select Financial Year");
        $("#accYr").focus();
        return false;
    } else if (month === "") {
        addActionError("error", "Please Select Month");
        $("#monthlist").focus();
        return false;
    } else if (branchCode === "") {
        addActionError("error", "Please Enter Branch Code");
        $("#client_code").focus();
        return false;
    } else if (deducteeName === "") {
        addActionError("error", "Please Enter Deductee Name");
         $("#deductee_name").focus();
        return false;
    } else if (deducteePan === "") {
        addActionError("error", "Please Enter Deductee PAN");
        $("#deductee_panno").focus();
        return false;
    } 
    else if (dotOfTax === "") {
        addActionError("error", "Please Select Invoice Date");
        $("#tran_ref_date").focus();
        return false;
    }else if (sectionVal === "") {
        addActionError("error", "Please Select Section");
        $("#tds_code").focus();
        return false;
    }  else if (paymentAmt === "") {
        addActionError("error", "Please Enter Payment Amount");
        $("#tran_amt").focus();
        return false;
    } else if (totalTds === "") {
        addActionError("error", "Please Enter Total TDS");
        $("#tds_amt").focus();
        return false;
    } else if (certRequiredFlag === 'true' && lhsIsNull(certNo)) {
        addActionError("error", "Please Enter Certificate No.");
        $("#certRequiredFlag").focus();
        return false;
    }
//    else if (tanNo === "") {
//        addActionError("error", "PLease Enter TAN No.");
//        return false;
//    }

    if (lhsIsNull(bglCode)) {
        if (challanCode === "") {
            addActionError("error", "Please Enter Challan Code");
            return false;
        } else if (challanDate === "") {
            addActionError("error", "Please Select Challan Date");
            return false;
        } else if (challanNo === "") {
            addActionError("error", "Please Enter Challan No.");
            return false;
        } else if (challanAmt === "") {
            addActionError("error", "Please Enter Challan Amount");
            return false;
        } else if (tds_error_status8 === "") {
            addActionError("error", "Please Enter Challan TAN");
            return false;
        }
    }

    if (natureRemVal === "" && tdsTypeCode === "27Q") {
        addActionError("error", "Please Select Nature Of Remittance");
        return false;
    } else if (rateTypeVal === "" && tdsTypeCode === "27Q") {
        addActionError("error", "Please Select Rate Type");
        return false;
    } else if (countryCodeVal === "" && tdsTypeCode === "27Q") {
        addActionError("error", "Please Select Country");
        return false;
    } else if (!lhsIsNull(tds_deduct_reason) && (parseFloat(getSimpleAmount(totalTds)) > parseFloat(getSimpleAmount(paymentAmt)))) {
        addActionError("error", "Total Tds Amt should be less than or equal to Payment Amount!");
        return false;
    } else if (!lhsIsNull(tds_deduct_reason) && (parseFloat(getSimpleAmount(paymentAmt)) < parseFloat(getSimpleAmount(totalTds)))) {
        addActionError("error", "Payment amount should be greater than or equal to Total TDS Amt!");
        return false;
    } else {
        return true;

    }

}

function saveMiscCINDetails(entryFlag) {
    //alert('in save method');
    if (validateMiscForm()) {
        removeError();
        var formdata = $("#miscCinForm").serialize();
        console.log('data-' + formdata);
//        return;
        $.ajax({
            url: "./saveMiscellaneousCIN?action=saveMiscDetails",
            data: formdata,
            type: "GET",
            success: function (response, textStatus, jqXHR) {
                if (response === "success") {
                    if (entryFlag === 'new')
                        openSuccessModal("Transaction Saved Successfully...!", "callUrl('/miscellaneousCIN');");
                    else if (entryFlag === 'exit')
                        openSuccessModal("Transaction Saved Successfully...!", "callUrl('/miscellaneous');");
                } else {
                    openErrorModal("Some Error Occured...!");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
            }
        });
    }
}

function openTaxCalendar(id, btnid) {

//    var selectedMonth = $("#monthlist").val();
//    var selectYear = $("#sessionAccYear").val();

    var myCalendar;
    myCalendar = new dhtmlXCalendarObject({input: id, button: btnid});
    myCalendar.setDateFormat("%d-%m-%Y");
    myCalendar.hideTime();

//    var monthNumber;
//    if (selectedMonth === "JAN") {
//        monthNumber = 1;
//    } else if (selectedMonth === "FEB") {
//        monthNumber = 2;
//    } else if (selectedMonth === "MAR") {
//        monthNumber = 3;
//    } else if (selectedMonth === "APR") {
//        monthNumber = 4;
//    } else if (selectedMonth === "MAY") {
//        monthNumber = 5;
//    } else if (selectedMonth === "JUN") {
//        monthNumber = 6;
//    } else if (selectedMonth === "JUL") {
//        monthNumber = 7;
//    } else if (selectedMonth === "AUG") {
//        monthNumber = 8;
//    } else if (selectedMonth === "SEP") {
//        monthNumber = 9;
//    } else if (selectedMonth === "OCT") {
//        monthNumber = 10;
//    } else if (selectedMonth === "NOV") {
//        monthNumber = 11;
//    } else if (selectedMonth === "DEC") {
//        monthNumber = 12;
//    } else {
//        monthNumber = new Date().getMonth() + 1;
//    }

//    var fromYear;
//    var toYear;
//    var dotFromYear;
//    var dotToYear;
//    fromYear = selectYear.split("-")[0];
//    toYear = selectYear.split("-")[1];

//    var fixedIndex = "20";
//    dotFromYear = fixedIndex.concat((monthNumber === 1 || monthNumber === 2 || monthNumber === 3) ? toYear : fromYear);
//    dotToYear = fixedIndex.concat(toYear);

//    myCalendar.showMonth(new Date(dotFromYear, monthNumber - 1, 1));
    myCalendar.setSensitiveRange(null, new Date());

    if (window.dhx4.isIE11) {
        let settings = {
            date_field_id: id,
            calendar: myCalendar,
            left: '931px',
            right: '198px'
        };
        openGlobalCalendarForIE(settings);
    }
}

var lastday = function (y, m) {
    return  new Date(y, m, 0).getDate();
}

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
    // var checkbox = document.getElementById("checkbox").checked;
    //if (checkbox === false) {
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
            document.getElementById("deductee_panno").value = "";
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
    // }
}//end function

//filter
function validateMiscFilter() {
    var panno = $("#deductee_panno").val();
    var deducteeName = $("#deductee_name").val();
    var tds_code = $("#tds_code").val();
    var browse_month = $("#browse_month").val();
    var authStatusFlag = $("#authStatusFlag").val();
    //alert("TDS Code----->"+tds_code);
    if (lhsIsNull(panno) && lhsIsNull(deducteeName) && lhsIsNull(tds_code) && lhsIsNull(browse_month) && lhsIsNull(authStatusFlag)) {
        openErrorModal("Please select any filter to see the records !");
    } else {
        showRecordsPaginator();
    }
}

function deleteMiscCinRecord(rowIdSeq) {
    //alert("RowId----->"+rowIdSeq.value);
    var rowId = rowIdSeq.value;
    //var formdata = $("#miscCinForm").serialize();
    $.ajax({
        url: "./deleteMiscellaneousCIN?action=deleteMiscellaneousCIN&rowid_seq=" + encodeURIComponent(rowId),
        //data : rowIdSeq,
        type: "GET",
        success: function (response, textStatus, jqXHR) {
            //alert("Delete Result----->"+response);
            if (response === "success") {
                openSuccessModal("Record Deleted Successfully...!", "callUrl('/miscellaneous');");
            } else {
                openErrorModal("Some Error Occured...!");
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
        }
    });
}

function updateMiscCinRecord(rowIdSeq) {
    //alert('in update method');
    if (validateMiscForm()) {
        var rowId = rowIdSeq.value;
        var formdata = $("#miscCinForm").serialize();
        $.ajax({
            url: "./updateMiscellaneousCIN?action=updateMiscDetails&rowid_seq=" + encodeURIComponent(rowId),
            data: formdata,
            type: "GET",
            success: function (response, textStatus, jqXHR) {
                if (response === "success") {
                    openSuccessModal("Transaction Updated Successfully...!", "callUrl('/miscellaneous');");
                } else {
                    openErrorModal("Some Error Occured...!");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
            }
        });
    }
}

function validateTANNO(field) {
    var value = field.value;

    if (!lhsIsNull(value)) {
        var Exp = /^([a-zA-Z]){4}([0-9]){5}([a-zA-Z]){1}?$/;
        if (!Exp.test(value)) {
            addActionError('message', "Invalid TAN Number");
            document.getElementById("client_tanno").value = "";
            field.focus();
            return false;
        } else {
            removeError();
            return true;
        }
    } else {
        return false;
    }

}//end function



function validateNumber(e) {
    e = e || window.event || e.htmlEvent;
    var sss;
    if (window.event) {
        keycode = e.which ? window.event.which : window.event.keyCode;
        sss = e.which;
    }

    var value;
    if (e.which >= 48 && e.which <= 57) {
        value = true;
    } else if (e.which === 8) {

        value = true;
    } else if (e.which === 0 || e.which === 46 || e.which === 37 || e.which === 39) {

        value = true;
    } else {
        value = false;
    }
//    alert(value);
    return value;

}//end method


function goToDashboard() {
    var cp = $("#globalcontextpath").val();
    window.location = cp + "/miscellaneous";
}

function setBGLSectionRate(bglCode) {
    if (!lhsIsNull(bglCode)) {
        $.ajax({
            url: "./miscellaneousBGLSection?action=getBGLSectionRate" + "&bglCode=" + encodeURIComponent(bglCode),
            type: "GET",
            success: function (response, textStatus, jqXHR) {
                $("#tds_code").html(response);
                $("#tds_code").css('pointer-events', 'none');

                hideOrDisplayChallanDetailsDiv(lhsIsNull(bglCode));
                getTdsRateAndThresholdLimit();
            },
            error: function (jqXHR, textStatus, errorThrown) {

            }
        });
    } else {
        hideOrDisplayChallanDetailsDiv(lhsIsNull(bglCode));
        getDefaultSection($("#tdsTypeCode").val());

        $("#tds_code").css('pointer-events', 'all');
        $("#tds_code").html('<option value"">--Select Section--');

        getTdsRateAndThresholdLimit();
    }
}


function setBGLSectionBrowse(bglCode) {
    if (!lhsIsNull(bglCode)) {
        $.ajax({
            url: "./miscellaneousBGLSection?action=getBGLSectionRate" + "&bglCode=" + encodeURIComponent(bglCode),
            type: "GET",
            success: function (response, textStatus, jqXHR) {
                $("#tds_code").html(response);
                // $("#tds_code").attr('disabled', '');
            },
            error: function (jqXHR, textStatus, errorThrown) {

            }
        });
    } else {
        getDefaultSection($("#tdsTypeCode").val());
        $("#tds_code").removeAttr('disabled');
        $("#tds_code").html('<option value"">--Select Section--');
    }
}

function hideOrDisplayChallanDetailsDiv(flag) {
    try {
        if (flag)
            $('#challanDiv').css('display', 'block');
        else
            $('#challanDiv').css('display', 'none');
    } catch (e) {
    }
}

function setCertificateRequired(value) {

    if (!lhsIsNull(value)) {
        $('#certRequired').css('visibility', 'visible');
        $('#certRequiredFlag').val('true');
        $('#tds_amt').removeAttr('readonly');
        $('#tds_amt').val('');
    } else {
        $('#certRequired').css('visibility', 'hidden');
        $('#certRequiredFlag').val('false');
        $('#tds_amt').attr('readonly', '');
    }
}//end

function getRateAndTotalAmt() {

    var calculateFlag = $("#tds_applicable_flag").val();

    if (!lhsIsNull(calculateFlag) && calculateFlag === 'NO') {
        $("#tds_amt").attr('readonly', '');
        $("#tds_amt").val('');

        calculateTDSAmt();
        return;
    } else if (!lhsIsNull(calculateFlag) && calculateFlag === 'YES') {
        getTdsRateAndThresholdLimit();
    }
}//end

function getTdsRateAndThresholdLimit() {
    var tds_deduct_reason = $("#tds_deduct_reason").val();
    if (lhsIsNull(tds_deduct_reason)) {
        var panno = $("#deductee_panno").val();
        var rowid_seq = $("#rowid_seq").val();
        var tds_code = $("#tds_code").val();
        var bglCode = $("#bglCode").val();
        var tran_ref_date = $("#tran_ref_date").val();
        var cert_no = $("#certificate_no").val();
        var nature_of_rem = $("#nature_of_remittance").val();
        var country_code = $("#country_code").val();
        var total_paid_amt = $("#paid_amt").val();
        var payment_amt = $("#tran_amt").val();
        var rate_type = $("#rate_type").val();

        if (lhsIsNull(panno)) {
            openErrorModal("Please Enter Deductee PAN No.");
            return;
        } else if (lhsIsNull(tran_ref_date)) {
            openErrorModal("Please Select Date Of Tax Deduction");
            return;
        }

        var actionUrl = "./miscellaneousRateAndTotalAmt?action=getRateAndTotalAmt" +
                "&bglCode=" + encodeURIComponent(bglCode) +
                "&rowid_seq=" + encodeURIComponent(rowid_seq) +
                "&section=" + encodeURIComponent(tds_code) +
                "&cert_no=" + encodeURIComponent(cert_no) +
                "&nature_of_rem=" + encodeURIComponent(nature_of_rem) +
                "&country_code=" + encodeURIComponent(country_code) +
                "&total_paid_amt=" + encodeURIComponent(total_paid_amt) +
                "&payment_amt=" + encodeURIComponent(payment_amt) +
                "&tran_ref_date=" + encodeURIComponent(tran_ref_date) +
                "&rate_type=" + encodeURIComponent(rate_type) +
                "&panno=" + encodeURIComponent(panno);

        $.ajax({
            url: actionUrl,
            type: "GET",
            success: function (response, textStatus, jqXHR) {
                if (!response.indexOf('error') !== -1) {
                    let tds_rate = response.split('#')[0],
                            threshold_limit = response.split('#')[1],
                            tran_amt = response.split('#')[2],
                            tds_amt = response.split('#')[3];

                    tds_rate = !lhsIsNull(tds_rate) ? tds_rate : 0;
                    threshold_limit = !lhsIsNull(threshold_limit) ? threshold_limit : 0;

                    $('#tds_rate').val(getAmountInINR(tds_rate));
                    $('#threshold_limit').val(getAmountInINR(threshold_limit));
                    $('#paid_amt').val(getAmountInINR(tran_amt));
                    $('#tds_deducted').val(getAmountInINR(tds_amt));

                    //function to calculate TDS Amt.
                    calculateTDSAmt();
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {

            }
        });
    }
}//end function

function calculateTDSAmt() {
    let tds_rate = getSimpleAmount($('#tds_rate').val());
    let tds_paid_amt = getSimpleAmount($('#paid_amt').val());
    let tds_deducted = getSimpleAmount($('#tds_deducted').val());
    let payment_amt = getSimpleAmount($('#tran_amt').val());
    let tds_applicable_flag = $('#tds_applicable_flag').val();

    try {
        let actual_tds_amt = 0.0;
        let total_paid_payment_perc_amt = 0.0;

        if (lhsIsNull(tds_applicable_flag) || tds_applicable_flag === 'YES') {

            actual_tds_amt = (payment_amt / 100) * tds_rate;
            actual_tds_amt = Math.ceil(actual_tds_amt);
        } else if (tds_applicable_flag === 'NO') {
            let threshold_limit = getSimpleAmount($('#threshold_limit').val());
            let total_paid_payment_amt = tds_paid_amt + payment_amt;

            if (total_paid_payment_amt > threshold_limit) {

                total_paid_payment_perc_amt = (total_paid_payment_amt / 100) * tds_rate;

                actual_tds_amt = total_paid_payment_perc_amt - tds_deducted;
                actual_tds_amt = Math.ceil(actual_tds_amt);
            }

        }
        $('#tds_amt').val(getAmountInINR(actual_tds_amt));
        $('#tran_amt').val(getAmountInINR(payment_amt));

    } catch (e) {
    }
}//end

function getPanStatus(field) {
    if (validatePAN(field)) {
        //alert('valid panno');
        try {
            $.ajax({
                url: "./miscellaneousRateAndTotalAmt?action=getPanStatusName&panno=" + encodeURIComponent(field.value),
                type: "GET",
                success: function (response, textStatus, jqXHR) {

                    $("#pan_status").val(response);

                },
                error: function (jqXHR, textStatus, errorThrown) {

                }
            });
        } catch (e) {
        }
    }
}

function getAmountInINR(amount) {
    var finalAnt = 0.0;
    try {
        finalAnt = parseFloat(amount).toFixed(2).replace(/(\d)(?=(\d{2})+\d\.)/g, '$1,');
//        finalAnt = isNaN(finalAnt) ? 0 : finalAnt;
    } catch (e) {
        finalAnt = amount;
    }
    return finalAnt;
}// End Function

function getSimpleAmount(amount) {
    let finalAnt = 0.0;
    try {
        if (!lhsIsNull(amount) && amount.toString() !== 'NaN') {
            finalAnt = parseFloat(amount.toString().replace(/[,]/g, '')).toFixed(2);
        }
    } catch (e) {
        finalAnt = 0.0;
    }
//    console.log(finalAnt);
    return Number(finalAnt);
}// End Function







function getMiscPanNotAvailble(e) {
//    var checkbox = document.getElementById("checkbox").checked;
    var checkbox = e.target.checked;
    var le_value = "PANNOTAVBL";

    if (checkbox === true) {
        document.getElementById("deductee_panno").value = le_value;
        document.getElementById("deductee_panno").readOnly = true;
    }

    if (checkbox === false) {
        document.getElementById("deductee_panno").value = "";
        document.getElementById("deductee_panno").readOnly = false;
        document.getElementById("deductee_panno").setAttribute("onblur", "getPanStatus(this);");
    }
}//end function

function checkSelectedMonthDate(element) {
//    removeError();
//
//    let selectedMonth = document.getElementById("monthlist").value;
//
//    if (lhsIsNull(selectedMonth)) {
//        addActionError("message", "Please select Month first");
//        document.getElementById("monthlist").focus();
//        return;
//    }
//
//    var monthNumber;
//    if (selectedMonth === "JAN") {
//        monthNumber = 1;
//    } else if (selectedMonth === "FEB") {
//        monthNumber = 2;
//    } else if (selectedMonth === "MAR") {
//        monthNumber = 3;
//    } else if (selectedMonth === "APR") {
//        monthNumber = 4;
//    } else if (selectedMonth === "MAY") {
//        monthNumber = 5;
//    } else if (selectedMonth === "JUN") {
//        monthNumber = 6;
//    } else if (selectedMonth === "JUL") {
//        monthNumber = 7;
//    } else if (selectedMonth === "AUG") {
//        monthNumber = 8;
//    } else if (selectedMonth === "SEP") {
//        monthNumber = 9;
//    } else if (selectedMonth === "OCT") {
//        monthNumber = 10;
//    } else if (selectedMonth === "NOV") {
//        monthNumber = 11;
//    } else if (selectedMonth === "DEC") {
//        monthNumber = 12;
//    }
//
//    let deduct_date = document.getElementById("tran_ref_date").value;
//
//    let actionURL = "./saveMiscellaneousCIN?action=isValidDeductDate";
//
//    $.ajax({
//        url: actionURL,
//        type: "POST",
//        data: {
//            month: selectedMonth,
//            month_number: monthNumber,
//            tran_ref_date: deduct_date
//        },
//        success: function (data, textStatus, jqXHR)
//        {
////            let returnResult = false;
//            if (data === 'monthError') {
//                addActionError("error", "Tax Deduct Date Not In This Selected Month");
//                element.focus();
////                returnResult = false;
//            } else if (data === 'refDateError') {
//                addActionError("error", "Tax Deduct Date Not In This Selected Month And Entered Year");
//                element.focus();
////                returnResult = false;
//            }
////            return returnResult;
//
//        },
//        error: function (jqXHR, textStatus, errorThrown)
//        {
//            ajax_call_enabled = false; //if fails
//
//        }
//    });
}