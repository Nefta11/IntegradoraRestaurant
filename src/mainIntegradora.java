import javax.swing.JOptionPane;

public class mainIntegradora {
    public static void main(String[] args) {
        int cantidadMesas = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de mesas en el restaurante: "));
        integradora mesas = new integradora(cantidadMesas);

        int opcion;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Selecciona la opción que deseas ejecutar: \n1. Asignar Mesa\n2. Liberar Mesa\n3. Mostrar Estado de Mesas\n4. Salir"));

            switch (opcion) {
                case 1:
                    int numeroMesaAsignar = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de mesa a asignar: "));
                    mesas.asignarMesa(numeroMesaAsignar);
                    break;
                case 2:
                    int numeroMesaLiberar = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de mesa a liberar: "));
                    mesas.liberarMesa(numeroMesaLiberar);
                    break;
                case 3:
                    mesas.mostrarEstadoMesas();
                    break;
                case 4:
                    System.out.println("¡Gracias por su visita al restaurante!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        } while (opcion != 4);
    }
}
