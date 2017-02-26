
package ca.andrewmcburney.cs349.a2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coord {
    public Coord(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @JsonProperty("x")
    private final int x;
    @JsonProperty("y")
    private final int y;
}
