import javax.swing.JOptionPane;

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

// Clase Integradora
public class integradora {
    public static void tomarPedido(Mesa[] mesas, int numeroMesa) {
        if (numeroMesa >= 1 && numeroMesa <= mesas.length) {
            Mesa mesa = mesas[numeroMesa - 1];
            if (mesa.ocupada) {
                String descripcionPedido = JOptionPane.showInputDialog(null, "Ingrese la descripción del pedido: ");
                // Precio fijo para los pedidos
                double costoPedido = 10.0; // Puedes cambiar esto según tus necesidades
                Pedido pedido = new Pedido(descripcionPedido, costoPedido);
                mesa.colaPedidos.encolar(pedido);
                mesa.totalPedidos += costoPedido;
                System.out.println("Pedido registrado para la mesa " + numeroMesa + ".");
            } else {
                System.out.println("La mesa " + numeroMesa + " no está ocupada. No se puede tomar pedido.");
            }
        } else {
            System.out.println("Número de mesa no válido.");
        }
    }

    public static void liberarMesa(Mesa[] mesas, int numeroMesa) {
        if (numeroMesa >= 1 && numeroMesa <= mesas.length) {
            Mesa mesa = mesas[numeroMesa - 1];
            if (mesa.ocupada && mesa.cuentaPagada) {
                mesa.ocupada = false;
                mesa.personasEnMesa = 0;
                mesa.colaPedidos = new ColaPedidos();
                mesa.totalPedidos = 0.0;
                mesa.cuentaPagada = false;
                System.out.println("Mesa " + numeroMesa + " liberada correctamente.");
            } else {
                System.out.println("La mesa " + numeroMesa + " no puede ser liberada. Asegúrese de que la cuenta esté pagada.");
            }
        } else {
            System.out.println("Número de mesa no válido.");
        }
    }

    public static void mostrarEstadoMesas(Mesa[] mesas) {
        System.out.println("\n--- Estado de las Mesas ---");
        for (Mesa mesa : mesas) {
            System.out.println("Mesa " + mesa.numero + ": " + (mesa.ocupada ? "Ocupada" : "Desocupada") + " - Aforo: " + mesa.personasEnMesa + "/" + mesa.aforoMaximo);
            if (mesa.ocupada) {
                System.out.println("Pedidos en cola:");
                NodoPedido nodoActual = mesa.colaPedidos.frente;
                while (nodoActual != null) {
                    System.out.println(" - " + nodoActual.pedido.descripcion + " ($" + nodoActual.pedido.costo + ")");
                    nodoActual = nodoActual.siguiente;
                }
                System.out.println("Total de Pedidos: $" + mesa.totalPedidos);
                if (mesa.cuentaPagada) {
                    System.out.println("Cuenta Pagada");
                } else {
                    System.out.println("Cuenta Pendiente de Pago");
                }
            }
            System.out.println("--------------------------");
        }
    }
}
