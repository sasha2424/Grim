package entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import items.Bread;
import main.DoubleStat;
import main.EventHandler;
import main.GameWindow;
import terrain.Tile;

public class Crocodile extends MovingEntity {

	// head and tail rotation
	private double angleCounter;
	private double angleMax = Math.PI / 8;
	private double angleV = .02;

	private double feetShift;
	private double feetShiftMax = 10;
	private double shiftV = feetShiftMax / (angleMax / angleV); // sync with
																// head

	private int timer = 0;

	public Crocodile(double X, double Y) {
		super(X, Y, new int[] { 7, 8, 9, 7, 8 }, new int[] { 0, 0, 0, 1, 1 });
		// head,body,tail
		// left feet,right feet
		speed = new DoubleStat(2, 2);
		angleCounter = 0;
		name = "crocodile";
		HP = new DoubleStat(30, 30);
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

		// head
		g.rotate(-angleCounter);
		g.drawImage(texture[0], -k / 2, -3 * k / 2, k, k, null);
		g.rotate(angleCounter);

		// body
		g.drawImage(texture[1], -k / 2, -k / 2, k, k, null);

		// left feet
		g.drawImage(texture[3], -3 * k / 2, (int) (-k / 2 + feetShift), k, k, null);

		// right feet
		g.drawImage(texture[4], k / 2, (int) (-k / 2 - feetShift), k, k, null);

		// tail
		if (shouldMove) {
			g.rotate(angleCounter);
		}
		g.drawImage(texture[2], -k / 2, k / 2, k, k, null);
		if (shouldMove) {
			g.rotate(-angleCounter);
		}

		g.rotate(-angle - rotation + Math.PI / 2);
		g.translate(-x, -y - height);

		// move tail
		angleCounter += angleV;
		if (angleCounter > angleMax || angleCounter < -angleMax) {
			angleV = -angleV;
		}

		if (shouldMove) {
			// move tail
			feetShift += shiftV;
			if (feetShift > feetShiftMax || feetShift < -feetShiftMax) {
				shiftV = -shiftV;
			}
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
		if (Math.random() < .8)
			player.inventory.addItem(new Bread());
	}

}
