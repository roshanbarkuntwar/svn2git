

function validateFilter() {
    var panno = $("#panno").val();
    var deducteeRefNo = $("#deducteeRefNo").val();
    var accountNo = $("#accountNo").val();
    var verifiedFlag = $("#verifiedFlag").val();
    var deducteeName = $("#deducteeName").val();
    if (lhsIsNull(panno) && lhsIsNull(deducteeRefNo) && lhsIsNull(accountNo) && lhsIsNull(verifiedFlag) && lhsIsNull(deducteeName)) {
        openErrorModal("Please select any filter to see the records !");
    } else {
        showRecordsPaginator();
    }
}

function doGenerateAndDownloadXml(xmlButton) {
    xmlButton.disabled = true;
    callUrl("/panVerificationOffline?action=xmlfile&flag=true");
}//end function

function generateAndDownload() {
    $('#generatePan').modal('hide');
    if ($('#downloadText')[0].checked) {
        var unverifiedPanCount = $("#unverifiedPanCount").text();
        ajaxPostUrl("/panVerificationForText?action=textfile&flag=true&unverifiedPanCount=" + unverifiedPanCount, getResponseOfTextFile);
    } else if ($('#downloadXML')[0].checked) {
        ajaxPostUrl("/panVerificationForXML?action=xmlfile&flag=true", getResponseOfXMLFile);
    } else {
        openErrorModal("Please Select For Generate");
    }

}

function getResponseOfTextFile(response) {
    console.log('response' + response);
//    hideProcessIndicator();
    if (!lhsIsNull(response)) {
        if (response.includes('success')) {
            let tokenNo = response.split("#")[1];
            let unverifiedPanCount = response.split("#")[2];
            try {
                callDedicatedProcedure({
                    callingProcName: "ProcPanUnverifiedTxt",
                    tokenNo: tokenNo,
                    unverifiedPanCount: unverifiedPanCount
                });
            } catch (e) {
                console.log('%cprocedure calling ajax error', 'color: red;');
                console.log(e);
            }


            document.getElementById("dialog-skip-upload-success-p").innerHTML = "Go to Download Section Your  Generated Token No. is : " + tokenNo;
            document.getElementById("dialog-skip-upload-success").style.display = "block";
            $(function () {
                $("#dialog-skip-upload-success").dialog({
                    modal: true,
                    buttons: {
                        Ok: function () {
                            $(this).dialog("close");
                            window.location = "panVerificationOffline";
                        }
                    }
                });
            });
        } else {
            openErrorModal('Could not Provide Download, Some Error Occured.');
        }
    }

}

function getResponseOfXMLFile(response) {
    console.log('response' + response);
//    hideProcessIndicator();
    if (!lhsIsNull(response)) {
        if (response.includes('success')) {
            document.getElementById("dialog-skip-upload-success-p").innerHTML = "Go to Download Section Your  Generated Token No. is : " + response.split("#")[1];
            document.getElementById("dialog-skip-upload-success").style.display = "block";
            $(function () {
                $("#dialog-skip-upload-success").dialog({
                    modal: true,
                    buttons: {
                        Ok: function () {
                            $(this).dialog("close");
                            window.location = "panVerificationOffline";
                        }
                    }
                });
            });
        } else {
            openErrorModal('Could not Provide Download, Some Error Occured.');
        }
    }

}

let refreshUnverifiedCount = panCount => {
    if (!lhsIsNull(panCount)) {
        ajaxPostUrl("/panVerificationForText?action=unverifiedPanCountRefresh", getResponseOfUnverifiedPanCount);
    }
};
let getResponseOfUnverifiedPanCount = response => {
    if (!lhsIsNull(response)) {
        if (response.includes('success')) {
            document.getElementById("dialog-skip-upload-success-p").innerHTML = "Unverified Pan Count Sucessfully Refreshed";
            document.getElementById("dialog-skip-upload-success").style.display = "block";
            $(function () {
                $("#dialog-skip-upload-success").dialog({
                    modal: true,
                    buttons: {
                        Ok: function () {
                            $(this).dialog("close");
                            window.location = "panVerificationOffline";
                        }
                    }
                });
            });
        } else {
            openErrorModal('Could not refresh Unverified Pan Count');
        }
    }
}

