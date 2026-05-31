import java.time.LocalTime;
import java.util.Objects;

public class Alarm {
    // Atributos base
    private int id;
    private LocalTime time;
    private String label;
    private boolean active;
    
    // Atributos de composición (Objetos relacionados)
    private SoundProfile sound;
    private Recurrence recurrence;
    private SnoozeConfig snooze;
    private Challenge challenge; // Puede ser null

    public Alarm(int id, LocalTime time, String label, Recurrence recurrence) {
        this.id = id;
        this.time = Objects.requireNonNull(time, "La hora no puede ser nula");
        this.label = (label == null || label.isEmpty()) ? "Alarma" : label;
        this.recurrence = recurrence;
        this.active = true;
        this.sound = new SoundProfile(); 
        this.snooze = new SnoozeConfig();
        this.challenge = null;
    }

    // --- MÉTODOS DE LÓGICA ---

    public void ring() {
        if (this.active) {
            System.out.println("\n🔔 [ALERTA] " + label + " sonando a las " + time);
            if (hasChallenge()) {
                System.out.println("⚠️ RETO ACTIVO: " + challenge.getPrompt());
            }
        }
    }

    public boolean stop(String answer) {
        if (hasChallenge()) {
            if (challenge.solve(answer)) {
                System.out.println("✅ Reto superado.");
                return true;
            } else {
                System.out.println("❌ Respuesta incorrecta. La alarma sigue sonando.");
                return false;
            }
        }
        System.out.println("⏹️ Alarma '" + label + "' detenida.");
        return true;
    }

public void startCircadianWakeUp() {
    System.out.println("\n🌅 [DESPERTAR CIRCADIANO] Iniciando para: " + label);
    System.out.println("💡 Simulando aumento gradual de brillo en pantalla...");
    
    // Simulamos el incremento de volumen progresivo
    if (sound.isProgressive()) {
        sound.setVolume(10); // Empezamos bajo
        for (int i = 0; i < 5; i++) {
            sound.increaseVolume();
        }
    }
    System.out.println("🎶 Sonido actual: Naturaleza/Relajante (" + sound.getTrackName() + ")");
}

    // --- GETTERS Y SETTERS ---

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public SoundProfile getSound() { return sound; }
    public void setSound(SoundProfile sound) { this.sound = sound; }

    public Recurrence getRecurrence() { return recurrence; }
    public void setRecurrence(Recurrence recurrence) { this.recurrence = recurrence; }

    public SnoozeConfig getSnooze() { return snooze; }
    public void setSnooze(SnoozeConfig snooze) { this.snooze = snooze; }

    public Challenge getChallenge() { return challenge; }
    public void setChallenge(Challenge challenge) { this.challenge = challenge; }

    public boolean hasChallenge() { return challenge != null; }

    // --- UTILIDADES ---

    @Override
    public String toString() {
        String infoReto = hasChallenge() ? " [RETO: SI]" : " [RETO: NO]";
        return String.format("[%02d] %s - %s (%s)%s", 
            id, time, label, (active ? "ACTIVA" : "DES"), infoReto);
    }
}
