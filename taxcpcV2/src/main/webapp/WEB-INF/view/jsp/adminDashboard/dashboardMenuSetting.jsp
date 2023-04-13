<%-- 
    Document   : dashboardMenuSetting
    Created on : Apr 25, 2022, 1:08:37 PM
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

            <li  class="nav-item mr-5"><a class="nav-link active" href="#"  ><p>Dashboard Menu Setting </p> </a></li>
            
        </ul>
 
        <div class="clearfix"></div>
     </div>
    <div class="col-md-12 text-right"> 
     <button type="button" class="form-btn" onclick="callUrl('/adminDashboard');"><i class="fa fa-chevron-left" aria-hidden="true"></i>Back</button>
    </div>
    
    <div table-responsive mt-2 d-flex justify-content-center">
    <div _ngcontent-swn-c5="" class="row justify-content-center" >
        <div _ngcontent-swn-c5="" class="col-md-6">
            <div _ngcontent-swn-c5="" class="row">
                
                 <div _ngcontent-swn-c5="" class="col-md-6 text-center w-100">
                            <h4 _ngcontent-swn-c5="" class="text-primary">Dashboard Menu</h4>
                 </div>
               
            </div>
                <div _ngcontent-swn-c5="" class="">
                    <table _ngcontent-swn-c5="" class="table table-striped ">
                        <thead _ngcontent-swn-c5="">
                            <tr _ngcontent-swn-c5="">
                                <th _ngcontent-swn-c5="" class="td-report-sr">Sr No.</th>
                                <th _ngcontent-swn-c5="" class="td-seq-id">Menu Name</th>
                                <th _ngcontent-swn-c5="" class="td-seq-id">Module type</th>
                                <th _ngcontent-swn-c5="" class="td-report-desc">Action</th>
                            </tr>
                        </thead>
                        <tbody _ngcontent-swn-c5="">
                            <s:if test="%{dashboardMenuList != null && !dashboardMenuList.isEmpty()}">
                                <s:iterator value="dashboardMenuList" status="statusVar">
                                    <tr _ngcontent-swn-c5="">
                                        <td class="text-center" ><s:property value="%{#statusVar.count}"/></td>
                                        <td _ngcontent-swn-c5=""><s:property value="navigation_name"/></td>
                                        <td _ngcontent-swn-c5=""><s:property value="module_type"/></td>
                                        <td _ngcontent-swn-c5="">
                                            <s:if test="%{active_flag.equalsIgnoreCase('A')}">
                                                <div class="form-check form-switch">
                                                    <input class="form-check-input" type="checkbox"  onchange="updateDashboardFlag(<s:property value="%{#statusVar.count}"/>, '<s:property value="navigation_name"/>', '<s:property value="module_type"/>', this.id, '<s:property value="active_flag"/>');" id="SwitchChecked~<s:property value="%{#statusVar.count}"/>" checked>
                                                    <label id="dashboardswitchFlag~<s:property value="%{#statusVar.count}"/>"><s:property value="active_flag"/></label>
                                                </div>
                                            </s:if>
                                            <s:else>
                                                <div class="form-check form-switch">
                                                    <input class="form-check-input" type="checkbox"  onchange="updateDashboardFlag(<s:property value="%{#statusVar.count}"/>, '<s:property value="navigation_name"/>', '<s:property value="module_type"/>', this.id, '<s:property value="active_flag"/>');"   id="SwitchUnChecked~<s:property value="%{#statusVar.count}"/>">
                                                    <label id="dashboardswitchFlag~<s:property value="%{#statusVar.count}"/>"><s:property value="active_flag"/></label>
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