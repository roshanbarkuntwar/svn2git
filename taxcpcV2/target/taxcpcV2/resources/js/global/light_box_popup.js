/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function open_single_selection_lov(lov_id, replacement_string, postbackcolstring) {
    try {
        document.getElementById("lov_loading").style.display = "block";
        document.getElementById("light_box_overlay").style.top = "0px";
        document.getElementById("light_box_overlay").style.right = "0px";
        document.getElementById("light_box_overlay").style.bottom = "0px";
        document.getElementById("light_box_overlay").style.left = "0px";
        document.getElementById("light_box_overlay").style.display = "block";

        var pg_width = 1024;
        var pg_height = 768;
        try {
            pg_width = document.getElementById("myid").offsetWidth;
//            pg_height = document.getElementById("home").offsetHeight;
        } catch (err) {
            pg_width = 300;
            console.log(err);
        }

        document.getElementById('light_box_content').style.transition = '0.6s ease 0s';
        var width = parseInt(pg_width) > 600 ? 600 : parseInt(pg_width) - 30;
        document.getElementById("light_box_content").style.width = width + "px";
        document.getElementById("light_box_content").style.bottom = "0px";
        document.getElementById("light_box_content").style.top = "0px";
        document.getElementById("light_box_content").style.display = "block";
        var cp = document.getElementById("globalcontextpath").value;
        document.getElementById("light_box_page_src").src = cp + "/open_lov_single_selection?lov_id=" + encodeURIComponent(lov_id) +
                "&lov_replacement=" + encodeURIComponent(replacement_string) + "&postbackcolstring=" + encodeURIComponent(postbackcolstring);
    } catch (error) {
        window.alert("All lOV parameters are not available to open LOV");
        close_light_box();
        console.log(error);
    }
}
function open_single_selection_lov_small(lov_id, replacement_string) {
    try {
        document.getElementById("lov_loading").style.display = "block";
        document.getElementById("light_box_overlay").style.left = "0px";
        document.getElementById("light_box_overlay").style.top = "0px";
        document.getElementById("light_box_overlay").style.right = "0px";
        document.getElementById("light_box_overlay").style.bottom = "0px";
        document.getElementById("light_box_overlay").style.width = "100%";
        document.getElementById("light_box_overlay").style.height = "100%";
        document.getElementById("light_box_overlay").style.display = "block";

        var pg_width = 1024;
        var pg_height = 768;
        try {
            pg_width = document.getElementById("main_pg_main_outer").offsetWidth;
            pg_height = document.getElementById("main_pg_main_outer").offsetHeight;
        } catch (err) {
            pg_width = 1024;
            pg_height = 768;
            console.log(err);
        }
        var left = (parseInt(pg_width) - 50) / 2;
        var top = (parseInt(pg_height) - 50) / 2;
        document.getElementById("light_box_content").style.left = left + "px";
        document.getElementById("light_box_content").style.top = top + "px";
        document.getElementById("light_box_content").style.width = "150px";
        document.getElementById("light_box_content").style.height = "100px";
        document.getElementById("light_box_content").style.display = "block";
        var cp = document.getElementById("globalcontextpath").value;
        document.getElementById("light_box_page_src").src = cp + "/open_lov_single_selection_small?lov_id=" + encodeURIComponent(lov_id) + "&lov_replacement=" + encodeURIComponent(replacement_string);
    } catch (error) {
        window.alert("All lOV parameters are not available to open LOV");
        close_light_box();
    }
}


function close_light_box() {
    document.getElementById("light_box_content").style.border = "none";
    document.getElementById("light_box_content").style.display = "none";
    document.getElementById("light_box_overlay").style.display = "none";
    document.getElementById("light_box_page_src").src = "";
    document.getElementById("light_box_page_src").style.display = "none";
}


function resize_container(h, w) {

    var pg_width = 1024;
    var pg_height = 700;
    try {
//        pg_width = document.getElementById("home").offsetWidth;
        pg_width = document.getElementById("myid").offsetWidth;
//        pg_height = document.getElementById("home").offsetHeight;
        pg_height = document.getElementById("myid").offsetHeight;
    } catch (error) {
        pg_width = 1024;
        pg_height = 700;
        console.log(error);
    }
    var width = parseInt(pg_width) > 600 ? 600 : parseInt(pg_width) - 30;
//    var height = parseInt(pg_height) > 700 ? parseInt(pg_height) : 700;
    document.getElementById("light_box_page_src").style.width = width + "px";
    document.getElementById("light_box_page_src").style.top = "0px";
//    document.getElementById("light_box_page_src").style.height = height + "px";
    document.getElementById("light_box_page_src").style.height = "100%";
    document.getElementById("light_box_page_src").style.display = "block";
    document.getElementById("lov_loading").style.display = "none";
//    document.getElementById("light_box_content").style.border = "10px solid #FFF";
}


