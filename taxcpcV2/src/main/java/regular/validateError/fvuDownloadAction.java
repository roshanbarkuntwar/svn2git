/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.validateError;

import com.lhs.taxcpcv2.bean.Assessment;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import dao.generic.DAOFactory;
import dao.globalDBObjects.GetTokenTransactions;
import globalUtilities.Util;
import hibernateObjects.LhssysParameters;
import hibernateObjects.UserMast;
import hibernateObjects.ViewClientMast;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author akash.meshram
 */
public class fvuDownloadAction extends ActionSupport implements SessionAware {
     @Override
    public String execute() throws Exception {
        try{
                System.out.println("fvuDownloadAction");
                ViewClientMast viewClientMast = null;
                viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
                Assessment asmt = (Assessment) session.get("ASSESSMENT");
       
                String javaDriveName = (String) session.get("JAVADRIVE") != null ? (String) session.get("JAVADRIVE") : "";
                    File f = new File(javaDriveName);
                   utl.generateLog("Drive Name is->",javaDriveName);     
                    if (!utl.isnull(javaDriveName)) {
                        // create path of all required directories
                        String clientDir = javaDriveName + File.separator + "FVU_RELATED_FILES" + File.separator + viewClientMast.getClient_code();
                        utl.generateLog("client Dir is--->",clientDir);     
                        String accYearDir = clientDir + File.separator + asmt.getAccYear();
                        String qtrDir = accYearDir + File.separator + "Q" + asmt.getQuarterNo();
                        String finalFVUDirectory = qtrDir + File.separator + asmt.getTdsTypeCode();
           utl.generateLog("final FVUDirectory  -> ", finalFVUDirectory);                  
           utl.generateLog("List of files and directories in the specified directory:", "");
        
         
         //Creating a File object for directory
         File directoryPath = new File(finalFVUDirectory);
         //List of all files and directories
         File filesList[] = directoryPath.listFiles();
         map=new HashMap<String,String>();
         System.out.println("List of files  in the specified directory:");
         utl.generateLog("Process_Status--", getProcessStatus());
         
         
        if(filesList!=null){
                for(File file : filesList) {
                        if(!utl.isnull(processStatus) && getProcessStatus().equalsIgnoreCase("FD")){    
                          if (file.getName().endsWith(".err")||file.getName().endsWith(".txt")||file.getName().endsWith(".html")){    
                        map.put(file.getName(), file.getAbsolutePath());
                        System.out.println("File name: "+file.getName());
                        System.out.println("File path: "+file.getAbsolutePath());
                        System.out.println("Size :"+file.getTotalSpace());
                        System.out.println(" ");

                            }
                        } 
                        
                        if(!utl.isnull(processStatus) && getProcessStatus().equalsIgnoreCase("FC")){      
                         if (file.getName().endsWith(".fvu")||file.getName().endsWith(".pdf")||file.getName().endsWith(".html")||file.getName().endsWith(".txt")){
                          map.put(file.getName(), file.getAbsolutePath());
                          System.out.println("File name: "+file.getName());
                          System.out.println("File path: "+file.getAbsolutePath());
                          System.out.println("Size :"+file.getTotalSpace());
                          System.out.println(" ");  
                           } 
                        }
         }//end for loop
        }else{
        utl.generateLog("Empty List of files  in the specified directory:--", "");
        }
        utl.generateLog("File List size is :--", map.size());       
            
       } 
        
     }catch(Exception e){
            e.printStackTrace();
    }
                
        
    return "success";
    }//end method

    public fvuDownloadAction() {
      utl = new Util();
    }

    
    
    
 InputStream inputStream;
 private String processStatus;
 private String action;
 private Map<String, Object> session;
 Util utl;
 private HashMap<String,String> map; 

 public String getProcessStatus() {
        return processStatus;
  }

 public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
 }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public HashMap<String, String> getMap() {
        return map;
    }

    public void setMap(HashMap<String, String> map) {
        this.map = map;
    }

   @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
 

    
}
