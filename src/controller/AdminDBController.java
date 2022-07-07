package controller;

import db.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDBController {

    public AnchorPane adminDBContext;
    public Label lblUserName;
    private double x,y ;

    public void initialize(){
        lblUserName.setText(Database.currentUserName);
    }

    public void btnSwitchToLoginOnAction(ActionEvent actionEvent) throws IOException {
        // load login form
        setUi("LoginForm");
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void btnMinimizeOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) adminDBContext.getScene().getWindow();
        stage.setIconified(true);
    }

    public void setUi(String location) throws IOException {
        Stage stage = (Stage) adminDBContext.getScene().getWindow();
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

    public void btnAvailableRoomsOnAction(ActionEvent actionEvent) throws IOException {
        setUi("AvailableRooms");
    }

    public void btnPackageDetailsOnAction(ActionEvent actionEvent) throws IOException {
        setUi("PackageDetails");
    }

    public void btnReservedRoomsOnAction(ActionEvent actionEvent) throws IOException {
        setUi("CustomerReservedRooms");
    }

    public void btnMealPlansOnAction(ActionEvent actionEvent) throws IOException {
        setUi("MealPlans");
    }

    public void btnAddRoomsOnAction(ActionEvent actionEvent) throws IOException {
        setUi("AddRooms");
    }

    public void btnAddMealPlans(ActionEvent actionEvent) throws IOException {
        setUi("AddMealPlans");
    }

    public void btnIncomeReportsOnAction(ActionEvent actionEvent) throws IOException {
        setUi("IncomeReports");
    }

    public void btnEditMealPlansAndRoomsOnAction(ActionEvent actionEvent) throws IOException {
        setUi("EditMealPlansAndRooms");
    }

    public void btnEditPackagesOnAction(ActionEvent actionEvent) throws IOException {
        setUi("EditPackages");
    }

    public void btnUserDetailsOnAction(ActionEvent actionEvent) throws IOException {
        setUi("UserDetails");
    }

    public void btnMaintenanceRoomsOnAction(ActionEvent actionEvent) throws IOException {
        setUi("MaintenanceRooms");
    }
}
