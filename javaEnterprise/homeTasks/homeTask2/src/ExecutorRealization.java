import java.util.ArrayList;
import java.util.List;

public class ExecutorRealization implements Executor<Double, TaskRealization> {
    private List<TaskRealization> tasks = new ArrayList<>();
    private boolean isExecuteNotRun = true;
    List<Double> validResults = new ArrayList<>();
    List<Double> invalidResults = new ArrayList<>();

    @Override
    public void addTask(TaskRealization task) {
        try {
            if (isExecuteNotRun) {
                tasks.add(task);
            }
            else throw new Exception();
        } catch (Exception e) {
            System.out.println("[Error]: execute method is already running");
        }
    }

    @Override
    public void addTask(TaskRealization task, Validator validator) {
        task.setValidator(validator);
        try {
            if (isExecuteNotRun) {
                tasks.add(task);
            }
            else throw new Exception();
        } catch (Exception e) {
            System.out.println("[Error]: execute method is already running");
        }
    }

    @Override
    public void execute() {
        isExecuteNotRun = false;
        for (TaskRealization task: tasks) {
            double result = task.getResult();
            Validator validator = task.getValidator();
            if (validator == null) {
                validResults.add(result);
            } else {
                if (validator.isValid(result)) {
                    validResults.add(result);
                } else {
                    invalidResults.add(result);
                }
            }
        }
    }

    @Override
    public List<Double> getValidResults() {
        try {
            if (isExecuteNotRun) throw new Exception();
            else {
                System.out.println("Valid results are:");
                validResults.forEach(value -> System.out.printf("%.2f%n", value));
            }
        } catch (Exception ex) {
            System.out.println("[Error]: execute method wasn't running else");
        }
        return validResults;
    }

    @Override
    public List<Double> getInvalidResults() {
        try {
            if (isExecuteNotRun) throw new Exception();
            else {
                System.out.println("Invalid results are:");
                invalidResults.forEach(value -> System.out.printf("%.2f%n", value));
            }
        } catch (Exception ex) {
            System.out.println("[Error]: execute method wasn't running else");
        }
        return invalidResults;
    }
}
