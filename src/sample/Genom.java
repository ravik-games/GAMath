package sample;

public class Genom implements Comparable<Genom> {
    double num = 0;
    double f = 0;
    int accuracy = 1;

    public Genom(double n, int acc) {
        num = n;
        accuracy = acc;
        /*for (int i = 0; i < str.length(); i++) {
            //f = f + Math.abs(str.charAt(i) - Main.goal[i]);
        }*/

        f = Math.abs(0 - (num * num - (4 * num) + 4));
    }

    public double getF() {
        return f;
    }

    @Override
    public int compareTo (Genom gen){
        //System.out.println(accuracy);
        return (int)Math.round((f - gen.f) * accuracy);
    }
}
