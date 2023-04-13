<%-- 
    Document   : misGroupReportContent
    Created on : Oct 22, 2019, 2:43:31 PM
    Author     : gaurav.khanzode
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<link href="resources/css/global/bootstrap-datetimepicker.css" rel="stylesheet" type="text/css"/>
<link href="resources/css/global/calendar/dhtmlxcalendar.css" rel="stylesheet">

<script src="resources/js/global/calendar/dhtmlxcalendar.js"></script>
<script type="text/javascript" src="resources/js/admin/misReport/misReport.js?r=<%= Math.random()%>"></script>
<script type="text/javascript" src="resources/js/global/scroll.js"></script>
<script src="resources/js/global/paginator/globalPaginator.js?r=<%= Math.random()%>"></script>
<link href="resources/css/misReport/misReport.css" rel="stylesheet">

<div class="page-section">
    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">
            <li class="nav-item mr-5" style="cursor: not-allowed;"><a class="nav-link disabled" style="pointer-events:none;" data-toggle="pill" href="#"><p>MIS Reports</p></a></li>
            <li class="nav-item mr-5">
                <a class="nav-link active" data-toggle="pill" href="#browseMISReport">
                    <p><s:property value="%{report_header_text != null ? report_header_text : 'MIS Report Details'}"/></p>
                </a>
            </li>
        </ul>
        <div class="clearfix"></div>
    </div>

    <div class="tab-content px-4 py-4">
        <div class="button-section my-1">

        </div>
        <div class="container-fluid pt-2">
            <div class="row">
                <div class="col-md-6">
                    <button type="button" class="form-btn" id="g_download" title="Select filter for download" disabled="disabled" 
                            onclick="downloadStaticExcel('REPORT DETAILS');"><i class="fa fa-file-excel-o" aria-hidden="true"></i>Capture Page Data in Excel</button>
                    <!--                    <button type="button" class="form-btn" onclick="callUrl('/reportAction');" title="Go Back"><i class="fa back" aria-hidden="true"></i>Back</button>-->
                </div>
                <div class="col-md-6">
                    <!--                    <div class="table-editor-set text-right">
                                            <div class="options text-center">
                                                <button type="button" class="edit-option" title="Maximize"> <img src="resources/images/mis-dashboard/maximize.png" alt="maximize">
                                                </button>
                                            </div>
                                            <div class="  options text-center">
                                                <button type="button" class="edit-option" title="Fit to Size">  <img src="resources/images/mis-dashboard/fit-to-width.png" alt="maximize">
                                                </button>
                                            </div>
                                            <div class="   options text-center">
                                                <button type="button" class="edit-option" title=" Wrap Text"> <img src="resources/images/mis-dashboard/wrap.png" alt="maximize">
                                                </button>
                                            </div>  
                                            <div class=" options  text-center">
                                                <button type="button" class="edit-option" title=" Unwrap text">  <img src="resources/images/mis-dashboard/more.png" alt="maximize">
                                                </button>
                                            </div>
                                            <div class=" options  text-center">
                                                <button type="button" class="edit-option" title="Send Mail">  <img src="resources/images/mis-dashboard/paper-plane.png" alt="maximize">
                                                </button>
                                            </div>
                                        </div>-->
                </div>
            </div> 
        </div>


        <div class="container-fluid pt-2">
            <s:form id="reportModelContentForm" name="reportModelContentForm" action="showReportDetailAction?search=true">

                <s:hidden id="h_seqId" name="seqId" value="%{seqId}"/>
                <s:hidden id="h_reportGroup" name="reportGroup" value="%{reportGroup}"/>
                <s:hidden id="h_reqPagination" name="req_pagination_flag" value="%{!req_pagination_flag.isEmpty() ? req_pagination_flag : ''}"/>
                <s:hidden id="h_action" name="action" value=""/>
                <s:set var="assessment" value="%{#session.get('ASSESSMENT')}"/>

                <div class="row sec-bottom">
                    <div class="col-md-11">
                        <div class="row form-group">
                            <s:if test="%{reportConfig != null}">
                                <s:if test="%{reportConfig.rep_assesm_fltr_flag.equalsIgnoreCase('T')}">
                                    <div class="col-lg-2">
                                        <select id="accYear" name="accYear" class="form-control mb-2">
                                            <option <s:if test="#assessment.getAccYear() == '16-17'">selected</s:if> value="16-17">16-17</option>
                                            <option <s:if test="#assessment.getAccYear() == '17-18'">selected</s:if> value="17-18">17-18</option>
                                            <option <s:if test="#assessment.getAccYear() == '18-19'">selected</s:if> value="18-19">18-19</option>
                                            <option <s:if test="#assessment.getAccYear() == '19-20'">selected</s:if> value="19-20">19-20</option>
                                            <option <s:if test="#assessment.getAccYear() == '20-21'">selected</s:if> value="20-21">20-21</option>
                                            </select>
                                        </div>
                                        <div class="col-lg-2">
                                            <select id="quarterNo" name="quarterNo" class="form-control mb-2">
                                                <option <s:if test="#assessment.getQuarterNo() == 1">selected</s:if> value="1" >Q1</option>
                                            <option <s:if test="#assessment.getQuarterNo() == 2">selected</s:if> value="2" >Q2</option>
                                            <option <s:if test="#assessment.getQuarterNo() == 3">selected</s:if> value="3">Q3</option>
                                            <option <s:if test="#assessment.getQuarterNo() == 4">selected</s:if> value="4">Q4</option>
                                            </select>
                                        </div>
                                        <div class="col-lg-2">
                                            <select id="tdsTypeCode" name="tdsTypeCode" class="form-control mb-2">
                                                <option <s:if test="#assessment.getTdsTypeCode() == '24Q'">selected</s:if> value="24Q">24Q</option>
                                            <option <s:if test="#assessment.getTdsTypeCode() == '26Q'">selected</s:if>  value="26Q">26Q</option>
                                            <option <s:if test="#assessment.getTdsTypeCode() == '27Q'">selected</s:if>  value="27Q">27Q</option>
                                            <option <s:if test="#assessment.getTdsTypeCode() == '27EQ'">selected</s:if>  value="27EQ">27EQ</option>
                                            </select>
                                        </div>
                                </s:if>
                            </s:if>
                            <s:if test="%{reportParaList != null && !reportParaList.isEmpty()}">
                                <s:iterator value="reportParaList" status="head">

                                    <s:if test="%{status.equalsIgnoreCase('T')}">
                                        <%--<s:if test="%{entry_by_user.equalsIgnoreCase('F')}">
                                            <s:set name="readonly" value="%{'true'}"></s:set>
                                        </s:if>
                                        <s:elseif test="%{entry_by_user.equalsIgnoreCase('T')}">
                                            <s:set name="readonly"  value="%{'false'}"></s:set>
                                        </s:elseif>--%>

                                        <s:if test="%{nullable_flag.equalsIgnoreCase('F')}">
                                            <s:set name="mandatory" value="%{'*'}"></s:set>                        
                                        </s:if>
                                        <s:elseif test="%{nullable_flag.equalsIgnoreCase('T')}">
                                            <s:set name="mandatory" value="%{' '}"></s:set>  
                                        </s:elseif>

                                        <s:if test="%{filter_attribute.equalsIgnoreCase('T')}">

                                            <div class="d-none">
                                                <s:label class="d-none" id="lbl_%{#head.count}" value="%{filter_desc} :" />
                                                <s:label id="mlbl_%{#head.count}" value="%{mandatory}" class="d-none" />
                                            </div>
                                            <div class="col-lg-2">
                                                <s:textfield id="field_%{#head.count}" name="%{filter_column}" value="%{para_default_value_processed}" placeholder="%{filter_desc}"
                                                             readonly="#readonly" maxLength="%{filter_size}" cssClass="form-control" />
                                            </div>

                                        </s:if>

                                        <s:if test="%{filter_attribute.equalsIgnoreCase('A')}">

                                            <div class="d-none">
                                                <s:label class="d-none" id="lbl_%{#head.count}" value="%{filter_desc} :" />
                                                <s:label class="d-none" id="mlbl_%{#head.count}" value="%{mandatory}"/>
                                                <%--<s:hidden value="%{filter_attribute_value}" id="fltAtrVal_%{#head.count}"/>--%>
                                            </div>
                                            <div class="col-lg-2">
                                                <s:textfield id="field_%{#head.count}" name="%{filter_column}" value="%{para_default_value_processed}" placeholder="%{filter_desc}"
                                                             readonly="#readonly" maxLength="%{filter_size}" cssClass="form-control" onfocus="getAutocompleteList(this, %{#head.count});" />
                                            </div>

                                        </s:if>

                                        <s:if test="%{filter_attribute.equalsIgnoreCase('D')}">

                                            <div class="d-none">
                                                <s:label class="d-none" id="lbl_%{#head.count}" value="%{filter_desc} :" />
                                                <s:label class="d-none" id="mlbl_%{#head.count}" value="%{mandatory}"/>
                                            </div>
                                            <div class="col-lg-2">
                                                <div class="input-group">
                                                    <s:textfield id="field_%{#head.count}" name="%{filter_column}" value="%{para_default_value_processed}" placeholder="%{filter_desc}"
                                                                 readonly="#readonly" maxLength="%{filter_size}" cssClass="form-control"/>
                                                    <div class="input-group-append">
                                                        <button type="button" class="btn btn-primary addon-btn" id="btnC_<s:property value="#head.count"/>" 
                                                                onmouseover="openCalendar('field_<s:property value="#head.count"/>', 'cal_<s:property value="#head.count"/>');">
                                                            <i class="fa fa-calendar" id="cal_<s:property value="#head.count"/>"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <!--                                            <div class="col-lg-5 col-xl-2">
                                            <%--<img src="resources/images/global/icon_deleteImage.png" id="btnC_<s:property value="#head.count"/>" onclick="clear_calendor_filed(this.id);" title="Clear"/>--%>
                                            <button id="btnC_<s:property value="#head.count"/>" type="button" title="Clear" class="btn btn-primary addon-btn filter-inner-btn" onclick="clear_calendor_filed(this.id);"><i class="fa clean"></i></button>
                                        </div>-->

                                        </s:if>

                                        <%--<s:if test="%{filter_attribute.equalsIgnoreCase('L') || filter_attribute.equalsIgnoreCase('M')}">
                                        <!--<div class="row form-group">-->
                                        <div class="col-lg-3 col-xl-3">
                                            <s:label class="control-label" id="lbl_%{#head.count}" value="%{filter_desc} :" />
                                            <s:label id="mlbl_%{#head.count}" value="%{mandatory}"/>
                                        </div>
                                        <div class="col-lg-5 col-xl-2">
                                            <s:textfield id="field_%{#head.count}" name="%{filter_column}" value="%{para_default_value_processed}" readonly="#readonly" maxLength="%{filter_size}"  cssClass="form-control" cssStyle="width:100%;" />

                                                <s:hidden id="Hfield_%{#head.count}" name="H%{filter_column}" value="%{para_default_value_processed}"/>
                                                <s:hidden value="%{item_help_property}" name="htype_%{#head.count}" id="htype_%{#head.count}" />
                                                <s:hidden value="%{ref_lov_table_col}" name="hlovqry_%{#head.count}" id="hlovqry_%{#head.count}" />
                                                <s:hidden value="%{ref_lov_where_clause}" name="hwhereclause_%{#head.count}" id="hwhereclause_%{#head.count}" />
                                                <s:hidden value="%{order_clause}" name="horderby_%{#head.count}" id="horderby_%{#head.count}" />
                                                <s:hidden value="" name="hsearchcol_%{#head.count}" id="hsearchcol_%{#head.count}" />
                                                <s:hidden value="%{dependent_slno}" name="dependent_slno_%{#head.count}" id="dependent_slno_%{#head.count}" />
                                            </div>
                                            <div class="col-lg-3 col-xl-3">
                                                <button type="button" id="btnL_<s:property value="#head.count"/>" name="btnL_<s:property value="#head.count"/>" title="Search" class="btn btn-primary addon-btn filter-inner-btn" onclick="open_para_lov(this.id);"><i class="fa search"></i></button>
                                                <button type="button" id="btnC_<s:property value="#head.count"/>" name="btnC_<s:property value="#head.count"/>" class="btn btn-primary addon-btn filter-inner-btn" onclick="clear_lov_filed(this.id);"><i class="fa clean"></i></button>
                                            </div>
                                            <!--</div>-->
                                        </s:if>--%>

                                        <%--<s:if test="%{filter_attribute.equalsIgnoreCase('C')}">
                                        <!--<div class="row form-group">-->
                                        <div class="col-lg-3 ">
                                            <s:label class="control-label" id="lbl_%{#head.count}" value="%{filter_desc} :" />
                                            <s:label id="mlbl_%{#head.count}" value="%{mandatory}"/>
                                        </div>
                                        <div class="col-lg-5 col-xl-2">
                                            <select id="field_%{#head.count}" name="%{filter_column}" class="form-control" style="width:100%;">
                                                <option value="">----select----</option>
                                            </select>
                                        </div>

                                            <!--</div>-->
                                        </s:if>>--%>

                                        <s:if test="%{filter_attribute.equalsIgnoreCase('S')}">

                                            <div class="d-none">
                                                <s:label class="d-none" id="lbl_%{#head.count}" value="%{filter_desc} :" />
                                                <s:label class="d-none" id="mlbl_%{#head.count}" value="%{mandatory}"/>
                                            </div>

                                            <s:hidden value="%{filter_column}" id="paraColumn_%{#head.count}"/>
                                            <s:hidden value="%{filter_attribute_value}" id="columnSelectListValue_%{#head.count}"/>
                                            <div class="col-lg-2" id="h_select~<s:property value="%{#head.count}"/>" title="<s:property value="%{filter_desc}"/>">

                                            </div>

                                            <script type="text/javascript">
                                                getReportParaDescSelect('h_select~<s:property value="%{#head.count}"/>');
                                            </script>
                                        </s:if>

                                    </s:if>

                                </s:iterator>



                                <s:hidden value="%{reportParaList.size()}" id="paraDescColumnsCount"/>
                            </s:if>
                        </div>
                    </div>
                    <div class="col-md-1 pl-0 d-flex justify-content-between">

                        <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="showReportData();"><i class="fa fa-search"></i></button>
                        <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="resetMisFilters();"><i class="fa fa-eraser"></i></button>
                        <button type="button" class="btn btn-primary addon-btn filter-inner-btn"  data-toggle="collapse" data-target="#remainingFilter" aria-expanded="false"><i class="fa fa-ellipsis-h"></i></button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-11">
                        <div class="collapse" id="remainingFilter" style="">
                            <div class="row form-group">
                                <!--                                <div class="col-lg-2">
                                                                    <select name="deducteeFltr.verifiedFlag" id="verifiedFlag" class="form-control" title="Verification Status" placeholder="Verification Status">
                                                                        <option value="">---Select---</option>
                                                                        <option value="Y">Verified</option>
                                                                        <option value="N">Not Verified</option>
                                                                        <option value="X">Invalid</option>
                                                                    </select>
                                                                </div>-->
                                <!--
                                                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="showReportData();" title="Show Report"><i class="fa fa-search"></i></button>
                                                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="resetMisFilters();" title="Reset Filters"><i class="fa fa-eraser"></i></button>
                                                            <button type="button" class="btn btn-primary addon-btn filter-inner-btn" data-toggle="collapse" data-target="#remainingFilter" 
                                                                    aria-expanded="false" title="Expand filters"><i class="fa fa-ellipsis-h"></i></button>-->

                            </div>
                        </div>

                    </div>
                </div>


                <s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
                <s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>
                <s:hidden value="%{dataGridAction}" id="dataGridAction"/>
                <s:hidden value="%{pageNumber}" id="pageNumber"/>
                <input type="hidden" id="countCall" value="0">
                <input type="hidden" id="fltrformId" value="reportModelContentForm">
                <input type="hidden" name="filterFlag" id="filterFlag">
            </s:form>

            <div class="clearfix"></div> 

            <s:if test="%{req_pagination_flag.equalsIgnoreCase('T')}">
                <!--paginator-->
                <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %>
                <!-----------------settings------------>
                <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginatorSettings.jsp" %> 
                <div class="clearfix"></div> 
            </s:if>

        </div>
        <div class="mt-2" id="dataSpan">
            <%
                out.println(GlobalMessage.PAGINATION_SHOW_MORE);
            %>
            <!--Ajax data here-->
        </div>  
        <div class="row">
            <div class="col-lg-12 text-center">
                <button type="button" class="form-btn" onclick="callUrl('/reportAction');" title="Go Back"><i class="fa back" aria-hidden="true"></i>Back</button>
            </div>
        </div>
    </div>

    <!--    <div class="btn-action-section  bg-white text-center fixed-bottom">
            <button type="button" class="form-btn" onclick="callUrl('/reportAction');" title="Go Back"><i class="fa back" aria-hidden="true"></i>Back</button>
        </div>-->
</div>
<script type="text/javascript">
    $(document).ready(() => {
        setTimeout(function () {
            try {
                $('#recordsPerPageSelect').attr('onchange', 'customCurrentPageOnChange();');
            } catch (e) {
                console.warn("Pagination is not available for this report dashboar.");
            }
        }, 500);
    });
</script>
