/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.generateFVU;

import dao.LhssysFileTranDAO;
import dao.LhssysProcessLogDAO;
import dao.generic.DAOFactory;
import globalUtilities.Util;
import hibernateObjects.LhssysFileTran;
import hibernateObjects.LhssysProcessLog;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author aniket.naik
 */
public class GetUserCodeAndProcessId {

    private Util utl;

    public GetUserCodeAndProcessId() {
        utl = new Util();
    }

    public LinkedHashMap<String, String> getUserCodeAndProcessId(String fvuVersion) {
        LinkedHashMap<String, String> processIdAndNameList = new LinkedHashMap<String, String>();
        try {
            String line;
//          String tasklist = "tasklist";
//          String tasklist = "jps";
//          String tasklist = "jps -l";
//          String tasklist = "jps -m";
            String tasklist = "jps -lm";
            Process p = Runtime.getRuntime().exec(tasklist);
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                //System.out.println(line);
                String[] lineArray = {};
                lineArray = line.split(" ");

                if (lineArray.length > 8) {
                    processIdAndNameList = getProcessIDAndName(lineArray, processIdAndNameList, fvuVersion);
                }
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return processIdAndNameList;
    }

    private LinkedHashMap<String, String> getProcessIDAndName(String[] processMsgArray, LinkedHashMap<String, String> processIdAndNameList, String fvuVersion) {

        String jarName = "TDS_STANDALONE_FVU_" + fvuVersion + ".jar";
//        String jarName = "TDS_STANDALONE_FVU_5.1.jar";
        //System.out.println("\n processMsgArray[1] : " + processMsgArray[1]);
     
//        String getJar = "Z:\\TAXCPC\\FVU49\\TDS_STANDALONE_FVU_5.0.jar"; //// challan
//        String getJar = "TDS_STANDALONE_FVU_4.9.jar";  //// FVU
        String getJar = processMsgArray[1].trim();

        if (getJar.endsWith(jarName)) {
            utl.generateLog("JAR Name :- ", jarName);
            utl.generateLog(null, "JAR Match");

            String txtFilePath = processMsgArray[2].trim();

            boolean isProcessIsAlreadyRun = checkProcessIsRun(processIdAndNameList, txtFilePath);

            processIdAndNameList.put(txtFilePath, processMsgArray[0]);

        } else {
        }
        return processIdAndNameList;
    }
    
    public boolean updateDataByFileName(String txtFileName, String path, String processId, String processFlag) {
        boolean result = false;

        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        LhssysFileTranDAO lhssysFileTranDAO = factory.getLhssysFileTranDAO();

        LhssysFileTran fileTran = lhssysFileTranDAO.readDataByFileName(txtFileName);

        fileTran.setPid_no(processId);
        fileTran.setPid_flag(processFlag);
        fileTran.setFile_name(txtFileName);
        fileTran.setFvu_storage_file_path(path);

        LhssysFileTranDAO lhssysFileTranDAO1 = factory.getLhssysFileTranDAO();
        result = lhssysFileTranDAO1.update(fileTran);
        return result;
    }// end updateDataByFileName

    public boolean checkProcessIsRun(LinkedHashMap<String, String> processIdAndNameList, String txtFilePath) {
        boolean isProcessIsAlreadyRun = false;
        try {
            for (Map.Entry<String, String> entry : processIdAndNameList.entrySet()) {
                //System.out.println("EmpCode : " + entry.getKey() + " Process ID : " + entry.getValue());
                if (txtFilePath.equalsIgnoreCase(entry.getKey())) {

                    isProcessIsAlreadyRun = killProcessById(entry.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isProcessIsAlreadyRun;
    }

    private boolean killProcessById(String processId) {
        boolean result = false;
        try {
            String cmd = "taskkill /F /PID " + processId;
            Runtime.getRuntime().exec(cmd);
            utl.generateLog("Kill Process Id : ", processId);
            result = true;
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    //Update Process ID and Process Flag in DataBase using TextFileName
//    public boolean updateDataByFileName(String txtFileName, String path, String processId, String processFlag) {
//        boolean result = false;
//
//        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
//        LhssysFileTranDAO lhssysFileTranDAO = factory.getLhssysFileTranDAO();
//
//        LhssysFileTran fileTran = lhssysFileTranDAO.readDataByFileName(txtFileName);
//
//        fileTran.setPid_no(processId);
//        fileTran.setPid_flag(processFlag);
//        fileTran.setFile_name(txtFileName);
//        fileTran.setFvu_storage_file_path(path);
//
//        LhssysFileTranDAO lhssysFileTranDAO1 = factory.getLhssysFileTranDAO();
//        result = lhssysFileTranDAO1.update(fileTran);
//        return result;
//    }// end updateDataByFileName
    public boolean updatePIDByTokenNo(String tokenNo, String processId, String processFlag) {
        
        boolean result = false;

        DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
        LhssysProcessLogDAO fileTranDAO2 = factory.getLhssysProcessLogDAO();
        //LhssysProcessLog log = fileTranDAO2.readById(Long.parseLong(tokenNo), true);
          LhssysProcessLog log = fileTranDAO2.readProcessbySeqNo(Long.parseLong(tokenNo));
        if (log != null) {
            log.setFvu_pid(processId);
            log.setFvu_pid_flag(processFlag);
            LhssysProcessLogDAO fileTranDAO = factory.getLhssysProcessLogDAO();
            result = fileTranDAO.update(log);
            
        }
        return result;
    }// end updateDataByFileName

    public boolean checkAllFileAreDownload(String directoryLocation) {
        boolean result = false;
//        System.out.println("\n\n");
        for (int i = 1; i < 10; i++) {
            utl.generateLog("For FVU check File Are Download ", i);
            result = allFileAreDownload(directoryLocation);
            if (result) {
                //System.out.println("break");
                try {
                    Thread.sleep(10000);//if all file are download but file not write completly then increase this time
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            try {
                Thread.sleep(30000);//increse time when required
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        utl.generateLog("For FVU result-----", result);

        return result;
    }

    public boolean checkAllFileAreDownload(String directoryLocation, String csi) {
        boolean result = false;
//        System.out.println("\n\n");
        for (int i = 1; i < 10; i++) {
            utl.generateLog("For CSI check file Are Download ", i);
            result = allFileAreDownload(directoryLocation, csi);
            if (result) {
                //System.out.println("break");
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            try {
                Thread.sleep(20000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        utl.generateLog("For CSI result---------", result);

        return result;
    }

    private boolean allFileAreDownload(String directoryLocation) {
        utl.generateLog(" for FVU Path : ", directoryLocation);
        boolean result = false;
        File directory = new File(directoryLocation);
        File[] fvuFilesList = directory.listFiles();
        int totalFile = 6;
        int countFile = 0;
//        
//        File[] fvuFilesList = fvuFile.listFiles();
//        if (fvuFilesList != null) {
        for (File file : fvuFilesList) {
//            System.out.println("file name..."+file.getName());
            if (file.getName().endsWith(".txt")) {
                countFile++;

            } else if (file.getName().endsWith(".html")) {
                countFile++;

            } else if (file.getName().endsWith(".fvu.log")) {
                countFile++;

            } else if (file.getName().endsWith(".rtf")) {
                countFile++;

            } else if (file.getName().endsWith(".err")) {
                countFile++;

            } else if (file.getName().endsWith(".bat")) {
                countFile++;

            }
        }
//        }
        utl.generateLog(" for FVU totalFile.." + totalFile + "\n for FVU countFile..", countFile);

        if (totalFile <= countFile) {
            utl.generateLog(null, " for FVU total File are Download");
            result = true;
        }
        return result;
    }

    private boolean allFileAreDownload(String directoryLocation, String csi) {
        utl.generateLog("For CSI File Path : ", directoryLocation);
        boolean result = false;
        File directory = new File(directoryLocation);
        File[] fvuFilesList = directory.listFiles();
        int totalFile = 5;
        int countFile = 0;
//        
//        File[] fvuFilesList = fvuFile.listFiles();
//        if (fvuFilesList != null) {
        for (File file : fvuFilesList) {
//            System.out.println("file name..."+file.getName());
            if (file.getName().endsWith(".txt")) {
                countFile++;

            } else if (file.getName().endsWith(".html")) {
                countFile++;

            } else if (file.getName().endsWith(".fvu.log")) {
                countFile++;

            } else if (file.getName().endsWith(".rtf")) {
                countFile++;

//            } else if (file.getName().endsWith(".err")) {
//                countFile++;
            } else if (file.getName().endsWith(".bat")) {
                countFile++;

            }
        }
//        }

        utl.generateLog("For CSI totalFile.." + totalFile + "\nFor CSI countFile..", countFile);
        if (totalFile <= countFile) {
            utl.generateLog(null, "For CSI  File are Download");
            result = true;
        }
        return result;
    }

    public String getPathAndName(String txtFile) {
        String result = "";
//        String txtFile = "Z:\\CHALLAN_VERIFICATION_CSI\\730\\15-16\\Q1\\24Q\\19919027.txt";
        //System.out.println("" + txtFile);
        String[] strArray = txtFile.split("\\\\");
        StringBuilder sb = new StringBuilder();
        //// Z:\CHALLAN_VERIFICATION_CSI\730\15-16\Q1\24Q\19919027.txt
        for (int i = 0; i < (strArray.length - 1); i++) {
//            //System.out.println("" + strArray[i]);
            sb.append(strArray[i]);
            sb.append(File.separator);
        }
        String path = sb.toString().substring(0, sb.length() - 1);

        String fileName = strArray[(strArray.length - 1)];
        result = path + "~" + fileName;
        //System.out.println("Path : " + result);

        return result;
    }

}
