package es.ieslavereda.Chess.test;

import es.ieslavereda.Chess.model.common.Color;
import es.ieslavereda.Chess.model.common.Coordenada;
import es.ieslavereda.Chess.model.common.Juego;
import es.ieslavereda.Chess.model.common.Jugador;
import es.ieslavereda.Chess.model.common.Tablero;

public class Test {

	public static void main(String[] args) {
		Coordenada c = new Coordenada('F',5);
		
		Tablero t = new Tablero();
	
		System.out.println(t.print(Color.WHITE));
		
//		Jugador j1 = new Jugador("Elpaco");
//		Jugador j2 = new Jugador("Elpepe");
//		
//		Juego juego = new Juego(j1,j2);
//		
//		juego.start();
		Coordenada pawn = new Coordenada('c',3);
		System.out.println(t.getCeldaAt(pawn).getPieza().getNextMovements());
	}

}
