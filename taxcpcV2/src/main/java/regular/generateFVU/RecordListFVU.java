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
public class RecordListFVU {

    public String user_Seq;
    public String data_type;
    public String data_type_name;
    public String rec_count;
//    public String entity_code;
//    public String acc_year;
//    public String quarter_no;
//    public String tds_type_code;
//    public String tds_tran_count;
//    public String tds_challan_count;

    public String getUser_Seq() {
        return user_Seq;
    }

    public void setUser_Seq(String user_Seq) {
        this.user_Seq = user_Seq;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public String getData_type_name() {
        return data_type_name;
    }

    public void setData_type_name(String data_type_name) {
        this.data_type_name = data_type_name;
    }

    public String getRec_count() {
        return rec_count;
    }

    public void setRec_count(String rec_count) {
        this.rec_count = rec_count;
    }

}
