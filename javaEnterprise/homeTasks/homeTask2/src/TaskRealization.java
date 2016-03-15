public class TaskRealization implements Task<Double> {
    private double result;
    private int income;
    private int fillUp;
    private int air;
    private int depreciation;
    private Validator validator;

    public TaskRealization(int income, int fillUp, int air, int depreciation) {
        this.income = income;
        this.fillUp = fillUp;
        this.air = air;
        this.depreciation = depreciation;
        this.validator = null;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public Validator getValidator() {
        return validator;
    }

    @Override
    public void execute() {
        result = (income - fillUp - air - depreciation) / depreciation;
    }

    @Override
    public Double getResult() {
        execute();
        return result;
    }

}
