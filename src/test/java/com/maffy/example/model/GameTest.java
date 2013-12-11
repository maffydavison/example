package com.maffy.example.model;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by maryannfinnerty on 12/10/13.
 */
public class GameTest {

    Game game;
    Player player_1;
    Player player_2;



    @Before
    public void setUp() throws Exception {
        game = new Game(2);
        player_1 = new Player("maffy");
        player_2 = new Player("mark");

    }

    @Test
    public void testAddPlayer() throws Exception {
        String first = game.addPlayer(player_1);
        assert first.contains("Welcome");
        String second = game.addPlayer(player_2);
        assert second.contains("Welcome");
        Player out = new Player("nobody");
        String response = game.addPlayer(out);
        assert response.contains("Sorry");
    }

    @Test
    public void testPlay() throws Exception {
        game.addPlayer(player_1);
        game.addPlayer(player_2);
        String[] result = game.play();
        assert result != null;
        assert result.length == 2;
    }
}
