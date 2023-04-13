/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var cp = document.getElementById("globalcontextpath").value;

function saveNewBranchDataAjax(btnAction) {
    var activeTab = $('ul.nav-pills li.mr-5 a.active');
    var activeClassHref = $(activeTab).attr('href');

    if (!lhsIsNull(activeClassHref)) {

        if (btnAction === 'saveNext') {
            if (activeClassHref === '#branchDetails') {
                if (validateBranchDetails()) {
                    removeError();

                    $('a[href=#branchAddress]').tab('show');
                    $('#previousBtn').css('display', 'inline-block');
                }
            } else if (activeClassHref === '#branchAddress') {
                if (validateBranchAddress()) {
                    removeError();

                    $('a[href=#responsiblePersonDetails]').tab('show');
                    $('#saveNextBtn').css('display', 'none');
                    $('#saveExitBtn').css('display', 'inline-block');
                }
            }
        } else if (btnAction === 'saveExit') {
            if (validateResposiblePersonDetails()) {
                removeError();

                submitForm("branchGenerationForm");// after successful validations submitting form
            }
        }
    }
}//end function

function goToPreviousTab() {

    $('#saveNextBtn').css('display', 'inline-block');
    $('#saveExitBtn').css('display', 'none');

    var activeTab = $('ul.nav-pills li.mr-5 a.active');
    var activeClassHref = $(activeTab).attr('href');

    if (!lhsIsNull(activeClassHref)) {
        if (activeClassHref === '#responsiblePersonDetails') {
            $('a[href=#branchAddress]').tab('show');
            $('#previousBtn').css('display', 'inline-block');
        }
        if (activeClassHref === '#branchAddress') {
            $('a[href=#branchDetails]').tab('show');
            $('#previousBtn').css('display', 'none');
        }
    }
}//end function

function submitForm(formId) {
    formId = '#' + formId;

    $(formId).on("submit", function (e) {

        var formMode = $('#formMode').val();
        var formData = $(formId).serializeArray();
        var actrionUrl = $(formId).attr('action');

        $.ajax({
            url: actrionUrl,
            type: 'POST',
            data: formData,
            beforeSend: function (xhr) {
                showProcessIndicator();
            },
            success: function (data, textStatus, jqXHR) {
                if (formMode === 'saveMode') {
                    if (!lhsIsNull(data) && data.search('success') !== -1) {
                        openSuccessModal(globalSaveMessage + "\nClient code : " + data.split('~')[1], "callUrl('/branchGenerationBrowse');");
                    } else {
                        openErrorModal(globalErrorMessage, "callUrl('/branchGeneration');");
                    }
                } else {
                    if (!lhsIsNull(data) && data.search('success') !== -1) {
                        openSuccessModal(globalUpdateMessage, "callUrl('/branchGenerationBrowse');");
                    } else {
                        openErrorModal(globalErrorMessage, "callUrl('/branchGeneration');");
                    }
                }

            },
            error: function (jqXHR, textStatus, errorThrown) {
                ajax_call_enabled = false;
                hideProcessIndicator();
                console.log('error - ' + jqXHR);
            },
            complete: function (jqXHR, textStatus) {
                ajax_call_enabled = false;
                hideProcessIndicator();
                console.log('complete - ' + jqXHR);
            }
        });
        e.preventDefault();
        e.unbind();
    });
//
    $(formId).submit();
}//end function

