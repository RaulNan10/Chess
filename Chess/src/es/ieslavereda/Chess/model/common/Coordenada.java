package es.ieslavereda.Chess.model.common;

/**
 * Se define la clase coordenada, una coordenada es un conjunto de 2 caracteres,
 * un char(column) y un int(row)
 * 
 * @author RAUL
 *
 */
public class Coordenada {

	private int row;
	private char column;

	public Coordenada(char column, int row) {
		super();
		this.row = row;
		this.column = column;

	}

	/**
	 * 
	 * 
	 * @return Devuelve la celda inmediata arriba de la posicion de la pieza
	 */
	public Coordenada up() {
		return new Coordenada(column, row + 1);
	}

	/**
	 * 
	 * 
	 * @return Devuelve la celda inmediata abajo de la posicion de la pieza
	 */
	public Coordenada down() {
		return new Coordenada(column, row - 1);
	}

	/**
	 * 
	 * 
	 * @return Devuelve la celda inmediata a la izquierda de la posicion de la pieza
	 */
	public Coordenada left() {
		return new Coordenada((char) (column - 1), row);
	}

	/**
	 * 
	 * 
	 * @return Devuelve la celda inmediata a la derecha de la posicion de la pieza
	 */
	public Coordenada right() {
		return new Coordenada((char) (column + 1), row);
	}

	/**
	 * 
	 * 
	 * @return Devuelve la celda inmediata diagonal arriba izquierda de la posicion
	 *         de la pieza
	 */
	public Coordenada diagonalUpLeft() {
		return this.up().left();
	}

	/**
	 * 
	 * 
	 * @return Devuelve la celda inmediata diagonal derecha arriba de la posicion de
	 *         la pieza
	 */
	public Coordenada diagonalUpRight() {
		return up().right();
	}

	/**
	 * 
	 * 
	 * @return Devuelve la celda inmediata diagonal derecha abajo de la posicion de
	 *         la pieza
	 */
	public Coordenada diagonalDownRight() {
		return down().right();
	}

	/**
	 * 
	 * 
	 * @return Devuelve la celda inmediata diagonal izquierda abajo de la posicion
	 *         de la pieza
	 */
	public Coordenada diagonalDownLeft() {
		return down().left();
	}

	/**
	 * 
	 * 
	 * @return Devuelve la fila(int) de la coordenada
	 */
	public int getRow() {
		return row;
	}

	/**
	 * 
	 * 
	 * @return Devuelve la columna de la coordenada(char)
	 */
	public char getColumn() {
		return column;
	}

	/**
	 * sobreescribe el metodo equals para definir cuando se deben considerar 2
	 * objetos iguales
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordenada other = (Coordenada) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.valueOf(column) + row;
	}
}
