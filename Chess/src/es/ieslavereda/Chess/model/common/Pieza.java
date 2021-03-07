package es.ieslavereda.Chess.model.common;

/**
 * Crea la clase pieza, de la cual heredarán todas las piezas que forman la
 * partida. Tiene un tipo, un tablero y una posicion
 * 
 * @author RAUL
 *
 */
public abstract class Pieza {

	protected Tipo tipo;
	protected Tablero tablero;
	protected Coordenada posicion;

	public Pieza(Coordenada posicion, Tablero tablero) {
		super();
		this.posicion = posicion;
		this.tablero = tablero;
		colocate(posicion);
	}

	/**
	 * Coloca la pieza en la coordenada indicada
	 * 
	 * @param c Coordenada en la cual hay que colocar la pieza
	 */
	private void colocate(Coordenada c) {

		tablero.getCeldaAt(posicion).setPieza(null);
		posicion = c;
		tablero.getCeldaAt(posicion).setPieza(this);

	}

	/**
	 * Comprueba que la pieza se puede mover a la coordenada
	 * 
	 * @param c Coordenada a la que se comprueba si es posible moverse
	 */
	public void moveTo(Coordenada c) {

		if (tablero.getPiezaAt(c) == null) {
			colocate(c);
		} else {
			tablero.eliminarPieza(tablero.getPiezaAt(c));
			colocate(c);
		}
	}

	public Color getColor() {
		return tipo.getColor();
	}

	@Override
	public String toString() {
		return tipo.getForma();
	}

	/**
	 * Metodo obligatorio que tendran que implementar todas las clases que hereden
	 * de pieza
	 * 
	 * @return Una lista con sus posibles movimientos
	 */
	public abstract Lista<Coordenada> getNextMovements();

	public Coordenada getPosicion() {
		return posicion;
	}

}
