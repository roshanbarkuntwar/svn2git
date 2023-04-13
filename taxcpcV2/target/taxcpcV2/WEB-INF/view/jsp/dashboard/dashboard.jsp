<%-- 
    Document   : dashboardNew
    Created on : Jan 21, 2019, 12:54:28 PM
    Author     : neha.bisen
--%>
<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<link rel="stylesheet" href="resources/css/dashboard/dashboard.css" type="text/css">
<script src="resources/js/dashboard/dashboard.js?r=" type="text/javascript"></script>
<script type="text/javascript" src="resources/js/lhs/lhsAjax.js?r=<%=Math.random()%>"></script>
<!--Body Start Here-->
<div class="page-section">
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
<!--<a href="/taxcpcV2/DownloadBlobFile">Download Text File From Blob</a>  -->
        <s:if test="%{sessionResult != null}">
            <div class="text-center col-md-12" id="session_notification" >
                <div class="d-inline-block">
                    <s:div cssClass="%{#session.ERRORCLASS}">                                
                        <h5 class="d-inline-block" > <s:property value="sessionResult" /> </h5>
                    </s:div>
                </div>
            </div>
        </s:if>

        <div class="dashboard-container ">
            <s:set var="count" value="0"/>
            <s:if test="%{navList != null && navList.size() > 0}">
                <s:iterator begin="1" end="%{totalRows}" status="status">

                    <div class="row dashboard-card-row">

                        <s:iterator begin="1" end="4" status="status">

                            <s:if test="%{#count < navList.size()}">
                                <div class="col-md-3">
                                    <div class="dashboard-card" onclick="location.href = '<s:property value='%{navList.get(#count).getNavigation_link()}'/>';">
                                        <div class="dashboard-card-header text-center">

                                            <div class="dashboard-icon-wrap">
                                                <img src="resources/images/dashboard-new/invoice.png">


                                            </div> 

                                        </div>
                                        <h3 class="dashboard-card-title text-center"> <%--title="<s:property value="%{navList.get(#count).getNavigation_name()}"/>"--%>
                                            <a href="<s:property value="%{navList.get(#count).getNavigation_link()}"/>">
                                                <s:property value="%{navList.get(#count).getNavigation_name()}"/>
                                            </a>
                                        </h3>
                                    </div>
                                    <s:set var="count" value="%{#count+1}" />
                                </div>
                            </s:if>
                        </s:iterator>
                    </div>
              </s:iterator>

            </s:if>
            <s:else>

                <%
                    out.print(GlobalMessage.DASHBOARD_NO_RECORDS);
                %>

            </s:else>

            <s:if test="%{totalMenuCount > 16}">
                <div class="pagination-kpi">
                    <a class="prev-kpi" href="#" onclick="menuNavigation('prev');"><i class="fa fa-chevron-left" aria-hidden="true"></i>Previous</a>
                    <a class="prev-kpi" href="#" onclick="menuNavigation('next');">Next<i class="fa fa-chevron-right" aria-hidden="true"></i></a>
                </div>

                <form name="menuForm" id="menuForm" action="dashboard" method="post">                
                    <input type="hidden" id="navListSize" value="<s:property value="%{totalMenuCount}"/>">                
                    <input type="hidden" name="startIndex" id="startIndex" value="<s:property value="%{startIndex}"/>">
                </form>
            </s:if>
        </div>
    </div>
    <div class="important-notice d-flex">
        <p class="w-70"><strong>Warning :</strong></p>
        <p>
            This application is meant to be used by Authorized Individuals only.
            All activities on this application will be logged and monitored by TaxCPC Team.
            Any unauthorized access will be treated as violation of TaxCPC Internal Security Policy & Standards and will be penalized accordingly.
        </p>
    </div>
</div>
