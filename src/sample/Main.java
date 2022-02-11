package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main extends Application {

    //Коэффиценты для ГА
    double elite = 0.1;
    double survive = 0.5;
    double mutation = 0.2;

    EquasionData data;
    //double[] coef = new double[]{1, 2, 3, 4, -30};

    int populationSize = 100;
    List<Genom> population = new ArrayList();

    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Window.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Math");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();

        controller = loader.getController();
        controller.getMain(this);
    }

    // x^2 - 4x + 4 = 0
    // x == 2

    public static void main(String[] args) {
        launch(args); }

    public void GeneticAlgorithm(EquasionData vals, int iterations){
        if(vals == null)
            return;
        data = vals;
        String result = "Программе не удалось найти подходящий ответ или уранение не имеет решений.\nПопробуйте изменить диапазон поиска.";

        for (int i = 0; i < iterations; i++) {
            initializePopulation();

            if(Math.abs(population.get(0).getF()) < data.getError()){
                // Format decimal to remove trail
                String pattern = "0.";
                for (int j = 0; j < String.valueOf(data.getError()).length() - 2; j++) {
                    pattern += "#";
                }
                DecimalFormat format = new DecimalFormat("0.##");
                                
                result = "Найдено:\n";
                for (double n: population.get(0).num) {
                    result += format.format(n) + "\n";
                }
                result += "За " + i + " циклов.";
                break;
            }

            population = childPopulation(population);
        }
        population.clear();
        controller.showResult(result);
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

            double num[] = new double[data.getRoots()];
            for (int j = 0; j < data.getRoots(); j++) {
                num[j] = (population.get(p1).num[j] + population.get(p2).num[j]) / 2;

                if(Math.random() < mutation){
                    num[j] = mutate(num[j]);
                }
            }

            Genom child = new Genom(num, data.getCoef(), data.getType(), data.getGoal());
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

            double num[] = new double[data.getRoots()];
            for (int j = 0; j < data.getRoots(); j++) {
                num[j] = Math.random() * (data.getRangeMax() - data.getRangeMin()) + data.getRangeMin();
            }

            Genom citizen = new Genom(num, data.getCoef(), data.getType(), data.getGoal());
            population.add(citizen);
        }

        Collections.sort(population);

        for (Genom g : population) {
            //System.out.println(g.num[0]+" "+g.getF());
        }
    }
}
