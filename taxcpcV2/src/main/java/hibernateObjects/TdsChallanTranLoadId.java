/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import javax.persistence.Column;

/**
 *
 * @author aniket.naik
 */
public class TdsChallanTranLoadId implements java.io.Serializable {

    @Column(name = "file_name", length = 1000, nullable = false)
    private String file_name;
    @Column(name = "slno", nullable = false)
    private String slno;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (this.file_name != null ? this.file_name.hashCode() : 0);
        hash = 13 * hash + (this.slno != null ? this.slno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TdsChallanTranLoadId other = (TdsChallanTranLoadId) obj;
        if ((this.file_name == null) ? (other.file_name != null) : !this.file_name.equals(other.file_name)) {
            return false;
        }
        if ((this.slno == null) ? (other.slno != null) : !this.slno.equals(other.slno)) {
            return false;
        }
        return true;
    }

    public TdsChallanTranLoadId() {
    }//end constructor

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getSlno() {
        return slno;
    }

    public void setSlno(String slno) {
        this.slno = slno;
    }

}
