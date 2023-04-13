 <%-- 
    Document   : branchHierarchyBrowse.jsp
    Created on : Aug 19, 2021, 4:31:08 PM
    Author     : kapil.gupta
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<script src="resources/js/dashboard/dashboard.js?r" type="text/javascript"></script>
<link rel="stylesheet" href="resources/css/dashboard/dashboard.css" type="text/css">
<link rel="stylesheet" href="resources/css/global/jstree.min.css" />
<!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />-->
<script src="resources/js/jstree.min.js"></script>
<!DOCTYPE html>
<div class="page-section">
    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">
            <li class="nav-item mr-5" onclick="showBranchHierarchy('loggedIn')"><a class="nav-link active" data-toggle="tab" href="#loggedIn" id="loggedInBranchTab"><p>Logged In Branch Hierarchy</p></a></li>
            <li class="nav-item mr-5" onclick="showBranchHierarchy('allBranch')"><a class="nav-link" data-toggle="tab" href="#allBranch" id="allBranchTab"><p>All Branch Hierarchy</p></a></li>
        </ul>
        <div class="clearfix"></div>
    </div>
    <div class="tab-content px-4 py-4">
        <div id="loggedIn" class="tab-pane container active">
            <div class="container-fluid mt-4">
                <div class="d-flex justify-content-end">
                    <div class="hiearchy-legends">
                        <span class="f-w-700">( )</span> : 
                        <span class="mr-3">Branch Code</span>
                        <span class="f-w-700">{ }</span> : 
                        <span class="mr-3">Branch Name</span>
                        <span class="f-w-700">[ ]</span> : 
                        <span>Branch Level</span>
                    </div>
                </div>
                <div id="loggedInTree"></div>
            </div>
            <!--            <div class="hierarchy-wrapper mt-2"  id="dataSpan">
            
                        </div>   -->
            <a href="#" onclick="activeAllBranchTab('allBranchTab');" class="d-flex justify-content-end"></i>See All Branch Hierarchy</a>
        </div>
        <div id="allBranch" class="tab-pane fade">
            <div class="container-fluid mt-4">
                <div class="d-flex justify-content-end align-items-center mb-3">
                    <div class="expand-collapse-btn">
                        <a href="#" class="mr-2" onclick="$('#allTree').jstree('open_all');">Expand All</a>  
                        <a href="#" onclick="$('#allTree').jstree('close_all');" style="border-right: 2px solid #4e8dff;
    padding-right: 10px;margin-right: 10px">Collapse All</a>  
                    </div>
                    <div class="hiearchy-legends">
                        <span class="f-w-700">( )</span> : 
                        <span class="mr-3">Branch Code</span>
                        <span class="f-w-700">{ }</span> : 
                        <span class="mr-3">Branch Name</span>
                        <span class="f-w-700">[ ]</span> : 
                        <span>Branch Level</span>
                    </div>
                </div>
                <div id="allTree"></div>
            </div>
            <!--            <div class="hierarchy-wrapper mt-2"  id="dataSpan">
            
                        </div>   -->
            
        </div>
        
        
    </div>        
</div>
<script type="text/javascript">
    showBranchHierarchy('loggedIn');
    
</script>