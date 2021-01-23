package sample.controller;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import sample.model.*;

import java.sql.*;
import java.text.*;
import java.util.Calendar;

public class Office {
    public TableColumn<Apply, String> State;
    @FXML
    private TableView<Apply> applyTable;
    @FXML
    private TableColumn<Apply, Integer> workIDa;
    @FXML
    private TableColumn<Apply, String> type;
    @FXML
    private TableColumn<Apply, String> title;
    @FXML
    private TableColumn<Apply, String> content;
    @FXML
    private TableColumn<Apply, Time> applyTime;
    @FXML
    private TableColumn<Apply, Time> startTime;
    @FXML
    private TableColumn<Apply, Time> endTime;
    @FXML
    private TextField Title;
    @FXML
    private TextField Type;
    @FXML
    private TextArea Content;
    @FXML
    private TextField StartTime;
    @FXML
    private TextField EndTime;
    @FXML
    private Button ClockButton;
    @FXML
    private Label clockLabel;
    @FXML
    private TableView<Clock> staffTable;
    @FXML
    private TableColumn<Clock, String> name;
    @FXML
    private TableColumn<Clock, Integer> workID;
    @FXML
    private TableColumn<Clock, String> department;
    @FXML
    private TableColumn<Clock, String> position;
    @FXML
    private TableColumn<Clock, Date> date;
    @FXML
    private TableColumn<Clock, Time> time;
    @FXML
    private TableColumn<Clock, String> statement;
    @FXML
    private final MySQL mysql = new MySQL();
    private Staff staff;
    ObservableList<Clock> clocks = FXCollections.observableArrayList();
    ObservableList<Apply> applies = FXCollections.observableArrayList();

    private Time String2Time(String str) {
        try {
            return new Time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setUsers(Staff staff) {
        try {
            ResultSet rs = mysql.Select("select * from clock where WorkID = ? and CDate = ?", staff.getWorkID(), new Date(System.currentTimeMillis()));
            if (rs.next()) {
                clockLabel.setText("今日已打卡");
                ClockButton.setDisable(true);
            } else {
                clockLabel.setText("今日你还未打卡");
                ClockButton.setDisable(false);
            }
            this.staff = staff;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void iniStaffTable() {
        name.setCellValueFactory(cellData -> cellData.getValue().snameProperty());
        workID.setCellValueFactory(cellData -> cellData.getValue().workIDProperty().asObject());
        department.setCellValueFactory(cellData -> cellData.getValue().departmentProperty());
        position.setCellValueFactory(cellData -> cellData.getValue().positionProperty());
        date.setCellValueFactory(cellData -> cellData.getValue().CDateProperty());
        time.setCellValueFactory(cellData -> cellData.getValue().CTimeProperty());
        statement.setCellValueFactory(cellData -> cellData.getValue().statementProperty());
        staffTable.setItems(clocks);
    }

    private void iniApplyTable() {
        applyTable.setEditable(true);
        workIDa.setCellValueFactory(cellData -> cellData.getValue().workIDProperty().asObject());
        type.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        title.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        applyTime.setCellValueFactory(cellData -> cellData.getValue().logTimeProperty());
        startTime.setCellValueFactory(cellData -> cellData.getValue().startTimeProperty());
        endTime.setCellValueFactory(cellData -> cellData.getValue().endTimeProperty());
        State.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
        State.setCellFactory(TextFieldTableCell.forTableColumn());
        applyTable.setItems(applies);
    }

    @FXML
    private void initialize() {
        try {
            ResultSet rs = mysql.Select("select sname, staff.workid, department, position, phone, passwd, Authority, cdate, ctime, statement from staff left join clock on staff.WorkID = clock.WorkID");
            while (rs.next()) clocks.add(new Clock(rs));
            iniStaffTable();
            rs = mysql.Select("select * from apply");
            while (rs.next()) applies.add(new Apply(rs));
            iniApplyTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void handleClock() {
        Date date = new Date(System.currentTimeMillis());
        Time time = new Time(System.currentTimeMillis());
        if (mysql.Operator("insert into clock values (?, ?, ?, '已签到')", staff.getWorkID(), date, time) > 0) {
            clockLabel.setText("今日已打卡");
            ClockButton.setDisable(true);
            clocks.add(new Clock(staff, date, time, "已签到"));
        }
    }

    @FXML
    private void handleOK() {
        Time time = new Time(System.currentTimeMillis());
        if (mysql.Operator("insert into apply (WorkID, Type, Title, Content, ATime, BeginTime, EndTime, State) values (?, ?, ?, ?, ?, ?, ?, ?)", staff.getWorkID(), Type.getText(), Title.getText(), Content.getText(), new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss").format(time), StartTime.getText(), EndTime.getText(), "未审批") > 0) {
            applies.add(new Apply(staff.getWorkID(), Type.getText(), Title.getText(), Content.getText(), time, String2Time(StartTime.getText()), String2Time(EndTime.getText()), "未审批"));
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("保存失败");
            alert.setHeaderText("插入数据库失败");
            alert.setContentText("检查是否有格式错误");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleCancel() {
        Type.setText("");
        Title.setText("");
        Content.setText("");
        StartTime.setText("");
        EndTime.setText("");
    }

    @FXML
    private void onStateSubmit(TableColumn.CellEditEvent<Apply, String> applyStringCellEditEvent) {
        Apply apply = applyStringCellEditEvent.getRowValue();
        apply.setState(applyStringCellEditEvent.getNewValue());
        mysql.Operator("update apply set State = ? where ANo = ?", apply.getState(), apply.getId());
    }
}
