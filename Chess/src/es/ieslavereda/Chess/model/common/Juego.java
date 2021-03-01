package es.ieslavereda.Chess.model.common;

import java.util.*;

import es.ieslavereda.Chess.tools.Input;

import java.io.Serializable;

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

	private void cambiarTurno() {
		turn = Color.values()[(turn.ordinal() + 1) % Color.values().length];
	}

	public void start() {
		do {
			switch (turn) {
			case WHITE:
				movePiece(white);
				break;
			case BLACK:
				movePiece(black);
				break;
			}
		} while (board.blackKingIsAlive() && board.whiteKingIsAlive());

		if (board.blackKingIsAlive())
			System.out.println("BLACK WINS");
		else
			System.out.println("WHITE WINS");
	}

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
		} while (!moved);

	}

	private void comprobarMovimiento(Coordenada pieza) {
		Coordenada movimiento;
		movimiento = Input.getCoordenada("Introduce la coordenada a la que te quieres mover");

		if (board.getCeldaAt(pieza).getPieza().getNextMovements().contains(movimiento)) {
			board.getCeldaAt(pieza).getPieza().moveTo(movimiento);
		} else
			comprobarMovimiento(pieza);
	}

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

	public static String getString(String msg) {
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.next();
	}

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
