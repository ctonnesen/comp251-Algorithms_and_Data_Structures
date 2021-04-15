import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Honors {

	static class Node{
		private int classRow;
		private int classCol;
		private int classJumps;

		public Node(int row, int col, int jump) {
			classRow = row;
			classCol = col;
			classJumps = jump;
		}
	}


	public static int min_moves(int[][] board) {
		int rowDest = board.length - 1;
		int colDest = board[0].length - 1;
		int[][] solveState = new int[rowDest + 1][colDest + 1];
		int jumps = 0;
		boolean flag = false;
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(0,0,0));
		while (!queue.isEmpty()) {
			Node pos = queue.remove();
			int row = pos.classRow;
			int col = pos.classCol;
			int internalJumps = pos.classJumps;
			if ((row < 0) || (rowDest < row) || (col < 0) || (colDest < col)) {
				continue;
			}
			if (solveState[row][col] == 1) {
				continue;
			}
			if ((row == rowDest) && (col == colDest)) {
				flag = true;
				jumps = internalJumps;
				break;
			}
			solveState[row][col] = 1;
			int up = row - board[row][col];
			int down = row + board[row][col];
			int right = col + board[row][col];
			int left = col - board[row][col];
			internalJumps++;
			if (!((down < 0) || (rowDest < down) || (solveState[down][col] == 1))) {
				queue.add(new Node(down,col,internalJumps));
			}
			if (!((right < 0) || (colDest < right) || (solveState[row][right] == 1))) {
				queue.add(new Node(row,right,internalJumps));
			}
			if (!((up < 0) || (rowDest < up) || (solveState[up][col] == 1))) {
				queue.add(new Node(up,col,internalJumps));
			}
			if (!((left < 0) || (colDest < left) || (solveState[row][left] == 1))) {
				queue.add(new Node(row,left,internalJumps));
			}
		}
		if (!flag) {
			jumps = -1;
		}
		return jumps;
	}


//	private static int minMoveRecur(int row, int col, int[][] board) {
//		if (flag) {
//			return 0;
//		}
//		if ((row == rowDest) && (col == colDest)) {
//			flag = true;
//			return 0;
//		}
//		if ((row < 0) || ( rowDest < row) || (col < 0) || (colDest < col)) {
//			return 300;
//		}
//		if (solveState[row][col] == 1) {
//			return 300;
//		}
//		solveState[row][col] = 1;
//		int up = row - board[row][col];
//		int down = row + board[row][col];
//		int right = col + board[row][col];
//		int left = col - board[row][col];
//		int vertBest = Math.min(minMoveRecur(up,col,board),minMoveRecur(down,col,board));
//		int horizBest = Math.min(minMoveRecur(row,right,board),minMoveRecur(row,left,board));
//		int best = Math.min(vertBest,horizBest);
//		smallestJump[row][col] = 1 + best;
////		dpArray[row][col] = 1 + Math.min(Math.min(minMoveRecur(up,col,board),minMoveRecur(down,col,board)),Math.min(minMoveRecur(row,right,board),minMoveRecur(row,left,board)));
//		return smallestJump[row][col];


	public void test(String filename) throws FileNotFoundException{
		Scanner sc = new Scanner(new File(filename));
		int num_rows = sc.nextInt();
		int num_columns = sc.nextInt();
		int [][]board = new int[num_rows][num_columns];
		for (int i=0; i<num_rows; i++) {
			char line[] = sc.next().toCharArray();
			for (int j=0; j<num_columns; j++) {
				board[i][j] = (int)(line[j]-'0');
			}
		}
		sc.close();
		int answer = min_moves(board);
		System.out.println(answer);
	}

	public static void main(String[] args) throws FileNotFoundException{
		Honors honors = new Honors();
		honors.test(args[0]); // run 'javac Honors.java' to compile, then run 'java Honors testfilename'
//		 int [][]board = {{2},{4},{2},{0},{4},{4},{3},{5},{1},{3}};
//		 int answer = min_moves(board);
//		 System.out.println(answer);
	}

}
