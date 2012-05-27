package villebasse.ui;

import villebasse.*;
import java.io.*;


public class BoardTestInteractive
{
	private static BufferedReader stdin = new BufferedReader(
		new InputStreamReader(System.in));

	public static void main(String args[])
	{
		Board board = new Board(new Piece());
		int[] pos;

		printBoard(board);
		while (true) {
			pos = readInput();
			if (pos == null)
				break;

			try {
				board.putPieceRelative(pos[0], pos[1], new Piece());
				printBoard(board);
			} catch (Exception e) {
				System.err.println(e + ": " + pos[0] + "," + pos[1]);
			}
		}
	}

	private static void printBoard(Board b)
	{
		for (int i = b.width() + 2; i > 0; --i)
			System.out.print('.');
		System.out.println();

		for (Piece[] row : b.asArray()) {
			System.out.print('.');
			for (Piece p : row)
				System.out.print(p == null ? '.' : '#');
			System.out.println('.');
		}

		for (int i = b.width() + 2; i > 0; --i)
			System.out.print('.');
		System.out.println("\n");
	}

	private static int[] readInput()
	{
		System.out.print("<x> <y>: ");

		String input;
		try {
			input = stdin.readLine();
			if (input == null)
				return null;
		} catch (Exception e) {
			return null;
		}

		int delim = input.indexOf(' ');
		int[] pos = new int[2];
		try {
			pos[0] = Integer.parseInt(input.substring(0, delim));
			pos[1] = Integer.parseInt(input.substring(delim + 1, input.length()));
		} catch (Exception e) {
			return null;
		}

		return pos;
	}
}
