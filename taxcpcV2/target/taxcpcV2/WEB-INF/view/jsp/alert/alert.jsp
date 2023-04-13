<%-- 
    Document   : alert
    Created on : Jan 28, 2019, 4:18:38 PM
    Author     : sneha.parpelli
--%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#taxcpcModal">Modal</button>

<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#successAlert">Success Alert</button>

<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#dangerAlert">Error Alert</button>


<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#conformationAlert">Conformation Alert</button>

<!--Simple Modal-->
<div class="modal fade" id="taxcpcModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Modal</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
            </div>

        </div>
    </div>
</div>
<!--Simple Modal-->

<!--successAlert-->
<!--<div class="modal alert success-alert fade" id="successAlert" tabindex="-1" role="dialog" aria-labelledby="successAlert" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header position-relative d-flex justify-content-center">
                <i class="fa fa-check-circle-o" aria-hidden="true"></i>
            </div>
            <div class="modal-body py-5 text-center">
                <h4>Data Save Successfully</h4>
                <button type="button" class="btn btn-success" data-dismiss="modal" aria-label="Close">OK </button>
            </div>

        </div>
    </div>
</div>-->
<!--successAlert-->

<!--ErrorAlert-->
<div class="modal alert danger-alert fade" id="dangerAlert" tabindex="-1" role="dialog" aria-labelledby="dangerAlert" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header position-relative d-flex justify-content-center">
                <i class="fa fa-exclamation-triangle mr-2" aria-hidden="true"></i>
            </div>
            <div class="modal-body py-5 text-center">
                <h4>Some Error Occured</h4>
                <button type="button" class="btn btn-danger" data-dismiss="modal" aria-label="Close">OK </button>
            </div>

        </div>
    </div>
</div>
<!--ErrorAlert-->

<!--Conformation-->
<div class="modal alert conformation-alert fade" id="conformationAlert" tabindex="-1" role="dialog" aria-labelledby="conformationAlert" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header position-relative d-flex justify-content-center">
                <i class="fa fa-check-circle-o" aria-hidden="true"></i>
            </div>
            <div class="modal-body py-5 text-center">
                <h5>Are You Sure..</h5>
                <button type="button" class="btn btn-info" data-dismiss="modal" aria-label="Close">Yes </button>
                <button type="button" class="btn btn-default" data-dismiss="modal" aria-label="Close">No</button>
            </div>

        </div>
    </div>
</div>
<!--Conformation-->

<!--Form Validation success-->
<div class="text-center col-md-12">
    <div class="d-inline-block">
        <div class="form-validation form-validation--success p-1 my-1">
            <i class="fa fa-check mr-2" aria-hidden="true"></i>
            <h5 class="d-inline-block">Data Save Successfully</h5>
        </div>
    </div>
</div>
<!--Form Validation success-->

<!--Form Validation Error-->
<div class="text-center col-md-12">
    <div class="d-inline-block">
        <div class="form-validation form-validation--error p-1 my-1">
            <i class="fa fa-exclamation-triangle mr-2" aria-hidden="true"></i>
            <h5 class="d-inline-block">Some Error Occured</h5>
        </div>
    </div>
</div>
<!--Form Validation Error-->

<!--Form Validation Info-->
<div class="text-center col-md-12">
    <div class="d-inline-block">
        <div class="form-validation form-validation--info p-1 my-1">
            <i class="fa fa-info-circle mr-2" aria-hidden="true"></i>
            <h5 class="d-inline-block">Please Fill Mandatory Fields</h5>
        </div>
    </div>
</div>
<!--Form Validation Info-->