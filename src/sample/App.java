package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controller.Login;
import sample.controller.Office;
import sample.model.Staff;

import java.io.IOException;

public class App extends Application {
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Login.fxml"));
            Parent root = loader.load();
            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("登录");
            Scene scene=new Scene(root);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
            Login login = loader.getController();
            login.setInfo(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showOffice(Staff staff) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Office.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("办公管理系统");
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
            Office office=loader.getController();
            office.setUsers(staff);
            primaryStage.getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
