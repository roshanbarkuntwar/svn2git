/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.importExcelFiles;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.LhssysTemplateDAO;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class FilterFileUploadResult extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        String l_return_value = "";
        StringBuilder sb = new StringBuilder();
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");//use for procedure
        Assessment asmt = (Assessment) session.get("ASSESSMENT");//use for procedure
        if (viewClientMast != null) {
            String l_entity_code = viewClientMast.getEntity_code();
            String l_client_code = viewClientMast.getClient_code();
//            String TdsTypeCode = (String) session.get("IMPORTMASTERTDSTYPE");
//            String AccYear = (String) session.get("IMPORTMASTERACCYEAR");
//            String QuarterNo = (String) session.get("IMPORTMASTERQUARTERNO");
//            String TempleteCode = (String) session.get("IMPORTMASTERTEMPLATEECODE");
            String TempleteCode = getTemplateCode();
            //paginator
            int pages = 0;
            setPageNumber("0");
            if (!utl.isnull(getSearch()) && getSearch().equalsIgnoreCase("true")) {
                setBrowseAction("getImportDetailAction");
                Long l_import_seq = getImportSeqNo();
                LhssysTemplateDAO objtempdao = factory.getLhssysTemplateDAO();
                Long total = 0l;
                if (!utl.isnull(getErrorType())) {

                    total = objtempdao.getTempDataCountAsErrorOrAllData(l_entity_code, l_client_code, asmt.getAccYear(), asmt.getQuarterNo(), asmt.getTdsTypeCode(), TempleteCode, getErrorType(), l_import_seq.toString(), utl);
                }
                System.out.println("totaltotal"+total);
                setPageNumber("0");
                setTotalRecordsCount(total);
                if (total > 0) {
                    String recNumber = (String) session.get("EXCELIMPORTMASTRECPERPG");//Excel records per page
                    setRecordsPerPage(utl.isnull(recNumber) ? "10" : recNumber);
                    if (!getRecordsPerPage().equalsIgnoreCase("all")) {
                        int recVal = Integer.parseInt(getRecordsPerPage());
                        //System.out.println("recVal.." + recVal);
                        long mod = total % recVal;
                        int rem = 0;
                        if (mod > 0) {
                            rem = 1;
                        }
                        pages = (int) Math.ceil(total / recVal) + rem;
                        //System.out.println("pages..." + pages);
                    } else {
                        pages = 0;
                    }
                    setPageNumber("1");
                }
                setTotalPages(pages);
                String return_data = getTotalPages() + "#" + getTotalRecordsCount() + "#" + getRecordsPerPage() + "#" + getPageNumber();
                sb.append(return_data);
                l_return_value = "filter_success";
            } else {
                setTotalPages(pages);
                l_return_value = "success";
            }
        } else {
            l_return_value = "success";
        }
        inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
        return l_return_value;
    }//end method

    public FilterFileUploadResult() {
        utl = new Util();
    }//end constructor

    Map<String, Object> session;
    Util utl;
    private Long importSeqNo;
    private InputStream inputStream;
    private String action;
    private String search;
    private long totalRecordsCount;
    private int totalPages;
    private String recordsPerPage;
    private String fetchRecords;
    private String pageNumber;
    private String resultMessage;
    private String browseAction;
    private String ErrorType;
    private String templateCode;

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public Long getImportSeqNo() {
        return importSeqNo;
    }

    public void setImportSeqNo(Long importSeqNo) {
        this.importSeqNo = importSeqNo;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public String getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(String recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public String getFetchRecords() {
        return fetchRecords;
    }

    public void setFetchRecords(String fetchRecords) {
        this.fetchRecords = fetchRecords;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getBrowseAction() {
        return browseAction;
    }

    public void setBrowseAction(String browseAction) {
        this.browseAction = browseAction;
    }

    public String getErrorType() {
        return ErrorType;
    }

    public void setErrorType(String ErrorType) {
        this.ErrorType = ErrorType;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

}//end class

