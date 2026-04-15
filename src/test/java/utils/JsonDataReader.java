package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonDataReader {

    @DataProvider(name = "bookingData")
    public Object[][] getBookingData() {

        List<Object[]> dataList =
                new ArrayList<>();

        try {

            ObjectMapper mapper =
                    new ObjectMapper();

            JsonNode root =
                    mapper.readTree(
                            new File(
                                    "src/test/resources/bookingData.json"));

            Iterator<JsonNode> elements =
                    root.elements();

            while (elements.hasNext()) {

                JsonNode node =
                        elements.next();

                dataList.add(
                        new Object[]{node});
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return dataList
                .toArray(new Object[0][]);
    }
}