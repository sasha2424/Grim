package entities;

import processing.core.PApplet;
import terrain.Tile;
import terrain.TileHandler;

public class Tree extends StationaryEntity {

	public Tree(double X, double Y, int x, int y) {
		super(X, Y, x, y);
	}

	// TODO fix height for render
	public void draw(PApplet p, Tile t, Player player, double rotation, double height) {

		double x = absX - player.getX();
		double y = absY - player.getY();
		x = (double) (getX(x, y, rotation));
		y = (double) (getY(x, y, rotation));

		p.image(texture, (float) x, (float) y, 30, 30);

	}

}
