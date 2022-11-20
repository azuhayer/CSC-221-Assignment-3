package com.example.csc221_assignment3;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class Slice
{
    private MyPoint center;
    private double startAngle;
    private double radius;
    private double angle;
    private MyColor color;

    //constructor
    Slice(MyPoint center, double radius, double startAngle, double angle, MyColor color)
    {
        this.center = center;
        this.radius = radius;
        this.startAngle = startAngle;
        this.angle = angle;
        this.color = color;
    }

    //Get Functions
    public MyPoint getCenter() { return center; }
    public double getRadius() { return radius; }
    public double getStartAngle() { return startAngle; }
    public double getAngle() { return angle; }

    //Area Function
    public double area() { return (Math.pow(radius, 2) * Math.toRadians(angle)) / 2; }

    //Perimeter function
    public double perimeter() { return radius * Math.toRadians(angle); }

    //String Function
    public String toString()
    {
        return "This is an object of the Slice class: The center point is " +
                getCenter() + ". The radius is "
                + getRadius() + ". The angle starts at " + getStartAngle() +
                " degrees and it opens for "
                + getAngle() + " degrees.";
    }

    //Draw function
    public void draw(GraphicsContext gc)
    {
        gc.setFill(color.getColor());
        gc.fillArc(getCenter().getX() - radius, getCenter().getY() - radius, radius * 2, radius *
                2, startAngle, angle, ArcType.ROUND);
    }
}
