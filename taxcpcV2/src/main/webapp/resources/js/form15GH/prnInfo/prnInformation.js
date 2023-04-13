/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    try {
        defaultValues();
    } catch (err) {

    }
    try {
        getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);
//        document.getElementById("paginationBlock").style.display = "none";
    } catch (e) {
    }
//    onloadMsg();
});