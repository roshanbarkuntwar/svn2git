/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import globalUtilities.Util;
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
 * @author ayushi.jain
 */
@Entity
@Table(name = "user_mast")
public class UserMast implements java.io.Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "sequence-identity", parameters
            = @Parameter(name = "sequence", value = "user_code_seq"))
    @GeneratedValue(generator = "generator")
    @Column(name = "user_code", length = 15, nullable = false)
    private String user_code;
    @Column(name = "user_name", length = 100, nullable = true)
    private String user_name;
    @Column(name = "short_name", length = 15, nullable = true)
    private String short_name;
    @Column(name = "user_level", length = 1, nullable = true)
    private Long user_level;
    @Column(name = "login_id", length = 15, nullable = true)
    private String login_id;
    @Column(name = "login_pwd", length = 15, nullable = true)
    private String login_pwd;
    @Column(name = "entity_code", length = 15, nullable = true)
    private String entity_code;
    @Column(name = "client_code", length = 15, nullable = true)
    private String client_code;
    @Column(name = "default_acc_year", length = 15, nullable = true)
    private String default_acc_year;
    @Column(name = "default_quarter_no", length = 15, nullable = true)
    private String default_quarter_no;
    @Column(name = "default_tds_type_code", length = 15, nullable = true)
    private String default_tds_type_code;
    @Column(name = "add_right", length = 15, nullable = true)
    private Long add_right;
    @Column(name = "edit_right", length = 15, nullable = true)
    private Long edit_right;
    @Column(name = "delete_right", length = 15, nullable = true)
    private Long delete_right;

    @Column(name = "query_right", length = 15, nullable = true)
    private Long query_right;
    @Column(name = "print_right", length = 15, nullable = true)
    private Long print_right;
    @Column(name = "approve_right", length = 15, nullable = true)
    private Long approve_right;
    @Column(name = "valuation_right", length = 15, nullable = true)
    private Long valuation_right;
    @Column(name = "special_right", length = 15, nullable = true)
    private Long special_right;
    @Column(name = "lastupdate", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastupdate;
    @Column(name = "flag", length = 15, nullable = true)
    private String flag;

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public Long getUser_level() {
        return user_level;
    }

    public void setUser_level(Long user_level) {
        this.user_level = user_level;
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

    public String getDefault_acc_year() {
        return default_acc_year;
    }

    public void setDefault_acc_year(String default_acc_year) {
        this.default_acc_year = default_acc_year;
    }

    public String getDefault_quarter_no() {
        return default_quarter_no;
    }

    public void setDefault_quarter_no(String default_quarter_no) {
        this.default_quarter_no = default_quarter_no;
    }

    public String getDefault_tds_type_code() {
        return default_tds_type_code;
    }

    public void setDefault_tds_type_code(String default_tds_type_code) {
        this.default_tds_type_code = default_tds_type_code;
    }

    public Long getAdd_right() {
        return add_right;
    }

    public void setAdd_right(Long add_right) {
        this.add_right = add_right;
    }

    public Long getEdit_right() {
        return edit_right;
    }

    public void setEdit_right(Long edit_right) {
        this.edit_right = edit_right;
    }

    public Long getDelete_right() {
        return delete_right;
    }

    public void setDelete_right(Long delete_right) {
        this.delete_right = delete_right;
    }

    public Long getQuery_right() {
        return query_right;
    }

    public void setQuery_right(Long query_right) {
        this.query_right = query_right;
    }

    public Long getPrint_right() {
        return print_right;
    }

    public void setPrint_right(Long print_right) {
        this.print_right = print_right;
    }

    public Long getApprove_right() {
        return approve_right;
    }

    public void setApprove_right(Long approve_right) {
        this.approve_right = approve_right;
    }

    public Long getValuation_right() {
        return valuation_right;
    }

    public void setValuation_right(Long valuation_right) {
        this.valuation_right = valuation_right;
    }

    public Long getSpecial_right() {
        return special_right;
    }

    public void setSpecial_right(Long special_right) {
        this.special_right = special_right;
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

    @Override
    public String toString() {
        globalUtilities.Util utl = new Util();
        return utl.printObjectAsString(this);
    }//End Method

}//End Class
