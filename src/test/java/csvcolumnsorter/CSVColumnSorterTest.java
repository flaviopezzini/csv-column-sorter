package csvcolumnsorter;

import org.junit.Test;

import csvcolumnsorter.CSVColumnSorter;

import static org.junit.Assert.*;

public class CSVColumnSorterTest {
    @Test
    public void shouldHandleTheExample() {
        assertEquals("Adam,Beth,Charles,Danielle,Eric\n3907,17945,10091,10088,10132\n48,2,12,13,11", CSVColumnSorter
                .sortCsvColumns("Beth,Charles,Danielle,Adam,Eric\n17945,10091,10088,3907,10132\n2,12,13,48,11"));
    }

    @Test
    public void shouldHandleManyLines() {
        String expected = "Adam,Beth,Charles,Danielle,Eric\n" +
                "3907,17945,10091,10088,10132\n" + 
                "48,2,12,13,11\n" +
                "0,1,2,3,4\n" + "0,1,2,3,4\n" + "0,1,2,3,4\n" + "0,1,2,3,4\n" + "0,1,2,3,4\n" + "0,1,2,3,4\n"
                + "0,1,2,3,4\n" + "0,1,2,3,4\n" + "0,1,2,3,4\n" + "0,1,2,3,4\n" + "0,1,2,3,4\n" + "0,1,2,3,4\n"
                + "0,1,2,3,4\n" + "0,1,2,3,4\n" + "0,1,2,3,4\n" + "0,1,2,3,4";
        String input = "Beth,Charles,Danielle,Adam,Eric\n" + "17945,10091,10088,3907,10132\n" + "2,12,13,48,11\n" + 
                "1,2,3,0,4\n" + "1,2,3,0,4\n" + "1,2,3,0,4\n" + "1,2,3,0,4\n" + "1,2,3,0,4\n" + "1,2,3,0,4\n"
                + "1,2,3,0,4\n" + "1,2,3,0,4\n" + "1,2,3,0,4\n" + "1,2,3,0,4\n" + "1,2,3,0,4\n" + "1,2,3,0,4\n"
                + "1,2,3,0,4\n" + "1,2,3,0,4\n" + "1,2,3,0,4\n" + "1,2,3,0,4";
        assertEquals(expected, CSVColumnSorter
                .sortCsvColumns(input));
    }

}