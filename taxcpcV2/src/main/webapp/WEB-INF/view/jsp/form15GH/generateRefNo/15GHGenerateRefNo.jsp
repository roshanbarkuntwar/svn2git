<%-- 
    Document   : 15GHTransaction
    Created on : Feb 27, 2019, 3:22:03 PM
    Author     : ayushi.jain
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<script src="resources/js/regular/DeducteeManage15ghDetail/deducteeManage15GHDetail.js" type="text/javascript"></script>-->
<script src="resources/js/form15GH/deducteeManage15GHDetail.js" type="text/javascript"></script>

<div class="page-section">
    <s:hidden value="%{#session.CONTEXTPATH}" id="contextPath"/>
    <s:hidden id="clientLoginLevel" name="clientLoginLevel" value="%{clientLoginLevel}" />
    <s:hidden id="GenTypeValue" name="GenTypeValue"/>
    <s:hidden id="dispTempFlag" name="dispTempFlag" value="F"/>
    <s:hidden id="loginGenerationLevelClient" name="generationLevelClient"/>
    
    
    <div class="col-md-12 my-1  main-page-heading">
        <h4 class="page-title mb-2">Referance No Generation</h4>
    </div>
    <div class="tab-content form-wrapper px-4 py-2">

        <div class="container-fluid pt-2">


            <div class="row form-group">
                <div class="col-lg-11 text-center">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" class="custom-control-input" id="customRadio" name="example" value="customEx">
                        <label class="custom-control-label" for="customRadio">Login Level</label>
                    </div>

                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" class="custom-control-input" id="customRadio" name="example" value="customEx">
                        <label class="custom-control-label" for="customRadio">Next Level</label>
                    </div>
                </div>
            </div>

            <div class="row form-group">
                <div class="col-lg-4 col-xl-4 text-right">
                    <label class="control-label">
                        Last Generated Number 15G <span class="text-danger font-weight-bold ml-1">*</span>
                    </label>
                </div>

                <div class="col-lg-5 col-xl-4">
                    <div class="input-group">
                        <div class="input-group-append">
                            <button type="button" class="btn btn-primary btn-sm addon-btn">
                                G - </button>
                        </div>               
                        <input type="text" class="form-control"  placeholder="Last Generated Number 15G" name="lastGen15GNo" id="lastGen15GNoId">
                    </div>
                </div>
            </div>



            <div class="row form-group">
                <div class="col-lg-4 col-xl-4 text-right">
                    <label class="control-label">
                        Last Generated Number 15H <span class="text-danger font-weight-bold ml-1">*</span>
                    </label>
                </div>

                <div class="col-lg-5 col-xl-4">
                    <div class="input-group">
                        <div class="input-group-append">
                            <button type="button" class="btn btn-primary btn-sm addon-btn">
                                H - </button>
                        </div>               
                        <input type="text" class="form-control"  placeholder="Last Generated Number 15H" name="lastGen15HNo" id="lastGen15HNoId">
                    </div>
                </div>


            </div>


            <div class="row form-group">
                <div class="col-lg-4 col-xl-4 text-right">
                    <label class="control-label">
                        Next Number 15G <span class="text-danger font-weight-bold ml-1">*</span>
                    </label>
                </div>

                <div class="col-lg-5 col-xl-4">
                    <div class="input-group">
                        <div class="input-group-append">
                            <button type="button" class="btn btn-primary btn-sm addon-btn">
                                G - </button>
                        </div>               
                        <input type="text" class="form-control"  placeholder="Next Number 15G" name="NextGen15GNo" id="NextGen15GNoId">



                    </div>
                </div>

                <div class="col-lg-3 col-xl-4">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" class="custom-control-input" id="customRadio" name="example" value="customEx">
                        <label class="custom-control-label" for="customRadio">Refresh</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" class="custom-control-input" id="customRadio2" name="example" value="customEx">
                        <label class="custom-control-label" for="customRadio2">Blank</label>
                    </div> 
                </div> 


            </div>



            <div class="row form-group">
                <div class="col-lg-4 col-xl-4 text-right">
                    <label class="control-label">
                        Next Number 15H <span class="text-danger font-weight-bold ml-1">*</span>
                    </label>
                </div>

                <div class="col-lg-5 col-xl-4">
                    <div class="input-group">
                        <div class="input-group-append">
                            <button type="button" class="btn btn-primary btn-sm addon-btn">
                                H - </button>
                        </div>               
                        <input type="text" class="form-control"  placeholder="Next Number 15H" name="NextGen15HNo" id="NextGen15HNoId">
                    </div>
                </div>

                <div class="col-lg-3 col-xl-4">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" class="custom-control-input" id="customRadio3" name="example" value="customEx">
                        <label class="custom-control-label" for="customRadio3">Refresh</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" class="custom-control-input" id="customRadio4" name="example" value="customEx">
                        <label class="custom-control-label" for="customRadio4">Blank</label>
                    </div> 
                </div> 
            </div>
            <div class="row">
                <div class="button-section col-md-12 my-1 text-center"> 


                    <button type="button" id="saveContinue" class="form-btn"><i class="fa generate" aria-hidden="true" onclick="saveReferenceNo();"></i>Generate</button>
                    <s:url id="cancelBtn_id" namespace="/" action="getGenReferenceNoAction"></s:url>  
                     <s:a href="%{cancelBtn_id}">
                            <button type="button" id="saveexit" class="form-btn"><i class="fa cancel" aria-hidden="true"></i>Cancel</button>
                     </s:a>      
                  
                </div>
            </div>


            <div class="row form-group">
                <div class="col-lg-4 col-xl-4 text-right">
                    <label class="control-label">
                        15GH Number Generation Leve <span class="text-danger font-weight-bold ml-1">*</span>
                    </label>
                </div>

                <div class="col-lg-5 col-xl-4">

                    <input type="text" class="form-control"  placeholder="Last Generated Number 15G">
                </div>
            </div>
            <div class="table-responsive mt-2">
                    <table class="table table-bordered table-striped mb-1">  
                        <thead>                 
                            <tr>           
                                <th class="check-action text-center">                              
                                    <div class="custom-control custom-checkbox action-check ">
                                        <input type="checkbox" class="custom-control-input toggle-action-section" id="checkAll" onclick="setAllChecked(this)">
                                        <label class="custom-control-label" for="checkAll"></label>
                                    </div>
                                </th> 
                                <th> 
                                    Sr No.
                                </th>
                                <th> 
                                    Bank  Branch
                                </th>
                                <th>
                                    Client Name
                                </th>
                                <th>
                                    Tan No
                                </th>
                                <th>
                                    Last Generated No 15G
                                </th>
                                <th>
                                    Last Generated No 15H
                                </th>

                                <th>
                                    Next No 15G 
                                </th>        

                                <th>
                                    Next No 15H
                                </th>   

                                <th>
                                    <div class="custom-control custom-radio custom-control-inline">
                                        <input type="radio" class="custom-control-input" id="customRadio" name="example" value="customEx">
                                        <label class="custom-control-label" for="customRadio">Refresh</label>
                                    </div>
                                </th>   
                                <th>
                                    <div class="custom-control custom-radio custom-control-inline">
                                        <input type="radio" class="custom-control-input" id="customRadio" name="example" value="customEx">
                                        <label class="custom-control-label" for="customRadio">Blank   </label>
                                    </div>
                                </th>   



                            </tr>                   
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <div class="custom-control custom-checkbox action-check ">
                                        <input type="checkbox" class="custom-control-input toggle-action-section">
                                        <label class="custom-control-label" for="check~1"></label>
                                    </div>
                                </td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>

                            </tr>
                        </tbody>
                    </table>   

                </div>


                <!--Table Section-->
                <div class="clearfix"></div> 


           

            <div class="row">
                <div class="button-section col-md-12 my-1 text-center"> 
                    <button type="button" id="saveexit" class="form-btn"><i class="fa fa-download" aria-hidden="true"></i>Download</button>
                    <!--<button type="button" id="saveexit" class="form-btn"><i class="fa fa-upload" aria-hidden="true"></i>Upload</button>-->
                    <button type="button" id="saveContinue" class="form-btn"><i class="fa fa-cog" aria-hidden="true"></i>Generate</button>
                    <button type="button" id="saveexit" class="form-btn"><i class="fa cancel" aria-hidden="true"></i>Cancel</button>






                </div>
            </div> 


        </div>
    </div>
</div>


