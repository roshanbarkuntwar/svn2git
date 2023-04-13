package regular.challan;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.DbProc.ProcTdsChallanRebuild;
import dao.TdsTranLoadDAO;
import dao.ViewClientMastDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author aniket.naik
 */
public class ChallanAjaxAction extends ActionSupport implements SessionAware {

    @Override
    public String execute() throws Exception {

        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        ViewClientMastDAO viewclientdaodata = factory.getViewClientMastDAO();
        ViewClientMast client = viewclientdaodata.readClientByClientCode(((ViewClientMast) session.get("WORKINGUSER")).getClient_code());
        StringBuilder sb = new StringBuilder();
        Assessment asmt = (Assessment) session.get("ASSESSMENT");
        String entity_code = client.getEntity_code();
        String client_code = client.getClient_code();
        String accYr = asmt.getAccYear();
        String tds_type_code = asmt.getTdsTypeCode();
        String quarterNo = asmt.getQuarterNo();
        Date quarter_fromDate = asmt.getQuarterFromDate();
        Date quarter_toDate = asmt.getQuarterToDate();
        int a_tds_challan_rowid_seq = 0;
        String result = "error";
        String returnString = "success";

        try {

            if (!utl.isnull(getRebuild()) && getRebuild().equalsIgnoreCase("true")) {

                ProcTdsChallanRebuild challanProc = new ProcTdsChallanRebuild();
                String return_result = challanProc.executeProcedure(entity_code, client_code, accYr, quarterNo, quarter_fromDate, quarter_toDate, tds_type_code, a_tds_challan_rowid_seq, "D");
                if (!utl.isnull(return_result)) {
                    if (return_result.equalsIgnoreCase("0")) {
                        result = "success";
                    }
                    sb.append(result);
                }
            } else {
                if (!utl.isnull(getAction())) {
                    if (getAction().equalsIgnoreCase("getDeducteeCount")) {

                        TdsTranLoadDAO tdsTranLoadDAO = factory.getTdsTranLoadDAO();
                        Long deducteesCountForChallan = tdsTranLoadDAO.getDeducteesCountForChallan(getChallanRowIdSeq());
                        sb.append(String.valueOf(deducteesCountForChallan));
                    } else if (getAction().equalsIgnoreCase("getSaveRemark")) {
                        if (!utl.isnull(getFlag())) {
                            String l_query = "";
                            if (getFlag().equalsIgnoreCase("challan")) {
                                l_query = "update tds_challan_tran_load  set file_doc_code= '" + getReviewValue() + "'\n"
                                        + "where rowid_seq='" + getRowIdSeq() + "'";
                            } else if (getFlag().equalsIgnoreCase("deductee")) {
                                l_query = "update deductee_mast  set remark= '" + getReviewValue() + "'\n"
                                        + "where deductee_code='" + getRowIdSeq() + "'";
                            } else if (getFlag().equalsIgnoreCase("tds")) {
                                l_query = "update tds_tran_load  set tds_error_status1= '" + getReviewValue() + "'\n"
                                        + "where rowid_seq='" + getRowIdSeq() + "'";
                            }
                            DbFunctionExecutorAsString objDbFunctionClass = new DbFunctionExecutorAsString();
                            boolean updateResult = objDbFunctionClass.execute_oracle_update_function_as_string(l_query);
                            utl.generateLog("updateResult...", updateResult);
                            if (updateResult) {
                                sb.append("save");
                            } else {
                                sb.append("error");
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
        return returnString;
    }

    private Map<String, Object> session;

    private String rebuild;
    private final Util utl;
    private InputStream inputStream;
    private String action;
    private String flag;
    private String RowIdSeq;
    private String ReviewValue;
    private String challanRowIdSeq;

    public ChallanAjaxAction() {
        utl = new Util();
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getRebuild() {
        return rebuild;
    }

    public void setRebuild(String rebuild) {
        this.rebuild = rebuild;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getRowIdSeq() {
        return RowIdSeq;
    }

    public void setRowIdSeq(String RowIdSeq) {
        this.RowIdSeq = RowIdSeq;
    }

    public String getReviewValue() {
        return ReviewValue;
    }

    public void setReviewValue(String ReviewValue) {
        this.ReviewValue = ReviewValue;
    }

    public String getChallanRowIdSeq() {
        return challanRowIdSeq;
    }

    public void setChallanRowIdSeq(String challanRowIdSeq) {
        this.challanRowIdSeq = challanRowIdSeq;
    }

}
