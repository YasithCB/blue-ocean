package controller;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class LoginFormController {
    public JFXPasswordField pwPassword;
    public JFXTextField txtUserName;
    public Label lblNew;
    public Label lblLoginTitle;
    public JFXButton btnLetsGo;
    public AnchorPane loginFormContext;
    private byte attempts = 3;

    double x,y;


    public void btnAdminOnAction(ActionEvent actionEvent) {
        if (lblLoginTitle.getText().equals("RECEPTIONIST LOGIN")){
            lblLoginTitle.setText("ADMIN LOGIN");
            lblNew.setText("N e w  A d m i n ?");
        }

    }

    public void btnReceptionistOnAction(ActionEvent actionEvent) {
        if (lblLoginTitle.getText().equals("ADMIN LOGIN")){
            lblLoginTitle.setText("RECEPTIONIST LOGIN");
            lblNew.setText("N e w  R e c e p t i o n i s t ?");
        }

    }

    /**
     * when pressed the lets go button it must load for the dashboard if it
     * is usrname and psswrd valid
     * @param actionEvent
     * @throws IOException
     */
    public void btnLetsGoOnAction(ActionEvent actionEvent) throws IOException {
        if (attempts > 0){
            // check the user

            attempts = 3;
            // go to the dashboard
            if (lblLoginTitle.getText().equals("RECEPTIONIST LOGIN")){

                // check and get user
                boolean isIn = false;
                for (Receptionist r: Database.receptionists) {
                    if (txtUserName.getText().equals(r.getUserName())){
                        isIn = true;
                        if (pwPassword.getText().equals(r.getPassword())){
                            Database.currentUserName = txtUserName.getText();

                            Stage stgReceptionistDB = (Stage) loginFormContext.getScene().getWindow();
                            Parent root = FXMLLoader.load(getClass().getResource("../view/ReceptionistDB.fxml"));
                            stgReceptionistDB.setScene(new Scene(root));
                            // make draggable the window
                            root.setOnMousePressed(event -> {
                                x = event.getSceneX();
                                y = event.getSceneY();
                            });
                            root.setOnMouseDragged(event -> {
                                stgReceptionistDB.setX(event.getScreenX() - x);
                                stgReceptionistDB.setY(event.getScreenY() - y);
                            });

                            stgReceptionistDB.show();
                            stgReceptionistDB.centerOnScreen();
                            break;
                        }else {
                            new Alert(Alert.AlertType.ERROR,"Incorrect Password!").show();
                        }
                    }
                }
                if (!isIn){
                    new Alert(Alert.AlertType.ERROR,"Invalid Username!").show();
                }


            }else{
                // check and get user
                boolean isIn = false;
                for (Admin a : Database.admins) {
                    if (txtUserName.getText().equals(a.getUserName())){
                        isIn = true;
                        if (pwPassword.getText().equals(a.getPassword())){
                            // change current DB
                            Database.currentDB = 'A';
                            Database.currentUserName = txtUserName.getText();

                            // load adminDashboard
                            Stage stgAdminDB = (Stage) loginFormContext.getScene().getWindow();
                            Parent root = FXMLLoader.load(getClass().getResource("../view/AdminDB.fxml"));
                            stgAdminDB.setScene(new Scene(root));
                            // make draggable the window
                            root.setOnMousePressed(event -> {
                                x = event.getSceneX();
                                y = event.getSceneY();
                            });
                            root.setOnMouseDragged(event -> {
                                stgAdminDB.setX(event.getScreenX() - x);
                                stgAdminDB.setY(event.getScreenY() - y);
                            });

                            stgAdminDB.show();
                            stgAdminDB.centerOnScreen();
                        }else {
                            new Alert(Alert.AlertType.ERROR,"Incorrect Password!").show();
                        }
                    }
                }
                if (!isIn){
                    new Alert(Alert.AlertType.ERROR,"Invalid Username!").show();
                }
            }

        }else{
            txtUserName.setEditable(false);
            pwPassword.setEditable(false);
            btnLetsGo.setText("ACCESS DENIED!");
        }

    }

    public void setUi(String location) throws IOException {
        Stage stage = (Stage) loginFormContext.getScene().getWindow();
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
        setUi("NewUserForm");
    }

    public void btnMinimizeOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) loginFormContext.getScene().getWindow();
        stage.setIconified(true);
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }
}
