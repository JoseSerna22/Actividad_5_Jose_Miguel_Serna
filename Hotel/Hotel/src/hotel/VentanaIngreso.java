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
 * Ventana para registrar el ingreso de un huésped a una habitación
 */
public class VentanaIngreso extends JFrame implements ActionListener {
    private Habitacion habitacion;
    private VentanaConsultar ventanaConsultar;
    private JTextField campoFecha, campoNombre, campoApellidos, campoDocumento;
    private JButton botonRegistrar;

    public VentanaIngreso(Habitacion habitacion, VentanaConsultar ventanaConsultar) {
        this.habitacion = habitacion;
        this.ventanaConsultar = ventanaConsultar;
        setTitle("Ingreso de huésped");
        setSize(380, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        campoFecha = new JTextField(new SimpleDateFormat("dd/MM/yyyy").format(new Date()), 12);
        campoNombre = new JTextField(12);
        campoApellidos = new JTextField(12);
        campoDocumento = new JTextField(12);
        botonRegistrar = new JButton("Registrar");
        botonRegistrar.addActionListener(this);

        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 10, 5, 10);
        c.anchor = GridBagConstraints.WEST;

        agregarFila(c, 0, "Habitación:", new JLabel(String.valueOf(habitacion.getNumero())));
        agregarFila(c, 1, "Fecha de ingreso (dd/mm/aaaa):", campoFecha);
        agregarFila(c, 2, "Nombre:", campoNombre);
        agregarFila(c, 3, "Apellidos:", campoApellidos);
        agregarFila(c, 4, "Documento de identidad:", campoDocumento);

        c.gridx = 0; c.gridy = 5; c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        getContentPane().add(botonRegistrar, c);
    }

    private void agregarFila(GridBagConstraints c, int fila, String texto, JComponent componente) {
        c.gridy = fila;
        c.gridx = 0; getContentPane().add(new JLabel(texto), c);
        c.gridx = 1; getContentPane().add(componente, c);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        String textoFecha = campoFecha.getText().trim();
        String nombre = campoNombre.getText().trim();
        String apellidos = campoApellidos.getText().trim();
        String documento = campoDocumento.getText().trim();

        // Todos los campos son obligatorios
        if (textoFecha.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || documento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Date fechaIngreso;
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            formato.setLenient(false);
            fechaIngreso = formato.parse(textoFecha);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "La fecha de ingreso no es válida (use dd/mm/aaaa)",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        habitacion.ocupar(new Huesped(nombre, apellidos, documento), fechaIngreso);
        ventanaConsultar.actualizar();
        JOptionPane.showMessageDialog(this,
                "Ingreso registrado en la habitación " + habitacion.getNumero(),
                "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
