/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function validateEntityForm() {
    var entity = document.getElementById("entityName").value;
    var clientName = document.getElementById("clientName").value;
    var clientLoginId = document.getElementById("clientLoginId").value;
    var clientLoginPassword = document.getElementById("clientLoginPassword").value;
    var clientShortName = document.getElementById("clientShortName").value;
    var clientBankBranchCode = document.getElementById("clientBankBranchCode").value;

    if (lhsIsNull(entity)) {
        openErrorModal("Please enter Entity Name first.", "");
        return false;
    }
    if (lhsIsNull(clientName)) {
        openErrorModal("Please enter Client Name first.", "");
        return false;
    }
    if (lhsIsNull(clientLoginId)) {
        openErrorModal("Please enter Login Id first.", "");
        return false;
    }
    if (lhsIsNull(clientLoginPassword)) {
        openErrorModal("Please enter Password first.", "");
        return false;
    }
    if (lhsIsNull(clientShortName)) {
        openErrorModal("Please enter Short Name first.", "");
        return false;
    }
    if (lhsIsNull(clientBankBranchCode)) {
        openErrorModal("Please enter Bank Branch Code first.", "");
        return false;
    }
    showProcessIndicator();
    return true;
}//end function


