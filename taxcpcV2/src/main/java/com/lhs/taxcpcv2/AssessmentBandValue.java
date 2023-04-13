/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhs.taxcpcv2;

import dao.globalDBObjects.GetGlobalList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 *
 * @author Kapil Gupta
 */
public class AssessmentBandValue {

    private LinkedList<String> selectAssessmentyear;
    private LinkedHashMap<String, String> selectQuarter;
    private LinkedHashMap<String, String> selectTdsType;

    public AssessmentBandValue() {
        selectAssessmentyear = new LinkedList<>();
//        selectAssessmentyear.add("17-18");
//        selectAssessmentyear.add("18-19");
        selectAssessmentyear.add("19-20");
        selectAssessmentyear.add("20-21");
        selectAssessmentyear.add("21-22");
        selectAssessmentyear.add("22-23");

        selectQuarter = new LinkedHashMap<>();
        selectQuarter.put("1", "Q1");
        selectQuarter.put("2", "Q2");
        selectQuarter.put("3", "Q3");
        selectQuarter.put("4", "Q4");

        selectTdsType = new LinkedHashMap<>();
        GetGlobalList gb = new GetGlobalList();
        selectTdsType = gb.getTdsTypeList("V2");
//        selectTdsType.put("24Q", "24Q");
//        selectTdsType.put("26Q", "26Q");
//        selectTdsType.put("27Q", "27Q");
//        selectTdsType.put("27EQ", "27EQ");
//            tdsSectionList = gb.getSectionList(ass.getTdsTypeCode(), ass.getQuarterToDate());
    }

    public LinkedList<String> getSelectAssessmentyear() {
        return selectAssessmentyear;
    }

    public void setSelectAssessmentyear(LinkedList<String> selectAssessmentyear) {
        this.selectAssessmentyear = selectAssessmentyear;
    }

    public LinkedHashMap<String, String> getSelectQuarter() {
        return selectQuarter;
    }

    public void setSelectQuarter(LinkedHashMap<String, String> selectQuarter) {
        this.selectQuarter = selectQuarter;
    }

    public LinkedHashMap<String, String> getSelectTdsType() {
        return selectTdsType;
    }

    public void setSelectTdsType(LinkedHashMap<String, String> selectTdsType) {
        this.selectTdsType = selectTdsType;
    }

}
