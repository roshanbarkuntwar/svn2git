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
 * @author akash.dev
 */
@Entity
@Table(name = "entity_mast")
public class EntityMast implements java.io.Serializable {

    @Id
    @Column(name = "entity_code", length = 2, nullable = false)
    private String entity_code;
    @Column(name = "entity_name", length = 40, nullable = false)
    private String entity_name;
    @Column(name = "parent_code", length = 2, nullable = true)
    private String parent_code;
    @Column(name = "codetype", length = 1, nullable = true)
    private String codetype;
    @Column(name = "user_code", length = 8, nullable = true)
    private String user_code;
    @Column(name = "flag", length = 1, nullable = true)
    private String flag;
    @Column(name = "eccno", length = 20, nullable = true)
    private String eccno;
    @Column(name = "rangeno", length = 30, nullable = true)
    private String rangeno;
    @Column(name = "range_name", length = 60, nullable = true)
    private String range_name;
    @Column(name = "rangeadd1", length = 40, nullable = true)
    private String rangeadd1;
    @Column(name = "rangeadd2", length = 40, nullable = true)
    private String rangeadd2;
    @Column(name = "division", length = 60, nullable = true)
    private String division;
    @Column(name = "collector", length = 60, nullable = true)
    private String collector;
    @Column(name = "add1", length = 40, nullable = true)
    private String add1;
    @Column(name = "add2", length = 40, nullable = true)
    private String add2;
    @Column(name = "add3", length = 40, nullable = true)
    private String add3;
    @Column(name = "city", length = 20, nullable = true)
    private String city;
    @Column(name = "pin", length = 6, nullable = true)
    private String pin;
    @Column(name = "district", length = 20, nullable = true)
    private String district;
    @Column(name = "country", length = 25, nullable = true)
    private String country;
    @Column(name = "phone", length = 100, nullable = true)
    private String phone;
    @Column(name = "email", length = 100, nullable = true)
    private String email;
    @Column(name = "panno", length = 20, nullable = true)
    private String panno;
    @Column(name = "stno", length = 50, nullable = true)
    private String stno;
    @Column(name = "cstno", length = 50, nullable = true)
    private String cstno;
    @Column(name = "regd_office", length = 50, nullable = true)
    private String regd_office;
    @Column(name = "regn_no", length = 50, nullable = true)
    private String regn_no;
    @Column(name = "tariff_no", length = 50, nullable = true)
    private String tariff_no;
    @Column(name = "plano", length = 50, nullable = true)
    private String plano;
    @Column(name = "pf_no", length = 11, nullable = true)
    private String pf_no;
    @Column(name = "esic_no", length = 15, nullable = true)
    private String esic_no;
    @Column(name = "stdcode", length = 10, nullable = true)
    private String stdcode;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastupdate", nullable = false)
    private Date lastupdate;
    @Column(name = "fax", length = 50, nullable = true)
    private String fax;
    @Column(name = "tanno", length = 11, nullable = true)
    private String tanno;
    @Column(name = "factory_add", length = 100, nullable = true)
    private String factory_add;
    @Column(name = "fa_acc_year", length = 5, nullable = true)
    private String fa_acc_year;
    @Column(name = "currency_code", length = 3, nullable = true)
    private String currency_code;
    @Column(name = "print_name", length = 50, nullable = true)
    private String print_name;
    @Column(name = "acc_code", length = 5, nullable = true)
    private String acc_code;
    @Column(name = "default_godown_code", length = 5, nullable = true)
    private String default_godown_code;
    @Column(name = "default_batchno", length = 25, nullable = true)
    private String default_batchno;
    @Column(name = "default_ref_slno", length = 25, nullable = true)
    private String default_ref_slno;
    @Column(name = "default_dept_code", length = 5, nullable = true)
    private String default_dept_code;
    @Column(name = "default_cost_code", length = 5, nullable = true)
    private String default_cost_code;
    @Column(name = "iec_no", length = 15, nullable = true)
    private String iec_no;
    @Column(name = "bin_no", length = 15, nullable = true)
    private String bin_no;
    @Column(name = "cinno", length = 50, nullable = true)
    private String cinno;
    @Column(name = "website", length = 50, nullable = true)
    private String website;
    @Column(name = "db_user", length = 15, nullable = true)
    private String db_user;
    @Column(name = "db_user_pwd", length = 15, nullable = true)
    private String db_user_pwd;

