<%-- 
    Document   : importTabContent
    Created on : Feb 24, 2021, 4:47:30 PM
    Author     : Kapil Gupta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>'
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="resources/js/admin/misReport/misReport.js?r="></script>
<script type="text/javascript" src="resources/js/lhs/lhsAjax.js?r=<%=Math.random()%>"></script>

<div class="page-section">
    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">
            <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link disabled" style="pointer-events:none;" data-toggle="pill" href="#"><p>MIS Reports</p></a></li>
            <li class="nav-item mr-5">
                <a class="nav-link active" data-toggle="pill" href="#browseMISReport">
                    <p><s:property value="report_header_text"/>  (Upload File)</p>
                </a>
            </li>
        </ul>
        <div class="clearfix"></div>
    </div>
    <div class="tab-content px-4 py-4">
        <br>
        <div class="button-section my-1">

        </div>
        <form id="misuploadTabFile" name="misuploadTabFile" action="/taxcpcV2/showMisUploadTab" method="post" enctype="multipart/form-data" autocomplete="off">
              <input type="hidden" id="seq_id" name="seq_id" value="<s:property value="seqId"/>"/>
              <input type="hidden" id="report_header_text" name="report_header_text" value="<s:property value="report_header_text"/>"/>
              <input type="hidden" id="rep_upload_template_code" name="rep_upload_template_code" value="<s:property value="rep_upload_template_code"/>"/>
              <input type="hidden" id="reportGroup" name="reportGroup" value="<s:property value="reportGroup"/>"/>
              <input type="hidden" id="rep_upload_dir" name="rep_upload_dir" value="<s:property value="rep_upload_dir"/>"/>
              <input type="hidden" id="docname" name="docname" value="<s:property value="filename"/>"/>
            <div class="row form-group">
            <div class="col-lg-4 col-xl-4 text-right">
                <label class="control-label">
                    File <span class="font-weight-bold text-danger ml-1">*</span>
                </label>
            </div>
            <div class="col-lg-7 col-xl-4">
                <div class="custom-file">
                    <input type="file" class="custom-file-input form-control" name="misUploadFile" id="misUploadFile" onchange="$(this).next('.custom-file-label').html(this.files[0].name)" title="Upload File">
                    <label class="custom-file-label" for="validatedCustomFile">Choose file...</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="button-section col-md-12 my-1 text-center"> 
                    <button type="button" class="form-btn" onclick="validateMisImportFile(event);"><i class="fa upload" aria-hidden="true"></i>Upload</button>
                    <a href='reportAction'><button type="button"   class="form-btn" ><i class="fa cancel" aria-hidden="true"></i>Cancel</button></a>
                </div>
            </div>
        </div>
        </form>
    </div> 
</div> 

