package entities;

import java.util.ArrayList;

import terrain.Tile;
import terrain.TileHandler;

public class EntityHandler {

	ArrayList<Entity> entities;

	public EntityHandler() {
		entities = new ArrayList<Entity>();
	}

	public void tick(TileHandler t) {
		for (Entity e : entities) {
			e.tick(this);
		}
		t.updateEntityLocations();
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	public void addEntities(ArrayList<Entity> e) {
		for (Entity a : e) {
			entities.add(a);
		}
	}

	public void sendOutEntities(TileHandler t) {
		for (Entity e : entities) {
			t.addEntity(e);
		}
	}

	public Entity randomEntity() { // FIXME get list of all Entities and use
									// those
		return null;
	}

	public Entity getNearestEntity(Entity e) {
		Entity closest = null;
		for (int i = 0; i < entities.size(); i++) {
			if (closest == null && entities.get(i) != e) {
				closest = entities.get(i);
			} else if (entities.get(i) != e && dist(e, closest) > dist(e, entities.get(i))) {
				closest = entities.get(i);
			}
		}
		return closest;
	}

	private double dist(Entity e1, Entity e2) {
		double dx = (e1.getAbsX() - e2.getAbsX()) * (e1.getAbsX() - e2.getAbsX());
		double dy = (e1.getAbsY() - e2.getAbsY()) * (e1.getAbsY() - e2.getAbsY());
		return Math.sqrt(dx + dy);
	}

}
