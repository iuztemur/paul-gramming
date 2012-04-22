/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pacman;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import org.game.engine.Game;
import org.game.engine.GameApplication;

/**
 *
 * @author paul
 */
public class Pacman extends Game {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		GameApplication.start(new Pacman());
	}

	private BufferedImage sheet;
	int frame;

	public Pacman() {
		title = "Pacman";
		width = 176;
		height = 240;
		frame = 0; // redundant
	}

	@Override
	public void draw(Graphics2D g) {
		if (sheet != null) {
			g.drawImage(sheet, 0, 0, null);

			g.drawImage(sheet.getSubimage(628+(frame/2)*(15+2), 33, 15,15), 80, 105, null);
		}
	}

	@Override
	public void init() {
		try {
//			if you have pacman.png inside images/ directory as in video 
//		sheet = ImageIO.read(getClass().getResource("/images/pacman.png"));
		sheet = ImageIO.read(new URL("http://spriters-resource.com/gameboy/mspacman/mspacmangeneral.png"));
		} catch (Exception ex) {
		}
		
	}

	@Override
	public void update() {
		frame++;
		if (frame > 3*2) {
			frame = 0;
		}
	}

}
