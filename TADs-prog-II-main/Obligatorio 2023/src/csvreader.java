import org.apache.commons.csv.*;
import org.junit.*;
import java.time.LocalDateTime;
import java.time.format.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;


public class csvreader {
    private static final String f1_dataset_test = "./f1_dataset_test.csv";
    public static void main(String[] args) throws IOException {
        String pattern = "MM/d/yyyy hh:mm:ss a";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try (
                Reader reader = Files.newBufferedReader(Paths.get(f1_dataset_test));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing values by Header names
                String user_name = csvRecord.get("user_name");
                String user_location = csvRecord.get("user_location");
                String user_description = csvRecord.get("user_description");
                String date = LocalDateTime.parse(csvRecord.get("date")).format(formatter);
                String user_created = csvRecord.get("user_created");
                int user_followers = Integer.parseInt(csvRecord.get("user_followers"));
                String user_friends = csvRecord.get("user_friends");
                String user_favourites = csvRecord.get("user_favourites");
                boolean user_verified = Boolean.parseBoolean(csvRecord.get("user_verified"));
                String text = csvRecord.get("text");
                String hashtags = csvRecord.get("hashtags");
                String source = csvRecord.get("source");
                boolean is_retweet = Boolean.parseBoolean(csvRecord.get("is_retweet"));

            }
        }
    }
}
