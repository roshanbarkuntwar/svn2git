<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<link href="resources/css/excelImport/excelImport.css" rel="stylesheet">  
<div class="page-section">

    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">                                                                                      <!--fa fa-table mr-2   fa fa-pencil-square mr-2-->
            <li onclick="callPanWise()" class="nav-item mr-5"><a class="nav-link active" data-toggle="pill" href="#importStatus"><i class="fa fa-download mr-2" aria-hidden="true"></i><p>Download TDS Certificate (PANNO Wise)</p></a></li>
            <li onclick="callTanWise()" class="nav-item"><a class="nav-link" data-toggle="pill" href="#importStatus1"><i class="fa fa-download mr-2" aria-hidden="true"></i><p>Download TDS Certificate (Branch Wise)</p> </a></li>
        </ul>
        <div class="clearfix"></div>
    </div>

    <div class="clearfix"></div>

    <div class="col-lg-12">
        <div class="progress d-none" id="prgogress_bar">
            <div class="progress-bar progress-bar-striped progress-bar-animated bg-success w-100" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100">Generating Form 16... Don't Refresh the Page and Don't Press Any Button </div>
        </div>
    </div>
    <%@include file="/WEB-INF/view/jsp/global/validationMessages.jsp" %>



    <!--        <div id="importStatus" class="tab-pane importStatus-container  show in active "> 
                <div class="container-fluid pt-4">
                    <div class="row">
    
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header text-white position-relative">
                                    <i class="fa fa-download position-absolute" aria-hidden="true"></i>  Download Form16
                                </div>
    <s:form id="downloadForm" name ="downloadForm" action ="downloadTdsCertificate" method="post" >

        <s:hidden name ="urlPath" id= "url" />
        

        <div class="card-body">
            <div class="row form-group">
                <div class="col-lg-4 col-xl-4 text-right">
                    <label class="control-label">
                        Enter Your PANNO. <span class="font-weight-bold text-danger ml-1">*</span>
                    </label>
                </div>
                <div class="col-lg-4 col-xl-4">
        <s:textfield  name="panno"  id="panno" cssClass="form-control" placeholder="PAN No." onblur= "submitForm(this);" />
    </div>
    <div class="col-lg-4 col-xl-4">
        <button type="button" class="form-btn" id="saveCaptchaBTN"  onclick="download();"><i class="fa fa-download" aria-hidden="true"></i>Download</button>
    </div>
</div>

</div>
    </s:form>
</div>
</div>
</div>
</div>
</div>-->
    <s:form id="downloadForm" name ="downloadForm" action ="downloadUsingTanOrPan" method="post" >
            <s:hidden name="downloadType" id="downloadType"/>
        <div class="tab-content px-4 py-4">
            <div id="importStatus1" class="tab-pane importStatus1-container  show in"> 

                <s:if test="%{sessionResult != null}">
                    <div class="text-center col-md-12" id="session_notification" >
                        <div class="d-inline-block">
                            <s:div cssClass="%{#session.ERRORCLASS}">                                
                                <h5 class="d-inline-block" > <s:property value="sessionResult" /> </h5>
                            </s:div>
                        </div>
                    </div>
                </s:if> 

                <div class="container-fluid pt-4">
                    <div class="row">
                        <div class="col-lg-12">
                            <s:hidden name ="flag" id= "flag" />
                            <!--                                        <div class="col-lg-6 col-xl-6 " > 
                                                                        <button type="button" class="form-btn w-100"  onclick="callRefershProcedure();"><i class="fa fa-download" aria-hidden="true"></i>Refresh Data</button>
                                                                    </div>-->
                            <!--                                        <div class="col-lg-6 col-xl-6 ">
                                                                        <button type="button" class="form-btn w-100" id="saveCaptchaBTN"  onclick="downloadFile('T');"><i class="fa fa-download" aria-hidden="true"></i>Download</button>
                                                                    </div>-->
                            <!--<div class="table-responsive mt-2" id="">-->
                            <s:if test="tannoList!=null && tannoList.size()>0">
                                <table class="table table-bordered table-striped mb-1" id="table" align="center">
                                    <thead>
                                        <tr class="text-center">                                                    
                                            <th>Sr No.</th>
                                            <s:if test="%{downloadType.equalsIgnoreCase('F')}">
                                            <th>Bank Branch Code</th>
                                            </s:if>
                                                <s:else>
                                                <th>TANNO</th>
                                                
                                                </s:else>
                                            
                                            <th>Download</th>
                                                <s:if test="%{uploadFileFlag.equalsIgnoreCase('T')}">
                                                <th>Upload</th>
                                                </s:if>
                                        </tr>
                                    </thead>
                                    <tbody style="border: 1px solid #e0e0e0; border-radius: 3px;">                                        
                                        <s:iterator value="tannoList" id="head" status="indexValue">
                                            <s:set var="tds_tan" value="%{#head.get(0)}"></s:set>
                                            <%--<s:set var="tds_filepath" value="%{#head.get(1)}"></s:set>--%>
                                            <s:set var="tds_rowid" value="%{#head.get(2)}"></s:set>
                                                <tr class="text-center">
                                                    <td>
                                                    <s:property value="%{#indexValue.count}"/>
                                                </td>
                                                <td>
                                                    <s:property value="#head.get(0)"/>
                                                </td>
                                                <td>
                                                    <!--<button type="button" class="form-btn w-30" id="saveCaptchaBTN" >-->
                                                    <i class="fa download text-primary" aria-hidden="true" title="Download" onclick="downloadFile('T');"></i>
                                                    <!--</button>-->
                                                </td>
                                                <s:if test="%{uploadFileFlag.equalsIgnoreCase('T')}">
                                                    <td>
                                                        <!--<button type="button" class="form-btn w-30"  >-->
                                                        <i class="fa fa-upload" data-toggle="collapse" data-target="#uploadfilediv" aria-hidden="true" onclick="tdsUpload('<s:property value="#tds_tan"/>', '<s:property value="#tds_rowid"/>');"></i>
                                                        <!--</button>-->
                                                    </td>
                                                </s:if>
                                            </tr>                                                                      
                                        </s:iterator>
                                    </tbody>
                                </table>
                            </s:if>
                            <s:else>
                                <!--No records found!-->
                                <div class="table-responsive mt-2" id="dataSpan">
                                    <!--Ajax response here-->
                                    <%
                                        out.println(GlobalMessage.PAGINATION_NO_RECORDS);
                                    %>
                                </div>
                                <!--Table Section-->
                                <div class="clearfix"></div> 
                            </s:else>
                            <!--</div>-->
                        </div>
                    </div>
                </div>

            </div>
            <!--</div>-->
            <!--<div class="tab-content px-4 py-4">-->
            <div id="importStatus" class="tab-pane importStatus-container  show in active"> 
                <div class="container-fluid pt-4">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-body">
                                    <div class="row form-group">
                                        <div class="col-lg-4 col-xl-4 text-right">
                                            <label class="control-label">
                                                PANNO.<span class="font-weight-bold text-danger ml-1">*</span>
                                            </label>
                                        </div>
                                        <div class="col-lg-4 col-xl-4">
                                            <s:textfield  name="panno"  id="panno" cssClass="form-control" placeholder="PAN No." maxlength="10" cssStyle="text-transform: uppercase;" onblur= "validatePAN(this);" />
                                        </div>
                                        <div class="col-lg-4 col-xl-4">
                                            <button type="button" class="form-btn mt-0" id="saveCaptchaBTN"  onclick="downloadFile('P');"><i class="fa fa-download" aria-hidden="true"></i>Download</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </s:form>   
