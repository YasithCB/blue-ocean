package controller;

import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.CustomerReservedRoomsTM;
import view.tm.MaintenanceRoomsTM;

import java.io.IOException;
import java.util.Calendar;
import java.util.Optional;

public class CustomerReservedRoomsController {

    public AnchorPane customerReservedRoomsContext;
    double x,y;

    public TableView tblCustomerReservedRooms;
        public TableColumn colRoomId;
        public TableColumn colAc;
        public TableColumn colCustomerName;
        public TableColumn colNoOfDays;
        public TableColumn colNoOfPersons;
        public TableColumn colReservedDate;
        public TableColumn colRoomFree;

    ObservableList obListCRRooms = FXCollections.observableArrayList();

    public JFXTextField txtRoomId;

    public void initialize(){
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colAc.setCellValueFactory(new PropertyValueFactory<>("AC"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNoOfDays.setCellValueFactory(new PropertyValueFactory<>("noOfDays"));
        colNoOfPersons.setCellValueFactory(new PropertyValueFactory<>("noOfPersons"));
        colReservedDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colRoomFree.setCellValueFactory(new PropertyValueFactory<>("roomFreeDate"));


        // load to table
        loadAllReservedRooms();

        // add listener
        tblCustomerReservedRooms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData((CustomerReservedRoomsTM) newValue);
        });
    }

    private void setData(CustomerReservedRoomsTM tm) {
        txtRoomId.setText(tm.getRoomId());
    }

    private void loadAllReservedRooms() {

        for (Room r : Database.rooms) {
            if (!r.isAvailable()){
                Customer c = r.getCustomer();

                CustomerReservedRoomsTM customerReservedRoomsTM = new CustomerReservedRoomsTM(r.getId(),r.isAC(),c.getName(),
                        c.getNoOfDays(),c.getNoOfPersons(),c.getRoomFreeDate(),c.getRoomFreeDate(),c.getPrice());
                obListCRRooms.add(customerReservedRoomsTM);
            }
        }
        // add to table
        tblCustomerReservedRooms.setItems(obListCRRooms);
    }

    public void btnCancelReservationOnAction(ActionEvent actionEvent) throws IOException {
        for (int i = 0; i < obListCRRooms.size(); i++) {
            CustomerReservedRoomsTM crr = (CustomerReservedRoomsTM) obListCRRooms.get(i);
            if (txtRoomId.getText().equals(crr.getRoomId())){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure to cancel this Reservation?", ButtonType.YES,ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get().equals(ButtonType.YES)){
                    int j = -1;
                    for (Customer c : Database.customers) {
                        j++;
                        if (c.getName().equals(crr.getCname())){
                            Database.customers.remove(j);
                        }
                    }
                }
            }
        }

        // make related room available
        int i = -1;
        for (Room r : Database.rooms) {
            i++;
            if (r.getId().equals(txtRoomId.getText())){
                Database.rooms.get(i).setAvailable(true);
            }
        }

        setUi("CustomerReservedRooms");
    }

    public void btnMinimizeOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) customerReservedRoomsContext.getScene().getWindow();
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
        Stage stage = (Stage) customerReservedRoomsContext.getScene().getWindow();
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

    public void btnMaintenanceOnAction(ActionEvent actionEvent) {
        // add to maintenance
        int i = -1;
        for (Room r : Database.rooms) {
            i++;
            if (r.getId().equals(txtRoomId.getText())){
                // add to maintenance array
                Database.maintenanceRooms.add(new MaintenanceRoomsTM
                        (r.getId(),r.isAC(),r.getCustomer().getName(),r.getCustomer().getDate().toString(),r.getCustomer().getRoomFreeDate()));
                // alert
                new Alert(Alert.AlertType.INFORMATION,"Room added to Maintenance!").show();
            }
        }
    }
}
