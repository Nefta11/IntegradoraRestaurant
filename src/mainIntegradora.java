import javax.swing.JOptionPane;

// Clase MainIntegradora
public class mainIntegradora {
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

        // Preguntar cuántas mesas desea seleccionar
        int cantidadMesasSeleccionadas = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Cuántas mesas deseas seleccionar?"));

        // Validar que la cantidad de mesas seleccionadas sea suficiente
        if (cantidadPersonas > 0 && cantidadMesasSeleccionadas > 0) {
            int totalPersonasAsignadas = 0;
            int mesasSeleccionadas = 0;

            while (mesasSeleccionadas < cantidadMesasSeleccionadas) {
                // Mostrar mesas disponibles
                mostrarMesasDisponibles(mesas);

                // Elegir una mesa
                int mesaElegida = Integer.parseInt(JOptionPane.showInputDialog(null, "Elige el número de una mesa disponible:"));

                // Validar la elección de la mesa
                if (mesaElegida >= 1 && mesaElegida <= cantidadMesas) {
                    Mesa mesa = mesas[mesaElegida - 1];

                    // Validar si la mesa está ocupada y tiene capacidad suficiente
                    if (!mesa.ocupada && mesa.personasEnMesa + cantidadPersonas <= mesa.aforoMaximo) {
                        mesa.ocupada = true;
                        mesa.personasEnMesa += cantidadPersonas;
                        totalPersonasAsignadas += cantidadPersonas;
                        mesasSeleccionadas++;
                    } else {
                        JOptionPane.showMessageDialog(null, "La mesa seleccionada no está disponible para la cantidad de personas ingresadas. Elige otra mesa.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Número de mesa no válido. Elige otra mesa.");
                }
            }

            System.out.println("Mesas asignadas correctamente para " + totalPersonasAsignadas + " personas.");

            // Resto del código...
            // Aquí puedes continuar con el menú y otras funcionalidades según tus necesidades.
        } else {
            System.out.println("Cantidad de personas o mesas no válida.");
        }
    }

    // Método para mostrar las mesas disponibles
    private static void mostrarMesasDisponibles(Mesa[] mesas) {
        System.out.println("\n--- Mesas Disponibles ---");
        for (Mesa mesa : mesas) {
            if (!mesa.ocupada) {
                System.out.println("Mesa " + mesa.numero + ": Aforo: " + mesa.personasEnMesa + "/" + mesa.aforoMaximo);
            }
        }
        System.out.println("--------------------------");
    }

    // Resto del código...
    // Puedes continuar con el resto del código según tus necesidades.
}
