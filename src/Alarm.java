import java.time.LocalTime;
import java.util.Objects;

/**
 * Clase que representa la lógica base de una Alarma. Se centra en el
 * almacenamiento de datos y estados.
 */
public class Alarm {
	private int id;
	private LocalTime time;
	private String label;
	private boolean active;

	// Estos objetos se implementarán en los siguientes pasos
	private SoundProfile sound;
	private Recurrence recurrence;
	private SnoozeConfig snooze;

	public Alarm(int id, LocalTime time, String label, Recurrence recurrence) {
		this.id = id;
		this.time = Objects.requireNonNull(time, "La hora no puede ser nula");
		this.label = (label == null || label.isEmpty()) ? "Alarma" : label;
		this.recurrence = recurrence;
		this.active = true; // Por defecto, una alarma creada está activa

		// Inicialización por defecto de componentes
		this.sound = new SoundProfile();
		this.snooze = new SnoozeConfig();
	}

	// --- MÉTODOS DE LÓGICA ---

	public void toggle() {
		this.active = !this.active;
	}

	public void ring() {
		if (this.active) {
			System.out.println("🔔 [" + time + "] " + label + " SONANDO...");
		}
	}

	public void stop() {
		System.out.println("⏹️ Alarma '" + label + "' detenida.");
	}

	// --- GETTERS Y SETTERS ---

	public int getId() {
		return id;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return String.format("[%02d] %s - %s (%s)", id, time, label, (active ? "ACTIVA" : "DESACTIVADA"));
	}
}
