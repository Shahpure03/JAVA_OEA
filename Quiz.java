import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Quiz {
    private final int quizId;
    private final String title;
    private final String subject;
    private final List<Question> questions;
    private final List<QuizAttempt> attempts;

    public Quiz(int quizId, String title, String subject) {
        this.quizId = quizId;
        this.title = title;
        this.subject = subject;
        this.questions = new ArrayList<>();
        this.attempts = new ArrayList<>();
    }

    public int getQuizId() {
        return quizId;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return new ArrayList<>(questions);
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public void addAttempt(QuizAttempt attempt) {
        attempts.add(attempt);
    }

    public List<QuizAttempt> getAttempts() {
        return new ArrayList<>(attempts);
    }

    @Override
    public String toString() {
        return "Quiz ID: " + quizId + ", Title: " + title + ", Total Questions: " + getTotalQuestions();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quiz)) return false;
        Quiz quiz = (Quiz) o;
        return quizId == quiz.quizId && Objects.equals(title, quiz.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quizId, title);
    }
}