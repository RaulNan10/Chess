package es.ieslavereda.Chess.model.common;

public class Bishop extends Pieza {

	public Bishop(Color color,Coordenada posicion, Tablero tablero) {
		super(posicion, tablero);
		
		if(color==Color.WHITE)
			tipo = Tipo.WHITE_BISHOP;
		else
			tipo = Tipo.BLACK_BISHOP;
	}

	@Override
	public Lista<Coordenada> getNextMovements() {
		return getNextMovements(this);
	}
	
	public static Lista<Coordenada> getNextMovements(Pieza p){
		
		Tablero t = p.tablero;
		Lista<Coordenada> lista = new Lista<>();
		Coordenada c;
		
		// UpRight 
		c= p.posicion.diagonalUpRight();
		while(t.contiene(c) && t.getPiezaAt(c)==null) {
			lista.addHead(c);
			c=c.diagonalUpRight();
		}
		if(t.contiene(c) && t.getPiezaAt(c).getColor() != p.getColor()) 
			lista.addHead(c);
		
		// UpLeft
		c= p.posicion.diagonalUpLeft();
		while(t.contiene(c) && t.getPiezaAt(c)==null) {
			lista.addHead(c);
			c=c.diagonalUpLeft();
		}
		if(t.contiene(c) && t.getPiezaAt(c).getColor() != p.getColor()) 
			lista.addHead(c);
		// DownRight
		c= p.posicion.diagonalDownRight();
		while(t.contiene(c) && t.getPiezaAt(c)==null) {
			lista.addHead(c);
			c=c.diagonalDownRight();
		}
		if(t.contiene(c) && t.getPiezaAt(c).getColor() != p.getColor()) 
			lista.addHead(c);
		// DownLeft
		c= p.posicion.diagonalDownLeft();
		while(t.contiene(c) && t.getPiezaAt(c)==null) {
			lista.addHead(c);
			c=c.diagonalDownLeft();
		}
		if(t.contiene(c) && t.getPiezaAt(c).getColor() != p.getColor()) 
			lista.addHead(c);
		
		return lista;
	}
}
