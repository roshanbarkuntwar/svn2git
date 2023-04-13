<%-- 
    Document   : globalpaginatorSettings
    Created on : Jan 23, 2019, 11:19:09 AM
    Author     : ayushi.jain
--%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<s:div cssClass="collapse  mt-2 pagination-setting-section well" id="settings">
    <s:div cssClass="container-fluid " cssStyle="padding: 0px;">
        <div class="row">
            <div class="col-md-4 offset-md-1 text-right">
                <label id="lblrecordsPerPage" for="recordsPerPageSelect">Number Of Records Per Page :</label>
            </div>
            <div class="col-md-4">
                <select id="recordsPerPageSelect" name="recordsPerPage" class="form-control" onchange="getCurrentPageOnChange();">
                    <option id="recordsPerPageSelect10" value="10">10</option>
                    <option id="recordsPerPageSelect50" value="50">50</option>
                    <option id="recordsPerPageSelect100" value="100">100</option>
                    <option id="recordsPerPageSelect500" value="500">500</option>
                    <option id="recordsPerPageSelect1000" value="1000">1000</option>
                    <option id="recordsPerPageSelect5000" value="5000">5000</option>
                </select>  
            </div>
        </div>
    </s:div>
</s:div>
