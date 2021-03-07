package es.ieslavereda.Chess.model.common;

/**
 * Crea la clase tablero. Tiene un vector de celdas, dos listas de piezas
 * (blancas, y negras), 2 listas de piezas eliminadas y al rey blanco y negro
 * 
 * @author RAUL
 *
 */
public class Tablero {

	private Celda[][] tablero;
	private Lista<Pieza> blancas;
	private Lista<Pieza> blancasEliminadas;
	private Lista<Pieza> negras;
	private Lista<Pieza> negrasEliminadas;
	private Pieza blackKing;
	private Pieza whiteKing;

	public Tablero() {
		super();
		tablero = new Celda[8][8];
		blancas = new Lista<>();
		blancasEliminadas = new Lista<>();
		negras = new Lista<>();
		negrasEliminadas = new Lista<>();
		inicializar();
	}

	/**
	 * Crea las piezas y las añade a las listas
	 */
	private void inicializar() {

		// Inicializamos el tablero
		for (int fila = 0; fila < tablero.length; fila++) {
			for (int col = 0; col < tablero[0].length; col++)
				tablero[fila][col] = new Celda();
		}

		blancas.addHead(new Rook(Color.WHITE, new Coordenada('A', 1), this));
		blancas.addHead(new Knight(Color.WHITE, new Coordenada('B', 1), this));
		blancas.addHead(new Bishop(Color.WHITE, new Coordenada('C', 1), this));
		blancas.addHead(new Queen(Color.WHITE, new Coordenada('D', 1), this));
		whiteKing = new King(Color.WHITE, new Coordenada('E', 1), this);
		blancas.addHead(whiteKing);
		blancas.addHead(new Bishop(Color.WHITE, new Coordenada('F', 1), this));
		blancas.addHead(new Knight(Color.WHITE, new Coordenada('G', 1), this));
		blancas.addHead(new Rook(Color.WHITE, new Coordenada('H', 1), this));

		negras.addHead(new Rook(Color.BLACK, new Coordenada('A', 8), this));
		negras.addHead(new Knight(Color.BLACK, new Coordenada('B', 8), this));
		negras.addHead(new Bishop(Color.BLACK, new Coordenada('C', 8), this));
		negras.addHead(new Queen(Color.BLACK, new Coordenada('D', 8), this));
		blackKing = new King(Color.BLACK, new Coordenada('E', 8), this);
		negras.addHead(blackKing);
		negras.addHead(new Bishop(Color.BLACK, new Coordenada('F', 8), this));
		negras.addHead(new Knight(Color.BLACK, new Coordenada('G', 8), this));
		negras.addHead(new Rook(Color.BLACK, new Coordenada('H', 8), this));

		for (int i = 0; i < tablero.length; i++) {
			blancas.addHead(new Pawn(Color.WHITE, new Coordenada((char) ('A' + i), 2), this));
			new Pawn(Color.BLACK, new Coordenada((char) ('A' + i), 7), this);
		}

		blancas.addHead(new Pawn(Color.WHITE, new Coordenada('A', 6), this));
	}

	/**
	 * Comprueba si una coordenadada contiene una pieza
	 * 
	 * @param c Coordenada a comprobar
	 * @return True si contiene pieza, false si no
	 */
	public boolean contiene(Coordenada c) {
		return !(c.getRow() > 8 || c.getRow() < 1 || c.getColumn() < 'A' || c.getColumn() > 'H');
	}

	/**
	 * Devuelve una pieza a partir de una coordenada
	 * 
	 * @param c Coordenada de la cual hay que devolver la pieza
	 * @return La pieza de la coordenada, o null si no hay
	 */
	public Pieza getPiezaAt(Coordenada c) {
		if (!contiene(c))
			return null;
		else
			return getCeldaAt(c).getPieza();
	}

	public Lista<Pieza> getBlancas() {
		return blancas;
	}

	public Lista<Pieza> getNegras() {
		return negras;
	}

	/**
	 * Elimina la pieza de la lista de piezas activas, y la añade a la lista de
	 * eliminadas
	 * 
	 * @param p Pieza de la cual hay que realizar las acciones
	 */
	public void eliminarPieza(Pieza p) {

		if (p.getColor() == Color.WHITE) {
			blancasEliminadas.addHead(blancas.getAndRemove(p));
		} else
			negrasEliminadas.addHead(negras.getAndRemove(p));

	}

	/**
	 * Devuelve una celda a partir de una coordenada
	 * 
	 * @param c Coordenada de la cual se devuelve la celda
	 * @return Celda
	 */
	public Celda getCeldaAt(Coordenada c) {

		if (contiene(c))
			return tablero[8 - c.getRow()][c.getColumn() - 'A'];

		return null;
	}

