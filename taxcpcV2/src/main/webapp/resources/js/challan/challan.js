var globalradiobuttoId;

$(function () {
    $('#challantab').tablesorter();
});

function changeDateFilter() {
    document.getElementById("to_date").value = "";
}

function challanBrowsePage() {
    var cp = $("#globalcontextpath").val();
    window.location = cp + "/tdsChallan";
}

function challanEntryPage() {
    var cp = $("#globalcontextpath").val();
    window.location = cp + "/tdsChallanEntry?action=add";
}

function callCheckResult(id) {
    
    
    if (id !== null && id !== "" && id !== "null") {
       
        if (id === 'nill_challan_flag') {
            document.getElementById('book_entry_flag').checked = false;
            document.getElementById('challan_entry_flag').checked = false;
        }
        if (id === 'book_entry_flag') {
            document.getElementById('nill_challan_flag').checked = false;
            document.getElementById('challan_entry_flag').checked = false;
        }
        if (id === 'challan_entry_flag') {
            document.getElementById('book_entry_flag').checked = false;
            document.getElementById('nill_challan_flag').checked = false;
        }
        
        
    }
}

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


function validateDate(field) {
    var value = field.value;

    if (value !== "" && value !== "null" && value !== null) {
        var Exp = /^([0-2][0-9]|(3)[0-1])(\-)(((0)[0-9])|((1)[0-2]))(\-)\d{4}$/;
        if (!Exp.test(value)) {
          //  field.value = "";
        } else {
            validateDateOnBlur(field);
        }
    }
    get_month();
}//end method.


function get_month() {
    var date = document.getElementById("l_challan_date").value;
    var month = date.substring(3, 5);
    if (month === "01") {
        document.getElementById("month").value = "JAN";
    } else if (month === "02") {
        document.getElementById("month").value = "FEB";

    } else if (month === "03") {
        document.getElementById("month").value = "MAR";

    } else if (month === "04") {
        document.getElementById("month").value = "APR";

    } else if (month === "05") {
        document.getElementById("month").value = "MAY";

    } else if (month === "06") {
        document.getElementById("month").value = "JUNE";

    } else if (month === "07") {
        document.getElementById("month").value = "JULY";

    } else if (month === "08") {
        document.getElementById("month").value = "AUG";

    } else if (month === "09") {
        document.getElementById("month").value = "SEP";

    } else if (month === "10") {
        document.getElementById("month").value = "OCT";

    } else if (month === "11") {
        document.getElementById("month").value = "NOV";

    } else if (month === "12") {
        document.getElementById("month").value = "DEC";
    }

}

function validateDateOnBlur(field) {
    try {
        var value = field.value;
        var checkTodayDate = new Date();

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
            var checkDate = value.split("-")[1] + "/" + value.split("-")[0] + "/" + firstTwo + lastTwo;
            var convertToDateFormat = new Date(checkDate);

            if (convertToDateFormat > checkTodayDate) {
                field.value = "";
                openErrorModal("Future date is not allowed");
            } else {
                field.value = dateFinal;
            }
        }

    } catch (e) {
    }
}//end method

function validateDateOnKeyDown(field, evt) {
    var dateVal = field.value;
    var dateValLen = dateVal.length;
    var theEvent = evt || window.event;
    var key = theEvent.keyCode || theEvent.which;
    key = String.fromCharCode(key);
    var regex = /[0-9]|\-/;

    if (!regex.test(key)) {//if other than no or hyphen
        theEvent.returnValue = false;
        if (theEvent.preventDefault)
            theEvent.preventDefault();
    } else {//
        var regex1 = /-/;
        var regex2 = /[0-9]/;
        if (key.match(regex1)) {//for hyphen
            if (dateValLen != 2 && dateValLen != 5) {
                theEvent.returnValue = false;
                if (theEvent.preventDefault)
                    theEvent.preventDefault();
            }
        } else {//for numbers
            if (key.match(regex2)) {
                if (dateValLen == 2 || dateValLen == 5) {
                    theEvent.returnValue = false;
                    if (theEvent.preventDefault)
                        theEvent.preventDefault();
                }
            }
        }
    }
}//end method


