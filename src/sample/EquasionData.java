package sample;

public class EquasionData {
    private double[] coef;
    private int argNum;
    private Equations type;

    private int rangeMin;
    private int rangeMax;
    private double error;
    private double[] roots;
    private int rootNumber;
    private int iterations;

    public double getGoal() {
        return goal;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }

    private double goal;

    public EquasionData(Equations t, double[] coefficient, int argN,
                        int minRange, int maxRange, double acc, int rootN,
                        int iter){
        type = t;
        coef = coefficient;
        argNum = argN;
        rangeMin = minRange;
        rangeMax = maxRange;
        error = acc / 100;
        rootNumber = rootN;
        iterations = iter;
    }

    public double[] getCoef() {
        return coef;
    }

    public void setCoef(double[] coef) {
        this.coef = coef;
    }

    public int getArgNum() {
        return argNum;
    }

    public void setArgNum(int argNum) {
        this.argNum = argNum;
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

    public double[] getRoots() { return roots; }

    public void setRoots(double[] roots) { this.roots = roots; }

    public int getRootNumber() { return rootNumber; }

    public void setRootNumber(int rootNumber) { this.rootNumber = rootNumber; }
    public int getIterations() { return iterations; }

    public void setIterations(int iterations) { this.iterations = iterations; }
}
