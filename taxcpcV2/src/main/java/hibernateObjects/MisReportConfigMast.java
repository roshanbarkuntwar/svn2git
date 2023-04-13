/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gaurav.khanzode
 */
@Entity
@Table(name = "mis_report_config_mast")
public class MisReportConfigMast {

    @Column(name = "entity_code", length = 2)
    private String entity_code;
    @Id
    @Column(name = "rep_seq_id")
    private Long rep_seq_id;
    @Column(name = "rep_desc", length = 50)
    private String rep_desc;
    @Column(name = "rep_group", length = 50)
    private String rep_group;
    @Column(name = "rep_total_column")
    private String rep_total_column;
    @Column(name = "rep_pl_sql_text", length = 4000)
    private String rep_pl_sql_text;
    @Column(name = "rep_sql_text", length = 4000)
    private String rep_sql_text;
    @Column(name = "no_of_column")
    private String no_of_column;
    @Column(name = "rep_format")
    private String rep_format;
    @Column(name = "rep_filter_req_flag")
    private String rep_filter_req_flag;
    @Column(name = "rep_assesm_fltr_flag")
    private String rep_assesm_fltr_flag;
    @Column(name = "rep_custom_filter_flag")
    private String rep_custom_filter_flag;
    @Column(name = "rep_status")
    private String rep_status;
    @Column(name = "rep_orderby")
    private Long rep_orderby;
    @Column(name = "rep_req_dwnl_flag")
    private String rep_req_dwnl_flag;
    @Column(name = "email_to")
    private String email_to;
    @Column(name = "email_cc")
    private String email_cc;
    @Column(name = "email_bcc")
    private String email_bcc;
    @Column(name = "email_subject")
    private String email_subject;
    @Column(name = "email_body_text")
    private String email_body_text;
    @Column(name = "email_color_code_format")
    private String email_color_code_format;
    @Column(name = "jar_file")
    private String jar_file;
    @Column(name = "batch_file")
    private String batch_file;
    @Column(name = "heading1")
    private String heading1;
    @Column(name = "heading2")
    private String heading2;
    @Column(name = "heading3")
    private String heading3;
    @Column(name = "heading4")
    private String heading4;
    @Column(name = "heading5")
    private String heading5;
    @Column(name = "heading6")
    private String heading6;
    @Column(name = "heading7")
    private String heading7;
    @Column(name = "heading8")
    private String heading8;
    @Column(name = "heading9")
    private String heading9;
    @Column(name = "heading10")
    private String heading10;
    @Column(name = "heading11")
    private String heading11;
    @Column(name = "heading12")
    private String heading12;
    @Column(name = "heading13")
    private String heading13;
    @Column(name = "heading14")
    private String heading14;
    @Column(name = "heading15")
    private String heading15;
    @Column(name = "heading16")
    private String heading16;
    @Column(name = "heading17")
    private String heading17;
    @Column(name = "heading18")
    private String heading18;
    @Column(name = "heading19")
    private String heading19;
    @Column(name = "heading20")
    private String heading20;
    @Column(name = "heading21")
    private String heading21;
    @Column(name = "heading22")
    private String heading22;
    @Column(name = "heading23")
    private String heading23;
    @Column(name = "heading24")
    private String heading24;
    @Column(name = "heading25")
    private String heading25;
    @Column(name = "head_width1")
    private String head_width1;
    @Column(name = "head_width2")
    private String head_width2;
    @Column(name = "head_width3")
    private String head_width3;
    @Column(name = "head_width4")
    private String head_width4;
    @Column(name = "head_width5")
    private String head_width5;
    @Column(name = "head_width6")
    private String head_width6;
    @Column(name = "head_width7")
    private String head_width7;
    @Column(name = "head_width8")
    private String head_width8;
    @Column(name = "head_width9")
    private String head_width9;
    @Column(name = "head_width10")
    private String head_width10;
    @Column(name = "head_width11")
    private String head_width11;
    @Column(name = "head_width12")
    private String head_width12;
    @Column(name = "head_width13")
    private String head_width13;
    @Column(name = "head_width14")
    private String head_width14;
    @Column(name = "head_width15")
    private String head_width15;
    @Column(name = "head_width16")
    private String head_width16;
    @Column(name = "head_width17")
    private String head_width17;
    @Column(name = "head_width18")
    private String head_width18;
    @Column(name = "head_width19")
    private String head_width19;
    @Column(name = "head_width20")
    private String head_width20;
    @Column(name = "head_width21")
    private String head_width21;
    @Column(name = "head_width22")
    private String head_width22;
    @Column(name = "head_width23")
    private String head_width23;
    @Column(name = "head_width24")
    private String head_width24;
    @Column(name = "head_width25")
    private String head_width25;
    @Column(name = "data_type1")
    private String data_type1;
    @Column(name = "data_type2")
    private String data_type2;
    @Column(name = "data_type3")
    private String data_type3;
    @Column(name = "data_type4")
    private String data_type4;
    @Column(name = "data_type5")
    private String data_type5;
    @Column(name = "data_type6")
    private String data_type6;
    @Column(name = "data_type7")
    private String data_type7;
    @Column(name = "data_type8")
    private String data_type8;
    @Column(name = "data_type9")
    private String data_type9;
    @Column(name = "data_type10")
    private String data_type10;
    @Column(name = "data_type11")
    private String data_type11;
    @Column(name = "data_type12")
    private String data_type12;
    @Column(name = "data_type13")
    private String data_type13;
    @Column(name = "data_type14")
    private String data_type14;
    @Column(name = "data_type15")
    private String data_type15;
    @Column(name = "data_type16")
    private String data_type16;
    @Column(name = "data_type17")
    private String data_type17;
    @Column(name = "data_type18")
    private String data_type18;
    @Column(name = "data_type19")
    private String data_type19;
    @Column(name = "data_type20")
    private String data_type20;
    @Column(name = "data_type21")
    private String data_type21;
    @Column(name = "data_type22")
    private String data_type22;
    @Column(name = "data_type23")
    private String data_type23;
    @Column(name = "data_type24")
    private String data_type24;
    @Column(name = "data_type25")
    private String data_type25;
    @Column(name = "font_size")
    private String font_size;
    @Column(name = "lastupdate")
    @Temporal(TemporalType.DATE)
    private Date lastupdate;
    @Column(name = "user_code")
    private String user_code;
    @Column(name = "flag")
    private String flag;
    @Column(name = "rep_pagination_flag", length = 1)
    private String rep_pagination_flag;
    @Column(name = "rep_dashboard_req_flag", length = 1)
    private String rep_dashboard_req_flag;
    @Column(name = "entity_code_str", length = 500)
    private String entity_code_str;
    @Column(name = "rep_upload_flag", length = 1)
    private String rep_upload_flag;
    @Column(name = "rep_upload_dir", length = 1)
    private String rep_upload_dir;
    @Column(name = "rep_upload_template_code", length = 5)
    private String rep_upload_template_code;
    @Column(name = "rep_app_type", length = 5)
    private String rep_app_type;

