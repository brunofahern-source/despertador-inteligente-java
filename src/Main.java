import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.EnumSet;

public class Main {
    public static void main(String[] args) {
        System.out.println("⏰ INICIANDO SMART ALARM SYSTEM...");
        
        // 1. Inicializar el Manager
        AlarmManager manager = new AlarmManager();

        // 2. Crear una alarma para "dentro de un minuto" para probar
        LocalTime ahora = LocalTime.now();
        LocalTime horaAlarma = ahora.plusMinutes(1);
        
        // Alarma diaria (Lunes a Domingo)
        Recurrence diaria = new Recurrence(EnumSet.allOf(DayOfWeek.class));
        Alarm alarma1 = new Alarm(1, horaAlarma, "Gimnasio", diaria);
        
        // Alarma de fin de semana
        Recurrence finde = new Recurrence(EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
        Alarm alarma2 = new Alarm(2, LocalTime.of(10, 30), "Descanso", finde);

        // 3. Añadir alarmas al sistema
        manager.addAlarm(alarma1);
        manager.addAlarm(alarma2);

        System.out.println("\n--- ESTADO INICIAL ---");
        manager.getAlarms().forEach(System.out::println);

        // 4. SIMULACIÓN DE RELOJ
        // Vamos a simular que pasan 2 minutos para ver si la alarma 1 suena
        System.out.println("\n⏳ Iniciando simulación de tiempo (2 minutos)...");
        
        for (int i = 0; i <= 120; i++) { // 120 segundos
            LocalTime tiempoSimulado = ahora.plusSeconds(i);
            
            // Solo imprimimos cada 30 segundos para no saturar la consola
            if (i % 30 == 0) {
                System.out.println("🕒 Hora actual simulada: " + tiempoSimulado.getHour() + ":" + tiempoSimulado.getMinute() + ":" + tiempoSimulado.getSecond());
            }

            // El Manager chequea si debe sonar algo
            manager.checkActiveAlarms(tiempoSimulado);

            try {
                Thread.sleep(100); // Aceleramos la simulación (100ms reales = 1s simulado)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 5. PROBAR MODO VACACIONES
        System.out.println("\n🏖️ Probando MODO VACACIONES...");
        manager.toggleVacationMode(true);
        System.out.println("Intentando hacer sonar la alarma a las " + horaAlarma + " con modo vacaciones activo:");
        manager.checkActiveAlarms(horaAlarma); // No debería imprimir nada
        
        System.out.println("\n✅ Simulación finalizada con éxito.");
    }
}