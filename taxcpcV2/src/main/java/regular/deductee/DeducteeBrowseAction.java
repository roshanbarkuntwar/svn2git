/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.deductee;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsIntegar;
import globalUtilities.Util;
import hibernateObjects.LhssysParameters;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class DeducteeBrowseAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        String returnType = "success";
        
        setDataGridAction("deducteeDatagridAction");
        setBulkDownloadFor("TDS_DEDUCTEE");
        session.put("ACTIVE_TAB", "tdsDeductees");

        StringBuilder sb = new StringBuilder();
        try {

            ViewClientMast clientMast = (ViewClientMast) session.get("WORKINGUSER");
            Assessment assessment = (Assessment) session.get("ASSESSMENT");
            DeducteeDB db = new DeducteeDB();
            String l_query = db.deducteeCountQuery(clientMast, assessment, getDeducteeFltr());

//            DbFunctionExecutorAsIntegar objDBListCount = new DbFunctionExecutorAsIntegar();
//            long total = objDBListCount.execute_oracle_function_as_integar(l_query);
              DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
               try {
                        LhssysParameters lhssys_parameters = factory.getLhssysParametersDAO().readParametersBy("DEDUCTEE_FILTER_FLAG");
                        if (lhssys_parameters != null) {
                            setDeducteeFilterFlag(lhssys_parameters.getParameter_value());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

            long total = 0L;

            //paginator logic start..
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

            if (!utl.isnull(getSearch()) && getSearch().equalsIgnoreCase("true")) {
                DbFunctionExecutorAsIntegar objDBListCount = new DbFunctionExecutorAsIntegar();
                total = objDBListCount.execute_oracle_function_as_integar(l_query);
                //paginator logic start..
                setTotalRecordsCount(total);
                pages = 0;
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

                String return_data = "";
                if (getTotalRecordsCount() != 0) {
                    return_data = getTotalPages() + "#" + getTotalRecordsCount() + "#" + getRecordsPerPage() + "#" + getPageNumber();
                } else {
                    return_data = 0 + "#" + 0 + "#" + 0 + "#" + 0;
                }

                sb.append(return_data);
                returnType = "filter_success";
            } else {
                setTotalPages(pages);
                returnType = "success";
            }

            //paginator logic end
        } catch (Exception e) {

        }
        inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
        return returnType;
    }

    public DeducteeBrowseAction() {
        utl = new Util();
        verificationFlagList = new LinkedHashMap<String, String>();
        verificationFlagList.put("", "Select");
        verificationFlagList.put("Y", "Verified");
        verificationFlagList.put("N", "Not Verified");
        verificationFlagList.put("X", "Invalid");
    }
    Util utl;
    private Map<String, Object> session;
    private LinkedHashMap<String, String> verificationFlagList;
    private String search;
    private long totalRecordsCount;
    private int totalPages;
    private String recordsPerPage;
    private String fetchRecords;
    private String pageNumber;
    private String dataGridAction;
    private DeducteeFilterEntity deducteeFltr;
    private InputStream inputStream;
    private String bulkDownloadFor;
    private String deducteeFilterFlag;

    public String getBulkDownloadFor() {
        return bulkDownloadFor;
    }

    public void setBulkDownloadFor(String bulkDownloadFor) {
        this.bulkDownloadFor = bulkDownloadFor;
    }

    public DeducteeFilterEntity getDeducteeFltr() {
        return deducteeFltr;
    }

    public void setDeducteeFltr(DeducteeFilterEntity deducteeFltr) {
        this.deducteeFltr = deducteeFltr;
    }

    public String getDataGridAction() {
        return dataGridAction;
    }

    public void setDataGridAction(String dataGridAction) {
        this.dataGridAction = dataGridAction;
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

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public LinkedHashMap<String, String> getVerificationFlagList() {
        return verificationFlagList;
    }

    public void setVerificationFlagList(LinkedHashMap<String, String> verificationFlagList) {
        this.verificationFlagList = verificationFlagList;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getDeducteeFilterFlag() {
        return deducteeFilterFlag;
    }

    public void setDeducteeFilterFlag(String deducteeFilterFlag) {
        this.deducteeFilterFlag = deducteeFilterFlag;
    }

}
