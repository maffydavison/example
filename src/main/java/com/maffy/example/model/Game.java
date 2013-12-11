package com.maffy.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by maryannfinnerty on 12/10/13.
 */
public class Game {

    private final int capacity;
    private List<Card> deck;
    private List<Player> players;

    public Game(int capacity) {
        this.capacity = capacity;
        this.players = new ArrayList<Player>();
        buildDeck();
    }

    private void buildDeck() {
        String [] suits = {"S", "H", "C", "D"};
        String [] names = {"K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2", "A"};
        deck = new ArrayList<Card>();
        for (String s : suits) {
            for (String n : names) {
                deck.add(new Card(s, n));
            }
        }
    }

    public String addPlayer(Player player) {
        String result;
        if (players.size() < capacity) {
            player.setPosition(players.size() + 1);
            players.add(player);
            result = "Welcome player " + player.getPosition();
        } else {
            result = "Sorry, game is full!";
        }

        return result;
    }

    public String [] play() {
        Collections.shuffle(deck);
        for (int i = 0, j=0; i < players.size(); i++) {
            deal(players.get(i), 5, j);
            j += 5;
        }
        for (Player p : players) {
            convertRoyalValues(p.getHand());
        }
        Rank rank = new Rank();
        String [] result = rank.rankHands(players);

        return result;
    }

    private void convertRoyalValues(List<Card> cards) {
        boolean foundAce = false;
        boolean foundRoyal = false;
        for (int i = 0; i < cards.size(); i++) {
           if (cards.get(i).getValue() == 1) {
               foundAce = true;
           } else if (cards.get(i).getValue() > 10) {
               foundRoyal = true;
           }
        }

        if (foundAce && foundRoyal) {
            Iterator iterator = cards.iterator();
            while (iterator.hasNext()) {
                Card card = (Card) iterator.next();
                if (card.getValue() == 1) {
                    card.setValue(14);
                }
            }
        }
        Collections.sort(cards);
    }

    private void deal(Player player, int cards, int last) {
        for (int i = last; i < cards + last; i++) {
            player.getHand().add(deck.get(i));
        }
    }
}
