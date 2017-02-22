
package ca.andrewmcburney.cs349.a2;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Drawing {
    public Drawing(final String name, Set<Stroke> strokes) {
        this.name = name;
        this.strokes = strokes;
    }

    public void saveDrawing(Set<Stroke> newStrokes) {this.strokes = newStrokes;}

    // Data
    @JsonProperty("name")
    private final String name;
    @JsonProperty("strokes")
    private Set<Stroke> strokes;
}
