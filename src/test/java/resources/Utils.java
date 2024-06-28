package resources;

import  io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

public class Utils {

    static RequestSpecification req;
    static Properties prop;
    private static final Logger logger = Logger.getLogger(Utils.class.getName());

    static {
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("app.log");

        fileHandler.setLevel(Level.ALL);  // Set the logging level
        logger.addHandler(fileHandler);
        logger.setLevel(Level.ALL); // Adjust level as needed
    }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void  loginfo(String message){
     logger.info(message);
    }


    public RequestSpecification requestSpecification() throws IOException {
        logger.info("Strated Request specification");
        if(req==null) {
            PrintStream log = new PrintStream(new FileOutputStream("loging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                    .addFilter(RequestLoggingFilter.logRequestTo(log)).build();

            return req;
        }
        return req;
    }

    public static ResponseSpecification
        setupResponseSpecification()
    {
        // Create a ResponseSpecification
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType("application/json")
                .expectBody("data", not(empty()))
                .build();

    }

    public static String getGlobalValue(String key) throws IOException {
        prop =  new Properties();
        FileInputStream fls = new FileInputStream("src\\test\\java\\resources\\global.properties");
        prop.load(fls);
        return prop.getProperty(key);
    }
    protected Integer getJsonPath(Response response, String Key) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);

        return js.getInt(Key);
    }
    protected String getJsonPathString(Response response,String Key) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);

        return js.get(Key).toString();
    }

    public static void assertEquals(Object actual, Object expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }



}
