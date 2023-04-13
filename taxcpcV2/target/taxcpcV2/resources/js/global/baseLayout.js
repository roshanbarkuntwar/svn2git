
var ajax_call_enabled = false;

window.onkeyup = function (e) {
    e.preventDefault();
    try {
        let currentDbUser = document.getElementById("currentDbUser").value || '';
        if (e.ctrlKey && e.shiftKey && 76 === (e.which || e.keyCode)) {
            alert('Browser error: ' + currentDbUser);
        }
    } catch (e) {
    }
};

function callModule(moduleType) {
    document.getElementById("session_module").value = moduleType;
    document.getElementById("module_form").submit();
}

//Google signin-----------------------------------------------------------------
let auth2;
var startApp = function () {
    gapi.load('auth2', function () {
        // Retrieve the singleton for the GoogleAuth library and set up the client.
        auth2 = gapi.auth2.init({
            client_id: '254580028675-kdrlu0a6v6ngl6lft84qnd1i51udussv.apps.googleusercontent.com',
            cookiepolicy: 'single_host_origin',
            // Request scopes in addition to 'profile' and 'email'
            //scope: 'additional_scope'
        });
        console.log(auth2);
        attachSignin(document.getElementById('customBtn'));
        attachSignin(document.getElementById('gSignupBtn'));
    });
};

function attachSignin(element) {
    console.log(element.id);
    auth2.attachClickHandler(element, {},
            function (googleUser) {
                let gProfile = googleUser.getBasicProfile();
                if (element.id === 'customBtn')
                    onSignIn(googleUser);
                else if (element.id === 'gSignupBtn')
                    registerUser(gProfile.getId(), gProfile.getEmail(), gProfile.getName());
            },
            function (error) {
                alert(JSON.stringify(error, undefined, 2));
            });
}

function onSignIn(googleUser) {
    let profile;
    let id_token;
    try {
        profile = googleUser.getBasicProfile();
        id_token = profile.getId();
        googleUser.disconnect();
    } catch (e) {
        console.log('Error while signing in..');
        console.log(e);
    }

    if (lhsIsNull(id_token)) {
        openErrorModal('Could not sign in, please try again.');
    } else {
        console.log('Signed in success!');
        console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
        console.log('Name: ' + profile.getName());
        console.log('Image URL: ' + profile.getImageUrl());
        console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.

        var auth2 = gapi.auth2.getAuthInstance();
        console.log('auth object', auth2);
        if (auth2.isSignedIn.get()) {
            authenticateUser(profile.getId(), profile.getEmail());
        }
    }
}//end function

function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });
}//end function

function authenticateUser(userId, email) {
    let cp = document.getElementById("contextPath").value;
    $.ajax({
        url: cp + '/tokensignin',
        type: 'POST',
        data: {
            loginAction: 'authenticateUser',
            profileId: userId,
            profileEmail: email
        },
        contentType: 'application/x-www-form-urlencoded',
        cache: false,
        beforeSend: function (xhr) {
            showProcessIndicator();
        },
        success: function (data, textStatus, jqXHR) {
            if (!lhsIsNull(data) && data !== 'invalidRequest') {
                signOut();
                let parseObject = JSON.parse(data);
                let response = parseObject.authenticationResponse;

                if (response.authenticationStatus === 'authenticated') {
                    document.getElementById("login_id").value = response.loginId;
                    document.getElementById("login_pwd").value = response.loginPassword;
                    document.forms[0].submit();
                } else if (response.authenticationStatus === 'notRegistered') {
                    openErrorModal('This account is not registered with our system, Please register using your Google Account.', 'callUrl("/login");');
                } else if (response.authenticationStatus === 'invalid') {
                    openErrorModal('Account is invalid');
                }
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('error in Google login', jqXHR, textStatus, errorThrown);
        },
        complete: function (jqXHR, textStatus) {
            hideProcessIndicator();
        }
    });
}
//Google signin-----------------------------------------------------------------



function closeDeductorList() {
    document.getElementById("deductorListDiv").className = "deductor-list px-2 py-1 position-absolute w-100 collapse";
}


function PageReload()
{
    location.reload();
}



function getDeductorList() {
    var term = $("#term").val();
    var url = "getDeductorList?term=" + term;
    $.ajax({
        url: url,

        success: function (result) {
            if (result !== null && result !== "null" && result !== "") {
                $("#deductorList").html(result);


            }
        }
    });


}


function resetList() {
    $("#term").val("");
    getDeductorList();
}

function changeDeductor(id) {
    document.getElementById("dialog-confirm_change_deductor").style.display = "block";
    $(function () {
        $("#dialog-confirm_change_deductor").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                "Yes": function () {
                    var cp = $("#globalcontextpath").val();
                    var splitId = id.split("~")[1];
                    var clientCode = document.getElementById("hiddenVal~" + splitId).value;

                    var url = cp + "/setAssessment?action=changeDeductor&clientCode=" + clientCode;

                    $.ajax({
                        url: url,
                        success: function (result) {
                            window.location.reload();
                        }
                    });
                },
                "No": function () {
                    $(this).dialog("close");
                }
            }
        });
    });


}


