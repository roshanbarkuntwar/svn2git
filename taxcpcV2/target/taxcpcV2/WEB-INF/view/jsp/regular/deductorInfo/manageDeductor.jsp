<%-- 
    Document   : manageDeductor
    Created on : Jan 31, 2019, 12:02:52 PM
    Author     : sneha.parpelli
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<link rel="stylesheet" href="resources/css/manageDeductor/manageDeductor.css" type="text/css">
<script type="text/javascript" src="resources/js/lhs/lhsGlobalValidation.js?r=<%= Math.random()%>"></script>
<script type = "text/javascript" src="resources/js/lhs/lhsGlobal.js?r=<%Math.random();%>" ></script>
<script type = "text/javascript" src="resources/js/lhs/lhsAjax.js?r=<%Math.random();%>" ></script>
<div class="page-section heading-and-tab-page">
    <div class="col-md-12 my-1  main-page-heading">
        <h4 class="page-title mb-2">Manage Deductor</h4>
    </div>
    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">

            <li class="nav-item mr-5"><a class="nav-link active" id="perInfo"  href="#permanentInformation"><p>  Permanent Information </p> </a></li>
            <li class="nav-item mr-5"><a class="nav-link" id="addInfo" href="#deductorAddress"><p> Deductor Address </p></a></li>
            <li class="nav-item mr-5" id="minInfoLi"><a class="nav-link"  id="minInfo"  href="#goverment"><p> Ministry Details</p></a></li>
            <li class="nav-item mr-5"><a class="nav-link"  id="conInfo" href="#responsiblePersonContactDetails"><p> Responsible Person Contact Details </p></a></li>

        </ul>
        <div class="clearfix"></div>       
    </div>
    <s:hidden id="h_client_catg_code" name="clientMastObj.client_catg_code"/>
    <s:form id="DeductorManipulationActionFormID" name="update_allDeductor" method="post" action="DeductorManipulationAction?action=updateDeductor" autocomplete="off"> 
        <s:hidden id="activeTab"/>
        <s:hidden id="hdnInfoLevel" name="deductorInfoLevel" value="PermanentInfo"/>
        <s:hidden id="hdnClientType" name="client_category_type"/>
        <s:hidden name="clientMastObj.entity_code"/>
        <s:hidden  id="saveAndExit" name="saveAndExit"/>
        <s:hidden id="parentFlag" value="%{#session.get('PARENTFLAG')}"/>
        <s:hidden id="childFlag" value="%{#session.get('CHILDFLAG')}"/>
        <s:hidden id="clientCode" name="clientCode"/>
        <s:hidden id="editIsClientLoginLevel" name="editIsClientLoginLevel"/>
        <s:hidden id="consolidate_chkbx_value" name="consolidate_chkbx_value"/>
        <s:hidden id="genLevelClientCode" name="genLevelClientCode"/>
        <s:hidden id="levelGenCount" name="levelGenCount"/>
        <s:hidden id="BFlagGenCount" name="BFlagGenCount"/>
        <s:hidden id="getChkBxFlag" name="getChkBxFlag"/>
       
    <input type="hidden" id="client_min_code"  name="minstatecode" value="<s:property value="%{minstatecode}"/>">
        <div class="clearfix"></div>
        <div class="tab-content px-4 py-4">
            <!--Form Validation Info-->
            <div class="text-center col-md-12" id="notificationMsgDiv" style="display:none">
                <div class="d-inline-block">
                    <div class="form-validation form-validation--info p-1 my-1">
                        <i class="fa fa-info-circle mr-2" aria-hidden="true"></i>
                        <h5 class="d-inline-block" id="notificationMsg">Please Fill Mandatory Fields</h5>
                    </div>
                </div>
            </div>
            <div id="permanentInformation" class="tab-pane form-wrapper show in active">
                <div class="container-fluid pt-2">
                    <div class="choose-file" style="background: #e2ecff;border-radius: 10px;">
                <div class="row form-group py-2" style="margin-top: 11px; display: flex;justify-items: center;align-items: center;">
                    <div class="col-lg-4 col-xl-4 text-right">

                        <label class="control-label">
                            Import From Consolidated/text File
                        </label>
                    </div>
                    <div class="col-lg-6 col-xl-4">
                        <div class="custom-file">
                            <input type="file" name="consolidatedTdsFile" class="custom-file-input form-control" id="validatedCustomFile" required
                                   onchange="$(this).next('.custom-file-label').html(this.files[0].name)">
                            <label class="custom-file-label" for="validatedCustomFile">Choose file...</label>
                        </div>
                    </div>
                    <div class="col-lg-1 col-xl-1">
                        <button type="button" class="form-btn mt-0" id="uploadTdsFileBtn" onclick="validateUpdateBtn();" title="Upload Deductor Details From Consolidated/text File"><i class="fa upload" aria-hidden="true"></i>Upload</button>
                    </div>
                </div>
            </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">

                            <label class="control-label">
                                TAN No <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control" id="tanno" placeholder="Enter TAN No"  onblur="validateTAN(this);"  maxlength="10" cssStyle="text-transform: uppercase;" name="clientMastObj.tanno" />
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">

                            <label class="control-label">
                                Deductor Name <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <div class="input-group">
                                <s:textfield type="text" cssClass="form-control" id="client_name" placeholder="Enter Deductor Name" name="clientMastObj.client_name"/>
                                <div class="input-group-append">
                                    <button type="button" class="btn btn-primary btn-sm addon-btn" title="Update from ITD" onclick="getDeductorNameFromCsiFile();"><i class="fa fa-magnet"></i></button>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                PAN No. <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>

                        <div class="col-lg-5 col-xl-4">
                            <s:textfield type="text" class="form-control" id="panno" placeholder="Enter PAN No." name="clientMastObj.panno" cssStyle="text-transform: uppercase;" onblur="validatePAN(this);check_deductor_type(this.id);PanNoIsBlank()" oncontextmenu="return false;" />
                        </div>
                        <div class="col-lg-2 col-xl-2 text-left">
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="customCheck1" onclick="pannotreqdCheckbx(this)">
                                <label class="custom-control-label" for="customCheck1">PAN Not Required</label>
                            </div>
                        </div>

                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">

                            <label class="control-label">
                                Deductor Category <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:select list="selectClientCatg" name="clientMastObj.client_catg_code" id="client_catgID" onchange="selectClientTypeAsCatg(); validateMinistryDetails();"  cssClass="form-control" cssStyle="text-transform: uppercase;" />
                            <!--                        <select class="form-control">
                                                        <option>--Select--</option>
                                                        <option>Option One</option>
                                                        <option>Option Two</option>
                                                        <option>Option Three</option>
                                                    </select>-->
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">

                            <label class="control-label">
                                Branch/Division (If any)
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control" id="branch_division" placeholder="Enter Branch/Division (If any)" name="clientMastObj.branch_division" />
                        </div>
                    </div>
                    <s:hidden id="client_catg_name" name="clientMastObj.client_type_code"/>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">

                            <label class="control-label">
                                Traces ID
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control" id="traces_id" placeholder="Enter Traces ID" name="clientMastObj.traces_id"/>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Traces Password 
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <div class="input-group">
                                <s:textfield type="password" cssClass="form-control" id="traces_pwd" placeholder="Enter Traces Password " name="clientMastObj.traces_pwd"/>
                                <div class="input-group-append">
                                    <button type="button" class="btn btn-primary btn-sm addon-btn" title="Show Password" onclick="togglePasswordFields('traces_pwd', 'showHidePassword');"><i id="showHidePassword" class="fa fa-eye"  aria-hidden="true" title="Show Password" ></i></button>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">

                            <label class="control-label">
                                E-filing Password
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <div class="input-group">
                                <s:textfield type="password" cssClass="form-control" id="web1_login_pwd" placeholder="Enter E-filing Password" name="clientMastObj.web1_login_pwd"/>
                                <div class="input-group-append">
                                    <button type="button" class="btn btn-primary btn-sm addon-btn" title="Show Password" onclick="togglePasswordFields('web1_login_pwd', 'showPassword');"><i id="showPassword" class="fa fa-eye"  aria-hidden="true" ></i></button>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">

                            <label class="control-label">
                                GST No.
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control" id="gstn_no" placeholder="Enter GST No." cssStyle="text-transform: uppercase;" onblur="validateGst(this);" name="clientMastObj.gstn_no"/>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">

                            <label class="control-label">
                                Bank Branch
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control" id="bank_branch_code" placeholder="Enter Bank Branch"  onkeypress=" return lhsValidateAlphaAndNumber(event);" name="clientMastObj.bank_branch_code"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            &nbsp;
                        </div>
                        <div class="col-md-5">
                            <div class="form-group" cssStyle="float:left;">
                                <s:if test="%{getChkBxFlag!= null && getChkBxFlag.equalsIgnoreCase('T')}">

                                    <s:hidden id="bflag_value" name="bflag_value" value="0"/>
                                    <label for="exampleInputName2" style="float: left; display:none">
                                        <s:checkbox name="bflag_chkbx" id="bflag_chkbx"  onclick="getCheckboxValue(this.id);"  cssStyle="position: relative;  opacity: 1; margin-left: 5px; margin-right: 5px; display:inline;" ></s:checkbox>
                                            Auto Generate reference No. for 15G/15H</label>
                                    </s:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>            
            <div id="deductorAddress" class="tab-pane form-wrapper fade">
                <div class="container-fluid pt-2">
                    <div class="row form-group pt-2">
                        <div class="col-lg-4 col-xl-4 text-right">

                            <label class="control-label">
                                Address <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control" id="add1" placeholder="Enter Address" name="clientMastObj.add1" maxLength="25"/>
                        </div>
                    </div>
                    <div class="row form-group">

                        <div class="col-lg-7 offset-lg-4 col-xl-4 offset-xl-4">

                            <s:textfield type="text" cssClass="form-control" id="add2" placeholder="Enter Street" name="clientMastObj.add2" maxLength="25"/>
                        </div>
                    </div>
                    <div class="row form-group">

                        <div class="col-lg-7 offset-lg-4 col-xl-4 offset-xl-4">

                            <s:textfield type="text" cssClass="form-control" id="add3" placeholder="Enter LandMark" name="clientMastObj.add3" maxLength="25"/>
                        </div>
                    </div>
                    <div class="row form-group">

                        <div class="col-lg-7 offset-lg-4 col-xl-4 offset-xl-4">

                            <s:textfield type="text" cssClass="form-control" id="add4" placeholder="Enter Area" name="clientMastObj.add4" maxLength="25"/>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                PIN Code<span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>

                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" class="form-control" id="pin" placeholder="Enter PIN Code" maxlength="6" onblur="pinCode(this);" onkeypress="return lhsIsNumber(event);" name="clientMastObj.pin"/>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">

                            <label class="control-label">
                                City <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield  name="clientMastObj.city_code" onkeypress="lhsValidateAlphabet(event);" id="address_city_code" cssClass="form-control" placeholder=" Enter City"  cssStyle="width:100%;text-transform: uppercase;" />
                        </div>
                    </div>
                    <div class="row form-group">    
                        <div class="col-lg-4 col-xl-4 text-right">

                            <label class="control-label">
                                State <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:select list="select_state" name="clientMastObj.state_code" id="address_state_code" onchange="selectAddressCity();" headerKey="" headerValue="SELECT" cssClass="form-control" cssStyle="width:100%;text-transform: uppercase;" />
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">

                            <label class="control-label">
                                STD Code/Telephone<span class="text-danger font-weight-bold ml-1"></span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <div class="input-group">
                                <s:textfield type="text" cssClass="form-control" id="stdcode" placeholder="Enter STD Code" onkeypress="return lhsIsNumber(event);"  maxlength="10" name="clientMastObj.stdcode"/>
                                <s:textfield type="text" cssClass="form-control" id="phoneno" placeholder="Enter Telephone No." onkeypress="return lhsIsNumber(event);" maxlength="10" name="clientMastObj.phoneno"/>
                                <!--                                <input type="text" name="" value="0712" id="" class="form-control" placeholder="Enter Telephone No.">-->
                            </div>                         
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Email <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control" id="email_id" placeholder="Enter Email" name="clientMastObj.email_id"/>
                        </div>
                    </div>
                    <!--                    <div class="col-lg-11 form-group text-right pr-1">
                                            <button type="button" class="btn btn-primary btn-sm" data-toggle="collapse" data-target="#alternativeContactDetails">
                                                Alternative Contact Details
                                            </button>
                                        </div>-->

                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                STD Code(Alternative)
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" class="form-control" id="alternate_stdcode" placeholder="Enter STD Code(Alternative)" maxlength="10" onkeypress="return lhsIsNumber(event);" name="clientMastObj.alternate_stdcode"/>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Telephone Number(Alternative)
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" class="form-control" id="alternate_phoneno" placeholder="Enter Telephone Number(Alternative)" onkeypress="return lhsIsNumber(event);" maxlength="10" name="clientMastObj.alternate_phoneno"/>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Mobile Number(Alternative)
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" class="form-control" id="alternate_mobileno" placeholder="Enter Mobile Number(Alternative)" onkeypress="return lhsIsNumber(event);" maxlength="10" name="clientMastObj.alternate_mobileno"/>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Email (Alternative)
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" class="form-control" id="alternate_email_id" placeholder="Enter Email (Alternative)" onblur="validateEmail(this);" name="clientMastObj.alternate_email_id"/>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>               
            </div>
            <div id="responsiblePersonContactDetails" class="tab-pane form-wrapper fade">
                <div class="container-fluid pt-2">

                    <div class="row form-group pt-2">
                        <div class="col-lg-4 col-xl-4 text-right">

                            <label class="control-label">
                                Responsible Person Name <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control" id="deductor_name" placeholder=" Enter Deductor Name" name="clientMastObj.deductor_name"/>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Responsible Person PAN No.<span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>

                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" class="form-control" id="deductor_panno" cssStyle="text-transform: uppercase;" onblur="validatePAN(this);" placeholder="Enter Deductor PAN" name="clientMastObj.deductor_panno" />
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">

                            <label class="control-label">
                                Responsible Person Designation  <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control" id="deductor_desig" placeholder=" Enter Deductor Designation" name="clientMastObj.deductor_desig"/>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Responsible Person Mobile No.  <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control" id="deductor_mobileno" placeholder="Enter Mobile No." maxlength="10" onkeypress="return lhsIsNumber(event);" name="clientMastObj.deductor_mobileno"/>
                        </div>
                    </div>
                        <div id="phonno_lbl">
                    <div class="row form-group" >
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                Responsible Person STD Code/Telephone No.<span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-3 col-xl-2">
                            <s:textfield type="text" cssClass="form-control" id="deductor_stdcode" placeholder="Enter Resp. Person STD Code" onkeypress="return lhsIsNumber(event);" name="clientMastObj.deductor_stdcode"/>
                        </div>
                        <div class="col-lg-4 col-xl-2">
                            <s:textfield type="text" cssClass="form-control" id="deductor_phoneno" placeholder="Enter Resp. Person Telephone No." onkeypress="return lhsIsNumber(event);" name="clientMastObj.deductor_phoneno"/>
                        </div>
                    </div>
                        </div>
                        <div class="row form-group" style="margin-bottom: 12px;" >
                            <div class="col-lg-4 offset-lg-7 col-xl-4 offset-xl-4">
                        <div class="input-group">
                            <div class="input-group-append">
                                <div class="input-group-text line-height-2"> 
                                    <s:if test="%{clientMastObj.geo_org_code.equalsIgnoreCase('T') && clientMastObj.geo_org_code !=null}">
                                        <input  checked type="checkbox"  name="clientMastObj.geo_org_code" id="geo_org_code"   onclick="getCopyDeductorAddress(this.id);"/>
                                    </s:if>
                                    <s:else>                                   
                                        <s:textfield  type="checkbox"  name="clientMastObj.geo_org_code" id="geo_org_code"  onclick="getCopyDeductorAddress(this.id);"/>
                                    </s:else>
                                </div>
                            </div>
                            <input type="text" class="form-control" placeholder="Copy Deductor Address" id="copyDeductorAddress" name="copyDeductorAddress" title="Copy Deductor Address"  readonly >
                            
                        </div>
