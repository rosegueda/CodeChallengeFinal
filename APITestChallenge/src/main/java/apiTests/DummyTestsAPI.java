package apiTests;

import POJOS.PojoList;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.APIUtilities;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DummyTestsAPI extends APIUtilities {

    @Test(priority = 0)
    public void Get_And_Print_Age(){

        List<Integer> ids = new ArrayList<>();
        ids.add(2);
        ids.add(1);
        ids.add(3);

        PrintBirthdayByParamethers("data.employee_age",ids);
    }
    @Test(priority = 1)
    public void Test1(){
        String body = given()
                        //.contentType(ContentType.JSON)
                //.get("/employee/1")
                .when()
                        .get("http://dummy.restapiexample.com/api/v1/employees")
                    .then()
                    .statusCode(200)
                .body("data[0].employee_name",equalTo("Tiger Nixon"))
                    .extract().body().asPrettyString();
        System.out.println(body);
    }

    @Test(priority = 1)
    public void Test2(){
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/employee/1")
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println(response.asPrettyString());
    }

    @Test(priority = 2)
    public void All_Employees_Information(){
        for(PojoList l: PojoListEmployees()){
            System.out.println("Hello");
        }
    }
}
