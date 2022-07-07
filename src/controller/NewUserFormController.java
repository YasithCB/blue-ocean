package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.base.IFXLabelFloatControl;
import db.Database;
import entity.Admin;
import entity.Receptionist;
import entity.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class NewUserFormController {
    public AnchorPane NewUserContext;
    public Label lblLoginTitle;
    double x,y;

    public JFXTextField txtUserName;
    public JFXPasswordField pwReEnterPassword;
    public JFXPasswordField pwPassword;

    public void btnMinimizeOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) NewUserContext.getScene().getWindow();
        stage.setIconified(true);
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void setUi(String location) throws IOException {
        Stage stage = (Stage) NewUserContext.getScene().getWindow();
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

    public void btnRegisterOnAction(ActionEvent actionEvent) throws IOException {
        if (lblLoginTitle.getText().equals("NEW RECEPTIONIST LOGIN")){
            if (txtUserName.getText().equals("") || pwPassword.getText().equals("")){
                // alert
                new Alert(Alert.AlertType.ERROR,"Please fill all required fields!").show();
            }else {
                if (pwPassword.getText().equals(pwReEnterPassword.getText())) {
                    // add user to db
                    Receptionist receptionist = new Receptionist(txtUserName.getText(),pwPassword.getText());
                    Database.receptionists.add(receptionist);
                    // alert
                    Database.currentDB = 'R';
                    new Alert(Alert.AlertType.INFORMATION,"Registration Success!").show();
                    // load dashboard
                    Database.currentUserName = txtUserName.getText();
                    setUi("ReceptionistDB");
                }else {
                    // alert --> pw didn't match
                    new Alert(Alert.AlertType.ERROR,"Password didn't match!").show();
                }
            }


        } else {

            if (txtUserName.getText().equals("") || pwPassword.getText().equals("")){
                // alert
                new Alert(Alert.AlertType.ERROR,"Please fill all required fields!").show();
            }else {
                // new admin
                if (pwPassword.getText().equals(pwReEnterPassword.getText())){
                    // add user to db
                    Admin admin = new Admin(txtUserName.getText(),pwPassword.getText());
                    Database.admins.add(admin);
                    // alert
                    Database.currentDB = 'A';
                    new Alert(Alert.AlertType.INFORMATION,"Registration Success!").show();
                    // load dashboard
                    Database.currentUserName = txtUserName.getText();
                    setUi("AdminDB");
                }else {
                    // alert --> pw didn't match
                    new Alert(Alert.AlertType.ERROR,"Password didn't match!").show();
                }
            }


        }


    }

    public void btnBackToLoginOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }

    public void btnAdminOnAction(ActionEvent actionEvent) {
        if (lblLoginTitle.getText().equals("NEW RECEPTIONIST LOGIN")){
            lblLoginTitle.setText("NEW ADMIN LOGIN");
        }

    }

    public void btnReceptionistOnAction(ActionEvent actionEvent) {
        if (lblLoginTitle.getText().equals("NEW ADMIN LOGIN")){
            lblLoginTitle.setText("NEW RECEPTIONIST LOGIN");
        }

    }
}
