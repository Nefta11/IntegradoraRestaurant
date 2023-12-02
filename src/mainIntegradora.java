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

        // Asignar mesa
        int mesaElegida = asignarMesaDisponible(mesas, cantidadPersonas);

        if (mesaElegida != -1) {
            System.out.println("Mesa " + mesaElegida + " asignada correctamente para " + cantidadPersonas + " personas.");

            int opcion;
            do {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Selecciona la opción que deseas ejecutar: \n1. Tomar Pedido\n2. Liberar Mesa\n3. Mostrar Estado de Mesas\n4. Salir"));

                switch (opcion) {
                    case 1:
                        integradora.tomarPedido(mesas, mesaElegida);
                        break;
                    case 2:
                        integradora.liberarMesa(mesas, mesaElegida);
                        break;
                    case 3:
                        integradora.mostrarEstadoMesas(mesas);
                        break;4
                    case 4:
                        System.out.println("¡Gracias por su visita al restaurante!");
                        break;
                    default:
                        System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            } while (opcion != 4);
        } else {
            System.out.println("Lo siento, no hay mesas disponibles para la cantidad de personas ingresadas.");
        }
    }

    public static int asignarMesaDisponible(Mesa[] mesas, int cantidadPersonas) {
        if (cantidadPersonas > 0) {
            for (Mesa mesa : mesas) {
                if (!mesa.ocupada && mesa.personasEnMesa + cantidadPersonas <= mesa.aforoMaximo) {
                    mesa.ocupada = true;
                    mesa.personasEnMesa += cantidadPersonas;
                    return mesa.numero;
                }
            }
        }
        return -1; // Indica que no hay mesas disponibles
    }
}
