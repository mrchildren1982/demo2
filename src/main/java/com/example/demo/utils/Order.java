package com.example.demo.utils;

public class Order {

  public static int order(int[][] values) {

    int total = 0;

    try {

      for (int[] value : values) {

        int price = value[0];
        int count= value[1];

        total += Calculator.calc(price, count);
      }

    } catch (Exception e) {

      e.printStackTrace();
    }

    return total;
  }

}
