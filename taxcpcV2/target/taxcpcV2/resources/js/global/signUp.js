/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var xmlHttp = "";
var ajax_call_enabled = false;
function make_ajax_call(url, resuting_call) {
    if (!ajax_call_enabled) {
        if (typeof XMLHttpRequest !== "undefined") {
            xmlHttp = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        if (xmlHttp === null) {
//            alert("Browser does not support XMLHTTP Request");
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
//        window.alert("Your previous request is in progress");
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
function lhsIsNull(value) {
    if (value !== "" && value !== "null" && value !== null)
        return false;
    else
        return true;
}//end function

function addFieldError(field, error) {

    document.getElementById(field).style.display = "block";
    document.getElementById(field).innerHTML = error;
}//end function

function removeFieldError(field) {
    document.getElementById(field).style.display = "none";
    document.getElementById(field).innerHTML = "";
}

function validatePAN(field) {
    var value = field.value;
    if (value === "PANNOTREQD" || value === "pannotreqd") {
        return true;
    } else {
        if (!lhsIsNull(value)) {
//            var Exp = /^([a-zA-Z]){3}(a|A|p|P|f|F|c|C|h|H|t|T){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
            var Exp = /^([a-zA-Z]){3}(a|A|b|B|c|C|f|F|g|G|h|H|j|J|k|K|l|L|p|P|t|T){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
            if (!Exp.test(value)) {
                addFieldError("panErrorSpan", "Invalid");
                field.focus();
                return false;
            } else {
                removeFieldError("panErrorSpan");
                return true;
            }
        } else {
//            removeFieldError("panErrorSpan");
//            return false;
            return true;
        }
    }
}//end function

function validateTAN(field) {
    var value = field.value;

    if (!lhsIsNull(value)) {
        var Exp = /^([a-zA-Z]){4}([0-9]){5}([a-zA-Z]){1}?$/;
        if (!Exp.test(value)) {
            addFieldError("tanErrorSpan", "Invalid");
            field.focus();
            return false;
        } else {
            removeFieldError("tanErrorSpan");
            return true;
        }
    } else {
//            document.getElementById("browse_process_indicator").style.visibility = 'hidden';
        field.focus();
        addFieldError("tanErrorSpan", "Cannot Be Blank");
        return false;
    }

}//end function

function validateMultiplePANTAN() {
    var tan_chk = document.getElementById('multiple_user_flag').checked;
    var loginLevel = document.getElementById('client_login_type').value;
    if (tan_chk) {
        if (loginLevel !== "" && loginLevel !== "null" && loginLevel !== null) {
            removeFieldError("loginLevelErrorSpan");
            return true;
        } else {
            document.getElementById("browse_process_indicator").style.visibility = 'hidden';
            addFieldError("loginLevelErrorSpan", "Cannot Be Blank");
            return false;
        }
    } else {
        removeFieldError("loginLevelErrorSpan");
        return true;
    }
}//end function

function validateEmail(emailField) {
    if (!lhsIsNull(emailField.value)) {
//        var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

        var reg = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        if (reg.test(emailField.value) === false)
        {
            addFieldError("emailErrorSpan", "Invalid");
            emailField.focus();
            return false;
        } else {
            removeFieldError("emailErrorSpan");
            return true;
        }
    } else {
        emailField.focus();
        addFieldError("emailErrorSpan", "Cannot Be Blank");
        return false;
    }
}//end method

function validatePassword() {
    var pass = document.getElementById("password").value;
    var cpass = document.getElementById("confirmPassword").value;
    if (pass !== cpass) {
        addFieldError("confrmPwdErrorSpan", "Password and confirm password must be same !");
//        document.getElementById("confirmPassword").focus();
        return false;
    } else {
        removeFieldError("confrmPwdErrorSpan");
        return true;
    }
}//end method


function getCheckBoxFlagVale(id) {
    if (id !== null && id !== "" && id !== "null") {
        if (id === 'l_single_user_flag') {
            document.getElementById('multiCheckHidden').value = "false";
            document.getElementById('tannoDiv').style.display = "block";
            document.getElementById('Tanno').value = "";
        }
        if (id === 'multiple_user_flag') {
            document.getElementById("multiCheckHidden").value = "true";
            document.getElementById('l_single_user_flag').checked = false;
            document.getElementById('tannoDiv').style.display = "none";
            document.getElementById('Tanno').value = "";
        }
    }
}//end function

//Google Signin Integration-----------------------------------------------------
//let auth2;
//var startApp = function () {
//    gapi.load('auth2', function () {
//        // Retrieve the singleton for the GoogleAuth library and set up the client.
//        auth2 = gapi.auth2.init({
//            client_id: '254580028675-kdrlu0a6v6ngl6lft84qnd1i51udussv.apps.googleusercontent.com',
//            cookiepolicy: 'single_host_origin',
//            // Request scopes in addition to 'profile' and 'email'
//            //scope: 'additional_scope'
//        });
//        attachSignin(document.getElementById('customBtn'));
//    });
//};

//function attachSignin(element) {
//    console.log(element.id);
//    auth2.attachClickHandler(element, {},
//            function (googleUser) {
//                onSignIn(googleUser);
//            },
//            function (error) {
//                alert(JSON.stringify(error, undefined, 2));
//            });
//}

//function onSignIn(googleUser) {
//    let profile;
//    let id_token;
//    try {
//        profile = googleUser.getBasicProfile();
//        id_token = profile.getId();
//        googleUser.disconnect();
//    } catch (e) {
//        console.log('Error while signing in..');
//        console.log(e);
//    }
//    console.log('Google signup started', googleUser);
//    if (lhsIsNull(id_token)) {
//        openErrorModal('Could not sign in, please try again.');
//        console.log('Google login interrupted!!!');
//        return;
//    } else {
//        console.log('Signed in success!');
//        console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
//        console.log('Name: ' + profile.getName());
//        console.log('Image URL: ' + profile.getImageUrl());
//        console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
//
//        var auth2 = gapi.auth2.getAuthInstance();
//        if (auth2.isSignedIn.get()) {
//            registerUser(profile.getId(), profile.getEmail(), profile.getName());
//        }
//    }
//}

//function signOut() {
//    var auth2 = gapi.auth2.getAuthInstance();
//    auth2.signOut().then(function () {
//        console.log('User signed out.');
//    });
//}//end function

function registerUser(userId, email, accountName) {
    let cp = document.getElementById("globalcontextpath").value;

    $.ajax({
        url: cp + '/tokensignin',
        type: 'POST',
        data: {
            loginAction: 'registerUser',
            profileId: userId,
            profileEmail: email,
            signupAccountName: accountName
        },
        contentType: 'application/x-www-form-urlencoded',
        cache: false,
        beforeSend: function (xhr) {
            showProcessIndicator();
        },
        success: function (data, textStatus, jqXHR) {
            if (!lhsIsNull(data)) {
                signOut();
                if (data === 'userRegisteredSuccessfully') {
                    openSuccessModal('You have registered successfully, you can now login to your account using your Google Account.', 'callUrl("/login");');
                } else if (data === 'userUnableToRegistered') {
                    openErrorModal('Some error occurred, could not register user please try again.');
                } else if (data === 'userAlreadyRegistered') {
                    openSuccessModal('Your account is already registered with us, please login using your Google Account.', 'callUrl("/login");');
                }
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('error in Google signup', jqXHR, textStatus, errorThrown);
        },
        complete: function (jqXHR, textStatus) {
            hideProcessIndicator();
        }
    });
}
//Google Signin Integration end-------------------------------------------------
function registerNewUser() {
    var cp = document.getElementById("contextPath").value;
    var tanno = document.getElementById("Tanno");
    var userName = document.getElementById("userName");
    var emailId = document.getElementById("UserName");
    var mobile = document.getElementById("mobile");
    var password = document.getElementById("password");
    var confirmPassword = document.getElementById("confirmPassword");
//    document.getElementById("browse_process_indicator").style.visibility = 'visible';
//    if (validateMultiplePANTAN()) {
    if (validateTAN(tanno)) {
        removeFieldError("tanErrorSpan");
        if (!lhsIsNull(userName.value)) {
            removeFieldError("userNameErrorSpan");
            if (!lhsIsNull(emailId.value)) {
                removeFieldError("emailErrorSpan");
                if (validateEmail(emailId)) {
                    if (!lhsIsNull(mobile.value)) {
                        removeFieldError("mobileErrorSpan");
                        if (ckhMobileLength(mobile)) {
                            //-------START---
                            var l_return_value = false;
                            removeFieldError("emailErrorSpan");
                            var val = emailId.value;
                            var callingurl = cp + "/signup?register=false" + "&chkEmailId=" + encodeURIComponent(val);
                            $.ajax({
                                url: callingurl,
                                context: document.body,
                                success: function (result) {
                                    if (!lhsIsNull(result)) {
                                        if (result === 'error') {
//                                        document.getElementById("browse_process_indicator").style.visibility = 'hidden';
                                            addFieldError("emailErrorSpan", "Email Id already Present, Try Another");
                                            emailId.focus();
                                            l_return_value = false;
                                        } else {
                                            removeFieldError("emailErrorSpan");
                                            l_return_value = true;
                                        }
                                        //--------END----
                                        if (l_return_value) {
                                            if (!lhsIsNull(password.value)) {
                                                removeFieldError("pwdErrorSpan");
                                                if (!lhsIsNull(confirmPassword.value)) {
                                                    removeFieldError("confrmPwdErrorSpan");
                                                    if (validatePassword()) {
//                                                        showProcessIndicator();
                                                        document.getElementById("signupfrm").submit();
                                                    } else {
//                                                    document.getElementById("browse_process_indicator").style.visibility = 'hidden';
                                                    }
                                                } else {
//                                                document.getElementById("browse_process_indicator").style.visibility = 'hidden';
                                                    addFieldError("confrmPwdErrorSpan", "Cannot Be Blank");
                                                }
                                            } else {
//                                            document.getElementById("browse_process_indicator").style.visibility = 'hidden';
                                                password.focus();
                                                addFieldError("pwdErrorSpan", "Cannot Be Blank");
                                            }
                                        }
                                        //-----start1--
                                    } else {
//                                    document.getElementById("browse_process_indicator").style.visibility = 'hidden';
                                        addFieldError("emailErrorSpan", "Email Id already Present, Try Another");
                                        return false;
                                    }
                                }
                            });
                        } else {
//                        document.getElementById("browse_process_indicator").style.visibility = 'hidden';
                        }
                    } else {
//                    document.getElementById("browse_process_indicator").style.visibility = 'hidden';
                        mobile.focus();
                        addFieldError("mobileErrorSpan", "Cannot Be Blank");
                    }
                } else {
//                document.getElementById("browse_process_indicator").style.visibility = 'hidden';
                }
            } else {
//            document.getElementById("browse_process_indicator").style.visibility = 'hidden';
                emailId.focus();
                addFieldError("emailErrorSpan", "Cannot Be Blank");
            }
        } else {
//            document.getElementById("browse_process_indicator").style.visibility = 'hidden';
            userName.focus();
            addFieldError("userNameErrorSpan", "Cannot Be Blank");
        }
    } else {
//        document.getElementById("browse_process_indicator").style.visibility = 'hidden';
    }
//    }
}//end method

$(function () {
    $('form,input,select,textarea').attr("autocomplete", "off");
    $('#example1').hover(function () {
        $(this).popover('show');
    }, function () {
        $(this).popover('hide');
    }
    );
    $('#loginIdPopover').hover(function () {
        $(this).popover('show');
    }, function () {
        $(this).popover('hide');
    }
    );
    $('#loginPwdPopover').hover(function () {
        $(this).popover('show');
    }, function () {
        $(this).popover('hide');
    }
    );
    $('#tanNoPopover').hover(function () {
        $(this).popover('show');
    }, function () {
        $(this).popover('hide');
    }
    );
});
function ckhMobileLength(field) {
    if (!lhsIsNull(field.value)) {
        if (field.value.length < 6) {
            addFieldError("mobileErrorSpan", "Minimim 6 Digits");
            field.focus();
            return false;
        } else {
            removeFieldError("mobileErrorSpan");
            return true;
        }
    } else {
        field.focus();
        addFieldError("mobileErrorSpan", "Cannot Be Blank");
        return false;
    }
}

function sendOTP(evt) {
    evt.preventDefault();

    let cxp = document.getElementById("globalcontextpath").value;
    let tanno = document.getElementById("Tanno").value;
    let accName = document.getElementById("userName").value;
    let emailId = document.getElementById("UserName").value;

    if (validateFieldsForOtp()) {
        try {
            $.ajax({
                url: cxp + "/signup",
                type: 'POST',
                data: {
                    'clientMast.tanno': tanno,
                    'clientMast.client_name': accName,
                    'clientMast.login_id': emailId,
                    'action': 'registerNewUser'
                },
                beforeSend: function (xhr) {
                    showProcessIndicator();
                },
                success: function (data, textStatus, jqXHR) {
                    if (lhsIsNull(data) || data === 'couldNotRegister' || data === 'error') {
                        openErrorModal('Could not register the user please try again.');
                    } else if (data === 'emailAlreadyExists') {
                        openErrorModal('Email already exists. Please try using different email.');
                    } else if (data === 'otpError') {
                        openErrorModal('Could not generate OPT. Please try again.');
                    } else if (data.includes('#')) {
                        let otpresult = data.split('#');
                        if (otpresult[0] === 'registeredSuccessfully' || !lhsIsNull(otpresult[1])) {
                            openSuccessModal('Your OTP has been sent to your email. Please enter OTP to complete your registration.');
                            $('.otp-field').removeAttr('disabled');
                        }
                    }
                },
                complete: function (jqXHR, textStatus) {
                    hideProcessIndicator();
                }
            });
        } catch (e) {
            console.log(e);
        }
    }
}//end function

function verifyOTP(evt) {
    evt.preventDefault();

    let cxp = document.getElementById("globalcontextpath").value;
    let emailId = document.getElementById("UserName");
    let appr_verify_code = document.getElementById("mobile").value;

    if (lhsIsNull(emailId.value)) {
        addFieldError("emailErrorSpan", "Cannot Be Blank");
        return false;
    } else {
        validateEmail(emailId);
    }

    if (lhsIsNull(appr_verify_code)) {
        addFieldError("mobileErrorSpan", "Cannot Be Blank");
        return false;
    }

    try {
        $.ajax({
            url: cxp + "/signup",
            type: 'POST',
            data: {
                'clientMast.appr_verify_code': appr_verify_code,
                'clientMast.login_id': emailId.value,
                'action': 'verifyOtp'
            },
            beforeSend: function (xhr) {
                showProcessIndicator();
            },
            success: function (data, textStatus, jqXHR) {

                if (data === 'invalidOtp') {
                    openErrorModal('Entered OTP is invalid. Please enter valid OTP.');
                } else if (data === 'validOtp') {
                    openSuccessModal('OTP Verified successfully.');

                    $('.otp-verify').css('display', 'none');
                    $('.otp-verified').css('display', 'block');
                    $('.otp-verified').css('pointer-events', 'none');
                    $('.reg-user-new').removeAttr('disabled');
                } else if (data === 'otpError') {
                    openErrorModal('Some error occurred. Please enter valid OTP.');
                }

            },
            complete: function (jqXHR, textStatus) {
                hideProcessIndicator();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log('error...');
                console.log('error...', jqXHR, textStatus, errorThrown);
            }
        });
    } catch (e) {
        console.log(e);
    }
}//end function

function validateFieldsForOtp() {
    var tanno = document.getElementById("Tanno");
    var userName = document.getElementById("userName");
    var emailId = document.getElementById("UserName");

    if (validateTAN(tanno)) {
        removeFieldError("tanErrorSpan");
        if (!lhsIsNull(userName.value)) {
            removeFieldError("userNameErrorSpan");
            if (!lhsIsNull(emailId.value)) {
                removeFieldError("emailErrorSpan");
                if (validateEmail(emailId)) {
                    return true;
                }
            } else {
                emailId.focus();
                addFieldError("emailErrorSpan", "Cannot Be Blank");
                return false;
            }
        } else {
            userName.focus();
            addFieldError("userNameErrorSpan", "Cannot Be Blank");
            return false;
        }
    }
}
