package cristian;
import java.util.Map;
import java.util.HashMap;

public class Row<K> {
    private Map<K, DataValue> columns;

    public Row() {
        this.columns = new HashMap<>();
    }

    public void addColumn(K key, DataValue value) {
        columns.put(key, value);
    }

    public DataValue getColumn(K key) {
        return columns.get(key);
    }

    public Map<K, DataValue> getColumns() {
        return columns;
    }
    
}