package entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import items.Bread;
import items.Fur;
import main.DoubleStat;
import main.EventHandler;
import main.GameWindow;
import terrain.Tile;

public class Rat extends MovingEntity {

	private double angleCounter;
	private double angleV;
	private double angleMax = Math.PI / 8;

	private int timer = 0;

	public Rat(double X, double Y) {
		super(X, Y, new int[] { 5, 6 }, new int[] { 0, 0 });
		speed = new DoubleStat(3, 3);
		angleCounter = 0;
		angleV = .02;
		name = "rat";
		HP = new DoubleStat(10, 10);
	}

	@Override
	public void draw(GameWindow w, Graphics2D g, Tile t, Player player, double rotation, double height) {
		// TODO draw graphics in super class method (because all the same)

		double x = (double) (getX(absX - player.getX(), absY - player.getY(), rotation, w.getCurrentWidth() / 2));
		double y = (double) (getY(absX - player.getX(), absY - player.getY(), rotation, w.getCurrentHeight() / 2));

		// size of the entity
		int k = 30;
		double angle = EntityHandler.getAngle(this, player);

		g.translate(x, y + height);
		g.rotate(angle + rotation - Math.PI / 2);

		g.drawImage(texture[0], -k / 2, -k / 2, k, k, null);

		g.rotate(angleCounter);
		g.drawImage(texture[1], -k / 2, +k / 2, k, k, null);
		g.rotate(-angleCounter);

		g.rotate(-angle - rotation + Math.PI / 2);
		g.translate(-x, -y - height);

		// g.drawImage(texture, (int) (x - k / 2), (int) (y - k / 2 + height),
		// k, k, null);

		// move tail
		angleCounter += angleV;
		if (angleCounter > angleMax || angleCounter < -angleMax) {
			angleV = -angleV;
		}
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

	public void deathEvent(EntityHandler e, Player player) {
		if (Math.random() < .5)
			player.inventory.addItem(new Fur());
	}

}
