import cristian.*;

// DataFrame class that stores rows



import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        // Define a DataFrame with String keys and DataValue values for demo
        DataFrame<String> df = new DataFrame<>();

        // Adding rows with mixed types (String as key, DataValue as value)
        Row<String> row1 = new Row<>();
        row1.addColumn("Name", new DataValue("Alice"));
        row1.addColumn("Age", new DataValue(25));
        row1.addColumn("GPA", new DataValue(3.75));

        Row<String> row2 = new Row<>();
        row2.addColumn("Name", new DataValue("Bob"));
        row2.addColumn("Age", new DataValue(30));
        row2.addColumn("GPA", new DataValue(3.9));

        df.addRow(row1.getColumns());
        df.addRow(row2.getColumns());

        System.out.println("Before Sorting:");
        df.printDataFrame();

        // Sort the dataframe by "Age"
        df.sortByKey("Age");

        System.out.println("\nAfter Sorting by Age:");
        df.printDataFrame();

        // Now load from CSV
        SimpleCSVReader csvReader = new SimpleCSVReader();
        DataFrame<String> csvDf = csvReader.readCSV(new File("/home/cris/Documents/dev2024/algorritmos/TP integrador Grupo 5/datasets/MOCK_DATA2.csv"));

        System.out.println("\nDataFrame from CSV:");
        csvDf.printDataFrame();


    }
}
