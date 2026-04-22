import java.util.List;
import java.util.Scanner;

public class QuizService {

    public static QuizAttempt conductQuizForStudent(Quiz quiz, Student student, Scanner sc) {
        QuizAttempt attempt = new QuizAttempt(student, quiz);

        System.out.println("\n===== STUDENT DETAILS =====");
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Branch: " + student.getBranch());
        System.out.println("\nStarting Quiz for " + student.getName());

        for (Question question : quiz.getQuestions()) {
            System.out.println("\nQ" + question.getId() + ": " + question.getText());

            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            int choice = InputHelper.readIntInRange(
                    sc,
                    "Enter answer (1-" + options.size() + "): ",
                    1,
                    options.size()
            );

            attempt.submitAnswer(question, choice);
        }

        attempt.evaluateScore();

        System.out.println(
                student.getName() + "'s Score: " +
                attempt.getScore() + "/" + quiz.getTotalQuestions()
        );

        return attempt;
    }
}