package part2;

public class IncorrectLengthException extends Exception{
    private int length;

    public IncorrectLengthException(int length) {
        RuntimeException ex;
        this.length = length;
        System.out.println("[Error]: Length = " + length + " not suitable for this bouquet");
        initCause(ex = new RuntimeException());
        throw ex;
    }

}
