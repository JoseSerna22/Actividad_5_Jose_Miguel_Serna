package nomina;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

/**
 * Clase que almacena la lista de empleados ingresados
 */
public class ListaEmpleados {
    private Vector<Empleado> lista = new Vector<>();

    public void agregarEmpleado(Empleado e) {
        lista.add(e);
    }

    public int getCantidad() {
        return lista.size();
    }

    /** Total de la nómina = suma de los salarios mensuales de todos los empleados */
    public double calcularTotalNomina() {
        double total = 0;
        for (int i = 0; i < lista.size(); i++) {
            total += lista.elementAt(i).calcularNomina();
        }
        return total;
    }

    /** Convierte la lista en una matriz para mostrarla en un JTable */
    public Object[][] convertirMatriz() {
        Object[][] datos = new Object[lista.size()][3];
        for (int i = 0; i < lista.size(); i++) {
            Empleado e = lista.elementAt(i);
            datos[i][0] = e.getNombre();
            datos[i][1] = e.getApellidos();
            datos[i][2] = String.format("%,.2f", e.calcularNomina());
        }
        return datos;
    }

    /** Genera el archivo Nómina.txt en la carpeta indicada */
    public void guardarArchivo(File carpeta) throws IOException {
        File archivo = new File(carpeta, "Nómina.txt");
        archivo.createNewFile();
        BufferedWriter salida = new BufferedWriter(new FileWriter(archivo));
        salida.write("NÓMINA DE EMPLEADOS\n");
        salida.write("Nombre\tApellidos\tSueldo mensual\n");
        for (int i = 0; i < lista.size(); i++) {
            Empleado e = lista.elementAt(i);
            salida.write(e.getNombre() + "\t" + e.getApellidos() + "\t"
                    + String.format("%.2f", e.calcularNomina()) + "\n");
        }
        salida.write("\nTotal nómina: " + String.format("%.2f", calcularTotalNomina()) + "\n");
        salida.close();
    }
}
