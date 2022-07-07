package controller;

import com.jfoenix.controls.JFXTextField;
import db.Database;
import entity.MealPlan;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AddMealPlansController {
    public AnchorPane AddMealPlansContext;
    double x,y;

    public JFXTextField txtMealName;
    public JFXTextField txtPrice;
    public JFXTextField txtMealCode;

    public void btnMinimizeOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) AddMealPlansContext.getScene().getWindow();
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
        Stage stage = (Stage) AddMealPlansContext.getScene().getWindow();
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
        // make the room obj
        MealPlan mealPlan = new MealPlan(txtMealName.getText(),txtMealCode.getText(),Double.parseDouble(txtPrice.getText()));
        // add it to database
        Database.mealPlans.add(mealPlan);
        // alert
        new Alert(Alert.AlertType.INFORMATION,"Meal Added!").show();
    }
}
