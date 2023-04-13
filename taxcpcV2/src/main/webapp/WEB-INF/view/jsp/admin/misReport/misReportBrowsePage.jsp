<%-- 
    Document   : misReportBrowsePage
    Created on : Oct 18, 2019, 11:39:46 AM
    Author     : gaurav.khanzode
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<link href="resources/css/misReport/misReport.css" rel="stylesheet">
<div class="page-section">
    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">
            <li class="nav-item mr-5"><a class="nav-link active" data-toggle="pill" href="reportAction"><p>MIS Reports</p></a></li>
        </ul>
        <div class="clearfix"></div>
    </div>
    <% 
        String javadrive = (String)  request.getSession().getAttribute("JAVADRIVE");
        String oracledrive = (String)  request.getSession().getAttribute("ORACLEDRIVE");
        String blobdownloadflag = (String)  request.getSession().getAttribute("BLOBDOWNLOADFLAG");
        //System.out.println("Response get javadrive--->" + javadrive);
    %>
  
    <input type="hidden" name="javadrive" id="javadrive_name"  value="<%=javadrive%>" /> 
    <input type="hidden" name="oracledrive" id="oracledrive_name"  value="<%=oracledrive%>" />  
    <input type="hidden" name="blobdownloadflag" id="blobdownloadflag"  value="<%=blobdownloadflag%>" />  


    <div class="tab-content px-4 py-4">
        <div class="text-center col-md-12" id="error_div_dwn" style="display: none;">
            <div class="d-inline-block">
                <div class="form-validation form-validation--info p-1 my-1" id="err_rep">
                    <i class="fa fa-info-circle mr-2" aria-hidden="true"></i>
                    <h5 class="d-inline-block" id="error_details" ></h5>
                </div>
            </div>
        </div>
        <div class="tab-pane show in active">
            <div class="text-center col-md-12" id="notificationMsgDiv" style="display: none;">
                <div class="d-inline-block">
                    <div class="form-validation form-validation--info p-1 my-1">
                        <i class="fa fa-info-circle mr-2" aria-hidden="true"></i>
                        <h5 class="d-inline-block" id="notificationMsg">Notification</h5>
                    </div>
                </div>
            </div>
            <div class="panel-group" id="dataSpan">
                <!-- MIS Dashboard data here-->
            </div>
        </div>
    </div>
      

</div>

<script type="text/javascript" src="resources/js/admin/misReport/misReport.js?r=<%=Math.random()%>"></script>
<script type="text/javascript">
    $(document).ready(function () {
        try {
            onloadMisDashboard();
        } catch (e) {
        }



    });


</script>
