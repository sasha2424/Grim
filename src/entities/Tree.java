package entities;

import java.awt.Graphics;

import processing.core.PApplet;
import terrain.Tile;
import terrain.TileHandler;

public class Tree extends StationaryEntity {

	public Tree(double X, double Y, int x, int y) {
		super(X, Y, x, y);
	}

	// TODO fix height for render
	public void draw(Graphics g, Tile t, Player player, double rotation, double height) {

		double x = absX - player.getX();
		double y = absY - player.getY();
		x = (double) (getX(x, y, rotation));
		y = (double) (getY(x, y, rotation));

		//g.drawImage(texture, (float) x, (float) y, 30, 30);
		g.drawImage(texture, (int) x, (int) y, 10, 10, null);
	}


}
