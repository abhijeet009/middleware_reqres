package testCase;

import io.restassured.filter.session.SessionFilter;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.LoginPojo;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

public class Login extends Utils {
   Response response;
    LoginPojo loginObj;
    TestDataBuild TD = new TestDataBuild();
    SessionFilter seassion = new SessionFilter();
    APIResources apipathLogin = APIResources.valueOf("Login");

    APIResources apipathLogout = APIResources.valueOf("Logout");
   @Test
    public void login() throws IOException {
        loginObj = TD.LoginPojo();

       response = given().spec(requestSpecification()).contentType(" application/json")
               .body(loginObj)
               .when().post(apipathLogin.getResource()).then().spec(setupResponseSpecification()).log().all()
               .extract().response();

       assertEquals(response.getBody().asString().contains("token"),true,"Check login access data");
       loginfo("Login completed");
   }

   @Test
    public void logOut() throws IOException{
       loginfo("Starting logout ");
       response= given().spec(requestSpecification()).contentType(" application/json").filter(seassion)
               .when().post(apipathLogout.getResource()).then().spec(setupResponseSpecification()).log().all().extract().response();

       loginfo("Logout successful ");
   }
}
