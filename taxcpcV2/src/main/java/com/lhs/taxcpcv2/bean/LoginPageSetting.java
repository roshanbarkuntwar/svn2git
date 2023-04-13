/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhs.taxcpcv2.bean;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gaurav.khanzode
 *
 * LoginPageSetting for pre-login logo and tagline setting.
 */
public class LoginPageSetting {

    public HashMap<String, Object> login_features = new HashMap<>();
    public Map sbi_map = new HashMap();
    public Map pnb_map = new HashMap();

    public LoginPageSetting() {
        //For SBI user
        sbi_map.put("logo_name", "TAXCPC-logo.png");//used for logo name
        sbi_map.put("logo_tagline", "");//user for tagline
        login_features.put("SBI", sbi_map);

        //
        pnb_map.put("logo_name", "");
        pnb_map.put("logo_tagline", "");
        login_features.put("PNB", pnb_map);

    }

    public HashMap getLoginFeaturesForUser(String user_suffix) {
        return (HashMap) login_features.get(user_suffix);
    }
}//end class
