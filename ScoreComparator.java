import java.util.Comparator;

public class ScoreComparator implements Comparator<QuizAttempt> {

    @Override
    public int compare(QuizAttempt first, QuizAttempt second) {
        int byScore = Double.compare(second.getScore(), first.getScore());
        if (byScore != 0) {
            return byScore;
        }

        // Tie-breaker: sort by student name.
        return first.getStudent().getName()
                .compareToIgnoreCase(second.getStudent().getName());
    }
}
