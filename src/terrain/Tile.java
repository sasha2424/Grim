package terrain;

import java.awt.Color;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

import biomes.Biome;
import entities.Entity;
import entities.Player;
import main.GameWindow;
import main.KeyHandler;
import processing.core.PApplet;

public class Tile implements Serializable, Comparable {

	public static final double TILE_SIZE = 400; // 1600

	// public static final double TILE_SIZE = 100;

	private int boardX, boardY; // grid location

	private double absX, absY, H;

	public Tile(int x, int y, double h, Biome b) {
		this.boardX = x;
		this.boardY = y;
		this.H = h;
		absX = boardX * TILE_SIZE;
		absY = boardY * TILE_SIZE;
	}

	public boolean inBorder(double x, double y) {
		return absX < x && x < absX + TILE_SIZE && absY < y && y < absY + TILE_SIZE;
	}

	public void draw(GameWindow w,Graphics g, TileHandler t, double rotation, Player player, double heightShift) {
		double[] h = t.getAdjacentTileHeights(boardX, boardY);
		// top left bottom right

		double k = heightShift;

		double[][] C = getCoords(rotation, TILE_SIZE, absX - player.getX(), absY - player.getY(), w.getCurrentWidth() / 2,
				w.getCurrentHeight() / 2);

		g.setColor(Biome.getBiome(boardX, boardY).getGroundColor());

		if (Math.PI / 2 < rotation && rotation < 3 * Math.PI / 2) {
			rect(g, C[0][0], C[1][0] - H + k, C[0][3], C[1][3] - H + k, C[0][3], C[1][3] - h[0] + k, C[0][0],
					C[1][0] - h[0] + k);
		}

		g.setColor(Biome.getBiome(boardX, boardY).getGroundColor());

		if (Math.PI < rotation && rotation < 2 * Math.PI) {
			rect(g, C[0][0], C[1][0] - H + k, C[0][1], C[1][1] - H + k, C[0][1], C[1][1] - h[1] + k, C[0][0],
					C[1][0] - h[1] + k);
		}
		g.setColor(Biome.getBiome(boardX, boardY).getGroundColor());

		if (!(Math.PI / 2 < rotation && rotation < 3 * Math.PI / 2)) {
			rect(g, C[0][1], C[1][1] - H + k, C[0][2], C[1][2] - H + k, C[0][2], C[1][2] - h[2] + k, C[0][1],
					C[1][1] - h[2] + k);
		}

		g.setColor(Biome.getBiome(boardX, boardY).getGroundColor());

		if (0 < rotation && rotation < Math.PI) {
			rect(g, C[0][3], C[1][3] - H + k, C[0][2], C[1][2] - H + k, C[0][2], C[1][2] - h[3] + k, C[0][3],
					C[1][3] - h[3] + k);
		}

		g.setColor(Biome.getBiome(boardX, boardY).getSurfaceColor());

		rect(g, C[0][0], C[1][0] - H + k, C[0][1], C[1][1] - H + k, C[0][2], C[1][2] - H + k, C[0][3], C[1][3] - H + k);
	}

	private static double getX(double x, double y, double r, double Width) {
		return (x) * Math.cos(r) - (y) * Math.sin(r) + Width;
	}

	private static double getY(double x, double y, double r, double Height) {
		return (y) * Math.cos(r) + (x) * Math.sin(r) + Height;
	}

	private void line(PApplet p, double r, double x1, double y1, double x2, double y2) {
		float X1 = (float) getX(x1, y1, r, p.width);
		float Y1 = (float) getY(x1, y1, r, p.height);
		float X2 = (float) getX(x2, y2, r, p.width);
		float Y2 = (float) getY(x2, y2, r, p.height);
		p.line(X1, Y1, X2, Y2);
	}

	private void rect(Graphics g, double X1, double Y1, double X2, double Y2, double X3, double Y3, double X4,
			double Y4) {

		g.fillPolygon(new int[] { (int) X1, (int) X2, (int) X3, (int) X4 },
				new int[] { (int) Y1, (int) Y2, (int) Y3, (int) Y4 }, 4);

		g.setColor(Color.black);

		g.drawPolygon(new int[] { (int) X1, (int) X2, (int) X3, (int) X4 },
				new int[] { (int) Y1, (int) Y2, (int) Y3, (int) Y4 }, 4);

	}

	private double[][] getCoords(double r, double s, double x, double y, double Width, double Height) {

		// returns a 2d array for the x and y coordinates of the corners of the
		// top surface;

		/*
		 * first x then y 4 for each as the 4 top locations.
		 */
		double[][] t = new double[2][8];

		t[0][0] = getX(x, y, r, Width);
		t[0][1] = getX(x, y + s, r, Width);
		t[0][2] = getX(x + s, y + s, r, Width);//
		t[0][3] = getX(x + s, y, r, Width);//

		t[1][0] = getY(x, y, r, Height);
		t[1][1] = getY(x, y + s, r, Height);
		t[1][2] = getY(x + s, y + s, r, Height);
		t[1][3] = getY(x + s, y, r, Height);

		return t;
	}

	@Override
	public int compareTo(Object a) {
		Tile k = (Tile) a;
		return (int) (getY(this.absX, this.absY, GameWindow.rotation, 0)
				- getY(k.absX, k.absY, GameWindow.rotation, 0));
	}

	public int getBoardX() {
		return boardX;
	}

	public void setBoardX(int boardX) {
		this.boardX = boardX;
	}

	public int getBoardY() {
		return boardY;
	}

	public void setBoardY(int boardY) {
		this.boardY = boardY;
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

	public double getH() {
		return H;
	}

	public void setH(double h) {
		H = h;
	}

}
