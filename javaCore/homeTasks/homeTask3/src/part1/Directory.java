package part1;

import part1.File;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

enum fileTypes { AUDIO, PICTURE, TEXT }

public class Directory {
    private static Set<File> files;
    private static Set<File> filesTree;

    public Directory() {
        files = new HashSet<>();
    }

    public static Set<File> getFiles() {
        return files;
    }

    public static void setFilesTree() {
        filesTree = new TreeSet<>(files);
    }

    public static Set<File> getFilesTree() {
        return filesTree;
    }

    public static void addFile(String type, String name, String extension, double size){
        File newFile;
        try {
            fileTypes fileType = fileTypes.valueOf(type);
            if (size < 0) throw new FileExceptions(size);
            else {
                switch (fileType) {
                    case AUDIO:
                        newFile = new AudioFile();
                        break;
                    case PICTURE:
                        newFile = new PictureFile();
                        break;
                    case TEXT:
                        newFile = new TextFile();
                        break;
                    default:
                        newFile = new File() {
                            @Override
                            public void setParametres(String name, String extension, double size) {
                                super.setParametres(name, extension, size);
                            }
                        };
                        break;
                }
                newFile.setParametres(name, extension, size);
                files.add(newFile);
                System.out.println("File " + newFile.getName() + "." + newFile.getExtension() + " was created");
            }
        }
        catch (IllegalArgumentException fileType){
            System.out.println("[Error: incorrect variable]; correct are AUDIO, PICTURE or TEXT");
        }
        catch (FileExceptions ex){
            System.out.println("[Error]: Size of file should be larger than 0");
        }
    }

}
