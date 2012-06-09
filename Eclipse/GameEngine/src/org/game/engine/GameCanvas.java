/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.game.engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JComponent;

/**
 *
 * @author paul
 */
public class GameCanvas extends JComponent implements ComponentListener {

	private Game game;

	public GameCanvas() {
	}

    public GameCanvas(Game game) {
		this.game = game;
		addKeyListener(game);
		addMouseListener(game);
		addMouseMotionListener(game);
		addMouseWheelListener(game);
		requestFocus();
		addComponentListener(this);
    }

	public void setGame(Game game) {
		this.game = game;
		addKeyListener(game);
		addMouseListener(game);
		addMouseMotionListener(game);
		requestFocus();
		addComponentListener(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		game.draw((Graphics2D)g);
	}

	@Override
	public void componentResized(ComponentEvent ce) {
		game.resize(ce.getComponent().getWidth(), ce.getComponent().getHeight());	
	}

	@Override
	public void componentMoved(ComponentEvent ce) {
		
	}

	@Override
	public void componentShown(ComponentEvent ce) {
		//game.resize(ce.getComponent().getWidth(), ce.getComponent().getHeight());
	}

	@Override
	public void componentHidden(ComponentEvent ce) {
		
	}

}
