package com.maffy.example.model;

/**
 * Created by maryannfinnerty on 12/10/13.
 */
public class Card implements Comparable<Card> {

    private final String suit;
    private final String name;
    private int value;

    public Card(String suit, String name) {
        this.suit = suit;
        this.name = name;
        this.value = computeValue(name);

    }

    public String getSuit() {
        return suit;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private int computeValue(String name) {
        int result = 0;
        try {
            result = Integer.parseInt(name);
        } catch (NumberFormatException e) {
            if (name.equalsIgnoreCase("k")) {
                result = 13;
            }else if (name.equalsIgnoreCase("Q")) {
                result = 12;
            } else if (name.equalsIgnoreCase("J")){
                result = 11;
            } else if (name.equalsIgnoreCase("A")) {
                result = 1;
            }
        }
        return result;
    }

    @Override
    public int compareTo(Card o) {

        // desc
        return o.value - this.value;

        //asc
        // return this.value - o.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (value != card.value) return false;
        if (name != null ? !name.equals(card.name) : card.name != null) return false;
        if (suit != null ? !suit.equals(card.suit) : card.suit != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = suit != null ? suit.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + value;
        return result;
    }

    @Override
    public String toString() {
        return suit + name;
    }
}
