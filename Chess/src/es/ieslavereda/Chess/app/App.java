package es.ieslavereda.Chess.app;

import es.ieslavereda.Chess.model.common.*;

/**
 * En esta clase se ejecuta el juego, simplemente crear 2 jugadores y un juego y
 * lo ejecuta
 * 
 * @author RAUL
 *
 */
public class App {

	public static void main(String[] args) {
		Jugador j1 = new Jugador("Raul");
		Jugador j2 = new Jugador("Joaquin");
		Juego juego = new Juego(j1, j2);

		juego.start();
	}

}
