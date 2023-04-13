/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.importExcelFiles;

import com.opensymphony.xwork2.ActionSupport;
import dao.LhssysEngineColsDAO;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.LhssysEngineCols;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author akash.dev
 */
public class FileUploadResult extends ActionSupport implements SessionAware {

    Map<String, Object> session;
    Util utl;
    private String action;
    private String sessionResult;
    private int NoOfColumns;
    private LinkedHashMap<String, String> selectErrorFilterList;
    private String EngineHeadingData;
    private Long importSeqNo;
    private String templateCode;
    private String processType;

    public FileUploadResult() {
        utl = new Util();

        selectErrorFilterList = new LinkedHashMap<String, String>();
    }//end constructor

    @Override
    public String execute() throws Exception {
        String l_return_value = "success";

        String result_value = (String) session.get("session_result");
        result_value = utl.isnull(result_value) ? "" : result_value;
        if (!utl.isnull(result_value)) {
            setSessionResult(result_value);
            session.remove("session_result");
        }

        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        String templeteTypeCode = !utl.isnull(getTemplateCode()) ? getTemplateCode() : "TAXCPC01";

        try {// used to create list of heading
            LhssysEngineColsDAO lhssysengcolsdao = factory.getLhssysEngineColsDAO();
            List<LhssysEngineCols> listLhssysEngineCols = lhssysengcolsdao.getDataAsEngineCode(templeteTypeCode);
            if (listLhssysEngineCols != null) {
                setNoOfColumns(listLhssysEngineCols.size());
                if (getEngineColumnList(listLhssysEngineCols).toString().length() > 0) {
                    setEngineHeadingData(getEngineColumnList(listLhssysEngineCols).toString());
                }
            }
        } catch (Exception e) {
        }
        return l_return_value;
    }//end method

    public StringBuilder getEngineColumnList(List<LhssysEngineCols> listLhssysEngineCols) {
        StringBuilder sb = new StringBuilder();
        try {
            int header_count = 0;
            for (LhssysEngineCols lhssysEngineCols : listLhssysEngineCols) {
                header_count++;
                if (header_count <= 9) {
                    sb.append("<th style=\"text-align:left;width: 30px;display: none;\">").append(lhssysEngineCols.getDisp_column()).append("</th>");
                } else if (header_count == 10) {
                    sb.append("<th style=\"text-align:left;width: 80px;\">Excel Sr.No.</th>");
                } else if (header_count > 10) {
                    sb.append("<th style=\"text-align:left;width: 30px;\">").append(lhssysEngineCols.getDisp_column()).append("</th>");
                }
            }
        } catch (Exception e) {
        }
        return sb;
    }//end method

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSessionResult() {
        return sessionResult;
    }

    public void setSessionResult(String sessionResult) {
        this.sessionResult = sessionResult;
    }

    public int getNoOfColumns() {
        return NoOfColumns;
    }

    public void setNoOfColumns(int NoOfColumns) {
        this.NoOfColumns = NoOfColumns;
    }

    public LinkedHashMap<String, String> getSelectErrorFilterList() {
        return selectErrorFilterList;
    }

    public void setSelectErrorFilterList(LinkedHashMap<String, String> selectErrorFilterList) {
        this.selectErrorFilterList = selectErrorFilterList;
    }

    public String getEngineHeadingData() {
        return EngineHeadingData;
    }

    public void setEngineHeadingData(String EngineHeadingData) {
        this.EngineHeadingData = EngineHeadingData;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public Long getImportSeqNo() {
        return importSeqNo;
    }

    public void setImportSeqNo(Long importSeqNo) {
        this.importSeqNo = importSeqNo;
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
