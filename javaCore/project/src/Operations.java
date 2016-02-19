import java.math.BigDecimal;

/**
 * Created by JAvengers on 28.01.2016.
 * class that implements the basic arithmetic operations with two numbers
 * @see BigDecimal
 */
public class Operations {

    /**
     * method that implements the operation of addition of two numbers
     * @param  operand1 is first summand
     * @param  operand2 is second summand
     * @return sum
     */
    public BigDecimal add(BigDecimal operand1, BigDecimal operand2) {
        return operand1.add(operand2);
    }

    /**
     * method that implements the operation of subtraction of two numbers
     * @param  operand1 is minuend
     * @param  operand2 is subtrahend
     * @return difference
     */
    public BigDecimal subtract(BigDecimal operand1, BigDecimal operand2) {
        return operand1.subtract(operand2);
    }

    /**
     * method that implements the operation of multiplication of two numbers
     * @param  operand1 is first multiplier
     * @param  operand2 is second multiplier
     * @return product
     */
    public BigDecimal multiply(BigDecimal operand1, BigDecimal operand2) {
        return operand1.multiply(operand2);
    }

    /**
     * method that implements the operation of division of two numbers
     * @param     operand1 is dividend
     * @param     operand2 is divisor
     * @exception DivisionByZeroException generated in the case of division by 0
     * @return    quotient
     */
    public BigDecimal divide(BigDecimal operand1, BigDecimal operand2) throws DivisionByZeroException {
        if (operand2.signum() == 0) {
            throw new DivisionByZeroException ();
        }
        return operand1.divide(operand2, InputOutput.getPrecision(), BigDecimal.ROUND_HALF_UP);
    }
}