    public EntityMast() {

    }//end constructor

    public EntityMast(String entity_code, String entity_name, String parent_code, String codetype, String user_code, String flag, String eccno, String rangeno, String range_name, String rangeadd1, String rangeadd2, String division, String collector, String add1, String add2, String add3, String city, String pin, String district, String country, String phone, String email, String panno, String stno, String cstno, String regd_office, String regn_no, String tariff_no, String plano, String pf_no, String esic_no, String stdcode, Date lastupdate, String fax, String tanno, String factory_add, String fa_acc_year, String currency_code, String print_name, String acc_code, String default_godown_code, String default_batchno, String default_ref_slno, String default_dept_code, String default_cost_code, String iec_no, String bin_no, String cinno, String website, String db_user, String db_user_pwd) {
        this.entity_code = entity_code;
        this.entity_name = entity_name;
        this.parent_code = parent_code;
        this.codetype = codetype;
        this.user_code = user_code;
        this.flag = flag;
        this.eccno = eccno;
        this.rangeno = rangeno;
        this.range_name = range_name;
        this.rangeadd1 = rangeadd1;
        this.rangeadd2 = rangeadd2;
        this.division = division;
        this.collector = collector;
        this.add1 = add1;
        this.add2 = add2;
        this.add3 = add3;
        this.city = city;
        this.pin = pin;
        this.district = district;
        this.country = country;
        this.phone = phone;
        this.email = email;
        this.panno = panno;
        this.stno = stno;
        this.cstno = cstno;
        this.regd_office = regd_office;
        this.regn_no = regn_no;
        this.tariff_no = tariff_no;
        this.plano = plano;
        this.pf_no = pf_no;
        this.esic_no = esic_no;
        this.stdcode = stdcode;
        this.lastupdate = lastupdate;
        this.fax = fax;
        this.tanno = tanno;
        this.factory_add = factory_add;
        this.fa_acc_year = fa_acc_year;
        this.currency_code = currency_code;
        this.print_name = print_name;
        this.acc_code = acc_code;
        this.default_godown_code = default_godown_code;
        this.default_batchno = default_batchno;
        this.default_ref_slno = default_ref_slno;
        this.default_dept_code = default_dept_code;
        this.default_cost_code = default_cost_code;
        this.iec_no = iec_no;
        this.bin_no = bin_no;
        this.cinno = cinno;
        this.website = website;
        this.db_user = db_user;
        this.db_user_pwd = db_user_pwd;
    }//end constructor

    public String getEntity_code() {
        return entity_code;
    }

    public void setEntity_code(String entity_code) {
        this.entity_code = entity_code;
    }

    public String getEntity_name() {
        return entity_name;
    }

    public void setEntity_name(String entity_name) {
        this.entity_name = entity_name;
    }

    public String getParent_code() {
        return parent_code;
    }

    public void setParent_code(String parent_code) {
        this.parent_code = parent_code;
    }

    public String getCodetype() {
        return codetype;
    }

