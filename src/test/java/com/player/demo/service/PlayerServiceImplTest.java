package com.player.demo.service;

import com.player.demo.dto.Player;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerServiceImplTest {

    @Autowired
    PlayerService playerService;


    @Test
    public void testGetAllPlayers() {
        List<Player> playerList = playerService.getAllPlayers("player.csv");
        Assert.assertEquals(6,playerList.size());
        Assert.assertEquals("Virat",playerList.get(0).getName());
        Assert.assertEquals(170, playerList.get(1).getHeight());
        Assert.assertEquals("Hyderabad", playerList.get(3).getLocation().getBirthCity());
        Assert.assertEquals("Jarkhand", playerList.get(5).getLocation().getBirthState());
    }

    @Test(expected = ResponseStatusException.class)
    public void testfileDoesnotExist () {
        playerService.getAllPlayers("random.csv");

    }

    @Test
    public void testInSufficientData () {
        List<Player> playerList = playerService.getAllPlayers("player1.csv");
        Assert.assertEquals(5,playerList.size());
        Assert.assertNotEquals("Virat", playerList.get(0).getName());
    }
    @Test
    public void testCorruptedData () {
        List<Player> playerList = playerService.getAllPlayers("player2.csv");
        Assert.assertEquals(5,playerList.size());
        Assert.assertNotEquals("Virat", playerList.get(0).getName());
    }


}
