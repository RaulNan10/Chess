package es.ieslavereda.Chess.model.common;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;


public class Player implements Serializable {

	private String name;
	private Color color;

	public Player(String name, Color color) {
		this.color = color;
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
		return "Player [name=" + name + ", socket=" + socket + ", oos=" + oos + ", ois=" + ois + ", ip=" + ip
				+ ", color=" + color + "]";
	}
	
	public void movePiece(Color c) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Coordenada de la pieza");
		
	}
}
