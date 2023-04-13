/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correction.importFile;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DAOFactory;
import dao.globalDBObjects.GetGlobalList;
import globalUtilities.Util;
import hibernateObjects.LhssysCorrFileUploadTran;
import hibernateObjects.ViewClientMast;
import hibernateObjects.ViewCorrType;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class CorrImportFileAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        String returnType = "success";
        session.put("ACTIVE_TAB", "corrImport");
        ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
        Assessment asmt = (Assessment) session.get("ASSESSMENT");
        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        try {
            GetGlobalList gl = new GetGlobalList();
            selectedUploadTypeList = gl.getUploadFileType();
            List<ViewCorrType> list = factory.getViewCorrTypeDAO().getObjList();
            viewCorrTypeList = createViewCorrTypeList(list);
            fileNameList = createFilterFileNameList(client.getClient_code(), asmt.getTdsTypeCode(), asmt.getAccYear(), Double.parseDouble(asmt.getQuarterNo()));
            try {
                String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";
                destPath = javaDriveName + "/CORR_BULK_FILE/" + client.getClient_code() + "/" + asmt.getAccYear() + "/Q" + asmt.getQuarterNo() + "/" + asmt.getTdsTypeCode() + "/";
            } catch (Exception e) {
                e.printStackTrace();
                destPath = "";
                returnType = "upload_failed";
            }
            try {
                if (!setDisplayCleanDataValidation(destPath)) {
                    setDisplayCleanData("disabled=\"true\"");
                } else {
                    setDisplayCleanData("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("uploadzip")) {

                if (!utl.isnull(getPanNOCorrectionFileFileName())) {

                    try {
                        File destFile = new File(destPath + "PANNO_Correction/", getPanNOCorrectionFileFileName());

                        FileUtils.copyFile(getPanNOCorrectionFile(), destFile);

                        returnType = "upload_success";

                    } catch (IOException e) {
                        e.printStackTrace();
                        returnType = "upload_failed";
                    }

                }

                if (!utl.isnull(getCorrectionFileFileName())) {

                    try {
                        File destFile = new File(destPath, getCorrectionFileFileName());

                        FileUtils.copyFile(getCorrectionFile(), destFile);

                        returnType = "upload_success";

                    } catch (IOException e) {
                        e.printStackTrace();
                        returnType = "upload_failed";
                    }

                }

                if (!utl.isnull(getJustificationFileFileName())) {

                    try {
                        File destFile = new File(destPath + "Justification/", getJustificationFileFileName());

                        FileUtils.copyFile(getJustificationFile(), destFile);

                        returnType = "upload_success";

                    } catch (IOException e) {
                        e.printStackTrace();
                        returnType = "upload_failed";

                    }

                }

                if (returnType.equalsIgnoreCase("upload_success")) {

                    addActionError("All files are uploaded");
                    returnType = "upload_success";

                } else if (returnType.equalsIgnoreCase("sizeError")) {
                    returnType = "upload_failed";
                    addActionError("File Should Not Be Greater Than 20KB");
                } else {
                    returnType = "upload_failed";
                    addActionError("File Not Uploaded");
                }

                if (getSelectedUploadType().equalsIgnoreCase("S")) {
                    setNxtBtnDisable("");

                    returnType = "upload_failed";
                } else if (getSelectedUploadType().equalsIgnoreCase("M")) {
                    setNxtBtnDisable("");
                    returnType = "upload_failed";

                }
            } else {
                setNxtBtnDisable("disabled");

            }//end if

            // Start set data in Paginator
            ProcessUploadedFilesAction processUploadedFilesAction = new ProcessUploadedFilesAction();
            processUploadedFilesAction.setRecordsPerPage(getRecordsPerPage());
            processUploadedFilesAction.paginatorLogic(asmt, client.getClient_code(), asmt.getTdsTypeCode(), asmt.getAccYear(), getFilterData());
            setBrowseAction(processUploadedFilesAction.getBrowseAction());
            setTotalRecordsCount(processUploadedFilesAction.getTotalRecordsCount());
            setRecordsPerPage(processUploadedFilesAction.getRecordsPerPage());
            setPageNumber(processUploadedFilesAction.getPageNumber());
            setTotalPages(processUploadedFilesAction.getTotalPages());
            // End set data in Paginator

        } catch (Exception e) {
            e.printStackTrace();

        }
        return returnType;
    }

    public boolean setDisplayCleanDataValidation(String destFolder) {
        boolean status = false;
        try {

            File folder2 = new File(destFolder);
            File[] listOfFiles2 = folder2.listFiles();
            if (listOfFiles2 != null) {

                int count = listOfFiles2.length;
                if (count > 0) {
                    status = true;
                }

                for (int i = 0; i < listOfFiles2.length; i++) {
                }//End For

            }
        } catch (Exception e) {
            e.addSuppressed(e);
        }
//        System.out.println("setDisplayCleanDataValidation _______" + status);
        return status;
    }

    private LinkedHashMap<String, String> createFilterFileNameList(String client_code, String tds_type_code, String acc_year, Double l_period_no) {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("", "-----Select-----");
        try {

            int l_record_MNL = 0;
            int l_record_MXL = 0;
            String showAll = "DisplayAll";

            List<LhssysCorrFileUploadTran> objlist = new CorrImportDB().getDataGridQuery(client_code, tds_type_code, acc_year, l_period_no, l_record_MNL, l_record_MXL, showAll, null);
            if (objlist != null) {
                for (LhssysCorrFileUploadTran obj : objlist) {
                    map.put(obj.getFile_name(), obj.getFile_name());
                }//End For
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }//End Method

    public CorrImportFileAction() {
        utl = new Util();
    }
    Util utl;
    Map<String, Object> session;
    private LinkedHashMap<String, String> selectedUploadTypeList;
    private LinkedHashMap<String, String> viewCorrTypeList;
    private String destPath;
    private String displayCleanData;
    private String action;

    private File panNOCorrectionFile;
    private String panNOCorrectionFileContentType;
    private String panNOCorrectionFileFileName;

    private File correctionFile;
    private String correctionFileContentType;
    private String correctionFileFileName;

    private File justificationFile;
    private String justificationFileContentType;
    private String justificationFileFileName;

    private String nxtBtnDisable;
    private String selectedUploadType;
    private String selectCorrectionType;

    private String browseAction;
    private long totalRecordsCount;
    private String recordsPerPage;
    private String pageNumber;
    private int totalPages;

    private LhssysCorrFileUploadTran filterData;

    public LhssysCorrFileUploadTran getFilterData() {
        return filterData;
    }

    public void setFilterData(LhssysCorrFileUploadTran filterData) {
        this.filterData = filterData;
    }

    public String getBrowseAction() {
        return browseAction;
    }

    public void setBrowseAction(String browseAction) {
        this.browseAction = browseAction;
    }

    public long getTotalRecordsCount() {
        return totalRecordsCount;
    }

    public void setTotalRecordsCount(long totalRecordsCount) {
        this.totalRecordsCount = totalRecordsCount;
    }

    public String getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(String recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    private LinkedHashMap<String, String> fileNameList;

    public LinkedHashMap<String, String> getFileNameList() {
        return fileNameList;
    }

    public void setFileNameList(LinkedHashMap<String, String> fileNameList) {
        this.fileNameList = fileNameList;
    }

    public String getNxtBtnDisable() {
        return nxtBtnDisable;
    }

    public void setNxtBtnDisable(String nxtBtnDisable) {
        this.nxtBtnDisable = nxtBtnDisable;
    }

    public String getSelectedUploadType() {
        return selectedUploadType;
    }

    public void setSelectedUploadType(String selectedUploadType) {
        this.selectedUploadType = selectedUploadType;
    }

    public String getSelectCorrectionType() {
        return selectCorrectionType;
    }

    public void setSelectCorrectionType(String selectCorrectionType) {
        this.selectCorrectionType = selectCorrectionType;
    }

    public File getPanNOCorrectionFile() {
        return panNOCorrectionFile;
    }

    public void setPanNOCorrectionFile(File panNOCorrectionFile) {
        this.panNOCorrectionFile = panNOCorrectionFile;
    }

    public String getPanNOCorrectionFileContentType() {
        return panNOCorrectionFileContentType;
    }

    public void setPanNOCorrectionFileContentType(String panNOCorrectionFileContentType) {
        this.panNOCorrectionFileContentType = panNOCorrectionFileContentType;
    }

    public String getPanNOCorrectionFileFileName() {
        return panNOCorrectionFileFileName;
    }

    public void setPanNOCorrectionFileFileName(String panNOCorrectionFileFileName) {
        this.panNOCorrectionFileFileName = panNOCorrectionFileFileName;
    }

    public File getCorrectionFile() {
        return correctionFile;
    }

    public void setCorrectionFile(File correctionFile) {
        this.correctionFile = correctionFile;
    }

    public String getCorrectionFileContentType() {
        return correctionFileContentType;
    }

    public void setCorrectionFileContentType(String correctionFileContentType) {
        this.correctionFileContentType = correctionFileContentType;
    }

    public String getCorrectionFileFileName() {
        return correctionFileFileName;
    }

    public void setCorrectionFileFileName(String correctionFileFileName) {
        this.correctionFileFileName = correctionFileFileName;
    }

    public File getJustificationFile() {
        return justificationFile;
    }

    public void setJustificationFile(File justificationFile) {
        this.justificationFile = justificationFile;
    }

    public String getJustificationFileContentType() {
        return justificationFileContentType;
    }

    public void setJustificationFileContentType(String justificationFileContentType) {
        this.justificationFileContentType = justificationFileContentType;
    }

    public String getJustificationFileFileName() {
        return justificationFileFileName;
    }

    public void setJustificationFileFileName(String justificationFileFileName) {
        this.justificationFileFileName = justificationFileFileName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDisplayCleanData() {
        return displayCleanData;
    }

    public void setDisplayCleanData(String displayCleanData) {
        this.displayCleanData = displayCleanData;
    }

    public String getDestPath() {
        return destPath;
    }

    public void setDestPath(String destPath) {
        this.destPath = destPath;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public LinkedHashMap<String, String> getSelectedUploadTypeList() {
        return selectedUploadTypeList;
    }

    public void setSelectedUploadTypeList(LinkedHashMap<String, String> selectedUploadTypeList) {
        this.selectedUploadTypeList = selectedUploadTypeList;
    }

    public LinkedHashMap<String, String> getViewCorrTypeList() {
        return viewCorrTypeList;
    }

    public void setViewCorrTypeList(LinkedHashMap<String, String> viewCorrTypeList) {
        this.viewCorrTypeList = viewCorrTypeList;
    }

    private LinkedHashMap<String, String> createViewCorrTypeList(List<ViewCorrType> list) {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
//        map.put("", "-----Select-----");
        try {
            for (ViewCorrType viewCorrType : list) {
                map.put(viewCorrType.getPan_type(), viewCorrType.getPan_type_name());
            }//
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }//End Method

}
