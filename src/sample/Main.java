package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main extends Application {

    //Коэффиценты для ГА
    static double elite = 0.1;
    static double survive = 0.5;
    static double mutation = 0.2;
    static int accuracy = 10000;

    static int populationSize = 100;
    //static char[] goal;
    static List<Genom> population = new ArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Window.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    // x^2 - 4x + 4 = 0
    // x == 2

    public static void main(String[] args) {
        //launch(args);

        for (int i = 0; i < 100000000; i++) {
            initializePopulation();

            //System.out.println(population.get(0).getF() < 0.3);

            if(population.get(0).getF() < ((double) 1 / ((double) accuracy))){
                System.out.println("найдено: " + population.get(0).num + " за " + i + " циклов");
                break;
            }

            population = childPopulation(population);
        }
    }

    //create and return child population
    static List<Genom> childPopulation(List<Genom> population){
        int esize = (int) (populationSize * elite);

        List<Genom> children = new ArrayList<>();

        for (int i = 0; i < esize; i++) {
            children.add(population.get(i));
        }

        for (int i = esize; i < populationSize; i++) {
            int p1 = (int)(Math.random() * populationSize * survive);
            int p2 = (int)(Math.random() * populationSize * survive);

            double num = (population.get(p1).num + population.get(p2).num) / 2;

            if(Math.random() < mutation){
                num = mutate(num);
            }

            Genom child = new Genom(num, accuracy);
            children.add(child);
        }

        return children;
    }

    static double mutate(double n){
        //int ipos = (int)(Math.random() * goal.length);
        //char delta = (char) (Math.random()*10 + 48);
        //String str = s.substring(0, ipos) + delta + s.substring(ipos + 1);
        double num = Math.random() * (n / 10);
        num = num * Math.round(Math.random()) == 0 ? -1 : 1;
        return n + num;
    }

    static void initializePopulation(){
        //int tsize = goal.length;

        for (int i = 0; i < populationSize; i++) {
            /*StringBuilder str = new StringBuilder();
            for (int j = 0; j < tsize; j++) {
                str.append((char) (Math.random()*10 + 48));
            }*/

            double num = Math.random() * 10;

            Genom citizen = new Genom(num, accuracy);
            population.add(citizen);
        }

        Collections.sort(population);

        for (Genom g : population) {
            System.out.println(g.num+" "+g.getF());
        }
    }
}
