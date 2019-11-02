package org.nlb.security.utils;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
    public static String converter(String pw) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest rsa = MessageDigest.getInstance("SHA-256");
        rsa.update(pw.getBytes("UTF-8"));
        String sha256= Hex.encodeHexString(rsa.digest());
        return "{sha256}"+sha256;
    }
}
