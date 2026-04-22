import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizManager {

    private final List<Student> students;
    private final Map<Integer, Quiz> quizzesById;

    public QuizManager() {
        this.students = new ArrayList<>();
        this.quizzesById = new HashMap<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addQuiz(Quiz quiz) {
        quizzesById.put(quiz.getQuizId(), quiz);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Student getStudentById(int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }

    public Quiz getQuizById(int quizId) {
        return quizzesById.get(quizId);
    }

    public List<Integer> getAllQuizIds() {
        List<Integer> quizIds = new ArrayList<>(quizzesById.keySet());
        Collections.sort(quizIds);
        return quizIds;
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
            System.out.println(quizzesById.get(quizId));
        }
    }

    public void displayLeaderboard(Quiz quiz) {
        List<QuizAttempt> attempts = quiz.getAttempts();

        if (attempts.isEmpty()) {
            System.out.println("===== LEADERBOARD: " + quiz.getTitle() + " =====");
            System.out.println("No attempts recorded for this quiz.");
        } else {
            LeaderboardService.displayQuizLeaderboard(quiz, attempts);
        }
    }

    public void displayStudentAttemptHistory(Student student) {
        System.out.println("\n===== ATTEMPT HISTORY FOR " + student.getName() + " =====");
        
        List<QuizAttempt> studentAttempts = getAllQuizIds().stream()
            .map(this::getQuizById)
            .flatMap(quiz -> quiz.getAttempts().stream())
            .filter(attempt -> attempt.getStudent().equals(student))
            .toList();
        
        if (studentAttempts.isEmpty()) {
            System.out.println("No quiz attempts found for " + student.getName());
            return;
        }
        
        studentAttempts.forEach(attempt -> 
            System.out.println("Quiz: " + attempt.getQuiz().getTitle() + 
                             " | Score: " + attempt.getScore() + "/" + 
                             attempt.getQuiz().getTotalQuestions())
        );
    }
}