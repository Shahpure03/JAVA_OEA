import java.util.Scanner;

public class Main {
    private static QuizManager quizManager;
    private static Scanner sc;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            sc = scanner;
            quizManager = new QuizManager();

            System.out.println("===== QUIZ MANAGEMENT SYSTEM =====");

            boolean running = true;
            while (running) {
                printMenu();
                int choice = InputHelper.readIntInRange(sc, "Enter your choice (1-6): ", 1, 6);

                switch (choice) {
                    case 1 -> createStudent();
                    case 2 -> createQuiz();
                    case 3 -> attemptQuiz();
                    case 4 -> viewLeaderboard();
                    case 5 -> viewQuizHistory();
                    case 6 -> {
                        System.out.println("\nThank you for using Quiz Management System. Goodbye!");
                        running = false;
                    }
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║        QUIZ MANAGEMENT DASHBOARD     ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ 1. Create Student                    ║");
        System.out.println("║ 2. Create Quiz                       ║");
        System.out.println("║ 3. Attempt a Quiz                    ║");
        System.out.println("║ 4. View Leaderboard                  ║");
        System.out.println("║ 5. View Quiz History                 ║");
        System.out.println("║ 6. Exit                              ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    private static void createStudent() {
        System.out.println("\n===== CREATE STUDENT =====");

        int id = InputHelper.readPositiveInt(sc, "Enter student ID: ");
        String name = InputHelper.readNonEmptyLine(sc, "Enter student name: ");
        String branch = InputHelper.readNonEmptyLine(sc, "Enter student branch: ");

        Student student = new Student(id, name, branch);
        quizManager.addStudent(student);

        System.out.println("Student created successfully.");
    }

    private static void createQuiz() {
        System.out.println("\n===== CREATE QUIZ =====");

        Quiz quiz = QuizBuilder.buildQuiz(sc);
        quizManager.addQuiz(quiz);

        System.out.println("Quiz created successfully.");
    }

    private static void attemptQuiz() {
        System.out.println("\n===== ATTEMPT A QUIZ =====");

        if (quizManager.getAllStudents().isEmpty()) {
            System.out.println("No students available. Please create students first.");
            return;
        }

        if (quizManager.getAllQuizIds().isEmpty()) {
            System.out.println("No quizzes available. Please create quizzes first.");
            return;
        }

        int studentId = InputHelper.readPositiveInt(sc, "Enter student ID: ");
        Student student = quizManager.getStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        int quizId = InputHelper.readPositiveInt(sc, "Enter quiz ID: ");
        Quiz quiz = quizManager.getQuizById(quizId);

        if (quiz == null) {
            System.out.println("Quiz not found.");
            return;
        }

        QuizAttempt attempt = QuizService.conductQuizForStudent(quiz, student, sc);
        quiz.addAttempt(attempt);

        System.out.println("Quiz attempt recorded successfully.");
    }

    private static void viewLeaderboard() {
        System.out.println("\n===== VIEW LEADERBOARD =====");

        if (quizManager.getAllQuizIds().isEmpty()) {
            System.out.println("No quizzes available.");
            return;
        }

        int quizId = InputHelper.readPositiveInt(sc, "Enter quiz ID: ");
        Quiz quiz = quizManager.getQuizById(quizId);

        if (quiz == null) {
            System.out.println("Quiz not found.");
            return;
        }

        quizManager.displayLeaderboard(quiz);
    }

    private static void viewQuizHistory() {
        System.out.println("\n===== VIEW QUIZ HISTORY =====");

        if (quizManager.getAllStudents().isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        int studentId = InputHelper.readPositiveInt(sc, "Enter student ID: ");
        Student student = quizManager.getStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        quizManager.displayStudentAttemptHistory(student);
    }
}
