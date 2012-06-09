package game.packman;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.game.engine.Game;
import org.game.engine.GameApplication;

public class PackMan extends Game {
	
	public static void main(String[] args) {
		GameApplication.start(new PackMan());
	}

	final int STEP = 2;
	
	BufferedImage packman;
	int frame;
	int reqDir, curDir;
	int column, row;
	
	int columns, rows;
	
	ArrayList<String> lines = new ArrayList<String>();
	
	public PackMan() {
		// load the lines
		try {
			Scanner s = new Scanner(new File("maze.txt"));
			int r = 0;
			while (s.hasNextLine()) {
				String line = s.nextLine();
				lines.add(line);
				if (line.contains("5")) {
					row = r;
					column = line.indexOf('5');
				}
				r++;
			}
			s.close();
			
			rows = lines.size();
			columns = lines.get(0).length();
			
			width = columns*STEP;
			height = rows*STEP;
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		title = "PackMan";
		frame = 0;
		curDir = reqDir = KeyEvent.VK_RIGHT;
//		width = height = 400;
		try {
			packman = ImageIO.read(new File("images/packman.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (37 <= key && key <= 40) {
			reqDir = key;
		}
	}
	
	@Override
	public void update() {
		frame++;
		if (frame > 5) {
			frame = 0;
		}
		
		if (move(reqDir) == SUCCESS) {
			curDir = reqDir;
		} else {
			move(curDir);
		}
	}
	
	static int SUCCESS = 1, FAIL = 0;
	
	private int move(int reqDir) {
		// current position of packman is (row, column)
		switch (reqDir) {
		case KeyEvent.VK_LEFT: // 37
			if (column > 0 && charAt(row, column-1) != '0') {
				column -= 1;
				return SUCCESS;
			}
			break;
		case KeyEvent.VK_UP:   // 38
			if (row > 0 && charAt(row-1, column) != '0') {
				row -= 1;
				return SUCCESS;
			}
			break;
		case KeyEvent.VK_RIGHT: // 39
			if (column < columns-1 && charAt(row, column+1) != '0') {
				column += 1;
				return SUCCESS;
			}
			break;
		case KeyEvent.VK_DOWN:  // 40
			if (row < rows-1 && charAt(row+1, column) != '0') {
				row += 1;
				return SUCCESS;
			}
			break;
		}
		return FAIL;
	}

	private char charAt(int row, int column) {
		return lines.get(row).charAt(column);
	}

	@Override
	public void draw(Graphics2D g) {
		for (int r=0; r<rows; r++) {
			for (int c=0; c<columns; c++) {
				if (charAt(r,c) != '0') {
					g.fillRect(c*STEP-14, r*STEP-14, 28, 28);
				}
			}
		}
		g.drawImage(packman.getSubimage((frame/2)*30, (curDir-37)*30, 28, 28), column*STEP-14, row*STEP-14, null);
	}

}
