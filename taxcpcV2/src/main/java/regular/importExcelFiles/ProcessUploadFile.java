/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.importExcelFiles;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.DbProc.ProcLhsFuvTxtInsert;
import dao.LhssysParametersDAO;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import globalUtilities.VBRunUtil;
import hibernateObjects.ViewClientMast;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author abhijeet.joshi
 */
public class ProcessUploadFile {

    private Util utl;

    public ProcessUploadFile() {
        utl = new Util();
    }

    public int saveImportMastUploadedTemplate(String engineType, String processType, ViewClientMast viewClientMast, Assessment assesment, String filePath, final String templeteCode,
            String javaDriveName, String oracleDriveName, final Long importSeqNo, final String userCode, final Long tokenNo, Long processSeqNo, final LhssysParametersDAO lhssysParameters, String CLOB_LOG_FLAGValue) throws IOException, ParseException {
        int result = 0;
        final String accYear = assesment.getAccYear();
        final String quarterNo = assesment.getQuarterNo();
        final String tdsTypeCode = assesment.getTdsTypeCode();
        final Date fromDate = assesment.getQuarterFromDate();
        final Date toDate = assesment.getQuarterToDate();
        final String entityCode = viewClientMast.getEntity_code();
        final String clientCode = viewClientMast.getClient_code();
        try {
            File inputFileXls = new File(filePath);//file path
            if (!utl.isnull(engineType.trim()) && engineType.equalsIgnoreCase("EXCEL")) {
                String xls_path = inputFileXls.getAbsolutePath();//excel path
                String csvFolderPath = javaDriveName + File.separator + "csv";//csv folder
                String csvFileName = viewClientMast.getEntity_code() + "_" + viewClientMast.getClient_code() + "_" + accYear.substring(0, 2) + quarterNo + templeteCode + "_" + importSeqNo;
                String csvPath = csvFolderPath + File.separator + csvFileName;// csv file path
                String fullcsvPathName = csvPath + ".csv";
                VBRunUtil vbrUtl = new VBRunUtil();
                if (!templeteCode.startsWith("24Q")) {
                    vbrUtl.xlsTocsv(xls_path, csvPath, javaDriveName);
                } else {
                     utl.generateLog("Use Salary Template", templeteCode);
                    vbrUtl.xlsTocsvSalaryTempalte(xls_path, csvPath, javaDriveName);
                }

                File outputFileCsv = new File(fullcsvPathName);

                boolean csvExists = false;
                for (int i = 0; i < 1000; i++) {
                    try {
                        if (outputFileCsv.exists()) {
                            csvExists = true;
                            break;
                        } else {
                            Thread.sleep(2000);//delay time
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (csvExists) {
                    if (CLOB_LOG_FLAGValue.equalsIgnoreCase("T")) {
                        utl.generateLog("Import Feature as CLOB", "");
                        DbFunctionExecutorAsString dbFunctionExecutorAsString = new DbFunctionExecutorAsString();
                        dbFunctionExecutorAsString.save_file_clob(importSeqNo, fullcsvPathName);
                    } else {
                        utl.generateLog("Import Feature as Copy to Oracle Drive", "");
                        //csb directory
                        // String copyfororaclepath =  File.separator + "oradata02" +File.separator+"taxcpc_dir" +File.separator + "LHS_FVU_TEXT_IMPORT";
                        String copyfororaclepath = oracleDriveName + File.separator + "FVU_TEXT_IMPORT";
                        utl.generateLog("copied uploaded file to database drive---", copyfororaclepath);
                        File copyforOracle = new File(copyfororaclepath);
                        try {
                            File fileLoc = new File(csvFolderPath);
                            if (!fileLoc.exists()) {
                                fileLoc.mkdirs();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        FileUtils.copyFileToDirectory(outputFileCsv, copyforOracle);
                        utl.generateLog("File successfully copied to database drive...", "");
                    }
                    result = 1;
                }

            } else if (!utl.isnull(engineType.trim()) && engineType.equalsIgnoreCase("FUVTXT")) {
                ProcLhsFuvTxtInsert proc_fuvtx = new ProcLhsFuvTxtInsert();
                String assesment_acc_year = utl.ChangeAccYearToAssessmentAccYear(accYear);
                String result1 = proc_fuvtx.execute_procedure(entityCode, clientCode, accYear, assesment_acc_year, Integer.valueOf(quarterNo), fromDate, toDate, tdsTypeCode,
                        importSeqNo, processType, templeteCode, inputFileXls.getName(), userCode, processSeqNo);
                result = 1;
            }
        } catch (Exception e) {
            result = 0;
            e.printStackTrace();
        }
        return result;
    }

}//End Class
