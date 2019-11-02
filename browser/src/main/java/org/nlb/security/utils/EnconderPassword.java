package org.nlb.security.utils;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EnconderPassword {
    public static String EncoderPW(String message) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String newMessage = "nlb"+message+"nlb";//增加点长度
        Base64.Encoder encoder = Base64.getEncoder();
        String encodeToString = encoder.encodeToString(md5.digest(newMessage.getBytes("UTF-8")));//MD5进行首次加密
        String subEncodeToString = encodeToString.substring(1,encodeToString.length()-3);//去除MD5后的等号，防止让他人猜测
        String nextEncodeToString  = "^&*()"+subEncodeToString+"%$#@!";//加点特殊字符，不对称
        MessageDigest sha = MessageDigest.getInstance("SHA");
        sha.update(nextEncodeToString.getBytes("UTF-8"));
        String shaPW = Hex.encodeHexString(sha.digest());
        String pw ="="+shaPW.substring(3,shaPW.length())+"=";//防止直接RSA解密
        return pw;//最后再来个SHA加密方法
    }
}
