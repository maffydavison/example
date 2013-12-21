package com.maffy.example.model;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by maffy davison on 12/10/13.
 */
public class Rank {

    private Map<String, Integer> check;

    public String[] rankHands(List<Player> players) {
        String [] result = new String [players.size()];
        int counter = 0;
        for (Player p : players) {
            String rank = "Player " + p.getPosition() + " " + getRank(p.getHand());
            result[counter] = rank;
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
        if (containsAnyRoyals()) {
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
                    if (containsAllRoyals()) {
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
                builder.append("(").append(getHighCard(hand)).append(" high)");
            }
        }

        return builder.toString();
    }

    private boolean isFlush(List<Card> hand) {
        boolean result = false;
        if (uniqueValues() == 5) {
            result = true;
            String suit = null;
            for (Card c : hand) {
                if (suit == null) {
                    suit = c.getSuit();
                } else if (!c.getSuit().equalsIgnoreCase(suit)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    private boolean isStraight(List<Card> hand) {
        boolean result = false;
        if (uniqueValues() == 5) {
            result = true;
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
        }


        return result;
    }

    private boolean containsAllRoyals() {
        return check.keySet().contains("A") && check.keySet().contains("K") && check.keySet().contains("Q") && check.keySet().contains("J");
    }

    private boolean containsAnyRoyals() {
        return check.keySet().contains("A") && (check.keySet().contains("K") || check.keySet().contains("Q") || check.keySet().contains("J"));
    }

    private boolean isFullHouse() {
        return uniqueValueFrequency(2) && uniqueValueFrequency(3);

    }

    private boolean isFourOfAKind() {
        return uniqueValues() == 2;
    }

    private boolean isThreeOfAKind() {
        return uniqueValueFrequency(3);
    }

    private boolean isTwoPair() {
        return uniqueValues() == 3 && !isThreeOfAKind();
    }

    private boolean isOnePair() {
        return uniqueValueFrequency(2);
    }

    private String getHighCard(List<Card> hand) {
        return hand.get(0).getName();
    }

    private int uniqueValues() {
        return check.size();
    }

    private boolean uniqueValueFrequency(int frequency) {
        return check.values().contains(frequency);
    }

    public boolean isStraightPattern(String test) {
        String straight = "[A|a][K|k][Q|q][J|j][1][0]|[K|k][Q|q][J|j][1][0][9]|[Q|q][J|j][1][0][9][8]|[J|j][1][0][9][8][7]|[1][0][9][8][7][6]|[9][8][7][6][5]|[8][7][6][5][4]|[7][6][5][4][3]|[6][5][4][3][2]|[5][4][3][2][A|a]";
        Pattern pattern = Pattern.compile(straight);
        Matcher matcher = pattern.matcher(test);
        return matcher.find();
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