function changeAssessment() {

    document.getElementById("dialog-confirm_change_assessment").style.display = "block";
    $(function () {
        $("#dialog-confirm_change_assessment").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                "Yes": function () {
                    var cp = $("#globalcontextpath").val();
                    var accYear = $("#accYear").val();
                    var qrtNo = $("#quarter").val();
                    var tds = $("#tdsType").val();
                    var url = cp + "/setAssessment?action=changeAssessment&assessment.accYear=" + accYear + "&assessment.quarterNo=" + qrtNo + "&assessment.tdsTypeCode=" + tds;
                    $.ajax({
                        url: url,
                        cache: false,
                        success: function (result) {
                            window.location.reload();
                        }
                    });
                    $(this).dialog("close");
                },
                "No": function () {
                    $("#accYear").val($("#sessionAccYear").val());
                    $("#quarter").val($("#sessionQuarterNo").val());
                    $("#tdsType").val($("#sessionTdsType").val());

                    $(this).dialog("close");
                }
            }
        });
    });


}


function exitUser() {
    var cp = $("#globalcontextpath").val();
    window.location = cp + "/logout";
}


function getGlobalSessionData() {
    var contextPath = document.getElementById("globalcontextpath").value;
    var globalSessionData = document.getElementById("globalSessionData").value;
    if (globalSessionData === "" || globalSessionData === "null" || globalSessionData === null) {
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
//                alert(q);
            }
        };
        var url = contextPath + "/getGlobalAJAXExecuter?action=globalSessionValue";
        xmlhttp.open("GET", url, true);
        xmlhttp.send(null);
    }
}//end method

function someFunction()
{

    $("#es").val(encodeURIComponent('1ttRzdyNcGONph/hjmZRcjYv33ksa1uNBRMjfIRv59mxEsyrIkxsPOjJ5MXhYsNHXAugaBcceJl6NuLWBAkrlcDjOMXeKcwxtWj8B7okp5eY/g0C5c3AjwSnksxINOcyDA9UOHQK/l881lUCZBXR6VbWjojaDbd42ondR32dCaygGqzjMM7as8NkDDiqyk9GYd81/Q4sb3yJwdChS7xIXw=='));
    $("#token").val(encodeURIComponent('SaEPaspXBOBEe+AyJpwZebgxHXNEeH05/YDSUg3tQAOvvU4LtMA3SY0wwfWtgyjAB5S7/+9DcmLTgjAMHUNDbLiFqCC5BCAKMauqx2x+pZpAexzl6WxXCaVU0TEhZ4sO6nCE9AnFk9BG8qeAjIDkLVWi87ON/zSedZZLOSqbWrTmMcqpWkZ1cNMLktqbD41xob7NxhQAMVuXmZxKzUbvE1fT4tBGSGy4OsUQ0JsF8KUg9l/vXGsIr57urp9jE57jJ6JY3HmS1iEfrNq9bBlqa8UDWVi9vpLRxasI+fmc+yDRcfBBdD15elZLmP7OmJwa91lb4vBG7D7WO+E4vOVY+w=='));
    $("#sbiTrsForm").submit();

//    alert("Some Function");
//     var cp = document.getElementById("globalcontextpath").value;
//    var url = cp+'/linkToSBI';
//      alert("Base URL-------------->"+url);
//    $.ajax({
//        type: "POST",
//        url: url,
//        success: function (data) {
//
//            console.log("taxcpc-sbi-web url=====" + data);
//            if (!lhsIsNull(data) && data.includes("###")) {
//                var urlSpit = data.split("###");
//                $("#es").val(encodeURIComponent(urlSpit[0]));
//                $("#token").val(encodeURIComponent(urlSpit[1]));
//                $("#sbiTrsForm").submit();
//                //window.open(urlSpit[0]+"link="+encodeURIComponent(urlSpit[1]), "_blank");
//            }
//        },
//        error: function (result) {
//            console.log("result.."+result);
//
//
//
//        }
//    });
}//end method

