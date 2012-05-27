package villebasse.ui;

import java.io.*;
import villebasse.*;


public class UIConsole implements UI
{
	private Board board;
	private BufferedReader stdin;
	private boolean initialized;


	public int initialize(String args[])
	{
		this.board = new Board(new Piece());
		this.stdin = new BufferedReader(new InputStreamReader(System.in));
		this.initialized = true;
		return 0;
	}


	public void run()
	{
		if (!this.initialized)
			this.initialize(null);

		this.printBoard();

		while (true) {
			int pos[] = this.readInput();
			if (pos == null)
				break;

			try {
				this.board.putPieceRelative(pos[0], pos[1], new Piece());
				this.printBoard();
			} catch (Exception e) {
				System.err.println(e.getMessage() + ": " + pos[0] + "," + pos[1]);
			}
		}
	}


	private void printBoard()
	{
		for (int i = this.board.width() + 2; i > 0; --i)
			System.out.print('.');
		System.out.println();

		for (Piece[] row : this.board.asArray()) {
			System.out.print('.');
			for (Piece p : row)
				System.out.print(p == null ? '.' : '#');
			System.out.println('.');
		}

		for (int i = this.board.width() + 2; i > 0; --i)
			System.out.print('.');
		System.out.println("\n");
	}


	private int[] readInput()
	{
		System.out.print("<x> <y>: ");

		String input;
		try {
			input = this.stdin.readLine().trim();
			if (input == null)
				return null;
		} catch (Exception e) {
			return null;
		}

		int delim = input.indexOf(' ');
		int[] pos = new int[2];
		try {
			pos[0] = Integer.parseInt(input.substring(0, delim));
			pos[1] = Integer.parseInt(input.substring(delim + 1, input.length()).trim());
		} catch (Exception e) {
			return null;
		}

		return pos;
	}
}
