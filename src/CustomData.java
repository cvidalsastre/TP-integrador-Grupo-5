import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para representar datos personalizados (RF 3.1).
 */
class CustomData {
    private String stringValue;
    private int intValue;

    public CustomData(String stringValue, int intValue) {
        this.stringValue = stringValue;
        this.intValue = intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public int getIntValue() {
        return intValue;
    }

    @Override
    public String toString() {
        return stringValue + ":" + intValue;
    }
}