package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.Database;
import entity.Admin;
import entity.Receptionist;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.IOException;

public class UserDetailsController {
    public AnchorPane UserDetailsContext;
    double x,y;

    public JFXTextField txtName;
    public JFXTextField txtPassword;
        public JFXPasswordField pwNewPassword;
        public JFXPasswordField pwReEnter;

    public void initialize(){
        // find index
        int userIndex;
        if (Database.currentDB == 'A'){
            int i = -1;
            for (Admin a : Database.admins) {
                i++;
                if (a.getUserName().equals(Database.currentUserName)){
                    userIndex = i;
                    txtPassword.setText(Database.admins.get(i).getPassword());
                }
            }
        }else {
            int i = -1;
            for (Receptionist r : Database.receptionists) {
                i++;
                if (r.getUserName().equals(Database.currentUserName)){
                    userIndex = i;
                    txtPassword.setText(Database.receptionists.get(i).getPassword());
                }
            }
        }

        // update labels
        txtName.setText(Database.currentUserName);
        txtPassword.setEditable(false);
    }

    public void btnMinimizeOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) UserDetailsContext.getScene().getWindow();
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
        Stage stage = (Stage) UserDetailsContext.getScene().getWindow();
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

    public void btnSaveChangesOnAction(ActionEvent actionEvent) throws IOException {
        if (pwNewPassword.getText().equals(pwReEnter.getText())){
            // update password
            if (Database.currentDB == 'A'){
                for (Admin a : Database.admins) {
                    if (Database.admins.size() == 1){
                        Database.admins.get(0).setUserName(txtName.getText());
                        if (!pwNewPassword.getText().equals("")){
                            Database.admins.get(0).setPassword(pwNewPassword.getText());
                        }
                        Database.currentUserName = txtName.getText();
                    }
                }
            }else {
                for (Receptionist r : Database.receptionists) {
                    if (Database.receptionists.size() == 1){
                        Database.receptionists.get(0).setUserName(txtName.getText());
                        if (!pwNewPassword.getText().equals("")){
                            Database.receptionists.get(0).setPassword(pwNewPassword.getText());
                        }
                        Database.currentUserName = txtName.getText();
                    }
                }
            }
            // alert
            new Alert(Alert.AlertType.INFORMATION,"User Details Updated!").show();

            // refresh
            setUi("UserDetails");

        }else {
            // alert
            new Alert(Alert.AlertType.INFORMATION,"Passwords doesn't match!").show();
        }


    }
}
