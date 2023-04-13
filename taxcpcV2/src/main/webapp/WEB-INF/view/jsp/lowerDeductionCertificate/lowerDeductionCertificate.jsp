<%-- 
    Document   : lowerDeductionCertificate
    Created on : Feb 2, 2019, 1:02:24 PM
    Author     : sneha.parpelli
--%>

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

            <li  class="nav-item mr-5"><a class="nav-link active" data-toggle="pill" href="#browse"><p>Lower Deduction Certificate Browse </p> </a></li>
            <li class="nav-item"><a class="nav-link" data-toggle="pill" href="#entry"><p> Lower Deduction Certificate Entry </p></a></li>

        </ul>
        <div class="clearfix"></div>
    </div>

    <div class="clearfix"></div>


    <div class="tab-content px-4 py-4">
        <div id="browse" class="tab-pane show in active">

        </div>

        <div id="entry" class="tab-pane fade">
            <div class="container-fluid pt-2">

                <div class="row form-group row form-group pt-2">
                    <div class="col-lg-4 col-xl-3 text-right">

                        <label class="control-label">
                            Deductee PAN <span class="text-danger font-weight-bold ml-1">*</span>
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-8">
                        <div class="input-group">
                            <s:textfield type="text" cssClass="form-control" id="" placeholder="Enter Deductee PAN" name=""/>
                            <!--                            <div class="input-group-append">
                                                            <button type="button" class="btn btn-primary btn-sm addon-btn">Update From Traces</button>
                                                        </div>-->
                        </div>

                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-3 text-right">
                        <label class="control-label">
                            Certificate No.<span class="text-danger font-weight-bold ml-1">*</span>
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-8">
                        <s:textfield type="text" cssClass="form-control" id="" placeholder="Enter Certificate No." name=""/>
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
                            Certificate From Date <span class="text-danger font-weight-bold ml-1">*</span>
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-8">
                        <div class="input-group">
                            <s:textfield type="text" cssClass="form-control" id="" placeholder="Enter Certificate From Date" name=""/>
                            <div class="input-group-append">
                                <button type="button" class="btn btn-primary btn-sm addon-btn"><i class="fa fa-calendar"></i></button>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-3 text-right">

                        <label class="control-label">
                            Certificate Valid Upto <span class="text-danger font-weight-bold ml-1">*</span>
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-8">
                        <div class="input-group">
                            <s:textfield type="text" cssClass="form-control" id="" placeholder="Enter Certificate Valid Upto" name=""/>
                            <div class="input-group-append">
                                <button type="button" class="btn btn-primary btn-sm addon-btn"><i class="fa fa-calendar"></i></button>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-3 text-right">
                        <label class="control-label">
                            TDS Limit Amount<span class="text-danger font-weight-bold ml-1">*</span>
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-8">
                        <s:textfield type="text" cssClass="form-control" id="" placeholder="Enter TDS Limit Amount" name=""/>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-3 text-right">
                        <label class="control-label">
                            Amount Consumed
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-8">
                        <s:textfield type="text" cssClass="form-control" id="" placeholder="Enter Amount Consumed" name=""/>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-lg-4 col-xl-3 text-right">
                        <label class="control-label">
                            TDS Rate<span class="text-danger font-weight-bold ml-1">*</span>
                        </label>
                    </div>
                    <div class="col-lg-7 col-xl-8">
                        <s:textfield type="text" cssClass="form-control" id="" placeholder="Enter TDS Rate" name=""/>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="row">
                <div class="button-section col-md-12 my-1 text-center"> 
                    <button type="button" class="form-btn"><i class="fa save" aria-hidden="true"></i>Save</button>
                    <button type="button" class="form-btn" ><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

