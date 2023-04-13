<%-- 
    Document   : processErrorSummaryRefresh15GH
    Created on : Dec 16, 2019, 7:14:53 PM
    Author     : gaurav.khanzode
--%>
<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<div class="row mx-0">
    <s:if test="%{viewTranLoadErrorsSummery!=null && viewTranLoadErrorsSummery.size()>0}">              
        <s:iterator value="viewTranLoadErrorsSummery" status="head" >
            <div class="col-lg-4">
                <div class="media error-list position-relative">
                    <i class="fa fa-info-circle mr-3 position-absolute" title="<s:property value="error_type_name"/>"></i>
                    <div class="media-body">
                        <s:hidden id="error_type_code~%{#head.count}" name="error_type_code"/>
                        <h5 class="d-inline-block"><s:property value="error_type_name"/></h5>
                        <span class="text-primary float-right font-weight-bold"><s:property value="record_count"/></span>
                    </div>
                </div>
            </div>

            <div class="col-lg-4">
                <div class="media error-list position-relative">
                    <i class="fa fa-info-circle mr-3 position-absolute" title="Reviewed Error Records"></i>
                    <div class="media-body">
                        <h5 class="d-inline-block">Reviewed Error Records</h5>
                        <span class="text-primary float-right font-weight-bold"><s:property value="review_error_record"/></span>
                    </div>
                </div>
            </div> 

            <div class="col-lg-4">
                <div class="media error-list position-relative">
                    <i class="fa fa-info-circle mr-3 position-absolute" title="Pending for review"></i>
                    <div class="media-body">
                        <h5 class="d-inline-block">Pending for review</h5>
                        <span class="text-primary float-right font-weight-bold"><s:property value="pending_for_review"/></span>
                    </div>
                </div>
            </div>                                    
        </s:iterator>
    </s:if> 
    <s:else>
        <%=GlobalMessage.PAGINATION_NO_RECORDS%>
    </s:else>
</div>
