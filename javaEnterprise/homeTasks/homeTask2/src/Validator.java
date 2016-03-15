public interface Validator<T extends Number> {

    boolean isValid(T result);
}
