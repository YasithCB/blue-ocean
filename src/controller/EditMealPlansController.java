package controller;

import com.jfoenix.controls.JFXTextField;
import db.Database;
import entity.MealPlan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.MealPlanTM;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;

public class EditMealPlansController {
    public AnchorPane EditMealPlansContext;
    double x,y;

    public TableView tblMealPlans;
        public TableColumn colName;
        public TableColumn colMealCode;
        public TableColumn colPrice;

    public JFXTextField txtMealName;
    public JFXTextField txtMealCode;
    public JFXTextField txtPrice;

    int mealPlanIndex;
    ObservableList<MealPlanTM> obList = FXCollections.observableArrayList();


    public void initialize(){
        // introduce columns for data
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMealCode.setCellValueFactory(new PropertyValueFactory<>("mealCode"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        // load to the table
        loadAllMealPlans();
        txtMealCode.setEditable(false);

        // add listener
        tblMealPlans.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData((MealPlanTM) newValue);
        });
    }

    private void setData(MealPlanTM tm) {
        txtMealName.setText(tm.getName());
        txtMealCode.setText(tm.getMealCode());
        txtPrice.setText(String.valueOf(tm.getPrice()));
    }

    private void loadAllMealPlans() {
        // get MealPlans to the obList
        for (MealPlan m  : Database.mealPlans) {
            obList.add(new MealPlanTM(m.getName(),m.getMealCode(),m.getPrice()));
        }
        tblMealPlans.setItems(obList);
    }

    public void btnMinimizeOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) EditMealPlansContext.getScene().getWindow();
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
        Stage stage = (Stage) EditMealPlansContext.getScene().getWindow();
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

    public void btnSaveOnAction(ActionEvent actionEvent) throws IOException {
        for (int i = 0; i < obList.size(); i++) {
            MealPlanTM tm = obList.get(i);
            if (txtMealCode.getText().equals(tm.getMealCode())){
                mealPlanIndex = i;
                break;
            }
        }

        MealPlan mealPlan = new MealPlan(txtMealName.getText(),txtMealCode.getText(),Double.parseDouble(txtPrice.getText()));
        Database.mealPlans.set(mealPlanIndex,mealPlan);

        // refresh
        setUi("EditMealPlans");
        // alert confirmation
        new Alert(Alert.AlertType.CONFIRMATION,"Meal Updated").show();
    }
}
