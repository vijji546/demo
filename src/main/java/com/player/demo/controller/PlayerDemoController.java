package com.player.demo.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.player.demo.dto.Player;
import com.player.demo.service.PlayerService;

@RestController
@CrossOrigin
@Slf4j
public class PlayerDemoController {
	
	@Autowired
	PlayerService playerService;
	
	@GetMapping(value = "/api/players")
	public List<Player> getPlayers () {
		
		log.info("PlayerDemoController : getPlayers(), Begin Controller");
		return playerService.getAllPlayers("player.csv");
	}

}
