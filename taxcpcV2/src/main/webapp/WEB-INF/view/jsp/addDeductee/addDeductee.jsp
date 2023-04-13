<%-- 
    Document   : addDeductee
    Created on : Feb 19, 2019, 5:30:48 PM
    Author     : neha.bisen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="page-section">
            <div class="tab-section col-md-12 my-1">
                <ul class="nav nav-pills">
                    <li  class="nav-item mr-5"><a class="nav-link active" data-toggle="pill" href="#browse"><i class="fa fa-table mr-2" aria-hidden="true"></i><p>Browse </p> </a></li>
                    <li   class="nav-item mr-5"><a class="nav-link" data-toggle="pill" href="#entry"><i class="fa fa-file-o mr-2" aria-hidden="true"></i> <p>Entry </p></a></li>
                </ul>
                <div class="clearfix"></div>
            </div>


            <div class="tab-content px-4 py-4">

                <div id="browse" class="tab-pane show in active">






                    <div class="button-section my-1"> 

                        <button type="button" class="form-btn"><i class="fa fa-download" aria-hidden="true"></i>Download</button>

                    </div> 

                    <div class="filter-section">
                        <div class="row sec-bottom">
                            <div class="col-lg-10 col-xl-11">
                                <div class="row form-group">
                                    <div class="col-lg-3">

                                        <input type="text"  class="form-control" title="PAN No." placeholder="PAN No.">
                                    </div>
                                    <div class="col-lg-3">

                                        <input type="text"  class="form-control" title="Deductee Name" placeholder="Deductee Name">

                                    </div>

                                    <div class="col-lg-3">
                                        <select class="form-control">

                                            <option value="48">Incomplete 15GH Entry</option>
                                            <option value="">All</option>
                                            <option value="INV">InComplete</option>
                                            <option value="V">Complete</option>

                                        </select>



                                    </div>    
                                    <div class="col-lg-3">
                                        <select  class="form-control">
                                            <option value="">Verified Flag</option>
                                            <option value="Verified">Verified</option>
                                            <option value="Not Verified">Not Verified</option>
                                            <option value="Invalid">Invalid</option>


                                        </select>


                                    </div> 

                                </div>

                                <div class="collapse" id="remainingFilter">
                                    <div class="row form-group">



                                        <div class="col-lg-3">
                                            <select  class="form-control" >
                                                <option value="">Incomplete 15G/H Amount Details</option>
                                                <option value="I">InComplete</option>
                                                <option value="C">Complete</option>


                                            </select>



                                        </div>
                                        <div class="col-lg-3">

                                            <input type="text" class="form-control"  placeholder="15 G/H Ref. No.">

                                        </div>
                                        <div class="col-lg-3">

                                            <input type="text" class="form-control"  placeholder="Cust Id">

                                        </div>



                                    </div>

                                </div>
                            </div>
                            <div class="col-lg-2 col-xl-1 pl-0 d-flex justify-content-between">
                                <button type="button" class="btn btn-primary addon-btn filter-inner-btn" title="Search" onclick="showRecords();"><i class="fa fa-search"></i></button>
                                <button type="button" class="btn btn-primary addon-btn filter-inner-btn" onclick="lhsResetform('transactionMgmtForm', 'submit');" title="Clear"><i class="fa fa-eraser"></i></button>
                                <button type="button" class="btn btn-primary addon-btn filter-inner-btn more-filter-option-btn" data-toggle="collapse" title="Show More Filter" data-target="#remainingFilter"><i class="fa fa-ellipsis-h" aria-hidden="true"></i></button> 
                            </div> 
                        </div>  
                    </div>





                    <div class="table-responsive mt-2" id="dataSpan">
                        <table class="table table-bordered table-striped mb-1" id="table">             
                            <thead>            
                                <tr>

                                    <th> Grid1      
                                    </th> 
                                    <th>
                                        Grid2  
                                    </th> 


                                    <th>
                                        Grid3

                                    </th> 


                                    <th>         
                                        Grid4      
                                    </th> 

                                    <th>         

                                        Grid5  


                                    </th> 




                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="text-center">1</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>


                                </tr>
                            </tbody>


                        </table>

                    </div>

                    <!--paginator-->
                    <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginator.jsp" %>

                    <!-----------------settings------------>
                    <%@include file="/WEB-INF/view/jsp/global/paginator/globalPaginatorSettings.jsp" %>


                </div>



                <div id="entry" class="tab-pane">

                    <div class="container-fluid pt-2">
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4 text-right">

                                <label class="control-label">
                                    Deductee Category <span class="text-danger font-weight-bold ml-1">*</span>
                                </label>
                            </div>
                            <div class="col-lg-7 col-xl-4">
                                <select class="form-control">
                                    <option value="R">Select Category</option>
                                    <option value="">Others</option>

                                </select>

                            </div>

                        </div>

                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">

                                <label class="control-label">
                                    PAN <span class="text-danger font-weight-bold ml-1">*</span> <img src="resources/images/help.png" width="14" height="14" data-toggle="tooltip" data-placement="right">
                                </label>
                            </div>

                            <div class="col-lg-7 col-xl-4">
                                <div class="input-group">
                                    <input type="text" class="form-control"  placeholder="Deductee PAN No.">
                                    <input type="text" class="form-control" placeholder="PANNO Status Not Available" title="PANNO Status Not Available" readonly="">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text line-height-2">
                                            <input type="checkbox" title="Pan Not Available" id="checkbox" >
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">

                                <label class="control-label">
                                    Deductee Name <span class="text-danger font-weight-bold ml-1">*</span>
                                </label>
                            </div>

                            <div class="col-lg-7 col-xl-4">

                                <input type="text" name="tdsTranLoad.deductee_name" value="" id="deducteeName" class="form-control" placeholder="Deductee Name">
                            </div> 
                        </div>


                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">

                                <label class="control-label">
                                    Identification No.
                                </label>
                            </div>

                            <div class="col-lg-7 col-xl-4">

                                <input type="text" name="tdsTranLoad.deductee_name" value="" id="deducteeName" class="form-control" placeholder="Identification No.">
                            </div> 
                        </div>

                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">

                                <label class="control-label">
                                    Resident Status
                                </label>
                            </div>

                            <div class="col-lg-7 col-xl-4">

                                <select class="form-control">
                                    <option value="R">Select Status</option>
                                    <option value="R">Resident</option>
                                    <option value="N">Non-Resident</option>
                                    <option value="O">Resident but not ordinarly Resident</option>


                                </select>
                            </div> 
                        </div>

                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">

                                <label class="control-label">
                                    Address
                                </label>
                            </div>

                            <div class="col-lg-7 col-xl-4">

                                <input type="text" class="form-control" placeholder="Address">
                            </div> 
                        </div>

                        <div class="row form-group">

                            <div class="col-lg-7 offset-lg-4 col-xl-4 offset-xl-4">

                                <input type="text"  class="form-control" placeholder="Address 2">
                            </div>
                        </div>


                        <div class="row form-group">

                            <div class="col-lg-7 offset-lg-4 col-xl-4 offset-xl-4">

                                <input type="text"  class="form-control" placeholder="Address 3">
                            </div>
                        </div>


                        <div class="row form-group">

                            <div class="col-lg-7 offset-lg-4 col-xl-4 offset-xl-4">

                                <input type="text"  class="form-control" placeholder="Address 4">
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">

                                <label class="control-label">
                                    Pin Code
                                </label>
                            </div>

                            <div class="col-lg-7 col-xl-4">

                                <input type="text"  class="form-control" placeholder="Pin Code">
                            </div> 
                        </div>

                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">

                                <label class="control-label">
                                    State
                                </label>
                            </div>

                            <div class="col-lg-7 col-xl-4">
                                <select class="form-control">
                                    <option value="R">Select State</option>

                                </select>
                            </div> 
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">

                                <label class="control-label">
                                    City
                                </label>
                            </div>

                            <div class="col-lg-7 col-xl-4">

                                <input type="text"  class="form-control" placeholder="City">
                            </div> 
                        </div>

                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">

                                <label class="control-label">
                                    STD Code/Telephone No
                                </label>
                            </div>

                            <div class="col-lg-7 col-xl-4">

                                <div class="input-group">
                                    <input type="text"   class="form-control" placeholder="STD Code">
                                    <input type="text"   class="form-control" placeholder="Telephone No.">

                                </div>
                            </div> 
                        </div>

                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">

                                <label class="control-label">
                                    Mobile No
                                </label>
                            </div>

                            <div class="col-lg-7 col-xl-4">

                                <input type="text"  class="form-control" placeholder="Mobile No">
                            </div> 
                        </div>

                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">

                                <label class="control-label">
                                    Email
                                </label>
                            </div>

                            <div class="col-lg-7 col-xl-4">

                                <input type="text"  class="form-control" placeholder="Email">
                            </div> 
                        </div>

                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">

                                <label class="control-label">
                                    File Type
                                </label>
                            </div>

                            <div class="col-lg-7 col-xl-4">

                                <select class="form-control">
                                    <option value="R">Select Type</option>
                                    <option value="B">15G</option>
                                    <option value="D">15H</option>
                                    <option value="A">Lower deduction u/s 197</option>


                                </select>
                            </div> 
                        </div>

                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">

                                <label class="control-label">
                                    Reference No.
                                </label>
                            </div>

                            <div class="col-lg-7 col-xl-4">

                                <input type="text"  class="form-control" placeholder="   Reference No.">
                            </div> 
                        </div>


                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">

                                <label class="control-label">
                                    PAN Status
                                </label>
                            </div>

                            <div class="col-lg-7 col-xl-4">
                                <select class="form-control">
                                    <option value="">Select No</option>
                                    <option value="1">INDIVIDUAL</option>
                                    <option value="2">HUF</option>
                                    <option value="3">AOP_BOI</option>
                                    <option value="4">TRUST</option>
                                    <option value="5">LOCAL_AUTHORITY</option>
                                    <option value="6">ART_JUR_PERSON</option>
                                    <option value="7">CO_OPERATIVE_SOCIETY</option>
                                    <option value="9">PRIVATE_DIS_TRUST</option>
                                </select>
                            </div> 
                        </div>


                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">

                                <label class="control-label">
                                    DOB
                                </label>
                            </div>

                            <div class="col-lg-7 col-xl-4">
                                <input type="text"  class="form-control" placeholder=" DOB">
                            </div> 
                        </div>

                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">

                                <label class="control-label">
                                    Whether assessed to tax under the Income Tax Act 1961
                                </label>
                            </div>

                            <div class="col-lg-7 col-xl-4">
                                <select class="form-control">
                                    <option value="">Select</option>
                                    <option value="1">No</option>
                                    <option value="2">Yes</option>

                                </select>
                            </div> 
                        </div>

                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">

                                <label class="control-label">
                                    Estimated income for declaration
                                </label>
                            </div>

                            <div class="col-lg-7 col-xl-4">
                                <input type="text"  class="form-control" placeholder="Estimated Income">
                            </div> 
                        </div>

                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">

                                <label class="control-label">
                                    Estimated total income including above
                                </label>
                            </div>

                            <div class="col-lg-7 col-xl-4">
                                <input type="text"  class="form-control" placeholder="Estimated Total Income">
                            </div> 
                        </div>

                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">

                                <label class="control-label">
                                    Total number of form filed(Other then this)
                                </label>
                            </div>

                            <div class="col-lg-7 col-xl-4">
                                <input type="text"  class="form-control" placeholder="Form Field">
                            </div> 
                        </div>


                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">

                                <label class="control-label">
                                    Aggregate amount of income(other then this)
                                </label>
                            </div>

                            <div class="col-lg-7 col-xl-4">
                                <input type="text"  class="form-control" placeholder="Aggregate Amount">
                            </div> 
                        </div>


                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">

                                <label class="control-label">
                                    Date of Declaration
                                </label>
                            </div>

                            <div class="col-lg-7 col-xl-4">
                                <input type="text"  class="form-control" placeholder="Date of declaration">
                            </div> 
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">

                                <label class="control-label">
                                    Amount of Income Paid
                                </label>
                            </div>

                            <div class="col-lg-7 col-xl-4">
                                <input type="text"  class="form-control" placeholder="Amount of income paid">
                            </div> 
                        </div>

                        <div class="row form-group">
                            <div class="col-lg-4 col-xl-4  text-right">

                                <label class="control-label">
                                    Date of Income Paid
                                </label>
                            </div>

                            <div class="col-lg-7 col-xl-4">
                                <input type="text"  class="form-control" placeholder="Date of income paid">
                            </div> 
                        </div>
                    </div>


                    <div class="table-responsive mt-2" id="dataSpan">
                        <table class="table table-bordered table-striped mb-1" id="table">             
                            <thead>            
                                <tr>

                                    <th> Sr NO       
                                    </th> 
                                    <th>
                                        Identification no of relevant investment/ account, etc.      
                                    </th> 


                                    <th>
                                        Section under which tax is deductable  


                                    </th> 


                                    <th>         
                                        Nature Of Income      
                                    </th> 

                                    <th>         

                                        Amount Of Income   


                                    </th> 
                                    <th class="text-center ">
                                        <i class="fa fa-plus-circle cursor-pointer"></i>
                                    </th>    



                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="text-center">1</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>

                                    <td class="text-center">
                                        <i class="fa fa-trash text-danger cursor-pointer"></i>
                                    </td>
                                </tr>
                            </tbody>


                        </table>

                    </div>

                    <div class="button-section text-center my-1"> 

                        <button type="button" class="form-btn"><i class="fa fa-upload" aria-hidden="true"></i>Save</button>
                        <button type="button" class="form-btn"><i class="fa fa-upload" aria-hidden="true"></i>Cancel</button>


                    </div>
                </div>



                 
                    
                    
            </div>
        </div>
    </body>
</html>
