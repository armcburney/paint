/**
 * Model.java
 *
 * @author: Andrew McBurney
 */

package ca.andrewmcburney.cs349.a2;

// Java IO
import java.io.*;
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

    private void notifyViews(String value) {
        Object object = value;
        setChanged();
        notifyObservers(object);
    }

    /*--------------------------------------------------------------------*
     * Drawing
     *--------------------------------------------------------------------*/

    public Drawing getDrawing() {
        return drawing;
    }

    public void clearDrawing() {
        isSaved = true;
        drawing.clear();
        notifyViews("");
    }

    // Modifies model's internal state and notifies observers of changes
    public void updateDrawing(DrawingLambda lambda, String value) {
        isSaved = false;
        lambda.draw(drawing);
        notifyViews(value);
    }

    /*--------------------------------------------------------------------*
     * Images
     *--------------------------------------------------------------------*/

    public void loadImage(final String fileName) {
        System.out.println(fileName);

        try {
            fileInput = new FileInputStream(fileName);
            objectInput = new ObjectInputStream(fileInput);
            drawing = (Drawing) objectInput.readObject();
            objectInput.close();
            fileInput.close();
        } catch(IOException i) {
            i.printStackTrace();
            return;
        }

        notifyViews("loaded");
        /*
        try {
            drawing = jMap.readValue(new File("files/" + fileName), Drawing.class);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void saveImage(final String fileName) {
        try {
            fileOutput = new FileOutputStream(fileName + ".paint");
            objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(drawing);
            objectOutput.close();
            fileOutput.close();
            System.out.printf("Serialized Drawing.");
        } catch(IOException i) {
            i.printStackTrace();
        }

        System.out.println("Save image.");
        /*
        try {
            notifyViews("");
            jMap.writeValue(new File("files/" + fileName + ".json"), drawing);
            isSaved = true;
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    /*--------------------------------------------------------------------*
     * Viewport
     *--------------------------------------------------------------------*/

    public void setViewSmall(boolean viewValue) {
        isViewSmall = viewValue;
        notifyViews("");
    }

    public boolean isViewSmall() {
        return isViewSmall;
    }

    /*--------------------------------------------------------------------*
     * Data
     *--------------------------------------------------------------------*/

    // Jackson
    private ObjectMapper jMap = new ObjectMapper();

    // Java Serializer
    private FileInputStream fileInput;
    private ObjectInputStream objectInput;
    private FileOutputStream fileOutput;
    private ObjectOutputStream objectOutput;

    // Other data
    private Drawing drawing;
    private boolean isSaved = true;
    private boolean isViewSmall = false;
}
