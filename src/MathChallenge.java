import java.util.Random;

public class MathChallenge implements Challenge {
    private int operand1;
    private int operand2;
    private int result;

    public MathChallenge() {
        generateProblem();
    }

    private void generateProblem() {
        Random rand = new Random();
        this.operand1 = rand.nextInt(10) + 1; // Números del 1 al 10
        this.operand2 = rand.nextInt(10) + 1;
        this.result = operand1 + operand2;
    }

    @Override
    public String getPrompt() {
        return "¿Cuánto es " + operand1 + " + " + operand2 + "?";
    }

    @Override
    public boolean solve(String answer) {
        try {
            return Integer.parseInt(answer) == result;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}