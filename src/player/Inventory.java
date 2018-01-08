package player;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

import items.Item;
import main.GameWindow;

public class Inventory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int SPACING = 60;

	private static final int DOWN_SHIFT = 210;

	private ArrayList<Item> inventory;

	public Inventory() {
		inventory = new ArrayList<Item>();
	}
	
	public void reloadItemTextures(){
		for(Item i : inventory){
			i.updateTexture();
		}
	}

	public void addItem(Item i) {
		inventory.add(i);
	}

	public void render(GameWindow w, Graphics g) {
		g.fillRect(0, 0, (int) w.getCurrentWidth(), (int) w.getCurrentHeight());
		for (int i = 0; i < inventory.size(); i++) {
			inventory.get(i).draw(g, i % 10 * SPACING, (int) (i / 10) * SPACING + DOWN_SHIFT);
		}
	}

	public void renderHandBar(GameWindow w, Graphics g) {
		for (int i = 0; i < 4 && i < inventory.size(); i++) {
			inventory.get(i).draw(g, i % 10 * SPACING * 2, w.getCurrentHeight() - 2 * SPACING);
		}
	}
}
