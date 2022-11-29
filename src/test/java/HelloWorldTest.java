import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class HelloWorldTest {


    @Test
    public void TestRestAssured() {
        Map<String, String> head = new HashMap<>();
        head.put("myHeader1","myValue1");
        head.put("myHeader2","myValue2");



        Response response = RestAssured
                .given()
                .redirects()
                .follow(true)
                .when()
                .get("https://playground.learnqa.ru/api/get_303")
                .andReturn();

        int statusCode = response.statusCode();
        System.out.println(statusCode);

    }


     @Test
    public void TestCookie() {
        Map<String, String> creds = new HashMap<>();
        creds.put("login","secret_login");
        creds.put("password","secret_pass");

        Response response = RestAssured
                .given()
                .body(creds)
                .when()
                .post("https://playground.learnqa.ru/api/get_auth_cookie")
                .andReturn();

         String responseCookie = response.getCookie("auth_cookie");
         Map<String,String> cookie = new HashMap<>();

         if(responseCookie != null) {
             cookie.put("auth_cookie", responseCookie);
         }

        Response responseForCheck = RestAssured
                .given()
                .cookies(cookie)
                .when()
                .post("https://playground.learnqa.ru/api/check_auth_cookie")
                .andReturn();

         responseForCheck.print();


     }
}
