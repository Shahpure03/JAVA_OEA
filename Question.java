import java.util.*;

public final class Question {

    private final int id;
    private final String text;
    private final List<String> options;
    private final int correctOption;

    public Question(int id, String text, List<String> options, int correctOption) {
        this.id = id;
        this.text = text;
        this.options = new ArrayList<>(options); // defensive copy
        this.correctOption = correctOption;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return Collections.unmodifiableList(options);
    }

    public int getCorrectOption() {
        return correctOption;
    }

    @Override
    public String toString() {
        return "Question{id=" + id + ", text='" + text + "', options=" + options + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Question))
            return false;
        Question q = (Question) o;
        return id == q.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}