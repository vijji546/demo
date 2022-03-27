package com.player.demo.service;

import java.util.List;

import com.player.demo.dto.Player;

public interface PlayerService {

	List<Player> getAllPlayers(String fileName);
}
