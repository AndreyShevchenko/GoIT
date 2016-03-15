public class ValidatorRealization  implements Validator<Double> {

    @Override
    public boolean isValid(Double result) {
        return result > 2.5;
    }
}
