<%-- 
    Document   : deducteeBrowse
    Created on : Jan 23, 2019, 10:56:07 AM
    Author     : ayushi.jain
--%>

<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<script src="resources/js/global/paginator/globalPaginator.js?r=<%= Math.random()%>"></script>
<script src="resources/js/deductee/deductee.js" type="text/javascript"></script>
<div class="page-section">
    <div class="tab-section col-md-12 my-1" >
        <ul class="nav nav-pills">

            <li  class="nav-item mr-5"><a class="nav-link active"  href="tdsDeductees"><p>Deductee Browse </p> </a></li>
            <li  class="nav-item mr-5"><a class="nav-link "  href="panVerificationOffline"><p> PAN Verification </p> </a></li>
            <li  class="nav-item mr-5"><a class="nav-link "  href="deducteePanDetails"><p> PAN Verification Status </p> </a></li>
            <!--<li class="nav-item"><a class="nav-link" data-toggle="pill" href="#entry"><i class="fa fa-file-o mr-2" aria-hidden="true"></i> <p> Entry </p></a></li>-->

        </ul>

        <div class="clearfix"></div>
    </div>

    <div class="clearfix"></div>


    <div class="tab-content px-4 py-4">
        <div id="browse" class="tab-pane show in active">



            <div class="button-section  my-1 pt-1"> 
                <button type="button" class="form-btn" id="g_download" title="Select filter for download" disabled="disabled" onclick="downloadStaticExcel('DEDUCTEE DETAILS');"><i class="fa fa-file-excel-o" aria-hidden="true"></i>Capture Page Data in Excel</button>
                <!--                                    <button type="button" class="form-btn"><i class="fa fa-floppy-o" aria-hidden="true"></i>Save</button>-->
                <!--<a href="tdsImportAction"><button type="button" class="form-btn"><i class="fa fa-upload" aria-hidden="true"></i>Import</button></a>-->
                <!--<button type="button" class="form-btn"><i class="fa approve" aria-hidden="true"></i>Approve All</button>-->
                <!--<button type="button" class="form-btn"><i class="fa revert" aria-hidden="true"></i>Revert All</button>-->
                <!--<a href="panVerificationOffline"><button type="button" class="form-btn"><i class="fa verification" aria-hidden="true"></i>PAN Verification</button></a>-->

            </div>
            <s:form name="deducteeMgmtFrm" id="deducteeMgmtFrm" method="post" action="tdsDeductees?search=true&initErr=0" theme="simple"> 



                <div class="filter-section">
                    <div class="row sec-bottom">
                        <div class="col-lg-10 col-xl-11">
                            <div class="row form-group">
                                <div class="col-lg-3">

                                    <s:textfield cssClass="form-control"  id="panno" placeholder="PAN No." title="PAN No." name="deducteeFltr.panno"/>
                                </div>
                                <div class="col-lg-3">

                                    <s:textfield cssClass="form-control"  id="deducteeRefNo" placeholder="Deductee Reference" title="Deductee Reference" name="deducteeFltr.deducteeRefNo"/>

                                </div>

                                <div class="col-lg-3">
                                    
                                    

                                    <s:textfield cssClass="form-control"  id="deducteeName" placeholder="Deductee Name" title="Deductee Name" name="deducteeFltr.deducteeName"/>

                                </div>
                                <div class="col-lg-3">
                                    <s:textfield cssClass="form-control" id="accountNo" placeholder="Acc No." title="Acc No." name="deducteeFltr.accountNo"/>

                                </div>    

                            </div>

                            <div class="collapse" id="remainingFilter">
                                <div class="row form-group">
                                    <div class="col-lg-3">
                                        <s:select   list="verificationFlagList" cssClass="form-control" id="verifiedFlag" placeholder="Verification Status" title="Verification Status" name="deducteeFltr.verifiedFlag"/>

                                    </div>


                                </div>

                            </div>
                        </div>
                        <div class="col-lg-2 col-xl-1 pl-0 d-flex justify-content-between">
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="validateFilter();" title="Search"><i class="fa search"></i></button>
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="lhsResetform('deducteeMgmtFrm', 'submit');" title="Clear"><i class="fa clear"></i></button>
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn more-filter-option-btn" data-toggle="collapse" data-target="#remainingFilter" title="Extra Filters"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></button> 
                        </div> 
                    </div>  
                </div> 




                <!--                <div class="filter-section">
                                    <div class="row form-group">
                                    <div class="col-md-4">
                                       
                <s:textfield cssClass="form-control"  id="panno" placeholder="PAN No." title="PAN No." name="deducteeFltr.panno"/>
                    
                 </div>
                 <div class="col-md-4">
                    

                <s:textfield cssClass="form-control"  id="deducteeRefNo" placeholder="Deductee Reference" title="Deductee Reference" name="deducteeFltr.deducteeRefNo"/>
               
            </div>
            <div class="col-md-4">
               
                    <div class="input-group">
                <s:textfield cssClass="form-control"  id="deducteeName" placeholder="Deductee Name" title="Deductee Name" name="deducteeFltr.deducteeName"/>
                <div class="input-group-btn">
                    <button type="submit" class="btn btn-primary addon-btn filter-inner-btn ml-2">GO</button>
                    <button type="button" class="btn btn-primary addon-btn filter-inner-btn more-filter-option-btn" data-toggle="collapse" data-target="#remainingFilter"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></button>
                </div>

            </div>
       
    </div>
    </div>
    <div class="collapse" id="remainingFilter">
        <div class="row form-group">
            <div class="col-md-4">
           
                <s:textfield cssClass="form-control" id="accountNo" placeholder="Acc No." title="Acc No." name="deducteeFltr.accountNo"/>
      
        </div>
        <div class="col-md-4">
   
                <s:select   list="verificationFlagList" cssClass="form-control" id="verifiedFlag" placeholder="Verification Status" title="Verification Status" name="deducteeFltr.verifiedFlag"/>
          
        </div>
        
    </div>
