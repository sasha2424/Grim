package player;

import java.awt.Graphics;
import java.util.ArrayList;

import items.Item;
import main.GameWindow;

public class Inventory {
	private static final int SPACING = 60;

	private static final int DOWN_SHIFT = 210;

	private ArrayList<Item> inventory;

	public Inventory() {
		inventory = new ArrayList<Item>();
	}

	public void addItem(Item i) {
		inventory.add(i);
	}

	public void render(Graphics g) {
		g.fillRect(0, 0, (int) GameWindow.WIDTH, (int) GameWindow.HEIGHT);
		for (int i = 0; i < inventory.size(); i++) {
			inventory.get(i).draw(g, i % 10 * SPACING, (int) (i / 10) * SPACING + DOWN_SHIFT);
		}
	}

	public void renderHandBar(Graphics g) {
		for (int i = 0; i < 4 && i < inventory.size(); i++) {
			inventory.get(i).draw(g, i % 10 * SPACING * 2, GameWindow.HEIGHT - 2 * SPACING);
		}
	}
}
