package es.ieslavereda.Chess.model.common;

public class Pawn extends Pieza {

	public Pawn(Color color, Coordenada posicion, Tablero tablero) {
		super(posicion, tablero);

		if (color == Color.WHITE)
			tipo = Tipo.WHITE_PAWN;
		else
			tipo = Tipo.BLACK_PAWN;

	}

	@Override
	public Lista<Coordenada> getNextMovements() {
		Lista<Coordenada> lista = new Lista();
		if (getColor() == Color.WHITE) {
			addCoordenada(posicion.up(), lista);
			if (posicion.getRow() == 2) {
				addCoordenada(posicion.up().up(), lista);
			}
			if (comprobarDiagonal(posicion.diagonalUpLeft())) {
				addCoordenada(posicion.diagonalUpLeft(), lista);
			}

			if (comprobarDiagonal(posicion.diagonalUpRight())) {
				addCoordenada(posicion.diagonalUpRight(), lista);
			}
		} else {
			addCoordenada(posicion.down(), lista);
			if (posicion.getRow() == 7) {
				addCoordenada(posicion.down().down(), lista);
			}
			if (comprobarDiagonal(posicion.diagonalDownLeft())) {
				addCoordenada(posicion.diagonalDownLeft(), lista);
			}

			if (comprobarDiagonal(posicion.diagonalDownRight())) {
				addCoordenada(posicion.diagonalDownRight(), lista);
			}
		}

		return lista;
	}

	private boolean comprobarDiagonal(Coordenada c) {
		if (tablero.contiene(c) && tablero.getCeldaAt(c).contienePieza()
				&& tablero.getPiezaAt(c).getColor() != this.getColor()) {
			return true;
		} else
			return false;
	}

	private void addCoordenada(Coordenada c, Lista<Coordenada> lista) {
		if (tablero.contiene(c)) {
			if (tablero.getCeldaAt(c).contienePieza()) {
				if (tablero.getCeldaAt(c).getPieza().getColor() != getColor())
					lista.addHead(c);
			} else {
				lista.addHead(c);
			}
		}
	}

	public void moveTo(Coordenada c) {
		super.moveTo(c);

		if (getColor() == Color.WHITE && posicion.getRow() == 8) {
			tablero.eliminarPieza(this);
			tablero.getBlancas().addHead(new Queen(Color.WHITE, c, tablero));
		} else if (getColor() == Color.BLACK && posicion.getRow() == 1) {
			tablero.eliminarPieza(this);
			tablero.getNegras().addHead(new Queen(Color.BLACK, c, tablero));

		}

	}

}
