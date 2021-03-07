package es.ieslavereda.Chess.model.common;

/**
 * Esta clase crea la pieza caballo
 * 
 * @author RAUL
 *
 */
public class Knight extends Pieza {

	public Knight(Color color, Coordenada posicion, Tablero tablero) {
		super(posicion, tablero);

		if (color == Color.WHITE)
			tipo = Tipo.WHITE_KNIGHT;
		else
			tipo = Tipo.BLACK_KNIGHT;

	}

	/**
	 * Crea los posibles movimientos del caballo
	 */
	@Override
	public Lista<Coordenada> getNextMovements() {
		Lista<Coordenada> lista = new Lista<Coordenada>();

		addCoordenada(posicion.up().diagonalUpRight(), lista);
		addCoordenada(posicion.diagonalUpLeft().up(), lista);
		addCoordenada(posicion.right().diagonalUpRight(), lista);
		addCoordenada(posicion.left().diagonalUpLeft(), lista);
		addCoordenada(posicion.down().diagonalDownRight(), lista);
		addCoordenada(posicion.diagonalDownLeft().down(), lista);
		addCoordenada(posicion.right().diagonalDownRight(), lista);
		addCoordenada(posicion.left().diagonalDownLeft(), lista);

		return lista;
	}

	/**
	 * Comprueba si los movimientos de el caballo se pueden realizar
	 * 
	 * @param c     Coordenada a comprobar
	 * @param lista Lista a la que hay que añadir la coordenada en caso de que se
	 *              puede realizar ese movimiento
	 */
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

}
