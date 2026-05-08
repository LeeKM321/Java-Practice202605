package oop.static_final;

public class Calculator {

    String color;
    static double pi = 3.14159265;


    public void printColor(String color) {
        this.color = color;
    }

    public static double calcCircleArea(int radius) {
        return radius * radius * Math.PI;
    }


}
