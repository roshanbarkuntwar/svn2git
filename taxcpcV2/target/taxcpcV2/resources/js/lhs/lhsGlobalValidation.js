/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function lhsIsNull(value) {
    try {
        value = value.trim();
    } catch (err) {
        value = value;
    }
    if (value !== "" && value !== "null" && value !== null && value !== undefined && value !== 'undefined')
        return false;
    else
        return true;
}//end lhs_lhsIsNull function


function lhsValidateEmail(emailField) {
    if (!lhsIsNull(emailField.value)) {
//        var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

        var reg = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        if (reg.test(emailField.value) === false)
        {
//            emailField.focus();
            alert("not a valid email");
            return false;
        } else {
            return true;
        }
    } else {
        return true;
    }
}//end lhs_validateEmail method


function lhsValidateWebsite(id) {
    var urlToValidate = document.getElementById(id).value;
    if (urlToValidate !== "" && urlToValidate !== "null" && urlToValidate !== null) {
        var myRegExp = /^(?:(?:https?|ftp):\/\/)(?:\S+(?::\S*)?@)?(?:(?!10(?:\.\d{1,3}){3})(?!127(?:\.\d{1,3}){3})(?!169\.254(?:\.\d{1,3}){2})(?!192\.168(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]+-?)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]+-?)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})))(?::\d{2,5})?(?:\/[^\s]*)?$/i;
//    example:-  "http://www.google.co.in";
        if (!myRegExp.test(urlToValidate)) {
            alert("Not a valid website.");
            document.getElementById(id).value = "";
        }
    }
}//end lhs_ValidateWebsite function


function lhsValidatePassword(pass_id, conf_pass_id) {
    var pass = document.getElementById(pass_id).value;
    var cpass = document.getElementById(conf_pass_id).value;
    if (pass !== cpass) {
        return false;
    } else {
        return true;
    }
}//end lhs_validatePassword method


function lhsValidateAlphanumeric(event)
{
    var inputValue = event.which;
    if (!(inputValue >= 65 && inputValue <= 90)
            && !(inputValue >= 97 && inputValue <= 122)
            && (inputValue != 32 && inputValue != 0)
            && !(inputValue >= 48 && inputValue <= 57)
            && (inputValue != 47 && inputValue != 45)) {

        event.preventDefault();
    }
}// end lhs_validateAlphanumeric method


function lhsValidateAlphaNumericWithoutSpclChars(event)
{
    var browser = checkUserBrowser();
    var inputValue = event.which;
    var keyCode = event.keyCode;
    console.log("inputValue.." + inputValue);
    console.log("keyCode.." + keyCode);
    // allow letters, numbers and whitespaces,'/','-' only.
    if (!(inputValue >= 65 && inputValue <= 90) && !(inputValue >= 97 && inputValue <= 122) &&
            (inputValue != 32 && inputValue != 0) &&
            !(inputValue >= 48 && inputValue <= 57)) {
        if (browser === 'mozilla') {
            var keyCode = event.keyCode;
            if (inputValue === 46 || (keyCode >= 37 && keyCode <= 40) || inputValue === 8) {

            } else {
                event.preventDefault();
            }
        } else {
//            if (charCode === 46 || (charCode >= 37 && charCode <= 40)) {
            if (inputValue === 8) {

            } else {
                event.preventDefault();
            }
        }

    }
}// end lhs_validateAlphaNumericWithoutSpclChars method


function lhsValidateAlphabet(event)
{
    var inputValue = event.which;
    if ((inputValue > 64 && inputValue < 91) || (inputValue > 96 && inputValue < 123) || inputValue == 8 || inputValue === 32) {
    } else {
        var browser = checkUserBrowser();
        if (browser === 'mozilla') {
            var keyCode = event.keyCode;
            if (inputValue === 46 || (keyCode >= 37 && keyCode <= 40) || inputValue === 8) {
            } else {
                event.preventDefault();
            }
        } else {
            event.preventDefault();
        }
    }
}// end lhs_validateAlphaNumericWithoutSpclChars method


