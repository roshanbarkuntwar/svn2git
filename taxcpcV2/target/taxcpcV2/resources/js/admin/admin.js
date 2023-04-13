/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var lebel_value = '';
var index = '';
var row_id_seq = '';
function updateParameterFlag(index, parameter_name, id, parameter_type) {

    if (parameter_type === 'java') {
        lebel_value = document.getElementById('javaswitchFlag~' + index).innerHTML;
        row_id_seq = document.getElementById('javaFlagrowid~' + index).value;
    } else if (parameter_type === 'oracle') {
        lebel_value = document.getElementById('oracleswitchFlag~' + index).innerHTML;
        row_id_seq = document.getElementById('oracleFlagrowid~' + index).value;
    }
    if (lebel_value === 'T') {
        lebel_value = 'F';
    } else if (lebel_value === 'F') {
        lebel_value = 'T';
    }
    document.getElementById("dialog-confirm_for_updation").style.display = "block";
    var cp = document.getElementById("globalcontextpath").value;
    var url = cp + '/parameterSettingAction?action=update';
    $(function () {
        $("#dialog-confirm_for_updation").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                Yes: function () {
                    $(this).dialog("close");
                    $.ajax({
                        url: url,
                        type: 'POST',
                        data: {
                            parameter_name: parameter_name,
                            parameter_value: lebel_value,
                            rowid: row_id_seq

                        },
                        beforeSend: function (xhr) {
                            showProcessIndicator();
                        },
                        success: function (response, textStatus, jqXHR) {
                            if (response === 'updated') {
                                if (parameter_type === 'java') {
                                    document.getElementById('javaswitchFlag~' + index).innerHTML = lebel_value;
                                } else if (parameter_type === 'oracle') {
                                    document.getElementById('oracleswitchFlag~' + index).innerHTML = lebel_value;
                                }

                                openSuccessModal("Updated Successfully");
                            } else if (response === 'error') {
                                openErrorModal("Not Updated");
                            } else {
                                openErrorModal("Not Updated");
                            }
                        },
                        complete: function (jqXHR, textStatus) {
                            hideProcessIndicator();
                        }
                    });

                },
                No: function () {
                    $(this).dialog("close");
                    var isChecked = document.getElementById(id).checked;
                    if (isChecked) {
                        document.getElementById(id).checked = false;
                    } else {
                        document.getElementById(id).checked = true;
                    }
                }
            }
        });
    });

}


function tabchange(tabname, ) {
    if (tabname === 'java') {
        document.getElementById('java_parameter_div').style.display = 'block';
        document.getElementById('oracle_parameter_div').style.display = 'none';
        $("#oracle_list").removeClass("active");
        $("#java_list").addClass("active");
    } else if (tabname === 'oracle') {
        document.getElementById('java_parameter_div').style.display = 'none';
        document.getElementById('oracle_parameter_div').style.display = 'block';
        $("#java_list").removeClass("active");
        $("#oracle_list").addClass("active");
    }
}


function openRemarkModel(remark) {
    document.getElementById('remark_data').innerHTML = remark;
    $("#remark-modal").modal("show", true);
}


function updateDashboardFlag(index, menu_name, module_type, id, lebel_valueee) {
    var lebel_value = document.getElementById('dashboardswitchFlag~' + index).innerHTML;

    if (lebel_value === 'A') {
        lebel_value = 'D';
    } else if (lebel_value === 'D') {
        lebel_value = 'A';
    }
    document.getElementById("dialog-confirm_for_updation").style.display = "block";
    var cp = document.getElementById("globalcontextpath").value;
    var url = cp + '/dashboardSetting?action=update';
    $(function () {
        $("#dialog-confirm_for_updation").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                Yes: function () {
                    $(this).dialog("close");
                    $.ajax({
                        url: url,
                        type: 'POST',
                        data: {
                            menu_name: menu_name,
                            module_type: module_type,
                            active_flag: lebel_value
                        },
                        beforeSend: function (xhr) {
                            showProcessIndicator();
                        },
                        success: function (response, textStatus, jqXHR) {
                            if (response === 'updated') {
                                document.getElementById('dashboardswitchFlag~' + index).innerHTML = lebel_value;
                                openSuccessModal("Updated Successfully");
                            } else if (response === 'error') {
                                openErrorModal("Not Updated");
                            } else {
                                openErrorModal("Not Updated");
                            }
                        },
                        complete: function (jqXHR, textStatus) {
                            hideProcessIndicator();
                        }
                    });

                },
                No: function () {
                    $(this).dialog("close");
                    var isChecked = document.getElementById(id).checked;
                    if (isChecked) {
                        document.getElementById(id).checked = false;
                    } else {
                        document.getElementById(id).checked = true;
                    }
                }
            }
        });
    });
}



