package nomina;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

/**
 * Ventana principal con la barra de menús del programa de nómina
 */
public class VentanaPrincipal extends JFrame implements ActionListener {
    private ListaEmpleados lista = new ListaEmpleados();
    private JMenuBar barraMenu;
    private JMenu menu;
    private JMenuItem itemAgregar, itemCalcular, itemGuardar;

    public VentanaPrincipal() {
        setTitle("Nómina");
        setSize(320, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        barraMenu = new JMenuBar();
        menu = new JMenu("Nómina");
        itemAgregar = new JMenuItem("Agregar empleado");
        itemCalcular = new JMenuItem("Calcular nómina");
        itemGuardar = new JMenuItem("Guardar archivo");

        itemAgregar.addActionListener(this);
        itemCalcular.addActionListener(this);
        itemGuardar.addActionListener(this);

        menu.add(itemAgregar);
        menu.add(itemCalcular);
        menu.add(new JSeparator());
        menu.add(itemGuardar);
        barraMenu.add(menu);
        setJMenuBar(barraMenu);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == itemAgregar) {
            new VentanaNuevoEmpleado(lista).setVisible(true);
        } else if (evento.getSource() == itemCalcular) {
            new VentanaNomina(lista).setVisible(true);
        } else if (evento.getSource() == itemGuardar) {
            guardarArchivo();
        }
    }

    private void guardarArchivo() {
        JFileChooser selector = new JFileChooser();
        selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (selector.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File carpeta = selector.getSelectedFile();
            try {
                lista.guardarArchivo(carpeta);
                JOptionPane.showMessageDialog(this,
                        "Archivo Nómina.txt guardado en:\n" + carpeta.getAbsolutePath(),
                        "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "No se pudo guardar el archivo",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}
