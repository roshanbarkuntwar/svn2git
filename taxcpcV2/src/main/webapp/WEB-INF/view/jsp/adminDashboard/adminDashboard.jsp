<%-- 
    Document   : adminDashboard
    Created on : Apr 22, 2022, 3:20:11 PM
    Author     : akash.meshram
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="resources/css/dashboard/dashboard.css" type="text/css">
<script type="text/javascript" src="resources/js/admin/admin.js"></script>

<div class="heading-marquee">
    <div class="container-fluid">
        <div>
            <div class="row">
                <div class="col-md-12 ">
                    <div class="container">

                        <h5 class="mb-0"><marquee scrollamount="4"><span class="text-primary"><i class="fa fa-star"></i> Welcome to TaxCPC  <i class="fa fa-star"></i> 
                                    <s:if test="%{dashboardHeaderMessage != null}">
                                        <span style="color:#000"><s:property value="dashboardHeaderMessage" /></span>  
                                    </s:if>
                                </span></marquee></h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container-fluid ">
    <div class="dashboard-container ">
        <div class="row dashboard-card-row">
            <div class="col-md-3">
                <div class="dashboard-card" onclick="window.location.href = 'parameterSettingAction';">
                    <div class="dashboard-card-header text-center">

                        <div class="dashboard-icon-wrap">
                            <img src="resources/images/dashboard-new/invoice.png">


                        </div> 

                    </div>
                    <h3 class="dashboard-card-title text-center"> 

                        Application Parameter Setting

                    </h3>
                </div>

            </div>

            <div class="col-md-3">
                <div class="dashboard-card" onclick="window.location.href = 'dashboardSetting';">
                    <div class="dashboard-card-header text-center">

                        <div class="dashboard-icon-wrap">
                            <img src="resources/images/dashboard-new/invoice.png">


                        </div> 

                    </div>
                    <h3 class="dashboard-card-title text-center"> 

                        Dashboard Menu Setting

                    </h3>
                </div>

            </div>



            <div class="col-md-3">
                <div class="dashboard-card" onclick="window.location.href = 'misReportSettingAction';">
                    <div class="dashboard-card-header text-center">

                        <div class="dashboard-icon-wrap">
                            <img src="resources/images/dashboard-new/invoice.png">


                        </div> 

                    </div>
                    <h3 class="dashboard-card-title text-center"> 

                        MIS Report Setting

                    </h3>
                </div>

            </div>

        </div>
        <div class="row dashboard-card-row">
        </div>
    </div>
</div>