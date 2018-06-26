package algoritmos;

import java.util.LinkedList;
import java.util.Queue;

public class BusquedaEnAnchura {

	private final static int INFINITO = 999999999;
	int[][] nodos;
	int[] distancias;// me dice a cuantas aristas de distancia me quedan los nodos
	Queue<Integer> cola;

	public BusquedaEnAnchura(int[][] nodos) {
		this.nodos = nodos.clone();
		inicializarDistancias();
	}

	public void realizarBusqueda(int nodoOrigen) {
		int nodo = nodoOrigen;
		distancias[nodo] = 0;
		cola = new LinkedList<>();
		while (!todosNodosRecorridos()) {
			for (int i = 0; i < distancias.length; i++) {
				if (distancias[i] == INFINITO && nodos[nodo][i] != -1) {
					distancias[i] = distancias[nodo] + 1;
					cola.add(i);
				}
			}
			if (cola.isEmpty())
				break;
			nodo = cola.poll();
		}
	}

	private boolean todosNodosRecorridos() {
		for (int i = 0; i < distancias.length; i++) {
			if (distancias[i] != INFINITO)
				return false;
		}
		return true;
	}

	private void inicializarDistancias() {
		distancias = new int[nodos.length];
		for (int i = 0; i < distancias.length; i++) {
			distancias[i] = INFINITO;
		}
	}

	private void mostrar() {
		String dist = "";
		for (int i = 0; i < nodos.length; i++) {
			dist += distancias[i] + ",";
		}
		System.out.println(dist);
	}

	public static void main(String[] args) {
		int[][] matriz = { { -1, 5, 5, -1 }, { 5, -1, -1, 5 }, { 5, -1, -1, 5 }, { -1, 5, 5, -1 } };
		BusquedaEnAnchura bea = new BusquedaEnAnchura(matriz);
		bea.realizarBusqueda(0);
		bea.mostrar();

		int[][] matrizDos = {
			//    A  B   C   D   E  F   G   H   I
				{-1, 5, -1 , 5 ,-1, 5 , 5 ,-1,-1 },
				{ 5,-1,  5 ,-1 ,-1,-1 ,-1 ,-1,-1 },
				{-1, 5, -1 ,-1 , 5,-1 ,-1 ,-1,-1 },
				{ 5,-1, -1 ,-1 , 5,-1 ,-1 ,-1,-1 },
				{-1,-1,  5 , 5 ,-1, 5 ,-1 ,-1,-1 },
				{ 5,-1, -1 ,-1 , 5 ,-1,-1 ,-1,-1 },
				{ 5,-1, -1 ,-1 ,-1,-1 ,-1 , 5,-1 },
				{-1,-1, -1 ,-1 ,-1,-1 , 5 ,-1, 5 },
				{-1,-1, -1 ,-1 ,-1,-1 ,-1 , 5,-1 },
				 };
		BusquedaEnAnchura beaDos = new BusquedaEnAnchura(matrizDos);
		beaDos.realizarBusqueda(0);
		beaDos.mostrar();
	}
}