	/**
	 * Muestra el tablero, el orden de las piezas depende del color
	 * 
	 * @param color Color del cual hay que mostrar el tablero
	 * @return El tablero con las piezas
	 */
	public String print(Color color) {

		switch (color) {
		case WHITE:
			return printAsWhite();
		default:
			return printAsBlack();
		}
	}

	/**
	 * 
	 * @return Devuelve el tablero con las negras en la parte de abajo
	 */
	private String printAsBlack() {
		String salida = "           H   G   F   E   D   C   B   A\n";

		salida += obtenerParteSuperior();

		for (int fila = tablero.length - 1; fila > 0; fila--) {
			salida += obtenerParteFichaNegra(fila);
			salida += obtenerParteDivisoria();
		}
		salida += obtenerParteFichaNegra(0);
		salida += obtenerParteInferior() + "\n";
		salida += "           H   G   F   E   D   C   B   A\n";

		return salida;
	}

	/**
	 * 
	 * @return Devuelve el tablero con las blancas en la parte de abajo
	 */
	private String printAsWhite() {
		String salida = "           A   B   C   D   E   F   G   H\n";

		salida += obtenerParteSuperior();

		for (int fila = 0; fila < tablero.length - 1; fila++) {
			salida += obtenerParteFichaBlanca(fila);
			salida += obtenerParteDivisoria();
		}
		salida += obtenerParteFichaBlanca(tablero.length - 1);
		salida += obtenerParteInferior() + "\n";
		salida += "           A   B   C   D   E   F   G   H\n";

		return salida;
	}

	/**
	 * 
	 * @return Pinta la parte superior del tablero
	 */
	private String obtenerParteSuperior() {

		return "         â•”â•�â•�â•�â•¤â•�â•�â•�â•¤â•�â•�â•�â•¤â•�â•�â•�â•¤â•�â•�â•�â•¤â•�â•�â•�â•¤â•�â•�â•�â•¤â•�â•�â•�â•—\n";
	}

	/**
	 * Crea el tablero con las piezas negras abajo
	 * 
	 * @param fila Veces que tiene que pintar
	 * @return El tablero con las piezas negras abajo
	 */
	private String obtenerParteFichaNegra(int fila) {
		// String I = "\u2502";
		String salida = "       " + (8 - fila) + " â•‘";

		for (int col = tablero[0].length - 1; col > 0; col--) {
			salida = salida + " " + tablero[fila][col] + " â”‚";
		}

		salida = salida + " " + tablero[fila][0] + " â•‘ " + (8 - fila) + "\n";

		return salida;
	}

	/**
	 * Crea el tablero con las piezas blancas abajo
	 * 
	 * @param fila Veces que tiene que pintar
	 * @return El tablero con las piezas blancas abajo
	 */
	private String obtenerParteFichaBlanca(int fila) {
		// String I = "\u2502";
		String salida = "       " + (8 - fila) + " â•‘";

		for (int col = 0; col < tablero[0].length - 1; col++) {
			salida = salida + " " + tablero[fila][col] + " â”‚";
		}

		salida = salida + " " + tablero[fila][tablero[0].length - 1] + " â•‘ " + (8 - fila) + "\n";

		return salida;
	}

	/**
	 * 
	 * @return La sepracion de las celdas
	 */
	private String obtenerParteDivisoria() {

		return "         â•Ÿâ”€â”€â”€â”¼â”€â”€â”€â”¼â”€â”€â”€â”¼â”€â”€â”€â”¼â”€â”€â”€â”¼â”€â”€â”€â”¼â”€â”€â”€â”¼â”€â”€â”€â•¢ \n";
	}

	/**
	 * 
	 * @return Devuelve la parte baja del tablero
	 */
	private String obtenerParteInferior() {

		return "         â•šâ•�â•�â•�â•§â•�â•�â•�â•§â•�â•�â•�â•§â•�â•�â•�â•§â•�â•�â•�â•§â•�â•�â•�â•§â•�â•�â•�â•§â•�â•�â•�â•�\n";
	}

	/**
	 * 
	 * @return True si el rey negro esta vivo, false si no
	 */
	public boolean blackKingIsAlive() {
		return negras.contains(blackKing);
	}

	/**
	 * 
	 * @return True si el rey blanco esta vivo, false si no
	 */
	public boolean whiteKingIsAlive() {
		return blancas.contains(whiteKing);
	}

	/**
	 * 
	 * @return Devuelve el rey negro
	 */
	public Pieza getBlackKing() {
		return blackKing;
	}

	/**
	 * 
	 * @return Devuelve el rey blanco
	 */
	public Pieza getWhiteKing() {
		return whiteKing;
	}

}