function lhsValidateDateOnBlur(field) {
    try {
        var flag = false;
        var value = field.value;
        if (value !== "" && value !== "null" && value !== null) {
            var dataSplit = value.split("-")[2];
            var yearSplit = dataSplit.split('');
            var firstTwo = parseInt(yearSplit[0] + yearSplit[1]);
            var lastTwo = yearSplit[2] + yearSplit[3];
            if (firstTwo != 19) {
                if (firstTwo != 20) {
                    firstTwo = 20;
                    for (var i = 2; i < lastTwo.length; i++) {//--- if year has less than four chars
                        lastTwo = "0" + lastTwo;
                    }
                }
            }
            var dateFinal = value.split("-")[0] + "-" + value.split("-")[1] + "-" + firstTwo + lastTwo;
            flag = true;
        }
    } catch (e) {
        flag = false;
    }
    if (flag) {
        field.value = dateFinal;
    } else {
        field.value = "";
//        alert("Date should be in DD-MM-YYYY");
    }
    return flag;
}//end lhs_validateDateOnBlur method


function lhsIsNumber(evt) {
    var result = false;
    var browser = checkUserBrowser();
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    var target = evt.target || evt.srcElement;
    if (browser === 'mozilla') {
        var keyCode = evt.keyCode;

        var num = (charCode >= 48 && charCode <= 57);
        var other = ((keyCode >= 37 && keyCode <= 40) || (keyCode === 8 || keyCode === 46));
        if (num) {
            result = true;

        } else if (other) {
            result = true;

        } else {
            result = false;
        }
    } else {
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            result = false;
        } else {
            result = true;
        }
    }
    return result;

}//end lhs_isNumber method

//


function isNumberWithDecimal(evt) {
    var browser = checkUserBrowser();
    evt = (evt) ? evt : window.event;

    var charCode = (evt.which) ? evt.which : evt.keyCode;
//    var target = evt.target || evt.srcElement;

//    alert("which--" + evt.which + "\evt.keyCode--" + evt.keyCode);
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        if (browser === 'mozilla') {
            var keyCode = evt.keyCode;
            if (charCode === 46 || (keyCode >= 37 && keyCode <= 40)) {
                return true;
            } else {
                return false;
            }
        } else {
//            if (charCode === 46 || (charCode >= 37 && charCode <= 40)) {
            if (charCode === 46) {
                return true;
            } else {
                return false;
            }
        }
    } else {
        return true;
    }
}//end lhs_isNumber method

function lhsNumberWithCommas(id) {
    var x = document.getElementById(id).value;
    x = x.replace(/,/g, '');
    var afterPoint = '';
    if (x.indexOf('.') > 0)
        afterPoint = x.substring(x.indexOf('.'), x.length);
    x = Math.floor(x);
    x = x.toString();
    var lastThree = x.substring(x.length - 3);
    var otherNumbers = x.substring(0, x.length - 3);
    if (otherNumbers != '')
        lastThree = ',' + lastThree;
    var formattedValue = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ",") + lastThree + afterPoint;
    document.getElementById(id).value = formattedValue;
}//end lhs_numberWithCommas method


function checkUserBrowser() {
    // Opera 8.0+
    var isOpera = (!!window.opr && !!opr.addons) || !!window.opera || navigator.userAgent.indexOf(' OPR/') >= 0;

    // Firefox 1.0+
    var isFirefox = typeof InstallTrigger !== 'undefined';

    // Safari 3.0+ "[object HTMLElementConstructor]"
    var isSafari = /constructor/i.test(window.HTMLElement) || (function (p) {
        return p.toString() === "[object SafariRemoteNotification]";
    })(!window['safari'] || safari.pushNotification);

    // Internet Explorer 6-11
    var isIE = /*@cc_on!@*/false || !!document.documentMode;

    // Edge 20+
    var isEdge = !isIE && !!window.StyleMedia;

    // Chrome 1+
    var isChrome = !!window.chrome && !!window.chrome.webstore;

    // Blink engine detection
    var isBlink = (isChrome || isOpera) && !!window.CSS;

    var type = "";

    if (isOpera) {
        type = "opera";
    } else if (isFirefox) {
        type = "mozilla";
    } else if (isSafari) {
        type = "safari";
    } else if (isIE) {
        type = "ie";
    } else if (isEdge) {
        type = "edge";
    } else if (isChrome) {
        type = "crome";
    } else if (isBlink) {
        type = "blink";
    }
    return type;

}

$(function () {

    $('#pagination_number').data('holder', $('#pagination_number').attr('placeholder'));

    $('#pagination_number').focusin(function () {
        $(this).attr('placeholder', '');
    });
    $('#pagination_number').focusout(function () {
        $(this).attr('placeholder', $(this).data('holder'));
    });


})

