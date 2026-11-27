package nomina;

/**
 * Clase que representa a un empleado de la empresa
 */
public class Empleado {
    private String nombre;
    private String apellidos;
    private TipoCargo cargo;
    private char genero;          // 'M' masculino, 'F' femenino
    private double salarioDia;
    private int diasTrabajados;
    private double otrosIngresos;
    private double pagosSalud;
    private double aportePensiones;

    public Empleado(String nombre, String apellidos, TipoCargo cargo, char genero,
                    double salarioDia, int diasTrabajados, double otrosIngresos,
                    double pagosSalud, double aportePensiones) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cargo = cargo;
        this.genero = genero;
        this.salarioDia = salarioDia;
        this.diasTrabajados = diasTrabajados;
        this.otrosIngresos = otrosIngresos;
        this.pagosSalud = pagosSalud;
        this.aportePensiones = aportePensiones;
    }

    /**
     * Salario mensual = (días trabajados * sueldo por día) + otros ingresos
     *                   - pagos por salud - aporte pensiones
     */
    public double calcularNomina() {
        return (diasTrabajados * salarioDia) + otrosIngresos - pagosSalud - aportePensiones;
    }

    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public TipoCargo getCargo() { return cargo; }
    public char getGenero() { return genero; }
    public double getSalarioDia() { return salarioDia; }
    public int getDiasTrabajados() { return diasTrabajados; }
    public double getOtrosIngresos() { return otrosIngresos; }
    public double getPagosSalud() { return pagosSalud; }
    public double getAportePensiones() { return aportePensiones; }
}
