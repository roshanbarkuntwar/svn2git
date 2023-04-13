<%-- 
    Document   : formWithSingleButton
    Created on : Oct 11, 2021, 4:46:25 PM
    Author     : sakshi.bandhate
--%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<script src="resources/js/dashboard/dashboard.js?r=" type="text/javascript"></script>
<script type="text/javascript" src="resources/js/lhs/lhsAjax.js?r=<%=Math.random()%>"></script>
<div class="page-section">
    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">
            <li class="nav-item mr-5">
                <a class="nav-link active" href="tdsDeductees">
                    <s:if test="%{formName != null}">
                    <p><s:property value="formName" /> </p>
                    </s:if>
                    </a>
             </li>
        </ul>
      <div class="clearfix"></div>      
    </div>
    <div class="clearfix"></div>
    <div class="tab-content px-4 pt-4">
        <div class="tab-pane active" id="">
            <div class="text-center py-4">
                <button type="button" class="form-btn " >
                <i class="fa generate" aria-hidden="true"></i>
                <s:if test="%{formName != null}">
                    <a href="<s:property value="actionUrl"/>">
                        Generate <s:property value="formName" />
                    </a>
                </s:if>
                <s:else>Generate Data</s:else>
        </button>
            </div>
        
    </div>
        </div>
</div>

