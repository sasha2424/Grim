package main;

import java.awt.BorderLayout;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import javax.swing.JFrame;
import javax.swing.JPanel;

import biomes.SpawnHandler;
import entities.*;
import items.*;
import rendering.RenderQueue;
import saving.SaveHandler;
import terrain.TileHandler;

public class GameWindow extends JPanel {

	/*
	 * Don't Forget Graphics are done in GIMP
	 * 
	 */

	public static final boolean DEBUG = true;

	private static final long serialVersionUID = 1L;
	private static final double WIDTH = 800;
	private static final double HEIGHT = 800;
	public static long SEED;

	public static int SCROLL_SENSITIVITY = 100;
	// public static final long SEED = 5;

	public static TileHandler tileHandler;
	public static InputHandler inputHandler;
	public static EntityHandler entityHandler;
	public static SpawnHandler spawnHandler;
	public static SaveHandler saveHandler;
	public static EventHandler eventHandler;
	public static RenderQueue renderQueue;

	public static double rotation = 0;
	public static double playerRotation = 0;

	public static Player player;

	public static int Tab = 0;

	// TODO
	/*
	 * add width to all entities save player when saving and loading on exit and
	 * startup
	 * 
	 */

	public static void main(String[] args) {

		SpriteSheetLoader.load();

		player = loadPlayer();
		entityHandler = new EntityHandler();

		entityHandler.addEntity(player);

		entityHandler.addEntity(new Rat(400, 400));
		entityHandler.addEntity(new Rat(-400, 400));
		entityHandler.addEntity(new Rat(-400, -400));
		entityHandler.addEntity(new Rat(400, -400));

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
		renderQueue = new RenderQueue();

		JFrame frame = new JFrame("Grim");
		frame.getContentPane().add(new GameWindow(), BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize((int) WIDTH, (int) HEIGHT);
		frame.setVisible(true);

		inputHandler = new InputHandler();
		frame.addKeyListener(inputHandler);
		frame.addMouseWheelListener(inputHandler);

		SEED = getSeed(); // TODO load seed from save

		System.out.println("Seed: " + SEED);

		long t = System.currentTimeMillis();
		long dt = 0;
		while (true) {
			dt = System.currentTimeMillis() - t;
			frame.repaint();
			if (dt > 20) {
				t = System.currentTimeMillis();

				entityHandler.tick(tileHandler, player);
				spawnHandler.spawnEntities(entityHandler, player);

				// do all player key actions
				// ******************

				if (inputHandler.getKeyPressed(0)) {
					player.move(playerRotation - rotation);
				}
				if (inputHandler.getKeyPressed(5) && player.canAttack()) {
					entityHandler.playerInteract(player, playerRotation - rotation);
					player.resetAttackCounter();
				}

				if (inputHandler.getKeyPressed(7)) {
					saveHandler.saveAll(tileHandler, entityHandler, player);
					savePlayer(player);
					System.exit(ABORT);
				}

				// ******************
			}

		}
	}

	public void setBoardRotation() {
		rotation = (2 * Math.PI * inputHandler.getScroll() / 100) % (Math.PI * 2);
		if (rotation < 0) {
			rotation += Math.PI * 2;
		}
	}

	public void setPlayerRotation() {
		Point mouse = getMouse();
		double dx = -mouse.getX() + getCurrentWidth() / 2 + player.getSize() / 2;
		double dy = -mouse.getY() + getCurrentHeight() / 2 + 3 * player.getSize() / 2; // TODO
																						// figure
																						// out
																						// why?
		double angle = Math.atan2(dy, dx) + Math.PI;
		player.setRotation(angle);
		playerRotation = angle;
	}

	private Point getMouse() {
		Point mouse = MouseInfo.getPointerInfo().getLocation();
		Point screan = this.getLocation();
		Point r = new Point();
		r.setLocation(mouse.getX() + screan.getX(), mouse.getY() + screan.getY());
		return r;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);

		setPlayerRotation();
		setBoardRotation();

		if (Tab == 0) { // in game
			if (!inputHandler.getKeyPressed(7)) {
				// check for when game is closing

				saveHandler.updateLoadedTiles(tileHandler, entityHandler, player);
				
				//TODO fix these
				tileHandler.renderAll(renderQueue,player);
				entityHandler.renderAll(renderQueue,player);
				renderQueue.renderAll(this, g2d, player, rotation, TileHandler.getPlayerHeight(player));
				
				
				g2d.drawString(player.getBoardX() + "  " + player.getBoardY(), 10, 10);

				player.inventory.renderHandBar(this, g2d);
			}
		}
		if (inputHandler.getKeyPressed(4)) { // inventory
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

	private static Player loadPlayer() {
		try {
			FileInputStream fis = new FileInputStream("./Save/player.ser");
			ObjectInputStream in = new ObjectInputStream(fis);
			Player p = (Player) in.readObject();
			in.close();
			fis.close();
			p.updateTexture();
			p.inventory.reloadItemTextures();
			return p;
		} catch (FileNotFoundException ex) {
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}

		System.out.println("player load failed");

		return new Player(0, 0, new int[] { 1 }, new int[] { 0 });
	}

	private static void savePlayer(Player p) {
		try {
			String save = "./Save/player.ser";

			FileOutputStream fos = new FileOutputStream(save);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(p);

			out.close();
			fos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
