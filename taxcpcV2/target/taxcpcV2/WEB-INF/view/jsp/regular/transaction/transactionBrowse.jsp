<%-- 
    Document   : transactionBrowse
    Created on : Jan 23, 2019, 6:18:31 PM
    Author     : ayushi.jain
--%>

<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<%@page import="org.apache.struts2.views.velocity.components.DateDirective"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<link href="resources/css/global/bootstrap-datetimepicker.css" rel="stylesheet">
<link href="resources/css/transaction/transaction.css" rel="stylesheet">
<script src="resources/js/global/moment-with-locales.js"></script>
<link href="resources/css/global/calendar/dhtmlxcalendar.css" rel="stylesheet">
<script src="resources/js/global/moment-with-locales.js" type="text/javascript"></script>
<!--<script src="resources/js/global/bootstrap-datetimepicker.js" type="text/javascript"></script>-->
<script src="resources/js/global/calendar/dhtmlxcalendar.js"></script>
<script src="resources/js/regular/transaction/transaction.js?<%=Math.random()%>"></script>
<style>
    .header .sign:after{
        content:"+";
        display:inline-block;      
    }
    .header.expand .sign:after{
        content:"-";
    }

</style>
<div class="page-section">


    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">

            <li onclick="callBrowsePage();" class="nav-item mr-5"><a class="nav-link active" data-toggle="pill" href="#browse"><p>Transaction Browse </p> </a></li>
            <li onclick="callEntryPage();" class="nav-item"><a class="nav-link" data-toggle="pill" href="tdsTransactionEntry"><p> Transaction Entry </p></a></li>

        </ul>
        <div class="clearfix"></div>
    </div>

    <div class="clearfix"></div>

    <div class="tab-content px-4 py-4">
        <s:if test="%{sessionResult != null}">
            <div class="text-center col-md-12" id="session_notification">
                <div class="d-inline-block">
                    <s:div class="%{#session.ERRORCLASS}" >
                        <i class="fa fa-check mr-2" aria-hidden="true"></i>
                        <h5 class="d-inline-block" ><s:property value="sessionResult"/></h5>
                    </s:div>
                </div>
            </div>
        </s:if>

        <div id="browse" class="tab-pane show in active">
            <div class="button-section  my-1 pt-1"> 
                <button type="button" class="form-btn" id="g_download" title="Select filter for download" disabled="disabled" onclick="downloadStaticExcel('TRANSACTION DETAILS');"><i class="fa fa-file-excel-o" aria-hidden="true"></i>Capture Page Data in Excel</button>
                <!--<button type="button" class="form-btn" onclick="DownloadTemplate('transactionMgmtForm', 'downloadInTemplate');"><i class="fa fa-download" aria-hidden="true"></i>Download</button>-->
                <!--<a href="tdsImportAction"><button type="button" class="form-btn"><i class="fa fa-upload" aria-hidden="true"></i>Import</button></a>-->
                <!--                <button type="button" class="form-btn"><i class="fa fa-check" aria-hidden="true"></i>Approve All</button>
                                <button type="button" class="form-btn"><i class="fa fa-refresh" aria-hidden="true"></i>Revert All</button>-->
            </div>
            <s:form name="transactionMgmtForm" id="transactionMgmtForm" method="post" action="tdsTransaction?search=true" theme="simple"> 


                <s:hidden value="32" id="noOfColumn" name="noOfColumn"/>
                <s:hidden value="transaction" id="downloadFlag" name="downloadFlag"/>
                <s:hidden  id="seqidarray" name="seqidformuldel"/>
                <s:hidden  id="seqcount" name="seqcount"/>
                <s:hidden  id="muldelTotRec" name="muldelTotRec"  />
                <s:hidden  id="where_Clause" name="whereClause"  />
                <s:hidden  id="selectedRecordCount" name="selectedRecordCount"  />
                <div class="filter-section">
                    <div class="row sec-bottom">
                
                        
                        <div class="col-lg-10 col-xl-11">
                            <s:hidden id ="allAssessment" value="%{#session.get('ALLASSESSMENT')}" />

                            <s:if test="%{#session.get('ALLASSESSMENT') !=null && (#session.get('ALLASSESSMENT')).equalsIgnoreCase('T')}">

                                <div class="row form-group">
                                    <div class="col-lg-3">
                                        <s:set var="dynaccyear" value="%{#session.get('DYNACCYEAR')}"/>
                                        <select class="form-control" id="assAccYear" name="tranFilter.asmt.accYear">
                                            <option> Select Financial Year </option>
                                            <!--                                            <option>All</option>-->
                                            <s:if test="%{#dynaccyear != null && #dynaccyear.size() > 0}">
                                                <s:iterator value="%{#dynaccyear}">
                                                    <option value="<s:property/>"><s:property/></option>
                                                </s:iterator>
                                            </s:if>
                                            <s:else>
                                                <s:iterator value="%{#session.sessionAccYearList}">
                                                    <option value="<s:property/>"><s:property/></option>
                                                </s:iterator>
                                            </s:else>
                                        </select>
                                    </div>
                                    <div class="col-lg-3">
                                        <s:select label="Select Quarter" cssClass="form-control" id="assQuarter"
                                                  headerKey="" headerValue=" Select Quarter "
                                                  list="#{'all':'All','1':'Q1','2':'Q2','3':'Q3','4':'Q4'}"
                                                  name="tranFilter.asmt.quarterNo" 
                                                  value="" />

                                    </div>
                                    <div class="col-lg-3">
                                        <s:select label="Select Tds Type Code" cssClass="form-control" id="assTdsType"
                                                  headerKey="" headerValue=" Select Tds Type Code "
                                                  list="#{'all':'All','24Q':'24Q','26Q':'26Q','27Q':'27Q','27EQ':'27EQ'}" 
                                                  name="tranFilter.asmt.tdsTypeCode" 
                                                  value="" />
                                    </div>
                                    <!--                                    <div class="col-lg-3">
                                                                            <select class="form-control" title="Error List">
                                                                                <option>Error List</option>
                                                                            </select>
                                                                        </div>-->

                                </div>
                            </s:if>




                            <div class="row form-group">
                                <div class="col-lg-3">

                                    <s:textfield type="text" cssClass="form-control" id="panno" placeholder="PAN No." title="PAN No." name="tranFilter.panno" cssStyle="text-transform: uppercase;"/>
                                </div>
                                <div class="col-lg-3">

                                    <s:textfield type="text" cssClass="form-control" id="deducteeRefNo"  name="tranFilter.deducteeRefNo" placeholder="Deductee Ref. No." title="Deductee Ref. No."  maxlength="10" cssStyle="text-transform: uppercase;"/>

                                </div>

                                <div class="col-lg-3">

                                    <s:textfield type="text" cssClass="form-control" id="accountNo"  name="tranFilter.accountNo"  placeholder="Account Number" title="Account Number"/>

                                </div>
                                <div class="col-lg-3">
                                    <s:textfield type="text" cssClass="form-control" id="deducteeName"  name="tranFilter.deducteeName" placeholder="Deductee Name" title="Deductee Name" cssStyle="text-transform: uppercase;"/>

                                </div>

                            </div>
                            <div class="collapse" id="remainingFilter">
                                <div class="row form-group">
                                    <s:if test="%{tdsSectionList.size>0 && tdsSectionList !=null}">
                                        <div class="col-lg-3">
                                            <s:select list="tdsSectionList" cssClass="form-control" title="Section" id="section" name="tranFilter.section" />
                                        </div>
                                    </s:if>
                                    <div class="col-lg-3">
                                        <s:select list="tdsAmtOperatList" cssClass="form-control" title="Tds Amount Operator" id="tdsAmountOperat" name="tranFilter.tdsAmountOperat" />
                                    </div> 
                                    <div class="col-lg-3">
                                        <s:textfield type="text" cssClass="form-control" id="tdsAmountFrom"  name="tranFilter.tdsAmountFrom" placeholder="From TDS Amount" title="From TDS Amount"/>
                                    </div>
                                    <div class="col-lg-3">
                                        <s:textfield type="text" cssClass="form-control" id="tdsAmountTo"  name="tranFilter.tdsAmountTo" placeholder="To TDS Amount" title="To TDS Amount"/>
                                    </div>

                                </div>

                                <div class="row form-group">

                                    <div class="col-lg-3">

                                        <div class="input-group">
                                            <s:textfield type="text" cssClass="form-control" id="fromDate"  name="tranFilter.fromDate" placeholder="From Date" title="From Date" onblur="validateDateOnBlur(this);"/>

                                            <div class="input-group-append">

                                                <button type="button" class="btn btn-primary addon-btn" onmouseover="openBrowseCalendar('fromDate', 'fromDateIcon', '300px', '232px')" >
                                                    <i class="fa fa-calendar" id="fromDateIcon"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-lg-3">
                                        <div class="input-group">
                                            <s:textfield type="text" cssClass="form-control" id="toDate"  name="tranFilter.toDate" placeholder="To Date" title="To Date"  onblur="validateDateOnBlur(this);" />
                                            <div class="input-group-append">
                                                <button type="button" class="btn btn-primary addon-btn"  onmouseover="openBrowseCalendar('toDate', 'toDateIcon', '589px', '232px')">
                                                    <i class="fa fa-calendar" id="toDateIcon"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-3">
                                        <s:textfield type="text" cssClass="form-control" id="bankBranchCode"  name="tranFilter.bankBranchCode" placeholder="Bank Branch Code" title="Bank Branch Code"/>
                                    </div>
                                    <s:if test="%{tdsDeductReasonList.size>0 && tdsDeductReasonList !=null}">
                                        <div class="col-lg-3">
