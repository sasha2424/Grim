package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class InputHandler implements KeyListener, MouseWheelListener {
	private Boolean[] keys;

	private int scroll;

	public InputHandler() {
		super();
		scroll = 0;
		keys = new Boolean[8];
		keys[0] = false; // move forward w
		keys[1] = false; // move left a
		keys[2] = false; // move back s
		keys[3] = false; // move right d
		keys[4] = false; // inventory e
		keys[5] = false; // interact " "
		keys[6] = false; // special / spell / misc q
		keys[7] = false; // quit and save esc
	}

	public boolean getKeyPressed(int i) {
		return keys[i];
	}

	@Override
	public void keyPressed(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_W) {
			keys[0] = true;
		}
		if (k.getKeyCode() == KeyEvent.VK_SPACE) {
			keys[5] = true;
		}
		if (k.getKeyCode() == KeyEvent.VK_E) {
			keys[4] = !keys[4];
		}
		if (k.getKeyCode() == KeyEvent.VK_ESCAPE) {
			keys[7] = !keys[7];
		}
	}

	@Override
	public void keyReleased(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_W) {
			keys[0] = false;
		}
		if (k.getKeyCode() == KeyEvent.VK_SPACE) {
			keys[5] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent k) {

	}

	private class Key { // TODO give key codes and make streamlined
		private String name;
		private Boolean pressed;

		public Key(String name) {
			this.name = name;
			pressed = false;
		}

		public boolean isPressed() {
			return pressed;
		}

		public String getName() {
			return name;
		}

		public void toggle() {
			pressed = !pressed;
		}

		public void keySet(boolean t) {
			pressed = t;
		}

		public void setPressed() {
			pressed = true;
		}

		public void setNotPressed() {
			pressed = false;
		}
	}

	public int getScroll() {
		return scroll;

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		scroll += e.getWheelRotation();
	}

}
