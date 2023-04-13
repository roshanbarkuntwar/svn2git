/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onscroll = function () {
    let top_div = document.getElementById('top_div');
    if (!lhsIsNull(top_div)) {
        let div_style = top_div.style.display;

        if (div_style === 'none' && window.pageYOffset > 150) {
            top_div.style.display = 'block';
        } else if (div_style === 'block' && window.pageYOffset === 0) {
            top_div.style.display = 'none';
        }
    }
};

window.onload = function (e) {
    e.preventDefault();


    $('#salaryEntryForm').find('input[name*=amt]').on('blur', function () {
        disableValidateBtn(false);
        callValidateFunction();
    });

    let formAction = document.getElementById("formAction");
    if (!lhsIsNull(formAction) && formAction.value === 'update') {
        disableSaveBtn(true);
        disableValidateBtn(true);
        setDisableFields();

        showProcessIndicator();

        (async function () {
            let temp = await $('#salaryEntryForm').find('input[name*=amt]').get().forEach(function (elem) {
                let amt = elem.value;

                if (!lhsIsNull(amt)) {
                    amt = getAmountInINR(getSimpleAmount(amt));
                    elem.value = amt;
                }
            });
//        console.log('input values..');
        }());

        (async function () {
            let temp = await $('.show-total').get().forEach(elem => {
                let amt = elem.innerHTML;

                if (!lhsIsNull(amt)) {
                    amt = getAmountInINR(getSimpleAmount(amt));
                    elem.innerHTML = amt;
                }
            });
//        console.log('label values..');
            hideProcessIndicator();
        }());
    }
};//end function

let global_cp = $("#globalcontextpath").val();

function salaryEntryNewPage() {
    var cp = $("#globalcontextpath").val();
    window.location = cp + "/salaryEntryNew2";
}//end function

function callBrowsePage() {
    var cp = $("#globalcontextpath").val();
    window.location = cp + "/salaryBrowseNew";
}//end function

function getPanNotAvailble() {
    var checkbox = document.getElementById("checkbox").checked;
    var le_value = "PANNOTAVBL";
    if (checkbox === true) {
        document.getElementById("panno").value = le_value;
        document.getElementById("panno").readOnly = true;
    }
    if (checkbox === false) {
        document.getElementById("panno").value = "";
        document.getElementById("panno").readOnly = false;
    }
}//end function

let myCalendar;
function setFrom(id, btnid) {
    myCalendar = new dhtmlXCalendarObject({input: id, button: btnid});
    myCalendar.setDateFormat("%d-%M-%Y");
    myCalendar.hideTime();
    var fromDate = document.getElementById("yearBegDate").value;
    var toDate = document.getElementById("yearEndDate").value;
    myCalendar.setSensitiveRange(fromDate, toDate);
    //myCalendar.setInsensitiveRange(null,"2011-07-07"); // the same
}
function setTo(id, btnid) {
    myCalendar = new dhtmlXCalendarObject({input: id, button: btnid});
    myCalendar.setDateFormat("%d-%M-%Y");
    myCalendar.hideTime();
    var fromDate = document.getElementById("fromDate").value;
    var toDate = document.getElementById("yearEndDate").value;
    myCalendar.setDate(fromDate);
    myCalendar.setSensitiveRange(fromDate, toDate);
    //myCalendar.setInsensitiveRange(null,"2011-07-07"); // the same
}
function doOnLoad(id, btnid) {
    myCalendar = new dhtmlXCalendarObject({input: id, button: btnid});
    myCalendar.setDateFormat("%d-%M-%Y");
    myCalendar.hideTime();
    var yearBegDate = document.getElementById("yearBegDate").value;
    myCalendar.setDate(yearBegDate);
    $("#fromDate").blur(function () {
        myCalendar.hide();
    });
    $("#toDate").blur(function () {
        myCalendar.hide();
    });
}

function fromDateChange() {
    document.getElementById("toDate").value = "";
}