    public MisReportConfigMast() {
    }

    public String getRep_dashboard_req_flag() {
        return rep_dashboard_req_flag;
    }

    public void setRep_dashboard_req_flag(String rep_dashboard_req) {
        this.rep_dashboard_req_flag = rep_dashboard_req_flag;
    }

    public String getEntity_code() {
        return entity_code;
    }

    public void setEntity_code(String entity_code) {
        this.entity_code = entity_code;
    }

    public Long getRep_seq_id() {
        return rep_seq_id;
    }

    public void setRep_seq_id(Long rep_seq_id) {
        this.rep_seq_id = rep_seq_id;
    }

    public String getRep_desc() {
        return rep_desc;
    }

    public void setRep_desc(String rep_desc) {
        this.rep_desc = rep_desc;
    }

    public String getRep_group() {
        return rep_group;
    }

    public void setRep_group(String rep_group) {
        this.rep_group = rep_group;
    }

    public String getRep_total_column() {
        return rep_total_column;
    }

    public void setRep_total_column(String rep_total_column) {
        this.rep_total_column = rep_total_column;
    }

    public String getRep_pl_sql_text() {
        return rep_pl_sql_text;
    }

    public void setRep_pl_sql_text(String rep_pl_sql_text) {
        this.rep_pl_sql_text = rep_pl_sql_text;
    }

