<%-- 
    Document   : baseLayout
    Created on : Jan 4, 2019, 10:57:21 AM
    Author     : sneha.parpelli
--%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${initParam.BrowserPageTitleText}</title>
        <link rel="icon" href="${initParam.BrowserPageTitleImageURL}" type="images"/>
        <!--Fontawesome CSS-->
        <link rel="stylesheet" href="resources/font-awesome/css/font-awesome.min.css">
        <script type="text/javascript" src="resources/js/global/jquery-3.2.1.min.js"></script>

        <!--Bootstarp CSS-->
        <link href="resources/css/global/bootstrap/bootstrap.min.css" rel="stylesheet">     
        <!--Base Layout CSS-->
        <link rel="stylesheet" href="resources/css/main/layout.css" type="text/css">

        <!--Global form,table,button CSS-->
        <link rel="stylesheet" href="resources/css/main/main.css" type="text/css">

        <!--Multiselect CSS-->
        <link href="resources/css/global/bootstrap-multiselect.css" rel="stylesheet">  
        <script src="resources/js/global/multiSelect-with-search.js"></script>
        <script src="resources/js/global/popper.min.js"></script>
        <script src="resources/js/global/bootstrap.min.js"></script>
        <script type="text/javascript" src="resources/js/global/scrolltopimg.js"></script>
        <script type="text/javascript" src="resources/js/global/baseLayout.js?r=<%=Math.random()%>"></script>
        <script src="resources/js/global/multiSelect-with-search.js"></script>
        <script type = "text/javascript" src="resources/js/lhs/lhsGlobal.js?r=<%=Math.random()%>" ></script>
        <!---TableSorter--->
        <script src="resources/js/global/tableSorter/jquery.tablesorter.js" type="text/javascript"></script>
        <script src="resources/js/global/jquery.table2excel.js?r=<%=Math.random()%>" type="text/javascript"></script>

    </head>
    <body>
        <s:set var="loginClient" value="%{#session.get('WORKINGUSER')}"/>
        <s:set var="loginuser" value="%{#session.get('LOGINUSER')}"/>
        <s:set var="activeTab" value="%{#session.get('ACTIVE_TAB')}"/>
        <s:set var="assessment" value="%{#session.get('ASSESSMENT')}"/>
        <s:set var="module" value="%{#session.get('MODULE')}"/>    
        <s:set var="dynaccyear" value="%{#session.get('DYNACCYEAR')}"/>
        <s:set var="chnageLogoFtrFlag" value="%{#session.get('CHANGELOGOFTR')}"/>
        <s:set var="adminUser" value="%{#session.get('ADMINUSER')}"/>
        <s:set var="errorProcessReport" value="%{#session.get('errorProcessReport')}"/>
        
        <s:hidden id="activeDashboardAssessmentBandFlag" name="activeDashboardAssessmentBandFlag"/>
       
        <div class="template-container">
            <!-- Top Header-->
            <div class="text-center logo-container  px-2"  <s:if test="%{#chnageLogoFtrFlag != null && #chnageLogoFtrFlag.equalsIgnoreCase('T')}">ondblclick="addIndvLogo();"</s:if>>
