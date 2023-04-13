/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.lowerDeductCertificate;

import com.lhs.taxcpcv2.bean.Assessment;
import com.lhs.taxcpcv2.bean.GlobalMessage;
import com.lhs.taxcpcv2.bean.MessageType;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.Util;
import hibernateObjects.TdsSplRateMast;
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
public class LowerDeductionCertificateDataGrid extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {
        String return_view = "grid_success";
        String return_value;
        session.put("ERRORCLASS", MessageType.errorMessage);
        StringBuilder sb = new StringBuilder();

        try {

            ViewClientMast clientMast = ((ViewClientMast) session.get("WORKINGUSER"));
            Assessment ass = (Assessment) session.get("ASSESSMENT");

            if (!utl.isnull(getSearch())) {

                if (getTotalRecords() > 0) {

                    if (getRecordsPerPage() > 0) {
                        if (getPageNumber() > 0) {
//                            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
//                            TdsSplRateMastDAO tdsSplMastDAO = factory.getTdsSplRateMastDAO();
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
                                TdsSplRateMast tdsSplRateMast = getTdsSplRateMastFltrSrch() == null ? null : getTdsSplRateMastFltrSrch();
//                                List<TdsSplRateMast> tdsSplRateList = tdsSplMastDAO.getTdsSplRateByType(tdsSplRateMast, getSearch(), minVal - 1, getRecordsPerPage(), utl);

                                DbGenericFunctionExecutor db_gen = new DbGenericFunctionExecutor();
                                LowerDeducteeCertificateDB ld_data = new LowerDeducteeCertificateDB();

                                String dataSql = ld_data.getTdsSplRateDetailsQuery(clientMast, ass, tdsSplRateMast, utl, l_record_MNL, l_record_MXL);

                                utl.generateSpecialLog("Lower deductee details query", dataSql);

                                List<TdsSplRateMastDTO> tdsSplRateList = db_gen.getGenericList(new TdsSplRateMastDTO(), dataSql);

                                LowerDeductCertificateSupport lb = new LowerDeductCertificateSupport();
                                try {
                                    lb.getLowerCertificateGrid(sb, tdsSplRateList, utl, getStartIndex(), getPageNumber());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
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
                session.put("LOWERDEDUCTRECPERPG", String.valueOf(getSetRecPerPage()));
                return_value = "success";
                return_view = "input_success";
                inputStream = new ByteArrayInputStream(return_value.getBytes("UTF-8"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return return_view;
    }

    public LowerDeductionCertificateDataGrid() {
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
    private TdsSplRateMast tdsSplRateMastFltrSrch;

    public TdsSplRateMast getTdsSplRateMastFltrSrch() {
        return tdsSplRateMastFltrSrch;
    }

    public void setTdsSplRateMastFltrSrch(TdsSplRateMast tdsSplRateMastFltrSrch) {
        this.tdsSplRateMastFltrSrch = tdsSplRateMastFltrSrch;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
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
