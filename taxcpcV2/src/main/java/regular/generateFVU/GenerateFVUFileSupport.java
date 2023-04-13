/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.generateFVU;

import dao.DbProc.ProcGenerateTdsTextFile;
import dao.DbProc.ProcLhsTdsTranInsert;
import dao.TdsChallanTranLoadDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.FileOptUtil;
import globalUtilities.FileOptUtil.FileDeletePermanent;
import globalUtilities.Util;
import globalUtilities.ZipFileUtil;
import globalUtilities.ZipFileUtil.MultipleFileZip;
import hibernateObjects.TdsChallanTranLoad;
import hibernateObjects.ViewClientMast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author aniket.naik
 */
public class GenerateFVUFileSupport {

    public boolean processRecords(ViewClientMast client, String acc_year, int l_quarter_no, Date dateFromDate, Date dateToDate, String tds_type, long l_client_loginid_seq) {
        boolean tdsTranRecordsInserted = false;
        try {
            ProcLhsTdsTranInsert call_db_pr = new ProcLhsTdsTranInsert();
            int result = call_db_pr.execute_procedure(client.getClient_code(), client.getEntity_code(), l_quarter_no, dateFromDate, dateToDate, acc_year, tds_type, 0, 0l, null);
            if (result == 0) {
                tdsTranRecordsInserted = true;
            }//add else for exception
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tdsTranRecordsInserted;
    }//end method

    public boolean generateTextFile(ViewClientMast client, String acc_year, int l_quarter_no, Date dateFromDate, Date dateToDate, String tds_type, long l_client_loginid_seq, String user_code, String proc_seq) {
        boolean textFilecreated = false;
        try {
            // New procedure for text file generation
            ProcGenerateTdsTextFile prc = new ProcGenerateTdsTextFile();
            prc.execute_procedure(
                    client.getEntity_code(),
                    client.getClient_code(),
                    acc_year,
                    utl.ChangeAccYearToAssessmentAccYear(acc_year),
                    l_quarter_no, "",
                    dateFromDate,
                    dateToDate,
                    tds_type, "R", 0,
                    l_client_loginid_seq,
                    "", "", "TX", "",
                    user_code, null);

            textFilecreated = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return textFilecreated;
    }//end method

    public boolean generateRtfFile(String fvuFileDirectory, String generateFileLoc) {
        boolean rtfFileCreated = false;
        try {
            String originalRtfFilePath = "";
            File fileLoc = new File(generateFileLoc);
            File fvuFile = new File(fvuFileDirectory);
            if (fvuFile.exists()) {
                File[] fvuFilesList = fvuFile.listFiles();
                for (File file : fvuFilesList) {
                    if (file.getName().endsWith(".rtf")) {
                        originalRtfFilePath = file.getAbsolutePath();
                    }
                }
                if (!utl.isnull(originalRtfFilePath)) {
                    Boolean rtfFilePresent = false;

                    File originalRtfFile = new File(originalRtfFilePath);
                    String originalRtfFileName = originalRtfFile.getName();
                    File[] generatedFileList = fileLoc.listFiles();
                    for (File file : generatedFileList) {
                        if (file.getName().endsWith(".rtf")) {
                            if (originalRtfFileName.equals(file.getName())) {
                                rtfFilePresent = true;
                                rtfFileCreated = true;
                            }
                        }
                    }

                    if (!rtfFilePresent) {
                        String generatedRtfFilePath = generateFileLoc + File.separator + originalRtfFileName;
                        File generatefRtfFile = new File(generatedRtfFilePath);
                        boolean newFileCreated = generatefRtfFile.createNewFile();
                        if (newFileCreated) {
                            FileUtils.copyFileToDirectory(originalRtfFile, fileLoc);//copy rtf file to new generate file location
                            rtfFileCreated = true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtfFileCreated;
    }//end method

    public boolean generateBatchFile(String fvuFileDirectory,
            String generateFileLoc,
            String rtfFileAbsLocation,
            File newGeneratedTextFile,
            File newGeneratedCsiFile,
            File newGeneratedRtfFile,
            File errorFile,
            String fvuVersion, String tokenNo) {

        boolean batchFileCreated = false;
        String absolutePath = newGeneratedTextFile.getAbsolutePath();
        String getAbsolutePath = absolutePath.substring(0, absolutePath.length() - 3) + "fvu";
        File newGeneratedFVUFile = new File(getAbsolutePath);
        try {
            File fileLoc = new File(generateFileLoc);

            File oldfile = new File(fileLoc + File.separator + "runGeneratedFile.bat");
            File newfile = new File(fileLoc + File.separator + "runGeneratedFile.txt");

            if (oldfile.exists()) {
                oldfile.renameTo(newfile);
            }
            String jarName = "TDS_STANDALONE_FVU_" + fvuVersion + ".jar";
            String[] l_split = rtfFileAbsLocation.split(":");
            String text = "cd\\\n"
                    + "" + l_split[0] + ":\n"
                    + "cd " + fvuFileDirectory + "\\ \n"
                    + "java -jar " + jarName + " \"" + newGeneratedTextFile + "\" \"" + errorFile + "\" \"" + newGeneratedFVUFile + "\" \"0\" \"" + fvuVersion + "\" \"0\" \"" + newGeneratedCsiFile + "\" \n"
                    + "exit";
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(newfile);
                fileOutputStream.write(text.getBytes());
                fileOutputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            File oldfile1 = new File(fileLoc + File.separator + "runGeneratedFile.bat");
            File newfile1 = new File(fileLoc + File.separator + "runGeneratedFile.txt");

            if (newfile1.exists()) {
                newfile1.renameTo(oldfile1);
            }
            File generatedfile = new File(fileLoc + File.separator + "runGeneratedFile.bat");
//            closeAllPopups();   --------: DO NOT DELETE THIS LINE
            try {
                if (generatedfile.exists()) {
                    String pathToExecute = "cmd /c start " + fileLoc + File.separator + "runGeneratedFile.bat";
                    Runtime runtime = Runtime.getRuntime();
                    Process p1 = runtime.exec(pathToExecute);
                    InputStream is = p1.getInputStream();

                    ////  1296 TDS_STANDALONE_FVU_4.9.jar Z:\FVU_RELATED_FILES\730\14-15\Q1\26Q\13928812.txt Z:\FVU_RELATED_FILES\730\14-15\Q1\26Q\error.txt Z:\FVU_RELATED_FILES\730\14-15\Q1\26Q\Q126QC.fvu 0 5.0 0 Z:\FVU_RELATED_FILES\730\14-15\Q1\26Q\NGPF00334F220316.csi
                    try {
                        closeCommandWindow(newGeneratedTextFile.toString(), fvuVersion, tokenNo);//when fvu file not  make increrase the time in checkAllFileAreDownload() in this method
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    batchFileCreated = true;

                } else {
                    utl.generateLog(null, "BATCH fILE NOT GENERATED.....");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
        }
        return batchFileCreated;
    }// end method

    public void closeCommandWindow(String textFilePath, String fvuVersion, String tokenNo) {
        utl.generateLog(null, "\n\n");
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ////  1296 TDS_STANDALONE_FVU_4.9.jar Z:\FVU_RELATED_FILES\730\14-15\Q1\26Q\13928812.txt Z:\FVU_RELATED_FILES\730\14-15\Q1\26Q\error.txt Z:\FVU_RELATED_FILES\730\14-15\Q1\26Q\Q126QC.fvu 0 5.0 0 Z:\FVU_RELATED_FILES\730\14-15\Q1\26Q\NGPF00334F220316.csi
        GetUserCodeAndProcessId codeAndProcessId = new GetUserCodeAndProcessId();
        utl.generateLog(null, "getUserCodeAndProcessId.....");
        LinkedHashMap<String, String> filePathAndProcessIdList = codeAndProcessId.getUserCodeAndProcessId(fvuVersion);

        String txtFileName = "";
        String directoryLocation = "";

        utl.generateLog(null, "Update Process ID and Process Flag in DataBase using TextFileName.....");

        for (Map.Entry<String, String> entry : filePathAndProcessIdList.entrySet()) {
            String txtFilePath = entry.getKey();

            if (textFilePath.equalsIgnoreCase(txtFilePath)) {
                //// Z:\FVU_RELATED_FILES\730\14-15\Q1\26Q\13928812.txt

                String pathAndName = codeAndProcessId.getPathAndName(txtFilePath);
                String[] pathAndNameArray = pathAndName.split("~");

                directoryLocation = pathAndNameArray[0];
                txtFileName = pathAndNameArray[1].trim();
                String processId = entry.getValue();
                String processFlag = "P";
                boolean updatePidNo = codeAndProcessId.updatePIDByTokenNo(tokenNo, processId, processFlag);
//                boolean updatePidNo = codeAndProcessId.updateDataByFileName(txtFileName, directoryLocation, processId, processFlag);
                if (updatePidNo) {
                    //System.out.println("Data Update");
                } else {
                    //System.out.println("Data Not Update");
                }
            }

        }

        // Check Is all File are Download
        boolean allFileAreDownload = codeAndProcessId.checkAllFileAreDownload(directoryLocation);
        utl.generateLog(null, "All Files are Download : " + allFileAreDownload);
        if (allFileAreDownload) {
            codeAndProcessId.checkProcessIsRun(filePathAndProcessIdList, textFilePath);
            String processId = "";
            String processFlag = "K";
            boolean updatePidNo = codeAndProcessId.updatePIDByTokenNo(tokenNo, processId, processFlag);
//            boolean updatePidNo = codeAndProcessId.updateDataByFileName(txtFileName, directoryLocation, processId, processFlag);
        }
        utl.generateLog(null, "\n\n");
    }// end method

    public void closeAllPopups() {
        ClosePopupMessagesThread messagesThread = new ClosePopupMessagesThread();
        Thread thread = new Thread(messagesThread);
        thread.start();
    }// end method

    public void createDirectory(String filePath) {
        File fileLoc = null;
        if (!utl.isnull(filePath)) {
            fileLoc = new File(filePath);
            if (!fileLoc.exists()) {
                fileLoc.mkdirs();
            }
        }
    }// end method

    public void deleteFilesInDirectory(String directoryLocation) throws IOException {

        FileDeletePermanent opt = new FileOptUtil().new FileDeletePermanent();
        opt.checkStatus(directoryLocation, "", "D");

    }// end method

    public void updateChallanRecords(String clientCode, String entityCode, String accYear, String quarterNo, String tdsTypeCode, Document doc) {
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            TdsChallanTranLoadDAO tdsChallanTranLoadDAO = factory.getTdsChallanTranLoadDAO();
            TdsChallanTranLoad challanTranLoad = new TdsChallanTranLoad();
            challanTranLoad.setClient_code(clientCode);
            challanTranLoad.setAcc_year(accYear);
            challanTranLoad.setQuarter_no(quarterNo);
            challanTranLoad.setTds_type_code(tdsTypeCode);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
            List<TdsChallanTranLoad> tdsChallanTranLoadList = tdsChallanTranLoadDAO.getFVUFileDetails(challanTranLoad);
            if (tdsChallanTranLoadList != null && !tdsChallanTranLoadList.isEmpty()) {
                for (TdsChallanTranLoad challanTranLoadObj : tdsChallanTranLoadList) {
                    String csi_verify_flag = "U";
                    if (doc != null) {
                        csi_verify_flag = "M";
                        for (Element table : doc.select("table")) {
                            for (Element row : table.select("tr")) {
                                if (table.select("tr").indexOf(row) != 0) {
                                    Elements tds = row.select("td");
                                    String challanDate = tds.get(5).text().trim();
                                    challanDate = sdf1.format(sdf.parse(challanDate));
                                    String challanNo = tds.get(6).text().trim();
                                    String bsrCode = tds.get(7).text().trim();
                                    if (challanTranLoadObj.getTds_challan_no().equalsIgnoreCase(challanNo)
                                            && challanTranLoadObj.getTds_challan_date().trim().equalsIgnoreCase(challanDate)
                                            && challanTranLoadObj.getBank_bsr_code().equalsIgnoreCase(bsrCode)) {
                                        csi_verify_flag = "U";
                                    }
                                }
                            }
                        }
                    }
                    challanTranLoadObj.setCsi_verify_flag(csi_verify_flag);
                    tdsChallanTranLoadDAO = factory.getTdsChallanTranLoadDAO();
                    boolean updateResult = tdsChallanTranLoadDAO.update(challanTranLoadObj);
                }
            }
        } catch (Exception eq) {
            eq.printStackTrace();
        }
    }

    public void calculateAndUpdateFVUTime(String textFilePath) {
//
//        double resutlTime = 0.00;
//        try {
//            ReadFromConsolidatedFile readFromConsolidatedFile = new ReadFromConsolidatedFile();
//            Long lineCount = readFromConsolidatedFile.getLineCount(textFilePath, "ALL");
//            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
//            LhssysParametersDAO lhssysParametersDAO = factory.getLhssysParametersDAO();
//            LhssysParameters readFVURecord = lhssysParametersDAO.readFVURecord();
//            if (readFVURecord != null) {
//                String parameterValue = readFVURecord.getParameter_value();
//                String recordLineCount = "";
//                String recordTimeValue = "";
//
//                if (!utl.isnull(parameterValue)) {
//                    String[] split = parameterValue.split("#");
//                    recordLineCount = split[0];
//                    recordTimeValue = split[1];
//                }
//
//                if (!utl.isnull(recordLineCount)) {
//                    Double recoLineCount = Double.parseDouble(recordLineCount);
//                    Double recoTimeValue = Double.parseDouble(recordTimeValue);
//                    double r = (double) recoTimeValue;
//                    resutlTime = (lineCount * r) / recoLineCount;
//                    if (recoLineCount < lineCount) {
//                        //update
//                        LhssysParameters lp = new LhssysParameters();
//                        lp.setParameter_name("FVU_FILE_PROCESS_TIME");
//                        lp.setParameter_value(lineCount + "#" + resutlTime);
//                        lhssysParametersDAO = factory.getLhssysParametersDAO();
//                        boolean update = lhssysParametersDAO.update(lp);
//                    }
//                }
//            }
//        } catch (Exception e) {
//        }
    }// end method

    public String getMaxMinDate(String client_code, String acc_year, String tds_type_code, String quarter_no, String maxOrMin, Util utl) {
        String returnDate = "";
        try {
            String sqlQuery
                    = "select to_char(max(to_date(t.tds_challan_date, 'dd-mon-rrrr')),'dd-mm-rrrr' )\n"
                    + " from tds_challan_tran_load  t where t.client_code='" + client_code + "'\n"
                    + "and t.acc_year='" + acc_year + "'\n"
                    + "and t.quarter_no='" + quarter_no + "'\n"
                    + "and t.tds_type_code='" + tds_type_code + "'";
//            System.out.println("query---" + sqlQuery);
            returnDate = new DbFunctionExecutorAsString().execute_oracle_function_as_string(sqlQuery);
            if (!utl.isnull(returnDate)) {
                if (!utl.isnull(maxOrMin) && maxOrMin.equalsIgnoreCase("min")) { // if min subtract 23 months from max date
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    Date parseDate = sdf.parse(returnDate);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(parseDate);
                    calendar.add(Calendar.MONTH, -23);
                    returnDate = sdf.format(calendar.getTime());
                }
            }
        } catch (Exception e) {
            returnDate = null;
        }
        return returnDate;
    }// end method

    public boolean zipFiles(String generateFileLoc, String zipFileLocation, String textFileName, String fileType) {
        boolean result = true;
        try {
            List<String> filesPathToZip = new ArrayList<String>();
//            List<String> fileName = new ArrayList<String>();
//            if (fileType.equalsIgnoreCase("FVU")) {
//                fileName.add(".fvu");
//                fileName.add("Electronic_Statement_Warning_File.html");
//                fileName.add(".pdf");
//                fileName.add(textFileName);
//            } else {// if error
////                fileName.add("error.txt");
//                fileName.add("err.html");
//            }

            File genFileDir = new File(generateFileLoc);
            File[] listFiles = genFileDir.listFiles();
            if (listFiles.length > 0) {
                for (File file : listFiles) {
//                    if (fileName.size() > 0) {
//                        for (String fileExt : fileName) {
//                            if (file.getName().endsWith(fileExt)) {
                    // do something
                    filesPathToZip.add(file.getAbsolutePath());
//                                fileName.remove(fileExt);
//                                break;
//                            }
//                        }
//                    }
                }
            }
            if (filesPathToZip.size() > 0) {
                MultipleFileZip zipfile = new ZipFileUtil().new MultipleFileZip();
                zipfile.zipListOfFiles(filesPathToZip, zipFileLocation);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }// end method

    public Date getPreviousQuarterDates(String accYear, int qtr, String FromToDate) {
        Date returnDate = null;
        try {
            if (!utl.isnull(accYear) && qtr > 0 && !utl.isnull(FromToDate)) {
                String[] splitVal = accYear.split("\\-");
                String fromDate = "";
                String toDate = "";
                switch (qtr) {
                    case 1:
                        fromDate = "01-04-20" + splitVal[0];
                        toDate = "30-06-20" + splitVal[0];
                        break;
                    case 2:
                        fromDate = "01-07-20" + splitVal[0];
                        toDate = "30-09-20" + splitVal[0];
                        break;
                    case 3:
                        fromDate = "01-10-20" + splitVal[0];
                        toDate = "31-12-20" + splitVal[0];
                        break;
                    case 4:
                        fromDate = "01-01-20" + splitVal[1];
                        toDate = "31-03-20" + splitVal[1];
                        break;
                    default:
                        break;
                }
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                if (FromToDate.equals("from")) {
                    returnDate = sdf.parse(fromDate);
                } else {
                    returnDate = sdf.parse(toDate);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnDate;
    }

    public String getCsiFileName(String tanno) {
        String csiName = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("ddMMyy");
            String csiDateFormatString = format.format(new Date());
            csiName = tanno + csiDateFormatString + ".csi";
        } catch (Exception e) {
        }
        return csiName;
    }// end method

    private final Util utl;

    public GenerateFVUFileSupport() {
        utl = new Util();
    }

}
