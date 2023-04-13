<%-- 
    Document   : uploadedFileDateGrid
    Created on : Sep 8, 2016, 1:12:24 PM
    Author     : akash.dev
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<form id="processZipFileForm" name="processUploadedFiles" action="processUploadedFiles" method="post">
    <s:hidden name="action" id="hdnAction" value="update"/>
    <input type="hidden" id="process_typeID" name="process_type"/>
    <input type="hidden" id="process_fltrDatafile_name" name="filterData.file_name" value="<s:property value="filterData.file_name"/>"/>
    <s:if test="hasActionErrors()">
        <s:div cssClass="gap2"></s:div>
        <s:div cssStyle="position: relative; top: -5px;">
            <s:div id="error_hidden" cssClass="errors">
                <s:actionerror/> 
            </s:div>
        </s:div>
    </s:if>
    <%--<s:hidden name="selectFileSeqs" id="fileSeqIds"/>--%>
    <s:div>
     
                        <s:if test="%{lhssysCorrFileUploadTranList != null && !lhssysCorrFileUploadTranList.isEmpty()}">
                            <table id="fileListTable" class="table table-striped mt-1">
                                <thead>
                                    <tr>
                                        <th style="width: 20px; text-align:center;"><input type="checkbox" class="align-middle mx-1" name="checkAll" id="checkAll" value="AllData"  onclick="checkUnchekAll();"/>All</th>
                                        <th style="width: 80px; text-align:center;">File Name</th>
                                        <th style="width: 80px; text-align:center;">Load start timestamp</th>
                                        <th style="width: 80px; text-align:center;">Load end timestamp</th>
                                        <th style="width: 80px; text-align:center;">Load status</th>
                                        <th style="width: 80px; text-align:center;">Load remark</th>
                                    </tr>
                                </thead>
                                <tbody style="border: 1px solid #e0e0e0; border-radius: 3px;">
                                    <s:iterator value="lhssysCorrFileUploadTranList" status="head">
                                        <tr>
                                            <td style="width: 20px; text-align:center;">
                                                <input type="checkbox" style="opacity: 1.5;margin-left:5px;" name="fileSeqnoList[<s:property value="%{#head.index}"/>]" id="fileSeqNo~<s:property value="%{#head.count}"/>" value="<s:property value="file_seqno"/>" onclick="setFileString();" >
                                                <%--
                                                <input type="checkbox" style="opacity: 1.5;margin-left:5px;" 
                                                       name="fileSeqno~<s:property value="%{#head.count}"/>" 
                                                       id="fileSeqNo~<s:property value="%{#head.count}"/>" 
                                                       value='<s:property value="file_seqno"/>'
                                                       onclick="setFileString();" />
                                                --%>
                                            </td>
                                            <td style="width: 80px; text-align: left;">
                                                <s:property value="file_name"/>
                                            </td>
                                            <td style="width: 80px; text-align: center;">
                                                <s:date name="load_start_timestamp" format="dd-MM-YYYY hh:mm" />
                                            </td>
                                            <td style="width: 80px; text-align:center;">
                                                <s:date name="load_end_timestamp" format="dd-MM-YYYY hh:mm" />
                                            </td>
                                            <td style="width: 80px; text-align: left;">
                                                <s:property value="load_status"/>
                                            </td>
                                            <td style="width: 80px; text-align: left;">
                                                <s:property value="load_remark"/>
                                            </td>
                                        </tr>
                                    </s:iterator>
                                </tbody>        
                            </table>
                        </s:if>
           
                <div class="form-group">
                    <div class="row">
                        <div class="button-section col-md-12 my-1 text-center">    
                            <button type="button" class="form-btn" id="processBtnId" onclick="updatedFiles();" disabled="true"><i class="fa fa-cog" aria-hidden="true"></i>Process</button>
                        </div>
                    </div>
                </div>
<!--                <div class="row">
                    <div class="col-sm-1 col-xs-12">
                    </div>
                    <div class="col-sm-10 col-xs-12" style="top: 8px;">
                        <button type="button" class="btn btn-info btn-block" id="processBtnId" style="width:100%;" onclick="updatedFiles();" disabled="true">Process</button>
                    </div>
                </div>-->
       
        </s:div>
   
</form>
