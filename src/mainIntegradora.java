import javax.swing.JOptionPane;

// Clase MainIntegradora
public class MainIntegradora {
    public static void main(String[] args) {
        // Mostrar mensaje de bienvenida
        JOptionPane.showMessageDialog(null, "¡Bienvenido al Restaurante!");

        // Preguntar cuántas personas quieren reservar
        int cantidadPersonas = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Para cuántas personas quieres reservar?"));

        if (cantidadPersonas > 0) {
            int cantidadMesas = 10;
            Mesa[] mesas = new Mesa[cantidadMesas];

            for (int i = 0; i < cantidadMesas; i++) {
                mesas[i] = new Mesa(i + 1);
            }

            // Mostrar mesas disponibles
            Metodo.mostrarMesasDisponibles(mesas);

            int totalPersonasAsignadas = 0;

            // Permitir seleccionar mesas hasta que se asignen todas las personas
            while (totalPersonasAsignadas < cantidadPersonas) {
                // Elegir una mesa
                int mesaElegida = Integer.parseInt(JOptionPane.showInputDialog(null, "Elige el número de una mesa disponible:"));

                // Validar la elección de la mesa
                if (Metodo.validarNumeroMesa(mesaElegida, mesas)) {
                    Mesa mesa = mesas[mesaElegida - 1];

                    // Calcular cuántas personas se pueden agregar a la mesa
                    int personasRestantes = cantidadPersonas - totalPersonasAsignadas;
                    int personasAgregadas = Math.min(personasRestantes, mesa.aforoMaximo - mesa.personasEnMesa);

                    // Asignar personas a la mesa
                    mesa.ocupada = true;
                    mesa.personasEnMesa += personasAgregadas;
                    totalPersonasAsignadas += personasAgregadas;

                    // Mostrar mensaje de asignación
                    System.out.println("Personas asignadas a la mesa " + mesa.numero + ": " + personasAgregadas);
                } else {
                    JOptionPane.showMessageDialog(null, "Número de mesa no válido o mesa llena. Elige otra mesa.");
                }

                // Mostrar mesas disponibles después de cada asignación
                Metodo.mostrarMesasDisponibles(mesas);
            }

            // Mensaje al final del bucle
            System.out.println("Mesas asignadas correctamente para " + totalPersonasAsignadas + " personas.");

            // Llamar a la clase Metodo para mostrar el menú y realizar operaciones en las mesas
            Metodo.mostrarMenu(mesas);
        } else {
            JOptionPane.showMessageDialog(null, "Cantidad de personas no válida.");
        }
    }
}
