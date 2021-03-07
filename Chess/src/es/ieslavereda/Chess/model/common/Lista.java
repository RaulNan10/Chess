package es.ieslavereda.Chess.model.common;

/**
 * Crea la clase lista, que tiene un tamaño, una cabeza, y una cola
 * 
 * @author RAUL
 *
 * @param <T>
 */
public class Lista<T> {

	private int size;
	private Nodo<T> cabeza;
	private Nodo<T> cola;

	public Lista() {
		super();

		size = 0;
		cabeza = null;
		cola = null;
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Añade un elemento a la lista y lo define como nueva cabeza
	 * 
	 * @param info
	 */
	public void addHead(T info) {

		Nodo<T> nodo = new Nodo<T>(info);

		if (cabeza == null) {
			cabeza = nodo;
			cola = nodo;
		} else {
			nodo.setSiguiente(cabeza);
			cabeza.setAnterior(nodo);
			cabeza = nodo;
		}

		size++;
	}

	/**
	 * Añade todos los elementos de una lista a una nueva
	 * 
	 * @param lista Lista de la cual hay que añadir su informacion a la nueva lista
	 * @return Lista nueva con toda la informacion (nodos no) de la lista pasada por
	 *         parametro
	 */
	public Lista<T> addAll(Lista<T> lista) {

		Nodo<T> aux = lista.cabeza;
		while (aux != null) {
			addHead(aux.getInfo());
			aux = aux.getSiguiente();
		}

		return this;

	}

	/**
	 * Devuelve el nodo de la cabeza
	 * 
	 * @return
	 */
	public Nodo<T> getNodo() {
		return cabeza;
	}

	/**
	 * Añade un elemento a la lista y lo define como cola
	 * 
	 * @param info
	 */
	public void addTail(T info) {

		Nodo<T> aux = new Nodo<T>(info);

		if (cabeza == null) {
			cabeza = aux;
			cola = aux;
		} else {
			aux.setAnterior(cola);
			cola.setSiguiente(aux);
			cola = aux;
		}
		size++;
	}

	/**
	 * Devuelve solo el valor de la cabeza y lo elimina de la lista
	 * 
	 * @return La informacion del nodo
	 */
	public T getHead() {
		T valor = null;

		if (cabeza == null) {
			return null;
		} else if (cabeza.equals(cola)) {
			valor = cabeza.getInfo();
			cabeza = null;
			cola = null;
			size--;
		} else {
			valor = cabeza.getInfo();
			cabeza = cabeza.getSiguiente();
			cabeza.setAnterior(null);
			// cabeza.getSiguiente().setAnterior(null);

			size--;
		}

		return valor;
	}

	/**
	 * Devuelve solo el valor de la cola y lo elimina de la lista
	 * 
	 * @return
	 */
	public T getTail() {
		T valor = null;

		if (cabeza == null) {
			return null;
		} else if (cabeza == cola) {
			valor = cabeza.getInfo();
			cabeza = null;
			cola = null;
			size--;
		} else {
			valor = cola.getInfo();
			cola.getAnterior().setSiguiente(null);
			cola = cola.getAnterior();
			size--;
		}

		return valor;

	}

	/**
	 * Busca un elemento en la lista
	 * 
	 * @param info Elemento a buscar
	 * @return -1 si este elemento no esta en la lista y 0 si lo esta
	 */
	public int search(T info) {

		int posicion = -1;

		Nodo<T> aux = cabeza;
		int i = 0;

		while (aux != null && posicion == -1) {
			if (info.equals(aux.getInfo()))
				posicion = i;
			i++;
			aux = aux.getSiguiente();
		}

		return posicion;
	}

	/**
	 * Devuelve la informacion de una posicion y la elimina de la lista
	 * 
	 * @param index Posicion a buscar
	 * @return La informacion
	 */
	public T get(int index) {

		if (index == 0)
			return getHead();
		else if (index == size - 1)
			return getTail();
		else if (index > 0 && index < size) {

			Nodo<T> aux = cabeza;
			int i = 0;
			while (i < index) {
				aux = aux.getSiguiente();
				i++;
			}

			aux.getAnterior().setSiguiente(aux.getSiguiente());
			aux.getSiguiente().setAnterior(aux.getAnterior());
			size--;

			return aux.getInfo();

		} else
			return null;
	}

	/**
	 * Busca un elemento en la lista lo devuelve y lo elimina
	 * 
	 * @param elemento Elemento a buscar
	 * @return El valor del elemento
	 */
	public T getAndRemove(T elemento) {

		if (elemento.equals(cabeza))
			return getHead();
		else if (elemento.equals(cola))
			return getTail();
		else {

			Nodo<T> aux = cabeza;
			T valor = null;

			while (aux != null && valor == null) {
				if (aux.getInfo().equals(elemento))
					valor = aux.getInfo();
				else
					aux = aux.getSiguiente();
			}
			if (valor != null) {
				aux.getAnterior().setSiguiente(aux.getSiguiente());
				aux.getSiguiente().setAnterior(aux.getAnterior());
				size--;
			}
			return valor;

		}
	}

	@Override
	public String toString() {

		String salida = "";
		salida = "size: " + size + "\n";
		salida += "valores:\n ";

		Nodo<T> aux = cabeza;

		while (aux != null) {
			salida += aux.toString() + " ";
			aux = aux.getSiguiente();
		}

		return salida;
	}

	public boolean contains(T elemento) {
		boolean contiene = false;
		Nodo<T> nodo = cabeza;
		while (nodo != null && !contiene) {
			if (nodo.getInfo().equals(elemento))
				contiene = true;
			nodo = nodo.getSiguiente();
		}

		return contiene;
	}

}
