package part1;

public class FileExceptions extends Exception{
    private double fileSize;

    public FileExceptions(final double fileSize){
        this.fileSize = fileSize;
    }

}
