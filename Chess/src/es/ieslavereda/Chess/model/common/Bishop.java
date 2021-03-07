package es.ieslavereda.Chess.model.common;

/**
 * Esta clase pertenece al alfil que hereda de pieza
 * 
 * @author RAUL
 *
 */
public class Bishop extends Pieza {

	public Bishop(Color color, Coordenada posicion, Tablero tablero) {
		super(posicion, tablero);

		if (color == Color.WHITE)
			tipo = Tipo.WHITE_BISHOP;
		else
			tipo = Tipo.BLACK_BISHOP;
	}
	/**
	 * Llama al método
	 */
	@Override
	public Lista<Coordenada> getNextMovements() {
		return getNextMovements(this);
	}
	/**
	 * Devuelve la lista de movimientos de la pieza
	 * @param p La pieza 
	 * @return La lista de movimientos
	 */
	public static Lista<Coordenada> getNextMovements(Pieza p) {

		Tablero t = p.tablero;
		Lista<Coordenada> lista = new Lista<>();
		Coordenada c;

		// UpRight
		c = p.posicion.diagonalUpRight();
		while (t.contiene(c) && t.getPiezaAt(c) == null) {
			lista.addHead(c);
			c = c.diagonalUpRight();
		}
		if (t.contiene(c) && t.getPiezaAt(c).getColor() != p.getColor())
			lista.addHead(c);

		// UpLeft
		c = p.posicion.diagonalUpLeft();
		while (t.contiene(c) && t.getPiezaAt(c) == null) {
			lista.addHead(c);
			c = c.diagonalUpLeft();
		}
		if (t.contiene(c) && t.getPiezaAt(c).getColor() != p.getColor())
			lista.addHead(c);
		// DownRight
		c = p.posicion.diagonalDownRight();
		while (t.contiene(c) && t.getPiezaAt(c) == null) {
			lista.addHead(c);
			c = c.diagonalDownRight();
		}
		if (t.contiene(c) && t.getPiezaAt(c).getColor() != p.getColor())
			lista.addHead(c);
		// DownLeft
		c = p.posicion.diagonalDownLeft();
		while (t.contiene(c) && t.getPiezaAt(c) == null) {
			lista.addHead(c);
			c = c.diagonalDownLeft();
		}
		if (t.contiene(c) && t.getPiezaAt(c).getColor() != p.getColor())
			lista.addHead(c);

		return lista;
	}
}
