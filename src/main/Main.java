package main;

import java.awt.EventQueue;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;

import entities.Player;
import processing.core.PApplet;
import terrain.*;

public class Main extends PApplet {

	public static final double HEIGHT = 600; // height is a float copy
	public static final double WIDTH = 600; // width is a float copy

	public TileHandler tileHandler;

	public static double rotation = 0;

	public Player player;

	public int Tab = 0;

	public void settings() {
		size((int) WIDTH, (int) HEIGHT);
		fullScreen();
	}

	public void setup() {

		SpriteSheetLoader.load(this);
		player = new Player(0, 0, 0, 1);

		tileHandler = new TileHandler(this);

	}

	public void draw() {
		background(255);
		if (Tab == 0) {
			tileHandler.renderTiles(rotation, player);
			tileHandler.movePlayer(player);

			fill(0, 0, 0);
			text(player.getBoardX() + "     " + player.getBoardY(), 10, 10);
			rotation = mouseX / 100d;
			rotation = rotation % (Math.PI * 2);
		}
	}

	private void loadChunk() { // TODO make tiles serializable and make chunk
								// loaders

	}

	private void UnLoadChunk() {

	}

	public void keyPressed() {
		if (key == 'w') {
			player.move(rotation);
		}
		if (key == 's') {
			player.move(rotation + Math.PI);
		}
		if (key == 'a') {
			player.move(rotation + Math.PI / 2);
		}
		if (key == 'd') {
			player.move(rotation - Math.PI / 2);
		}
	}

	public static void main(String[] args) {
		PApplet.main("main.Main");

		GameWindow window = new GameWindow();

	}
}
