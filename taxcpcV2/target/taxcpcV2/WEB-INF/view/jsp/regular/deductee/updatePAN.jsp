<%-- 
    Document   : updatePAN
    Created on : Feb 20, 2019, 6:07:55 PM
    Author     : sneha.parpelli
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<div class="page-section">
    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">

            <li  class="nav-item mr-5"><a class="nav-link" data-toggle="pill" href="#browse"><i class="fa fa-table mr-2" aria-hidden="true"></i><p> Deductee Browse </p> </a></li>
            <li class="nav-item"><a class="nav-link active" data-toggle="pill" href="#updatePAN"><i class="fa fa-file-o mr-2" aria-hidden="true"></i> <p> Update PAN </p></a></li>

        </ul>

        <div class="clearfix"></div>
    </div>

    <div class="clearfix"></div>


    <div class="tab-content px-4 py-4">
        <div id="updatePAN" class="tab-pane show in active">
            <div class="container-fluid pt-2">
                <div class="row form-group my-2">
                    <div class="col-lg-12">
                        <div class="count-container">
                            <label class="mr-1">Total Records </label> <label class="badge-pill badge-primary  mr-3">1</label>

                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-4  text-right">
                        <label class="control-label">
                            Deductee PAN
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-4">
                        <div class="input-group">
                            <s:textfield type="text"  name=""   cssClass="form-control"  placeholder="Enter  Deductee PAN"/>

                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-4  text-right">
                        <label class="control-label">
                            Current PANNO.
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-4">
                        <div class="input-group">
                            <s:textfield type="text"  name=""   cssClass="form-control"  placeholder="Enter   Current PANNO."/>

                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-4  text-right">
                        <label class="control-label">
                            New PANNO.
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-4">
                        <div class="input-group">
                            <s:textfield type="text"  name=""   cssClass="form-control"  placeholder="Enter New PANNO."/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">

                        <div class="button-section col-md-12 my-1 text-center"> 



                            <button type="button" class="form-btn" id="saveBtn1" onclick=""><i class="fa fa-pencil-square-o" aria-hidden="true"></i>Update PAN NO.</button>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>