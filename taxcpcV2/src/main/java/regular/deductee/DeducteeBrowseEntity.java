/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.deductee;

/**
 *
 * @author ayushi.jain
 */
public class DeducteeBrowseEntity {

    public String slno;

    public String deductee_ref_code1;
    public String deductee_panno;
    public String deductee_name;
    public String name_as_panno;
    public String verifiedby_flag_name;
    public String deductee_panno_verified;
    public String account_no;

    public String getDeductee_panno() {
        return deductee_panno;
    }

    public void setDeductee_panno(String deductee_panno) {
        this.deductee_panno = deductee_panno;
    }

    public String getSlno() {
        return slno;
    }

    public void setSlno(String slno) {
        this.slno = slno;
    }

    public String getDeductee_ref_code1() {
        return deductee_ref_code1;
    }

    public void setDeductee_ref_code1(String deductee_ref_code1) {
        this.deductee_ref_code1 = deductee_ref_code1;
    }

    public String getDeductee_name() {
        return deductee_name;
    }

    public void setDeductee_name(String deductee_name) {
        this.deductee_name = deductee_name;
    }

    public String getName_as_panno() {
        return name_as_panno;
    }

    public void setName_as_panno(String name_as_panno) {
        this.name_as_panno = name_as_panno;
    }

    public String getDeductee_panno_verified() {
        return deductee_panno_verified;
    }

    public void setDeductee_panno_verified(String deductee_panno_verified) {
        this.deductee_panno_verified = deductee_panno_verified;
    }

    public String getVerifiedby_flag_name() {
        return verifiedby_flag_name;
    }

    public void setVerifiedby_flag_name(String verifiedby_flag_name) {
        this.verifiedby_flag_name = verifiedby_flag_name;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

}
