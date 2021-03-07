package es.ieslavereda.Chess.model.common;

/**
 * Crea la clase de la pieza torre. Que tiene un color, una coordenada y un
 * tablero
 * 
 * @author RAUL
 *
 */
public class Rook extends Pieza {

	public Rook(Color color, Coordenada posicion, Tablero tablero) {
		super(posicion, tablero);
		// TODO Auto-generated constructor stub

		if (color == Color.WHITE)
			tipo = Tipo.WHITE_ROOK;
		else
			tipo = Tipo.BLACK_ROOK;

	}

	/**
	 * Llama al metodo de abajo
	 */
	@Override
	public Lista<Coordenada> getNextMovements() {

		return getNextMovements(this);
	}

	/**
	 * Añade a una lista todos los movimientos que son posibles para la pieza y
	 * devuelve la lista
	 */
	public static Lista<Coordenada> getNextMovements(Pieza p) {

		Tablero t = p.tablero;
		Lista<Coordenada> lista = new Lista<>();
		Coordenada c;

		c = p.posicion.up();
		while (t.contiene(c) && t.getPiezaAt(c) == null) {
			lista.addHead(c);
			c = c.up();
		}
		if (t.contiene(c) && t.getPiezaAt(c).getColor() != p.getColor())
			lista.addHead(c);

		c = p.posicion.right();
		while (t.contiene(c) && t.getPiezaAt(c) == null) {
			lista.addHead(c);
			c = c.right();
		}
		if (t.contiene(c) && t.getPiezaAt(c).getColor() != p.getColor())
			lista.addHead(c);
		c = p.posicion.down();
		while (t.contiene(c) && t.getPiezaAt(c) == null) {
			lista.addHead(c);
			c = c.down();
		}
		if (t.contiene(c) && t.getPiezaAt(c).getColor() != p.getColor())
			lista.addHead(c);
		c = p.posicion.left();
		while (t.contiene(c) && t.getPiezaAt(c) == null) {
			lista.addHead(c);
			c = c.left();
		}
		if (t.contiene(c) && t.getPiezaAt(c).getColor() != p.getColor())
			lista.addHead(c);

		return lista;
	}

}
