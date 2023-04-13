<%-- 
    Document   : alertBoxes
    Created on : Jan 31, 2019, 12:26:05 PM
    Author     : ayushi.jain
--%>

<script src="resources/js/global/jquery-ui.js"></script>
<link rel="stylesheet" href="resources/css/global/jquery-ui.css">


<div class=" alert conformation-alert" id="dialog-confirm_delete" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Do You Want To Delete This Record?</p>
</div>

<div class=" alert conformation-alert" id="dialog-confirm_update" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Are You Sure Want To Update This Record?</p>
</div>

<div class=" alert conformation-alert" id="dialog-confirm_save" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Are You Sure Want To Save This Record?</p>
</div>

<div class="alert conformation-alert" id="dialog-confirm_empty_row_avbl" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Empty row is available</p>
</div>

<div class=" alert  conformation-alert" id="dialog-confirm_change_assessment" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Are you sure you want to change the assessment ?</p>
</div>
<div class="alert  conformation-alert" id="dialog-confirm_change_review" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Are you sure you want to update?</p>
</div>
<div class="alert  conformation-alert" id="dialog-confirm_change_deductor" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Are you sure you want to change the Deductor ?</p>
</div>
<div class="alert  conformation-alert" id="dialog-confirm_change_logo" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Are you sure you want to change the Logo ?</p>
</div>
<div class="alert  conformation-alert" id="dialog-confirm_allRcrdTDS" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Are You Sure to Allocate All Record TDS?</p>
</div>
<div class="alert  conformation-alert" id="dialog-confirm_unaRcrdTDS" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Are You Sure to UnAllocate All Record TDS?</p>
</div>

<div class="alert  conformation-alert" id="dialog-cleanExistingFile" title="Alert Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Some files has been present,<br/> Do you wan't to clean the directory or process with this file. </p>
</div>

<div class="alert conformation-alert" id="dialog-confirm_revert_all" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Are you sure to revert all records?</p>
</div>
<div class="alert conformation-alert" id="dialog-confirm_updtChkRcrds" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Do You want to Update Checked Record(s)?</p>
</div>
<div class="alert conformation-alert" id="dialog-confirm_excludeErr" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Please refresh error before moving forward , if you still want to process data with excluding error then press ok !   </p>
    <!--    <p style="font-size: 15px;">Are You Sure to Process Data with Excluding Error?</p>-->
</div>
<div class="alert conformation-alert" id="dialog-confirm_call_bulk_update" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Are You Sure to proceed for bulk updation YES/NO  </p>
    <!--    <p style="font-size: 15px;">Are You Sure to Process Data with Excluding Error?</p>-->
</div>

<div id="dialog-confirm_ref_no_Present_Msg" title="Confirmation Box" style="display: none;">
    <p style="font-size: 15px;">Are You Sure To Generate Reference Number?</p>
</div>

<div id="dialog-confirm_review_checkkk" title="Confirmation Box" style="display: none;">
    <p style="font-size: 15px;">All erroneous entries are not reviewed, Do you want to leave those entries and generate FVU text file?</p>
</div>

<div class="modal alert danger-alert fade" id="dialog-confirm_review_check" tabindex="-1" role="dialog" aria-labelledby="dangerAlert" aria-hidden="true" >
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header position-relative d-flex justify-content-center">
                <i class="fa fa-exclamation-triangle mr-2" aria-hidden="true"></i>
            </div>
            <div class="modal-body py-5 text-center">
               <p style="font-size: 15px;">All erroneous entries are not reviewed, Do you want to leave those entries and generate FVU text file?</p>
               <button   type="button" class="btn btn-danger"  onclick="tdsGenerateFVUcalling()">Yes </button>
               <button id="errorBtn"  type="button" class="btn btn-danger" data-dismiss="modal" aria-label="Close">No </button>
         
            </div>

        </div>
    </div>
</div>

<div id="dialog-error-procedure-success" title="Alert Box" style="display: none;">
    <p style="font-size: 15px;" >Process completed successfully ! </p>
</div>

<div id="dialog-error-procedure-error" title="Alert Box" style="display: none;">
    <p style="font-size: 15px;" >Some Error  Occurred ! </p>
</div>

<div class="alert conformation-alert" id="dialog-confirm_Upload" title="Alert Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Please check branch detail to avoid double execution of process </p>
</div>

<div class="alert conformation-alert" id="dialog-skip-process-confirm-extract" title="Alert Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">If Extraction Process Is Already Completed Then You Can Skip This Process... Do You Want To Skip This Process?  </p>
</div>

<div class="alert conformation-alert" id="dialog-refresh-success" title="Alert Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;" id="dialog-refresh-success-msg">Record Refreshed Successfully !</p>
</div>

<div class="alert conformation-alert" id="dialog-skip-process-confirm-insertion" title="Alert Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">If Insertion Process Is Already Completed Then You Can Skip This Process... Do You Want To Skip This Process?  </p>
</div>

