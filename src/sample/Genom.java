package sample;

public class Genom implements Comparable<Genom> {
    double fitness = 0;
    double[] num;

    public Genom(double[] n, double[] coef, Equations equation, double goal) {

        num = n;
        //f = Math.abs(0 - (Math.pow(num, power) * a + (b * num) + c));
        //num * num - (4 * num) + 4
        switch (equation){
            case Power:
                fitness = calcPower(coef, n, goal);
                break;
            case Diophantine:
                fitness = calcDio(coef, n, goal);
                break;
        }
    }

    private double calcPower(double coef[], double num[], double goal){
        double f = 0;
        for (int i = 0; i < coef.length; i++){
            f += Math.pow(num[0], coef.length - 1 - i) * coef[i];
        }
        return Math.abs(goal - f);
    }

    private double calcDio(double coef[], double num[], double goal){
        double f = 0;
        for (int i = 0; i < num.length; i++){
            f += num[i] * coef[i];
        }
        f += coef[coef.length - 1]; // ?
        return Math.abs(goal - f);
    }

    public double getF() {
        return fitness;
    }

    @Override
    public int compareTo (Genom gen){
        return Double.compare(fitness, gen.fitness);
    }
}
