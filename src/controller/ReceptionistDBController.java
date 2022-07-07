package controller;

import db.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;

public class ReceptionistDBController {

    public AnchorPane ReceptionistDBContext;
    public Label lblUserName;
    double x,y;

    public void initialize(){
        lblUserName.setText(Database.currentUserName);
    }

    public void btnMinimizeOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) ReceptionistDBContext.getScene().getWindow();
        stage.setIconified(true);
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * When you're in Receptionist dashboard, if you want to switch as an admin user?
     * then you can press this and back to the login form
     * @param actionEvent -- The action which sent by button
     * @throws IOException -- exception trowing
     */
    public void btnSwitchToLoginOnAction(ActionEvent actionEvent) throws IOException {
        // load login form
        setUi("LoginForm");
    }

    /**
     * when press package details. there will open a new window of package details
     * @param actionEvent
     */
    public void btnPackageDetailsOnAction(ActionEvent actionEvent) throws IOException {
        setUi("PackageDetails");
    }

    public void setUi(String location) throws IOException {
        Stage stage = (Stage) ReceptionistDBContext.getScene().getWindow();
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

    public void btnMealPlansOnAction(ActionEvent actionEvent) throws IOException {
        setUi("MealPlans");
    }

    public void btnCustomerReservedRoomsOnAction(ActionEvent actionEvent) throws IOException {
        setUi("CustomerReservedRooms");
    }

    public void btnMakeAReservationOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent load = FXMLLoader.load(getClass().getResource("../view/MakeAReservation.fxml"));
        stage.setScene(new Scene(load));

        stage.initStyle(StageStyle.UNDECORATED);
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

    public void btnUserDetailsOnAction(ActionEvent actionEvent) throws IOException {
        setUi("UserDetails");
    }

    public void btnMaintenanceRoomsOnAction(ActionEvent actionEvent) throws IOException {
        setUi("MaintenanceRooms");
    }
}
