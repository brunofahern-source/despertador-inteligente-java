import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador principal que gestiona el ciclo de vida de las alarmas.
 */
public class AlarmManager {
    private List<Alarm> alarms;
    private boolean vacationMode;

    public AlarmManager() {
        this.alarms = new ArrayList<>();
        this.vacationMode = false;
    }

    // --- GESTIÓN DE ALARMAS ---

    public void addAlarm(Alarm alarm) {
        alarms.add(alarm);
        System.out.println("✅ Alarma añadida: " + alarm.getLabel());
    }

    public boolean removeAlarm(int id) {
        return alarms.removeIf(a -> a.getId() == id);
    }

    public List<Alarm> getAlarms() {
        return new ArrayList<>(alarms); // Retorna copia para proteger la original
    }

    // --- LÓGICA DE CONTROL ---

    /**
     * Activa o desactiva el modo vacaciones.
     * Si está activo, ninguna alarma sonará.
     */
    public void toggleVacationMode(boolean status) {
        this.vacationMode = status;
        String msg = vacationMode ? "ACTIVADO" : "DESACTIVADO";
        System.out.println("🏖️ Modo Vacaciones " + msg);
    }

    /**
     * Comprueba si alguna alarma debe sonar en este momento.
     * @param now Hora actual para comparar.
     */
    public void checkActiveAlarms(LocalTime now) {
        if (vacationMode) {
            return; // No se hace nada si estamos de vacaciones
        }

        // Buscamos alarmas que coincidan en hora y minuto, y estén activas
        for (Alarm alarm : alarms) {
            if (alarm.isActive() && 
                alarm.getTime().getHour() == now.getHour() && 
                alarm.getTime().getMinute() == now.getMinute()) {
                
                alarm.ring(); // Ejecuta la lógica de sonar
            }
        }
    }

    /**
     * Devuelve la lista de próximas alarmas configuradas.
     */
    public List<Alarm> getActiveAlarms() {
        return alarms.stream()
                .filter(Alarm::isActive)
                .collect(Collectors.toList());
    }

    public boolean isVacationMode() {
        return vacationMode;
    }
}