<!--                <img src="<s:property value="%{#session.get('SESSION_ENTITY_LOGO')}"/>" class="img-fluid allahabad_logo rounded" id="session_entity_logo">-->
             <img src="resources/images/global/IndiaBankLogo.png" class="img-fluid allahabad_logo rounded" id="session_entity_logo">


            </div>
            <div class="right-section__header">
                <!--Top Navigation-->
                <div class="client-section-header">
                    <div class="row">
                        <div class="col-md-4">
                            <s:if test="%{activeDashboardAssessmentBandFlag.equalsIgnoreCase('ActiveAssessmentBand')}">
                                <div class="change-deductor-section-outer d-inline-block position-relative  w-100">
                                    <div class="change-deductor-section text-truncate px-2 py-1" data-toggle="collapse" data-target="#deductorListDiv" onclick="getDeductorList();">
                                        <div class="text-ellipsis">
                                            <span><s:property escape="false" value="#session.DEDUCTOR"/></span>
                                        </div>
                                        <i class="fa fa-caret-down pt-1 ml-3" aria-hidden="true"></i>
                                    </div>
                                    <div class="deductor-list px-2 py-1 position-absolute w-100 collapse" id="deductorListDiv">
                                        <div class="card">
                                            <div class="card-header p-2">
                                                <div class="input-group w-90">
                                                    <input type="text" class="form-control" id="term">
                                                    <div class="input-group-append">
                                                        <button type="submit" class="btn btn-dark addon-btn" onclick="getDeductorList();" title="Search"><i class="fa fa-search"></i></button>
                                                        <button type="submit" class="btn btn-warning addon-btn text-white" onclick="resetList();" title="Reset"><i class="fa fa-eraser"></i></button>
                                                    </div>
                                                </div>
                                                <a class="text-white close-btn position-absolute" onclick="closeDeductorList();"><i title="Closed" class="fa fa-times" aria-hidden="true"></i></a>
                                            </div>
                                            <div class="card-body p-0">
                                                <ul class="list-group list-group-flush" id="deductorList">

                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </s:if>
                            <s:else>
                                <div class="change-deductor-section-outer d-inline-block position-relative  w-100 cursor-not-allowed" title="Only Excess on Dashboard Tab"> 
                                    <div class="change-deductor-section text-truncate px-2 py-1 add-ban-cursor-tan" data-toggle="collapse" data-target="#deductorListDiv" onclick="getDeductorList();">
                                      
                                        <div class="text-ellipsis">
                                            <span><s:property escape="false" value="#session.DEDUCTOR"/></span>
                                        </div>
                                        <i class="fa fa-caret-down pt-1 ml-3" aria-hidden="true"></i>
                                        
                                    </div>
                                    <div class="deductor-list px-2 py-1 position-absolute w-100 collapse" id="deductorListDiv">
                                        <div class="card">
                                            <div class="card-header p-2">
                                                <div class="input-group w-90">
                                                    <input type="text" class="form-control" id="term">
                                                    <div class="input-group-append">
                                                        <button type="submit" class="btn btn-dark addon-btn" onclick="getDeductorList();"><i class="fa fa-search"></i></button>
                                                        <button type="submit" class="btn btn-warning addon-btn text-white" onclick="resetList();"><i class="fa fa-eraser"></i></button>
                                                    </div>
                                                    
                                                </div>
                                                <a class="text-white close-btn position-absolute" onclick="closeDeductorList();"><i class="fa fa-times" aria-hidden="true"></i></a>
                                             
                                            </div>
                                            <div class="card-body p-0">
                                                <ul class="list-group list-group-flush" id="deductorList">

                                             </ul>
                                            </div>
                                            
                                        </div>
                                         
                                               
                                    </div>
                                              
                                        
                                </div>
                            </s:else>
                            <input  type="hidden" id="session_clientCode" value="<s:property value="%{#loginuser.client_code}"/>" />
                          
                        </div>
                            <div class="col-md-2 text-center">
                                <s:if test="%{#adminUser.equalsIgnoreCase('ADMINUSER')}">
<!--                                    <h5 style="padding: 4px 0;margin-bottom: 0">THIS IS ADMIN USER</h5>-->
                                    <h5 style="padding: 4px 0;margin-bottom: 0"><span class="text-primary">THIS IS ADMIN USER</span></h5>
                                 </s:if>
                            </div> 
                        <div class="col-md-6 text-right px-lg-3">

                            <div class="dropdown top-dropdown d-inline-block">
                                <span style="color: yellow;"><s:property value="%{#loginClient.entity_code}"/>|<s:property value="%{#loginClient.client_code}"/></span>
                                <img class="mr-1 align-sub" style="width:13px;" src="resources/images/global/time.png" ondblclick="getDBEntityMast();"> <span>Login Since : </span> <s:property value="%{#session.SESSIONTIMESTEMP}"/> | 
                                <i class="fa fa-user mr-1" title="Logged in user" data-toggle="modal" data-backdrop="false"  ondblclick="$('#remarkModal').modal('show')"> </i> 
                                <div class="text-ellipsis">
                                    <a id ="updateUserProfile" href="userProfileAction"  title="Edit Profile"><s:property value="%{#loginuser.user_name}"/></a>
                                </div>

                            </div>
                            <button type="button" class="btn btn-sm btn-primary logout-btn" title="Logout" onclick="exitUser();"> <i class="fa fa-sign-out"></i>  </button>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <!--Top Navigation-->

                <!--Main Navigation-->


                <div class="position-fixed taxcpc-process-indicator-outer taxcpc-process-indicator-outer--black " id="global-black-process-indicator" style="display:none">
                    <div class="taxcpc-process-indicator position-absolute text-center">
                        <img class="pulse2" src="resources/images/global/taxcpcProcessIndicator.png">
                        <div class="spinner">
                            <div class="bounce1"></div>
                            <div class="bounce2"></div>
                            <div class="bounce3"></div>
                        </div>
                    </div>
                </div>
                <div class="position-fixed taxcpc-process-indicator-outer " id="global-simple-process-indicator" style="display:none">
                    <div class="taxcpc-process-indicator position-absolute text-center">
                        <img class="pulse2" src="<s:property value="#session.CONTEXTPATH" />/resources/images/global/taxcpcProcessIndicator.png">
                        <div class="spinner">
                            <div class="bounce1"></div>
                            <div class="bounce2"></div>
                            <div class="bounce3"></div>
                        </div>
                    </div>
                </div>

                <div class="position-fixed taxcpc-process-indicator-outer global-text-process-indicator" id="global-text-process-indicator" style="display:none;">
                    <div class="taxcpc-process-indicator position-absolute text-center">
                    </div>
                </div>

                <input type="hidden" id="sessionAccYear" value="<s:property value="%{#assessment.getAccYear()}" />"/> 
                <input type="hidden" id="sessionQuarterNo" value="<s:property value="%{#assessment.getQuarterNo()}" />"/> 
                <input type="hidden" id="sessionTdsType" value="<s:property value="%{#assessment.getTdsTypeCode()}" />"/> 
                <input type="hidden" id="defaultDeductor" value="<s:property value="%{#session.get('DEDUCTOR')}" />"/> 
                <input type="hidden" id="module" value="<s:property value="%{module}" />"/>

                <s:hidden id="g_fileExtension" value="%{#session.get('EXCELFORMAT')}"/>
                <s:hidden id="g_excelFileName" value="%{#session.get('EXCELTEMPLATENAME')}"/>
                <s:hidden id="clientLoginLevel" value="%{#session.get('CLIENTLOGINLEVEL')}" />
                <div class="assessment-section-header">
                    <div class="row">
                        <div class="col-md-6">
                            <s:if test="%{activeDashboardAssessmentBandFlag.equalsIgnoreCase('ActiveAssessmentBand')}">
                                <span class="dropdown-head">FY :</span>

                                <s:if test="%{#dynaccyear != null && #dynaccyear.size() > 0}">
                                    <select class="borderless-select add-bold" title="Acc-Year" id="accYear" onchange="changeAssessment();">
                                        <s:iterator value="%{#dynaccyear}">
                                            <option value="<s:property/>" <s:if test="%{#assessment.getAccYear().equals(top)}">selected</s:if>><s:property/></option> 
                                        </s:iterator>
                                    </select>
                                </s:if>
                                <s:else>
                                    <select class="borderless-select add-bold" title="Acc-Year" id="accYear" onchange="changeAssessment();">
                                        <s:iterator value="%{#session.sessionAccYearList}">
                                            <option value="<s:property/>" <s:if test="%{#assessment.getAccYear().equals(top)}">selected</s:if>><s:property/></option> 
                                        </s:iterator>
                                    </select>
                                    <%--<option <s:if test="#assessment.getAccYear() == '16-17'">selected</s:if> value="16-17">16-17</option>
                                    <option <s:if test="#assessment.getAccYear() == '17-18'">selected</s:if> value="17-18">17-18</option>
                                    <option <s:if test="#assessment.getAccYear() == '18-19'">selected</s:if> value="18-19">18-19</option>
                                    <option <s:if test="#assessment.getAccYear() == '19-20'">selected</s:if> value="19-20">19-20</option>                                
                                    <option <s:if test="#assessment.getAccYear() == '20-21'">selected</s:if> value="20-21">20-21</option>--%>                                    
                                </s:else>                                
                                <span class="dropdown-head">QTR :</span>
                                <select class="borderless-select add-bold" title="Quarter" id="quarter" onchange="changeAssessment();">
                                    <s:iterator value="%{#session.sessionQtrList}" var="qtrEntry">
                                        <option value="<s:property value="#qtrEntry.key"/>" <s:if test="%{#assessment.getQuarterNo() == #qtrEntry.key}">selected</s:if>><s:property value="#qtrEntry.value"/></option> 
                                    </s:iterator>
                                    <%--<option <s:if test="#assessment.getQuarterNo() == 1">selected</s:if> value="1" >Q1</option>-->
                                    <option <s:if test="#assessment.getQuarterNo() == 2">selected</s:if> value="2" >Q2</option>
                                    <option <s:if test="#assessment.getQuarterNo() == 3">selected</s:if> value="3">Q3</option>
                                    <!--<option <s:if test="#assessment.getQuarterNo() == 4">selected</s:if> value="4">Q4</option>--%>
                                </select>

                                <span class="dropdown-head">TDS Type :</span>
                                <select list="%{#session.sessionTdsTypeCodeList}" class="borderless-select add-bold" title="TDS Type" id="tdsType"  onchange="changeAssessment();" style="text-align-last: center">
                                    <s:iterator value="%{#session.sessionTdsTypeCodeList}" var="tdsTypeEntry">
                                        <option value="<s:property value="#tdsTypeEntry.key"/>" <s:if test="%{#assessment.getTdsTypeCode() == #tdsTypeEntry.key}">selected</s:if>><s:property value="#tdsTypeEntry.value"/></option> 
                                    </s:iterator>
                                    <%--<option <s:if test="#assessment.getTdsTypeCode() == '24Q'">selected</s:if> value="24Q">24Q</option>-->
                                    <option <s:if test="#assessment.getTdsTypeCode() == '26Q'">selected</s:if>  value="26Q">26Q</option>
                                    <option <s:if test="#assessment.getTdsTypeCode() == '27Q'">selected</s:if>  value="27Q">27Q</option>
                                    <!--<option <s:if test="#assessment.getTdsTypeCode() == '27EQ'">selected</s:if>  value="27EQ">27EQ</option>--%>
                                </select>
                            </s:if>
                            <s:else>
                                <span class="dropdown-head">FY :</span>

                                <s:if test="%{#dynaccyear != null && #dynaccyear.size() > 0}">
                                    <select class="borderless-select add-ban-cursor-assessment-band" title="Only Access on Dashboard Tab" id="accYear" onchange="changeAssessment();" disabled>
                                        <s:iterator value="%{#dynaccyear}">
                                            <option value="<s:property/>" <s:if test="%{#assessment.getAccYear().equals(top)}">selected</s:if>><s:property/></option> 
                                        </s:iterator>
                                    </select>
                                </s:if>

                                <s:else>
                                    <select class="borderless-select add-ban-cursor-assessment-band" title="Only Access on Dashboard Tab" id="accYear" onchange="changeAssessment();" disabled>
                                        <s:iterator value="%{#session.sessionAccYearList}">
                                            <option value="<s:property/>" <s:if test="%{#assessment.getAccYear().equals(top)}">selected</s:if>><s:property/></option> 
                                        </s:iterator>
                                    </select>
                                </s:else>                                
                                <span class="dropdown-head">QTR :</span>
                                <select class="borderless-select add-ban-cursor-assessment-band" title="Only Access on Dashboard Tab" id="quarter" onchange="changeAssessment();" disabled>
                                    <s:iterator value="%{#session.sessionQtrList}" var="qtrEntry">
                                        <option value="<s:property value="#qtrEntry.key"/>" <s:if test="%{#assessment.getQuarterNo() == #qtrEntry.key}">selected</s:if>><s:property value="#qtrEntry.value"/></option> 
                                    </s:iterator>
                                </select>
                                <span class="dropdown-head">TDS Type :</span>
                                <select list="%{#session.sessionTdsTypeCodeList}" class="borderless-select add-ban-cursor-assessment-band " title="Only Access on Dashboard Tab" id="tdsType" onchange="changeAssessment();"  disabled style="text-align-last: center">
                                    <s:iterator value="%{#session.sessionTdsTypeCodeList}" var="tdsTypeEntry">
                                        <option value="<s:property value="#tdsTypeEntry.key"/>" <s:if test="%{#assessment.getTdsTypeCode() == #tdsTypeEntry.key}">selected</s:if>><s:property value="#tdsTypeEntry.value"/></option> 
                                    </s:iterator>
                                </select>
                            </s:else>
                        </div>
                        <div class="col-md-6 ">
                            <ul class="nav nav-pills pull-right main-menu-navigation">
                                    <s:if test="%{#adminUser.equalsIgnoreCase('ADMINUSER')}">
                                   <li class="notification-icon" title="Asmin Dashboard" style="margin-top: -4px;"><a class="position-relative" href="#" onclick="" style="font-size: 1.5rem;    padding: 2px 11px;"><i class="fa fa-home" aria-hidden="true" style="border-right: 2px solid #4e8dff;padding-right: 10px;"></i></a></li>    
                                         
                                    </s:if>
                                    <s:else>
                                    <li class="notification-icon" title="Main Dashboard" style="margin-top: -4px;"><a class="position-relative" href="#" onclick="callModule('R');" style="font-size: 1.5rem;    padding: 2px 11px;"><i class="fa fa-home" aria-hidden="true" style="border-right: 2px solid #4e8dff;padding-right: 10px;"></i></a></li>    
                                    </s:else>
                                    
                                 
                                <!--<li class="notification-icon mr-2" title="Notifications"><a class="position-relative" href="notification " style="    padding: 2px 11px;"><i class="fa fa-bell" aria-hidden="true"></i></a></li>-->
                                     <s:if test="%{#adminUser.equalsIgnoreCase('ADMINUSER')}">
                                    <li class="active"><a href="">Regular</a></li>
                                    </s:if>
                                    <s:elseif test="%{#loginClient.tanno == null}">
                                    <li class="active"><a href="dashboard?module=R">Regular</a></li>
                                    </s:elseif>
                                    <s:else>
                                        <s:set var="module_map" value="%{#session.get('MODULESLIST')}"/>
                                        <%--<s:textfield value="%{#module_map.indexOf(\"M\")}"/>--%>
                                        <s:if test ="%{#module != null && (!#module.equalsIgnoreCase('A'))}">
                                        <li <s:if test="#module.equalsIgnoreCase('R')"> class="active"</s:if>><a href="#" onclick="callModule('R');">Regular</a></li>
<!--                                        <li <s:if test="#module.equalsIgnoreCase('C')"> class="active"</s:if>><a <s:if test="%{#module_map != null && #module_map.indexOf(\"C\") != -1}"> href="#" onclick="callModule('C');" </s:if> <s:else> href="#" style="cursor: not-allowed;" title="You have no rights for accessing Correction" </s:else>>Correction</a></li>
                                        <li <s:if test="#module.equalsIgnoreCase('G')"> class="active"</s:if>><a <s:if test="%{#module_map != null && #module_map.indexOf(\"G\") != -1}"> href="#" onclick="callModule('G');"</s:if> <s:else> href="#" style="cursor: not-allowed;" title="You have no rights for accessing 15G/H" </s:else>>15G/H</a></li>
                                        <li <s:if test="#module.equalsIgnoreCase('M')"> class="active"</s:if>><a <s:if test="%{#module_map != null && #module_map.indexOf(\"M\") != -1}"> href="#" onclick="callModule('M');"</s:if> <s:else> href="#" style="cursor: not-allowed;" title="You have no rights for accessing MIS" </s:else>>MIS</a></li>-->

                                    </s:if>
                                </s:else>
                            </ul>
                            <form id="module_form" action="dashboard" method="POST">
                                <input type="hidden" id="session_module" name="module" value="<s:property value="%{#module}"/>" />
                            </form>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <!--Main Navigation-->
            </div>
            <!-- Top Header-->
            <!--Left Section-->
            <div class="left-sidebar">

                <div class="text-center powered-by position-fixed">
<!--                    <p class="text-white mb-0" style="font-size: 8px;">Powered By : <a href="http://www.taxcpc.com/" target="_blank"><img src="resources/images/global/taxcpcLogoWhite.jpg"></a> <sub class="version-detail" style="">v2.0</sub> </p>-->
<!--                    <p class="text-white mb-0" style="font-size: 8px;">Powered By : <a href="https://www.indianbank.in/" target="_blank"><img src="resources/images/global/IndiaBankLogo.jpg"></a> <sub class="version-detail" style="">v2.0</sub> </p>-->

                    <p class="text-white mb-0" style="font-size: 8px;">Powered By : <a href="http://www.taxcpc.com/" target="_blank"><img src="resources/images/global/taxcpcLogoWhite.jpg"></a> <sub class="version-detail" style="">v2.1</sub> </p>


                    <!--                    <p class="text-white mb-0" style="font-size: 8px;"></p>-->
                </div>
                <s:if test ="%{#module != null && (#module.equalsIgnoreCase('A'))}">                    
                    <ul class="nav nav-pills flex-column ">                        
                        <li title="Dashboard" <s:if test="#activeTab == 'dashboard'">class="active"</s:if>><a href="#" onclick="callUrl('/dashboard')"><i class="fa dashboard mr-2" aria-hidden="true"></i>Dashboard</a></li>                        
                        <!--<li title="Entity Client Generation"  <s:if test="#activeTab == 'entityGeneration'"> class="active"</s:if> ><a href="entityGeneration"><i class="fa generate  mr-2" aria-hidden="true"></i>Entity Client Generation</a></li>-->
                        <!--<li title="Bank Branch Generation"  <s:if test="#activeTab == 'branchGeneration'"> class="active"</s:if> ><a href="branchGeneration"><i class="fa fa-user mr-2" aria-hidden="true"></i>Bank Branch Generation</a></li>-->
                        </ul>
                </s:if> 
                <s:elseif test ="%{#module != null && !(#module.equalsIgnoreCase('R'))}"> 
                    <s:if test="%{#module.equalsIgnoreCase('C')}">
                        <ul class="nav nav-pills flex-column ">
                            <li title="Dashboard" <s:if test="#activeTab == 'dashboard'">class="active"</s:if>><a href="dashboard"><i class="fa dashboard mr-2" aria-hidden="true"></i>Dashboard</a></li>
                            <li title="Deductor Information" <s:if test="#activeTab == 'tdsDeductorInfo'">class="active"</s:if>> <a href="#"><i class="fa deductor  mr-2" aria-hidden="true"></i>Deductor Info</a></li>
                            <li title="Import Correction  Template" <s:if test="#activeTab == 'corrImport'">class="active"</s:if>><a href="corrImportAction"><i class="fa import mr-2" aria-hidden="true"></i>Import Correction Template</a></li>
                            <li title="TDS Challan Details" <s:if test="#activeTab == 'tdsChallan'">class="active"</s:if>><a href="#"><i class="fa challan  mr-2" aria-hidden="true"></i>Challan</a></li>
                            <li title="TDS Deductee Details" <s:if test="#activeTab == 'tdsDeductees'">class="active"</s:if>><a href="#"><i class="fa deductee  mr-2" aria-hidden="true"></i>Deductee</a></li>
                            <li title="TDS Deductee Transaction" <s:if test="#activeTab == 'tdsTransaction'">class="active"</s:if> ><a href="#"><i class="fa transaction mr-2" aria-hidden="true"></i>Transaction</a></li>
                            <li title="Salary Details" <s:if test="#activeTab == 'tdsSalary'">class="active"</s:if> ><a href="#"><i class="fa salary mr-2" aria-hidden="true"></i>Salary</a></li>
                                <li title="Validation & Error Processing" > <a href="#"><i class="fa validation  mr-2" aria-hidden="true"></i>Validation</a></li>
                                <li title="PRN Information"  <s:if test="#activeTab == 'prnInfo'"> class="active"</s:if> ><a href="#"><i class="fa prn-information  mr-2" aria-hidden="true"></i>PRN Information</a></li>
                            <!--<li title="Download Section"  <s:if test="#activeTab == 'downloadStatusBrowse'"> class="active"</s:if> ><a href="#"><i class="fa download  mr-2" aria-hidden="true"></i>Download Section</a></li>-->
                            </ul>
                    </s:if>
                    <s:elseif test="%{#module.equalsIgnoreCase('G')}">
                        <ul class="nav nav-pills flex-column " >
                            <li <s:if test="#activeTab == 'dashboard'">class="active"</s:if>><a href="dashboard"><i class="fa dashboard mr-2" aria-hidden="true"></i>Dashboard</a></li>
                            <li title="Import File" <s:if test="#activeTab == 'tdsImport'">class="active"</s:if>><a href="tdsImportStatus"><i class="fa import mr-2" aria-hidden="true"></i>Import Template</a></li>
                            <li <s:if test="#activeTab == '15GHTransaction'">class="active"</s:if> ><a href="form15GHTransaction"><i class="fa transaction mr-2" aria-hidden="true"></i>Transaction</a></li>
                            <li<s:if test="#activeTab == '15GHGenerateRefNo'"> class="active"</s:if>><a href="getGenReferenceNoAction?action=RefNoPage"><i class="fa generate-reference-no  mr-2" aria-hidden="true"></i>Generate Reference No.</a></li>
                            <!--<li <s:if test="#activeTab == '15GHValidation'">class="active"</s:if>><a href="errorStatus15GH"><i class="fa validation  mr-2" aria-hidden="true"></i>Validate & XML Generation</a></li>-->
                            <li <s:if test="#activeTab == '15GHValidation'">class="active"</s:if>><a href="errorStatus15GH"><i class="fa validation  mr-2" aria-hidden="true"></i>Validate & CSV Generation</a></li>
                            <li <s:if test="#activeTab == 'prnInfo'">class="active"</s:if>><a href="prnInfo"><i class="fa prn-information mr-2" aria-hidden="true"></i>PRN Information</a></li>
                            <!--<li title="Download Section"  <s:if test="#activeTab == 'downloadStatusBrowse'"> class="active"</s:if> ><a href="downloadStatusBrowse"><i class="fa download  mr-2" aria-hidden="true"></i>Download Section</a></li>-->
                            </ul>
                    </s:elseif> 
                    <s:elseif test="%{#module.equalsIgnoreCase('M')}">
                        <ul class="nav nav-pills flex-column " >
                            <li title="MIS Reports" <s:if test="#activeTab == 'reportAction'">class="active"</s:if>><a href="reportAction"><i class="fa prn-information mr-2" aria-hidden="true"></i>MIS Reports</a></li>
                            <li title="Download Section"  <s:if test="#activeTab == 'downloadStatusBrowse'">class="active"</s:if> ><a href="downloadStatusBrowse"><i class="fa download  mr-2" aria-hidden="true"></i>Download Section</a></li>
                            </ul>
                    </s:elseif>
                </s:elseif>                
                <s:else>
                    <ul class="nav nav-pills flex-column ">
                            <s:if test="%{#adminUser.equalsIgnoreCase('ADMINUSER')}">
                            <li title="Admin Dashboard" <s:if test="#activeTab == 'adminDashboard'">class="active"</s:if>> <a href="adminDashboard"><i class="fa dashboard mr-2" aria-hidden="true"></i>Admin Dashboard</a></li>
                            <li title="Login Details" <s:if test="#activeTab == 'userLogin'">class="active"</s:if>> <a href="loginDetails?loginDetailsType=client"><i class="fa deductor  mr-2" aria-hidden="true"></i>User Login Details</a></li>
                            </s:if>
                            <s:elseif test="%{#loginClient.tanno == null}">
                            <li title="Deductor Information" class="active"> <a href="deductors"><i class="fa deductor  mr-2" aria-hidden="true"></i>Deductor Info</a></li>
                            </s:elseif>
                            <s:else>
                            <li title="Dashboard" <s:if test="#activeTab == 'dashboard'">class="active"</s:if>><a href="#" onclick="callUrl('/dashboard')"><i class="fa dashboard mr-2" aria-hidden="true"></i>Dashboard</a></li>
                            <li title="Deductor Information" <s:if test="#activeTab == 'tdsDeductorInfo'">class="active"</s:if>> <a href="deductors"><i class="fa deductor  mr-2" aria-hidden="true"></i>Deductor Info</a></li>
<!--                            <li title="Import Template" class="disable-li" <s:if test="#activeTab == 'tdsImport'">class="active"</s:if>><a href="#" onclick="callUrl('/tdsImportStatus')"><i class="fa import mr-2" aria-hidden="true"></i>Import Template</a></li>
                            <li title="TDS Challan Details" class="disable-li" <s:if test="#activeTab == 'tdsChallan'">class="active"</s:if>><a href="tdsChallan"><i class="fa challan  mr-2" aria-hidden="true"></i>Challan</a></li>
                            <li title="TDS Deductee Details" class="disable-li" <s:if test="#activeTab == 'tdsDeductees'">class="active"</s:if>><a href="tdsDeductees"><i class="fa deductee  mr-2" aria-hidden="true"></i>Deductee</a></li>
                            <li title="TDS Deductee Transaction" class="disable-li"  <s:if test="#activeTab == 'tdsTransaction'">class="active"</s:if> ><a href="#" onclick="callUrl('/tdsTransactionBridge?action=true')"><i class="fa fa-text-width mr-2" aria-hidden="true"></i>Transaction</a></li>
                            <li title="Salary" class="disable-li" <s:if test="#activeTab == 'salaryEntryNew'"> class="active"</s:if> ><a href="salaryBrowseNew"><i class="fa salary mr-2" aria-hidden="true"></i>Salary</a></li>
                            <li title="Lower Deduction Certificate" class="disable-li" <s:if test="#activeTab == 'tdsLowerDeductionBrowse'">class="active"</s:if> ><a href="tdsLowerDeductionBrowse"><i class="fa fa-indent mr-2" aria-hidden="true"></i>Lower Deduction</a></li>
                            <li title="Validation & Error Processing" class="disable-li" <s:if test="#activeTab == 'errorStatus'">class="active"</s:if> ><a href="errorStatus"><i class="fa validation  mr-2" aria-hidden="true"></i>Validation & FVU Generation</a></li>
                            <li title="PRN Information" class="disable-li"  <s:if test="#activeTab == 'prnInfo'"> class="active" </s:if> ><a href="prnInfo"><i class="fa prn-information  mr-2" aria-hidden="true"></i>PRN Information</a></li>                        
                            <li title="Download TDS Certificate (Form-16 Generation)" <s:if test="#activeTab == 'downloadTdsCertificate'"> class="active"</s:if> ><a href="downloadTdsCertificate"><i class="fa fa-download  mr-2" aria-hidden="true"></i>Download TDS Certificate</a></li>-->
                            <!--<li title="Download Section"  <s:if test="#activeTab == 'downloadStatusBrowse'"> class="active"</s:if> ><a href="downloadStatusBrowse"><i class="fa download  mr-2" aria-hidden="true"></i>Download Section</a></li>-->
                                <!--<li title="Misc Transaction"  <s:if test="#activeTab == 'miscellaneous'"> class="active"</s:if> ><a href="miscellaneous"><i class="fa transaction mr-2" aria-hidden="true"></i>Misc Transaction</a></li>-->
                          </s:else>
                    </ul>
                </s:else>
                <div class="position-absolute sessionId-section w-100 text-right p-1">
                    <p class="mb-1">Session Id : <span><s:property value="%{#session.LOGINSESSIONID}"/></span> | <span><s:property value="%{#loginuser.client_code}"/></span></p>
                </div>
            </div>
            <!--Left Section-->

            <!--Right Section-->
            <div class="right-section">

                <div class="header-gap"></div>
                <!--Body Start Here-->



                <s:hidden value="%{#session.CONTEXTPATH}" id="globalcontextpath"/>
                <s:hidden id="globalSessionData" value="%{#session.SESSIONINSERT}" />
                <script type="text/javascript">
                    try {
                        getGlobalSessionData();
                    } catch (err) {
                    }
                </script>

                <%@include file="/WEB-INF/view/jsp/global/alertBoxes.jsp" %>

                <tiles:insertAttribute name="body" />





                <!--Body Start Here-->
                <div class="clearfix"></div>
                <!--                <div class="footer-gap"></div>-->
            </div>
            <!--Right Section-->
        </div>
        <!-- The Modal -->
        <div class="remark-modal modal" id="remarkModal">
            <div class="modal-dialog">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h6 class="modal-title">Feedback</h6>
                        <button type="button" class="close" data-dismiss="modal" onclick="window.location.reload();">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <div class="text-center col-md-12" id="successMsgDiv" style="display:none">
                            <div class="d-inline-block">
                                <div class="form-validation form-validation--success p-1 my-1">
                                    <i class="fa fa-check mr-2" aria-hidden="true"></i>
                                    <h5 class="d-inline-block" id="successMsg"></h5>
                                </div>
                            </div> 
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-8">

                                <textarea class="form-control" id='remark' placeholder="Remark" rows="3"></textarea>

                            </div>
                            <div class="col-lg-4 text-right">
                                <button type="button" class="btn btn-primary w-100 mx-auto rounded-0 mb-2 p-1" id="save_remark" onclick="remarkSave();">Save</button><br>
                                <button type="button" class="btn btn-danger w-100 mx-auto rounded-0 p-1" id="cancel_remark" data-dismiss="modal" onclick="window.location.reload();">cancel</button>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
         $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
                $.ajaxSetup({cache: false});
            });

            function getDBEntityMast() {
                $.ajax({
                    type: "POST",
                    url: "executeQuery",
                    success: function (response) {
                        console.log("Data----->" + response);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log(errorThrown);
                    }
                });
            }
            
//             function PanIsBlankchedk(id) {alert("call");
//    var pan_no = document.getElementById(id).value;alert(pan_no);
//    if (lhsIsNull(pan_no)) {
//        alert("blank pan no");
//    } else {
//       updatepanNoNew(id);
//    }
//}// end method

function lhsIsNull(value) {
    try {
        value = value.trim();
    } catch (err) {
        value = value;
    }
    if (value !== "" && value !== "null" && value !== null && value !== undefined && value !== 'undefined')
        return false;
    else
        return true;
}//end lhs_isNull function
        </script>
    </body>
</html>

