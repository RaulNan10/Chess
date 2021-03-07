package es.ieslavereda.Chess.model.common;

import java.util.*;

import es.ieslavereda.Chess.tools.Input;

/**
 * Aqui se crea la clase juego, que consta de 2 jugador para cada color, un
 * tablero y una variable turno para definir quien mueve pieza
 * 
 * @author RAUL
 *
 */
public class Juego {

	private Jugador white;
	private Jugador black;
	private Tablero board;
	private Color turn;

	public Juego(Jugador j1, Jugador j2) {
		board = new Tablero();
		turn = Color.WHITE;
		white = j1;
		black = j2;

	}

	/**
	 * Cambia el turno de la partida
	 */
	private void cambiarTurno() {
		turn = Color.values()[(turn.ordinal() + 1) % Color.values().length];
	}

	/**
	 * Este método es el que llama a los demás métodos para que la partida avance
	 */
	public void start() {
		do {
			switch (turn) {
			case WHITE:
				System.out.println(board.print(Color.WHITE));
				compruebaJaque(Color.WHITE);
				if (compruebaJaque(Color.WHITE) == true) {
					System.out.println("¡Estás en jaque!");
				}
				movePiece(white);
				cambiarTurno();

			case BLACK:
				System.out.println(board.print(Color.BLACK));
				compruebaJaque(Color.BLACK);
				if (compruebaJaque(Color.BLACK) == true) {
					System.out.println("¡Estás en jaque!");
				}

				movePiece(black);
				cambiarTurno();
			}
		} while (board.blackKingIsAlive() && board.whiteKingIsAlive());

		if (board.blackKingIsAlive())
			System.out.println("BLACK WINS");
		else
			System.out.println("WHITE WINS");
	}

	/**
	 * Este método se encarga de realizar los movimientos de un jugador
	 * 
	 * @param player El jugador que tiene que mover pieza
	 */
	private void movePiece(Jugador player) {

		Coordenada pieza;

		boolean moved = false;

		do {
			pieza = Input.getCoordenada("Introduce la coordenada de la pieza que quieres mover");
			if (board.getCeldaAt(pieza).contienePieza() == true
					&& board.getCeldaAt(pieza).getPieza().getColor() == turn) {

				comprobarMovimiento(pieza);
				moved = true;
			}
		} while (moved == false);

	}

	/**
	 * Comprueba que la coordenada de movimiento que introduce el jugador es posible
	 * 
	 * @param coordenadaPieza Coordenada
	 */
	private void comprobarMovimiento(Coordenada coordenadaPieza) {
		Coordenada movimiento;

		movimiento = Input.getCoordenada("Introduce la coordenada a la que te quieres mover");

		if (board.getCeldaAt(coordenadaPieza).getPieza().getNextMovements().contains(movimiento)) {
			board.getCeldaAt(coordenadaPieza).getPieza().moveTo(movimiento);

		} else
			comprobarMovimiento(coordenadaPieza);
	}

	/**
	 * Pide al usuario que introduzca una coordenada
	 * 
	 * @param msg Mensaje que se puede mostrar por pantalla
	 * @return La coordenada introducida por el usuario
	 */
	public static Coordenada getCoordenada(String msg) {
		Coordenada c = null;
		String texto;

		do {
			try {
				texto = getString(msg).toUpperCase();
				if (texto.length() != 2)
					System.out.println("Solo debes incluir dos caracteres");
				else if (texto.charAt(0) < 'A' || texto.charAt(0) > 'H')
					System.out.println("La letra debe estar comprendida entre [A-H]");
				else if (Integer.parseInt(String.valueOf(texto.charAt(1))) < 1
						|| Integer.parseInt(String.valueOf(texto.charAt(1))) > 8)
					System.out.println("El numero debe estar comprendido entre [1-8]");
				else
					c = new Coordenada(texto.charAt(0), Integer.parseInt(String.valueOf(texto.charAt(1))));
			} catch (Exception e) {
				System.out.println("Debes introducir un numero en el segundo caracter.");
			}

		} while (c == null);

		return c;
	}

	/**
	 * Comprueba si hay jaque
	 * 
	 * @param color Color del cual hay que comprobar si el rey esta en jaque
	 * @return True si hay jaque para el rey de ese color, false si no lo hay
	 */
	private boolean compruebaJaque(Color color) {
		Lista<Coordenada> movements = new Lista<Coordenada>();
		if (color == Color.BLACK) {
			movements = getColorMoves(Color.WHITE);
			if (movements.contains(board.getBlackKing().getPosicion())) {
				return true;
			}
		} else {
			movements = getColorMoves(Color.BLACK);
			if (movements.contains(board.getWhiteKing().getPosicion())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Almacena los movimientos de las piezas de color pasado por parámetro
	 * 
	 * @param color Color de las piezas de las que comprobaremos sus movimientos
	 * @return lista Lista con los movimientos de las piezas
	 */
	private Lista<Coordenada> getColorMoves(Color color) {
		Lista<Coordenada> lista = new Lista<Coordenada>();
		if (color == Color.BLACK) {
			Nodo<Pieza> aux = board.getNegras().getNodo();
			while (aux != null) {
				lista.addAll(aux.getInfo().getNextMovements());
				aux = aux.getSiguiente();
			}
		}
		if (color == Color.WHITE) {
			Nodo<Pieza> aux = board.getBlancas().getNodo();
			while (aux != null) {
				lista.addAll(aux.getInfo().getNextMovements());
				aux = aux.getSiguiente();
			}
		}
		return lista;
	}

	public static String getString(String msg) {
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.next();
	}

	/**
	 * Comprueba que el numero de la coordenada es correcto
	 * 
	 * @param msg String del cual hay que extraer el numero
	 * @return Devuelve el numero de la coordenada
	 */
	public static int getInt(String msg) {
		int salida = 0;
		boolean error = true;

		do {
			try {
				salida = Integer.parseInt(getString(msg));
				error = false;
			} catch (Exception e) {
				System.out.println("Debe introducir un numero.");
			}
		} while (error);

		return salida;

	}
}
