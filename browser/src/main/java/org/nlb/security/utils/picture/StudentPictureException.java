package org.nlb.security.utils.picture;

import org.springframework.security.core.AuthenticationException;


public class StudentPictureException extends AuthenticationException {


    public StudentPictureException(String msg) {
        super(msg);
    }
}
