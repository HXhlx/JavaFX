package sample.model;

import javafx.beans.property.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class Clock extends Staff {
    private ObjectProperty<Date> CDate;
    private ObjectProperty<Time> CTime;
    private StringProperty statement;

    public Clock(ResultSet rs) throws SQLException {
        super(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
        setClock(rs.getDate(8), rs.getTime(9), rs.getString(10));
    }

    public Clock(Staff staff, Date date, Time time, String state) {
        super(staff);
        setClock(date, time, state);
    }

    public void setClock(Date date, Time time, String state) {
        CDate = new SimpleObjectProperty<>(date);
        CTime = new SimpleObjectProperty<>(time);
        statement = new SimpleStringProperty(state);
    }

    public void setClock(ResultSet rs) {
        try {
            setClock(rs.getDate(2), rs.getTime(3), rs.getString(4));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Date getCDate() {
        return CDate.get();
    }

    public ObjectProperty<Date> CDateProperty() {
        return CDate;
    }

    public void setCDate(Date CDate) {
        this.CDate.set(CDate);
    }

    public Time getCTime() {
        return CTime.get();
    }

    public ObjectProperty<Time> CTimeProperty() {
        return CTime;
    }

    public void setCTime(Time CTime) {
        this.CTime.set(CTime);
    }

    public String getStatement() {
        return statement.get();
    }

    public StringProperty statementProperty() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement.set(statement);
    }
}
