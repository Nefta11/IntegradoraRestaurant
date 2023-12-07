

// Clase Pedido
class Pedido {
    String descripcion;
    double costo;

    public Pedido(String descripcion, double costo) {
        this.descripcion = descripcion;
        this.costo = costo;
    }
}

// Clase NodoPedido
class NodoPedido {
    Pedido pedido;
    NodoPedido siguiente;

    public NodoPedido(Pedido pedido) {
        this.pedido = pedido;
        this.siguiente = null;
    }
}

// Clase ColaPedidos
class ColaPedidos {
    NodoPedido frente;
    NodoPedido fin;

    public ColaPedidos() {
        this.frente = null;
        this.fin = null;
    }

    public void encolar(Pedido pedido) {
        NodoPedido nuevoNodo = new NodoPedido(pedido);
        if (frente == null) {
            frente = nuevoNodo;
            fin = nuevoNodo;
        } else {
            fin.siguiente = nuevoNodo;
            fin = nuevoNodo;
        }
    }

    public Pedido desencolar() {
        if (frente == null) {
            return null;
        } else {
            Pedido pedidoDesencolado = frente.pedido;
            frente = frente.siguiente;
            if (frente == null) {
                fin = null;
            }
            return pedidoDesencolado;
        }
    }
}

// Clase Mesa
class Mesa {
    int numero;
    boolean ocupada;
    int aforoMaximo = 4; // Aforo máximo de 4 personas por mesa
    int personasEnMesa;
    ColaPedidos colaPedidos;
    double totalPedidos;
    boolean cuentaPagada;

    public Mesa(int numero) {
        this.numero = numero;
        this.ocupada = false;
        this.personasEnMesa = 0;
        this.colaPedidos = new ColaPedidos();
        this.totalPedidos = 0.0;
        this.cuentaPagada = false;
    }
}
