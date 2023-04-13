/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.branchHierarchy;

/**
 *
 * @author kapil.gupta
 */
public class BranchHierarchyBean {

    private String entity_code;
    private String client_code;
    private String client_name;
    private String parent_code;
    private String bank_branch_code;
    private String code_level;
    private String client_level_type;
    private String total_branches;

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

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getParent_code() {
        return parent_code;
    }

    public void setParent_code(String parent_code) {
        this.parent_code = parent_code;
    }

    public String getBank_branch_code() {
        return bank_branch_code;
    }

    public void setBank_branch_code(String bank_branch_code) {
        this.bank_branch_code = bank_branch_code;
    }

    public String getCode_level() {
        return code_level;
    }

    public void setCode_level(String code_level) {
        this.code_level = code_level;
    }

    public String getClient_level_type() {
        return client_level_type;
    }

    public void setClient_level_type(String client_level_type) {
        this.client_level_type = client_level_type;
    }

    public String getTotal_branches() {
        return total_branches;
    }

    public void setTotal_branches(String total_branches) {
        this.total_branches = total_branches;
    }

}
