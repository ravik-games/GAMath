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

    TextField eqFields[];

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


        // Choose equation
        if(actionEvent.getSource() == EqP1){
            showPowerEq(1);
        }else if(actionEvent.getSource() == EqP2){
            showPowerEq(2);
        }else if(actionEvent.getSource() == EqP3){
            showPowerEq(3);
        }else if(actionEvent.getSource() == EqP4){
            showPowerEq(4);
        }else if(actionEvent.getSource() == EqP5){
            showPowerEq(5);
        }else if(actionEvent.getSource() == EqP6){
            showPowerEq(6);
        }
    }

    private void showPowerEq(int power){
        // Array for labels
        String powLabel[] = new String[]{"", "X +", "X² +", "X³ +", "X⁴ +", "X⁵ +", "X⁶ +", "X⁷ +", "X⁸ +", "X⁹ +"};
        // Clear HBox and TextField array
        EqBox.getChildren().clear();
        eqFields = null;

        eqFields = new TextField[power + 1];
        for (int i = power; i >= 0; i--) {
            System.out.println(i);
            eqFields[power] = new TextField();
            copyValues(NumField1, eqFields[power]);


            EqBox.getChildren().add(eqFields[power]);

            if(i > 0) {
                Label l = new Label();
                l.setText(powLabel[i]);
                EqBox.getChildren().add(l);
            }
        }
        Label equals = new Label();
        equals.setText("= " + /* ADD RESULT VAR */ "0");
        TextField freePart = new TextField();
        freePart.setId("eqField0");
        EqBox.getChildren().addAll(equals);
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

