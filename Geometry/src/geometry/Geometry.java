package geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

abstract class GeoObj implements Comparable<GeoObj> {

    String color;

    GeoObj(String color) {
        this.color = color;
    }

    abstract double getSurface();

    @Override
    public int compareTo(GeoObj other) {
        System.out.printf("compareTo called (%s vs %s)\n", this.color, other.color);
        return color.compareTo(other.color);
    }

    public final static Comparator<GeoObj> surfaceComparator = new SurfaceComparator();
} // end of GeoObj

class SurfaceComparator implements Comparator<GeoObj> {
    @Override
    public int compare(GeoObj o1, GeoObj o2) {
        if (o1.getSurface() == o2.getSurface()) {
            return 0;
        }
        if (o1.getSurface() > o2.getSurface()) {
            return 1;
        } else {
            return -1;
        }
    }

};

class Rectangle extends GeoObj {

    double width, height;

    Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    double getSurface() {
        return width * height;
    }

}

class Circle extends GeoObj {

    double radius;

    Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    double getSurface() {
        return Math.PI * radius * radius;
    }
}

public class Geometry {

    public static void main(String[] args) {
        ArrayList<GeoObj> list = new ArrayList<>();
        list.add(new Rectangle("blue", 21, 3.2));
        list.add(new Circle("white", 2.7));
        list.add(new Rectangle("green", 12, 2));

        System.out.println("======= Original order:");
        for (GeoObj go : list) {
            System.out.printf("%s object has surface %.2f\n", go.color, go.getSurface());
        }

        Collections.sort(list);
        System.out.println("======= Natural order (by color):");
        for (GeoObj go : list) {
            System.out.printf("%s object has surface %.2f\n", go.color, go.getSurface());
        }
        
        Collections.sort(list, GeoObj.surfaceComparator);
        System.out.println("======= Special order (by surface):");
        for (GeoObj go : list) {
            System.out.printf("%s object has surface %.2f\n", go.color, go.getSurface());
        }


    }

}

interface DaysAreHere {

    int currentDay = 0;

    int getDay();
}
