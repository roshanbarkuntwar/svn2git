
$(document).ready(function () {
    $('[data-toggle="tooltip"]').tooltip();
});
function selectClientCatg() {
    var deductor_type = document.getElementById("client_catg_name").value;
    var pan = "";
    try {
        pan = document.getElementById("pannoID").value;
    } catch (err) {
        pan = document.getElementById("panno").value;
    }

    if (pan.toUpperCase() === 'PANNOTREQD') {
//    if (pan === 'PANNOTREQD') {
        deductor_type = "PANNOTREQD";
    }
    var cp = document.getElementById("globalcontextpath").value;
    var h_client_catg_code = document.getElementById("h_client_catg_code").value;
    var callingurl = cp + "/getClientCategoryList?action=select_client_catg_list" + "&deductor_type_code=" + encodeURIComponent(deductor_type) + "&hiddenClientCatgCode=" + encodeURIComponent(h_client_catg_code);
//    var callingurl = cp + "/getClientCategoryList?action=select_client_catg_list" + "&deductor_type_code=" + encodeURIComponent(deductor_type)+ "&hiddenClientCatgCode=" + encodeURIComponent(h_client_catg_code);
    $.ajax({
        url: callingurl,
        context: document.body,
        success: function (result) {
            if (result !== null && result !== "null" && result !== "") {
                document.getElementById("client_catgID").innerHTML = result;
                try {
                    validateMinistryDetails();
                } catch (err) {
                }
            } else {
                document.getElementById("client_catgID").innerHTML = "<option value=\"\">--Select--</option>";
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('inside error......');
        }
    });


}//end function
function check_deductor_type(id) {
    try {
        var pannoID = document.getElementById(id).value;
    } catch (err) {
        var pannoID = id;
    }
//    alert(pannoID);
// added on 02.02.2017
    try {
// empty ministry details when not G
        var fourthChar = pannoID.charAt(3);
        if (!isNull(fourthChar) && fourthChar !== 'G') {
            document.getElementById("ministry_state_code").value = "";
            document.getElementById("ministry_code").value = "";
            document.getElementById("sub_ministry_code").value = "";
            document.getElementById("ddo_code").value = "";
            document.getElementById("pao_code").value = "";
            document.getElementById("ain_no").value = "";
        }
    } catch (err) {

    }
    if (pannoID !== "" && pannoID !== "NULL" && pannoID !== null && pannoID.toUpperCase() !== "PANNOTREQD") {
//    if (pannoID !== "" && pannoID !== "NULL" && pannoID !== null && pannoID !== "PANNOTREQD") {
        var cp = document.getElementById("contextPath").value;
//        var callingurl = cp + "/getClientCategoryList?action=setDeducteeType" + "&PANNO=" + encodeURIComponent(pannoID);
        var callingurl = cp + "/getClientCategoryList?action=setDeducteeTypeList" + "&PANNO=" + encodeURIComponent(pannoID);
        $.ajax({
            url: callingurl,
            success: function (result) {

                document.getElementById("client_catg_name").value = result;
                selectClientCatg();
            }
        });
    } else {
        try {
            document.getElementById("client_catg_name").value = '';
            selectClientCatg();
        } catch (err) {
        }
    }
}//end method

function validateDeductorPANNo(field) {
    var value = field.value;
    if (value === "PANNOTAVBL") {

        return true;
    } else {
        if (value !== "" && value !== "null" && value !== null) {
//            var Exp = /^([a-zA-Z]){3}(a|A|p|P|f|F|c|C|h|H|t|T){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
            var Exp = /^([a-zA-Z]){3}(g|G|c|C|l|L|a|A|t|T|j|J|b|B|p|P|f|F|h|H){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
            if (!Exp.test(value)) {
                if (value.toUpperCase() === "PANNOTREQD") {

                    return true;
                } else {
                    $("#deductor_notification").attr("class", "errorMessage");
                    addActionError("Invalid PAN Number");
                    field.focus();
                    return false;
                }
            } else {

                return true;
            }
        } else {

            return false;
        }
    }
}

function PanNoIsBlank() {
    var pan_no = document.getElementById("panno").value;
//    if (isNull(pan_no)) {
//        document.getElementById("popUpSpan").style.visibility = "visible";
////        document.getElementById("popUpSpan").style.display = "inline";
//    } else {
//        document.getElementById("popUpSpan").style.display = "none";
//    }
}// end method
function updateDataByAjax(id, val) {
//document.getElementById(id).disabled = true;
    var cp = document.getElementById("contextPath").value;
    document.getElementById('saveAndExit').value = val;
    var objActiveClass = $('ul.nav-pills li.mr-5 a.active');
    var activeClassHref = $(objActiveClass).attr('href');
    var isValidated = true;
    if (!lhsIsNull(activeClassHref)) {
        if (activeClassHref === '#permanentInformation') {
// call validation method
            isValidated = validateParmanentData();
        } else if (activeClassHref === '#deductorAddress') {
            isValidated = validateAddressData();
        } else if (activeClassHref === '#goverment') {
          var mincode =  document.getElementById("ministry_state_code").value;
            if (!lhsIsNull(mincode)) {
          document.getElementById("client_min_code").value = mincode;  
            }
            isValidated = saveResetDedoctorInfoLevel();
        } else if (activeClassHref === '#responsiblePersonContactDetails') {
            isValidated = validateContactData();
        }
    }
    if (isValidated) {
        removeError();
        submitForm(id, activeClassHref);
        // end result
    }
//    resetDedoctorInfoLevel();
}//end function
function submitForm(id, active) {
    document.getElementById("activeTab").value = active;
    var cp = document.getElementById("contextPath").value;
    if (!ajax_call_enabled) {
        ajax_call_enabled = true;
        $("#DeductorManipulationActionFormID").submit(function (e) {
            var activeTab = document.getElementById("activeTab").value;
            var gValue = document.getElementById('saveAndExit').value;
            var deductee_catg = document.getElementById("client_catg_name").value;
            var postData = $(this).serializeArray();
            var formURL = $(this).attr("action");
            $.ajax({
                url: formURL,
                type: "POST",
                data: postData,
                beforeSend: function (xhr) {
                    showProcessIndicator();
                },
                success: function (data, textStatus, jqXHR) {
                    if (!lhsIsNull(data) && data === 'success') {
                        if (activeTab === '#permanentInformation') { // go to address

                            if (gValue === 'saveAndExit') {
                                try {
                                    parentFlag = document.getElementById("parentFlag").value;
                                    childFlag = document.getElementById("childFlag").value;
                                } catch (err) {
                                }
//                                window.location = cp + "/dashboard";
                                openSuccessModal("Record Updated Successfully", 'window.location="deductors";');
                            } else {
                                document.getElementById("perInfo").href = "#";
                                document.getElementById("addInfo").href = "#deductorAddress";
                                $("a[href='#deductorAddress']").tab('show');
                                document.getElementById("previous").style.visibility = "visible";
                                document.getElementById("hdnInfoLevel").value = "AddressInfo";
                                // check for Government
                                var isMinistry = checkMinistry();
                                if (isMinistry) {
                                    $("#minInfoLi").removeAttr("style");
                                    document.getElementById("minInfoLi").style.display = "inline-list-item";
                                    document.getElementById("minInfoLi").style.pointerEvents = "none";
                                } else {
                                    document.getElementById("minInfoLi").style.display = "none";
                                }
                            }

                        } else if (activeTab === '#deductorAddress') {
                            
                            var statecode= document.getElementById("client_min_code").value;
                            document.getElementById("ministry_state_code").value = statecode;
                            document.getElementById("notificationMsgDiv").style.display = "none";
                            if (gValue === 'saveAndExit') {
                                try {
                                    parentFlag = document.getElementById("parentFlag").value;
                                    childFlag = document.getElementById("childFlag").value;
                                    openSuccessModal("Record Updated Successfully");
                                } catch (err) {
                                }
                                window.location = cp + "/dashboard";
                            } else {
                                var isMinistry = checkMinistry(); // check for Government
                                if (isMinistry) {   // go to ministry
                                    document.getElementById("addInfo").href = "#";
                                    document.getElementById("minInfo").href = "#goverment";
                                    $("a[href='#goverment']").tab('show');
                                    document.getElementById("previous").style.visibility = "visible";
                                    document.getElementById("hdnInfoLevel").value = "MinistryInfo";
                                    if (deductee_catg == 'L' || deductee_catg == 'A') {
                                        document.getElementById("ministry_state_code").removeAttribute('disabled');
                                        document.getElementById("ain_no").removeAttribute('disabled');
                                        document.getElementById('AinLbl').style.display = "inline-block";
                                    }
                                } else {  // go to contact
                                    document.getElementById("addInfo").href = "#";
                                    document.getElementById("conInfo").href = "#responsiblePersonContactDetails";
                                    $("a[href='#responsiblePersonContactDetails']").tab('show');
                                    document.getElementById("previous").style.visibility = "visible";
                                    document.getElementById("saveBtn").value = "Save";
                                    document.getElementById("hdnInfoLevel").value = "ContactInfo";
                                }
                            }
                        } else if (activeTab === '#goverment') {  // go to contact
                          
                            var statecode= document.getElementById("client_min_code").value;
                            document.getElementById("ministry_state_code").value = statecode;
                            document.getElementById("notificationMsgDiv").style.display = "none";
                            if (gValue === 'saveAndExit') {
                                try {
                                    parentFlag = document.getElementById("parentFlag").value;
                                    childFlag = document.getElementById("childFlag").value;
                                    openSuccessModal("Record Updated Successfully");
                                } catch (err) {
                                }
                                window.location = cp + "/dashboard";
                            } else {

                                document.getElementById("minInfo").href = "#";
                                document.getElementById("conInfo").href = "#responsiblePersonContactDetails";
                                $("a[href='#responsiblePersonContactDetails']").tab('show');
                                document.getElementById("previous").style.visibility = "visible";
                                document.getElementById("saveBtn").value = "Save";
                                document.getElementById("hdnInfoLevel").value = "ContactInfo";
                            }
                        } else if (activeTab === '#responsiblePersonContactDetails') {   // go to permanent
                            document.getElementById("notificationMsgDiv").style.display = "none";
                            if (gValue === 'saveAndExit') {
                                try {
                                    parentFlag = document.getElementById("parentFlag").value;
                                    childFlag = document.getElementById("childFlag").value;
                                    openSuccessModal("Record Updated Successfully");
                                } catch (err) {
                                }
                                window.location = cp + "/dashboard";
                            } else {
                                document.getElementById("conInfo").href = "#";
                                document.getElementById("perInfo").href = "#permanentInformation";
                                $("a[href='#permanentInformation']").tab('show');
                                document.getElementById("previous").style.visibility = "hidden";
                                document.getElementById("hdnInfoLevel").value = "PermanentInfo";
                                document.getElementById("saveBtn").value = "Save & Next";
                                $("#deductor_notification").attr("class", "successMessage");
                                openSuccessModal("Record Updated Successfully");
                                try {
                                    if (document.getElementById("copy_deductor_address_flag").checked === true) {
                                        g_add1 = "";
                                        g_add2 = "";
                                        g_add3 = "";
                                        g_add4 = "";
                                        g_add5 = "";
                                        g_add6 = "";
                                        g_add7 = "";
                                        g_stdcode = "";
                                        g_phoneno = "";
                                        document.getElementById("respCntctDtlsAdd1").style.display = "block";
                                        document.getElementById("respCntctDtlsAdd2").style.display = "block";
                                        document.getElementById("respCntctDtlsAdd3").style.display = "block";
                                        document.getElementById("respCntctDtlsAdd4").style.display = "block";
                                        document.getElementById("state_lbl").style.display = "block";
                                        document.getElementById("city_lbl").style.display = "block";
                                        document.getElementById("pin_lbl").style.display = "block";
                                        document.getElementById("phonno_lbl").style.display = "block";
                                        document.getElementById("copy_deductor_address_flag").checked = false;
                                    }

                                } catch (err) {
                                }
                            }
                        }
                    } else if (!lhsIsNull(data) && data === "setassessment") {
                        window.location = cp + "/setassessment";
                    } else { // error
                        $("#deductor_notification").attr("class", "errorMessage");
                        //addError("Some Error Occured, Could Not Update");
                    }

                    document.getElementById(id).disabled = false;
                },
                error: function (jqXHR, textStatus, errorThrown)
                {
                    document.getElementById(id).disabled = false;
                    ajax_call_enabled = false; //if fails
                },
                complete: function (jqXHR, textStatus) {
                    hideProcessIndicator();
                    ajax_call_enabled = false;
                }
            }
            );
            e.preventDefault(); //STOP default action
//            e.unbind(); //unbind. to stop multiple form submit.
        });
//    removeError();
        $("#DeductorManipulationActionFormID").submit(); //Submit  the FORM
    }

}//end function

function pannotreqdCheckbx(id) {
    if (id.checked) {
        document.getElementById("panno").value = "PANNOTREQD";
        document.getElementById("panno").focus();
        document.getElementById("panno").readOnly = true;
        document.getElementById("client_catgID").focus();
    } else {
        document.getElementById("panno").value = "";
        document.getElementById("panno").readOnly = false;
    }
}// end method




function checkMinistry() {
    var returnValue = false;
    try {
        var client_category_type = document.getElementById("client_catg_name").value;
        if (!lhsIsNull(client_category_type) &&
                (client_category_type === 'A' ||
                        client_category_type === 'S' ||
                        client_category_type === 'D' ||
                        client_category_type === 'E' ||
                        client_category_type === 'G' ||
                        client_category_type === 'H' ||
                        client_category_type === 'N' ||
                        client_category_type === 'L'
                        )) {
            returnValue = true;
        }
    } catch (err) {

    }
    return returnValue;
}// end method

function getPreviousTab() {
    removeError();
    var objActiveClass = $('ul.nav-pills li.mr-5 a.active');
    var activeClassHref = $(objActiveClass).attr('href');
    if (!lhsIsNull(activeClassHref)) {
        activeClassHref = activeClassHref.replace("#", "");
        if (activeClassHref === 'deductorAddress') { // go to permanent info
            document.getElementById("addInfo").href = "#";
            document.getElementById("addInfo").removeAttribute("class");
            document.getElementById("perInfo").href = "#permanentInformation";
            $("a[href='#permanentInformation']").tab('show');
            document.getElementById("previous").style.visibility = "hidden";
            document.getElementById("saveBtn").value = "Save & Next";
            document.getElementById("hdnInfoLevel").value = "PermanentInfo";
        } else if (activeClassHref === 'goverment') { // go to address
            document.getElementById("minInfo").href = "#";
            document.getElementById("addInfo").href = "#deductorAddress";
            $("a[href='#deductorAddress']").tab('show');
            document.getElementById("saveBtn").value = "Save & Next";
            document.getElementById("hdnInfoLevel").value = "AddressInfo";
        } else if (activeClassHref === 'responsiblePersonContactDetails') {
            var isMinistry = checkMinistry();
            try {
                if (document.getElementById("copy_deductor_address_flag").checked === true) {
                    g_add1 = "";
                    g_add2 = "";
                    g_add3 = "";
                    g_add4 = "";
                    g_add5 = "";
                    g_add6 = "";
                    g_add7 = "";
                    g_stdcode = "";
                    g_phoneno = "";
                    document.getElementById("respCntctDtlsAdd1").style.display = "block";
                    document.getElementById("respCntctDtlsAdd2").style.display = "block";
                    document.getElementById("respCntctDtlsAdd3").style.display = "block";
                    document.getElementById("respCntctDtlsAdd4").style.display = "block";
                    document.getElementById("state_lbl").style.display = "block";
                    document.getElementById("city_lbl").style.display = "block";
                    document.getElementById("pin_lbl").style.display = "block";
                    document.getElementById("phonno_lbl").style.display = "block";
                    document.getElementById("copy_deductor_address_flag").checked = false;
                }
            } catch (err) {
            }
            setministrySateCode();
            if (isMinistry) {
// go to ministry
                document.getElementById("conInfo").href = "#";
                document.getElementById("minInfo").href = "#goverment";
                $("a[href='#goverment']").tab('show');
                document.getElementById("saveBtn").value = "Save & Next";
                document.getElementById("hdnInfoLevel").value = "MinistryInfo";
               
                
            } else {
// go to address
                document.getElementById("minInfo").href = "#";
                document.getElementById("addInfo").href = "#deductorAddress";
                $("a[href='#deductorAddress']").tab('show');
                document.getElementById("saveBtn").value = "Save & Next";
                document.getElementById("hdnInfoLevel").value = "AddressInfo";
            }
        }
    }
}//end function

function selectAddressState() {

    var country_code = document.getElementById("country_code").value;
    var cp = document.getElementById("globalcontextpath").value;
    var callingurl = cp + "/getClientCategoryList?action=selectState" + "&selectedCountryCode=" + encodeURIComponent(country_code);
    $.ajax({
        url: callingurl,
        context: document.body,
        success: function (result) {
            document.getElementById("address_state_code").innerHTML = result;
            selectAddressCity();
        }
    });
}//end function


function selectAddressCity() {

   var state_code = document.getElementById("address_state_code").value;
    var cp = document.getElementById("globalcontextpath").value;
    var callingurl = cp + "/getClientCategoryList?action=selectCity" + "&selectedStateCode=" + encodeURIComponent(state_code);
    $.ajax({
        url: callingurl,
        context: document.body,
        success: function (result) {
            document.getElementById("address_city_code").innerHTML = result;
        }
    });
}//end function


function selectDeductorState() {
    var country_code = document.getElementById("deducter_country_code").value;
    var cp = document.getElementById("globalcontextpath").value;
    var callingurl = cp + "/getClientCategoryList?action=selectState" + "&selectedCountryCode=" + encodeURIComponent(country_code);
    $.ajax({
        url: callingurl,
        context: document.body,
        success: function (result) {
            document.getElementById("deductor_state_code").innerHTML = result;
            selectDeductorCity();
        }
    });
}//end function

function selectDeductorCity() {
    var state_code = document.getElementById("deductor_state_code").value;
    var cp = document.getElementById("globalcontextpath").value;
    var callingurl = cp + "/getClientCategoryList?action=selectCity" + "&selectedStateCode=" + encodeURIComponent(state_code);
    $.ajax({
        url: callingurl,
        context: document.body,
        success: function (result) {
            document.getElementById("deducter_city_code").innerHTML = result;
        }
    });
}//end function

function getCopyDeductorAddress(id) {
    var checkflag = document.getElementById(id).checked;
    if (checkflag) {
        g_add1 = document.getElementById("deductor_add1").value;
        g_add2 = document.getElementById("deductor_add2").value;
        g_add3 = document.getElementById("deductor_add3").value;
        g_add4 = document.getElementById("deductor_add4").value;
        g_add5 = document.getElementById("deductor_state_code").value;
        g_add6 = document.getElementById("deductor_city_code").value;
        g_add7 = document.getElementById("deductor_pin").value;
        g_stdcode = document.getElementById("deductor_stdcode").value;
        g_phoneno = document.getElementById("deductor_phoneno").value;
        //alert("g_add1.." + g_add1 + "g_add2..." + g_add2 + "g_add3..." + g_add3 + "g_add5..." + g_add5);
        document.getElementById("deductor_add1").value = document.getElementById("add1").value;
        document.getElementById("deductor_add2").value = document.getElementById("add2").value;
        document.getElementById("deductor_add3").value = document.getElementById("add3").value;
        document.getElementById("deductor_add4").value = document.getElementById("add4").value;
//        document.getElementById("deducter_add4").value = document.getElementById("add4").value;
        document.getElementById("deductor_state_code").value = document.getElementById("address_state_code").value;
        document.getElementById("deductor_city_code").value = document.getElementById("address_city_code").value;
        document.getElementById("deductor_pin").value = document.getElementById("pin").value;
        document.getElementById("copyDeductorAddress").value = "Deductor Address Copied !";
        document.getElementById("geo_org_code").value = "T";
        $("#deductor_add1").addClass("copied-field");
        $("#deductor_add2").addClass("copied-field");
        $("#deductor_add3").addClass("copied-field");
        $("#deductor_add4").addClass("copied-field");
        $("#deductor_city_code").addClass("copied-field");
        $("#deductor_pin").addClass("copied-field");
        $("#deductor_state_code").addClass("copied-field");
//
    } else {
//alert("g_add1.." + g_add1 + "g_add2..." + g_add2 + "g_add3..." + g_add3 + "g_add4..." + g_add4);
        $("#deductor_add1").removeClass("copied-field");
        $("#deductor_add2").removeClass("copied-field");
        $("#deductor_add3").removeClass("copied-field");
        $("#deductor_add4").removeClass("copied-field");
        $("#deductor_city_code").removeClass("copied-field");
        $("#deductor_pin").removeClass("copied-field");
        $("#deductor_state_code").removeClass("copied-field");
        document.getElementById("copyDeductorAddress").value = "";
        document.getElementById("deductor_add1").value = "";
        document.getElementById("deductor_add2").value = "";
        document.getElementById("deductor_add3").value = "";
        document.getElementById("deductor_add4").value = "";
        document.getElementById("deductor_state_code").value = "";
        document.getElementById("deductor_city_code").value = "";
        document.getElementById("deductor_pin").value = "";
        document.getElementById("geo_org_code").value = "F";

    }
}//end function


function validatedSelectMinistryCode() {

}

function selectClientTypeAsCatg() {
    var client_catgID = document.getElementById("client_catgID").value;
    if (client_catgID === 'H' || client_catgID === 'N' || client_catgID === 'S' || client_catgID === 'E') {
        document.getElementById("DDO_Code").innerHTML = "*";
        $("#DDO_Code").css("color", "red");
        document.getElementById("PAO_Code").innerHTML = "*";
        $("#PAO_Code").css("color", "red");
        document.getElementById("stateCodeLbl").innerHTML = "*";
        $("#stateCodeLbl").css("color", "red");
        document.getElementById("AinLbl").innerHTML = "*";
        $("#AinLbl").css("color", "red");
    }
    if (client_catgID === 'G' || client_catgID === 'D') {
        document.getElementById("DDO_Code").innerHTML = "*";
        $("#DDO_Code").css("color", "red");
        document.getElementById("PAO_Code").innerHTML = "*";
        $("#PAO_Code").css("color", "red");
        document.getElementById("minstryNameLbl").innerHTML = "*";
        $("#minstryNameLbl").css("color", "red");
        document.getElementById("AinLbl").innerHTML = "*";
        $("#AinLbl").css("color", "red");
    }
    if (client_catgID === 'L') {
        document.getElementById("DDO_Code").innerHTML = "*";
        $("#DDO_Code").css("color", "red");
        document.getElementById("PAO_Code").innerHTML = "*";
        $("#PAO_Code").css("color", "red");
        document.getElementById("AinLbl").innerHTML = "*";
        $("#AinLbl").css("color", "red");
    }
    if (client_catgID === 'A') {
        document.getElementById("AinLbl").innerHTML = "*";
        $("#AinLbl").css("color", "red");
        document.getElementById("minstryNameLbl").innerHTML = "*";
        $("#minstryNameLbl").css("color", "red");
    }
    var cp = document.getElementById("contextPath").value;
    var callingurl = cp + "/getClientCategoryList?action=select_client_type_list_data" + "&deductor_type_catg=" + encodeURIComponent(client_catgID);
    $.ajax({
        url: callingurl,
        context: document.body,
        success: function (result) {
//            alert(result);
            if (result !== null && result !== "null" && result !== "") {
                document.getElementById("client_catg_name").value = result;
            } else {
                document.getElementById("client_catg_name").value = 'N';
            }
        }
    });
}//end function

function validateMinistryDetails() {
    var deductorCategory = document.getElementById('client_catgID').value;
    if (deductorCategory !== "" && deductorCategory !== "null" && deductorCategory !== null) {
        var dedCat1 = deductorCategory;
        var dedCat2 = deductorCategory;
        var dedCat3 = deductorCategory;
        var dedCat4 = deductorCategory;
        if (deductorCategory === 'S' || deductorCategory === 'E' || deductorCategory === 'H' || deductorCategory === 'N') {
            document.getElementById('ministry_state_code').value = " ";
            document.getElementById('ministry_state_code').disabled = false;
            document.getElementById('stateCodeLbl').style.display = "inline-block";
        } else {
            document.getElementById('ministry_state_code').value = " ";
            document.getElementById('stateCodeLbl').style.display = "none";
            document.getElementById('ministry_state_code').disabled = true;
        }

        if (dedCat1 === 'A' || dedCat1 === 'D' || dedCat1 === 'G') {
            document.getElementById('minstryNameLbl').style.display = "inline-block";
        } else {
            document.getElementById('minstryNameLbl').style.display = "none";
        }

        if (dedCat2 === 'S' || dedCat2 === 'D' || dedCat2 === 'E' || dedCat2 === 'G' || dedCat2 === 'H' || dedCat2 === 'L' || dedCat2 === 'N') {
            document.getElementById('pao_code').disabled = false;
            document.getElementById('paoCodeLbl').style.display = "inline-block";
        } else if (dedCat2 === 'A') {
            document.getElementById('pao_code').style.display = "inline-block";
            document.getElementById('paoCodeLbl').disabled = false;
        } else {
            document.getElementById('pao_code').value = "";
            document.getElementById('paoCodeLbl').style.display = "none";
            document.getElementById('pao_code').disabled = true;
        }

        if (dedCat3 === 'S' || dedCat3 === 'D' || dedCat3 === 'E' || dedCat3 === 'G' || dedCat3 === 'H' || dedCat3 === 'L' || dedCat3 === 'N') {
            document.getElementById('ddo_code').disabled = false;
            document.getElementById('DaoCodeLbl').style.display = "inline-block";
        } else if (dedCat3 === 'A') {
            document.getElementById('ddo_code').disabled = false;
        } else {
            document.getElementById('DaoCodeLbl').style.display = "none";
            document.getElementById('ddo_code').value = "";
            document.getElementById('ddo_code').disabled = true;
        }

//        if (dedCat4 === 'A' || dedCat4 === 'S') {
//            document.getElementById('AinLbl').style.display = "inline-block";
//            document.getElementById('ain_no').disabled = false;
//        } else {
//            document.getElementById('ain_no').value = "";
//            document.getElementById('AinLbl').style.display = "none";
//            document.getElementById('ain_no').disabled = true;
//        }

    }//end main if
}//end function

function onChangeMinistryName() {
    var ministry_code = document.getElementById("ministry_code").value;
    if (ministry_code === '99') {
        document.getElementById("ministry_code_other_div").style.display = "block";
    } else {
        document.getElementById("ministry_code_other_div").style.display = "none";
    }
}


function validateParmanentData() {
    var return_value = true;
    var client_name = document.getElementById('client_name').value;
    var client_catg_name = document.getElementById('client_catg_name').value;
    var client_catgID = document.getElementById('client_catgID').value;
    var panno = document.getElementById('panno').value;
    var GST = document.getElementById('gstn_no').value;
    var tanno = document.getElementById('tanno').value;
    var validPan = validateDeductorPANNo(document.getElementById('panno'));
    if (tanno === "" || tanno === "null" || tanno === null) {
        document.getElementById('tanno').focus();
        $("#deductor_notification").attr("class", "errorMessage");
        addActionError('message', 'Enter TAN Number');
        return_value = false;
    } else if (client_name === "" || client_name === "null" || client_name === null) {
        document.getElementById('client_name').focus();
        $("#deductor_notification").attr("class", "errorMessage");
        addActionError('message', 'Enter Deductor Name');
        return_value = false;
    } else if (client_catgID === "" || client_catgID === "null" || client_catgID === null) {
        document.getElementById('client_catgID').focus();
        $("#deductor_notification").attr("class", "errorMessage");
        addActionError('message', 'Enter Deductor Category');
        return_value = false;
    } else if (panno === "" || panno === "null" || panno === null) {
        document.getElementById('panno').focus();
        $("#deductor_notification").attr("class", "errorMessage");
        addActionError('message', 'Enter PAN Number');
        return_value = false;
    } else if (!validPan) {
        document.getElementById('panno').focus();
        $("#deductor_notification").attr("class", "errorMessage");
        addActionError('message', 'Invalid PAN Number');
        return_value = false;
    }else if (!validateTAN(document.getElementById('tanno'))) {
        document.getElementById('tanno').focus();
        return_value = false;
    } 
    return return_value;
}//end function
function validateAddressData() {

    var return_value = true;
    var add1 = document.getElementById('add1').value;
    var stdcode = document.getElementById('stdcode').value;
    var address_state_code = document.getElementById('address_state_code').value;
    var address_city_code = document.getElementById('address_city_code').value;
    var pin = document.getElementById('pin').value;
    var email_id = document.getElementById('email_id').value;
    var phoneno = document.getElementById('phoneno').value;
    if (add1 === "" || add1 === "null" || add1 === null) {
        document.getElementById('add1').focus();
        $("#deductor_notification").attr("class", "errorMessage");
        addActionError('message', 'Enter Deductor Address');
        return_value = false;
    } else if (pin === "" || pin === "null" || pin === null) {
        document.getElementById('pin').focus();
        $("#deductor_notification").attr("class", "errorMessage");
        addActionError('message', "Enter Deductors's Pin Code");
        return_value = false;
    } else if (address_city_code === "" || address_city_code === "null" || address_city_code === null) {
        document.getElementById('address_city_code').focus();
        $("#deductor_notification").attr("class", "errorMessage");
        addActionError('message', 'Enter Deductor City');
        return_value = false;
    } else if (address_state_code === "" || address_state_code === "null" || address_state_code === null) {
        document.getElementById('address_state_code').focus();
        $("#deductor_notification").attr("class", "errorMessage");
        addActionError('message', 'Select Deductor State');
        return_value = false;
    }
//    else if ((stdcode !== "" || stdcode !== "null" || stdcode !== null) && (phoneno === "" || phoneno === "null" || phoneno === null)) {
//        document.getElementById('phoneno').focus();
//        $("#deductor_notification").attr("class", "errorMessage");
//        addActionError('message', 'Enter Deductor Telephone No');
//        return_value = false;
//    }
    else if (email_id === "" || email_id === "null" || email_id === null) {
        document.getElementById('email_id').focus();
        $("#deductor_notification").attr("class", "errorMessage");
        addActionError('message', "Enter Deductor's Email");
        return_value = false;
    } else {
        return_value = true;
    }

    return return_value;
}


function saveResetDedoctorInfoLevel(val) {
    var return_value = true;
//    document.getElementById('saveAndExit').value = val;
//    resetDedoctorInfoLevel();

    var deductorCategory = document.getElementById('client_catgID').value;
    if (deductorCategory !== "" && deductorCategory !== "null" && deductorCategory !== null) {
        var dedCat1 = deductorCategory;
        var dedCat2 = deductorCategory;
        var dedCat3 = deductorCategory;
        var dedCat4 = deductorCategory;
        dedCat4 = document.getElementById('client_catg_name').value;
        if (dedCat4 === 'A' || dedCat4 === 'L') {

            var ain_no = document.getElementById('ain_no').value;
            if (ain_no === "" || ain_no === "null" || ain_no === null) {
                document.getElementById('ain_no').focus();
                $("#deductor_notification").attr("class", "errorMessage");
                addActionError('message', 'AIN No cannot be left blank');
                return_value = false;
            } else {
                document.getElementById('ain_no').disabled = false;
            }
        } else {
            document.getElementById('ain_no').value = "";
            document.getElementById('ain_no').disabled = true;
        }
    } else {
        document.getElementById('ministry_state_code').disabled = false;
    }



    if (dedCat2 === 'S' || dedCat2 === 'D' || dedCat2 === 'E' || dedCat2 === 'G' || dedCat2 === 'H' || dedCat2 === 'L' || dedCat2 === 'N') {

        var pao_code = document.getElementById('pao_code').value;
        if (pao_code === "" || pao_code === "null" || pao_code === null) {
            document.getElementById('pao_code').focus();
            $("#deductor_notification").attr("class", "errorMessage");
            addActionError('message', 'Enter PAO Code');
            return_value = false;
        } else {
            document.getElementById('pao_code').disabled = false;
        }
    } else if (dedCat2 === 'A') {

        document.getElementById('pao_code').disabled = false;
    } else {
        document.getElementById('pao_code').value = "";
        document.getElementById('pao_code').disabled = true;
    }


    if (dedCat3 === 'S' || dedCat3 === 'D' || dedCat3 === 'E' || dedCat3 === 'G' || dedCat3 === 'H' || dedCat3 === 'L' || dedCat3 === 'N') {
        var ddo_code = document.getElementById('ddo_code').value;
        if (ddo_code === "" || ddo_code === "null" || ddo_code === null) {
            document.getElementById('ddo_code').focus();
            $("#deductor_notification").attr("class", "errorMessage");
            addActionError('message', 'Enter DDO Code');
            return_value = false;
        } else {
            document.getElementById('ddo_code').disabled = false;
        }
    } else if (dedCat3 === 'A') {

        document.getElementById('ddo_code').disabled = false;
    } else {
        document.getElementById('ddo_code').value = "";
        document.getElementById('ddo_code').disabled = true;
    }

    if (dedCat1 === 'A' || dedCat1 === 'D' || dedCat1 === 'G') {

        var ministry_code = (document.getElementById('ministry_code').value).trim();
        if (ministry_code === "" || ministry_code === "null" || ministry_code === null) {
            document.getElementById('ministry_code').focus();
            $("#deductor_notification").attr("class", "errorMessage");
            addActionError('message', 'Select Ministry Name');
            return_value = false;
        }
    }
    if (deductorCategory === 'S' || deductorCategory === 'E' || deductorCategory === 'H' || deductorCategory === 'N') {
        var ministry_state_code = (document.getElementById('ministry_state_code').value).trim();
        if (ministry_state_code === "" || ministry_state_code === "null" || ministry_state_code === null) {
            document.getElementById('ministry_state_code').focus();
            $("#deductor_notification").attr("class", "errorMessage");
            addActionError('message', 'Select State Code');
            return_value = false;
        } else {
            document.getElementById('ministry_state_code').disabled = false;
        }
    } else {
        document.getElementById('ministry_state_code').disabled = false;
    }
    return return_value;
}//end main if

//}// end method

function validateContactData() {


//document.getElementById('saveAndExit').value = val;
    var return_value = true;
    // deductor resposible person contact details tab
    var deductor_name = document.getElementById('deductor_name').value;
    var deductor_desig = document.getElementById('deductor_desig').value;
    var deductor_panno = document.getElementById('deductor_panno').value;
    var deductor_add1 = document.getElementById('deductor_add1').value;
    var deductor_state_code = document.getElementById('deductor_state_code').value;
    var deductor_city_code = document.getElementById('deductor_city_code').value;
    var deductor_pin = document.getElementById('deductor_pin').value;
    var deductor_mobileno = document.getElementById('deductor_mobileno').value;
    var validated = validateResponsiblePersonPANNo(document.getElementById("deductor_panno"));
    var deductor_stdcode = document.getElementById('deductor_stdcode').value;
    var deductor_phoneno = document.getElementById('deductor_phoneno').value;
    if ((deductor_name === "" || deductor_name === "null" || deductor_name === null)) {
        document.getElementById('deductor_name').focus();
        $("#deductor_notification").attr("class", "errorMessage");
        addActionError("message", "Enter Responsible Person Name");
        return_value = false;
    } else if ((deductor_panno === "" || deductor_panno === "null" || deductor_panno === null)) {
        document.getElementById('deductor_panno').focus();
        $("#deductor_notification").attr("class", "errorMessage");
        addActionError("message", 'Enter PAN Of Resp.Person');
        return_value = false;
    } else if ((deductor_desig === "" || deductor_desig === "null" || deductor_desig === null)) {
        document.getElementById('deductor_desig').focus();
        $("#deductor_notification").attr("class", "errorMessage");
        addActionError("message", 'Enter Designation of Resp. Person');
        return_value = false;
    } else if ((deductor_mobileno === "" || deductor_mobileno === "null" || deductor_mobileno === null)) {
        document.getElementById('deductor_mobileno').focus();
        $("#deductor_notification").attr("class", "errorMessage");
        addActionError('message', "Enter Resp.Person's Mobile No.");
        return_value = false;
    } else if ((deductor_stdcode === "" || deductor_stdcode === "null" || deductor_stdcode === null) && (deductor_phoneno !== "" || deductor_phoneno !== "null" || deductor_phoneno !== null)) {
        document.getElementById('deductor_stdcode').focus();
        $("#deductor_notification").attr("class", "errorMessage");
        addActionError('message', 'Enter Deductor  Std No');
        return_value = false;
    } else if ((deductor_stdcode !== "" || deductor_stdcode !== "null" || deductor_stdcode !== null) && (deductor_phoneno === "" || deductor_phoneno === "null" || deductor_phoneno === null)) {
        document.getElementById('deductor_phoneno').focus();
        $("#deductor_notification").attr("class", "errorMessage");
        addActionError('message', 'Enter Deductor Telephone No');
        return_value = false;
    } else if (!validated) {
        return false;
    } else if ((deductor_add1 === "" || deductor_add1 === "null" || deductor_add1 === null)) {
        document.getElementById('deductor_add1').focus();
        $("#deductor_notification").attr("class", "errorMessage");
        addActionError("message", 'Enter Address Of Resp.Person');
        return_value = false;
    } else if ((deductor_pin === "" || deductor_pin === "null" || deductor_pin === null)) {
        document.getElementById('deductor_pin').focus();
        $("#deductor_notification").attr("class", "errorMessage");
        addActionError('message', 'Enter Responsible Persons City Pin Code');
        return_value = false;
    } else if ((deductor_city_code === "" || deductor_city_code === "null" || deductor_city_code === null)) {
        document.getElementById('deductor_city_code').focus();
        $("#deductor_notification").attr("class", "errorMessage");
        addActionError('message', 'Enter Resp. Person City');
        return_value = false;
    } else if ((deductor_state_code === "" || deductor_state_code === "null" || deductor_state_code === null)) {
        document.getElementById('deductor_state_code').focus();
        $("#deductor_notification").attr("class", "errorMessage");
        addActionError('message', 'Select Resp.Person State');
        return_value = false;
    }
    return return_value;
}

function validateResponsiblePersonPANNo(field) {
    var value = field.value;
    if (value === "PANNOTAVBL") {
        removeError();
        return true;
    } else {
        if (value !== "" && value !== "null" && value !== null) {
            var Exp = /^([a-zA-Z]){3}(p|P){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
//            var Exp = /^([a-zA-Z]){3}(g|G|c|C|l|L|a|A|t|T|j|J|b|B|p|P|f|F|h|H){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
            if (!Exp.test(value)) {
                if (value.toUpperCase() === "PANNOTREQD") {
//removeError();
                    return true;
                } else {
                    $("#deductor_notification").attr("class", "errorMessage");
                    addActionError("message", "Invalid PAN Number");
                    field.focus();
                    // scrollPage();
                    return false;
                }
            } else {
// removeError();
                return true;
            }
        } else {
//removeError();
            return false;
        }
    }
}//end function

function getDeductorNameFromCsiFile() {

    var cp = document.getElementById("contextPath").value;
    showProcessIndicator();
    $.ajax({
        url: cp + "/getCsiFileDeductorName",
        type: 'POST',
        success: function (data) {
            if (!lhsIsNull(data)) {
//                document.getElementById("browse_process_indicator_autocomplete").style.visibility = "hidden";
                document.getElementById("client_name").value = data;
                document.getElementById("client_name").style.fontWeight = "900";
                document.getElementById("client_name").style.color = "green";
            } else {
//                document.getElementById("client_name").placeholder = "TAN NO NOT AVAILABLE AT ITD";
                openErrorModal('No CSI file found on OLTAS, please enter deductor name');
            }
            hideProcessIndicator();
        }
    });
}

function validateUpdateBtn() {
    var file = document.getElementById("validatedCustomFile").value;
    let fileLength;
    if (!lhsIsNull(file)) {
        fileLength = document.getElementById("validatedCustomFile").files[0].size;
    }

    if (file === null || file === "") {
        openErrorModal("Select valid .tds or .txt file first",'window.location="deductors";');
        return;
    } else if (fileLength <= 0) {
        openErrorModal("Select valid TDS file",'window.location="deductors";');
        return;
    } else {
        updateDeductorFromTdsFile();
    }
}

function updateDeductorFromTdsFile() {
    let formPrevAction = $('form[name=update_allDeductor]').attr("action");
    $('form[name=update_allDeductor]').attr("action", "getClientCategoryList?action=uploadConsolidatedTdsFileAction");
    $('form[name=update_allDeductor]').attr("enctype", "multipart/form-data");

    ajaxSubmitPostData('/getClientCategoryList?action=uploadConsolidatedTdsFileAction', 'DeductorManipulationActionFormID',
            fileResponse);

    function fileResponse(response) {
//        alert(response);
        if (!lhsIsNull(response)) {
            if (response.includes('error##')) {
                openErrorModal(response.replace('error##', ''));
            } else if (response === 'error') {
                openErrorModal('Some error occurred','window.location="deductors";');
            } else {
                openSuccessModal('Import from consolidated/text file completed successfully', 'window.location="deductors";');
            }
        }
        $('form[name=update_allDeductor]').attr("action", formPrevAction);
        $('form[name=update_allDeductor]').removeAttr("enctype");
    }
}

function validateGst(field) {
    if (!lhsIsNull(field.value))
    {
        var gstValue = field.value;
        var reggst = /^([0-9]){2}([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}([0-9]){1}([a-zA-Z]){1}([0-9]){1}?$/;
        if (!reggst.test(gstValue) && gstValue != '' && gstValue.length != 15) {
            openErrorModal("GST No. is not valid.");
            field.value = "";
            return false;
        }

    }

}

function validateEmail(emailField) {
    if (!lhsIsNull(emailField.value)) {
//        var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

        var reg = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        if (reg.test(emailField.value) === false)
        {
            openErrorModal("Invalid Email id.");
            emailField.value = "";
            return false;
        }
    }
}//end method


function validateTAN(field) {
    var value = field.value;

    if (!lhsIsNull(value)) {
        var Exp = /^([a-zA-Z]){4}([0-9]){5}([a-zA-Z]){1}?$/;
        if (!Exp.test(value)) {
            openErrorModal("Invalid TAN no.");
            field.value = "";
            return false;
        }else{
            return true;
        }
    }

}//end function

function validatePAN(field) {
    var value = field.value;
    if (value === "PANNOTREQD" || value === "pannotreqd") {
        return true;
    } else {
        if (!lhsIsNull(value)) {
//            var Exp = /^([a-zA-Z]){3}(a|A|p|P|f|F|c|C|h|H|t|T){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
            var Exp = /^([a-zA-Z]){3}(a|A|b|B|c|C|f|F|g|G|h|H|j|J|k|K|l|L|p|P|t|T){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
            if (!Exp.test(value)) {
                openErrorModal("Invalid PAN");
                field.value = "";
                return false;
            }
        }
    }
}//end function

function pinCode(field)
{
    var validPin = /(^\d{6}$)/;
    if (validPin.test(field.value))
    {
        return true;

    } else
    {
        openErrorModal("Invalid PIN code");
        field.value = "";
        return false;
    }
}// end function
function validateMobile(field) {
    var validMobile = /(^\d{10}$)/;
    if (validMobile.test(field.value))
    {
        return true;

    } else
    {
        openErrorModal("Invalid Mobile Number");
        field.value = "";
        return false;
    }
}


function validateDDo(field) {
    var ddo = field.value;
    if (ddo === '') {
        return false;
    }
    if (ddo.length < 3) {
        openErrorModal("Invalid DDO Code");
        $("#ddo_code").val("");
    }
    if (ddo.length > 20) {
        openErrorModal("Invalid DDO Code");
        $("#ddo_code").val("");
    }
}

function validatePAO(field) {
    var pao = field.value;
    if (pao === '') {
        return false;
    }
    if (pao.length < 3) {
        openErrorModal("Invalid PAO Code");
        $("#pao_code").val("");
    }
    if (pao.length > 20) {
        openErrorModal("Invalid PAO Code");
        $("#pao_code").val("");
    }
}

function validateAIN(field) {
    var ain = field.value;
    if (ain === '') {
        return false;
    }
    if (ain.length < 7) {
        openErrorModal("Invalid AIN Code");
        $("#ain_no").val("");
    }
}

  function  setministrySateCode(){
      var statecode= document.getElementById("client_min_code").value;
      if(!lhsIsNull(statecode)){
        
      document.getElementById("ministry_state_code").value = statecode;
       }
  }