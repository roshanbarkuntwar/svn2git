<%-- 
    Document   : globalPaginator
    Created on : Jan 23, 2019, 11:17:23 AM
    Author     : ayushi.jain
--%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<s:div id="paginator_top" class="paginator-section mb-2" cssStyle="display: none;">
    <div class="row " style="margin-top: 6px;">
        <div class="col-lg-3">
            <span style="vertical-align: -webkit-baseline-middle;">Total Records : <span id="totalRecordsSpan"> <s:property value="totalRecordsCount"/> </span></span>
        </div>
        <div class="col-lg-9 justify-content-end d-flex" id="paginationBlock">
            <button type="button" id="bulk_download_btn" disabled="disabled" class="btn form-btn mr-2 mt-0 d-none" title="Generate bulk data download request" onclick="bulkFileDownload('<s:property value="%{bulkDownloadFor}"/>');"><i class="fa download"></i>Request Download</button>
            
            <div class="cust_paginator_wrap cust_paginator_wrap d-flex justify-content-end text-center" >
                <input type="hidden" name="startValue" id="startValue" value="1"/>
                <input type="hidden" name="endValue" id="endValue" value="6" />
                <ul class="pagination">
                    <li class="page-item">

                        <a href="#" class="page-link go-to-pageno-link pt-1">  
                            <div class="input-group">
                                <input  class="form-control m-0" id="pagination_number" placeholder="Page No" onkeypress="return isNumber(event);" type="text" value="">   
                                <input id="totalPages" type="hidden" value="<s:property value="totalPages"/>">
                                <div class="input-group-append">
                                    <button class="my-0 btn btn-primary addon-btn" style="width:25px;" type="button" id="topBtn" onclick="jumpToPage(this.id);" >Go</button>
                                </div>
                            </div>
                        </a>   
                    </li>
                    <li class="page-item">
                        <a class="current-page no-cursor page-link">
                            <span id="pageNumberSpan"><s:property value="%{pageNumber}"/></span>&nbsp;of&nbsp;
                            <span  id="totalPagesSpan"><s:property value="%{totalPages}"/></span>
                        </a>
                    </li>
                    <li class="page-item">
                        <a href="#settings"  data-toggle="collapse" class="setting-btn page-link"> <i class="fa fa-cog" aria-hidden="true"></i></a>
                    </li>
                </ul>
                <ul class="pagination" id="paginationUl">
                    <li class="page-item" title="Go To First Page"><a class="page-link" onclick="getCurrentPageData('1', 'JUMP');"><i class="fa fa-fast-backward"></i></a></li>         
                    <li class="page-item"  title="Go To Previous Page"><a class="page-link"><i class="fa fa-chevron-left" onclick="getPreviousPage();"></i></a></li>   
                    <li class="page-item" title="Go To Next Page"><a class="page-link" onclick="getNextPage();"><i class="fa fa-chevron-right"></i></a>   </li>
                    <li class="page-item" title="Go To Last Page"><a class="page-link" onclick="getCurrentPageData('<s:property value="totalPages"/>', 'JUMP');"><i class="fa fa-fast-forward"></i></a></li>                  
                </ul>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="resources/js/regular/bulkDownload/bulkDownload.js?r=<%=Math.random()%>"></script>
</s:div>
