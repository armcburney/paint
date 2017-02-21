
package ca.andrewmcburney.cs349.a2;

import java.util.Set;

public class Drawing {
    public Drawing(final String name, Set<Stroke> strokes) {
        this.name = name;
        this.strokes = strokes;
    }

    public void saveDrawing(Set<Stroke> newStrokes) {this.strokes = newStrokes;}

    // Data
    private final String name;
    private Set<Stroke> strokes;
}
