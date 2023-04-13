/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.processDetails;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.GlobalMessage;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import hibernateObjects.ViewClientTemplate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gaurav.khanzode
 */
public class ProcessDetailsSupport {

    public String getProcessDetailsCountQuery(String entityCode, String clientCode, ViewClientMast clientMast, Assessment ass, ProcessDetailBean filterObj) {

        String return_sql = " SELECT COUNT(*)\n"
                + "  FROM ( "
                + getProcessDetailsQuery(filterObj,clientMast, ass,0, 0)
                + ") ";

        return return_sql;
    }//end

    public String getProcessDetailsQuery(ProcessDetailBean filterObj, ViewClientMast clientMast, Assessment ass,int minVal, int maxVal){
        System.out.println("filterObj.."+filterObj.getTemplate_code());
        System.out.println("filterObj.."+filterObj.getTemplate_name());
     String return_sql ="select * from(SELECT  row_number() over (order by 1) SLNO,A.PROCESS_SEQNO,\n" +
                        "b.bank_branch_code,\n"+
                        "A.PROCESS_REMARK, \n"+
                        "A.CLIENT_CODE,\n" +
                        "A.process_status,\n"+
                        "A.USER_CODE,\n" +
                        "A.TEMPLATE_CODE,\n" +
                        "c.template_name,\n" +
                        "A.FILE_NO_OF_RECORD,\n" +
                        "to_char(A.process_start_timestamp,'DD-MM-RRRR HH:MI:SS' ) process_start_timestamp,\n" +
                        "to_char(A.process_end_timestamp,'DD-MM-RRRR HH:MI:SS' ) process_end_timestamp,"+
                        "A.IMPORT_FILENAME,\n" +
                        "A.record_insert_flag\n"+
                        "FROM LHSSYS_PROCESS_LOG A,client_mast b ,view_client_template c\n" +
                        " where a.template_code =c.template_code\n" +
                        " and c.view_name ='Y'";
     
     return_sql =return_sql +"and b.entity_code = A.entity_code \n" +
"                             and b.client_code = A.client_code                    \n" +
"                               and (b.client_code = '" + clientMast.getClient_code() + "' Or                    \n" +
"                                   b.parent_code = '" + clientMast.getClient_code() + "' OR                       \n" +
"                                    b.g_parent_code = '" + clientMast.getClient_code() + "' or                      \n" +
"                                    b.sg_parent_code = '" + clientMast.getClient_code() + "' OR                      \n" +
"                                       b.ssg_parent_code = '" + clientMast.getClient_code() + "' or                       \n" +
"                                  b.sssg_parent_code = '" + clientMast.getClient_code() + "'                      \n" +
"                                     )                    \n" +
"                                 and A.entity_code = '" + clientMast.getEntity_code() + "'                      \n" +
"                                  and A.acc_year = '" + ass.getAccYear() + "'                      \n" +
"                               and A.quarter_no = '" + ass.getQuarterNo() + "'                     \n" +
"                               and A.tds_type_code = '" + ass.getTdsTypeCode() + "' ";
     
     
     if(!filterObj.getTemplate_code().isEmpty()){
         return_sql = return_sql+" and a.template_code='"+ filterObj.getTemplate_code()+"'";
     }
     if(!filterObj.getProcess_seqno().isEmpty()){
          return_sql = return_sql+"and a.process_seqno='"+filterObj.getProcess_seqno()+"'";
     }
     return_sql = return_sql + "order by  A.PROCESS_SEQNO desc)";
     if(minVal != 0 && maxVal != 0){
      return_sql += " where 1=1 AND slno BETWEEN " + minVal + " AND " + maxVal + "";
     }
     return return_sql;
    }


    private String getQueryWhereClause(ProcessDetailBean filterObj) {
        String whr_clause = "";

        if (filterObj.getProcess_seqno() != null && !filterObj.getProcess_seqno().isEmpty()) {
            whr_clause += " AND A.PROCESS_SEQNO = '" + filterObj.getProcess_seqno() + "'\n";
        }

        if (filterObj.getTemplate_code() != null && !filterObj.getTemplate_code().isEmpty()) {
            whr_clause += " AND A.TEMPLATE_CODE = '" + filterObj.getTemplate_code() + "'\n";
        }

        return whr_clause;
    }//end

