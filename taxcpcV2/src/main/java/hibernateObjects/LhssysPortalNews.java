/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author ankush.jangle
 */
@Entity
@Table(name = "LHSSYS_PORTAL_NEWS")
public class LhssysPortalNews implements Serializable {

    @Id
    @Column(name = "news_id", nullable = false)
    private Long news_id;
    @Column(name = "news_header", length = 500)
    private String news_header;
    @Column(name = "news_type", length = 1)
    private String news_type;
    @Column(name = "news_text", length = 4000)
    private String news_text;
    @Column(name = "news_attachment_path", length = 500)
    private String news_attachment_path;
    @Column(name = "news_external_link", length = 500)
    private String news_external_link;

//  @Column(name = "client_code_str", length = 2000)
//  private String client_code_str;
    @Column(name = "entity_code_str", length = 200)
    private String entity_code_str;
    @Column(name = "to_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date to_date;
    @Column(name = "from_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date from_date;
    @Column(name = "status", length = 1, nullable = false)
    private String status;
    @Column(name = "lastupdate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastupdate;
    @Column(name = "createdby", length = 8)
    private String createdby;
    @Column(name = "createddate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createddate;
    @Column(name = "approvedby", length = 8)
    private String approvedby;
    @Column(name = "approveddate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date approveddate;
    @Column(name = "user_code", length = 8)
    private String user_code;

    public Long getNews_id() {
        return news_id;
    }

    public void setNews_id(Long news_id) {
        this.news_id = news_id;
    }

    public String getNews_header() {
        return news_header;
    }

    public void setNews_header(String news_header) {
        this.news_header = news_header;
    }

    public String getNews_type() {
        return news_type;
    }

    public void setNews_type(String news_type) {
        this.news_type = news_type;
    }

    public String getNews_text() {
        return news_text;
    }

    public void setNews_text(String news_text) {
        this.news_text = news_text;
    }

    public String getNews_attachment_path() {
        return news_attachment_path;
    }

    public void setNews_attachment_path(String news_attachment_path) {
        this.news_attachment_path = news_attachment_path;
    }

    public String getNews_external_link() {
        return news_external_link;
    }

    public void setNews_external_link(String news_external_link) {
        this.news_external_link = news_external_link;
    }

//    public String getClient_code_str() {
//        return client_code_str;
//    }
//
//    public void setClient_code_str(String client_code_str) {
//        this.client_code_str = client_code_str;
//    }
    public String getEntity_code_str() {
        return entity_code_str;
    }

    public void setEntity_code_str(String entity_code_str) {
        this.entity_code_str = entity_code_str;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
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

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    @Override
    public String toString() {
        return "LhssysPortalNews{" + "news_id=" + news_id + ", news_header=" + news_header + ", news_type=" + news_type + ", news_text=" + news_text + ", news_attachment_path=" + news_attachment_path + ", news_external_link=" + news_external_link + ", entity_code_str=" + entity_code_str + ", to_date=" + to_date + ", from_date=" + from_date + ", status=" + status + ", lastupdate=" + lastupdate + ", createdby=" + createdby + ", createddate=" + createddate + ", approvedby=" + approvedby + ", approveddate=" + approveddate + ", user_code=" + user_code + '}';
    }

//    @Override
//    public String toString() {
//        return "LhssysPortalNews{" + "news_id=" + news_id + ", news_header=" + news_header + ", news_type=" + news_type + ", news_text=" + news_text + ", news_attachment_path=" + news_attachment_path + ", news_external_link=" + news_external_link + ", client_code_str=" + client_code_str + ", entity_code_str=" + entity_code_str + ", to_date=" + to_date + ", from_date=" + from_date + ", status=" + status + ", lastupdate=" + lastupdate + ", createdby=" + createdby + ", createddate=" + createddate + ", approvedby=" + approvedby + ", approveddate=" + approveddate + ", user_code=" + user_code + '}';
//    }
    public LhssysPortalNews() {
    }

    public LhssysPortalNews(Long news_id, String news_header, String news_type, String news_text, String news_attachment_path, String news_external_link, String entity_code_str, Date to_date, Date from_date, String status, Date lastupdate, String createdby, Date createddate, String approvedby, Date approveddate, String user_code) {
        this.news_id = news_id;
        this.news_header = news_header;
        this.news_type = news_type;
        this.news_text = news_text;
        this.news_attachment_path = news_attachment_path;
        this.news_external_link = news_external_link;
        this.entity_code_str = entity_code_str;
        this.to_date = to_date;
        this.from_date = from_date;
        this.status = status;
        this.lastupdate = lastupdate;
        this.createdby = createdby;
        this.createddate = createddate;
        this.approvedby = approvedby;
        this.approveddate = approveddate;
        this.user_code = user_code;
    }

}
