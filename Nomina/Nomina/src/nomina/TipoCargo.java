package nomina;

/**
 * Dato enumerado que especifica el tipo de cargo que tiene un empleado
 */
public enum TipoCargo {
    DIRECTIVO, ESTRATEGICO, OPERATIVO;

    @Override
    public String toString() {
        switch (this) {
            case DIRECTIVO:   return "Directivo";
            case ESTRATEGICO: return "Estratégico";
            default:          return "Operativo";
        }
    }
}
