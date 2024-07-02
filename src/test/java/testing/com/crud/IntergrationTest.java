package testing.com.crud;

import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import testing.com.base.BaseTest;
import testing.com.endpoints.APIConstants;
import testing.com.payloadPojos.Booking;
import testing.com.payloadPojos.BookingResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class IntergrationTest extends BaseTest {


    // Create A Booking, Create a Token
    // Get booking
    // Update the Booking
    // Delete the Booking
    //

    // How to pass the data to one testcase to another.
   String token;
    String bookingID;

 @Test(groups = {"Sanity"})
    public void createBooking(){
     token= getToken();
     r.basePath(APIConstants.CREATE_UPDATE_URL);
     response= RestAssured.given().spec(r).body(payloadManager.createPayload()).when().post();

     String bookingResponse=response.asString();
     System.out.println(bookingResponse);

     ValidatableResponse vResponse= response.then();
     vResponse.statusCode(200);

     jsonPath=jsonPath.from(response.asString());
     bookingID= jsonPath.getString("bookingid");
     System.out.println("Booking id is ->"+bookingID);

     Gson gson=new Gson();

     BookingResponse bookingRes= gson.fromJson(bookingResponse, BookingResponse.class);
     assertThat(bookingRes.getBookingid()).isNotNull();
     assertThat(bookingRes.getBooking().getLastname()).isNotEmpty();
     assertThat(bookingRes.getBooking().getTotalprice()).isNotNull();

 }

   @Description("Update booking by id")
   @Test(dependsOnMethods = {"createBooking"}, groups = "Sanity")
    public void updateBooking(){
    r.basePath(APIConstants.CREATE_UPDATE_URL+"/"+bookingID);
    response= RestAssured.given().spec(r).cookie("token",token).body(payloadManager.updatePayload()).when().put();

    vResponse=response.then().log().all();
    vResponse.statusCode(200);
   String jsonString= response.asString();

   Gson gson= new Gson();

Booking updateResponse= gson.fromJson(jsonString, Booking.class);

    assertThat(updateResponse.getFirstname()).isEqualTo("Manoj");
    assertThat(updateResponse.getLastname()).isNotEmpty();
    assertThat(updateResponse.getTotalprice()).isNotNull();




    }

 @Description("Delete booking by id")
 @Test(dependsOnMethods = {"updateBooking"}, groups = "Sanity")
    public void deleteBooking(){

  r.basePath(APIConstants.CREATE_UPDATE_URL+"/"+bookingID);
  response= RestAssured.given().spec(r).cookie("token",token).body(payloadManager.updatePayload()).when().delete();

  vResponse=response.then().log().all();
  vResponse.statusCode(201);

 }

 @Test(dependsOnMethods = {"deleteBooking"})
  public void testGetBookingIDAfterDelete(){

     r.basePath(APIConstants.CREATE_UPDATE_URL+"/"+bookingID);
     response= RestAssured.given().spec(r).cookie("token",token).when().get();

     vResponse=response.then().log().all();
     vResponse.statusCode(404);

  }


}
