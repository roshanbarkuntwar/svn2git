/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.transaction;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.TempDataDAO;
import dao.generic.DAOFactory;
import globalUtilities.ExcelUtil;
import globalUtilities.Util;
import hibernateObjects.TempData;
import hibernateObjects.ViewClientMast;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author aniket.naik
 */
public class ProcessUploadFileReferenceNo implements SessionAware {

    private final Util utl;
    Map<String, Object> session;
    ExcelUtil excelUtl;

    public ProcessUploadFileReferenceNo(Util util) {
        this.utl = util;
        this.excelUtl = new ExcelUtil();
    }//end constructor

    int saveImportMastUploadedTemplate(String filePath, String readLineNo, ViewClientMast viewClientMast, Assessment asmt, String templateFileName, String TempleteCode, int l_strt_col, int l_end_col, String l_valide_file_code, String accYear, String f_quarter_no, Date from_date, Date to_date, String tds_type_code, Map<String, Object> session, String GenClientCode, ArrayList<String> bankBranchCodeList) throws IOException, ParseException {
        int resultdata = 0;
        try {
            if (viewClientMast != null) {
                DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);

                Long l_client_loginid_seq;
                Object sessionId = session.get("LOGINSESSIONID");
                try {
                    l_client_loginid_seq = (Long) sessionId;
                } catch (Exception e) {
                    l_client_loginid_seq = 0l;
                }

                SimpleDateFormat sdfnew = new SimpleDateFormat("dd-MMM-yyyy");
                //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                FileInputStream inputStream = null;
                try {
                    inputStream = new FileInputStream(new File(filePath));
                    Workbook excelworkbook = excelUtl.getExcelPoiWorkbook(inputStream, filePath);
                    //FormulaEvaluator evaluator = excelworkbook.getCreationHelper().createFormulaEvaluator();
                    if (excelworkbook != null) {
                        String l_entity_code = "";

                        try {
                            l_entity_code = viewClientMast.getEntity_code();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        String loginSeqno = "0";
                        try {
                            loginSeqno = String.valueOf(l_client_loginid_seq);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        String assesment_acc_year = accYear;
                        try {
                            String[] acc_year_split = accYear.split("-");
                            String acc_year_p1 = acc_year_split[0];
                            int newaccyear_p1 = Integer.valueOf(acc_year_p1) + 1;
                            String acc_year_p2 = acc_year_split[1];
                            int newaccyear_p2 = Integer.valueOf(acc_year_p2) + 1;
                            assesment_acc_year = newaccyear_p1 + "-" + newaccyear_p2;
                        } catch (Exception e) {
                        }

                        int rowcounter = 0;
                        int savedcounter = 0;
                        Form15GHDB form15HDB = new Form15GHDB();

                        try {
                            String query = form15HDB.getTempDataQuery(l_entity_code, accYear, f_quarter_no, tds_type_code, TempleteCode, GenClientCode);
                            
                            GetMasterExcelDataListThroughWorkbook objdataworkbook = new GetMasterExcelDataListThroughWorkbook();
                            boolean delResult = objdataworkbook.getTempDetailDelete(query, utl);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //delete temp data redord end
                        TempDataDAO tampdatadao = factory.getTempDataDAO();
                        Session dbssn = tampdatadao.getHibernateSession();
                        Sheet sheet = excelworkbook.getSheetAt(0);// Get the first sheet
                        int lastRowNum = sheet.getPhysicalNumberOfRows();
                        //System.out.println("lastRowNum..." + lastRowNum);
                        int begain = l_strt_col;
                        int end = l_end_col;
                        // System.out.println("begain.." + begain + "end..." + end);
                        Transaction tx = dbssn.beginTransaction(); // added for session problem
                        boolean CheckRefnoFlag = true;
                        try {
                            outer:
                            for (int i = begain; i < (lastRowNum); i++) {
                                //System.out.println("i value...." + i);
                                TempData odjrempdata = new TempData();//ADD FOR REMOVING DATA REPETATION
                                Row row = sheet.getRow(i);
                                odjrempdata.setCol1(l_entity_code);//entity code
                                //odjrempdata.setCol2(l_client_code);//l_client_code 
                                odjrempdata.setCol3(accYear);//acc year
                                odjrempdata.setCol4(assesment_acc_year);//assessment year
                                odjrempdata.setCol5(f_quarter_no);//quarter no
                                odjrempdata.setCol6((sdfnew.format(from_date)).toUpperCase());//from date
                                odjrempdata.setCol7((sdfnew.format(to_date)).toUpperCase());//to date
                                odjrempdata.setCol8(tds_type_code);//tds type
                                odjrempdata.setCol9(loginSeqno);//session id
                                try {
                                    odjrempdata.setCol10(Long.valueOf(i + 1));//rownum chaqnges the datadtype as long on 10-09-2018
                                } catch (Exception e) {
                                }
                                //System.out.println("Row Number(i)...." + i);
                                int colCount = 1;
                                int checkCellNull = 0;
                                int bgVal = (begain - 1);
                                for (int q = bgVal; q < end; q++) {
                                    colCount++;
                                    String columnName = "setCol" + colCount;

                                    Cell cell = null;
                                    try {
                                        cell = row.getCell(q);
                                    } catch (Exception e) {
                                    }
                                    if (cell != null) {
                                        Method[] method = odjrempdata.getClass().getMethods();
                                        for (Method method1 : method) {
                                            String name = method1.getName();
                                            //System.out.println("method1.getName()..." + method1.getName());
//                                                System.out.println("columnName val..." + columnName);
                                            if (!utl.isnull(name) && name.equals("setCol2")) {
                                                if (colCount == 2) {
                                                    colCount = colCount + 8;
                                                    try {
                                                        if (cell != null) {
                                                            if (cell.getCellType() != cell.CELL_TYPE_BLANK) {
                                                                checkCellNull++;
                                                                switch (cell.getCellType()) {
                                                                    case Cell.CELL_TYPE_STRING:// used For String Type Cell Value
                                                                        try {
                                                                            method1.invoke(odjrempdata, (cell.getStringCellValue()));
                                                                        } catch (Exception e) {
                                                                            method1.invoke(odjrempdata, cell.getStringCellValue());
                                                                        }
                                                                        break;
                                                                    case Cell.CELL_TYPE_FORMULA:// used For Formula Type Cell Value

                                                                        try {
                                                                            //method1.invoke(odjrempdata, evaluator.evaluateInCell(cell).toString());
                                                                        } catch (Exception e) {
                                                                            //method1.invoke(odjrempdata, evaluator.evaluateInCell(cell).toString());
                                                                        }
                                                                        break;
                                                                    case Cell.CELL_TYPE_NUMERIC:// used For Numeric Type Cell Value
                                                                        if (DateUtil.isCellDateFormatted(cell)) {
                                                                            try {
                                                                                method1.invoke(odjrempdata, cell.getDateCellValue().toString());
                                                                            } catch (Exception e) {
                                                                                method1.invoke(odjrempdata, cell.getDateCellValue().toString());
                                                                            }
                                                                        } else {
                                                                            String str = NumberToTextConverter.toText(cell.getNumericCellValue());
                                                                            method1.invoke(odjrempdata, str);
                                                                        }
                                                                        break;
                                                                    case Cell.CELL_TYPE_BLANK:// used For Blanks or Null Type Cell Value
                                                                        break;
                                                                    case Cell.CELL_TYPE_BOOLEAN:// used For Boolean Type Cell Value                                                                   
                                                                        try {
                                                                            method1.invoke(odjrempdata, Boolean.toString(cell.getBooleanCellValue()));
                                                                        } catch (Exception e) {
                                                                            method1.invoke(odjrempdata, Boolean.toString(cell.getBooleanCellValue()));
                                                                        }
                                                                        break;
                                                                }//end switch
                                                            }
                                                        }
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            } else if (name.equalsIgnoreCase(columnName) && !name.equalsIgnoreCase("setCol2")) {

                                                if ((colCount - 10) < end) {
                                                    try {
                                                        if (cell != null) {
                                                            if (cell.getCellType() != cell.CELL_TYPE_BLANK) {
                                                                checkCellNull++;
                                                                switch (cell.getCellType()) {
                                                                    case Cell.CELL_TYPE_STRING:// used For String Type Cell Value
                                                                        try {
                                                                            if (name.equalsIgnoreCase("setCol14") || name.equalsIgnoreCase("setCol15") || name.equalsIgnoreCase("setCol16") || name.equalsIgnoreCase("setCol17")) {
                                                                                if (utl.isValidRefNo(cell.getStringCellValue())) {
                                                                                    method1.invoke(odjrempdata, (cell.getStringCellValue()));
                                                                                } else {
                                                                                    resultdata = 0;
                                                                                    CheckRefnoFlag = false;
                                                                                    break outer;
                                                                                }
                                                                            } else {
                                                                                method1.invoke(odjrempdata, (cell.getStringCellValue()));
                                                                            }
                                                                        } catch (Exception e) {
                                                                            method1.invoke(odjrempdata, cell.getStringCellValue());
                                                                        }
                                                                        break;
                                                                    case Cell.CELL_TYPE_FORMULA:// used For Formula Type Cell Value

                                                                        try {
                                                                            //method1.invoke(odjrempdata, evaluator.evaluateInCell(cell).toString());
                                                                        } catch (Exception e) {
                                                                            //method1.invoke(odjrempdata, evaluator.evaluateInCell(cell).toString());
                                                                        }
                                                                        break;
                                                                    case Cell.CELL_TYPE_NUMERIC:// used For Numeric Type Cell Value
                                                                        if (DateUtil.isCellDateFormatted(cell)) {
                                                                            try {
                                                                                if (name.equalsIgnoreCase("setCol14") || name.equalsIgnoreCase("setCol15") || name.equalsIgnoreCase("setCol16") || name.equalsIgnoreCase("setCol17")) {
                                                                                    if (utl.isValidRefNo(cell.getDateCellValue().toString())) {
                                                                                        method1.invoke(odjrempdata, cell.getDateCellValue().toString());
                                                                                    } else {
                                                                                        resultdata = 0;
                                                                                        CheckRefnoFlag = false;
                                                                                        break outer;
                                                                                    }
                                                                                } else {
                                                                                    method1.invoke(odjrempdata, cell.getDateCellValue().toString());
                                                                                }
                                                                            } catch (Exception e) {
                                                                                method1.invoke(odjrempdata, cell.getDateCellValue().toString());
                                                                            }
                                                                        } else {
                                                                            String str = NumberToTextConverter.toText(cell.getNumericCellValue());
                                                                            if (name.equalsIgnoreCase("setCol14") || name.equalsIgnoreCase("setCol15") || name.equalsIgnoreCase("setCol16") || name.equalsIgnoreCase("setCol17")) {
                                                                                if (utl.isValidRefNo(str)) {
                                                                                    method1.invoke(odjrempdata, str);
                                                                                } else {
                                                                                    resultdata = 0;
                                                                                    CheckRefnoFlag = false;
                                                                                    break outer;
                                                                                }
                                                                            } else {
                                                                                method1.invoke(odjrempdata, str);
                                                                            }
                                                                        }
                                                                        break;
                                                                    case Cell.CELL_TYPE_BLANK:// used For Blanks or Null Type Cell Value
                                                                        break;
                                                                    case Cell.CELL_TYPE_BOOLEAN:// used For Boolean Type Cell Value                                                                   
                                                                        try {
                                                                            if (name.equalsIgnoreCase("setCol14") || name.equalsIgnoreCase("setCol15") || name.equalsIgnoreCase("setCol16") || name.equalsIgnoreCase("setCol17")) {
                                                                                if (utl.isValidRefNo(Boolean.toString(cell.getBooleanCellValue()))) {
                                                                                    method1.invoke(odjrempdata, Boolean.toString(cell.getBooleanCellValue()));
                                                                                } else {
                                                                                    resultdata = 0;
                                                                                    CheckRefnoFlag = false;
                                                                                    break outer;
                                                                                }
                                                                            } else {
                                                                                method1.invoke(odjrempdata, Boolean.toString(cell.getBooleanCellValue()));
                                                                            }
                                                                        } catch (Exception e) {
                                                                            method1.invoke(odjrempdata, Boolean.toString(cell.getBooleanCellValue()));
                                                                        }
                                                                        break;
                                                                }//end switch
                                                            }
                                                        }
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                }

                                                odjrempdata.setCol100(TempleteCode);//templete code
                                            }
                                        }//End For
                                    }//end cell null check if
                                }
                                Serializable save;
                                //System.out.println("checkCellNull..." + checkCellNull);
                                if (checkCellNull > 0) {

                                    boolean insertData = bankBranchCodeList.contains(odjrempdata.getCol11());

                                    if (insertData) {
                                        save = dbssn.save(odjrempdata);
                                        if (save != null) {
                                            savedcounter += 1;
                                        }
                                        if (rowcounter % 25 == 0) {//saving in 25 rows' batch
                                            dbssn.flush();
                                            dbssn.clear();
                                        }
                                    }
                                }
                            }//end for loop(iterate rows)
                            if (savedcounter > 0) {
                                if (CheckRefnoFlag) {
                                    resultdata = savedcounter;
                                    try {
                                        tx.commit(); // added for session problem
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                resultdata = 0;
                                tx.rollback();// added for session problem
                            }

                        } catch (Exception e) {
                            if (tx != null) {
                                tx.rollback();// added for session problem
                            }
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    resultdata = 0;
                    e.printStackTrace();
                }
            }//end client null check if
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultdata;
    }//end method

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

}
