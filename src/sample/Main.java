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
        primaryStage.setScene(new Scene(root, 1300, 800));
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

        Genom answers[] = new Genom[data.getRootNumber()];
        int ansNum = 0;
        int antiInfinite = data.getIterations();

        String result = "Программе не удалось найти подходящий ответ\nили уравнение не имеет решений." +
                "\nПопробуйте изменить диапазон поиска.";

        for (int j = 0; j < data.getRootNumber(); j++) {
            // prevent algorithm to create infinite loop
            if(antiInfinite <= 0)
                break;

            population.clear();
            for (int i = 0; i < iterations; i++) {
                initializePopulation();

                if(Math.abs(population.get(0).getF()) < data.getError()){

                    boolean condition = false;

                    answerCheckLoop:
                    {
                        for (Genom g : answers) {
                            if(g != null) {
                                for (double num : g.num) {
                                    for (double curNum : population.get(0).num) {
                                        //check for unique answer
                                        if (Math.abs(num - curNum) < data.getError()) {
                                            condition = true;
                                            break answerCheckLoop;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(condition){
                        j--;
                        antiInfinite--;

                        /*result += " Решение " + (ansNum) + ":\n" +
                                "   НЕ НАЙДЕНО\n";*/

                        break;
                    }

                    ansNum++;
                    answers[ansNum - 1] = population.get(0);

                    // Format decimal to remove trail
                    String pattern = "#.";
                    for (int o = 0; o < String.valueOf(data.getError()).length() - 4; o++) {
                        pattern += "#";
                    }
                    DecimalFormat format = new DecimalFormat(pattern);

                    if(ansNum == 1) {
                        result = "Найдено:\n";
                    }
                    result += " Решение " + (ansNum) + ":\n";
                    for (int k = 0; k < answers[ansNum - 1].num.length; k++) {
                        switch (data.getType()){
                            default:
                                result += "     X = " + format.format(answers[ansNum - 1].num[k]) + "\n";
                                break;
                            case Diophantine:
                                result += "     " + (char)(k + 65) + " = " + format.format(answers[ansNum - 1].num[k]) + "\n";
                                break;
                        }

                    }

                    break;
                }else{

                }
                population = childPopulation(population);
            }
        }

        if(answers[0] != null) {
            vals.setRoots(answers[0].num);
            vals.setRoots(answers[0].num);
            controller.ED = vals;
            controller.drawGraph(false);
        }

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

            double num[] = new double[data.getArgNum()];
            for (int j = 0; j < data.getArgNum(); j++) {
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

            double num[] = new double[data.getArgNum()];
            for (int j = 0; j < data.getArgNum(); j++) {
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
