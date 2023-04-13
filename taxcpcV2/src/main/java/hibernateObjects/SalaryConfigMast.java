/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import globalUtilities.Util;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;


/**
 *
 * @author bhawna.agrawal
 */
@Entity
@Table(name = "salary_config_mast")
@IdClass(SalaryConfigMastId.class)
public class SalaryConfigMast implements Serializable {

    @Id
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date from_date;
    @Column(name = "to_date", nullable = true, unique = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date to_date;
    @Column(name = "afhead", length = 240, nullable = false)
    private String afhead;
    @Column(name = "afcode", length = 5, nullable = true)
    private String afcode;
    @Column(name = "afratei", length = 1, nullable = false)
    private String afratei;
    @Column(name = "afrate", length = 9, precision = 2, nullable = true)
    private Long afrate;
    @Column(name = "aflogic", length = 100, nullable = true)
    private String aflogic;
    @Column(name = "afsys", length = 1, nullable = false)
    private String afsys;
    @Column(name = "user_code", length = 8, nullable = false)
    private String user_code;
    @Column(name = "lastupdate", nullable = false, insertable = false, columnDefinition = "DATE default sysdate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastupdate;
    @Column(name = "afmin_amount", length = 9, precision = 2, nullable = true)
    private Long afmin_amount;
    @Column(name = "afmax_amount", length = 9, precision = 2, nullable = true)
    private Long afmax_amount;
    @Column(name = "afamt_limit", length = 9, precision = 2, nullable = true)
    private Long afamt_limit;
    @Id
    private String itax_catg;
    @Id
    private String afno;
    @Column(name = "itax_order", nullable = true)
    private Long itax_order;
    @Column(name = "parent_afcode", length = 5, nullable = true)
    private String parent_afcode;
    @Column(name = "formula_column", length = 1000, nullable = true)
    private String formula_column;
    @Column(name = "code_level", length = 2, nullable = true)
    private Long code_level;
    @Column(name = "dependent_column", length = 1000, nullable = true)
    private String dependent_column;
    @Column(name = "applicable_flag", length = 1, nullable = true)
    private String applicable_flag;
    @Column(name = "validate_flag", length = 1, nullable = false)
    private String validate_flag;
    @Column(name = "mandatory_flag", length = 1, nullable = true)
    private String mandatory_flag;
    @Column(name = "period_flag", length = 1, nullable = true)
    private String period_flag;
    @Column(name = "lov_seq_id", nullable = true)
    private Long lov_seq_id;
    @Column(name = "sql_text", length = 4000, nullable = true)
    private String sql_text;
    @Column(name = "exempt_flag", length = 1, nullable = true)
    private String exempt_flag;
    @Column(name = "negative_flag", length = 1, nullable = true)
    private String negative_flag;

    public SalaryConfigMast() {
        this.user_code = "SHASHANK";
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    public String getAfhead() {
        return afhead;
    }

    public void setAfhead(String afhead) {
        this.afhead = afhead;
    }

    public String getAfcode() {
        return afcode;
    }

    public void setAfcode(String afcode) {
        this.afcode = afcode;
    }

    public String getAfratei() {
        return afratei;
    }

    public void setAfratei(String afratei) {
        this.afratei = afratei;
    }

    public Long getAfrate() {
        return afrate;
    }

    public void setAfrate(Long afrate) {
        this.afrate = afrate;
    }

    public String getAflogic() {
        return aflogic;
    }

    public void setAflogic(String aflogic) {
        this.aflogic = aflogic;
    }

    public String getAfsys() {
        return afsys;
    }

    public void setAfsys(String afsys) {
        this.afsys = afsys;
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

    public Long getAfmin_amount() {
        return afmin_amount;
    }

    public void setAfmin_amount(Long afmin_amount) {
        this.afmin_amount = afmin_amount;
    }

    public Long getAfmax_amount() {
        return afmax_amount;
    }

    public void setAfmax_amount(Long afmax_amount) {
        this.afmax_amount = afmax_amount;
    }

    public Long getAfamt_limit() {
        return afamt_limit;
    }

    public void setAfamt_limit(Long afamt_limit) {
        this.afamt_limit = afamt_limit;
    }

    public String getItax_catg() {
        return itax_catg;
    }

    public void setItax_catg(String itax_catg) {
        this.itax_catg = itax_catg;
    }

    public String getAfno() {
        return afno;
    }

    public void setAfno(String afno) {
        this.afno = afno;
    }

    public Long getItax_order() {
        return itax_order;
    }

    public String getParent_afcode() {
        return parent_afcode;
    }

    public void setParent_afcode(String parent_afcode) {
        this.parent_afcode = parent_afcode;
    }

    public String getFormula_column() {
        return formula_column;
    }

    public void setFormula_column(String formula_column) {
        this.formula_column = formula_column;
    }

    public Long getCode_level() {
        return code_level;
    }

    public void setCode_level(Long code_level) {
        this.code_level = code_level;
    }

    public String getDependent_column() {
        return dependent_column;
    }

    public void setDependent_column(String dependent_column) {
        this.dependent_column = dependent_column;
    }

    public void setItax_order(Long itax_order) {
        this.itax_order = itax_order;
    }

    public String getApplicable_flag() {
        return applicable_flag;
    }

    public void setApplicable_flag(String applicable_flag) {
        this.applicable_flag = applicable_flag;
    }

    public String getValidate_flag() {
        return validate_flag;
    }

    public void setValidate_flag(String validate_flag) {
        this.validate_flag = validate_flag;
    }

    public String getMandatory_flag() {
        return mandatory_flag;
    }

    public void setMandatory_flag(String mandatory_flag) {
        this.mandatory_flag = mandatory_flag;
    }

    public String getPeriod_flag() {
        return period_flag;
    }

    public void setPeriod_flag(String period_flag) {
        this.period_flag = period_flag;
    }

    public Long getLov_seq_id() {
        return lov_seq_id;
    }

    public void setLov_seq_id(Long lov_seq_id) {
        this.lov_seq_id = lov_seq_id;
    }

    public String getSql_text() {
        return sql_text;
    }

    public void setSql_text(String sql_text) {
        this.sql_text = sql_text;
    }

    public String getExempt_flag() {
        return exempt_flag;
    }

    public void setExempt_flag(String exempt_flag) {
        this.exempt_flag = exempt_flag;
    }

    public String getNegative_flag() {
        return negative_flag;
    }

    public void setNegative_flag(String negative_flag) {
        this.negative_flag = negative_flag;
    }
    

    @Override
    public String toString() {
        Util utl = new Util();
        return utl.printObjectAsString(this);
    }
}
