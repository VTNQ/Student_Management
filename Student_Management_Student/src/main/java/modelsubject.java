import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class modelsubject {
    private final StringProperty subject;

    public modelsubject(String subject) {
        this.subject = new SimpleStringProperty(subject);
    }

    public String getSubject() {
        return subject.get();
    }

    public StringProperty subjectProperty() {
        return subject;
    }
}