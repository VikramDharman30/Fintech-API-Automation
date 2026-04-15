package payload;

import java.util.HashMap;

public class UserPayload {

    public static HashMap<String, Object> createBookingPayload() {

        HashMap<String, Object> booking = new HashMap<>();

        booking.put("firstname", "John");
        booking.put("lastname", "Doe");
        booking.put("totalprice", 1000);
        booking.put("depositpaid", true);

        HashMap<String, String> bookingDates =
                new HashMap<>();

        bookingDates.put("checkin", "2024-01-01");
        bookingDates.put("checkout", "2024-01-05");

        booking.put("bookingdates", bookingDates);
        booking.put("additionalneeds", "Breakfast");

        return booking;
    }
    public static HashMap<String, Object> updateBookingPayload() {

        HashMap<String, Object> booking =
                new HashMap<>();

        booking.put("firstname", "Jane");
        booking.put("lastname", "Smith");
        booking.put("totalprice", 2000);
        booking.put("depositpaid", false);

        HashMap<String, String> bookingDates =
                new HashMap<>();

        bookingDates.put("checkin", "2024-02-01");
        bookingDates.put("checkout", "2024-02-10");

        booking.put("bookingdates", bookingDates);
        booking.put("additionalneeds", "Lunch");

        return booking;
    }
}