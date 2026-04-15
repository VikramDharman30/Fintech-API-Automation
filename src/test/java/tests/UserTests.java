package tests;

import base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payload.UserPayload;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Listeners;
import listeners.ExtentListener;
import utils.JsonDataReader;

@Listeners(ExtentListener.class)
public class UserTests extends BaseTest {

    public static int bookingId;

    @Test(priority = 1)
    public void createBookingTest() {

        Response response =

                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .body(UserPayload.createBookingPayload())

                        .when()
                        .post("/booking")

                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();

        bookingId =
                response.jsonPath().getInt("bookingid");

        System.out.println("Generated Booking ID: " + bookingId);
    }
    @Test(priority = 2)
    public void getBookingTest() {

        given()
                .log().all()

                .when()
                .get("/booking/" + bookingId)

                .then()
                .log().all()
                .statusCode(200)
                .body("firstname", equalTo("John"))
                .body("lastname", equalTo("Doe"))
                .body(matchesJsonSchemaInClasspath(
                        "schema/bookingSchema.json"));
    }
    public static String token;

    @Test(priority = 3)
    public void createTokenTest() {

        String authPayload = "{\n" +
                "  \"username\" : \"admin\",\n" +
                "  \"password\" : \"password123\"\n" +
                "}";

        Response response =

                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .body(authPayload)

                        .when()
                        .post("/auth")

                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();

        token =
                response.jsonPath().getString("token");

        System.out.println("Generated Token: " + token);
    }

    @Test(priority = 4)
    public void updateBookingTest() {

        given()
                .log().all()
                .header("Cookie", "token=" + token)
                .contentType(ContentType.JSON)
                .body(UserPayload.updateBookingPayload())

                .when()
                .put("/booking/" + bookingId)

                .then()
                .log().all()
                .statusCode(200)
                .body("firstname", equalTo("Jane"))
                .body("lastname", equalTo("Smith"));
    }

    @Test(priority = 5)
    public void deleteBookingTest() {

        given()
                .log().all()
                .header("Cookie", "token=" + token)

                .when()
                .delete("/booking/" + bookingId)

                .then()
                .log().all()
                .statusCode(201);

        System.out.println("Deleted Booking ID: " + bookingId);
    }

    @Test(priority = 6)
    public void verifyDeletedBookingTest() {

        given()
                .log().all()

                .when()
                .get("/booking/" + bookingId)

                .then()
                .log().all()
                .statusCode(404);
    }

    @Test(
            priority = 7,
            dataProvider = "bookingData",
            dataProviderClass = JsonDataReader.class
    )
    public void createBookingDataDrivenTest(
            JsonNode data) {

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(
                        UserPayload
                                .createBookingFromJson(data))

                .when()
                .post("/booking")

                .then()
                .log().all()
                .statusCode(200)
                .body("booking.firstname",
                        equalTo(
                                data.get("firstname").asText()));
    }
}