<!--                        <div class="custom-control custom-checkbox">
                            <input type="checkbox"  id="copy_deductor_address_flag" onclick="getCopyDeductorAddress(this.id);" class="custom-control-input" >
                            <label class="custom-control-label" for="copy_deductor_address_flag">Copy Deductor Address</label>
                        </div>-->
                    </div>
                        </div>
                    <div id="respCntctDtlsAdd1">
                    <div class="row form-group" >
                        <div class="col-lg-4 col-xl-4 text-right">

                            <label class="control-label">
                                Responsible Person Address  <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control copied-field" id="deductor_add1" placeholder="Enter Address" name="clientMastObj.deductor_add1"/>
                        </div>
                    </div>
                        </div>
                    <div class="row form-group" id="respCntctDtlsAdd2">

                        <div class="col-lg-7 offset-lg-4  col-xl-4 offset-xl-4">

                            <s:textfield type="text" cssClass="form-control copied-field" id="deductor_add2" placeholder="Enter Street" name="clientMastObj.deductor_add2"/>
                        </div>
                    </div>
                        <div id="respCntctDtlsAdd3">
                    <div class="row form-group" >

                        <div class="col-lg-7 offset-lg-4  col-xl-4 offset-xl-4">

                            <s:textfield type="text" cssClass="form-control copied-field" id="deductor_add3" placeholder="Enter LandMark" name="clientMastObj.deductor_add3"/>
                        </div>
                    </div>
                        </div>
                        <div id="respCntctDtlsAdd4">
                    <div class="row form-group">

                        <div class="col-lg-7 offset-lg-4  col-xl-4 offset-xl-4">

                            <s:textfield type="text" cssClass="form-control copied-field" id="deductor_add4" placeholder="Enter Area" name="clientMastObj.deductor_add4"/>
                        </div>
                    </div>
                        </div>
                         <div id="pin_lbl">
                    <div class="row form-group" >
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                PIN Code<span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>

                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" class="form-control copied-field" id="deductor_pin" placeholder="Enter Pin Code" maxlength="6" onblur="pinCode(this);" onkeypress="return lhsIsNumber(event);" name="clientMastObj.deductor_pin"/>
                            <span id="pinErrorSpan" class="text-danger " style="display:none"></span>
                        </div>
                    </div>
                         </div>
                        <div id="city_lbl">    
                     <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                City <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:textfield type="text" cssClass="form-control copied-field" id="deductor_city_code" placeholder="Enter City" onkeypress="lhsValidateAlphabet(event);" name="clientMastObj.deductor_city_code"/>
                        </div>
                    </div>
                        </div>
                        <div id="state_lbl">
                    <div class="row form-group">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <label class="control-label">
                                State <span class="text-danger font-weight-bold ml-1">*</span>
                            </label>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <s:select list="select_state" name="clientMastObj.deductor_state_code" id="deductor_state_code" onchange="selectDeductorCity();" headerKey="-1" headerValue="SELECT" cssClass="form-control copied-field" cssStyle="width:100%;text-transform: uppercase;" />
                        </div>
                    </div>
                        </div>
                </div>
            </div>
            <div id="goverment" class="tab-pane form-wrapper fade">
                 
                <div class="container-fluid pt-2">
                    <div class="row pt-2">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <div class="form-group">
                                <label for="exampleInputName2">State<span id="stateCodeLbl"></span></label>
                            </div>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <div class="form-group">
                                <s:select list="select_state" name="clientMastObj.ministry_state_code" id="ministry_state_code" headerKey="" headerValue="SELECT" cssClass="form-control" cssStyle="width:100%;text-transform: uppercase;" onchange="selectAddressCity();"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <div class="form-group">
                                <label for="exampleInputName2">Ministry/Department Name<span id="minstryNameLbl"></span></label>
                            </div>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <div class="form-group">
                                <s:select list="selectMinistryCode" name="clientMastObj.ministry_code" id="ministry_code"  cssClass="form-control" cssStyle="width:100%;text-transform: uppercase;"  placeholder="Ministry/Department Name" onchange="onChangeMinistryName();"/>                           
                            </div>
                        </div>
                    </div>
                    <div class="row" id="ministry_code_other_div" style="display: none;">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <div class="form-group">
                                <label for="exampleInputName2">Ministry/Department Name(Others)</label>
                            </div>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <div class="form-group">
                                <%--<s:select list="selectSubMinistryCode" name="objlist.sub_ministry_code" id="sub_ministry_code" cssClass="select_tag" cssStyle="width:100%;text-transform: uppercase;" placeholder="Ministry/Department Name"/>--%>
                                <s:textfield name="clientMastObj.ministry_name_other" id="sub_ministry_code" cssClass="form-control" cssStyle="width:100%;text-transform: uppercase;" placeholder="Ministry/Department Name(Other)"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <div class="form-group">
                                <label>DDO Code<span id="DDO_Code"></span></label>
                            </div>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <div class="form-group">
                                <s:textfield name="clientMastObj.ddo_code" id="ddo_code" cssClass="form-control" placeholder="DDO Code" maxlength="20" onkeypress=" return lhsIsNumber(event);" onblur="validateDDo(this);" minlength="8" cssStyle="text-transform: uppercase;" />
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <div class="form-group">
                                <label>PAO Code<span id="PAO_Code"></span></label>
                            </div>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <div class="form-group">
                                <s:textfield name="clientMastObj.pao_code" id="pao_code" cssClass="form-control" placeholder="PAO Code" onkeypress=" return lhsIsNumber(event);" onblur="validatePAO(this);" maxlength="20"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-4 col-xl-4 text-right">
                            <div class="form-group">
                                <label for="exampleInputName2">Account Officer Identification(AIN) Number<span id="AinLbl"></span></label>
                            </div>
                        </div>
                        <div class="col-lg-7 col-xl-4">
                            <div class="form-group">
                                <s:textfield name="clientMastObj.ain_no" id="ain_no" cssClass="form-control" placeholder="Account Officer Identification(AIN) Number" onkeypress=" return lhsIsNumber(event);" onblur="validateAIN(this);" maxlength="7"/>
                            </div>
                        </div>
                    </div>                
                </div>
            </div>
            <div class="form-group">
                <div class="row">
                    <div class="button-section col-md-12 my-1 text-center"> 
                        <s:a href="" id="previous" cssStyle="visibility: hidden;" onclick="getPreviousTab();" >  <button type="button" class="form-btn" ><i class="fa previous" aria-hidden="true"></i>Previous</button></s:a>
                            <button type="button" id="saveBtn" class="form-btn"  onclick="return updateDataByAjax(this.id, 'save');" ><i class="fa save" aria-hidden="true"></i>Save & Next</button>
                            <button type="button" id="saveExitBtn"  class="form-btn" onclick="return updateDataByAjax(this.id, 'saveAndExit');"><i class="fa save" aria-hidden="true"></i>Save & Exit</button>
                        <s:if test="%{enableNilReturnBtn.equals('true')}">
                            <button type="button" id="nilBackBtn" class="form-btn" onclick="callUrl('/nilReturn');"><i class="fa fa-exchange" aria-hidden="true"></i>Back to Nil Return</button>
                        </s:if>
                    </div>
                </div>
            </div>
        </div>
    </s:form>
