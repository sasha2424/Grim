package entities;

import java.awt.Graphics;

import main.GameWindow;

import terrain.Tile;

public class Bush extends StationaryEntity {

	public Bush(double X, double Y) {
		super(X, Y, 0, 2);
	}

	public void draw(Graphics g, Tile t, Player player, double rotation, double height) {

		double x = (double) (getX(absX - player.getX(), absY - player.getY(), rotation, GameWindow.WIDTH / 2));
		double y = (double) (getY(absX - player.getX(), absY - player.getY(), rotation, GameWindow.HEIGHT / 2));

		int k = 40;
		g.drawImage(texture, (int) (x - k / 2), (int) (y + height - k), k, k, null);

	}

	@Override
	public void tick(EntityHandler e) {
		// TODO Auto-generated method stub
		
	}

}