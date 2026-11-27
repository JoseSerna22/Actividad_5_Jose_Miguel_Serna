package nomina;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Ventana que muestra en una tabla el sueldo de cada empleado
 * y en la parte inferior el total de la nómina
 */
public class VentanaNomina extends JFrame {

    public VentanaNomina(ListaEmpleados lista) {
        setTitle("Nómina de empleados");
        setSize(500, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnas = {"Nombre", "Apellidos", "Sueldo mensual"};
        DefaultTableModel modelo = new DefaultTableModel(lista.convertirMatriz(), columnas) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        JTable tabla = new JTable(modelo);

        JLabel etiquetaTotal = new JLabel("Total nómina: $ "
                + String.format("%,.2f", lista.calcularTotalNomina()));
        etiquetaTotal.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new JScrollPane(tabla), BorderLayout.CENTER);
        getContentPane().add(etiquetaTotal, BorderLayout.SOUTH);
    }
}