let callSalarySaveFunction = function () {
//    if (validateRequiredFields()) {
    var cp = $("#globalcontextpath").val();
    let formAction = document.getElementById("formAction").value;
//    let rowid = document.getElementById("rowid_seq").value;
    let salaryForm = document.getElementById("salaryEntryForm");
//    let formData = $(salaryForm).serializeArray();
    let formData = new FormData(salaryForm);
    let formUrl = '';

//    console.log('Form Data:');
//    for (var pair of formData.entries()) {
//        if (pair[0].includes('total'))
//            console.log(pair[0] + ' : ' + pair[1]);
//    }
//    return;
    if (formAction === 'save') {
        formUrl = cp + '/salaryEntryNew2?action=save';
        $(function () {
            $("#dialog-confirm_save").dialog({
                resizable: false,
                font: 10,
                modal: true,
                buttons: {
                    "Ok": function () {
//                        alert('save..');
                        $(this).dialog("close");
                        manageSalaryEntry();
                    },
                    "Cancel": function () {
                        $(this).dialog("close");
                        return;
                    }
                }
            });
        });
    } else if (formAction === 'update') {
        formUrl = cp + '/salaryEntryNew2?action=update';
        $(function () {
            $("#dialog-confirm_update").dialog({
                resizable: false,
                font: 10,
                modal: true,
                buttons: {
                    "Ok": function () {
//                        alert('update..');
                        $(this).dialog("close");
                        manageSalaryEntry();
                    },
                    "Cancel": function () {
                        $(this).dialog("close");
                        return;
                    }
                }
            });
        });
    }

    let manageSalaryEntry = function () {

        if (!ajax_call_enabled) {

            $.ajax({
                url: formUrl,
                type: 'POST',
                data: formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                beforeSend: function (xhr) {
                    ajax_call_enabled = true;
                    showProcessIndicator();
                },
                success: function (data, textStatus, jqXHR) {

                    if (!lhsIsNull(data)) {
                        if (data.startsWith("success")) {
                            if (formAction === 'save') {
                                let row_id_seq = data.split('#')[1];
                                openSuccessModal(`Record saved successfully.\nRowId Seq: ${row_id_seq}`, 'callBrowsePage();');
                            } else if (formAction === 'update') {
                                try {
                                    let row_id_seq = document.getElementById("rowid_seq").value;
                                    let month = document.getElementById("month").value;
                                    callDedicatedProcedure({
                                        callingProcName: "ProcSalaryTranTotalRefresh",
                                        rowIdSeq: row_id_seq,
                                        month: month,
                                        validate: "update"
                                    });
                                } catch (e) {
                                    console.log('%cprocedure calling ajax error', 'color: red;');
                                    console.log(e);
                                }
                                openSuccessModal(`Record updated successfully.`, 'callBrowsePage();');
                            }
                        } else {
                            if (formAction === 'save') {

                                openErrorModal("Please fill out the employee details", "");
                            } else if (formAction === 'update') {
                                openErrorModal("Some error occurred, record could not be updated.");

                            }
                        }
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log('Salary entry error: ', textStatus);
                    ajax_call_enabled = false;
                    hideProcessIndicator();
                },
                complete: function (jqXHR, textStatus) {
                    ajax_call_enabled = false;
                    hideProcessIndicator();
                    resetSalaryForm();
                }
            });
        } else {
            openErrorModal("Please wait.. Your previous request is in progress!");
        }
    };
//    }
};//end function

function resetSalaryForm() {
    document.getElementById("top_emp_panno").innerHTML = '';
    document.getElementById("top_emp_name").innerHTML = '';
    document.getElementById("top_emp_catg").innerHTML = '';
    window.salaryEntryForm.reset();
}//end function

function setDefaultValue(value, element, elemType) {
    if (!lhsIsNull(value) && !lhsIsNull(element)) {
        try {
            if (value.toLowerCase().includes('-select-')) {
                if (elemType === 'html')
                    element.innerHTML = '';
                else
                    element.value = '';
                return;
            }

            if (typeof element === 'object') {
                if (elemType === 'html')
                    element.innerHTML = value;
                else
                    element.value = value;
            } else if (typeof element === 'string') {
                if (elemType === 'html')
                    document.getElementById(element).innerHTML = value;
                else
                    document.getElementById(element).value = value;
            }
        } catch (e) {
        }
    }
}//end function

let convertToINR = function (amt, display_fld) {
    try {
        if (!lhsIsNull(amt)) {
            try {
                amt = amt.replace(/[,]/g, '');
            } catch (e) {
            }

            let inr_value = parseFloat(amt).toFixed(2).replace(/(\d)(?=(\d{2})+\d\.)/g, '$1,');
            display_fld.value = inr_value;
        }
    } catch (e) {
    }
};

let calculateSumOf = function (... amts) {
    let totalAmt = '';
    try {
        if (amts != null) {
            totalAmt = amts.reduce((total, amt) => +(total + amt).toFixed(2));
        }
    } catch (e) {
    }
    return totalAmt;
};

let calculateSubstractionOf = function (... amts) {
    let totalAmt = '';
    try {
        if (amts != null) {
            totalAmt = amts.reduce((total, amt) => (total - amt).toFixed(2));
        }
    } catch (e) {
    }
    return totalAmt;
};

let getTotalAmtOf = function (totalField, totalDisplayField, ... fields) {
    let totalAmt = 0.0;
    if (fields != null && fields != undefined) {
        let amtArray = [];
        let value;
        try {
            amtArray = fields.map(field => {
                if (typeof field === 'object')
                    value = field.value;
                else if (typeof field === 'string')
                    value = field;

                if (!lhsIsNull(value)) {
                    return parseFloat(value.replace(/[,]/g, ''));
                } else {
                    return 0;
                }
            });
        } catch (e) {
        }
        totalAmt = calculateSumOf(...amtArray);

        if (!isNaN(totalAmt))
            totalAmt = parseFloat(totalAmt).toFixed(2).replace(/(\d)(?=(\d{2})+\d\.)/g, '$1,');
        else
            totalAmt = 0.0;

        if (!lhsIsNull(totalField))
            setDefaultValue(totalAmt, totalField);

        if (!lhsIsNull(totalDisplayField))
            setDefaultValue(totalAmt, totalDisplayField, 'html');

    }

    return totalAmt;
};
let getSubstractAmtOf = function (totalField, totalDisplayField, ... fields) {
    let totalAmt = 0.0;
    if (fields != null && fields != undefined) {
        let amtArray = [];
        let value;
        try {
            amtArray = fields.map(field => {
                value = field.value;
                if (!lhsIsNull(value)) {
                    return parseFloat(value.replace(/[,]/g, ''));
                } else {
                    return 0;
                }
            });
        } catch (e) {
        }
        totalAmt = calculateSubstractionOf(...amtArray);

        if (!isNaN(totalAmt))
            totalAmt = parseFloat(totalAmt).toFixed(2).replace(/(\d)(?=(\d{2})+\d\.)/g, '$1,');
        else
            totalAmt = 0.0;

        if (!lhsIsNull(totalField))
            setDefaultValue(totalAmt, totalField);

        if (!lhsIsNull(totalDisplayField))
            setDefaultValue(totalAmt, totalDisplayField, 'html');

    }

    return totalAmt;
};

function disableValidateBtn(hideFlag) {
    if (hideFlag)
        document.getElementById("validateButton").disabled = "disabled";
    else {
        document.getElementById("validateButton").disabled = "";
        disableSaveBtn(true);
    }
}

function disableSaveBtn(hideFlag) {
    if (hideFlag)
        document.getElementById("saveButton").disabled = "disabled";
    else
        document.getElementById("saveButton").disabled = "";
}

function refreshSalaryData() {
    var cp = document.getElementById("globalcontextpath").value;

    var url = cp + "/salryTranLoadAjaxNew?action=refreshSalary";

    window.location = url;

}

function getAmountInINR(amount) {
    let finalAnt = 0.0;
    try {
        finalAnt = parseFloat(amount).toFixed(2).replace(/(\d)(?=(\d{2})+\d\.)/g, '$1,');
    } catch (e) {
        finalAnt = amount;
    }
    return finalAnt;
}// End Function

function getSimpleAmount(amount) {
    let finalAnt = 0.0;
    try {
        finalAnt = parseFloat(amount.toString().replace(/[,]/g, '')).toFixed(2);
    } catch (e) {
        finalAnt = amount;
    }
//    console.log(finalAnt);
    return finalAnt;
}// End Function

// Tax before Adjustments main Total
function getGroup6_total_amt(button) {
    let totalTax = document.getElementById("afg30_total_amt");
    let totalTaxMain = document.getElementById("afg30_total_amt_main");

    let afgr4_total_amt = document.getElementById("h_afgr4_total_amt").value;
    afgr4_total_amt = !lhsIsNull(afgr4_total_amt) ? getSimpleAmount(afgr4_total_amt) : 0.00;

    let emp_catg = document.getElementById("deductee_catg").value;
    let tax_115bac_flag = document.getElementById("tax_115bac_flag").value;
    let salary_to_date = document.getElementById("toDate").value;
    let deductee_panno_verified = document.getElementById("h_panno_verified").value;


    let afg30_tds_amt = document.getElementById("afg30_tds_amt");
    let afg30_sur_amt = document.getElementById("afg30_sur_amt");
    let afg30_ces_amt = document.getElementById("afg30_ces_amt");
    let afg40_itr_amt = document.getElementById("afg40_itr_amt");//Rebate amount

    let requestUrl = `/salryTranLoadAjaxNew`;

    //console.log(requestUrl);

    $.ajax({
        url: global_cp + requestUrl,
        type: 'POST',
        data: {
            action: "callTaxableAmtFunc",
            afgr4_total_amt,
            emp_catg,
            tax_115bac_flag,
            salary_to_date,
            deductee_panno_verified
        },
        beforeSend: function (xhr) {
            try {
                button.disabled = true;
            } catch (e) {
            }
            showProcessIndicator();
        },
        success: function (data, textStatus, jqXHR) {
            if (!lhsIsNull(data) && data.includes("#")) {
                // Array destructuring in js
                let [tds_amt, sur_amt, ces_amt, itr_amt] = data.split("#");
                try {
                    afg30_tds_amt.value = getAmountInINR(tds_amt);
                    afg30_sur_amt.value = getAmountInINR(sur_amt);
                    afg30_ces_amt.value = getAmountInINR(ces_amt);
                    afg40_itr_amt.value = getAmountInINR(itr_amt);
                } catch (e) {
                }

                getTotalAmtOf(totalTax, totalTaxMain, ...[afg30_tds_amt, afg30_sur_amt, afg30_ces_amt]);

                (async function () {
                    let g7 = await getGroup7_total_amt();

                    let g8 = await getGroup8_total_amt();

                    let g9 = await getGroup9_total_amt();
//                    console.log('call complete..', 7, 8, 9);
                })();
            }
        },
        error: function (jqXHR, textStatus) {
            afg30_tds_amt.value = "";
            afg30_sur_amt.value = "";
            afg30_ces_amt.value = "";
            afg40_itr_amt.value = "";
            hideProcessIndicator();
            try {
                button.disabled = false;
            } catch (e) {
            }
//            console.log('call complete..');
        },
        complete: function (jqXHR, textStatus) {
            try {
                button.disabled = false;
            } catch (e) {
            }
            hideProcessIndicator();
//            console.log('call complete..');
        }
    });

}// End Function

function getGroup1_total_amt_a(totalField, totalDisplayField, ...fields) {

    try {
        getTotalAmtOf(totalField, totalDisplayField, ...fields);
    } catch (e) {
    }


    let h_afg01_total_amt = document.getElementById("h_afg01_total_amt");
    let h_afg05_total_amt = document.getElementById("h_afg05_total_amt");
    let h_afg10_total_amt = document.getElementById("h_afg10_total_amt");

    let m_totalField = document.getElementById("h_afgr2_total_amt");
    let m_totalDisplayField = document.getElementById("afgr2_total_amt");


    getSubstractAmtOf(m_totalField, m_totalDisplayField, ...[h_afg01_total_amt, h_afg05_total_amt, h_afg10_total_amt]);

    // getGroup4_total_amt();
}// End Function

function getGroup2_total_amt(totalField, totalDisplayField, ...fields) {

    try {
        getTotalAmtOf(totalField, totalDisplayField, ...fields);
    } catch (e) {
    }

    // getGroup4_total_amt();
}// End Function

function getGroup3_total_amt(totalField, totalDisplayField, ...fields) {

    try {
        getTotalAmtOf(totalField, totalDisplayField, ...fields);
    } catch (e) {
    }

    // getGroup4_total_amt();
}// End Function

function getGroup4_total_amt() {
    let afgr2 = document.getElementById("h_afgr2_total_amt");
    let afg20 = document.getElementById("afg20_ios_amt");
    let h_afg15_total_amt = document.getElementById("h_afg15_total_amt");

    let totalField = document.getElementById("h_afgr3_total_amt");
    let totalDisplayField = document.getElementById("afgr3_total_amt");
    try {
        getTotalAmtOf(totalField, totalDisplayField, ...[afgr2, h_afg15_total_amt, afg20]);
    } catch (e) {
    }
}// End Function

function getGroup5_amt_total(type, n) {
    let totalField = null;
    let totalDisplayFieldMain = null;


    if (type === 'gross') {
        totalField = document.getElementById("gross_amt_total");
//        totalDisplayFieldMain = document.getElementById("afg25_total_gross_amt");
    } else if (type === 'deduct') {
        totalField = document.getElementById("deduct_amt_total");
        totalDisplayFieldMain = document.getElementById("afg26_total_deduct_amt");
    } else if (type === 'qualify') {
        totalField = document.getElementById("qualify_amt_total");
    } else {
        document.getElementById("gross_amt_total").value = '';
        document.getElementById("deduct_amt_total").value = '';
        document.getElementById("qualify_amt_total").value = '';
        return;
    }

    if (totalField != null) {

        let amt_fields = [];
        for (let i = 1; i <= 10; i++) {
            try {
                amt_fields[i - 1] = document.getElementById(`${type}_amt-${i}`);
            } catch (e) {
            }
        }
        let isValidAmt = false;
        try {
            amt_fields = amt_fields.filter(fld => fld != null);
//            console.log('gross array= ', amt_fields);
            getTotalAmtOf(totalField, totalDisplayFieldMain, ...amt_fields);
            isValidAmt = true;
        } catch (e) {
            console.log('error 1');
        }

        if (type === 'deduct' && isValidAmt) {
            let totalFieldMain = document.getElementById("h_afgr4_total_amt");
            let totalDisplayField = document.getElementById("afgr4_total_amt");

            let gross_total_income = document.getElementById("h_afgr3_total_amt").value;
            let gross_total = document.getElementById("deduct_amt_total").value;
//            let gross_total = document.getElementById("gross_amt_total").value;// Firstly was gross amt to calculate

            try {
                gross_total_income = gross_total_income ? parseFloat(gross_total_income.replace(/[,]/g, '')).toFixed(2) : 0.0;
                gross_total = gross_total ? parseFloat(gross_total.replace(/[,]/g, '')).toFixed(2) : 0.0;

            } catch (e) {
                gross_total_income = 0.0;
                gross_total = 0.0;
                console.log('error 2');
            }

            try {
                let total = (gross_total_income - gross_total).toFixed();
                total = total ? getAmountInINR(total) : 0.0;

                setDefaultValue(total, totalFieldMain);
                setDefaultValue(total, totalDisplayField, 'html');
            } catch (e) {
                console.log('error 3');
            }
        }



    }
}// End Function

function getGroup7_total_amt() {
    try {
        let rebate_and_relief_total_amt = document.getElementById("rebate_and_relief_total_amt"),
                afg35_rus_amt = document.getElementById("afg35_rus_amt"),
                afg40_itr_amt = document.getElementById("afg40_itr_amt");
//        getGroup7_total_amt(rebate_and_relief_total_amt, afg35_rus_amt, afg40_itr_amt);
        getTotalAmtOf("", rebate_and_relief_total_amt, ...[afg35_rus_amt, afg40_itr_amt]);
    } catch (e) {
    }
}// End Function

function getGroup8_total_amt() {
    let taxBeforeAdjustAmt = document.getElementById("afg30_total_amt").value;
    let rebateAndReliefAmt = document.getElementById("rebate_and_relief_total_amt").innerHTML;

    try {
        taxBeforeAdjustAmt = taxBeforeAdjustAmt ? getSimpleAmount(taxBeforeAdjustAmt) : 0.0;
        rebateAndReliefAmt = rebateAndReliefAmt ? getSimpleAmount(rebateAndReliefAmt) : 0.0;
    } catch (e) {
    }

    try {
        let total = (taxBeforeAdjustAmt - rebateAndReliefAmt).toFixed(2);
        total = total ? getAmountInINR(total) : 0.0;

        setDefaultValue(total, document.getElementById("h_afgr5_total_amt"));
        setDefaultValue(total, document.getElementById("afgr5_total_amt"), 'html');
    } catch (e) {
    }
}// End Function

function getGroup9_total_amt() {
    let hAndECessAmt = document.getElementById("afgr5_total_amt").innerHTML;
    try {
        let tdsForCurrEmplyr = document.getElementById("afg45_ttce_amt");
        let tdsForPrevEmplyr = document.getElementById("afg45_ttpe_amt");
        let total = getTotalAmtOf('', '', ...[tdsForCurrEmplyr, tdsForPrevEmplyr]);


        try {
            let shortfallAmt = parseFloat(getSimpleAmount(hAndECessAmt)) - parseFloat(getSimpleAmount(total));
            setDefaultValue(shortfallAmt.toFixed(2), document.getElementById("h_afg46_sftd_amt"));
            setDefaultValue(getAmountInINR(shortfallAmt.toFixed(2)), document.getElementById("afg46_sftd_amt"), 'html');
        } catch (e) {
        }
    } catch (e) {
    }

}// End Function

async function callValidateFunction(valButton) {
    if (validateRequiredFields()) {
        try {
            let h_afg01_total_amt = document.getElementById("h_afg01_total_amt"),
                    afg01_total_amt = document.getElementById("afg01_total_amt"),
                    afg01_pes_amt = document.getElementById("afg01_pes_amt"),
                    afg01_ces_amt = document.getElementById("afg01_ces_amt"),
                    afg01_vpce_amt = document.getElementById("afg01_vpce_amt"),
                    afg01_plsce_amt = document.getElementById("afg01_plsce_amt");

            await getGroup1_total_amt_a(h_afg01_total_amt, afg01_total_amt, afg01_pes_amt, afg01_ces_amt, afg01_vpce_amt, afg01_plsce_amt);
        } catch (e) {
        }

        try {
            let h_afg05_total_amt = document.getElementById("h_afg05_total_amt"),
                    afg05_total_amt = document.getElementById("afg05_total_amt"),
                    afg05_tca_amt = document.getElementById("afg05_tca_amt"),
                    afg05_drg_amt = document.getElementById("afg05_drg_amt"),
                    afg05_cvp_amt = document.getElementById("afg05_cvp_amt"),
                    afg05_celse_amt = document.getElementById("afg05_celse_amt"),
                    afg05_hra_amt = document.getElementById("afg05_hra_amt"),
                    afg05_other_amt = document.getElementById("afg05_other_amt");
            await getGroup1_total_amt_a(h_afg05_total_amt, afg05_total_amt, afg05_tca_amt, afg05_drg_amt, afg05_cvp_amt, afg05_celse_amt, afg05_hra_amt, afg05_other_amt);
        } catch (e) {
        }

        try {
            let h_afg10_total_amt = document.getElementById("h_afg10_total_amt"),
                    afg10_total_amt = document.getElementById("afg10_total_amt"),
                    afg10_pt_amt = document.getElementById("afg10_pt_amt"),
                    afg10_eag_amt = document.getElementById("afg10_eag_amt"),
                    afg10_sd_amt = document.getElementById("afg10_sd_amt");
            await getGroup1_total_amt_a(h_afg10_total_amt, afg10_total_amt, afg10_pt_amt, afg10_eag_amt, afg10_sd_amt);
        } catch (e) {
        }
//    console.log('call complete..', 1);
        try {
            let h_afg15_total_amt = document.getElementById("h_afg15_total_amt"),
                    afg15_total_amt = document.getElementById("afg15_total_amt"),
                    afg15_riot_amt = document.getElementById("afg15_riot_amt"),
                    afg15_iphl_amt = document.getElementById("afg15_iphl_amt");
            await getGroup2_total_amt(h_afg15_total_amt, afg15_total_amt, afg15_riot_amt, afg15_iphl_amt);
//        console.log('call complete..', 2);
        } catch (e) {
        }

        try {
            let afg20_ios_amt_main = document.getElementById("afg20_ios_amt_main"),
                    afg20_ios_amt = document.getElementById("afg20_ios_amt");
            await getGroup3_total_amt('', afg20_ios_amt_main, afg20_ios_amt);
//        console.log('call complete..', 3);
        } catch (e) {
        }

        try {
            await getGroup4_total_amt();
//        console.log('call complete..', 4);
        } catch (e) {
        }

        try {
            await getGroup5_amt_total('gross');

            await getGroup5_amt_total('deduct');

            await getGroup5_amt_total('qualify');
//        console.log('call complete..', 5);
        } catch (e) {
        }
        try {
            await getGroup6_total_amt();
//        console.log('call complete..', 6);
        } catch (e) {
        }

        try {
            if (!lhsIsNull(valButton)) {
                disableSaveBtn(false);
                disableValidateBtn(true);
                try {
                    let row_id_seq = document.getElementById("rowid_seq").value;
                    let month = document.getElementById("month").value;
                    callDedicatedProcedure({
                        callingProcName: "ProcSalaryTranTotalRefresh",
                        rowIdSeq: row_id_seq,
                        month: month,
                        validate: "validate"
                    });
                } catch (e) {
                    console.log('%cprocedure calling ajax error', 'color: red;');
                    console.log(e);
                }
            }
        } catch (e) {
        }
    }
}// End Function


function lhsIsNumber1(evt) {
//    var result = false;
//    var browser = checkUserBrowser();
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
//    var target = evt.target || evt.srcElement;

    if (charCode === 45) {
        return false;
    } else if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    } else {
        return true;
    }


}//end lhs_isNumber method

