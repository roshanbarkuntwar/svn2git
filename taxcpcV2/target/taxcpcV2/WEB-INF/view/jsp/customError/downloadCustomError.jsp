<%-- 
    Document   : downloadCustomError
    Created on : May 9, 2022, 12:40:49 PM
    Author     : akash.meshram
--%>


<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<link rel="stylesheet" href="resources/css/customError/customError.css" type="text/css">



<% 
    String errorMessage = (String) request.getSession().getAttribute("error_Message");
%>
 <input type="hidden" name="errorMessage" id="errorMessage"  value="<%=errorMessage%>" /> 
  
<div class="container-fluid h-100">
    <div class="row h-100 align-items-center">

        <div class="col-xl-4 offset-xl-4 col-lg-6 offset-lg-3">

            <div class="custom-form p-4 mx-auto text-center d-flex justify-content-center align-items-center position-relative flex-wrap">
                <i class="fa  fa-exclamation-triangle"></i>                   
                <h2 class="font-weight-bold mb-5 customError-title text-white">
                    <div id="error_msg"></div>

               </div>


        </div>
    </div>
</div>




</body>
<script>
    document.getElementById("error_msg").innerHTML=document.getElementById("errorMessage").value;
</script>
</html>
