/*package Input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class ReadingInput {
    String filePath = "C:\\Users\\Kranthi\\Desktop\\UDEMY\\Input1.txt";
    Map<String, Integer> dataMap = new HashMap<>();

        //try {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            // Split each line into key and value using a delimiter (e.g., a comma)
            String[] parts = line.split(",");

            if (parts.length == 2) {
                String key = parts[0].trim();
                int value = Integer.parseInt(parts[1].trim());

                // Put the key-value pair into the HashMap
                dataMap.put(key, value);
            } else {
                System.err.println("Invalid line: " + line);
            }
        }

        reader.close();
    //} catch (IOException e) {
      //  e.printStackTrace();
    //}

    // Print the HashMap
        for (Map.Entry<String, Integer> entry : dataMap.entrySet()) {
        System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
    }
}

 */
