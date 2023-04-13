/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.salaryNew;

/**
 *
 * @author gaurav.khanzode
 */
public class SalaryTranLoadFilter {

    private String deductee_panno;
    private String deductee_name;
    private String deductee_catg;
    private String deduction_catg;
    private String duplicate_record_flag;
    private String tax_115bac_flag;
    private String client_bank_branch_code;
    private String challan_bank_branch_code;

    public String getClient_bank_branch_code() {
        return client_bank_branch_code;
    }

    public void setClient_bank_branch_code(String client_bank_branch_code) {
        this.client_bank_branch_code = client_bank_branch_code;
    }

    public String getChallan_bank_branch_code() {
        return challan_bank_branch_code;
    }

    public void setChallan_bank_branch_code(String challan_bank_branch_code) {
        this.challan_bank_branch_code = challan_bank_branch_code;
    }

    public String getTax_115bac_flag() {
        return tax_115bac_flag;
    }

    public void setTax_115bac_flag(String tax_115bac_flag) {
        this.tax_115bac_flag = tax_115bac_flag;
    }

    public String getDeductee_catg() {
        return deductee_catg;
    }

    public String getDeductee_name() {
        return deductee_name;
    }

    public String getDeductee_paano() {
        return deductee_panno;
    }

    public String getDeduction_catg() {
        return deduction_catg;
    }

    public void setDeductee_catg(String deductee_catg) {
        this.deductee_catg = deductee_catg;
    }

    public void setDeductee_name(String deductee_name) {
        this.deductee_name = deductee_name;
    }

    public void setDeductee_paano(String deductee_paano) {
        this.deductee_panno = deductee_paano;
    }

    public void setDeduction_catg(String deduction_catg) {
        this.deduction_catg = deduction_catg;
    }

    public String getDeductee_panno() {
        return deductee_panno;
    }

    public void setDeductee_panno(String deductee_panno) {
        this.deductee_panno = deductee_panno;
    }

    public String getDuplicate_record_flag() {
        return duplicate_record_flag;
    }

    public void setDuplicate_record_flag(String duplicate_record_flag) {
        this.duplicate_record_flag = duplicate_record_flag;
    }

    @Override
    public String toString() {
        return "SalaryTranLoadFilter{" + "deductee_panno=" + deductee_panno + ",\n deductee_name=" + deductee_name + ",\n deductee_catg=" + deductee_catg
                + ",\n deduction_catg=" + deduction_catg + ",\n duplicate_record_flag=" + duplicate_record_flag + '}';
    }

}
