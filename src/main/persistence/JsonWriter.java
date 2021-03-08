package persistence;

import model.*;
import org.json.JSONObject;
import java.io.*;

// Represents the JsonWriter class that writes the Order object into a JSON file
// Citation: code obtained from JsonSerializationDemo
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonWriter {
    //fields
    private static final int TAB = 4;
    private PrintWriter orderWriter;
    private String orderDestination;

    // EFFECTS: Constructs writer to write to destination file
    public JsonWriter(String orderDestination) {
        this.orderDestination = orderDestination;
    }

    // MODIFIES: this
    // EFFECTS: Opens orderWriter, throws FileNotFoundException if destination file is not found
    public void open() throws FileNotFoundException {
        orderWriter = new PrintWriter(new File(orderDestination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Order to file
    public void write(Order order) {
        JSONObject json = order.toJson();
        saveToOrderFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: Closes orderWriter
    public void close() {
        orderWriter.close();
    }

    // MODIFIES: this
    // EFFECTS: Writes string to order file
    private void saveToOrderFile(String json) {
        orderWriter.print(json);
    }
}
