package villebasse.gamelogic;

import java.awt.Color;
import java.util.Vector;
import villebasse.gamelogic.defaultpieces.*;

/**
 * Pelimoottori.
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
	private Vector<GameStateEventListener> listeners;


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
		this.listeners = new Vector<GameStateEventListener>(2);
		this.piecesOnBoard = new Vector<Piece>(deck.size());
		this.players = new Vector<Player>(6);
		this.state = EngineState.INITIALIZATION;
		this.turn = 0;
	}

	public void addGameStateEventListener(GameStateEventListener gsl)
	{
		if (gsl != null)
			this.listeners.add(gsl);
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

		if (this.players.contains(player))
			return false;

		this.players.add(player);
		return true;
	}

	public boolean addPoint(int points)
	{
		return this.addPoints(this.curPlayer, points);
	}

	public boolean addPoints(Player player, int points)
	{
		if (player == null)
			return false;

		player.addPoints(points);
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

	public Player getCurrentPlayer()
	{
		return this.curPlayer;
	}

	/**
	 * Monesko pelikierros on menossa.
	 *
	 * @return Monesko pelikierros on menossa
	 */
	public int getRoundNumber()
	{
		return this.turn / this.players.size() + 1;
	}

	public Vector<Player> getPlayers()
	{
		return this.players;
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
		if (this.turn > 0 &&
				this.state.ordinal() <= EngineState.INGAME_PUT_PIECE.ordinal())
			return false;

		if (!this.draw())
			return false;

		int i = this.turn % this.players.size();
		if (i == 0)
			this.stateRoundStart();

		this.curPlayer = this.players.get(i);
		this.turn++;
		this.stateTurnStart();

		return true;
	}

	public boolean placeMeeple()
	{
		if (this.state != EngineState.INGAME_PLACE_MEEPLE || this.curPiece == null)
			return false;

		this.stateRemoveMeeples();
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
		this.stateRemoveMeeples();

		return true;
	}

	public boolean placeMeeple(int pieceX, int pieceY,
		double meepleX, double meepleY)
	{
		if (this.board.getPieceRelative(pieceX, pieceY) != this.curPiece)
			return false;
		return this.placeMeeple(meepleX, meepleY);
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
		this.statePlaceMeeple();

		return true;
	}

	/**
	 * Montako laattaa pakassa on jäljellä.
	 *
	 * @return Jäljellä olevien laattojen lukumäärä
	 */
	public int piecesLeft()
	{
		return this.deck.size();
	}

	public void removeGameStateEventListener(GameStateEventListener gsl)
	{
		if (gsl != null)
			this.listeners.remove(gsl);
	}

	/**
	 * Poista nappula laudalta.
	 *
	 * @param pieceX  Palan suhteellinen vaakasuuntainen koordinaatti
	 * @param pieceY  Palan suhteellinen pystysuuntainen koordinaatti
	 *
	 * @return Onnistuiko nappulan poistaminen
	 */
	public boolean removeMeeple(int pieceX, int pieceY)
	{
		return this.removeMeeple(pieceX, pieceY, 0);
	}

	/**
	 * Poista nappula laudalta ja lisää pisteitä nappulan omistajalle.
	 *
	 * @param pieceX  Palan suhteellinen vaakasuuntainen koordinaatti
	 * @param pieceY  Palan suhteellinen pystysuuntainen koordinaatti
	 * @param points  Lisättävä pistemäärä (voi olla negatiivinen)
	 *
	 * @return Onnistuiko nappulan poistaminen
	 */
	public boolean removeMeeple(int pieceX, int pieceY, int points)
	{
		if (this.state.ordinal() < EngineState.INGAME_PLACE_MEEPLE.ordinal())
			return false;

		if (this.state != EngineState.INGAME_PROFIT)
			this.stateRemoveMeeples();

		Piece piece = this.board.getPieceRelative(pieceX, pieceY);
		if (piece == null)
			return false;

		if (!piece.hasMeeple())
			return false;

		Meeple meeple = piece.removeMeeple();
		return meeple.getPlayer().returnMeeple(meeple, points);
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

	public void setGameStateEventListener(GameStateEventListener gsl)
	{
		this.listeners.removeAllElements();
		this.addGameStateEventListener(gsl);
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

		this.stateGameStart();
		this.nextTurn();
		return true;
	}


	private boolean draw()
	{
		try {
			this.curPiece = this.deck.draw();
		} catch (Exception e) {
			// deck empty
			this.stateGameEnd();
			this.curPiece = null;
			return false;
		}
		return true;
	}

	private void stateGameEnd()
	{
		this.state = EngineState.DECKEMPTY;

		if (this.listeners.size() > 0) {
			GameStateEvent gse = new GameStateEvent(this);
			for (GameStateEventListener gsel : this.listeners)
				gsel.gameStateGameEnd(gse);
		}
	}

	private void stateGameStart()
	{
		this.state = EngineState.INGAME;

		if (this.listeners.size() > 0) {
			GameStateEvent gse = new GameStateEvent(this);
			for (GameStateEventListener gsel : this.listeners)
				gsel.gameStateGameStart(gse);
		}
	}

	private void statePlaceMeeple()
	{
		this.state = EngineState.INGAME_PLACE_MEEPLE;

		if (this.listeners.size() > 0) {
			GameStateEvent gse = new GameStateEvent(this);
			for (GameStateEventListener gsel : this.listeners)
				gsel.gameStatePlaceMeeple(gse);
		}
	}

	private void stateRemoveMeeples()
	{
		this.state = EngineState.INGAME_PROFIT;

		if (this.listeners.size() > 0) {
			GameStateEvent gse = new GameStateEvent(this);
			for (GameStateEventListener gsel : this.listeners)
				gsel.gameStateRemoveMeeples(gse);
		}
	}

	private void stateRoundStart()
	{
		if (this.listeners.size() > 0) {
			GameStateEvent gse = new GameStateEvent(this);
			for (GameStateEventListener gsel : this.listeners)
				gsel.gameStateRoundStart(gse);
		}
	}

	private void stateTurnStart()
	{
		this.state = EngineState.INGAME_PUT_PIECE;

		if (this.listeners.size() > 0) {
			GameStateEvent gse = new GameStateEvent(this);
			for (GameStateEventListener gsel : this.listeners)
				gsel.gameStateTurnStart(gse);
		}
	}
}
