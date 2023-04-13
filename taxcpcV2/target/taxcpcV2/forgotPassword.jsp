<%-- 
    Document   : forgotPassword
    Created on : Sep 18, 2019, 2:49:23 PM
    Author     : gaurav.khanzode
--%>

<%@page import="globalUtilities.DateTimeUtil"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>

<!DOCTYPE html>
<html class="h-100">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${initParam.BrowserPageTitleText}</title>
        <link rel="icon" href="${initParam.BrowserPageTitleImageURL}" type="images"/>
        <script src="resources/js/global/jquery.easing.1.3.js"></script>
        <script src="resources/js/global/jquery.bxslider.js"></script>

        <!--Fontawesome CSS-->
        <link rel="stylesheet" href="resources/font-awesome/css/font-awesome.min.css">
        <script type="text/javascript" src="resources/js/global/jquery-3.2.1.min.js"></script>
        
        <script src="resources/js/global/template.js?r=<%=Math.random()%>"></script>
        <script type="text/javascript" src="resources/js/global/supersized.3.2.7.min.js"></script>
        <script type="text/javascript" src="resources/js/global/supersized.shutter.min.js"></script>

        <link href="resources/css/global/main-9bf9ed18.css" media="screen" rel="stylesheet" type="text/css"/>
        <script src="resources/js/global/main-9198a61e.js" type="text/javascript"></script>
        <script src="resources/js/global/homepage-d3768b32.js" type="text/javascript"></script>

        <!--Bootstarp CSS-->
        <link href="resources/css/global/bootstrap/bootstrap.min.css" rel="stylesheet">  
        <link href="resources/css/login-new/login-new.css" rel="stylesheet">

        <!--Main CSS-->
        <link rel="stylesheet" href="resources/css/main/main.css" type="text/css">

        <script type="text/javascript">
            $('form').submit(function () {
                $(this).children('input[type=submit]').prop('disabled', true);
            });
        </script>
        <sx:head/>
        <script type="text/javascript" src="resources/js/global/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="resources/js/global/scrolltopimg.js"></script>
        <script type = "text/javascript" src="resources/js/lhs/lhsGlobal.js?r=<%Math.random();%>" ></script>
       
    </head>
    <body class="d-flex align-items-center justify-content-center h-100" onload="showPopupMessage();">
        <div class="login-wrap bg-white mx-auto">
            <div class="row m-0">
                <div class="col-md-6 d-none d-md-block pl-0">
                    <div class="login-vector h-100 d-flex justify-content-cnter align-items-center p-4 position-relative">
                        <img src="resources/images/login/login-vector.png " class="img-fluid">
                        <%--<p class='position-absolute browser-compatibility'>This site is best viewed in latest version of    <s:a href="%{dwnldURL}" target="_blank" class="font-weight-bold text-primary">Google Chrome</s:a></p>--%>
                        </div>
                    </div>
                    <div class="col-md-6 ">
                        <div class="login-form px-md-4 pt-3 pb-0 px-1">
                            <div class="logo text-center">
                                <a href="http://taxcpc.com/" target="_blank">  <img src="resources/images/global/TAXCPC-logo.png" class="company-logo mb-5"  alt="company-logo"></a>
                            </div>
                       
                            
                         <input type="hidden"  id="global_msg_value" value="<s:property value="%{#session.GLOBALMSGVALUE}"/>"/>
                         <input type="hidden"  id="global_msg_type" value="<s:property value="%{#session.GLOBALMSGTYPE}"/>"/>
                            <%  
// 
                                  session.removeAttribute("GLOBALMSGVALUE");
                                  session.removeAttribute("GLOBALMSGTYPE");
  
                            %>
                            
                         <%@include file="/WEB-INF/view/jsp/global/alertBoxes.jsp" %>
                         <s:form name="forgotPasswordForm" id="forgotPasswordForm" action="forgotPassword?submit=true" method="POST" autocomplete="off">      
                            <h2 class="font-weight-bold mb-4">Forgot Password</h2>
                            <div class="form-group mb-4">
                                <label for=UserName class="mb-2">Enter Registered E-mail Id <span class="text-danger font-weight-bold">*</span></label><br />
                                <input class="form-control rounded-0 px-0" id="email" name="email" required="required" type="text" placeholder="Enter Registered E-mail Id"/>
                            </div>

                            <div class="form-group mb-4">
                                <div class="text-left" >
                                    <a id="forgot-password" href="login" tabindex="-1" class="sign-up-btn font-weight-bold">Login</a>
                                </div>
                            </div>
                            <div class="form-group mt-4">
                                <button type="submit" class="btn btn-primary btn-block rounded-0 login-btn border-0 p-2 font-weight-bold"> Submit </button>
                            </div>
                        </s:form>  
                        <!--<p>Don't have an Account? <a href="signup"><button class='bg-transparent border-0 font-weight-bold sign-up-btn'>Sign Up</button></a></p>-->
                    </div>
                </div>
            </div>
        </div>
        <footer class="position-fixed d-flex justify-content-between w-100 copyright-issue text-white px-md-4">
            <div class="text-dark"> Ⓒ  <%
                DateTimeUtil dateUtl = new DateTimeUtil();
                String currentYear = dateUtl.get_sysdate("yyyy");
                out.print(currentYear);
                %> <a href="http://taxcpc.com/" target="_blank" class="font-weight-bold">TaxCPC</a> All Rights Reserved</div>
            <sx:div cssClass="site-updation text-dark" href="getBuildVersion" delay="10" id="getBuildVersion" showLoadingText="true" loadingText="Loading..."></sx:div>
        </footer>
        <script type="text/javascript" src="resources/js/global/jquery-3.6.1.min.js"></script>
        <script src="resources/js/global/popper.min.js"></script>
        <script src="resources/js/global/bootstrap.min.js"></script>
        <script type="text/javascript" src="resources/js/global/scrolltopimg.js"></script>
    </body>
</html>
