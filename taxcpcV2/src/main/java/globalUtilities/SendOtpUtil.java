/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package globalUtilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 *
 * @author akash.dev
 */
public class SendOtpUtil {

    private globalUtilities.Util utl;

    public SendOtpUtil() {
        utl = new Util();
    }

    private void send_message(String URL2, String msg) throws UnsupportedEncodingException, MalformedURLException, IOException {
        String decodedString;
        String rest_credentials = URL2;
        String message = URLEncoder.encode(msg, "UTF-8");
        String value_to_call = rest_credentials + message;
        URL url = new URL(value_to_call);
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
        out.write(value_to_call);
//        out.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((decodedString = in.readLine()) != null) {
            String ack_text = decodedString;
            utl.generateLog(null, ack_text);
        }
        in.close();
    }

//    public static void main(String[] args) throws UnsupportedEncodingException, MalformedURLException, IOException {
//        String msg = "congrates4";
//        SendOtpMobile sm = new SendOtpMobile();
//        sm.send_message(URL2, msg);
//    }
}
