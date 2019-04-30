package dataproviderTest.calculator;

import calculator.Calculator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalculatorTestCase {

    @Test( dataProvider="calcultorTest")
    public void instanceDbProvider(int firstNumber, int secondNumber) {
        Calculator.sum( firstNumber, secondNumber );
    }

    @DataProvider(name = "calcultorTest")
    public Object[][] getData() {
        return new Object[][]{{5, 6}, {1, 2}};
    }

}
