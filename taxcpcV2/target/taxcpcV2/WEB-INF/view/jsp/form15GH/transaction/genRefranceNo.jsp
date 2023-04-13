<%-- 
    Document   : genRefranceNo
    Created on : Mar 19, 2019, 12:34:49 PM
    Author     : aniket.naik
--%>

<%@taglib prefix="s" uri="/struts-tags" %>
<script src="resources/js/form15GH/deducteeManage15GHDetail.js?r=<%Math.random();%>" type="text/javascript"></script>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<div class="page-section">
    <s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
    <s:hidden id="clientLoginLevel" name="clientLoginLevel" value="%{clientLoginLevel}" />
    <s:hidden id="GenTypeValue" name="GenTypeValue"/>
    <s:hidden id="dispTempFlag" name="dispTempFlag" value="F"/>
    <s:hidden id="loginGenerationLevelClient" name="generationLevelClient"/>

    <div class="col-md-12 my-1  main-page-heading">
        <h4 class="page-title mb-2">Reference No Generation</h4>
    </div>
    <div class="tab-content form-wrapper px-4 py-2">
        <%@include file="/WEB-INF/view/jsp/global/validationMessages.jsp" %>
        <div class="container-fluid pt-4" >
            <s:if test="%{clientLoginLevel < 6}">
                <div class="row form-group">
                    <div class="col-lg-11 text-center">
                        <div class="custom-control custom-radio custom-control-inline">
                            <input type="radio" class="custom-control-input" id="Loginradio" name="" value="customEx" checked="true" onclick="displayGenTypeOneDiv();">
                            <label class="custom-control-label" for="Loginradio">Login Level</label>
                        </div>
                        <div class="custom-control custom-radio custom-control-inline">
                            <input type="radio" class="custom-control-input" id="nextradio" name="" value="customEx"  onclick="displayNextLevelData();">
                            <label class="custom-control-label" for="nextradio">Next Level</label>
                        </div>
                    </div> 
                </div>
            </s:if>
            <div id="15GHNumberGenerationLevelDivId" style="display: none;">
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-4 text-right" style="text-align: center;">
                        <label for="exampleInputName2">15GH Number Generation Level<span style="color: red;"></span> :</label>
                    </div>
                    <div class="col-lg-5 col-xl-4">
                        <s:select list="generationLevel" id="generationLevelClientId" name="generationLevelClient"  cssClass="form-control" onchange="checkClientLevel();" />
                    </div>
                </div>
            </div>
            <div id="GenTypeOne">
                <div class="row form-group"> 
                    <div class="col-lg-4 col-xl-4 text-right">
                        <label class="control-label">
                            Last Generated Number 15G 
                        </label>
                    </div>
                    <div class="col-lg-5 col-xl-4">
                        <div class="input-group">
                            <div class="input-group-append">
                                <button type="button" class="btn btn-primary btn-sm addon-btn">
                                    G - 
                                </button>
                            </div>               
                            <s:textfield id="lastGen15GNoId" name="lastGen15GNo" readonly="true" cssClass="form-control"  maxLength="9"/>
                        </div>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-lg-4 col-xl-4 text-right">
                        <label class="control-label">
                            Last Generated Number 15H 
                        </label>
                    </div>

                    <div class="col-lg-5 col-xl-4">
                        <div class="input-group">
                            <div class="input-group-append">
                                <button type="button" class="btn btn-primary btn-sm addon-btn">
                                    H - </button>
                            </div>               
                            <s:textfield id="lastGen15HNoId" name="lastGen15HNo" readonly="true" cssClass="form-control"  maxLength="9"/>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-4 text-right">
                        <label class="control-label">
                            Next Number 15G <span class="text-danger font-weight-bold ml-1">*</span>
                        </label>
                    </div>

                    <div class="col-lg-5 col-xl-4">
                        <div class="input-group">
                            <div class="input-group-append">
                                <button type="button" class="btn btn-primary btn-sm addon-btn">
                                    G - </button>
                            </div>               
                            <s:textfield id="NextGen15GNoId" name="NextGen15GNo" onblur="validateRefranceNoG(this.id);" cssClass="form-control" maxLength="9"/>
                        </div>
                    </div>

                    <div class="col-lg-3 col-xl-4">
                        <s:hidden id="editRadioBtnG" name="editRadioBtnG" value="false"/>
                        <div class="custom-control custom-radio custom-control-inline">
                            <input id="RValueG" type="radio" class="custom-control-input"  onclick="editRadioBtnG(this.id)"  name="" value="customEx">
                            <label class="custom-control-label" for="RValueG">Refresh</label>
                        </div>
                        <div class="custom-control custom-radio custom-control-inline">
                            <input  id="BValueG" type="radio" class="custom-control-input"   onclick="editRadioBtnG(this.id)" name="" value="customEx">
                            <label class="custom-control-label" for="BValueG">Blank</label>
                        </div> 
                    </div> 
                </div>
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-4 text-right">
                        <label class="control-label">
                            Next Number 15H <span class="text-danger font-weight-bold ml-1">*</span>
                        </label>
                    </div>

                    <div class="col-lg-5 col-xl-4">
                        <div class="input-group">
                            <div class="input-group-append">
                                <button type="button" class="btn btn-primary btn-sm addon-btn">
                                    H - </button>
                            </div>               
                            <s:textfield id="NextGen15HNoId" name="NextGen15HNo" onblur="validateRefranceNoH(this.id);" cssClass="form-control"  maxLength="9"/>
                        </div>
                    </div>
                    <div class="col-lg-3 col-xl-4">
                        <s:hidden id="editRadioBtnH" name="editRadioBtnH" value="false"/>
                        <div class="custom-control custom-radio custom-control-inline">
                            <input id="RValueH" type="radio" class="custom-control-input" onclick="editRadioBtnH(this.id)" name="" value="customEx">
                            <label class="custom-control-label" for="RValueH">Refresh</label>
                        </div>
                        <div class="custom-control custom-radio custom-control-inline">
                            <input id="BValueH" type="radio" class="custom-control-input"  onclick="editRadioBtnH(this.id)" name="" value="customEx">
                            <label class="custom-control-label" for="BValueH">Blank</label>
                        </div> 
                    </div> 
                </div>
                <div class="row">
                    <div class="button-section col-md-12 my-1 text-center"> 
                        <button type="button" id="saveContinue" class="form-btn" onclick="saveReferenceNo();"><i class="fa generate" aria-hidden="true"></i>Generate</button>
                        <s:url id="cancelBtn_id" namespace="/" action="form15GHTransaction"></s:url>             
                        <s:a href="%{cancelBtn_id}">
                            <button type="button" id="saveexit" class="form-btn"><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                        </s:a>      
                    </div>
                </div>
            </div>          
        </div>
        <!--    </div>
            <div class="tab-content form-wrapper px-4 py-2 text-center"  id="GenTypeTwo">-->
        <div class="container-fluid px-4 py-2 text-center" id="GenTypeTwo">
            <div id="genType2TableID">
                <!-- Ajax Data -->
            </div>

            <div id="next-level-btns" style="display: none;">
                <button type="button" class="form-btn" onclick="downloadGenerationError();"><i class="fa download" aria-hidden="true"></i>Download</button>
                <button type="button" class="form-btn" onclick="uploadReferenceNoModal();"><i class="fa upload" aria-hidden="true"></i>Upload</button>
                <button type="button" id="saveContinue" class="form-btn" onclick="save15GhRefNoDetailsData();"><i class="fa generate" aria-hidden="true"></i>Generate</button>
                <s:url id="cancelBtn" namespace="/" action="getGenReferenceNoAction?action=RefNoPage"></s:url>
                <s:a href="%{cancelBtn}">
                    <button type="button" id="saveexit" class="form-btn"><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                </s:a>
            </div>
        </div>
    </div>                    
