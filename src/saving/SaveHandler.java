package saving;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import biomes.Biome;
import entities.Entity;
import entities.EntityHandler;
import entities.Player;
import terrain.Tile;
import terrain.TileHandler;

public class SaveHandler {
	private int oldX;
	private int oldY;

	private static final int BUFFER = 2; // buffer around load size for when to
											// reload tiles

	public SaveHandler() {
		oldX = 1000;
		oldY = 1000;
	}

	public void updateLoadedTiles(TileHandler tileHandler, EntityHandler entityHandler, Player player) {
		int X = player.getBoardX();
		int Y = player.getBoardY();
		int loadSize = TileHandler.LOAD_SIZE;

		if (Math.abs(X - oldX) > loadSize - BUFFER || Math.abs(Y - oldY) > loadSize - BUFFER) {

			for (int i = X - loadSize; i < X + loadSize; i++) {
				for (int j = Y - loadSize; j < Y + loadSize; j++) {
					if (tileHandler.missingTile(i, j)) {
						SavePacket p = load(i, j);

						tileHandler.addTilefromSave(p.getTile());
						entityHandler.addEntitiesFromSave(p.getEntities());
					}
				}
			}

			ArrayList<Tile> toSave = tileHandler.UnLoadTiles(player);

			for (Tile t : toSave) {
				// TODO run saving as a separate thread to make the game faster
				SavePacket p = new SavePacket(t, entityHandler.getEntitiesInTile(t));
				save(p);
			}

			oldX = player.getBoardX();
			oldY = player.getBoardY();
		}
	}

	public void saveAll(TileHandler tileHandler, EntityHandler entityHandler, Player player) {

		ArrayList<Tile> toSave = tileHandler.getAllTiles();

		for (Tile t : toSave) {
			// TODO run saving as a separate thread to make the game faster
			SavePacket p = new SavePacket(t, entityHandler.getEntitiesInTile(t));
			save(p);
		}

	}

	public SavePacket load(int x, int y) {

		try {
			FileInputStream fis = new FileInputStream("./Save/(" + x + "," + y + ").ser");
			ObjectInputStream in = new ObjectInputStream(fis);
			SavePacket p = (SavePacket) in.readObject();
			in.close();
			fis.close();
			return p;
		} catch (FileNotFoundException ex) {
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}

		Biome b = Biome.getBiome(x, y);
		Tile t = new Tile(x, y, TileHandler.terrainHeight(x, y), Biome.getBiome(x, y));
		ArrayList<Entity> e = b.generateEntitiesForTile(t);
		return new SavePacket(t, e);
	}

	public void save(SavePacket p) {

		try {
			String save = "./Save/" + p.getName() + ".ser";

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
