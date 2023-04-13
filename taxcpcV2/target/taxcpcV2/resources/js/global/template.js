/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * used in index.jsp 
 * @param {type} btnField
 * @returns {undefined}
 */
//$(document).ready(function() {
//    $('.slider4').bxSlider({
//        mode: 'vertical',
//        slideWidth: 300,
//        minSlides: 3,
//        maxSlides: 3,
//        moveSlides: 1,
//        slideMargin: 10
//    });
//});
//$(document).ready(function() {
//    setInterval(callSlider, 3000);
//});
//function callSlider() {
//    $('.bx-next').click();
//}//end method
//
//function defaultSettings() {
//    var hei = $(window).height();
//    var wid = $(window).width();
//    if (wid > 990) {
//        $('.body_part').css('height', hei - 120 + 'px');
//    }
//}


function close_nav_overlay_div() {
    document.getElementById("nav-overlay").style.left = "100%";
    document.getElementById("nav-overlay").style.transition = "1.5s";
    document.getElementById("nav-overlay").style.visibility = "hidden";
//    transition-delay: 2s;
}//end function

function open_nav_overlay_div() {
    document.getElementById("nav-overlay").style.left = "inherit";
    document.getElementById("nav-overlay").style.transition = "1.5s";
    document.getElementById("nav-overlay").style.visibility = "visible";
//    transition-delay: 2s;
}//end function

var scrollVal = 0;
function hideNav() {
    var c = document.getElementById('navbar').className;
    var height = $(window).scrollTop();
    if (height > 0 && scrollVal == 0) {
        if (c.indexOf("hide-nav") > -1) {
            c = c.replace("hide-nav", "");
            document.getElementById('navbar').className = c;
        }
        scrollVal += 1;
    } else {
        scrollVal = 0;
    }
    if (c.indexOf("hide-nav") > -1) {
        c = c.replace("hide-nav", "");
        document.getElementById('navbar').className = c;
        $("#show").hide();
        $("#hide").show();
    } else {
        $("#show").hide();
        $("#hide").hide();
        document.getElementById('navbar').className += ' hide-nav';
    }
    return false;
}


function scrollLink(id) {
    if (id !== '' && id !== 'null' && id !== null) {
        document.getElementById(id).addEventListener("click", function(event) {
            event.preventDefault();
        });
        var h = document.getElementById(id).href;
        var arr = h.split("#")[1];
        if (arr !== '' && arr !== 'null' && arr !== null) {
            var top = document.getElementById(arr).offsetTop;
            $('html,body').animate({
                scrollTop: top
            }, 1500);
        }
        return false;
    }
    else {
        return false;
    }
}//end method
function onlyAlphabets(e, t) {
    try {
        if (window.event) {
            var charCode = window.event.keyCode;
        }
        else if (e) {
            var charCode = e.which;
        }
        else {
            return true;
        }
        if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123))
            return true;
        else
            return false;
    }
    catch (err) {
        alert(err.Description);
    }
}
function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

//function validateDateOnKeyDown(field, evt) {
//    var dateVal = field.value;
//    var dateValLen = dateVal.length;
//    var theEvent = evt || window.event;
//    var key = theEvent.keyCode || theEvent.which;
//    key = String.fromCharCode(key);
//    // alert(key)
//    var regex = /[0-9]|\-|[\b]|[\t]|[\%]|[\.]|[\']/;
//
//    if (!regex.test(key)) {//if other than no. or hyphen
//        theEvent.returnValue = false;
//        if (theEvent.preventDefault)
//            theEvent.preventDefault();
//    } else {//
//        var regex1 = /-/;
//        var regex2 = /[0-9]/;
//        if (key.match(regex1)) {//for hyphen
//            if (dateValLen != 2 && dateValLen != 5) {
//                theEvent.returnValue = false;
//                if (theEvent.preventDefault)
//                    theEvent.preventDefault();
//            }
//        } else {//for numbers
//            if (key.match(regex2)) {
//                if (dateValLen == 2 || dateValLen == 5) {
//                    theEvent.returnValue = false;
//                    if (theEvent.preventDefault)
//                        theEvent.preventDefault();
//                }
//            }
//        }
//    }
//
//
//
//
//}//end method
//
//function validateDateOnBlur(field) {
//    try {
//        var value = field.value;
//        if (value !== "" && value !== "null" && value !== null) {
//            var dataSplit = value.split("-")[2];
//            var yearSplit = dataSplit.split('');
//            var firstTwo = parseInt(yearSplit[0] + yearSplit[1]);
//            var lastTwo = yearSplit[2] + yearSplit[3];
//            if (firstTwo != 19) {
//                if (firstTwo != 20) {
//                    firstTwo = 20;
//                    for (var i = 2; i < lastTwo.length; i++) {//--- if year has less than four chars
//                        lastTwo = "0" + lastTwo;
//                    }
//                }
//            }
//            var dateFinal = value.split("-")[0] + "-" + value.split("-")[1] + "-" + firstTwo + lastTwo;
//            field.value = dateFinal;
//        }
//    } catch (e) {
//    }
//}//end method

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



