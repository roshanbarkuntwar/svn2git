/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhs.taxcpcv2.bean;

/**
 *
 * @author ayushi.jain
 */
public class TokenStatusAttribs {

    private String tokenNo;
    private String templateCode;
    private String templateName;
    private String processStartTimestamp;
    private String processEndTimestamp;
    private String processStatus;
    private String processRemark;
    private String processType;
    private String processTypeName;
    private String procBatchFileName;

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    public String getProcessStartTimestamp() {
        return processStartTimestamp;
    }

    public void setProcessStartTimestamp(String processStartTimestamp) {
        this.processStartTimestamp = processStartTimestamp;
    }

    public String getProcessEndTimestamp() {
        return processEndTimestamp;
    }

    public void setProcessEndTimestamp(String processEndTimestamp) {
        this.processEndTimestamp = processEndTimestamp;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public String getProcessRemark() {
        return processRemark;
    }

    public void setProcessRemark(String processRemark) {
        this.processRemark = processRemark;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getProcessTypeName() {
        return processTypeName;
    }

    public void setProcessTypeName(String processTypeName) {
        this.processTypeName = processTypeName;
    }

    public String getProcBatchFileName() {
        return procBatchFileName;
    }

    public void setProcBatchFileName(String procBatchFileName) {
        this.procBatchFileName = procBatchFileName;
    }

    @Override
    public String toString() {
        return "TokenStatusAttribs{" + "tokenNo=" + tokenNo + ", templateCode=" + templateCode + ", templateName=" + templateName + ", processStartTimestamp=" + processStartTimestamp + ", processEndTimestamp=" + processEndTimestamp + ", processStatus=" + processStatus + ", processRemark=" + processRemark + ", processType=" + processType + ", processTypeName=" + processTypeName + '}';
    }

}
