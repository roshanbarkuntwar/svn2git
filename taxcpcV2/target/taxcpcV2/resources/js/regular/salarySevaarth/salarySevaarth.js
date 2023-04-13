/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function validateFilter() {
    var DDO_NO = $("#DDO_NO").val();
    var BILL_ID = $("#BILL_ID").val();
    var SEVARTH_ID = $("#SEVARTH_ID").val();
     var DEDUCTEE_PANNO = $("#DEDUCTEE_PANNO").val();
    var flag = true;

    if (lhsIsNull(DDO_NO) && lhsIsNull(BILL_ID) && lhsIsNull(SEVARTH_ID) && lhsIsNull(DEDUCTEE_PANNO) && flag) {
        openErrorModal("Please select any filter to see the records !");
    } else {
        showRecordsPaginator();
    }
}