function expandCollapseAll(el) {
    try {
        let button = $(el).find('i');
        let buttonClass = $(button).attr('class');
        $(button).removeClass();
        if (buttonClass === 'fa fa-plus') {
            $(button).addClass('fa fa-minus');
            $('#all-section').attr('title', 'Click here to compress/collapsed all sections');
        } else {
            $(button).addClass('fa fa-plus');
            $('#all-section').attr('title', 'Click here to expand/collapse all sections');
        }

    } catch (e) {

    }
    $('.expandable').click();
}// end function

function updateStandardDeduction(button) {
    if (!ajax_call_enabled) {
        let actionUrl = `${global_cp}/salryTranLoadAjaxNew`;
        $.ajax({
            url: actionUrl,
            type: 'POST',
            data: {
                action: "call_proc_stdded_updt"
            },
            beforeSend: function (xhr) {
                showProcessIndicator();
                button.disabled = true;
                ajax_call_enabled = true;
            },
            success: function (data, textStatus, jqXHR) {
                if (!lhsIsNull(data) && data === "success") {
//                    let [procStatus, tokenNo] = data.split('#');
                    openSuccessModal(`Your request is initiated.`);
                } else {
                    openErrorModal("Some error occurred.");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
//                hideProcessIndicator();
            },
            complete: function (jqXHR, textStatus) {
                button.disabled = false;
                ajax_call_enabled = false;
                hideProcessIndicator();
            }
        });
    }
}//end function

function setDisableFields() {
    //6.  Total Taxable Income, Subject to Limits under Chapter VI-A
    document.getElementById("gross_amt_total").setAttribute("readonly", "readonly");
    document.getElementById("deduct_amt_total").setAttribute("readonly", "readonly");
    document.getElementById("qualify_amt_total").setAttribute("readonly", "readonly");

    //8. Tax before Adjustments
    document.getElementById("afg30_tds_amt").setAttribute("disabled", "readonly");
    document.getElementById("afg30_sur_amt").setAttribute("readonly", "readonly");
    document.getElementById("afg30_ces_amt").setAttribute("readonly", "readonly");

    //9. Less : Rebate and Reliefs
    document.getElementById("afg40_itr_amt").setAttribute("readonly", "readonly");
}//end function

function validateRequiredFields() {
    let deductee_panno = document.getElementById("panno").value;
    let deductee_name = document.getElementById("deductee_name").value;
    let deductee_catg = document.getElementById("deductee_catg").value;
    let tax_115bac_flag = document.getElementById("tax_115bac_flag").value;
//    let identification_code = document.getElementById("afg30_tds_amt").value;
    let salary_from_date = document.getElementById("fromDate").value;
    let salary_to_date = document.getElementById("toDate").value;

    if (lhsIsNull(deductee_panno)) {
        openErrorModal("Please enter Employee PAN.");
        return false;
    } else if (lhsIsNull(deductee_name)) {
        openErrorModal("Please enter Employee Name");
        return false;
    } else if (lhsIsNull(deductee_catg)) {
        openErrorModal("Please select Employee Category");
        return false;
    } else if (lhsIsNull(tax_115bac_flag)) {
        openErrorModal("Please select Whether opting for taxation u/s 115BAC [Yes/No]");
        return false;
    } else if (lhsIsNull(salary_from_date)) {
        openErrorModal("Please select Salary From Date");
        return false;
    } else if (lhsIsNull(salary_to_date)) {
        openErrorModal("Please select Salary To Date");
        return false;
    }
    return true;
}// end function

function get_verified_panno_status(field) {//DEDUCTEE NOT USE here we send panno
    var panno = field.value;
    if (!lhsIsNull(panno)) {
        if (validatePANSalaryNew(field)) {//if PAN IS VALID
            var VerifyCallingurl = global_cp + "/getTDSCRUDEAction?action=verifyPAN" + "&l_panno=" + encodeURIComponent(panno);
            $.ajax({
                url: VerifyCallingurl,
                success: function (result) {
                    if (!lhsIsNull(result)) {
                        if (result.split("#")[0].trim().toUpperCase() === 'Y') {
                            document.getElementById("panno_verified").value = 'Yes';
                            document.getElementById("h_panno_verified").value = 'Y';
                            document.getElementById("deductee_name").value = result.split("#")[1].trim();
                        } else if (result.split("#")[0].trim().toUpperCase() === 'N') {
                            document.getElementById("panno_verified").value = 'No';
                            document.getElementById("h_panno_verified").value = 'N';
                        } else if (result.split("#")[0].trim().toUpperCase() === 'X') {
                            document.getElementById("panno_verified").value = 'No';
                            document.getElementById("h_panno_verified").value = 'N';
                        }


                        document.getElementById("panno_valid").value = 'Yes';
                        document.getElementById("h_panno_valid").value = 'Y';
                        setDefaultValue(field.value, document.getElementById("top_emp_panno"), 'html');
                    } else {
                        document.getElementById("panno_verified").value = '';
                        document.getElementById("h_panno_verified").value = '';
                    }
//                    if (result === null || result === "null" || result === "") {
//                        document.getElementById("verifiedBy").value = 'Not Verified';
//                    } else if (result === 'W') {//NOT IN USE
//                        document.getElementById("verifiedBy").value = 'Verified';
//                    } else if (result === 'M') {
//                        document.getElementById("verifiedBy").value = 'Verified';
//                    } else if (result === 'S') {
//                        document.getElementById("verifiedBy").value = 'Verified';
//                    } else if (result === 'Y') {
//                        document.getElementById("verifiedBy").value = 'Verified';
//                    } else if (result === 'N') {
//                        document.getElementById("verifiedBy").value = 'Not Verified';
//                    } else if (result === 'X') {
//                        document.getElementById("verifiedBy").value = 'Invalid';
//                    } else {
//                        document.getElementById("verifiedBy").value = 'Not Available';
//                    }
                }
            });
        } else {
            document.getElementById("panno_valid").value = 'No';
            document.getElementById("h_panno_valid").value = 'N';

            document.getElementById("panno_verified").value = '';
            document.getElementById("h_panno_verified").value = '';
        }
    }
}//end function

function validatePANSalaryNew(field) {

    let value = field.value;
    let tdsType = $("#tdsType").val();
    let quarter = $("#quarter").val();
    let Exp;
    if (!lhsIsNull(value)) {
        Exp = /^([a-zA-Z]){3}(a|A|b|B|c|C|f|F|g|G|h|H|j|J|k|K|l|L|p|P|t|T){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
        if (Exp.test(value)) {
            if (tdsType === '24Q' && quarter === '4') {
                Exp = /^([a-zA-Z]){3}(p|P){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
            }
            if (!Exp.test(value) && tdsType === '24Q' && quarter === '4') {
                addActionError("message", "PAN 4th character should be 'P' only");
                field.focus();
                return false;
            } else {
                removeError();
                return true;
            }
        } else {
            addActionError("message", "Invalid PAN");
            return false;
        }
    } else {
        removeError();
        return false;
    }
}//end function

function deleteSalaryEntry(salary_rowid, deductee_code) {
    if (!lhsIsNull(salary_rowid)) {
        document.getElementById("dialog-confirm_delete").style.display = "block";
        $(function () {
            $("#dialog-confirm_delete").dialog({
                resizable: false,
                font: 10,
                modal: true,
                buttons: {
                    "Ok": function () {
                        $(this).dialog("close");
                        deleteSalaryDetails();
                    },
                    Cancel: function () {
                        $(this).dialog("close");
                        return;
                    }
                }
            });
        });

        function deleteSalaryDetails() {
            let actionUrl = global_cp + '/salryTranLoadAjaxNew';
            $.ajax({
                url: actionUrl,
                type: 'POST',
                data: {
                    action: "deleteSalaryFromBrowse",
                    rowidSeq: salary_rowid,
                    deductee_code: deductee_code
                },
                beforeSend: function (xhr) {
                    showProcessIndicator();
                },
                success: function (data, textStatus, jqXHR) {
                    if (!lhsIsNull(data) && data === "true") {
                        openSuccessModal('Record deleted successfully', 'showRecordsPaginator();');
                    } else {
                        openErrorModal("Some error occurred. Couldn't delete record");
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    hideProcessIndicator();
                },
                complete: function (jqXHR, textStatus) {
                    hideProcessIndicator();
                }
            });
        }
    }
}

function reCalculateTds(button) {
    if (!ajax_call_enabled) {
        let actionUrl = `${global_cp}/salryTranLoadAjaxNew`;
        $.ajax({
            url: actionUrl,
            type: 'POST',
            data: {
                action: "reCalculateTds"
            },
            beforeSend: function (xhr) {
                showProcessIndicator();
                button.disabled = true;
                ajax_call_enabled = true;
            },
            success: function (data, textStatus, jqXHR) {
                if (!lhsIsNull(data) && data === "success") {
//                    let [procStatus, tokenNo] = data.split('#');
                    openSuccessModal(`Your request is initiated.`);
                } else {
//                    openErrorModal("Some error occurred.");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
//                hideProcessIndicator();
            },
            complete: function (jqXHR, textStatus) {
                button.disabled = false;
                ajax_call_enabled = false;
                hideProcessIndicator();
            }
        });
    }

}