</div>
<s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
<script src="resources/js/regular/deductorInfo/manageDeductor.js?r=<%= Math.random()%>"></script>     
<script>
                                try {
                                    selectClientCatg();
                                } catch (err) {
                                    console.log("er--" + err);
                                }
</script>
<script>
    var checkflag = document.getElementById("geo_org_code").checked;
   if (checkflag) {
       document.getElementById("copyDeductorAddress").value ="Deductor Address Copied !" ;
       $("#deductor_add1").addClass("copied-field");
        $("#deductor_add2").addClass("copied-field");
        $("#deductor_add3").addClass("copied-field");
        $("#deductor_add4").addClass("copied-field");
        $("#deductor_city_code").addClass("copied-field");
        $("#deductor_pin").addClass("copied-field");
        $("#deductor_state_code").addClass("copied-field");  
   }
   else{
       document.getElementById("copyDeductorAddress").value ="" ;
        $("#deductor_add1").removeClass("copied-field");
        $("#deductor_add2").removeClass("copied-field");
        $("#deductor_add3").removeClass("copied-field");
        $("#deductor_add4").removeClass("copied-field");
        $("#deductor_city_code").removeClass("copied-field");
        $("#deductor_pin").removeClass("copied-field");
        $("#deductor_state_code").removeClass("copied-field");
   }
    console.log(checkflag);
    try {
        var isMinistry = checkMinistry();
        if (isMinistry) {
            // open ministry tab
            $("#minInfoLi").removeAttr("style");
            document.getElementById("minInfoLi").style.display = "inline-list-item";
            document.getElementById("minInfoLi").style.pointerEvents = "none";
        } else {
            //   close min
            document.getElementById("minInfoLi").style.display = "none";
        }
    } catch (err) {
    }
    
    try{
        checkstatenull();
    }catch{
        
    }
   
</script>
