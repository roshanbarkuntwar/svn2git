/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import globalUtilities.Util;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author akash.dev
 */
@Entity
@Table(name = "tds_spl_rate_mast")
//@IdClass(TdsSplRateMastId.class)
public class TdsSplRateMast implements java.io.Serializable {

//    @Id
    @Column(name = "from_date", length = 7, nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date from_date;
    @Column(name = "to_date", length = 7, nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date to_date;
    @Column(name = "deductee_code", length = 22, nullable = true)
    private String deductee_code;
    @Id
    private String tds_code;
    @Column(name = "tds_rate", length = 22, nullable = false)
    private String tds_rate;
    @Column(name = "surcharge_rate", length = 22, nullable = true)
    private String surcharge_rate;
    @Column(name = "cess_rate", length = 22, nullable = true)
    private String cess_rate;
    @Column(name = "tds_limit_amt", length = 22, nullable = true)
    private String tds_limit_amt;
    @Id
    @Column(name = "certificate_no", length = 50, nullable = false)
    private String certificate_no;
    @Column(name = "certificate_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date certificate_date;
    @Column(name = "tds_tran_rowid_seq", length = 22, nullable = true)
    private String tds_tran_rowid_seq;
    @Column(name = "user_code", length = 8, nullable = false)
    private String user_code;
    @Column(name = "lastupdate", nullable = false, insertable = false, columnDefinition = "DATE default sysdate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastupdate;
    @Column(name = "flag", length = 1, nullable = true)
    private String flag;
    @Column(name = "certificate_valid_upto", length = 7, nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date certificate_valid_upto;
    @Column(name = "hecess_rate", length = 22, nullable = true)
    private String hecess_rate;
    @Column(name = "amount_consumed", length = 22, nullable = true)
    private String amount_consumed;
    @Id
    @Column(name = "client_code", length = 10, nullable = true)
    private String client_code;
    @Id
    @Column(name = "deductee_panno", length = 15, nullable = true)

    private String deductee_panno;
    @Id
    @Column(name = "entity_code", length = 2, nullable = true)
    private String entity_code;
    @Column(name = "acc_year", length = 5, nullable = true)
    private String acc_year;
    @Id
    @Column(name = "tds_type_code", length = 5, nullable = true)
    private String tds_type_code;
    @Transient
    @Column(name = "parent_code", length = 10, nullable = true)
    private String parent_code;
    @Transient
    @Column(name = "g_parent_code", length = 10, nullable = true)
    private String g_parent_code;
    @Transient
    @Column(name = "sg_parent_code", length = 10, nullable = true)
    private String sg_parent_code;
    @Transient
    @Column(name = "ssg_parent_code", length = 10, nullable = true)
    private String ssg_parent_code;
    @Transient
    @Column(name = "sssg_parent_code", length = 10, nullable = true)
    private String sssg_parent_code;

    public TdsSplRateMast() {
        this.user_code = "SHASHANK";
        this.to_date = new Date();
    }

    public TdsSplRateMast(Date from_date, Date to_date, String deductee_code, String tds_code, String tds_rate, String surcharge_rate, String cess_rate, String tds_limit_amt, String certificate_no, Date certificate_date, String tds_tran_rowid_seq, String user_code, Date lastupdate, String flag, Date certificate_valid_upto, String hecess_rate, String amount_consumed, String parent_code, String g_parent_code, String sg_parent_code, String ssg_parent_code, String sssg_parent_code) {
        this.from_date = from_date;
        this.to_date = to_date;
        this.deductee_code = deductee_code;
        this.tds_code = tds_code;
        this.tds_rate = tds_rate;
        this.surcharge_rate = surcharge_rate;
        this.cess_rate = cess_rate;
        this.tds_limit_amt = tds_limit_amt;
        this.certificate_no = certificate_no;
        this.certificate_date = certificate_date;
        this.tds_tran_rowid_seq = tds_tran_rowid_seq;
        this.user_code = user_code;
        this.lastupdate = lastupdate;
        this.flag = flag;
        this.certificate_valid_upto = certificate_valid_upto;
        this.hecess_rate = hecess_rate;
        this.amount_consumed = amount_consumed;
        this.parent_code = parent_code;
        this.g_parent_code = g_parent_code;
        this.sg_parent_code = sg_parent_code;
        this.ssg_parent_code = ssg_parent_code;
        this.sssg_parent_code = sssg_parent_code;
    }

    public String getHecess_rate() {
        return hecess_rate;
    }

    public void setHecess_rate(String hecess_rate) {
        this.hecess_rate = hecess_rate;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        Date d;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d = sdf.parse(from_date);
        } catch (Exception e) {
            d = null;
        }
        this.from_date = d;
    }
//    public void setFrom_date(String from_date) {
//        Date d;
//        try {
//            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//            SimpleDateFormat formatter_s = new SimpleDateFormat("dd-MMM-yyyy");
//
//            d = formatter.parse(from_date.trim());
//
//            String s_Date = formatter_s.format(d);
//            d = formatter_s.parse(s_Date);
//        } catch (Exception e) {
//            d = null;
//        }
//        this.from_date = d;
//    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    public String getDeductee_code() {
        return deductee_code;
    }

    public void setDeductee_code(String deductee_code) {
        this.deductee_code = deductee_code;
    }

    public String getTds_code() {
        return tds_code;
    }

    public void setTds_code(String tds_code) {
        this.tds_code = tds_code;
    }

    public String getTds_rate() {
        return tds_rate;
    }

    public void setTds_rate(String tds_rate) {
        this.tds_rate = tds_rate;
    }

    public String getSurcharge_rate() {
        return surcharge_rate;
    }

    public void setSurcharge_rate(String surcharge_rate) {
        this.surcharge_rate = surcharge_rate;
    }

    public String getCess_rate() {
        return cess_rate;
    }

    public void setCess_rate(String cess_rate) {
        this.cess_rate = cess_rate;
    }

    public String getTds_limit_amt() {
        return tds_limit_amt;
    }

    public void setTds_limit_amt(String tds_limit_amt) {
        this.tds_limit_amt = tds_limit_amt;
    }

    public String getCertificate_no() {
        return certificate_no;
    }

    public void setCertificate_no(String certificate_no) {
        if (certificate_no != null) {
            this.certificate_no = certificate_no.toUpperCase();
        } else {
            this.certificate_no = certificate_no;
        }
    }

    public Date getCertificate_date() {
        return certificate_date;
    }

    public void setCertificate_date(String certificate_date) {
        Date d;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d = sdf.parse(certificate_date.trim());
        } catch (Exception e) {
            d = null;
        }
        this.certificate_date = d;
    }
//    public void setCertificate_date(String certificate_date) {
//        Date d;
//        try {
//            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//            SimpleDateFormat formatter_s = new SimpleDateFormat("dd-MMM-yyyy");
//
//            d = formatter.parse(certificate_date.trim());
//
//            String s_Date = formatter_s.format(d);
//            d = formatter_s.parse(s_Date);
//
//        } catch (Exception e) {
//            d = null;
//        }
//        this.certificate_date = d;
//    }

    public String getTds_tran_rowid_seq() {
        return tds_tran_rowid_seq;
    }

    public void setTds_tran_rowid_seq(String tds_tran_rowid_seq) {
        this.tds_tran_rowid_seq = tds_tran_rowid_seq;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Date getCertificate_valid_upto() {
        return certificate_valid_upto;
    }

    public String getAmount_consumed() {
        return amount_consumed;
    }

    public String getParent_code() {
        return parent_code;
    }

    public void setParent_code(String parent_code) {
        this.parent_code = parent_code;
    }

    public String getG_parent_code() {
        return g_parent_code;
    }

    public void setG_parent_code(String g_parent_code) {
        this.g_parent_code = g_parent_code;
    }

    public String getSg_parent_code() {
        return sg_parent_code;
    }

    public void setSg_parent_code(String sg_parent_code) {
        this.sg_parent_code = sg_parent_code;
    }

    public String getSsg_parent_code() {
        return ssg_parent_code;
    }

    public void setSsg_parent_code(String ssg_parent_code) {
        this.ssg_parent_code = ssg_parent_code;
    }

    public String getSssg_parent_code() {
        return sssg_parent_code;
    }

    public void setSssg_parent_code(String sssg_parent_code) {
        this.sssg_parent_code = sssg_parent_code;
    }

    public void setAmount_consumed(String amount_consumed) {
        this.amount_consumed = amount_consumed;
    }

//    public void setCertificate_valid_upto(String certificate_valid_upto) {
//        Date d;
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
//            d = sdf.parse(certificate_valid_upto.trim());
//        } catch (Exception e) {
//            d = null;
//        }
//        this.certificate_valid_upto = d;
//    }
    public void setCertificate_valid_upto(String certificate_valid_upto) {
        Date d;
        try {
//            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//            SimpleDateFormat formatter_s = new SimpleDateFormat("dd-MMM-yyyy");

            d = formatter.parse(certificate_valid_upto.trim());

//            String s_Date = formatter_s.format(d);
//            d = formatter_s.parse(s_Date);
        } catch (Exception e) {
            d = null;
        }
        this.certificate_valid_upto = d;
    }

    @Override
    public String toString() {
        Util utl = new Util();
        return utl.printObjectAsString(this);
    }

    public String getClient_code() {
        return client_code;
    }

    public void setClient_code(String client_code) {
        this.client_code = client_code;
    }

    public String getDeductee_panno() {
        return deductee_panno;
    }

    public void setDeductee_panno(String deductee_panno) {
        this.deductee_panno = deductee_panno;
    }

    public String getEntity_code() {
        return entity_code;
    }

    public void setEntity_code(String entity_code) {
        this.entity_code = entity_code;
    }

    public String getAcc_year() {
        return acc_year;
    }

    public void setAcc_year(String acc_year) {
        this.acc_year = acc_year;
    }

    public String getTds_type_code() {
        return tds_type_code;
    }

    public void setTds_type_code(String tds_type_code) {
        this.tds_type_code = tds_type_code;
    }

}
