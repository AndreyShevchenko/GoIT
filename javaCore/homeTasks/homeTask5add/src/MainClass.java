/**
 * Created by Андрей Шевченко on 05.02.2016.
 */
public class MainClass {

    public static void main(String[] args){
        double[][] a = {{0,1,2},{3,4,5},{5,2,2}};
        double[][] b = {{0,1,2},{3,4,5},{5,2,2}};
        MatrixCalculator c = new MatrixCalculator();
        c.add(a, b);
        c.subtract(a, b);
        c.multiply(a, 2);
    }
}
