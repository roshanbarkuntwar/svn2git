/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import globalUtilities.Util;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ayushi.jain
 */
@Entity
//@Table(name = "QUICK_NAVIGATION_MAST_NEW")
@Table(name = "LHSSYS_APPV2_DASHBOARD_MENU")
public class QuickNavigationMast implements java.io.Serializable {

    @Id
//    @Column(name = "navigation_code", length = 8, nullable = false)
    @Column(name = "menu_code", length = 8, nullable = false)
    private Long navigation_code;

//    @Column(name = "navigation_name", length = 100, nullable = true)
    @Column(name = "menu_name", length = 100, nullable = true)
    private String navigation_name;

//    @Column(name = "navigation_link", length = 100, nullable = true)
    @Column(name = "menu_link", length = 100, nullable = true)
    private String navigation_link;

//    @Column(name = "navigation_details", length = 500, nullable = true)
    @Column(name = "menu_details", length = 500, nullable = true)
    private String navigation_details;

    @Column(name = "module_type", length = 2, nullable = true)
    private String module_type;

    @Column(name = "order_by", length = 2, nullable = true)
    private Integer order_by;

    @Column(name = "entity_code", length = 100, nullable = true)
    private String entity_code;

    @Column(name = "active_flag", length = 1, nullable = true)
    private String active_flag;

    public QuickNavigationMast() {
    }

    public Long getNavigation_code() {
        return navigation_code;
    }

    public void setNavigation_code(Long navigation_code) {
        this.navigation_code = navigation_code;
    }

    public String getNavigation_name() {
        return navigation_name;
    }

    public void setNavigation_name(String navigation_name) {
        this.navigation_name = navigation_name;
    }

    public String getNavigation_link() {
        return navigation_link;
    }

    public void setNavigation_link(String navigation_link) {
        this.navigation_link = navigation_link;
    }

    public String getNavigation_details() {
        return navigation_details;
    }

    public void setNavigation_details(String navigation_details) {
        this.navigation_details = navigation_details;
    }

    public String getModule_type() {
        return module_type;
    }

    public void setModule_type(String module_type) {
        this.module_type = module_type;
    }

    public Integer getOrder_by() {
        return order_by;
    }

    public void setOrder_by(Integer order_by) {
        this.order_by = order_by;
    }

    public String getEntity_code() {
        return entity_code;
    }

    public void setEntity_code(String entity_code) {
        this.entity_code = entity_code;
    }

    public String getActive_flag() {
        return active_flag;
    }

    public void setActive_flag(String active_flag) {
        this.active_flag = active_flag;
    }

    @Override
    public String toString() {
        globalUtilities.Util utl = new Util();
        return utl.printObjectAsString(this);
    }
}
