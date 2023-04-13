/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.deductee;

/**
 *
 * @author ayushi.jain
 */
public class DeducteeFilterEntity {

    private String panno;
    private String deducteeRefNo;
    private String deducteeName;
    private String accountNo;
    private String verifiedFlag;

    public String getPanno() {
        return panno;
    }

    public void setPanno(String panno) {
        this.panno = panno;
    }

    public String getDeducteeRefNo() {
        return deducteeRefNo;
    }

    public void setDeducteeRefNo(String deducteeRefNo) {
        this.deducteeRefNo = deducteeRefNo;
    }

    public String getDeducteeName() {
        return deducteeName;
    }

    public void setDeducteeName(String deducteeName) {
        this.deducteeName = deducteeName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getVerifiedFlag() {
        return verifiedFlag;
    }

    public void setVerifiedFlag(String verifiedFlag) {
        this.verifiedFlag = verifiedFlag;
    }

}
