import javax.swing.JOptionPane;

// Clase MainIntegradora
public class MainIntegradora {
    public static void main(String[] args) {
        // Mostrar mensaje de bienvenida
        JOptionPane.showMessageDialog(null, "¡Bienvenido al Restaurante!");

        int cantidadMesas = 10;
        Mesa[] mesas = new Mesa[cantidadMesas];

        for (int i = 0; i < cantidadMesas; i++) {
            mesas[i] = new Mesa(i + 1);
        }

        // Preguntar cuántas personas quieren reservar
        int cantidadPersonas = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Para cuántas personas quieres reservar?"));

        // Validar la cantidad de personas
        if (cantidadPersonas > 0) {
            int totalPersonasAsignadas = 0;

            // Mientras queden personas por asignar
            while (totalPersonasAsignadas < cantidadPersonas) {
                // Mostrar mesas disponibles
                mostrarMesasDisponibles(mesas);

                // Elegir una mesa
                int mesaElegida = Integer.parseInt(JOptionPane.showInputDialog(null, "Elige el número de una mesa disponible:"));

                // Validar la elección de la mesa
                if (mesaElegida >= 1 && mesaElegida <= cantidadMesas) {
                    Mesa mesa = mesas[mesaElegida - 1];

                    // Validar si la mesa está ocupada y tiene capacidad suficiente
                    if (!mesa.ocupada || mesa.personasEnMesa < mesa.aforoMaximo) {
                        // Asignar personas a la mesa
                        int personasAsignar = Math.min(cantidadPersonas - totalPersonasAsignadas, mesa.aforoMaximo - mesa.personasEnMesa);
                        mesa.personasEnMesa += personasAsignar;
                        totalPersonasAsignadas += personasAsignar;

                        // Marcar la mesa como ocupada
                        mesa.ocupada = true;

                        System.out.println("Mesa " + mesa.numero + " asignada correctamente para " + personasAsignar + " personas.");
                    } else {
                        JOptionPane.showMessageDialog(null, "La mesa seleccionada no está disponible. Elige otra mesa.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Número de mesa no válido. Elige otra mesa.");
                }
            }

            // Mostrar menú de opciones
            mostrarMenu(mesas);

        } else {
            System.out.println("Cantidad de personas no válida.");
        }
    }

    // Método para mostrar las mesas disponibles
    private static void mostrarMesasDisponibles(Mesa[] mesas) {
        System.out.println("\n--- Mesas Disponibles ---");
        for (Mesa mesa : mesas) {
            if (!mesa.ocupada || mesa.personasEnMesa < mesa.aforoMaximo) {
                System.out.println("Mesa " + mesa.numero + ": Aforo: " + mesa.personasEnMesa + "/" + mesa.aforoMaximo);
            }
        }
        System.out.println("--------------------------");
    }

    // Método para mostrar el menú de opciones después de asignar mesas
    private static void mostrarMenu(Mesa[] mesas) {
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

    // Método para mostrar las mesas ocupadas
    private static void mostrarMesasOcupadas(Mesa[] mesas) {
        System.out.println("\n--- Mesas Ocupadas ---");
        for (Mesa mesa : mesas) {
            if (mesa.ocupada) {
                System.out.println("Mesa " + mesa.numero + ": Aforo: " + mesa.personasEnMesa + "/" + mesa.aforoMaximo);
            }
        }
        System.out.println("--------------------------");
    }

    // Método para realizar un pedido
    private static void realizarPedido(Mesa[] mesas) {
        int numeroMesa = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el número de mesa para realizar el pedido:"));
        if (validarNumeroMesa(numeroMesa, mesas)) {
            Mesa mesa = mesas[numeroMesa - 1];
            if (mesa.ocupada) {
                String descripcionPedido = JOptionPane.showInputDialog(null, "Ingrese la descripción del pedido: ");
                double costoPedido = 10.0; // Puedes cambiar esto según tus necesidades
                Pedido pedido = new Pedido(descripcionPedido, costoPedido);
                mesa.colaPedidos.encolar(pedido);
                mesa.totalPedidos += costoPedido;
                System.out.println("Pedido registrado para la mesa " + numeroMesa + ".");
            } else {
                JOptionPane.showMessageDialog(null, "La mesa seleccionada no está ocupada. No se puede tomar pedido.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Número de mesa no válido. Ingresa un número de mesa ocupada.");
        }
    }

    // Método para pagar la cuenta
    private static void pagarCuenta(Mesa[] mesas) {
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

    // Método para desocupar una mesa
    private static void desocuparMesa(Mesa[] mesas) {
        int numeroMesa = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el número de mesa para desocupar:"));
        if (validarNumeroMesa(numeroMesa, mesas)) {
            liberarMesa(mesas, numeroMesa);
            System.out.println("Mesa " + numeroMesa + " desocupada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Número de mesa no válido. Ingresa un número de mesa ocupada.");
        }
    }

    // Método para liberar una mesa
    private static void liberarMesa(Mesa[] mesas, int numeroMesa) {
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

    // Método para validar si el número de mesa es válido y está ocupada
    private static boolean validarNumeroMesa(int numeroMesa, Mesa[] mesas) {
        return (numeroMesa >= 1 && numeroMesa <= mesas.length && mesas[numeroMesa - 1].ocupada);
    }

    // ... (código anterior)
}
