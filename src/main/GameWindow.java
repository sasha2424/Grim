package main;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class GameWindow {

	private JFrame frame;
	private JPanel panel;

	public Point mouse;

	/**
	 * Create the application.
	 */
	public GameWindow() {
		initialize();
		this.getFrame().setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 600, 600);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.addMouseListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				mouse = e.getPoint();
			}
		});

		panel = new JPanel();
		getFrame().getContentPane().add(panel, BorderLayout.CENTER);
	}

	public Graphics getGraphics() {
		return panel.getGraphics();
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
