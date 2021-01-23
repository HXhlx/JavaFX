package sample.model;

import javafx.beans.property.*;

import java.sql.*;

public class Vacation extends Staff {
    private final ObjectProperty<Time> StartTime;
    private final ObjectProperty<Time> EndTime;
    private final StringProperty type;

    public Vacation(int workID, Time startTime, Time endTime, String type) {
        super(workID);
        StartTime = new SimpleObjectProperty<>(startTime);
        EndTime = new SimpleObjectProperty<>(endTime);
        this.type = new SimpleStringProperty(type);
    }

    public Vacation(ResultSet rs) throws SQLException {
        this(rs.getInt(1), rs.getTime(2), rs.getTime(3), rs.getString(4));
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

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }
}
