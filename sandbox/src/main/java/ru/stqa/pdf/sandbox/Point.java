package ru.stqa.pdf.sandbox;

public class Point {

    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p) {
        double side1 = (p.x - this.x) * (p.x - this.x);
        double side2 = (p.y - this.y) * (p.y - this.y);
        return Math.sqrt(side1 + side2);
    }
}
