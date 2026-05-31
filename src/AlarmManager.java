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
     * Comprueba si alguna alarma debe iniciar el despertar circadiano o sonar.
     * @param now Hora actual para comparar.
     */
    public void checkActiveAlarms(LocalTime now) {
        if (vacationMode) {
            return; // No se hace nada si estamos de vacaciones
        }

        for (Alarm alarm : alarms) {
            if (!alarm.isActive()) {
                continue;
            }

            // 1. Inicio del despertar circadiano (2 minutos antes de la hora configurada)
            LocalTime circadianStart = alarm.getTime().minusMinutes(2);
            if (now.getHour() == circadianStart.getHour() && 
                now.getMinute() == circadianStart.getMinute()) {
                alarm.startCircadianWakeUp();
            }

            // 2. Alarma principal y Reto (en el minuto exacto)
            if (now.getHour() == alarm.getTime().getHour() && 
                now.getMinute() == alarm.getTime().getMinute()) {
                alarm.ring();
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