</div>

<!--Ref no upload modal-->
<div class="modal" tabindex="-1" id="uploadReferenceModal" role="dialog">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <s:form id="excelReferenceNoFiles" name="excelReferenceNoFiles" action="importMasterExcelFiles?validate=true" 
                    method="post" theme="css_xhtml" enctype="multipart/form-data" autocomplete="off">

                <s:hidden id="genClientCode" name="GenClientCode"/>

                <div class="modal-header">
                    <h5 class="modal-title" id="myModalLabel">Upload Attachment</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4  text-right">
                            <label class="control-label">
                                Attachment<span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-7">
                            <s:file title="Upload only Excel File" id="template" name="obj_exl_html.template" TYPE="file" cssClass="filestyle choose-file-btn" data-buttonText="Choose file" data-buttonName="btn-primary choose-file-btn" cssStyle="width: 100%;" onchange="checkFile(event);">
                            </s:file>
                            <input type="file" class="custom-file-input form-control" title="Upload only Excel File" onchange="checkFile(event);">
                        </div>
                    </div>
                    <div class="text-center">
                        <button type="button" class="form-btn" onclick="uploadReferenceXls();"><i class="fa upload" aria-hidden="true"></i>Upload</button>
                        <button type="button"  class="form-btn" data-dismiss="modal"><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                    </div>
                </div>
            </s:form>
        </div>
    </div>
</div>
<!--Ref no upload modal-->

<s:if test="%{clientLoginLevel < 6}">
    <script type="text/javascript">
        try {
            displayGenTypeOneDiv();
        } catch (err) {
        }
    </script>   
</s:if>
<s:else>
    <script type="text/javascript">
        try {
            checkClientLevel();
        } catch (err) {
        }
    </script>
</s:else>    