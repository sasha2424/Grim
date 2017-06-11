package main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheetLoader {
	// private static Image Entities;
	private static BufferedImage Entities;
	private static BufferedImage[] sprites;

	public static final int SPRITE_SIZE = 10;
	public static final int SPRITE_TILE_SIZE = 10;
	public static final int SPRITE_SHEET_SIZE = SPRITE_TILE_SIZE * SPRITE_SIZE;

	public static void load() {
		try {
			Entities = ImageIO.read(new File("./spritesheets/Entities.png"));
		} catch (IOException e1) {
			System.err.println("Failed to load Image");
			e1.printStackTrace();
		}

		final int width = 10;
		final int height = 10;
		final int rows = 5;
		final int cols = 5;
		BufferedImage[] sprites = new BufferedImage[rows * cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				sprites[(i * cols) + j] = Entities.getSubimage(j * width, i * height, width, height);
			}
		}

		// try {
		// Entities = ImageIO.read(new File("./spritesheets/Entities.png"));
		// } catch (IOException e) {
		// System.err.println("Failed to load Image");
		// }
	}

	public static Image getTexture(int x, int y) { // row column
		return sprites[5*y + x];
	}

}
