package com.example.csc221_assignment3;

import javafx.scene.canvas.GraphicsContext;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.Random;

public class HistogramAlphabet
{
    Map<Character, Integer> frequency = new HashMap<Character, Integer>();
    Map<Character, Double> probability = new HashMap<Character, Double>();

    //Constructor with parameter
    HistogramAlphabet(String text)
    {
        String w = text.replaceAll("[^a-zA-Z]","");
        for (int i = 0; i < w.length(); i++)
        {
            Character key = w.charAt(i);
            incrementFrequency(frequency, key);
        }
    }

    //Get functions
    public Map<Character, Integer> getFrequency() { return frequency; }

    public Integer getCumulativeFrequency()
    {
        return frequency.values().stream().reduce(0, Integer::sum);
    }

    public Map<Character, Double> getProbability()
    {
        double inverseCumulativeFrequency = 1.0 / getCumulativeFrequency();
        for (Character Key: frequency.keySet())
        {
            probability.put(Key, (double) frequency.get(Key) * inverseCumulativeFrequency);
        }
        return probability.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                        .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

    public Double getSumOfProbability()
    {
        return probability.values().stream().reduce(0.0, Double::sum);
    }

    //Sort Functions
    public Map<Character, Integer> sortUpFrequency()
    {
        return frequency.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue, (e1,e2) ->
                    e2, LinkedHashMap::new));
    }

    public Map<Character, Integer> sortDownFrequency()
    {
        return frequency.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue, (e1,e2) -> e2, LinkedHashMap::new));
    }

    //Increments Frequency Function
    public static<L> void incrementFrequency(Map<L, Integer> m, L Key)
    {
        m.putIfAbsent(Key, 0);
        m.put(Key, m.get(Key) + 1);
    }

    //String Function
    @Override
    public String toString()
    {
        String output = "The Frequency of Characters is:\n"; for (Character K : frequency.keySet())
        {
            output = output + K + ": " + frequency.get(K) + "\n";
        }
        return output;
    }


    /*****BEGINNING OF MyPieChart INNER CLASS*****/
    public class MyPieChart
    {
        Map<Character, Slice> slice = new HashMap<Character, Slice>();
        int n;
        MyPoint center;
        double radius;
        double rotateAngle;

        //Constructor with parameters
        MyPieChart(int n, MyPoint center, double radius, double rotateAngle)
        {
            this.n = n;
            this.center = center;
            this.radius = radius;
            this.rotateAngle = rotateAngle;
            probability = getProbability();
            slice = getMyPieChart();
        }

        //Get Function
        public Map<Character, Slice> getMyPieChart()
        {
            MyColor[] colors = MyColor.values();
            Random rand = new Random();
            int colorLength = colors.length;
            double startAngle = rotateAngle;

            for (Character Key : probability.keySet())
            {
                double angle = 360 * probability.get(Key);
                slice.put(Key, new Slice(center, radius, startAngle, angle, colors[rand.nextInt(colorLength)]));
                startAngle = startAngle + angle;
            }
            return slice;
        }

        //Draw Function
        public void draw(GraphicsContext gc)
        {
            Map<Character, Integer> sortedFrequency = sortDownFrequency();
            Object key = 0;
            double sum = 0;
            Slice other = new Slice(center, radius, 0, 360, MyColor.GRAY);
            other.draw(gc);

            for (int i = 0; i < n; i++)
            {
                key = sortedFrequency.keySet().toArray()[i]; slice.get(key).draw(gc);
                String text = (Character) key + ", " + probability.get(key);
                System.out.println("Probabilities, " + text);

                double angle = slice.get(key).getStartAngle();
                double x1 = (radius + 200) * Math.cos(Math.toRadians(angle + 20));
                double y1 = (radius + 50) * Math.sin(Math.toRadians(angle + 20));
                double centerX1 = center.getX() + x1;
                double centerY1 = center.getY() - y1;

                gc.setFill(MyColor.BLACK.getColor());
                gc.fillText(text, centerX1, centerY1);
                sum = sum + (double) probability.get(key);
            }

            double angle2 = slice.get(key).getStartAngle();
            double x2 = (radius - 300) * Math.cos(Math.toRadians(angle2 + 40));
            double y2 = radius * Math.sin(Math.toRadians(angle2 + 40));
            double centerX2 = center.getX() + x2;
            double centerY2 = center.getY() - y2;

            gc.setFill(MyColor.BLACK.getColor());
            gc.fillText("All other letters, " + (1 - sum), centerX2, centerY2);
            System.out.println("All other letters, " + (1 - sum));
        }
    }
}
