package hibernateObjects;

import globalUtilities.Util;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author aniket.naik
 */
@Entity
@Table(name = "view_client_template")
public class ViewClientTemplate implements java.io.Serializable {

    @Id
    @Column(name = "template_code", length = 3, nullable = true)
    private String template_code;
    @Id
    @Column(name = "template_name", length = 16, nullable = true)
    private String template_name;
    @Column(name = "order_slno", length = 1, nullable = true)
    private String order_slno;
    @Column(name = "tepmlate_flag", length = 2, nullable = true)
    private String tepmlate_flag;
    @Column(name = "client_code", length = 15, nullable = true)
    private String client_code;
    @Column(name = "file_location", length = 250, nullable = true)
    private String file_location;
    @Column(name = "read_column_dtl", length = 30, nullable = true)
    private String read_column_dtl;
    @Column(name = "valid_file_code", length = 30, nullable = true)
    private String valid_file_code;
    @Column(name = "key_code", length = 16, nullable = true)
    private String key_code;
    @Column(name = "engine_type", length = 6, nullable = true)
    private String engine_type;
    @Column(name = "module_type", length = 6, nullable = true)
    private String module_type;
    @Column(name = "template_seq", length = 10, nullable = true)
    private String template_seq;
    @Column(name = "process_type", length = 10, nullable = true)
    private String process_type;

    public ViewClientTemplate() {
    }

    public ViewClientTemplate(String template_code, String template_name, String order_slno, String tepmlate_flag, String client_code, String file_location, String read_column_dtl, String valid_file_code, String key_code, String engine_type, String module_type) {
        this.template_code = template_code;
        this.template_name = template_name;
        this.order_slno = order_slno;
        this.tepmlate_flag = tepmlate_flag;
        this.client_code = client_code;
        this.file_location = file_location;
        this.read_column_dtl = read_column_dtl;
        this.valid_file_code = valid_file_code;
        this.key_code = key_code;
        this.engine_type = engine_type;
        this.module_type = module_type;
    }

    public String getClient_code() {
        return client_code;
    }

    public void setClient_code(String client_code) {
        this.client_code = client_code;
    }

    public String getTemplate_code() {
        return template_code;
    }

    public void setTemplate_code(String template_code) {
        this.template_code = template_code;
    }

    public String getTemplate_name() {
        return template_name;
    }

    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }

    public String getOrder_slno() {
        return order_slno;
    }

    public void setOrder_slno(String order_slno) {
        this.order_slno = order_slno;
    }

    public String getTepmlate_flag() {
        return tepmlate_flag;
    }

    public void setTepmlate_flag(String tepmlate_flag) {
        this.tepmlate_flag = tepmlate_flag;
    }

    public String getFile_location() {
        return file_location;
    }

    public void setFile_location(String file_location) {
        this.file_location = file_location;
    }

    public String getRead_column_dtl() {
        return read_column_dtl;
    }

    public void setRead_column_dtl(String read_column_dtl) {
        this.read_column_dtl = read_column_dtl;
    }

    public String getValid_file_code() {
        return valid_file_code;
    }

    public void setValid_file_code(String valid_file_code) {
        this.valid_file_code = valid_file_code;
    }

    public String getEngine_type() {
        return engine_type;
    }

    public void setEngine_type(String engine_type) {
        this.engine_type = engine_type;
    }

    @Override
    public String toString() {
        Util utl = new Util();
        return utl.printObjectAsString(this);
    }

    public String getKey_code() {
        return key_code;
    }

    public void setKey_code(String key_code) {
        this.key_code = key_code;
    }

    public String getModule_type() {
        return module_type;
    }

    public void setModule_type(String module_type) {
        this.module_type = module_type;
    }

    public String getTemplate_seq() {
        return template_seq;
    }

    public void setTemplate_seq(String template_seq) {
        this.template_seq = template_seq;
    }

    public String getProcess_type() {
        return process_type;
    }

    public void setProcess_type(String process_type) {
        this.process_type = process_type;
    }

}
