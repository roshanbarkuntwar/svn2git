/**
 * Dashboard menu navigation function
 * @param {type} flag
 * @returns {undefined}
 */
function menuNavigation(flag) {
    let startIndex = $('#startIndex').val();
    startIndex = lhsIsNull(startIndex) ? 0 : parseInt(startIndex);
    let navListSize = $('#navListSize').val();
    navListSize = lhsIsNull(navListSize) ? 0 : parseInt(navListSize);
    let cardsCount = 16;
    if (flag === 'prev') {

        if (startIndex <= 0) {
            openErrorModal('Can not move to the previous page.');
            return;
        }
        $('#startIndex').val(startIndex - cardsCount);
    } else if (flag === 'next') {
        let nextStartIndex = startIndex + cardsCount;
        if (navListSize < cardsCount || nextStartIndex > navListSize) {
            openErrorModal('Can not move to the next page.');
            return;
        }
        $('#startIndex').val(nextStartIndex);
    }
    window.menuForm.submit();
}// End of navigation function

function  showBranchHierarchy(activeId) {
    showProcessIndicator();
    var cp = document.getElementById("globalcontextpath").value;
    if (!ajax_call_enabled) {
        $("#allBranch").removeClass("active");
        $("#loggedIn").removeClass("active");
        $("#"+activeId).addClass("active");
        
        var formURL = cp + "/getBranchHierarchy?action=getBranchHierarchyDetails";
        var activeTreeId = "allTree";
        if($("#allBranch").hasClass("active")){
            formURL = cp + "/getBranchHierarchy?action=getBranchHierarchyDetails";
            activeTreeId = "allTree";
        }else if($("#loggedIn").hasClass("active")){
            formURL = cp + "/getBranchHierarchy?action=getLoggedInBranchHierarchy";
            activeTreeId = "loggedInTree";
        }
//        alert(activeTreeId);
        $.ajax(
                {
                    url: formURL,
                    type: "POST",
                    success: function (treeData, textStatus, jqXHR)
                    {
                        if (!lhsIsNull(treeData)) {
//                            console.log(treeData);
                            if (/^<.*?>$/.test(treeData) && !!$(treeData)[0]) {
                                $("#"+activeTreeId).html(treeData);
                            } else {
                                $('#'+activeTreeId).jstree({'core': {
                                        'data': JSON.parse(treeData)
                                    }
                                });
                            }
                            $('#loggedInTree').on('ready.jstree', function() { 
                                $("#loggedInTree").jstree("open_all");  
                                $("#loggedInTree li a:last-child").css({'color' : 'green', 'font-weight' : 'bold'});
                            });
                        }
                    },
                    complete: function (jqXHR, textStatus) {
//                        $('#'+activeTreeId).jstree('open_all');
                        hideProcessIndicator();
                    },
                    error: function (jqXHR, textStatus, errorThrown)
                    {
                        hideProcessIndicator()
                        console.log("inside ajax error");
                        ajax_call_enabled = false; //if fails
                        openErrorModal("Some error occurred !!! ")
                    }
                });
    }
    ajax_call_enabled = false;
}

function activeAllBranchTab(id) {
      $('#'+id).trigger('click');
}

let nilReturn = () => {

    ajaxPostUrl("/dashboard?action=nilReturn", responseOfNilReturn);
}
;
let responseOfNilReturn = reponseOfToken => {
    if (!lhsIsNull(reponseOfToken)) {
        try {
            callDedicatedProcedure({
                callingProcName: "ProcGenerateTdsBulkTxtFile",
                tokenNo: reponseOfToken,
                fileHeaderType: "nil"
            });
        } catch (e) {
            console.log('%cprocedure calling ajax error', 'color: red;');
            console.log(e);
        }
        openSuccessModal("Your request is initiated & Your  Generated Token No. is :" + reponseOfToken, "window.location.reload();");
    }
};
