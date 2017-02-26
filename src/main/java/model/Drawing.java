
package ca.andrewmcburney.cs349.a2;

import java.awt.Color;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Drawing {
    public Drawing(final String name_) {
        currentColour = Color.BLACK;
        name = name_;
        strokes = new ArrayList<Stroke>();
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

    public void addStroke(Coord point) {
        Stroke stroke = new Stroke(numStrokes() + 1, currentColour, point);
        strokes.add(stroke);
    }

    public ArrayList<Stroke> getStrokes() {
        return strokes;
    }

    public int numStrokes() {
        return strokes.size();
    }

    public void setStrokeWidth(int newStrokeWidth) {
        strokeWidth = newStrokeWidth;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    /*--------------------------------------------------------------------*
     * Data
     *--------------------------------------------------------------------*/

    @JsonProperty("name")
    private final String name;

    @JsonProperty("strokeWidth")
    private int strokeWidth;

    @JsonProperty("strokes")
    private ArrayList<Stroke> strokes;

    @JsonProperty("currentColour")
    private Color currentColour;
}
