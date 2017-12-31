package main;

import java.awt.Graphics2D;
import java.util.ArrayList;

import entities.Entity;
import entities.Player;

public class EventHandler {
	private ArrayList<Entity> toRender;
	
	public EventHandler(){
		toRender = new ArrayList<Entity>();
	}
	
	public void addEntity(Entity e){
		toRender.add(e);
	}


}
