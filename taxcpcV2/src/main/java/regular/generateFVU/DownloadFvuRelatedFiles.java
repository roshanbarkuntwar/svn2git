/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.generateFVU;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.io.File;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author ayushi.jain
 */
public class DownloadFvuRelatedFiles extends ActionSupport implements SessionAware {
    
    @Override
    public String execute() {
        String returnType = "success";
        
        try {
            ViewClientMast client = (ViewClientMast) session.get("WORKINGUSER");
            Assessment asmt = (Assessment) session.get("ASSESSMENT");
            String zipFileName = client.getTanno() + "_" + asmt.getAccYear() + "_" + asmt.getQuarterNo() + "_" + asmt.getTdsTypeCode() + ".zip";
//            System.out.println("zipFileName--" + zipFileName);
            String query = "select t.fvu_files_path from LHSSYS_PROCESS_LOG t WHERE T.PROCESS_SEQNO ='" + getTokenNo() + "'";
            
            DbFunctionExecutorAsString db = new DbFunctionExecutorAsString();
            fileNamePath = db.execute_oracle_function_as_string(query);
            if (!utl.isnull(fileNamePath)) {
                File f = new File(fileNamePath);
                if (f.exists()) {
                    File list[] = f.listFiles();
                    if (list != null && list.length > 0) {
                        for (File file : list) {
                            if (file.getName().endsWith(".zip") && file.getName().equalsIgnoreCase(zipFileName)) {
                                fileNamePath = fileNamePath + File.separator + file.getName();
                                break;
                            }
                            
                        }
                    }
                }
            }
//            fileNamePath = "H:\\FVU_RELATED_FILES\\LHS\\18-19\\Q1\\26Q\\Q126QC.rtf";

            
        } catch (Exception e) {
            
        }
        
        return returnType;
    }
    
    private Map<String, Object> session;
    final Util utl;
    private String fileNamePath;
    private String tokenNo;
    
    public String getTokenNo() {
        return tokenNo;
    }
    
    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }
    
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
    
    public DownloadFvuRelatedFiles() {
        utl = new Util();
    }
    
    public String getFileNamePath() {
        return fileNamePath;
    }
    
    public void setFileNamePath(String fileNamePath) {
        this.fileNamePath = fileNamePath;
    }
}
