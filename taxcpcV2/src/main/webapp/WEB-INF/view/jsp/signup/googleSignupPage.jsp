<%-- 
    Document   : googleSignupPage
    Created on : Jul 4, 2020, 11:16:30 AM
    Author     : gaurav.khanzode
--%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <title>${initParam.BrowserPageTitleText} Signup Page</title>
        <link rel="icon" href="${initParam.BrowserPageTitleImageURL}" type="image/x-icon"/>

        <!--<meta tags>-->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--</meta tags>-->

        <!--<Styllesheets>-->
        <!--Fontawesome CSS-->
        <link rel="stylesheet" href="resources/font-awesome/css/font-awesome.min.css">
        <!--Bootstrap CSS-->
        <link href="resources/css/global/bootstrap/bootstrap.min.css" rel="stylesheet">  
        <!--Project CSS-->
        <link href="resources/css/login-new/login-new.css" rel="stylesheet">  
        <link rel="stylesheet" href="resources/css/main/main.css" type="text/css">
        <!--</Styllesheets>-->

        <!--<Javascript resources>-->
        <script type="text/javascript" src="resources/js/global/jquery-3.6.1.min.js?r=<%=Math.random()%>"></script>
        <script type = "text/javascript" src="resources/js/lhs/lhsGlobal.js?r=<%=Math.random()%>" ></script>
        <script type = "text/javascript" src="resources/js/global/bootstrap.min.js?r=<%=Math.random()%>""></script>
        <script type="text/javascript" src="resources/js/global/signUp.js?r=<%=Math.random()%>"></script>
        <!--</Javascript resources>-->

        <!--<Google SignIn Integration>-->
        <script src="https://apis.google.com/js/client:platform.js?onload=startApp" async defer></script>
        <meta name="google-signin-client_id" content="254580028675-kdrlu0a6v6ngl6lft84qnd1i51udussv.apps.googleusercontent.com">
        <!--</Google SignIn Integration>-->

        <!--<CSS code>-->
        <style type="text/css">
            .or{
                text-align: center;
                margin-top: 10px;
                color: gray;
            }
            .or-label{
                margin : 0 5px;
                font-size: 16px;                
            }
            #customBtn {
                display: inline-block;
                background:#3065c5;
                color: #444;
                width: 100% !important;
                border-radius: 5px;
                border: 1px solid #b8b1b1;
                box-shadow: 1px 1px 1px grey;
                white-space: nowrap;
            }
            #customBtn:hover {
                cursor: pointer;
            }
            span.icon {
                background: url('/identity/sign-in/g-normal.png') transparent 5px 50% no-repeat;
                display: inline-block;
                background: #fff;
                float: left;
                width: 35px;
                height: 35px;
                padding: 0px;
            }
            span.buttonText {
                display: inline-block;
                vertical-align: middle;
                padding-left: 42px;
                margin-top: 7px;
                padding-right: 42px;
                font-size: 14px;
                font-weight: bold;
                /* Use the Roboto font that is loaded in the <head> */
                font-family: 'Roboto', sans-serif;
                color: #fff;
            }
        </style>
        <!--</CSS code>-->
    </head>
    <body class="d-flex align-items-center justify-content-center h-100">
        <%@include file="/WEB-INF/view/jsp/global/alertBoxes.jsp" %>
        <!--<hidden fields>-->
        <%String contextPath = request.getContextPath();%>
        <input type="hidden" id="globalcontextpath" value="<%=contextPath%>"/>
        <!--</hidden fields>-->

        <div class="login-wrap bg-white" style="margin-top: 10% !important;">
            <div class="row m-0">
                <div class="col-md-6 d-none d-md-block pl-0">
                    <div class="login-vector h-100  p-4 position-relative">
                        <img src="resources/images/login/login-vector.png " class="img-fluid">
                        <p class='position-absolute browser-compatibility'>This site is best viewed in latest version of   <s:url id="dwnldURL" action="dwnldChromeSetup"></s:url><s:a href="%{dwnldURL}" target="_blank" class="font-weight-bold text-primary">Google Chrome</s:a></span></p>
                        </div>

                    </div>
                    <div class="col-md-6 text-center" style="margin-top: 10% !important;">
                        <div class="login-form px-md-4 pt-3 pb-0 px-1">
                            <div id="gSignInWrapper">
                                <div id="customBtn" class="customGPlusSignIn">
                                    <span class="icon">
                                        <div style="width:35px;height:35px;     margin-top: 7px;" class="abcRioButtonSvgImageWithFallback abcRioButtonIconImage abcRioButtonIconImage18"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" width="18px" height="18px" viewBox="0 0 48 48" class="abcRioButtonSvg"><g><path fill="#EA4335" d="M24 9.5c3.54 0 6.71 1.22 9.21 3.6l6.85-6.85C35.9 2.38 30.47 0 24 0 14.62 0 6.51 5.38 2.56 13.22l7.98 6.19C12.43 13.72 17.74 9.5 24 9.5z"></path><path fill="#4285F4" d="M46.98 24.55c0-1.57-.15-3.09-.38-4.55H24v9.02h12.94c-.58 2.96-2.26 5.48-4.78 7.18l7.73 6c4.51-4.18 7.09-10.36 7.09-17.65z"></path><path fill="#FBBC05" d="M10.53 28.59c-.48-1.45-.76-2.99-.76-4.59s.27-3.14.76-4.59l-7.98-6.19C.92 16.46 0 20.12 0 24c0 3.88.92 7.54 2.56 10.78l7.97-6.19z"></path><path fill="#34A853" d="M24 48c6.48 0 11.93-2.13 15.89-5.81l-7.73-6c-2.15 1.45-4.92 2.3-8.16 2.3-6.26 0-11.57-4.22-13.47-9.91l-7.98 6.19C6.51 42.62 14.62 48 24 48z"></path><path fill="none" d="M0 0h48v48H0z"></path></g></svg></div>
                                    </span>
                                    <span class="buttonText">Continue with Google Account</span>
                                </div> 
                            </div>
                        </div>
                        <div class="or"><span>----------</span> <span class="or-label">OR</span><span>----------</span></div>
                        <div class="login-form px-md-4 pt-3 pb-0 px-1">
                            <div class="text-center">
                                <input type="button" class="btn btn-primary btn-block rounded-0 login-btn border-0 p-2 font-weight-bold" 
                                       value="Continue To New Signup" name="basicSignup" onclick="callUrl('/signup');"/>
                            </div>
                        </div>
                        <div class="login-form px-md-4 pt-3 pb-0 px-1 text-left">
                            <p>Already have an Account? <a href="login" >
                                    <button class='bg-transparent border-0 font-weight-bold sign-up-btn'>Login</button>
                                </a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>

            <footer class="position-fixed w-100 copyright-issue text-white px-md-4">
                <div class="text-dark float-left"> Ⓒ  2019   <a href="http://taxcpc.com/" target="_blank" class="font-weight-bold">TaxCPC</a>  All Rights Reserved</div>
            <%--<sx:div cssClass="site-updation text-dark float-right" href="getBuildVersion" delay="10" id="getBuildVersion" showLoadingText="true" loadingText="Loading..."></sx:div>--%>
        </footer>
    </body>
</html>