<!--                                            <s:select list="tdsDeductReasonList" cssClass="form-control"  title="TDS Deduct Reeason" id="tran_tds_deduct_reason" name="tranFilter.tdsDeductReason" />    -->
                                           <s:select list="tdsDeductReasonList" cssClass="form-control"  headerKey="" headerValue="Select Process Type" title="TDS Deduct Reeason" id="tran_tds_deduct_reason" name="tranFilter.tdsDeductReason" />    
                                             </div>
                                    </s:if>
                                </div>
                                <div class="row form-group">
                                    <div class="col-lg-3">
                                        <s:textfield type="text" cssClass="form-control" id="challanBankBranchCode"  name="tranFilter.challan_bank_branch_code" placeholder="Challan Bank Branch Code" title="Challan Bank Branch Code"/>
                                    </div>
                                    <div class="col-lg-3">

                                    <s:textfield type="text" cssClass="form-control" id="rowIdSeq"  name="tranFilter.rowIdSeq"  placeholder="Tran.seq" title="rowIdSeq" onkeypress="return lhsIsNumber(event);"/>

                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="col-lg-2 col-xl-1 pl-0 d-flex justify-content-between">
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn" title="Search" onclick="validateFilter();"><i class="fa search"></i></button>
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="lhsResetform('transactionMgmtForm', 'submit');"  title="Clear & Reset"><i class="fa clear"></i></button>
                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn more-filter-option-btn" data-toggle="collapse" title="Extra Filters" onclick="showFilterData('tdsTransaction');" data-target="#remainingFilter"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></button> 
                        </div> 
                    </div>  
                </div> 

                <s:hidden  name="filterFlag" id="filterFlag" />
               
             
            </s:form>
            
            

            <div class="clearfix"></div>



            <!--paginator-->
            <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %>

            <!-----------------settings------------>
            <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginatorSettings.jsp" %>