function validateCode(id) {
    var TCode = document.getElementById(id).value;
    if (/[^a-zA-Z0-9]/.test(TCode)) {
        document.getElementById(id).value = '';
        alert('Input is not alphanumeric');
        return false;
    }
    return true;
}//end method

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

function ValidateAlpha(e, id)
{
    e = e || window.event || e.htmlEvent;
    var sss;
    if (window.event) {
        keycode = e.which ? window.event.which : window.event.keyCode;
        sss = e.which;
    }
    var value;

    if (e.which >= 65 && e.which <= 90) {
        value = true;
    } else if (e.which >= 97 && e.which <= 123) {
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
}//end function

function ValidateDeducteeNameAlpha(e, id) {
    e = e || window.event || e.htmlEvent;
    var sss;
    if (window.event) {
        keycode = e.which ? window.event.which : window.event.keyCode;
        sss = e.which;
    }
    var value;

    if (e.which >= 65 && e.which <= 90) {
        value = true;
    } else if (e.which >= 97 && e.which <= 123) {
        value = true;
    } else if (e.which === 8) {
        value = true;
    } else if (e.which === 32) {
        value = true;
    } else if (e.which === 0 || e.which === 46 || e.which === 37 || e.which === 39) {
        value = true;
    } else {
        value = false;
    }
//    alert(value);
    return value;
}//end function

function validateNegativeNumber(e, id) {
    e = e || window.event || e.htmlEvent;
    var sss;
    if (window.event) {
        keycode = e.which ? window.event.which : window.event.keyCode;
        sss = e.which;
    }
    var filedValue = document.getElementById(id).value;

    var value;
    if (isNull(filedValue) && e.which === 45) {
        value = true;
    } else if (e.which >= 48 && e.which <= 57) {
        value = true;
    } else if (e.which === 8) {

        value = true;
    } else if (e.which === 0 || e.which === 46 || e.which === 37 || e.which === 39) {

        value = true;
    } else {
        value = false;
    }
    return value;
}//end method

function doOnLoad(id) {
    var myCalendar;
    myCalendar = new dhtmlXCalendarObject(id);
    myCalendar.setDateFormat("%d-%m-%Y");
    myCalendar.hideTime();
//    $("#from_date").blur(function() {
//        myCalendar.hide();
//    });
    $("#certificate_date").blur(function() {
        myCalendar.hide();
    });
//    $("#certificate_valid_upto").blur(function() {
//        myCalendar.hide();
//    });
//    $("#dob").blur(function() {
//        myCalendar.hide();
//    });
//    $("#to_date").blur(function() {
//        myCalendar.hide();
//    });
//    $("#declaration_date").blur(function() {
//        myCalendar.hide();
//    });
//    $("#income_paid_date").blur(function() {
//        myCalendar.hide();
//    });
}//end function

function downloadExcelFileUsingFilter(url, id) {
    
    try {
        var action = document.getElementById(id).getAttribute("action");
        document.getElementById(id).setAttribute("action", url);
        document.getElementById(id).submit();
        document.getElementById(id).setAttribute("action", action);
    } catch (err) {

    }
}//end function


/**
 * function to disable button after click while operation in being performed
 * @param {type} btnField
 * @returns {undefined}
 */
function disableButton(btnField) {
    var className = btnField.className;
    className += " customDisabled";
    btnField.className = className;
}// end function

function enableButton(btnField) {
    var className = btnField.className;
    if (className.indexOf("customDisabled") > -1) {
        className = className.replace("customDisabled", "");
    }
    btnField.className = className;
}// end function

function hoverLogout() {
    document.getElementById("logout").style.color = "white";
}
function removeHoverLogout() {
    document.getElementById("logout").style.color = "#272e38";
}


// Reset QuickNavigation list with selected menu
function setQuickNavigationList() {
    $.ajax({
        url: "quickNavigationAction",
        data: {},
        type: 'POST',
        success: function(data) {
            if (data !== null && data !== '' && data !== 'null') {
                document.getElementById("quickNavigationListDIV").innerHTML = data;
            }
        },
    });
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
        xmlhttp.onreadystatechange = function() {
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