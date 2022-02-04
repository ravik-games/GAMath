package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class Controller {
    @FXML
    TextField NumField, NumField1, NumField2, NumField3, NumField4, NumField5, NumField6, n1;
    @FXML
    Label EqLabel;
    @FXML
    Button Button;
    @FXML
    MenuItem EqP1, EqP2, EqP3, EqP4, EqP5, EqP6;
    @FXML
    HBox EqBox;

    private Main main;

    public void getMain(Main main){
        this.main = main;
    }

    public void initialize(){
        //EqChoiceBox.setValue("Степень");
        //EqChoiceBox.setItems(FXCollections.observableArrayList("1 степень", "2 степень", "3 степень", "4 степень", "5 степень", "6 степень"));
    }

    public void findRoot(ActionEvent actionEvent) {
        main.GeneticAlgorithm(Equations.Power, 1);
    }

    public void changeEq(ActionEvent actionEvent) {
        if(actionEvent.getSource() == EqP1){
            TextField tf = new TextField();

            copyValues(NumField1, tf);

            Label label = new Label();
            label.setText("123");

            EqBox.getChildren().addAll(tf, label);


        }else if(actionEvent.getSource() == EqP2){

        }else if(actionEvent.getSource() == EqP3){

        }else if(actionEvent.getSource() == EqP4){

        }else if(actionEvent.getSource() == EqP5){

        }else if(actionEvent.getSource() == EqP6){

        }
    }

    // Copy values from n1 to n2
    private void copyValues(Region n1, Region n2){
        // Copy size
        n2.setMinWidth(n1.getMinWidth());
        n2.setMinHeight(n1.getMinHeight());
        n2.setPrefHeight(n1.getPrefHeight());
        n2.setPrefWidth(n1.getPrefWidth());
        n2.setMaxWidth(n1.getMaxWidth());
        n2.setMaxHeight(n1.getMaxHeight());
    }
}

