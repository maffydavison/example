package com.maffy.example.model;

import java.util.*;

/**
 * Created by maryannfinnerty on 12/6/13.
 */
public class StudPoker {

    public String getRank(List<String> hand) {

        StringBuilder builder = new StringBuilder();
        if (hand == null || hand.isEmpty()) {
            builder.append("Hand provided is null or empty.");
        } else if (hand.size() < 5)  {
            builder.append("Hand size less than 5.  Ranking requires 5 cards.");
        } else {

            builder.append("Hand : ");
            for (String s : hand) {
                builder.append(s + " ");
            }

            if (isFlush(hand)) {
                if (isStraight(hand)) {
                    if (containsAllRoyals(getValuesFromHand(hand))) {
                        builder.append("(Royal Flush)");
                    } else {
                        builder.append("(Straight Flush)");
                    }
                } else {
                    builder.append("(Flush)");
                }
            } else if (isStraight(hand)) {
                builder.append("(Straight)");
            } else if (isFullHouse(hand)) {
                builder.append("(Full House)");
            } else if (isFourOfAKind(hand)) {
                builder.append("(Four of a kind)");
            } else if (isThreeOfAKind(hand)) {
                builder.append("(Three of a kind)");
            } else if (isTwoPair(hand)) {
                builder.append("(Two pair)");
            }  else if (isOnePair(hand)) {
                builder.append("(One pair)");
            } else {
                // get High card
                String card = getHighCard(hand);
                builder.append("(" + card + " high)");
            }
        }

        return builder.toString();
    }

    private boolean isOnePair(List<String> hand) {
        boolean result = true;
        Map<String, Integer> resultMap = findValueMatches(hand);
        Iterator iterator = resultMap.values().iterator();
        int check = 0;
        while (iterator.hasNext()) {
            Integer test = (Integer) iterator.next();
            if (test == 2) {
                check++;
            }
        }

        if (check != 1) {
            result = false;
        }

        return result;

    }

    private boolean isTwoPair(List<String> hand) {
        boolean result = true;
        Map<String, Integer> resultMap = findValueMatches(hand);
        Iterator iterator = resultMap.values().iterator();
        int check = 0;
        while (iterator.hasNext()) {
            Integer test = (Integer) iterator.next();
            if (test == 2) {
                check++;
            }
        }

        if (check != 2) {
            result = false;
        }

        return result;

    }

    private boolean isThreeOfAKind(List<String> hand) {
        boolean result = true;
        Map<String, Integer> resultMap = findValueMatches(hand);
        int check = -1;
        int check1 = -1;

        for (String s : resultMap.keySet()) {
            if (check == -1) {
                check = resultMap.get(s);
            } else if (check1 == -1) {
                check1 = resultMap.get(s);
            } else if (check1 != 3 && check != 3 && resultMap.get(s) != 3) {
                result = false;
            }
        }


        return result;
    }

    private boolean isFourOfAKind(List<String> hand) {
        boolean result = true;
        Map<String, Integer> resultMap = findValueMatches(hand);
        int check = -1;

        for (String s : resultMap.keySet()) {
            if (check == -1) {
                check = resultMap.get(s);
            } else {
                if (check == 4 && resultMap.get(s) != 1) {
                    result = false;
                } else if (check == 1 && resultMap.get(s) != 4) {
                    result = false;
                }
            }
        }


        return result;
    }

    private boolean isFullHouse(List<String> hand) {
        boolean result = true;
        Map<String, Integer> resultMap = findValueMatches(hand);
        int check = -1;
        int check1 = -1;

        for (String s : resultMap.keySet()) {
           if (check == -1) {
               check = resultMap.get(s);
           } else if (check1 == -1){
               check1 = resultMap.get(s);
           } else {
               result = false;
           }
        }

        if (!(check == 3 && check1 == 2 || check1 == 3 && check == 2)) {
            result = false;
        }

        return result;
    }

