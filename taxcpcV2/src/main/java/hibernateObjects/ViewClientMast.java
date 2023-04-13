/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import globalUtilities.Util;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author akash.dev
 * @Immutable--add for fast login under testing
 */
@Entity
@Immutable
@Table(name = "client_mast")
//@Table(name = "view_client_mast_salary")
public class ViewClientMast implements java.io.Serializable {

    @Column(name = "entity_code", length = 2, nullable = true)
    private String entity_code;
    @Id
    @Column(name = "client_code", length = 15, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = {
        @Parameter(name = "sequence", value = "client_code_seq")})
    private String client_code;
    @Column(name = "client_name", length = 100, nullable = true)
    private String client_name;
    @Column(name = "parent_code", length = 15, nullable = true)
    private String parent_code;
    @Column(name = "remark", length = 240, nullable = true)
    private String remark;
    @Column(name = "initiate_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date initiate_date;
    @Column(name = "client_type_code", length = 5, nullable = true)
    private String client_type_code;
    @Column(name = "client_status", length = 1, nullable = true)
    private String client_status;
    @Column(name = "closed_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date closed_date;
    @Column(name = "geo_org_code", length = 500, nullable = true)
    private String geo_org_code;
    @Column(name = "approvedby", length = 8, nullable = true)
    private String approvedby;
    @Column(name = "approveddate", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date approveddate;
    @Column(name = "owner_name", length = 50, nullable = true)
    private String owner_name;
    @Column(name = "deductor_name", length = 100, nullable = true)
    private String deductor_name;
    @Column(name = "deductor_desig", length = 100, nullable = true)
    private String deductor_desig;
    @Column(name = "deductor_phoneno", length = 100, nullable = true)
    private String deductor_phoneno;
    @Column(name = "deductor_mobileno", length = 100, nullable = true)
    private String deductor_mobileno;
    @Column(name = "deductor_email_id", length = 250, nullable = true)
    private String deductor_email_id;
    @Column(name = "reference_remark", length = 240, nullable = true)
    private String reference_remark;
    @Column(name = "website", length = 25, nullable = true)
    private String website;
    @Column(name = "add1", length = 100, nullable = true)
    private String add1;
    @Column(name = "add2", length = 100, nullable = true)
    private String add2;
    @Column(name = "add3", length = 100, nullable = true)
    private String add3;
    @Column(name = "add4", length = 100, nullable = true)
    private String add4;
    @Column(name = "city_code", length = 100, nullable = true)
    private String city_code;
    @Column(name = "pin", length = 6, nullable = true)
    private String pin;
    @Column(name = "stdcode", length = 6, nullable = true)
    private String stdcode;
    @Column(name = "phoneno", length = 100, nullable = true)
    private String phoneno;
    @Column(name = "mobileno", length = 100, nullable = true)
    private String mobileno;
    @Column(name = "email_id", length = 250, nullable = true)
    private String email_id;
    @Column(name = "estd_year", length = 4, nullable = true)
    private String estd_year;
    @Column(name = "panno", length = 10, nullable = true)
    private String panno;
    @Column(name = "tanno", length = 12, nullable = true)
    private String tanno;
    @Column(name = "login_id", length = 100, nullable = true)
    private String login_id;
    @Column(name = "login_pwd", length = 100, nullable = true)
    private String login_pwd;
    @Column(name = "web1_login_id", length = 100, nullable = true)
    private String web1_login_id;
    @Column(name = "web1_login_pwd", length = 100, nullable = true)
    private String web1_login_pwd;
    @Column(name = "web2_login_id", length = 100, nullable = true)
    private String web2_login_id;
    @Column(name = "web2_login_pwd", length = 100, nullable = true)
    private String web2_login_pwd;
    @Column(name = "web3_login_id", length = 100, nullable = true)
    private String web3_login_id;
    @Column(name = "web3_login_pwd", length = 100, nullable = true)
    private String web3_login_pwd;
    @Column(name = "lastupdate", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastupdate;
    @Column(name = "flag", length = 1, nullable = true)
    private String flag;
    @Column(name = "state_code", length = 2, nullable = true)
    private String state_code;
    @Column(name = "branch_division", length = 50, nullable = true)
    private String branch_division;
    @Column(name = "deductor_add1", length = 100, nullable = true)
    private String deductor_add1;
    @Column(name = "deductor_add2", length = 100, nullable = true)
    private String deductor_add2;
    @Column(name = "deductor_add3", length = 100, nullable = true)
    private String deductor_add3;
    @Column(name = "deductor_add4", length = 100, nullable = true)
    private String deductor_add4;
    @Column(name = "deductor_city_code", length = 100, nullable = true)
    private String deductor_city_code;
    @Column(name = "deductor_pin", length = 6, nullable = true)
    private String deductor_pin;
    @Column(name = "deductor_stdcode", length = 6, nullable = true)
    private String deductor_stdcode;
    @Column(name = "deductor_state_code", length = 2, nullable = true)
    private String deductor_state_code;
    @Column(name = "alternate_phoneno", length = 100, nullable = true)
    private String alternate_phoneno;
    @Column(name = "alternate_mobileno", length = 100, nullable = true)
    private String alternate_mobileno;
    @Column(name = "alternate_email_id", length = 250, nullable = true)
    private String alternate_email_id;
    @Column(name = "country_code", length = 3, nullable = true)
    private String country_code;
    @Column(name = "deductor_country_code", length = 3, nullable = true)
    private String deductor_country_code;
    @Column(name = "ministry_code", length = 5, nullable = true)
    private String ministry_code;
    @Column(name = "sub_ministry_code", length = 5, nullable = true)
    private String sub_ministry_code;
    @Column(name = "alternate_stdcode", length = 7, nullable = true)
    private String alternate_stdcode;
    @Column(name = "deductor_alternate_stdcode", length = 6, nullable = true)
    private String deductor_alternate_stdcode;
    @Column(name = "deductor_alternate_phoneno", length = 100, nullable = true)
    private String deductor_alternate_phoneno;
    @Column(name = "deductor_alternate_email_id", length = 250, nullable = true)
    private String deductor_alternate_email_id;
    @Column(name = "ain_no", length = 50, nullable = true)
    private String ain_no;
    @Column(name = "client_catg_code", length = 5, nullable = true)
    private String client_catg_code;
    @Column(name = "pao_code", length = 20, nullable = true)
    private String pao_code;
    @Column(name = "pao_registration_no", length = 10, nullable = true)
    private String pao_registration_no;
    @Column(name = "ddo_code", length = 20, nullable = true)
    private String ddo_code;
    @Column(name = "ddo_registration_no", length = 10, nullable = true)
    private String ddo_registration_no;
    @Column(name = "temp_zen_deductor_id", nullable = true)
    private String temp_zen_deductor_id;
    @Column(name = "deductor_panno", length = 10, nullable = true)
    private String deductor_panno;
    @Column(name = "deductor_add_change", length = 1, nullable = true)
    private String deductor_add_change;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "deductor_add_change_on", nullable = true)
    private Date deductor_add_change_on;
    @Column(name = "ministry_name_other", length = 100, nullable = true)
    private String ministry_name_other;
    @Column(name = "login_pwd_expiry_days", length = 4, nullable = true)
    private String login_pwd_expiry_days;
    @Column(name = "closed_remark", length = 4000, nullable = true)
    private String closed_remark;
    @Column(name = "ministry_state_code", length = 2, nullable = true)
    private String ministry_state_code;
    @Column(name = "traces_id", length = 50, nullable = true)
    private String traces_id;
    @Column(name = "traces_pwd", length = 50, nullable = true)
    private String traces_pwd;
    @Column(name = "add_change", length = 1, nullable = true)
    private String add_change;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "add_change_on", nullable = true)
    private Date add_change_on;
    @Column(name = "last_deduction_ref_no_e_catg", nullable = true)
    private String last_deduction_ref_no_e_catg;
    @Column(name = "last_deduction_ref_no_o_catg", nullable = true)
    private String last_deduction_ref_no_o_catg;
    @Column(name = "bulk_pan_verification_username", length = 50, nullable = true)
    private String bulk_pan_verification_username;
    @Column(name = "bulk_pan_verification_password", length = 50, nullable = true)
    private String bulk_pan_verification_password;
    @Column(name = "code_level", length = 1)
    private String code_level;
    @Column(name = "bank_branch_code", length = 15, nullable = true)
    private String bank_branch_code;
    @Column(name = "g_parent_code", length = 15, nullable = true)
    private String g_parent_code;

    @Column(name = "sg_parent_code", length = 15, nullable = true)
    private String sg_parent_code;

    @Column(name = "ssg_parent_code", length = 15, nullable = true)
    private String ssg_parent_code;

    @Column(name = "sssg_parent_code", length = 15, nullable = true)
    private String sssg_parent_code;
    @Column(name = "appr_random_no", length = 15, nullable = true)
    private String appr_random_no;
    @Column(name = "appr_verify_code", length = 15, nullable = true)
    private String appr_verify_code;
    @Column(name = "pan_verification_rn", length = 15, nullable = true)
    private String pan_verification_rn;
    @Column(name = "efiling_rn", length = 15, nullable = true)
    private String efiling_rn;
    @Column(name = "bulk_15gh_xml_rn", length = 15, nullable = true)
    private String bulk_15gh_xml_rn;
    @Column(name = "bflag_generation_level", length = 30, nullable = true)
    private String bflag_generation_level;
    @Column(name = "client_level_type")
    private String client_level_type;
    @Column(name = "gstn_no", length = 15, nullable = true)
    private String gstn_no;

    @Column(name = "validation_group", nullable = true)
    private String validation_group;
    @Column(name = "bflag_generation_level_value", nullable = true)
    private String bflag_generation_level_value;
    @Column(name = "deduction_ref_no_e_catg_15gh", nullable = true)
    private Long deduction_ref_no_e_catg_15gh;
    @Column(name = "deduction_ref_no_o_catg_15gh", nullable = true)
    private Long deduction_ref_no_o_catg_15gh;
    @Column(name = "add_right", nullable = true)
    private String add_right;
    @Column(name = "edit_right", nullable = true)
    private String edit_right;
    @Column(name = "delete_right", nullable = true)
    private String delete_right;
    @Column(name = "print_right", nullable = true)
    private Long print_right;
    @Column(name = "query_right", nullable = true)
    private String query_right;
    @Column(name = "special_right", nullable = true)
    private Long special_right;
    @Column(name = "approved_right", nullable = true)
    private Long approved_right;
    @Column(name = "valuation_right", nullable = true)
    private Long valuation_right;
    @Column(name = "process_seqno", nullable = true)
    private Long process_seqno;
    

    public ViewClientMast() {
        this.lastupdate = new Date();
    }//end constructor

    public ViewClientMast(String entity_code, String client_code) {
        this.entity_code = entity_code;
        this.client_code = client_code;
    }//end constructor

    public ViewClientMast(String entity_code, String client_code, String client_name, String parent_code, String remark, Date initiate_date, String client_type_code, String client_status, Date closed_date, String geo_org_code, String approvedby, Date approveddate, String owner_name, String deductor_name, String deductor_desig, String deductor_phoneno, String deductor_mobileno, String deductor_email_id, String reference_remark, String website, String add1, String add2, String add3, String add4, String city_code, String pin, String stdcode, String phoneno, String mobileno, String email_id, String estd_year, String panno, String tanno, String login_id, String login_pwd, String web1_login_id, String web1_login_pwd, String web2_login_id, String web2_login_pwd, String web3_login_id, String web3_login_pwd, Date lastupdate, String flag, String state_code, String branch_division, String deductor_add1, String deductor_add2, String deductor_add3, String deductor_add4, String deductor_city_code, String deductor_pin, String deductor_stdcode, String deductor_state_code, String alternate_phoneno, String alternate_mobileno, String alternate_email_id, String country_code, String deductor_country_code, String ministry_code, String sub_ministry_code, String alternate_stdcode, String deductor_alternate_stdcode, String deductor_alternate_phoneno, String deductor_alternate_email_id, String ain_no, String client_catg_code, String pao_code, String pao_registration_no, String ddo_code, String ddo_registration_no, String temp_zen_deductor_id, String deductor_panno, String deductor_add_change, Date deductor_add_change_on, String ministry_name_other, String login_pwd_expiry_days, String closed_remark, String ministry_state_code, String traces_id, String traces_pwd, String add_change, String bulk_pan_verification_username, String bulk_pan_verification_password, String g_parent_code, String sg_parent_code, String ssg_parent_code, String sssg_parent_code, String appr_random_no, String appr_verify_code, String code_level, String pan_verification_rn, String efiling_rn, String bulk_15gh_xml_rn, String bflag_generation_level, String client_level_type) {
        this.entity_code = entity_code;
        this.client_code = client_code;
        this.client_name = client_name;
        this.parent_code = parent_code;
        this.remark = remark;
        this.initiate_date = initiate_date;
        this.client_type_code = client_type_code;
        this.client_status = client_status;
        this.closed_date = closed_date;
        this.geo_org_code = geo_org_code;
        this.approvedby = approvedby;
        this.approveddate = approveddate;
        this.owner_name = owner_name;
        this.deductor_name = deductor_name;
        this.deductor_desig = deductor_desig;
        this.deductor_phoneno = deductor_phoneno;
        this.deductor_mobileno = deductor_mobileno;
        this.deductor_email_id = deductor_email_id;
        this.reference_remark = reference_remark;
        this.website = website;
        this.add1 = add1;
        this.add2 = add2;
        this.add3 = add3;
        this.add4 = add4;
        this.city_code = city_code;
        this.pin = pin;
        this.stdcode = stdcode;
        this.phoneno = phoneno;
        this.mobileno = mobileno;
        this.email_id = email_id;
        this.estd_year = estd_year;
        this.panno = panno;
        this.tanno = tanno;
        this.login_id = login_id;
        this.login_pwd = login_pwd;
        this.web1_login_id = web1_login_id;
        this.web1_login_pwd = web1_login_pwd;
        this.web2_login_id = web2_login_id;
        this.web2_login_pwd = web2_login_pwd;
        this.web3_login_id = web3_login_id;
        this.web3_login_pwd = web3_login_pwd;
        this.lastupdate = lastupdate;
        this.flag = flag;
        this.state_code = state_code;
        this.branch_division = branch_division;
        this.deductor_add1 = deductor_add1;
        this.deductor_add2 = deductor_add2;
        this.deductor_add3 = deductor_add3;
        this.deductor_add4 = deductor_add4;
        this.deductor_city_code = deductor_city_code;
        this.deductor_pin = deductor_pin;
        this.deductor_stdcode = deductor_stdcode;
        this.deductor_state_code = deductor_state_code;
        this.alternate_phoneno = alternate_phoneno;
        this.alternate_mobileno = alternate_mobileno;
        this.alternate_email_id = alternate_email_id;
        this.country_code = country_code;
        this.deductor_country_code = deductor_country_code;
        this.ministry_code = ministry_code;
        this.sub_ministry_code = sub_ministry_code;
        this.alternate_stdcode = alternate_stdcode;
        this.deductor_alternate_stdcode = deductor_alternate_stdcode;
        this.deductor_alternate_phoneno = deductor_alternate_phoneno;
        this.deductor_alternate_email_id = deductor_alternate_email_id;
        this.ain_no = ain_no;
        this.client_catg_code = client_catg_code;
        this.pao_code = pao_code;
        this.pao_registration_no = pao_registration_no;
        this.ddo_code = ddo_code;
        this.ddo_registration_no = ddo_registration_no;
        this.temp_zen_deductor_id = temp_zen_deductor_id;
        this.deductor_panno = deductor_panno;
        this.deductor_add_change = deductor_add_change;
        this.deductor_add_change_on = deductor_add_change_on;
        this.ministry_name_other = ministry_name_other;
        this.login_pwd_expiry_days = login_pwd_expiry_days;
        this.closed_remark = closed_remark;
        this.ministry_state_code = ministry_state_code;
        this.traces_id = traces_id;
        this.traces_pwd = traces_pwd;
        this.add_change = add_change;
        this.bulk_pan_verification_username = bulk_pan_verification_username;
        this.bulk_pan_verification_password = bulk_pan_verification_password;
        this.g_parent_code = g_parent_code;
        this.sg_parent_code = sg_parent_code;
        this.ssg_parent_code = ssg_parent_code;
        this.sssg_parent_code = sssg_parent_code;
        this.appr_random_no = appr_random_no;
        this.appr_verify_code = appr_verify_code;
        this.code_level = code_level;
        this.pan_verification_rn = pan_verification_rn;
        this.efiling_rn = efiling_rn;
        this.bulk_15gh_xml_rn = bulk_15gh_xml_rn;
        this.bflag_generation_level = bflag_generation_level;
        this.client_level_type = client_level_type;
    }

    public String getBank_branch_code() {
        return bank_branch_code;
    }

    public void setBank_branch_code(String bank_branch_code) {
        this.bank_branch_code = bank_branch_code;
    }

    public String getEntity_code() {
        return entity_code;
    }

    public void setEntity_code(String entity_code) {
        if (entity_code != null) {
            this.entity_code = entity_code.toUpperCase();
        } else {
            this.entity_code = entity_code;
        }
    }

    public String getClient_code() {
        return client_code;
    }

    public void setClient_code(String client_code) {
        this.client_code = client_code;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        if (client_name != null) {
            this.client_name = client_name.toUpperCase();
        } else {
            this.client_name = client_name;
        }
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

    public Date getInitiate_date() {
        return initiate_date;
    }

    public void setInitiate_date(Date initiate_date) {
//        Date d;
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//            d = sdf.parse(initiate_date);
//        } catch (Exception e) {
//            d = null;
//        }
        this.initiate_date = initiate_date;
    }

    public String getClient_type_code() {
        return client_type_code;
    }

    public void setClient_type_code(String client_type_code) {
        this.client_type_code = client_type_code;
    }

    public String getClient_status() {
        return client_status;
    }

    public void setClient_status(String client_status) {
        this.client_status = client_status;
    }

    public Date getClosed_date() {
        return closed_date;
    }

    public void setClosed_date(String closed_date) {
        Date d;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d = sdf.parse(closed_date);
        } catch (Exception e) {
            d = null;
        }
        this.closed_date = d;
    }

    public String getGeo_org_code() {
        return geo_org_code;
    }

    public void setGeo_org_code(String geo_org_code) {
        this.geo_org_code = geo_org_code;
    }

    public String getApprovedby() {
        return approvedby;
    }

    public void setApprovedby(String approvedby) {
        this.approvedby = approvedby;
    }

    public Date getApproveddate() {
        return approveddate;
    }

    public void setApproveddate(Date approveddate) {
        this.approveddate = approveddate;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        if (owner_name != null) {
            this.owner_name = owner_name.toUpperCase();
        } else {
            this.owner_name = owner_name;
        }
    }

    public String getDeductor_name() {
        return deductor_name;
    }

    public void setDeductor_name(String deductor_name) {
        if (deductor_name != null) {
            this.deductor_name = deductor_name.toUpperCase();
        } else {
            this.deductor_name = deductor_name;
        }
    }

    public String getDeductor_desig() {
        return deductor_desig;
    }

    public void setDeductor_desig(String deductor_desig) {
        if (deductor_desig != null) {
            this.deductor_desig = deductor_desig.toUpperCase();
        } else {
            this.deductor_desig = deductor_desig;
        }
    }

    public String getDeductor_phoneno() {
        return deductor_phoneno;
    }

    public void setDeductor_phoneno(String deductor_phoneno) {
        this.deductor_phoneno = deductor_phoneno;
    }

    public String getDeductor_mobileno() {
        return deductor_mobileno;
    }

    public void setDeductor_mobileno(String deductor_mobileno) {
        this.deductor_mobileno = deductor_mobileno;
    }

    public String getDeductor_email_id() {
        return deductor_email_id;
    }

    public void setDeductor_email_id(String deductor_email_id) {
        if (deductor_email_id != null) {
            this.deductor_email_id = deductor_email_id.toUpperCase();
        } else {
            this.deductor_email_id = deductor_email_id;
        }
    }

    public String getReference_remark() {
        return reference_remark;
    }

    public void setReference_remark(String reference_remark) {
        this.reference_remark = reference_remark;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        if (website != null) {
            this.website = website.toUpperCase();
        } else {
            this.website = website;
        }
    }

    public String getAdd1() {
        return add1;
    }

    public void setAdd1(String add1) {
        if (add1 != null) {
            this.add1 = add1.toUpperCase();
        } else {
            this.add1 = add1;
        }
    }

    public String getAdd2() {
        return add2;
    }

    public void setAdd2(String add2) {
        if (add2 != null) {
            this.add2 = add2.toUpperCase();
        } else {
            this.add2 = add2;
        }
    }

    public String getAdd3() {
        return add3;
    }

    public void setAdd3(String add3) {
        if (add3 != null) {
            this.add3 = add3.toUpperCase();
        } else {
            this.add3 = add3;
        }
    }

    public String getAdd4() {
        return add4;
    }

    public void setAdd4(String add4) {
        if (add4 != null) {
            this.add4 = add4.toUpperCase();
        } else {
            this.add4 = add4;
        }
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
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
        if (email_id != null) {
            this.email_id = email_id.toUpperCase();
        } else {
            this.email_id = email_id;
        }
    }

    public String getEstd_year() {
        return estd_year;
    }

    public void setEstd_year(String estd_year) {
        this.estd_year = estd_year;
    }

    public String getPanno() {
        return panno;
    }

    public void setPanno(String panno) {
        if (panno != null) {
            this.panno = panno.toUpperCase();
        } else {
            this.panno = panno;
        }
    }

    public String getTanno() {
        return tanno;
    }

    public void setTanno(String tanno) {
        if (tanno != null) {
            this.tanno = tanno.toUpperCase();
        } else {
            this.tanno = tanno;
        }
    }

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getLogin_pwd() {
        return login_pwd;
    }

    public void setLogin_pwd(String login_pwd) {
        this.login_pwd = login_pwd;
    }

    public String getWeb1_login_id() {
        return web1_login_id;
    }

    public void setWeb1_login_id(String web1_login_id) {
        this.web1_login_id = web1_login_id;
    }

    public String getWeb1_login_pwd() {
        return web1_login_pwd;
    }

    public void setWeb1_login_pwd(String web1_login_pwd) {
        this.web1_login_pwd = web1_login_pwd;
    }

    public String getWeb2_login_id() {
        return web2_login_id;
    }

    public void setWeb2_login_id(String web2_login_id) {
        this.web2_login_id = web2_login_id;
    }

    public String getWeb2_login_pwd() {
        return web2_login_pwd;
    }

    public void setWeb2_login_pwd(String web2_login_pwd) {
        this.web2_login_pwd = web2_login_pwd;
    }

    public String getWeb3_login_id() {
        return web3_login_id;
    }

    public void setWeb3_login_id(String web3_login_id) {
        this.web3_login_id = web3_login_id;
    }

    public String getWeb3_login_pwd() {
        return web3_login_pwd;
    }

    public void setWeb3_login_pwd(String web3_login_pwd) {
        this.web3_login_pwd = web3_login_pwd;
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

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    public String getBranch_division() {
        return branch_division;
    }

    public void setBranch_division(String branch_division) {
        this.branch_division = branch_division;
    }

    public String getDeductor_add1() {
        return deductor_add1;
    }

    public void setDeductor_add1(String deductor_add1) {
        if (deductor_add1 != null) {
            this.deductor_add1 = deductor_add1.toUpperCase();
        } else {
            this.deductor_add1 = deductor_add1;
        }
    }

    public String getDeductor_add2() {
        return deductor_add2;
    }

    public void setDeductor_add2(String deductor_add2) {
        if (deductor_add2 != null) {
            this.deductor_add2 = deductor_add2.toUpperCase();
        } else {
            this.deductor_add2 = deductor_add2;
        }
    }

    public String getDeductor_add3() {
        return deductor_add3;
    }

    public void setDeductor_add3(String deductor_add3) {
        if (deductor_add3 != null) {
            this.deductor_add3 = deductor_add3.toUpperCase();
        } else {
            this.deductor_add3 = deductor_add3;
        }
    }

    public String getDeductor_add4() {
        return deductor_add4;
    }

    public void setDeductor_add4(String deductor_add4) {
        if (deductor_add4 != null) {
            this.deductor_add4 = deductor_add4.toUpperCase();
        } else {
            this.deductor_add4 = deductor_add4;
        }
    }

    public String getDeductor_city_code() {
        return deductor_city_code;
    }

    public void setDeductor_city_code(String deductor_city_code) {
        this.deductor_city_code = deductor_city_code;
    }

    public String getDeductor_pin() {
        return deductor_pin;
    }

    public void setDeductor_pin(String deductor_pin) {
        this.deductor_pin = deductor_pin;
    }

    public String getDeductor_stdcode() {
        return deductor_stdcode;
    }

    public void setDeductor_stdcode(String deductor_stdcode) {
        this.deductor_stdcode = deductor_stdcode;
    }

    public String getDeductor_state_code() {
        return deductor_state_code;
    }

    public void setDeductor_state_code(String deductor_state_code) {
        this.deductor_state_code = deductor_state_code;
    }

    public String getDeductor_country_code() {
        return deductor_country_code;
    }

    public void setDeductor_country_code(String deductor_country_code) {
        this.deductor_country_code = deductor_country_code;
    }

    public String getAlternate_phoneno() {
        return alternate_phoneno;
    }

    public void setAlternate_phoneno(String alternate_phoneno) {
        this.alternate_phoneno = alternate_phoneno;
    }

    public String getAlternate_mobileno() {
        return alternate_mobileno;
    }

    public void setAlternate_mobileno(String alternate_mobileno) {
        this.alternate_mobileno = alternate_mobileno;
    }

    public String getAlternate_email_id() {
        return alternate_email_id;
    }

    public void setAlternate_email_id(String alternate_email_id) {
        if (alternate_email_id != null) {
            this.alternate_email_id = alternate_email_id.toUpperCase();
        } else {
            this.alternate_email_id = alternate_email_id;
        }
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getMinistry_code() {
        return ministry_code;
    }

    public void setMinistry_code(String ministry_code) {
        this.ministry_code = ministry_code;
    }

    public String getSub_ministry_code() {
        return sub_ministry_code;
    }

    public void setSub_ministry_code(String sub_ministry_code) {
        this.sub_ministry_code = sub_ministry_code;
    }

    public String getAlternate_stdcode() {
        return alternate_stdcode;
    }

    public void setAlternate_stdcode(String alternate_stdcode) {
        this.alternate_stdcode = alternate_stdcode;
    }

    public String getDeductor_alternate_stdcode() {
        return deductor_alternate_stdcode;
    }

    public void setDeductor_alternate_stdcode(String deductor_alternate_stdcode) {
        this.deductor_alternate_stdcode = deductor_alternate_stdcode;
    }

    public String getDeductor_alternate_phoneno() {
        return deductor_alternate_phoneno;
    }

    public void setDeductor_alternate_phoneno(String deductor_alternate_phoneno) {
        this.deductor_alternate_phoneno = deductor_alternate_phoneno;
    }

    public String getDeductor_alternate_email_id() {
        return deductor_alternate_email_id;
    }

    public void setDeductor_alternate_email_id(String deductor_alternate_email_id) {
        if (deductor_alternate_email_id != null) {
            this.deductor_alternate_email_id = deductor_alternate_email_id.toUpperCase();
        } else {
            this.deductor_alternate_email_id = deductor_alternate_email_id;
        }
    }

    public String getAin_no() {
        return ain_no;
    }

    public void setAin_no(String ain_no) {
        this.ain_no = ain_no;
    }

    public String getClient_catg_code() {
        return client_catg_code;
    }

    public void setClient_catg_code(String client_catg_code) {
        this.client_catg_code = client_catg_code;
    }

    public String getPao_code() {
        return pao_code;
    }

    public void setPao_code(String pao_code) {
        this.pao_code = pao_code;
    }

    public String getPao_registration_no() {
        return pao_registration_no;
    }

    public void setPao_registration_no(String pao_registration_no) {
        this.pao_registration_no = pao_registration_no;
    }

    public String getDdo_code() {
        return ddo_code;
    }

    public void setDdo_code(String ddo_code) {
        this.ddo_code = ddo_code;
    }

    public String getDdo_registration_no() {
        return ddo_registration_no;
    }

    public void setDdo_registration_no(String ddo_registration_no) {
        this.ddo_registration_no = ddo_registration_no;
    }

    public String getTemp_zen_deductor_id() {
        return temp_zen_deductor_id;
    }

    public void setTemp_zen_deductor_id(String temp_zen_deductor_id) {
        this.temp_zen_deductor_id = temp_zen_deductor_id;
    }

    public String getDeductor_panno() {
        return deductor_panno;
    }

    public void setDeductor_panno(String deductor_panno) {
        this.deductor_panno = deductor_panno;
    }

    public String getDeductor_add_change() {
        return deductor_add_change;
    }

    public void setDeductor_add_change(String deductor_add_change) {
        this.deductor_add_change = deductor_add_change;
    }

    public Date getDeductor_add_change_on() {
        return deductor_add_change_on;
    }

//    public void setDeductor_add_change_on(Date deductor_add_change_on) {
//        this.deductor_add_change_on = deductor_add_change_on;
//    }
    public void setDeductor_add_change_on(String deductor_add_change_on) {
        Date d;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d = sdf.parse(deductor_add_change_on);
        } catch (Exception e) {
            d = null;
        }
        this.deductor_add_change_on = d;
    }

    public String getMinistry_name_other() {
        return ministry_name_other;
    }

    public void setMinistry_name_other(String ministry_name_other) {
        if (ministry_name_other != null) {
            this.ministry_name_other = ministry_name_other.toUpperCase();
        } else {
            this.ministry_name_other = ministry_name_other;
        }
    }

    public String getLogin_pwd_expiry_days() {
        return login_pwd_expiry_days;
    }

    public void setLogin_pwd_expiry_days(String login_pwd_expiry_days) {
        this.login_pwd_expiry_days = login_pwd_expiry_days;
    }

    public String getClosed_remark() {
        return closed_remark;
    }

    public void setClosed_remark(String closed_remark) {
        this.closed_remark = closed_remark;
    }

    public String getMinistry_state_code() {
        return ministry_state_code;
    }

    public void setMinistry_state_code(String ministry_state_code) {
        this.ministry_state_code = ministry_state_code;
    }

    public String getTraces_id() {
        return traces_id;
    }

    public void setTraces_id(String traces_id) {
        this.traces_id = traces_id;
    }

    public String getTraces_pwd() {
        return traces_pwd;
    }

    public void setTraces_pwd(String traces_pwd) {
        this.traces_pwd = traces_pwd;
    }

    public String getAdd_change() {
        return add_change;
    }

    public void setAdd_change(String add_change) {
        this.add_change = add_change;
    }

    public String getBulk_pan_verification_username() {
        return bulk_pan_verification_username;
    }

    public void setBulk_pan_verification_username(String bulk_pan_verification_username) {
        this.bulk_pan_verification_username = bulk_pan_verification_username;
    }

    public String getBulk_pan_verification_password() {
        return bulk_pan_verification_password;
    }

    public void setBulk_pan_verification_password(String bulk_pan_verification_password) {
        this.bulk_pan_verification_password = bulk_pan_verification_password;
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

    public String getAppr_random_no() {
        return appr_random_no;
    }

    public void setAppr_random_no(String appr_random_no) {
        this.appr_random_no = appr_random_no;
    }

    public String getAppr_verify_code() {
        return appr_verify_code;
    }

    public void setAppr_verify_code(String appr_verify_code) {
        this.appr_verify_code = appr_verify_code;
    }

    public String getCode_level() {
        return code_level;
    }

    public void setCode_level(String code_level) {
        this.code_level = code_level;
    }

    public String getPan_verification_rn() {
        return pan_verification_rn;
    }

    public void setPan_verification_rn(String pan_verification_rn) {
        this.pan_verification_rn = pan_verification_rn;
    }

    public String getEfiling_rn() {
        return efiling_rn;
    }

    public void setEfiling_rn(String efiling_rn) {
        this.efiling_rn = efiling_rn;
    }

    public String getBulk_15gh_xml_rn() {
        return bulk_15gh_xml_rn;
    }

    public void setBulk_15gh_xml_rn(String bulk_15gh_xml_rn) {
        this.bulk_15gh_xml_rn = bulk_15gh_xml_rn;
    }

    public String getBflag_generation_level() {
        return bflag_generation_level;
    }

    public void setBflag_generation_level(String bflag_generation_level) {
        this.bflag_generation_level = bflag_generation_level;
    }

    public String getClient_level_type() {
        return client_level_type;
    }

    public void setClient_level_type(String client_level_type) {
        this.client_level_type = client_level_type;
    }

    public Date getAdd_change_on() {
        return add_change_on;
    }

    public void setAdd_change_on(Date add_change_on) {
        this.add_change_on = add_change_on;
    }

    public String getLast_deduction_ref_no_e_catg() {
        return last_deduction_ref_no_e_catg;
    }

    public void setLast_deduction_ref_no_e_catg(String last_deduction_ref_no_e_catg) {
        this.last_deduction_ref_no_e_catg = last_deduction_ref_no_e_catg;
    }

    public String getLast_deduction_ref_no_o_catg() {
        return last_deduction_ref_no_o_catg;
    }

    public void setLast_deduction_ref_no_o_catg(String last_deduction_ref_no_o_catg) {
        this.last_deduction_ref_no_o_catg = last_deduction_ref_no_o_catg;
    }

    public String getGstn_no() {
        return gstn_no;
    }

    public void setGstn_no(String gstn_no) {
        this.gstn_no = gstn_no;
    }

    public String getValidation_group() {
        return validation_group;
    }

    public void setValidation_group(String validation_group) {
        this.validation_group = validation_group;
    }

    public String getBflag_generation_level_value() {
        return bflag_generation_level_value;
    }

    public void setBflag_generation_level_value(String bflag_generation_level_value) {
        this.bflag_generation_level_value = bflag_generation_level_value;
    }

    public Long getDeduction_ref_no_e_catg_15gh() {
        return deduction_ref_no_e_catg_15gh;
    }

    public void setDeduction_ref_no_e_catg_15gh(Long deduction_ref_no_e_catg_15gh) {
        this.deduction_ref_no_e_catg_15gh = deduction_ref_no_e_catg_15gh;
    }

    public Long getDeduction_ref_no_o_catg_15gh() {
        return deduction_ref_no_o_catg_15gh;
    }

    public void setDeduction_ref_no_o_catg_15gh(Long deduction_ref_no_o_catg_15gh) {
        this.deduction_ref_no_o_catg_15gh = deduction_ref_no_o_catg_15gh;
    }

    

    public String getEdit_right() {
        return edit_right;
    }

    public void setEdit_right(String edit_right) {
        this.edit_right = edit_right;
    }

    

    public Long getPrint_right() {
        return print_right;
    }

    public void setPrint_right(Long print_right) {
        this.print_right = print_right;
    }

    public String getQuery_right() {
        return query_right;
    }

    public void setQuery_right(String query_right) {
        this.query_right = query_right;
    }

    public Long getSpecial_right() {
        return special_right;
    }

    public void setSpecial_right(Long special_right) {
        this.special_right = special_right;
    }

    public Long getApproved_right() {
        return approved_right;
    }

    public void setApproved_right(Long approved_right) {
        this.approved_right = approved_right;
    }

    public Long getValuation_right() {
        return valuation_right;
    }

    public void setValuation_right(Long valuation_right) {
        this.valuation_right = valuation_right;
    }

    public Long getProcess_seqno() {
        return process_seqno;
    }

    public void setProcess_seqno(Long process_seqno) {
        this.process_seqno = process_seqno;
    }

    public String getAdd_right() {
        return add_right;
    }

    public void setAdd_right(String add_right) {
        this.add_right = add_right;
    }

    public String getDelete_right() {
        return delete_right;
    }

    public void setDelete_right(String delete_right) {
        this.delete_right = delete_right;
    }

    
    
    @Override
    public String toString() {
        globalUtilities.Util utl = new Util();
        return utl.printObjectAsString(this);
    }

}//end class
