package testing.com.crud;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import testing.com.base.BaseTest;
import testing.com.endpoints.APIConstants;

public class CreateBooking extends BaseTest {

    @Description(" Create a booking with Valid Payload")
    @Owner("Manoj")
    @Test
    public void testCreateBooking(){
        r= r.basePath(APIConstants.CREATE_UPDATE_URL);
        response= RestAssured.given().spec(r).body(payloadManager.createPayload()).when().post();

        vResponse= response.then().log().all();
        vResponse.statusCode(200);



    }
}
