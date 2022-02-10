package sample;

public class Genom implements Comparable<Genom> {
    double num [];
    double fitness = 0;

    public Genom(double n [], double coef[], Equations equation) {
        num = n;

        //f = Math.abs(0 - (Math.pow(num, power) * a + (b * num) + c));
        //num * num - (4 * num) + 4
        switch (equation){
            case Power:
                fitness = calcPower(coef);
                break;
            case Diophantine:
                fitness = calcDio(coef);
                break;
        }
    }

    private double calcPower(double coef[]){
        double f = 0;
        for (int i = 0; i < coef.length; i++){
            f += Math.pow(num[0], coef.length - 1 - i) * coef[i];
        }
        return Math.abs(f);
    }

    private double calcDio(double coef[]){
        double f = 0;
        for (int i = 0; i < num.length; i++){
            f += num[i] * coef[i];
        }
        f += coef[coef.length - 1];
        return Math.abs(f);
    }

    public double getF() {
        return fitness;
    }

    @Override
    public int compareTo (Genom gen){
        return Double.compare(fitness, gen.fitness);
    }
}
