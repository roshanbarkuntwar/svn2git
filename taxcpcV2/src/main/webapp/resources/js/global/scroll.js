/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function global_HorizontalTableScroller(id, target) {
    var target = $("#" + target);
    $("#" + id).scroll(function () {
        target.prop("scrollLeft", this.scrollLeft);


    });
}