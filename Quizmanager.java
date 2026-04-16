import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizManager {

    private final List<Student> students;
    private final Map<Integer, Quiz> quizzesById;
    private final List<QuizAttempt> attempts;

    public QuizManager() {
        this.students = new ArrayList<>();
        this.quizzesById = new HashMap<>();
        this.attempts = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addQuiz(Quiz quiz) {
        quizzesById.put(quiz.getQuizId(), quiz);
    }

    public void recordAttempts(List<QuizAttempt> newAttempts) {
        attempts.addAll(newAttempts);
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students registered.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void displayAllQuizzes() {
        if (quizzesById.isEmpty()) {
            System.out.println("No quizzes available.");
            return;
        }

        for (int quizId : getAllQuizIds()) {
            Quiz quiz = quizzesById.get(quizId);
            System.out.println(quiz);
        }
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Quiz getQuizById(int quizId) {
        return quizzesById.get(quizId);
    }

    public List<Integer> getAllQuizIds() {
        List<Integer> quizIds = new ArrayList<>(quizzesById.keySet());
        Collections.sort(quizIds);
        return quizIds;
    }

    public void displayLeaderboard(Quiz quiz) {
        List<QuizAttempt> quizAttempts = getAttemptsForQuiz(quiz);
        if (quizAttempts.isEmpty()) {
            System.out.println("===== LEADERBOARD: " + quiz.getTitle() + " =====");
            System.out.println("No attempts recorded for this quiz.");
        } else {
            LeaderboardService.displayQuizLeaderboard(quiz, quizAttempts);
        }
    }

    private List<QuizAttempt> getAttemptsForQuiz(Quiz quiz) {
        List<QuizAttempt> quizAttempts = new ArrayList<>();
        for (QuizAttempt attempt : attempts) {
            if (attempt.getQuiz().equals(quiz)) {
                quizAttempts.add(attempt);
            }
        }
        return quizAttempts;
    }
}
