package api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ReqresTest {

    private final static String url = "https://reqres.in";

    @Test
    public void checkAvatarAndIdTest(){
        List<UserData> users = given()
                .when()
                .contentType(ContentType.JSON)
                .get(url + "/api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        users.forEach(x-> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assert.assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("@reqres.in")));
    }
}
