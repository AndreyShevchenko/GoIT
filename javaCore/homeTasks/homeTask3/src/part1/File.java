package part1;

public abstract class File implements Comparable<File>{
    private String name;
    private String extension;
    private double size;

    @Override
    public int compareTo(File o) {
        if (name.compareTo(o.getName()) != 0) {
            return name.compareTo(o.getName());
        }
        else {
            if (size > o.getSize()) {
                return 1;
            }
            else {
                if (size < o.getSize()) {
                    return -1;
                }
                else return 0;
            }
        }
    }

    public void setParametres(String name, String extension, double size){
        this.name = name;
        this.extension = extension;
        this.size = size;
    }

    public String getName(){
        return name;
    }

    public String getExtension(){
        return extension;
    }

    public double getSize(){
        return size;
    }

    @Override
    public String toString() {
        return (name + "." + extension + " , size: " + size + "Kb");
    }

}