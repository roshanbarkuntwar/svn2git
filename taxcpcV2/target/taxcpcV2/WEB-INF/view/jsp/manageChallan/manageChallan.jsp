<%-- 
    Document   : manageChallan
    Created on : Jan 31, 2019, 4:13:25 PM
    Author     : sneha.parpelli
--%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="sx" uri="/struts-dojo-tags" %>
<script src="resources/js/global/paginator/globalPaginator.js?r=<%= Math.random()%>"></script>
<div class="page-section">


    <div class="tab-section col-md-12 my-1">
        <ul class="nav nav-pills">

            <li  class="nav-item mr-5"><a class="nav-link active" data-toggle="pill" href="#browse"><i class="fa fa-table mr-2" aria-hidden="true"></i><p>Challan Browse </p> </a></li>
            <li class="nav-item"><a class="nav-link" data-toggle="pill" href="#entry"><i class="fa fa-file-o mr-2" aria-hidden="true"></i> <p> Challan Entry </p></a></li>

        </ul>
        <div class="clearfix"></div>
    </div>

    <div class="clearfix"></div>


    <div class="tab-content px-4 py-4">
        <div id="browse" class="tab-pane show in active">

        </div>

        <div id="entry" class="tab-pane fade">
            <div class="container-fluid pt-2">
                <div class="row form-group">
                    <div class="col-lg-11 text-center">
                        <div class="custom-control custom-checkbox custom-control-inline">
                            <input type="checkbox" class="custom-control-input" id="nillChallan">
                            <label class="custom-control-label" for="nillChallan">Nill Challan</label>

                        </div>
                        <div class="custom-control custom-checkbox custom-control-inline">
                            <input type="checkbox" class="custom-control-input" id="bookEntry">
                            <label class="custom-control-label" for="bookEntry">Book Entry</label>

                        </div>
                    </div>

                </div>
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-3 text-right">

                        <label class="control-label">
                            BSR Code<span class="text-danger font-weight-bold ml-1">*</span>
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-8">
                        <div class="input-group">
                            <s:textfield type="text" cssClass="input-group-input border-right-0 w-25" id="" placeholder="Enter BSR Code" name=""/>
                            <s:textfield type="text" cssClass="form-control" id="" placeholder="Enter Bank Name/Branch Name" name=""/>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-3 text-right">

                        <label class="control-label">
                            Challan Date <span class="text-danger font-weight-bold ml-1">*</span>
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-8">
                        <div class="input-group">
                            <s:textfield type="text" cssClass="form-control" id="" placeholder="Enter TDSChallanDate(DD-MM-YYYY)" name=""/>
                            <div class="input-group-append">
                                <button type="button" class="btn btn-primary btn-sm addon-btn"><i class="fa fa-calendar"></i></button>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-3 text-right">
                        <label class="control-label">
                            DDO/Challan Serial No.<span class="text-danger font-weight-bold ml-1">*</span>
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-8">
                        <s:textfield type="text" cssClass="form-control" id="" placeholder="Enter DDO/Challan Serial No." name=""/>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-3 text-right">
                        <label class="control-label">
                            Challan Amount<span class="text-danger font-weight-bold ml-1">*</span>
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-8">
                        <s:textfield type="text" cssClass="form-control" id="" placeholder="Enter Challan Amount" name=""/>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-3 text-right">
                        <label class="control-label">
                            TDS Section 
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-8">

                        <select class="form-control">
                            <option>--Select--</option>
                            <option>Option One</option>
                            <option>Option Two</option>
                            <option>Option Three</option>
                        </select>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-3 text-right">
                        <label class="control-label">
                            Interest On Late Payment
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-8">
                        <input type="text" class="form-control" id="" placeholder="Enter Interest On Late Payment">
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-3 text-right">
                        <label class="control-label">
                            Late Payment Fee
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-8">
                        <input type="text" class="form-control" id="" placeholder="Enter Late Payment Fee">
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-3 text-right">
                        <label class="control-label">
                            Minor Head <span class="text-danger font-weight-bold ml-1">*</span>
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-8">

                        <select class="form-control">
                            <option>--Select--</option>
                            <option>Option One</option>
                            <option>Option Two</option>
                            <option>Option Three</option>
                        </select>
                    </div>
                </div>   
                <div class="row">
                    <div class="button-section col-md-12 my-1 text-center"> 

                        <button type="button" class="form-btn"><i class="fa save" aria-hidden="true"></i>Save & Continue</button>
                        <button type="button" class="form-btn"><i class="fa save" aria-hidden="true"></i>Save & Exit</button>
                        <button type="button" class="form-btn" ><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
