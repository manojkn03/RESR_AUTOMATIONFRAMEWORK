package testing.com.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testing.com.Actions.AssertActions;
import testing.com.Modules.PayloadManager;
import testing.com.endpoints.APIConstants;

public class BaseTest {

   public RequestSpecification r;
    public Response response;
   public  ValidatableResponse vResponse;

   public PayloadManager payloadManager;

   public AssertActions assertActions;


   public JsonPath jsonPath;

   @BeforeMethod(alwaysRun = true)
    public void setConfig(){
       payloadManager= new PayloadManager();
       assertActions = new AssertActions();
       r= new RequestSpecBuilder()
               .setBaseUri(APIConstants.BASE_URL)
               .addHeader("Content-Type", "Application/json")
               .build().log().all();


    }

    @Test(alwaysRun = true)
    public  String getToken(){
       r= RestAssured.given();
       r.baseUri(APIConstants.BASE_URL);
       r.basePath("/auth");
       r.contentType(ContentType.JSON);

      /* String payload= "{\n" +
               "    \"username\" : \"admin\",\n" +
               "    \"password\" : \"password123\"\n" +
               "}";*/

       r.body(payloadManager.createTokenPayload());

       response=r.when().post();

       jsonPath= new JsonPath(response.asString());
       return jsonPath.getString("token");





    }


}
