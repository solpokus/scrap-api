package id.scrap.util;

import java.util.Arrays;
import java.util.List;


public class CSVWriter {
//    private static final String COMMA = ",";
    private static final String COMMA = "#";
    private static final String NEWLINE = "\n";
//    private static final String DOUBLE_QUOTE = "\"";
    private static final String DOUBLE_QUOTE = "";
//    private static final String DOUBLE_DOUBLE_QUOTES = "\"\"";
    private static final String DOUBLE_DOUBLE_QUOTES = "";

    /**
     * Encode a CSV field by surrounding it in double quotes and escaping any double quote in between with another double quote adjacent to it
     *
     * @param field to encode
     * @return encoded field
     */
    public static String encodeField(String field) {
        return DOUBLE_QUOTE + field.replaceAll(DOUBLE_QUOTE, DOUBLE_DOUBLE_QUOTES) + DOUBLE_QUOTE;
    }

    /**
     * Encode a list of fields to CSV line by encoding each field and separating them with comma
     * Newline is not appended at the end
     *
     * @param fields to encode
     * @return encoded line
     */
    public static String encodeLine(List<String> fields) {
        StringBuilder line = new StringBuilder();
        for (String field : fields) line.append(encodeField(field)).append(COMMA);
        return line.toString().substring(0, line.length() - 2);
    }

    /**
     * Encode an Array of fields to CSV line by encoding each field and separating them with comma
     * Newline is not appended at the end
     *
     * @param fields to encode
     * @return encoded line
     */
    public static String encodeLine(String... fields) {
        return encodeLine(Arrays.asList(fields));
    }

    /**
     * Encode a List of (rows) List of String (columns)
     *
     * @param data List of (rows) List of String (columns)
     * @return CSV encoded data
     */
    public static String encode(List<List<String>> data) {
//    	System.out.println(data);
        StringBuilder csv = new StringBuilder();
        for (List<String> line : data) csv.append(encodeLine(line)).append(NEWLINE);
        return csv.toString().substring(0, csv.length() - 1);
    }

    public static String encode1(List<String> data) {
    	System.out.println("data : \n"+data);
        StringBuilder csv = new StringBuilder();
//        for (List<String> line : data) csv.append(encodeLine(line)).append(NEWLINE);
        for (String line : data) csv.append(encodeLine(line)).append(NEWLINE);
        return csv.toString().substring(0, csv.length() - 1);
    }
    private CSVWriter() {} // Prevent instantiation
}