function open_pop_up_light_box(url) {
    document.getElementById("lov_loading").style.display = "block";
    document.getElementById("light_box_overlay").style.top = "0px";
    document.getElementById("light_box_overlay").style.right = "0px";
    document.getElementById("light_box_overlay").style.bottom = "0px";
    document.getElementById("light_box_overlay").style.left = "0px";
    document.getElementById("light_box_overlay").style.display = "block";

    var pg_width = 1024;
    var pg_height = 768;
    try {
//        pg_width = document.getElementById("home").offsetWidth;
        pg_width = document.getElementById("myid").offsetWidth;
//            pg_height = document.getElementById("home").offsetHeight;
    } catch (err) {
        pg_width = 300;
        console.log(err);
    }

    document.getElementById('light_box_content').style.transition = '0.6s ease 0s';
    var width = parseInt(pg_width) > 600 ? 600 : parseInt(pg_width) - 30;
    document.getElementById("light_box_content").style.width = width + "px";
    document.getElementById("light_box_content").style.bottom = "0px";
    document.getElementById("light_box_content").style.top = "0px";
    document.getElementById("light_box_content").style.display = "block";
    var cp = document.getElementById("globalcontextpath").value;
    try {
        document.getElementById("light_box_page_src").src = url;
    } catch (err) {
        close_light_box();
        window.alert("Could not open URL");
    }
}//end function


function validate_from_LOV(elem_id, lov_id, replacement_string, postbackcolstring) {
    var elem_value = document.getElementById(elem_id).value;
    if (elem_id !== null && lov_id !== null && elem_value !== null && elem_value.length > 0) {
        var value_to_validate = document.getElementById(elem_id).value;
        try {
            var cp = document.getElementById("globalcontextpath").value;
            var xmlhttp;
            if (window.XMLHttpRequest) { //code for IE7+, Firefox, Chrome, Opera, Safari
                xmlhttp = new XMLHttpRequest();
            }
            else {
                //code for IE6, IE5
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
            xmlhttp.onreadystatechange = function() {
                if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                    var q = xmlhttp.responseText;
                    if (q !== null && q !== '' && q.length > 0)
                    {
                        try {
                            var p = q.trim();
                            if (p === "SE" && p.length === 2) {
                                window.alert("Your login session expired, please relogin");
                                window.parent.location.reload();
                                q = "";
                            }
                        } catch (error) {
                            q = xmlhttp.responseText;
                            console.log(error);
                        }
                        //processing data here to fill UI components
                        try {
                            if (q !== "") {
                                q = q.trim();
                                var all_key_value = q.split("#~l!h$s~#");
                                for (var i = 0; i < all_key_value.length; i++) {
                                    var key_value_str = all_key_value[i].trim();
                                    var key_value = key_value_str.split(":!:~:");
                                    document.getElementById(key_value[0].trim()).value = key_value[1].trim();
                                }
                            }
                        } catch (error) {
                            open_single_selection_lov(lov_id, replacement_string, postbackcolstring);
                            document.getElementById(elem_id).value = "";
                        }
                    }
                }// end if
            };
            var url = cp + "/validate_from_lov?lov_id=" + encodeURIComponent(lov_id) + "&elem_id=" + encodeURIComponent(elem_id) + "&elem_value=" + encodeURIComponent(value_to_validate) + "&lov_replacement=" + encodeURIComponent(replacement_string);
            url += "&postbackcolstring=" + encodeURIComponent(postbackcolstring);
            xmlhttp.open("GET", url, true);
            xmlhttp.send(null);
        } catch (error) {
            open_single_selection_lov(lov_id, replacement_string);
            console.log(error);
        }
    }
}

//document.onkeydown = function(evt) {
//    evt = evt || window.event;
//    if (evt.keyCode === 27) {
//        close_light_box();
//    }
//};




