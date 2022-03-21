package com.example.demo.domain.utils;

import static org.junit.Assert.*;
import org.junit.Test;
import com.example.demo.utils.Order;

public class OrderTest {

  @Test
  public void 黒明細のみの合計金額を求める() throws Exception {

    int[][] values = {{100, 8}, {200, 5}, {300, 3}};
    int expected = 2700;

    int actual = Order.order(values);
    assertEquals(expected, actual);
  }



  @Test
  public void 赤明細を含む合計金額を求める() throws Exception {

    int[][] values = {{100, 8}, {200, 5}, {300, 3}, {100, -2}};
    int expected = 2500;

    int actual = Order.order(values);
    assertEquals(expected, actual);
  }


}
