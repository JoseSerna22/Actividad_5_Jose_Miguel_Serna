package hotel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Ventana que muestra las diez habitaciones con su estado y permite
 * seleccionar una habitación para registrar el ingreso de un huésped
 */
public class VentanaConsultar extends JFrame implements ActionListener {
    private Hotel hotel;
    private JLabel[] etiquetasEstado = new JLabel[Hotel.NUM_HABITACIONES];
    private JSpinner spinnerHabitacion;
    private JButton botonIngresar;

    public VentanaConsultar(Hotel hotel) {
        this.hotel = hotel;
        setTitle("Consultar habitaciones");
        setSize(380, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 10, 4, 10);
        c.anchor = GridBagConstraints.WEST;

        // Encabezados
        c.gridy = 0;
        c.gridx = 0; getContentPane().add(new JLabel("Habitación"), c);
        c.gridx = 1; getContentPane().add(new JLabel("Precio"), c);
        c.gridx = 2; getContentPane().add(new JLabel("Estado"), c);

        // Una fila por habitación
        for (int i = 1; i <= Hotel.NUM_HABITACIONES; i++) {
            Habitacion h = hotel.getHabitacion(i);
            c.gridy = i;
            c.gridx = 0; getContentPane().add(new JLabel(String.valueOf(i)), c);
            c.gridx = 1; getContentPane().add(new JLabel(String.format("$%,.0f", h.getPrecio())), c);
            etiquetasEstado[i - 1] = new JLabel();
            c.gridx = 2; getContentPane().add(etiquetasEstado[i - 1], c);
        }

        // Selección de la habitación a ocupar
        SpinnerNumberModel modelo = new SpinnerNumberModel();
        modelo.setMinimum(1);
        modelo.setMaximum(Hotel.NUM_HABITACIONES);
        modelo.setValue(1);
        spinnerHabitacion = new JSpinner();
        spinnerHabitacion.setModel(modelo);

        botonIngresar = new JButton("Ingresar huésped");
        botonIngresar.addActionListener(this);

        c.gridy = Hotel.NUM_HABITACIONES + 1;
        c.gridx = 0; getContentPane().add(new JLabel("Habitación a ocupar:"), c);
        c.gridx = 1; getContentPane().add(spinnerHabitacion, c);
        c.gridx = 2; getContentPane().add(botonIngresar, c);

        actualizar();
    }

    /** Refresca el estado (disponible / no disponible) de cada habitación */
    public void actualizar() {
        for (int i = 1; i <= Hotel.NUM_HABITACIONES; i++) {
            etiquetasEstado[i - 1].setText(
                    hotel.getHabitacion(i).isDisponible() ? "Disponible" : "No disponible");
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        int numero = (Integer) spinnerHabitacion.getValue();
        Habitacion habitacion = hotel.getHabitacion(numero);
        if (!habitacion.isDisponible()) {
            JOptionPane.showMessageDialog(this, "La habitación " + numero + " está ocupada",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            new VentanaIngreso(habitacion, this).setVisible(true);
        }
    }
}
