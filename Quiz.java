import java.util.*;

public class Quiz {

    private final int quizId;
    private String title;
    private String subject;
    private List<Question> questions;

    public Quiz(int quizId, String title, String subject) {
        this.quizId = quizId;
        this.title = title;
        this.subject = subject;
        this.questions = new ArrayList<>();
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    @Override
    public String toString() {
        return "Quiz{id=" + quizId +
                ", title='" + title + '\'' +
                ", subject='" + subject + '\'' +
                ", totalQuestions=" + questions.size() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Quiz))
            return false;
        Quiz quiz = (Quiz) o;
        return quizId == quiz.quizId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quizId);
    }
}