package algoritmos;

public class NodoNodoCosto{

	int nodo1;
	int nodo2;
	int costo;

	public NodoNodoCosto(int nodo1, int nodo2, int costo) {
		this.nodo1 = nodo1;
		this.nodo2 = nodo2;
		this.costo = costo;
	}

	public int getNodo1() {
		return nodo1;
	}

	public void setNodo1(int nodo1) {
		this.nodo1 = nodo1;
	}

	public int getNodo2() {
		return nodo2;
	}

	public void setNodo2(int nodo2) {
		this.nodo2 = nodo2;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}
}
