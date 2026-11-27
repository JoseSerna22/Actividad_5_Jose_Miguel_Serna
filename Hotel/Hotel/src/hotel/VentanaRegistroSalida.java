package hotel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

/**
 * Ventana que muestra los datos del huésped, pide la fecha de salida,
 * calcula los días y el total a pagar, y registra la salida
 */
public class VentanaRegistroSalida extends JFrame implements ActionListener {
    private Habitacion habitacion;
    private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private JTextField campoFechaSalida;
    private JLabel etiquetaDias, etiquetaTotal;
    private JButton botonCalcular, botonRegistrarSalida;

    public VentanaRegistroSalida(Habitacion habitacion) {
        this.habitacion = habitacion;
        formato.setLenient(false);
        setTitle("Registro de salida");
        setSize(420, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        Huesped huesped = habitacion.getHuesped();
        campoFechaSalida = new JTextField(formato.format(new Date()), 12);
        etiquetaDias = new JLabel("-");
        etiquetaTotal = new JLabel("-");
        botonCalcular = new JButton("Calcular");
        botonRegistrarSalida = new JButton("Registrar salida");
        botonRegistrarSalida.setEnabled(false); // se habilita si la fecha es correcta
        botonCalcular.addActionListener(this);
        botonRegistrarSalida.addActionListener(this);

        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 10, 5, 10);
        c.anchor = GridBagConstraints.WEST;

        agregarFila(c, 0, "Habitación:", new JLabel(String.valueOf(habitacion.getNumero())));
        agregarFila(c, 1, "Huésped:", new JLabel(huesped.getNombre() + " " + huesped.getApellidos()));
        agregarFila(c, 2, "Documento:", new JLabel(huesped.getDocumento()));
        agregarFila(c, 3, "Fecha de ingreso:", new JLabel(formato.format(habitacion.getFechaIngreso())));
        agregarFila(c, 4, "Fecha de salida (dd/mm/aaaa):", campoFechaSalida);
        agregarFila(c, 5, "Días de alojamiento:", etiquetaDias);
        agregarFila(c, 6, "Total a pagar:", etiquetaTotal);

        c.gridy = 7;
        c.gridx = 0; getContentPane().add(botonCalcular, c);
        c.gridx = 1; getContentPane().add(botonRegistrarSalida, c);
    }

    private void agregarFila(GridBagConstraints c, int fila, String texto, JComponent componente) {
        c.gridy = fila;
        c.gridx = 0; getContentPane().add(new JLabel(texto), c);
        c.gridx = 1; getContentPane().add(componente, c);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == botonCalcular) {
            calcular();
        } else {
            habitacion.desocupar();
            JOptionPane.showMessageDialog(this, "Salida registrada. La habitación "
                    + habitacion.getNumero() + " queda disponible",
                    "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    private void calcular() {
        botonRegistrarSalida.setEnabled(false);
        Date fechaSalida;
        try {
            fechaSalida = formato.parse(campoFechaSalida.getText().trim());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "La fecha de salida no es válida (use dd/mm/aaaa)",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // La fecha de salida debe ser posterior a la fecha de ingreso
        if (fechaSalida.compareTo(habitacion.getFechaIngreso()) <= 0) {
            JOptionPane.showMessageDialog(this,
                    "La fecha de salida debe ser mayor a la fecha de ingreso",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        etiquetaDias.setText(String.valueOf(habitacion.calcularDias(fechaSalida)));
        etiquetaTotal.setText(String.format("$%,.0f", habitacion.calcularTotal(fechaSalida)));
        botonRegistrarSalida.setEnabled(true);
    }
}