    public String getRep_sql_text() {
        return rep_sql_text;
    }

    public void setRep_sql_text(String rep_sql_text) {
        this.rep_sql_text = rep_sql_text;
    }

    public String getNo_of_column() {
        return no_of_column;
    }

    public void setNo_of_column(String no_of_column) {
        this.no_of_column = no_of_column;
    }

    public String getRep_format() {
        return rep_format;
    }

    public void setRep_format(String rep_format) {
        this.rep_format = rep_format;
    }

    public String getRep_filter_req_flag() {
        return rep_filter_req_flag;
    }

    public void setRep_filter_req_flag(String rep_filter_req_flag) {
        this.rep_filter_req_flag = rep_filter_req_flag;
    }

    public String getRep_assesm_fltr_flag() {
        return rep_assesm_fltr_flag;
    }

    public void setRep_assesm_fltr_flag(String rep_assesm_fltr_flag) {
        this.rep_assesm_fltr_flag = rep_assesm_fltr_flag;
    }

    public String getRep_custom_filter_flag() {
        return rep_custom_filter_flag;
    }

    public void setRep_custom_filter_flag(String rep_custom_filter_flag) {
        this.rep_custom_filter_flag = rep_custom_filter_flag;
    }

    public String getRep_status() {
        return rep_status;
    }

    public void setRep_status(String rep_status) {
        this.rep_status = rep_status;
    }

    public Long getRep_orderby() {
        return rep_orderby;
    }

    public void setRep_orderby(Long rep_orderby) {
        this.rep_orderby = rep_orderby;
    }

    public String getRep_req_dwnl_flag() {
        return rep_req_dwnl_flag;
    }

    public void setRep_req_dwnl_flag(String rep_req_dwnl_flag) {
        this.rep_req_dwnl_flag = rep_req_dwnl_flag;
    }

    public String getEmail_to() {
        return email_to;
    }

    public void setEmail_to(String email_to) {
        this.email_to = email_to;
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

    public String getEmail_subject() {
        return email_subject;
    }

    public void setEmail_subject(String email_subject) {
        this.email_subject = email_subject;
    }

    public String getEmail_body_text() {
        return email_body_text;
    }

    public void setEmail_body_text(String email_body_text) {
        this.email_body_text = email_body_text;
    }

    public String getEmail_color_code_format() {
        return email_color_code_format;
    }

    public void setEmail_color_code_format(String email_color_code_format) {
        this.email_color_code_format = email_color_code_format;
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

    public String getRep_pagination_flag() {
        return rep_pagination_flag;
    }

    public void setRep_pagination_flag(String rep_pagination_flag) {
        this.rep_pagination_flag = rep_pagination_flag;
    }

    public String getEntity_code_str() {
        return entity_code_str;
    }

    public void setEntity_code_str(String entity_code_str) {
        this.entity_code_str = entity_code_str;
    }

    public String getRep_upload_flag() {
        return rep_upload_flag;
    }

    public void setRep_upload_flag(String rep_upload_flag) {
        this.rep_upload_flag = rep_upload_flag;
    }

    public String getRep_upload_dir() {
        return rep_upload_dir;
    }

    public void setRep_upload_dir(String rep_upload_dir) {
        this.rep_upload_dir = rep_upload_dir;
    }

    public String getRep_upload_template_code() {
        return rep_upload_template_code;
    }

    public void setRep_upload_template_code(String rep_upload_template_code) {
        this.rep_upload_template_code = rep_upload_template_code;
    }

    public String getRep_app_type() {
        return rep_app_type;
    }

    public void setRep_app_type(String rep_app_type) {
        this.rep_app_type = rep_app_type;
    }

}
