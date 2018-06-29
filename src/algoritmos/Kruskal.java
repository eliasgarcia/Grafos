package algoritmos;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Kruskal {

	int[][] nodos;
	int[] padre;// aca almacenare el padre de cada nodo
	int[][] arbolMin;
	int costo;
	private Queue<NodoNodoCosto> monticulo;

	public Kruskal(int[][] grafo) {
		nodos = grafo.clone();
		arbolMin = new int[nodos.length][nodos.length];
		costo = 0;
		padre = new int[nodos.length];
		monticulo = new PriorityQueue<>(new Comparator<NodoNodoCosto>() {
			public int compare(NodoNodoCosto o1, NodoNodoCosto o2) {
				return o1.getCosto() - o2.getCosto();
			}
		});
	}

	public void ejecutar() {

		cargarColaPrioridad();
		inicializarPadre();

		NodoNodoCosto n;
		int n1 = 0, n2 = 0, p1, p2, c;
		int cantAristas = 0;
		while (!monticulo.isEmpty() && cantAristas<nodos.length-1) {
			n = monticulo.poll(); // extraigo la raiz (sería el de menor costo)
			n1 = n.getNodo1();
			n2 = n.getNodo2();
			c = n.getCosto();
			p1 = find(n1);
			p2 = find(n2);
			if (p1 != p2) { // si tienen el mismo padre --> ya pertenecen a mismo conjunto y los ignoro
				costo += c;
				arbolMin[n1][n2] = c;
				union(n1, n2);
				cantAristas++;
			}
		}
		System.out.println("Aristas: "  + cantAristas);
	}

	private void cargarColaPrioridad() {
		// Se cargan los costos de las aristas
		for (int i = 0; i < nodos.length; i++) {
			for (int j = i; j < nodos.length; j++) {// recorro la mitad
				if (nodos[i][j] != -1) {
					monticulo.offer(new NodoNodoCosto(i, j, nodos[i][j]));
				}
			}
		}
	}

	private void inicializarPadre() {
		// En principio cada nodo es referente de sí mismo
		for (int i = 0; i < padre.length; i++) {
			padre[i] = i;
		}
	}

	private int find(int x) {
		return (x == padre[x]) ? x : find(padre[x]);
	}

	private void union(int x, int y) {
		padre[find(x)] = find(y);
	}

	private void mostrar() {
		String dist = "";
		for (int i = 0; i < nodos.length; i++) {
			for (int j = 0; j < nodos.length; j++) {
				dist += arbolMin[i][j] + ",";
			}
			dist += "\n";
		}
		System.out.println(dist);
	}

	public static void main(String[] args) {
		int[][] matriz = { { -1, 2, 2, 50 }, { 2, -1, 50, 2 }, { 2, 50, -1, 2 }, { 50, 2, 2, -1 } };
		Kruskal krukal = new Kruskal(matriz);
		krukal.ejecutar();
		krukal.mostrar();
		
		//0,2,2,0,
		//0,0,0,2,
		//0,0,0,0,
		//0,0,0,0,
	}
}
