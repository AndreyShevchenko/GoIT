package part2;

public class IncorrectColourException extends Exception{
    private String colour;

    public IncorrectColourException(String colour) {
        RuntimeException ex;
        this.colour = colour;
        System.out.println("[Error]: " + colour + " colour of flower - it's not good choice for this bouquet");
        initCause(ex = new RuntimeException());
        throw ex;
    }

}
