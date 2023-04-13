/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.branchGeneration;

import java.util.Date;

/**
 *
 * @author gaurav.khanzode
 */
public class BranchDetailsBean {

    private String sr_no;
    private String entity_code;
    private String client_code;
    private String code_level;
    private String bank_branch_code;
    private String client_name;
    private String parent_branch_code;
    private String parent_code;
    private String sg_parent_code;
    private String ssg_parent_code;
    private String sssg_parent_code;
    private String panno;
    private String tanno;
    private String state_code;
    private String state_name;
    private String client_catg_code;
    private String client_category_name;
    private Date lastupdate;

    //----filters
    private String codeLevel;

    public String getSr_no() {
        return sr_no;
    }

    public void setSr_no(String sr_no) {
        this.sr_no = sr_no;
    }

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

    public String getCode_level() {
        return code_level;
    }

    public void setCode_level(String code_level) {
        this.code_level = code_level;
    }

    public String getBank_branch_code() {
        return bank_branch_code;
    }

    public void setBank_branch_code(String bank_branch_code) {
        this.bank_branch_code = bank_branch_code;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getParent_branch_code() {
        return parent_branch_code;
    }

    public void setParent_branch_code(String parent_branch_code) {
        this.parent_branch_code = parent_branch_code;
    }

    public String getParent_code() {
        return parent_code;
    }

    public void setParent_code(String parent_code) {
        this.parent_code = parent_code;
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

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getClient_catg_code() {
        return client_catg_code;
    }

    public void setClient_catg_code(String client_catg_code) {
        this.client_catg_code = client_catg_code;
    }

    public String getClient_category_name() {
        return client_category_name;
    }

    public void setClient_category_name(String client_category_name) {
        this.client_category_name = client_category_name;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getCodeLevel() {
        return codeLevel;
    }

    public void setCodeLevel(String codeLevel) {
        this.codeLevel = codeLevel;
    }

    @Override
    public String toString() {
        return "\nBranchDetailsBean{" + "sr_no=" + sr_no + ", entity_code=" + entity_code + ", client_code=" + client_code + ", code_level=" + code_level + ", bank_branch_code=" + bank_branch_code + ", client_name=" + client_name + ", parent_branch_code=" + parent_branch_code + ", parent_code=" + parent_code + ", sg_parent_code=" + sg_parent_code + ", ssg_parent_code=" + ssg_parent_code + ", sssg_parent_code=" + sssg_parent_code + ", panno=" + panno + ", tanno=" + tanno + ", state_code=" + state_code + ", state_name=" + state_name + ", client_catg_code=" + client_catg_code + ", client_category_name=" + client_category_name + ", lastupdate=" + lastupdate + ", codeLevel=" + codeLevel + '}';
    }

}
