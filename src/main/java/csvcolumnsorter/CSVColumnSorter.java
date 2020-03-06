package csvcolumnsorter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Sort CSV columns by the header
 */
public class CSVColumnSorter {

    private static final String SEPARATOR = ",";
    private static final String LINE_FEED = "\n";

    public static String sortCsvColumns(String csv_data) {
        // store the list of headers sorted
        TreeSet<String> sortedHeaders = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        // stores the original index for each header
        TreeMap<String, Integer> unsortedIndexMap = new TreeMap<>();
        // map original index to new sorted index
        TreeMap<Integer, Integer> originalIndexToSortedIndexMap = new TreeMap<>();

        StringReader sr = new StringReader(csv_data);
        BufferedReader br = new BufferedReader(sr);

        // stores the output string
        StringBuilder builder = new StringBuilder();

        try {
            // read the header line
            String line = br.readLine();
            if (line == null) { // dismiss empty file
                return null;
            }
            // process the header
            String[] headerColumns = line.split(SEPARATOR);
            int headerPosition = 0;
            for (String headerValue : headerColumns) {
                unsortedIndexMap.put(headerValue, headerPosition);
                headerPosition++;
                sortedHeaders.add(headerValue);
            }

            // print the sorted header
            boolean firstColumn = true;
            int sortedIndex = 0;
            for (String headerValue : sortedHeaders) {
                if (firstColumn) {
                    firstColumn = false;
                } else {
                    builder.append(SEPARATOR);
                }
                builder.append(headerValue);
                int originalIndex = unsortedIndexMap.get(headerValue);
                originalIndexToSortedIndexMap.put(originalIndex, sortedIndex);
                sortedIndex++;
            }

            int headerColumnCount = sortedHeaders.size();

            // read the remaining lines
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(SEPARATOR);
                String[] sortedColumns = new String[headerColumnCount];
                Arrays.fill(sortedColumns, "");
                int columnCount = 0;
                for (String column : columns) {
                    // String headerKey = unsortedIndexMap.get(columnCount);
                    // ArrayList<String> itemsPerHeader = sorted.get(headerKey);
                    // itemsPerHeader.add(column);
                    int currentSortedIndex = originalIndexToSortedIndexMap.get(columnCount);
                    sortedColumns[currentSortedIndex] = column;
                    columnCount++;
                }
                // print the sorted line
                builder.append(LINE_FEED);
                firstColumn = true;
                for (String sortedColumn : sortedColumns) {
                    if (firstColumn) {
                        firstColumn = false;
                    } else {
                        builder.append(SEPARATOR);
                    }
                    builder.append(sortedColumn);
                }
            }

            br.close();
            sr.close();

            return builder.toString();
        } catch (IOException e) {
            System.err.println(e);
            return null;
        }
    }
}