package json;

import com.google.gson.Gson;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

public class TestJSON2YAML {

    // Test method to convert JSON to YAML
    @Test
    public void testJsonToYamlConversion() {
        String jsonInput = "{\"name\":\"John\", \"age\":30}";
        Gson gson = new Gson();
        Yaml yaml = new Yaml();

        // Convert JSON to a Map
        Map<String, Object> map = gson.fromJson(jsonInput, Map.class);

        // Convert Map to YAML
        String yamlOutput = yaml.dump(map);

        // Assert that YAML output is not null or empty
        assertNotNull("YAML output should not be null", yamlOutput);
        assertFalse(yamlOutput.isBlank(), "YAML output should not be empty");
    }

    // Test method to convert YAML to JSON
    @Test
    public void testYamlToJsonConversion() {
        String yamlInput = "name: John\nage: 30";
        Gson gson = new Gson();
        Yaml yaml = new Yaml();

        // Convert YAML to a Map
        Map<String, Object> map = yaml.load(yamlInput);

        // Convert Map to JSON
        String jsonOutput = gson.toJson(map);

        // Assert that JSON output is not null or empty
        assertNotNull("JSON output should not be null", jsonOutput);
        assertFalse(jsonOutput.isEmpty(), "JSON output should not be empty");
    }

    // Test method to check if YAML output matches expected structure
    @Test
    public void testYamlStructure() {
        String jsonInput = "{\"name\":\"John\", \"age\":30}";
        Gson gson = new Gson();
        Yaml yaml = new Yaml();

        // Convert JSON to a Map
        Map<String, Object> map = gson.fromJson(jsonInput, Map.class);

        // Convert Map to YAML
        String yamlOutput = yaml.dump(map);

        // Expected YAML structure
        String expectedYaml = "name:John\nage:30\n";

        // Assert that the YAML output matches the expected structure
        assertEquals(expectedYaml, yamlOutput, "YAML output should match the expected structure");
    }
}
