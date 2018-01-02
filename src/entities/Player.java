package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import main.EventHandler;
import main.GameWindow;
import main.KeyHandler;
import player.Inventory;
import processing.core.PApplet;
import terrain.Tile;
import terrain.TileHandler;

public class Player extends MovingEntity {

	public static final double speed = 10;
	public static final double interactDistance = 80;
	public static final int attackDelay = 10;
	private int attackTickCount = 0;
	public Inventory inventory;

	public Player(int X, int Y, int[] x, int[] y) {
		super(X, Y, x, y);
		this.name = "player";
		inventory = new Inventory();
		size = 30;
		collisionRange = 30;
	}

	public boolean canAttack() {
		return attackTickCount > attackDelay;
	}

	public void resetAttackCounter() {
		attackTickCount = 0;
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

	public void draw(GameWindow w, Graphics2D g, Tile t, Player player, double rotation, double height) {
		g.drawImage(texture[0], (int) (w.getCurrentWidth() / 2 - size / 2), (int) (w.getCurrentHeight() / 2 - size / 2), size, size,
				null);

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
		attackTickCount++;
		// TODO stuff like burns and poison and effects

	}

	public static int absToBoard(double a) {
		int r = a < 0 ? (int) (a / Tile.TILE_SIZE - 1) : (int) (a / Tile.TILE_SIZE);
		return r;
	}

	public void deathEvent(EntityHandler e, Player player) {
		// TODO clear inventory
	}
	
	public int getSize(){
		return size;
	}
}