    private boolean isStraight(List<String> hand) {
        boolean result = true;
        List<String> valuesFromHand = convertRoyalValues(getValuesFromHand(hand));
        Collections.sort(valuesFromHand);
        int tester = -1;
        for (String s : valuesFromHand) {
            if (tester == -1) {
                tester = getIntValue(s, false);
            } else if (getIntValue(s, false) == tester +1 || getIntValue(s, false) == tester -1){
                tester = getIntValue(s, false);
            } else {
                result = false;
                break;
            }
        }

        return result;
    }

    private boolean isFlush(List<String> hand) {
        Map<String, Integer> resultMap = findSuitMatches(hand);
        return (resultMap.keySet().size() == 1) && (resultMap.values().iterator().next() == 5);

    }

    private List<String> convertRoyalValues(List<String> values) {
        boolean high = false;
        if (values.contains("A") && values.contains("K")) {
              high = true;
        }
        List<String> result = new ArrayList<String>();
        for (String s : values) {
            int test = getIntValue(s, high);
            if (test < 10) {
                result.add("0" + String.valueOf(getIntValue(s, high)));
            } else {
                result.add(String.valueOf(getIntValue(s, high)));
            }
        }
        return result;
    }

    private Map<String, Integer>  findSuitMatches(List<String> hand) {
        Map<String, Integer> resultMap = new HashMap<String, Integer>();

            List<String> suitsFromHand = getSuitsFromHand(hand);
            Collections.sort(suitsFromHand);
            for (String s : suitsFromHand) {
                if (resultMap.isEmpty() || !resultMap.containsKey(s)) {
                    resultMap.put(s, 1);
                } else if (resultMap.containsKey(s)) {
                    int temp = resultMap.get(s);
                    resultMap.put(s, ++temp);
                }
            }

        return resultMap;

    }

    private Map<String, Integer> findValueMatches(List<String> hand) {
        Map<String, Integer> resultMap = new HashMap<String, Integer>();

            List<String> valuesFromHand = getValuesFromHand(hand);
            Collections.sort(valuesFromHand);
            for (String s : valuesFromHand) {
                if (resultMap.isEmpty() || !resultMap.containsKey(s)) {
                    resultMap.put(s, 1);
                } else if (resultMap.containsKey(s)) {
                    int temp = resultMap.get(s);
                    resultMap.put(s, ++temp);
                }
            }

        return resultMap;
    }


    private List<String> getSuitsFromHand(List<String> hand) {
        List<String> result = new ArrayList<String>();

            for (String s : hand) {
                if (s.length() > 2) {
                    result.add(String.valueOf(s.charAt(2)));
                } else {
                    result.add(String.valueOf(s.charAt(1)));
                }
            }

        return result;
    }

    private List<String> getValuesFromHand(List<String> hand) {
        List<String> result = new ArrayList<String>();

            for (String s : hand) {
                if (s.length() > 2) {
                    result.add(s.substring(0,2));
                } else {
                    result.add(String.valueOf(s.charAt(0)));
                }
            }

        return result;
    }

    private boolean containsAllRoyals(List<String> values) {
        return values.contains("A") && values.contains("K") && values.contains("Q") && values.contains("J");
    }
    private int getIntValue(String value, boolean high) {
        int result = 0;
        try {
            result = Integer.parseInt(value);
        } catch (NumberFormatException e) {
          // must be alpha
            if (value.equalsIgnoreCase("J")) {
                result = 11;
            } else if (value.equalsIgnoreCase("Q")) {
                result = 12;
            } else if (value.equalsIgnoreCase("K")) {
                result = 13;
            } else if (value.equalsIgnoreCase("A")) {
                result = high? 14 : 1;
            }
        }

        return result;
    }

    private String getHighCard(List<String> hand) {
        String result = null;
        List<String> values = convertRoyalValues(getValuesFromHand(hand));
        int high = 0;
        for (String s : values) {
            if (getIntValue(s, false) > high) {
                 high = getIntValue(s, false);
            }
        }
        if (high > 10 || high == 1) {
            if (high == 11) {
                result = "J";
            } else if (high == 12) {
                result = "Q";
            } else if (high == 13) {
                result = "K";
            } else if (high == 14 || high == 1) {
                result = "A";
            }
        } else {
            result = String.valueOf(high);
        }
        return result;
    }
}
