package com.example.csc221_assignment3;

import javafx.scene.paint.Color;

public enum MyColor
{
    BLACK(0,0,0,1),
    VERYSOFTCYAN(74,92,93,1),
    GRAY(128,128,128,1),
    PINK(255,192,203,1),
    MAGENTA(255,0,255, 1),
    CYAN(0,255,255, 1),
    LIME(128,255,0,1),
    FUCHSIA(255,0,128,1),
    MAROON(128,0,0,1),
    BEIGE(245,245,220,1),
    SIENNA(160,82,45,1),
    HONEYDEW(240,255,240,1),
    INDIGO(75,0,130,1),
    AQUAMARINE(127,255,212,1),
    YELLOW(255,255,0,1),
    RED(255,0,0,1),
    ROSYBROWN(188,143,143,1),
    LAVENDER(230,230,250,1),
    WHITE(100, 100, 100, 1);

    private int r; //red component
    private int g; //green component
    private int b; //blue component
    private int a; //opacity component
    private int argb; //packed color [opacity|red|green|blue]

    //Constructor
    MyColor(int r, int g, int b, int a)
    {
        setR(r);
        setG(g);
        setB(b);
        setA(a);
        setARGB(a, r, g, b);
    }

    //Set Methods
    public void setR(int r)
    {
        if(r >= 0 && r <= 255)
        {
            this.r = r;
        }
    }

    public void setG(int g)
    {
        if(g >= 0 && g <= 255)
        {
            this.g = g;
        }
    }

    public void setB(int b)
    {
        if(b >= 0 && b <= 255)
        {
            this.b = b;
        }
    }

    public void setA(int a)
    {
        if(a >= 0 && a <= 255)
        {
            this.a = a;
        }
    }

    public void setARGB(int r, int g, int b, int a)
    {
        this.argb = (a << 24) & 0xFF000000 |
                (r << 16) & 0x00FF0000 |
                (g << 8) & 0x0000FF00 |
                b;
    }

    //Get Methods
    public int getR() { return r; }
    public int getG() { return g; }
    public int getB() { return b; }
    public int getA() { return a; }
    public int getARGB() { return argb; }

    public String getHexColor()
    {
        return "#" + Integer.toHexString(argb).toUpperCase();
    }

    public Color getColor() { return Color.rgb(r, g, b, a);}

    //Print Method
    public void print()
    {
        System.out.println(this + "(" + this.r + ", " + this.g + ", " + this.b + ", " + this.a + ")" +
                " Hexadecimal Code: " + this.getHexColor());
    }
}
