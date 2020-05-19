package ru.stqa.pdf.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        Point point1 = new Point(1, 1);
        Point point2 = new Point(3, 3);

        System.out.println("Расстояние между точками, высчитанное функцией, равно: " +
                distance(point1, point2));

        System.out.println("Расстояние между точками, высчитанное методом класса, равно: " +
                point1.distance(point2));
    }

    public static double distance(Point p1, Point p2) {
        double side1 = (p2.x - p1.x) * (p2.x - p1.x);
        double side2 = (p2.y - p1.y) * (p2.y - p1.y);
        return Math.sqrt(side1 + side2);
    }
}
