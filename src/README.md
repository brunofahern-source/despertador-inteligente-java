# despertador-inteligente-java
⏰ Smart Alarm System - Java

### 1. Descripción del proyecto

Smart Alarm System es un sistema de gestión de alarmas inteligente desarrollado en Java. A diferencia de un despertador convencional, este sistema integra dinámicas de Despertar Circadiano (progresión sensorial) y Retos Cognitivos (Gamificación) para asegurar que el usuario no solo se despierte, sino que active su mente antes de apagar la alerta.

### 2. Objetivos

Evitar el "Snooze" infinito: Mediante la resolución de retos matemáticos obligatorios.
Mejorar la higiene del sueño: Implementando un despertar progresivo que evita el estrés del sonido repentino.
Gestión Flexible: Permitir configuraciones específicas para vacaciones y repeticiones semanales.

### 3. Tecnologías utilizadas

Lenguaje: Java JDK 17+
Gestor de versiones: Git / GitHub
Modelado: Mermaid (UML)
Lógica de Tiempo: API java.time

### 4. Instalación y ejecución

Clonar el repositorio: git clone https://github.com/brunofahern-source/despertador-inteligente-java.git
Navegar a la carpeta: cd despertador-inteligente-java
Compilar: javac src/*.java -d bin
Ejecutar: java -cp bin Main

### 5. Estructura del proyecto

/src                # Código fuente (.java)
README.md           # Guía principal del proyecto

### 6. Diseño Orientado a Objetos

El sistema aplica los siguientes pilares:
Clases y Responsabilidades: * AlarmManager: Actúa como controlador central (Low Coupling).
Alarm: Entidad que encapsula los datos y el estado de la alerta.
Challenge (Interfaz): Define el contrato para la extensibilidad de retos.
Relaciones: Se utiliza Composición para SoundProfile y SnoozeConfig, ya que su ciclo de vida depende de la alarma. Se utiliza Agregación para el Challenge, ya que es opcional.
Encapsulación: Todos los atributos son private y se acceden mediante métodos public (Getters/Setters), protegiendo la lógica interna.

### 7. Diagrama de Clases UML (Mermaid)
```
classDiagram
    class AlarmManager {
        -List~Alarm~ alarms
        -boolean vacationMode
        +addAlarm(Alarm alarm)
        +removeAlarm(int id)
        +toggleVacationMode(boolean status)
        +checkActiveAlarms(LocalTime now)
    }

    class Alarm {
        -int id
        -LocalTime time
        -String label
        -boolean active
        -SoundProfile sound
        -Recurrence recurrence
        -SnoozeConfig snooze
        -Challenge challenge
        +startCircadianWakeUp()
        +ring()
        +stop(String answer) boolean
        +hasChallenge() boolean
    }

    class Challenge {
        <<interface>>
        +getPrompt() String
        +solve(String answer) boolean
    }

    class MathChallenge {
        -int operand1
        -int operand2
        -int result
        -generateProblem()
    }

    class SoundProfile {
        -String trackName
        -int volume
        -boolean isProgressive
        +increaseVolume()
    }

    class Recurrence {
        -EnumSet~DayOfWeek~ days
        +isActiveToday(LocalDate date) boolean
    }

    class SnoozeConfig {
        -int intervalMinutes
        -int maxTries
        -int currentTries
        +snooze() boolean
        +resetTries()
    }

    %% Relaciones
    AlarmManager "1" *-- "many" Alarm : gestiona
    Alarm "1" *-- "1" SoundProfile : tiene
    Alarm "1" *-- "1" Recurrence : tiene
    Alarm "1" *-- "1" SnoozeConfig : tiene
    Alarm "1" o-- "0..1" Challenge : requiere (opcional)
    Challenge <|.. MathChallenge : implementa
```

Justificación: Se ha utilizado encapsulación (atributos privados con getters/setters) para proteger la integridad de los datos. La relación entre Alarm y Challenge es una agregación opcional, permitiendo alarmas con o sin reto.

### 8. Diagrama de Casos de Uso

usecaseDiagram
    actor Usuario
    Usuario --> (Crear Alarma)
    Usuario --> (Activar Modo Vacaciones)
    Usuario --> (Resolver Reto)
    (Resolver Reto) ..> (Detener Alarma) : <<include>>

### 9. Especificación de Casos de Uso 

Campo	Detalle
Nombre	UC-01: Detener Alarma con Reto
Objetivo	Asegurar que el usuario está despierto antes de silenciar el dispositivo.
Actor principal	Usuario
Precondiciones	La alarma debe estar sonando y tener un reto asignado.
Flujo principal	1. La alarma suena. 2. El sistema muestra el reto. 3. El usuario introduce la respuesta. 4. El sistema valida y apaga.
Flujos alternativos	3a. Respuesta incorrecta: El sistema mantiene el sonido y pide nueva respuesta.
Postcondiciones	La alarma se detiene y se marca como procesada.
Reglas de negocio	No se puede apagar la alarma mediante el método stop() si la respuesta es nula o incorrecta.

### 10. Reflexión Técnica

Decisiones de diseño: Se optó por EnumSet para la recurrencia por su alta eficiencia en memoria y facilidad para manejar días de la semana.
Problemas encontrados: La gestión de hilos en la simulación de tiempo del Main fue compleja de sincronizar con el AlarmManager.
Mejoras futuras: Implementar persistencia en base de datos SQLite y una interfaz gráfica (GUI) con Swing.
Patrones aplicados: Estrategia (para los retos) y Singleton (potencial para el Manager).

### 11. Reflexión sobre IA

Ayuda: Gemini facilitó la generación de código repetitivo (boilers) y la estructura inicial del diagrama Mermaid.
Fallos: La IA inicialmente sugirió una estructura de carpetas que no coincidía con el entorno de VS Code del usuario, lo que requirió una corrección manual de los package.
Aprendizaje: He aprendido a delegar la lógica de negocio en interfaces para hacer el código más extensible y a documentar cada paso del proceso Git.