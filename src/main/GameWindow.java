package main;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import biomes.SpawnHandler;
import entities.*;
import items.Bread;
import saving.SaveHandler;
import terrain.TileHandler;

public class GameWindow extends JPanel {

	/*
	 * Don't Forget Graphics are done in GIMP
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private static final double WIDTH = 800;
	private static final double HEIGHT = 800;
	public static long SEED;
	// public static final long SEED = 5;
	public static Point mouse;

	public static TileHandler tileHandler;
	public static KeyHandler keyHandler;
	public static EntityHandler entityHandler;
	public static SpawnHandler spawnHandler;
	public static SaveHandler saveHandler;
	public static EventHandler eventHandler;

	public static double rotation = 0;

	public static Player player;

	public static int Tab = 0;

	// TODO
	/*
	 * add width to all entities make tiles render with graphics give rat actual
	 * texture
	 * 
	 */

	public static void main(String[] args) {

		SpriteSheetLoader.load();

		player = new Player(0, 0, new int[] { 1 }, new int[] { 0 });
		entityHandler = new EntityHandler();

		entityHandler.addEntity(player);

		// for (int i = 0; i < 5; i++) {
		// entityHandler.addEntity(new Tree(Math.random() * 200, Math.random() *
		// 200));
		// entityHandler.addEntity(new Bush(Math.random() * 200, Math.random() *
		// 200));
		// }

		tileHandler = new TileHandler(entityHandler);

		spawnHandler = new SpawnHandler();

		saveHandler = new SaveHandler();

		eventHandler = new EventHandler();

		JFrame frame = new JFrame("Grim");
		frame.getContentPane().add(new GameWindow(), BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize((int) WIDTH, (int) HEIGHT);
		frame.setVisible(true);

		keyHandler = new KeyHandler();
		frame.addKeyListener(keyHandler);

		SEED = getSeed(); // TODO load seed from save

		System.out.println("Seed: " + SEED);

		long t = System.currentTimeMillis();
		long dt = 0;
		while (true) {
			dt = System.currentTimeMillis() - t;
			frame.repaint();
			if (dt > 20) {
				entityHandler.tick(tileHandler, player);
				t = System.currentTimeMillis();
				spawnHandler.spawnEntities(entityHandler, player);

				// do all player key actions
				// ******************

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
				if (keyHandler.getKeyPressed(5) && player.canAttack()) {
					entityHandler.playerInteract(player, rotation);
					player.resetAttackCounter();
				}

				if (keyHandler.getKeyPressed(7)) {
					saveHandler.saveAll(tileHandler, entityHandler, player);
					System.exit(ABORT);
				}

				// ******************
			}

		}
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);

		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, (int) GameWindow.WIDTH, (int) GameWindow.HEIGHT);

		g2d.setColor(Color.red);

		if (Tab == 0) { // in game
			if (!keyHandler.getKeyPressed(7)) {
				// check for when game is closing
				saveHandler.updateLoadedTiles(tileHandler, entityHandler, player);
				tileHandler.renderAll(this, g2d, entityHandler, rotation, player);
				g2d.drawString(player.getBoardX() + "  " + player.getBoardY(), 10, 10);

				player.inventory.renderHandBar(this, g2d);
			}
		}
		if (keyHandler.getKeyPressed(4)) { // inventory
			Tab = 1;
			player.inventory.render(this, g2d);
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

	private static long getSeed() {

		String saveDataFile = "./Save/save.txt";

		File f = new File(saveDataFile);
		if (f.exists() && !f.isDirectory()) {
			// GET SEED FROM EXISTING FILE
			try {
				String text = "";
				BufferedReader br = new BufferedReader(new FileReader(saveDataFile));
				String line = br.readLine();
				while (line != null) {
					text = text + line + "\n";
					line = br.readLine();
				}
				br.close();
				Long s = Long.parseLong(text.split("\n")[0]);
				return s;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			// CREATE FILE AND ADD SEED TO FILE
			Long S = (long) (Math.random() * 10);
			PrintWriter writer;
			try {
				writer = new PrintWriter(saveDataFile);
				writer.println("" + (S));
				writer.close();
				return S;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return 0; // seed if file save failed.

	}

}
