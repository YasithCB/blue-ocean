package controller;

import db.Database;
import entity.Customer;
import entity.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.RoomTM;

import java.io.IOException;

public class AvailableRoomsController {
    public AnchorPane availableRoomsContext;
    double x,y;

    // Table of Available Rooms
    public TableView tblAvailableRooms;
        public TableColumn colAvailableRoomsId;
        public TableColumn colAvailableRoomsAC;
        public TableColumn colAvailableRoomsHumanCapacity;

    public void initialize(){
        // introduce the room data for the columns
        colAvailableRoomsId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAvailableRoomsAC.setCellValueFactory(new PropertyValueFactory<>("AC"));
        colAvailableRoomsHumanCapacity.setCellValueFactory(new PropertyValueFactory<>("humanCapacity"));

        // load alla available rooms to the table
        loadAllRooms();
    }

    private void loadAllRooms() {
        ObservableList<RoomTM> obList = FXCollections.observableArrayList();

        // load available rooms to the obList
        for (Room r : Database.rooms) {
            if (r.isAvailable() == true){
                obList.add(new RoomTM(r.getId(),r.isAC(),r.isAvailable(),r.getHumanCapacity(),r.getPrice(),new Customer()));
            }
        }
        tblAvailableRooms.setItems(obList);
    }

    public void btnMinimizeOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) availableRoomsContext.getScene().getWindow();
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
        Stage stage = (Stage) availableRoomsContext.getScene().getWindow();
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
}
