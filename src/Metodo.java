import javax.swing.JOptionPane;
// Clase Metodo
public class Metodo {
    public static void mostrarMesasDisponibles(Mesa[] mesas) {
        System.out.println("\n--- Mesas Disponibles ---");
        for (Mesa mesa : mesas) {
            if (!mesa.ocupada || mesa.personasEnMesa < mesa.aforoMaximo) {
                System.out.println("Mesa " + mesa.numero + ": Aforo: " + mesa.personasEnMesa + "/" + mesa.aforoMaximo);
            }
        }
        System.out.println("--------------------------");
    }

    public static void mostrarMenu(Mesa[] mesas) {
        boolean salir = false;

        while (!salir) {
            // Mostrar opciones del menú
            String menu = "1. Mostrar Mesas Ocupadas\n2. Realizar Pedido\n3. Pagar Cuenta\n4. Desocupar Mesa\n5. Salir";
            int opcion = Integer.parseInt(JOptionPane.showInputDialog(null, menu));

            switch (opcion) {
                case 1:
                    mostrarMesasOcupadas(mesas);
                    break;
                case 2:
                    realizarPedido(mesas);
                    break;
                case 3:
                    pagarCuenta(mesas);
                    break;
                case 4:
                    desocuparMesa(mesas);
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Introduce un número del 1 al 5.");
            }
        }
    }

    public static void mostrarMesasOcupadas(Mesa[] mesas) {
        System.out.println("\n--- Mesas Ocupadas ---");
        for (Mesa mesa : mesas) {
            if (mesa.ocupada) {
                System.out.println("Mesa " + mesa.numero + ": Aforo: " + mesa.personasEnMesa + "/" + mesa.aforoMaximo);
            }
        }
        System.out.println("--------------------------");
    }

    public static void realizarPedido(Mesa[] mesas) {
        int numeroMesa = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el número de mesa para realizar el pedido:"));
        if (validarNumeroMesa(numeroMesa, mesas)) {
            Mesa mesa = mesas[numeroMesa - 1];
            if (mesa.ocupada) {
                // Menú de productos con precios
                String menuProductos = "1. Tacos ($5c/u)\n2. Coca-cola ($20)\n3. Sopa Fria ($45)\n4.Pozole ($33)\n5.Hamburguesa($55.0)\n6.Ensalada César($120)\n7.Smoothie de Frutas($85)\n8.Pizza Margarita($180.0)\n9.Café Espresso($25)";
                int opcionProducto = Integer.parseInt(JOptionPane.showInputDialog(null, "Selecciona un producto:\n" + menuProductos));

                switch (opcionProducto) {
                    case 1:
                        mesa.agregarPedido("Taco", solicitarCantidad(), 5.0); 
                        break;
                    case 2:
                        mesa.agregarPedido("Coca-cola", solicitarCantidad(), 20.0);
                        break;
                    case 3:
                        mesa.agregarPedido("Sopa Fria", solicitarCantidad(), 45.0);
                        break;
                    case 4:
                        mesa.agregarPedido("Pozole", solicitarCantidad(), 33.0);
                        break;
                    case 5:
                        mesa.agregarPedido("Hamburguesa", solicitarCantidad(), 55.0);
                        break;
                    case 6:
                        mesa.agregarPedido("Ensalada César", solicitarCantidad(), 120.0);
                        break;
                    case 7:
                        mesa.agregarPedido("Smoothie de Frutas", solicitarCantidad(), 85.0);
                        break;
                    case 8:
                        mesa.agregarPedido("Pizza Margarita", solicitarCantidad(), 180.0);
                        break;
                    case 9:
                        mesa.agregarPedido("Café Espresso", solicitarCantidad(), 25.0);
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "La mesa seleccionada no está ocupada. No se puede tomar pedido.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Número de mesa no válido. Ingresa un número de mesa ocupada.");
        }
    }

    public static void pagarCuenta(Mesa[] mesas) {
        int numeroMesa = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el número de mesa para pagar la cuenta:"));
        if (validarNumeroMesa(numeroMesa, mesas)) {
            Mesa mesa = mesas[numeroMesa - 1];
            if (mesa.ocupada) {
                if (!mesa.cuentaPagada) {
                    System.out.println("Total de la cuenta para la mesa " + numeroMesa + ": $" + mesa.totalPedidos);
                    double montoPagado = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el monto a pagar:"));
                    if (montoPagado >= mesa.totalPedidos) {
                        mesa.cuentaPagada = true;
                        System.out.println("Cuenta pagada correctamente para la mesa " + numeroMesa + ".");
                    } else {
                        JOptionPane.showMessageDialog(null, "El monto ingresado no cubre el total de la cuenta. Pago no realizado.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La cuenta para la mesa " + numeroMesa + " ya ha sido pagada.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "La mesa seleccionada no está ocupada. No se puede pagar la cuenta.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Número de mesa no válido. Ingresa un número de mesa ocupada.");
        }
    }

    public static void desocuparMesa(Mesa[] mesas) {
        int numeroMesa = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el número de mesa para desocupar:"));
        if (validarNumeroMesa(numeroMesa, mesas)) {
            liberarMesa(mesas, numeroMesa);
            System.out.println("Mesa " + numeroMesa + " desocupada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Número de mesa no válido. Ingresa un número de mesa ocupada.");
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

    private static int solicitarCantidad() {
        return Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad:"));
    }

    static boolean validarNumeroMesa(int numeroMesa, Mesa[] mesas) {
        return (numeroMesa >= 1 && numeroMesa <= mesas.length);
    }
    
}
