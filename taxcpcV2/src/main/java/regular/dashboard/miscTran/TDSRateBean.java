/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.miscTran;

/**
 *
 * @author gaurav.khanzode
 */
public class TDSRateBean {

    private String rowid_seq;
    private String bgl_code;
    private String deductee_panno;
    private String tran_date;
    private String certificate_no;
    private String rate_type;
    private String nature_of_rem;
    private String country_code;
    private String tds_section;
    private Double total_paid_amt;
    private Double payment_amt;

    public String getRowid_seq() {
        return rowid_seq;
    }

    public void setRowid_seq(String rowid_seq) {
        this.rowid_seq = rowid_seq;
    }

    public String getBgl_code() {
        return bgl_code;
    }

    public void setBgl_code(String bgl_code) {
        this.bgl_code = bgl_code;
    }

    public String getDeductee_panno() {
        return deductee_panno;
    }

    public void setDeductee_panno(String deductee_panno) {
        this.deductee_panno = deductee_panno;
    }

    public String getTran_date() {
        return tran_date;
    }

    public void setTran_date(String tran_date) {
        this.tran_date = tran_date;
    }

    public String getCertificate_no() {
        return certificate_no;
    }

    public void setCertificate_no(String certificate_no) {
        this.certificate_no = certificate_no;
    }

    public String getRate_type() {
        return rate_type;
    }

    public void setRate_type(String rate_type) {
        this.rate_type = rate_type;
    }

    public String getNature_of_rem() {
        return nature_of_rem;
    }

    public void setNature_of_rem(String nature_of_rem) {
        this.nature_of_rem = nature_of_rem;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getTds_section() {
        return tds_section;
    }

    public void setTds_section(String tds_section) {
        this.tds_section = tds_section;
    }

    public Double getTotal_paid_amt() {
        return total_paid_amt;
    }

    public void setTotal_paid_amt(Double total_paid_amt) {
        this.total_paid_amt = total_paid_amt;
    }

    public Double getPayment_amt() {
        return payment_amt;
    }

    public void setPayment_amt(Double payment_amt) {
        this.payment_amt = payment_amt;
    }

    @Override
    public String toString() {
        return "TDSRateBean{" + "rowid_seq=" + rowid_seq + ", bgl_code=" + bgl_code + ", deductee_panno=" + deductee_panno + ", tran_date=" + tran_date + ", certificate_no=" + certificate_no + ", rate_type=" + rate_type + ", nature_of_rem=" + nature_of_rem + ", country_code=" + country_code + ", tds_section=" + tds_section + ", total_paid_amt=" + total_paid_amt + ", payment_amt=" + payment_amt + '}';
    }

}
