/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package globalUtilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author akash.dev
 */
public class WriteFileUtil {

    public String write_file(String fvu_full_path, String tanno, String dsc_pan, String dsc_pin, String folder_path) {
        String status = "F";
        try {
            String content = fvu_full_path + "\n"
                    + tanno + "\n"
                    + dsc_pan + "\n"
                    + dsc_pin + "\n"
                    + folder_path;
            File file = new File("E:/DSC_MAN/DCS_DETAILS.txt");
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
            status = "T";
        } catch (IOException e) {
            e.printStackTrace();
            status = "F";
        }
        return status;
    }

    /**
     *
     * @param login_id
     * @param login_pwd
     * @param tanno
     * @param generated_path
     * @param request_no
     * @return
     */
    public static String write_file(String login_id, String login_pwd, String tanno, String generated_path, int request_no) {
        String status = "F";
        try {
            String content = login_id + "#" + login_pwd + "#" + tanno + "#" + generated_path + "#" + request_no + "#";
            File file = new File("D:/abc.txt");
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
            status = "T";
        } catch (IOException e) {
            e.printStackTrace();
            status = "F";
        }
        return status;
    }

    public static String write_file(String login_id, String login_pwd, String tanno, String generated_path) {
        String status = "F";
        try {
            String content = "";
            File file = new File("D:/abc.txt");
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("if WScript.Arguments.Count < 2 Then ");
            bw.newLine();
            bw.write(" WScript.Echo \"Error! Please specify the source path and the destination. Usage: XlsToCsv SourcePath.xls Destination.csv\" ");
            bw.newLine();
            bw.write(" Wscript.Quit");
            bw.newLine();
            bw.write("End If");
            bw.newLine();
            bw.write("Dim oExcel");
            bw.newLine();
            bw.write("Set oExcel = CreateObject(\"Excel.Application\")");
            bw.newLine();
            bw.write("oExcel.DisplayAlerts = FALSE 'to avoid prompts");
            bw.newLine();
            bw.write("Dim oBook, local");
            bw.newLine();
            bw.write("Set oBook = oExcel.Workbooks.Open(Wscript.Arguments.Item(0))");
            bw.newLine();
            bw.write("local = true");
            bw.newLine();
            bw.write("call oBook.SaveAs(WScript.Arguments.Item(1), 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, local) 'this changed");
            bw.newLine();
            bw.write("oBook.Close False");
            bw.newLine();
            bw.write("oExcel.Quit");
            bw.newLine();
            bw.write("rem WScript.Echo \"Done\"");
            bw.newLine();
            bw.close();
            status = "T";
        } catch (IOException e) {
            e.printStackTrace();
            status = "F";
        }
        return status;
    }

//    public static void main(String[] args) {
////        write_file("NGPW00193E9", "FGSDCA123", "NGPW00193E", "F:\\File_Download", 41235630);
//        write_file("NGPW00193E9", "FGSDCA123", "NGPW00193E", "F:\\File_Download");
//    }
}
