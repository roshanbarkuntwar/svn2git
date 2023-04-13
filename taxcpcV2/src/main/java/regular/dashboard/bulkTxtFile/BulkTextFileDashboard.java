/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.bulkTxtFile;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.TokenStatusAttribs;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbFunctionExecutorAsIntegar;
import dao.globalDBObjects.GetTokenTransactions;
import globalUtilities.Util;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author akash.meshram
 */
public class BulkTextFileDashboard extends ActionSupport implements SessionAware {

    public BulkTextFileDashboard() {
        utl = new Util();
    }

    @Override
    public String execute() throws UnsupportedEncodingException {
        String return_msg = "";
        StringBuilder sb = new StringBuilder();
        String returnView = "successDashboard";
        System.out.println("Bulk Text File Dashboard");
        
        try {
            ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
            Assessment assesment = (Assessment) session.get("ASSESSMENT");
            String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";
            String oracleDriveName = (String) session.get("ORACLEDRIVE") != null ? (String) session.get("ORACLEDRIVE") : "";
            
            
            UserMast user = (UserMast) session.get("LOGINUSER");
            if (!utl.isnull(search) && getSearch().equalsIgnoreCase("true")) {
               System.out.println("Bulk Text File Dashboard search");
              
                BulkTextFileSupport querysupp =new BulkTextFileSupport();
                String count_sql_query =querysupp.getBulkTextFileCountQuery(viewClientMast, assesment, bulkTextFileBean,getTokenNo());
                DbFunctionExecutorAsIntegar db = new DbFunctionExecutorAsIntegar();
                long total = db.execute_oracle_function_as_integar(count_sql_query);
                System.out.println("totaltotal-"+total);           
                
               
                        setTotalRecords(total);
                        setPageNumber(0);
                        setTotalRecordsCount(total);

                        int pages = 0;
                        if (total > 0) {
                            String recNumber = (String) session.get("PROCESSDELETERECPERPAGE");// records per page
                            setRecordsPerPage(utl.isnull(recNumber) ? 10 : Integer.parseInt(recNumber));
                            int recVal = getRecordsPerPage();
                            long mod = total % recVal;
                            int rem = 0;
                            if (mod > 0) {
                                rem = 1;
                            }
                            pages = (int) Math.ceil(total / recVal) + rem;

                            setPageNumber(1);
                        } else {
                            setPageNumber(0);
                        }

                        setTotalPages(pages);

                        String return_data = "";
                        if (getTotalRecordsCount() != 0) {
                            return_data = getTotalPages() + "#" + getTotalRecordsCount() + "#" + getRecordsPerPage() + "#" + getPageNumber();
                        } else {
                            return_data = 0 + "#" + 0 + "#" + 0 + "#" + 0;
                        }

                        return_msg = return_data;
                        returnView ="filter_success";
                
                
                
            }

        

        } catch (Exception e) {

        }
        inputStream = new ByteArrayInputStream(return_msg.getBytes("UTF-8"));
        return returnView;
    }//End Method

    private Map<String, Object> session;
    private Util utl;
    private InputStream inputStream;
    private String tokenNo;
    private String fileNameToDownload;
    private String downloadedFileName;
    private TokenStatusAttribs tokenUploadObj;
    private String action;
    private String processLevel;
    private String search;
    private String browseAction;
    
    private int recordsPerPage;
    private int pageNumber;
    private int startIndex;
    private int endIndex;
    private int totalPages;
    private long totalRecords;
    private long totalRecordsCount;
    private BulkTextFileBean bulkTextFileBean;

    public String getDownloadedFileName() {
        return downloadedFileName;
    }

    public void setDownloadedFileName(String downloadedFileName) {
        this.downloadedFileName = downloadedFileName;
    }

    public String getFileNameToDownload() {
        return fileNameToDownload;
    }

    public void setFileNameToDownload(String fileNameToDownload) {
        this.fileNameToDownload = fileNameToDownload;
    }

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public TokenStatusAttribs getTokenUploadObj() {
        return tokenUploadObj;
    }

    public void setTokenUploadObj(TokenStatusAttribs tokenUploadObj) {
        this.tokenUploadObj = tokenUploadObj;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getProcessLevel() {
        return processLevel;
    }

    public void setProcessLevel(String processLevel) {
        this.processLevel = processLevel;
    }

    public Util getUtl() {
        return utl;
    }

    public void setUtl(Util utl) {
        this.utl = utl;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public long getTotalRecordsCount() {
        return totalRecordsCount;
    }

    public void setTotalRecordsCount(long totalRecordsCount) {
        this.totalRecordsCount = totalRecordsCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

   

    public String getBrowseAction() {
        return browseAction;
    }

    public void setBrowseAction(String browseAction) {
        this.browseAction = browseAction;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
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

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public BulkTextFileBean getBulkTextFileBean() {
        return bulkTextFileBean;
    }

    public void setBulkTextFileBean(BulkTextFileBean bulkTextFileBean) {
        this.bulkTextFileBean = bulkTextFileBean;
    }

    
    
}
