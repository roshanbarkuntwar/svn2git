<%-- 
    Document   : signup
    Created on : Jan 29, 2019, 12:59:38 PM
    Author     : neha.bisen
--%>

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

        <script src="resources/js/global/template.js?r=<%=Math.random()%>"></script>
        <script type="text/javascript" src="resources/js/global/supersized.3.2.7.min.js"></script>
        <script type="text/javascript" src="resources/js/global/supersized.shutter.min.js"></script>

        <link href="resources/css/global/main-9bf9ed18.css" media="screen" rel="stylesheet" type="text/css"/>
        <script src="resources/js/global/main-9198a61e.js" type="text/javascript"></script>
        <script src="resources/js/global/homepage-d3768b32.js" type="text/javascript"></script>
        <!--Fontawesome CSS-->
        <link rel="stylesheet" href="resources/font-awesome/css/font-awesome.min.css">
        <script type="text/javascript" src="resources/js/global/jquery-3.2.1.min.js"></script>

        <!--Bootstarp CSS-->
        <link href="resources/css/global/bootstrap/bootstrap.min.css" rel="stylesheet">  
        <link href="resources/css/login-new/login-new.css" rel="stylesheet">  

        <link rel="stylesheet" href="resources/css/main/main.css" type="text/css">

        <script type="text/javascript">
            $('form').submit(function () {
                $(this).children('input[type=submit]').prop('disabled', true);
            });
        </script>
        <sx:head/>
        <script type="text/javascript" src="resources/js/global/scrolltopimg.js"></script>
        <script type="text/javascript" src="resources/js/global/signUp.js"></script>
        <script type = "text/javascript" src="resources/js/lhs/lhsGlobal.js?r=<%Math.random();%>" ></script>
        <script type="text/javascript" src="resources/js/lhs/lhsGlobalValidation.js?r=<%= Math.random()%>"></script>
    </head>
    <body class="d-flex align-items-center justify-content-center h-100">

        <%  String contextPath = request.getContextPath();%>
        <input type="hidden" id="contextPath" value="<%=contextPath%>"/>
        <input type="hidden" id="globalcontextpath" value="<%=contextPath%>"/>

        <div class="login-wrap bg-white mx-auto">
            <div class="row m-0">
                <div class="col-md-6 d-none d-md-block pl-0">
                    <div class="login-vector h-100 d-flex justify-content-cnter align-items-center p-4 position-relative">
                        <img src="resources/images/login/login-vector.png " class="img-fluid">
                        <%--<p class='position-absolute browser-compatibility'>This site is best viewed in latest version of <s:url id="dwnldURL" action="dwnldChromeSetup"></s:url>   <s:a href="%{dwnldURL}" target="_blank" class="font-weight-bold text-primary">Google Chrome</s:a></p>--%>
                    </div>
                </div>
                <div class="col-md-6 ">
                    <div class="login-form px-md-4 pt-3 pb-0 px-1">
                        <div class="logo text-center">
                            <a href="http://taxcpc.com/" target="_blank">  <img src="resources/images/global/TAXCPC-logo.png" class="company-logo mb-3"  alt="company-logo"></a>
                        </div>

                        
                            <input type="hidden"  id="global_msg_value" value="<s:property value="%{#session.GLOBALMSGVALUE}"/>"/>
                            <input type="hidden"  id="global_msg_type" value="<s:property value="%{#session.GLOBALMSGTYPE}"/>"/>
                            <%  
//     
                                  session.removeAttribute("GLOBALMSGVALUE");
                                  session.removeAttribute("GLOBALMSGTYPE");
  
                            %>
                        <%@include file="/WEB-INF/view/jsp/global/alertBoxes.jsp" %>
                        <form name="signupfrm" id="signupfrm" action="signup?register=true" method="post" autocomplete="off">      
                            <h2 class="font-weight-bold mb-3">Create a New Account</h2>

                            <input type="hidden" name="action" value="submitUserDetails">
                            <div>
                                <div class="form-group mb-2 " id="tannoDiv">
                                    <label for="Tanno" class="mb-0">TAN No. <span class="text-danger font-weight-bold">*</span> </label>  <br />
                                    <input class="form-control rounded-0 px-0 py-1" id="Tanno" name="clientMast.tanno" type="text" onblur="validateTAN(this);" style="text-transform: uppercase;" maxlength="10"  onkeypress=" return lhsValidateAlphaAndNumber(event);" required placeholder="Enter TAN No."/>
                                    <span id="tanErrorSpan" class="text-danger " style="display:none"></span>
                                </div>

                                <div class="form-group mb-2 " id="userNameDiv">
                                    <label for="accName" class="mb-0">Account Name <span class="text-danger font-weight-bold">*</span> </label>  <br />
                                    <input class="form-control rounded-0 px-0 py-1" id="userName" name="clientMast.client_name" type="text" required placeholder="Enter Account Name" onkeypress=" return lhsValidateAlphabet(event);"/>
                                    <span id="userNameErrorSpan" class="text-danger " style="display:none"></span>
                                </div>
                                <div class="row">
                                    <div class="col-md-8">
                                        <div class="form-group mb-2">
                                            <label for=UserName class="mb-0">Email Id <span class="text-danger font-weight-bold">*</span> </label><br />
                                            <input class="form-control rounded-0 px-0 py-1" class="conLowercase"  id="UserName" name="clientMast.login_id" type="text" style="text-transform: lowercase" required placeholder="Your Email Id Is your Login Id" onblur="validateEmail(this);"/>
                                            <span id="emailErrorSpan" class="text-danger " style="display:none"></span>
                                        </div>
                                    </div>
                                    <div class="col-md-4 mt-3">                                        
                                        <button class="btn btn-primary btn-block otp-btns" onclick="sendOTP(event);"><i class="fa fa-upload mr-2" aria-hidden="true"></i> Send OTP</button>
                                    </div>
                                </div>
                                    <div class="row">
                                        <div class="col-md-8">
                                            <div class="form-group mb-2">
                                                <label for="mobile" class="mb-0">OTP <span class="text-danger font-weight-bold">*</span> </label> <br />
                                                <input class="form-control rounded-0 px-0 py-1 otp-field" disabled id="mobile" name="clientMast.appr_verify_code" onkeypress="return isNumber(event);" type="text" required placeholder="Enter OTP" onblur="ckhMobileLength(this);" maxlength="10"/>
                                                <span id="mobileErrorSpan" class="text-danger " style="display:none"></span>
                                            </div>
                                        </div>
                                        <div class="col-md-4 mt-3">
                                            <button class="btn btn-danger btn-block otp-btns otp-field otp-verify" disabled style="display: block;" onclick="verifyOTP(event);">Verify OTP</button>
                                            <button class="btn btn-success btn-block otp-btns otp-field otp-verified" style="display: none;"><i class="fa fa-check mr-2"></i>OTP Verified</button>
                                        </div>
                                    </div>
                                
                                    <!--                                    <div class="form-group mb-2">
                                                                            <label for="mobile" class="mb-0">Mobile <span class="text-danger font-weight-bold">*</span> </label> <br />
                                                                            <input class="form-control rounded-0 px-0 py-1" id="mobile" name="clientMast.mobileno" onkeypress="return isNumber(event);" type="text" required placeholder="Enter Mobile" onblur="ckhMobileLength(this);" maxlength="10"/>
                                                                            <span id="mobileErrorSpan" class="text-danger " style="display:none"></span>
                                                                        </div>-->
                                    

                                    <div class="form-group mb-2">
                                        <label for="password" class="mb-0">Password <span class="text-danger font-weight-bold">*</span> </label> <br />
                                        <input class="form-control rounded-0 px-0 py-1 reg-user-new" disabled id="password" name="clientMast.login_pwd"   type="password" onkeypress="return AvoidSpace(event);" required placeholder="Enter password"/>
                                        <span id="pwdErrorSpan" class="text-danger " style="display:none"></span>
                                    </div>

                                    <div class="form-group mb-2">
                                        <label for="confirmPassword" class="mb-0">Confirm Password <span class="text-danger font-weight-bold">*</span> </label> <br />
                                        <input class="form-control rounded-0 px-0 py-1 reg-user-new" disabled id="confirmPassword" name="confrm_pwd" type="password"  onblur="validatePassword();" onkeypress="return AvoidSpace(event);" required placeholder="Enter Confirm Password"/>
                                        <span id="confrmPwdErrorSpan" class="text-danger " style="display:none"></span>
                                    </div>
                                
                            </div>
                            <div class="form-group mt-2">
                                <button type="button" class="btn btn-primary btn-block rounded-0 login-btn border-0 p-2 font-weight-bold reg-user-new" disabled onclick="registerNewUser();">Create My Account </button>
                            </div>
                        </form>  
                        <p>Already have an Account? <a href="login" >
                                <button class='bg-transparent border-0 font-weight-bold sign-up-btn'>Login</button>
                            </a>
                        </p>
                    </div>

                </div>
            </div>
        </div>

        <footer class="position-fixed d-flex justify-content-between w-100 copyright-issue text-white px-md-4">
            <div class="text-dark"> Ⓒ  2019   <a href="http://taxcpc.com/" target="_blank" class="font-weight-bold">TaxCPC</a>  All Rights Reserved</div>

            <sx:div cssClass="site-updation text-dark" href="getBuildVersion" delay="10" id="getBuildVersion" showLoadingText="true" loadingText="Loading..."></sx:div>
        </footer>
        <div class="position-fixed taxcpc-process-indicator-outer " id="global-simple-process-indicator" style="display:none">
                    <div class="taxcpc-process-indicator position-absolute text-center">
                        <img class="pulse2" src="resources/images/global/taxcpcProcessIndicator.png">
                        <div class="spinner">
                            <div class="bounce1"></div>
                            <div class="bounce2"></div>
                            <div class="bounce3"></div>
                        </div>
                    </div>
                </div>


        <script type="text/javascript" src="resources/js/global/jquery-3.6.1.min.js"></script>
        <script src="resources/js/global/popper.min.js"></script>
        <script src="resources/js/global/bootstrap.min.js"></script>
        <script type="text/javascript" src="resources/js/global/scrolltopimg.js"></script>


    </body>
</html>
