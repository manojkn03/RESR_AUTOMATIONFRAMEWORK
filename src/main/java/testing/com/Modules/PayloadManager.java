package testing.com.Modules;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import org.testng.annotations.Test;
import testing.com.payloadPojos.Booking;
import testing.com.payloadPojos.Bookingdates;
import testing.com.payloadPojos.Token;

public class PayloadManager {


   @Test
    public  String createPayload(){

        // java object  to JSON

       Faker faker= new Faker();
       String expFirstName= faker.name().firstName();
       String expLastName= faker.name().lastName();
       Integer expectedPrice= faker.number().numberBetween(100,200);
       Booking booking = new Booking();
       booking.setFirstname(expFirstName);
       booking.setLastname(expLastName);
       booking.setDepositpaid(true);
       booking.setTotalprice(expectedPrice);
       booking.setAdditionalneeds("Breakfast");

       Bookingdates bookingdates = new Bookingdates();
       bookingdates.setCheckin("2024-01-01");
       bookingdates.setCheckout("2024-02-02");

       booking.setBookingdates(bookingdates);

       //converting object to json using Gson library (Serialization)

       Gson gson= new Gson();
       String jsonPayload= gson.toJson(booking);
       System.out.println(jsonPayload);
       return jsonPayload;

    }

    public String createTokenPayload() {

       Token token = new Token();
       token.setUsername("admin");
       token.setPassword("password123");

       Gson gson = new Gson();
       String tokenPayload = gson.toJson(token);
       return tokenPayload;
    }

   public String updatePayload() {
      Faker faker= new Faker();

      Booking booking= new Booking();
      booking.setFirstname("Manoj");
      booking.setLastname("KN");
      booking.setTotalprice(100);
      booking.setAdditionalneeds("breakfast");
      booking.setDepositpaid(true);

      Bookingdates bookingdates=new Bookingdates();
      bookingdates.setCheckin("2018-01-01");
      bookingdates.setCheckout("2019-01-01");

      booking.setBookingdates(bookingdates);
      // Object -> JSON String (GSON)
      Gson gson=new Gson();
      String jsonUpdateBookingPayload=gson.toJson(booking);
      System.out.println(jsonUpdateBookingPayload);
      return  jsonUpdateBookingPayload;

   }
}

