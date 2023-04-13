<%-- 
    Document   : fileDashboard
    Created on : Mar 4, 2016, 11:36:55 AM
    Author     : bhawna.agrawal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<link rel="stylesheet" href="resources/css/processFolder/Errorcommon.css" type="text/css">
<link rel="stylesheet" href="resources/css/processFolder/processFolder.css" type="text/css">

<div class="row">
<!--    <div class="col">
        <div class="card list-sec border-0 p-2 mb-2 pt-1">
            <div class="row align-items-start">
                <div class="col-md-8">
                    <p class="mb-3">Challan Transaction(s)</p>
                </div>
                <div  class="icon-sec font-weight-bold">
                    <s:if test="viewTdsProcessRecoListCh!=null && viewTdsProcessRecoListCh.size()>0">
                        <p class="recordNo d-inline-block px-1 mb-0"><s:property value="viewTdsProcessRecoListCh.get(0).rec_count"/></p>
                        <s:hidden  id="%{viewTdsProcessRecoListCh.get(0).user_Seq}" name="data_type" value="%{viewTdsProcessRecoListCh.get(0).data_type}"/>
                    </s:if>                    
                </div>
            </div>
        </div>
    </div>-->
    <div class="col-xl pr-xl-2 mb-lg-3 mb-xl-0 col-lg-6" >
        <div class="error-list pl-2 py-3 mb-0" >
            <div class="d-flex justify-content-between">           
                <h4 class="font-weight-bold mb-0">Challan Transaction(s)</h4>
                <div class="count font-weight-bold badge badge-primary text-white" >
                     <s:if test="viewTdsProcessRecoListCh!=null && viewTdsProcessRecoListCh.size()>0">
                        <p class="recordNo d-inline-block px-1 mb-0"><s:property value="viewTdsProcessRecoListCh.get(0).rec_count"/></p>
                        <s:hidden  id="%{viewTdsProcessRecoListCh.get(0).user_Seq}" name="data_type" value="%{viewTdsProcessRecoListCh.get(0).data_type}"/>
                    </s:if> 
                </div> 
              
            </div>
            <div class="icon-sec font-weight-bold" >
                <!--<i class="fa fa-check" title="Click Here to Verify"></i>-->
            </div>
        </div>
    </div>
    <s:if test="viewTdsProcessRecoListDed!=null && viewTdsProcessRecoListDed.size()>0">
        <s:iterator value="viewTdsProcessRecoListDed" status="head">
<!--            <div class="col">
                <div class="card list-sec border-0 p-2 mb-2 pt-1">
                    <div class="row align-items-start">
                        <div class="col-md-8">
                            <p class="mb-3"><s:property value="data_type_name"/></p>
                        </div>
                        <div class="icon-sec font-weight-bold">
                            <%--<s:if test="viewTdsProcessRecoListDed!=null && viewTdsProcessRecoListDed.size()>0">--%>                        
                            <p class="recordNo d-inline-block px-1 mb-0"><s:property value="rec_count"/></p>
                            <s:hidden  id="%{user_Seq}" name="data_type" value="%{data_type}"/>
                            <%--</s:if>--%>                    
                        </div>
                    </div>
                </div>
            </div>-->
                        <div class="col-xl pr-xl-2 mb-lg-3 mb-xl-0 col-lg-6" >
                                    <div class="error-list pl-2 py-3 mb-0" >
                                        <div class="d-flex justify-content-between">           
                                            <h4 class="font-weight-bold mb-0"><s:property value="data_type_name"/></h4>
                                            <div class="count font-weight-bold badge badge-primary text-white" ><s:property value="rec_count"/></div> 
                                            <s:hidden  id="%{user_Seq}" name="data_type" value="%{data_type}"/>
                                        </div>
                                        <div class="icon-sec font-weight-bold" >
                                            <!--<i class="fa fa-check" title="Click Here to Verify"></i>-->
                                        </div>
                                    </div>
                                </div>
        </s:iterator>        
    </s:if>
</div>