<!--            <input type="checkbox" id="checkAll" onclick="setAllChecked(this)" /><h6>Select All</h6>-->


            <div class="clearfix"></div> 
            <!--Table Section-->

            <div class="table-responsive mt-2" id="dataSpan">
<!--                <input type="checkbox" id="checkAll" onclick="setAllChecked(this)" /><h6>Select All</h6>-->
                <!--Ajax response here-->
                <%
                    out.println(GlobalMessage.PAGINATION_SHOW_MORE);
                %>
             </div>


            <!--Table Section-->
            <div class="clearfix"></div> 




        </div>
        <div class="action-section  bg-white text-center fixed-bottom" id="actiondiv">

            <s:if test="%{(#loginuser.edit_right != null) && (#loginuser.edit_right == 1)}">


                <input type="hidden" id="editFormId" />
                <button type="button" class="action-btn action-btn--edit mr-2" id="editBtn" onclick="submitForm('edit');"> <i class="fa fa-pencil"></i>Edit</button>

            </s:if>

            <s:if test="%{(#loginuser.delete_right != null) && (#loginuser.delete_right == 1)}">
                <input type="hidden" id="rowidForDelete" />

                <button type="button" class="action-btn action-btn--delete mr-2" id="deleteBtn" onclick="deleteTDS();"> <i class="fa fa-trash"></i>Delete</button>
                <button type="button" class="action-btn action-btn--delete mr-2" id="deleteAllBtn" onclick="deleteAllTDS();"> <i class="fa fa-trash"></i>Delete All</button>
                <button type="button" class="action-btn action-btn--delete mr-2" id="deleteMultiple" onclick="deleteAllTDS();"> <i class="fa fa-trash"></i>Delete Multiple</button>
            </s:if>  
            <s:if test="%{(#loginuser.delete_right != null) && (#loginuser.query_right == 1)}">
                <input type="hidden" id="viewFormId" />
                <button type="button" class="action-btn action-btn--view mr-2" id="viewBtn" onclick="submitForm('view');"> <i class="fa fa-eye"></i>View</button>
            </s:if>  
            <s:if test="%{(#loginuser.approve_right != null) && (#loginuser.approve_right == 1)}">
