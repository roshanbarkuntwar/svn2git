<%-- 
    Document   : applicationParameterSetting
    Created on : Apr 25, 2022, 1:08:10 PM
    Author     : akash.meshram
--%>
<%@page import="com.lhs.taxcpcv2.bean.GlobalMessage"%>
<%@page import="org.apache.struts2.views.velocity.components.DateDirective"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"> 
<!-- JavaScript Bundle with Popper -->
<!--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>-->
<script type="text/javascript" src="resources/js/admin/admin.js"></script>
<script type="text/javascript" src="resources/js/lhs/lhsGlobal.js"></script>


<!DOCTYPE html>
<div>

    <div class="page-section">
        <div class="tab-section col-md-12 my-1" >
            <ul class="nav nav-pills">

                <li  class="nav-item mr-5"><a class="nav-link active" href="#" id="java_list" onclick="tabchange('java', this.id)"><p>Java parameters setting </p> </a></li>
                <li  class="nav-item mr-5"><a class="nav-link "  href="#" id="oracle_list" onclick="tabchange('oracle', this.id)"><p>Oracle parameters setting</p> </a></li>
            </ul>

            <div class="clearfix"></div>
        </div>


        <div _ngcontent-swn-c5="" class="tab-content px-2 pb-2 pt-5" id="java_parameter_div">
            <div class="col-md-12 text-right"> 
                <button type="button" class="form-btn" onclick="callUrl('/adminDashboard');"><i class="fa fa-chevron-left" aria-hidden="true"></i>Back</button>
            </div>
            <div _ngcontent-swn-c5="" class="row">
                <div _ngcontent-swn-c5="" class="col-md-6">
                    <div _ngcontent-swn-c5="" class="row">
                        <div _ngcontent-swn-c5="" class="col-md-6 text-center w-100">
                            <h4 _ngcontent-swn-c5="" class="text-primary">App Parameter Flag Setting</h4>
                        </div>
                    </div>
                    <div _ngcontent-swn-c5="" class="overflow-hidden">
                        <table _ngcontent-swn-c5="" class="table table-striped ">
                            <thead _ngcontent-swn-c5="">
                                <tr _ngcontent-swn-c5="">
                                    <th _ngcontent-swn-c5="" class="td-report-sr"></th>
                                    <th class="text-center">Sr No.</th>
                                    <th _ngcontent-swn-c5="" class="td-seq-id">Parameter Name</th>
                                    <th _ngcontent-swn-c5="" class="td-report-desc">Parameter Value</th>
                                </tr>
                            </thead>
                            <tbody _ngcontent-swn-c5="">
                                <s:if test="%{javaflagparameter != null && !javaflagparameter.isEmpty()}">
                                    <s:iterator value="javaflagparameter" status="statusVar">
                                        <tr _ngcontent-swn-c5="">
                                            <td _ngcontent-swn-c5="" class="text-center">
                                                <span _ngcontent-swn-c5="" class="btn btn-primary addon-btn filter-inner-btn" >
                                                    <i _ngcontent-swn-c5="" class="fa fa-info-circle" onclick="openRemarkModel('<s:property value="remark"/>')"></i>
                                                </span>

                                            </td>
                                            <td  class="text-center"><s:property value="%{#statusVar.count}"/></td>
                                            <td _ngcontent-swn-c5=""><s:property value="parameter_name"/></td>
                                            <td _ngcontent-swn-c5="">
                                                <s:if test="%{parameter_value.equalsIgnoreCase('T')}">
                                                    <div class="form-check form-switch">
                                                        <input class="form-check-input" type="checkbox"  onchange="updateParameterFlag(<s:property value="%{#statusVar.count}"/>, '<s:property value="parameter_name"/>', this.id, 'java');" id="SwitchChecked_java~<s:property value="%{#statusVar.count}"/>" checked>
                                                        <label id="javaswitchFlag~<s:property value="%{#statusVar.count}"/>"><s:property value="parameter_value"/></label>
                                                    </div>
                                                </s:if>
                                                <s:else>
                                                    <div class="form-check form-switch">
                                                        <input class="form-check-input" type="checkbox" onchange="updateParameterFlag(<s:property value="%{#statusVar.count}"/>, '<s:property value="parameter_name"/>', this.id, 'java');" id="SwitchUnChecked_java~<s:property value="%{#statusVar.count}"/>">
                                                        <label id="javaswitchFlag~<s:property value="%{#statusVar.count}"/>"><s:property value="parameter_value"/></label>
                                                    </div>   
                                                </s:else>
                                                <input type="hidden"  value='<s:property value="remark"/>'  id="javaflagremark~<s:property value="%{#statusVar.count}"/>"/> 
                                                <input type="hidden"  value='<s:property value="rowid"/>'  id="javaFlagrowid~<s:property value="%{#statusVar.count}"/>"/> 

                                            </td>
                                            </div>
                                            </td>
                                        </tr>
                                    </s:iterator>
                                </s:if>   

                            </tbody>
                        </table>
                    </div>

                </div>


                <div _ngcontent-swn-c5="" class="col-md-6">

                    <div class="row">
                        <div _ngcontent-swn-c5="" class="col-md-6 text-center w-100">
                            <h4 _ngcontent-swn-c5="" class="text-primary">App Parameter Text Setting</h4>
                        </div>
                    </div>
                    <div _ngcontent-swn-c5="" class="overflow-hidden">
                        <table _ngcontent-swn-c5="" class="table table-striped " >
                            <thead _ngcontent-swn-c5="">
                                <tr _ngcontent-swn-c5="">
                                    <th _ngcontent-swn-c5="" class="td-report-sr"></th>
                                    <th class="text-center">Sr No.</th>
                                    <th _ngcontent-swn-c5="" class="td-seq-id">Parameter Name</th>
                                    <th _ngcontent-swn-c5="" class="td-report-desc">Parameter Value</th>
                                </tr>
                            </thead>
                            <tbody _ngcontent-swn-c5="" >
                                <s:if test="%{javatextparameter != null && !javatextparameter.isEmpty()}">
                                    <s:iterator value="javatextparameter" status="statusVar">
                                        <tr _ngcontent-swn-c5="">
                                            <td _ngcontent-swn-c5="" class="text-center">
                                                <span _ngcontent-swn-c5="" class="btn btn-primary addon-btn filter-inner-btn" onclick="openRemarkModel('<s:property value="remark"/>')">
                                                    <i _ngcontent-swn-c5="" class="fa fa-info-circle"></i>
                                                </span>
                                            </td>
                                            <td class="text-center"><s:property value="%{#statusVar.count}"/></td>
                                            <td _ngcontent-swn-c5=""><s:property value="parameter_name"/></td>
                                            <td _ngcontent-swn-c5=""><s:property value="parameter_value"/></td>
                                    <input type="hidden"  value='<s:property value="remark"/>'  id="javatextremark~<s:property value="%{#statusVar.count}"/>"/> 
                                    <input type="hidden"  value='<s:property value="rowid"/>'  id="javaTextrowid~<s:property value="%{#statusVar.count}"/>"/> 

                                    </tr>
                                </s:iterator>
                            </s:if>



                            </tbody>
                        </table>


                    </div>
                </div>

            </div>


        </div>

        <div _ngcontent-swn-c5="" class="tab-content px-2 pb-2 pt-5"  style="display:none;" id="oracle_parameter_div">

            <div class="col-md-12 text-right"> 
                <button type="button" class="form-btn" onclick="callUrl('/adminDashboard');"><i class="fa fa-chevron-left" aria-hidden="true"></i>Back</button>
            </div>
            <div _ngcontent-swn-c5="" class="row">
                <div _ngcontent-swn-c5="" class="col-md-6">
                    <div _ngcontent-swn-c5="" class="row">
                        <div _ngcontent-swn-c5="" class="col-md-6">
                            <h4 _ngcontent-swn-c5="" class="text-center">App Parameter Flag Setting</h4>
                        </div>
                    </div>
                    <div _ngcontent-swn-c5="" class="overflow-hidden">
                        <table _ngcontent-swn-c5="" class="table table-striped ">
                            <thead _ngcontent-swn-c5="">
                                <tr _ngcontent-swn-c5="">
                                    <th _ngcontent-swn-c5="" class="td-report-sr"></th>
                                    <th class="text-center">Sr No.</th>
                                    <th _ngcontent-swn-c5="" class="td-seq-id">Parameter Name</th>
                                    <th _ngcontent-swn-c5="" class="td-report-desc">Parameter Value</th>
                                </tr>
                            </thead>
                            <tbody _ngcontent-swn-c5="">
                                <s:if test="%{oracleflagparameter != null && !oracleflagparameter.isEmpty()}">
                                    <s:iterator value="oracleflagparameter" status="statusVar">
                                        <tr _ngcontent-swn-c5="">
                                            <td _ngcontent-swn-c5="" class="text-center">
                                                <span _ngcontent-swn-c5="" class="btn btn-primary addon-btn filter-inner-btn" >
                                                    <i _ngcontent-swn-c5="" class="fa fa-info-circle" onclick="openRemarkModel('<s:property value="remark"/>')"></i>
                                                </span>
                                            </td>
                                            <td class="text-center"><s:property value="%{#statusVar.count}"/></td>
                                            <td _ngcontent-swn-c5=""><s:property value="parameter_name"/></td>
                                            <td _ngcontent-swn-c5="">
                                                <s:if test="%{parameter_value.equalsIgnoreCase('T')}">
                                                    <div class="form-check form-switch">
                                                        <input class="form-check-input" type="checkbox"  onchange="updateParameterFlag(<s:property value="%{#statusVar.count}"/>, '<s:property value="parameter_name"/>', this.id, 'oracle');" id="SwitchChecked_oracle~<s:property value="%{#statusVar.count}"/>" checked>
                                                        <label id="oracleswitchFlag~<s:property value="%{#statusVar.count}"/>"><s:property value="parameter_value"/></label>
                                                    </div>
                                                </s:if>
                                                <s:else>
                                                    <div class="form-check form-switch">
                                                        <input class="form-check-input" type="checkbox" onchange="updateParameterFlag(<s:property value="%{#statusVar.count}"/>, '<s:property value="parameter_name"/>', this.id, 'oracle');" id="SwitchUnChecked_oracle~<s:property value="%{#statusVar.count}"/>">
                                                        <label id="oracleswitchFlag~<s:property value="%{#statusVar.count}"/>"><s:property value="parameter_value"/></label>
                                                    </div>   
                                                </s:else>
                                                <input type="hidden"  value='<s:property value="remark"/>'  id="oracleflagremark~<s:property value="%{#statusVar.count}"/>"/> 
                                                <input type="hidden"  value='<s:property value="rowid"/>'  id="oracleFlagrowid~<s:property value="%{#statusVar.count}"/>"/> 

                                            </td>
                                            </div>
                                            </td>
                                        </tr>
                                    </s:iterator>
                                </s:if>   

                            </tbody>
                        </table>


                    </div>




                </div>


                <div _ngcontent-swn-c5="" class="col-md-6">
                    <div _ngcontent-swn-c5="" class="row">
                        <div _ngcontent-swn-c5="" class="col-md-6">
                            <h4 _ngcontent-swn-c5="" class="text-center">App Parameter Text Setting</h4>
                        </div>
                    </div>
                    <div _ngcontent-swn-c5="" class="overflow-hidden">
                        <table _ngcontent-swn-c5="" class="table table-striped ">
                            <thead _ngcontent-swn-c5="">
                                <tr _ngcontent-swn-c5="">
                                    <th _ngcontent-swn-c5="" class="td-report-sr"></th>
                                    <th class="text-center">Sr No.</th>
                                    <th _ngcontent-swn-c5="" class="td-seq-id">Parameter Name</th>
                                    <th _ngcontent-swn-c5="" class="td-report-desc">Parameter Value</th>
                                </tr>
                            </thead>
                            <tbody _ngcontent-swn-c5="">
                                <s:if test="%{oracletextparameter != null && !oracletextparameter.isEmpty()}">
                                    <s:iterator value="oracletextparameter" status="statusVar">
                                        <tr _ngcontent-swn-c5="">
                                            <td _ngcontent-swn-c5="" class="text-center">
                                                <span _ngcontent-swn-c5="" class="btn btn-primary addon-btn filter-inner-btn" onclick="openRemarkModel('<s:property value="remark"/>')" >
                                                    <i _ngcontent-swn-c5="" class="fa fa-info-circle"></i>
                                                </span>
                                            </td>
                                            <td class="text-center"><s:property value="%{#statusVar.count}"/></td>
                                            <td _ngcontent-swn-c5=""><s:property value="parameter_name"/></td>
                                            <td _ngcontent-swn-c5=""><s:property value="parameter_value"/></td>
                                    <input type="hidden"  value='<s:property value="remark"/>'  id="oracletextremark~<s:property value="%{#statusVar.count}"/>"/> 
                                    <input type="hidden"  value='<s:property value="rowid"/>'  id="oracleTextrowid~<s:property value="%{#statusVar.count}"/>"/> 

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
                <button type="button" class="close" title="Close" data-dismiss="modal" aria-label="Close" onclick="closeremarkmodal();">
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