function validateBranchDetails() {
    var return_value = true;

    var bank_code_level = document.getElementById('bank_code_level').value;
    var bank_branch_code = document.getElementById('bank_branch_code').value;
    var imm_bank_branch_code = document.getElementById('imm_bank_branch_code').value;
    var imm_branch_required_flag = document.getElementById('immBranchRequiredFlag').value;
    var bank_branch_name = document.getElementById('bank_branch_name').value;
    var client_bank_name = document.getElementById('client_bank_name').value;

    try {
        var login_id = document.getElementById('login_id').value;
        var login_password = document.getElementById('login_password').value;
    } catch (e) {
    }

    if (bank_code_level === "" || bank_code_level === "null" || bank_code_level === null) {
        document.getElementById('bank_code_level').focus();
        addActionError('message', 'Select Code Level');
        return_value = false;

    } else if (bank_branch_code === "" || bank_branch_code === "null" || bank_branch_code === null) {
        document.getElementById('bank_branch_code').focus();
        addActionError('message', 'Enter Bank Branch Code');
        return_value = false;

    } else if ((imm_bank_branch_code === "" || imm_bank_branch_code === "null" || imm_bank_branch_code === null) && imm_branch_required_flag === 'true') {
        document.getElementById('imm_bank_branch_code').focus();
        addActionError('message', 'Enter Immediate Bank Branch Code');
        return_value = false;

    } else if (bank_branch_name === "" || bank_branch_name === "null" || bank_branch_name === null) {
        document.getElementById('bank_branch_name').focus();
        addActionError('message', 'Enter Bank Branch Name');
        return_value = false;

    } else if (client_bank_name === "" || client_bank_name === "null" || client_bank_name === null) {
        document.getElementById('client_bank_name').focus();
        addActionError('message', 'Enter Client Bank Name');
        return_value = false;

    } else if (login_id === "" || login_id === "null" || login_id === null) {
        try {
            document.getElementById('login_id').focus();
            addActionError('message', 'Enter Login ID');
            return_value = false;
        } catch (e) {
        }

    } else if (login_password === "" || login_password === "null" || login_password === null) {
        document.getElementById('login_password').focus();
        addActionError('message', 'Enter Login Password');
        return_value = false;
    }
    return return_value;
}//end function

function validateBranchAddress() {
    var return_value = true;
    var state = document.getElementById('state').value;

    if (state === "" || state === "null" || state === null) {
        document.getElementById('state').focus();
        addActionError('message', 'Select State');
        return_value = false;

    }
    return return_value;
}//end function

function validateResposiblePersonDetails() {
    var return_value = true;

    var resp_person_name = document.getElementById('resp_person_name').value;
    var resp_person_desig = document.getElementById('resp_person_desig').value;
    var resp_person_mobile = document.getElementById('resp_person_mobile').value;
    var resp_person_mail = document.getElementById('resp_person_mail').value;
    var resp_person_panno = document.getElementById('resp_person_panno').value;

    var validPan = validatePANNO(document.getElementById('resp_person_panno'));
    var validEmail = validateEmailId(document.getElementById('resp_person_mail'));

    if (resp_person_name === "" || resp_person_name === "null" || resp_person_name === null) {
        document.getElementById('resp_person_name').focus();
        addActionError('message', 'Enter Responsible Person Name');
        return_value = false;

    } else if (resp_person_desig === "" || resp_person_desig === "null" || resp_person_desig === null) {
        document.getElementById('resp_person_desig').focus();
        addActionError('message', 'Enter Responsible Person Designation');
        return_value = false;

    } else if (resp_person_mobile === "" || resp_person_mobile === "null" || resp_person_mobile === null) {
        document.getElementById('resp_person_mobile').focus();
        addActionError('message', 'Enter Responsible Person Mobile');
        return_value = false;

    } else if (resp_person_mail === "" || resp_person_mail === "null" || resp_person_mail === null) {
        document.getElementById('resp_person_mail').focus();
        addActionError('message', 'Enter Responsible Person Email Id');
        return_value = false;

    } else if (resp_person_panno === "" || resp_person_panno === "null" || resp_person_panno === null) {
        document.getElementById('resp_person_panno').focus();
        addActionError('message', 'Enter Responsible Person PAN Number');
        return_value = false;

    } else if (!validPan) {
        document.getElementById('resp_person_panno').focus();
        addActionError('message', 'Invalid PAN Number');
        return_value = false;

    } else if (!validEmail) {
        document.getElementById('resp_person_mail').focus();
        addActionError('message', 'Invalid Email Id');
        return_value = false;
    }
    return return_value;
}//end function

