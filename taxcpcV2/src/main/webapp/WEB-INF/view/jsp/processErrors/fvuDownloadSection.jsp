<%-- 
    Document   : fvuDownloadSection
    Created on : Apr 14, 2022, 10:34:52 AM
    Author     : akash.meshram
--%>


<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="resources/css/processFolder/Errorcommon.css" type="text/css">
    </head>
    <body>
        <div class="page-section">
            <div class="tab-section col-md-12 my-1">
                <ul class="nav nav-pills">
                   <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link active" href="#"><p>FVU Download</p></a></li>
                </ul>
                <div class="clearfix"></div>
            </div>

            <div class="tab-content px-4 py-4">
                <div class='mt-4'>
                    <div style="background: #e2ecff;border-radius: 10px;" class="py-3">
                        <p class="text-center mb-0"><span style="font-size:16px">FVU Download Status :</span>
                             <s:if  test="%{processStatus.equalsIgnoreCase('FD')}">  
                                <strong><span class="text-danger">FVU -Error File Generated</span></strong>
                            </s:if>
                            <s:if  test="%{processStatus.equalsIgnoreCase('FC')}"> 
                                <strong><span class="text-success">FVU 27A PDF file Creation successfully completed</span></strong>
                                 </s:if>
                        </p>
                    </div>
                </div>
               <div class="table-responsive mt-2 d-flex justify-content-center">                                                                                                            
                        <table class="table table-striped text-center" style="width:auto;">
                            <thead>
<!--                                <tr>
                                    <s:if  test="%{processStatus.equalsIgnoreCase('FD')}">  
                                    <th colspan="3" class="text-center"><span style="font-size: 18px">FVU -Error File Generated</span></th>
                                    </s:if>
                                    <s:if  test="%{processStatus.equalsIgnoreCase('FC')}">  
                                    <th colspan="3" class="text-center"><span style="font-size: 18px">FVU 27A PDF file Creation successfully completed</span></th>
                                    </s:if>
                                    
                                    </tr>-->
                                <tr>
                                    <th class="td-fvu-sr-no">Sr No.</th>
                            <th class="td-fvu-file-type">File Name</th>
                            <th class="td-fvu-action">Download</th>
                                </tr>
                            
                            </thead>
                            <tbody>
                    <s:if test="map!=null && map.size()>0">            
                    <s:iterator value="map" var="filelist" id="head" status="indexValue">
                            <tr>
                            <td><s:property value="%{#indexValue.count}"/></td>
                            <td><s:property  value="key"/></td>
                            <input type="hidden" id="file_name~<s:property value="%{#indexValue.count}"/>"  value="<s:property  value="key"/>">
                            <input type="hidden" id="file_path~<s:property value="%{#indexValue.count}"/>"  value="<s:property  value="value"/>">
                            <td><button type="button" class="download-btn ml-0"  onclick="downloadsinglefile('<s:property value="%{#indexValue.count}"/>');" ><i title="Download FVU File" class="fa download" aria-hidden="true"></i></button></td>
                            </tr>
                             
                            </tr>                                                                            
                    </s:iterator>
                    </s:if>        
                       </tbody>
                        </table>
                    </div>
                <hr>
                 <div class="button-section col-md-12 my-1 text-center"> 
                            <button type="button" id="backBtn" class="form-btn" onclick="callUrl('/errorStatus');" ><i class="fa back" aria-hidden="true"></i>Back</button>       
                           
                </div>
               </div>
                
               
            </div>
        </div>
    </body>
 <script type="text/javascript" src="resources/js/regular/validation/validateError.js"></script>    
</html>
