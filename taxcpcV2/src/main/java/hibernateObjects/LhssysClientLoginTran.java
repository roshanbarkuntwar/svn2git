/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import java.sql.Timestamp;
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
 * @author akash.dev
 */
@Entity
@Table(name = "lhssys_client_login_tran")
public class LhssysClientLoginTran implements java.io.Serializable {

    @Column(name = "client_session_seqno", nullable = false)
    @GenericGenerator(name = "generator", strategy = "sequence-identity", parameters
            = @Parameter(name = "sequence", value = "lhssys_client_login_tran_seq"))
    @GeneratedValue(generator = "generator")
    @Id
    private Long client_session_seqno;
    @Column(name = "entity_code", length = 2, nullable = true)
    private String entity_code;
    @Column(name = "client_code", length = 15, nullable = false)
    private String client_code;
    @Column(name = "login_time", nullable = false)
    private Timestamp login_time;
    @Column(name = "logout_time", nullable = true)
    private Timestamp logout_time;
    @Column(name = "logout_method", length = 1, nullable = true)
    private String logout_method;
    @Column(name = "machine_name", length = 100, nullable = true)
    private String machine_name;
    @Column(name = "machine_ip", length = 24, nullable = true)
    private String machine_ip;
    @Column(name = "machine_browser", length = 200, nullable = true)
    private String machine_browser;
    @Column(name = "machine_os_name", length = 100, nullable = true)
    private String machine_os_name;
    @Column(name = "machine_other_info", length = 4000, nullable = true)
    private String machine_other_info;
    @Column(name = "user_code", length = 200, nullable = true)
    private String user_code;
    @Column(name = "lastupdate", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastupdate;
    @Column(name = "flag", length = 1, nullable = true)
    private String flag;

    public LhssysClientLoginTran() {
    }

    public Long getClient_session_seqno() {
        return client_session_seqno;
    }

    public void setClient_session_seqno(Long client_session_seqno) {
        this.client_session_seqno = client_session_seqno;
    }

    public String getClient_code() {
        return client_code;
    }

    public void setClient_code(String client_code) {
        this.client_code = client_code;
    }

    public Timestamp getLogin_time() {
        return login_time;
    }

    public void setLogin_time(Timestamp login_time) {
        this.login_time = login_time;
    }

    public Timestamp getLogout_time() {
        return logout_time;
    }

    public void setLogout_time(Timestamp logout_time) {
        this.logout_time = logout_time;
    }

    public String getLogout_method() {
        return logout_method;
    }

    public void setLogout_method(String logout_method) {
        this.logout_method = logout_method;
    }

    public String getMachine_name() {
        return machine_name;
    }

    public void setMachine_name(String machine_name) {
        this.machine_name = machine_name;
    }

    public String getMachine_ip() {
        return machine_ip;
    }

    public void setMachine_ip(String machine_ip) {
        this.machine_ip = machine_ip;
    }

    public String getMachine_browser() {
        return machine_browser;
    }

    public void setMachine_browser(String machine_browser) {
        this.machine_browser = machine_browser;
    }

    public String getMachine_os_name() {
        return machine_os_name;
    }

    public void setMachine_os_name(String machine_os_name) {
        this.machine_os_name = machine_os_name;
    }

    public String getMachine_other_info() {
        return machine_other_info;
    }

    public void setMachine_other_info(String machine_other_info) {
        this.machine_other_info = machine_other_info;
    }

    public String getEntity_code() {
        return entity_code;
    }

    public void setEntity_code(String entity_code) {
        this.entity_code = entity_code;
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

}
