/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author gaurav.khanzode
 */
@Entity
@Table(name = "view_lhssys_latest_ass_year")
public class ViewLhssysLatestAssYear {

    @Id
    @Column(name = "slno")
    private Long slno;

    @Column(name = "latest_assessment_year")
    private String latest_assessment_year;

    public ViewLhssysLatestAssYear() {
    }

    public Long getSlno() {
        return slno;
    }

    public void setSlno(Long slno) {
        this.slno = slno;
    }

    public String getLatest_assessment_year() {
        return latest_assessment_year;
    }

    public void setLatest_assessment_year(String latest_assessment_year) {
        this.latest_assessment_year = latest_assessment_year;
    }

}
