package sample;

public class Genom implements Comparable<Genom> {
    double num = 0;
    double fitness = 0;
    int accuracy = 1;

    public Genom(double n, int acc, double coef[]) {
        num = n;
        accuracy = acc;

        //f = Math.abs(0 - (Math.pow(num, power) * a + (b * num) + c));
        //num * num - (4 * num) + 4
        fitness = calcEquation(coef);
    }

    private double calcEquation(double coef[]){
        double f = 0;
        for (int i = 0; i < coef.length; i++){
            f += Math.pow(num, coef.length - 1 - i) * coef[i];
        }
        return f;
    }

    public double getF() {
        return fitness;
    }

    @Override
    public int compareTo (Genom gen){
        //System.out.println(accuracy);
        return (int)Math.round((fitness - gen.fitness) * accuracy);
    }
}
