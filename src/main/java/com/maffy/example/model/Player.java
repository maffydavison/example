package com.maffy.example.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maryannfinnerty on 12/10/13.
 */
public class Player {

    private final String name;
    private int position;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.position = 0;
        this.hand = new ArrayList<Card>();
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (position != player.position) return false;
        if (hand != null ? !hand.equals(player.hand) : player.hand != null) return false;
        if (name != null ? !name.equals(player.name) : player.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + position;
        result = 31 * result + (hand != null ? hand.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Player " + position + " " + name;
    }
}
