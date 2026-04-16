import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class QuizAttempt {

    private final Student student;
    private final Quiz quiz;
    private final Map<Question, Integer> answers; // Question -> chosen option
    private double score;

    public QuizAttempt(Student student, Quiz quiz) {
        this.student = student;
        this.quiz = quiz;
        this.answers = new HashMap<>();
        this.score = 0.0;
    }

    public void submitAnswer(Question question, int chosenOption) {
        answers.put(question, chosenOption);
    }

    public void evaluateScore() {
        int correct = 0;

        for (Map.Entry<Question, Integer> entry : answers.entrySet()) {
            if (entry.getKey().getCorrectOption() == entry.getValue()) {
                correct++;
            }
        }

        this.score = correct;
    }

    public double getScore() {
        return score;
    }

    public Student getStudent() {
        return student;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    @Override
    public String toString() {
        return "QuizAttempt{student=" + student.getName() +
                ", quiz=" + quiz.getTitle() +
                ", score=" + score + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuizAttempt)) return false;
        QuizAttempt qa = (QuizAttempt) o;
        return student.equals(qa.student) && quiz.equals(qa.quiz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, quiz);
    }
}
