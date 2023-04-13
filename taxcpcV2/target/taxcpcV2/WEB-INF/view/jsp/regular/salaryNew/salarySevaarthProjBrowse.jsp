<%-- 
    Document   : salarySevaarthProjBrowse
    Created on : Jul 9, 2021, 11:55:49 AM
    Author     : kapil.gupta
--%>

<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>

<script src="resources/js/lhs/lhsAjax.js" type="text/javascript"></script>


<div class="page-section">


    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">

            <li onclick="callBrowsePage();" class="nav-item mr-5"><a class="nav-link " data-toggle="pill" href="tdsSalary"><p> Salary Browse </p> </a></li>
            <li onclick="salaryEntryNewPage();" class="nav-item mr-5"><a class="nav-link" data-toggle="pill" href="salaryEntryNew2"> <p> Income and Investment Declaration Form </p></a></li>
            <li onclick="" class="nav-item"><a class="nav-link active " data-toggle="pill" href="#"><p> Salary Seavaarth Projection Details </p> </a></li>
        </ul>
        <div class="clearfix"></div>
    </div>

    <div class="clearfix"></div>




    <div class="tab-content px-4 py-4">
        <div id="browse" class="tab-pane show in active">

            <div class="clearfix"></div>
            <s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
            <s:hidden value="%{dataGridAction}" id="dataGridAction"/>
            <s:hidden value="%{deducteePanno}" id="deducteePanno"/>
            <div class="clearfix"></div> 

            <!--Table Section-->
            
                <div class="table-responsive mt-2" id="dataSpan">
                    <!--Ajax data here-->
                   
                </div>
                <div class="clearfix"></div> 
           
        </div>


    </div>
</div>
<script src="resources/js/regular/salaryNew/salaryNew.js" type="text/javascript"></script>
<script src="resources/js/global/paginator/globalPaginator.js"></script>
<script type="text/javascript">
    try {
       showPageData();
    } catch (e) {
    }

</script>  
