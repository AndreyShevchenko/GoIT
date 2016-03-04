import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static junitparams.JUnitParamsRunner.$;

@RunWith(JUnitParamsRunner.class)
public class ReadWriteFileTest {

    @Test
    @Parameters
    public void testRWFile(String fileName, boolean isWriteToEnd, String text, String key) throws Exception {
        ReadWriteFile test = new ReadWriteFile();
        test.write(fileName, isWriteToEnd, text, key);
        Assert.assertEquals(text, test.read(fileName).get(0));
    }

    @Test
    @Parameters
    public void testRWFileForNull(String fileName, boolean isWriteToEnd, String text, String key) throws Exception {
        ReadWriteFile test = new ReadWriteFile();
        test.write(fileName, isWriteToEnd, text, key);
        Assert.assertNull(test.read("testfile.txt").get(0));
    }

    @Test(timeout = 3000)
    public void testRWFileForRecordArray() throws Exception {
        String fileName = "testarray.txt";
        ReadWriteFile test = new ReadWriteFile();
        ArrayList<String> array = getArray(test, fileName);
        Assert.assertEquals(array, test.read(fileName));
    }

    @Test
    @Parameters
    public void testRWFileForWrongFileName(String fileName) throws Exception {
        ReadWriteFile test = new ReadWriteFile();
        Assert.assertFalse(test.write(fileName, false, "text", "key"));
        Assert.assertNull(test.read(fileName));
    }

    public Object[] parametersForTestRWFile() {
        return $(
                $("testfile.txt", false, "CipherVigenereTest", "C"),
                $("testfile.txt", true, "CipherVigenereTest", "C"),
                $("t", false, "CipherVigenereTest", "C")
        );
    }

    public Object[] parametersForTestRWFileForNull() {
        return $(
                $("testfile.txt", false, null, "C"),
                $("testfile.txt", false, "CipherVigenereTest", null)
        );
    }

    public Object[] parametersForTestRWFileForWrongFileName() {
        return $(
                $(" \\ / ? : * \" > < |"),
                $(getLongName()),
                $(getNull())
        );
    }

    private String getLongName() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            for (int j = 97; j < 123; j++) {
                result.append((char)j);
            }
        }
        return result.append(".txt").toString();
    }

    private String getNull() {
        return null;
    }

    private void deleteFile(String fileName) {
        File myFile = new File(fileName);
        if (myFile.exists()) {
            myFile.delete();
            try {
                myFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private ArrayList<String> getArray(ReadWriteFile file, String fileName) {
        ArrayList<String> array = new ArrayList<>();
        deleteFile(fileName);
        for (int i = 0; i < 1_000; i++) {
            file.write(fileName, true, "CipherVigenereTest", "C");
            array.add("CipherVigenereTest");
        }
        return array;
    }
}