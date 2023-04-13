/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global globalUpdateMessage */

function validateUserProfileForm() {
    var status;
    
    var loginId = document.getElementById("loginId").value;
    var userName = document.getElementById("userName").value;
    var shortName = document.getElementById("shortName").value;
    var newPassword = document.getElementById("newPassword").value;
    var confirmNewPassword = document.getElementById("confirmNewPassword").value;
    
    if(lhsIsNull(loginId)) {
        addActionError("message","Login Id cannot be blank");
        status = false;
    } else if(lhsIsNull(userName)) {
        addActionError("message","Name cannot be blank");
        status = false;
    } else if(lhsIsNull(shortName)) {
        addActionError("message","Short Name cannot be blank");
        status = false;
    } else
    {
        if (newPassword !== confirmNewPassword) {
            addActionError("message", "New Password and Confirm New Password must be same");
            status = false;
            if (lhsIsNull(confirmNewPassword)) {
                addActionError("message", "Please Provide Confirm New Password also! ");
                status = false;
            }
        } else {
            status = true;
        }
    }
    
    return status;
}//End function

function updateUserProfile() {
    if(validateUserProfileForm()) {
        ajaxSubmitPostData("/userProfileCRUDAction?action=update", "userProfileForm", updateUserProfileResponse);
    }
}//End function

function updateUserProfileResponse(response) {
    if(response === "SUCCESS") {
        openSuccessModal(globalUpdateMessage ,"exitUser()");
    } else {
        openErrorModal("Some Error Occured..!","");
    }
}//End function
function editAssessment(){
    $("#accYr").prop("disabled",false);
    $("#quarterr").prop("disabled",false);
    $("#tdsTypee").prop("disabled",false);
}