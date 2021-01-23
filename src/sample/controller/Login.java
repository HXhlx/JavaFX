package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.App;
import sample.model.Staff;

import java.sql.*;
import java.util.ArrayList;

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
        try {
            ResultSet rs = mysql.Select("select * from staff where phone = ? and passwd = ?", phone.getText(), password.getText());
            if (rs.next()) {
                Staff currentStaff = new Staff(rs);
                app.showOffice(currentStaff);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("登录失败");
                alert.setHeaderText("登录失败");
                alert.setContentText("号码或密码错误,请重新输入");
                alert.showAndWait();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
