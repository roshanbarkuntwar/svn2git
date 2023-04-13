/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhs.taxcpcv2.bean;

import globalUtilities.Util;
import java.util.Date;

/**
 *
 * @author ayushi.jain
 */
public class Assessment {

    private String accYear;
    private String tdsTypeCode;
    private String quarterNo;
    private Date quarterFromDate;
    private Date quarterToDate;
    private Date yearBegDate;
    private Date yearEndDate;

    public String getAccYear() {
        return accYear;
    }

    public void setAccYear(String accYear) {
        this.accYear = accYear;
    }

    public String getTdsTypeCode() {
        return tdsTypeCode;
    }

    public void setTdsTypeCode(String tdsTypeCode) {
        this.tdsTypeCode = tdsTypeCode;
    }

    public String getQuarterNo() {
        return quarterNo;
    }

    public void setQuarterNo(String quarterNo) {
        this.quarterNo = quarterNo;
    }

    public Date getQuarterFromDate() {
        return quarterFromDate;
    }

    public void setQuarterFromDate(Date quarterFromDate) {
        this.quarterFromDate = quarterFromDate;
    }

    public Date getQuarterToDate() {
        return quarterToDate;
    }

    public void setQuarterToDate(Date quarterToDate) {
        this.quarterToDate = quarterToDate;
    }

    public Date getYearBegDate() {
        return yearBegDate;
    }

    public void setYearBegDate(Date yearBegDate) {
        this.yearBegDate = yearBegDate;
    }

    public Date getYearEndDate() {
        return yearEndDate;
    }

    public void setYearEndDate(Date yearEndDate) {
        this.yearEndDate = yearEndDate;
    }
    
    

    @Override
    public String toString() {
        globalUtilities.Util utl = new Util();
        return utl.printObjectAsString(this);
    }

}
