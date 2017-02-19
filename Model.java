/**
 * Main.java
 * @author: Andrew McBurney
 */

// Java IO
import java.io.File;
import java.io.IOException;

// Java util
import java.util.Set;
import java.util.Observable;

// Serializer
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.ObjectCodec;

public class Model extends Observable {
    public Model() { setChanged(); }

    public void loadImage(String name) {
        try {
            this.drawing = jMap.readValue(new File("test.json"), Drawing.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveImage() {
        try {
            this.jMap.writeValue(new File("test.json"), this.drawing);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ObjectMapper jMap = new ObjectMapper();
    private Drawing drawing;
    private Set<Stroke> strokes;
}
