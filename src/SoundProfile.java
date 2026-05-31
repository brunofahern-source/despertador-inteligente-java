
/**
 * Clase que gestiona el sonido de la alarma.
 * Incluye el nombre de la pista, el volumen y si es progresivo.
 */
public class SoundProfile {
    private String trackName;
    private int volume;
    private boolean isProgressive;

    public SoundProfile() {
        // Valores por defecto
        this.trackName = "Default_Beep.mp3";
        this.volume = 50;
        this.isProgressive = false;
    }

    // Método requerido por el diagrama
    public void increaseVolume() {
        if (volume < 100) {
            volume += 5;
        }
    }

    // Getters y Setters básicos para evitar errores futuros
    public String getTrackName() { return trackName; }
    public void setTrackName(String trackName) { this.trackName = trackName; }

    public int getVolume() { return volume; }
    public void setVolume(int volume) { this.volume = volume; }

    public boolean isProgressive() { return isProgressive; }
    public void setProgressive(boolean progressive) { isProgressive = progressive; }
}
