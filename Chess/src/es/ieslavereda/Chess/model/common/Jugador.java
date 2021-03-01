package es.ieslavereda.Chess.model.common;

import java.io.Serializable;

public class Jugador implements Serializable {

	private String name;
r;

	public Jugador(String name) {
		this.name = name;

	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "Player [name=" + name +  "color=" + color + "]";
	}

}
