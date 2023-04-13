<%-- 
    Document   : fileListGrid
    Created on : Mar 13, 2019, 4:50:56 PM
    Author     : ayushi.jain
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
    <s:if test="%{challanCount!=null && deducteeCount!=null}">
<div class="count-container">

        <label class="mr-1">Challan Count : </label> <label class="badge-pill badge-primary  mr-3"><s:property value="challanCount"/></label>
        <label class="mr-1">Deductee Count : </label> <label class="badge-pill badge-primary  mr-3"> <s:property value="deducteeCount"/></label>
</div>
    </s:if>
<!--        <div class="button-section col-md-12 my-1 text-center" id="dwnldBtnRow"> 
            <button type="button" id="" class="form-btn" ><i class="fa fa-download" aria-hidden="true"></i>Download All FVU Related Files</button>       
            <button type="button" id="" class="form-btn" ><i class="fa fa-download" aria-hidden="true"></i>Download Text File</button>       
            <button type="button" id="" class="form-btn" ><i class="fa fa-download" aria-hidden="true"></i>Download FVU File</button>       
        </div>-->

