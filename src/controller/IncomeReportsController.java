package controller;

import com.jfoenix.controls.JFXComboBox;
import db.Database;
import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.CustomerReservedRoomsTM;
import view.tm.IncomesTM;

import java.io.IOException;
import java.time.Year;
import java.util.Calendar;

public class IncomeReportsController {
    public AnchorPane IncomeReportsContext;
    double x,y;

    public JFXComboBox cmbMonth;
    public JFXComboBox cmbYear;

    public TableView tblIncomeReport;
        public TableColumn colCustomer;
        public TableColumn colRoomId;
        public TableColumn colDateReserved;
        public TableColumn colIncome;

    String selectedMonth = "";
    String selectedYear= "";

    public Label lblTotalIncome;
    double totalIncome;

    ObservableList obListMonthIncome = FXCollections.observableArrayList();
    ObservableList obListYearIncome = FXCollections.observableArrayList();
    ObservableList obListYearMonthIncome = FXCollections.observableArrayList();
    ObservableList obListAllIncome = FXCollections.observableArrayList();

    public void initialize(){

        // introduce columns
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colDateReserved.setCellValueFactory(new PropertyValueFactory<>("date"));
        colIncome.setCellValueFactory(new PropertyValueFactory<>("income"));

        // initialize cmb
        ObservableList<String> months = FXCollections.observableArrayList();
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
        cmbMonth.setItems(months);

        ObservableList<String> years = FXCollections.observableArrayList();
        years.add("2021");
        years.add("2022");
        years.add("2023");
        years.add("2024");
        years.add("2025");
        years.add("2026");
        years.add("2027");
        cmbYear.setItems(years);



        // add listener
        cmbMonth.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedMonth = (String) newValue;
        });
        cmbYear.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedYear = (String) newValue;
        });

        // load to the table
        loadDataToTable();
    }

    private void loadMonthIncome(String selectedMonth) {

        for (Customer c : Database.customers) {

            if (c.getDate().toString().contains("/01/")){
                System.out.println("runn");
                obListMonthIncome.add(new IncomesTM(c.getName(),c.getRoomId(),c.getDate(),c.getPrice()));
                //add to income
                totalIncome += c.getPrice();
            }
        }
    }

    private void loadYearMonthIncome(String selectedYear, String selectedMonth) {

        for (Customer c : Database.customers) {
            Calendar cal = c.getDate();
            if (cal.get(Calendar.YEAR) == Integer.parseInt(selectedYear)){
                if (cal.get(Calendar.MONTH) == Integer.parseInt(selectedMonth)){
                    obListYearMonthIncome.add(new IncomesTM(c.getName(),c.getRoomId(),c.getDate(),c.getPrice()));
                    //add to income
                    totalIncome += c.getPrice();
                }
            }
        }
    }

    private void loadYearIncome(String selectedYear) {

        for (Customer c : Database.customers) {
            Calendar cal = c.getDate();
            if (cal.get(Calendar.YEAR) == Integer.parseInt(selectedYear)){
                obListYearIncome.add(new IncomesTM(c.getName(),c.getRoomId(),c.getDate(),c.getPrice()));
                //add to income
                totalIncome += c.getPrice();
            }
        }
    }

    private void loadAllIncomes() {

        for (Customer c : Database.customers) {
            obListAllIncome.add(new IncomesTM(c.getName(),c.getRoomId(),c.getDate(),c.getPrice()));
            //add to income
            totalIncome += c.getPrice();
        }
    }

    public void btnMinimizeOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) IncomeReportsContext.getScene().getWindow();
        stage.setIconified(true);
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void btnBackOnAction(MouseEvent mouseEvent) throws IOException {
        if (Database.currentDB == 'R'){
            setUi("ReceptionistDB");
        }else {
            setUi("AdminDB");
        }
    }

    public void setUi(String location) throws IOException {
        Stage stage = (Stage) IncomeReportsContext.getScene().getWindow();
        Parent load = FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        stage.setScene(new Scene(load));
        // make draggable the window
        load.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        load.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

        stage.show();
        stage.centerOnScreen();
    }

    public void btnGetCustomizedReportOnAction(ActionEvent actionEvent) {
        loadDataToTable();
    }

    private void loadDataToTable() {

        if (!selectedYear.equals("") && !selectedMonth.equals("")){
            loadYearMonthIncome(selectedYear,selectedMonth);
            tblIncomeReport.setItems(obListYearMonthIncome);
        }else if (selectedYear.equals("") && selectedMonth.equals("")){
            loadAllIncomes();
            tblIncomeReport.setItems(obListAllIncome);
        }else if (!selectedYear.equals("")){
            loadYearIncome(selectedYear);
            tblIncomeReport.setItems(obListYearIncome);
        }else {
            loadMonthIncome(selectedMonth);
            tblIncomeReport.setItems(obListMonthIncome);
        }

        // update total income
        lblTotalIncome.setText(String.valueOf(totalIncome));
    }
}
