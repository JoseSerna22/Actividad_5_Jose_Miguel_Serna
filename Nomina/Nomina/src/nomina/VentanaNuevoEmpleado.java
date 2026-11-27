package nomina;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Ventana para ingresar los datos de un empleado
 */
public class VentanaNuevoEmpleado extends JFrame implements ActionListener {
    private ListaEmpleados lista;
    private JTextField campoNombre, campoApellidos, campoSalario,
            campoOtrosIngresos, campoSalud, campoPensiones;
    private JList<TipoCargo> listaCargo;
    private JCheckBox casillaMasculino, casillaFemenino;
    private JSpinner spinnerDias;
    private JButton botonAgregar, botonLimpiar;

    public VentanaNuevoEmpleado(ListaEmpleados lista) {
        this.lista = lista;
        setTitle("Agregar empleado");
        setSize(400, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(10, 2, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        campoNombre = new JTextField();
        campoApellidos = new JTextField();

        listaCargo = new JList<>(TipoCargo.values());
        listaCargo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaCargo.setSelectedIndex(0);

        // JCheckBox agrupados para que solo se pueda marcar uno
        casillaMasculino = new JCheckBox("Masculino", true);
        casillaFemenino = new JCheckBox("Femenino");
        ButtonGroup grupoGenero = new ButtonGroup();
        grupoGenero.add(casillaMasculino);
        grupoGenero.add(casillaFemenino);
        JPanel panelGenero = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelGenero.add(casillaMasculino);
        panelGenero.add(casillaFemenino);

        campoSalario = new JTextField();

        SpinnerNumberModel modeloDias = new SpinnerNumberModel();
        modeloDias.setMinimum(1);
        modeloDias.setMaximum(31);
        modeloDias.setValue(30);
        spinnerDias = new JSpinner();
        spinnerDias.setModel(modeloDias);

        campoOtrosIngresos = new JTextField("0");
        campoSalud = new JTextField("0");
        campoPensiones = new JTextField("0");

        botonAgregar = new JButton("Agregar");
        botonLimpiar = new JButton("Limpiar");
        botonAgregar.addActionListener(this);
        botonLimpiar.addActionListener(this);

        panel.add(new JLabel("Nombre:"));            panel.add(campoNombre);
        panel.add(new JLabel("Apellidos:"));         panel.add(campoApellidos);
        panel.add(new JLabel("Cargo:"));             panel.add(new JScrollPane(listaCargo));
        panel.add(new JLabel("Género:"));            panel.add(panelGenero);
        panel.add(new JLabel("Salario por día:"));   panel.add(campoSalario);
        panel.add(new JLabel("Días trabajados:"));   panel.add(spinnerDias);
        panel.add(new JLabel("Otros ingresos:"));    panel.add(campoOtrosIngresos);
        panel.add(new JLabel("Pagos por salud:"));   panel.add(campoSalud);
        panel.add(new JLabel("Aporte pensiones:"));  panel.add(campoPensiones);
        panel.add(botonAgregar);                     panel.add(botonLimpiar);

        getContentPane().add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == botonAgregar) {
            agregarEmpleado();
        } else {
            limpiarCampos();
        }
    }

    private void agregarEmpleado() {
        String nombre = campoNombre.getText().trim();
        String apellidos = campoApellidos.getText().trim();
        if (nombre.isEmpty() || apellidos.isEmpty() || listaCargo.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            TipoCargo cargo = listaCargo.getSelectedValue();
            char genero = casillaMasculino.isSelected() ? 'M' : 'F';
            double salarioDia = Double.parseDouble(campoSalario.getText().trim());
            int dias = (Integer) spinnerDias.getValue();
            double otros = Double.parseDouble(campoOtrosIngresos.getText().trim());
            double salud = Double.parseDouble(campoSalud.getText().trim());
            double pensiones = Double.parseDouble(campoPensiones.getText().trim());

            if (salarioDia < 0 || otros < 0 || salud < 0 || pensiones < 0) {
                throw new NumberFormatException();
            }

            lista.agregarEmpleado(new Empleado(nombre, apellidos, cargo, genero,
                    salarioDia, dias, otros, salud, pensiones));
            JOptionPane.showMessageDialog(this, "Empleado agregado",
                    "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Los campos de salario, ingresos, salud y pensiones deben ser números válidos",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        campoNombre.setText("");
        campoApellidos.setText("");
        listaCargo.setSelectedIndex(0);
        casillaMasculino.setSelected(true);
        campoSalario.setText("");
        spinnerDias.setValue(30);
        campoOtrosIngresos.setText("0");
        campoSalud.setText("0");
        campoPensiones.setText("0");
    }
}
