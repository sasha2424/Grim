package rendering;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

import entities.Player;
import main.GameWindow;
import terrain.Tile;


public class RenderQueue {
	
	ArrayList<Renderable> toRender;
	
	public RenderQueue(){
		toRender = new ArrayList<Renderable>();
	}
	
	public void clear(){
		toRender.clear();
	}
	
	public void addRenderable(Renderable e){
		toRender.add(e);
	}
	
	public void renderAll(GameWindow w, Graphics2D g, Player player, double rotation, double height){
		Collections.sort(toRender);
		for(Renderable r: toRender){
			r.draw(w, g, player, rotation, height);
		}
	}

}
