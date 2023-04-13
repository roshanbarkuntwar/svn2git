<%-- 
    Document   : misReportSetting
    Created on : Apr 25, 2022, 1:09:01 PM
    Author     : akash.meshram
--%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"> 
<!-- JavaScript Bundle with Popper -->
<!--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>-->
<script type="text/javascript" src="resources/js/admin/admin.js"></script>
<!--<script type="text/javascript" src="resources/js/lhs/lhsGlobal.js"></script>-->
<!DOCTYPE html>

<div _ngcontent-swn-c5="" class="tab-content px-2 pb-2 pt-5">
     <div class="tab-section col-md-12 my-1" >
        <ul class="nav nav-pills">

            <li  class="nav-item mr-5"><a class="nav-link active" href="#"  ><p>MIS Menu Setting </p> </a></li>
           
        </ul>

        <div class="clearfix"></div>
    </div>
    <div class="col-md-12 text-right"> 
     <button type="button" class="form-btn" onclick="callUrl('/adminDashboard');"><i class="fa fa-chevron-left" aria-hidden="true"></i>Back</button>
    </div>
    
    <div table-responsive mt-2 d-flex justify-content-center">
    <div _ngcontent-swn-c5="" class="row justify-content-center" >
        <div _ngcontent-swn-c5="" class="col-md-8">
            <div _ngcontent-swn-c5="" class="row">
                
                 <div _ngcontent-swn-c5="" class="col-md-6 text-center w-100">
                            <h4 _ngcontent-swn-c5="" class="text-primary">MIS Menu</h4>
                 </div>
               
            </div>
            <div _ngcontent-swn-c5="" class="">
                <table _ngcontent-swn-c5="" class="table table-striped ">
                    <thead _ngcontent-swn-c5="">
                        <tr _ngcontent-swn-c5="">
                            <th _ngcontent-swn-c5="" class="td-report-sr"></th>
                            <th _ngcontent-swn-c5="" class="td-report-sr">Sr No.</th>
                            <th _ngcontent-swn-c5="" class="td-seq-id">Report Name</th>
                            <th _ngcontent-swn-c5="" class="td-report-desc">Report Status</th>
                        </tr>
                    </thead>
                    <tbody _ngcontent-swn-c5="">
                       <s:if test="%{MisReportConfigMastList != null && !MisReportConfigMastList.isEmpty()}">
                         <s:iterator value="MisReportConfigMastList" status="statusVar">
                            <tr _ngcontent-swn-c5="">
                                <td _ngcontent-swn-c5="" class="text-center">
                                            <span _ngcontent-swn-c5="" class="btn btn-primary addon-btn filter-inner-btn" >
                                                <i _ngcontent-swn-c5="" class="fa fa-info-circle" onclick="openRemarkModel('<s:property value="rep_desc"/>')"></i>
                                            </span>

                               </td>
                                <td class="text-center" ><s:property value="%{#statusVar.count}"/></td>
                                <td _ngcontent-swn-c5=""><s:property value="rep_desc"/></td>
                               
                            <td _ngcontent-swn-c5="">
                                            <s:if test="%{rep_status.equalsIgnoreCase('T')}">
                                                <div class="form-check form-switch">
                                                    <input class="form-check-input" type="checkbox"  onchange="updateReportFlag(<s:property value="%{#statusVar.count}"/>,'<s:property value="rep_seq_id"/>',this.id);" id="SwitchChecked~<s:property value="%{#statusVar.count}"/>" checked>
                                                    <label id="reportswitchFlag~<s:property value="%{#statusVar.count}"/>"><s:property value="rep_status"/></label>
                                               </div>
                                            </s:if>
                                            <s:else>
                                                <div class="form-check form-switch">
                                                    <input class="form-check-input" type="checkbox" onchange="updateReportFlag(<s:property value="%{#statusVar.count}"/>,'<s:property value="rep_seq_id"/>',this.id);" id="SwitchUnChecked~<s:property value="%{#statusVar.count}"/>">
                                                    <label id="reportswitchFlag~<s:property value="%{#statusVar.count}"/>"><s:property value="rep_status"/></label>
                                                </div>   
                                            </s:else>
                                                   </td>
                            </tr>
                        </s:iterator>
                       </s:if>   
                 
                    </tbody>
                </table>


            </div>




        </div>


     
    
     </div>                           
    </div>
</div>

 
  


<div _ngcontent-swn-c5="" aria-hidden="true" aria-labelledby="kpiUpdateModal" class="modal fade" id="parameterInfoModal" role="dialog" tabindex="-1"><div _ngcontent-swn-c5="" class="modal-dialog modal-dialog-centered" role="document">
        <div _ngcontent-swn-c5="" class="modal-content">
            <div _ngcontent-swn-c5="" class="modal-header">
                <h5 _ngcontent-swn-c5="" class="modal-title">Information</h5>
                <button _ngcontent-swn-c5="" aria-label="Close" class="close" data-dismiss="modal" type="button">
                    <span _ngcontent-swn-c5="" aria-hidden="true">×</span>
                </button>
            </div>
            <div _ngcontent-swn-c5="" class="modal-body">
                <p _ngcontent-swn-c5=""></p>
            </div>
        </div>
    </div>
</div>



<div  class="modal" id="remark-modal">
    <div class="modal-dialog modal-sm modal-dialog-centered" >
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Information</h4>
                <button type="button" class="close" title="Close" data-dismiss="modal" onclick="closeremarkmodal();" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="text-center">
                 <p class="text-justify" id="remark_data">remak modal box</p> 
                </div>
            </div>
        </div>
    </div>
</div> 