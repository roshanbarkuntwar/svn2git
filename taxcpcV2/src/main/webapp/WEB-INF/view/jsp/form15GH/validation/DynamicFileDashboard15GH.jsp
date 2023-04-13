<%-- 
    Document   : DynamicFileDashboard15GH
    Created on : May 7, 2019, 9:59:27 AM
    Author     : gaurav.khanzode
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/processFolder/Errorcommon.css" type="text/css">
        <link rel="stylesheet" href="resources/css/processFolder/processFolder.css" type="text/css">
        <script type="text/javascript" src="resources/js/form15GH/validateError/validation15GH.js?r=<%=Math.random()%>"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="row">
            <div class="col">
                <div class="card list-sec border-0 p-2 mb-2 pt-1">
                    <div class="row align-items-start">
                        <s:if test="viewTdsProcessRecoListCh!=null && viewTdsProcessRecoListCh.size()>0">
                            <div class="col-md-8">
                                <p class="mb-3"><s:property value="viewTdsProcessRecoListCh.get(0).data_type_name"/></p>
                            </div>
                            <div class="col-md-4 text-center">
                                <p class="recordNo d-inline-block px-1 mb-0"><s:property value="viewTdsProcessRecoListCh.get(0).rec_count"/></p>
                                <s:hidden  id="%{viewTdsProcessRecoListCh.get(0).user_Seq}" name="data_type" value="%{viewTdsProcessRecoListCh.get(0).data_type}"/>
                            </div>
                        </s:if>                    
                    </div>
                </div>
            </div>
            <s:if test="viewTdsProcessRecoListDed!=null && viewTdsProcessRecoListDed.size()>0">
                <s:iterator value="viewTdsProcessRecoListDed" status="head">
                    <div class="col">
                        <div class="card list-sec border-0 p-2 mb-2 pt-1">
                            <div class="row align-items-start">
                                <div class="col-md-8">
                                    <p class="mb-3"><s:property value="data_type_name"/></p>
                                </div>
                                <div class="col-md-4 text-center">     
                                    <p class="recordNo d-inline-block px-1 mb-0"><s:property value="rec_count"/></p>
                                    <s:hidden  id="%{user_Seq}" name="data_type" value="%{data_type}"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </s:iterator>        
            </s:if>
        </div>
    </body>
</html>
