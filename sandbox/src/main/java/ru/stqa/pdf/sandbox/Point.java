package ru.stqa.pdf.sandbox;

public class Point {

    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(double x, double y) {
        double side1 = (x - this.x) * (x - this.x);
        double side2 = (y - this.y) * (y - this.y);
        return Math.sqrt(side1 + side2);
    }
}
