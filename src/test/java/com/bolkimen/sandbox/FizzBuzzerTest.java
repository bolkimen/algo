package com.bolkimen.sandbox;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class FizzBuzzerTest {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 0, 0, 0 }, { 1, 1, 2 }
        });
    }

    private final Calculator calculator;
    private final int first;
    private final int second;
    private final int expectedSum;

    public FizzBuzzerTest(int first,
                                  int second,
                                  int expectedSum) {

        this.calculator = new Calculator();
        this.first = first;
        this.second = second;
        this.expectedSum = expectedSum;
    }

    @Test
    public void shouldReturnCorrectSum() {
        int actualSum = calculator.sum(first, second);
        assertEquals(expectedSum, actualSum);
    }
}