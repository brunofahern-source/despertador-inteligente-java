import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.EnumSet;

public class Main {
    public static void main(String[] args) {
        System.out.println("⏰ INICIANDO SMART ALARM SYSTEM CON RETOS...");
        
        // 1. Inicializar el Manager
        AlarmManager manager = new AlarmManager();
        LocalTime ahora = LocalTime.now();
        Recurrence diaria = new Recurrence(EnumSet.allOf(DayOfWeek.class));

        // 2. Crear alarmas normales
        Alarm alarma1 = new Alarm(1, ahora.plusMinutes(1), "Gimnasio", diaria);
        manager.addAlarm(alarma1);
        
        // 3. NUEVA ALARMA CON RETO MATEMÁTICO
        // Programada para dentro de 5 minutos según tu petición
        Alarm alarmaConReto = new Alarm(3, ahora.plusMinutes(5), "Despertar Pro", diaria);
        alarmaConReto.setChallenge(new MathChallenge());
        manager.addAlarm(alarmaConReto);

        System.out.println("\n--- ESTADO DE LAS ALARMAS ---");
        manager.getAlarms().forEach(System.out::println);

        // 4. PRUEBA ESPECÍFICA DEL RETO (Simulación de usuario)
        System.out.println("\n--- SIMULACIÓN DE INTERACCIÓN CON RETO ---");
        if (alarmaConReto.hasChallenge()) {
            // Mostramos el reto generado aleatoriamente
            System.out.println("🔔 Alarma sonando: " + alarmaConReto.getLabel());
            System.out.println("📝 Reto: " + alarmaConReto.getChallenge().getPrompt());
            
            // Simulamos un intento fallido y luego uno exitoso (puedes cambiar "15" por el resultado real)
            System.out.println("Intentando apagar con respuesta errónea '999'...");
            alarmaConReto.stop("999");
            
            // En una ejecución real, aquí obtendríamos la respuesta del usuario por teclado
            // Para esta prueba, usamos un valor fijo o el resultado del reto si quieres forzar el éxito:
            // String respuestaCorrecta = String.valueOf(tu_logica_aqui); 
            System.out.println("Intentando apagar con respuesta '15' (simulada)...");
            boolean exito = alarmaConReto.stop("15"); 
            
            if (exito) {
                System.out.println("✅ El sistema de retos funciona correctamente.");
            }
        }

        // 5. SIMULACIÓN DE TIEMPO (Corta para no esperar los 5 minutos)
        System.out.println("\n⏳ Ejecutando chequeo rápido del manager...");
        manager.checkActiveAlarms(ahora.plusMinutes(1)); // Debería sonar la Alarma 1
        
        // 6. PROBAR MODO VACACIONES
        System.out.println("\n🏖️ Probando MODO VACACIONES...");
        manager.toggleVacationMode(true);
        manager.checkActiveAlarms(ahora.plusMinutes(1)); // No debería sonar nada

        // Simulamos que faltan 2 minutos para la alarma
        LocalTime horaAlarma = LocalTime.of(8, 0);
        Alarm alarmaPro = new Alarm(1, horaAlarma, "Despertar Importante", diaria);
        alarmaPro.getSound().setProgressive(true);
        manager.addAlarm(alarmaPro);

        System.out.println("\n--- Probando flujo circadiano ---");
        manager.checkActiveAlarms(LocalTime.of(7, 58)); // Debería activar el amanecer progresivo
        manager.checkActiveAlarms(LocalTime.of(8, 0));  // Debería sonar la alarma y pedir el reto

        System.out.println("\n✅ Pruebas finalizadas.");
    }
}