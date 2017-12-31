package entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import main.DoubleStat;
import main.EventHandler;
import main.GameWindow;
import terrain.Tile;

public class Walker extends MovingEntity {
	private int timer = 0; // death animation timer

	public Walker(double X, double Y) {
		super(X, Y, new int[] { 1 }, new int[] { 0 });
		speed = new DoubleStat(1, 1);
		name = "walker";
		HP = new DoubleStat(20, 20);
	}

	@Override
	public void draw(GameWindow w, Graphics2D g, Tile t, Player player, double rotation, double height) {
		// TODO draw graphics in super class method (because all the same)

		double x = (double) (getX(absX - player.getX(), absY - player.getY(), rotation, w.getCurrentWidth() / 2));
		double y = (double) (getY(absX - player.getX(), absY - player.getY(), rotation, w.getCurrentHeight() / 2));

		int k = 40;
		g.drawImage(texture[0], (int) (x - k / 2), (int) (y - k / 2 + height), k, k, null);
	}

	@Override
	public void tick(EntityHandler e) {

		if (HP.getVal() > 0) {
			Entity nearest = e.getNearestEntity(this, "player");
			if (nearest != null) {
				double dx = nearest.getAbsX() - this.getAbsX();
				double dy = nearest.getAbsY() - this.getAbsY();
				double d = Math.sqrt(dx * dx + dy * dy);
				velX = this.speed.getVal() * dx / d;
				velY = this.speed.getVal() * dy / d;
				super.move(d);
			}

		} else if (HP.getVal() <= 0) {
			super.deathAnimation(e, 0, 30, 3);
		}
	}

	public void runGraphic(GameWindow w, Graphics2D g, Player player, double rotation, double height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deathEvent(EntityHandler e, Player p) {
		// TODO Auto-generated method stub

	}

}
