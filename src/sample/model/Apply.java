package sample.model;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class Apply extends Log {
    private final StringProperty type;
    private final ObjectProperty<Time> StartTime;
    private final ObjectProperty<Time> EndTime;
    private final StringProperty State;

    public Apply(int workID, String type, String title, String content, Time applyTime, Time startTime, Time endTime, String state) {
        super(workID, title, content, applyTime);
        this.type = new SimpleStringProperty(type);
        StartTime = new SimpleObjectProperty<>(startTime);
        EndTime = new SimpleObjectProperty<>(endTime);
        State = new SimpleStringProperty(state);
    }

    public Apply(int no, int workID, String type, String title, String content, Time applyTime, Time startTime, Time endTime, String state) {
        super(no, workID, title, content, applyTime);
        this.type = new SimpleStringProperty(type);
        StartTime = new SimpleObjectProperty<>(startTime);
        EndTime = new SimpleObjectProperty<>(endTime);
        State = new SimpleStringProperty(state);
    }

    public Apply(ResultSet rs) throws SQLException {
        this(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getTime(6), rs.getTime(7), rs.getTime(8), rs.getString(9));
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public Time getStartTime() {
        return StartTime.get();
    }

    public ObjectProperty<Time> startTimeProperty() {
        return StartTime;
    }

    public void setStartTime(Time startTime) {
        this.StartTime.set(startTime);
    }

    public Time getEndTime() {
        return EndTime.get();
    }

    public ObjectProperty<Time> endTimeProperty() {
        return EndTime;
    }

    public void setEndTime(Time endTime) {
        this.EndTime.set(endTime);
    }

    public String getState() {
        return State.get();
    }

    public StringProperty stateProperty() {
        return State;
    }

    public void setState(String state) {
        this.State.set(state);
    }
}
