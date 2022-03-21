package com.example.demo.domain.utils;

import static org.junit.Assert.*;
import org.junit.Test;
import com.example.demo.utils.Calculator;

public class CalculatorTest {

  @Test
  public void 単価100数量5でcalcメソッドを呼び出しで500を返す() throws Exception {

    //given:テストデータを定義
    int price = 100;
    int count = 5;
    int expected = 500;

    //when:テスト対象のメソッドを実行する
    int actual = Calculator.calc(price, count);

    //then
    assertEquals(expected, actual);
  }


}
