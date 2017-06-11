package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;

import entities.Player;
import processing.core.PApplet;
import terrain.*;

public class Main {

	public static final double HEIGHT = 600; // height is a float copy
	public static final double WIDTH = 600; // width is a float copy

	public static TileHandler tileHandler;

	public static double rotation = 0;

	public static Player player;

	public static int Tab = 0;

	private void loadChunk() { // TODO make tiles serializable and make chunk
								// loaders

	}

	private void UnLoadChunk() {

	}

	public void keyPressed() {
		// TODO add key controlls
		// if (key == 'w') {
		// player.move(rotation);
		// }
		// if (key == 's') {
		// player.move(rotation + Math.PI);
		// }
		// if (key == 'a') {
		// player.move(rotation + Math.PI / 2);
		// }
		// if (key == 'd') {
		// player.move(rotation - Math.PI / 2);
		// }
	}

	public static void main(String[] args) {
		GameWindow window = new GameWindow();

		SpriteSheetLoader.load();
		player = new Player(0, 0, 0, 1);

		tileHandler = new TileHandler(window);

		while (true) {
			Graphics g = window.getGraphics();
			g.setColor(Color.white);
			if (Tab == 0) {
				tileHandler.renderTiles(rotation, player);
				tileHandler.movePlayer(player);

				g.setColor(Color.black);
				g.drawString(player.getBoardX() + "     " + player.getBoardY(), 10, 10);
				rotation = window.mouse.getX() / 100d;
				rotation = rotation % (Math.PI * 2);
			}
		}
	}
}
