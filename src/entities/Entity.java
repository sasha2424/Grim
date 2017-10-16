package entities;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

import main.DoubleStat;
import main.SpriteSheetLoader;
import processing.core.PApplet;
import processing.core.PImage;
import terrain.Tile;

public abstract class Entity implements Serializable {

	// Movement and rendering
	protected double absX, absY;
	protected double velX, velY;
	protected transient Image texture;
	protected int textureX, textureY;
	protected DoubleStat speed;

	// battle stats
	protected DoubleStat HP;
	protected DoubleStat A;
	protected DoubleStat D;
	// TODO add and Clothes Set to this ( class which keeps track of clothes
	// equiped and each ones"thickness)

	protected boolean canMove;
	protected boolean canAttack;
	protected boolean canDie;
	protected boolean visible;
	protected boolean collides;

	public Entity(double X, double Y, int x, int y) {
		this.absX = X;
		this.absY = Y;
		textureX = x;
		textureY = y;
		updateTexture();
	}
	
	public void updateTexture(){
		texture = SpriteSheetLoader.getTexture(textureX, textureY);
	}

	public abstract void draw(Graphics g, Tile t, Player player, double rotation, double height);

	public abstract void tick(EntityHandler e);

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
		return HP.getVal();
	}

	public void setHP(double hP) {
		HP.set(hP);
		;
	}

	protected static double getX(double x, double y, double r, double Width) {
		return (x) * Math.cos(r) - (y) * Math.sin(r) + Width;
	}

	protected static double getY(double x, double y, double r, double Height) {
		return (y) * Math.cos(r) + (x) * Math.sin(r) + Height;
	}

}
