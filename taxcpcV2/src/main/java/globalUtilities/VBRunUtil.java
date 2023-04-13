/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package globalUtilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author akash.dev
 */
public class VBRunUtil {

    private globalUtilities.DateTimeUtil dtl;
    private globalUtilities.Util utl;

    public VBRunUtil() {
        dtl = new globalUtilities.DateTimeUtil();
        utl = new globalUtilities.Util();
    }

    public void xlsTocsv(String inputFile, String outputFile, String javaDriveName) {
        try {
            Util utl = new Util();
            String l_tm_stam = dtl.get_sysdate("DD_dd_MM_yy_hh_mm_ss_SSS").toUpperCase();
            String l_vbs_file_name = javaDriveName + "/vbs/XLSTOCSV" + l_tm_stam + ".vbs";
            File file = new File(l_vbs_file_name);
            file.getParentFile().mkdirs();
            String para0 = "\"" + inputFile + "\"";
            String para1 = "\"" + outputFile + ".csv\"";
            String para4 = "\"" + outputFile + "_temp.csv" + "\"";
            if (!file.exists()) {
                file.createNewFile();//file not exists create it
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);//use buffered writer for multiline and multichar
            bw.write("Set FSO=CreateObject(\"Scripting.FileSystemObject\")");
            bw.newLine();
            bw.write("if fso.FileExists(" + para1 + ") THEN ");
            bw.newLine();
            bw.write("fso.DeleteFile" + para1);
            bw.newLine();
            bw.write("end if");
            bw.newLine();
            bw.write("Dim oExcel");
            bw.newLine();
            bw.write("Set oExcel = CreateObject(\"Excel.Application\")");
            bw.newLine();
            bw.write("oExcel.DisplayAlerts = FALSE 'to avoid prompts");
            bw.newLine();
            bw.write("Dim oBook, local");
            bw.newLine();
            bw.write("Set oBook = oExcel.Workbooks.Open(" + para0 + ")");
            bw.newLine();
//         START-->ADD COLUMN VALIDATION UPTO 34 RAnge A TO AH THIS REDUCE THE SIZE ON 23-11-2021.
            bw.write("Set wsSource = oBook.Sheets(1)");
            bw.newLine();
            bw.write("Set wsTarget = oBook.Sheets.Add()");
            bw.newLine();
            bw.write("Const xlUp = -4162");
            bw.newLine();
            bw.write("With wsSource");
            bw.newLine();
            bw.write(".Range(\"A1\", .Range(\"A\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"A1\")");
            bw.newLine();
            bw.write(".Range(\"B1\", .Range(\"B\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"B1\")");
            bw.newLine();
            bw.write(".Range(\"C1\", .Range(\"C\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"C1\")");
            bw.newLine();
            bw.write(".Range(\"D1\", .Range(\"D\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"D1\")");
            bw.newLine();
            bw.write(".Range(\"E1\", .Range(\"E\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"E1\")");
            bw.newLine();
            bw.write(".Range(\"F1\", .Range(\"F\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"F1\")");
            bw.newLine();
            bw.write(".Range(\"G1\", .Range(\"G\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"G1\")");
            bw.newLine();
            bw.write(".Range(\"H1\", .Range(\"H\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"H1\")");
            bw.newLine();
            bw.write(".Range(\"I1\", .Range(\"I\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"I1\")");
            bw.newLine();
            bw.write(".Range(\"J1\", .Range(\"J\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"J1\")");
            bw.newLine();
            bw.write(".Range(\"K1\", .Range(\"K\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"K1\")");
            bw.newLine();
            bw.write(".Range(\"L1\", .Range(\"L\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"L1\")");
            bw.newLine();
            bw.write(".Range(\"M1\", .Range(\"M\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"M1\")");
            bw.newLine();
            bw.write(".Range(\"N1\", .Range(\"N\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"N1\")");
            bw.newLine();
            bw.write(".Range(\"O1\", .Range(\"O\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"O1\")");
            bw.newLine();
            bw.write(".Range(\"P1\", .Range(\"P\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"P1\")");
            bw.newLine();
            bw.write(".Range(\"Q1\", .Range(\"Q\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"Q1\")");
            bw.newLine();
            bw.write(".Range(\"R1\", .Range(\"R\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"R1\")");
            bw.newLine();
            bw.write(".Range(\"S1\", .Range(\"S\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"S1\")");
            bw.newLine();
            bw.write(".Range(\"T1\", .Range(\"T\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"T1\")");
            bw.newLine();
            bw.write(".Range(\"U1\", .Range(\"U\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"U1\")");
            bw.newLine();
            bw.write(".Range(\"V1\", .Range(\"V\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"V1\")");
            bw.newLine();
            bw.write(".Range(\"W1\", .Range(\"W\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"W1\")");
            bw.newLine();
            bw.write(".Range(\"X1\", .Range(\"X\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"X1\")");
            bw.newLine();
            bw.write(".Range(\"Y1\", .Range(\"Y\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"Y1\")");
            bw.newLine();
            bw.write(".Range(\"Z1\", .Range(\"Z\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"Z1\")");
            bw.newLine();
            bw.write(".Range(\"AA1\", .Range(\"AA\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AA1\")");
            bw.newLine();
            bw.write(".Range(\"AB1\", .Range(\"AB\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AB1\")");
            bw.newLine();
            bw.write(".Range(\"AC1\", .Range(\"AC\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AC1\")");
            bw.newLine();
            bw.write(".Range(\"AD1\", .Range(\"AD\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AD1\")");
            bw.newLine();
            bw.write(".Range(\"AE1\", .Range(\"AE\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AE1\")");
            bw.newLine();
            bw.write(".Range(\"AF1\", .Range(\"AF\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AF1\")");
            bw.newLine();
            bw.write(".Range(\"AG1\", .Range(\"AG\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AG1\")");
            bw.newLine();
            bw.write(".Range(\"AH1\", .Range(\"AH\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AH1\")");
            bw.newLine();
            bw.write(".Range(\"AI1\", .Range(\"AI\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AI1\")");
            bw.newLine();
            bw.write(".Range(\"AJ1\", .Range(\"AJ\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AJ1\")");
            bw.newLine();
            bw.write(".Range(\"AK1\", .Range(\"AK\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AK1\")");
            bw.newLine();
            bw.write(".Range(\"AL1\", .Range(\"AL\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AL1\")");
            bw.newLine();
            bw.write(".Range(\"AM1\", .Range(\"AM\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AM1\")");
            bw.newLine();
            bw.write(".Range(\"AN1\", .Range(\"AN\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AN1\")");
            bw.newLine();
            bw.write(".Range(\"AO1\", .Range(\"AO\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AO1\")");
            bw.newLine();
            bw.write("End With");
            bw.newLine();
            //-->END ADD COLUMN VALIDATION UPTO 34 RAnge A TO AH THIS REDUCE THE SIZE 0N 23-11-2021.
            bw.write("local = true");
            bw.newLine();
            bw.write("call oBook.SaveAs(" + para4 + ", 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, local) 'this changed");
            bw.newLine();
            bw.write("oBook.Close False");
            bw.newLine();
            bw.write("oExcel.Quit");
            bw.newLine();
            bw.write("FSO.MoveFile " + para4 + " ," + para1);
            bw.close();
            Runtime.getRuntime().exec("wscript" + " " + l_vbs_file_name);
        } catch (Exception e) {
            utl.generateLog(null, e.getMessage());
            //System.exit(0);
        }
    }

