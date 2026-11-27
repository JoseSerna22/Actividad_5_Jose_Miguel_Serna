package hotel;

/**
 * Clase que contiene las diez habitaciones del hotel.
 * Las habitaciones 1 a 5 cuestan $120.000 y las 6 a 10 cuestan $160.000.
 */
public class Hotel {
    public static final int NUM_HABITACIONES = 10;
    private Habitacion[] habitaciones = new Habitacion[NUM_HABITACIONES];

    public Hotel() {
        for (int i = 0; i < NUM_HABITACIONES; i++) {
            double precio = (i < 5) ? 120000 : 160000;
            habitaciones[i] = new Habitacion(i + 1, precio);
        }
    }

    /** Retorna la habitación con el número dado (1 a 10) o null si no existe */
    public Habitacion getHabitacion(int numero) {
        if (numero < 1 || numero > NUM_HABITACIONES) {
            return null;
        }
        return habitaciones[numero - 1];
    }
}
