package sample.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Position {
    protected StringProperty position;
    protected StringProperty department;
    private final StringProperty address;
    private final StringProperty phone;
    private final StringProperty message;

    public Position(String position, String department) {
        this(position, department, null, null, null);
    }

    public Position(String position, String department, String address, String phone, String message) {
        this.position = new SimpleStringProperty(position);
        this.department = new SimpleStringProperty(department);
        this.address = new SimpleStringProperty(address);
        this.phone = new SimpleStringProperty(phone);
        this.message = new SimpleStringProperty(message);
    }

    public Position(ResultSet rs) throws SQLException {
        this(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
    }

    public String getPosition() {
        return position.get();
    }

    public StringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public String getDepartment() {
        return department.get();
    }

    public StringProperty departmentProperty() {
        return department;
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getMessage() {
        return message.get();
    }

    public StringProperty messageProperty() {
        return message;
    }

    public void setMessage(String message) {
        this.message.set(message);
    }
}
