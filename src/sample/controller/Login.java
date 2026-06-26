package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.App;
import sample.model.Staff;

import java.sql.*;

public class Login {
    @FXML
    private TextField phone;
    @FXML
    private TextField password;
    private final MySQL mysql = new MySQL();
    private App app;

    public void setInfo(App app) {
        this.app = app;
    }

    public void handleLogin() {
        try (ResultSet rs = mysql.Select("select * from staff where phone = ? and passwd = ?", phone.getText(), password.getText())) {
            if (rs.next()) {
                Staff currentStaff = new Staff(
                    rs.getString("Sname"),
                    rs.getInt("WorkID"),
                    rs.getString("Department"),
                    rs.getString("Position"),
                    rs.getString("Phone"),
                    rs.getString("Passwd"),
                    rs.getString("Authority")
                );
                mysql.close();
                app.showOffice(currentStaff);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("登录失败");
                alert.setHeaderText("登录失败");
                alert.setContentText("号码或密码错误,请重新输入");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("数据库错误");
            alert.setHeaderText("查询失败");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
