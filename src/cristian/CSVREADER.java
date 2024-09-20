package cristian;
import java.io.File;
import java.io.IOException;
public interface CSVREADER<K> {
    DataFrame<K> readCSV(File file) throws IOException;
}
