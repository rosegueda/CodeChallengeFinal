package utilities;

import POJOS.Datum;
import POJOS.UsersPojo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;

import java.util.List;

import static io.restassured.RestAssured.given;

public class APIUtilities {
    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }

    public void printParameterByParameters(String parameter, List<Integer> Values) {
        for (Integer value : Values) {
            Response response = given()
                    .contentType(ContentType.JSON)
                    .get("/users/" + value)
                    .then().statusCode(200)
                    .extract().response();
            JsonPath jsonPath = response.jsonPath();
            String firstName = jsonPath.getString("data.first_name");
            String lastName = jsonPath.getString("data.last_name");
            String userParameter = jsonPath.getString((parameter));
            String singleParameter = parameter.replace("data.", "");
            System.out.println(firstName + " "+lastName+"'s " + singleParameter + ": " + userParameter);
        }
    }

    public Response getUsersInformation() {
        Response response = given()
                .contentType(ContentType.JSON)
                .get("/users")
                .then().statusCode(200)
                .extract().response();
        return response;
    }

    public List<Integer> addIDByNameAndLastName(List<Integer> ids, String firstName, String lastName){
        Gson gson = new GsonBuilder().create();
        UsersPojo usersList = gson.fromJson(getUsersInformation().asString(), UsersPojo.class);
        for (Datum l : usersList.getData()) {
            if (l.getFirst_name().contains(firstName)) {
                if (l.getLast_name().contains(lastName)) {
                    ids.add(l.getId());
                    //PrintParameterByParameters("data.email", ids);
                }
            }
        }
        return ids;
    }
}
