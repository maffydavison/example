package com.maffy.example.model;

import java.util.*;

/**
 * Created by maryannfinnerty on 12/6/13.
 */
public class StudPoker {

    public String getRank(List<String> hand) {
        StringBuilder builder = new StringBuilder();

        builder.append("Hand : ");
        for (String s : hand) {
            builder.append(s + " ");
        }

        if (isRoyalFlush(hand)) {
            builder.append("(Royal Flush)");
        } else if (isStraightFlush(hand)) {
            builder.append("(Straight Flush)");
        } else if (isStraight(hand)) {
            builder.append("(Straight)");
        } else if (isFlush(hand)) {
            builder.append("(Flush)");
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
        return builder.toString();
    }

    public boolean isOnePair(List<String> hand) {
        boolean result = true;
        Map<String, Integer> resultMap = findValueMatches(hand);
        Iterator iter = resultMap.values().iterator();
        int check = 0;
        while (iter.hasNext()) {
            Integer test = (Integer) iter.next();
            if (test.intValue() == 2) {
                check++;
            }
        }

        if (check != 1) {
            result = false;
        }

        return result;

    }

    public boolean isTwoPair(List<String> hand) {
        boolean result = true;
        Map<String, Integer> resultMap = findValueMatches(hand);
        Iterator iter = resultMap.values().iterator();
        int check = 0;
        while (iter.hasNext()) {
            Integer test = (Integer) iter.next();
            if (test.intValue() == 2) {
                check++;
            }
        }

        if (check != 2) {
            result = false;
        }

        return result;

    }

    public boolean isThreeOfAKind(List<String> hand) {
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

    public boolean isFourOfAKind(List<String> hand) {
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

    public boolean isFullHouse(List<String> hand) {
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

    public boolean isStraight(List<String> hand) {
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

    public boolean isFlush(List<String> hand) {
        Map<String, Integer> resultMap = findSuitMatches(hand);
        return resultMap.keySet().size() == 1 && resultMap.values().iterator().next().intValue() == 5;

    }

    public boolean isStraightFlush(List<String> hand) {

        boolean result = true;
        if (!isFlush(hand)) {
            result = false;
        } else if (!isStraight(hand)) {
            result = false;
        }

        return result;
    }

    public boolean isRoyalFlush(List<String> hand) {
        boolean result = true;

        if (!isStraightFlush(hand)) {
            result = false;
        } else {
            List<String> valuesFromHand = getValuesFromHand(hand);
            if (!containsAllRoyals(valuesFromHand)) {
                result = false;
            }
        }
        return result;
    }

    public List<String> convertRoyalValues(List<String> values) {
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

    public Map<String, Integer>  findSuitMatches(List<String> hand) {
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

    public Map<String, Integer> findValueMatches(List<String> hand) {
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


    public List<String> getSuitsFromHand(List<String> hand) {
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

    public List<String> getValuesFromHand(List<String> hand) {
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

    private boolean containsRoyals(List<String> values) {
        return values.contains("K") || values.contains("Q") || values.contains("J");
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

    public String getHighCard(List<String> hand) {
        String result = null;
        List<String> values = convertRoyalValues(getValuesFromHand(hand));
        Collections.sort(values);
        int numeric = getIntValue(values.get(0), false);
        if (numeric > 10 || numeric == 1) {
           if (numeric == 11) {
               result = "J";
           } else if (numeric == 12) {
               result = "Q";
           } else if (numeric == 13) {
               result = "K";
           } else if (numeric == 14 || numeric == 1) {
               result = "A";
           }
        } else {
            result = values.get(0);
        }
        return result;
    }


}
