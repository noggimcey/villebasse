package villebasse;

import java.io.*;


public class BoardTest
{
	private static BufferedReader stdin = new BufferedReader(
		new InputStreamReader(System.in));

	public static void main(String args[])
	{
		Board board = new Board(new Piece());

		while (true) {
			int[] pos = readInput();
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
		System.out.println();
	}

	private static int[] readInput()
	{
		try {
			String input = stdin.readLine().trim();
			int delim = input.indexOf(' ');
			if (delim == -1)
				return null;

			int[] pos = new int[2];
			pos[0] = Integer.parseInt(input.substring(0, delim));
			pos[1] = Integer.parseInt(input.substring(delim + 1, input.length()));
			return pos;
		} catch (Exception e) {
			return null;
		}

	}
}
