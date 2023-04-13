/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interceptors;

import globalUtilities.Util;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author akash.dev
 */
public class CalledUrl implements java.io.Serializable {

    private String actionName;
    private Map<String, Object> actionParas;
    private Date timestamp;

    public CalledUrl(String actionName, Map<String, Object> actionParas) {
        this.actionName = actionName;
        this.actionParas = actionParas;
    }

    public CalledUrl(String actionName, Map<String, Object> actionParas, Date timestamp) {
        this.actionName = actionName;
        this.actionParas = actionParas;
        this.timestamp = timestamp;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Map<String, Object> getActionParas() {
        return actionParas;
    }

    public void setActionParas(Map<String, Object> actionParas) {
        this.actionParas = actionParas;
    }

    @Override
    public String toString() {
        Util utl = new Util();
        return utl.printObjectAsString(this);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.actionName != null ? this.actionName.hashCode() : 0);
        hash = 47 * hash + (this.actionParas != null ? this.actionParas.hashCode() : 0);
        hash = 47 * hash + (this.timestamp != null ? this.timestamp.hashCode() : 0);
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
        final CalledUrl other = (CalledUrl) obj;
        if ((this.actionName == null) ? (other.actionName != null) : !this.actionName.equals(other.actionName)) {
            return false;
        }
        if (this.actionParas != other.actionParas && (this.actionParas == null || !this.actionParas.equals(other.actionParas))) {
            return false;
        }
        if (this.timestamp != other.timestamp && (this.timestamp == null || !this.timestamp.equals(other.timestamp))) {
            return false;
        }
        return true;
    }

}
