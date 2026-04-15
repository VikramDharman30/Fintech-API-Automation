package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.util.Properties;

public class BaseTest {

    public static Properties prop;

    @BeforeClass
    public void setup() {

        try {
            prop = new Properties();
            FileInputStream fis =
                    new FileInputStream("src/main/resources/config.properties");

            prop.load(fis);

            RestAssured.baseURI =
                    prop.getProperty("baseUrl");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}