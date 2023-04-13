var ajax_call_enabled = false;
function openActionDiv(id, rowid) {
    var len = getMultipleCheckFlag(id);
    if (parseInt(len) > 0 && len === 1) {
        $(".action-section").show(true);
        singleCheckBtnDisplay(true);


        var splitId = id.split("~")[1];
        $("#editFormId").val("editForm~" + splitId);
        $("#viewFormId").val("viewForm~" + splitId);
        $("#addFormId").val("addForm~" + splitId);
        $("#deleteFormId").val("deleteForm~" + splitId);
        $("#rowidForDelete").val(rowid);



    } else if (len > 1) {
        singleCheckBtnDisplay(false);
        $(".action-section").show(true);
    } else {
        $(".action-section").hide(true);
    }
}


function getMultipleCheckFlag(tableid) {
    var len = 0;
    try {
        var count = $('#table tbody:has(tr:has(td))').find('input[type="checkbox"]:checked');
        len = count.length;

    } catch (err) {
    }
    return len;
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
                document.getElementById("revertBtn").style.display = "inline-block";
            } catch (err) {

            }
            try {
                document.getElementById("approveBtn").style.display = "inline-block";
            } catch (err) {

            }
            try {
                document.getElementById("deleteAllBtn").style.display = "inline-block";
            } catch (err) {

            }
        }
    }
}

function submitChallanForm(action) {
    if (!lhsIsNull(action)) {
        if (action === 'edit') {
            var formid = $("#editFormId").val();
            document.getElementById(formid).submit();
        } else if (action === 'view') {
            var formid = $("#viewFormId").val();
            document.getElementById(formid).submit();
        } else if (action === 'add') {
            var formid = $("#addFormId").val();
            document.getElementById(formid).submit();
        }
    }
}

function getPanNotAvailble() {
    // alert("iN pAN nOT available");
    var checkbox = document.getElementById("checkbox").checked;
    var tds_type_code = document.getElementById("tds_type_code").value;
    var le_value = "PANNOTAVBL";
    if (checkbox === true) {
        document.getElementById("panno").value = le_value;
        document.getElementById("panno").readOnly = true;
        if (tds_type_code !== "" && tds_type_code !== "null" && tds_type_code !== null && tds_type_code !== '24Q') {
            try {

            } catch (err) {
            }
        }

        removeError();
    }
    if (checkbox === false) {
        document.getElementById("panno").value = "";
        document.getElementById("panno").readOnly = false;
        if (tds_type_code !== "" && tds_type_code !== "null" && tds_type_code !== null && tds_type_code !== '24Q') {
            try {

            } catch (err) {
            }
        }

    }
}//end function


function isPANverified(panno) {
    var cp = document.getElementById("contextPath").value;
    var VerifyCallingurl = cp + "/getDeducteeCRUDEAction15gh?action=verifyPAN" + "&panNoVal=" + encodeURIComponent(panno);
    $.ajax({
        url: VerifyCallingurl,
        context: document.body,
        success: function (result) {
            if (document.getElementById("verifiedBy") !== null) {
//                alert(result);
                if (result === null || result === "null" || result === "") {
                    document.getElementById("verifiedBy").value = 'Not Verified';
                } else if (result === 'W') {
                    document.getElementById("verifiedBy").value = 'Verified';
                } else if (result === 'M') {
                    document.getElementById("verifiedBy").value = 'Verified';
                } else if (result === 'S') {
                    document.getElementById("verifiedBy").value = 'Verified';
                } else if (result === 'N') {
                    document.getElementById("verifiedBy").value = 'Invalid/PAN Not Exist';
                } else {
                    document.getElementById("verifiedBy").value = 'Not Verified';
                }
            }
        }
    });
}

function getDeductee15ghRefAmount() {
//    alert(1);
    var deductee_client_code = document.getElementById("deductee_client_code").value;
    var tds_type_code = document.getElementById("tds_type_code").value;
    var viewFlag = document.getElementById("action").value;
    var rowid_seq = document.getElementById("rowid_seq").value;
    var panno = document.getElementById("panno").value;

    if (tds_type_code !== "" && tds_type_code !== "null" && tds_type_code !== null && tds_type_code === '26Q') {
        var formURL = "deducteesAction15gh?action=getDeducteeRefAmtDetail";
//        alert(2);
        $.ajax({
            url: formURL,
            type: "POST",
            data: {
                deductee_panno: panno,
                deductee_client_code: deductee_client_code,
                rowid_seq: rowid_seq,
                viewFlag: viewFlag
            },
            success: function (result) {
//                alert(result);
                if (result !== "" && result !== "null" && result !== null) {
                    document.getElementById("RefDetailDivID").innerHTML = result;
                }
            }
        });
    }
}
function setNatureOfIncome(id) {
    var nature = "";
    var select = document.getElementById(id);
    var val = select.options[select.selectedIndex].value;
    var text = select.options[select.selectedIndex].text;
    if (!isNull(val)) {
        var dataSplit = text.split("(");
        nature = dataSplit[1].replace(")", "");
    }
    var idSplit = id.split("~");
    document.getElementById("natureOfIncome~" + idSplit[1]).value = nature;
}// end method

function update15ghDeducteeContentData() {
    // alert(1);

    var deductee_catg = document.getElementById("deductee_catg");
    var panno = document.getElementById("panno");
    var fltrDeducteeName = document.getElementById("fltrDeducteeName");
    var identificationNO = document.getElementById("identificationNO");
    var employeeCatg = document.getElementById("gender");
    var hidden_tdsTypeCode = document.getElementById("hidden_tdsTypeCode").value;
    var deductee_type = document.getElementById("deductee_type");
    var tds_type_code = document.getElementById("tds_type_code");
    var emailId = document.getElementById("email_id");
    var country_code = document.getElementById("country_code");
    var resident_status = document.getElementById("resident_status");
    var assessyear = document.getElementById("assessment_year");
    var form_filed = document.getElementById("form_filed").value;
    var dob = document.getElementById("dob");
    var aggregate_amount = document.getElementById("aggregate_amount").value;
    var est_total_income = document.getElementById("est_total_income").value;
    var est_income = document.getElementById("est_income").value;
    var est_total_income_int = 0;
    var total_est_int = 0;

    try {

        est_total_income_int = parseInt(isNull(est_total_income) ? "0" : est_total_income);
        var est_income_int = parseInt(isNull(est_income) ? "0" : est_income);
        var aggregate_amount_int = parseInt(isNull(aggregate_amount) ? "0" : aggregate_amount);
        total_est_int = est_income_int + aggregate_amount_int;

    } catch (err) {

    }

    var fileType = "";
    try {
        fileType = document.getElementById("fileType").value;
    } catch (err) {

    }

    if (document.getElementById("checkbox").checked) {
        document.getElementById("panno").value = 'PANNOTAVBL';
    }


    if (!lhsIsNull(deductee_catg.value)) {

        if (!lhsIsNull(panno.value)) {

            if (!lhsIsNull(fltrDeducteeName.value)) {

                if (hidden_tdsTypeCode === '24Q') {

                    if (lhsIsNull(identificationNO.value) || identificationNO.value.length === 9) {

                        if (validatePAN(panno)) {

                            if (panno.value !== "PANNOTAVBL") {

                                if (validateDeducteeType(deductee_type, panno)) {

                                    var LPanNO = panno.value.toUpperCase();
                                    var l_pan_char = LPanNO.charAt(3);
                                    if (deductee_catg.value !== "" && deductee_catg.value !== null && deductee_catg.value !== "null" && deductee_catg.value === "E") {
                                        if (l_pan_char === 'P') {
                                            if (validateEmail(emailId)) {
                                                // removeError();
                                                updateContentData();
                                            }
                                        } else {
                                            addError("PAN 4th Character Should Be P Only");
                                            scrollPage();
                                            document.getElementById("panno").focus();
                                        }
                                    } else {
                                        if (validateEmail(emailId)) {
                                            //  removeError();
                                            updateContentData();
                                        }
                                    }
                                }
                            } else {
                                if (validateEmail(emailId)) {
                                    removeError();
                                    updateContentData();
                                }
                            }

                        }

                    } else {
                        identificationNO.focus();
                        addActionError("Identification No. Must Be Of 9 Digits");
                    }
                } else if (hidden_tdsTypeCode === '26Q') {
                    if (!lhsIsNull(fileType) && fileType === 'H' && lhsIsNull(dob.value)) {
                        addActionError("DOB is Mandatory If File Type is 15H");
                    } else if (validatePAN(panno)) {
                        if (panno.value !== "PANNOTAVBL") {
                            if (validateDeducteeType(deductee_type, panno)) {

                                if (assessemnetYearValue()) {

                                    if (CheckCkBoforRefNo()) {

                                        if (validateEmail(emailId)) {

                                            if ((form_filed !== "" && form_filed !== null && form_filed !== "null") && (aggregate_amount === "" || aggregate_amount === "null" || aggregate_amount === "")) {
                                                addActionError("message", "Aggregate amount of income(other then this) Is Mandatory");
                                                //  scrollPage();
                                            } else if ((aggregate_amount !== "" && aggregate_amount !== null && aggregate_amount !== "null") && (form_filed === "" || form_filed === "null" || form_filed === "")) {
                                                addActionError("message", "Total number of form filed(Other then this) Is Mandatory");
                                                // scrollPage();
                                            } else if (!lhsIsNull(est_total_income) && (est_total_income_int < total_est_int)) {
                                                addActionError("message", "Estimated total income including above must not be less than sum of Estimated income for declaration and Aggregate amount of income(other then this)");
                                                // scrollPage();
                                            } else {
                                                //  removeError();
                                                updateContentData();
                                            }
                                        }
                                    }
                                } else {
                                    assessyear.focus();
                                    addActionError("message", "Select Assessment Year");
//                                    scrollPage();
                                }
                            } else {
                                addActionError("message", "Deductee Type Incorrect");
//                                scrollPage();
                            }
                        } else {
                            if (assessemnetYearValue()) {
                                if (CheckCkBoforRefNo()) {
                                    if (validateEmail(emailId)) {
                                        if ((form_filed !== "" && form_filed !== null && form_filed !== "null") && (aggregate_amount === "" || aggregate_amount === "null" || aggregate_amount === "")) {
                                            addActionError("message", "Aggregate amount of income(other then this) Is Mandatory");
//                                            scrollPage();
                                        } else if ((aggregate_amount !== "" && aggregate_amount !== null && aggregate_amount !== "null") && (form_filed === "" || form_filed === "null" || form_filed === "")) {
                                            addActionError("message", "Total number of form filed(Other then this) Is Mandatory");
//                                            scrollPage();
                                        } else if (!isNull(est_total_income) && (est_total_income_int < total_est_int)) {
                                            addActionError("message", "Estimated total income including above must not be less than sum of Estimated income for declaration and Aggregate amount of income(other then this)");
//                                            scrollPage();
                                        } else {
//                                            removeError();
                                            updateContentData();
                                        }
                                    }
                                }
                            } else {
                                assessyear.focus();
                                addActionError("message", "Select Assessment Year");
                                // scrollPage();
                            }
                        }
                    } else if (panno === "PANNOTAVBL") {
                        if (validateEmail(emailId)) {
                            //removeError();
                            updateContentData();
                        }
                    }
                } else {
                    if (resident_status.value === 'N' && (country_code.value === null || country_code.value === "" || country_code.value === "null")) {
                        addActionError("message", "Please Select Country");
//                        scrollPage();
                    } else {
                        if (validatePAN(panno)) {
                            if (panno.value !== "PANNOTAVBL") {
                                //alert(deductee_type);
                                if (validateDeducteeType(deductee_type, panno)) {
                                    if (validateEmail(emailId)) {
                                        // removeError();
                                        updateContentData();
                                    }
                                }
                            } else {
                                if (validateEmail(emailId)) {
                                    //  removeError();
                                    updateContentData();
                                }
                            }
                        }//
                        else if (panno === "PANNOTAVBL") {
                            if (validateEmail(emailId)) {
                                //; removeError();
                                updateContentData();
                            }
                        }
                    }
                }

            } else {
                fltrDeducteeName.focus();
                addActionError("message", "Enter Deductee Name.");
                // scrollPage();
            }
        } else {
            panno.focus();
            addActionError("message", "Enter PAN No.");
            //scrollPage();
        }

    } else {
        deductee_catg.focus();
        addActionError("message", "Select Deductee Category");
//        scrollPage();
    }
}



