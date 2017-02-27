/**
 * Drawing.java
 *
 * @author: Andrew McBurney
 */

package ca.andrewmcburney.cs349.a2;

import java.awt.Color;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Drawing {
    public Drawing(final String name_) {
        currentColour = Color.BLACK;
        name = name_;
        strokeWidth = 15;
        numStrokes = 0;
        leftCoord = new ArrayList<Coord>();
        rightCoord = new ArrayList<Coord>();
    }

    public void clear() {
        currentColour = Color.BLACK;
        strokeWidth = 15;
        numStrokes = 0;
        leftCoord.clear();
        rightCoord.clear();
    }

    /*--------------------------------------------------------------------*
     * Colors
     *--------------------------------------------------------------------*/

    public void setCurrentColor(Color newColor) {
        currentColour = newColor;
    }

    public Color getCurrentColor() {
        return currentColour;
    }

    /*--------------------------------------------------------------------*
     * Strokes
     *--------------------------------------------------------------------*/

    public ArrayList<Coord> getLeftCoords() {
        return leftCoord;
    }

    public ArrayList<Coord> getRightCoords() {
        return rightCoord;
    }

    public void clearRightCoords() {
        rightCoord.clear();
    }

    public int numStrokes() {
        return numStrokes;
    }

    public void setStrokeWidth(int newStrokeWidth) {
        strokeWidth = newStrokeWidth;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void addCoord(int x, int y, boolean head, boolean tail) {
        if (head) { numStrokes ++; }

        Coord point = new Coord(x, y, head, tail, currentColour, strokeWidth);
        leftCoord.add(point);
        rightCoord.clear();
    }

    /*--------------------------------------------------------------------*
     * Data
     *--------------------------------------------------------------------*/

    @JsonProperty("name")
    private final String name;

    @JsonProperty("numStrokes")
    private int numStrokes;

    @JsonProperty("strokeWidth")
    private int strokeWidth;

    @JsonProperty("leftCoord")
    private ArrayList<Coord> leftCoord;

    @JsonProperty("rightCoord")
    private ArrayList<Coord> rightCoord;

    @JsonProperty("currentColour")
    private Color currentColour;
}
