package persistence;

import model.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads order from JSON data stored in file
// Citation: code obtained from JsonSerializationDemo
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonReader {
    private String orderSource;

    // EFFECTS: Constructs a reader that reads order from JSON data stored in file
    public JsonReader(String orderSource) {
        this.orderSource = orderSource;
    }

    // EFFECTS: Reads order from file and returns it
    // throws IOException if there is an error in reading reading data from file
    public Order readOrder() throws IOException {
        String jsonData = readFile(orderSource);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseOrder(jsonObject);
    }

    // EFFECTS: Reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: Parses order from JSON object and returns it
    private Order parseOrder(JSONObject jsonObject) {
        Order order = new Order();
        addItems(order, jsonObject);
        return order;
    }

    // MODIFIES: order
    // EFFECTS: parses items from JSON object and adds them to order
    private void addItems(Order order, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("orderlist");
        for (Object json: jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(order, nextItem);
        }
    }

    // MODIFIES: order
    // EFFECTS: Parses item from JSON object and adds it to order
    // If the JSON object has a volume key, then it adds a Drink object to the order
    // If the JSON object does not have a volume key, then it adds a Food object to the order
    private void addItem(Order order, JSONObject jsonObject) {
        Item item;
        String name = jsonObject.getString("name");
        double price = jsonObject.getDouble("price");
        int calories = jsonObject.getInt("calories");
        item = new Food(name, price, calories);
        if (jsonObject.has("volume")) {
            int volume = jsonObject.getInt("volume");
            item = new Drink(name, price, calories, volume);
        }
        order.addItemToOrder(item);
    }
}
