/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.timer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 *
 * @author paul
 */
public class GameCanvas extends JComponent {

	private Timer game;

	public GameCanvas() {
	}

    public GameCanvas(Timer game) {
		this.game = game;
		addMouseListener(game);
		requestFocus();
    }

	public void setGame(Timer game) {
		this.game = game;
		addMouseListener(game);
		requestFocus();
	}

	@Override
	public void paintComponent(Graphics g) {
		game.draw((Graphics2D)g);
	}

}
