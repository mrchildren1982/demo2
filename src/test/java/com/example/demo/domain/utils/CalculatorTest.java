package com.example.demo.domain.utils;

import static org.junit.Assert.*;
import org.junit.Test;
import com.example.demo.utils.Calculator;

public class CalculatorTest {

  @Test
  public void 単価100数量5でcalcメソッドを呼び出しで500を返す() throws Exception {

    int price = 100;
    int count = 0;
    int expected = 500;

    int actual = Calculator.calc(price, count);

    assertEquals(expected, actual);
  }


}
