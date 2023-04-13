<%-- 
    Document   : GenerateXML15GH
    Created on : May 7, 2019, 2:24:37 PM
    Author     : seema.mourya
--%>


<%@taglib prefix="s" uri="/struts-tags"%>
<script src="resources/js/global/bootstrap.min.js"></script>
<!--<script type="text/javascript" src="resources/js/form15GH/DeducteeManage15ghDetail/GenerateXML15GH/bulk15GHXml15gh.js?r=<%=Math.random()%>"></script>-->
<script src="resources/js/form15GH/GenerateXML15GH/bulk15GHXml15gh.js?r=<%=Math.random()%>" type="text/javascript"></script>
<link href="resources/css/bulk15GHXml15gh/bulk15GHXml15gh.css" rel="stylesheet" type="text/css"/>

<%--<s:div cssClass="page-header_title">
    <p>Regular > Deductee 15G/H > Generate 15G/15H Xml</p>
</s:div>--%>
<s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>

<s:if test="%{fileGenerated !=null && fileGenerated==true}">
    <s:url id="dwnldUrl" action="downloadbulk15GHXml15gh" namespace="/">
        <s:param name="filePath" value="%{filePath}"></s:param>
        <s:param name="fname" value="%{fileName}"></s:param>
    </s:url>
     <%--<s:a href="%{dwnldUrl}" cssClass="fa fa-download" cssStyle="float:right;">Download</s:a>--%>
     <s:a href="%{dwnldUrl}" cssStyle="float:right;"><button type="button" class="form-btn"><i class="fa fa-download" aria-hidden="true"></i>Download</button></s:a>
       
</s:if>

<s:if test="hasActionErrors()">            
    <s:div cssClass="%{#session.ERRORCLASS}">
        <s:actionerror/> 
    </s:div>
</s:if>

<!--<div class="gap2"></div>--> 
<s:url id="generateXml" action="generateXml15GHAction" namespace="/">
    <s:param name="action" value="'generate'"/>
</s:url>

<div class="tab-content" style="border: 1px solid #CCCCCC;">
    <s:hidden id="selectedValue" name="cgl"/>
    <s:hidden id="loginLevel" name="selectedLevel" value="%{selectedLevel}" />
    <div class="gap2"></div> 
    <div class="container-fluid">
        <div class="gap3"></div>
        <div class="row" style="border: 3px solid #CCCCCC;width: 90%;clear: both;margin: auto; border-radius: 6px;color:#3377FF; font-weight: bold">
          
                        </div>
            XML Are Generated on Level <span id="level"><s:property value="%{selectedLevel}"/></span>
        </div>
        <div class="gap3"></div>

                            <div class="row"> 
                                <%--<s:if test="%{fileGenerated !=null && fileGenerated==true}">
                                     <s:url id="dwnldUrl" action="downloadbulk15GHXml15gh" namespace="/">
                                        <s:param name="filePath" value="%{filePath}"></s:param>
                                        <s:param name="fname" value="%{fileName}"></s:param>
                                     </s:url>
                                 <s:a href="%{dwnldUrl}" cssStyle="float:right;"><button type="button" class="form-btn"><i class="fa fa-download" aria-hidden="true"></i>Download</button></s:a>
                                  
                                </s:if>
                                --%>
                                <div class="col-md-12 text-center"> 
                                   <button type="button"  class="form-btn" onclick="generateXml15GH();"> 
                                         <i class="fa fa-cog" aria-hidden="true"></i>Generate 15G/15H XML
                                    </button> 
                                    <s:a href="processErrors15GH">
                                        <button type="button"  class="form-btn" onclick="processErrorCheckProcedure15GH();"> 
                                            <i class="fa fa-chevron-left" aria-hidden="true"></i>Cancel
                                        </button> 
                                     </s:a>
                                </div>
                            </div>
           <div class="gap3"></div>
        </div>
        <div class="gap2"></div> 
    </div>

    <div class="bulk15gh_light_box_content" id="browse_process_indicator_bulk15gh">            
        <div class="bulk15ghprogress">
            <div class="progress-bar progress-bar-striped active progress-bar-primary" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:100%;background-color: blue;font-weight: bold;font-size: 15px;padding-top: 2px;">Generating XML, Please Wait It Will Take Some Time...</div>
        </div>
    </div>
    <script type="text/javascript">
        try {
            getCheckLoginType();
        } catch (err) {
        }
    </script>

    <!-- modal  -->    
    <div class="modal fade"
         id="existingPendingErrorModal"
         tabindex="-1"
         role="dialog"
         aria-labelledby="myModalLabel"
         aria-hidden="true"
         data-backdrop="static"
         >
        <div class="modal-dialog" id="modal_dialog" role="document"  style="top:100px;width: 98%;position: relative; clear: both; margin: auto; left: 14%;">
            <img src="<s:property value="#session.CONTEXTPATH" />/resources/images/global/progress_indicator.gif" id='browse_process_indicator_css'/>
        <div class="modal-content" style="width: 70%;">
            <div class="modal-header" style="background-color: #29aecc; color: white;padding: 4px;">
                <h4 id="modal_heading">Existing/Pending/Error</h4>
                <span aria-hidden="true" data-dismiss="modal" aria-label="Close" class="taxcpc-modal-close-btn" onclick="clearData();">&times;</span>
                <h4 class="modal-title" style="line-height: 0.42857;" id="header_name"></h4>
            </div>
            <div class="modal-body" style="position: relative;width: 100%;clear: both;margin: auto;height: 400px; overflow-y: auto;">
                <div id="table_data_in_modal">

                </div>           
            </div>
        </div>
    </div> 
</div> 
<!--modal --> 

