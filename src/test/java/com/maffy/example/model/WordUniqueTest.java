package com.maffy.example.model;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Maffy Davison on 12/21/13.
 */
public class WordUniqueTest {

    WordUnique service;

    @Before
    public void setUp() throws Exception {
        service = new WordUnique();
    }

    @Test
    public void testGetRepeatedCharsAsString() throws Exception {
        Assert.assertEquals("bbca", service.getRepeatedCharsAsString("bbbbaaacccb", "ddddlkfjjlkjsdbbkajlkjkjc"));
        Assert.assertEquals("ca", service.getRepeatedCharsAsString("bbbbaaacccb", "ddddcajjjjkjlkjlkjjljlkjlk"));
    }
}
