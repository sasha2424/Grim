package main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheetLoader {
	private static Image Entities;
	private static BufferedImage bigImg;

	public static final int SPRITE_SIZE = 10;
	public static final int SPRITE_TILE_SIZE = 10;
	public static final int SPRITE_SHEET_SIZE = SPRITE_TILE_SIZE * SPRITE_SIZE;

	public static void load() {
		bigImg = ImageIO.read(new File("sheet.png"));
		
		try {
			Entities = ImageIO.read(new File("./spritesheets/Entities.png"));
		} catch (IOException e) {
			System.err.println("Failed to load Image");
		}
	}

	public static Image getTexture(int x, int y) { // row column
		return Entities.(x * SPRITE_TILE_SIZE, y * SPRITE_TILE_SIZE, SPRITE_TILE_SIZE, SPRITE_TILE_SIZE);
	}

}
