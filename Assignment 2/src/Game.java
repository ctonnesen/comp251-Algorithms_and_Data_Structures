import java.util.*;
import java.lang.*;
import java.io.*;


public class Game {
	
	Board sudoku;
	
	public class Cell {
		private int row = 0;
		private int column = 0;

		public Cell(int row, int column) {
			this.row = row;
			this.column = column;
		}
		public int getRow() {
			return row;
		}
		public int getColumn() {
			return column;
		}
		public boolean equal(Cell input) {
			if (input.getRow() == this.row) {
				return input.getColumn() == this.column;
			}
			return false;
		}
	}
	
	public class Region{
		private Cell[] matrix;
		private int num_cells;
		public Region(int num_cells) {
			this.matrix = new Cell[num_cells];
			this.num_cells = num_cells;
		}
		public Cell[] getCells() {
			return matrix;
		}
		public void setCell(int pos, Cell element){
			matrix[pos] = element;
		}
		}

	
	public class Board{
		private int[][] board_values;
		private Region[] board_regions;
		private int num_rows;
		private int num_columns;
		private int num_regions;
		
		public Board(int num_rows,int num_columns, int num_regions){
			this.board_values = new int[num_rows][num_columns];
			this.board_regions = new Region[num_regions];
			this.num_rows = num_rows;
			this.num_columns = num_columns;
			this.num_regions = num_regions;
		}
		
		public int[][] getValues(){
			return board_values;
		}
		public int getValue(int row, int column) {
			return board_values[row][column];
		}
		public Region getRegion(int index) {
			return board_regions[index];
		}
		public Region[] getRegions(){
			return board_regions;
		}
		public void setValue(int row, int column, int value){
			board_values[row][column] = value;
		}
		public void setRegion(int index, Region initial_region) {
			board_regions[index] = initial_region;
		}	
		public void setValues(int[][] values) {
			board_values = values;
		}

	}
//	public int[][] solver() {
//		try {
//			for (int row = 0; row < sudoku.getValues().length; row++) {
//				for (int col = 0; col < sudoku.getValues()[row].length; col++) {
//					// Empty cell
//					if (sudoku.getValue(row, col) == -1) {
//						Region region = regionIdentifier(row, col);
//						// Cycle through possible values for cell
//						for (int i = 1; i < region.matrix.length + 1; i++) {
//							sudoku.setValue(row, col, i);
//							if (isValid(row, col, region, i)) {
//								solver();
//							}
//							sudoku.setValue(row, col, -1);
//							}
//						}
//					}
//				}
//		} catch(Exception e){
//			System.out.println("There was an error in the original Sudoku");
//			return null;
//		}
//		// All positions filled
//		return sudoku.board_values;
//	}
	
	public int[][] solver() {
		if (sudokuSolve()) {
			return sudoku.board_values;
		}
		return null;
	}

	private boolean sudokuSolve() {
		try {
			for (int row = 0; row < sudoku.getValues().length; row++) {
				for (int col = 0; col < sudoku.getValues()[row].length; col++) {
					// Empty cell
					if (sudoku.getValue(row, col) == -1) {
						Region region = regionIdentifier(row, col);
						// Cycle through possible values for cell
						for (int i = 1; i < region.matrix.length+1;i++) {
							sudoku.setValue(row,col,i);
							if (isValid(row,col,region,i) && sudokuSolve()) {
								return true;
							}
							sudoku.setValue(row,col,-1);
						}
						return false;
					}
				}
			}
			// All positions filled
			return true;
		}
		catch (Exception e) {
			System.out.println("There was an error in the original Sudoku");
			return false;
		}
	}

	private Region regionIdentifier(int row, int col) throws Exception {
		int regionCycle = 0;
		for (Region curRegion : sudoku.getRegions()) {
			for (Cell curPos : curRegion.getCells()) {
				if (curPos.equal(new Cell(row, col))) {
					return curRegion;
				}
			}
		}
		throw new Exception("Cell is not in any regions");
	}

	private boolean isValid(int row, int col, Region region, int val) {
		return !(isAlreadyIn(row,col,region,val) || isAdjacent(row,col,val));
	}

	private boolean isAlreadyIn(int row, int col, Region region, int val) {
		for (Cell curCell : region.matrix) {
			if (curCell.getRow() == row && curCell.getColumn() == col) {
				continue;
			}
			if (val==sudoku.getValue(curCell.row, curCell.column)) {
				return true;
			}
		}
		return false;
	}

