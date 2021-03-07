package es.ieslavereda.Chess.model.common;

/**
 * Esta es la clase de la pieza rey
 * 
 * @author RAUL
 *
 */
public class King extends Pieza {

	public King(Color color, Coordenada posicion, Tablero tablero) {
		super(posicion, tablero);
		if (color == Color.WHITE)
			tipo = Tipo.WHITE_KING;
		else
			tipo = Tipo.BLACK_KING;
	}

	/**
	 * Crea los posibles movimientos del rey
	 */
	@Override
	public Lista<Coordenada> getNextMovements() {

		Lista<Coordenada> lista = new Lista<Coordenada>();

		addCoordenada(posicion.up(), lista);
		addCoordenada(posicion.right(), lista);
		addCoordenada(posicion.down(), lista);
		addCoordenada(posicion.left(), lista);
		addCoordenada(posicion.diagonalUpRight(), lista);
		addCoordenada(posicion.diagonalUpLeft(), lista);
		addCoordenada(posicion.diagonalDownRight(), lista);
		addCoordenada(posicion.diagonalDownLeft(), lista);

		return lista;
	}

	/**
	 * Comprueba si los movimientos del rey se pueden realizar y los añade a una
	 * lista
	 * 
	 * @param c     Coordenada que hay que comprobar
	 * @param lista Lista en la que hay que añadir la coordenada en caso de ser
	 *              correcta
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

	/**
	 * Devuelve la posicion dcel rey
	 * 
	 * @param c Color del rey
	 * @return La posicion del rey en el tablero
	 */
	public Coordenada kingCoordenada(Color c) {
		if (c == this.getColor()) {
			return posicion;
		} else
			return null;
	}

}
