/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.challan;

/**
 *
 * @author aniket.naik
 */
public class ChallanGrossTotalSumList {

    private Double total_tds_challan_amt;
    private Double tds_challan_tds_amt;
    private Double tds_challan_int_amt;
    private Double tds_challan_other_amt;
    private Double allocated_amount;
    private Double balance_to_allocate;  // change long data type to double bcz its creates exception

    public Double getTotal_tds_challan_amt() {
        return total_tds_challan_amt;
    }

    public void setTotal_tds_challan_amt(Double total_tds_challan_amt) {
        this.total_tds_challan_amt = total_tds_challan_amt;
    }

    public Double getTds_challan_tds_amt() {
        return tds_challan_tds_amt;
    }

    public void setTds_challan_tds_amt(Double tds_challan_tds_amt) {
        this.tds_challan_tds_amt = tds_challan_tds_amt;
    }

    public Double getTds_challan_int_amt() {
        return tds_challan_int_amt;
    }

    public void setTds_challan_int_amt(Double tds_challan_int_amt) {
        this.tds_challan_int_amt = tds_challan_int_amt;
    }

    public Double getTds_challan_other_amt() {
        return tds_challan_other_amt;
    }

    public void setTds_challan_other_amt(Double tds_challan_other_amt) {
        this.tds_challan_other_amt = tds_challan_other_amt;
    }

    public Double getAllocated_amount() {
        return allocated_amount;
    }

    public void setAllocated_amount(Double allocated_amount) {
        this.allocated_amount = allocated_amount;
    }

    public Double getBalance_to_allocate() {
        return balance_to_allocate;
    }

    public void setBalance_to_allocate(Double balance_to_allocate) {
        this.balance_to_allocate = balance_to_allocate;
    }
}
