package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    TextField NumField, NumField1, NumField2, NumField3, NumField4, NumField5, NumField6;
    @FXML
    Label EqLabel;
    @FXML
    Button Button;

    private Main main;

    public void getMain(Main main){
        this.main = main;
    }

    public void initialize(){
        //EqChoiceBox.setValue("Степень");
        //EqChoiceBox.setItems(FXCollections.observableArrayList("1 степень", "2 степень", "3 степень", "4 степень", "5 степень", "6 степень"));
    }

    public void FindRoot(ActionEvent actionEvent) {
        main.GeneticAlgorithm();
    }
}

