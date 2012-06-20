package game.packman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Maze {

	ArrayList<String> lines;
	int row, column;
	int rows, columns; 
	int width, height; 
	
	public Maze(int m) {
		// load the lines
		try {
			lines = new ArrayList<String>();
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
			
			width = columns*2;
			height = rows*2;
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	public char charAt(int r, int c) {
		return lines.get(r).charAt(c);
	}

	public char[][] getCells() {
		char[][] cells = new char[rows][columns];
		for (int r=0; r<rows; r++) {
			System.arraycopy(lines.get(r).toCharArray(), 0, cells[r], 0, columns);
//			for (int c=0; c<columns; c++) {
//			}
		}
		return cells;
	}

}
