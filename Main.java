import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int TOTAL_QUIZZES = 3;

    public static void main(String[] args) {
        QuizManager manager = new QuizManager();

        System.out.println("===== QUIZ MANAGEMENT SYSTEM =====\n");

        try (Scanner sc = new Scanner(System.in)) {
            createStudents(manager, sc);
            displayStudents(manager);
            createQuizzes(manager, sc);
            displayQuizzes(manager);
            conductAllQuizzes(manager, sc);
        }
    }

    private static void createStudents(QuizManager manager, Scanner sc) {
        int studentCount = InputHelper.readPositiveInt(sc, "Enter number of students: ");

        for (int i = 1; i <= studentCount; i++) {
            System.out.println("\n===== CREATE STUDENT " + i + " =====");

            int id = InputHelper.readPositiveInt(sc, "Enter Student ID: ");
            String name = InputHelper.readNonEmptyLine(sc, "Enter Name: ");
            String branch = InputHelper.readNonEmptyLine(sc, "Enter Branch: ");

            manager.addStudent(new Student(id, name, branch));
        }
    }

    private static void displayStudents(QuizManager manager) {
        System.out.println("\nStudents Registered:");
        manager.displayAllStudents();
        System.out.println();
    }

    private static void createQuizzes(QuizManager manager, Scanner sc) {
        for (int quizId = 1; quizId <= TOTAL_QUIZZES; quizId++) {
            System.out.println();
            Quiz quiz = QuizBuilder.createQuiz(sc, quizId);
            manager.addQuiz(quiz);
        }
    }

    private static void displayQuizzes(QuizManager manager) {
        System.out.println("\nQuizzes Created:");
        manager.displayAllQuizzes();
    }

    private static void conductAllQuizzes(QuizManager manager, Scanner sc) {
        List<Student> students = manager.getAllStudents();
        for (int quizId : manager.getAllQuizIds()) {
            conductSingleQuiz(manager, quizId, students, sc);
        }
    }

    private static void conductSingleQuiz(QuizManager manager, int quizId, List<Student> students, Scanner sc) {
        Quiz quiz = manager.getQuizById(quizId);
        if (quiz == null) {
            return;
        }

        System.out.println("\n===== ATTEMPT QUIZ ID " + quizId + ": " + quiz.getTitle() + " =====");
        List<QuizAttempt> quizAttempts = QuizService.conductQuiz(quiz, students, sc);
        manager.recordAttempts(quizAttempts);

        System.out.println();
        manager.displayLeaderboard(quiz);
    }
}
