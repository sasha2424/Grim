package entities;

import java.awt.Color;
import java.awt.Graphics;

import main.GameWindow;
import main.KeyHandler;
import player.Inventory;
import processing.core.PApplet;
import terrain.Tile;
import terrain.TileHandler;

public class Player extends Entity {

	public static final double speed = 2;
	public Inventory inventory;

	public Player(int X, int Y, int x, int y) {
		super(X, Y, x, y);
		inventory = new Inventory();
	}

	public double getX() {
		return absX;
	}

	public int getBoardX() {
		return absToBoard(getX());
	}

	public void setX(double x) {
		this.absX = x;
	}

	public double getY() {
		return absY;
	}

	public int getBoardY() {
		return absToBoard(getY());
	}

	public void setY(double y) {
		this.absY = y;
	}

	public void draw(Graphics g, Tile t, Player player, double rotation, double height) {
		int k = 30;
		g.drawImage(texture, (int) (GameWindow.WIDTH / 2 - k / 2), (int) (GameWindow.HEIGHT / 2 - k / 2), k, k, null);

	}

	public void move(double rotation) {
		incrementX(getX(0, -speed, -rotation, 0));
		incrementY(getY(0, -speed, -rotation, 0));
	}

	public void incrementX(double dx) {
		absX += dx;
	}

	public void incrementY(double dy) {
		absY += dy;
	}

	@Override
	public void tick(EntityHandler e) {
		// TODO stuff like burns and poison and effects

	}

	public static int absToBoard(double a) {
		int r = a < 0 ? (int) (a / Tile.TILE_SIZE - 1) : (int) (a / Tile.TILE_SIZE);
		return r;
	}

}
