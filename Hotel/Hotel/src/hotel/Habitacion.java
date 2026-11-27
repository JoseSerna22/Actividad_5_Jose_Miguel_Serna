package hotel;

import java.util.Date;

/**
 * Clase que representa una habitación del hotel
 */
public class Habitacion {
    private int numero;
    private double precio;
    private boolean disponible = true;
    private Huesped huesped;
    private Date fechaIngreso;

    public Habitacion(int numero, double precio) {
        this.numero = numero;
        this.precio = precio;
    }

    /** Registra el ingreso de un huésped: la habitación queda no disponible */
    public void ocupar(Huesped huesped, Date fechaIngreso) {
        this.huesped = huesped;
        this.fechaIngreso = fechaIngreso;
        this.disponible = false;
    }

    /** Registra la salida del huésped: la habitación queda disponible */
    public void desocupar() {
        this.huesped = null;
        this.fechaIngreso = null;
        this.disponible = true;
    }

    /** Número de días entre la fecha de ingreso y la fecha de salida */
    public long calcularDias(Date fechaSalida) {
        long milisegundos = fechaSalida.getTime() - fechaIngreso.getTime();
        return Math.round(milisegundos / (1000.0 * 60 * 60 * 24));
    }

    /** Total a pagar = días de alojamiento * precio por día */
    public double calcularTotal(Date fechaSalida) {
        return calcularDias(fechaSalida) * precio;
    }

    public int getNumero() { return numero; }
    public double getPrecio() { return precio; }
    public boolean isDisponible() { return disponible; }
    public Huesped getHuesped() { return huesped; }
    public Date getFechaIngreso() { return fechaIngreso; }
}
