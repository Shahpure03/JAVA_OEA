import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizService {

    public static List<QuizAttempt> conductQuiz(Quiz quiz, List<Student> students, Scanner sc) {
        List<QuizAttempt> attempts = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            QuizAttempt attempt = conductAttemptForStudent(quiz, students.get(i), i + 1, sc);
            attempts.add(attempt);
        }

        return attempts;
    }

    private static QuizAttempt conductAttemptForStudent(Quiz quiz, Student student, int studentNumber, Scanner sc) {
        QuizAttempt attempt = new QuizAttempt(student, quiz);

        printStudentHeader(student, studentNumber);
        askAllQuestions(quiz, attempt, sc);
        finalizeAndPrintScore(attempt);

        return attempt;
    }

    private static void printStudentHeader(Student student, int studentNumber) {
        System.out.println("\n===== STUDENT " + studentNumber + " =====");
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Branch: " + student.getBranch());
        System.out.println("\nStarting Quiz for " + student.getName());
    }

    private static void askAllQuestions(Quiz quiz, QuizAttempt attempt, Scanner sc) {
        for (Question question : quiz.getQuestions()) {
            askSingleQuestion(question, attempt, sc);
        }
    }

    private static void askSingleQuestion(Question question, QuizAttempt attempt, Scanner sc) {
        displayQuestion(question);
        int choice = readValidChoice(sc, question.getOptions().size());
        attempt.submitAnswer(question, choice);
    }

    private static void displayQuestion(Question question) {
        System.out.println("\nQ" + question.getId() + ": " + question.getText());
        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    private static int readValidChoice(Scanner sc, int optionCount) {
        return InputHelper.readIntInRange(sc, "Enter answer (1-" + optionCount + "): ", 1, optionCount);
    }

    private static void finalizeAndPrintScore(QuizAttempt attempt) {
        attempt.evaluateScore();
        System.out.println(attempt.getStudent().getName() + "'s Score: " +
                attempt.getScore() + "/" + attempt.getQuiz().getTotalQuestions());
    }
}
