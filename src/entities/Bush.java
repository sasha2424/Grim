package entities;

import java.awt.Graphics;
import java.awt.Graphics2D;

import main.GameWindow;
import rendering.Renderable;
import terrain.Tile;

public class Bush extends StationaryEntity {

	public Bush(double X, double Y) {
		super(X, Y, new int[]{2}, new int[]{0});
		name = "bush";
		size = 40;
		collisionRange = 30;
	}

	public void draw(GameWindow w, Graphics2D g, Player player, double rotation, double height) {

		double x = (double) (getX(absX - player.getX(), absY - player.getY(), rotation, w.getCurrentWidth() / 2));
		double y = (double) (getY(absX - player.getX(), absY - player.getY(), rotation, w.getCurrentHeight() / 2));

		g.drawImage(texture[0], (int) (x - size / 2), (int) (y + height - size), size, size, null);

	}

	@Override
	public void tick(EntityHandler e) {

	}


}
