package com.maffy.example.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by maryannfinnerty on 12/3/13.
 */
public class WordUnique {



    /*
    function taking two strings - respond with a string that has each letter that is found in BOTH words, and
    each character in the response should appear the maximum number of times that that character appears
    in BOTH strings

    example:

        baac
        cbbc

        result:  bc

        bbbaac
        ddddcbba

        result:  bba
     */

    public String getRepeatedCharsAsString(String one, String two) {
        Map<Character, Integer> charFrequency;
        StringBuilder builder = new StringBuilder();
        String longer;
        if (one.length() > two.length()) {
            charFrequency = makeCharFrequencyMap(two);
            longer = one;
        } else {
            charFrequency = makeCharFrequencyMap(one);
            longer = two;
        }
        boolean done = false;
        String values = keysToString(charFrequency.keySet());
        for (int i = 0; i < values.length() ; i++) {
            if (charFrequency.isEmpty()) {
                done = true;
            } else {
                int frequency = charFrequency.get(values.charAt(i));
                int index = longer.indexOf(values.charAt(i));
                if (index != -1 && frequency > 0) {
                    builder.append(values.charAt(i));
                    charFrequency.put(values.charAt(i), --frequency);
                    while (frequency > 0 && !done) {
                        frequency = charFrequency.get(values.charAt(i));
                        index = longer.indexOf(values.charAt(i), ++index);
                        if (index == -1 || frequency < 1) {
                            done = true;
                        } else {
                            charFrequency.put(values.charAt(i), --frequency);
                            builder.append(values.charAt(i));
                        }
                    }

                }

                charFrequency.remove(values.charAt(i));

            }
        }

        return builder.toString();
    }

    private Map<Character, Integer> makeCharFrequencyMap(String one) {
        Map<Character, Integer> result = new HashMap<Character, Integer>();
        for (char c : one.toCharArray()) {
            if (result.containsKey(c)) {
                int temp = result.get(c);
                result.put(c, ++temp);
            } else {
                result.put(c, 1);
            }
        }
        return result;
    }
    private String keysToString(Set<Character> keys) {
        String result = "";
        for (char c : keys) {
            result += c;
        }
        return result;

    }

}