    public String getProcessDetailsGrid(ArrayList<ProcessDetailBean> details, Util utl) {
       
        StringBuilder procDetail = new StringBuilder();
        System.out.println("inside string builder");
        procDetail.append("  <table id=\"table\" class=\"table table-striped mb-1\">");
        procDetail.append("                    <thead>");
        procDetail.append("                    <tr><th class=\"text-center\">Action</th>");
        procDetail.append("                    <th class=\"text-center\">Sr No.</th>");
        procDetail.append("                    <th class=\"text-center\"><div class=\"table-head-inner\"><div class=\"table-heading\">Token No.</div></div></th>");
        procDetail.append("                    <th class=\"text-center\"><div class=\"table-head-inner\"><div class=\"table-heading\">Branch Code</div></div></th>");
        procDetail.append("                    <th class=\"text-center\"><div class=\"table-head-inner\"><div class=\"table-heading\">User Code</div></div></th>");
        procDetail.append("                    <th class=\"text-center\"><div class=\"table-head-inner\"><div class=\"table-heading\">Template Name</div></div></th>");
        procDetail.append("                    <th class=\"text-center\"><div class=\"table-head-inner\"><div class=\"table-heading\">File No of Records</div></div></th>");
        procDetail.append("                    <th class=\"text-center\"><div class=\"table-head-inner\"><div class=\"table-heading\">Process Start Time</div></div></th>");
        procDetail.append("                    <th class=\"text-center\"><div class=\"table-head-inner\"><div class=\"table-heading\">Process End Time</div></div></th>");
        procDetail.append("                    <th class=\"text-center\"><div class=\"table-head-inner\"><div class=\"table-heading\">Process Remark</div></div></th>");
        procDetail.append("                    <th class=\"text-center\"><div class=\"table-head-inner\"><div class=\"table-heading\">Process Log</div></div></th>");
        procDetail.append("                    <th class=\"text-center\"><div class=\"table-head-inner\"><div class=\"table-heading\">Kill Process</div></div></th>");
        procDetail.append("                    <th class=\"text-center\"><div class=\"table-head-inner\"><div class=\"table-heading\">Get Uploded File</div></div> </th></tr>");
        procDetail.append("                    </thead>");
        procDetail.append("<tbody>");
       
        if (details != null && details.size() > 0) {
            int count = 0;
            
            for (ProcessDetailBean detail : details) {
//                count = details.indexOf(detail) + 1;
                count = Integer.valueOf(detail.getSlno());
                String import_filepath = detail.getImport_filename() != null? detail.getImport_filename():"";
                procDetail.append("<input type=\"hidden\" id=\"process_seq~" + count + "\" value=\"" + detail.getProcess_seqno() + "\">");
                procDetail.append("<input type=\"hidden\" id=\"template_code~" + count + "\" value=\"" + detail.getTemplate_code() + "\">");
                procDetail.append("<input type=\"hidden\" id=\"import_filepath~" + count + "\" value=\"" + import_filepath + "\">");
               
                procDetail.append("<tr>");
                if(!utl.isnull(detail.getProcess_remark()) && detail.getProcess_remark().equalsIgnoreCase("Process Seqno Wise Deletion started") ){
                procDetail.append("<td class=\"text-center\">");
                procDetail.append("<i class=\"fa delete \" aria-hidden=\"true\" title=\"Delete Process\" data-toggle=\"tooltip\" "
                        + "data-placement=\"top\"  disabled></i>");
                procDetail.append("</td>");
                }else{
                procDetail.append("<td class=\"text-center\">");
                procDetail.append("<i class=\"fa delete text-danger cursor-pointer\" aria-hidden=\"true\" title=\"Delete Process\" data-toggle=\"tooltip\" "
                        + "data-placement=\"top\" onclick=\"deleteProcessByProcessId(" + count + ");\"></i>");
                procDetail.append("</td>");
                }
            
                procDetail.append("<td class=\"td-action text-center\">");
                procDetail.append(count);
                procDetail.append("</td>");
              
                procDetail.append("<td class=\"text-center\">");
                procDetail.append(detail.getProcess_seqno());
                procDetail.append("</td>");

                procDetail.append("<td class=\"text-center\">");
                procDetail.append(detail.getBank_branch_code());
                procDetail.append("</td>");
                
                procDetail.append("<td class=\"text-center\">");
                procDetail.append(detail.getUser_code());
                procDetail.append("</td>");
                
                procDetail.append("<td class=\"\">");
                procDetail.append(detail.getTemplate_name());
                procDetail.append("</td>");
              
                procDetail.append("<td class=\"text-center font-weight-bold\">");
                procDetail.append(detail.getFile_no_of_record());
                procDetail.append("</td>");
                
                procDetail.append("<td class=\"text-center \">");
                procDetail.append(detail.getProcess_start_timestamp());
                procDetail.append("</td>");
                
                procDetail.append("<td class=\"text-center\">");
                procDetail.append(detail.getProcess_end_timestamp());
                procDetail.append("</td>");
               
                
                procDetail.append("<td class=\"\">");
                procDetail.append(detail.getProcess_remark());
                procDetail.append("</td>");
                
                procDetail.append("<td class=\"text-center\">");
                procDetail.append("<button type=\"button\" class=\"btn log-file-btn\" title=\"\" data-toggle=\"modal\" onclick=\"getProcessDetailsProcessLog('"+detail.getProcess_seqno()+"', '"+detail.getProcess_status()+"');\"><i class=\"fa fa-file-text\"></i></button>");
                procDetail.append("</td>");
                
                if(!utl.isnull(detail.getProcess_remark()) && detail.getProcess_remark().equalsIgnoreCase("Process Seqno Wise Deletion started") ){
                procDetail.append("<td class=\"text-center\">");
                procDetail.append("<button type=\"button\" class=\"btn kill-process-btn\" title=\"\" data-toggle=\"modal\" disabled><i class=\"fa fa-window-close\"></i></button>");
                procDetail.append("</td>");
                }else{
                procDetail.append("<td class=\"text-center\">");
                procDetail.append("<button type=\"button\" class=\"btn kill-process-btn\" title=\"\" data-toggle=\"modal\" onclick=\"killProcessDetails('"+detail.getProcess_seqno()+"', '"+detail.getRecord_insert_flag()+"');\"><i class=\"fa fa-window-close\"></i></button>");
                procDetail.append("</td>");   
                }
                
                if(!utl.isnull(detail.getProcess_remark()) && detail.getProcess_remark().equalsIgnoreCase("Process Seqno Wise Deletion started") ){
                procDetail.append("<td class=\"text-center\">");
                procDetail.append("<i class=\"fa download \"></i>");
                procDetail.append("</td>");
                }else{
                procDetail.append("<td class=\"text-center\">");
                procDetail.append("<i class=\"fa download cursor-pointer text-primary mr-1\" onclick=\"getUploadedFile("+count+");\"></i>");
                procDetail.append("</td>");   
                }
                //procDetail.append("<input type=\"hidden\"  id=\"process_seq_no~"+count +"\" value=\"" + detail.getProcess_seqno()+ "\"/>");
                //procDetail.append("<input type=\"hidden\"  id=\"record_insert_flag~"+count +"\" value=\"" + detail.getRecord_insert_flag()+ "\"/>");
               procDetail.append("</tr>");
                
            }
        } else {
            procDetail.append("<tr>");
            procDetail.append("<td class=\"td-action text-center\" colspan=\"8\">");
            procDetail.append(GlobalMessage.PAGINATION_NO_RECORDS);
            procDetail.append("</td>");
            procDetail.append("</tr>");

        }
        procDetail.append("</tbody>");
        procDetail.append("</table>");

        return procDetail.toString();
    }//end

