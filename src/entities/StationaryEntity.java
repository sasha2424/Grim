package entities;

import java.awt.Graphics;
import java.awt.Graphics2D;

import main.EventHandler;
import main.GameWindow;
import processing.core.PApplet;
import terrain.Tile;

public abstract class StationaryEntity extends Entity {

	public boolean spawnsMonster;

	public StationaryEntity(double X, double Y, int[] x, int[] y) {
		super(X, Y, x, y);
		canMove = false;
	}

	public StationaryEntity(double X, double Y) {
		super(X, Y);
		canMove = false;
	}

	public abstract void draw(GameWindow w, Graphics2D g, Tile t, Player player, double rotation, double height);

	// public abstract void displayInfoText();
	// use event handler to make text pop up on screen

	public void interactPlayer(Player player) {
		this.HP.increment(-1 * player.A.getVal());
	}

	public void deathEvent(EntityHandler e, Player player) {
		// TODO
	}

}
