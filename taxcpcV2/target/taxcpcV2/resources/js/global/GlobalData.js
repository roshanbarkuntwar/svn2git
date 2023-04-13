/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function callProcessIndicator() {
    alert();
    $("#rotator").rotator({
        starting: 0,
        ending: 98,
        percentage: true,
        color: '#12b321',
        lineWidth: 7,
        timer: 75,
        radius: 40,
        fontStyle: 'Calibri',
        fontSize: '20pt',
        fontColor: 'darkblue',
        backgroundColor: 'lightgray',
        callback: function() {
        }
    });
}//end function
