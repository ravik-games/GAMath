package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main extends Application {

    //Коэффиценты для ГА
    double elite = 0.1;
    double survive = 0.5;
    double mutation = 0.2;

    int accuracy = 100;
    int range = 10;
    int rootNum = 1;
    Equations equation = Equations.Power;

    int populationSize = 100;

    double[] coef = new double[]{1, -4, 4};
    List<Genom> population = new ArrayList();

    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Window.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Math");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();

        controller = loader.getController();
        controller.getMain(this);
    }

    // x^2 - 4x + 4 = 0
    // x == 2

    public static void main(String[] args) {
        launch(args); }

    public void GeneticAlgorithm(Equations eq, int rootN){
        equation = eq;
        rootNum = rootN;
        for (int i = 0; i < 1000000; i++) {
            initializePopulation();

            //System.out.println(population.get(0).getF() + " " + population.get(0).num[0] + " " + population.get(0).num[1]);

            if(Math.abs(population.get(0).getF()) < ((double) 1 / ((double) accuracy))){
                System.out.print("найдено: ");
                for (double n: population.get(0).num) {
                    System.out.print(n + " ");
                }
                System.out.println("за " + i + " циклов");
                break;
            }

            population = childPopulation(population);
        }
        population.clear();
    }

    public int[] getCoef(){
        int[] c =  new int[3];
        return c;
    }

    //create and return child population
    List<Genom> childPopulation(List<Genom> population){
        int esize = (int) (populationSize * elite);

        List<Genom> children = new ArrayList<>();

        for (int i = 0; i < esize; i++) {
            children.add(population.get(i));
        }

        for (int i = esize; i < populationSize; i++) {
            int p1 = (int)(Math.random() * populationSize * survive);
            int p2 = (int)(Math.random() * populationSize * survive);

            double num[] = new double[rootNum];
            for (int j = 0; j < rootNum; j++) {
                num[j] = (population.get(p1).num[j] + population.get(p2).num[j]) / 2;

                if(Math.random() < mutation){
                    num[j] = mutate(num[j]);
                }
            }

            Genom child = new Genom(num, accuracy, coef, equation);
            children.add(child);
        }

        return children;
    }

    double mutate(double n){
        double num = Math.random() * (n / 10);
        num = num * Math.round(Math.random()) == 0 ? -1 : 1;
        return n + num;
    }

    void initializePopulation(){
        //int tsize = goal.length;

        for (int i = 0; i < populationSize; i++) {
            /*StringBuilder str = new StringBuilder();
            for (int j = 0; j < tsize; j++) {
                str.append((char) (Math.random()*10 + 48));
            }*/

            double num[] = new double[rootNum];
            for (int j = 0; j < rootNum; j++) {
                num[j] = Math.random() * range;
            }

            Genom citizen = new Genom(num, accuracy, coef, equation);
            population.add(citizen);
        }

        Collections.sort(population);

        /*for (Genom g : population) {
            System.out.println(g.num[0]+" "+g.getF());
        }*/
    }
}
