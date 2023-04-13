<%-- 
    Document   : changeLogo
    Created on : Jul 26, 2021, 10:08:25 AM
    Author     : kapil.gupta
--%>

<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="resources/js/lhs/lhsAjax.js" type="text/javascript"></script> 
<script src="resources/js/lhs/lhsGlobal.js?r=<%= Math.random()%>"></script>
<script type="text/javascript" src="resources/js/global/baseLayout.js?r=<%=Math.random()%>"></script>

<div class="page-section">
    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">
            <li  class="nav-item mr-5"><a class="nav-link active"  ><p> Change Logo </p></a></li>
        </ul>
        <div class="clearfix"></div>
    </div>
    <div class="tab-content px-4 py-4">
        <div id="LogoPage" class="tab-pane importStatus-container  show in active ">
            <form id="logoTabUploadFile" name="logoTabUploadFile" action="/taxcpcV2/changeLogo?upload=true" method="post" enctype="multipart/form-data" autocomplete="off">
                <div class="container-fluid pt-4">
                    <div class="row form-group">
                        <div class="col-lg-3 col-xl-3 text-right pl-0">
                            <label class="control-label">
                               Upload Logo <span class="font-weight-bold text-danger ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-5 col-xl-5 pl-0">
                            <div class="custom-file">
                                <input type="file" class="custom-file-input form-control" name="logoUploadFile" id="logoUploadFile" onchange="$(this).next('.custom-file-label').html(this.files[0].name)" accept="image/png,image/jpeg"title="Upload logo"> 
                                <label class="custom-file-label" for="validatedCustomFile">Choose logo...</label> 
                            </div>
                            
                        </div>
                        <div class="col-lg-4 col-xl-4 pl-0">
                            <span class="text-danger"> ** Image size should not be more than 200 KB and accept only(.jpg, .jpeg, .png)</span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="button-section col-md-12 my-1 text-center"> 
                            <button type="button" class="form-btn" onclick="uploadLogoFile(event);"><i class="fa upload" aria-hidden="true"></i>Upload</button>
                            <button type="button" class="form-btn" onclick="resetLogoFile(event);"><i class="fa fa refresh" aria-hidden="true"></i>Reset Logo</button>
                            <button type="button"  onclick="window.location.reload();" class="form-btn" ><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                        </div>
                    </div>
                </div>
               
            </form>
        </div>
    </div>
</div>

