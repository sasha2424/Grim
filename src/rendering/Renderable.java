package rendering;

import java.awt.Graphics2D;

import entities.Player;
import main.GameWindow;
import terrain.Tile;

public abstract class Renderable implements Comparable<Renderable> {

	private int RP = 0;// render priority (higher is rendered on top)

	protected double absX;
	protected double absY;
	
	abstract public void draw(GameWindow w, Graphics2D g, Player player, double rotation, double height);

	@Override
	public int compareTo(Renderable o) {
		return this.getRP() - o.getRP();
	}

	public int getRP() {
		return RP;
	}

	public void setRP(int rP) {
		RP = rP;
	}
	
	public double getAbsX() {
		return absX;
	}

	public void setAbsX(double absX) {
		this.absX = absX;
	}

	public double getAbsY() {
		return absY;
	}

	public void setAbsY(double absY) {
		this.absY = absY;
	}

}