function updateReportFlag(index, seq_id, id) {
    var lebel_value = document.getElementById('reportswitchFlag~' + index).innerHTML;

    if (lebel_value === 'T') {
        lebel_value = 'F';
    } else if (lebel_value === 'F') {
        lebel_value = 'T';
    }
    document.getElementById("dialog-confirm_for_updation").style.display = "block";
    var cp = document.getElementById("globalcontextpath").value;
    var url = cp + '/misReportSettingAction?action=update';
    $(function () {
        $("#dialog-confirm_for_updation").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                Yes: function () {
                    $(this).dialog("close");
                    $.ajax({
                        url: url,
                        type: 'POST',
                        data: {
                            report_seq_id: seq_id,
                            report_status: lebel_value
                        },
                        beforeSend: function (xhr) {
                            showProcessIndicator();
                        },
                        success: function (response, textStatus, jqXHR) {
                            if (response === 'updated') {
                                document.getElementById('reportswitchFlag~' + index).innerHTML = lebel_value;
                                openSuccessModal("Updated Successfully");
                            } else if (response === 'error') {
                                openErrorModal("Not Updated");
                            } else {
                                openErrorModal("Not Updated");
                            }
                        },
                        complete: function (jqXHR, textStatus) {
                            hideProcessIndicator();
                        }
                    });

                },
                No: function () {
                    $(this).dialog("close");
                    var isChecked = document.getElementById(id).checked;
                    if (isChecked) {
                        document.getElementById(id).checked = false;
                    } else {
                        document.getElementById(id).checked = true;
                    }
                }
            }
        });
    });
}

function closeremarkmodal() {
    $("#remark-modal").modal("hide", true);
}

function logindetailsReadioBtn(id) {
    if (id === 'clientRadioBtn') {
        document.getElementById('clientRadioBtn').checked = true;
        document.getElementById('userRadioBtn').checked = false;
        document.getElementById('loginDetailsType').value = "client";
        document.getElementById('clientfiltdiv').style.display = "flex";
        document.getElementById('userfiltdiv').style.display = "none";
        showRecordsPaginator();
    }


    if (id === 'userRadioBtn') {
        document.getElementById('userRadioBtn').checked = true;
        document.getElementById('clientRadioBtn').checked = false;
        document.getElementById('loginDetailsType').value = "user";
        document.getElementById('userfiltdiv').style.display = "flex";
        document.getElementById('clientfiltdiv').style.display = "none";

        showRecordsPaginator();
    }
}



function viewUserDetails(userCode) {

    window.location.href = "loginDetails?loginDetailsType=userView&userCode=" + userCode;


}

function viewClientDetails(clientCode) {

    window.location.href = "loginDetails?loginDetailsType=clientView&clientCode=" + clientCode;

}



function loginDetailsFilter() {
    var loginDetailsType = document.getElementById('loginDetailsType').value;
    if (!lhsIsNull(loginDetailsType)) {
        if (loginDetailsType === 'client') {
            document.getElementById('userRadioBtn').checked = false;
            document.getElementById('clientRadioBtn').checked = true;
            document.getElementById('clientfiltdiv').style.display = "block";
            document.getElementById('userfiltdiv').style.display = "none";
        } else if (loginDetailsType === 'user') {
            document.getElementById('userRadioBtn').checked = true;
            document.getElementById('clientRadioBtn').checked = false;
            document.getElementById('clientfiltdiv').style.display = "none";
            document.getElementById('userfiltdiv').style.display = "flex";
        }

    }
    showRecordsPaginator();
}


function loginDashbordFilter() {

    showRecordsPaginator();
}


function openBrowseCalendar(id, btnid, left, right) {
    var myCalendar;
    myCalendar = new dhtmlXCalendarObject({input: id, button: btnid});
    myCalendar.setDateFormat("%d-%m-%Y");
    myCalendar.hideTime();

    if (window.dhx4.isIE11) {
        let settings = {
            date_field_id: id,
            calendar: myCalendar,
            left: left,
            right: right
        };
        openGlobalCalendarForIE(settings);
    }
}


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
