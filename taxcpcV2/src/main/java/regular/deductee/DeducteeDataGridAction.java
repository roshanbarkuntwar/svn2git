/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.deductee;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.ErrorType;
import com.lhs.taxcpcv2.bean.GlobalMessage;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbFunctionExecutorAsIntegar;
import dao.generic.DbFunctionExecutorAsString;
import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;


/**
 *
 * @author ayushi.jain
 */
public class DeducteeDataGridAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {

        String return_view = "grid_success";
        String return_value;
        session.put("ERRORCLASS", ErrorType.errorMessage);
        StringBuilder sb = new StringBuilder();
        try {

            ViewClientMast clientMast = ((ViewClientMast) session.get("WORKINGUSER"));
            Assessment assessment = (Assessment) session.get("ASSESSMENT");
            
             if(utl.isnull(getAction())){
                 
                
            if (!utl.isnull(getFilterFlag()) && getFilterFlag().equalsIgnoreCase("true")) {
                if (!utl.isnull(getSearch())) {

                    if (getTotalRecords() > 0) {

                        if (getRecordsPerPage() > 0) {
                            if (getPageNumber() > 0) {

                                int maxVal = getTotalRecords();
                                int minVal = 1;

                                if (getTotalRecords() > getRecordsPerPage()) {
                                    maxVal = getPageNumber() * getRecordsPerPage();
                                    minVal = maxVal - getRecordsPerPage() + 1;
                                    if (maxVal > getTotalRecords()) {
                                        maxVal = getTotalRecords();
                                    }
                                }

                                setStartIndex(minVal - 1);
                                setEndIndex((maxVal - 1));

                                int l_start_page = 0;
                                if (getPageNumber() == 0) {
                                    l_start_page = 1;
                                } else {
                                    l_start_page = getPageNumber();
                                }

                                int l_records_to_add = getRecordsPerPage();
                                int l_record_MXL = (l_start_page * l_records_to_add);
                                int l_record_MNL = ((l_start_page * l_records_to_add) - l_records_to_add) + 1;

                                if (clientMast != null) {

                                    DeducteeDB db = new DeducteeDB();
                                    String l_query = db.deducteeDataGridQuery(clientMast, assessment, l_record_MNL, l_record_MXL, getDeducteeFltr());
                                    System.out.println("queryyyy--"+l_query);
                                    DbGenericFunctionExecutor dbQueryList = new DbGenericFunctionExecutor();

                                    List<DeducteeBrowseEntity> deducteesByTypeList = dbQueryList.getGenericList(new DeducteeBrowseEntity(), l_query);

                                    DeducteeSupport ds = new DeducteeSupport();
                                    sb = ds.dataGrid(deducteesByTypeList, sb, getStartIndex(), getPageNumber());
                                } else {
                                    addActionError("some error occured");
                                    return_view = "input";
                                }
                            }
                        }
                    } else {

                        sb.append(GlobalMessage.PAGINATION_NO_RECORDS);
                    }
                    sb.append("<input type=\"hidden\" id=\"pageNumber\" name=\"pageNumber\" value=\"").append(getPageNumber()).append("\">");

                    inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));

                } else if (getSetRecPerPage() > 0) {
                    session.put("DEDUCTERECPERPG", String.valueOf(getSetRecPerPage()));
                    return_value = "success";
                    return_view = "input_success";
                    inputStream = new ByteArrayInputStream(return_value.getBytes("UTF-8"));
                }
            } else {
                sb.append(GlobalMessage.PAGINATION_SHOW_MORE);

                inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
            }
            
             }else if(!utl.isnull(getAction()) && getAction().equalsIgnoreCase("updatepannonew") ){
                 
               try{
                 String accyear = assessment.getAccYear();
                 String qtr = assessment.getQuarterNo();
                 String entitycode =clientMast.getEntity_code();
                
                 String Querystring =" update tds_tran_load t\n" +
"                             set t.deductee_panno = '"+getPannonew().toUpperCase()+"'\n" +
"                           where t.entity_code = '"+entitycode+"'\n" +
"                             and t.acc_year =  '"+accyear+"'\n" +
"                             and t.quarter_no = '"+qtr+"'\n" +
"                             and t.deductee_ref_code1 = '"+getDeductee_ref()+"' \n" +
"                             and t.deductee_name = '"+getDeductee_name()+"'";
                 System.out.println(Querystring);
                DbFunctionExecutorAsString executor = new DbFunctionExecutorAsString();
                int update_function_count = executor.execute_oracle_update_function_returnAffected_rows(Querystring);
                System.out.println("after updated query result--"+update_function_count);
//                if (update_function) {
//                       sb.append("success");
//                } else {
//                      sb.append("error");
//                }
                
                   sb.append(update_function_count);
                   inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
                   
               }catch(Exception e){
                   sb.append("0");  
                 inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));  
               }
                
                
              
             
            
             }else if(!utl.isnull(getAction()) && getAction().equalsIgnoreCase("getupdatePanCount") ){
                 try{
                String accyear = assessment.getAccYear();
                String qtr = assessment.getQuarterNo();
                String tds_type_code = assessment.getTdsTypeCode();
                String clientcode = clientMast.getClient_code();
                String entitycode =clientMast.getEntity_code();
                String deducteeref = getDeductee_ref();
                String deducteepanno = getDeductee_panno();
                 
                
                                    String Query = "select count(*)\n" +
                    "  from (select a.deductee_ref_code1,\n" +
                    "                       a.deductee_panno,\n" +
                    "                       a.deductee_name,\n" +
                    "                       a.name_as_panno name_as_panno,\n" +
                    "                       a.deductee_panno_verified\n" +
                    "                  from tds_tran_load a, client_mast b\n" +
                    "                 where b.entity_code = a.entity_code\n" +
                    "                   and b.client_code = a.client_code\n" +
                    "                   and (b.client_code = '"+clientcode+"' or \n" +
                    "                        b.parent_code = '"+clientcode+"'  or\n" +
                    "                        b.g_parent_code = '"+clientcode+"' or\n" +
                    "                        b.sg_parent_code = '"+clientcode+"' or\n" +
                    "                        b.ssg_parent_code = '"+clientcode+"' or\n" +
                    "                       b.sssg_parent_code = '"+clientcode+"')\n" +
                    "                   and a.entity_code = '"+entitycode+"'\n" +
                    "                   and a.acc_year = '"+accyear+"'\n" +
                    "                   and a.tds_type_code = '"+tds_type_code+"'\n" +
                    "                   and a.quarter_no = '"+qtr+"'\n" +
                    "                   and a.deductee_panno = '"+deducteepanno.toUpperCase()+"'\n" +
                    "                   and a.deductee_ref_code1 = '"+deducteeref+"')";
                        
                                    
                                    System.out.println("printquery-->"+Query);
                 
                  DbFunctionExecutorAsIntegar executor = new DbFunctionExecutorAsIntegar();
                  Long update_function_count = executor.execute_oracle_function_as_integar(Query);
                  sb.append(update_function_count);
                  inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
             
                 }catch(Exception e){
                       sb.append("0");  
                       inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
                     e.printStackTrace();
                 }
                 
             }
             
    
                
                
                
                     
        } catch (Exception e) {
            e.printStackTrace();
        }
        return return_view;
    }//end method

    public DeducteeDataGridAction() {
        utl = new Util();
    }
    Util utl;
    private Map<String, Object> session;
    private int pageNumber;
    private int recordsPerPage;
    private int totalRecords;
    private String search;
    private int startIndex;
    private int endIndex;
    private int setRecPerPage;
    InputStream inputStream;
    private String filterFlag;
    private DeducteeFilterEntity deducteeFltr;
    private String action;
    private String pannonew;
    private String deductee_name;
    private String deductee_ref;
    private String deductee_panno;
 
    public DeducteeFilterEntity getDeducteeFltr() {
        return deducteeFltr;
    }

    public void setDeducteeFltr(DeducteeFilterEntity deducteeFltr) {
        this.deducteeFltr = deducteeFltr;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public int getSetRecPerPage() {
        return setRecPerPage;
    }

    public void setSetRecPerPage(int setRecPerPage) {
        this.setRecPerPage = setRecPerPage;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(String filterFlag) {
        this.filterFlag = filterFlag;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPannonew() {
        return pannonew;
    }

    public void setPannonew(String pannonew) {
        this.pannonew = pannonew;
    }

    public String getDeductee_name() {
        return deductee_name;
    }

    public void setDeductee_name(String deductee_name) {
        this.deductee_name = deductee_name;
    }

    public String getDeductee_ref() {
        return deductee_ref;
    }

    public void setDeductee_ref(String deductee_ref) {
        this.deductee_ref = deductee_ref;
    }

    public String getDeductee_panno() {
        return deductee_panno;
    }

    public void setDeductee_panno(String deductee_panno) {
        this.deductee_panno = deductee_panno;
    }

    
    
}