function validatePAN(field) {
    var value = field.value;
    var hidden_tdsTypeCode = document.getElementById("hidden_tdsTypeCode").value;
    if (!lhsIsNull(value)) {
        if (!lhsIsNull(hidden_tdsTypeCode)) {
            if (value !== "PANNOTAVBL" || value !== "PANINVALID" || value !== "PANAPPLIED") {
                var Exp = /^([a-zA-Z]){3}(a|A|b|B|c|C|f|F|g|G|h|H|j|J|k|K|l|L|p|P|t|T){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
                if (!Exp.test(value)) {
                    addActionError("Invalid PAN Number");
                    field.focus();
                    return false;
                } else {
                    if (hidden_tdsTypeCode === '26Q') {
                        if (value === "PANNOTAVBL" && value === "PANINVALID" && value === 'PANAPPLIED') {
                            addActionError("Reference no. will not be generated for " + value);
                            return true;
                        } else {
                            return true;
                        }
                    } else {
                        //    removeError();
                        return true;
                    }
                }
            } else {
                if (hidden_tdsTypeCode === '26Q') {
                    if (value === "PANNOTAVBL" && value === "PANINVALID" && value === 'PANAPPLIED') {
                        addError("Reference no. will not be generated for " + value);
                        return true;
                    }
                } else {
                    return true;
                }
                // removeError();
                return true;
            }
        }
    } else {
        clearDeducteeType();
        /// removeError();
        return false;
    }
}

function clearDeducteeType() {
    document.getElementById("deductee_type").innerHTML = "";
}//end function

function validateDeducteeType(deductee_type, panno) {
    try {
        // alert(deductee_type.value);
        if (!lhsIsNull(deductee_type.value) && !lhsIsNull(panno.value)) {
            var charAt3 = (panno.value.charAt(3)).toUpperCase();
            var chkVar = "";
            try {
                chkVar = deductee_type.value.split("~")[1];
            } catch (err) {
            }

            if (chkVar !== "" && chkVar !== "null" && chkVar !== null) {
                try {
                    chkVar = chkVar.toUpperCase();
                } catch (err) {
                    chkVar = charAt3;
                }
            }
            if (chkVar !== charAt3) {
                //    scrollPage();
                addActionError("Deductee Type Not Correct");
                return false;
            } else {
                //removeError();
                return true;
            }
        } else {
            return false;
        }
    } catch (err) {
        // alert(err);
    }
}//end function 

function assessemnetYearValue() {
    var flag = false;
    var assessment_select = document.getElementById("assessment_select").value;

    if (assessment_select === 'Y') {
        var assessyear = document.getElementById("assessment_year").value;
        if (assessyear === null || assessyear === "" || assessyear === "null") {
            flag = false;
        } else {
            flag = true;
        }
    } else {
        flag = true;
    }

    return flag;
}//end function

function CheckCkBoforRefNo() {
    var assessment_year = document.getElementById("assessment_year").value;
    var est_income = document.getElementById("est_income").value;
    var est_total_income = document.getElementById("est_total_income").value;
    var form_filed = document.getElementById("form_filed").value;
    var aggregate_amount = document.getElementById("aggregate_amount").value;
    var declaration_date = document.getElementById("declaration_date").value;
    var income_paid = document.getElementById("income_paid").value;
    var income_paid_date = document.getElementById("income_paid_date").value;

    var cnd = false;
    if (!lhsIsNull(assessment_year) || !lhsIsNull(est_income) || !lhsIsNull(est_total_income) || !lhsIsNull(form_filed) || !lhsIsNull(aggregate_amount) || !lhsIsNull(declaration_date) || !lhsIsNull(income_paid) || !lhsIsNull(income_paid_date)) {
        cnd = true;
    } else {
        cnd = true;
    }
    return cnd;
}//end function

function validateEmail(emailField) {

    if (!lhsIsNull(emailField.value)) {

//        var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

        var reg = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

        if (reg.test(emailField.value) === false)
        {
            addActionError('message', 'Invalid Email Address');
            emailField.focus();
            return false;
        } else {
            removeError();
            return true;
        }
    } else {
        removeError();
        return true;
    }
}//end method


function updateContentData() {

    var xml_15gh_error = "";
    try {
        xml_15gh_error = document.getElementById("xml_15gh_error").value;
    } catch (err) {
    }
    var cp = document.getElementById("globalcontextpath").value;
    var btnSave = document.getElementById("btnUpdate");
    if (!ajax_call_enabled) {
        $("#deductee_entry_page").submit(function (e) {
            var postData = $(this).serializeArray();
            var formURL = cp + "/getDeducteeCRUDEAction15gh?action=update";
         //                        getDeducteeCRUDEAction15gh
         //var formURL = cp + "/dropdownListDataAction15gh?action=selectTDSCode";
            // console.log('formURL----------->' + formURL);
            $.ajax(
                    {
                        url: formURL,
                        type: "POST",
//                        data:{
                            data: postData,
//                        },
                        success: function (data, textStatus, jqXHR) {
                            //  alert(1);
                            ajax_call_enabled = false;
                            var splitData = data.split("-");
                            ajax_call_enabled = false;
                            if (splitData[0].match("updateSuccess")) {
                                //window.location = cp + "/deducteesAction15gh?pageNumber=" + splitData[1] + "&update_data_row=" + splitData[2] + "&xml_15gh_error=" + encodeURIComponent(xml_15gh_error);
                                //openSuccessModal("Record Updated Successfully");
                                
                                let url = cp + "/form15GHTransaction";
                                openSuccessModal("Record Updated Successfully", "window.location='" + url + "'");
                                
                               
                            } else {
                                if (!data.includes('-')) {
                                    openErrorModal(data);
                                } else {
                                     let url = cp + "/form15GHTransaction";
                                    openErrorModal("Some Error Occured, Could Not Update","window.location='" + url + "'");
                                }
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            ajax_call_enabled = false;  //if fails
                        }

                    });
            e.preventDefault();
        });
        ajax_call_enabled = true;
        // disableButton(btnSave);
        $("#deductee_entry_page").submit(); //Submit  the FORM
    }



}


function getCityAndStateByPin(id) {

    var field_value = document.getElementById(id).value;
    var cp = document.getElementById("contextPath").value;
    if (field_value.length < 6) {
        document.getElementById(id).focus();
        openErrorModal("Invalid Pin Code");
        scrollPage();
    } else {
        removeError();
        var url = cp + "/getCityAndStateByPinCode";
//        alert(field_value + " " + id);
        if (field_value !== "") {
            $.ajax({
                url: url,
                type: "GET",
                data: {field_value: field_value},
                success: function (data) {
//                    alert(data)
                    if (data !== "")
                    {
                        var data_sp = data.split("#");
                        var datafirst = data_sp[0];
                        var datasec = data_sp[1];
                        document.getElementById("state_code").value = datasec;
                        document.getElementById("city_code").value = datafirst;
                    } else {
                        document.getElementById("state_code").value = "";
                        document.getElementById("city_code").value = "";
                    }
                }

            });
        }
    }

}

async function selectAddressCity() {
    var state_code = document.getElementById("state_code").value;
    var cp = document.getElementById("contextPath").value;
    var callingurl = cp + "/getListDataAction?action=selectCity" + "&selectedStateCode=" + encodeURIComponent(state_code);
    $.ajax({
        url: callingurl,
        context: document.body,
        success:  await function (result) {
                 document.getElementById("city_code").innerHTML = result;
            }
    });
}//end function

function selectCountry() {
    var resident_status = document.getElementById("resident_status").value;
    var hcountry_code = document.getElementById("hcountry_code").value;
    if (resident_status !== null && resident_status !== "" && resident_status !== "null") {
        if (resident_status === 'N') {

//            document.getElementById("countryLbl").style.display = "block";
//            document.getElementById("country_code").style.display = "block";
             $("#state_code").attr('disabled','disabled');
             $("#state_code").attr("style","cursor: not-allowed");
            document.getElementById("countryRowID").style.display = "block";
        } else {
//            document.getElementById("countryLbl").style.display = "none";
//            document.getElementById("country_code").style.display = "none";
            $("#state_code").removeAttr("style");
            $("#state_code").attr('disabled',false);
            document.getElementById("countryRowID").style.display = "none";
        }
        var cp = document.getElementById("contextPath").value;
        var callingurl = cp + "/dropdownListDataAction15gh?action=selectCountry" + "&resident_status=" + encodeURIComponent(resident_status) + "&hcountry_code=" + encodeURIComponent(hcountry_code);
        $.ajax({
            url: callingurl,
            context: document.body,
            success: function (result) {

                if (result !== "" && result !== null && result !== "null") {
                    document.getElementById("country_code").innerHTML = result;
                } else {
                    document.getElementById("country_code").innerHTML = "<option value=\"\">Select</option>";
                }
            }
        });
    } else {
        document.getElementById("country_code").innerHTML = "<option value=\"\">Select</option>";
    }
}


function check_deductee_type(defaultId) {
    var id = "panno";
    var le_value = "PANNOTAVBL";
    var pan = document.getElementById(id).value;
    var cp = document.getElementById("contextPath").value;
    if (pan !== null && pan !== "" && pan !== "null") {
        if (le_value !== pan) {
            if (validatePAN(document.getElementById(id))) {
                var panno = document.getElementById(id).value;
                var LPanNO = panno.toUpperCase();
                document.getElementById(id).value = LPanNO;
                var l_pan_char = LPanNO.charAt(3);
                var deductee_catg = document.getElementById("deductee_catg").value;
                // alert("Deductee Category==="+deductee_catg);
                if (deductee_catg !== "" && deductee_catg !== null && deductee_catg !== "null" && deductee_catg === "E") {
                    if (l_pan_char === 'P') {
                        var callingurl = cp + "/dropdownListDataAction15gh?action=setDeducteeType" + "&panno=" + encodeURIComponent(panno);
                        callDeducteeFunction(callingurl);
                        getnamedata(id);
                    } else {
                        addError("PAN 4th Character Should Be P Only");
                        scrollPage();
                        document.getElementById("panno").focus();
                    }
                } else {
                    var callingurl = cp + "/dropdownListDataAction15gh?action=setDeducteeType" + "&panno=" + encodeURIComponent(panno);
                    callDeducteeFunction(callingurl);
                    getnamedata(id);
                }
                setPanStatus(l_pan_char);
            }
        } else {
            var panno = "";
            var cp = document.getElementById("contextPath").value;
            var callingurl = cp + "/dropdownListDataAction15gh?action=setDeducteeType" + "&panno=" + encodeURIComponent(panno);
            callDeducteeFunction(callingurl);
        }
    } else {
        var panno = "";
        var callingurl = cp + "/dropdownListDataAction15gh?action=setDeducteeType" + "&panno=" + encodeURIComponent(panno);
        callDeducteeFunction(callingurl);
    }

    isPANverified(pan);
}

function callDeducteeFunction(callingurl) {
    $.ajax({
        url: callingurl,
        context: document.body,
        success: function (result) {
            if (result !== null && result !== "" && result !== "null") {
                document.getElementById("deductee_type").innerHTML = result;
            }
        }
    });
}//end function

function check_edit_15gh_deductee_type() {
    var id = "panno";
    var panno = document.getElementById(id).value;
    var hdeducteeType = document.getElementById("hdeducteeType").value;
    var l_panno = panno.toUpperCase();
    var cp = document.getElementById("contextPath").value;
    var callingurl = cp + "/dropdownListDataAction15gh?action=setDeducteeType" + "&panno=" + encodeURIComponent(l_panno) + "&hdeducteeType=" + encodeURIComponent(hdeducteeType);

    $.ajax({
        url: callingurl,
        context: document.body,
        success: function (result) {
            document.getElementById("deductee_type").innerHTML = result;
        }
    });
}


function setPanStatus(panFourthChar) {
    var hidden_tdsTypeCode = document.getElementById("hidden_tdsTypeCode").value;
    if (!isNull(hidden_tdsTypeCode) && hidden_tdsTypeCode === '26Q') {
        if (!isNull(panFourthChar)) {
            if (panFourthChar === 'P') {// refer pan_mast for values
                document.getElementById("pan_status").value = "1";
            } else if (panFourthChar === 'H') {
                document.getElementById("pan_status").value = "2";
            } else if (panFourthChar === 'A') {
                document.getElementById("pan_status").value = "3";
            } else if (panFourthChar === 'B') {
                document.getElementById("pan_status").value = "3";
            } else if (panFourthChar === 'T') {
                document.getElementById("pan_status").value = "4";
            } else if (panFourthChar === 'L') {
                document.getElementById("pan_status").value = "5";
            } else if (panFourthChar === 'J') {
                document.getElementById("pan_status").value = "6";
            } else if (panFourthChar === 'C') {
                document.getElementById("pan_status").value = "7";
            } else if (panFourthChar === 'F') {
                document.getElementById("pan_status").value = "7";
            }
        }
    }
}//end function


function checkCheckedPanNo() {
    var hPanNo = document.getElementById("hPanNo").value;
    if (hPanNo === 'PANNOTAVBL') {
        document.getElementById("checkbox").checked = true;
        document.getElementById("DeductTypeMendID").style.visibility = 'visible';
        getPanNotAvailble();
    }
}//end function

function selectTDSSection() {
    var deductee_catg = document.getElementById("deductee_catg").value;
    var htdsCode = document.getElementById("htdsCode").value;
    if (deductee_catg !== null && deductee_catg !== "" && deductee_catg !== "null") {
        var l_panChar = "B";
        var l_pan_no = document.getElementById("panno").value;
        if (l_pan_no !== "" && l_pan_no !== "null" && l_pan_no !== null && l_pan_no !== 'PANNOTAVBL') {
            l_pan_no = l_pan_no.charAt(3);
        }
        var cp = document.getElementById("contextPath").value;
        var callingurl = cp + "/dropdownListDataAction15gh?action=selectTDSCode" + "&panChar=" + encodeURIComponent(l_panChar) + "&deducteeCatg=" + encodeURIComponent(deductee_catg) + "&htdsCode=" + encodeURIComponent(htdsCode);
        $.ajax({
            url: callingurl,
            context: document.body,
            success: function (result) {
                if (result !== "" && result !== null && result !== "null") {
                    if (document.getElementById("tds_code") != null) {
                        document.getElementById("tds_code").innerHTML = result;
                    }
                } else {
                    if (document.getElementById("tds_code") != null) {
                        document.getElementById("tds_code").innerHTML = "<option value=\"\">----Select----</option>";
                    }
                }

            }
        });
    } else {
        document.getElementById("tds_code").innerHTML = "<option value=\"\">----Select----</option>";

    }

}//end function

function selectAssessmentYear(id) {
    var assessment_select = document.getElementById("assessment_select").value;
    if (assessment_select === 'Y') {
        document.getElementById("assessment_year_div").style.display = 'inline-block';
    } else {
        document.getElementById("assessment_year_div").style.display = 'none';
    }

}//end function

function changeAssessmentYear() {
    var assessment_select = document.getElementById("assessment_select").value;
    if (assessment_select === 'N') {
        document.getElementById("latestAccYearID").style.display = "none";
        document.getElementById("assessment_year").value = "";
        document.getElementById("assessment_year").style.pointerEvents = "none";
    } else {
        document.getElementById("latestAccYearID").style.display = "block";
        document.getElementById("assessment_year").style.pointerEvents = "all";
    }
}//end function


function deleteTDS() {
    document.getElementById("dialog-confirm_delete").style.display = "block";
    $(function () {
        $("#dialog-confirm_delete").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                "Ok": function () {
                    var formid = $("#deleteFormId").val();
                    document.getElementById(formid).submit();
                    $(this).dialog("close");
                },
                Cancel: function () {
                    $(this).dialog("close");
                }
            }
        });
    });
}

