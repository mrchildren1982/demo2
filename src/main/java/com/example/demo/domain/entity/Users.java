package com.example.demo.domain.entity;

public class Users {

  private int id;

  private String name;

  private int age;

  private String personalColor;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Users() {
    super();
    // TODO 自動生成されたコンストラクター・スタブ
  }

  public String getPersonalColor() {
    return personalColor;
  }

  public void setPersonalColor(String personalColor) {
    this.personalColor = personalColor;
  }

  public Users(int id, String name, int age, String personalColor) {
    super();
    this.id = id;
    this.name = name;
    this.age = age;
    this.personalColor = personalColor;
  }


}
