package org.nlb.security.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BC {
    public static String bcEncoder(String password) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //加密"0"
        String encode = bCryptPasswordEncoder.encode("0");
        return encode;
    }


}

