/**
 * Drawing.java
 *
 * @author: Andrew McBurney
 */

package ca.andrewmcburney.cs349.a2;

import java.io.*;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Drawing implements java.io.Serializable  {
    public Drawing(final String name_) {
        playForward = true;
        currentColour = Color.BLACK;
        name = name_;
        strokeWidth = 15;
        numStrokes = 0;
        leftCoord = new ArrayList<Coord>();
        rightCoord = new ArrayList<Coord>();
    }

    public void clear() {
        playForward = true;
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
        if (leftCoord.size() == 0) {
            numStrokes = 1;
        } else if (head) {
            numStrokes = leftCoord.get(leftCoord.size() - 1).getNum() + 1;
        }

        Coord point = new Coord(x, y, numStrokes, head, tail, currentColour, strokeWidth);
        leftCoord.add(point);
        rightCoord.clear();
    }

    public void sliderStart() {
        List<Coord> temp = new ArrayList<Coord>();
        temp.addAll(leftCoord);
        temp.addAll(rightCoord);

        rightCoord = new ArrayList<Coord>(temp);
        leftCoord  = new ArrayList<Coord>();
    }

    public void sliderEnd() {
        List<Coord> temp = new ArrayList<Coord>();
        temp.addAll(leftCoord);
        temp.addAll(rightCoord);

        leftCoord  = new ArrayList<Coord>(temp);
        rightCoord = new ArrayList<Coord>();
    }

    public void partition(double value) {
        List<Coord> temp = new ArrayList<Coord>();
        temp.addAll(leftCoord);
        temp.addAll(rightCoord);

        int splitIndex = (int) Math.floor(temp.size() * value);

        // Partition list left of the slider
        leftCoord  = new ArrayList<Coord>(temp.subList(0, splitIndex));

        // Partition list right of the slider
        if (temp.size() != 0) {
            rightCoord = new ArrayList<Coord>(temp.subList(splitIndex, temp.size()));
        } else {
            rightCoord = new ArrayList<Coord>();
        }
    }

    /*--------------------------------------------------------------------*
     * Play direction
     *--------------------------------------------------------------------*/

    public boolean isPlayForward() {
        return playForward;
    }

    public void play(boolean playDir) {
        playForward = playDir;
    }

    /*--------------------------------------------------------------------*
     * Data
     *--------------------------------------------------------------------*/

    @JsonProperty("playDirection")
    private boolean playForward;

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
