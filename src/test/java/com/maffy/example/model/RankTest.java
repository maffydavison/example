package com.maffy.example.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maryannfinnerty on 12/10/13.
 */
public class RankTest {

    Rank rank;
    private List<Card> royalFlush;
    private List<Card> straightFlush;
    private List<Card> fourOfAKind;
    private List<Card> fullHouse;
    private List<Card> straight;
    private List<Card> flush;

    private List<Card> threeOfAKind;
    private List<Card> twoPair;
    private List<Card> onePair;
    private List<Card> highCard;
    private List<Card> lowStraight;

    Player player_1;
    Player player_2;

    @Before
    public void setUp() {

        rank = new Rank();

        player_1 = new Player("maffy");
        player_2 = new Player("mark");

        royalFlush = new ArrayList<Card>();
        flush = new ArrayList<Card>();
        straightFlush = new ArrayList<Card>();
        fourOfAKind = new ArrayList<Card>();
        fullHouse = new ArrayList<Card>();
        straight = new ArrayList<Card>();

        threeOfAKind = new ArrayList<Card>();
        twoPair = new ArrayList<Card>();
        onePair = new ArrayList<Card>();
        highCard = new ArrayList<Card>();
        lowStraight = new ArrayList<Card>();



        royalFlush.add(new Card("H", "A"));
        royalFlush.add(new Card("H", "K"));
        royalFlush.add(new Card("H", "Q"));
        royalFlush.add(new Card("H", "J"));
        royalFlush.add(new Card("H", "10"));

        straightFlush.add(new Card("H", "4"));
        straightFlush.add(new Card("H", "5"));
        straightFlush.add(new Card("H", "6"));
        straightFlush.add(new Card("H", "7"));
        straightFlush.add(new Card("H", "8"));

        flush.add(new Card("D", "2"));
        flush.add(new Card("D", "4"));
        flush.add(new Card("D", "5"));
        flush.add(new Card("D", "9"));
        flush.add(new Card("D", "Q"));

        fullHouse.add(new Card("H", "4"));
        fullHouse.add(new Card("C", "4"));
        fullHouse.add(new Card("S", "4"));
        fullHouse.add(new Card("H", "7"));
        fullHouse.add(new Card("S", "7"));

        straight.add(new Card("H", "4"));
        straight.add(new Card("S", "5"));
        straight.add(new Card("C", "6"));
        straight.add(new Card("D", "7"));
        straight.add(new Card("H", "8"));

        lowStraight.add(new Card("H", "A"));
        lowStraight.add(new Card("S", "2"));
        lowStraight.add(new Card("C", "3"));
        lowStraight.add(new Card("D", "4"));
        lowStraight.add(new Card("H", "5"));

        fourOfAKind.add(new Card("H", "4"));
        fourOfAKind.add(new Card("S", "4"));
        fourOfAKind.add(new Card("C", "4"));
        fourOfAKind.add(new Card("D", "4"));
        fourOfAKind.add(new Card("H", "8"));

        threeOfAKind.add(new Card("H", "4"));
        threeOfAKind.add(new Card("S", "4"));
        threeOfAKind.add(new Card("D", "4"));
        threeOfAKind.add(new Card("H", "9"));
        threeOfAKind.add(new Card("D", "7"));

        twoPair.add(new Card("C", "4"));
        twoPair.add(new Card("D", "4"));
        twoPair.add(new Card("H", "8"));
        twoPair.add(new Card("D", "8"));
        twoPair.add(new Card("D", "7"));

        onePair.add(new Card("C", "4"));
        onePair.add(new Card("D", "4"));
        onePair.add(new Card("S", "9"));
        onePair.add(new Card("D", "2"));
        onePair.add(new Card("D", "7"));

        highCard.add(new Card("S", "7"));
        highCard.add(new Card("D", "K"));
        highCard.add(new Card("C", "6"));
        highCard.add(new Card("H", "2"));
        highCard.add(new Card("C", "8"));

    }
    @Test
    public void testRankHands1() throws Exception {
        player_1.setHand(royalFlush);
        player_1.setPosition(1);
        player_2.setHand(fourOfAKind);
        player_2.setPosition(2);

        List<Player> players = new ArrayList<Player>();
        players.add(player_1);
        players.add(player_2);

        String [] result = rank.rankHands(players);
        assert result[0].contains("Royal");
        assert result[1].contains("Four");

    }

    @Test
    public void testRankHands2() throws Exception {
        player_1.setHand(onePair);
        player_1.setPosition(1);
        player_2.setHand(straight);
        player_2.setPosition(2);

        List<Player> players = new ArrayList<Player>();
        players.add(player_1);
        players.add(player_2);

        String [] result = rank.rankHands(players);
        assert result[0].contains("One");
        assert result[1].contains("Straight");

    }

    @Test
    public void testRankHands3() throws Exception {
        player_1.setHand(twoPair);
        player_1.setPosition(1);
        player_2.setHand(highCard);
        player_2.setPosition(2);

        List<Player> players = new ArrayList<Player>();
        players.add(player_1);
        players.add(player_2);

        String [] result = rank.rankHands(players);
        assert result[0].contains("Two");
        assert result[1].contains("K high");

    }

    @Test
    public void testRankHands4() throws Exception {
        player_1.setHand(fullHouse);
        player_1.setPosition(1);
        player_2.setHand(threeOfAKind);
        player_2.setPosition(2);

        List<Player> players = new ArrayList<Player>();
        players.add(player_1);
        players.add(player_2);

        String [] result = rank.rankHands(players);
        assert result[0].contains("Full");
        assert result[1].contains("Three");

    }

    @Test
    public void testRankHands5() throws Exception {
        player_1.setHand(straight);
        player_1.setPosition(1);
        player_2.setHand(lowStraight);
        player_2.setPosition(2);

        List<Player> players = new ArrayList<Player>();
        players.add(player_1);
        players.add(player_2);

        String [] result = rank.rankHands(players);
        assert result[0].contains("Straight");
        assert result[1].contains("Straight");

    }

    @Test
    public void testIsStraightPattern() throws Exception {
        String test = "AKQJ10";
        boolean result = rank.isStraightPattern(test);
        assert result == true;
    }
}