    public List<ViewClientTemplate> getTemplateCodesAsMap() {
        List<ViewClientTemplate> templateCodes = new ArrayList<>();
        try {
            String templateCodesSql = "SELECT T.TEMPLATE_CODE, T.TEMPLATE_NAME\n"
                    + "  FROM VIEW_CLIENT_TEMPLATE T\n"
                    + " WHERE 1 = 1\n"
                    + "   AND T.TEMPLATE_CODE IN\n"
                    + "       ('TAXCPC01', 'CHALLAN_ALL', 'Deductee_15GH', 'Deductee_15GHD')\n"
                    + "   order by T.TEMPLATE_NAME";

            //System.out.println("templateCodesSql--" + templateCodesSql);
            DbFunctionExecutorAsString executer = new DbFunctionExecutorAsString();
            ArrayList<ArrayList<String>> codeList = executer.execute_oracle_query_as_list(templateCodesSql);

            if (codeList != null && !codeList.isEmpty()) {
                for (ArrayList<String> data : codeList) {
                    ViewClientTemplate template = new ViewClientTemplate();

                    template.setTemplate_code(data.get(0));
                    template.setTemplate_name(data.get(1));

                    templateCodes.add(template);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("templateCodes--" + templateCodes);
        return templateCodes;
    }//end
}//end
