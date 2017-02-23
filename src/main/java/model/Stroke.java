package ca.andrewmcburney.cs349.a2;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Stroke {
    public Stroke(int id, String color, final Coord startingPoint) {
        this.id = id;
        this.color = color;
        this.isDisplayed = true;
        this.coordinates.add(startingPoint);
    }

    public void toggleDisplay() {this.isDisplayed = this.isDisplayed ? false : true;}
    public void addCoordinate(final Coord newPoint) {this.coordinates.add(newPoint);}

    // Class accessors for id, color, and coordinates
    public int getId() { return this.id; }
    public String getColor() { return this.color; }
    public Set<Coord> getCoordinates() { return this.coordinates; }

    // Data
    @JsonProperty("id")
    private final int id;
    @JsonProperty("color")
    private final String color;
    @JsonProperty("isDisplayed")
    private boolean isDisplayed;
    @JsonProperty("coordinates")
    private Set<Coord> coordinates;
}
