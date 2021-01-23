package sample.model;

import javafx.beans.property.*;

import java.sql.*;

public class Staff extends Position {
    private final StringProperty Sname;
    protected IntegerProperty WorkID;
    private final StringProperty phone;
    private final StringProperty passwd;
    private final StringProperty autority;

    public Staff(Staff staff) {
        super(staff.getPosition(), staff.getDepartment());
        Sname = staff.Sname;
        WorkID = staff.WorkID;
        phone = staff.phone;
        passwd = staff.passwd;
        autority = staff.autority;
    }

    public Staff(int workID) {
        this(null, workID, null, null, null, null, null);
    }

    public Staff(String sname, Integer workID, String department, String position, String phone, String passwd, String autor) {
        super(position, department);
        Sname = new SimpleStringProperty(sname);
        WorkID = new SimpleIntegerProperty(workID);
        this.department = new SimpleStringProperty(department);
        this.position = new SimpleStringProperty(position);
        this.phone = new SimpleStringProperty(phone);
        this.passwd = new SimpleStringProperty(passwd);
        autority = new SimpleStringProperty(autor);
    }

    public Staff(ResultSet rs) throws SQLException {
        this(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
    }

    public String getSname() {
        return Sname.get();
    }

    public StringProperty snameProperty() {
        return Sname;
    }

    public void setSname(String sname) {
        this.Sname.set(sname);
    }

    public int getWorkID() {
        return WorkID.get();
    }

    public IntegerProperty workIDProperty() {
        return WorkID;
    }

    public void setWorkID(int workID) {
        this.WorkID.set(workID);
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

    public String getPasswd() {
        return passwd.get();
    }

    public StringProperty passwdProperty() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd.set(passwd);
    }

    public String getAutority() {
        return autority.get();
    }

    public StringProperty autorityProperty() {
        return autority;
    }

    public void setAutority(String autority) {
        this.autority.set(autority);
    }
}
