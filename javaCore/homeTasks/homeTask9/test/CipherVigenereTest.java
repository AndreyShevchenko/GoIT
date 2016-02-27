import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;

@RunWith(JUnitParamsRunner.class)
public class CipherVigenereTest {

    @Test
    @Parameters
    public void testCipherVigenere(String original, String key, String result) throws Exception {
        CipherVigenere test = new CipherVigenere();
        test.setData(original, key);
        Assert.assertEquals(0, result.compareTo(test.encrypt()));
        Assert.assertEquals(0, original.compareTo(test.decrypt()));
    }

    @Test
    public void testCipherVigenereForNull() throws Exception {
        CipherVigenere test = new CipherVigenere();
        test.setData(null, null);
        Assert.assertNull(test.encrypt());
        Assert.assertNull(test.decrypt());
    }

    public Object[] parametersForTestCipherVigenere() {
        return $(
                $("CipherVigenereTest", "C", "EkrjgtXkigpgtgVguv"),
                $("CipherVigenereTest", "1/.^   *~C56+=", "EkrjgtXkigpgtgVguv"),
                $("", "test", ""),
                $("test", "", "test"),
                $("1/.^   *~C56+=", "CipherVigenereTest", "1/.^   *~G56+="),
                $("Hello World!", "Go IT", "Nsteu Ehxzl!"),
                $("", "", ""),
                $("Hello", null, "Hello"),
                $("AbCdEf", "Z Y X W V U", "ZzZzZz")
        );
    }
}