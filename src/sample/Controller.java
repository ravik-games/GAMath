package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class Controller {
    @FXML
    TextField NumField1, minRangeField, maxRangeField, accField;
    @FXML
    Button Button;
    @FXML
    MenuItem EqP1, EqP2, EqP3, EqP4, EqP5, EqP6;
    @FXML
    HBox EqBox;
    @FXML
    TextArea resultArea;

    TextField eqFields[];

    private Main main;
    Equations type;

    int rootNum;

    public void getMain(Main main){
        this.main = main;
    }

    public void initialize(){
    }

    public void findRoot(ActionEvent actionEvent) {
        main.GeneticAlgorithm(getData(), 10000);
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
        type = Equations.Power;
        rootNum = 1;
        // Array for labels
        String[] powLabel = new String[]{"", "X +", "X² +", "X³ +", "X⁴ +", "X⁵ +", "X⁶ +", "X⁷ +", "X⁸ +", "X⁹ +"};
        // Clear HBox and TextField array
        EqBox.getChildren().clear();
        eqFields = null;

        eqFields = new TextField[power + 1];
        for (int i = power; i >= 0; i--) {
            eqFields[i] = new TextField();
            copyValues(NumField1, eqFields[i]);

            EqBox.getChildren().add(eqFields[i]);

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

    public EquasionData getData(){
        if(eqFields == null || minRangeField == null || maxRangeField == null || accField == null) {
            return null;
        }
        double[] coef = new double[eqFields.length];
        for (int i = 0; i < eqFields.length; i++) {
            coef[i] = Double.parseDouble(eqFields[eqFields.length - 1 - i].getText());
        }

        int minR = Integer.parseInt(minRangeField.getText());
        int maxR = Integer.parseInt(maxRangeField.getText());
        double accuracy = Double.parseDouble(accField.getText());

        return new EquasionData(type, coef, rootNum, minR, maxR, accuracy);
    }

    public void showResult(String text){
        resultArea.setText(text);
    }
}