function validatePANNO(field) {
    var value = field.value;
    removeError();
    if (value !== "" && value !== "null" && value !== null) {
        var Exp = /^([a-zA-Z]){3}(a|A|b|B|c|C|f|F|g|G|h|H|j|J|k|K|l|L|p|P|t|T){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
        if (!Exp.test(value)) {
            addActionError('message', "Invalid PAN Number");
            field.focus();
            return false;
        } else {
            return true;
        }
    } else {
        return false;
    }
}//end function

function validateEmailId(emailField) {

    if (!lhsIsNull(emailField.value)) {
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
}//end function

function validateTANNO(field) {
    var value = field.value;

    if (!lhsIsNull(value)) {
        var Exp = /^([a-zA-Z]){4}([0-9]){5}([a-zA-Z]){1}?$/;
        if (!Exp.test(value)) {
            addActionError('message', "Invalid TAN Number");
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

function getBankBranchDetails() {

    var cp = document.getElementById("globalcontextpath").value;
    var actionUrl = cp + "/branchGeneration?action=getBranches";

    var codeLevel = document.getElementById('bank_code_level').value;
    if (Number(codeLevel) > 1) {
        codeLevel--;
        enableImmBranchRequired();

    } else {
        disableImmBranchRequired();
        return;
    }
    $.ajax({
        url: actionUrl,
        type: 'POST',
        data: {
            "clientMastObj.code_level": codeLevel
        },
        beforeSend: function (xhr) {
            showProcessIndicator();
        },
        success: function (data, textStatus, jqXHR) {

            if (!lhsIsNull(data)) {
                var branchDetails = data.split('#');
                document.getElementById("imm_bank_branch_code").innerHTML = branchDetails[0];

                if (Number(branchDetails[1]) === 0) {
                    document.getElementById("immBranchRequiredSpan").innerHTML = "";
                    document.getElementById("immBranchRequiredFlag").value = "false";
                } else {
                    document.getElementById("immBranchRequiredSpan").innerHTML = "*";
                    document.getElementById("immBranchRequiredFlag").value = "true";
                }
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            ajax_call_enabled = false;
            hideProcessIndicator();
            console.log('error - ' + jqXHR);
        },
        complete: function (jqXHR, textStatus) {
            ajax_call_enabled = false;
            hideProcessIndicator();
            console.log('complete - ' + jqXHR);
        }
    });
//    }
}//end function

function enableImmBranchRequired() {
    document.getElementById("imm_bank_branch_code").disabled = false;
    document.getElementById("immBranchRequiredSpan").innerHTML = "*";
    document.getElementById("immBranchRequiredFlag").value = "true";
}//end function

function disableImmBranchRequired() {
    document.getElementById("imm_bank_branch_code").disabled = true;
    document.getElementById("immBranchRequiredSpan").innerHTML = "";
    document.getElementById("immBranchRequiredFlag").value = "false";
}//end function

function getLocalityDetails(pinCode) {
    if (!lhsIsNull(pinCode)) {
        var cp = document.getElementById("globalcontextpath").value;
        var actionUrl = cp + "/branchGeneration?action=getLocality";

        $.ajax({
            url: actionUrl,
            type: 'POST',
            data: {
                "clientMastObj.pin": pinCode
            },
            beforeSend: function (xhr) {
                showProcessIndicator();
            },
            success: function (data, textStatus, jqXHR) {

                if (!lhsIsNull(data)) {
//                    alert('data: ' + data);
                    var localityDetails = data.split('#');
                    try {
                        document.getElementById("city").value = localityDetails[0]; // City Name
                        document.getElementById("state").value = localityDetails[1]; // State Code
                    } catch (e) {
                    }
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                ajax_call_enabled = false;
                hideProcessIndicator();
                console.log('error - ' + jqXHR);
            },
            complete: function (jqXHR, textStatus) {
                ajax_call_enabled = false;
                hideProcessIndicator();
                console.log('complete - ' + jqXHR);
            }
        });
    } else {
        addActionError('message', 'Please Enter Pin Code');
    }
}//end function

function editBranchDetails(editClientCode) {
    if (lhsIsNull(editClientCode)) {
        openErrorModal('Some error occurred!');
    } else {
        $('#actionName').val('edit');
        $('#actionClientCode').val(editClientCode.replace('edit_', ''));

        $('#branchAction').submit();
    }
}//end function

function deleteBranchDetails(editClientCode) {
    if (lhsIsNull(editClientCode)) {
        openErrorModal('Some error occurred!');
    } else {
        $(function () {
            $("#dialog-confirm_delete").dialog({
                resizable: false,
                font: 10,
                modal: true,
                buttons: {
                    "Ok": function () {
                        $(this).dialog("close");

                        $('#actionName').val('delete');
                        $('#actionClientCode').val(editClientCode.replace('del_', ''));

                        $('#branchAction').submit();
                    },
                    "Cancel": function () {
                        $(this).dialog("close");
                        return;
                    }
                }
            });
        });

    }
}//end function
function convertToUppercase() {
    var input = document.getElementById('bankBranchCode');
    input.onkeyup = function () {
        this.value = this.value.toUpperCase();
    }
}

function callBranchEntry(){
    let client_code=$('#client-code').val();
    if(client_code === '6'){
        openErrorModal("You can not add clients, please contact to your Upper Level"); 
    }else{
        callUrl("/branchGeneration");
    }
}
