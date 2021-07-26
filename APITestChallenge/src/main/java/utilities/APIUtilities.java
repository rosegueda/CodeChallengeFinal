package utilities;

import POJOS.PojoList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.List;

import static io.restassured.RestAssured.given;

public class APIUtilities {
    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "http://dummy.restapiexample.com";
        RestAssured.basePath = "/api/v1";
    }

    public void PrintBirthdayByParamethers(String parameter, List<Integer> Values){
        for(Integer value: Values)
        {
            Response response = given()
                    .contentType(ContentType.JSON)
                    .get("/employee/"+ value)
                    .then().statusCode(200)
                    .extract().response();
            JsonPath jsonPath = response.jsonPath();
            String employeeName = jsonPath.getString("data.employee_name");
            String employeeParameter = jsonPath.getString((parameter));
            System.out.println(employeeName+"'s Age: "+employeeParameter);
        }
    }
    public PojoList[] PojoListEmployees(){
    Response response = given().contentType(ContentType.JSON).get("/employees")
            .then().statusCode(200)
            .extract().response();
            Gson gson= new GsonBuilder().create();
            PojoList[] employeesList = gson.fromJson(response.asString(), PojoList[].class);
        return employeesList;
    }

}
