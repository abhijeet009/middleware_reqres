package testCase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Data;
import pojo.Fields;
import pojo.Support;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetAllUsers extends Utils {
    APIResources getUsers = APIResources.valueOf("GetUsers");
    APIResources getUSersID= APIResources.valueOf("GetUsersID");
    Fields fieObj = new Fields();
    Response responseGetData,responseCompare;

    Support sc = new Support();

    @Test
    public void getAllUsers() throws IOException {
        /*
        code to execute API request
         */
       getAPIResponseForUser();

        /* Store response in pojo */
        addDataInPojo();

        /* get user data based on user id and compare with existing users*/
        int id=5;
        getUserDetailsByID(id);
    }

    public void getAPIResponseForUser() throws IOException {

        responseGetData=given().spec(requestSpecification())
                .when()
                .get(getUsers.getResource())
                .then().spec(setupResponseSpecification()).log().all().extract().response();;
    }

    public void addDataInPojo() throws IOException {

        TestDataBuild TD = new TestDataBuild();
        List<Object> addData = new ArrayList<>();

        JsonPath jsonPath = responseGetData.jsonPath();

        // Loop through each object in the 'data' array
        for (int i = 0; i < jsonPath.getList("data").size(); i++) {
            // Add the extracted data to 'addData' list (if needed)
            addData.add(getJsonPath(responseGetData,"data[" + i + "].id"));
            addData.add(getJsonPathString(responseGetData,"data[" + i + "].email"));
            addData.add(getJsonPathString(responseGetData,"data[" + i + "].first_name"));
            addData.add(getJsonPathString(responseGetData,"data[" + i + "].last_name"));
            addData.add(getJsonPathString(responseGetData,"data[" + i + "].last_name"));

        }
        TD.fields(getJsonPath(responseGetData,"page")
                ,getJsonPath(responseGetData,"per_page")
                ,getJsonPath(responseGetData,"total")
                ,getJsonPath(responseGetData,"total_pages")
                ,addData
        ,getJsonPathString(responseGetData, "support.url")
                        ,getJsonPathString(responseGetData, "support.text"));
    }

    public void getUserDetailsByID(int id) throws IOException {
        responseCompare= given().spec(requestSpecification())
                .pathParam("id", id)
                .baseUri(getGlobalValue("baseUrl"))
                .when()
                .get(getUSersID.getResource())
                .then()
                .spec(setupResponseSpecification()).log().all().extract().response();

        assertEquals(getJsonPathString(responseGetData,"data["+(id-1)+"].email")
                ,getJsonPathString(responseCompare,"data.email")
                ,"User data not matching");

    }

}
