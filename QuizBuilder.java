import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizBuilder {

    public static Quiz buildQuiz(Scanner sc) {
        int quizId = readQuizId(sc);
        System.out.println("===== CREATE QUIZ " + quizId + " =====");
        String title = readQuizTitle(sc);
        String subject = readQuizSubject(sc);
        Quiz quiz = new Quiz(quizId, title, subject);
        int questionCount = readQuestionCount(sc);
        addQuestionsToQuiz(quiz, questionCount, sc);
        return quiz;
    }

    private static int readQuizId(Scanner sc) {
        return InputHelper.readPositiveInt(sc, "Enter Quiz ID: ");
    }

    private static String readQuizTitle(Scanner sc) {
        return InputHelper.readNonEmptyLine(sc, "Enter Quiz Title: ");
    }

    private static String readQuizSubject(Scanner sc) {
        return InputHelper.readNonEmptyLine(sc, "Enter Subject: ");
    }

    private static int readQuestionCount(Scanner sc) {
        return InputHelper.readPositiveInt(sc, "Enter number of questions: ");
    }

    private static void addQuestionsToQuiz(Quiz quiz, int questionCount, Scanner sc) {
        for (int questionId = 1; questionId <= questionCount; questionId++) {
            Question question = buildQuestion(questionId, sc);
            quiz.addQuestion(question);
        }
    }

    private static Question buildQuestion(int questionId, Scanner sc) {
        System.out.println("\n--- Question " + questionId + " ---");
        String questionText = readQuestionText(sc);
        List<String> options = readOptions(sc, 4);
        int correctOption = readCorrectOption(sc, options.size());
        return new Question(questionId, questionText, options, correctOption);
    }

    private static String readQuestionText(Scanner sc) {
        return InputHelper.readNonEmptyLine(sc, "Enter question text: ");
    }

    private static List<String> readOptions(Scanner sc, int optionCount) {
        List<String> options = new ArrayList<>();
        for (int optionNumber = 1; optionNumber <= optionCount; optionNumber++) {
            options.add(InputHelper.readNonEmptyLine(sc, "Enter option " + optionNumber + ": "));
        }
        return options;
    }

    private static int readCorrectOption(Scanner sc, int optionCount) {
        return InputHelper.readIntInRange(sc, "Enter correct option (1-" + optionCount + "): ", 1, optionCount);
    }
}
