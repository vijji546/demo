package com.player.demo.service;

import com.opencsv.CSVReader;
import com.player.demo.dto.Location;
import com.player.demo.dto.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PlayerServiceImpl implements PlayerService  {
    
	@Override
	public List<Player> getAllPlayers(String fileName) {
		log.info("PlayerServiceImpl: getAllPlayers(), Received file name: " + fileName);
		List<Player> playersList = new ArrayList<>();

		try {
			String path = ResourceUtils.getFile("classpath:"+fileName).getAbsolutePath();
			CSVReader reader = new CSVReader(new FileReader(path));
            List<String[]> r = reader.readAll();
			for(String[] line : r) {
				if(line.length >= 6) {
					try {
						playersList.add(buildPlayer(line));
					}
					catch(Exception e) {
						log.error("Exception occured while building player and exception is: " + e);
					}
				}
				else
					log.warn("Skipping this line due to insufficient data");
			}

        }

		catch(Exception e) {
			log.error("Exception occured and exception is: " + e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to process data");
		}

		return playersList;
	}

	private Player  buildPlayer (String[] line) {
		Location location = new Location(line[4], line[5]);
		Player player = new Player(line[0],Integer.parseInt(line[1]),Float.parseFloat(line[2]),Integer.parseInt(line[3]),location);
		return player;
	}
}
