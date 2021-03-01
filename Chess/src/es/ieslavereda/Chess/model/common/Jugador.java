package es.ieslavereda.Chess.model.common;

import java.io.Serializable;

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
