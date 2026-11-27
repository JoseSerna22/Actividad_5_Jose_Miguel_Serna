package hotel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Ventana principal con el menú del programa del hotel
 */
public class VentanaPrincipal extends JFrame implements ActionListener {
    private Hotel hotel = new Hotel();
    private JMenuBar barraMenu;
    private JMenu menu;
    private JMenuItem itemConsultar, itemSalida;

    public VentanaPrincipal() {
        setTitle("Hotel");
        setSize(320, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        barraMenu = new JMenuBar();
        menu = new JMenu("Menú");
        itemConsultar = new JMenuItem("Consultar habitaciones");
        itemSalida = new JMenuItem("Salida de huéspedes");
        itemConsultar.addActionListener(this);
        itemSalida.addActionListener(this);
        menu.add(itemConsultar);
        menu.add(itemSalida);
        barraMenu.add(menu);
        setJMenuBar(barraMenu);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == itemConsultar) {
            new VentanaConsultar(hotel).setVisible(true);
        } else if (evento.getSource() == itemSalida) {
            new VentanaSalida(hotel).setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}
