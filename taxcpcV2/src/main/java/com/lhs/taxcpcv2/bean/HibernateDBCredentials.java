/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhs.taxcpcv2.bean;

/**
 *
 * @author akash.dev
 */
public class HibernateDBCredentials {

    public String l_db_server;
    public String l_db_server_id;
    public String l_db_userid;
    public String l_db_userpwd;
    public String l_db_url;

    public String getL_db_url() {
        return l_db_url;
    }

    public void setL_db_url(String l_db_url) {
        this.l_db_url = l_db_url;
    }

    public String getL_db_server() {
        return l_db_server;
    }

    public void setL_db_server(String l_db_server) {
        this.l_db_server = l_db_server;
    }

    public String getL_db_server_id() {
        return l_db_server_id;
    }

    public void setL_db_server_id(String l_db_server_id) {
        this.l_db_server_id = l_db_server_id;
    }

    public String getL_db_userid() {
        return l_db_userid;
    }

    public void setL_db_userid(String l_db_userid) {
        this.l_db_userid = l_db_userid;
    }

    public String getL_db_userpwd() {
        return l_db_userpwd;
    }

    public void setL_db_userpwd(String l_db_userpwd) {
        this.l_db_userpwd = l_db_userpwd;
    }

    @Override
    public String toString() {
        return "HibernateDBCredentials{" + "l_db_server=" + l_db_server + ", l_db_server_id=" + l_db_server_id + ", l_db_userid=" + l_db_userid + ", l_db_userpwd=" + l_db_userpwd + ", l_db_url=" + l_db_url + '}';
    }

}//end class
