package entities;

import java.awt.Color;
import java.awt.Graphics;

import main.Main;
import processing.core.PApplet;
import terrain.Tile;
import terrain.TileHandler;

public class Player extends Entity {

	public static final double speed = 5;

	public Player(int X, int Y, int x, int y) {
		super(X, Y, x, y);
	}

	public double getX() {
		return absX;
	}

	public int getBoardX() {
		return (int) (getX() / Tile.TILE_SIZE);
	}

	public void setX(int boardX) {
		this.absX = boardX * Tile.TILE_SIZE;
	}

	public double getY() {
		return absY;
	}

	public int getBoardY() {
		return (int) (getY() / Tile.TILE_SIZE);
	}

	public void setY(int boardY) {
		this.absY = boardY * Tile.TILE_SIZE;
	}

	public void draw(Graphics g, Tile t, Player player, double rotation, double height) {
		g.setColor(Color.red);
		g.fillOval((int) (Main.WIDTH / 2), (int) (Main.HEIGHT / 2), 30, 30);
	}

	public void move(double rotation) {
		incrementX(getX(0, -speed, -rotation));
		incrementY(getY(0, -speed, -rotation));
	}

	public void incrementX(double dx) {
		absX += dx;
	}

	public void incrementY(double dy) {
		absY += dy;
	}

	private static double getY(double x, double y, double r, double Height) {
		return (y) * Math.cos(r) + (x) * Math.sin(r) + Height;
	}

	public int compareTo(Tile a) {
		return (int) (getY(this.absX, this.absY, Main.rotation, 0) - getY(a.getAbsX(), a.getAbsY(), Main.rotation, 0));
	}

}
