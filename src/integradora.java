// Clase Pedido
class Pedido {
    String descripcion;
    int cantidad;
    double precioUnitario;

    public Pedido(String descripcion, int cantidad, double precioUnitario) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public double calcularCosto() {
        return cantidad * precioUnitario;
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
    public static final double AFORO_MAXIMO = 0;
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
    public void limpiarPedidos() {
    this.colaPedidos = new ColaPedidos();
    this.totalPedidos = 0.0;
}


    // Método para agregar un pedido con cantidad y precio unitario
    public void agregarPedido(String descripcion, int cantidad, double precioUnitario) {
        Pedido pedido = new Pedido(descripcion, cantidad, precioUnitario);
        colaPedidos.encolar(pedido);
        totalPedidos += pedido.calcularCosto();
        System.out.println("Pedido de " + descripcion + " agregado para la mesa " + numero + ".");
    }
}
