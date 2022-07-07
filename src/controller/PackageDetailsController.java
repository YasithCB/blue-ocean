package controller;

import db.Database;
import entity.Package;
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
import view.tm.PackageTM;

import java.io.IOException;

public class PackageDetailsController {
    public AnchorPane packageDetailsContext;
    public double x,y;

    //table light packages
    public TableView<PackageTM> tblLightPackages;
        public TableColumn colLightPackageFunctions;
        public TableColumn colLightPackagesPrice;
        public TableColumn colLightPackagePackageCode;
    // table mid packages
    public TableView tblMidPackages;
        public TableColumn colMidPackagesFunctions;
        public TableColumn colMidPackagesPrice;
        public TableColumn colMidPackagesPackageCode;
    // table premium packages
    public TableView tblPremiumPackages;
        public TableColumn colPremiumPackagesFunctions;
        public TableColumn colPremiumPackagesPrice;
        public TableColumn colPremiumPackagesPackageCode;


    public void initialize() {
        // introduce packages data for the table fields
        introduceColumns();

        // load all package details to the table
        loadAllPackages();
    }

    void introduceColumns() {
                    // introduce packages data for the table fields
        // lightPackages
        colLightPackageFunctions.setCellValueFactory(new PropertyValueFactory<>("functions"));
        colLightPackagesPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colLightPackagePackageCode.setCellValueFactory(new PropertyValueFactory<>("packageCode"));
        // midPackages
        colMidPackagesFunctions.setCellValueFactory(new PropertyValueFactory<>("functions"));
        colMidPackagesPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colMidPackagesPackageCode.setCellValueFactory(new PropertyValueFactory<>("packageCode"));
        // midPackages
        colPremiumPackagesFunctions.setCellValueFactory(new PropertyValueFactory<>("functions"));
        colPremiumPackagesPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colPremiumPackagesPackageCode.setCellValueFactory(new PropertyValueFactory<>("packageCode"));
    }

    void loadAllPackages() {
        ObservableList<PackageTM> obListLightPackages = FXCollections.observableArrayList();
        ObservableList<PackageTM> obListMidPackages = FXCollections.observableArrayList();
        ObservableList<PackageTM> obListPremiumPackages = FXCollections.observableArrayList();

        //take the lightPackages items into the observable List
        for (Package p : Database.lightPackages) {
            obListLightPackages.add(new PackageTM(p.getFunctions(),p.getPrice(),p.getPackageCode()));
        }
        //take the midPackages items into the observable List
        for (Package p : Database.midPackages) {
            obListMidPackages.add(new PackageTM(p.getFunctions(),p.getPrice(), p.getPackageCode()));
        }
        //take the premiumPackages items into the observable List
        for (Package p : Database.premiumPackages) {
            obListPremiumPackages.add(new PackageTM(p.getFunctions(),p.getPrice(), p.getPackageCode()));
        }
        // add obList to the table
        tblLightPackages.setItems(obListLightPackages);
        tblMidPackages.setItems(obListMidPackages);
        tblPremiumPackages.setItems(obListPremiumPackages);
    }

    public void btnMinimizeOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) packageDetailsContext.getScene().getWindow();
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
        Stage stage = (Stage) packageDetailsContext.getScene().getWindow();
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
