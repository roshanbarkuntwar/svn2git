/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminDashboard;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.ErrorType;
import com.lhs.taxcpcv2.bean.GlobalMessage;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DAOFactory;
import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import regular.challan.ChallanFilterEntity;
import regular.dashboard.bulkTxtFile.BulkTextFileBean;
import regular.dashboard.bulkTxtFile.BulkTextFileSupport;

/**
 *
 * @author akash.meshram
 */
public class userLoginDetailsDataGrid extends ActionSupport implements SessionAware {
     
    public userLoginDetailsDataGrid() {
        utl = new Util();
    }

    @Override
    public String execute() throws Exception {
   String return_view = "grid_success";
        String return_value = "";
        session.put("ERRORCLASS", ErrorType.errorMessage);
        StringBuilder sb = new StringBuilder();
        System.out.println("datagridaction");
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            ViewClientMast clientMast = ((ViewClientMast) session.get("WORKINGUSER"));
            Assessment assessment = (Assessment) session.get("ASSESSMENT");
         

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

//                            BulkTextFileSupport querysupp = new BulkTextFileSupport();
//                            String Query_String = querysupp.getBulkTextFileQuery(bulkTextFileBean, clientMast, assessment, l_record_MNL, l_record_MXL,getTokenNo());
//                            System.out.println("datagridQuery-" + Query_String);
//                            DbGenericFunctionExecutor db = new DbGenericFunctionExecutor();
//                            ArrayList<BulkTextFileBean> BulkTextFileList = db.getGenericList(new BulkTextFileBean(), Query_String);
//                            return_value = querysupp.getProcessDetailsGrid(BulkTextFileList, utl,minVal,BLOB_FILE_DOWNLOAD_FLAG,oracalDrive);

                        }
                    }
                } else {
                    sb.append(GlobalMessage.PAGINATION_NO_RECORDS);
                }
                inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
            } else {
                sb.append(GlobalMessage.PAGINATION_SHOW_MORE);

                inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
            }
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(return_value.getBytes("UTF-8"));

        return return_view;
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
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public Util getUtl() {
        return utl;
    }

    public void setUtl(Util utl) {
        this.utl = utl;
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
    
}
