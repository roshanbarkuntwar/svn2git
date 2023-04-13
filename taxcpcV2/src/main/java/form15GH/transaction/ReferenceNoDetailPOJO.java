/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form15GH.transaction;

import globalUtilities.Util;

/**
 *
 * @author aniket.naik
 */
public class ReferenceNoDetailPOJO {

    public String client_code;
    public String bank_branch_code;
    public String client_name;
    public String tanno;
    public String last_gen_15g;
    public String last_gen_15h;
    public String next_gen_15g;
    public String next_gen_15h;

    public String getClient_code() {
        return client_code;
    }

    public void setClient_code(String client_code) {
        this.client_code = client_code;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getTanno() {
        return tanno;
    }

    public void setTanno(String tanno) {
        this.tanno = tanno;
    }

    public String getLast_gen_15g() {
        return last_gen_15g;
    }

    public void setLast_gen_15g(String last_gen_15g) {
        this.last_gen_15g = last_gen_15g;
    }

    public String getLast_gen_15h() {
        return last_gen_15h;
    }

    public void setLast_gen_15h(String last_gen_15h) {
        this.last_gen_15h = last_gen_15h;
    }

    public String getNext_gen_15g() {
        return next_gen_15g;
    }

    public void setNext_gen_15g(String next_gen_15g) {
        this.next_gen_15g = next_gen_15g;
    }

    public String getNext_gen_15h() {
        return next_gen_15h;
    }

    public void setNext_gen_15h(String next_gen_15h) {
        this.next_gen_15h = next_gen_15h;
    }

    public String getBank_branch_code() {
        return bank_branch_code;
    }

    public void setBank_branch_code(String bank_branch_code) {
        this.bank_branch_code = bank_branch_code;
    }

    @Override
    public String toString() {
        Util utl = new Util();
        return utl.printObjectAsString(this);
    }

}