function addNewRow() {

    var idCountOld = document.getElementById("rowCount").value;
    if (!checkRowFilled(idCountOld)) {
        document.getElementById("dialog-confirm_empty_row_avbl").style.display = "block";
        $(function () {
            $("#dialog-confirm_empty_row_avbl").dialog({
                modal: true,
                buttons: {
                    Ok: function () {
                        $(this).dialog("close");
                    }
                }
            });
        });
//        return;
    } else {
        //  removeError();
        var id = "row~" + idCountOld;
        var row = document.getElementById(id); // find row to copy
        var table = document.getElementById("table").getElementsByTagName('tbody')[0]; // find table to append to
        var clone = row.cloneNode(true); // copy children too
        var idSplit = id.split("~");
        var idCount = parseInt(idSplit[1]) + 1;
        clone.id = idSplit[0] + "~" + idCount; // change id or other attributes/contents
// change srno
        var InputType = clone.getElementsByTagName("td");
        if (InputType.length > 0) {
            InputType[0].innerHTML = idCount;
        }
        // change id
        var InputType1 = clone.getElementsByTagName("input");
        for (var i = 0; i < InputType1.length; i++) {
            if (InputType1[i].type === 'text' || InputType1[i].type === 'hidden') {
                InputType1[i].id = InputType1[i].id.split("~")[0] + "~" + idCount;
                InputType1[i].name = InputType1[i].name.replace(idCountOld - 1, idCount - 1);
                InputType1[i].value = "";
            }
        }
        // change select id
        var InputType2 = clone.getElementsByTagName("select");
        for (var i = 0; i < InputType2.length; i++) {
            InputType2[i].id = InputType2[i].id.split("~")[0] + "~" + idCount;
            InputType2[i].name = InputType2[i].name.replace(idCountOld - 1, idCount - 1);
            InputType2[i].value = "";
        }

        var InputType3 = clone.getElementsByTagName("i");
        for (var i = 0; i < InputType3.length; i++) {
            InputType3[i].id = InputType3[i].id.split("~")[0] + "~" + idCount;
            InputType2[i].value = "";
        }
        table.appendChild(clone); // add new row to end of table
        document.getElementById("rowCount").value = idCount;
    }



}

