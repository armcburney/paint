/**
 * Model.java
 *
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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.ObjectCodec;

// Other
import java.awt.Color;

// Drawing lambda for updating internal state
interface DrawingLambda { void draw(Drawing g); }

public class Model extends Observable {
    public Model() {
        drawing = new Drawing("test");
    }

    /*--------------------------------------------------------------------*
     * Drawing
     *--------------------------------------------------------------------*/

    public Drawing getDrawing() {
        return drawing;
    }

    // Modifies model's internal state and notifies observers of changes
    public void updateDrawing(DrawingLambda lambda) {
        lambda.draw(drawing);

        setChanged();
        notifyObservers();
    }

    /*--------------------------------------------------------------------*
     * Images
     *--------------------------------------------------------------------*/

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

    /*--------------------------------------------------------------------*
     * Data
     *--------------------------------------------------------------------*/

    private ObjectMapper jMap = new ObjectMapper();
    private Drawing drawing;
}
