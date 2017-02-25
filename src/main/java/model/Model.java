/**
 * Main.java
 * @author: Andrew McBurney
 */

package ca.andrewmcburney.cs349.a2;

// Java IO
import java.io.File;
import java.io.IOException;

// Java util
import java.util.*;

// Serializer
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.ObjectCodec;

public class Model extends Observable {
    public Model() {
        setChanged();
        this.drawing = new Drawing("test", new HashSet<Stroke>());
    }

    public void loadImage(String name) {
        try {
            this.drawing = jMap.readValue(new File("files/test.json"), Drawing.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveImage() {
        System.out.println("Save image.");
        try {
            setChanged();
            notifyObservers();
            this.jMap.writeValue(new File("files/test.json"), this.drawing);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ObjectMapper jMap = new ObjectMapper();
    private Drawing drawing;
    private Set<Stroke> strokes;
}
