package controller;

import db.Database;
import entity.MealPlan;
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
import view.tm.MealPlanTM;

import java.io.IOException;

public class MealPlansController {
    public AnchorPane mealPlansContext;
    double x,y;

    // table of Meal Plans
    public TableView tblMealPlans;
        public TableColumn colName;
        public TableColumn colMealCode;
        public TableColumn colPrice;

    public void initialize(){
        // introduce columns for data
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMealCode.setCellValueFactory(new PropertyValueFactory<>("mealCode"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        // load to the table
        loadAllMealPlans();
    }

    private void loadAllMealPlans() {
        ObservableList<MealPlanTM> obList = FXCollections.observableArrayList();

        // get MealPlans to the obList
        for (MealPlan m  : Database.mealPlans) {
            obList.add(new MealPlanTM(m.getName(),m.getMealCode(),m.getPrice()));
        }
        tblMealPlans.setItems(obList);
    }

    public void btnMinimizeOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) mealPlansContext.getScene().getWindow();
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
        Stage stage = (Stage) mealPlansContext.getScene().getWindow();
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
