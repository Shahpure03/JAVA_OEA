import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeaderboardService {

    public static void displayQuizLeaderboard(Quiz quiz, List<QuizAttempt> attempts) {
        List<QuizAttempt> sortedAttempts = new ArrayList<>(attempts);
        Collections.sort(sortedAttempts, new ScoreComparator());

        System.out.println("\n===== LEADERBOARD: " + quiz.getTitle() + " =====");
        printRanks(sortedAttempts);
        printTopper(sortedAttempts);
    }

    private static void printRanks(List<QuizAttempt> sortedAttempts) {
        int rank = 1;
        for (QuizAttempt attempt : sortedAttempts) {
            System.out.println(rank + ". " + attempt.getStudent().getName() + " Score: " + attempt.getScore());
            rank++;
        }
    }

    private static void printTopper(List<QuizAttempt> sortedAttempts) {
        System.out.println("\n===== TOPPER =====");
        if (sortedAttempts.isEmpty()) {
            System.out.println("No toppers available.");
            return;
        }

        double topScore = sortedAttempts.get(0).getScore();
        sortedAttempts.stream()
                .filter(attempt -> Double.compare(attempt.getScore(), topScore) == 0)
                .forEach(attempt -> System.out.println("Topper: " + attempt.getStudent().getName()));
    }
}
