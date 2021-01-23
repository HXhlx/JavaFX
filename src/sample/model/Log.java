package sample.model;

import javafx.beans.property.*;

import java.sql.*;

public class Log extends Staff {
    private final IntegerProperty id;
    private final StringProperty title;
    private final StringProperty content;
    private final ObjectProperty<Time> logTime;

    public Log(int workID, String title, String content, Time logTime) {
        super(workID);
        this.id = null;
        this.title = new SimpleStringProperty(title);
        this.content = new SimpleStringProperty(content);
        this.logTime = new SimpleObjectProperty<>(logTime);
    }

    public Log(int id, int workID, String title, String content, Time logTime) {
        super(workID);
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.content = new SimpleStringProperty(content);
        this.logTime = new SimpleObjectProperty<>(logTime);
    }

    public Log(ResultSet rs) throws SQLException {
        this(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getTime(5));
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getContent() {
        return content.get();
    }

    public StringProperty contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public Time getLogTime() {
        return logTime.get();
    }

    public ObjectProperty<Time> logTimeProperty() {
        return logTime;
    }

    public void setLogTime(Time logTime) {
        this.logTime.set(logTime);
    }
}