<div class="alert conformation-alert" id="dialog-skip-process-confirm-insert" title="Alert Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Some Records are Already Present ...Do You Want To reprocess </p>
</div>

<div class="alert conformation-alert" id="dialog-skip-process-confirm-branching" title="Alert Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">If Branching Process Is Already Completed Then You Can Skip This Process... Do You Want To Skip This Process?  </p>
</div>
<div class="alert conformation-alert" id="dialog-skip-process-confirm" title="Alert Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">If Allocation Process Is Already Completed Then You Can Skip This Process... Do You Want To Skip This Process?  </p>
</div>

<div class="alert conformation-alert" id="dialog-skip-upload-success" title="Alert Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;" id="dialog-skip-upload-success-p">File Uploaded Successfully ! </p>
</div>

<div  class="alert conformation-alert" id="dialog-confirm_insrtDeductee" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Deductee Not Available, Do You Want to Insert?</p>
</div>

<!--<div  class="alert conformation-alert" id="dialog-confirm-refresh-append-prn" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Please select the process</p>    
</div>-->


<!--xmlGenerationAlert-->
<div id="dialog-confirm_xml_generation" title="Alert Box" style="display: none;">
    <p style="font-size: 15px;">All erroneous entries are found, do you want to leave those entries and generate XML File </p>
</div>

<div  class="alert conformation-alert" id="dialog-alert-generate-fvu" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">FVU file not generated , do you want to update the records?</p>    
</div>


<div  class="alert conformation-alert" id="dialog-confirm-revert" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Are you sure to revert this record?</p>  
</div>

<div  class="alert conformation-alert" id="dialog-confirm-NIL" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Are you sure to Generate NIL Text File</p>  
</div>

<!--successAlert-->
<div class="modal alert success-alert fade" id="successAlert" tabindex="-1" role="dialog" aria-labelledby="successAlert" aria-hidden="true" >
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header position-relative d-flex justify-content-center">
                <i class="fa fa-check-circle-o" aria-hidden="true"></i>
            </div>
            <div class="modal-body py-5 text-center">
                <h4 id="successAlertMsg">Data Save Successfully</h4>
                <button id="successBtn" type="button" class="btn btn-success" data-dismiss="modal" aria-label="Close">OK </button>
            </div>

        </div>
    </div>
</div>
<!--successAlert-->


<!--Success With Copy Text Alert-->
<div class="modal alert success-alert fade" id="successWithCopyTextAlert" tabindex="-1" role="dialog" aria-labelledby="successAlert" aria-hidden="true" >
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header position-relative d-flex justify-content-center">
                <i class="fa fa-check-circle-o" aria-hidden="true"></i>
            </div>
            <div class="modal-body py-5 text-center">
                <h4 id="successWithCopyTextAlertMsg">Data Save Successfully</h4>
                <div class="input-group mb-3">
                    <input type="text" name="copyClipboard" value="" id="copyClipboard" class="form-control" readonly>
                    <div class="input-group-append">
                        <span class="input-group-text btn-success" onclick="copyToClipboard('#copyClipboard');" style="height: 25px;padding: 1px 10px;"><i class="fa fa-copy text-white"></i></span>
                    </div>
                </div>
                <button id="successCopyBtn" type="button" class="btn btn-success" data-dismiss="modal" aria-label="Close">OK </button>
            </div>

        </div>
    </div>
</div>
<!--Success With Copy Text Alert-->

<!--ErrorAlert-->
<div class="modal alert danger-alert fade" id="dangerAlert" tabindex="-1" role="dialog" aria-labelledby="dangerAlert" aria-hidden="true" >
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header position-relative d-flex justify-content-center">
                <i class="fa fa-exclamation-triangle mr-2" aria-hidden="true"></i>
            </div>
            <div class="modal-body py-5 text-center">
                <h4 id="errorAlertMsg">Some Error Occured</h4>
                <button id="errorBtn"  type="button" class="btn btn-danger" data-dismiss="modal" aria-label="Close">OK </button>
            </div>

        </div>
    </div>
</div>
<!--ErrorAlert-->

<!--Conformation-->
<div class="modal alert conformation-alert fade" id="conformationAlert" tabindex="-1" role="dialog" aria-labelledby="conformationAlert" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header position-relative d-flex justify-content-center">
                <i class="fa fa-question-circle" aria-hidden="true"></i>
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
<div class=" alert conformation-alert" id="dialog-confirm_certificate_allo" title="Confirmation Box" style="display: none;" >
    <button type="button"  onclick="$('#dialog-confirm_certificate_allo').dialog('close');" class="alert-dismiss-button">
                    <span aria-hidden="true">&times;</span>
        </button>
    <div class="modal-header position-relative d-flex justify-content-center">
        

                <i class="fa fa-question-circle" aria-hidden="true"></i>
            
    </div>
    <p style="font-size: 15px;margin-bottom: 0;">Do You Want To Allocate  Lower Deduction Certificate </p>