</div>
<script>
    function submitForm(field) {
        var panno = document.getElementById("panno").value;
        var cp = document.getElementById("globalcontextpath").value;
        var url = cp + "/downloadTdsCertificate?action=urlData&panno=" + panno;
        if (panno !== "" && panno !== "null" && panno !== null && validatePAN(field)) {
            $.ajax({
                url: url,
                type: "POST",
                success: function (data) {
                    document.getElementById("url").value = data;
                }
            });
        }
    }
    function download() {
        var panno = document.getElementById("panno").value;
        if (!lhsIsNull(panno) && validatePAN(document.getElementById("panno"))) {
            showProcessIndicator();
            var url = document.getElementById("url").value;
            window.location = url;
            setTimeout(function () {//           
                hideProcessIndicator();
            }, 5000);
        } else {
            addActionError("message", "Enter Valid PAN Number");
        }
    }
    // create new function for pan validation due to global validation pan validation purpose is different.
    function validatePAN(field) {
        var value = field.value;

        if (value !== "" && value !== "null" && value !== null) {
            var Exp = /^([a-zA-Z]){3}(a|A|B|b|p|P|f|F|c|C|G|k|K|g|h|H|L|l|J|j|t|T){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/;
            if (!Exp.test(value)) {
                addActionError("message", "Invalid PAN Number");
                field.focus();
                return false;
            } else {
                removeError();
                return true;
            }
        } else {
            removeError();
            return false;
        }
    }
    function hideProcessIndicator() {
        try {
//            document.getElementById("browse_process_indicator_css1").style.display = "none";
            document.getElementById("prgogress_bar").style.display = "none";
        } catch (err) {

        }
    }//end method
    function showProcessIndicator() {
        try {
//            document.getElementById("browse_process_indicator_css1").style.display = "block";
            document.getElementById("prgogress_bar").style.display = "block";
        } catch (err) {

        }
    }
    function validateTAN(field) {
        var value = field.value;
        if (!lhsIsNull(value)) {
            var Exp = /^([a-zA-Z]){4}([0-9]){5}([a-zA-Z]){1}?$/;
            if (!Exp.test(value)) {

                addActionError("message", "Invalid TAN Number");
                field.focus();
                return false;
            } else {
                removeError();
                return true;
            }
        } else {
            removeError();
            return false;
        }
    }//end function
    function downloadFile(flag) {
        document.getElementById("flag").value = flag;
        if (flag === 'P') {
            var panno = document.getElementById("panno").value;
            if (!lhsIsNull(panno) && validatePAN(document.getElementById("panno"))) {

                document.getElementById("downloadForm").submit();
            } else {
                openErrorModal("Enter Valid PAN Number");
//                addActionError("message", "Enter Valid PAN Number");
            }
        } else if (flag === 'T') {
//            var tanno = document.getElementById("tanno").value;
//            if (!lhsIsNull(tanno) && validateTAN(document.getElementById("tanno"))) {

            document.getElementById("downloadForm").submit();
//            } else {
//                addActionError("message", "Enter Valid TAN Number");
//            }
        }
    }
    function callRefershProcedure() {
        var cp = $("#globalcontextpath").val();
        var url = cp + "/tdsCertAjax?action=refreshForm16";
        $.ajax({
            url: url,
            success: function (result) {
                if (result !== null && result !== "null" && result !== "" && result === "success") {
                    openSuccessModal("Records Refreshed Successfully!");
                } else {
                    openErrorModal("Some Error Occurred!");
                }
            }
        });
    }//end function

    function tdsUpload(tanno, rowid) {
        $('#uploadfilediv').attr('class', 'd-block');
        document.getElementById("tds_tan_no").value = tanno;
        document.getElementById("tds_rowid").value = rowid;
    }//end function
</script>