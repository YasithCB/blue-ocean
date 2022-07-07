package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.Database;
import entity.Customer;
import entity.MealPlan;
import entity.Package;
import entity.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.MealPlanTM;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class MakeAReservationController {
    public AnchorPane MakeaReservationContext;
    double x,y;
    int roomIndex= -1;

    // textFields
        public JFXTextField txtName;
        public JFXTextField txtNicNumber;
        public JFXTextField txtTpNumber;
        public JFXTextField txtEmail;
        public JFXTextField txtAddress;
        public JFXTextField txtNoOfPersons;
        public JFXTextField txtNoOfDays;
        public JFXTextField txtRoomID;
        public JFXTextField txtPackageID;

    public JFXComboBox<MealPlanTM> cmbMealPlans;

    // Labels
    public Label lblAcStatus;
    public Label lblHumanCapacity;
    public Label lblMeal;
    public Label lblTotalPrice;

    public void initialize(){
        // initialize combobox
        ObservableList<MealPlanTM> obList = FXCollections.observableArrayList();
        for (MealPlan mp : Database.mealPlans) {
            obList.add(new MealPlanTM(mp.getName(),mp.getMealCode(),mp.getPrice()));
        }
        cmbMealPlans.setItems(obList);
    }

    public void btnValidateDetailsOnAction(ActionEvent actionEvent) {

        // check all fields filled
        if (txtName.getText().equals("") || txtNicNumber.getText().equals("") || txtTpNumber.getText().equals("") || txtEmail.getText().equals("") ||
                txtAddress.getText().equals("") || txtRoomID.getText().equals("") || txtNoOfPersons.getText().equals("") || txtNoOfDays.getText().equals("")){
            // alert --> fill all fields
            new Alert(Alert.AlertType.ERROR,"Please fill all required fields!").show();
        }else{
            // find the index of the room
            for (int i = 0; i < Database.rooms.size(); i++) {
                Room room = Database.rooms.get(i);
                if (room.getId().equals(txtRoomID.getText())){
                    roomIndex = i;
                    // update labels
                    lblAcStatus.setText(Database.rooms.get(i).isAC());
                    lblHumanCapacity.setText(String.valueOf(Database.rooms.get(i).getHumanCapacity()));
                    lblMeal.setText(String.valueOf(cmbMealPlans.getSelectionModel().getSelectedItem()));
                }
            }
            // Calc total price
            double packagePrice = 0;
            double total = 0;
            if (!txtPackageID.getText().equals("")){
                // find package price

                for (int i = 0; i < Database.lightPackages.size(); i++) {
                    // check in light packages
                    Package pl = Database.lightPackages.get(i);
                    if (pl.getPackageCode().equals(txtPackageID.getText())) {
                        packagePrice = pl.getPrice();
                    }else{
                        // check in mid packages
                        Package pm = Database.midPackages.get(i);
                        if (pm.getPackageCode().equals(txtPackageID.getText())){
                            packagePrice = pm.getPrice();
                        }else {
                            // check in premium packages
                            Package pp = Database.premiumPackages.get(i);
                            if (pp.getPackageCode().equals(txtPackageID.getText())){
                                packagePrice = pp.getPrice();
                            }
                        }
                    }

                }


                total = packagePrice * Double.parseDouble(txtNoOfDays.getText()) * Double.parseDouble(txtNoOfPersons.getText() ) ;

            }else {
                total = Database.rooms.get(roomIndex).getPrice() * Integer.parseInt(txtNoOfDays.getText()) * Integer.parseInt(txtNoOfPersons.getText()) ;
            }

            // find price of meal
            double mealPrice = 0;
            int j = -1;
            for (MealPlan mp : Database.mealPlans) {
                j++;
                if (mp.getName().equals(lblMeal.getText())){
                    mealPrice = Database.mealPlans.get(j).getPrice();
                }
            }
            // add meal price
            total += mealPrice * Double.parseDouble(txtNoOfDays.getText()) * Double.parseDouble(txtNoOfPersons.getText());

            // update total label
            lblTotalPrice.setText(String.valueOf(total));
        }




    }

    public void btnReserveOnAction(ActionEvent actionEvent) {
        if (lblTotalPrice.getText().equals("0.0")){
            // alert --> you must press validate details button first
            new Alert(Alert.AlertType.ERROR,"Please Validate the Details first!").show();
        }else{
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());

            // alert
                new Alert(Alert.AlertType.INFORMATION,"Rooms Reserved!").show();
            // add customer details to the database
            Customer customer = new Customer(txtName.getText(), txtNicNumber.getText(), txtTpNumber.getText(), txtEmail.getText(), txtAddress.getText(),
                    Integer.parseInt(txtNoOfPersons.getText()), Integer.parseInt(txtNoOfDays.getText()),
                    cmbMealPlans.getSelectionModel().getSelectedItem().toString(),txtRoomID.getText(), txtPackageID.getText(),cal,Double.parseDouble(lblTotalPrice.getText()));
            Database.customers.add(customer);
            Database.rooms.get(roomIndex).setCustomer(customer);
            // make the room not available
            Database.rooms.get(roomIndex).setAvailable(false);
        }
    }

    public void btnMinimizeOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) MakeaReservationContext.getScene().getWindow();
        stage.setIconified(true);
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) MakeaReservationContext.getScene().getWindow();
        stage.close();
    }

    public void setUi(String location) throws IOException {
        Stage stage = (Stage) MakeaReservationContext.getScene().getWindow();
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