<!--                <button type="button" class="action-btn action-btn--approve mr-2" id="approveBtn"> <i class="fa fa-check-square"></i>Approve All</button>
                <button type="button" class="action-btn action-btn--reject mr-2" id="revertBtn"> <i class="fa fa-times-rectangle"></i>Revert All</button>-->
            </s:if>  
            <!--                                                <button type="button" class="action-btn action-btn--action1 mr-2"> <i class="fa fa-times-rectangle"></i>Action1</button>
                                                            <button type="button" class="action-btn action-btn--action2 mr-2"> <i class="fa fa-times-rectangle"></i>Action2</button>
                                                            <button type="button" class="action-btn action-btn--action3 mr-2"> <i class="fa fa-times-rectangle"></i>Action3</button>-->
        </div>
    </div>
</div>
<s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
<s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>
<s:hidden value="%{browseAction}" id="browseAction"/>
<s:hidden value="%{dataGridAction}" id="dataGridAction"/>
<s:hidden value="%{pageNumber}" id="pageNumber"/>
<s:hidden value="%{mulDelFlag}" id="mulDelFlag"/>
<input type="hidden" id="countCall" value="0">
<input type="hidden" id="fltrformId" value="transactionMgmtForm">
<script type = "text/javascript" src="resources/js/lhs/lhsGlobalValidation.js?r=<%Math.random();%>" ></script>
<script src="resources/js/global/paginator/globalPaginator.js?r=<%= Math.random()%>"></script>
<script type="text/javascript">
                   try {
                        defaultValues(); 
                    } catch (err) {

                    }
                    try {
                        $('#bulk_download_btn').removeClass('d-none');
//                        getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);
//                        document.getElementById("paginationBlock").style.display = "none";
                    } catch (e) {

                    }

 try {
  setTimeout(() => {
       removeError();
    }, 2000);
} catch (e) {

   }
//onkeypress="return lhsIsNumber(event);"
 
</script>
