public class integradora{
    boolean[] mesasOcupadas;

    // Constructor
    public integradora(int cantidadMesas) {
        mesasOcupadas = new boolean[cantidadMesas];
    }

    // Método para asignar una mesa
    public void asignarMesa(int numeroMesa) {
        if (numeroMesa >= 0 && numeroMesa < mesasOcupadas.length) {
            if (!mesasOcupadas[numeroMesa]) {
                mesasOcupadas[numeroMesa] = true;
                System.out.println("Mesa " + numeroMesa + " asignada correctamente.");
            } else {
                System.out.println("La mesa " + numeroMesa + " ya está ocupada.");
            }
        } else {
            System.out.println("Número de mesa no válido.");
        }
    }

    // Método para liberar una mesa
    public void liberarMesa(int numeroMesa) {
        if (numeroMesa >= 0 && numeroMesa < mesasOcupadas.length) {
            if (mesasOcupadas[numeroMesa]) {
                mesasOcupadas[numeroMesa] = false;
                System.out.println("Mesa " + numeroMesa + " liberada correctamente.");
            } else {
                System.out.println("La mesa " + numeroMesa + " no está ocupada.");
            }
        } else {
            System.out.println("Número de mesa no válido.");
        }
    }

    // Método para mostrar el estado de las mesas
    public void mostrarEstadoMesas() {
        System.out.println("\n--- Estado de las Mesas ---");
        for (int i = 0; i < mesasOcupadas.length; i++) {
            System.out.println("Mesa " + i + ": " + (mesasOcupadas[i] ? "Ocupada" : "Desocupada"));
        }
        System.out.println("--------------------------");
    }
}