	private boolean isAdjacent(int row, int col, int val) {
			for (int i = row-1; i < row+2; i++) {
				for (int j = col - 1; j < col + 2; j++) {
					try {
						if (i == row && j == col) {
							continue;
						}
						if (val == sudoku.getValue(i, j)) {
							return true;
						}
					} catch (Exception ignored) {
					}
				}
			}
		return false;
	}
//		if (row != 0) {
//			if (val == sudoku.getValue(row - 1, col)) {
//				return true;
//			}
//		}
//		if (col != sudoku.getValues()[row].length) {
//			if (val == sudoku.getValue(row, col + 1)) {
//				return true;
//			}
//		}
//		if (row != sudoku.getValues().length) {
//			if (val == sudoku.getValue(row + 1, col)) {
//				return true;
//			}
//		}
//		if (col != 0) {
//			return val == sudoku.getValue(row, col - 1);
//		}
//		return false;

//	private boolean isDiagonal(int row, int col,int val) {
//		if (row != 0) {
//			// Checks top left for row>0, col>0
//			if (col != 0) {
//				if (val == sudoku.getValue(row - 1, col - 1)) {
//					return true;
//				}
//			}
//			// Checks top right for row>0, col<length
//			if (col != sudoku.getValues()[row].length) {
//				if (val == sudoku.getValue(row - 1, col + 1)) {
//					return true;
//				}
//			}
//		}
//		if (col != 0) {
//			// Checks bottom left for row>0,col>0
//			if (row != 0) {
//				if (val == sudoku.getValue(row + 1, col - 1)) {
//					return true;
//				}
//			}
//			// Checks bottom right for row>0,col<length
//			if (row != sudoku.getValues().length) {
//				if (val == sudoku.getValue(row + 1, col + 1)) {
//					return true;
//				}
//			}
//		}
//		if (row == 0) {
//			// Checks 0,0 for bottom right
//			if (col == 0) {
//				if (val == sudoku.getValue(row + 1, col + 1)) {
//					return true;
//				}
//			}
//			// Checks 0,length for bottom left
//			if (col == sudoku.getValues()[row].length) {
//				if (val == sudoku.getValue(row + 1, col -1 1)) {
//					return true;
//				}
//			}
//		}
//		if (row == 0) {
//			// Checks 0,0 for bottom right
//			if (col == 0) {
//				if (val == sudoku.getValue(row + 1, col + 1)) {
//					return true;
//				}
//			}
//			// Checks 0,length for bottom left
//			if (col == sudoku.getValues()[row].length) {
//				if (val == sudoku.getValue(row + 1, col -1 1)) {
//					return true;
//				}
//			}
//		}
//	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int rows = sc.nextInt();
		int columns = sc.nextInt();
		int[][] board = new int[rows][columns];
		//Reading the board
		for (int i=0; i<rows; i++){
			for (int j=0; j<columns; j++){
				String value = sc.next();
				if (value.equals("-")) {
					board[i][j] = -1;
				}else {
					try {
						board[i][j] = Integer.valueOf(value);
					}catch(Exception e) {
						System.out.println("Ups, something went wrong");
					}
				}	
			}
		}
		int regions = sc.nextInt();
		Game game = new Game();
	    game.sudoku = game.new Board(rows, columns, regions);
		game.sudoku.setValues(board);
		for (int i=0; i< regions;i++) {
			int num_cells = sc.nextInt();
			Game.Region new_region = game.new Region(num_cells);
			for (int j=0; j< num_cells; j++) {
				String cell = sc.next();
				String value1 = cell.substring(cell.indexOf("(") + 1, cell.indexOf(","));
				String value2 = cell.substring(cell.indexOf(",") + 1, cell.indexOf(")"));
				Game.Cell new_cell = game.new Cell(Integer.valueOf(value1)-1,Integer.valueOf(value2)-1);
				new_region.setCell(j, new_cell);
			}
			game.sudoku.setRegion(i, new_region);
		}
		int[][] answer = game.solver();
		for (int i=0; i<answer.length;i++) {
			for (int j=0; j<answer[0].length; j++) {
				System.out.print(answer[i][j]);
				if (j<answer[0].length -1) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
	


}


