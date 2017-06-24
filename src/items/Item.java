package items;

import java.awt.Graphics;
import java.awt.Image;

import main.SpriteSheetLoader;

public class Item {

	protected Image texture;

	public static final int ICON_SIZE = 50;

	public Item(int spriteX, int spriteY) {
		texture = SpriteSheetLoader.getTexture(spriteX, spriteY);
	}

	public void draw(Graphics g, double x, double y) {
		g.drawImage(texture, (int) x, (int) y, ICON_SIZE, ICON_SIZE, null);
	}

}
