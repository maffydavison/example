package com.maffy.example.model;

import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maryannfinnerty on 12/10/13.
 */
public class CardTest {
    List<Card> deck;
    List<Card> hand;

    @Before
    public void setUp() {
        String [] suits = {"S", "H", "C", "D"};
        String [] names = {"K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2", "A"};
        deck = new ArrayList<Card>();
        for (String s : suits) {
            for (String n : names) {
                deck.add(new Card(s, n));
            }
        }
        hand = new ArrayList<Card>();
    }


}
