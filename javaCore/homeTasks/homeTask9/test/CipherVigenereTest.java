import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class CipherVigenereTest {
    private String original;
    private String key;
    private String result;

    public CipherVigenereTest(String original, String key, String result) {
        this.original = original;
        this.key = key;
        this.result = result;
    }

    @Test
    public void testEncript() throws Exception {
        CipherVigenere test = new CipherVigenere();
        test.setData(original, key);
        Assert.assertEquals(0, result.compareTo(test.encrypt()));
    }

    @Test
    public void testDecript() throws Exception {
        CipherVigenere test = new CipherVigenere();
        test.setData(result, key);
        Assert.assertEquals(0, original.compareTo(test.decrypt()));
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"CipherVigenereTest", "C", "EkrjgtXkigpgtgVguv"},
                {"", "test", ""},
                {"test", "", "test"},
                {"Hello World!", "Go IT", "Nsteu Ehxzl!"},
                {"Hello World!", "Go IT", "Nsteu Ehxzl!"},
                {"Hello World!", "Go IT", "Nsteu Ehxzl!"},
                {"AbCdEf", "Z Y X W V U", "ZzZzZz"}});
    }
}