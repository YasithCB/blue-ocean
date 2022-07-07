package controller;

import com.jfoenix.controls.JFXTextField;
import db.Database;
import entity.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.MaintenanceRoomsTM;

import java.io.IOException;

public class MaintenanceRoomsController {
    public AnchorPane MaintenanceRoomsContext;
    double x,y;
    public TableView tblMaintenanceRooms;
        public TableColumn colRoomId;
        public TableColumn colAc;
        public TableColumn colCustomerName;
        public TableColumn colReservedDate;
        public TableColumn colRoomFree;

    public JFXTextField txtRoomId;


    public void initialize(){
        loadMaintenanceRooms();
    }

    private void loadMaintenanceRooms() {
        // introduce columns
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colAc.setCellValueFactory(new PropertyValueFactory<>("ac"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colReservedDate.setCellValueFactory(new PropertyValueFactory<>("reservedDate"));
        colRoomFree.setCellValueFactory(new PropertyValueFactory<>("roomFreeDate"));

        // add data to table
        ObservableList<MaintenanceRoomsTM> observableList = FXCollections.observableArrayList();
        for (MaintenanceRoomsTM tm : Database.maintenanceRooms) {
            observableList.add(tm);
        }
        tblMaintenanceRooms.setItems(observableList);

        // add listener
        tblMaintenanceRooms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData((MaintenanceRoomsTM) newValue);
        });
    }

    private void setData(MaintenanceRoomsTM tm) {
        txtRoomId.setText(tm.getRoomId());
    }

    public void btnMinimizeOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) MaintenanceRoomsContext.getScene().getWindow();
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
        Stage stage = (Stage) MaintenanceRoomsContext.getScene().getWindow();
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

    public void btnMakeAvailableOnAction(ActionEvent actionEvent) throws IOException {
        // mark as available
        int index = -1;
        for (Room r : Database.rooms) {
            index++;
            if (r.getId().equals(txtRoomId.getText())){
                Database.rooms.get(index).setAvailable(true);
                // alert
                new Alert(Alert.AlertType.INFORMATION,"Room updated as Available!").show();
            }
        }

        // remove from maintenance
        int i = -1;
        if (Database.maintenanceRooms.size() == 1){
            Database.maintenanceRooms.remove(0);
        }else {
            for (MaintenanceRoomsTM tm : Database.maintenanceRooms) {
                i++;
                if (tm.getRoomId().equals(txtRoomId.getText())){
                    Database.maintenanceRooms.remove(i);
                }
            }
        }

        // refresh
        setUi("MaintenanceRooms");
    }
}
