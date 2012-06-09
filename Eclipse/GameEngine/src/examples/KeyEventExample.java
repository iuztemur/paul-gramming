/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import org.game.engine.Game;
import org.game.engine.GameApplication;

/**
 *
 * @author paul
 */
public class KeyEventExample extends Game {

	public static void main(String[] args) {
		GameApplication.start(new KeyEventExample());
	}

	int checkerSize = 40, stepSize = 1;
	int x, y;
	int row, column;
	Color color;

	KeyEventExample() {
		title = "KeyEventExample";
		width = 350;
		height = 400;

		row = 6;
		column = 6;

		color = Color.GREEN;
	}

	@Override
	public void update() {

		int targetX = column*checkerSize;
		int targetY = row*checkerSize;

		if (x < targetX) {
			x += stepSize;
		}	else if (x > targetX) {
			x -= stepSize;
		}
		
		if (y < targetY) {
			y += stepSize;
		} else if (y > targetY) {
			y -= stepSize;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		
		// draw background checker board
		int size = 40;
		int x0=5, y0=20;
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (r % 2 == c % 2) {
					g.setColor(Color.BLACK);
					g.fillRect(x0 + c * size, y0 + r * size, size, size);
				}
			}
		}
		g.setColor(Color.BLUE);
		g.fillOval(x0+x, y0+y, 36, 36);

		// draw the target ball
		int targetX = column*checkerSize;
		int targetY = row*checkerSize;
		g.setColor(color);
		g.fillOval(x0+targetX, y0+targetY, 36, 36);
	}


	// KeyEvent handling method
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				row--;
				if (row < 0) {
					row = 0;
				}
				break;
			case KeyEvent.VK_DOWN:
				row++;
				if (row > 7) {
					row = 7;
				}
				break;
			case KeyEvent.VK_LEFT:
				column--;
				if (column < 0) {
					column = 0;
				}
				break;
			case KeyEvent.VK_RIGHT:
				column++;
				if (column > 7) {
					column = 7;
				}
				break;
		}
	}
}