function remove_comma_from_amt(id) {
    var sourceString = document.getElementById(id).value;
    var out = sourceString.replace(/[`~!@#$%^&*()_|+\-=?;:'",<>\{\}\[\]\\\/]/gi, '');
    document.getElementById(id).value = out;
    var value = document.getElementById(id).value;
//    var re = /^-?[0-9]+$/;
    var re = /^[1-9]\d*(\.[0]+)?$/;
    if (!isNull(value)) {
        if (re.test(value)) {
            removeError();
            CheckDecimal();
            document.getElementById(id).value = value.split("\.")[0];
        } else {
            openErrorModal("Decimal number not allowed, Please remove decimal");
            scrollPage();
        }
    }
}//end function

function remove_comma_from_amt_msg(id,mes) {
    var sourceString = document.getElementById(id).value;
    var out = sourceString.replace(/[`~!@#$%^&*()_|+\-=?;:'",<>\{\}\[\]\\\/]/gi, '');
    document.getElementById(id).value = out;
    var value = document.getElementById(id).value;
    var re = /^-?[0-9]+$/;
   // var re = /^[1-9]\d*(\.[0]+)?$/;
    if (!isNull(value)) {
       
        if (re.test(value)) {
            removeError();
            CheckDecimal();
            document.getElementById(id).value = value.split("\.")[0];
        } else {
            openErrorModal("Decimal number not allowed in '"+mes+"'<br> Please remove decimal");
            scrollPage();
        }
    }
}//end function

function isNull(value) {
    value = value.trim();
    if (value !== "" && value !== "null" && value !== null)
        return false;
    else
        return true;
}//end function

function removeError() {
    // document.getElementById("challan_notification").style.visibility = "hidden";
    //document.getElementById("challan_notification").style.display = "none";
}


function CheckDecimal()
{
    var inputtxt = document.getElementById('tds_challan_tds_amt').value;
    var decimal = /^[-+]?[0-9]+\.[0-9]+$/;
    if (inputtxt.match(decimal))
    {
        return true;
    } else
    {
        return false;
    }
}

function changeChalanNo(id) {
    var ChalanNo = document.getElementById(id).value;
    var length = document.getElementById(id).value.length;
    if (length === 1) {
        document.getElementById(id).value = "0000" + ChalanNo;
    } else if (length === 2) {
        document.getElementById(id).value = "000" + ChalanNo;
    } else if (length === 3) {
        document.getElementById(id).value = "00" + ChalanNo;
    } else if (length === 4) {
        document.getElementById(id).value = "0" + ChalanNo;
    }
}//end function

function paste() {
    window.addEventListener('load', function (e)
    {
        document.getElementById('tds_challan_tds_amt').addEventListener(' keydown', function
                (e)
        {
            if (e.keyCode == 86) //86 is Paste event
            {
                alert('You may not paste here !');
                e.preventDefault();
            }
            document.getElementById('events').innerHTML += e.keyCode + ' ' +
                    e.which + '<br />';
        }, false);
    }, false);
}//end function

function book_entry_disable_enable(id) {
//    var client_type = document.getElementById("client_type_code").value;
//    if (client_type === 'A') {
//        document.getElementById("book_entry_flag").disabled = false;
//    } else {
//        document.getElementById("book_entry_flag").disabled = true;
//    }
//}
    //callCheckResult(id);
    radiobuttonchangeornot(id);
}//end function

function saveChallanContentData(field)
{
    var tds_challan_no = document.getElementById("tds_challan_no").value;
    var tds_challan_tds_amt = document.getElementById("tds_challan_tds_amt").value;
    var tds_challan_minor_head = document.getElementById("tds_challan_minor_head").value;
    var l_challan_date = document.getElementById("l_challan_date").value;
    var bank_bsr_code = document.getElementById("bsr_code").value;
    var nill_challan_flag = document.getElementById("nill_challan_flag").checked;
    var book_entry_flag = document.getElementById("book_entry_flag").checked;
    var tds_challan_int_amt = document.getElementById("tds_challan_int_amt").value;
    var tds_challan_other_amt = document.getElementById("tds_challan_other_amt").value;
    

    if (bank_bsr_code === 'null' || bank_bsr_code === "" || bank_bsr_code === null) {
        addActionError("message", "Enter BSR Code of Bank");
        document.getElementById("bsr_code").focus();
    } else if (l_challan_date === 'null' || l_challan_date === "" || l_challan_date === null) {
        addActionError("message", "Challan Date cannot be empty");
        document.getElementById("l_challan_date").focus();
    } else if (tds_challan_no === 'null' || tds_challan_no === "" || tds_challan_no === null) {
        addActionError("message", "Challan Serial Number cannot be empty");
        document.getElementById("tds_challan_no").focus();
    } else if (tds_challan_tds_amt === 'null' || tds_challan_tds_amt === "" || tds_challan_tds_amt === null) {
        addActionError("message", "Challan Amount cannot be empty");
        document.getElementById("tds_challan_tds_amt").focus();
    } else if ((tds_challan_minor_head === 'null' || tds_challan_minor_head === "" || tds_challan_minor_head === null) && nill_challan_flag === false && book_entry_flag === false) {
        addActionError("message", "Select Minor Head");
    } else if (!isNulldecimal(tds_challan_int_amt) && CheckDecimal(tds_challan_int_amt)){
         addActionError("message","Decimal number not allowed, Please remove decimal"); 
    } else if (!isNulldecimal(tds_challan_other_amt) && CheckDecimal(tds_challan_other_amt)){
          addActionError("message","Decimal number not allowed, Please remove decimal"); 
    } else if (tds_challan_tds_amt !== 'null' && tds_challan_tds_amt !== "" && tds_challan_tds_amt !== null) {
        if (nill_challan_flag === false) {
            var value = document.getElementById("tds_challan_tds_amt").value;
            var re = /^-?[0-9]+$/;
            if (re.test(value)) {
                removeError();
                var amt = parseFloat(tds_challan_tds_amt);
                if (amt <= 0) {
                    addActionError("message", "Please Check Nill Challan Or Insert TDS Amount Greater Than 0");
                    //scrollPage();
                } else {
                    saveChallanContentDataFunction(field);
                }
            } else {
                addActionError("message", "Decimal number not allowed, Please remove decimal");
                // scrollPage();
            }
        } else {
            saveChallanContentDataFunction(field);
        }

    }
}


function saveChallanContentDataFunction(field) {

    var cp = document.getElementById("globalcontextpath").value;
    var btnText = field;
    if (btnText.indexOf("Continue") > -1) {
        document.getElementById("saveAndContinue").value = "true";
    } else {
        document.getElementById("saveAndContinue").value = "false";
    }
    if (!ajax_call_enabled) {
        var btnSave = document.getElementById(field.id);
        // document.getElementById("browse_process_indicator_css1").style.visibility = "visible";
        $("#challan_entry_page").submit(function (e)
        {
            var postData = $(this).serializeArray();
            var formURL = "getChallanCRUDAction?action=save";
            $.ajax(
                    {
                        url: formURL,
                        type: "POST",
                        data: postData,
                        success: function (data, textStatus, jqXHR)
                        {
                            //data: return data from server
                            ajax_call_enabled = false;
                            if (data === "success") {
                                let url = cp + "/tdsChallanEntry?action=add";
                                openSuccessModal("Data Saved Successfully", "window.location='" + url + "'");
                            } else if (data === "success1") {// for continue
                                //  window.location.reload();
                                let url = cp + "/tdsChallan";
                                openSuccessModal("Data Saved Successfully", "window.location='" + url + "'");
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown)
                        {
                            openErrorModal("Some Error Occured");
                            ajax_call_enabled = false;  //if fails
                        }
                    });
            e.preventDefault(); //STOP default action
            //e.unbind(); //unbind. to stop multiple form submit.
        });
        removeError();
        ajax_call_enabled = true;
        // disableButton(btnSave);
        $("#challan_entry_page").submit(); //Submit  the FORM
    } else {
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


//
//function deleteChallan(rowidSeq) {
//    var cp = document.getElementById("globalcontextpath").value;
//    var status = confirm("Do you want to delete this record ?");
//    if (status === true) {
//        window.location = cp + "/getChallanCRUDAction?action=DeleteChallan" + "&rowidSeq=" + encodeURIComponent(rowidSeq);
//    } else {
//        window.location = cp + "/tdsChallan";
//    }
//}//end function

function deleteTDS(rowidSeq) {
    var cp = document.getElementById("globalcontextpath").value;
    document.getElementById("dialog-confirm_delete").style.display = "block";
    $(function () {
        $("#dialog-confirm_delete").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                "Ok": function () {
                    $(this).dialog("close");
                    window.location = cp + "/getChallanCRUDAction?action=DeleteChallan" + "&rowidSeq=" + encodeURIComponent(rowidSeq);
                },
                Cancel: function () {
                    $(this).dialog("close");
                }
            }
        });
    });
//    }
}//end function

function checkEditCheckResult() {
    //alert(1);
    var h_nill_challan_flag = document.getElementById("h_nil_challan_flag").value;
    var h_book_entry_flag = document.getElementById("h_book_entry_flag").value;
    if (h_nill_challan_flag !== null && h_nill_challan_flag !== "" && h_nill_challan_flag !== "null" && h_nill_challan_flag === 'Y') {
        document.getElementById('nill_challan_flag').checked = true;
        document.getElementById('book_entry_flag').checked = false;
    }
    if (h_book_entry_flag !== null && h_book_entry_flag !== "" && h_book_entry_flag !== "null" && h_book_entry_flag === 'Y') {
        document.getElementById('nill_challan_flag').checked = false;
        document.getElementById('book_entry_flag').checked = true;
    }
    getCheckBoxVale();
}//end function



function getCheckBoxVale(id) {
    try {
        callCheckResult(id);
    } catch (err) {
    }
    var nill_challan_flag = document.getElementById("nill_challan_flag").checked;
    var book_entry_flag = document.getElementById("book_entry_flag").checked;

    if (nill_challan_flag === true) {
        document.getElementById("bsr_code").disabled = true;
        document.getElementById("bank_name").disabled = true;
//        document.getElementById("month").disabled = true;
        document.getElementById("tds_challan_no").disabled = true;
        document.getElementById("l_challan_date").disabled = true;
        document.getElementById("tds_challan_tds_amt").disabled = true;
//        document.getElementById("tds_section").disabled = true;
        document.getElementById("tds_challan_int_amt").disabled = true;
        document.getElementById("tds_challan_other_amt").disabled = true;
        document.getElementById("tds_challan_minor_head").value = "";
        document.getElementById("tds_challan_minor_head").disabled = true;
        document.getElementById("bsrcodespanid").style.visibility = "hidden";
//        document.getElementById("monthspanid").style.visibility = "hidden";
        document.getElementById("challanslnospanid").style.visibility = "hidden";
        document.getElementById("challandatespanid").style.visibility = "hidden";
        document.getElementById("tdsamtspanid").style.visibility = "hidden";
        document.getElementById("minorheadspanid").style.visibility = "hidden";
    } else {
        if (book_entry_flag === true) {
            document.getElementById("tds_challan_minor_head").value = "";
            document.getElementById("tds_challan_minor_head").disabled = true;
            document.getElementById("minorheadspanid").style.visibility = "hidden";
        } else {
            document.getElementById("tds_challan_minor_head").disabled = false;
            document.getElementById("tds_challan_minor_head").value = "200";
            document.getElementById("minorheadspanid").style.visibility = "visible";
        }
        document.getElementById("bsr_code").disabled = false;
        document.getElementById("bank_name").disabled = false;
//        document.getElementById("month").disabled = false;
        document.getElementById("tds_challan_no").disabled = false;
        document.getElementById("l_challan_date").disabled = false;
        document.getElementById("tds_challan_tds_amt").disabled = false;
//        document.getElementById("tds_section").disabled = false;
        document.getElementById("tds_challan_int_amt").disabled = false;
        document.getElementById("tds_challan_other_amt").disabled = false;

        document.getElementById("bsrcodespanid").style.visibility = "visible";
//        document.getElementById("monthspanid").style.visibility = "visible";
        document.getElementById("challanslnospanid").style.visibility = "visible";
        document.getElementById("challandatespanid").style.visibility = "visible";
        document.getElementById("tdsamtspanid").style.visibility = "visible";

    }
}//end function

function changeDateFormat() {
    var chdate = document.getElementById("h_tds_challan_date").value;
    var date = chdate.split("-");
    var months = ['JAN', 'FEB', 'MAR', 'APR', 'MAY', 'JUN', 'JUL', 'AUG', 'SEP', 'OCT', 'NOV', 'DEC'];
    for (var j = 0; j < months.length; j++) {
        if (date[1] == months[j]) {
            date[1] = months.indexOf(months[j]) + 1;
        }
    }
    if (date[1] < 10) {
        date[1] = '0' + date[1];
    }
    var formattedDate = date[0]+'-' + date[1] +'-'+ date[2];
    document.getElementById("l_challan_date").value = formattedDate;
}//end function


function checkTdsLinkResult() {
    var hLink = document.getElementById("hLink").value;
    document.getElementById("showLinkDiv").innerHTML = hLink;
}//end function

function updateChallanContentData(field) {
    var ret = /^-?[0-9]+$/;
    var bank_bsr_code = document.getElementById("bsr_code").value;
//    var month = document.getElementById("month").value;
    var tds_challan_no = document.getElementById("tds_challan_no").value;
    var tds_challan_tds_amt = document.getElementById("tds_challan_tds_amt").value;
    var l_challan_date = document.getElementById("l_challan_date").value;
    var tds_challan_minor_head = document.getElementById("tds_challan_minor_head").value;
    var nill_challan_flag = document.getElementById("nill_challan_flag").checked;
    var book_entry_flag = document.getElementById("book_entry_flag").checked;
    // var mapAllocatedAmount = document.getElementById("mapAllocatedAmount").value;
    var tds_challan_int_amt = document.getElementById("tds_challan_int_amt").value;
    var tds_challan_other_amt = document.getElementById("tds_challan_other_amt").value;
//    if (mapAllocatedAmount === "" || mapAllocatedAmount === "null" || mapAllocatedAmount === null) {
//        mapAllocatedAmount = "0";
//    }
//    mapAllocatedAmount = parseFloat(mapAllocatedAmount);

    if (tds_challan_int_amt === "" || tds_challan_int_amt === "null" || tds_challan_int_amt === null) {
        tds_challan_int_amt = "0";
    }
   // tds_challan_int_amt = parseFloat(tds_challan_int_amt);
  
 
   
    
    if (tds_challan_other_amt === "" || tds_challan_other_amt === "null" || tds_challan_other_amt === null) {
        tds_challan_other_amt = "0";
    }
 //   tds_challan_other_amt = parseFloat(tds_challan_other_amt);
 
    

    if (nill_challan_flag === true && book_entry_flag === false) {
        updateChallanDataFunction(field);
    } else {
        if (bank_bsr_code === 'null' || bank_bsr_code === "" || bank_bsr_code === null) {
            addActionError("message", "Enter BSR Code of Bank");
            $('#bsr_code').focus();
            //scrollPage();
        } else if (l_challan_date === 'null' || l_challan_date === "" || l_challan_date === null) {
            addActionError("message", "Challan Date cannot be empty");
            $('#l_challan_date').focus();
        } else if (tds_challan_no === 'null' || tds_challan_no === "" || tds_challan_no === null) {
            addActionError("message", "Challan Serial Number cannot be empty");
            $('#tds_challan_no').focus();
        } else if (tds_challan_tds_amt === 'null' || tds_challan_tds_amt === "" || tds_challan_tds_amt === null) {
            addActionError("message", "Challan Amount cannot be empty");
            $('#tds_challan_tds_amt').focus();
        } else if ((tds_challan_minor_head === 'null' || tds_challan_minor_head === "" || tds_challan_minor_head === null) && nill_challan_flag === false && book_entry_flag === false) {
            addActionError("message", "Select Minor Head");
            $('#tds_challan_minor_head').focus();
        } else if (!isNulldecimal(tds_challan_int_amt) && CheckDecimal(tds_challan_int_amt)){
         addActionError("message","Decimal number not allowed, Please remove decimal"); 
        } else if (!isNulldecimal(tds_challan_other_amt) && CheckDecimal(tds_challan_other_amt)){
          addActionError("message","Decimal number not allowed, Please remove decimal"); 
         } else if (tds_challan_tds_amt !== 'null' && tds_challan_tds_amt !== "" && tds_challan_tds_amt !== null) {
            if (nill_challan_flag === false) {
                var value = document.getElementById("tds_challan_tds_amt").value;
                var re = /^-?[0-9]+$/;
                if (re.test(value)) {
                    //     removeError();
                    var amt = parseFloat(tds_challan_tds_amt);
                    if (amt <= 0) {
                        addActionError("message", "Please Check Nill Challan Or Insert TDS Amount Greater Than 0");
                        //scrollPage();
                    } else {
                        updateChallanDataFunction(field);
                    }
                } else {
                    addActionError("message","Decimal number not allowed, Please remove decimal");
                    //scrollPage();
                }
            } else {
                updateChallanDataFunction(field);
            }
        } else {
            updateChallanDataFunction(field);
        }
    }
}//end function



function updateChallanDataFunction(field) {
    var cp = document.getElementById("globalcontextpath").value;
    var hprocrssFlag = document.getElementById("hprocrssFlag").value;
    if (hprocrssFlag === "" || hprocrssFlag === "null" || hprocrssFlag === null) {
        hprocrssFlag = "false";
    }
    if (!ajax_call_enabled) {
        // scrollPage();
        // document.getElementById("browse_process_indicator_css1").style.visibility = "visible";
        var btnUpdate = document.getElementById(field.id);
        $("#challan_entry_page").submit(function (e)
        {
            var postData = $(this).serializeArray();
            var formURL = "getChallanCRUDAction?action=update";
            $.ajax(
                    {
                        url: formURL,
                        type: "POST",
                        data: postData,
                        success: function (data, textStatus, jqXHR)
                        {
                            //data: return data from server
                            ajax_call_enabled = false;
                            //alert(data);
                            if (data.match("success")) {
                                if (hprocrssFlag === 'true') {
                                    window.parent.location.reload();
                                } else {
                                    openSuccessModal("Records Updated Successfully", 'window.location = "' + cp + '/tdsChallan";');
                                }
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown)
                        {
                            ajax_call_enabled = false;  //if fails
                            //enableButton(btnUpdate);
                        }
                    });
            e.preventDefault(); //STOP default action
            //e.unbind(); //unbind. to stop multiple form submit.
        });

        ajax_call_enabled = true;
        $("#challan_entry_page").submit(); //Submit  the FORM         } else {
    }
}//end function


function mapChallanAllocation(id) {
    var cp = document.getElementById("globalcontextpath").value;

    var splitid = id.split("~");
    var count = splitid[1];
    var l_tds_code = (document.getElementById("l_tds_code~" + count).value).trim();
    var l_tds_challan_tds_amt = document.getElementById("l_tds_challan_tds_amt~" + count).value;
    var l_rowid_seq = document.getElementById("l_rowid_seq~" + count).value;
    var l_allocated_amount = document.getElementById("l_allocated_amount~" + count).value;
    var l_month = document.getElementById("l_month~" + count).value;
    var l_tds_challan_date = document.getElementById("l_tds_challan_date~" + count).value;
    var l_tds_challan_no = document.getElementById("l_tds_challan_no~" + count).value;
    var l_pending_amt = document.getElementById("l_pending_amt~" + count).value;
    var l_int_amt_amt = document.getElementById("l_int_pmt_amt~" + count).value;
    var l_other_amt_amt = document.getElementById("l_other_payment_amt~" + count).value;
    var l_pending_amt = document.getElementById("l_pending_amt~" + count).value;
    if (l_tds_challan_tds_amt === "" || l_tds_challan_tds_amt === "null" || l_tds_challan_tds_amt === null) {
        l_tds_challan_tds_amt = '0';
    }
    if (l_allocated_amount === "" || l_allocated_amount === "null" || l_allocated_amount === null) {
        l_allocated_amount = '0';
    }
    if (l_pending_amt === "" || l_pending_amt === "null" || l_pending_amt === null) {
        l_pending_amt = '0';
    }
    if (l_int_amt_amt === "" || l_int_amt_amt === "null" || l_int_amt_amt === null) {
        l_int_amt_amt = '0';
    }
    if (l_other_amt_amt === "" || l_other_amt_amt === "null" || l_other_amt_amt === null) {
        l_other_amt_amt = '0';
    }

    l_tds_challan_tds_amt = parseFloat(l_tds_challan_tds_amt);
    l_allocated_amount = parseFloat(l_allocated_amount);
    l_int_amt_amt = parseFloat(l_int_amt_amt);
    l_other_amt_amt = parseFloat(l_other_amt_amt);
    l_pending_amt = parseFloat(l_pending_amt);
    l_tds_challan_tds_amt = (l_tds_challan_tds_amt - (l_int_amt_amt + l_other_amt_amt));

    window.location = cp + "/tdsChallanAllocation?identifyFlag=map_transaction" + "&tdsSection=" + encodeURIComponent(l_tds_code)
            + "&tdsAmount=" + l_tds_challan_tds_amt + "&ParaRowidSeq=" + l_rowid_seq + "&allocatedAmount=" + l_allocated_amount
            + "&ChallanMonth=" + l_month + "&ChallanDate=" + l_tds_challan_date + "&ChallanNo=" + l_tds_challan_no + "&pendingForalloc=" + l_pending_amt;
}



function refreshAllocation() {

    var cp = document.getElementById("globalcontextpath").value;
    var url = cp + "/challanAjax?rebuild=true";
    $.ajax({
        url: url,
        type: 'POST',
        success: function (data, textStatus, jqXHR) {

            if (data === 'success') {
                addActionError('message', 'Refresh Allocation Successfully');
                window.location = cp + "/tdsChallan";
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('error thrown');
        }

    });
}

var l_checkAmount = 0;
var chkAllCount = 0;
function getCheckedAll(id) {
    var totalAllocatedAmount = 0;
    var foramtTdsAmount = $("#foramtTdsAmount").html();
    var tdsAmtConcat = '';
    var tdsAllocatedAmtConcat = '';
    var split_id1 = foramtTdsAmount.split(",");
    for (var i = 0; i < split_id1.length; i++) {
        tdsAmtConcat += split_id1[i];
    }
    var l_tdsAmount = parseFloat(tdsAmtConcat);//tds total amount
    var putCount = $("#putCount").html();
    var totalCheckedCount = parseInt(putCount);//total checked data
    chkAllCount = totalCheckedCount;


    var ttlTdsAmount = $("#ttlTdsAmount").html();
    var split_id2 = ttlTdsAmount.split(",");
    for (var j = 0; j < split_id2.length; j++) {
        tdsAllocatedAmtConcat += split_id2[j];
    }
    var l_allocatedAmount = parseFloat(tdsAllocatedAmtConcat);//total allocated amount
    l_checkAmount = l_allocatedAmount;

    if (document.getElementById(id).checked === true) {
        $(".unallocated, .allocated").each(function (index, row) {
            if (!($(this).find("input").is(":checked"))) {
                try {
                    var amt = document.getElementById('tdsAmtId#' + (index + 1)).innerHTML;
                    var split_amt_id = amt.split(",").join('');

                    var selectedAmt = parseFloat(split_amt_id);
                    l_checkAmount += selectedAmt;
                    if (l_checkAmount <= l_tdsAmount) {
                        chkAllCount++;
                        totalAllocatedAmount = l_checkAmount;
                        document.getElementById('chk#' + (index + 1)).checked = true;
                    }
                } catch (e) {
                    console.log(e);
                }
            }
        });
    } else {
        $(".unallocated, .allocated").each(function (index) {
            if ($(this).find("input").is(":checked")) {
                chkAllCount = 0;
                totalAllocatedAmount = 0;
                document.getElementById('chk#' + (index + 1)).checked = false;
            }
        });
    }
    $("#putCount").html(chkAllCount);
    $("#ttlTdsAmount").html(formatNumber(totalAllocatedAmount));
    $("#pendingforalloc").html(formatNumber(l_tdsAmount - totalAllocatedAmount));

}//end method
function formatNumber(number) {
    var number = number.toFixed(2) + '';
    var x = number.split('.');
    var x1 = x[0];
    var x2 = x.length > 1 ? '.' + x[1] : '';
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) {
        x1 = x1.replace(rgx, '$1' + ',' + '$2');
    }
    return x1 + x2;
}
function chkAllocateAllTdsRecord() {
    $(".unallocated, .allocated").each(function (index) {
        if ($(this).find("input").is(":checked")) {
            chkAllCount = 0;
            totalAllocatedAmount = 0;
            document.getElementById('chk#' + (index + 1)).checked = false;
        } else {
            document.getElementById('chk#' + (index + 1)).checked = true;
        }
    });
    var ckh = document.getElementById("allocateAllRecordID").checked ? "Y" : "N";
    if (ckh === "Y") {
        let status = $('#allocationStatus').val();
        let selectMsg = "";
        if (status === 'ALC') {
            document.getElementById("dialog-confirm_unaRcrdTDS").style.display = "block";
            selectMsg = "#dialog-confirm_unaRcrdTDS";
        } else if (status === 'UNA') {
            document.getElementById("dialog-confirm_allRcrdTDS").style.display = "block";
            selectMsg = "#dialog-confirm_allRcrdTDS";
        }

        $(function () {
            $(selectMsg).dialog({
                resizable: false,
                font: 10,
                modal: true,
                buttons: {
                    "Ok": function () {
                        $(this).dialog("close");
                        showProcessIndicator();
//                        document.getElementById("l_btn_div").style.display = "none";

                        var identifyFlag = $("#identifyFlag").val();
//                        var htdsSection = $("#htdsSection").val();
//                        var hChallanMonth = $("#hChallanMonth").val();
                        var ParaRowidSeq = $("#ParaRowidSeq").val();
                        var ParaTdsAmount = $("#tdsAmount").val();
                        var allocationStatus = $("#allocationStatus").val();
                        var ParaAllocatedTdsAmount = document.getElementById("ttlTdsAmount").innerHTML;
                        $.ajax({
                            url: "allocateAllTdsAmountDataAction",
                            data: {
                                search: "true",
                                identifyFlag: identifyFlag,
                                ParaRowidSeq: ParaRowidSeq,
                                ParaTdsAmount: ParaTdsAmount,
                                ParaAllocatedTdsAmount: ParaAllocatedTdsAmount,
                                allocationStatus: allocationStatus
                            },
                            type: 'POST',
                            dataType: 'html',
                            success: function (data) {
                                var str = data.split('#');
                                var sucessflag = str[0];
                                var message = str[1];
                                if (sucessflag === 'success') {
                                    openSuccessModal(message, "callUrl('/tdsChallan');");
                                    refreshAllocation();
//                                    var cp = document.getElementById("globalcontextpath").value;
//                                    var url = cp + "/tdsChallan";
//                                    window.location = url;
                                } else {
                                    document.getElementById("allocateAllRecordID").checked = false;
                                    hideProcessIndicator();
//                                    document.getElementById("l_btn_div").style.display = "block";
                                    openErrorModal("Some Error Occured , Please Try Again");
                                    $("html, body").animate({
                                        scrollTop: 300
                                    }, 900);
                                }
                            }
                        });
                    },
//        }
//        else {
                    Cancel: function () {
                        $(this).dialog("close");
                        document.getElementById("allocateAllRecordID").checked = false;
                        $(".unallocated, .allocated").each(function (index) {
                            if ($(this).find("input").is(":checked")) {
                                chkAllCount = 0;
                                totalAllocatedAmount = 0;
                                document.getElementById('chk#' + (index + 1)).checked = false;
                            } else {
                                document.getElementById('chk#' + (index + 1)).checked = true;
                            }
                        });
                    }
                }
            });
        });
//        }
    }
}//end function

//allocation start
var l_count = 0;
var l_count1 = 0;
var getTdsAmout = 0;
function getMapTransaction(id) {
    try {
        var tbl = document.getElementById("table");
        var total_record = tbl.rows.length;
        var NoOFRow = (total_record - 3);
        var count = 0;
//        var seq = $('#table tbody tr td:nth-child(4)').html();
//        var count1 = lhsIsNull(seq) ? 0 : parseInt(seq) - 1;
        for (var i = 1; i <= (NoOFRow); i++) {
            try {
                var ckh = document.getElementById('chk#' + i).checked ? "Y" : "N";
                if (ckh === "Y") {
                    count++;
                }
//                count1++;
            } catch (e) {
            }
        }//end for
        if (NoOFRow === count) {
            document.getElementById("chkAll").checked = true;
        } else {
            document.getElementById("chkAll").checked = false;
        }
    } catch (err) {
        console.log(err);
    }

    var concatAllocatedAmount = '';
    var concatSelectedAmount = '';

    var split_id = id.split("#");

    var tdsAmount = $("#tdsAmount").val();//hidden tds amount
    var l_tdsAmount = parseInt(tdsAmount);


    var putCount = $("#putCount").html();
    l_count = putCount;


    var totalSelected = parseInt(putCount);//total number of checked box selected by default

    var ttlAllocatedTdsAmount = $("#ttlTdsAmount").html();


    var split_allocatedAmt = ttlAllocatedTdsAmount.split(",");

    for (var i = 0; i < split_allocatedAmt.length; i++) {
        concatAllocatedAmount += split_allocatedAmt[i];
    }
    var allocatedAmount = parseFloat(concatAllocatedAmount);// total amount allocated

    var rowTdsAmount = document.getElementById("tdsAmtId#" + split_id[1]).innerHTML;

    var getdd = rowTdsAmount.split(",");
    for (var i = 0; i < getdd.length; i++) {
        concatSelectedAmount += getdd[i];
    }
    var selectedAmount = parseFloat(concatSelectedAmount);//select amount for allocation
    var generatedAllocatedAmount = (allocatedAmount + selectedAmount);
    var finalAllocatedAmount = 0;
    var finalChecCount = 0;
    if (document.getElementById(id).checked === true) {
        if (generatedAllocatedAmount > l_tdsAmount) {
            finalAllocatedAmount = allocatedAmount;
            document.getElementById(id).checked = false;
            finalChecCount = l_count;
            var msg = "Allocated TDS Amount should be less than or equal to Challan Amount";
            document.getElementById(id).checked = false;
//            $("#putValidateMsg").css("display", "block");
//            $("#putValidateMsg").css("color", "red");
//            $("#putValidateMsg").html(msg);
            openErrorModal(msg);
            $("html, body").animate({
                scrollTop: 300
            }, 900);
        } else {
            l_count++;
            finalChecCount = l_count;
            finalAllocatedAmount = generatedAllocatedAmount;
        }
//        openActionDiv();
    } else {
        totalSelected--;
        finalChecCount = totalSelected;
        finalAllocatedAmount = allocatedAmount - selectedAmount;
    }
    $("#ttlTdsAmount").html(formatNumber(finalAllocatedAmount));
    $("#pendingforalloc").html(formatNumber(tdsAmount - finalAllocatedAmount));
    $("#putCount").html(finalChecCount);
//    openActionDiv();
}//end function

function getMultipleCheckFlag() {
    var len = 0;
    try {
        var count = $('#table tbody:has(tr:has(td))').find('input[type="checkbox"]:checked');
        len = count.length;
    } catch (err) {
    }
    return len;
}//end function

function openActionDiv(id, rowid) {
    var len = getMultipleCheckFlag();
    if (parseInt(len) > 0 && len === 1) {
        $("#actiondiv").show(true);
//        singleCheckBtnDisplay(true);

//        var splitId = id.split("~")[1];
//        $("#editFormId").val("editForm~" + splitId);
//        $("#viewFormId").val("viewForm~" + splitId);
//        $("#rowidForDelete").val(rowid);
    } else if (len > 1) {
//        singleCheckBtnDisplay(false);
        $("#actiondiv").show(true);
    } else {
        $("#actiondiv").hide(true);
    }
}//end function

$(document).ready(function () {
    //setAllChecked();
    $('#btnUpdateChallenRowId').click(function () {
        var ckh = document.getElementById("allocateAllRecordID").checked ? "Y" : "N";
        if (ckh === "Y") {
            chkAllocateAllTdsRecord();
        } else {
//            $("#putValidateMsg").css("display", "none");
            $("#actiondiv").hide(true);
//            document.getElementById("l_btn_div").style.display = "none";
//            document.getElementById("l_btn_div1").style.display = "none";
            var identifyFlag = $("#identifyFlag").val();
            var htdsSection = $("#htdsSection").val();
            var hChallanMonth = $("#hChallanMonth").val();
            var values = new Array();
            $(".allocated, .unallocated").each(function () {
                var column = '';
                var column1 = '';
                if ($(this).find("input").is(":checked")) {
                    column = $(this).find("td:eq(0)").html();
                    column1 = $(this).find("td:eq(1)").html();
                    values.push(column + "#" + column1 + "#true");
                } else {
                    column1 = $(this).find("td:eq(1)").html();
                    column = $(this).find("td:eq(0)").html();
                    if (column1 !== '' && column1 !== 'null') {
                        values.push(column + "#" + column1 + "#false");
                    }
                }
            });
            var rowidData = values.join(",");
            var ParaRowidSeq = $("#ParaRowidSeq").val();

            showProcessIndicator();
            $.ajax({
                url: "updateChallanAllocationData", // old action - updateTdsChallenLoadRowIdContentData
                data: {
                    ArrRowIdSeq: rowidData,
                    ParaRowidSeq: ParaRowidSeq,
                    identifyFlag: identifyFlag
                },
                type: 'POST',
                dataType: 'html',
                success: function (data) {
                    if (data === 'success') {
                        openSuccessModal(globalUpdateMessage, "window.location.reload()");
                    } else {
                        hideProcessIndicator();
//                        $(".action-section").show(true);
                        openErrorModal("For allocation, Please select the checkbox");
                    }
                }
            });
        }
    });
//    $("#back_btn").click(function () {
//        var cp = document.getElementById("globalcontextpath").value;
//        window.location = cp + "/challans";
//    });
});

function setAllChecked() {
//    setTimeout(function () {
    var tbl = document.getElementById("table");
    var total_record = tbl.rows.length;
    var NoOFRow = (total_record - 3);
    var count = 0;
    for (var i = 1; i <= (NoOFRow); i++) {
        var ckh = document.getElementById('chk#' + i).checked ? "Y" : "N";
        if (ckh === "Y") {
            count++;
        }
    }//end for
    if (NoOFRow === count) {
        document.getElementById("chkAll").checked = true;
    } else {
        document.getElementById("chkAll").checked = false;
    }
//                                            openActionDiv();
//    }, 1000);
}//end function

function validateChallanAllocationFilter() {

    var allocationStatus = $("#allocationStatus").val();
    var tdsSection = $("#tdsSection").val();
    var fromDate = $("#fromDate").val();
    var toDate = $("#toDate").val();
    var operator = $("#operator").val();
    var tdsFromAmount = $("#tdsFromAmount").val();
    var tdsToAmount = $("#tdsToAmount").val();
    var deducteeName = $("#deducteeName").val();
    var category = $("#category").val();
    var deducteeLevel = $("#deducteeLevel").val();
    var panno = $("#panno").val();

    if (lhsIsNull(panno) && lhsIsNull(allocationStatus) && lhsIsNull(tdsSection) && lhsIsNull(operator) && lhsIsNull(deducteeName) && lhsIsNull(tdsFromAmount) && lhsIsNull(tdsToAmount)
            && lhsIsNull(category) && lhsIsNull(deducteeLevel) && lhsIsNull(fromDate) && lhsIsNull(toDate)) {
        openErrorModal("Please select any filter to see the records !");
    } else {
        showRecordsPaginator();
    }
}//end function

function setCentralizedFlag(button) {

    setDefaultBtnProperties(button);

    $("#refreshAllocationBtn").attr("disabled", "true");
    $("#verifyChallanBtn").attr("disabled", "true");
    $("#fromAllocatedChallan").val("true");

//    setTimeout(function () {
    showRecordsPaginator();
//    }, 500);
}

function openChallanCalendar(id, btnid) {
    var flag = true;
    var todayDate = new Date();
    var dd = todayDate.getDate();
    var mm = todayDate.getMonth() + 1;
    var yyyy = todayDate.getFullYear();
    if (dd < 10)
    {
        dd = '0' + dd;
    }

    if (mm < 10)
    {
        mm = '0' + mm;
    }
    todayDate = yyyy + '-' + mm + '-' + dd;
    var currentDate = new Date(todayDate);
    var myCalendar;
    myCalendar = new dhtmlXCalendarObject({input: id, button: btnid});
    myCalendar.setDateFormat("%d-%m-%Y");
    myCalendar.hideTime();
    myCalendar.setDate(currentDate);
    myCalendar.setSensitiveRange(null, currentDate);

    if (window.dhx4.isIE11) {
        let settings = {
            date_field_id: id,
            calendar: myCalendar,
            left: '721px',
            right: '216px'
        };

        openGlobalCalendarForIE(settings);
    } else {
        addError("Please Select Proper Date!");
    }
    return flag;
}

function checkAndValidateDate() {
    var fromDate = $("#from_date").val();
    var toDate = $("#to_date").val();
    if (!lhsIsNull(fromDate) && !lhsIsNull(toDate)) {
        if (fromDate > toDate) {
            openErrorModal("Please provide valid date");
            $("#to_date").val("");
            $("#from_date").val("");
        }
    }

}
function addDefaultNilReturn(id) {
   var actionname= document.getElementById("action").value;
    checkprevi(id);
    if (id !== null && id !== "" && id !== "null") {
        if (id === 'nill_challan_flag') {
            if(actionname === 'add'){
            $("#bsr_code").val("1111111");
            $("#tds_challan_no").val("00001");
            $("#tds_challan_tds_amt").val("100");
            let endDate = $("#yearEndDate").val();
            $("#l_challan_date").val(endDate);
            $("#tds_challan_int_amt").val("0");
            $("#tds_challan_other_amt").val("0");
        }
            document.getElementById("book_entry_flag").checked=false;
            document.getElementById("challan_entry_flag").checked =false;

        } else if (id === 'book_entry_flag') {
            if(actionname === 'add'){
            $('#bsr_code,#tds_challan_no,#tds_challan_tds_amt,#l_challan_date,#tds_challan_int_amt,#tds_challan_other_amt').val('');
        }
             document.getElementById("nill_challan_flag").checked=false;
             document.getElementById("challan_entry_flag").checked =false;
        } else if (id === 'challan_entry_flag') {
            if(actionname === 'add'){
           $('#bsr_code,#tds_challan_no,#tds_challan_tds_amt,#l_challan_date,#tds_challan_int_amt,#tds_challan_other_amt').val('');
           }
             document.getElementById("book_entry_flag").checked=false;
             document.getElementById("nill_challan_flag").checked =false;
        }
        
        
       
       
    }
  
}

function onloadCheckRadioBtn() {
 
    
  //  var clientpanno=document.getElementById("clientpanno").value; 
    var deductee_cat=document.getElementById("client_catg_code").value; 
 try{

 if(!isNull(deductee_cat)){
     
     if(deductee_cat ==='A'){
          document.getElementById('book_entry_flag').checked = true;  
          document.getElementById("tds_challan_minor_head").value = "";
          document.getElementById("tds_challan_minor_head").disabled = true;
          document.getElementById("minorheadspanid").style.visibility = "hidden";
         
     }else{
        document.getElementById('challan_entry_flag').checked = true; 
     }
     
 }else{
     
       document.getElementById('challan_entry_flag').checked = true;
 }
 
    }catch(err){
        
    }
   
}

function radiobuttonchangeornot(id) {
    var cp = document.getElementById("globalcontextpath").value;
    var actionname= document.getElementById("action").value;
    document.getElementById("dialog-confirm_for_radio").style.display = "block";
    $(function () {
        $("#dialog-confirm_for_radio").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                "Yes": function () {
                        if (id === 'nill_challan_flag') {
                           document.getElementById('book_entry_flag').checked = false;
                           document.getElementById('challan_entry_flag').checked = false;
                           document.getElementById("tds_challan_minor_head").disabled = false;
                           document.getElementById("tds_challan_minor_head").value = "200";
                           document.getElementById("minorheadspanid").style.visibility = "visible";
                       }
                       if (id === 'book_entry_flag') {
                           document.getElementById("bsr_code").disabled = false;
                           document.getElementById('nill_challan_flag').checked = false;
                           document.getElementById('challan_entry_flag').checked = false;
                           document.getElementById("tds_challan_minor_head").value = "";
                           document.getElementById("tds_challan_minor_head").disabled = true;
                           document.getElementById("minorheadspanid").style.visibility = "hidden";
                           
                       }
                       if (id === 'challan_entry_flag') {
                           document.getElementById('book_entry_flag').checked = false;
                           document.getElementById('nill_challan_flag').checked = false;
                           document.getElementById("tds_challan_minor_head").disabled = false;
                           document.getElementById("tds_challan_minor_head").value = "200";
                           document.getElementById("minorheadspanid").style.visibility = "visible"; 
                       }
                        
                       $(this).dialog("close");
                  
                },
                No: function () {
                    
                        document.getElementById(globalradiobuttoId).checked = true;

                       if (globalradiobuttoId === 'nill_challan_flag') {
                            if(actionname === 'add'){
                       $("#bsr_code").val("1111111");
                       $("#tds_challan_no").val("00001");
                       $("#tds_challan_tds_amt").val("100");
                       let endDate = $("#yearEndDate").val();
                       $("#l_challan_date").val(endDate);
                       $("#tds_challan_int_amt").val("0");
                       $("#tds_challan_other_amt").val("0");
                            }
                       document.getElementById('book_entry_flag').checked = false;
                       document.getElementById('challan_entry_flag').checked = false;


                      }
                        if (globalradiobuttoId === 'book_entry_flag') {
                             if(actionname === 'add'){
                             $('#bsr_code,#tds_challan_no,#tds_challan_tds_amt,#l_challan_date,#tds_challan_int_amt,#tds_challan_other_amt').val('');
                             }
                            document.getElementById('nill_challan_flag').checked = false;
                            document.getElementById('challan_entry_flag').checked = false;
                            
                        }
                        if (globalradiobuttoId === 'challan_entry_flag') {
                             if(actionname === 'add'){
                             $('#bsr_code,#tds_challan_no,#tds_challan_tds_amt,#l_challan_date,#tds_challan_int_amt,#tds_challan_other_amt').val('');
                         }
                            document.getElementById('book_entry_flag').checked = false;
                            document.getElementById('nill_challan_flag').checked = false;
                        }
                        
                                    $(this).dialog("close");
                }
            }
        });
    });
//    }
}//end function


