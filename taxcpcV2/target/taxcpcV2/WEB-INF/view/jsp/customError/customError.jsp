<%-- 
    Document   : globalError
    Created on : Jan 31, 2019, 10:31:48 AM
    Author     : neha.bisen
--%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<link rel="stylesheet" href="resources/css/customError/customError.css" type="text/css">





<div class="container-fluid h-100">
    <div class="row h-100 align-items-center">

        <div class="col-xl-4 offset-xl-4 col-lg-6 offset-lg-3">

            <div class="custom-form p-4 mx-auto text-center d-flex justify-content-center align-items-center position-relative flex-wrap">
                <i class="fa  fa-exclamation-triangle"></i>                   
                <h2 class="font-weight-bold mb-5 customError-title text-white">

                    <s:if test="%{errorMessage!=null && errorMessage!='' && errorMessage !='null'}">                             
                        <s:property value="errorMessage"/>
                    </s:if>
                    <s:else>
                        Sorry! There is some problem. Please contact to support person.</h2>
                </s:else>


            </div>


        </div>
    </div>
</div>




</body>
</html>