</div>
</div>-->

                <input type="hidden" name="filterFlag" id="filterFlag">
                <s:hidden name="deducteeFilterFlag"  value="%{deducteeFilterFlag}" id="deducteeFilterFlag"/>
            </s:form>
            <div class="clearfix"></div>




            <!--paginator-->
            <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %>

            <!-----------------settings------------>
            <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginatorSettings.jsp" %>
            <div class="clearfix"></div> 

            <!--Table Section-->
<!--            <div class="col-md-12">-->
                <div class="table-responsive mt-2" id="dataSpan">
                    <!--Ajax Data Here-->
                    <%
                        out.println(GlobalMessage.PAGINATION_SHOW_MORE);
                    %>
                </div>

<!--            </div>-->
            <!--Table Section-->

        </div>


    </div>
</div>
                

<s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
<s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>
<s:hidden value="%{browseAction}" id="browseAction"/>
<s:hidden value="%{dataGridAction}" id="dataGridAction"/>
<s:hidden value="%{pageNumber}" id="pageNumber"/>
<input type="hidden" id="countCall" value="0">
<input type="hidden" id="fltrformId" value="deducteeMgmtFrm">

<script type="text/javascript">
    try {
        defaultValues();
    } catch (err) {

    }
    try {
        $('#bulk_download_btn').removeClass('d-none');
//        getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);
//        document.getElementById("paginationBlock").style.display = "none";
    } catch (e) {

    }

    $(function () {
        //  alert(1);
        $('#table').tablesorter();
    });
    
 try {
       var flag = document.getElementById("deducteeFilterFlag").value;
       if(flag === 'F'){
         document.getElementById("remainingFilter").hidden = true;  
       }else{
         document.getElementById("remainingFilter").hidden = false; 
       }
    } catch (err) {

    }
</script>
