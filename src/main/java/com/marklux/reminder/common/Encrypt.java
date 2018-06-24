package com.marklux.reminder.common;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by lumin on 18/6/23.
 */
public class Encrypt {
    private static String salt = "aafje9890312hkaf";
    public static String encrypt(String secret) throws Exception {
        try {
            secret = secret + salt;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(secret.getBytes());
            return new BigInteger(1,md.digest()).toString();
        }catch (Exception e) {
            throw new Exception("未知错误");
        }
    }

    public static boolean check(String raw,String old) throws Exception {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            raw = raw + salt;
            md.update(raw.getBytes());
            return new BigInteger(1,md.digest()).toString().equals(old);
        }catch (Exception e) {
            throw new Exception("未知错误");
        }
    }
}