/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package globalUtilities;

import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @
 */
public class EncryptDecryptUtil {

    private static final String salt = "tsrihgmlk";

    public static String encrypt(String strToEncrypt, String secret) {
        String encryptStr = null;
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);

            encryptStr = Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            encryptStr = null;
        }
        System.out.println("Encrypt String->" + encryptStr);
        return encryptStr;
    }

    public static String decrypt(String strToDecrypt, String secret) {
        String decryptStr = null;
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);

            decryptStr = new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            decryptStr = null;
        }
        System.out.println("Decrypt String->" + decryptStr);
        return decryptStr;
    }

    public static void main(String[] args) {
        //encrypt("All rights reserved 1987-2022 Copyright Lighthouse Info Systems Pvt. Ltd.||Source-code-owner:Shashank Maheshwari (Joint M.D. and C.E.O)||TaxCPC-Project-Manager:Akash dev(AVP-TECHNOLOGY)@LHISPL", "smakashlhs19898780");
       //decrypt("i7YRoSLhp71715Ud44NhPpkS9HjEpUJeE++77PDaJvsKS7VoHY29w4AUWquDwdpLQnTb05w0ZmaoTyp9v5va371ok5XHW0Ln+wE9Ku7npa4yUGcy7LN86Cbp+qvdEo17oCw5CPlvlxlpFeYEOIPC59/CY8Q/DfC+JD2hsahX8L2/wTMJFTNmmoRpqMOikhFxTo39UPEwp/zx7VOJFjMF4GMRCBtaH22VPSF6YEpmz8/vBGM/vZc+TsLMGn0a0zFb6dvhrB5732+dSAfYq9pVUg==","smakashlhs19898780");
    }

}
