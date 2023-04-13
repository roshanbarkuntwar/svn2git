/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author aniket.naik
 */
@Entity
@Table(name = "view_emp_catg")
public class ViewEmpCatg implements Serializable {

    @Id
    @Column(name = "emp_catg_code", length = 1, nullable = true)
    private String emp_catg_code;
    @Column(name = "emp_catg_name", length = 20, nullable = true)
    private String emp_catg_name;

    public ViewEmpCatg() {

    }

    public ViewEmpCatg(String emp_catg_code, String emp_catg_name) {
        this.emp_catg_code = emp_catg_code;
        this.emp_catg_name = emp_catg_name;
    }

    public String getEmp_catg_code() {
        return emp_catg_code;
    }

    public void setEmp_catg_code(String emp_catg_code) {
        this.emp_catg_code = emp_catg_code;
    }

    public String getEmp_catg_name() {
        return emp_catg_name;
    }

    public void setEmp_catg_name(String emp_catg_name) {
        this.emp_catg_name = emp_catg_name;
    }

}
