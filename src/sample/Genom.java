package sample;

public class Genom implements Comparable<Genom> {
    double num [];
    double fitness = 0;
    int accuracy = 1;

    public Genom(double n [], int acc, double coef[], Equations equation) {
        num = n;
        accuracy = acc;

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
        return f;
    }

    private double calcDio(double coef[]){
        double f = 0;
        for (int i = 0; i < num.length; i++){
            f += num[i] * coef[i];
            System.out.println(num[i] + " * " + coef[i]);
        }
        f += coef[coef.length - 1];
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
