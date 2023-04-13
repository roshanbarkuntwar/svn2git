<%-- 
    Document   : validationMessages
    Created on : Feb 19, 2019, 11:59:26 AM
    Author     : ayushi.jain
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


 <!---------------message div ------------------------->
<!--Form Validation success-->
                    <div class="text-center col-md-12" id="successMsgDiv" style="display:none">
                        <div class="d-inline-block">
                            <div class="form-validation form-validation--success p-1 my-1">
                                <i class="fa fa-check mr-2" aria-hidden="true"></i>
                                <h5 class="d-inline-block" id="successMsg">Data Save Successfully</h5>
                            </div>
                        </div>
                    </div>
                    <!--Form Validation success-->

                    <!--Form Validation Error-->
                    <div class="text-center col-md-12" id="errorMsgDiv" style="display:none">
                        <div class="d-inline-block">
                            <div class="form-validation form-validation--error p-1 my-1">
                                <i class="fa fa-exclamation-triangle mr-2" aria-hidden="true"></i>
                                <h5 class="d-inline-block" id="errorMsg">Some Error Occured</h5>
                            </div>
                        </div>
                    </div>
                    <!--Form Validation Error-->

                    <!--Form Validation Info-->
                    <div class="text-center col-md-12" id="notificationMsgDiv" style="display:none">
                        <div class="d-inline-block">
                            <div class="form-validation form-validation--info p-1 my-1">
                                <i class="fa fa-info-circle mr-2" aria-hidden="true"></i>
                                <h5 class="d-inline-block" id="notificationMsg">Please Fill Mandatory Fields</h5>
                            </div>
                        </div>
                    </div>

