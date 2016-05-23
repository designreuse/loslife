package com.asgab.entity;

public class ProgressBar {
  private int value;
  private String barClass;
  private String bgClass;

  private int[] valueArray = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90};
  private String[] barClassArray = {"progress-bar-danger", "progress-bar-danger", "progress-bar-danger", "progress-bar-yellow", "progress-bar-yellow",
      "progress-bar-yellow", "progress-bar-primary", "progress-bar-primary", "progress-bar-primary", "progress-bar-success"};
  private String[] bgClassArray =
      {"bg-red", "bg-red", "bg-red", "bg-yellow", "bg-yellow", "bg-yellow", "bg-light-blue", "bg-light-blue", "bg-light-blue", "bg-green"};

  public ProgressBar() {

  }

  public ProgressBar(int value) {
    setValue(value);
  }

  public void setValue(int value) {
    this.value = value;
    for (int i = valueArray.length - 1; i >= 0; i--) {
      if (value >= valueArray[i]) {
        barClass = barClassArray[i];
        bgClass = bgClassArray[i];
        break;
      }
    }
  }

  public int getValue() {
    return value;
  }

  public String getBarClass() {
    return barClass;
  }

  public String getBgClass() {
    return bgClass;
  }

  public String[] getBarClassArray() {
    return barClassArray;
  }

  public String[] getBgClassArray() {
    return bgClassArray;
  }

  public int[] getValueArray() {
    return valueArray;
  }

}
