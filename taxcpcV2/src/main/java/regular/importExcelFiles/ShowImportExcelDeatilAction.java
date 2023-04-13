/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.importExcelFiles;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author akash.dev
 */
public class ShowImportExcelDeatilAction extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;
    private String action;
    private InputStream inputStream;
    private int NoOfColumns;
    private String ErrorType;

    private int pageNumber;
    private int recordsPerPage;
    private int totalRecords;
    private int startIndex;
    private int endIndex;
    private int setRecPerPage;
    private String update_data_row;
    private String templateCode;
    private String processType;
    private Long importSeqNo;

    public ShowImportExcelDeatilAction() {
        utl = new Util();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String l_return_value = "success";
        String return_message;
        StringBuilder sb = new StringBuilder();
        try {
            if (!utl.isnull(getAction())) {
                if (getAction().equalsIgnoreCase("showImportDtl")) {
                    try {
                        ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");//use for procedure
                        Assessment asmt = (Assessment) session.get("ASSESSMENT");//use for procedure
//                        String l_entity_code = viewClientMast.getEntity_code();
//                        String l_client_code = viewClientMast.getClient_code();
////                        String TempleteCode = getTemplateCode();
//                        try {
//                            SetDatabaseValues objSetDatabaseValues = new SetDatabaseValues();
//                            objSetDatabaseValues.setClientCode(l_client_code);
//                            objSetDatabaseValues.setEntityCode(l_entity_code);
//                            objSetDatabaseValues.setAccYear(asmt.getAccYear());
//                            objSetDatabaseValues.setQuarterNo(Integer.parseInt(asmt.getQuarterNo()));
//                            objSetDatabaseValues.setTdsTypeCode(asmt.getTdsTypeCode());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
                        if (!utl.isnull(getErrorType())) {
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
                                        String whereClause = "";

                                        if (getErrorType().equalsIgnoreCase("AE")) {//All error data where clause
                                            whereClause = "and exists (select 1 from view_lhssys_template_error b where b.process_Seqno ='" + getImportSeqNo() + "' and b.lhssys_template_rowid_seq = t.rowid_seq) ";
                                        } else {//errror filter dat grid query
                                            whereClause = "and exists (select 1 from view_lhssys_template_error b where b.lhssys_template_rowid_seq = t.rowid_seq and b.error_code_str ='" + getErrorType() + "')";
                                        }

                                        String l_query
                                                = "select * from (select rownum slno, T1.* from (select t.* from lhssys_template t where t.entity_code = '" + viewClientMast.getEntity_code() + "' "
                                                + "and t.client_code = '" + viewClientMast.getClient_code() + "'\n"
                                                + "and t.acc_year = '" + asmt.getAccYear() + "'\n"
                                                + "and t.quarter_no = " + asmt.getQuarterNo() + ""
                                                + "and t.tds_type_code = '" + asmt.getTdsTypeCode() + "' and t.process_seqno = '" + getImportSeqNo() + "'" + whereClause + ""
                                                + "order by to_number(t.rowid_seq))T1)" + " where slno BETWEEN " + " " + (l_record_MNL) + " " + " AND " + " " + (l_record_MXL);
                                        
                                       
                                        utl.generateSpecialLog("Lhsssys Template Data grid Query----", l_query);
                                        GetMasterExcelDataListThroughWorkbook objdataworkbook = new GetMasterExcelDataListThroughWorkbook();
                                        ArrayList<ArrayList<String>> tempDataRecordList = objdataworkbook.getTempDataErrorDetail(l_query, utl);
                                        if (tempDataRecordList != null && tempDataRecordList.size() > 0) {
                                            int i = 0;
                                            for (ArrayList<String> arrayList : tempDataRecordList) {
                                                i++;
                                                int col_count = 0;
                                                sb.append("<tr id=\"row~").append(i).append("\">");
                                                sb.append("<input type=\"hidden\" id=\"editCheckBox~").append(i).append("\" name=\"editCheckBox\" value=\"false\">");
                                                sb.append("<td style=\"text-align:center; width: 20px;\"><input id=\"checkbox~").append(i).append("\" type=\"checkbox\" onclick=\"onClickCheckbox(").append(i).append(");\" style=\"position: relative;  opacity: 1; margin-left: 5px;\"></td>");
                                                sb.append("<td style=\"text-align:center; width: 20px;\"><span id=\"DeleteBtn~").append(i).append("\" onclick=\"DeleteErrorData(this.id);\" class=\"fa fa-trash text-danger cursor-pointer mr-1\" aria-hidden=\"true\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"Delete\" ></span></td>");
                                                for (String string : arrayList) {
                                                    col_count++;
                                                    if (col_count <= (getNoOfColumns() + 1)) {
                                                        if ((col_count > 1) && (col_count <= 10)) {
//                                                        String hiddendata = "<input type=\"hidden\" id=\"col" + i + "~" + (col_count - 1) + "\" name=\"objTempData[" + (i - 1) + "].entity_code\" value=\"" + string + "\"/>";
//                                                        sb.append(hiddendata);
//                                                        String hiddendata2 = "<input type=\"hidden\" id=\"col" + i + "~" + (col_count - 1) + "\" name=\"objTempData[" + (i - 1) + "].client_code\" value=\"" + string + "\"/>";
//                                                        sb.append(hiddendata2);
//                                                        String hiddendata3 = "<input type=\"hidden\" id=\"col" + i + "~" + (col_count - 1) + "\" name=\"objTempData[" + (i - 1) + "].acc_year\" value=\"" + string + "\"/>";
//                                                        sb.append(hiddendata3);
//                                                        String hiddendata4 = "<input type=\"hidden\" id=\"col" + i + "~" + (col_count - 1) + "\" name=\"objTempData[" + (i - 1) + "].assesment_acc_year\" value=\"" + string + "\"/>";
//                                                        sb.append(hiddendata4);
//                                                        String hiddendata5 = "<input type=\"hidden\" id=\"col" + i + "~" + (col_count - 1) + "\" name=\"objTempData[" + (i - 1) + "].quarter_no\" value=\"" + string + "\"/>";
//                                                        sb.append(hiddendata5);
//                                                        String hiddendata6 = "<input type=\"hidden\" id=\"col" + i + "~" + (col_count - 1) + "\" name=\"objTempData[" + (i - 1) + "].from_date\" value=\"" + string + "\"/>";
//                                                        sb.append(hiddendata6);
//                                                        String hiddendata7 = "<input type=\"hidden\" id=\"col" + i + "~" + (col_count - 1) + "\" name=\"objTempData[" + (i - 1) + "].to_date\" value=\"" + string + "\"/>";
//                                                        sb.append(hiddendata7);
//                                                        String hiddendata8 = "<input type=\"hidden\" id=\"col" + i + "~" + (col_count - 1) + "\" name=\"objTempData[" + (i - 1) + "].tds_type_code\" value=\"" + string + "\"/>";
//                                                        sb.append(hiddendata8);
//                                                        String hiddendata9 = "<input type=\"hidden\" id=\"col" + i + "~" + (col_count - 1) + "\" name=\"objTempData[" + (i - 1) + "].process_seqno\" value=\"" + string + "\"/>";
//                                                        sb.append(hiddendata9);
                                                        } else if (col_count == 11) {
                                                            sb.append("<td style=\"text-align:left; width: 80px;\"> \n");
                                                            sb.append("<input type=\"textfield\" style=\"border: none;background: inherit; text-align:center;width: 100%;\" id=\"col").append(i).append("~").append((col_count - 1)).append("\" name=\"objTempData[").append(i - 1).append("].rowid_seq\" value=\"").append(string).append("\" readonly=\"true\"/>");
                                                            sb.append("</td>");
                                                        } else if (col_count > 11) {
                                                            sb.append("<td style=\"text-align:left; width: 80px;\"> \n");
                                                            sb.append("<input type=\"textfield\" onblur=\"getChecked('").append(i).append("~").append((col_count - 1)).append("');\" style=\"border: none;background: inherit; text-align:center;white-space: nowrap;\" id=\"col").append(i).append("~").append((col_count - 1)).append("\" name=\"objTempData[").append(i - 1).append("].col").append(col_count - 1).append("\" value=\"").append(string).append("\" onclick=\"getTextValue(this.id);\" title=\"").append(string).append("\" />");
                                                            sb.append("</td>");
                                                        }
                                                    }
                                                }
                                                sb.append("</tr>");
                                            }
                                        } else {
                                            sb.append("<tr id=\"row").append("\">");
                                            sb.append("<td colspan=\"" + getNoOfColumns() + "\"> Records Not Found !</td>");
                                            sb.append("</tr>");
                                        }

                                        sb.append("<input type=\"hidden\" id=\"pageNumber\" name=\"pageNumber\" value=\"").append(getPageNumber()).append("\">");
                                    }
                                }
                            } else {
                                sb.append("<tr id=\"row").append("\">");
                                sb.append("<td colspan=\"" + getNoOfColumns() + "\"> Records Not Found !</td>");
                                sb.append("</tr>");
                            }
                        } else {
                            sb.append("<tr id=\"row").append("\">");
                            sb.append("<td colspan=\"" + getNoOfColumns() + "\">No Errors Found , Just Save and Process Your Records !</td>");
                            sb.append("</tr>");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //    System.out.println("sb.toString()--" + sb.toString());
                    inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
                }
            } else if (getSetRecPerPage() > 0) {
                session.put("EXCELIMPORTMASTRECPERPG", String.valueOf(getSetRecPerPage()));//bank records per page
                return_message = "success";
                //System.out.println("return_message--" + return_message);
                inputStream = new ByteArrayInputStream(return_message.getBytes("UTF-8"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return l_return_value;
    }//end method

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public int getNoOfColumns() {
        return NoOfColumns;
    }

    public void setNoOfColumns(int NoOfColumns) {
        this.NoOfColumns = NoOfColumns;
    }

    public String getErrorType() {
        return ErrorType;
    }

    public void setErrorType(String ErrorType) {
        this.ErrorType = ErrorType;
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

    public String getUpdate_data_row() {
        return update_data_row;
    }

    public void setUpdate_data_row(String update_data_row) {
        this.update_data_row = update_data_row;
    }

    public Long getImportSeqNo() {
        return importSeqNo;
    }

    public void setImportSeqNo(Long importSeqNo) {
        this.importSeqNo = importSeqNo;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

}//end class
