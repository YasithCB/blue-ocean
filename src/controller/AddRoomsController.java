package controller;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import db.Database;
import entity.Customer;
import entity.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AddRoomsController {
    double x,y;

    public AnchorPane addRoomContext;
    public JFXTextField txtRoomId;
    public JFXRadioButton rbtnAcYes;
    public JFXRadioButton rbtnAcNo;
    public JFXTextField txtHumanCapacity;
    public JFXTextField txtPrice;

    public void btnMinimizeOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) addRoomContext.getScene().getWindow();
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
        Stage stage = (Stage) addRoomContext.getScene().getWindow();
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

    public void btnAddOnAction(ActionEvent actionEvent) {
        //get the radioButton values
        boolean ac = rbtnAcYes.isSelected()? true:false;

        // make the room obj
        Room room = new Room(txtRoomId.getText(),ac,true,Byte.parseByte(txtHumanCapacity.getText()),Double.parseDouble(txtPrice.getText()),new Customer());
        // add it to database
        Database.rooms.add(room);
    }
}
