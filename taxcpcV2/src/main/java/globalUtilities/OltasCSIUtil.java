/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package globalUtilities;

import com.lhs.taxcpcv2.bean.Assessment;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import regular.generateFVU.GenerateFVUFileSupport;

/**
 *
 * @author gaurav.khanzode
 */
public class OltasCSIUtil {

    Util utl;

    public OltasCSIUtil() {
        utl = new Util();
    }
    GenerateFVUFileSupport fVUFileSupport = new GenerateFVUFileSupport();

    /**
     * A global method which returns a common CSI file download URL with the
     * specified parameters.
     *
     * @param tanno
     * @param csiClientCode
     * @param accYear
     * @param tdsTypeCode
     * @param quarterNo
     * @return
     */
    public String getCsiDownloadUrl(String tanno, String csiClientCode, String accYear, String tdsTypeCode, String quarterNo) {

        String csiDownloadUrl = "";
        try {
            String minDate = fVUFileSupport.getMaxMinDate(csiClientCode, accYear, tdsTypeCode, quarterNo, "min", utl);
            String maxDate = fVUFileSupport.getMaxMinDate(csiClientCode, accYear, tdsTypeCode, quarterNo, "max", utl);

            if (!utl.isnull(minDate) && !utl.isnull(maxDate)) {
                String[] split_from_date = minDate.split("-");
                String[] split_to_date = maxDate.split("-");

                csiDownloadUrl = "https://tin.tin.nsdl.com/oltas/servlet/TanSearch?request=true&encode=false&TAN_NO=" + tanno
                        + "&TAN_FROM_DT_DD=" + split_from_date[0] + "&TAN_FROM_DT_MM=" + split_from_date[1] + "&TAN_FROM_DT_YY=" + split_from_date[2]
                        + "&TAN_TO_DT_DD=" + split_to_date[0] + "&TAN_TO_DT_MM=" + split_to_date[1] + "&TAN_TO_DT_YY=" + split_to_date[2]
                        + "&HIDDEN_TAN_FROM_DT_DD=" + split_from_date[0] + "&HIDDEN_TAN_FROM_DT_MM=" + split_from_date[1]
                        + "&HIDDEN_TAN_TO_DT_DD=" + split_to_date[0] + "&HIDDEN_TAN_TO_DT_MM=" + split_to_date[1]
                        + "&NoCapScr=InvalidCapScr&HIDDEN_TAN_TO_DT_YY=&submit=Download+Challan+file&appUser=T";

                utl.generateLog("CSI URL for:" + csiClientCode, csiDownloadUrl);
            }
        } catch (Exception e) {
            System.out.println("CSI Download URL Error: " + e.getMessage());
        }

        return csiDownloadUrl;
    }//end

    /**
     * A global method which returns a common CSI file download URL with the
     * specified parameters.
     *
     * @param tanno
     * @param csiClientCode
     * @param asmt
     * @return
     */
    public String getCsiDownloadUrl(String tanno, String csiClientCode, Assessment asmt) {

        String csiDownloadUrl = "";
        try {
            String accYear = asmt.getAccYear(), tdsTypeCode = asmt.getTdsTypeCode(), quarterNo = asmt.getQuarterNo();

            String minDate = fVUFileSupport.getMaxMinDate(csiClientCode, accYear, tdsTypeCode, quarterNo, "min", utl);
            String maxDate = fVUFileSupport.getMaxMinDate(csiClientCode, accYear, tdsTypeCode, quarterNo, "max", utl);

            if (utl.isnull(minDate) && utl.isnull(maxDate)) {
                LocalDate minusDate = LocalDate.now().minusYears(2);
                String formattedDate = minusDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                minDate = maxDate = formattedDate;
                System.out.println("Min Max Date: " + minDate + ", " + maxDate);
            }
            String[] split_from_date = minDate.split("-");
            String[] split_to_date = maxDate.split("-");

            csiDownloadUrl = "https://tin.tin.nsdl.com/oltas/servlet/TanSearch?request=true&encode=false&TAN_NO=" + tanno
                    + "&TAN_FROM_DT_DD=" + split_from_date[0] + "&TAN_FROM_DT_MM=" + split_from_date[1] + "&TAN_FROM_DT_YY=" + split_from_date[2]
                    + "&TAN_TO_DT_DD=" + split_to_date[0] + "&TAN_TO_DT_MM=" + split_to_date[1] + "&TAN_TO_DT_YY=" + split_to_date[2]
                    + "&HIDDEN_TAN_FROM_DT_DD=" + split_from_date[0] + "&HIDDEN_TAN_FROM_DT_MM=" + split_from_date[1]
                    + "&HIDDEN_TAN_TO_DT_DD=" + split_to_date[0] + "&HIDDEN_TAN_TO_DT_MM=" + split_to_date[1]
                    + "&NoCapScr=InvalidCapScr&HIDDEN_TAN_TO_DT_YY=&submit=Download+Challan+file&appUser=T";

            utl.generateLog("URL--- ", csiDownloadUrl);
        } catch (Exception e) {
            System.out.println("CSI Download URL Error: " + e.getMessage());
            e.printStackTrace();
        }

//        System.out.println("csiDownloadUrl: " + csiDownloadUrl);
        return csiDownloadUrl;
    }//end

