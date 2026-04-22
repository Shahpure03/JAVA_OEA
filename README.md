# Quiz Management System (Java)

## Overview
Quiz Management System is a console-based Java application to manage students, create MCQ quizzes, conduct quiz attempts, and view results.

It is menu-driven and keeps data in memory while the program is running.

## Features with Explanation
1. Student Registration
- Creates a student using:
- `studentId` (positive integer)
- `name` (non-empty)
- `branch` (non-empty)
- Registered students are stored and can be used for quiz attempts and history lookup.

2. Quiz Creation
- Creates a quiz using:
- `quizId` (positive integer)
- `title` (non-empty)
- `subject` (non-empty)
- number of questions (positive integer)
- Each quiz contains multiple questions, and each question has 4 options with one correct option.

3. MCQ Question Builder
- For each question, the system asks for:
- question text
- 4 options
- correct option index (1-4)
- Questions are added to the quiz in sequence (`Q1`, `Q2`, ...).

4. Attempt Quiz (Student ID + Quiz ID Flow)
- Takes only:
- `studentId`
- `quizId`
- Validates that both student and quiz exist.
- If valid, displays student details and starts the quiz.
- Student answers each question by selecting option number.

5. Automatic Evaluation
- After submission, answers are compared with correct options.
- Score is calculated as number of correct answers.
- Result is shown as `score/totalQuestions`.

6. Attempt Recording
- Every quiz attempt is saved in that quiz's attempt list.
- Stored attempt links:
- student
- quiz
- submitted answers
- final score

7. Quiz-wise Leaderboard
- Takes `quizId` and shows leaderboard only for that quiz.
- Attempts are sorted by score (highest first).
- If scores are tied, names are used as tie-breaker (alphabetical).

8. Multi-Topper Support
- Topper section prints all students who share the highest score.
- This handles tied first rank correctly.

9. Student-wise Quiz History
- Takes only `studentId`.
- Shows all attempts made by that student across all quizzes.
- Each entry prints quiz title and score.

10. Input Validation
- Centralized input helper methods ensure:
- positive integers where required
- range checks (for menu and option selection)
- non-empty text inputs
- Invalid inputs are rejected with clear retry prompts.

11. Stream API Usage
- Student attempt history is collected using Stream API:
- iterate quizzes
- flatten attempts
- filter by selected student

## Menu Options
1. Create Student
2. Create Quiz
3. Attempt a Quiz
4. View Leaderboard
5. View Quiz History
6. Exit

## Project Structure
- `Main.java` - Program entry point and menu flow.
- `QuizBuilder.java` - Interactive quiz and question creation.
- `QuizManager.java` - Stores students/quizzes and displays results/history.
- `QuizService.java` - Conducts quiz attempts and evaluates score.
- `LeaderboardService.java` - Ranks attempts and prints toppers.
- `ScoreComparator.java` - Score-first comparator for ranking.
- `Quiz.java` - Quiz model (questions + attempts).
- `Question.java` - Question model (text, options, correct option).
- `Student.java` - Student model.
- `QuizAttempt.java` - Attempt model (answers + score).
- `InputHelper.java` - Safe input and validation utilities.

## How to Run
1. Compile:
```bash
javac *.java
```

2. Run:
```bash
java Main
```
