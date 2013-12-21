package com.maffy.example.model;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Maffy Davison on 12/20/13.
 */
public class NumberToTextTest {

    NumberToText service;

    @Before
    public void setUp() throws Exception {
        service = new NumberToText();
    }


    @Test
    public void testTranslate() throws Exception {

        Assert.assertEquals("one", service.translate(1));
        Assert.assertEquals("ten", service.translate(10));
        Assert.assertEquals("twenty", service.translate(20));
        Assert.assertEquals("twenty one", service.translate(21));
        Assert.assertEquals("one hundred", service.translate(100));
        Assert.assertEquals("one hundred fifty", service.translate(150));
        Assert.assertEquals("one thousand", service.translate(1000));
        Assert.assertEquals("one thousand one hundred fifty", service.translate(1150));

    }
}
