package cristian;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataFrame<K> {
    private List<Map<K, DataValue>> rows = new ArrayList<>();

    public void addRow(Map<K, DataValue> row) {
        rows.add(row);
    }

    public void sortByKey(K key) {
        rows.sort(Comparator.comparing(row -> row.get(key)));
    }

    public void printDataFrame() {
        if (rows.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        // Get the headers (keys from the first row)
        Set<K> headers = rows.get(0).keySet();

        // Print the headers
        for (K header : headers) {
            System.out.print(header + "\t");
        }
        System.out.println();

        // Print the rows
        for (Map<K, DataValue> row : rows) {
            for (K header : headers) {
                System.out.print(row.get(header) + "\t");
            }
            System.out.println();
        }
    }
}