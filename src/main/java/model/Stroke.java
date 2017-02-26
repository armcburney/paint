package ca.andrewmcburney.cs349.a2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Stroke {
    public Stroke(int id_, Color color_, int width_, final Coord startingPoint) {
        id = id_;
        color = color_;
        width = width_;
        coordinates = new ArrayList<Coord>() {{add(startingPoint);}};
    }

    public void addCoordinate(final Coord newPoint) {
        coordinates.add(newPoint);
    }

    // Class accessors for id, color, and coordinates
    public int getId() { return id; }
    public Color getColor() { return color; }
    public int getWidth() { return width; }
    public ArrayList<Coord> getCoordinates() { return coordinates; }

    // Data
    @JsonProperty("id")
    private final int id;
    @JsonProperty("color")
    private final Color color;
    @JsonProperty("width")
    private final int width;
    @JsonProperty("coordinates")
    private ArrayList<Coord> coordinates;
}