function validatePAN(field) {
    let value = field.value;
    let Exp;
    if (!lhsIsNull(value)) {
        Exp = /^([a-zA-Z]){3}(a|A|b|B|c|C|f|F|g|G|h|H|j|J|k|K|l|L|p|P|t|T){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
        if (Exp.test(value)) {
        } else {
            openErrorModal("Invalid PAN");
            field.value = "";
            return false;
        }
    }
}//end function

let getPanDetails = () => {
    let panno = $("#panno").val();
    if (!lhsIsNull(panno)) {
        var cp = document.getElementById("globalcontextpath").value;
        let url = cp + "/deducteePanDetails?panno=" + panno;
        showBlackProcessIndicator();
        $.ajax({
            type: "POST",
            url: url,
            success: function (response) {
                hideBlackProcessIndicator();
                if (!lhsIsNull(response)) {
                    if (!lhsIsNull(response) && response.includes("success")) {
                        let panData = response.split("#")[2];
                        $("#checkPanStatus").val(lhsIsNull(response.split("#")[1]) ? "PAN Not Available" : response.split("#")[1]);
                        $.each(JSON.parse(panData), function (key, value) {
                            document.getElementById('panStatus').style.display = 'block';
                            $("#panNo").val(lhsIsNull(value.panno) ? "PAN Not Available" : value.panno);
                            $("#first_name").val(lhsIsNull(value.first_name) ? "First Name Not Available" : value.first_name);
                            $("#middle_name").val(lhsIsNull(value.middle_name) ? "Middle Name Not Available" : value.middle_name);
                            $("#last_name").val(lhsIsNull(value.last_name) ? "Last Name Not Available" : value.last_name);
                            $("#pan_allot_date").val(lhsIsNull(value.pan_allot_date) ? "PAN Allot Date Not Available" : value.pan_allot_date);
                            $("#pan_aadhar_status").val(lhsIsNull(value.pan_aadhar_status) ? "PAN Aadhar Status Not Available" : value.pan_aadhar_status);
                            $("#ab206_status").val(lhsIsNull(value.ab206_status) ? "ab206_status Not Available" : value.ab206_status);
                            $("#n194_status").val(lhsIsNull(value.n194_status) ? "n194_status Not Available" : value.n194_status);
                            $("#remark").val(lhsIsNull(value.remark) ? "Closed Date Not Available" : value.remark);
                            !lhsIsNull(value.verified_date) && response.split("#")[1] === 'Verified' ? $("#verifiedPan").css("display", "flex") : $("#verifiedPan").css("display", "none");
                            $("#verified_date").val(lhsIsNull(value.verified_date) ? "Verified Date Not Available" : value.verified_date);
                        }
                        );
                    } else if (!lhsIsNull(response) && response.includes("error")) {
                        openErrorModal(response.split("#")[1], "window.location.reload();");
                    }
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown);
            }
        });
    } else {
        openErrorModal("Please Enter PAN Number");
    }
}




 
              
  function PanIsBlankchedk(id,no){
    var cp = document.getElementById("globalcontextpath").value;
    var deductee_ref = document.getElementById("deductee_ref"+no).innerHTML;
    var deductee_name = document.getElementById("deductee_name"+no).innerHTML;
    var pan_no = document.getElementById(id).value;
    if (lhsIsNull(pan_no)) {
        openErrorModal("Please Enter PAN no");
    }else{
   document.getElementById("dialog-confirm_pan_update_process").style.display = "block";
    $(function () {
        $("#dialog-confirm_pan_update_process").dialog({
            resizable: false,
            font: 10,
            modal: true,
            buttons: {
                "Ok": function () {
                    $(this).dialog("close");
                    showProcessIndicator();
                        pan_no= pan_no.toUpperCase();
                        var callingurl = cp + "/deducteeDatagridAction?action=updatepannonew";
                        $.ajax({
                            url: callingurl,

                            data: {
                            pannonew: pan_no,
                            deductee_name: deductee_name,
                            deductee_ref: deductee_ref
                             },
                            success: function (result) {
                                if (result==='0') {
                                 openErrorModal("PAN not update");
                                } else {

                               // openSuccessModal("PAN updated <br> "+result+ " row updated");
                                openSuccessModal("Total ("+result+") PAN No. Updated  Successfully");
                                validateFilter();

                                }


                            },
                           error: function (jqXHR, textStatus, errorThrown) {
                                hideProcessIndicator();
                            },
                            complete: function (jqXHR, textStatus) {
                                hideProcessIndicator();
                            }
                        });

                },
                Cancel: function () {
                    $(this).dialog("close");
                }
            }
        });
    });
          
          
    
    
       
    }
}// end method
   
function updatepanNoNew(id){
var cp = document.getElementById("globalcontextpath").value;

  var pan_no =document.getElementById(id).value;
  
    var callingurl = cp + "/deducteeDatagridAction?action=updatepannonew";
//    var callingurl = cp + "/getClientCategoryList?action=select_client_catg_list" + "&deductor_type_code=" + encodeURIComponent(deductor_type)+ "&hiddenClientCatgCode=" + encodeURIComponent(h_client_catg_code);
    $.ajax({
        url: callingurl,
        newpan:pan_no,
        success: function (result) {
            if (result==='success') {
              alert("success");
            } else {
                alert("error");
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('inside error......');
        }
    });
    }     
    
    
function editPanForUpdate(i){
    
    var srno = i;
    var className = $("#d"+i).attr("class");
    if(className === 'collapse'){
    showProcessIndicator();
    var cp = document.getElementById("globalcontextpath").value;
    var deductee_ref = document.getElementById("deductee_ref"+i).innerHTML;
    var deductee_panno = document.getElementById("deductee_panno"+i).innerHTML;
    var callingurl = cp + "/deducteeDatagridAction?action=getupdatePanCount";
     $.ajax({
        url: callingurl,
        data: { deductee_ref: deductee_ref,
                 deductee_panno: deductee_panno
              },
         success: function (result) {
             var count =result;
              if(!lhsIsNull(count) && (count==='0')){
              document.getElementById("btn-"+srno).disabled=true;    
              }
             var message="Found ("+count+")Record For This Transaction";
             $("#updatepancount"+srno).text(message);
        },
       error: function (jqXHR, textStatus, errorThrown) {
            hideProcessIndicator();
        },
        complete: function (jqXHR, textStatus) {
           hideProcessIndicator();
        }
         
    });
    }
  }
  
//cssStyle="text-transform: uppercase;"

