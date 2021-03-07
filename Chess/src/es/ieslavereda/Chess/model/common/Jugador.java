package es.ieslavereda.Chess.model.common;

import java.io.Serializable;

/**
 * Esta clase crea un jugador con un nombre para la partida
 * 
 * @author RAUL
 *
 */
public class Jugador implements Serializable {

	private String name;

	public Jugador(String name) {
		this.name = name;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + "]";
	}

}
