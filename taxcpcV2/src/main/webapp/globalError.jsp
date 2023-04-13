<%-- 
    Document   : globalError
    Created on : Jan 31, 2019, 10:31:48 AM
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
        <link href="resources/css/globalError/globalError.css" rel="stylesheet">  

        <script type="text/javascript">
            $('form').submit(function () {
                $(this).children('input[type=submit]').prop('disabled', true);
            });
        </script>
        <sx:head/>
        <script type="text/javascript" src="resources/js/global/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="resources/js/global/scrolltopimg.js"></script>

    </head>
    <body class="d-flex align-items-center justify-content-center h-100">




        <div class="login-wrap bg-white mx-auto position-relative">
            <div class="row m-0 h-100">
                <div class="col-md-7 d-none d-md-block pl-0">
                    <div class="login-vector h-100 d-flex justify-content-cnter align-items-center p-4 position-relative h-100">
                        <img src="resources/images/global/global-error.png " class="img-fluid">
                    </div>
                </div>
                <div class="col-md-5 h-100 text-center ">
                    <img src="resources/images/global/TAXCPC-logo.png" class="mt-4">
                    <div class="login-form px-md-4 pt-3 pb-0 px-1  h-100 d-flex justify-content-center align-items-center">
                     
                        <form name="form" id="form" action="logout" method="post" autocomplete="off">      
                            <h2 class="font-weight-bold mb-5">Sorry! There is some problem. Please contact to support person.</h2>



                            <div class="form-group mt-4">
                                <button type="submit" class="btn btn-primary btn-block rounded-0 login-btn border-0 p-2 font-weight-bold">Go To Home </button>
                            </div>
                            <div class="form-group mt-2">
                                <button type="submit" class="btn btn-primary btn-block rounded-0 login-btn border-0 p-2 font-weight-bold">Logout </button>
                            </div>
                            

                        </form>  

                    </div>

                </div>
            </div>
        </div>

        <footer class="position-fixed d-flex justify-content-between w-100 copyright-issue text-white px-md-4">
            <div class="text-dark"> Ⓒ  2019   <a href="http://taxcpc.com/" target="_blank" class="font-weight-bold">TaxCPC</a>  All Rights Reserved</div>

            <sx:div cssClass="site-updation text-dark" href="getBuildVersion" delay="10" id="getBuildVersion" showLoadingText="true" loadingText="Loading..."></sx:div>
        </footer>



        <script type="text/javascript" src="resources/js/global/jquery-3.6.1.min.js"></script>
        <script src="resources/js/global/popper.min.js"></script>
        <script src="resources/js/global/bootstrap.min.js"></script>
        <script type="text/javascript" src="resources/js/global/scrolltopimg.js"></script>


    </body>
</html>
