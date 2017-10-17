package entities;

import java.awt.Graphics;


import main.GameWindow;
import terrain.Tile;

public class Tree extends StationaryEntity {

	public Tree(double X, double Y) {
		super(X, Y, 0,0);
		name = "tree";
	}

	public void draw(GameWindow w,Graphics g, Tile t, Player player, double rotation, double height) {

		double x = (double) (getX(absX - player.getX(), absY - player.getY(), rotation, w.getCurrentWidth() / 2));
		double y = (double) (getY(absX - player.getX(), absY - player.getY(), rotation, w.getCurrentHeight() / 2));

		int k = 70;
		g.drawImage(texture, (int) (x - k / 2), (int) (y + height - k), k, k, null);
		
	}

	@Override
	public void tick(EntityHandler e) {
		
	}

}
