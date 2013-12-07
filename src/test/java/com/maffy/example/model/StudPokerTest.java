package com.maffy.example.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maryannfinnerty on 12/6/13.
 */
public class StudPokerTest {

    private StudPoker game;

    /*
        Royal flush
    Straight flush
    four of a kind
    full house
    flush
    straight
    three of a kind
    two pair
    one pair
    high card
     */
    private List<String> royalFlush;
    private List<String> straightFlush;
    private List<String> fourOfAKind;
    private List<String> fullHouse;
    private List<String> straight;
    private List<String> flush;

    private List<String> threeOfAKind;
    private List<String> twoPair;
    private List<String> onePair;
    private List<String> highCard;

    @Before
    public void setUp() {
        game = new StudPoker();

        royalFlush = new ArrayList<String>();
        flush = new ArrayList<String>();
        straightFlush = new ArrayList<String>();
        fourOfAKind = new ArrayList<String>();
        fullHouse = new ArrayList<String>();
        straight = new ArrayList<String>();

        threeOfAKind = new ArrayList<String>();
        twoPair = new ArrayList<String>();
        onePair = new ArrayList<String>();
        highCard = new ArrayList<String>();

        royalFlush.add("Ah");
        royalFlush.add("Kh");
        royalFlush.add("Qh");
        royalFlush.add("Jh");
        royalFlush.add("10h");

        straightFlush.add("4h");
        straightFlush.add("5h");
        straightFlush.add("6h");
        straightFlush.add("7h");
        straightFlush.add("8h");

        flush.add("2d");
        flush.add("5d");
        flush.add("7d");
        flush.add("9d");
        flush.add("Jd");

        fullHouse.add("4h");
        fullHouse.add("4c");
        fullHouse.add("4s");
        fullHouse.add("7h");
        fullHouse.add("7s");

        straight.add("Kh");
        straight.add("Qd");
        straight.add("Js");
        straight.add("10h");
        straight.add("9c");

        fourOfAKind.add("4h");
        fourOfAKind.add("4c");
        fourOfAKind.add("4s");
        fourOfAKind.add("4d");
        fourOfAKind.add("8h");

        threeOfAKind.add("4c");
        threeOfAKind.add("4s");
        threeOfAKind.add("4d");
        threeOfAKind.add("Js");
        threeOfAKind.add("7d");

        twoPair.add("4c");
        twoPair.add("4d");
        twoPair.add("8s");
        twoPair.add("8d");
        twoPair.add("7s");

        onePair.add("4c");
        onePair.add("4d");
        onePair.add("9s");
        onePair.add("2d");
        onePair.add("7s");

        highCard.add("7s");
        highCard.add("Kd");
        highCard.add("6c");
        highCard.add("2h");
        highCard.add("8h");
    }


    @Test
    public void testGetRankRoyalFlush() throws Exception {
        String result = game.getRank(royalFlush);
        assert result != null;
        assert result.contains("Royal Flush");
    }

    @Test
    public void testGetRankStraight() throws Exception {
        String result = game.getRank(straight);
        assert result != null;
        assert result.contains("Straight");
    }
    @Test
    public void testGetRankStraightFlush() throws Exception {
        String result = game.getRank(straightFlush);
        assert result != null;
        assert result.contains("Straight Flush");
    }

    @Test
    public void testGetRankFlush() throws Exception {
        String result = game.getRank(flush);
        assert result != null;
        assert result.contains("Flush");
    }

    @Test
    public void testGetRankFullHouse() throws Exception {
        String result = game.getRank(fullHouse);
        assert result != null;
        assert result.contains("Full House");
    }

    @Test
    public void testGetRankFourOfAKind() throws Exception {

        String result = game.getRank(fourOfAKind);
        assert result != null;
        assert result.contains("Four of a kind");
    }

    @Test
    public void testGetRankThreeOfAKind() throws Exception {
        String result = game.getRank(threeOfAKind);
        assert result != null;
        assert result.contains("Three of a kind");
    }

    @Test
    public void testGetRankTwoPair() throws Exception {
        String result = game.getRank(twoPair);
        assert result != null;
        assert result.contains("Two pair");
    }

    @Test
    public void testGetRankOnePair() throws Exception {
        String result = game.getRank(onePair);
        assert result != null;
        assert result.contains("One pair");
    }

    @Test
    public void testGetRankHighCard() throws Exception {
        String result = game.getRank(highCard);
        assert result != null;
        assert result.contains("K");
    }

}
