/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function show_progress_bar() {
    document.getElementById('prgogress_bar').style.visibility = 'visible';
    proccessingWindow();
}//end function

// function to block window until we get ajax response from server
//START
function proccessingWindow() {
    $("#serverSideFrame").css({"width": "100%"});
    $("#serverSideFrame").css({"height": "100%"});
    $("#serverSideFrame").css({"opacity": "0.4"}).fadeIn("slow");
}// end method


function closeingProccessingWindow() {
    $("#serverSideFrame").fadeOut("slow");
}
// END

function generateXml15GH() {
    var selectedValue = document.getElementById("selectedValue").value;
    var selectedLevel = document.getElementById("loginLevel").value;
    var cp = document.getElementById("globalcontextpath").value;
    document.getElementById("browse_process_indicator_bulk15gh").style.visibility = 'visible';
    window.location.href = cp + "/generateXml15GHAction?action=generate&genType=" + encodeURIComponent(selectedValue) + "&cgl=" + encodeURIComponent(selectedValue) + "&selectedLevel=" + encodeURIComponent(selectedLevel);
    // proccessingWindow();
}//end function

function open_modal_existing_pending_error_15GH(error_bflag) {
    document.getElementById("browse_process_indicator_css").style.visibility = 'visible';
    var cp = document.getElementById("globalcontextpath").value;
//    document.getElementById("existingPendingErrorIframe").src = cp + '/bulk15GHXmlModal?action=existingPendingError&error_bflag=' + error_bflag;
    var url = cp + '/generateXmlModal15gh?action=existingPendingError&error_bflag=' + error_bflag;
    //alert(url);
    $.ajax({
        url: url,
        type: 'POST',
        success: function (data) {
//            alert(data);
            document.getElementById("table_data_in_modal").innerHTML = data;
            document.getElementById("browse_process_indicator_css").style.visibility = 'hidden';
        }
    });
    $('#existingPendingErrorModal').modal('show', true);
}//end function

function open_modal_for_default_value() {
    document.getElementById("modal_heading").innerHTML = "Default 15GH Bflag Value";
    document.getElementById("browse_process_indicator_css").style.visibility = 'visible';
    var cp = document.getElementById("globalcontextpath").value;
    document.getElementById("modal_dialog").style.top = "50px";
//    document.getElementById("existingPendingErrorIframe").src = cp + '/bulk15GHXmlModal?action=defaultValue';
    var url = cp + '/generateXmlModal15gh?action=defaultValue';
    $.ajax({
        url: url,
        type: 'POST',
        success: function (data) {
//            alert(data);
            document.getElementById("table_data_in_modal").innerHTML = "";
            document.getElementById("table_data_in_modal").innerHTML = data;
            document.getElementById("browse_process_indicator_css").style.visibility = 'hidden';
        }
    });
    $('#existingPendingErrorModal').modal('show', true);
}//end function

function bulk15ghVerifiedFlag() {
    try {
        var vflag_value = document.getElementById("vflag_value").value;
        if (vflag_value === '1') {
            document.getElementById("vflag_chkbx").checked = true;
        }
    } catch (err) {
    }
    try {
        checkbulkVerified_DeducteeFlag();
    } catch (err) {
    }
}//end function

function checkbulkVerified_DeducteeFlag() {
    var unverify_flag = document.getElementById("h_unverifiedDdeducteeCount").value;
    if (unverify_flag === "" || unverify_flag === "null" || unverify_flag === null) {
        unverify_flag = "0";
    }
    unverify_flag = parseFloat(unverify_flag);
    if (unverify_flag > 1) {
        document.getElementById("vflag_chkbx").setAttribute("disabled", "true");
    }
}//end function

function openBulkDeducteeFlagRecord(id) {
    var cp = document.getElementById("contextPath").value;
    $('#openBulkDeducteeRecordModal').modal('show', true);

    var xmlhttp;
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    } else {
        // code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            var q = xmlhttp.responseText;
            q = q.trim();
            //alert(q);
            if (q !== "" && q !== "null" && q !== null) {
                document.getElementById("verifyDeducteeDtlIframe").innerHTML = q;
                try {
                    window.parent.document.getElementById("bflagDtlDivID").style.height = "260px";
                } catch (err) {
                }
            }
        }
    };
    var url = cp + '/getCountForButton15gh?action=getVerifyDeducteeDetail&verificationType=' + encodeURIComponent(id);
    xmlhttp.open("GET", url);
    xmlhttp.send(null);
}//end function

function getVFCheckboxValue(id) {
    var ckbx = document.getElementById(id).checked;
    var result = confirm("Are You Sure To Verified Deductee");
    if (result) {
        if (ckbx) {
            document.getElementById("vflag_value").value = "1";
            callUpdateFlagFunction('1');
        } else {
            document.getElementById("vflag_value").value = "0";
            callUpdateFlagFunction('0');
        }
    } else {
        if (ckbx) {
            document.getElementById(id).checked = false;
            document.getElementById("vflag_value").value = "0";
        } else {
            document.getElementById(id).checked = true;
            document.getElementById("vflag_value").value = "1";
        }
    }
}//end function

function callUpdateFlagFunction(vflag_value) {
    var cp = document.getElementById("contextPath").value;

    var xmlhttp;
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    } else {
        // code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            var q = xmlhttp.responseText;
            q = q.trim();
            //alert(q);
            if (q !== "" && q !== "null" && q !== null) {
                if (q === 'T') {
                    // alert("start");
                    window.location.reload();
                } else {
                    alert("some error occured");
                }
            } else {
                alert("some error occured");
            }
        }
    };
    var url = cp + "/getCountForButton15gh?action=getUpdateClientVerifyFlag";
    url += "&vflag_value=" + encodeURIComponent(vflag_value);
    xmlhttp.open("GET", url);
    xmlhttp.send(null);
}//end function

function open_modal_for_check_error() {
    var xmlType = "";
    if (document.getElementById("new").checked) {
        xmlType = document.getElementById("new").value;
    } else if (document.getElementById("old").checked) {
        xmlType = document.getElementById("old").value;
    }
    document.getElementById("modal_heading").innerHTML = "Deductee BFlag Bulk Xml";
    document.getElementById("browse_process_indicator_css").style.visibility = 'visible';
    var cp = document.getElementById("globalcontextpath").value;
    document.getElementById("modal_dialog").style.top = "50px";
//    document.getElementById("existingPendingErrorIframe").src = cp + '/bulk15GHXmlModal?action=defaultValue';
    var url = cp + '/generateXmlModal15gh?action=checkError&xmlType=' + xmlType;
//    alert(url)
    $.ajax({
        url: url,
        type: 'POST',
        success: function (data) {
//            alert(data);
            document.getElementById("table_data_in_modal").innerHTML = "";
            document.getElementById("table_data_in_modal").innerHTML = data;
            document.getElementById("browse_process_indicator_css").style.visibility = 'hidden';
        }
    });
    $('#existingPendingErrorModal').modal('show', true);
}//end function


function downloadBranchRecord() {
    try {
        var action = "downloadBranchesRecordActionXML15gh";
        document.getElementById("DownloadClientDetailForm").setAttribute("action", action);
        document.getElementById("DownloadClientDetailForm").submit();
    } catch (err) {

    }
}//end function

function getCheckLoginType() {
    var type = document.getElementById("selectedValue").value;
    //alert(type);
    if (type === 'L') {
        document.getElementById("BranchLevel").checked = false;
        document.getElementById("BulkLevel").checked = true;
    } else {
        document.getElementById("BranchLevel").checked = true;
        document.getElementById("BulkLevel").checked = false;
    }
}//end function
