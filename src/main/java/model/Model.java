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
        drawing = new Drawing("temp");
    }

    /*--------------------------------------------------------------------*
     * Drawing
     *--------------------------------------------------------------------*/

    public Drawing getDrawing() {
        return drawing;
    }

    // Modifies model's internal state and notifies observers of changes
    public void updateDrawing(DrawingLambda lambda) {
        isSaved = false;
        lambda.draw(drawing);

        setChanged();
        notifyObservers();
    }

    /*--------------------------------------------------------------------*
     * Images
     *--------------------------------------------------------------------*/

    public void loadImage(final String fileName) {
        System.out.println(fileName);
        try {
            drawing = jMap.readValue(new File("files/" + fileName),
                                     Drawing.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveImage(final String fileName) {
        System.out.println("Save image.");
        try {
            setChanged();
            notifyObservers();
            jMap.writeValue(new File("files/" + fileName + ".json"), drawing);
            isSaved = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*--------------------------------------------------------------------*
     * Data
     *--------------------------------------------------------------------*/

    private ObjectMapper jMap = new ObjectMapper();
    private Drawing drawing;
    private boolean isSaved = true;
}
