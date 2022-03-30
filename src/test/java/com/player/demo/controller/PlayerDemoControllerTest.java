package com.player.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.player.demo.dto.Location;
import com.player.demo.dto.Player;
import com.player.demo.service.PlayerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = PlayerDemoController.class)
@RunWith(SpringRunner.class)
public class PlayerDemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PlayerService playerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void playersReturnedFromService () throws Exception {
     List<Player> playerList = new ArrayList<>();
     Location location1 = new Location("Plano","Tx");
     Player player1 = new Player("Ram",180,199,2003,location1);
     Location location2 = new Location("Frisco","Tx");
     Player player2 = new Player("Laxman",170,189,2005,location2);
     playerList.add(player1);
     playerList.add(player2);
     Mockito.when(playerService.getAllPlayers(Mockito.anyString())).thenReturn(playerList);
     mockMvc.perform(get("/api/players")).andDo(print()).andExpect(status().isOk())
             .andExpect(content().string(containsString(objectMapper.writeValueAsString(playerList))));
    }

    @Test
    public void exceptionThrown () throws Exception {
        Exception e = new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to process data");
        Mockito.when(playerService.getAllPlayers(Mockito.anyString())).thenThrow(e);
        mockMvc.perform(get("/api/players")).andDo(print()).andExpect(status().is5xxServerError())
                .andExpect(result -> assertThat(result.getResolvedException().getMessage(),containsString("Unable to process data")));
    }
}
