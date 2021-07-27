package apiTests;

import POJOS.Datum;
import POJOS.UsersPojo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.annotations.Test;
import utilities.APIUtilities;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class DummyTestsAPI extends APIUtilities {

    @Test(priority = 0)
    public void Get_And_Print_Age() {
        List<Integer> ids = new ArrayList<>();
        addIDByNameAndLastName(ids,"Janet","Weaver");
        addIDByNameAndLastName(ids,"Emma","Wong");
        addIDByNameAndLastName(ids,"Eve","Holt");
        printParameterByParameters("data.email", ids);
    }


    @Test(priority = 2)
    public void All_Users_Information() {
        Gson gson = new GsonBuilder().create();
        UsersPojo usersList = gson.fromJson(getUsersInformation().asString(), UsersPojo.class);
        for (Datum l : usersList.getData())
        {
            System.out.println("Username: "+l.getFirst_name()+" "+l.getLast_name());
            System.out.println("Avatar: "+l.getAvatar());
            System.out.println("-------------------------------------------------------");
            }
        }
    }