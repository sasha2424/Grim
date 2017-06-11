package main;

import processing.core.PApplet;
import processing.core.PImage;

public class SpriteSheetLoader {
	static PImage Entities;

	public static final int SPRITE_SIZE = 10;
	public static final int SPRITE_TILE_SIZE = 10;
	public static final int SPRITE_SHEET_SIZE = SPRITE_TILE_SIZE * SPRITE_SIZE;

	public static void load(PApplet p) {
		Entities = p.loadImage("./spritesheets/Entities.png");
	}

	public static PImage getTexture(int x, int y) { // row column
		return Entities.get(x * SPRITE_TILE_SIZE, y * SPRITE_TILE_SIZE, SPRITE_TILE_SIZE, SPRITE_TILE_SIZE);
	}
	
	

}