    /**
     * A method for generating excel file for CSI download information for the
     * clients.
     *
     * @param clientCode
     * @param entityCode
     * @param accYear
     * @param quarterNo
     * @param tdsTypeCode
     * @param javaDriveName
     * @return
     */
//    public boolean generateExcelForCsiDownload(String clientCode, String entityCode, String accYear, String quarterNo,
//            String tdsTypeCode, String javaDriveName) {
//
//        boolean generateFlag = false;
//        try {
//            String finalPath = "";
//            String excelPath = "";
//            String filename = "";
//            String tdsTypeCodeLoc = "";
//
//            NilReturnDB nilReturnDB = new NilReturnDB();
//            String l_query = nilReturnDB.generateBulkNilReturnSql(clientCode, entityCode);
////            System.out.println("Oltas util query==" + l_query);
//
//            DbGenericFunctionExecutor db = new DbGenericFunctionExecutor();
//            ArrayList<ClientEntityBean> clientDtl = db.getGenericList(new ClientEntityBean(), l_query);
//
//            if (clientDtl != null && clientDtl.size() > 0) {
//
//                String rootLoc = javaDriveName + File.separator + "BULK_NIL_RETURN";
//                String clientCodeLoc = rootLoc + File.separator + clientCode;
//                String accYearLoc = clientCodeLoc + File.separator + accYear;
//                String qtrYrLoc = accYearLoc + File.separator + "Q" + quarterNo;
//                tdsTypeCodeLoc = qtrYrLoc + File.separator + tdsTypeCode;
//
//                finalPath = tdsTypeCodeLoc;
//
//                excelPath = finalPath + File.separator + "CONFIG_FILE";
//
//                File dirct = new File(finalPath);
//                if (dirct.exists()) {
//                    try {
////                        utl.generateLog("Oltas util finalPath==", finalPath);
//
//                        GenerateFVUFileSupport ob = new GenerateFVUFileSupport();
//                        ob.deleteFilesInDirectory(finalPath);
//                    } catch (IOException e) {
//                    }
//
//                }
//                utl.createDirectory(rootLoc);
//                utl.createDirectory(clientCodeLoc);
//                utl.createDirectory(accYearLoc);
//
//                utl.createDirectory(qtrYrLoc);
//                utl.createDirectory(tdsTypeCodeLoc);
//                utl.createDirectory(finalPath);
//                utl.createDirectory(excelPath);
//
//                filename = clientCode.concat("_").concat(accYear).concat("_").concat(quarterNo).concat("_").concat(tdsTypeCode);
//
//                ArrayList<List<String>> arrData = new ArrayList<>();
//
//                for (ClientEntityBean clientEntityBean : clientDtl) {
//                    List<String> arr = new LinkedList<>();
////                    boolean checkMendatoryField = getCheckMendatoryField(clientEntityBean, utl);
////
////                    if (checkMendatoryField) {
//
//                    String l_branch_finalpath = finalPath + File.separator + clientEntityBean.getClient_code() + File.separator;
//                    utl.createDirectory(l_branch_finalpath);
//                    String beanClientCode = clientEntityBean.getClient_code();
//                    String tanNumber = clientEntityBean.getTanno();
//                    String branchFileDirectory = l_branch_finalpath;
////                    System.out.println("Downloading CSI....");
//                    String csiUrl = this.getCsiDownloadUrl(tanNumber, beanClientCode, accYear, tdsTypeCode, quarterNo);
////                    System.out.println("Downloading URL...." + val4);
//                    if (!utl.isnull(csiUrl)) {
//                        arr.add(beanClientCode);
//                        arr.add(tanNumber);
//                        arr.add(branchFileDirectory);
//                        arr.add(csiUrl);
//
//                        arrData.add(arr);
//                    }
//
////                    }
//                }
//                System.out.println("Oltas CSI size...." + arrData.size());
//
//                ArrayList<String> headingList = new ArrayList<>();
//                headingList.add("Client code");
//                headingList.add("Tan no.");
//                headingList.add("Path");
//                headingList.add("Csi Url");
//
//                generateFlag = generateErrorFile(headingList, arrData, filename, excelPath);
//                System.out.println("Final Path===" + finalPath);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return generateFlag;
//    }//end
//    private boolean generateErrorFile(ArrayList<String> headingArray, ArrayList<List<String>> arrData, String filename, String fileLocation) throws IOException {
//
//        boolean result = true;
//        String file_ext = ".xls";
//        String l_return_filepath = RenameAndSaveUploadedFile(fileLocation, filename, file_ext);
//
//        try {
//            WriteExcelFileUtil ta = new WriteExcelFileUtil(utl);
//            ta.writeFile(new File(l_return_filepath), arrData, headingArray, file_ext);
//        } catch (IOException ex) {
//            result = false;
//            ex.printStackTrace();
//        }
//
//        return result;
//    }//end method
//
//    private String RenameAndSaveUploadedFile(String fileLocation, String fileName, String file_ext) {
//        String filePath;
//        try {
//            File destFile = new File(fileLocation, fileName + file_ext);
//            destFile.createNewFile();
//            filePath = destFile.getAbsolutePath();
//        } catch (IOException e) {
//            e.printStackTrace();
//            filePath = null;
//        }
//        return filePath;
//    }//end method
}//end