function setTouchEvents() {

    $("body").find("input[onkeypress]").each(function () {
        var keyFunction = $(this).attr("onkeypress");
        $(this).attr("ontouchstart", keyFunction);
        $(this).attr("onkeypress", keyFunction);
//        alert($(this).attr("id"));
    });
}



//$.fn.blockInput = function (options)
//{
//    this.filter('input,textarea')
//            .keyup(function (e)
//            {
//
//                var char = String.fromCharCode(e.which),
//                        regex = new RegExp(options.regex);
//                alert("aa.." + regex.test(char));
//                return regex.test(char);
//            });
//
//    return this;
//};


$.fn.blockInput = function (options)
{
    this.filter('input,textarea')
            .keydown(function (e)
            {
                try {
                    var keyCode = e.which;

                    var char = String.fromCharCode(e.which),
                            regex = new RegExp(options.regex);

                    if (keyCode === 8 || keyCode === 46 || keyCode === 37 || keyCode === 39) {
                        return this;
                    } else
                        return regex.test(char);
                } catch (err) {
                    alert(err);
                }
            });

    return this;
};

//$('#' + this.attr("id")).blockInput({regex: '[0-9]'});


//
//(function () {
//    var input = document.getElementById("test");
//    input.addEventListener("keydown", onKeyDown, false)
//    input.addEventListener("keypress", onKeyPress, false)
//
//    function onKeyDown(e) {
//        alert('keyDown');
//    }
//
//    function onKeyPress(e) {
//        alert('keyPress');
//    }
//
//})();




//var globalCount = 0;
function setTouchEventsOnAjax(response) {
//----------------------------
//    var htmlVal = $.parseHTML(response);
//    $(htmlVal).find("input[onkeypress]").each(function () {
//        var keyFunction = $(this).attr("onkeypress");
//        alert("in function--");
//        $(this).attr("ontouchstart", keyFunction);
//        alert($(this).attr("id"));
//    });
//----------------------------
//    var count = ++globalCount;
//    $('#htmlToStringHidden').prepend("<div id=\"htmlToStringHidden-" + count + "\" ></div>");
//    $('#htmlToStringHidden-' + count).html(response);
//    setTouchEvents();
//    var aaa = $('#htmlToStringHidden-' + count).html();
//    $('#htmlToStringHidden-' + count).remove();
//    return  aaa;
    return  response;
}
function validateCertificateNumber(field) {
    var value = field.value;
//    var Exp = /^([0-9]){4}([a-zA-Z]){2}([0-9]){3}([a-zA-Z]){1}?$/;
    var Exp = /^[\d][a-zA-Z]{2}[\d]{4}[a-zA-Z]{3}$/;
    if (!lhsIsNull(value)) {
        if (value.length == 10) {
            if (!Exp.test(value)) {
                addActionError("Invalid Certificate Number");
//                openErrorModal("Invalid Certificate Number : 1-4 no's, 5-6 alphabets, 7-9 no's, 10 alphabet");
//                value.focus();
                return false;
            } else {
                removeError();
                return true;
            }
        } else {
            addActionError("Certificate number must be of 10 digits");
//            openErrorModal("Certificate number must be of 10 digits : 1-4 no's, 5-6 alphabets, 7-9 no's, 10 alphabet");
//            value.focus();
            return false;
        }
    } else {
        removeError();
    }
}// end method

//function validatePAN(field) {
//    var value = field.value;
//    if (value !== "" && value !== "null" && value !== null) {
//        var Exp = /^([a-zA-Z]){3}(a|A|p|P|f|F|c|C|h|H|t|T){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
//        if (!Exp.test(value)) {
//            addActionError("message","Invalid PAN Number");
//            field.focus();
//            return false;
//        } else {
//            removeError();
//            return true;
//        }
//    } else {
//        removeError();
//        return false;
//    }
//}


