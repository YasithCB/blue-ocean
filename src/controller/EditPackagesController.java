package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.Database;
import entity.Package;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.PackageTM;

import java.io.IOException;

public class EditPackagesController extends PackageDetailsController{
    public AnchorPane EditPackagesContext;
    public JFXButton btnSave;
    public JFXButton btnNew;
    public JFXComboBox cmbPackageType;
    double x,y;

    public TableView tblLightPackages;
        public TableColumn colLightPackageFunctions;
        public TableColumn colLightPackagesPrice;
        public TableColumn colLightPackagePackageCode;

    public TableView tblMidPackages;
        public TableColumn colMidPackagesFunctions;
        public TableColumn colMidPackagesPrice;
        public TableColumn colMidPackagesPackageCode;

    public TableView tblPremiumPackages;
        public TableColumn colPremiumPackagesFunctions;
        public TableColumn colPremiumPackagesPrice;
        public TableColumn colPremiumPackagesPackageCode;

    public JFXTextField txtFunctions;
    public JFXTextField txtPrice;
    public JFXTextField txtPackageCode;


    public void initialize(){
        introduceColumns();
        loadAllPackages();

        // add listener
        tblLightPackages.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            SetData((PackageTM) newValue);
        });
        tblMidPackages.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            SetData((PackageTM) newValue);
        });
        tblPremiumPackages.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            SetData((PackageTM) newValue);
        });

        // initialize cmb
        cmbPackageType.getItems().add("Light Packages");
        cmbPackageType.getItems().add("Mid Packages");
        cmbPackageType.getItems().add("Premium Packages");
    }

    private void SetData(PackageTM tm) {
        txtFunctions.setText(tm.getFunctions());
        txtPackageCode.setText(tm.getPackageCode());
        txtPackageCode.setEditable(false);
        txtPrice.setText(String.valueOf(tm.getPrice()));

        // change button
        btnSave.setText("Save Changes");
        cmbPackageType.setVisible(false);
    }

    public void btnMinimizeOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) EditPackagesContext.getScene().getWindow();
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
        Stage stage = (Stage) EditPackagesContext.getScene().getWindow();
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

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (!txtFunctions.getText().equals("") && !txtPackageCode.getText().equals("") && !txtPrice.getText().equals("")){
            if (cmbPackageType.getSelectionModel().selectedItemProperty().equals("Light Packages")){
                Database.lightPackages.add(new Package(txtFunctions.getText(),Double.parseDouble(txtPrice.getText()),txtPackageCode.getText()));
            }else if (cmbPackageType.getSelectionModel().selectedItemProperty().equals("Mid Packages")){
                Database.midPackages.add(new Package(txtFunctions.getText(),Double.parseDouble(txtPrice.getText()),txtPackageCode.getText()));
            }else if (cmbPackageType.getSelectionModel().selectedItemProperty().equals("Premium Packages")){
                Database.premiumPackages.add(new Package(txtFunctions.getText(),Double.parseDouble(txtPrice.getText()),txtPackageCode.getText()));
            }

            // alert
            new Alert(Alert.AlertType.INFORMATION,"Package Added!").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Please fill all required fields!").show();
        }
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
        // clear fields
        txtFunctions.setText("");
        txtPackageCode.setText("");
        txtPrice.setText("");

        cmbPackageType.setVisible(true);

        if (btnSave.getText().equals("Save Changes")){
            btnSave.setText("Add Package");
            txtPackageCode.setEditable(true);
        }
    }
}
