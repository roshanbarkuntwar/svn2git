/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correction.importFile;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcTdsCorrInsertBulk;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.ClientMast;
import hibernateObjects.LhssysCorrFileUploadTran;
import hibernateObjects.LhssysCorrFileUploadTranDAO;
import hibernateObjects.ViewClientMast;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author akash.dev
 */
public class ProcessUploadedFilesAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {

        String return_view = "success";

        if (!utl.isnull(getAction())) {
            ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
            Assessment asmt = (Assessment) session.get("ASSESSMENT");

            Long l_client_loginid_seq;
            Object sessionId = session.get("LOGINSESSIONID");
            try {
                l_client_loginid_seq = (Long) sessionId;
            } catch (Exception e) {
                l_client_loginid_seq = 0l;
            }

            if (getAction().equalsIgnoreCase("browseFileList")) {
                paginatorLogic(asmt, client.getClient_code(), asmt.getTdsTypeCode(), asmt.getAccYear(), getFilterData());

            } else if (getAction().equalsIgnoreCase("update")) {

                DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                String flag = "F";

//                ArrayList<String> TANNoList = new ArrayList<String>();
//                ArrayList<LhssysCorrFileUploadTran> TANNoFilePathList = new ArrayList<LhssysCorrFileUploadTran>();
                LinkedHashMap<String, LhssysCorrFileUploadTran> TANNoList = new LinkedHashMap<String, LhssysCorrFileUploadTran>();
                try {
                    if (getFileSeqnoList() != null) {

                        String lastFileName = "";
                        int result = 0;
                        if (!utl.isnull(getCheckAll())) {

//                            String client_code = "";
//                            String tds_type_code = "";
//                            String acc_year = "";
//                            Double l_period_no = 0d;
                            int l_record_MNL = 0;
                            int l_record_MXL = 0;
                            String showAll = "DisplayAll";

                            List<LhssysCorrFileUploadTran> objlist = new CorrImportDB().getDataGridQuery(client.getClient_code(), asmt.getTdsTypeCode(), asmt.getAccYear(), Double.parseDouble(asmt.getQuarterNo()), l_record_MNL, l_record_MXL, showAll, getFilterData());
                            if (objlist != null) {
                                fileSeqnoList = new ArrayList<String>(objlist.size());
                                for (LhssysCorrFileUploadTran obj : objlist) {
                                    fileSeqnoList.add(obj.getFile_seqno());
                                }//End For
                            }
                        }//If CheckAll != null

                        for (String fileSeqno : getFileSeqnoList()) {
//                            System.out.println("fileSeqno______" + fileSeqno);
//                            result = result + factory.getLhssysCorrFileUploadTranDAO().updateData(fileSeqno, flag);
//                            lastFileName = factory.getLhssysCorrFileUploadTranDAO().updateData(fileSeqno, flag);
                            LhssysCorrFileUploadTran obj = factory.getLhssysCorrFileUploadTranDAO().updateData(fileSeqno, flag);
                            if (obj != null) {

                                lastFileName = obj.getFile_name();

                                if (!utl.isnull(lastFileName)) {
                                    try {
                                        TANNoList.put(lastFileName.substring(0, 10), obj);

                                        if (!lastFileName.equalsIgnoreCase("0")) {
                                            result++;
                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }//End For

//                        System.out.println("\n\nStart copy file with the help of TAN No");
                        // Start copy file with the help of TAN No
                        if (TANNoList != null) {
                            String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";
                            for (Map.Entry<String, LhssysCorrFileUploadTran> entry : TANNoList.entrySet()) {
                                String TAN_No = entry.getKey();
                                LhssysCorrFileUploadTran obj = entry.getValue();

                                List<ClientMast> clientMastList = factory.getClientMastDAO().getClientMastByTanno(TAN_No);
                                for (ClientMast clientMast : clientMastList) {
                                    String clientDir = javaDriveName + File.separator + "CORR_FVU_RELATED_FILES" + File.separator + clientMast.getClient_code();
                                    String accYearDir = clientDir + File.separator + asmt.getAccYear();
                                    String qtrDir = accYearDir + File.separator + "Q" + asmt.getQuarterNo();
//                            String filePathForFVU = qtrDir + File.separator + "TDS_FILE";
                                    String filePathForFVU = qtrDir + File.separator + "TDS_FILE" + File.separator;

                                    String srcPath = obj.getStorage_file_path() + obj.getFile_name();

                                    utl.generateLog("filePathForFVU --167---------", filePathForFVU);
                                    utl.generateLog("srcPath --168---------", srcPath);

                                    File srcFile = new File(srcPath);
                                    File destFile = new File(filePathForFVU);

                                    FileUtils.copyFileToDirectory(srcFile, destFile);

                                }//End For
                            }//End Map
                        }// TANNoList != null
                        // End copy file with the help of TAN No

                        if (result > 0) {
                            addActionError("Zip File updated.");

                            String assement_acc_year = utl.ChangeAccYearToAssessmentAccYear(asmt.getAccYear());//change according to DB
                            String tds_quarter = asmt.getQuarterNo();
                            String[] quarterNameSplit = tds_quarter.split("-");
                            String quarterNo = quarterNameSplit[2];
                            if (!utl.isnull(quarterNo) && quarterNo.length() > 1) {
                                quarterNo = quarterNo.substring(1, 2);
                            }
                            int quarter_no = (int) Double.parseDouble(quarterNo);

//                            long client_login_session_seq_no = 0l;
                            String a_process_type = "T";
                            if (!utl.isnull(getProcess_type())) {
                                a_process_type = getProcess_type();
                            }
                            String a_template_code = asmt.getTdsTypeCode() + "_TDS";
                            String file_name = "";
                            if (getFileSeqnoList().size() == 1) {

                                file_name = lastFileName;
                            }
                            utl.generateLog("\n\n\n\n Call 'lhs_tds_imp_corr.proc_tds_corr_insert_bulk' ", "");
                            new ProcTdsCorrInsertBulk().execute_procedure(client.getEntity_code(), client.getClient_code(), asmt.getAccYear(), assement_acc_year,
                                    quarter_no, asmt.getQuarterFromDate(), asmt.getQuarterToDate(), asmt.getTdsTypeCode(), l_client_loginid_seq, a_process_type,
                                    a_template_code, file_name);
                        }
                    }//End if
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return_view = "updated";
//            }
            }// End Action 
        }// End Action 

        return return_view;
    }// End Execute Method

    public ProcessUploadedFilesAction() {
        utl = new Util();
    }

    public void paginatorLogic(Assessment asmt, String client_code, String tds_type_code, String acc_year, LhssysCorrFileUploadTran filterObj) {
        try {
            setBrowseAction("corrImportDataGridAction");

            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            LhssysCorrFileUploadTranDAO lhssysCorrFileUploadTranDAO = factory.getLhssysCorrFileUploadTranDAO();

            //get filter from session
            List<LhssysCorrFileUploadTran> lhssysCorrFileUploadTranList = lhssysCorrFileUploadTranDAO.readDataByParameter(client_code, tds_type_code, acc_year, Double.parseDouble(asmt.getQuarterNo()), filterObj, utl);
            setLhssysCorrFileUploadTranList(lhssysCorrFileUploadTranList);
            long total = 0;
            if (lhssysCorrFileUploadTranList != null) {
                total = lhssysCorrFileUploadTranList.size();
            }

            setTotalRecordsCount(total);
            int pages = 0;
            if (total > 0) {
                String recNumber = getRecordsPerPage();
                if (!utl.isnull(getSearch()) && getSearch().equals("true")) {
                    recNumber = (String) session.get("DEDUCTERECPERPG");
                } else {
                    recNumber = getRecordsPerPage();

                }
                setRecordsPerPage(utl.isnull(recNumber) ? "10" : recNumber);
                if (!getRecordsPerPage().equalsIgnoreCase("all")) {
                    int recVal = Integer.parseInt(getRecordsPerPage());

                    long mod = total % recVal;
                    int rem = 0;
                    if (mod > 0) {
                        rem = 1;
                    }
                    pages = (int) Math.ceil(total / recVal) + rem;
                } else {
                    pages = 0;
                }
                int pageNoInt = Integer.parseInt(utl.isnull(getPageNumber()) ? "0" : getPageNumber());
                if (utl.isnull(getPageNumber()) || pageNoInt > pages || pageNoInt == 0) {
                    setPageNumber("1");
                }
            } else {
                setPageNumber("0");
            }
            setTotalPages(pages);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//End Method

    private final Util utl;
    private String action;
    private Map<String, Object> session;

    List<LhssysCorrFileUploadTran> lhssysCorrFileUploadTranList;

    private String search;
    private long totalRecordsCount;
    private int totalPages;
    private String recordsPerPage;
    private String fetchRecords;
    private String pageNumber;
    private String resultMessage;
    private String code_level;
    private String browseAction;

    private List<String> fileSeqnoList;
    private String checkAll;

    private LhssysCorrFileUploadTran filterData;

    private String process_type;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public List<LhssysCorrFileUploadTran> getLhssysCorrFileUploadTranList() {
        return lhssysCorrFileUploadTranList;
    }

    public void setLhssysCorrFileUploadTranList(List<LhssysCorrFileUploadTran> lhssysCorrFileUploadTranList) {
        this.lhssysCorrFileUploadTranList = lhssysCorrFileUploadTranList;
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

    public String getCode_level() {
        return code_level;
    }

    public void setCode_level(String code_level) {
        this.code_level = code_level;
    }

    public String getBrowseAction() {
        return browseAction;
    }

    public void setBrowseAction(String browseAction) {
        this.browseAction = browseAction;
    }

    public List<String> getFileSeqnoList() {
        return fileSeqnoList;
    }

    public void setFileSeqnoList(List<String> fileSeqnoList) {
        this.fileSeqnoList = fileSeqnoList;
    }

    public String getProcess_type() {
        return process_type;
    }

    public void setProcess_type(String process_type) {
        this.process_type = process_type;
    }

    public String getCheckAll() {
        return checkAll;
    }

    public void setCheckAll(String checkAll) {
        this.checkAll = checkAll;
    }

    public LhssysCorrFileUploadTran getFilterData() {
        return filterData;
    }

    public void setFilterData(LhssysCorrFileUploadTran filterData) {
        this.filterData = filterData;
    }

}//End Class
