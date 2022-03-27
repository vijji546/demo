package com.player.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

	private String name;
	private int height;
	private float weight;
	private int debut;
	private Location location;
}
