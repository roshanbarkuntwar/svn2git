<%-- 
    Document   : fileDashboardFVU
    Created on : Mar 13, 2019, 10:23:12 AM
    Author     : ayushi.jain
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
    .taxcpc-process-indicator-outer {background: rgba(0, 0, 0 , 0.6);} 
</style>
<div class="row mb-4"> 
    <div class="col-lg-6 pr-0">
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th class="srno">Sr. NO.</th>
                        <th>Challan Details</th>
                        <th class="records">Records</th>

                    </tr>
                </thead>

                <tbody>
                    <s:if test="viewTdsProcessRecoListCh!=null && viewTdsProcessRecoListCh.size()>0">
                        <s:iterator value="viewTdsProcessRecoListCh" status="head">
                            <s:if test="%{#head.count == 1}">  
                                <s:set var="labelType" value="%{'badge-primary '}" />                          
                            </s:if>
                            <s:elseif test="%{#head.count == 2}">
                                <s:set var="labelType" value="%{'badge-danger'}" />                          
                            </s:elseif>
                            <s:elseif test="%{#head.count == 3}">                                                   
                                <s:set var="labelType" value="%{'badge-info'}" />
                            </s:elseif>                     
                            <s:else>                        
                                <s:set var="labelType" value="%{'badge-default'}" />
                            </s:else>
                            <tr>
                                <td id="seq~<s:property value='#head.count'/>"><s:property value="user_Seq"/></td> 
                                <td id="data1~<s:property value='#head.count'/>" ><s:property value="data_type_name.toUpperCase()"/></td> 
                                <td id="data2~<s:property value='#head.count'/>" >
                                    <span class="badge-pill font-weight-bold <s:property value='labelType'/>" >
                                        <s:property value="rec_count"/>
                                    </span>
                                </td>                         
                                <s:hidden  id="%{user_Seq}" name="data_type" value="%{data_type}"/>
                            </tr>
                        </s:iterator>
                    </s:if>
                    <s:else>
                        <tr >
                            <td colspan="3" style="text-align: center;">No Records Found</td> 
                        </tr>
                    </s:else>
                </tbody>
            </table>
        </div>
    </div>
    <div class="col-lg-6 pl-0">  
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>

                        <th class="srno">Sr. NO.</th>
                        <th>Deductee Details</th>
                        <th class="records">Records</th>
                    </tr>
                </thead>

                <tbody >
                    <s:if test="viewTdsProcessRecoListDed!=null && viewTdsProcessRecoListDed.size()>0">
                        <s:iterator value="viewTdsProcessRecoListDed" status="head">                        
                            <s:if test="%{#head.count == 1}">  
                                <s:set var="labelType" value="%{'badge-primary '}" />                          
                            </s:if>
                            <s:elseif test="%{#head.count == 2}">
                                <s:set var="labelType" value="%{'badge-danger'}" />                          
                            </s:elseif>
                            <s:elseif test="%{#head.count == 3}">                                                   
                                <s:set var="labelType" value="%{'badge-info'}" />
                            </s:elseif>                     
                            <s:else>                        
                                <s:set var="labelType" value="%{'badge-secondary'}" />
                            </s:else>
                            <tr>
                                <td id="seq~<s:property value='#head.count'/>" ><s:property value="user_Seq"/></td> 
                                <td id="challan1~<s:property value='#head.count'/>" ><s:property value="data_type_name.toUpperCase()"/></td> 
                                <td id="challan2~<s:property value='#head.count'/>" >
                                    <span class="badge-pill font-weight-bold <s:property value='labelType'/>" id="challan3">
                                        <s:property value="rec_count"/>
                                    </span>
                                </td>                         
                                <s:hidden  id="%{user_Seq}" name="data_type" value="%{data_type}"/>
                            </tr>
                        </s:iterator>
                    </s:if>
                    <s:else>
                        <tr>
                            <td colspan="3" style="text-align: center;">No Records Found</td> 
                        </tr>
                    </s:else>
                </tbody>
            </table>
        </div>
    </div>
</div>
