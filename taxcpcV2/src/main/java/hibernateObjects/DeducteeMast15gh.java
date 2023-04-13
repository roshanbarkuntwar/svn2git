/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author aniket.naik
 */
@Entity
@Table(name = "deductee_mast_15gh")
public class DeducteeMast15gh implements java.io.Serializable {

    @Column(name = "rowid_seq", nullable = false)
    @GenericGenerator(name = "generator", strategy = "sequence-identity", parameters
            = @Parameter(name = "sequence", value = "deductee_code_15gh_seq"))
    @GeneratedValue(generator = "generator")
    @Id
    private Long rowid_seq;

    @Column(name = "entity_code", nullable = true, length = 2)
    private String entity_code;

    @Column(name = "client_code", nullable = true, length = 15)
    private String client_code;

    @Column(name = "acc_year", nullable = true, length = 5)
    private String acc_year;
    @Column(name = "assesment_acc_year", nullable = true, length = 5)
    private String assesment_acc_year;

    @Column(name = "quarter_no", nullable = true)
    private String quarter_no;
    @Column(name = "month", nullable = true, length = 5)
    private String month;
    @Column(name = "from_date", nullable = true, length = 11)
    private String from_date;
    @Column(name = "to_date", nullable = true, length = 11)
    private String to_date;

    @Column(name = "tds_type_code", nullable = true, length = 5)
    private String tds_type_code;