    public void setCodetype(String codetype) {
        this.codetype = codetype;
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

    public String getEccno() {
        return eccno;
    }

    public void setEccno(String eccno) {
        this.eccno = eccno;
    }

    public String getRangeno() {
        return rangeno;
    }

    public void setRangeno(String rangeno) {
        this.rangeno = rangeno;
    }

    public String getRange_name() {
        return range_name;
    }

    public void setRange_name(String range_name) {
        this.range_name = range_name;
    }

    public String getRangeadd1() {
        return rangeadd1;
    }

    public void setRangeadd1(String rangeadd1) {
        this.rangeadd1 = rangeadd1;
    }

    public String getRangeadd2() {
        return rangeadd2;
    }

    public void setRangeadd2(String rangeadd2) {
        this.rangeadd2 = rangeadd2;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getCollector() {
        return collector;
    }

    public void setCollector(String collector) {
        this.collector = collector;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPanno() {
        return panno;
    }

    public void setPanno(String panno) {
        this.panno = panno;
    }

    public String getStno() {
        return stno;
    }

    public void setStno(String stno) {
        this.stno = stno;
    }

    public String getCstno() {
        return cstno;
    }

    public void setCstno(String cstno) {
        this.cstno = cstno;
    }

    public String getRegd_office() {
        return regd_office;
    }

    public void setRegd_office(String regd_office) {
        this.regd_office = regd_office;
    }

    public String getRegn_no() {
        return regn_no;
    }

    public void setRegn_no(String regn_no) {
        this.regn_no = regn_no;
    }

    public String getTariff_no() {
        return tariff_no;
    }

    public void setTariff_no(String tariff_no) {
        this.tariff_no = tariff_no;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public String getPf_no() {
        return pf_no;
    }

    public void setPf_no(String pf_no) {
        this.pf_no = pf_no;
    }

    public String getEsic_no() {
        return esic_no;
    }

    public void setEsic_no(String esic_no) {
        this.esic_no = esic_no;
    }

    public String getStdcode() {
        return stdcode;
    }

    public void setStdcode(String stdcode) {
        this.stdcode = stdcode;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getTanno() {
        return tanno;
    }

    public void setTanno(String tanno) {
        this.tanno = tanno;
    }

    public String getFactory_add() {
        return factory_add;
    }

    public void setFactory_add(String factory_add) {
        this.factory_add = factory_add;
    }

    public String getFa_acc_year() {
        return fa_acc_year;
    }

    public void setFa_acc_year(String fa_acc_year) {
        this.fa_acc_year = fa_acc_year;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getPrint_name() {
        return print_name;
    }

    public void setPrint_name(String print_name) {
        this.print_name = print_name;
    }

    public String getAcc_code() {
        return acc_code;
    }

    public void setAcc_code(String acc_code) {
        this.acc_code = acc_code;
    }

    public String getDefault_godown_code() {
        return default_godown_code;
    }

    public void setDefault_godown_code(String default_godown_code) {
        this.default_godown_code = default_godown_code;
    }

    public String getDefault_batchno() {
        return default_batchno;
    }

    public void setDefault_batchno(String default_batchno) {
        this.default_batchno = default_batchno;
    }

    public String getDefault_ref_slno() {
        return default_ref_slno;
    }

    public void setDefault_ref_slno(String default_ref_slno) {
        this.default_ref_slno = default_ref_slno;
    }

    public String getDefault_dept_code() {
        return default_dept_code;
    }

    public void setDefault_dept_code(String default_dept_code) {
        this.default_dept_code = default_dept_code;
    }

    public String getDefault_cost_code() {
        return default_cost_code;
    }

    public void setDefault_cost_code(String default_cost_code) {
        this.default_cost_code = default_cost_code;
    }

    public String getIec_no() {
        return iec_no;
    }

    public void setIec_no(String iec_no) {
        this.iec_no = iec_no;
    }

    public String getBin_no() {
        return bin_no;
    }

    public void setBin_no(String bin_no) {
        this.bin_no = bin_no;
    }

    public String getCinno() {
        return cinno;
    }

    public void setCinno(String cinno) {
        this.cinno = cinno;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDb_user() {
        return db_user;
    }

    public void setDb_user(String db_user) {
        this.db_user = db_user;
    }

    public String getDb_user_pwd() {
        return db_user_pwd;
    }

    public void setDb_user_pwd(String db_user_pwd) {
        this.db_user_pwd = db_user_pwd;
    }

}//end class
