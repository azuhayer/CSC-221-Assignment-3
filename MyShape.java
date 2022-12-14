package com.example.csc221_assignment3;

import javafx.scene.canvas.GraphicsContext;

public abstract class MyShape implements MyShapeInterface
{
    MyPoint p; // point p(x,y)
    MyColor color; //color of MyShape object

    //Default Constructor
    MyShape()
    {
        this.p = new MyPoint();
        this.color = MyColor.BLACK;
    }

    //Constructor with parameters
    MyShape(MyPoint p, MyColor color)
    {
        setPoint(p);
        setColor(color);
    }

    //Set Methods
    public void setPoint(MyPoint p)
    {
        this.p = p;
    }
    public void setColor(MyColor color) { this.color = color; }

    //Get Methods
    public MyPoint getPoint() { return p; }
    public MyColor getColor() { return color; }

    public double getX() { return p.getX(); }
    public double getY() { return p.getY(); }


    public abstract double area();
    public abstract double perimeter();

    @Override
    public String toString()
    {
        return "This is an object of the MyShape class";
    }

    //Draw Method
    public void draw(GraphicsContext GC)
    {
        GC.setFill(color.getColor());
        GC.fillRect(0, 0, GC.getCanvas().getWidth(), GC.getCanvas().getHeight());
    }
}
