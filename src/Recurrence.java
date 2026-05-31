import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.stream.Collectors;

/**
 * Gestiona la repetición semanal de una alarma.
 */
public class Recurrence {
    private EnumSet<DayOfWeek> days;

    /**
     * Constructor para alarmas con días específicos.
     */
    public Recurrence(EnumSet<DayOfWeek> days) {
        this.days = days != null ? days : EnumSet.noneOf(DayOfWeek.class);
    }

    /**
     * Constructor para alarmas de un solo uso (sin repetición).
     */
    public static Recurrence once() {
        return new Recurrence(EnumSet.noneOf(DayOfWeek.class));
    }

    /**
     * Comprueba si la alarma debe activarse en una fecha dada.
     * @param date Fecha a comprobar.
     * @return true si el día de la semana coincide o si es una alarma sin repetición.
     */
    public boolean isActiveToday(LocalDate date) {
        // Si no hay días marcados, es una alarma de un solo uso (suena hoy)
        if (days.isEmpty()) {
            return true;
        }
        // Comprueba si el día de la semana de 'date' está en nuestro conjunto
        return days.contains(date.getDayOfWeek());
    }

    // --- MÉTODOS DE CONVENIENCIA (Para facilitar la creación) ---

    public void addDay(DayOfWeek day) { days.add(day); }
    public void removeDay(DayOfWeek day) { days.remove(day); }

    public EnumSet<DayOfWeek> getDays() {
        return days;
    }

    @Override
    public String toString() {
        if (days.isEmpty()) return "Solo una vez";
        if (days.size() == 7) return "Todos los días";
        
        // Retorna los días en formato legible: LUNES, MARTES...
        return days.stream()
                   .map(Enum::name)
                   .collect(Collectors.joining(", "));
    }
}