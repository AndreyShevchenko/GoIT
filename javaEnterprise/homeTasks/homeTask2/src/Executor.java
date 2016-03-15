import java.util.List;

public interface Executor<T extends Number, S extends Task> {

    void addTask(S task);

    void addTask(S task, Validator validator);

    void execute();

    List<T> getValidResults();

    List<T> getInvalidResults();
}

