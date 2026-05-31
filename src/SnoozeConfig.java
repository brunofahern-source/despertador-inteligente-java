
/**
 * Gestiona la configuración de la función de posponer (snooze).
 * Controla el intervalo de tiempo y el número máximo de repeticiones.
 */
public class SnoozeConfig {
    private int intervalMinutes;
    private int maxTries;
    private int currentTries;

    public SnoozeConfig() {
        // Valores por defecto: 9 minutos, máximo 3 veces
        this.intervalMinutes = 9;
        this.maxTries = 3;
        this.currentTries = 0;
    }

    /**
     * Incrementa el contador de posposiciones.
     * @return true si se puede posponer, false si se alcanzó el límite.
     */
    public boolean snooze() {
        if (currentTries < maxTries) {
            currentTries++;
            return true;
        }
        return false;
    }

    // Getters y Setters
    public int getIntervalMinutes() { return intervalMinutes; }
    public void setIntervalMinutes(int intervalMinutes) { this.intervalMinutes = intervalMinutes; }

    public int getMaxTries() { return maxTries; }
    public void setMaxTries(int maxTries) { this.maxTries = maxTries; }

    public int getCurrentTries() { return currentTries; }
    public void resetTries() { this.currentTries = 0; }
}
