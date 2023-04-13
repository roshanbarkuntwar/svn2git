var ajax_call_enabled = false;

var globalZIndex = 454;
//autocomplete
function lhsAutocomplete(field_id, other_field_id, url) {
    $(function () {
        $("#" + field_id).autocomplete({
            source: function (request, response) {
                $.ajax({
                    url: url,
                    type: "GET",
                    data: {
                        term: request.term
                    },
                    dataType: "json",
                    success: function (data) {
                        response(data);
                    }
                });
            },
            focus: function (event, ui) {
                var ResultData = ui.item.value;
                if (ResultData !== null && ResultData !== "" && ResultData !== "null") {
                    var splitResultData = ResultData.split("-");
                    $("#" + other_field_id).val(splitResultData[0]);
                    $("#" + field_id).val(splitResultData[1]);
                }
                return false;
            },
            select: function (event, ui) {
                var ResultData = ui.item.value;
                if (ResultData !== null && ResultData !== "" && ResultData !== "null") {
                    var splitResultData = ResultData.split("-");
                    $("#" + other_field_id).val(splitResultData[0]);
                    $("#" + field_id).val(splitResultData[1]);
                }
                return false;
            }
        });
    });
}//end lhs_autocomplete method

function ajaxSubmitPostData(url, formId, callBackFunction) {
//    alert("submit--"+url);
//    showProcessIndicator();
    if (!ajax_call_enabled) {
        var cp = document.getElementById("globalcontextpath").value;
        var formElement = document.getElementById(formId);
        ajax_call_enabled = true;
        $.ajax({
            type: 'POST',
            url: cp + url,
            data: new FormData($(formElement)[0]),
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            //or your custom data either as object {foo: "bar", ...} or foo=bar&...
            success: function (response) {
                // response = setTouchEventsOnAjax(response);
                ajax_call_enabled = false;
                hideProcessIndicator();
                callBackFunction(response);
            },
            error: function (jqXHR, textStatus, errorThrown)
            {
                callBackFunction(null);
                hideProcessIndicator();
                console.log("errorThrown : " + errorThrown);
                ajax_call_enabled = false; //if fails    
            }
        });
    } else {
        window.alert("Please wait..\nYour previous request is in progress");
    }
}//end method

function ajaxPostUrl(url, callBackFunction) {
//    alert("Post--"+url);
    showProcessIndicator();
    if (!ajax_call_enabled) {
        var cp = document.getElementById("globalcontextpath").value;
        ajax_call_enabled = true;
        $.ajax({
            url: cp + url,
            type: "POST",
            success: function (response, textStatus, jqXHR)
            {
                ajax_call_enabled = false;
                callBackFunction(response);
                hideProcessIndicator();
            },
            error: function (jqXHR, textStatus, errorThrown)
            {
                callBackFunction(null);
                hideProcessIndicator();
                console.log("errorThrown : " + errorThrown);
                ajax_call_enabled = false; //if fails     
            }
        });
    } else {
//        window.alert("Please wait..\nYour previous request is in progress");
        hideProcessIndicator();
    }
}//end method

function ajaxPostUrlWithParam(url, callBackFunction, callBackParameter) {
//    alert("Post--"+url);
    if (!ajax_call_enabled) {
        var cp = document.getElementById("globalcontextpath").value;
        ajax_call_enabled = true;
        $.ajax({
            url: cp + url,
            type: "POST",
            success: function (response, textStatus, jqXHR)
            {
                response = setTouchEventsOnAjax(response);
                ajax_call_enabled = false;
                callBackFunction(response, callBackParameter);
            },
            error: function (jqXHR, textStatus, errorThrown)
            {
                callBackFunction(null, callBackParameter);
                console.log("errorThrown : " + errorThrown);
                ajax_call_enabled = false; //if fails     
            }
        });
    } else {
        window.alert("Please wait..\nYour previous request is in progress");
    }
}//end method