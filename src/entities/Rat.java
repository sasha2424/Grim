package entities;

import java.awt.Graphics;

import main.DoubleStat;
import main.GameWindow;
import terrain.Tile;

public class Rat extends MovingEntity {

	public Rat(double X, double Y) {
		super(X, Y, 0, 8);
		speed = new DoubleStat(10, 10);
		name = "walker";

	}

	@Override
	public void draw(GameWindow w,Graphics g, Tile t, Player player, double rotation, double height) {
		// TODO draw graphics in super class method (because all the same)

		double x = (double) (getX(absX - player.getX(), absY - player.getY(), rotation, w.getCurrentWidth() / 2));
		double y = (double) (getY(absX - player.getX(), absY - player.getY(), rotation, w.getCurrentHeight() / 2));

		//size of the entity
		int k = 20;
		
		g.drawImage(texture, (int) (x - k / 2), (int) (y - k / 2 + height), k, k, null);
	}

	@Override
	public void tick(EntityHandler e) {
		Entity nearest = e.getNearestEntity(this,"player");
		if (nearest != null) {
			double dx = nearest.getAbsX() - this.getAbsX();
			double dy = nearest.getAbsY() - this.getAbsY();
			double d = Math.sqrt(dx * dx + dy * dy);
			velX = dx / d;
			velY = dy / d;
		}
		super.move();
	}

}