    public void xlsTocsvSalaryTempalte(String inputFile, String outputFile, String javaDriveName) {
        try {
            Util utl = new Util();
            String l_tm_stam = dtl.get_sysdate("DD_dd_MM_yy_hh_mm_ss_SSS").toUpperCase();
            String l_vbs_file_name = javaDriveName + "/vbs/XLSTOCSV" + l_tm_stam + ".vbs";
            File file = new File(l_vbs_file_name);
            file.getParentFile().mkdirs();
            String para0 = "\"" + inputFile + "\"";
            String para1 = "\"" + outputFile + ".csv\"";
            String para4 = "\"" + outputFile + "_temp.csv" + "\"";
            if (!file.exists()) {
                file.createNewFile();//file not exists create it
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);//use buffered writer for multiline and multichar
            bw.write("Set FSO=CreateObject(\"Scripting.FileSystemObject\")");
            bw.newLine();
            bw.write("if fso.FileExists(" + para1 + ") THEN ");
            bw.newLine();
            bw.write("fso.DeleteFile" + para1);
            bw.newLine();
            bw.write("end if");
            bw.newLine();
            bw.write("Dim oExcel");
            bw.newLine();
            bw.write("Set oExcel = CreateObject(\"Excel.Application\")");
            bw.newLine();
            bw.write("oExcel.DisplayAlerts = FALSE 'to avoid prompts");
            bw.newLine();
            bw.write("Dim oBook, local");
            bw.newLine();
            bw.write("Set oBook = oExcel.Workbooks.Open(" + para0 + ")");
            bw.newLine();
//         START-->ADD COLUMN VALIDATION UPTO 34 RAnge A TO AH THIS REDUCE THE SIZE ON 23-11-2021.
            bw.write("Set wsSource = oBook.Sheets(1)");
            bw.newLine();
            bw.write("Set wsTarget = oBook.Sheets.Add()");
            bw.newLine();
            bw.write("Const xlUp = -4162");
            bw.newLine();
            bw.write("With wsSource");
            bw.newLine();
            bw.write(".Range(\"A1\", .Range(\"A\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"A1\")");
            bw.newLine();
            bw.write(".Range(\"B1\", .Range(\"B\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"B1\")");
            bw.newLine();
            bw.write(".Range(\"C1\", .Range(\"C\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"C1\")");
            bw.newLine();
            bw.write(".Range(\"D1\", .Range(\"D\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"D1\")");
            bw.newLine();
            bw.write(".Range(\"E1\", .Range(\"E\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"E1\")");
            bw.newLine();
            bw.write(".Range(\"F1\", .Range(\"F\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"F1\")");
            bw.newLine();
            bw.write(".Range(\"G1\", .Range(\"G\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"G1\")");
            bw.newLine();
            bw.write(".Range(\"H1\", .Range(\"H\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"H1\")");
            bw.newLine();
            bw.write(".Range(\"I1\", .Range(\"I\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"I1\")");
            bw.newLine();
            bw.write(".Range(\"J1\", .Range(\"J\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"J1\")");
            bw.newLine();
            bw.write(".Range(\"K1\", .Range(\"K\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"K1\")");
            bw.newLine();
            bw.write(".Range(\"L1\", .Range(\"L\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"L1\")");
            bw.newLine();
            bw.write(".Range(\"M1\", .Range(\"M\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"M1\")");
            bw.newLine();
            bw.write(".Range(\"N1\", .Range(\"N\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"N1\")");
            bw.newLine();
            bw.write(".Range(\"O1\", .Range(\"O\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"O1\")");
            bw.newLine();
            bw.write(".Range(\"P1\", .Range(\"P\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"P1\")");
            bw.newLine();
            bw.write(".Range(\"Q1\", .Range(\"Q\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"Q1\")");
            bw.newLine();
            bw.write(".Range(\"R1\", .Range(\"R\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"R1\")");
            bw.newLine();
            bw.write(".Range(\"S1\", .Range(\"S\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"S1\")");
            bw.newLine();
            bw.write(".Range(\"T1\", .Range(\"T\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"T1\")");
            bw.newLine();
            bw.write(".Range(\"U1\", .Range(\"U\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"U1\")");
            bw.newLine();
            bw.write(".Range(\"V1\", .Range(\"V\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"V1\")");
            bw.newLine();
            bw.write(".Range(\"W1\", .Range(\"W\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"W1\")");
            bw.newLine();
            bw.write(".Range(\"X1\", .Range(\"X\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"X1\")");
            bw.newLine();
            bw.write(".Range(\"Y1\", .Range(\"Y\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"Y1\")");
            bw.newLine();
            bw.write(".Range(\"Z1\", .Range(\"Z\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"Z1\")");
            bw.newLine();
            bw.write(".Range(\"AA1\", .Range(\"AA\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AA1\")");
            bw.newLine();
            bw.write(".Range(\"AB1\", .Range(\"AB\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AB1\")");
            bw.newLine();
            bw.write(".Range(\"AC1\", .Range(\"AC\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AC1\")");
            bw.newLine();
            bw.write(".Range(\"AD1\", .Range(\"AD\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AD1\")");
            bw.newLine();
            bw.write(".Range(\"AE1\", .Range(\"AE\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AE1\")");
            bw.newLine();
            bw.write(".Range(\"AF1\", .Range(\"AF\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AF1\")");
            bw.newLine();
            bw.write(".Range(\"AG1\", .Range(\"AG\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AG1\")");
            bw.newLine();
            bw.write(".Range(\"AH1\", .Range(\"AH\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AH1\")");
            bw.newLine();
            bw.write(".Range(\"AI1\", .Range(\"AI\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AI1\")");
            bw.newLine();
            bw.write(".Range(\"AJ1\", .Range(\"AJ\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AJ1\")");
            bw.newLine();
            bw.write(".Range(\"AK1\", .Range(\"AK\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AK1\")");
            bw.newLine();
            bw.write(".Range(\"AL1\", .Range(\"AL\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AL1\")");
            bw.newLine();
            bw.write(".Range(\"AM1\", .Range(\"AM\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AM1\")");
            bw.newLine();
            bw.write(".Range(\"AN1\", .Range(\"AN\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AN1\")");
            bw.newLine();
            bw.write(".Range(\"AO1\", .Range(\"AO\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AO1\")");
            bw.newLine();
            bw.write(".Range(\"AP1\", .Range(\"AP\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AP1\")");
            bw.newLine();
            bw.write(".Range(\"AQ1\", .Range(\"AQ\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AQ1\")");
            bw.newLine();
            bw.write(".Range(\"AR1\", .Range(\"AR\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AR1\")");
            bw.newLine();
            bw.write(".Range(\"AS1\", .Range(\"AS\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AS1\")");
            bw.newLine();
            bw.write(".Range(\"AT1\", .Range(\"AT\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AT1\")");
            bw.newLine();
            bw.write(".Range(\"AU1\", .Range(\"AU\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AU1\")");
            bw.newLine();
            bw.write(".Range(\"AV1\", .Range(\"AV\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AV1\")");
            bw.newLine();
            bw.write(".Range(\"AW1\", .Range(\"AW\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AW1\")");
            bw.newLine();
            bw.write(".Range(\"AX1\", .Range(\"AX\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AX1\")");
            bw.newLine();
            bw.write(".Range(\"AY1\", .Range(\"AY\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AY1\")");
            bw.newLine();
            bw.write(".Range(\"AZ1\", .Range(\"AZ\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"AZ1\")");
            bw.newLine();
            bw.write(".Range(\"BA1\", .Range(\"BA\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BA1\")");
            bw.newLine();
            bw.write(".Range(\"BB1\", .Range(\"BB\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BB1\")");
            bw.newLine();
            bw.write(".Range(\"BC1\", .Range(\"BC\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BC1\")");
            bw.newLine();
            bw.write(".Range(\"BD1\", .Range(\"BD\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BD1\")");
            bw.newLine();
            bw.write(".Range(\"BE1\", .Range(\"BE\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BE1\")");
            bw.newLine();
            bw.write(".Range(\"BF1\", .Range(\"BF\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BF1\")");
            bw.newLine();
            bw.write(".Range(\"BG1\", .Range(\"BG\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BG1\")");
            bw.newLine();
            bw.write(".Range(\"BH1\", .Range(\"BH\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BH1\")");
            bw.newLine();
            bw.write(".Range(\"BI1\", .Range(\"BI\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BI1\")");
            bw.newLine();
            bw.write(".Range(\"BJ1\", .Range(\"BJ\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BJ1\")");
            bw.newLine();
            bw.write(".Range(\"BK1\", .Range(\"BK\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BK1\")");
            bw.newLine();
            bw.write(".Range(\"BL1\", .Range(\"BL\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BL1\")");
            bw.newLine();
            bw.write(".Range(\"BM1\", .Range(\"BM\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BM1\")");
            bw.newLine();
            bw.write(".Range(\"BN1\", .Range(\"BN\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BN1\")");
            bw.newLine();
            bw.write(".Range(\"BO1\", .Range(\"BO\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BO1\")");
            bw.newLine();
            bw.write(".Range(\"BP1\", .Range(\"BP\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BP1\")");
            bw.newLine();
            bw.write(".Range(\"BQ1\", .Range(\"BQ\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BQ1\")");
            bw.newLine();
            bw.write(".Range(\"BR1\", .Range(\"BR\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BR1\")");
            bw.newLine();
            bw.write(".Range(\"BS1\", .Range(\"BS\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BS1\")");
            bw.newLine();
            bw.write(".Range(\"BT1\", .Range(\"BT\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BT1\")");
            bw.newLine();
            bw.write(".Range(\"BU1\", .Range(\"BU\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BU1\")");
            bw.newLine();
            bw.write(".Range(\"BV1\", .Range(\"BV\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BV1\")");
            bw.newLine();
            bw.write(".Range(\"BW1\", .Range(\"BW\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BW1\")");
            bw.newLine();
            bw.write(".Range(\"BX1\", .Range(\"BX\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BX1\")");
            bw.newLine();
            bw.write(".Range(\"BY1\", .Range(\"BY\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BY1\")");
            bw.newLine();
            bw.write(".Range(\"BZ1\", .Range(\"BZ\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"BZ1\")");
            bw.newLine();
            bw.write(".Range(\"CA1\", .Range(\"CA\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"CA1\")");
            bw.newLine();
            bw.write(".Range(\"CB1\", .Range(\"CB\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"CB1\")");
            bw.newLine();
            bw.write(".Range(\"CC1\", .Range(\"CC\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"CC1\")");
            bw.newLine();
            bw.write(".Range(\"CD1\", .Range(\"CD\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"CD1\")");
            bw.newLine();
            bw.write(".Range(\"CE1\", .Range(\"CE\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"CE1\")");
            bw.newLine();
            bw.write(".Range(\"CF1\", .Range(\"CF\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"CF1\")");
            bw.newLine();
            bw.write(".Range(\"CG1\", .Range(\"CG\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"CG1\")");
            bw.newLine();
            bw.write(".Range(\"CH1\", .Range(\"CH\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"CH1\")");
            bw.newLine();
            bw.write(".Range(\"CI1\", .Range(\"CI\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"CI1\")");
            bw.newLine();
            bw.write(".Range(\"CJ1\", .Range(\"CJ\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"CJ1\")");
            bw.newLine();
            bw.write(".Range(\"CK1\", .Range(\"CK\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"CK1\")");
            bw.newLine();
            bw.write(".Range(\"CL1\", .Range(\"CL\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"CL1\")");
            bw.newLine();
            bw.write(".Range(\"CM1\", .Range(\"CM\" & .Rows.Count).End(xlUp)).Copy wsTarget.Range(\"CM1\")");
            bw.newLine();
            bw.write("End With");
            bw.newLine();
            //-->END ADD COLUMN VALIDATION UPTO 34 RAnge A TO AH THIS REDUCE THE SIZE 0N 23-11-2021.
            bw.newLine();
            bw.write("local = true");
            bw.newLine();
            bw.write("call oBook.SaveAs(" + para4 + ", 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, local) 'this changed");
            bw.newLine();
            bw.write("oBook.Close False");
            bw.newLine();
            bw.write("oExcel.Quit");
            bw.newLine();
            bw.write("FSO.MoveFile " + para4 + " ," + para1);
            bw.close();
            Runtime.getRuntime().exec("wscript" + " " + l_vbs_file_name);
        } catch (Exception e) {
            utl.generateLog(null, e.getMessage());
            //System.exit(0);
        }
    }

    public void xlsTocsv_chk(String output_chk) {
        try {
            Util utl = new Util();
            String l_tm_stam = dtl.get_sysdate("DD_dd_MM_yy_hh_mm_ss_SSS").toUpperCase();
            String l_vbs_file_name_chk = "D:/XLSTOCSVCHK" + l_tm_stam + ".vbs";
            File file = new File(l_vbs_file_name_chk);
            if (!file.exists()) {
                file.createNewFile();//file not exists create it
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);//use buffered writer for multiline and multichar
            bw.write("Set objFSO=CreateObject(\"Scripting.FileSystemObject\")");
            bw.newLine();
            bw.write("outFile=" + "\"" + output_chk + "\"");
            bw.newLine();
            bw.write("Set objFile = objFSO.CreateTextFile(outFile,True)");
            bw.newLine();
            bw.write("objFile.Close");
            bw.newLine();
            bw.close();
            Runtime.getRuntime().exec("wscript" + " " + l_vbs_file_name_chk);
            utl.generateLog(null, "execute2");
        } catch (Exception e) {
            utl.generateLog(null, e.getMessage());
            //System.exit(0);
        }
    }

}
