package cristian;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class SimpleCSVReader implements CSVREADER<String> {

    @Override
    public DataFrame<String> readCSV(File file) throws IOException {
        DataFrame<String> df = new DataFrame<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            List<String> headers = new ArrayList<>();

            // Read the header (first line)
            if ((line = br.readLine()) != null) {
                headers = Arrays.asList(line.split(","));
            }

         while ((line = br.readLine()) != null) {
    // Skip empty rows
    if (line.trim().isEmpty()) {
        continue;
    }

    String[] values = line.split(",");
    Map<String, DataValue> row = new HashMap<>();

    for (int i = 0; i < headers.size(); i++) {
        String value = i < values.length ? values[i] : ""; // Handle missing columns
        // Determine the type and convert
        if (value.equalsIgnoreCase("NA") || value.isEmpty()) {
            row.put(headers.get(i), new DataValue((String) null)); // Treat as null
        } else if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
            row.put(headers.get(i), new DataValue(Boolean.parseBoolean(value)));
        } else if (value.matches("-?\\d+")) {
            row.put(headers.get(i), new DataValue(Integer.parseInt(value)));
        } else if (value.matches("-?\\d+\\.\\d+")) {
            row.put(headers.get(i), new DataValue(Double.parseDouble(value)));
        } else {
            row.put(headers.get(i), new DataValue(value));
        }
    }

                df.addRow(row);
            }
        }

        return df;
    }
}