function unallocateAllocated(){
    
    
    document.getElementById("confirm_for_unallocate_before_delete").style.display = "block";
    $(function () {
        $("#confirm_for_unallocate_before_delete").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                "OK": function () {
             
                    $(this).dialog("close");
                  
                }
                
            }
        });
    });
//    }
}


function  checkprevi(id){
    
  
      
      if (id === 'nill_challan_flag') {
         var one =   document.getElementById('book_entry_flag').checked ;
         var two =  document.getElementById('challan_entry_flag').checked;
           if(one){
            globalradiobuttoId='book_entry_flag';   
           }
           if(two){
            globalradiobuttoId ='challan_entry_flag';   
           }
        }
        if (id === 'book_entry_flag') {
           var one =  document.getElementById('nill_challan_flag').checked ;
            var two = document.getElementById('challan_entry_flag').checked ;
             if(one){
            globalradiobuttoId ='nill_challan_flag';  
           }
           if(two){
             globalradiobuttoId ='challan_entry_flag' 
           }
        }
        if (id === 'challan_entry_flag') {
            var one = document.getElementById('book_entry_flag').checked;
            var two = document.getElementById('nill_challan_flag').checked ;
             if(one){
             globalradiobuttoId  ='book_entry_flag';
           }
           if(two){
               globalradiobuttoId ='nill_challan_flag';
           }
        }
        
        
}

function isNulldecimal(value) {
    
    if (value !== "" && value !== "null" && value !== null)
        return false;
    else
        return true;
}//end function


function CheckDecimal(inputtxt) 
{ 
  var re = /^-?[0-9]+$/;
if(re.test(inputtxt)) {

return false;
}
else
{ 

return true;
}
} 