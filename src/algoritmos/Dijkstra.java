package algoritmos;

import java.util.Stack;

public class Dijkstra {

	private final static int INFINITO = 999999999;
	int[][] nodos;
	int[] distancias;
	boolean[] visitados;
	Stack<Integer> pila;
	int[] camino;

	public Dijkstra(int[][] nodos) {
		this.nodos = nodos.clone();
		pila = new Stack<>();
		inicializarDistancias();
		inicializarVisitados();
		inicializarCamino();
	}

	public void econtrarDistancias(int nodoOrigen) {
		int nodo = nodoOrigen;
		distancias[nodo] = 0;
		visitados[nodo] = true;
		while (!todosNodosFueronVisitados()) {
			for (int i = 0; i < nodos.length; i++) {// 
				if (!visitados[i] && nodos[nodo][i] != -1 && distancias[i] > distancias[nodo] + nodos[nodo][i]) {
/*basicamente actualizo distancia de adyancentes que no fueron visitados, quizas ya tenian valores por recorridos
  anteriores, pero lo que busco es actulizarlo si es que con este nodo hay mejor costo
*/
					distancias[i] = distancias[nodo] + nodos[nodo][i];
					camino[i] = nodo;
				}
			}
// aca abajo buscare la distancia mas chica que no fue visitada (ya sean adyancentes actuales o de recorridos anteriores)
			int nodoMin = INFINITO;
			for (int i = 0; i < visitados.length; i++) {
				if (!visitados[i] && (nodoMin == INFINITO || distancias[i] < distancias[nodoMin])) {
					nodoMin = i;
				}
			}
			if (nodoMin != INFINITO) {
				visitados[nodoMin] = true;
				nodo = nodoMin;
			} else {
				if (!pila.isEmpty())
					nodo = pila.pop();
				break;
			}
		}
	}

	private void inicializarCamino() {
		camino = new int[nodos.length];
		for (int i = 0; i < camino.length; i++) {
			camino[i] = INFINITO;
		}
	}

	private boolean todosNodosFueronVisitados() {
		for (int i = 0; i < distancias.length; i++) {
			if (!visitados[i])
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

	private void inicializarVisitados() {
		visitados = new boolean[nodos.length];
		for (int i = 0; i < distancias.length; i++) {
			visitados[i] = false;
		}
	}

	private void mostrar() {
		String dist = "";
		String cami = "";
		for (int i = 0; i < nodos.length; i++) {
			dist += distancias[i] + ",";
			cami += camino[i] + ",";
		}
		System.out.println(dist);
		System.out.println(cami);
	}

	public static void main(String[] args) {
		// este grafo no necesita volver para atras(no se aprovecha la pila)
		int[][] matriz = { { -1, 10, -1, 20, 40 }, { -1, -1, 10, 5, -1 }, { -1, -1, -1, -1, -1 }, { -1, -1, 10, -1, 5 },
				{ -1, -1, 2, -1, -1 } };
		Dijkstra distra = new Dijkstra(matriz);
		distra.econtrarDistancias(0);
		distra.mostrar();

		// en este grafo se necesita volver para atras(se aprovecha la pila)
		int[][] matrizDos = { { -1, 10, -1, 20, 40, -1 }, { -1, -1, 10, 5, -1, 200 }, { -1, -1, -1, -1, -1, -1 },
				{ -1, -1, 10, -1, 5, -1 }, { -1, -1, 2, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 } };
		Dijkstra distraDos = new Dijkstra(matrizDos);
		distraDos.econtrarDistancias(0);
		distraDos.mostrar();

		// DIJKSTRA I = 0,10,22,15,20

		// Queue<Integer> colaPrioridad = new PriorityQueue<>();
		// colaPrioridad.add(50);
		// colaPrioridad.add(60);
		// colaPrioridad.add(30);
		// colaPrioridad.add(15);
		// colaPrioridad.add(100);
		// while(!colaPrioridad.isEmpty()) {
		// System.out.println(colaPrioridad.poll());
		// }
	}
}
