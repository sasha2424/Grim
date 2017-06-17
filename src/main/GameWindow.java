package main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

import entities.EntityHandler;
import entities.Player;
import entities.Walker;
import terrain.TileHandler;

public class GameWindow extends JPanel {

	private static final long serialVersionUID = 1L;
	public static final double WIDTH = 800;
	public static final double HEIGHT = 800;

	public Point mouse;

	public static TileHandler tileHandler;
	public static KeyHandler keyHandler;
	public static EntityHandler entityHandler;

	public static double rotation = 0;

	public static Player player;

	public static int Tab = 0;

	public static void main(String[] args) {
		SpriteSheetLoader.load();

		JFrame frame = new JFrame("Grim");
		frame.getContentPane().add(new GameWindow(), BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize((int) WIDTH, (int) HEIGHT);
		frame.setVisible(true);

		keyHandler = new KeyHandler();
		entityHandler = new EntityHandler();
		frame.addKeyListener(keyHandler);

		entityHandler.addEntity(new Walker(100, 100));
		entityHandler.addEntity(player);
		entityHandler.sendOutEntities(tileHandler);

		long t = System.currentTimeMillis();
		long dt = 0;
		while (true) {
			dt = System.currentTimeMillis() - t;
			frame.repaint();
			if (dt > 20) {
				entityHandler.tick(tileHandler);
				t = System.currentTimeMillis();
			}

		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.white);
		g.fillRect(0, 0, (int) GameWindow.WIDTH, (int) GameWindow.HEIGHT);

		tileHandler.movePlayer(player);

		g.setColor(Color.red);
		
		if (Tab == 0) {

			tileHandler.renderTiles(g, rotation, player);
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
		}
	}

	private void loadChunk() { // TODO make tiles serializable and make chunk
		// loaders

	}

	private void UnLoadChunk() {

	}

	public GameWindow() {
		player = new Player(0, 0, 0, 1);
		tileHandler = new TileHandler();
	}

}
