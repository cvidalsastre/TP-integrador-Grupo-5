package cristian;

public class DataValue implements Comparable<DataValue> {
    private Object value;

    public DataValue(Integer value) {
        this.value = value;
    }

    public DataValue(Double value) {
        this.value = value;
    }

    public DataValue(String value) {
        if (value == null || value.isEmpty()) {
            this.value = null;
        } else {
            this.value = value;
        }
    }

    public DataValue(Boolean value) {
        this.value = value;
    }

    public boolean isInteger() {
        return value instanceof Integer;
    }

    public boolean isDouble() {
        return value instanceof Double;
    }

    public boolean isString() {
        return value instanceof String;
    }

    public boolean isBoolean() {
        return value instanceof Boolean;
    }

    public Integer asInteger() {
        return (Integer) value;
    }

    public Double asDouble() {
        return (Double) value;
    }

    public String asString() {
        return (String) value;
    }

    public Boolean asBoolean() {
        return (Boolean) value;
    }

    @Override
    public int compareTo(DataValue other) {
        if (this.value == null && other.value == null) {
            return 0;
        }
        if (this.value == null) {
            return -1;
        }
        if (other.value == null) {
            return 1;
        }
        if (this.value instanceof Comparable && other.value instanceof Comparable) {
            return ((Comparable) this.value).compareTo(other.value);
        }
        throw new IllegalArgumentException("Incomparable data types.");
    }

    @Override
    public String toString() {
        return value == null ? "null" : value.toString();
    }
}