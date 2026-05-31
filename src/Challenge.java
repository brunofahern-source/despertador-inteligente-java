/**
 * Interfaz que define el contrato para cualquier reto 
 * que deba resolverse para apagar la alarma.
 */
public interface Challenge {
    /**
     * Genera el enunciado del reto.
     * @return String con la pregunta o instrucción.
     */
    String getPrompt();

    /**
     * Valida si la respuesta del usuario es correcta.
     * @param answer La respuesta introducida.
     * @return true si es correcta, false en caso contrario.
     */
    boolean solve(String answer);
}