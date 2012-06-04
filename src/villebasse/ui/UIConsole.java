package villebasse.ui;

import java.io.*;
import villebasse.gamelogic.*;
import villebasse.gamelogic.defaultpieces.*;


public class UIConsole implements UI
{
	private Board board;
	private Deck deck;
	private BufferedReader stdin;
	private boolean initialized;


	public boolean initialize(String args[])
	{
		this.deck = new DefaultDeckWithoutRoads();
		try {
			this.board = new Board(this.deck);
		} catch (Exception e) {
		}
		this.stdin = new BufferedReader(new InputStreamReader(System.in));
		this.initialized = true;
		return true;
	}


	public void run()
	{
		if (!this.initialized)
			this.initialize(null);

		System.out.println(this.board.asArray()[0][0] + "\n");

		while (!this.deck.isEmpty()) {
			Piece piece = null;
			try {
				 piece = this.deck.draw();
			} catch (Exception e) {
				System.err.println(e + ": shouldn't happen");
				System.exit(1);
			}

			System.out.println("Deck gave " + piece);

			int pos[] = null;
			while (true) {
				try {
					pos = this.readInput();
					if (pos == null)
						continue;

					this.board.putPieceRelative(pos[0], pos[1], piece);
					this.printBoard();
					break;
				} catch (Exception e) {
					System.err.println(e.getMessage() + ": " + pos[0] + "," + pos[1]);
				}
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
