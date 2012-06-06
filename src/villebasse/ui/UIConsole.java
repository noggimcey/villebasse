package villebasse.ui;

import java.io.*;
import villebasse.gamelogic.*;
import villebasse.gamelogic.defaultpieces.*;

/**
 * Komentorivi käyttöliittymä.
 *
 * Lähinnä testitarkoituksiin.
 */

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

					piece.rotate(pos[2]);
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
		System.out.print("<x> <y> [<r>]: ");

		String input;
		try {
			input = this.stdin.readLine().trim();
			if (input == null)
				return null;
		} catch (Exception e) {
			return null;
		}

		String[] fields = input.split("\\s+");
		if (fields.length < 2)
			return null;

		int[] pos = new int[3];
		try {
			pos[0] = Integer.parseInt(fields[0]);
			pos[1] = Integer.parseInt(fields[1]);
		} catch (Exception e) {
			return null;
		}
		try {
			pos[2] = Integer.parseInt(fields[2]);
		} catch (Exception e) {
			pos[2] = 0;
		}

		return pos;
	}
}
