package persistence;

import org.json.JSONObject;

// Represents the Writable interface that makes classes (that implement this interface) implement the toJson method
// Citation: code obtained from JsonSerializationDemo
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public interface Writable {
    // EFFECTS: returns a class object as a JSON object (conversion)
    JSONObject toJson();
}
