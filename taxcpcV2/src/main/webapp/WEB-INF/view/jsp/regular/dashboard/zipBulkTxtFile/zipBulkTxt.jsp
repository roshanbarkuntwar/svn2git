<%-- 
    Document   : zipBulkTxt
    Created on : Sep 3, 2021, 3:14:36 PM
    Author     : kapil.gupta
--%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<script type="text/javascript" src="resources/js/lhs/lhsAjax.js?r=<%=Math.random()%>"></script> 
<div class="page-section">
    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">
            <li  class="nav-item mr-5"><a class="nav-link active" data-toggle="pill" ><p>Zip For Bulk/NIL text File</p> </a></li>
            
        </ul>
    </div>
    <div class="clearfix"></div>
    <div class="tab-content px-4 py-4">
        <br>
        <div class="button-section my-1">
        </div>
     <s:form name="zipBulkTextFileForm" id="zipBulkTextFileForm" method="post" action="zipBulkTextFile"> 
            <div class="row form-group">
                <div class="col-lg-4 col-xl-4 text-right">
                    <label class="control-label">
                        Select Process Type <span class="font-weight-bold text-danger ml-1">*</span>
                    </label>
                </div>
                <div class="col-lg-7 col-xl-4">
                    <div class="custom-file">
                        <s:select label="Select Process Type" cssClass="form-control" id="processType"
                                  headerKey="" headerValue=" Select Process Type "
                                  list="#{'B':'Bulk Text File','N':'NIL Text File'}" 
                                  name="processType" 
                                  />
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-lg-4 col-xl-4 text-right">
                    <label class="control-label">
                        Enter Token Number <span class="font-weight-bold text-danger ml-1">*</span>
                    </label>
                </div>
                <div class="col-lg-7 col-xl-4">
                    <div class="custom-file">
                        <s:textfield type="text" cssClass="form-control" id="tokenNo" placeholder="Enter Token Number" title="Token Number" name="tokenNo"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="row">
                    <div class="button-section col-md-12 my-1 text-center"> 
                        <button type="button" id="zipBulkTextFile" class="form-btn mt-3" onclick="createZipBulkTextFile();" title="Click Here To Create Zip For Bulk/NIL text File/"><i class="fa generate" aria-hidden="true"></i>Create Zip File</button>
                    </div>
                </div>
            </div>
        </s:form>
        
       
</div>
<s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
<script type="text/javascript" src="resources/js/regular/bulkDownload/bulkDownload.js?r=<%=Math.random()%>"></script>
<script type = "text/javascript" src="resources/js/lhs/lhsGlobalValidation.js?r=<%Math.random();%>" ></script>