function loginOnloadData() {
    /**
     * Commented as per required -
     * Uncomment when Logo required on login page as per bank logo
     */
//    var cp = document.getElementById("contextPath").value;
//    $.ajax({
//        url: cp + '/loginData',
//        type: 'GET',
//        success: function (data, textStatus, jqXHR) {
//            try {
//                var result = JSON.parse(data);
//                document.getElementById("loginPageLogo").src = result.loginPageLogo;
//                document.getElementById("logoTagline").innerHTML = result.logoTagline;
//                document.getElementById("signUpFtr").style.display = result.signUpFtr;
//            } catch (e) {
//                document.getElementById("loginPageLogo").src = 'resources/images/global/TAXCPC-logo.jpg';
//                document.getElementById("logoTagline").innerHTML = '';
//                document.getElementById("signUpFtr").style.display = 'none';
//            }
//        }, error: function (jqXHR, textStatus, errorThrown) {
//            document.getElementById("loginPageLogo").src = 'resources/images/global/TAXCPC-logo.jpg';
//            document.getElementById("logoTagline").innerHTML = '';
//            document.getElementById("signUpFtr").style.display = 'none';
//        }
//    });
}//end function

/**
 * A global function declaration for calling the DB procedures using ajax call.
 * @param {type} procBody
 * @returns {undefined}
 */
function callDedicatedProcedure(procBody) {
    console.log("calling callDedicatedProcedure");
    console.log("procBody --"+procBody);
    let cxp = document.getElementById("globalcontextpath").value;
    try {
        $.ajax({
            url: cxp + "/executeCommonProcedureCallback",
            type: 'POST',
            data: procBody,
            async: true
        });
    } catch (e) {
        console.log('%cProcedure calling ajax error', 'color: red;');
        console.log(e);
    }
    console.log('%cCalling ' + procBody.callingProcName + ' procedure ajax completed...' + procBody.tokenNo, 'font-style: italic;');
}//end function

function remarkSave() {
    var remark = document.getElementById("remark").value;
    if (!lhsIsNull(remark)) {
        let cxp = document.getElementById("globalcontextpath").value;
        try {
            $.ajax({
                url: cxp + "/feedback?remark=" + remark,
                type: "POST",
                success: function (response) {
                    if (response === 'success') {
                        addActionError("success", "Data Save Successfully");
                        $("#save_remark").attr("disabled", true);
                        $("#remark").val('');
                    }
                }
            });
        } catch (e) {
            console.log(e);
        }
    } else {
        $("#remark").focus();
    }
}

function addIndvLogo() {
    document.getElementById("dialog-confirm_change_logo").style.display = "block";
    $(function () {
        $("#dialog-confirm_change_logo").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                "Yes": function () {
                    var callLogoUrl = "/changeLogo";
                    callUrl(callLogoUrl);
                },
                "No": function () {
                    $(this).dialog("close");
                }
            }
        });
    });
}

function uploadLogoFile(evt) {
    evt.preventDefault();
    let submitFlag = false;
    try {
        let selectedFile = document.getElementById('logoUploadFile').files[0];
        if (lhsIsNull(selectedFile) || lhsIsNull(selectedFile.name)) {
            openErrorModal('Please select Logo to upload.', 'window.location.reload();');
        } else {
            submitFlag = true;
        }
    } catch (e) {
    }
    if (submitFlag) {
        var actionUrl = '/changeLogo?upload=true';
        ajaxSubmitPostData(actionUrl, "logoTabUploadFile", response)

        function response(data, textStatus, jqXHR) {
            if (!lhsIsNull(data)) {
                var value = data.split("#");
                if (value[0] === 'success') {
                    openSuccessModal("Logo is upload Successfully", 'exitUser()');
                } else {
                    openErrorModal("Some Error Occurred!!", 'window.location.reload();');
                }
            } else {
                openErrorModal("Some Error Occurred!!", 'window.location.reload();');
            }
        }

    }
}
function resetLogoFile(evt) {
    evt.preventDefault();
    var actionUrl = '/changeLogo?resetLogo=true';
    ajaxSubmitPostData(actionUrl, "logoTabUploadFile", response)
    function response(data, textStatus, jqXHR) {
        if (!lhsIsNull(data)) {
            var value = data.split("#");
            if (value[0] === 'success') {
                openSuccessModal("Logo is reset Successfully", 'exitUser()');
            } else {
                openErrorModal("Some Error Occurred!!", 'window.location.reload();');
            }
        } else {
            openErrorModal("Some Error Occurred!!", 'window.location.reload();');
        }
    }

}