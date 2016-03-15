public class MainClass {

    public static void main(String[] args) {
        ExecutorRealization a = new ExecutorRealization();
        a.addTask(new TaskRealization(191, 0, 35, 38));
        a.addTask(new TaskRealization(766, 228, 35, 130));
        a.addTask(new TaskRealization(714, 0, 35, 124));
        a.addTask(new TaskRealization(858, 0, 35, 154));
        a.addTask(new TaskRealization(509, 251, 35, 86));
        a.addTask(new TaskRealization(160, 0, 35, 69));
        a.addTask(new TaskRealization(287, 215, 0, 72));
        a.execute();
        a.getValidResults();
        a.getInvalidResults();
    }
}
