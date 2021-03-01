package es.ieslavereda.Chess.model.common;

public class Knight extends Pieza{

	public Knight(Color color, Coordenada posicion, Tablero tablero) {
		super(posicion, tablero);
		
		if(color==Color.WHITE)
			tipo = Tipo.WHITE_KNIGHT;
		else
			tipo = Tipo.BLACK_KNIGHT;
		
	}

	@Override
	public Lista<Coordenada> getNextMovements() {
		Lista<Coordenada> lista = new Lista<Coordenada>();
		
		addCoordenada(posicion.up().diagonalUpRight(), lista);
		addCoordenada(posicion.diagonalUpLeft().up() ,lista);
		addCoordenada(posicion.right().diagonalUpRight(), lista);
		addCoordenada(posicion.left().diagonalUpLeft(), lista);
		addCoordenada(posicion.down().diagonalDownRight(), lista);
		addCoordenada(posicion.diagonalDownLeft().down() ,lista);
		addCoordenada(posicion.right().diagonalDownRight(), lista);
		addCoordenada(posicion.left().diagonalDownLeft(), lista);

		return lista;
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
	
}
