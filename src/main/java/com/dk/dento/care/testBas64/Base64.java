package com.dk.dento.care.testBas64;

import sun.misc.BASE64Encoder;

/**
 * Created by khana on 02/03/16.
 */
public class Base64 {

    public static void main(String[] arg) {

        System.out.println(new BASE64Encoder().encode("user:password".getBytes()));
    }
}
