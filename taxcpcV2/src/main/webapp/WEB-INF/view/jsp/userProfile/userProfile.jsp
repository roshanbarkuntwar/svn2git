<%-- 
    Document   : changePassword
    Created on : Jan 29, 2019, 5:28:26 PM
    Author     : neha.bisen
--%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<%@include file="/WEB-INF/view/jsp/global/alertBoxes.jsp" %>
<link rel="stylesheet" href="resources/css/changePassword/changePassword.css" type="text/css">
<script type="text/javascript" src="resources/js/lhs/lhsAjax.js"></script>
<script type="text/javascript" src="resources/js/userProfile/userProfile.js"></script>
<script type="text/javascript" src="resources/js/lhs/lhsGlobalValidation.js?r=<%= Math.random()%>"></script>
<!--Body Start Here-->
<div class="page-section">
    <div class="container-fluid ">
        <%@include file="/WEB-INF/view/jsp/global/validationMessages.jsp"%>
        <form id="userProfileForm" autocomplete="off">
            <s:hidden id="user_code" name="loginUser.user_code"/>
            <div class="col-md-12 my-1"> <h4 class="page-title mb-2">Update User Profile</h4></div>
            <div class="tab-content form-wrapper p-2">
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-4 text-right">
                        <label class="control-label">
                            TAN No
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-4">
                        <s:textfield name="tanNo" class="form-control" placeholder="TAN Number" readonly="true"/>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-4 text-right">
                        <label class="control-label">
                            Branch Code
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-4">
                        <s:textfield name="bankCode" class="form-control" placeholder="Branch Code" readonly="true"/>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-4 text-right">
                        <label class="control-label">
                            Branch Name
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-4">
                        <s:textfield name="bankName" class="form-control" placeholder="Branch Name" readonly="true"/>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-4 text-right">
                        <label class="control-label">
                            Login ID<span class="text-danger font-weight-bold ml-1">*</span>
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-4">
                        <s:textfield name="loginUser.login_id" class="form-control" id="loginId" readonly="true" placeholder="Enter Login ID"/>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-4 text-right">
                        <label class="control-label">
                            Name<span class="text-danger font-weight-bold ml-1">*</span>
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-4">
                        <s:textfield name="loginUser.user_name" class="form-control" id="userName" placeholder="Enter Name"/>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-4 text-right">
                        <label class="control-label">
                            Short Name<span class="text-danger font-weight-bold ml-1">*</span>
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-4">
                        <s:textfield name="loginUser.short_name" class="form-control" id="shortName" placeholder="Enter Short Name"/>
                    </div>
                </div>
                
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-4 text-right">
                        <label class="control-label">
                            Old Password
                        </label>
                    </div>
                    <div class="col-lg-5 col-xl-4 ">
                        <div class="input-group">
                            <input type="password" name="" value="<s:property value="%{loginUser.login_pwd}"/>" id="oldPassword" class="form-control" readonly="true">
                            <div class="input-group-append">
                                <button type="button" class="btn btn-primary" onclick="togglePasswordFields('oldPassword', 'oldPasswordIcon')" style="margin-right: 12px;"><i id="oldPasswordIcon" class="fa fa-eye" title="Show Password"></i></button>
                                 
                                <button type="button" class="btn btn-primary " data-toggle="collapse" data-target="#showPasswordFeilds" title="Change Password">
                                               <i class="fa fa-pencil"></i>
                                </button>
                            </div>
                        </div>
                    </div>

                   
                </div>
                <div class="collapse" id="showPasswordFeilds">
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-4 text-right">
                        <label class="control-label">
                            Change Password
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-4">
                        <div class="input-group">
                            <input type="password" name="loginUser.login_pwd"  id="newPassword" class="form-control" placeholder="Enter New Password" onkeypress="return AvoidSpace(event);">
                            <div class="input-group-append">
                                <button type="button" class="btn btn-primary btn-sm addon-btn" onclick="toggle_password_fields('newPassword', 'newPasswordIcon')"><i id="newPasswordIcon" class="fa fa-eye"></i></button>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-4 text-right">
                        <label class="control-label">
                            Confirm New Password
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-4">
                        <div class="input-group">
                            <input type="password" name=""  id="confirmNewPassword" class="form-control" placeholder="Enter Confirm New Password" onkeypress="return AvoidSpace(event);" >
                            <div class="input-group-append">
                                <button type="button" class="btn btn-primary btn-sm addon-btn" onclick="toggle_password_fields('confirmNewPassword', 'confirmNewPasswordIcon')"><i id="confirmNewPasswordIcon" class="fa fa-eye"></i></button>
                            </div>
                        </div>
                    </div>
                </div>
                 </div>
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-4 text-right">
                        <label class="control-label">
                            Transaction Rights 
                        </label>
                    </div>
                    <div class="col-lg-8 col-xl-8">
                        <div class="custom-control custom-control-inline custom-checkbox">
                            <s:if test="%{loginUser.add_right == 1}">
                                <input type="checkbox" class="custom-control-input" id="AddRights" checked="true" disabled>
                            </s:if>
                            <s:else>
                                <input type="checkbox" class="custom-control-input" id="AddRights" disabled>
                            </s:else>
                            <label class="custom-control-label" for="Add">Add</label>
                        </div>                 
                        <div class="custom-control custom-control-inline custom-checkbox">
                            <s:if test="%{loginUser.edit_right == 1}">
                                <input type="checkbox" class="custom-control-input" id="EditRights" checked="true" disabled>
                            </s:if>
                            <s:else>
                                <input type="checkbox" class="custom-control-input" id="EditRights" disabled>
                            </s:else>
                            <label class="custom-control-label" for="Edit">Edit</label>
                        </div>
                        <div class="custom-control custom-control-inline custom-checkbox">
                            <s:if test="%{loginUser.delete_right == 1}">
                                <input type="checkbox" class="custom-control-input" id="DeleteRights" checked="true" disabled>
                            </s:if>
                            <s:else>
                                <input type="checkbox" class="custom-control-input" id="DeleteRights" disabled>
                            </s:else>
                            <label class="custom-control-label" for="Delete">Delete</label>
                        </div>  
                        <div class="custom-control custom-control-inline custom-checkbox">
                            <s:if test="%{loginUser.special_right == 1}">
                                <input type="checkbox" class="custom-control-input" id="SpecialRights" checked="true" disabled>
                            </s:if>
                            <s:else>
                                <input type="checkbox" class="custom-control-input" id="SpecialRights" disabled>
                            </s:else>
                            <label class="custom-control-label" for="SpecialRights">Special Rights</label>
                        </div>  
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4 offset-md-4">
                        <div class="form-group  text-center">
                            <button type="button" class="btn btn-primary w-50 mx-auto rounded-0" onclick="updateUserProfile();">Update</button>
                        </div>               
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
