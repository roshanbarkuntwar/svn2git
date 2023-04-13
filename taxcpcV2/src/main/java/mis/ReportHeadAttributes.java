/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mis;

import java.lang.reflect.Field;

/**
 *
 * @author gaurav.khanzode
 */
public class ReportHeadAttributes {

    public String report_header_captions;
    public String report_header_width;
    public String report_header_data_type;

    public String getReport_header_captions() {
        return report_header_captions;
    }

    public void setReport_header_captions(String report_header_captions) {
        this.report_header_captions = report_header_captions;
    }

    public String getReport_header_width() {
        return report_header_width;
    }

    public void setReport_header_width(String report_header_width) {
        this.report_header_width = report_header_width;
    }

    public String getReport_header_data_type() {
        return report_header_data_type;
    }

    public void setReport_header_data_type(String report_header_data_type) {
        this.report_header_data_type = report_header_data_type;
    }

    public void to_String() {
        Field[] flds = this.getClass().getDeclaredFields();
        for (Field fld : flds) {
            try {
                //System.out.println(fld.getName() + " : " + fld.get(this));
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
        }
    }//end method

    public void clear_fields() {
        Field[] flds = this.getClass().getDeclaredFields();
        for (Field fld : flds) {
            try {
                fld.set(this, "");
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
    }//end method

}//end class

