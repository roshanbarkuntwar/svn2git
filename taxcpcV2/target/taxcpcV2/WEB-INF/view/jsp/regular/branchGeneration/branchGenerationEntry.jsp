<%-- 
    Document   : branchGenerationEntry
    Created on : Nov 21, 2019, 5:58:08 PM
    Author     : gaurav.khanzode
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script src="resources/js/lhs/lhsGlobal.js?r=<%= Math.random()%>"></script>
<!DOCTYPE html>

<div class="page-section heading-and-tab-page">
    <div class="col-md-12 my-1  main-page-heading">
        <h4 class="page-title mb-2">Bank Branch Generation</h4>
    </div>

    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">
            <li class="nav-item mr-5"><a class="nav-link" id="" href="branchGenerationBrowse"><p>Branch Details</p> </a></li>
            <li class="nav-item mr-5"><a class="nav-link active" id="" href="#branchDetails"><p>Branch Entry</p> </a></li>
            <li class="nav-item mr-5"><a class="nav-link" id="" href="#branchAddress" disabled><p>Branch Address</p></a></li>
            <li class="nav-item mr-5"><a class="nav-link" id="" href="#responsiblePersonDetails"><p>Responsible Person Details</p></a></li>
        </ul>
        <div class="clearfix"></div>
    </div>

    <s:form id="branchGenerationForm" name="branchGenerationForm" method="POST" action="branchGeneration" autocomplete="off">
        <s:hidden value="save" id="action" name="action"/>

        <s:set var="formMode" value="%{clientMastObj.client_code == null ? 'saveMode' : 'updateMode'}" />

        <s:if test="%{#formMode == 'updateMode'}">
            <s:hidden id="client_code" name="clientMastObj.client_code"/>
        </s:if>
        <s:hidden id="formMode" name="formMode" value="%{formMode}"/>

        <div class="tab-content px-4 py-4">
            <!--Form Validation Info-->
            <div class="text-center col-md-12" id="notificationMsgDiv" style="display:none">
                <div class="d-inline-block">
                    <div class="form-validation form-validation--info p-1 my-1">
                        <i class="fa fa-info-circle mr-2" aria-hidden="true"></i>
                        <h5 class="d-inline-block" id="notificationMsg"></h5>
                    </div>
                </div>
            </div>

            <div id="branchDetails" class="tab-pane show in active">
                <div class="container-fluid pt-2">

                    <div class="row form-group pt-2">
                        <div class="col-lg-4 col-xl-4 text-right mt-1">
                            <label class="control-label">
                                Code Level<span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:select cssClass="form-control" list="loginLevelList" id="bank_code_level" headerKey="" headerValue="Select Code Level"
                                      name="clientMastObj.code_level" onchange="getBankBranchDetails();"/>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right mt-1">
                            <label class="control-label">
                                Bank Branch Code<span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield cssClass="form-control" onkeypress="lhsValidateAlphaNumericWithoutSpclChars(event);" id="bank_branch_code" placeholder="Enter Bank Branch Code" name="clientMastObj.bank_branch_code"/>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right mt-1">
                            <input type="hidden" id="immBranchRequiredFlag" value="true">
                            <label class="control-label">
                                Immediate Bank Branch Code<span class="text-danger font-weight-bold ml-1" id="immBranchRequiredSpan">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <select class="form-control" id="imm_bank_branch_code" placeholder="" name="clientMastObj.parent_code">
                                <option value="">Select Immediate Bank Branch Code</option>
                                <s:if test="%{action.equalsIgnoreCase('edit') && clientMastObj.bank_branch_code != null}">
                                    <option value="<s:property value="clientMastObj.client_code"/>" selected><s:property value="clientMastObj.client_name"/> - <s:property value="clientMastObj.client_name"/></option>
                                </s:if>
                            </select>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right mt-1">
                            <label class="control-label">
                                Bank Branch Name<span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield cssClass="form-control" id="bank_branch_name" onkeypress="lhsValidateAlphaNumericWithoutSpclChars(event);" placeholder="Enter Bank Branch Name" name="clientMastObj.branch_division"/>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right mt-1">
                            <label class="control-label">
                                PAN No<span class="text-danger font-weight-bold ml-1"></span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <div class="input-group">
                                <s:textfield type="text" name="clientMastObj.panno" id="panno" class="form-control" cssStyle="text-transform: uppercase;" onblur="validatePANNO(this);" placeholder="Enter PAN No"/>
                            </div>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                TAN No<span class="text-danger font-weight-bold ml-1"></span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control text-uppercase" id="tanno" placeholder="Enter TAN No" onblur="validateTANNO(this);" name="clientMastObj.tanno"/>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Client Bank Name<span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control" id="client_bank_name" onkeypress="lhsValidateAlphaNumericWithoutSpclChars(event);" placeholder="Enter Client Bank Name" name="clientMastObj.client_name"/>
                        </div>
                    </div>

                    <s:if test="%{#formMode == 'saveMode'}">
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Client Login Id<span class="text-danger font-weight-bold ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-7 col-xl-4">
                                <s:textfield type="text" cssClass="form-control" id="login_id" placeholder="Enter Login Id" name="clientMastObj.login_id"/>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">
                                <label class="control-label">
                                    Client Login Password<span class="text-danger font-weight-bold ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-7 col-xl-4">
                                <div class="input-group">
                                    <s:textfield type="password" cssClass="form-control" id="login_password" onkeypress="return AvoidSpace(event);" placeholder="Enter Login Password" name="clientMastObj.login_pwd"/>
                                    <div class="input-group-append">
                                        <button type="button" class="btn btn-primary btn-sm addon-btn" onclick="togglePasswordFields('login_password', 'login_passwordIcon')"><i id="login_passwordIcon" class="fa fa-eye" title="Show Password"></i></button>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </s:if>
                </div>
            </div>

            <div id="branchAddress" class="tab-pane fade">
                <div class="container-fluid pt-2">
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Address <span class="text-danger font-weight-bold ml-1"></span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control" id="add_1" placeholder="Enter Address" name="clientMastObj.add1" maxlength="25"/>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                <span class="text-danger font-weight-bold ml-1"></span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control" id="add_2" placeholder="Enter Street" name="clientMastObj.add2" maxlength="25"/>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                <span class="text-danger font-weight-bold ml-1"></span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control" id="add_3" placeholder="Enter Landmark" name="clientMastObj.add3" maxlength="25"/>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                <span class="text-danger font-weight-bold ml-1"></span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control" id="add_4" placeholder="Enter Area" name="clientMastObj.add4" maxlength="25"/>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                PIN Code<span class="text-danger font-weight-bold ml-1"></span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control" id="pin_code" onkeypress="return lhsIsNumber(event);" placeholder="Enter Pincode" name="clientMastObj.pin" maxlength="6" onblur="getLocalityDetails(this.value);validatePinCode(this);"/>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                City<span class="text-danger font-weight-bold ml-1"></span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control" id="city" placeholder="Enter City" name="clientMastObj.city_code"/>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                State<span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:select cssClass="form-control" list="select_state" headerKey="" headerValue="Select" id="state" placeholder="" name="clientMastObj.state_code"/>
                        </div>
                    </div>

                </div>
            </div>

            <div id="responsiblePersonDetails" class="tab-pane fade">
                <div class="container-fluid pt-2">
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Name<span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control text-uppercase" id="resp_person_name" placeholder="Enter Person Name" onkeypress="return lhsValidateAlphabet(event);" name="clientMastObj.deductor_name"/>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Designation<span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control text-uppercase" id="resp_person_desig" placeholder="Enter Person Designation" name="clientMastObj.deductor_desig"/>
                        </div>
                    </div>                    
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Mobile Number<span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control" id="resp_person_mobile" placeholder="Enter Person Mobile Number" 
                                         onkeypress="return lhsIsNumber(event);" name="clientMastObj.deductor_mobileno" maxlength="10"/>
                        </div>
                    </div>                    
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Email Id<span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control text-uppercase" id="resp_person_mail" placeholder="Enter Person Email Id" onblur="validateEmailId(this);" name="clientMastObj.deductor_email_id"/>
                        </div>
                    </div>                    
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                PAN Number<span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control text-uppercase" id="resp_person_panno" onclick="validatePANNO(this);" placeholder="Enter Person PAN Number" name="clientMastObj.deductor_panno"/>
                        </div>
                    </div>                    
                </div>
            </div>
            <div class="form-group">
                <div class="row">
                    <div class="button-section col-md-12 my-1 text-center">
                        <button type="button" id="previousBtn" class="form-btn" onclick="goToPreviousTab();" style="display: none;"><i class="fa previous" aria-hidden="true"></i>Previous</button>
                        <button type="button" id="saveNextBtn" class="form-btn" onclick="saveNewBranchDataAjax('saveNext');"><i class="fa save" aria-hidden="true"></i><s:property value="%{#formMode.equals('saveMode') ? 'Save' : 'Update'}"/> & Next</button>
                        <button type="button" id="saveExitBtn" class="form-btn" onclick="saveNewBranchDataAjax('saveExit');" style="display: none;"><i class="fa save" aria-hidden="true"></i><s:property value="%{#formMode.equals('saveMode') ? 'Save' : 'Update'}"/> & Exit</button>
                        <button type="button" id="cancelBtn" class="form-btn" onclick="window.location='./branchGenerationBrowse';"><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                    </div>
                </div>
            </div>
        </div>

    </s:form>
</div>
<script type="text/javascript" src="resources/js/lhs/lhsGlobalValidation.js?r=<%=Math.random()%>"></script>
<script type="text/javascript" src="resources/js/regular/branchGeneration/branchGeneration.js?r=<%=Math.random()%>"></script>