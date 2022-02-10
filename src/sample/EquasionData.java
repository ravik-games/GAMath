package sample;

public class EquasionData {
    private double[] coef;
    private int roots;
    private Equations type;

    private int rangeMin;
    private int rangeMax;
    private double error;

    public EquasionData(Equations t, double[] coefficient, int rootNum, int minRange, int maxRange, double acc){
        type = t;
        coef = coefficient;
        roots = rootNum;
        rangeMin = minRange;
        rangeMax = maxRange;
        error = acc;
    }

    public double[] getCoef() {
        return coef;
    }

    public void setCoef(double[] coef) {
        this.coef = coef;
    }

    public int getRoots() {
        return roots;
    }

    public void setRoots(int roots) {
        this.roots = roots;
    }

    public Equations getType() {
        return type;
    }

    public void setType(Equations type) {
        this.type = type;
    }

    public int getRangeMin() {
        return rangeMin;
    }

    public void setRangeMin(int rangeMin) {
        this.rangeMin = rangeMin;
    }

    public int getRangeMax() {
        return rangeMax;
    }

    public void setRangeMax(int rangeMax) {
        this.rangeMax = rangeMax;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }
}
