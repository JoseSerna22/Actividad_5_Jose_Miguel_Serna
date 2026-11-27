package hotel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Ventana que solicita el número de la habitación a entregar
 */
public class VentanaSalida extends JFrame implements ActionListener {
    private Hotel hotel;
    private JTextField campoNumero;
    private JButton botonBuscar;

    public VentanaSalida(Hotel hotel) {
        this.hotel = hotel;
        setTitle("Salida de huéspedes");
        setSize(380, 110);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        campoNumero = new JTextField(5);
        botonBuscar = new JButton("Aceptar");
        botonBuscar.addActionListener(this);

        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        getContentPane().add(new JLabel("Número de habitación:"));
        getContentPane().add(campoNumero);
        getContentPane().add(botonBuscar);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        Habitacion habitacion = null;
        try {
            habitacion = hotel.getHabitacion(Integer.parseInt(campoNumero.getText().trim()));
        } catch (NumberFormatException e) {
            // se deja habitacion en null para mostrar el error
        }
        if (habitacion == null) {
            JOptionPane.showMessageDialog(this, "Número de habitación incorrecto (1 a 10)",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else if (habitacion.isDisponible()) {
            JOptionPane.showMessageDialog(this, "La habitación " + habitacion.getNumero()
                    + " no está ocupada", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            new VentanaRegistroSalida(habitacion).setVisible(true);
            dispose();
        }
    }
}
