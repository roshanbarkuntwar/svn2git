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

/**
 *
 * @author akash.dev
 */
@Entity
@Table(name = "lhssys_template")
public class LhssysTemplate implements java.io.Serializable {

    @Column(name = "entity_code", nullable = true)
    private String entity_code;
    @Column(name = "client_code", nullable = true)
    private String client_code;
    @Column(name = "acc_year", nullable = true)
    private String acc_year;
    @Column(name = "assesment_acc_year", nullable = true)
    private String assesment_acc_year;
    @Column(name = "quarter_no", nullable = false)
    private String quarter_no;
    @Column(name = "from_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date from_date;
    @Column(name = "to_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date to_date;
    @Column(name = "tds_type_code", nullable = false)
    private String tds_type_code;
    @Column(name = "process_seqno", nullable = true)
    private String process_seqno;
    @Id
    @Column(name = "rowid_seq", nullable = true)
    private String rowid_seq;

    @Column(name = "col11", length = 4000, nullable = true)
    private String col11;
    @Column(name = "col12", length = 4000, nullable = true)
    private String col12;
    @Column(name = "col13", length = 4000, nullable = true)
    private String col13;
    @Column(name = "col14", length = 4000, nullable = true)
    private String col14;
    @Column(name = "col15", length = 4000, nullable = true)
    private String col15;
    @Column(name = "col16", length = 4000, nullable = true)
    private String col16;
    @Column(name = "col17", length = 4000, nullable = true)
    private String col17;
    @Column(name = "col18", length = 4000, nullable = true)
    private String col18;
    @Column(name = "col19", length = 4000, nullable = true)
    private String col19;
    @Column(name = "col20", length = 4000, nullable = true)
    private String col20;
    @Column(name = "col21", length = 4000, nullable = true)
    private String col21;
    @Column(name = "col22", length = 4000, nullable = true)
    private String col22;
    @Column(name = "col23", length = 4000, nullable = true)
    private String col23;
    @Column(name = "col24", length = 4000, nullable = true)
    private String col24;
    @Column(name = "col25", length = 4000, nullable = true)
    private String col25;
    @Column(name = "col26", length = 4000, nullable = true)
    private String col26;
    @Column(name = "col27", length = 4000, nullable = true)
    private String col27;
    @Column(name = "col28", length = 4000, nullable = true)
    private String col28;
    @Column(name = "col29", length = 4000, nullable = true)
    private String col29;
    @Column(name = "col30", length = 4000, nullable = true)
    private String col30;
    @Column(name = "col31", length = 4000, nullable = true)
    private String col31;
    @Column(name = "col32", length = 4000, nullable = true)
    private String col32;
    @Column(name = "col33", length = 4000, nullable = true)
    private String col33;
    @Column(name = "col34", length = 4000, nullable = true)
    private String col34;
    @Column(name = "col35", length = 4000, nullable = true)
    private String col35;
    @Column(name = "col36", length = 4000, nullable = true)
    private String col36;
    @Column(name = "col37", length = 4000, nullable = true)
    private String col37;
    @Column(name = "col38", length = 4000, nullable = true)
    private String col38;
    @Column(name = "col39", length = 4000, nullable = true)
    private String col39;
    @Column(name = "col40", length = 4000, nullable = true)
    private String col40;
    @Column(name = "col41", length = 4000, nullable = true)
    private String col41;
    @Column(name = "col42", length = 4000, nullable = true)
    private String col42;
    @Column(name = "col43", length = 4000, nullable = true)
    private String col43;
    @Column(name = "col44", length = 4000, nullable = true)
    private String col44;
    @Column(name = "col45", length = 4000, nullable = true)
    private String col45;
    @Column(name = "col46", length = 4000, nullable = true)
    private String col46;
    @Column(name = "col47", length = 4000, nullable = true)
    private String col47;
    @Column(name = "col48", length = 4000, nullable = true)
    private String col48;
    @Column(name = "col49", length = 4000, nullable = true)
    private String col49;
    @Column(name = "col50", length = 4000, nullable = true)
    private String col50;
    @Column(name = "col51", length = 4000, nullable = true)
    private String col51;
    @Column(name = "col52", length = 4000, nullable = true)
    private String col52;
    @Column(name = "col53", length = 4000, nullable = true)
    private String col53;
    @Column(name = "col54", length = 4000, nullable = true)
    private String col54;
    @Column(name = "col55", length = 4000, nullable = true)
    private String col55;
    @Column(name = "col56", length = 4000, nullable = true)
    private String col56;
    @Column(name = "col57", length = 4000, nullable = true)
    private String col57;
    @Column(name = "col58", length = 4000, nullable = true)
    private String col58;
    @Column(name = "col59", length = 4000, nullable = true)
    private String col59;
    @Column(name = "col60", length = 4000, nullable = true)
    private String col60;
    @Column(name = "col61", length = 4000, nullable = true)
    private String col61;
    @Column(name = "col62", length = 4000, nullable = true)
    private String col62;
    @Column(name = "col63", length = 4000, nullable = true)
    private String col63;
    @Column(name = "col64", length = 4000, nullable = true)
    private String col64;
    @Column(name = "col65", length = 4000, nullable = true)
    private String col65;
    @Column(name = "col66", length = 4000, nullable = true)
    private String col66;
    @Column(name = "col67", length = 4000, nullable = true)
    private String col67;
    @Column(name = "col68", length = 4000, nullable = true)
    private String col68;
    @Column(name = "col69", length = 4000, nullable = true)
    private String col69;
    @Column(name = "col70", length = 4000, nullable = true)
    private String col70;
    @Column(name = "col71", length = 4000, nullable = true)
    private String col71;
    @Column(name = "col72", length = 4000, nullable = true)
    private String col72;
    @Column(name = "col73", length = 4000, nullable = true)
    private String col73;
    @Column(name = "col74", length = 4000, nullable = true)
    private String col74;
    @Column(name = "col75", length = 4000, nullable = true)
    private String col75;
    @Column(name = "col76", length = 4000, nullable = true)
    private String col76;
    @Column(name = "col77", length = 4000, nullable = true)
    private String col77;
    @Column(name = "col78", length = 4000, nullable = true)
    private String col78;
    @Column(name = "col79", length = 4000, nullable = true)
    private String col79;
    @Column(name = "col80", length = 4000, nullable = true)
    private String col80;
    @Column(name = "col81", length = 4000, nullable = true)
    private String col81;
    @Column(name = "col82", length = 4000, nullable = true)
    private String col82;
    @Column(name = "col83", length = 4000, nullable = true)
    private String col83;
    @Column(name = "col84", length = 4000, nullable = true)
    private String col84;
    @Column(name = "col85", length = 4000, nullable = true)
    private String col85;
    @Column(name = "col86", length = 4000, nullable = true)
    private String col86;
    @Column(name = "col87", length = 4000, nullable = true)
    private String col87;
    @Column(name = "col88", length = 4000, nullable = true)
    private String col88;
    @Column(name = "col89", length = 4000, nullable = true)
    private String col89;
    @Column(name = "col90", length = 4000, nullable = true)
    private String col90;
    @Column(name = "col91", length = 4000, nullable = true)
    private String col91;
    @Column(name = "col92", length = 4000, nullable = true)
    private String col92;
    @Column(name = "col93", length = 4000, nullable = true)
    private String col93;
    @Column(name = "col94", length = 4000, nullable = true)
    private String col94;
    @Column(name = "col95", length = 4000, nullable = true)
    private String col95;
    @Column(name = "col96", length = 4000, nullable = true)
    private String col96;
    @Column(name = "col97", length = 4000, nullable = true)
    private String col97;
    @Column(name = "col98", length = 4000, nullable = true)
    private String col98;
    @Column(name = "col99", length = 4000, nullable = true)
    private String col99;
    @Id
    @Column(name = "col100", length = 4000, nullable = true)
    private String col100;
    @Column(name = "template_code", length = 4000, nullable = true)
    private String template_code;
    @Column(name = "user_code", length = 4000, nullable = true)
    private String user_code;

    public LhssysTemplate() {

    }//end constructor

//    public LhssysTemplate(String entity_code, String client_code, String acc_year, String assesment_acc_year, String quarter_no, Date from_date, Date to_date, String tds_type_code, String process_seqno, String rowid_seq, String col11, String col12, String col13, String col14, String col15, String col16, String col17, String col18, String col19, String col20, String col21, String col22, String col23, String col24, String col25, String col26, String col27, String col28, String col29, String col30, String col31, String col32, String col33, String col34, String col35, String col36, String col37, String col38, String col39, String col40, String col41, String col42, String col43, String col44, String col45, String col46, String col47, String col48, String col49, String col50, String col51, String col52, String col53, String col54, String col55, String col56, String col57, String col58, String col59, String col60, String col61, String col62, String col63, String col64, String col65, String col66, String col67, String col68, String col69, String col70, String col71, String col72, String col73, String col74, String col75, String col76, String col77, String col78, String col79, String col80, String col81, String col82, String col83, String col84, String col85, String col86, String col87, String col88, String col89, String col90, String col91, String col92, String col93, String col94, String col95, String col96, String col97, String col98, String col99, String col100) {
//        this.entity_code = entity_code;
//        this.client_code = client_code;
//        this.acc_year = acc_year;
//        this.assesment_acc_year = assesment_acc_year;
//        this.quarter_no = quarter_no;
//        this.from_date = from_date;
//        this.to_date = to_date;
//        this.tds_type_code = tds_type_code;
//        this.process_seqno = process_seqno;
//        this.rowid_seq = rowid_seq;
//        this.col11 = col11;
//        this.col12 = col12;
//        this.col13 = col13;
//        this.col14 = col14;
//        this.col15 = col15;
//        this.col16 = col16;
//        this.col17 = col17;
//        this.col18 = col18;
//        this.col19 = col19;
//        this.col20 = col20;
//        this.col21 = col21;
//        this.col22 = col22;
//        this.col23 = col23;
//        this.col24 = col24;
//        this.col25 = col25;
//        this.col26 = col26;
//        this.col27 = col27;
//        this.col28 = col28;
//        this.col29 = col29;
//        this.col30 = col30;
//        this.col31 = col31;
//        this.col32 = col32;
//        this.col33 = col33;
//        this.col34 = col34;
//        this.col35 = col35;
//        this.col36 = col36;
//        this.col37 = col37;
//        this.col38 = col38;
//        this.col39 = col39;
//        this.col40 = col40;
//        this.col41 = col41;
//        this.col42 = col42;
//        this.col43 = col43;
//        this.col44 = col44;
//        this.col45 = col45;
//        this.col46 = col46;
//        this.col47 = col47;
//        this.col48 = col48;
//        this.col49 = col49;
//        this.col50 = col50;
//        this.col51 = col51;
//        this.col52 = col52;
//        this.col53 = col53;
//        this.col54 = col54;
//        this.col55 = col55;
//        this.col56 = col56;
//        this.col57 = col57;
//        this.col58 = col58;
//        this.col59 = col59;
//        this.col60 = col60;
//        this.col61 = col61;
//        this.col62 = col62;
//        this.col63 = col63;
//        this.col64 = col64;
//        this.col65 = col65;
//        this.col66 = col66;
//        this.col67 = col67;
//        this.col68 = col68;
//        this.col69 = col69;
//        this.col70 = col70;
//        this.col71 = col71;
//        this.col72 = col72;
//        this.col73 = col73;
//        this.col74 = col74;
//        this.col75 = col75;
//        this.col76 = col76;
//        this.col77 = col77;
//        this.col78 = col78;
//        this.col79 = col79;
//        this.col80 = col80;
//        this.col81 = col81;
//        this.col82 = col82;
//        this.col83 = col83;
//        this.col84 = col84;
//        this.col85 = col85;
//        this.col86 = col86;
//        this.col87 = col87;
//        this.col88 = col88;
//        this.col89 = col89;
//        this.col90 = col90;
//        this.col91 = col91;
//        this.col92 = col92;
//        this.col93 = col93;
//        this.col94 = col94;
//        this.col95 = col95;
//        this.col96 = col96;
//        this.col97 = col97;
//        this.col98 = col98;
//        this.col99 = col99;
//        this.col100 = col100;
//    }
    public String getEntity_code() {
        return entity_code;
    }

    public void setEntity_code(String entity_code) {
        this.entity_code = entity_code;
    }

    public String getClient_code() {
        return client_code;
    }

    public void setClient_code(String client_code) {
        this.client_code = client_code;
    }

    public String getAcc_year() {
        return acc_year;
    }

    public void setAcc_year(String acc_year) {
        this.acc_year = acc_year;
    }

    public String getAssesment_acc_year() {
        return assesment_acc_year;
    }

    public void setAssesment_acc_year(String assesment_acc_year) {
        this.assesment_acc_year = assesment_acc_year;
    }

    public String getQuarter_no() {
        return quarter_no;
    }

    public void setQuarter_no(String quarter_no) {
        this.quarter_no = quarter_no;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    public String getTds_type_code() {
        return tds_type_code;
    }

    public void setTds_type_code(String tds_type_code) {
        this.tds_type_code = tds_type_code;
    }

    public String getProcess_seqno() {
        return process_seqno;
    }

    public void setProcess_seqno(String process_seqno) {
        this.process_seqno = process_seqno;
    }

    public String getRowid_seq() {
        return rowid_seq;
    }

    public void setRowid_seq(String rowid_seq) {
        this.rowid_seq = rowid_seq;
    }

    public String getCol11() {
        return col11;
    }

    public void setCol11(String col11) {
        this.col11 = col11;
    }

    public String getCol12() {
        return col12;
    }

    public void setCol12(String col12) {
        this.col12 = col12;
    }

    public String getCol13() {
        return col13;
    }

    public void setCol13(String col13) {
        this.col13 = col13;
    }

    public String getCol14() {
        return col14;
    }

    public void setCol14(String col14) {
        this.col14 = col14;
    }

    public String getCol15() {
        return col15;
    }

    public void setCol15(String col15) {
        this.col15 = col15;
    }

    public String getCol16() {
        return col16;
    }

    public void setCol16(String col16) {
        this.col16 = col16;
    }

    public String getCol17() {
        return col17;
    }

    public void setCol17(String col17) {
        this.col17 = col17;
    }

    public String getCol18() {
        return col18;
    }

    public void setCol18(String col18) {
        this.col18 = col18;
    }

    public String getCol19() {
        return col19;
    }

    public void setCol19(String col19) {
        this.col19 = col19;
    }

    public String getCol20() {
        return col20;
    }

    public void setCol20(String col20) {
        this.col20 = col20;
    }

    public String getCol21() {
        return col21;
    }

    public void setCol21(String col21) {
        this.col21 = col21;
    }

    public String getCol22() {
        return col22;
    }

    public void setCol22(String col22) {
        this.col22 = col22;
    }

    public String getCol23() {
        return col23;
    }

    public void setCol23(String col23) {
        this.col23 = col23;
    }

    public String getCol24() {
        return col24;
    }

    public void setCol24(String col24) {
        this.col24 = col24;
    }

    public String getCol25() {
        return col25;
    }

    public void setCol25(String col25) {
        this.col25 = col25;
    }

    public String getCol26() {
        return col26;
    }

    public void setCol26(String col26) {
        this.col26 = col26;
    }

    public String getCol27() {
        return col27;
    }

    public void setCol27(String col27) {
        this.col27 = col27;
    }

    public String getCol28() {
        return col28;
    }

    public void setCol28(String col28) {
        this.col28 = col28;
    }

    public String getCol29() {
        return col29;
    }

    public void setCol29(String col29) {
        this.col29 = col29;
    }

    public String getCol30() {
        return col30;
    }

    public void setCol30(String col30) {
        this.col30 = col30;
    }

    public String getCol31() {
        return col31;
    }

    public void setCol31(String col31) {
        this.col31 = col31;
    }

    public String getCol32() {
        return col32;
    }

    public void setCol32(String col32) {
        this.col32 = col32;
    }

    public String getCol33() {
        return col33;
    }

    public void setCol33(String col33) {
        this.col33 = col33;
    }

    public String getCol34() {
        return col34;
    }

    public void setCol34(String col34) {
        this.col34 = col34;
    }

    public String getCol35() {
        return col35;
    }

    public void setCol35(String col35) {
        this.col35 = col35;
    }

    public String getCol36() {
        return col36;
    }

    public void setCol36(String col36) {
        this.col36 = col36;
    }

    public String getCol37() {
        return col37;
    }

    public void setCol37(String col37) {
        this.col37 = col37;
    }

    public String getCol38() {
        return col38;
    }

    public void setCol38(String col38) {
        this.col38 = col38;
    }

    public String getCol39() {
        return col39;
    }

    public void setCol39(String col39) {
        this.col39 = col39;
    }

    public String getCol40() {
        return col40;
    }

    public void setCol40(String col40) {
        this.col40 = col40;
    }

    public String getCol41() {
        return col41;
    }

    public void setCol41(String col41) {
        this.col41 = col41;
    }

    public String getCol42() {
        return col42;
    }

    public void setCol42(String col42) {
        this.col42 = col42;
    }

    public String getCol43() {
        return col43;
    }

    public void setCol43(String col43) {
        this.col43 = col43;
    }

    public String getCol44() {
        return col44;
    }

    public void setCol44(String col44) {
        this.col44 = col44;
    }

    public String getCol45() {
        return col45;
    }

    public void setCol45(String col45) {
        this.col45 = col45;
    }

    public String getCol46() {
        return col46;
    }

    public void setCol46(String col46) {
        this.col46 = col46;
    }

    public String getCol47() {
        return col47;
    }

    public void setCol47(String col47) {
        this.col47 = col47;
    }

    public String getCol48() {
        return col48;
    }

    public void setCol48(String col48) {
        this.col48 = col48;
    }

    public String getCol49() {
        return col49;
    }

    public void setCol49(String col49) {
        this.col49 = col49;
    }

    public String getCol50() {
        return col50;
    }

    public void setCol50(String col50) {
        this.col50 = col50;
    }

    public String getCol51() {
        return col51;
    }

    public void setCol51(String col51) {
        this.col51 = col51;
    }

    public String getCol52() {
        return col52;
    }

    public void setCol52(String col52) {
        this.col52 = col52;
    }

    public String getCol53() {
        return col53;
    }

    public void setCol53(String col53) {
        this.col53 = col53;
    }

    public String getCol54() {
        return col54;
    }

    public void setCol54(String col54) {
        this.col54 = col54;
    }

    public String getCol55() {
        return col55;
    }

    public void setCol55(String col55) {
        this.col55 = col55;
    }

    public String getCol56() {
        return col56;
    }

    public void setCol56(String col56) {
        this.col56 = col56;
    }

    public String getCol57() {
        return col57;
    }

    public void setCol57(String col57) {
        this.col57 = col57;
    }

    public String getCol58() {
        return col58;
    }

    public void setCol58(String col58) {
        this.col58 = col58;
    }

    public String getCol59() {
        return col59;
    }

    public void setCol59(String col59) {
        this.col59 = col59;
    }

    public String getCol60() {
        return col60;
    }

    public void setCol60(String col60) {
        this.col60 = col60;
    }

    public String getCol61() {
        return col61;
    }

    public void setCol61(String col61) {
        this.col61 = col61;
    }

    public String getCol62() {
        return col62;
    }

    public void setCol62(String col62) {
        this.col62 = col62;
    }

    public String getCol63() {
        return col63;
    }

    public void setCol63(String col63) {
        this.col63 = col63;
    }

    public String getCol64() {
        return col64;
    }

    public void setCol64(String col64) {
        this.col64 = col64;
    }

    public String getCol65() {
        return col65;
    }

    public void setCol65(String col65) {
        this.col65 = col65;
    }

    public String getCol66() {
        return col66;
    }

    public void setCol66(String col66) {
        this.col66 = col66;
    }

    public String getCol67() {
        return col67;
    }

    public void setCol67(String col67) {
        this.col67 = col67;
    }

    public String getCol68() {
        return col68;
    }

    public void setCol68(String col68) {
        this.col68 = col68;
    }

    public String getCol69() {
        return col69;
    }

    public void setCol69(String col69) {
        this.col69 = col69;
    }

    public String getCol70() {
        return col70;
    }

    public void setCol70(String col70) {
        this.col70 = col70;
    }

    public String getCol71() {
        return col71;
    }

    public void setCol71(String col71) {
        this.col71 = col71;
    }

    public String getCol72() {
        return col72;
    }

    public void setCol72(String col72) {
        this.col72 = col72;
    }

    public String getCol73() {
        return col73;
    }

    public void setCol73(String col73) {
        this.col73 = col73;
    }

    public String getCol74() {
        return col74;
    }

    public void setCol74(String col74) {
        this.col74 = col74;
    }

    public String getCol75() {
        return col75;
    }

    public void setCol75(String col75) {
        this.col75 = col75;
    }

    public String getCol76() {
        return col76;
    }

    public void setCol76(String col76) {
        this.col76 = col76;
    }

    public String getCol77() {
        return col77;
    }

    public void setCol77(String col77) {
        this.col77 = col77;
    }

    public String getCol78() {
        return col78;
    }

    public void setCol78(String col78) {
        this.col78 = col78;
    }

    public String getCol79() {
        return col79;
    }

    public void setCol79(String col79) {
        this.col79 = col79;
    }

    public String getCol80() {
        return col80;
    }

    public void setCol80(String col80) {
        this.col80 = col80;
    }

    public String getCol81() {
        return col81;
    }

    public void setCol81(String col81) {
        this.col81 = col81;
    }

    public String getCol82() {
        return col82;
    }

    public void setCol82(String col82) {
        this.col82 = col82;
    }

    public String getCol83() {
        return col83;
    }

    public void setCol83(String col83) {
        this.col83 = col83;
    }

    public String getCol84() {
        return col84;
    }

    public void setCol84(String col84) {
        this.col84 = col84;
    }

    public String getCol85() {
        return col85;
    }

    public void setCol85(String col85) {
        this.col85 = col85;
    }

    public String getCol86() {
        return col86;
    }

    public void setCol86(String col86) {
        this.col86 = col86;
    }

    public String getCol87() {
        return col87;
    }

    public void setCol87(String col87) {
        this.col87 = col87;
    }

    public String getCol88() {
        return col88;
    }

    public void setCol88(String col88) {
        this.col88 = col88;
    }

    public String getCol89() {
        return col89;
    }

    public void setCol89(String col89) {
        this.col89 = col89;
    }

    public String getCol90() {
        return col90;
    }

    public void setCol90(String col90) {
        this.col90 = col90;
    }

    public String getCol91() {
        return col91;
    }

    public void setCol91(String col91) {
        this.col91 = col91;
    }

    public String getCol92() {
        return col92;
    }

    public void setCol92(String col92) {
        this.col92 = col92;
    }

    public String getCol93() {
        return col93;
    }

    public void setCol93(String col93) {
        this.col93 = col93;
    }

    public String getCol94() {
        return col94;
    }

    public void setCol94(String col94) {
        this.col94 = col94;
    }

    public String getCol95() {
        return col95;
    }

    public void setCol95(String col95) {
        this.col95 = col95;
    }

    public String getCol96() {
        return col96;
    }

    public void setCol96(String col96) {
        this.col96 = col96;
    }

    public String getCol97() {
        return col97;
    }

    public void setCol97(String col97) {
        this.col97 = col97;
    }

    public String getCol98() {
        return col98;
    }

    public void setCol98(String col98) {
        this.col98 = col98;
    }

    public String getCol99() {
        return col99;
    }

    public void setCol99(String col99) {
        this.col99 = col99;
    }

    public String getCol100() {
        return col100;
    }

    public void setCol100(String col100) {
        this.col100 = col100;
    }

    public String getTemplate_code() {
        return template_code;
    }

    public void setTemplate_code(String template_code) {
        this.template_code = template_code;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

}//end class
