/**
 * Created by JAvengers on 28.01.2016.
 * class was created to handle exception of division by 0, as in the usual case,
 * the system outputs the result = infinity, that does not always satisfy the required result
 */
public class DivisionByZeroException extends Exception {

    public DivisionByZeroException() {
        System.out.println("[Error]: Division by zero is impossible");
    }
}
