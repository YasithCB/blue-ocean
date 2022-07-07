package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.org.apache.xpath.internal.operations.Bool;
import db.Database;
import entity.Customer;
import entity.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.RoomTM;

import java.io.IOException;

public class EditRoomsController {
    public AnchorPane editRoomsContext;
    double x,y;

    public TableView tblAvailableRooms;
        public TableColumn colAvailableRoomsId;
        public TableColumn colAvailableRoomsAC;
        public TableColumn colAvailableRoomsHumanCapacity;

    public JFXTextField txtRoomId;
    public JFXTextField txtHumanCapacity;
    public JFXTextField txtPrice;
    public JFXTextField txtAc;
    public JFXTextField txtAvailability;

    ObservableList<RoomTM> obList = FXCollections.observableArrayList();
    int roomIndex;

    public void initialize(){
        // introduce the room data for the columns
        colAvailableRoomsId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAvailableRoomsAC.setCellValueFactory(new PropertyValueFactory<>("AC"));
        colAvailableRoomsHumanCapacity.setCellValueFactory(new PropertyValueFactory<>("humanCapacity"));

        // load alla available rooms to the table
        loadAllRooms();

        // add listener
        tblAvailableRooms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData((RoomTM) newValue);
        });
    }

    private void setData(RoomTM tm) {
        txtRoomId.setText(tm.getId());
        txtHumanCapacity.setText(String.valueOf(tm.getHumanCapacity()));
        txtPrice.setText(String.valueOf(tm.getPrice()));
        // get data from cmb
        txtAc.setText("Yes");
        if (!tm.isAC()){
            txtAc.setText("No");
        }
        txtAvailability.setText("Yes");
        if (!tm.isAvailable()){
            txtAvailability.setText("No");
        }
    }

    private void loadAllRooms() {

        // load available rooms to the obList
        for (Room r : Database.rooms) {
                obList.add(new RoomTM(r.getId(),r.isAC(),r.isAvailable(),r.getHumanCapacity(),r.getPrice(),new Customer()));
        }
        tblAvailableRooms.setItems(obList);
    }

    public void btnMinimizeOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) editRoomsContext.getScene().getWindow();
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
        Stage stage = (Stage) editRoomsContext.getScene().getWindow();
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

    public void tbnSaveOnAction(ActionEvent actionEvent) throws IOException {
        // get ac and availability status
        boolean acStatus = txtAc.getText().equals("Yes")? true:false;
        boolean availability = txtAvailability.getText().equals("Yes")? true:false;
        // make new room
        Room room = new Room(txtRoomId.getText(),acStatus,availability,Byte.parseByte(txtHumanCapacity.getText()),Double.parseDouble(txtPrice.getText()),new Customer());

        // find index
        for (int i = 0; i < obList.size(); i++) {
            RoomTM tm = obList.get(i);
            System.out.println(i);
            if (tm.getId().equals(txtRoomId.getText())){
                roomIndex = i;
                break;
            }
        }
        System.out.println(roomIndex);

        // update
        Database.rooms.set(roomIndex,room);
        // refresh
        setUi("EditRooms");
        // alert confirmation
        new Alert(Alert.AlertType.CONFIRMATION,"Room Updated").show();
    }
}
