import javax.swing.JOptionPane;

public class mainIntegradora {
    public static void main(String[] args) {
        int cantidadMesas = 10;
        Mesa[] mesas = new Mesa[cantidadMesas];

        for (int i = 0; i < cantidadMesas; i++) {
            mesas[i] = new Mesa(i + 1);
        }

        int opcion;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Selecciona la opción que deseas ejecutar: \n1. Asignar Mesa\n2. Liberar Mesa\n3. Tomar Pedido\n4. Mostrar Estado de Mesas\n5. Salir"));

            switch (opcion) {
                case 1:
                    int numeroMesaAsignar = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de mesa a asignar: "));
                    asignarMesa(mesas, numeroMesaAsignar);
                    break;
                case 2:
                    int numeroMesaLiberar = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de mesa a liberar: "));
                    liberarMesa(mesas, numeroMesaLiberar);
                    break;
                case 3:
                    int numeroMesaTomarPedido = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de mesa para tomar el pedido: "));
                    tomarPedido(mesas, numeroMesaTomarPedido);
                    break;
                case 4:
                    mostrarEstadoMesas(mesas);
                    break;
                case 5:
                    System.out.println("¡Gracias por su visita al restaurante!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        } while (opcion != 5);
    }

    public static void asignarMesa(Mesa[] mesas, int numeroMesa) {
        if (numeroMesa >= 1 && numeroMesa <= mesas.length) {
            if (!mesas[numeroMesa - 1].ocupada) {
                mesas[numeroMesa - 1].ocupada = true;
                System.out.println("Mesa " + numeroMesa + " asignada correctamente.");
            } else {
                System.out.println("La mesa " + numeroMesa + " ya está ocupada.");
            }
        } else {
            System.out.println("Número de mesa no válido.");
        }
    }

    public static void liberarMesa(Mesa[] mesas, int numeroMesa) {
        if (numeroMesa >= 1 && numeroMesa <= mesas.length) {
            if (mesas[numeroMesa - 1].ocupada) {
                mesas[numeroMesa - 1].ocupada = false;
                System.out.println("Mesa " + numeroMesa + " liberada correctamente.");
            } else {
                System.out.println("La mesa " + numeroMesa + " no está ocupada.");
            }
        } else {
            System.out.println("Número de mesa no válido.");
        }
    }

    public static void tomarPedido(Mesa[] mesas, int numeroMesa) {
        if (numeroMesa >= 1 && numeroMesa <= mesas.length) {
            if (mesas[numeroMesa - 1].ocupada) {
                String descripcionPedido = JOptionPane.showInputDialog(null, "Ingrese la descripción del pedido: ");
                double costoPedido = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el costo del pedido: "));
                Pedido pedido = new Pedido(descripcionPedido, costoPedido);
                mesas[numeroMesa - 1].colaPedidos.encolar(pedido);
                mesas[numeroMesa - 1].totalPedidos += costoPedido;
                System.out.println("Pedido registrado para la mesa " + numeroMesa + ".");
            } else {
                System.out.println("La mesa " + numeroMesa + " no está ocupada. No se puede tomar pedido.");
            }
        } else {
            System.out.println("Número de mesa no válido.");
        }
    }

    public static void mostrarEstadoMesas(Mesa[] mesas) {
        System.out.println("\n--- Estado de las Mesas ---");
        for (Mesa mesa : mesas) {
            System.out.println("Mesa " + mesa.numero + ": " + (mesa.ocupada ? "Ocupada" : "Desocupada"));
            if (mesa.ocupada) {
                System.out.println("Pedidos en cola:");
                NodoPedido nodoActual = mesa.colaPedidos.frente;
                while (nodoActual != null) {
                    System.out.println(" - " + nodoActual.pedido.descripcion + " ($" + nodoActual.pedido.costo + ")");
                    nodoActual = nodoActual.siguiente;
                }
                System.out.println("Total de Pedidos: $" + mesa.totalPedidos);
            }
            System.out.println("--------------------------");
        }
    }
}