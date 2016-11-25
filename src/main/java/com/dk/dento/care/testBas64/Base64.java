package com.dk.dento.care.testBas64;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sun.misc.BASE64Encoder;

/**
 * Created by khana on 02/03/16.
 */
public class Base64 {

    public static void main(String[] arg) {

        System.out.println(new BASE64Encoder().encode("password".getBytes()));

        System.out.println(new BCryptPasswordEncoder().encode("dentocare@123"));
    }
}
