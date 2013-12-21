package com.maffy.example.model;

/**
 * Created by Maffy Davison on 12/20/13.
 */
public class NumberToText {
    private final String[] NUMBERS = { "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    private final String[] TENS = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    private final String HUNDRED = "hundred";
    private final String THOUSAND = "thousand";

    public String translate(int i) {
        String result = "";
        if (i <= 19) {
             result = NUMBERS[i];
        } else if (i / 10 < 10) {
            result = TENS[i/10];
            if (i % 10 > 0) {
                result += " " + translate(i%10);
            }
        } else if (i / 100 < 10) {
               result += translate(i / 100) + " " + HUNDRED;
             if (i % 100 > 0) {
                 result += " " + translate(i%100);
             }
        } else if (i/1000 < 1000) {
            result += translate(i/1000) + " " + THOUSAND;
            if (i % 1000 > 0) {
                result += " " + translate(i%1000);
            }
        }

        return result;

    }
}
