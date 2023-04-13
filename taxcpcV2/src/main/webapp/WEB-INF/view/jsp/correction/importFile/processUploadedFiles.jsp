<%-- 
    Document   : processUploadedFiles
    Created on : Mar 2, 2019, 5:53:46 PM
    Author     : ayushi.jain
--%>

<%@taglib prefix="s" uri="/struts-tags"%>
<script>
    function hidden_error() {
        document.getElementById('error_hidden').style.visibility = "hidden";
    }
</script>
<script type="text/javascript" src="js/bootstrap-filestyle.min.js"></script>

<style>
    @-moz-document url-prefix() {
        .customFile{
            width:96%;
            box-sizing:content-box;
            height: 25px;
        }
    }
</style>
<div class="text-center"><b><span id="approximatevalue" style="color: red "></span></b></div>
<div class="progress" style="visibility: hidden;" id="prgogress_bar">
    <div class="progress-bar progress-bar-striped active" role="progressbar"
         aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:100%">
        <span style="font-weight: bold; color:#7D1C1C; font-size: 15px;">Uploading Zip File... Don't Refresh the Page and Don't Press Any Button </span>
    </div>
</div>
<s:div cssClass="page-header_title">
    <p>Process Correction File(s)</p>
</s:div>
<s:div cssClass="div_gap"></s:div>

    <!--paginator-->
<%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %>

<!-----------------settings------------>
<%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginatorSettings.jsp" %>
<div style="position: relative">

    <span id="dataSpan">
        <!--data from ajax-->
    </span>  

</div>


<s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
<s:hidden value="%{recordsPerPage}" id="recordsPerPage"/>
<s:hidden value="%{browseAction}" id="browseAction"/>
<s:hidden value="%{browseAction}" id="dataGridAction"/>
<s:hidden value="%{pageNumber}" id="pageNumber"/>
<input type="hidden" id="countCall" value="0">
<input type="hidden" id="fltrformId" value="transactionMgmtForm">
<script src="resources/js/global/paginator/globalPaginator.js?r=<%= Math.random()%>"></script>
<script type="text/javascript">
try {
    defaultValues();
} catch (err) {

}
try {
    getCurrentPageData(document.getElementById("pageNumberSpan").innerHTML);
    document.getElementById("paginationBlock").style.display = "none";
} catch (e) {

}





</script>