</div>

<div class=" alert conformation-alert" id="dialog-confirm_certificate_unallo" title="Confirmation Box" style="display: none;">
     <button type="button"  onclick="$('#dialog-confirm_certificate_unallo').dialog('close');" class="alert-dismiss-button">
                    <span aria-hidden="true">&times;</span>
        </button>
    <div class="modal-header position-relative d-flex justify-content-center">
      

                <i class="fa fa-question-circle" aria-hidden="true"></i>
           
    </div>
    <p style="font-size: 15px;margin-bottom: 0;">Do You Want To Unallocate  Lower Deduction Certificate </p>
</div>


<div class=" alert conformation-alert" id="dialog-confirm_for_radio" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Do You Want To Change Challan Entry Type?</p>
</div>


<div class=" alert conformation-alert" id="confirm_for_unallocate_before_delete" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Challan is already allocated, Please un-allocate the same for deletion</p>
</div>

<div class=" alert conformation-alert" id="dialog-confirm_process_kill" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Are You Sure! You Want To Kill The Process?</p>
</div>

<div class=" alert conformation-alert" id="dialog-confirm_get_text_file" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Are You Sure! <br> You Want To Get Text File From DBS?</p>
</div>

<div class=" alert conformation-alert" id="dialog-confirm_get_regenerate_csi_file" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Are You Sure!<br> You Want To Re-Generate CSI File?</p>
</div>

<div class=" alert conformation-alert" id="dialog-confirm_get_csi_file" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Are You Sure!<br> You Want To Generate CSI File?</p>
</div>

<div class=" alert conformation-alert" id="dialog-confirm_upload_csi_file" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Are You Sure!<br> You Want Upload CSI File?</p>
</div>

<div class=" alert conformation-alert" id="dialog-confirm_pan_update_process" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Do You Want To Update PAN ?</p>
</div>

<div class=" alert conformation-alert" id="dialog-confirm_for_refresh" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Are You Sure!<br> You Want To Refresh Process?</p>
</div>

<div class=" alert conformation-alert" id="dialog-confirm_delete_proc_id" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Do You Want To Delete All Transaction For This Token?<br>(You can not download the uploaded file once you delete the record)</p>
</div>

<div class=" alert conformation-alert" id="dialog-confirm_delete_mul_tran" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Do You Want To Delete Records?</p>
</div>

<!--ErrorAlert-->
<div class="modal alert danger-alert fade" id="err_report_danger_Alert" tabindex="-1" role="dialog" aria-labelledby="dangerAlert" aria-hidden="true" >
    <div class="modal-dialog modal-dialog-centered" role="document">
               
        <div class="modal-content">
           
            <div class="modal-header position-relative d-flex justify-content-center">
               <i class="fa fa-exclamation-triangle mr-2" aria-hidden="true"></i>
            </div>
            
            <div class="modal-body py-5 text-center">
                <h4 id="reporterrorAlertMsg">Please Generate Error Process Report</h4>
               <button id="reportbtnmsg"  type="button" class="btn btn-danger" data-dismiss="modal" onclick="callviewProcessDatapage();" aria-label="Close">Click here to generate report </button>
               <button type="button"  class="btn btn-danger" data-dismiss="modal" aria-label="Close">Cancel</button>
            </div>

<!--            <a href="misReportSettingAction" title=""> Click Hear </a>-->

        </div>
    </div>
</div>
<!--ErrorAlert-->

<div class=" alert conformation-alert" id="dialog-confirm_Rep_dwnORnot_alert" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;"  id="down_rep_cont"></p>
<!--    <p style="font-size: 15px;" class="mt-0 mb-0" id="not_down_rep_cont"></p>-->
<!--    <p style="font-size: 15px;" class="mt-0"  id="dwnl_rep_counting">Do You Want To Continue?</p>-->
</div>


<div class="modal alert success-alert fade" id="successAlertReports" tabindex="-1" role="dialog" aria-labelledby="successAlert" aria-hidden="true" >
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header position-relative d-flex justify-content-center">
                <i class="fa fa-check-circle-o" aria-hidden="true"></i>
            </div>
            <div class="modal-body py-5 text-center">
                <h4 id="successAlertMsgReports">Data Save Successfully</h4>
                <button id="success_Btn_Rep" type="button" class="btn btn-success" data-dismiss="modal" aria-label="Close" >OK </buttonon>
            </div>

        </div>
    </div>
</div>
<div class="alert  conformation-alert" id="dialog-confirm_for_updation" title="Confirmation Box" style="display: none;">
    <div class="modal-header position-relative d-flex justify-content-center">
        <i class="fa fa-question-circle" aria-hidden="true"></i>
    </div>
    <p style="font-size: 15px;">Are you sure you want to update?</p>
</div>