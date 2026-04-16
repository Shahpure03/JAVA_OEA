import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizService {

    public static List<QuizAttempt> conductQuiz(Quiz quiz, List<Student> students, Scanner sc) {
        List<QuizAttempt> attempts = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            QuizAttempt attempt = new QuizAttempt(student, quiz);

            printStudentHeader(student, i + 1);
            askAllQuestions(quiz, attempt, sc);
            finalizeAndPrintScore(attempt);

            attempts.add(attempt);
        }

        return attempts;
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
            displayQuestion(question);
            int optionCount = question.getOptions().size();
            int choice = InputHelper.readIntInRange(sc, "Enter answer (1-" + optionCount + "): ", 1, optionCount);
            attempt.submitAnswer(question, choice);
        }
    }

    private static void displayQuestion(Question question) {
        System.out.println("\nQ" + question.getId() + ": " + question.getText());
        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    private static void finalizeAndPrintScore(QuizAttempt attempt) {
        attempt.evaluateScore();
        System.out.println(attempt.getStudent().getName() + "'s Score: " +
                attempt.getScore() + "/" + attempt.getQuiz().getTotalQuestions());
    }
}
