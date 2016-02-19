import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;


/**
 * Created by JAvengers on 01.02.2016.
 */
@RunWith(Parameterized.class)
public class LogicTest {
    private String expression;
    private BigDecimal result;

    public LogicTest(String expression, String result) {
        this.expression = expression;
        this.result = new BigDecimal(result);
    }

    @Test
    public void testHandlingParentheses() throws Exception {
        Verification ver = new Verification(expression);
        Logic testObject = new Logic(ver.finalVerification());
        Assert.assertEquals(0, result.compareTo(testObject.handlingParentheses()));
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"2+3", "5"},
                {"26/10-0.6", "2"},
                {"5*2E1=", "100"},
                {"26+(15/6((3+7)(4-2)/5))","36"},
                {"2.5E7-0.5E7sd","2E7"},
                {"5E-1(5+5)", "5"},
                {"-3-3-3(-3-3-3)", "21"},
                {"5E-1((1+2)+(1-2)/(1*2)-(1/2)(-0.5E-1+0.05))", "125E-2"}
        });
    }






}