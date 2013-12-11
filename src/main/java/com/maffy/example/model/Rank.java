package com.maffy.example.model;

import java.util.*;

/**
 * Created by maryannfinnerty on 12/10/13.
 */
public class Rank {

    private Map<String, Integer> check;

    public String[] rankHands(List<Player> players) {
        String [] result = new String [players.size()];
        int counter = 0;
        for (Player p : players) {
            StringBuilder builder = new StringBuilder();
            builder.append("Player " + p.getPosition() + " ");
            builder.append(getRank(p.getHand()));
            result[counter] = builder.toString();
            counter++;
        }
        return result;
    }

    private List<Card> fixAceValue(List<Card> hand) {
        List<Card> result = new ArrayList<Card>();
        for (Card c : hand) {
            if (c.getValue() == 1) {
                c.setValue(14);
            }
            result.add(c);
        }
        return result;
    }

    private String getRank(List<Card> hand) {

        check = getStringIntegerMap(hand);
        if (containsAnyRoyals(hand)) {
            hand = fixAceValue(hand);
        }
        Collections.sort(hand);

        StringBuilder builder = new StringBuilder();
        if (hand == null || hand.isEmpty()) {
            builder.append("Hand provided is null or empty.");
        } else if (hand.size() < 5)  {
            builder.append("Hand size less than 5.  Ranking requires 5 cards.");
        } else {

            builder.append("Hand : ");
            for (Card s : hand) {
                builder.append(s.toString() + " ");
            }

            if (isFlush(hand)) {
                if (isStraight(hand)) {
                    if (containsAllRoyals(hand)) {
                        builder.append("(Royal Flush)");
                    } else {
                        builder.append("(Straight Flush)");
                    }
                } else {
                    builder.append("(Flush)");
                }
            } else if (isStraight(hand)) {
                builder.append("(Straight)");
            } else if (isFullHouse()) {
                builder.append("(Full House)");
            } else if (isFourOfAKind()) {
                builder.append("(Four of a kind)");
            } else if (isThreeOfAKind()) {
                builder.append("(Three of a kind)");
            } else if (isTwoPair()) {
                builder.append("(Two pair)");
            }  else if (isOnePair()) {
                builder.append("(One pair)");
            } else {
                // get High card
                builder.append("(" + getHighCard(hand) + " high)");
            }
        }

        return builder.toString();
    }

    private boolean isFlush(List<Card> hand) {
        boolean result = true;
        String suit = null;
        for (Card c : hand) {
            if (suit == null) {
                suit = c.getSuit();
            } else if (!c.getSuit().equalsIgnoreCase(suit)) {
                result = false;
                break;
            }
        }
        return result;
    }

    private boolean isStraight(List<Card> hand) {
        boolean result = true;
        int tester = -1;
        for (Card c : hand) {
           if (tester == -1) {
               tester = c.getValue();
           } else if (tester == 1 && c.getValue() == 13) {
               tester = 13;
           } else if (c.getValue() != tester - 1) {
               result = false;
               break;
           } else {
               tester = c.getValue();
           }
        }


        return result;
    }

    private boolean containsAllRoyals(List<Card> hand) {

        return check.keySet().contains("A") && check.keySet().contains("K") && check.keySet().contains("Q") && check.keySet().contains("J");
    }

    private boolean containsAnyRoyals(List<Card> hand) {

        return check.keySet().contains("A") && (check.keySet().contains("K") || check.keySet().contains("Q") || check.keySet().contains("J"));
    }

    private boolean isFullHouse() {
        boolean result = true;

        if (check.keySet().size() == 2) {
            if (!(check.values().contains(2) && check.values().contains(3))) {
                result = false;
            }
        } else {
            result = false;
        }

        return result;

    }

    private boolean isFourOfAKind() {
        boolean result = true;

        if (check.size() != 2) {
            result = false;
        }

        return result;
    }

    private boolean isThreeOfAKind() {
        boolean result = true;
        if (!check.values().contains(3)){
            result = false;
        }
        return result;
    }

    private boolean isTwoPair() {
        boolean result = true;
        if (check.size() != 3) {
            result = false;
        } else if (!check.values().contains(2)) {
            result = false;
        }
        return result;
    }

    private boolean isOnePair() {
        boolean result = true;
        if (!check.values().contains(2)) {
            result = false;
        }
        return result;
    }

    private String getHighCard(List<Card> hand) {
        return hand.get(0).getName();
    }

    private Map<String, Integer> getStringIntegerMap(List<Card> hand) {
        Map<String, Integer> check = new HashMap<String, Integer>();

        for (Card c : hand) {
            Integer test = check.get(c.getName());
            if (test == null) {
                check.put(c.getName(), 1);
            } else {
                check.put(c.getName(), ++test);
            }
        }
        return check;
    }


}
