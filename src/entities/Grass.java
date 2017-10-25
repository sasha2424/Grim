package entities;

import java.awt.Graphics;
import java.awt.Graphics2D;

import main.GameWindow;

import terrain.Tile;

public class Grass extends StationaryEntity {

	public Grass(double X, double Y) {
		super(X, Y, new int[] { 0 }, new int[] { 3 });
		name = "grass";
	}

	public void draw(GameWindow w, Graphics2D g, Tile t, Player player, double rotation, double height) {

		double x = (double) (getX(absX - player.getX(), absY - player.getY(), rotation, w.getCurrentWidth() / 2));
		double y = (double) (getY(absX - player.getX(), absY - player.getY(), rotation, w.getCurrentHeight() / 2));

		int k = 20;
		g.drawImage(texture[0], (int) (x - k / 2), (int) (y + height - k), k, k, null);

	}

	@Override
	public void tick(EntityHandler e) {
		// TODO Auto-generated method stub

	}

}
