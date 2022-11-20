package com.example.csc221_assignment3;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Assignment3 extends Application
{

    @Override
    public void start(Stage stage)
    {
        stage.setTitle("CSC 221 Assignment 3 Output");

        double width = 900;     //Height of canvas
        double height = 650;    //Width of canvas

        //radius of circle
        double radius = 200;

        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        GraphicsContext GC = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        stage.show();

        //Point declaration
        MyPoint center = new MyPoint(width / 2, height / 2, MyColor.BLACK);

        //Histogram declaration
        HistogramAlphabet histogram = new HistogramAlphabet(s);

        //Pie chart declaration
        HistogramAlphabet.MyPieChart PieChart = histogram.new MyPieChart(4, center, radius, 60);

        //Draw
        PieChart.draw(GC);
    }

    /****OPEN/READ/CLOSE FILE****/
    static String file = "/Users/zuhayer/Desktop/CSC 22100/War and Peace.txt";
    static Scanner input;
    static String s = "";

    //Function to open the text file provided
    public static void openFile()
    {
        try
        {
            input = new Scanner(Paths.get(file));
        }
        catch (IOException ioException)
        {
            System.err.println("The File was not found, try again");
        }
    }

    //Function to read the text file
    public static void readFile()
    {
        try
        {
            while (input.hasNext())
            {
                s = s + input.nextLine().replaceAll("[^a-zAZ]","").toLowerCase();
            }

            System.out.println("The Number of Characters are: " + s.length() + "\n");
        }
        catch (NoSuchElementException elementException)
        {
            System.err.println("Invalid input. Terminating process...");
        }
        catch (IllegalStateException stateException)
        {
            System.err.println("There has been an error processing the file. Terminating all processes...");
        }
    }

    //Function that closes the file
    public static void closeFile()
    {
        if (input != null)
        {
            input.close();
        }
    }

    public static void main(String[] args)
    {
        openFile();
        readFile();
        closeFile();
        launch();
    }
}
