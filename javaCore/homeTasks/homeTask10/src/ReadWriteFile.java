import java.io.*;

public class ReadWriteFile {

    public void write(final String filename, final String someText, final String key) {
        CipherVigenere cipher = new CipherVigenere();
        cipher.setData(someText, key);
        cipher.encript();
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(cipher);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void read(final String filename) {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            CipherVigenere cipher = (CipherVigenere) in.readObject();
            System.out.println(cipher.decript());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
