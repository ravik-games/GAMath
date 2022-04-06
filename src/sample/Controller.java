package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    TextField NumField1, minRangeField, maxRangeField, accField, rootNumField;
    @FXML
    Button Button;
    @FXML
    MenuItem EqP, EqLin, EqDio, EqT1, EqT2, EqT3, EqT4, EqT5, EqT6;
    @FXML
    MenuButton EqChooseButton;
    @FXML
    HBox EqBox;
    @FXML
    TextArea resultArea;

    TextField eqFields[];
    @FXML
    LineChart lineChart;
    @FXML
    LineChart bigChart;

    EquasionData ED;
    private Main main;
    Equations type;

    int argNum;

    public void getMain(Main main){
        this.main = main;
    }

    public void initialize(){
    }

    public void findRoot(ActionEvent actionEvent) {
        main.GeneticAlgorithm(getData(), 10000);
    }

    //Graph
    public void drawGraph(boolean bChart){
        if(ED == null)
            return;
        double f = 0;

        ObservableList<XYChart.Data<Number, Number>> data = FXCollections.observableArrayList();

        for(int j = ED.getRangeMin(); j <= ED.getRangeMax(); j++) {
            switch (ED.getType()){
                case Power:
                    for (int i = 0; i < ED.getCoef().length; i++) {
                        f += Math.pow(j, ED.getCoef().length - 1 - i) * ED.getCoef()[i];
                    }
                    f = f - ED.getGoal();
                    break;
                case Diophantine:
                    /*for (int i = 0; i < ED.getRoots().length; i++){
                        f += ED.getRoots()[i] * ED.getRoots()[i];
                    }
                    System.out.println(f);
                    f += ED.getCoef()[ED.getCoef().length - 1];
                    f = f - ED.getGoal();
                    break;*/
                    lineChart.getData().clear();
                    return;
            }
            data.add( new XYChart.Data<Number, Number>(j, f));
            f = 0;
        }
        
        XYChart.Series<Number, Number> s1 = new XYChart.Series<>();
        s1.setName("График функции");
        s1.getData().addAll(data);
        
        if(bChart){
            System.out.println(bigChart);
            bigChart.getData().clear();
            bigChart.getData().add(s1);
        }else{
            lineChart.getData().clear();
            lineChart.getData().add(s1);
        }
    }

    public void changeEq(ActionEvent actionEvent) {
        // Choose equation
        if(actionEvent.getSource() == EqLin){
            showPowerEq(1);
            EqChooseButton.setDisable(true);
        }else if(actionEvent.getSource() == EqP){
            EqChooseButton.setDisable(false);
            type = Equations.Power;
        }else if(actionEvent.getSource() == EqDio){
            EqChooseButton.setDisable(false);
            type = Equations.Diophantine;
        }
    }

    public void chooseEq(ActionEvent actionEvent) {
        // Choose equation type
        if(actionEvent.getSource() == EqT1){
            if(type == Equations.Power)
                showPowerEq(1);
            else
                showDioEq(1);
        }else if(actionEvent.getSource() == EqT2){
            if(type == Equations.Power)
                showPowerEq(2);
            else
                showDioEq(2);
        }else if(actionEvent.getSource() == EqT3){
            if(type == Equations.Power)
                showPowerEq(3);
            else
                showDioEq(3);
        }else if(actionEvent.getSource() == EqT4){
            if(type == Equations.Power)
                showPowerEq(4);
            else
                showDioEq(4);
        }else if(actionEvent.getSource() == EqT5){
            if(type == Equations.Power)
                showPowerEq(5);
            else
                showDioEq(5);
        }else if(actionEvent.getSource() == EqT6){
            if(type == Equations.Power)
                showPowerEq(6);
            else
                showDioEq(6);
        }
    }

    private void showPowerEq(int power){
        type = Equations.Power;
        argNum = 1;
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

    private void showDioEq(int rootN){
        type = Equations.Diophantine;
        argNum = rootN;
        // Array for labels
        String[] dioLabel = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        // Clear HBox and TextField array
        EqBox.getChildren().clear();
        eqFields = null;

        eqFields = new TextField[rootN];
        for (int i = 0; i < argNum; i++) {
            eqFields[i] = new TextField();
            copyValues(NumField1, eqFields[i]);

            EqBox.getChildren().add(eqFields[i]);

            Label l = new Label();
            l.setText(dioLabel[i] + " +");
            if(i >= rootN - 1)
                l.setText(dioLabel[i]);
            EqBox.getChildren().add(l);

        }
        Label equals = new Label();
        equals.setText("= " + "0");
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

    // parse user data
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
        int rootN = Integer.parseInt(rootNumField.getText());

        return new EquasionData(type, coef, argNum, minR, maxR, accuracy, rootN, 1000);
    }

    public void showResult(String text){
        resultArea.setText(text);
    }

    public void openGraphWindow(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Graph.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Graph");
            stage.setScene(new Scene(root, 1600, 900));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        drawGraph(true);
    }
}