function open_pop_up_light_box_dashboard_calendat_event(url) {
    window.parent.document.getElementById("lov_loading").style.display = "block";
    window.parent.document.getElementById("light_box_overlay").style.left = "0px";
    window.parent.document.getElementById("light_box_overlay").style.top = "0px";
    window.parent.document.getElementById("light_box_overlay").style.right = "0px";
    window.parent.document.getElementById("light_box_overlay").style.bottom = "0px";
    window.parent.document.getElementById("light_box_overlay").style.width = "100%";
    window.parent.document.getElementById("light_box_overlay").style.height = "100%";
    window.parent.document.getElementById("light_box_overlay").style.display = "block";

    var pg_width = window.parent.document.getElementById("main_pg_main_outer").offsetWidth;
    var pg_height = window.parent.document.getElementById("main_pg_main_outer").offsetHeight;
    var left = (parseInt(pg_width) - 50) / 2;
    var top = (parseInt(pg_height) - 50) / 2;
    window.parent.document.getElementById("light_box_content").style.left = left + "px";
    window.parent.document.getElementById("light_box_content").style.top = top + "px";
    window.parent.document.getElementById("light_box_content").style.width = "150px";
    window.parent.document.getElementById("light_box_content").style.height = "100px";
    window.parent.document.getElementById("light_box_content").style.display = "block";
    var cp = document.getElementById("globalcontextpath").value;
    try {
        window.parent.document.getElementById("light_box_page_src").src = url;
    } catch (err) {
        close_light_box();
        window.alert("Could not open URL");
    }
}//end function


/**
 * customized method to operate 'ease in and out function' of bootstrap collapse
 * used in place of data-toggle="collapse" in filter and filter like operations
 */
$(document).ready(function() {
    $(".myCollapse").unbind().click(function(event) {
        var name = event.target.getAttribute('data-target');
        if ($(name).hasClass("in")) {
            $(name).removeClass("in");
            var originalText = $(this).text();
            if (originalText.trim() === 'Hide Filter') {
                $(this).text('Show Filter');
            }
        } else {
            $(name).addClass("in");
            var originalText = $(this).text();
            if (originalText.trim() === 'Show Filter') {
                $(this).text('Hide Filter');
            }
        }
    });

    /* Navbar hide and show click */

    $("#hide").click(function() {
        $("#navbar").slideUp(1000, function() {
            $("#show").show();
            $("#hide").hide();
        });
    });
    $("#show").click(function() {
        $("#navbar").slideDown(1000, function() {
            $("#show").hide();
            $("#hide").show();
        });
    });

});

function open_single_selection_lov_popupWindow(lov_id, replacement_string) {
    try {
        document.getElementById("lov_loading").style.display = "block";
        document.getElementById("light_box_overlay").style.left = "0px";
        document.getElementById("light_box_overlay").style.top = "0px";
        document.getElementById("light_box_overlay").style.right = "0px";
        document.getElementById("light_box_overlay").style.bottom = "0px";
        document.getElementById("light_box_overlay").style.width = "100%";
        document.getElementById("light_box_overlay").style.height = "100%";
        document.getElementById("light_box_overlay").style.display = "block";

        var pg_width = 1024;
        var pg_height = 768;
        try {
            pg_width = document.getElementById("main_pg_main_outer").offsetWidth;
            pg_height = document.getElementById("main_pg_main_outer").offsetHeight;
        } catch (err) {
            pg_width = 1024;
            pg_height = 768;
            console.log(err);
        }
        document.getElementById("light_box_content").style.width = "600px";
        document.getElementById("light_box_content").style.height = "520px";
        document.getElementById("light_box_content").style.display = "block";
        var cp = document.getElementById("globalcontextpath").value;
        document.getElementById("light_box_page_src").src = cp + "/open_single_selection_lov_popupWindow?lov_id=" + encodeURIComponent(lov_id) + "&lov_replacement=" + encodeURIComponent(replacement_string);
    } catch (error) {
        window.alert("All lOV parameters are not available to open LOV");
        close_light_box();
    }
}
// code to restrict back button
//(function (global) { 
//
//    if(typeof (global) === "undefined") {
//        throw new Error("window is undefined");
//    }
//
//    var _hash = "!";
//    var noBackPlease = function () {
//        global.location.href += "#";
//
//        // making sure we have the fruit available for juice (^__^)
//        global.setTimeout(function () {
//            global.location.href += "!";
//        }, 50);
//    };
//
//    global.onhashchange = function () {
//        if (global.location.hash !== _hash) {
//            global.location.hash = _hash;
//        }
//    };
//
//    global.onload = function () {  
//        noBackPlease();
//
//        // disables backspace on page except on input fields and textarea..
//        document.body.onkeydown = function (e) {
//            var elm = e.target.nodeName.toLowerCase();
//            if (e.which === 8 && (elm !== 'input' && elm  !== 'textarea')) {
//                e.preventDefault();
//            }
//            // stopping event bubbling up the DOM tree..
//            e.stopPropagation();
//        };          
//    }
//
//})(window);