function validatePAN(field) {
    var checkbox = document.getElementById("checkbox").checked;
    if (checkbox === false) {
        var value = field.value;
        if (value !== "" && value !== "null" && value !== null) {
            var Exp = /^([a-zA-Z]){3}(a|A|B|b|p|P|f|F|c|C|G|k|K|g|h|H|L|l|J|j|t|T){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
            var str = check_one_pan_string(field);
            var str2 = check_two_pan_string(field);
            if (!Exp.test(value)) {
                if (str === 'true') {
                    return true;
                }
                if (str2 === 'true') {
                    return true;
                }
                addActionError("message", "Invalid PAN Number");
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

function check_one_pan_string(id) {
    var value = id.value;
    if (value === 'PANAPPLIED') {
        return 'true';
    } else {
        return 'false';
    }
}//end method

function check_two_pan_string(id) {
    var value = id.value;
    if (value === 'PANNOTAVBL') {
        return 'true';
    } else {
        return 'false';
    }
}//end method



function validateDateOnKeyDown(field, evt) {
    var dateVal = field.value;
//    var dateVal = document.getElementById(field).value;
    var dateValLen = dateVal.length;
    var theEvent = evt || window.event;
    var key = theEvent.keyCode || theEvent.which;
    key = String.fromCharCode(key);
    var regex = /[0-9]|[\.\-\/]|[\b]|[\t]|[\%]|[\.]|[\']/;

    if (!regex.test(key)) {//if other than no. or hyphen
        theEvent.returnValue = false;
        if (theEvent.preventDefault)
            theEvent.preventDefault();
    } else {//
//        var regex1 = /-/;
        var regex1 = /[\.\-\/]/;
        var regex2 = /[0-9]/;
        if (key.match(regex1)) {//for hyphen
            if (dateValLen !== 2 && dateValLen !== 5) {
                theEvent.returnValue = false;
                if (theEvent.preventDefault)
                    theEvent.preventDefault();
            }
        } else if (key.match(regex2)) {//for numbers
            if (dateValLen === 2 || dateValLen === 5) {
                theEvent.returnValue = false;
                if (theEvent.preventDefault)
                    theEvent.preventDefault();
            }
        }
    }
}//end method

function changeBSRCode(id) {
    var BSRCode = document.getElementById(id).value;
    var length = document.getElementById(id).value.length;
    if (length === 1) {
        document.getElementById(id).value = "000000" + BSRCode;
    } else if (length === 2) {
        document.getElementById(id).value = "00000" + BSRCode;
    } else if (length === 3) {
        document.getElementById(id).value = "0000" + BSRCode;
    } else if (length === 4) {
        document.getElementById(id).value = "000" + BSRCode;
    } else if (length === 5) {
        document.getElementById(id).value = "00" + BSRCode;
    } else if (length === 6) {
        document.getElementById(id).value = "0" + BSRCode;
    }
}//end function
function changededucteeRefNo(id) {
    var BSRCode = document.getElementById(id).value;
    var length = document.getElementById(id).value.length;
    if (length === 1) {
        document.getElementById(id).value = "000000000" + BSRCode;
    } else if (length === 2) {
        document.getElementById(id).value = "00000000" + BSRCode;
    } else if (length === 3) {
        document.getElementById(id).value = "0000000" + BSRCode;
    } else if (length === 4) {
        document.getElementById(id).value = "000000" + BSRCode;
    } else if (length === 5) {
        document.getElementById(id).value = "00000" + BSRCode;
    } else if (length === 6) {
        document.getElementById(id).value = "0000" + BSRCode;
    } else if (length === 7) {
        document.getElementById(id).value = "000" + BSRCode;
    } else if (length === 8) {
        document.getElementById(id).value = "00" + BSRCode;
    } else if (length === 9) {
        document.getElementById(id).value = "0" + BSRCode;
    }
}//end function
//
function lhsValidateAlphaAndNumber(evt) {
    var keyCode = (evt.which) ? evt.which : evt.keyCode
    if (keyCode != 46 && keyCode > 31
            && (keyCode < 48 || keyCode > 57) && (keyCode < 65 || keyCode > 90) && (keyCode < 97 || keyCode > 123)) {
        return false;
    }
    return true;
}
function AvoidSpace(event) {
    var k = event ? event.which : window.event.keyCode;
    if (k == 32)
        return false;
}
function validatePinCode(field)
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
}
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

function scrollPage() {
    $("html, body").animate({
        scrollTop: 50
    }, 900);
}//end function

function lhsValidatePAN(field) {

    var checkbox = document.getElementById("checkbox").checked;
    if (checkbox === false) {
        let value = field.value;
        let tdsType = $("#tdsType").val();
//        let quarter = $("#quarter").val();
        let Exp;
        if (!lhsIsNull(value)) {
            Exp = /^([a-zA-Z]){3}(a|A|b|B|c|C|f|F|g|G|h|H|j|J|k|K|l|L|p|P|t|T){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
            if (Exp.test(value)) {
                if (tdsType === '24Q') {
                    Exp = /^([a-zA-Z]){3}(p|P){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
                }
                if (!Exp.test(value) && tdsType === '24Q') {
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
    }
}//end function