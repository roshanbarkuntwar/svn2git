/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import globalUtilities.Util;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
/**
 *
 * @author akash.dev
 */
@Entity
@Table(name = "lhssys_alert_direct_email")
public class LhssysAlertDirectEmail implements java.io.Serializable {

    @Column(name = "entity_code", length = 2, nullable = false)
    private String entity_code;
    @Id
    @Column(name = "seq_id", nullable = false)
    private Long seq_id;
    @Column(name = "alert_desc", length = 50, nullable = false)
    private String alert_desc;
    @Column(name = "email", length = 1000, nullable = false)
    private String email;
    @Column(name = "email_cc", length = 1000, nullable = true)
    private String email_cc;
    @Column(name = "email_bcc", length = 1000, nullable = true)
    private String email_bcc;
    @Column(name = "subject", length = 100, nullable = false)
    private String subject;
    @Column(name = "body_text", length = 300, nullable = false)
    private String body_text;
    @Column(name = "pl_sql_text", length = 4000, nullable = true)
    private String pl_sql_text;
    @Column(name = "sql_text", length = 4000, nullable = false)
    private String sql_text;
    @Column(name = "no_of_column", length = 2, nullable = true)
    private String no_of_column;
    @Column(name = "jar_file", length = 1000, nullable = false)
    private String jar_file;
    @Column(name = "batch_file", length = 50, nullable = false)
    private String batch_file;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "lastupdate", nullable = true)
    private Date lastupdate;
    @Column(name = "user_code", length = 8, nullable = false)
    private String user_code;
    @Column(name = "flag", length = 1, nullable = true)
    private String flag;
    @Column(name = "heading1", length = 200, nullable = true)
    private String heading1;
    @Column(name = "heading2", length = 200, nullable = true)
    private String heading2;
    @Column(name = "heading3", length = 200, nullable = true)
    private String heading3;
    @Column(name = "heading4", length = 200, nullable = true)
    private String heading4;
    @Column(name = "heading5", length = 200, nullable = true)
    private String heading5;
    @Column(name = "heading6", length = 200, nullable = true)
    private String heading6;
    @Column(name = "heading7", length = 200, nullable = true)
    private String heading7;
    @Column(name = "heading8", length = 200, nullable = true)
    private String heading8;
    @Column(name = "heading9", length = 200, nullable = true)
    private String heading9;
    @Column(name = "heading10", length = 200, nullable = true)
    private String heading10;
    @Column(name = "heading11", length = 200, nullable = true)
    private String heading11;
    @Column(name = "heading12", length = 200, nullable = true)
    private String heading12;
    @Column(name = "heading13", length = 200, nullable = true)
    private String heading13;
    @Column(name = "heading14", length = 200, nullable = true)
    private String heading14;
    @Column(name = "heading15", length = 200, nullable = true)
    private String heading15;
    @Column(name = "heading16", length = 200, nullable = true)
    private String heading16;
    @Column(name = "heading17", length = 200, nullable = true)
    private String heading17;
    @Column(name = "heading18", length = 200, nullable = true)
    private String heading18;
    @Column(name = "heading19", length = 200, nullable = true)
    private String heading19;
    @Column(name = "heading20", length = 200, nullable = true)
    private String heading20;
    @Column(name = "heading21", length = 200, nullable = true)
    private String heading21;
    @Column(name = "heading22", length = 200, nullable = true)
    private String heading22;
    @Column(name = "heading23", length = 200, nullable = true)
    private String heading23;
    @Column(name = "heading24", length = 200, nullable = true)
    private String heading24;
    @Column(name = "heading25", length = 200, nullable = true)
    private String heading25;
    @Column(name = "head_width1", length = 3, nullable = true)
    private String head_width1;
    @Column(name = "head_width2", length = 3, nullable = true)
    private String head_width2;
    @Column(name = "head_width3", length = 3, nullable = true)
    private String head_width3;
    @Column(name = "head_width4", length = 3, nullable = true)
    private String head_width4;
    @Column(name = "head_width5", length = 3, nullable = true)
    private String head_width5;
    @Column(name = "head_width6", length = 3, nullable = true)
    private String head_width6;
    @Column(name = "head_width7", length = 3, nullable = true)
    private String head_width7;
    @Column(name = "head_width8", length = 3, nullable = true)
    private String head_width8;
    @Column(name = "head_width9", length = 3, nullable = true)
    private String head_width9;
    @Column(name = "head_width10", length = 3, nullable = true)
    private String head_width10;
    @Column(name = "head_width11", length = 3, nullable = true)
    private String head_width11;
    @Column(name = "head_width12", length = 3, nullable = true)
    private String head_width12;
    @Column(name = "head_width13", length = 3, nullable = true)
    private String head_width13;
    @Column(name = "head_width14", length = 3, nullable = true)
    private String head_width14;
    @Column(name = "head_width15", length = 3, nullable = true)
    private String head_width15;
    @Column(name = "head_width16", length = 3, nullable = true)
    private String head_width16;
    @Column(name = "head_width17", length = 3, nullable = true)
    private String head_width17;
    @Column(name = "head_width18", length = 3, nullable = true)
    private String head_width18;
    @Column(name = "head_width19", length = 3, nullable = true)
    private String head_width19;
    @Column(name = "head_width20", length = 3, nullable = true)
    private String head_width20;
    @Column(name = "head_width21", length = 3, nullable = true)
    private String head_width21;
    @Column(name = "head_width22", length = 3, nullable = true)
    private String head_width22;
    @Column(name = "head_width23", length = 3, nullable = true)
    private String head_width23;
    @Column(name = "head_width24", length = 3, nullable = true)
    private String head_width24;
    @Column(name = "head_width25", length = 3, nullable = true)
    private String head_width25;
    @Column(name = "report_delivery_type", length = 1, nullable = false)
    private String report_delivery_type;
    @Column(name = "portlet_id", length = 10, nullable = true)
    private String portlet_id;
    @Column(name = "color_code_format", length = 50, nullable = true)
    private String color_code_format;
    @Column(name = "data_type1", length = 20, nullable = true)
    private String data_type1;
    @Column(name = "data_type2", length = 20, nullable = true)
    private String data_type2;
    @Column(name = "data_type3", length = 20, nullable = true)
    private String data_type3;
    @Column(name = "data_type4", length = 20, nullable = true)
    private String data_type4;
    @Column(name = "data_type5", length = 20, nullable = true)
    private String data_type5;
    @Column(name = "data_type6", length = 20, nullable = true)
    private String data_type6;
    @Column(name = "data_type7", length = 20, nullable = true)
    private String data_type7;
    @Column(name = "data_type8", length = 20, nullable = true)
    private String data_type8;
    @Column(name = "data_type9", length = 20, nullable = true)
    private String data_type9;
    @Column(name = "data_type10", length = 20, nullable = true)
    private String data_type10;
    @Column(name = "data_type11", length = 20, nullable = true)
    private String data_type11;
    @Column(name = "data_type12", length = 20, nullable = true)
    private String data_type12;
    @Column(name = "data_type13", length = 20, nullable = true)
    private String data_type13;
    @Column(name = "data_type14", length = 20, nullable = true)
    private String data_type14;
    @Column(name = "data_type15", length = 20, nullable = true)
    private String data_type15;
    @Column(name = "data_type16", length = 20, nullable = true)
    private String data_type16;
    @Column(name = "data_type17", length = 20, nullable = true)
    private String data_type17;
    @Column(name = "data_type18", length = 20, nullable = true)
    private String data_type18;
    @Column(name = "data_type19", length = 20, nullable = true)
    private String data_type19;
    @Column(name = "data_type20", length = 20, nullable = true)
    private String data_type20;
    @Column(name = "data_type21", length = 20, nullable = true)
    private String data_type21;
    @Column(name = "data_type22", length = 20, nullable = true)
    private String data_type22;
    @Column(name = "data_type23", length = 20, nullable = true)
    private String data_type23;
    @Column(name = "data_type24", length = 20, nullable = true)
    private String data_type24;
    @Column(name = "data_type25", length = 20, nullable = true)
    private String data_type25;
    @Column(name = "font_size", length = 10, nullable = true)
    private String font_size;
    @Column(name = "rep_code", length = 10, nullable = true)
    private String rep_code;
    @Column(name = "send_when_rec", length = 1, nullable = true)
    private String send_when_rec;
    @Column(name = "email_sql_text", length = 4000, nullable = true)
    private String email_sql_text;
    @Column(name = "email_sql_text_column", nullable = true)
    private String email_sql_text_column;
    @Column(name = "sub_where_clause_replace_val1", length = 30, nullable = true)
    private String sub_where_clause_replace_val1;
    @Column(name = "sub_where_clause_replace_val2", length = 30, nullable = true)
    private String sub_where_clause_replace_val2;
    @Column(name = "sub_where_clause_replace_val3", length = 30, nullable = true)
    private String sub_where_clause_replace_val3;
    @Column(name = "sub_where_clause_replace_val4", length = 30, nullable = true)
    private String sub_where_clause_replace_val4;
    @Column(name = "sub_where_clause_replace_val5", length = 30, nullable = true)
    private String sub_where_clause_replace_val5;
    @Column(name = "to_email_sub_clause", length = 1000, nullable = true)
    private String to_email_sub_clause;
    @Column(name = "cc_email_sub_clause", length = 1000, nullable = true)
    private String cc_email_sub_clause;
    @Column(name = "sub_where_clause_rep_val_col1", nullable = true)
    private String sub_where_clause_rep_val_col1;
    @Column(name = "sub_where_clause_rep_val_col2", nullable = true)
    private String sub_where_clause_rep_val_col2;
    @Column(name = "sub_where_clause_rep_val_col3", nullable = true)
    private String sub_where_clause_rep_val_col3;
    @Column(name = "sub_where_clause_rep_val_col4", nullable = true)
    private String sub_where_clause_rep_val_col4;
    @Column(name = "sub_where_clause_rep_val_col5", nullable = true)
    private String sub_where_clause_rep_val_col5;
    @Column(name = "to_email_sql_id", length = 4000, nullable = true)
    private String to_email_sql_id;
    @Column(name = "total_column", nullable = true)
    private String total_column;
    @Column(name = "report_group", length = 50, nullable = true)
    private String report_group;
    @Column(name = "report_format", length = 3, nullable = true)
    private String report_format;

    public LhssysAlertDirectEmail() {

    }//end constructor

    public LhssysAlertDirectEmail(String entity_code, Long seq_id, String alert_desc, String email, String email_cc, String email_bcc, String subject, String body_text, String pl_sql_text, String sql_text, String no_of_column, String jar_file, String batch_file, Date lastupdate, String user_code, String flag, String heading1, String heading2, String heading3, String heading4, String heading5, String heading6, String heading7, String heading8, String heading9, String heading10, String heading11, String heading12, String heading13, String heading14, String heading15, String heading16, String heading17, String heading18, String heading19, String heading20, String heading21, String heading22, String heading23, String heading24, String heading25, String head_width1, String head_width2, String head_width3, String head_width4, String head_width5, String head_width6, String head_width7, String head_width8, String head_width9, String head_width10, String head_width11, String head_width12, String head_width13, String head_width14, String head_width15, String head_width16, String head_width17, String head_width18, String head_width19, String head_width20, String head_width21, String head_width22, String head_width23, String head_width24, String head_width25, String report_delivery_type, String portlet_id, String color_code_format, String data_type1, String data_type2, String data_type3, String data_type4, String data_type5, String data_type6, String data_type7, String data_type8, String data_type9, String data_type10, String data_type11, String data_type12, String data_type13, String data_type14, String data_type15, String data_type16, String data_type17, String data_type18, String data_type19, String data_type20, String data_type21, String data_type22, String data_type23, String data_type24, String data_type25, String font_size, String rep_code, String send_when_rec, String email_sql_text, String email_sql_text_column, String sub_where_clause_replace_val1, String sub_where_clause_replace_val2, String sub_where_clause_replace_val3, String sub_where_clause_replace_val4, String sub_where_clause_replace_val5, String to_email_sub_clause, String cc_email_sub_clause, String sub_where_clause_rep_val_col1, String sub_where_clause_rep_val_col2, String sub_where_clause_rep_val_col3, String sub_where_clause_rep_val_col4, String sub_where_clause_rep_val_col5, String to_email_sql_id, String total_column, String report_group, String report_format) {

        this.entity_code = entity_code;
        this.seq_id = seq_id;
        this.alert_desc = alert_desc;
        this.email = email;
        this.email_cc = email_cc;
        this.email_bcc = email_bcc;
        this.subject = subject;
        this.body_text = body_text;
        this.pl_sql_text = pl_sql_text;
        this.sql_text = sql_text;
        this.no_of_column = no_of_column;
        this.jar_file = jar_file;
        this.batch_file = batch_file;
        this.lastupdate = lastupdate;
        this.user_code = user_code;
        this.flag = flag;
        this.heading1 = heading1;
        this.heading2 = heading2;
        this.heading3 = heading3;
        this.heading4 = heading4;
        this.heading5 = heading5;
        this.heading6 = heading6;
        this.heading7 = heading7;
        this.heading8 = heading8;
        this.heading9 = heading9;
        this.heading10 = heading10;
        this.heading11 = heading11;
        this.heading12 = heading12;
        this.heading13 = heading13;
        this.heading14 = heading14;
        this.heading15 = heading15;
        this.heading16 = heading16;
        this.heading17 = heading17;
        this.heading18 = heading18;
        this.heading19 = heading19;
        this.heading20 = heading20;
        this.heading21 = heading21;
        this.heading22 = heading22;
        this.heading23 = heading23;
        this.heading24 = heading24;
        this.heading25 = heading25;
        this.head_width1 = head_width1;
        this.head_width2 = head_width2;
        this.head_width3 = head_width3;
        this.head_width4 = head_width4;
        this.head_width5 = head_width5;
        this.head_width6 = head_width6;
        this.head_width7 = head_width7;
        this.head_width8 = head_width8;
        this.head_width9 = head_width9;
        this.head_width10 = head_width10;
        this.head_width11 = head_width11;
        this.head_width12 = head_width12;
        this.head_width13 = head_width13;
        this.head_width14 = head_width14;
        this.head_width15 = head_width15;
        this.head_width16 = head_width16;
        this.head_width17 = head_width17;
        this.head_width18 = head_width18;
        this.head_width19 = head_width19;
        this.head_width20 = head_width20;
        this.head_width21 = head_width21;
        this.head_width22 = head_width22;
        this.head_width23 = head_width23;
        this.head_width24 = head_width24;
        this.head_width25 = head_width25;
        this.report_delivery_type = report_delivery_type;
        this.portlet_id = portlet_id;
        this.color_code_format = color_code_format;
        this.data_type1 = data_type1;
        this.data_type2 = data_type2;
        this.data_type3 = data_type3;
        this.data_type4 = data_type4;
        this.data_type5 = data_type5;
        this.data_type6 = data_type6;
        this.data_type7 = data_type7;
        this.data_type8 = data_type8;
        this.data_type9 = data_type9;
        this.data_type10 = data_type10;
        this.data_type11 = data_type11;
        this.data_type12 = data_type12;
        this.data_type13 = data_type13;
        this.data_type14 = data_type14;
        this.data_type15 = data_type15;
        this.data_type16 = data_type16;
        this.data_type17 = data_type17;
        this.data_type18 = data_type18;
        this.data_type19 = data_type19;
        this.data_type20 = data_type20;
        this.data_type21 = data_type21;
        this.data_type22 = data_type22;
        this.data_type23 = data_type23;
        this.data_type24 = data_type24;
        this.data_type25 = data_type25;
        this.font_size = font_size;
        this.rep_code = rep_code;
        this.send_when_rec = send_when_rec;
        this.email_sql_text = email_sql_text;
        this.email_sql_text_column = email_sql_text_column;
        this.sub_where_clause_replace_val1 = sub_where_clause_replace_val1;
        this.sub_where_clause_replace_val2 = sub_where_clause_replace_val2;
        this.sub_where_clause_replace_val3 = sub_where_clause_replace_val3;
        this.sub_where_clause_replace_val4 = sub_where_clause_replace_val4;
        this.sub_where_clause_replace_val5 = sub_where_clause_replace_val5;
        this.to_email_sub_clause = to_email_sub_clause;
        this.cc_email_sub_clause = cc_email_sub_clause;
        this.sub_where_clause_rep_val_col1 = sub_where_clause_rep_val_col1;
        this.sub_where_clause_rep_val_col2 = sub_where_clause_rep_val_col2;
        this.sub_where_clause_rep_val_col3 = sub_where_clause_rep_val_col3;
        this.sub_where_clause_rep_val_col4 = sub_where_clause_rep_val_col4;
        this.sub_where_clause_rep_val_col5 = sub_where_clause_rep_val_col5;
        this.to_email_sql_id = to_email_sql_id;
        this.total_column = total_column;
        this.report_group = report_group;
        this.report_format = report_format;
    }

    public String getEntity_code() {
        return entity_code;
    }

    public void setEntity_code(String entity_code) {
        this.entity_code = entity_code;
    }

    public Long getSeq_id() {
        return seq_id;
    }

    public void setSeq_id(Long seq_id) {
        this.seq_id = seq_id;
    }

    public String getAlert_desc() {
        return alert_desc;
    }

    public void setAlert_desc(String alert_desc) {
        this.alert_desc = alert_desc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_cc() {
        return email_cc;
    }

    public void setEmail_cc(String email_cc) {
        this.email_cc = email_cc;
    }

    public String getEmail_bcc() {
        return email_bcc;
    }

    public void setEmail_bcc(String email_bcc) {
        this.email_bcc = email_bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody_text() {
        return body_text;
    }

    public void setBody_text(String body_text) {
        this.body_text = body_text;
    }

    public String getPl_sql_text() {
        return pl_sql_text;
    }

    public void setPl_sql_text(String pl_sql_text) {
        this.pl_sql_text = pl_sql_text;
    }

    public String getSql_text() {
        return sql_text;
    }

    public void setSql_text(String sql_text) {
        this.sql_text = sql_text;
    }

    public String getNo_of_column() {
        return no_of_column;
    }

    public void setNo_of_column(String no_of_column) {
        this.no_of_column = no_of_column;
    }

    public String getJar_file() {
        return jar_file;
    }

    public void setJar_file(String jar_file) {
        this.jar_file = jar_file;
    }

    public String getBatch_file() {
        return batch_file;
    }

    public void setBatch_file(String batch_file) {
        this.batch_file = batch_file;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getHeading1() {
        return heading1;
    }

    public void setHeading1(String heading1) {
        this.heading1 = heading1;
    }

    public String getHeading2() {
        return heading2;
    }

    public void setHeading2(String heading2) {
        this.heading2 = heading2;
    }

    public String getHeading3() {
        return heading3;
    }

    public void setHeading3(String heading3) {
        this.heading3 = heading3;
    }

    public String getHeading4() {
        return heading4;
    }

    public void setHeading4(String heading4) {
        this.heading4 = heading4;
    }

    public String getHeading5() {
        return heading5;
    }

    public void setHeading5(String heading5) {
        this.heading5 = heading5;
    }

    public String getHeading6() {
        return heading6;
    }

    public void setHeading6(String heading6) {
        this.heading6 = heading6;
    }

    public String getHeading7() {
        return heading7;
    }

    public void setHeading7(String heading7) {
        this.heading7 = heading7;
    }

    public String getHeading8() {
        return heading8;
    }

    public void setHeading8(String heading8) {
        this.heading8 = heading8;
    }

    public String getHeading9() {
        return heading9;
    }

    public void setHeading9(String heading9) {
        this.heading9 = heading9;
    }

    public String getHeading10() {
        return heading10;
    }

    public void setHeading10(String heading10) {
        this.heading10 = heading10;
    }

    public String getHeading11() {
        return heading11;
    }

    public void setHeading11(String heading11) {
        this.heading11 = heading11;
    }

    public String getHeading12() {
        return heading12;
    }

    public void setHeading12(String heading12) {
        this.heading12 = heading12;
    }

    public String getHeading13() {
        return heading13;
    }

    public void setHeading13(String heading13) {
        this.heading13 = heading13;
    }

    public String getHeading14() {
        return heading14;
    }

    public void setHeading14(String heading14) {
        this.heading14 = heading14;
    }

    public String getHeading15() {
        return heading15;
    }

    public void setHeading15(String heading15) {
        this.heading15 = heading15;
    }

    public String getHeading16() {
        return heading16;
    }

    public void setHeading16(String heading16) {
        this.heading16 = heading16;
    }

    public String getHeading17() {
        return heading17;
    }

    public void setHeading17(String heading17) {
        this.heading17 = heading17;
    }

    public String getHeading18() {
        return heading18;
    }

    public void setHeading18(String heading18) {
        this.heading18 = heading18;
    }

    public String getHeading19() {
        return heading19;
    }

    public void setHeading19(String heading19) {
        this.heading19 = heading19;
    }

    public String getHeading20() {
        return heading20;
    }

    public void setHeading20(String heading20) {
        this.heading20 = heading20;
    }

    public String getHeading21() {
        return heading21;
    }

    public void setHeading21(String heading21) {
        this.heading21 = heading21;
    }

    public String getHeading22() {
        return heading22;
    }

    public void setHeading22(String heading22) {
        this.heading22 = heading22;
    }

    public String getHeading23() {
        return heading23;
    }

    public void setHeading23(String heading23) {
        this.heading23 = heading23;
    }

    public String getHeading24() {
        return heading24;
    }

    public void setHeading24(String heading24) {
        this.heading24 = heading24;
    }

    public String getHeading25() {
        return heading25;
    }

    public void setHeading25(String heading25) {
        this.heading25 = heading25;
    }

    public String getHead_width1() {
        return head_width1;
    }

    public void setHead_width1(String head_width1) {
        this.head_width1 = head_width1;
    }

    public String getHead_width2() {
        return head_width2;
    }

    public void setHead_width2(String head_width2) {
        this.head_width2 = head_width2;
    }

    public String getHead_width3() {
        return head_width3;
    }

    public void setHead_width3(String head_width3) {
        this.head_width3 = head_width3;
    }

    public String getHead_width4() {
        return head_width4;
    }

    public void setHead_width4(String head_width4) {
        this.head_width4 = head_width4;
    }

    public String getHead_width5() {
        return head_width5;
    }

    public void setHead_width5(String head_width5) {
        this.head_width5 = head_width5;
    }

    public String getHead_width6() {
        return head_width6;
    }

    public void setHead_width6(String head_width6) {
        this.head_width6 = head_width6;
    }

    public String getHead_width7() {
        return head_width7;
    }

    public void setHead_width7(String head_width7) {
        this.head_width7 = head_width7;
    }

    public String getHead_width8() {
        return head_width8;
    }

    public void setHead_width8(String head_width8) {
        this.head_width8 = head_width8;
    }

    public String getHead_width9() {
        return head_width9;
    }

    public void setHead_width9(String head_width9) {
        this.head_width9 = head_width9;
    }

    public String getHead_width10() {
        return head_width10;
    }

    public void setHead_width10(String head_width10) {
        this.head_width10 = head_width10;
    }

    public String getHead_width11() {
        return head_width11;
    }

    public void setHead_width11(String head_width11) {
        this.head_width11 = head_width11;
    }

    public String getHead_width12() {
        return head_width12;
    }

    public void setHead_width12(String head_width12) {
        this.head_width12 = head_width12;
    }

    public String getHead_width13() {
        return head_width13;
    }

    public void setHead_width13(String head_width13) {
        this.head_width13 = head_width13;
    }

    public String getHead_width14() {
        return head_width14;
    }

    public void setHead_width14(String head_width14) {
        this.head_width14 = head_width14;
    }

    public String getHead_width15() {
        return head_width15;
    }

    public void setHead_width15(String head_width15) {
        this.head_width15 = head_width15;
    }

    public String getHead_width16() {
        return head_width16;
    }

    public void setHead_width16(String head_width16) {
        this.head_width16 = head_width16;
    }

    public String getHead_width17() {
        return head_width17;
    }

    public void setHead_width17(String head_width17) {
        this.head_width17 = head_width17;
    }

    public String getHead_width18() {
        return head_width18;
    }

    public void setHead_width18(String head_width18) {
        this.head_width18 = head_width18;
    }

    public String getHead_width19() {
        return head_width19;
    }

    public void setHead_width19(String head_width19) {
        this.head_width19 = head_width19;
    }

    public String getHead_width20() {
        return head_width20;
    }

    public void setHead_width20(String head_width20) {
        this.head_width20 = head_width20;
    }

    public String getHead_width21() {
        return head_width21;
    }

    public void setHead_width21(String head_width21) {
        this.head_width21 = head_width21;
    }

    public String getHead_width22() {
        return head_width22;
    }

    public void setHead_width22(String head_width22) {
        this.head_width22 = head_width22;
    }

    public String getHead_width23() {
        return head_width23;
    }

    public void setHead_width23(String head_width23) {
        this.head_width23 = head_width23;
    }

    public String getHead_width24() {
        return head_width24;
    }

    public void setHead_width24(String head_width24) {
        this.head_width24 = head_width24;
    }

    public String getHead_width25() {
        return head_width25;
    }

    public void setHead_width25(String head_width25) {
        this.head_width25 = head_width25;
    }

    public String getReport_delivery_type() {
        return report_delivery_type;
    }

    public void setReport_delivery_type(String report_delivery_type) {
        this.report_delivery_type = report_delivery_type;
    }

    public String getPortlet_id() {
        return portlet_id;
    }

    public void setPortlet_id(String portlet_id) {
        this.portlet_id = portlet_id;
    }

    public String getColor_code_format() {
        return color_code_format;
    }

    public void setColor_code_format(String color_code_format) {
        this.color_code_format = color_code_format;
    }

    public String getData_type1() {
        return data_type1;
    }

    public void setData_type1(String data_type1) {
        this.data_type1 = data_type1;
    }

    public String getData_type2() {
        return data_type2;
    }

    public void setData_type2(String data_type2) {
        this.data_type2 = data_type2;
    }

    public String getData_type3() {
        return data_type3;
    }

    public void setData_type3(String data_type3) {
        this.data_type3 = data_type3;
    }

    public String getData_type4() {
        return data_type4;
    }

    public void setData_type4(String data_type4) {
        this.data_type4 = data_type4;
    }

    public String getData_type5() {
        return data_type5;
    }

    public void setData_type5(String data_type5) {
        this.data_type5 = data_type5;
    }

    public String getData_type6() {
        return data_type6;
    }

    public void setData_type6(String data_type6) {
        this.data_type6 = data_type6;
    }

    public String getData_type7() {
        return data_type7;
    }

    public void setData_type7(String data_type7) {
        this.data_type7 = data_type7;
    }

    public String getData_type8() {
        return data_type8;
    }

    public void setData_type8(String data_type8) {
        this.data_type8 = data_type8;
    }

    public String getData_type9() {
        return data_type9;
    }

    public void setData_type9(String data_type9) {
        this.data_type9 = data_type9;
    }

    public String getData_type10() {
        return data_type10;
    }

    public void setData_type10(String data_type10) {
        this.data_type10 = data_type10;
    }

    public String getData_type11() {
        return data_type11;
    }

    public void setData_type11(String data_type11) {
        this.data_type11 = data_type11;
    }

    public String getData_type12() {
        return data_type12;
    }

    public void setData_type12(String data_type12) {
        this.data_type12 = data_type12;
    }

    public String getData_type13() {
        return data_type13;
    }

    public void setData_type13(String data_type13) {
        this.data_type13 = data_type13;
    }

    public String getData_type14() {
        return data_type14;
    }

    public void setData_type14(String data_type14) {
        this.data_type14 = data_type14;
    }

    public String getData_type15() {
        return data_type15;
    }

    public void setData_type15(String data_type15) {
        this.data_type15 = data_type15;
    }

    public String getData_type16() {
        return data_type16;
    }

    public void setData_type16(String data_type16) {
        this.data_type16 = data_type16;
    }

    public String getData_type17() {
        return data_type17;
    }

    public void setData_type17(String data_type17) {
        this.data_type17 = data_type17;
    }

    public String getData_type18() {
        return data_type18;
    }

    public void setData_type18(String data_type18) {
        this.data_type18 = data_type18;
    }

    public String getData_type19() {
        return data_type19;
    }

    public void setData_type19(String data_type19) {
        this.data_type19 = data_type19;
    }

    public String getData_type20() {
        return data_type20;
    }

    public void setData_type20(String data_type20) {
        this.data_type20 = data_type20;
    }

    public String getData_type21() {
        return data_type21;
    }

    public void setData_type21(String data_type21) {
        this.data_type21 = data_type21;
    }

    public String getData_type22() {
        return data_type22;
    }

    public void setData_type22(String data_type22) {
        this.data_type22 = data_type22;
    }

    public String getData_type23() {
        return data_type23;
    }

    public void setData_type23(String data_type23) {
        this.data_type23 = data_type23;
    }

    public String getData_type24() {
        return data_type24;
    }

    public void setData_type24(String data_type24) {
        this.data_type24 = data_type24;
    }

    public String getData_type25() {
        return data_type25;
    }

    public void setData_type25(String data_type25) {
        this.data_type25 = data_type25;
    }

    public String getFont_size() {
        return font_size;
    }

    public void setFont_size(String font_size) {
        this.font_size = font_size;
    }

    public String getRep_code() {
        return rep_code;
    }

    public void setRep_code(String rep_code) {
        this.rep_code = rep_code;
    }

    public String getSend_when_rec() {
        return send_when_rec;
    }

    public void setSend_when_rec(String send_when_rec) {
        this.send_when_rec = send_when_rec;
    }

    public String getEmail_sql_text() {
        return email_sql_text;
    }

    public void setEmail_sql_text(String email_sql_text) {
        this.email_sql_text = email_sql_text;
    }

    public String getEmail_sql_text_column() {
        return email_sql_text_column;
    }

    public void setEmail_sql_text_column(String email_sql_text_column) {
        this.email_sql_text_column = email_sql_text_column;
    }

    public String getSub_where_clause_replace_val1() {
        return sub_where_clause_replace_val1;
    }

    public void setSub_where_clause_replace_val1(String sub_where_clause_replace_val1) {
        this.sub_where_clause_replace_val1 = sub_where_clause_replace_val1;
    }

    public String getSub_where_clause_replace_val2() {
        return sub_where_clause_replace_val2;
    }

    public void setSub_where_clause_replace_val2(String sub_where_clause_replace_val2) {
        this.sub_where_clause_replace_val2 = sub_where_clause_replace_val2;
    }

    public String getSub_where_clause_replace_val3() {
        return sub_where_clause_replace_val3;
    }

    public void setSub_where_clause_replace_val3(String sub_where_clause_replace_val3) {
        this.sub_where_clause_replace_val3 = sub_where_clause_replace_val3;
    }

    public String getSub_where_clause_replace_val4() {
        return sub_where_clause_replace_val4;
    }

    public void setSub_where_clause_replace_val4(String sub_where_clause_replace_val4) {
        this.sub_where_clause_replace_val4 = sub_where_clause_replace_val4;
    }

    public String getSub_where_clause_replace_val5() {
        return sub_where_clause_replace_val5;
    }

    public void setSub_where_clause_replace_val5(String sub_where_clause_replace_val5) {
        this.sub_where_clause_replace_val5 = sub_where_clause_replace_val5;
    }

    public String getTo_email_sub_clause() {
        return to_email_sub_clause;
    }

    public void setTo_email_sub_clause(String to_email_sub_clause) {
        this.to_email_sub_clause = to_email_sub_clause;
    }

    public String getCc_email_sub_clause() {
        return cc_email_sub_clause;
    }

    public void setCc_email_sub_clause(String cc_email_sub_clause) {
        this.cc_email_sub_clause = cc_email_sub_clause;
    }

    public String getSub_where_clause_rep_val_col1() {
        return sub_where_clause_rep_val_col1;
    }

    public void setSub_where_clause_rep_val_col1(String sub_where_clause_rep_val_col1) {
        this.sub_where_clause_rep_val_col1 = sub_where_clause_rep_val_col1;
    }

    public String getSub_where_clause_rep_val_col2() {
        return sub_where_clause_rep_val_col2;
    }

    public void setSub_where_clause_rep_val_col2(String sub_where_clause_rep_val_col2) {
        this.sub_where_clause_rep_val_col2 = sub_where_clause_rep_val_col2;
    }

    public String getSub_where_clause_rep_val_col3() {
        return sub_where_clause_rep_val_col3;
    }

    public void setSub_where_clause_rep_val_col3(String sub_where_clause_rep_val_col3) {
        this.sub_where_clause_rep_val_col3 = sub_where_clause_rep_val_col3;
    }

    public String getSub_where_clause_rep_val_col4() {
        return sub_where_clause_rep_val_col4;
    }

    public void setSub_where_clause_rep_val_col4(String sub_where_clause_rep_val_col4) {
        this.sub_where_clause_rep_val_col4 = sub_where_clause_rep_val_col4;
    }

    public String getSub_where_clause_rep_val_col5() {
        return sub_where_clause_rep_val_col5;
    }

    public void setSub_where_clause_rep_val_col5(String sub_where_clause_rep_val_col5) {
        this.sub_where_clause_rep_val_col5 = sub_where_clause_rep_val_col5;
    }

    public String getTo_email_sql_id() {
        return to_email_sql_id;
    }

    public void setTo_email_sql_id(String to_email_sql_id) {
        this.to_email_sql_id = to_email_sql_id;
    }

    public String getTotal_column() {
        return total_column;
    }

    public void setTotal_column(String total_column) {
        this.total_column = total_column;
    }

    public String getReport_group() {
        return report_group;
    }

    public void setReport_group(String report_group) {
        this.report_group = report_group;
    }

    public String getReport_format() {
        return report_format;
    }

    public void setReport_format(String report_format) {
        this.report_format = report_format;
    }

    @Override
    public String toString() {
       Util utl=new Util();
       return utl.printObjectAsString(this);
    }

    
}//end class
