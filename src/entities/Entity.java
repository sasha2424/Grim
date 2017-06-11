package entities;

import java.awt.Graphics;

import main.SpriteSheetLoader;
import processing.core.PApplet;
import processing.core.PImage;
import terrain.Tile;

public abstract class Entity {

	protected double absX, absY;
	protected PImage texture;
	protected double HP;

	public Entity(double X, double Y, int x, int y) {
		this.absX = X;
		this.absY = Y;
		texture = SpriteSheetLoader.getTexture(x, y);
	}

	public abstract void draw(Graphics g, Tile t, Player player, double rotation, double height);

	public double getAbsX() {
		return absX;
	}

	public void setAbsX(double absX) {
		this.absX = absX;
	}

	public double getAbsY() {
		return absY;
	}

	public void setAbsY(double absY) {
		this.absY = absY;
	}

	public double getHP() {
		return HP;
	}

	public void setHP(double hP) {
		HP = hP;
	}

	protected static double getX(double x, double y, double r) {
		return (x) * Math.cos(r) - (y) * Math.sin(r);
	}

	protected static double getY(double x, double y, double r) {
		return (y) * Math.cos(r) + (x) * Math.sin(r);
	}

}