function checkRowFilled(idCount) {
    var rowFilled = true;
    for (var i = 1; i <= idCount; i++) {
        var accountNo = document.getElementById("accountNo~" + i).value;
        var natureOfIncome = document.getElementById("natureOfIncome~" + i).value;
        var section = document.getElementById("section~" + i).value;
        var amount = document.getElementById("amount~" + i).value;
        if (lhsIsNull(accountNo)) {
            rowFilled = false;
            break;
        } else if (lhsIsNull(section)) {
            rowFilled = false;
            break;
        }
    }

    return rowFilled;
}//end method


function deleteRow(id) {
    var contextPath = document.getElementById("contextPath").value;
    var idSplit = id.split("~");
    var bflag_tran_seq_id = document.getElementById("bflag_tran_seq_id~" + idSplit[1]).value;
    $(function () {
        $("#dialog-confirm_delete").dialog({
            modal: true,
            buttons: {
                Ok: function () {
                    $(this).dialog("close");

                    if (lhsIsNull(bflag_tran_seq_id)) {
                        deleteAndRearrangeRows("row~" + id.split("~")[1]);
                    } else {
                        var xmlhttp;
                        if (window.XMLHttpRequest) {
                            xmlhttp = new XMLHttpRequest();
                        } else {
                            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                        }
                        xmlhttp.onreadystatechange = function () {
                            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                                var q = xmlhttp.responseText;
                                q = q.trim();
                                if (!lhsIsNull(q) && q === 'success') {

                                    document.getElementById("dialog-confirm_bflag_delete_success").style.display = "block";
                                    $(function () {
                                        $("#dialog-confirm_bflag_delete_success").dialog({
                                            modal: true,
                                            buttons: {
                                                Ok: function () {
                                                    $(this).dialog("close");
                                                }
                                            }
                                        });
                                    });
                                    deleteAndRearrangeRows("row~" + id.split("~")[1]);
                                } else {
                                    //  document.getElementById("dialog-confirm_bflag_delete_error").style.display = "block";
                                    $(function () {
                                        $("#dialog-confirm_bflag_delete_error").dialog({
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
                        };
                        var url = contextPath + "/getDeducteeCRUDEAction15gh?";
                        url += "action=deleteBflagAmt";
                        url += "&deducteeBflagSeqNo=" + bflag_tran_seq_id;
                        xmlhttp.open("GET", url, true);
                        xmlhttp.send(null);
                    }
                },
                Cancel: function () {
                    $(this).dialog("close");
                }
            }
        });
    });
}

function deleteAndRearrangeRows(rowid) {

    var rowCount = document.getElementById("rowCount").value;
    var count = 1;
    var table = document.getElementById("table").getElementsByTagName('tbody')[0];
    if (rowCount !== '1') {
        var deleteRowId = parseInt(rowid.split("~")[1]);
        var deleteRowId1 = deleteRowId - 1;

        table.deleteRow(deleteRowId1);
        var rowCountInt = parseInt(rowCount);
        document.getElementById("rowCount").value = rowCountInt - 1;
    } else {
        var firstRow = table.getElementsByTagName("tr")[0];
        var firstRowCols = firstRow.getElementsByTagName("td");
        for (var i = 0; i < firstRowCols.length; i++) {
            var col = firstRow.getElementsByTagName("td")[i];
            if (i > 0) {
                if (i == 2) {
                    var selectElement = col.getElementsByTagName("select")[0];
                    selectElement.value = "";
                } else {
                    var inputElement = col.getElementsByTagName("input")[0];
                    inputElement.value = "";
                }
            }
        }
    }
    for (var i = 0; i < table.rows.length; i++) {
        try {
            var row = table.getElementsByTagName("tr")[i];
            row.id = "row~" + count;

            var hiddenElement = row.getElementsByTagName("input");
            if (hiddenElement.length > 0) {

                for (var k = 0; k < hiddenElement.length; k++) {
                    if (hiddenElement[k].type === 'hidden') {
                        hiddenElement[k].id = hiddenElement[k].id.split("~")[0] + "~" + count;

                        var inputSplit = hiddenElement[k].name.split("[");
                        var inputSplitRight = inputSplit[1].split("]");
                        hiddenElement[k].name = inputSplit[0] + "[" + (count - 1) + "]" + inputSplitRight[1];
                    }
                }
            }// end if

            var cols = row.getElementsByTagName("td");

            for (var j = 0; j < cols.length; j++) {
                var col = row.getElementsByTagName("td")[j];

                if (j == 0) {
                    col.innerHTML = count;
                } else if (j == 2) {
                    var selectElement = col.getElementsByTagName("select");
                    if (selectElement.length > 0) {

                        for (var l = 0; l < selectElement.length; l++) {

                            selectElement[l].id = selectElement[l].id.split("~")[0] + "~" + count;

                            var selectSplit = selectElement[l].name.split("[");
                            var selectSplitRight = selectSplit[1].split("]");
                            selectElement[l].name = selectSplit[0] + "[" + (count - 1) + "]" + selectSplitRight[1];
                        }
                    }// end if
                } else {
                    var inputElement = col.getElementsByTagName("input");
                    if (inputElement.length > 0) {

                        for (var k = 0; k < inputElement.length; k++) {
                            if (inputElement[k].type === 'text' || inputElement[k].type === 'hidden') {
                                inputElement[k].id = inputElement[k].id.split("~")[0] + "~" + count;
                                var inputSplit = inputElement[k].name.split("[");
                                var inputSplitRight = inputSplit[1].split("]");
                                inputElement[k].name = inputSplit[0] + "[" + (count - 1) + "]" + inputSplitRight[1];
                            }
                        }
                    }// end if

                    var iElement = col.getElementsByTagName("i");
                    if (iElement.length > 0) {
                        iElement[0].id = iElement[0].id.split("~")[0] + "~" + count;
                    }// end if

                }

            }
        } catch (err) {

        }
        count++;
    }
}//end function


function challanBrowsePage() {
    var cp = $("#globalcontextpath").val();
    window.location = cp + "/form15GHTransaction";
}

function challanEntryPage() {
    //   alert(1);
    var cp = $("#globalcontextpath").val();
    window.location = cp + "/deducteesAction15gh";
}

function displayGenTypeOneDiv() {
    //   alert(1);
    var loginGenerationLevelClient = document.getElementById("loginGenerationLevelClient").value;

    var splitData = loginGenerationLevelClient.split("~");
    var client_code = splitData[0];
    document.getElementById("GenTypeOne").style.display = "block";
    document.getElementById("GenTypeTwo").style.display = "none";

    document.getElementById("Loginradio").checked = true;
    document.getElementById("nextradio").checked = false;

    callNoGenerationTypeOne(client_code);
    document.getElementById("15GHNumberGenerationLevelDivId").style.display = "none";
    try {
        document.getElementById("user_level").value = "login";
    } catch (e) {
    }


}//end function

function callNoGenerationTypeOne(client_code, filterFlag, loginLevel) {
    var cp = document.getElementById("contextPath").value;
    if (!lhsIsNull(loginLevel) && loginLevel === 1) {
        loginLevel = 1;
    } else {
        loginLevel = 0;
    }
    if (!lhsIsNull(filterFlag) && filterFlag === 'T') {
        filterFlag = 'T';
    } else {
        filterFlag = 'F';

    }
    var xmlhttp;
    if (window.XMLHttpRequest) { //code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    showProcessIndicator();

    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            var q = xmlhttp.responseText;
            q = q.trim();
            if (q !== "" && q !== "null" && q !== null) {
                var splitValue = q.split("#");
                document.getElementById("lastGen15GNoId").value = splitValue[0];
                document.getElementById("lastGen15HNoId").value = splitValue[1];
                document.getElementById("NextGen15GNoId").value = splitValue[2];
                document.getElementById("NextGen15HNoId").value = splitValue[3];
            }
            hideProcessIndicator();
            // document.getElementById("15gh_refranceNO_process_indicator_css").style.visibility = "hidden";
        }
    };
    var url = cp + "/getGenReferenceNoAction?action=getRefNoType1Dtl&GenClientCode=" + encodeURIComponent(client_code) + "&loginFlag=" + encodeURIComponent(loginLevel) + "&filterFlag=" + encodeURIComponent(filterFlag);
    xmlhttp.open("GET", url, true);
    xmlhttp.send(null);
}//end function

function checkClientLevel() {
    var GenClientCode = document.getElementById("loginGenerationLevelClient").value;
    if (GenClientCode !== null && GenClientCode !== "" && GenClientCode !== "null") {
        var splitData = GenClientCode.split("~");
        var client_code = splitData[0];
        var selected_client_level = splitData[1];
        var selected_level_type = splitData[2];

        if (selected_level_type === "" && selected_level_type === null && selected_level_type === "") {
            selected_level_type = "6";
        }
        selected_level_type = parseFloat(selected_level_type);
        if (selected_client_level === "" && selected_client_level === null && selected_client_level === "") {
            selected_client_level = "6";
        }
        selected_client_level = parseFloat(selected_client_level);

        if (selected_level_type === selected_client_level) {
            callNoGenerationTypeOne(client_code);
            document.getElementById("GenTypeOne").style.display = "block";
            document.getElementById("GenTypeTwo").style.display = "none";
        } else {
            document.getElementById("GenTypeOne").style.display = "block";
            document.getElementById("GenTypeTwo").style.display = "none";
            callNoGenerationTypeOne(client_code, 'T', 1);
        }
    } else {
        var sessionClientCode = $("#session_clientCode").val();
        if (sessionClientCode !== null && sessionClientCode !== "" && sessionClientCode !== "null") {
            callNoGenerationTypeTwo(sessionClientCode, 'F', 1);
            document.getElementById("GenTypeOne").style.display = "NONE";
            document.getElementById("GenTypeTwo").style.display = "BLOCK";
        } else {
            document.getElementById("GenTypeOne").style.display = "none";
            document.getElementById("GenTypeTwo").style.display = "none";
        }
    }
}

function callNoGenerationTypeTwo(client_code, filterFlag, LoginFlag) {
    if (!lhsIsNull(filterFlag) && filterFlag === 'T') {
        filterFlag = 'T';
    } else {
        filterFlag = 'F';

    }


    if (!lhsIsNull(LoginFlag) && LoginFlag === 1) {
        LoginFlag = 1;
    } else {
        LoginFlag = 0;

    }
    document.getElementById("genType2TableID").innerHTML = "";
    var cp = document.getElementById("contextPath").value;
    var dispTempFlag = document.getElementById("dispTempFlag").value;
    var xmlhttp;
    if (window.XMLHttpRequest) { //code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    } else {
        //code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    showProcessIndicator();

    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            var q = xmlhttp.responseText;
            q = q.trim();
            if (q !== "" && q !== "null" && q !== null) {
                document.getElementById("genType2TableID").innerHTML = q;
                try {
                    document.getElementById("user_level").value = "next";
                    document.getElementById("next-level-btns").style.display = "inline-block";
                } catch (e) {
                }
            }

            hideProcessIndicator();
        }
    };
    var url = cp + "/getGenReferenceNoAction?action=getRefNoType2Dtl&GenClientCode=" + encodeURIComponent(client_code) +
            "&dispTempFlag=" + encodeURIComponent(dispTempFlag) + "&filterFlag=" + encodeURIComponent(filterFlag) + "&loginFlag=" + LoginFlag;

    xmlhttp.open("GET", url, true);
    xmlhttp.send(null);
}

function displayNextLevelData() {

    var loginGenerationLevelClient = document.getElementById("loginGenerationLevelClient").value;
    var splitData = loginGenerationLevelClient.split("~");
    var client_code = splitData[0];
    document.getElementById("GenTypeOne").style.display = "none";
    document.getElementById("GenTypeTwo").style.display = "block";

    document.getElementById("nextradio").checked = true;
    document.getElementById("Loginradio").checked = false;

    document.getElementById("15GHNumberGenerationLevelDivId").style.display = "block";
    if (lhsIsNull(client_code)) {
        client_code = $("#session_clientCode").val();
    }
    callNoGenerationTypeTwo(client_code, '', 1);
}//end function

function saveReferenceNo() {
    var cp = document.getElementById("contextPath").value;
    var generationLevelClient = document.getElementById("generationLevelClientId").value;
    var NextGen15GNo = document.getElementById("NextGen15GNoId").value;
    var NextGen15HNo = document.getElementById("NextGen15HNoId").value;

//    if (generationLevelClient === null || generationLevelClient === "" || generationLevelClient === "null") {
//        addActionError("message", "Please Select 15GH Number Generation Level");
//    } else 
    if (NextGen15GNo === null || NextGen15GNo === "" || NextGen15HNo === "null") {
        addActionError("message", "Next Number 15G Cannot Be Left Blank");
    } else if (NextGen15HNo === null || NextGen15HNo === "" || NextGen15HNo === "null") {
        addActionError("message", "Next Number 15H Cannot Be Left Blank");
    } else {
        var editRadioBtnG = document.getElementById("editRadioBtnG").value;
        var editRadioBtnH = document.getElementById("editRadioBtnH").value;
        var splitData = generationLevelClient.split("~");
        var selectedClient = splitData[0];
        // document.getElementById("dialog-confirm_ref_no_Present_Msg").style.display = "block";
        $(function () {
            $("#dialog-confirm_ref_no_Present_Msg").dialog({
                resizable: false,
                font: 10,
                modal: true,
                buttons: {
                    "Ok": function () {
                        $(this).dialog("close");
                        var xmlhttp;
                        if (window.XMLHttpRequest) { //code for IE7+, Firefox, Chrome, Opera, Safari
                            xmlhttp = new XMLHttpRequest();
                        } else {
                            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                        }
                        xmlhttp.onreadystatechange = function () {
                            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                                var q = xmlhttp.responseText;
                                q = q.trim();
                                console.log("Response---->" + q);
                                if (q !== "" && q !== "null" && q !== null) {
                                    if (q === "save") {
                                        openSuccessModal("Reference No Generated Successfully");
                                        displayGenTypeOneDiv();
                                        //checkClientLevel();
                                    } else if (q === "error") {
                                        openErrorModal("Could Not Generate Reference No,Some Error Occured");
                                        //  document.getElementById("15gh_refranceNO_process_indicator_css").style.visibility = "hidden";
                                    } else {

                                        // document.getElementById("15gh_refranceNO_process_indicator_css").style.visibility = "hidden";
                                    }
                                } else {
                                    openErrorModal("Could Not Generate, Some Error Occured");
                                    // getElementById("15gh_refranceNO_process_indicator_css").style.visibility = "hidden";
                                }
                            }
                        };
                        var url = cp + "/getDeducteeCRUDEAction15gh?action=checkExistingRefNo&generationLevelClient=" + encodeURIComponent(selectedClient)
                                + "&NextGen15GNo=" + encodeURIComponent(NextGen15GNo) + "&NextGen15HNo=" + encodeURIComponent(NextGen15HNo) + "&editRadioBtnG=" + encodeURIComponent(editRadioBtnG)
                                + "&editRadioBtnH=" + encodeURIComponent(editRadioBtnH);
                        xmlhttp.open("GET", url, true);
                        xmlhttp.send(null);
                    },
                    " Cancel": function () {
                        $(this).dialog("close");
                    }
                }
            });
        });
    }
}

function editRadioBtnG(id) {
    // alert(id);
    if (id === 'RValueG') {
        document.getElementById("RValueG").checked = true;
        document.getElementById("BValueG").checked = false;
        document.getElementById("editRadioBtnG").value = "R";
    } else {
        document.getElementById("RValueG").checked = false;
        document.getElementById("BValueG").checked = true;
        document.getElementById("editRadioBtnG").value = "B";
    }
}

function editRadioBtnH(id) {
    // alert(id);
    if (id === 'RValueH') {
        document.getElementById("RValueH").checked = true;
        document.getElementById("BValueH").checked = false;
        document.getElementById("editRadioBtnH").value = "R";
    } else {
        document.getElementById("RValueH").checked = false;
        document.getElementById("BValueH").checked = true;
        document.getElementById("editRadioBtnH").value = "B";
    }
}//end function

function editRadioBtnAll(id) {
    // alert(id);
    var tbl = document.getElementById("refranceNoTypeTwoTableId");
    var total_record = tbl.rows.length;
    if (id === 'RValueAll') {
//        document.getElementById("RValueAll").checked = true;
        document.getElementById("BValueAll").checked = false;
        for (var j = 1; j < total_record; j++) {
            document.getElementById("editRadioBtn~" + j).value = "R";
            document.getElementById("RValue~" + j).checked = true;
            document.getElementById("BValue~" + j).checked = false;
        }
    } else {
        document.getElementById("RValueAll").checked = false;
        document.getElementById("BValueAll").checked = true;
        for (var j = 1; j < total_record; j++) {
            document.getElementById("editRadioBtn~" + j).value = "B";
            document.getElementById("RValue~" + j).checked = false;
            document.getElementById("BValue~" + j).checked = true;
        }
    }
}

function editRadioBtnData(id) {
    var splitID = id.split("~");
    var chkID = splitID[0];
    var count = splitID[1];
    if (chkID === 'RValue') {
        document.getElementById("RValue~" + count).checked = true;
        document.getElementById("BValue~" + count).checked = false;
        document.getElementById("editRadioBtn~" + count).value = "R";
        checkRadioBtn();
    } else {
        document.getElementById("RValue~" + count).checked = false;
        document.getElementById("BValue~" + count).checked = true;
        document.getElementById("editRadioBtn~" + count).value = "B";
        checkRadioBtn();
    }
}//

function checkRadioBtn() {
    try {
        var tbl = document.getElementById("refranceNoTypeTwoTableId");
        var total_record = tbl.rows.length;
        var ckhRCount = 0;
        var ckhBCount = 0;
        for (var j = 1; j < total_record; j++) {
            var chk = document.getElementById("RValue~" + j).checked;
            if (chk) {
                ckhRCount++;
            }
            var chk = document.getElementById("BValue~" + j).checked;
            if (chk) {
                ckhBCount++;
            }
        }
        if ((ckhRCount + 1) === total_record) {
            document.getElementById("RValueAll").checked = true;
        } else {
            document.getElementById("RValueAll").checked = false;
        }

        if ((ckhBCount + 1) === total_record) {
            document.getElementById("BValueAll").checked = true;
        } else {
            document.getElementById("BValueAll").checked = false;
        }
    } catch (err) {
    }
}//end function

function downloadGenerationError() {
    //alert("Hello");
    var dataAvailFlag = document.getElementById("dataAvailFlag").value;
    // alert("DataAvailFlag"+dataAvailFlag);
    if (dataAvailFlag === 'T') {
        //  remove15GHError();
        var code_level = document.getElementById("clientLoginLevel").value;
        var client_code = document.getElementById("generationLevelClientId").value;
        var client = client_code.split("~");

//        if (!lhsIsNull(client[0])) {
        var cp = document.getElementById("contextPath").value;
        var url = "/getDownloadRefNoTemp?";
        url += "GenClientCode=" + encodeURIComponent(client[0]);
        url += "&processLevel=1";
        url += "&codeLevel=" + encodeURIComponent(code_level);

        callUrl(url);
//        } else {
//            openErrorModal("Please Select Branch Code");
//        }
    } else {
        openErrorModal("No Records Found, So Could Not Download");
    }
}

function save15GhRefNoDetailsData() {
    var cp = document.getElementById("contextPath").value;
    var dataAvailFlag = document.getElementById("dataAvailFlag").value;
    if (dataAvailFlag === 'T') {
        var l_record_count = 0;
        var client_code_list = "";
        var next_gen_15g_list = "";
        var next_gen_15h_list = "";
        try {
            var tbl = document.getElementById("refranceNoTypeTwoTableId");
            var total_record = tbl.rows.length;
            for (var j = 1; j < total_record; j++) {
                var chk = document.getElementById('chk~' + j).checked ? "Y" : "N";
                if (chk === "Y") {
                    l_record_count++;
                    var editRadioBtn = document.getElementById('editRadioBtn~' + j).value;
                    if (editRadioBtn === 'F' || editRadioBtn === 'B' || editRadioBtn === 'R') {
                        var client_code = document.getElementById('client_code~' + j).value;
                        var next_gen_15g = document.getElementById('next_gen_15g~' + j).value;
                        if (next_gen_15g === "" || next_gen_15g === "" || next_gen_15g === "") {
                            next_gen_15g = "0";
                        }
                        var next_gen_15h = document.getElementById('next_gen_15h~' + j).value;
                        if (next_gen_15h === "" || next_gen_15h === "" || next_gen_15h === "") {
                            next_gen_15h = "0";
                        }
                        client_code_list += client_code + "#";
                        next_gen_15g_list += next_gen_15g + "#";
                        next_gen_15h_list += next_gen_15h + "#";
                    }
                }
            }
        } catch (err) {

        }
        l_record_count = parseInt(l_record_count);
        if (l_record_count > 0) {
            var url = cp + "/getDeducteeCRUDEAction15gh";
            if (!ajax_call_enabled) {
                $.ajax({
                    url: url,
                    type: 'POST',
                    data: {
                        action: 'checkRefNoList',
                        client_code_list: client_code_list,
                        next_gen_15g_list: next_gen_15g_list,
                        next_gen_15h_list: next_gen_15h_list
                    },
                    beforeSend: function (xhr) {
                        ajax_call_enabled = true;
                        showBlackProcessIndicator();
                    },
                    success: function (data, textStatus, jqXHR) {
                        ajax_call_enabled = false;
                        var q = data;
                        q = q.trim();
                        if (q !== "" && q !== "null" && q !== null) {
                            if (q === "T") {
                                callSave();
                            } else {
                                openErrorModal("Some Error Occured, Could Not Generate");
                            }
                        } else {
                            openErrorModal("Some Error Occured, Could Not Generate");
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        ajax_call_enabled = false;
                        hideBlackProcessIndicator();
                    },
                    complete: function (jqXHR, textStatus) {
                        ajax_call_enabled = false;
                        hideBlackProcessIndicator();
                    }
                });
            }
        } else {
            openErrorModal("Please Select Record For Generate");
        }
    } else {
        openErrorModal("No Record Found, So Could Not Generate");
    }
}

function callSave() {
    if (!ajax_call_enabled) {
        ajax_call_enabled = true;
        showBlackProcessIndicator();

        $("#deductee15GhRefNoForm").submit(function (e) {
            var postData = $(this).serializeArray();
            var formURL = $(this).attr("action");
            $.ajax({
                url: formURL,
                type: "POST",
                data: postData,
                success: function (data, textStatus, jqXHR) {
                    console.log('Response--->' + data);
                    ajax_call_enabled = false;
                    if (data === 'saveData') {
                        openSuccessModal("Reference No Generated Successfully");
                        document.getElementById("dispTempFlag").value = "F";
                        //  scrollPage();
                        checkClientLevel();
                    } else {
                        openErrorModal("Some Error Occured, Could Not Generate Reference No");
                        //  scrollPage();
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert('error');
                    openErrorModal("Some Error Occured, Could Not Generate Reference No");
                    ajax_call_enabled = false;  //if fails      
                },
                complete: function (jqXHR, textStatus) {
                    ajax_call_enabled = false;
                    hideBlackProcessIndicator();
                }
            });
            e.preventDefault(); //STOP default action
            // e.unbind(); //unbind. to stop multiple form submit.
        });
        ajax_call_enabled = true;
        $("#deductee15GhRefNoForm").submit(); //Submit  the FORM
    }
}//end function

function onClickCheckbox(id) {
    var splitID = id.split("~");
    var count = splitID[1];
    var checked = document.getElementById(id).checked;
    if (checked) {
        document.getElementById("editCheckBox~" + count).value = "true";
        try {
            var tbl = document.getElementById("refranceNoTypeTwoTableId");
            var total_record = tbl.rows.length;
            var ckhCount = 0;
            for (var j = 1; j < total_record; j++) {
                var chk = document.getElementById("chk~" + j).checked;
                if (chk) {
                    ckhCount++;
                }
            }
            if ((ckhCount + 1) === total_record) {
                document.getElementById("allchk").checked = true;
            }
        } catch (err) {
            // alert(err);
        }
    } else {
        document.getElementById("editCheckBox~" + count).value = "false";
        document.getElementById("allchk").checked = false;
    }
}

function uploadReferenceNoModal() {
    var genClientCode = document.getElementById("generationLevelClientId").value;

    if (genClientCode === "" || genClientCode === "null" || genClientCode === null) {
        genClientCode = document.getElementById("session_clientCode").value;
    }
    document.getElementById("genClientCode").value = genClientCode;
////    else {
////        alert("Please Select 15GH Number Generation Level");
////    }
//
//    var splitData = GenClientCode.split("~");
//    var client_code = splitData[0];
//    var cp = document.getElementById("contextPath").value;
//    var url = cp + '/getGenReferenceNoAction?action=modalForUploadReference&GenClientCode=' + encodeURIComponent(client_code);
//    document.getElementById('uploadReferenceModalIframe').src = url;
    $('#uploadReferenceModal').modal('show');
}

function uploadReferenceXls() {
    var fileTemplate = document.getElementById('template').value;
    if (fileTemplate !== "" && fileTemplate !== null) {

        var cp = document.getElementById("globalcontextpath").value;
//        var url = cp + "/uploadXlsFileReferenceNo?validate=true";
        var url = cp + "/referenceNoUpload?validate=true";
        var formData = new FormData($("form#excelReferenceNoFiles")[0]);

        $.ajax({
            url: url,
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            beforeSend: function (xhr) {
                showProcessIndicator();
            },
            success: function (data) {
//                console.log(data);
                if (data === 'uploadSuccess') {
                    //alert("File Uploaded Successfully");
                    document.getElementById("dispTempFlag").value = "T";
                    try {
//                        window.parent.document.getElementById("deductee_bflag_notification").style.visibility = "visible";
//                        window.parent.document.getElementById("deductee_bflag_notification").style.display = "block";
//                        window.parent.document.getElementById("deductee_bflag_notification").innerHTML = "File Uploaded Successfully";
                        openSuccessModal("File Uploaded Successfully");
                    } catch (err) {
                    }
                    try {
                        callNoGenerationTypeTwoAtModal();
                    } catch (err) {
                    }
                } else if (data === 'uploadMaxResult') {
                    window.location = cp + "/logout?mx_upd_res=TBC";

                } else if (data.search('error') !== -1) {
                    try {
                        openErrorModal("Some error occurred!!");
                    } catch (err) {
                    }
                } else {
                    var details = data.split("#");
                    if (details[0] === 'success') {
                        document.getElementById("genType2TableID").innerHTML = details[1];
                    } else {
                        openErrorModal("Some Error Occurred!!");
                    }
                }
                hideProcessIndicator();
                $('#uploadReferenceModal').modal('hide');
            },
            error: function (jqXHR, status) {
                hideProcessIndicator();
            },
            complete: function (jqXHR, textStatus) {
                hideProcessIndicator();
            }
        });
    } else {
        $('#uploadReferenceModal').modal('hide');
        openErrorModal("Please Select File To Upload");
    }
}//end function

function checkFile(e) {
    var file_list = e.target.files;
    for (var i = 0, file; file = file_list[i]; i++) {
        var sFileName = file.name;
        var sFileExtension = sFileName.split('.')[sFileName.split('.').length - 1].toLowerCase();
        var iFileSize = file.size;
        var iConvert = (file.size / 1048576).toFixed(2);
        if (!(sFileExtension === "xls" || sFileExtension === "xlsx" || sFileExtension === "XLS" || iFileSize > 10485760)) { /// 10 mb
            txt = "File type : " + sFileExtension + "\n\n";
            txt += "Size: " + iConvert + " MB \n\n";
            txt += "Please make sure your file is in pdf or doc format and less than 10 MB.\n\n";
            alert(txt);
            document.getElementById('template').value = "";
        }
    }
}//end function

function callNoGenerationTypeTwoAtModal() {
    //alert("type2 start");
    var GenClientCode = window.parent.document.getElementById("generationLevelClientId").value;
    if (GenClientCode !== "" && GenClientCode !== "null" && GenClientCode !== null) {
        var splitData = GenClientCode.split("~");
        var client_code = splitData[0];

        var cp = window.parent.document.getElementById("contextPath").value;
        var dispTempFlag = window.parent.document.getElementById("dispTempFlag").value;
        // window.parent.document.getElementById("15gh_refranceNO_process_indicator_css").style.visibility = "visible";

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
                if (q !== "" && q !== "null" && q !== null) {
                    window.parent.document.getElementById("genType2TableID").innerHTML = q;
                }
                //window.parent.document.getElementById("15gh_refranceNO_process_indicator_css").style.visibility = "hidden";
            }
        };
        var url = cp + "/getGenReferenceNoAction?action=getRefNoType2Dtl&GenClientCode=" + encodeURIComponent(client_code) + "&dispTempFlag=" + encodeURIComponent(dispTempFlag);
        xmlhttp.open("GET", url, true);
        xmlhttp.send(null);
    }
}//end function

function callEntryPage()
{
    var cp = $("#globalcontextpath").val();
    window.location = cp + "/deducteesAction15gh?action=add";
}


function saveDeductee15ghContentData() {
//    document.getElementById("btnUpdate").setAttribute("disabled", true);

    var deductee_catg = document.getElementById("deductee_catg");
    var panno = document.getElementById("panno");
    var fltrDeducteeName = document.getElementById("fltrDeducteeName");
    var identificationNO = document.getElementById("identificationNO");
    var employeeCatg = document.getElementById("gender");
    var hidden_tdsTypeCode = document.getElementById("hidden_tdsTypeCode").value;
    var deductee_type = document.getElementById("deductee_type");
    var emailId = document.getElementById("email_id");
    var country_code = document.getElementById("country_code");
    var resident_status = document.getElementById("resident_status");
    var dob = document.getElementById("dob");
    var assessyear = document.getElementById("assessment_year");
    var form_filed = document.getElementById("form_filed").value;
    var aggregate_amount = document.getElementById("aggregate_amount").value;
    var est_total_income = document.getElementById("est_total_income").value;
    var est_income = document.getElementById("est_income").value;
    var est_total_income_int = 0;
    var total_est_int = 0;
    try {

        est_total_income_int = parseInt(isNull(est_total_income) ? "0" : est_total_income);

        var est_income_int = parseInt(isNull(est_income) ? "0" : est_income);

        var aggregate_amount_int = parseInt(isNull(aggregate_amount) ? "0" : aggregate_amount);

        total_est_int = est_income_int + aggregate_amount_int;

    } catch (err) {

    }

    if (document.getElementById("checkbox").checked) {
        document.getElementById("panno").value = 'PANNOTAVBL';

    }

    var fileType = "";
    try {

        fileType = document.getElementById("fileType").value;
    } catch (err) {

    }


    if (!isNull(deductee_catg.value)) {


        if (!isNull(panno.value)) {

            if (!isNull(fltrDeducteeName.value)) {

                if (hidden_tdsTypeCode === '24Q') {

                    if (isNull(identificationNO.value) || identificationNO.value.length === 9) {

                        if (!isNull(employeeCatg.value)) {

                            if (validatePAN(panno)) {

                                if (panno.value !== "PANNOTAVBL") {

                                    if (validateDeducteeType(deductee_type, panno)) {

                                        var LPanNO = panno.value.toUpperCase();
                                        var l_pan_char = LPanNO.charAt(3);
                                        if (deductee_catg.value !== "" && deductee_catg.value !== null && deductee_catg.value !== "null" && deductee_catg.value === "E") {
                                            if (l_pan_char === 'P') {
                                                if (validateEmail(emailId)) {

                                                    removeError();
                                                    saveContentData();
                                                }
                                            } else {


                                                addError("PAN 4th Character Should Be P Only");
                                                scrollPage();
                                                document.getElementById("panno").focus();
                                            }
                                        } else {
                                            if (validateEmail(emailId)) {

                                                removeError();
                                                saveContentData();
                                            }
                                        }
                                    } else {
                                        addError("Deductee Type Incorrect");
                                        scrollPage();
                                    }
                                } else {
                                    if (validateEmail(emailId)) {

                                        removeError();
                                        saveContentData();
                                    }
                                }

                            } else if (panno === "PANNOTAVBL") {
                                if (validateEmail(emailId)) {
                                    removeError();
                                    saveContentData();
                                }
                            }
                        } else {
                            employeeCatg.focus();
                            addError("Enter Employee Category.");
                            scrollPage();
                        }
                    } else {
                        identificationNO.focus();
                        addError("Identification No. Must Be Of 9 Digits");
                        scrollPage();
                    }
                } else if (hidden_tdsTypeCode === '26Q') {

                    if (!isNull(fileType) && fileType === 'H' && isNull(dob.value)) {
                        addError("DOB is Mandatory If File Type is 15H");
                        scrollPage();
                    } else if (validatePAN(panno)) {
                        if (panno.value !== "PANNOTAVBL") {
                            if (validateDeducteeType(deductee_type, panno)) {
                                if (assessemnetYearValue()) {
                                    if (CheckCkBoforRefNo()) {
                                        if (validateEmail(emailId)) {

                                            if ((form_filed !== "" && form_filed !== null && form_filed !== "null") && (aggregate_amount === "" || aggregate_amount === "null" || aggregate_amount === "")) {
//                                                addError("Aggregate amount of income(other then this) Is Mandatory");
                                                addActionError("message", "Aggregate amount of income(other then this) Is Mandatory");
                                                scrollPage();
                                            } else if ((aggregate_amount !== "" && aggregate_amount !== null && aggregate_amount !== "null") && (form_filed === "" || form_filed === "null" || form_filed === "")) {
//                                                addError("Total number of form filed(Other then this) Is Mandatory");
                                                addActionError("message", "Total number of form filed(Other then this) Is Mandatory");
                                                scrollPage();
                                            } else if (!isNull(est_total_income) && (est_total_income_int < total_est_int)) {
//                                                addError("Estimated total income including above must not be less than sum of Estimated income for declaration and Aggregate amount of income(other then this)");
                                                addActionError("message", "Estimated total income including above must not be less than sum of Estimated income for declaration and Aggregate amount of income(other then this)");
                                                scrollPage();
                                            } else {
                                                removeError();
                                                saveContentData();
                                            }
                                        }
                                    }
                                } else {
                                    assessyear.focus();
                                    addError("Select Assessment Year");
                                    scrollPage();
                                }
                            } else {
                                addError("Deductee Type Incorrect");
                                scrollPage();
                            }
                        } else {
                            if (assessemnetYearValue()) {
                                if (CheckCkBoforRefNo()) {
                                    if (validateEmail(emailId)) {
                                        if ((form_filed !== "" && form_filed !== null && form_filed !== "null") && (aggregate_amount === "" || aggregate_amount === "null" || aggregate_amount === "")) {
//                                            addError("Aggregate amount of income(other then this) Is Mandatory");
                                            addActionError("message", "Aggregate amount of income(other then this) Is Mandatory");
                                            scrollPage();
                                        } else if ((aggregate_amount !== "" && aggregate_amount !== null && aggregate_amount !== "null") && (form_filed === "" || form_filed === "null" || form_filed === "")) {
//                                            addError("Total number of form filed(Other then this) Is Mandatory");
                                            addActionError("message", "Total number of form filed(Other then this) Is Mandatory");
                                            scrollPage();
                                        } else if (!isNull(est_total_income) && (est_total_income_int < total_est_int)) {
//                                            addError("Estimated total income including above must not be less than sum of Estimated income for declaration and Aggregate amount of income(other then this)");
                                            addActionError("message", "Estimated total income including above must not be less than sum of Estimated income for declaration and Aggregate amount of income(other then this)");
                                            scrollPage();
                                        } else {
                                            removeError();
                                            saveContentData();
                                        }
                                    }
                                }
                            } else {
                                assessyear.focus();
                                addError("Select Assessment Year");
                                scrollPage();
                            }
                        }
                    } else if (panno === "PANNOTAVBL") {
                        if (validateEmail(emailId)) {
                            removeError();
                            saveContentData();
                        }
                    }
                } else {
                    if (resident_status.value === 'N' && (country_code.value === null || country_code.value === "" || country_code.value === "null")) {
                        addError("Please Select Country");
                        scrollPage();
                    } else {
                        if (validatePAN(panno)) {
                            if (panno.value !== "PANNOTAVBL") {
                                if (validateDeducteeType(deductee_type, panno)) {
                                    if (validateEmail(emailId)) {
                                        removeError();
                                        saveContentData();
                                    }
                                } else {
                                    addError("Deductee Type Incorrect");
                                    scrollPage();
                                }
                            } else {
                                if (validateEmail(emailId)) {
                                    removeError();
                                    saveContentData();
                                }
                            }
                        } else if (panno === "PANNOTAVBL") {
                            if (validateEmail(emailId)) {
                                removeError();
                                saveContentData();
                            }
                        }
                    }
                }
            } else {
                fltrDeducteeName.focus();
                addError("Enter Deductee Name.");
                scrollPage();
            }
        } else {
            panno.focus();
            addError("Enter PAN No.");
            scrollPage();
        }
    } else {
        deductee_catg.focus();
        addError("Select Deductee Category");
        scrollPage();
    }
}


function saveContentData() {
    var btnSave = document.getElementById("btnUpdate");
    var cp = document.getElementById("globalcontextpath").value;
    if (!ajax_call_enabled) {
        scrollPage();
        $("#deductee_entry_page").submit(function (e)
        {
            var postData = $(this).serializeArray();
            var formURL = "getDeducteeCRUDEAction15gh?action=save15GHTranEntry";
            $.ajax(
                    {
                        url: formURL,
                        type: "POST",
                        data: postData,
                        success: function (data, textStatus, jqXHR)
                        {
                            console.log("Data------------------:" + data);
                            //data: return data from server                            
                            ajax_call_enabled = false;
                            if (data.match("saveSuccess")) {
                                // window.location = cp + "/deducteesAction15gh?action=add";
                               // openSuccessModal("Record Saved Successfully");
                                  let url = cp + "/form15GHTransaction";
                                openSuccessModal("Record Saved Successfully", "window.location='" + url + "'");
                            } else {
//                                openErrorModal("Some Error Occured , Please Try Again");
                                addActionError("message", data);
                                enableButton(btnSave);
                                scrollPage();
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown)
                        {
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
        $("#deductee_entry_page").submit(); //Submit  the FORM
    } else {
        window.alert("Please wait..\nYour previous request is in progress");
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

function scrollPage() {
    $("html, body").animate({
        scrollTop: 50
    }, 900);
}//end function



function checkMendatoryField() {
    var form_filed = document.getElementById("form_filed").value;
    var aggregate_amount = document.getElementById("aggregate_amount").value;
    if ((form_filed !== "" && form_filed !== null && form_filed !== "null") && (aggregate_amount === "" || aggregate_amount === "null" || aggregate_amount === "")) {
//        document.getElementById("aggregate_amount").focus();
//        addError("Aggregate amount of income(other then this) Is Mandatory");
//        addFieldError("notificationMsgDiv", "Aggregate amount of income(other then this) Is Mandatory");
        addActionError("message", "Aggregate amount of income(other then this) Is Mandatory");
        scrollPage();
    } else if ((aggregate_amount !== "" && aggregate_amount !== null && aggregate_amount !== "null") && (form_filed === "" || form_filed === "null" || form_filed === "")) {
//        document.getElementById("form_filed").focus();
        addError("Total number of form filed(Other then this) Is Mandatory");
        scrollPage();
    }
}//end function


function isNull(value) {
    value = value.trim();
    if (value !== "" && value !== "null" && value !== null)
        return false;
    else
        return true;
}//end function


function onChangeFileType() {
    var fileType = document.getElementById("fileType").value;
    if (!isNull(fileType)) {
        if (fileType === 'D') {
            document.getElementById("dobSpan").style.display = "inline-block";
        } else {
            document.getElementById("dobSpan").style.display = "none";
        }
    }
}// end method


function validateIdentificationNO() {
    var identificationNO = document.getElementById("identificationNO").value;
    if (identificationNO !== null || identificationNO !== "" || identificationNO !== "null") {
        var Exp = /[^a-zA-Z0-9]/;
        if (!Exp.test(identificationNO)) {
            document.getElementById("identificationNO").value = '';
            addError('Input is not alphanumeric');
            document.getElementById("identificationNO").focus();
            scrollPage();
            document.getElementById("identificationNO").value = identificationNO;
            return false;
        } else {
            removeError();
        }
    } else {
        removeError();
    }
    return true;
}//end method


function getnamedata(id) {

    var panno = document.getElementById(id).value;
    document.getElementById(id).value = panno.toUpperCase();
    var cp = document.getElementById("contextPath").value;
    var action = document.getElementById("action").value;
    var callingurl = cp + "/deducteesAction15gh?action=findName" + "&l_panno=" + encodeURIComponent(panno);
    $.ajax({
        url: callingurl,
        context: document.body,
        success: function (result) {
            if (result === null || result === "null" || result === "") {
                try {
                    //document.getElementById("NameAsPerPAN").value = "";
                    // document.getElementById("NameAsPerPAN").className += " customfieldError"; //add the class .customfieldError to your element
                    if (action !== "" && action !== "null" && action !== null && action !== 'edit') {
                        document.getElementById("fltrDeducteeName").value = "";
                        document.getElementById("fltrDeducteeName").className += " customfieldError"; //add the class .customfieldError to your element
                    }
                } catch (err) {
                }
            } else {
                var splitResult = result;
                try {
                    //document.getElementById("NameAsPerPAN").value = splitResult;
                    //document.getElementById("deductee_name").placeholder = 'Deductee Name';
                    document.getElementById("fltrDeducteeName").placeholder = 'Deductee Name';
                    document.getElementById("fltrDeducteeName").value = splitResult.toUpperCase();
                } catch (err) {
                }
                //checkSameName("deductee_name");
                //$("#deductee_name").removeClass("customcustomfieldError");
            }
        }
    }
    );
}//end function 



function callRefrenceNoFunction() {
//    var tds_type_code = document.getElementById("s_tds_type_code").value;
//    if (tds_type_code !== "" && tds_type_code !== "null" && tds_type_code !== null && tds_type_code === '26Q') {
//        var deductee_name = document.getElementById("fltrDeducteeName").value;
//        var formURL = "dropdownListDataAction15gh?action=getAllDeducteeAsName";
//        $.ajax({
//            url: formURL,
//            type: "POST",
//            data: {
//                deducteeName: deductee_name
//            },
//            success: function(result) {
//                //data: return data from server 
////                alert(result);
//                if (result !== "" && result !== "null" && result !== null) {
//                    getRefrencNo(result);
//                }
//            }
//        });
//    }
//
//     var panno = document.getElementById("panno").value;
//     if (panno !== "" && panno !== "null" && panno !== null) {
//       getRefrencNo(panno);
//     }
}//end function


function setAllChecked(checkbox) {

    var isChecked = $(checkbox).prop("checked");
    $('#table tbody:has(tr:has(td))').find('input[type="checkbox"]').prop('checked', isChecked);
    if (isChecked) {

        singleCheckBtnDisplay(false);
        $(".action-section").show(true);

    } else {

        $(".action-section").hide(true);

    }
}

function onClickAllCheckbox() {

    var tbl = document.getElementById("refranceNoTypeTwoTableId");
    var total_record = tbl.rows.length;
    var checked = document.getElementById("allchk").checked;
    if (checked) {
        for (var j = 1; j < total_record; j++) {
            document.getElementById("chk~" + j).checked = true;
            document.getElementById("editCheckBox~" + j).value = "true";
        }
    } else {
        for (var j = 1; j < total_record; j++) {
            document.getElementById("chk~" + j).checked = false;
            document.getElementById("editCheckBox~" + j).value = "false";
        }
    }
}//end function