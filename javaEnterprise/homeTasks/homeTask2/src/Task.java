public interface Task<T extends Number> {

    void execute();

    T getResult();
}
