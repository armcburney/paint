/**
 * Coord.java
 *
 * @author: Andrew McBurney
 */

package ca.andrewmcburney.cs349.a2;

import java.awt.Color;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Coord {
    public Coord(final int x_, final int y_, final boolean head_,
                 final boolean tail_, final Color color_, final int width_) {
        color = color_;
        width = width_;
        head = head_;
        tail = tail_;
        x = x_;
        y = y_;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public boolean isHead() {
        return head;
    }

    public boolean isTail() {
        return tail;
    }

    public int getWidth() {
        return width;
    }

    @JsonProperty("x")
    private final int x;

    @JsonProperty("y")
    private final int y;

    @JsonProperty("color")
    private final Color color;

    @JsonProperty("head")
    private final boolean head;

    @JsonProperty("tail")
    private final boolean tail;

    @JsonProperty("width")
    private final int width;
}
