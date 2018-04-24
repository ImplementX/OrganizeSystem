package com.tjrac.organization.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class EncryptionUtilTest {
    @Test
    public void encoderByMd5() throws Exception {
        System.out.println( EncryptionUtil.EncoderByMd5("123456"));

    }

}