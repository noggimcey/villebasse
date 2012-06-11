package villebasse.gamelogic;

import java.awt.Color;
import java.util.Vector;

/**
 * Pelimoottori
 *
 */

public class VilleBasseEngine
{
	public static enum EngineState {
		INITIALIZATION,
		DECKEMPTY,
		INGAME,
		INGAME_PUT_PIECE,
		INGAME_PLACE_MEEPLE,
		INGAME_PROFIT,
	};

	private Board board;
	private Deck deck;
	private EngineState state;
	private int turn;
	private Piece curPiece;
	private Player curPlayer;
	private Vector<Piece> piecesOnBoard;
	private Vector<Player> players;


	/**
	 * Konstruktori.
	 *
	 */
	public VilleBasseEngine()
	{
		this(new DefaultDeck());
	}

	/**
	 * Konstruktori.
	 *
	 * @param deck  Käytettävä pakka
	 */
	public VilleBasseEngine(Deck deck)
	{
		try {
			this.board = new Board(deck);
		} catch (Exception e) {
			// TODO
			System.err.println(e);
		}

		this.deck = deck;
		this.piecesOnBoard = new Vector<Piece>(deck.size());
		this.players = new Vector<Player>(6);
		this.state = EngineState.INITIALIZATION;
		this.turn = 0;
	}

	/**
	 * Lisää pelaaja peliin.
	 *
	 * @param name  Pelaajan nimi
	 * @param color  Pelaajan nappuloiden väri
	 * @return Onnistuiko lisäys
	 */
	public boolean addPlayer(String name, Color color)
	{
		return this.addPlayer(new Player(name, color));
	}

	/**
	 * Lisää pelaaja peliin.
	 *
	 * @param player  Lisättävä pelaaja
	 * @return Onnistuiko lisäys
	 */
	public boolean addPlayer(Player player)
	{
		if (this.state != EngineState.INITIALIZATION || player == null)
			return false;

		this.players.add(player);
		return true;
	}

	/**
	 * Moottorin käyttämä lauta.
	 *
	 * @return Moottorin käyttämä lauta.
	 */
	public Board getBoard()
	{
		return this.board;
	}

	/**
	 * Moottorin käyttämä pakka.
	 *
	 * @return Moottorin käyttämä pakka.
	 */
	public Deck getDeck()
	{
		return this.deck;
	}

	/**
	 * Seuraavaksi asetteva pala.
	 *
	 * @return Seuraavaksi asetteva pala
	 */
	public Piece getCurrentPiece()
	{
		return this.curPiece;
	}

	/**
	 * Monesko pelikierros on menossa.
	 *
	 * @return Monesko pelikierros on menossa
	 */
	public int getRoundNumber()
	{
		if (this.turn == 0)
			return 0;
		return (this.turn - 1) / this.players.size() + 1;
	}

	/**
	 * Moottorin tila.
	 *
	 * @return Moottorin tila
	 */
	public EngineState getState()
	{
		return this.state;
	}

	/**
	 * Monesko pelivuoro on menossa.
	 *
	 * @return Monesko pelivuoro on menossa
	 */
	public int getTurnNumber()
	{
		return this.turn;
	}

	/**
	 * Siirry seuraavaan pelivuoroon.
	 *
	 * @return Onnistuiko seuraavaan pelivuoroon siirtyminen
	 */
	public boolean nextTurn()
	{
		//if (this.state != INGAME)
		if (this.turn > 0 && this.state.ordinal() <= EngineState.INGAME_PUT_PIECE.ordinal())
			return false;

		if (!this.draw())
			return false;

		this.curPlayer = this.players.get(this.turn % this.players.size());
		this.turn++;
		this.state = EngineState.INGAME_PUT_PIECE;

		return true;
	}

	/**
	 * Aseta vuorossa olevan pelaajan nappula viimeksi asetetulle laatalle.
	 *
	 * @param x  Vaakasuuntainen koordinaatti (0..1)
	 * @param y  Pystysuuntainen koordinaatti (0..1)
	 * @return Onnistuiko asettaminen
	 */
	public boolean placeMeeple(double x, double y)
	{
		if (this.state != EngineState.INGAME_PLACE_MEEPLE || this.curPiece == null)
			return false;

		Meeple m = this.curPlayer.takeMeeple();
		if (m == null) // should throw exception
			return false;

		this.curPiece.placeMeeple(x, y, m);
		this.state = EngineState.INGAME_PROFIT;

		return true;
	}

	/**
	 * Aseta viimeksi nostettu laatta pelilaudalle.
	 *
	 * @param x  Vaakasuuntainen koordinaatti
	 * @param y  Pystysuuntainen koordinaatti
	 * @return Onnistuiko asettaminen
	 */
	public boolean putPiece(int x, int y)
	{
		if (this.state != EngineState.INGAME_PUT_PIECE || this.curPiece == null)
			return false;

		try {
			this.board.putPieceRelative(x, y, this.curPiece);
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}

		this.piecesOnBoard.add(this.curPiece);
		this.state = EngineState.INGAME_PLACE_MEEPLE;

		return true;
	}

	/**
	 * Montako laattaa pakassa on jäljellä.
	 *
	 * @return Jäljellä olevien laattojen lukumäärä.
	 */
	public int piecesLeft()
	{
		return this.deck.size();
	}

	/**
	 * Montako laattaa pakassa on jäljellä.
	 *
	 * @return Jäljellä olevien laattojen lukumäärä.
	 */
	public boolean removeMeeple(int pieceX, int pieceY, double meepleX, double meepleY, int points)
	{
		if (this.state.ordinal() < EngineState.INGAME_PLACE_MEEPLE.ordinal())
			return false;

		Piece piece = this.board.getPieceRelative(pieceX, pieceY);
		if (piece == null)
			return false;

		Meeple meeple = piece.removeMeeple(meepleX, meepleY);
		if (meeple == null)
			return false;

		meeple.getPlayer().returnMeeple(points);

		return true;
	}

	/**
	 * Nosta uusi laatta pakasta ja sekoita nykyinen takaisin pakkaan.
	 *
	 * @return Onnistuiko laatan korvaaminen uudella
	 */
	public boolean replaceCurrentPiece()
	{
		if (this.state != EngineState.INGAME_PUT_PIECE)
			return false;

		Piece oldPiece = this.curPiece;
		if (!this.draw())
			return false;

		this.deck.putBack(oldPiece);

		return true;
	}

	/**
	 * Aloita peli.
	 *
	 * @return Onnistuiko pelin aloittaminen
	 */
	public boolean startGame()
	{
		if (this.state != EngineState.INITIALIZATION || this.players.size() == 0)
			return false;

		this.state = EngineState.INGAME;
		this.nextTurn();
		return true;
	}


	private boolean draw()
	{
		try {
			this.curPiece = this.deck.draw();
		} catch (Exception e) {
			// deck empty
			System.err.println(e);
			this.state = EngineState.DECKEMPTY;
			this.curPiece = null;
			return false;
		}
		return true;
	}
}
