<%-- 
    Document   : login
    Created on : Jan 22, 2019, 10:42:49 AM
    Author     : ayushi.jain
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

        <script type="text/javascript" src="resources/js/global/jquery-3.2.1.min.js"></script>
        <title>${initParam.BrowserPageTitleText}</title>
        <link rel="icon" href="${initParam.BrowserPageTitleImageURL}" type="image/x-icon"/>
        <script src="resources/js/global/jquery.easing.1.3.js"></script>
        <script src="resources/js/global/jquery.bxslider.js"></script>

        <link href="resources/css/global/main-9bf9ed18.css" media="screen" rel="stylesheet" type="text/css"/>
        <script src="resources/js/global/main-9198a61e.js" type="text/javascript"></script>
        <script src="resources/js/global/homepage-d3768b32.js" type="text/javascript"></script>
        <!--Fontawesome CSS-->
        <link rel="stylesheet" href="resources/font-awesome/css/font-awesome.min.css">

        <script src="resources/js/global/template.js?r=<%=Math.random()%>"></script>
        <script type="text/javascript" src="resources/js/global/supersized.3.2.7.min.js"></script>
        <script type="text/javascript" src="resources/js/global/supersized.shutter.min.js"></script>

        <!--Bootstarp CSS-->
        <link href="resources/css/global/bootstrap/bootstrap.min.css" rel="stylesheet">  
        <link href="resources/css/login-new/login-new.css" rel="stylesheet">  
        <link rel="stylesheet" href="resources/css/main/main.css" type="text/css">

        <script type = "text/javascript" src="resources/js/lhs/lhsGlobal.js?r=<%Math.random();%>" ></script>
        <script type="text/javascript" src="resources/js/global/baseLayout.js?r=<%Math.random();%>"></script>
        <script type="text/javascript" src="resources/js/global/signUp.js?r=<%Math.random();%>"></script>

        <s:set var="gSignupFtrFlag" value="%{#session.get('GSIGNUPFTR')}"/>
        <s:set var="tSignupFtrFlag" value="%{#session.get('TSIGNUPFTR')}"/>
        <s:set var="forgetPasswordFtrFlag" value="%{#session.get('FORGETPASSFTR')}"/>
        <!--Google SignIn Integration-->
        <s:if test="%{#gSignupFtrFlag != null && #gSignupFtrFlag.equalsIgnoreCase('T')}">
            <script src="https://apis.google.com/js/client:platform.js?onload=startApp" async defer></script>
            <meta name="google-signin-client_id" content="254580028675-kdrlu0a6v6ngl6lft84qnd1i51udussv.apps.googleusercontent.com">
        </s:if>
        <!--Google SignIn Integration end-->

        <script type="text/javascript">
            $('form').submit(function () {
                $(this).children('input[type=submit]').prop('disabled', true);
            });
        </script>
        <sx:head/>
        <script type="text/javascript" src="resources/js/global/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="resources/js/global/scrolltopimg.js"></script>
        <style>
            #customBtn {
                display: inline-block;
                background:#3065c5;
                color: #444;
                width: 100% !important;
                border-radius: 5px;
                border: 1px solid #b8b1b1;
                box-shadow: 1px 1px 1px grey;
                white-space: nowrap;
                height: 30px;
            }
            #customBtn:hover {
                cursor: pointer;
            }
            /*            span.label {
                            font-family: serif;
                            font-weight: normal;
                        }*/
            span.icon {
                background: url('/identity/sign-in/g-normal.png') transparent 5px 50% no-repeat;
                display: inline-block;
                background: #fff;
                float: left;
                width: 35px;
                height: 29px;
                padding: 0px;
            }
            span.buttonText {
                display: inline-block;
                vertical-align: middle;
                padding-top: 4px;
                font-size: 14px;
                font-weight: bold;
                /* Use the Roboto font that is loaded in the <head> */
                font-family: 'Roboto', sans-serif;
                color: #fff;
            }
            .abcRioButtonIcon {
                background: #fff;
            }
            .abcRioButtonContentWrapper {
                border: 1px solid #b8b1b1;
                background: #3065c5;
                display: none;
            }
            .abcRioButtonLightBlue{
                width: 100% !important;
                margin: auto;   
            }
            .login-btn{
                width: 100% !important;
                margin: auto; 
            }
            .abcRioButtonContents{
                font-size: 15px !important;
                color: #fff;
                line-height: 38px !important;
            }
            .or{
                text-align: center;
                margin-top: 10px;
                color: gray;
                margin-bottom: 10px;
            }
            .or-label{
                margin : 0 5px;
                font-size: 16px;
            }
        </style>
    </head>
    <body class="d-flex align-items-center justify-content-center h-100" onload="loginOnloadData();showPopupMessage();">
        <%  String contextPath = request.getContextPath();
         System.out.println("New Login Page Request Initiated...");
        %>
        <input type="hidden" id="contextPath" value="<%=contextPath%>"/>
        <input type="hidden" value="<%=contextPath%>" id="globalcontextpath"/>
        <div class="login-wrap bg-white">
            <div class="row m-0">
                <div class="col-md-6 d-none d-md-block pl-0">
                    <div class="login-vector h-100  p-4 position-relative">
                        <img src="resources/images/login/login-vector.png " class="img-fluid">
                        <p class='position-absolute browser-compatibility'>This site is best viewed in latest version of   <s:url id="dwnldURL" action="dwnldChromeSetup"></s:url>  <s:a href="%{dwnldURL}" target="_blank" class="font-weight-bold text-primary">Google Chrome</s:a></span></p>
                        </div>
                    </div>
                    <div class="col-md-6 ">
                        <div class="login-form px-md-4 pt-3 pb-0 px-1">
                            <div class="logo text-center">
                                <a href="#">
                                <%--<img src="<s:property value="%{loginPageLogo}"/>" id="loginPageLogo" class="company-logo mb-5" alt="taxcpc-logo"/>--%>
                                <img src="resources/images/global/TAXCPC-logo.png" id="loginPageLogo" class="company-logo mb-5" alt="taxcpc-logo"/>
                            </a>
                            <p class="text-center font-weight-bold mb-3" id="logoTagline" style="color:#2d62c1;"><s:property value="%{logoTagline}"/></p>
                            <!--taxcpc-->
                            <!--<a href="http://taxcpc.com/" target="_blank">  <img src="resources/images/global/TAXCPC-logo.png" class="company-logo mb-5"  alt="company-logo"></a>-->
                            <!--taxcpc-->

                            <!--ALLBANK-->
                            <!--                                <div class="allbank">
                                                                <a href="https://www.allahabadbank.in/" target="_blank">  <img src="resources/images/global/allahabad_logo.jpg" class="company-logo mb-2"  alt="company-logo"></a>
                                                                <p class="text-center font-weight-bold mb-3" style="color:#2d62c1;">(ALLBANK COLLECTION CENTER FOR PROCESSING OF TDS)</p>
                                                            </div>-->
                            <!--ALLBANK-->
                        </div>
                            
                            
                            <input type="hidden"  id="global_msg_value" value="<s:property value="%{#session.GLOBALMSGVALUE}"/>"/>
                            <input type="hidden"  id="global_msg_type" value="<s:property value="%{#session.GLOBALMSGTYPE}"/>"/>
                            <%  
//     
                                  session.removeAttribute("GLOBALMSGVALUE");
                                  session.removeAttribute("GLOBALMSGTYPE");
  
                            %>
                            <%@include file="/WEB-INF/view/jsp/global/alertBoxes.jsp" %>
                        <form name="form" id="form" action="login" method="post" autocomplete="off">
                            <input type="hidden" name="formLogin" value="true">
                            <h2 class="font-weight-bold mb-4">Login</h2>

                            <div class="form-group mb-4">

                                <label for=login_id class="mb-2">Login Id</label><br />
                                <input class="form-control rounded-0 px-0"  id="login_id" name="login_id" value="<s:property value="%{loginId}"/>" type="text" required placeholder="Enter Login Id" autofocus/>
                            </div>
                            <div class="form-group mb-4">

                                <label for="login_pwd" class="mb-2">Password</label> <br />
                                <input class="form-control rounded-0 px-0" id="login_pwd" name="login_pwd" value="<s:property value="%{password}"/>" type="password" required placeholder="Enter Password"/>
                            </div>
                            <s:if test="%{#forgetPasswordFtrFlag != null && #forgetPasswordFtrFlag.equalsIgnoreCase('T')}">  
                            <div class="form-group mb-4">
                                <div class="text-left">
                                    <a id="forgot-password" href="forgotPassword" tabindex="-1" class="font-weight-bold sign-up-btn">Forgot Password ?</a>
                                </div>
                            </div>
                            </s:if>
                            <div class="form-group mt-4 mb-2">
                                <button type="submit" class="btn btn-primary btn-block rounded-0 login-btn border-0 p-2 font-weight-bold"> Login </button>
                            </div>
                            <!--<GoogleSignIn Button>-->
                            <s:if test="%{#gSignupFtrFlag != null && #gSignupFtrFlag.equalsIgnoreCase('T')}">                                
                                <div class="or"><span>-----------------------------------</span> <span class="or-label">OR</span><span>-----------------------------------</span></div>
                                <div class="form-group  mb-3">
                                    <div id="gSignInWrapper">
                                        <div id="customBtn" class="customGPlusSignIn">
                                            <div style="width:20%;text-align: left;">
                                                <span class="icon">
                                                    <div style="width:35px;height:35px;     margin-top: 7px;text-align: center;" class="abcRioButtonSvgImageWithFallback abcRioButtonIconImage abcRioButtonIconImage18"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" width="18px" height="18px" viewBox="0 0 48 48" class="abcRioButtonSvg"><g><path fill="#EA4335" d="M24 9.5c3.54 0 6.71 1.22 9.21 3.6l6.85-6.85C35.9 2.38 30.47 0 24 0 14.62 0 6.51 5.38 2.56 13.22l7.98 6.19C12.43 13.72 17.74 9.5 24 9.5z"></path><path fill="#4285F4" d="M46.98 24.55c0-1.57-.15-3.09-.38-4.55H24v9.02h12.94c-.58 2.96-2.26 5.48-4.78 7.18l7.73 6c4.51-4.18 7.09-10.36 7.09-17.65z"></path><path fill="#FBBC05" d="M10.53 28.59c-.48-1.45-.76-2.99-.76-4.59s.27-3.14.76-4.59l-7.98-6.19C.92 16.46 0 20.12 0 24c0 3.88.92 7.54 2.56 10.78l7.97-6.19z"></path><path fill="#34A853" d="M24 48c6.48 0 11.93-2.13 15.89-5.81l-7.73-6c-2.15 1.45-4.92 2.3-8.16 2.3-6.26 0-11.57-4.22-13.47-9.91l-7.98 6.19C6.51 42.62 14.62 48 24 48z"></path><path fill="none" d="M0 0h48v48H0z"></path></g></svg></div>
                                                </span>
                                            </div>
                                            <div style="width:100%;text-align: center;">
                                                <span class="buttonText">Login with Google Account</span>
                                            </div>
                                        </div> 
                                    </div>
                                </div>
                            </s:if>
                            <!--</GoogleSignIn Button>-->
                        </form>  



                        <s:if test="%{(#gSignupFtrFlag != null && #gSignupFtrFlag.equalsIgnoreCase('T')) && (#tSignupFtrFlag != null && #tSignupFtrFlag.equalsIgnoreCase('T'))}">
                            <p id="signUpFtr" style="text-align: left;display: inline-block;">Don't have an Account Sign Up here &nbsp;
                            <div id="gSignInWrapper" style="display: inline-block;">
                                <div id="gSignupBtn" class="customGPlusSignIn" style="cursor: pointer;">
                                    <span class="">
                                        <div style="margin:0 5px" class="abcRioButtonSvgImageWithFallback abcRioButtonIconImage abcRioButtonIconImage18">
<!--                                            <svg version="1.1" xmlns="http://www.w3.org/2000/svg" width="18px" height="18px" viewBox="0 0 48 48" class="abcRioButtonSvg"><g><path fill="#EA4335" d="M24 9.5c3.54 0 6.71 1.22 9.21 3.6l6.85-6.85C35.9 2.38 30.47 0 24 0 14.62 0 6.51 5.38 2.56 13.22l7.98 6.19C12.43 13.72 17.74 9.5 24 9.5z"></path><path fill="#4285F4" d="M46.98 24.55c0-1.57-.15-3.09-.38-4.55H24v9.02h12.94c-.58 2.96-2.26 5.48-4.78 7.18l7.73 6c4.51-4.18 7.09-10.36 7.09-17.65z"></path><path fill="#FBBC05" d="M10.53 28.59c-.48-1.45-.76-2.99-.76-4.59s.27-3.14.76-4.59l-7.98-6.19C.92 16.46 0 20.12 0 24c0 3.88.92 7.54 2.56 10.78l7.97-6.19z"></path><path fill="#34A853" d="M24 48c6.48 0 11.93-2.13 15.89-5.81l-7.73-6c-2.15 1.45-4.92 2.3-8.16 2.3-6.26 0-11.57-4.22-13.47-9.91l-7.98 6.19C6.51 42.62 14.62 48 24 48z"></path><path fill="none" d="M0 0h48v48H0z"></path></g></svg>-->
                                        <img src="resources/images/google-signup.png" style="width:20px;"/>
                                        </div>
                                    </span>
                                </div> 
                            </div>
                            <span>or</span>
                            <a href="javascript:void(0);" style="cursor: pointer;margin: 0 5px;" onclick="callUrl('/signup');" title="Sign Up with your Email"> <img src="resources/images/sign-up.png" style="width:25px;"/></a>
                            </p>    
                        </s:if>
                        <s:elseif test="%{#gSignupFtrFlag != null && #gSignupFtrFlag.equalsIgnoreCase('T')}">
                            <p id="signUpFtr" style="text-align: left;display: inline-block;">Don't have an Account Sign Up here &nbsp;
                            <div id="gSignInWrapper" style="display: inline-block;">
                                <div id="gSignupBtn" class="customGPlusSignIn" style="cursor: pointer;">
                                    <span class="">
                                        <div style="margin:0 5px" class="abcRioButtonSvgImageWithFallback abcRioButtonIconImage abcRioButtonIconImage18">
<!--                                            <svg version="1.1" xmlns="http://www.w3.org/2000/svg" width="18px" height="18px" viewBox="0 0 48 48" class="abcRioButtonSvg"><g><path fill="#EA4335" d="M24 9.5c3.54 0 6.71 1.22 9.21 3.6l6.85-6.85C35.9 2.38 30.47 0 24 0 14.62 0 6.51 5.38 2.56 13.22l7.98 6.19C12.43 13.72 17.74 9.5 24 9.5z"></path><path fill="#4285F4" d="M46.98 24.55c0-1.57-.15-3.09-.38-4.55H24v9.02h12.94c-.58 2.96-2.26 5.48-4.78 7.18l7.73 6c4.51-4.18 7.09-10.36 7.09-17.65z"></path><path fill="#FBBC05" d="M10.53 28.59c-.48-1.45-.76-2.99-.76-4.59s.27-3.14.76-4.59l-7.98-6.19C.92 16.46 0 20.12 0 24c0 3.88.92 7.54 2.56 10.78l7.97-6.19z"></path><path fill="#34A853" d="M24 48c6.48 0 11.93-2.13 15.89-5.81l-7.73-6c-2.15 1.45-4.92 2.3-8.16 2.3-6.26 0-11.57-4.22-13.47-9.91l-7.98 6.19C6.51 42.62 14.62 48 24 48z"></path><path fill="none" d="M0 0h48v48H0z"></path></g></svg>-->
                                                <img src="resources/images/google-signup.png" style="width:20px;"/>
                                        </div>
                                    </span>
                                    <!--<span class="buttonText">Continue with Google Account</span>-->
                                </div> 
                            </div>
                            </p>   
                        </s:elseif>
                        <s:elseif test="%{#tSignupFtrFlag != null && #tSignupFtrFlag.equalsIgnoreCase('T')}">
                            <p id="signUpFtr" style="text-align: left;display: inline-block;">Don't have an Account? Sign Up here
                                <a href="javascript:void(0);" style="cursor: pointer;margin: 0 5px;" onclick="callUrl('/signup');" title="Sign Up with your Email"> <img src="resources/images/sign-up.png" style="width:20px;"/></a>
                            </p> 
                        </s:elseif>




                    </div>

                </div>
            </div>
        </div>
        <footer class="position-fixed w-100 copyright-issue text-white px-md-4">
            <div class="text-dark float-left"> Ⓒ <%
                DateTimeUtil dateUtl = new DateTimeUtil();
                String currentYear = dateUtl.get_sysdate("yyyy");
                out.print(currentYear);
                %>   <a href="http://taxcpc.com/" target="_blank" class="font-weight-bold">TaxCPC</a>  All Rights Reserved</div>
                <sx:div cssClass="site-updation text-dark float-right" href="getBuildVersion" delay="10" id="getBuildVersion" showLoadingText="true" loadingText="Loading..."></sx:div>
            </footer>
        <s:hidden value="%{#session.get('SESSIONDBUSERNAME')}" id="currentDbUser"/>

        <script type="text/javascript" src="resources/js/global/jquery-3.6.1.min.js"></script>
        <script src="resources/js/global/popper.min.js"></script>
        <script src="resources/js/global/bootstrap.min.js"></script>
        <script type="text/javascript" src="resources/js/global/scrolltopimg.js"></script>
    </body>
</html>