    @Column(name = "deductee_code", nullable = true, length = 15)
    private String deductee_code;
    @Column(name = "deductee_name", nullable = true, length = 100)
    private String deductee_name;
    @Column(name = "dob", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dob;
    @Column(name = "parent_code", length = 15, nullable = true)
    private String parent_code;
    @Column(name = "remark", length = 240, nullable = true)
    private String remark;
    @Column(name = "deductee_type_code", length = 5, nullable = true)
    private String deductee_type_code;
    @Column(name = "tds_code", length = 10, nullable = true)
    private String tds_code;
    @Column(name = "deductee_status", length = 1, nullable = true)
    private String deductee_status;
    @Column(name = "created_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date created_date;
    @Column(name = "closed_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date closed_date;
    @Column(name = "tds_tran_rowid_seq", nullable = true)
    private String tds_tran_rowid_seq;
    @Column(name = "website", length = 25, nullable = true)
    private String website;
    @Column(name = "add1", length = 50, nullable = true)
    private String add1;
    @Column(name = "add2", length = 50, nullable = true)
    private String add2;
    @Column(name = "add3", length = 50, nullable = true)
    private String add3;
    @Column(name = "add4", length = 50, nullable = true)
    private String add4;
    @Column(name = "city_code", length = 60, nullable = true)
    private String city_code;
    @Column(name = "state_code", length = 2, nullable = true)
    private String state_code;
    @Column(name = "pin", length = 6, nullable = true)
    private String pin;
    @Column(name = "stdcode", length = 6, nullable = true)
    private String stdcode;
    @Column(name = "phoneno", length = 100, nullable = true)
    private String phoneno;
    @Column(name = "mobileno", length = 10, nullable = true)
    private String mobileno;
    @Column(name = "email_id", length = 125, nullable = true)
    private String email_id;
    @Column(name = "panno", length = 10, nullable = true)
    private String panno;
    @Column(name = "tanno", length = 12, nullable = true)
    private String tanno;
    @Column(name = "income_status", nullable = true)
    private String income_status;
    @Column(name = "resident_status", length = 1, nullable = true)
    private String resident_status;
    @Column(name = "certficate_no", length = 50, nullable = true)
    private String certficate_no;
    @Column(name = "certficate_valid_upto", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date certficate_valid_upto;
    @Column(name = "identification_no", length = 50, nullable = true)
    private String identification_no;
    @Column(name = "company_name", length = 100, nullable = true)
    private String company_name;
    @Column(name = "country_code", length = 5, nullable = true)
    private String country_code;
    @Column(name = "is_transporter", nullable = true)
    private String is_transporter;
    @Column(name = "principle_of_business", length = 100, nullable = true)
    private String principle_of_business;
    @Column(name = "deduction_ref_no", length = 100, nullable = true)
    private String deduction_ref_no;
    @Column(name = "deductee_catg", length = 5, nullable = true)
    private String deductee_catg;
    @Column(name = "join_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date join_date;
    @Column(name = "left_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date left_date;
    @Column(name = "sex", length = 1, nullable = true)
    private String sex;
    @Column(name = "emp_code", length = 15, nullable = true)
    private String emp_code;
    @Column(name = "insert_flag", length = 1, nullable = true)
    private String insert_flag;
    @Column(name = "deductee_ref_code2", length = 100, nullable = true)
    private String deductee_ref_code2;
    @Column(name = "reference_no", length = 10, nullable = true)
    private String reference_no;
    @Column(name = "bflag", length = 1, nullable = true)
    private String bflag;
    @Column(name = "assessed_tax_flag", length = 1, nullable = true)
    private String assessed_tax_flag;
    @Column(name = "latest_assessment_year", length = 5, nullable = true)
    private String latest_assessment_year;
    @Column(name = "est_declaration_income", length = 14, nullable = true)
    private String est_declaration_income;
    @Column(name = "est_total_income", length = 15, nullable = true)
    private String est_total_income;
    @Column(name = "total_no_form", length = 14, nullable = true)
    private String total_no_form;
    @Column(name = "aggregate_income_amt", length = 14, nullable = true)
    private String aggregate_income_amt;
    @Column(name = "declaration_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date declaration_date;
    @Column(name = "income_amount_paid", nullable = true)
    private String income_amount_paid;
    @Column(name = "income_paid_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date income_paid_date;
    @Column(name = "deductee_panno_verified", length = 1, nullable = true)
    private String deductee_panno_verified;
    @Column(name = "deductee_panno_valid", length = 1, nullable = true)
    private String deductee_panno_valid;
    @Column(name = "name_as_panno", length = 500, nullable = true)
    private String name_as_panno;
    @Column(name = "user_code", length = 8, nullable = true)
    private String user_code;
    @Column(name = "lastupdate", nullable = true, insertable = true, columnDefinition = "DATE default sysdate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastupdate;
    @Column(name = "flag", length = 1, nullable = true)
    private String flag;
    @Column(name = "session_id", length = 10, nullable = true)
    private String session_id;
    @Column(name = "pan_status", length = 1, nullable = true)
    private String pan_status;

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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getTds_type_code() {
        return tds_type_code;
    }

    public void setTds_type_code(String tds_type_code) {
        this.tds_type_code = tds_type_code;
    }

    public String getDeductee_code() {
        return deductee_code;
    }

    public void setDeductee_code(String deductee_code) {
        this.deductee_code = deductee_code;
    }

    public String getDeductee_name() {
        return deductee_name;
    }

    public void setDeductee_name(String deductee_name) {
        this.deductee_name = deductee_name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(String dob) {
        Date d;
        try {
            //System.out.println("dob" + dob);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d = sdf.parse(dob);
        } catch (Exception e) {
            d = null;
        }
        this.dob = d;
    }

    public String getParent_code() {
        return parent_code;
    }

    public void setParent_code(String parent_code) {
        this.parent_code = parent_code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDeductee_type_code() {
        return deductee_type_code;
    }

    public void setDeductee_type_code(String deductee_type_code) {
        this.deductee_type_code = deductee_type_code;
    }

    public String getTds_code() {
        return tds_code;
    }

    public void setTds_code(String tds_code) {
        this.tds_code = tds_code;
    }

    public String getDeductee_status() {
        return deductee_status;
    }

    public void setDeductee_status(String deductee_status) {
        this.deductee_status = deductee_status;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getClosed_date() {
        return closed_date;
    }

    public void setClosed_date(Date closed_date) {
        this.closed_date = closed_date;
    }

    public String getTds_tran_rowid_seq() {
        return tds_tran_rowid_seq;
    }

    public void setTds_tran_rowid_seq(String tds_tran_rowid_seq) {
        this.tds_tran_rowid_seq = tds_tran_rowid_seq;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAdd1() {
        return add1;
    }

    public void setAdd1(String add1) {
        this.add1 = add1;
    }

    public String getAdd2() {
        return add2;
    }

    public void setAdd2(String add2) {
        this.add2 = add2;
    }

    public String getAdd3() {
        return add3;
    }

    public void setAdd3(String add3) {
        this.add3 = add3;
    }

    public String getAdd4() {
        return add4;
    }

    public void setAdd4(String add4) {
        this.add4 = add4;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getStdcode() {
        return stdcode;
    }

    public void setStdcode(String stdcode) {
        this.stdcode = stdcode;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getPanno() {
        return panno;
    }

    public void setPanno(String panno) {
        this.panno = panno;
    }

    public String getTanno() {
        return tanno;
    }

    public void setTanno(String tanno) {
        this.tanno = tanno;
    }

    public String getIncome_status() {
        return income_status;
    }

    public void setIncome_status(String income_status) {
        this.income_status = income_status;
    }

    public String getResident_status() {
        return resident_status;
    }

    public void setResident_status(String resident_status) {
        this.resident_status = resident_status;
    }

    public String getCertficate_no() {
        return certficate_no;
    }

    public void setCertficate_no(String certficate_no) {
        this.certficate_no = certficate_no;
    }

    public Date getCertficate_valid_upto() {
        return certficate_valid_upto;
    }

    public void setCertficate_valid_upto(Date certficate_valid_upto) {
        this.certficate_valid_upto = certficate_valid_upto;
    }

    public String getIdentification_no() {
        return identification_no;
    }

    public void setIdentification_no(String identification_no) {
        this.identification_no = identification_no;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getIs_transporter() {
        return is_transporter;
    }

    public void setIs_transporter(String is_transporter) {
        this.is_transporter = is_transporter;
    }

    public String getPrinciple_of_business() {
        return principle_of_business;
    }

    public void setPrinciple_of_business(String principle_of_business) {
        this.principle_of_business = principle_of_business;
    }

    public String getDeduction_ref_no() {
        return deduction_ref_no;
    }

    public void setDeduction_ref_no(String deduction_ref_no) {
        this.deduction_ref_no = deduction_ref_no;
    }

    public String getDeductee_catg() {
        return deductee_catg;
    }

    public void setDeductee_catg(String deductee_catg) {
        this.deductee_catg = deductee_catg;
    }

    public Date getJoin_date() {
        return join_date;
    }

    public void setJoin_date(String join_date) {
        Date d;
        try {
            //System.out.println("dob" + dob);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d = sdf.parse(join_date);
        } catch (Exception e) {
            d = null;
        }
        this.join_date = d;
    }

    public Date getLeft_date() {
        return left_date;
    }

    public void setLeft_date(String left_date) {
        Date d;
        try {
            //System.out.println("dob" + dob);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d = sdf.parse(left_date);
        } catch (Exception e) {
            d = null;
        }
        this.left_date = d;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmp_code() {
        return emp_code;
    }

    public void setEmp_code(String emp_code) {
        this.emp_code = emp_code;
    }

    public String getInsert_flag() {
        return insert_flag;
    }

    public void setInsert_flag(String insert_flag) {
        this.insert_flag = insert_flag;
    }

    public String getDeductee_ref_code2() {
        return deductee_ref_code2;
    }

    public void setDeductee_ref_code2(String deductee_ref_code2) {
        this.deductee_ref_code2 = deductee_ref_code2;
    }

    public String getReference_no() {
        return reference_no;
    }

    public void setReference_no(String reference_no) {
        this.reference_no = reference_no;
    }

    public String getBflag() {
        return bflag;
    }

    public void setBflag(String bflag) {
        this.bflag = bflag;
    }

    public String getAssessed_tax_flag() {
        return assessed_tax_flag;
    }

    public void setAssessed_tax_flag(String assessed_tax_flag) {
        this.assessed_tax_flag = assessed_tax_flag;
    }

    public String getLatest_assessment_year() {
        return latest_assessment_year;
    }

    public void setLatest_assessment_year(String latest_assessment_year) {
        this.latest_assessment_year = latest_assessment_year;
    }

    public String getEst_declaration_income() {
        return est_declaration_income;
    }

    public void setEst_declaration_income(String est_declaration_income) {
        this.est_declaration_income = est_declaration_income;
    }

    public String getEst_total_income() {
        return est_total_income;
    }

    public void setEst_total_income(String est_total_income) {
        this.est_total_income = est_total_income;
    }

    public String getTotal_no_form() {
        return total_no_form;
    }

    public void setTotal_no_form(String total_no_form) {
        this.total_no_form = total_no_form;
    }

    public String getAggregate_income_amt() {
        return aggregate_income_amt;
    }

    public void setAggregate_income_amt(String aggregate_income_amt) {
        this.aggregate_income_amt = aggregate_income_amt;
    }

    public Date getDeclaration_date() {
        return declaration_date;
    }

    public void setDeclaration_date(String declaration_date) {
        Date d;
        try {
            //System.out.println("dob" + dob);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d = sdf.parse(declaration_date);
        } catch (Exception e) {
            d = null;
        }
        this.declaration_date = d;
    }

    public String getIncome_amount_paid() {
        return income_amount_paid;
    }

    public void setIncome_amount_paid(String income_amount_paid) {
        this.income_amount_paid = income_amount_paid;
    }

    public Date getIncome_paid_date() {
        return income_paid_date;
    }

    public void setIncome_paid_date(String income_paid_date) {
        Date d;
        try {
            //System.out.println("dob" + dob);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d = sdf.parse(income_paid_date);
        } catch (Exception e) {
            d = null;
        }
        this.income_paid_date = d;
    }

    public String getDeductee_panno_verified() {
        return deductee_panno_verified;
    }

    public void setDeductee_panno_verified(String deductee_panno_verified) {
        this.deductee_panno_verified = deductee_panno_verified;
    }

    public String getDeductee_panno_valid() {
        return deductee_panno_valid;
    }

    public void setDeductee_panno_valid(String deductee_panno_valid) {
        this.deductee_panno_valid = deductee_panno_valid;
    }

    public String getName_as_panno() {
        return name_as_panno;
    }

    public void setName_as_panno(String name_as_panno) {
        this.name_as_panno = name_as_panno;
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

    public Long getRowid_seq() {
        return rowid_seq;
    }

    public void setRowid_seq(Long rowid_seq) {
        this.rowid_seq = rowid_seq;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getPan_status() {
        return pan_status;
    }

    public void setPan_status(String pan_status) {
        this.pan_status = pan_status;
    }

}
