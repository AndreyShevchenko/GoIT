import java.io.*;
import java.util.ArrayList;

public class ReadWriteFile {

    public boolean write(final String filename, final boolean isWriteToEnd, final String someText, final String key) {
        CipherVigenere cipher = new CipherVigenere();
        cipher.encrypt(someText, key, true);
        ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(byteArrayStream)) {
            out.writeObject(cipher);
            try (FileOutputStream file = new FileOutputStream(filename, isWriteToEnd)) {
                file.write(byteArrayStream.size());
                file.write(byteArrayStream.toByteArray());
            }
            return true;
        } catch (FileNotFoundException ex) {
            System.out.println("[Error:] Syntax error in the file name");
        } catch (NullPointerException ex) {
            System.out.println("[Error:] Missing file name; current value is null");
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<String> read(final String filename) {
        ArrayList<String> result = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(filename)) {
            {
                while (file.available() > 0) {
                    int size = file.read();
                    byte[] byteArrayStream = new byte[size];
                    file.read(byteArrayStream);
                    ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(byteArrayStream));
                    CipherVigenere cipher = (CipherVigenere) in.readObject();
                    result.add(cipher.decrypt(cipher.getEncodedText(), cipher.getWordKey()));
                }
                return result;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("[Error:] Syntax error in the file name");
        } catch (NullPointerException ex) {
            System.out.println("[Error:] Missing file name; current value is null");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
