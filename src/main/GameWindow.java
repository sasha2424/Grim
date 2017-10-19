package main;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;

import biomes.SpawnHandler;
import entities.*;
import items.Bread;
import terrain.TileHandler;

public class GameWindow extends JPanel {

	/*
	 * Don't Forget Graphics are done in GIMP
	 * 
	 */
	
	

	private static final long serialVersionUID = 1L;
	private static final double WIDTH = 800;
	private static final double HEIGHT = 800;
	public static long SEED = (long) (Math.random() * 10);

	public Point mouse;

	public static TileHandler tileHandler;
	public static KeyHandler keyHandler;
	public static EntityHandler entityHandler;
	public static SpawnHandler spawnHandler;

	public static double rotation = 0;

	public static Player player;

	public static int Tab = 0;

	public static void main(String[] args) {

		SpriteSheetLoader.load();

		player = new Player(0, 0, 0, 1);
		entityHandler = new EntityHandler();

		entityHandler.addEntity(new Walker(-800, -800));
		entityHandler.addEntity(player);

		player.inventory.addItem(new Bread());
		player.inventory.addItem(new Bread());

		for (int i = 0; i < 30; i++) {
			entityHandler.addEntity(new Tree(Math.random() * 1600, Math.random() * 1600));
			entityHandler.addEntity(new Bush(Math.random() * 1600, Math.random() * 1600));
		}

		tileHandler = new TileHandler(Math.random() * 100, entityHandler);

		spawnHandler = new SpawnHandler();

		JFrame frame = new JFrame("Grim");
		frame.getContentPane().add(new GameWindow(), BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize((int) WIDTH, (int) HEIGHT);
		frame.setVisible(true);

		keyHandler = new KeyHandler();
		frame.addKeyListener(keyHandler);

		long t = System.currentTimeMillis();
		long dt = 0;
		while (true) {
			dt = System.currentTimeMillis() - t;
			frame.repaint();
			if (dt > 20) {
				entityHandler.tick(tileHandler);
				t = System.currentTimeMillis();
				spawnHandler.spawnEntities(entityHandler, player);
			}

		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.white);
		g.fillRect(0, 0, (int) GameWindow.WIDTH, (int) GameWindow.HEIGHT);

		g.setColor(Color.red);

		if (Tab == 0) { // in game

			// only update, do spawn, and render when in game
			tileHandler.updateTiles(entityHandler, player);
			tileHandler.renderAll(this, g, entityHandler, rotation, player);
			g.drawString(player.getBoardX() + "  " + player.getBoardY(), 10, 10);

			mouse = MouseInfo.getPointerInfo().getLocation();

			if (mouse != null) {
				rotation = mouse.getX() / 100d;
			} else {
				rotation = 0;
			}
			rotation = rotation % (Math.PI * 2);

			if (keyHandler.getKeyPressed(0)) {
				player.move(rotation);
			}
			player.inventory.renderHandBar(this, g);
		}
		if (keyHandler.getKeyPressed(4)) { // inventory
			Tab = 1;
			player.inventory.render(this, g);
		} else {
			Tab = 0;
		}
	}

	public GameWindow() {

	}

	public double getCurrentWidth() {
		return (double) this.getSize().getWidth();
	}

	public double getCurrentHeight() {
		return (double) this.getSize().getHeight();
	}

}
