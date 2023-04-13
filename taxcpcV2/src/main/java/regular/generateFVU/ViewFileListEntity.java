/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.generateFVU;

/**
 *
 * @author akash.dev
 */
public class ViewFileListEntity {

    private String fileName;
    private String date;
    private String hiddenFileLoc;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHiddenFileLoc() {
        return hiddenFileLoc;
    }

    public void setHiddenFileLoc(String hiddenFileLoc) {
        this.hiddenFileLoc = hiddenFileLoc;
    }

}
