import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class DZ {

    @Test
    public void TestEx3() {
        System.out.println("Hello from Eugene");

    }


    @Test
    public void TestEx4() {
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/get_text")
                .andReturn();

        System.out.println(response.asString());


    }

    @Test
    public void TestEx5() {
        JsonPath response = RestAssured
                .get("https://playground.learnqa.ru/api/get_json_homework")
                .jsonPath();

        response.prettyPrint();
        Object secondMessageAndTimestamp = response.get("messages[1]");
        System.out.println("Полный текст второго сообщения: " + secondMessageAndTimestamp);

        String secondText = response.get("messages[1].message");
        System.out.println("Текст второго сообщения: " + secondText);
    }

    @Test
    public void TestEx6() {
        Response response = RestAssured
                .given()
                .redirects()
                .follow(false)
                .when()
                .get("https://playground.learnqa.ru/api/long_redirect")
                .andReturn();

        String getUrlForRedirect = response.getHeader("Location");
        System.out.println("\nUrl for redirect: " + getUrlForRedirect);
    }

    @Test
    public void TestEx7_1() {
        String requestUrl = "https://playground.learnqa.ru/api/long_redirect";
        boolean doRequest = true;
        int amountOfRedirects = 0;

        while (doRequest) {
            Response response = RestAssured
                    .given()
                    .redirects()
                    .follow(false)
                    .when()
                    .get(requestUrl)
                    .andReturn();
            if(response.statusCode() != 200) {
                requestUrl = response.getHeader("Location");
                amountOfRedirects += 1;
                System.out.println(amountOfRedirects);
                System.out.println(response.getHeader("Location"));
            } else {
                doRequest = false;
                System.out.println("\nFinish");
            }
        }
    }

    @Test
    public void TestEx7_2 () {
        String requestUrl = "https://playground.learnqa.ru/api/long_redirect";
        boolean doRequest = true;
        int amountOfRedirects = 0;

        while (doRequest) {
            Response response = getResponseWithNoRedirects(requestUrl);
            if(response.statusCode() != 200) {
                requestUrl = response.getHeader("Location");
                amountOfRedirects += 1;
                System.out.println(amountOfRedirects);
                System.out.println(requestUrl + "\n");
            } else {
                doRequest = false;
                System.out.println("Finish");
            }
        }

    }

    public Response getResponseWithNoRedirects(String url) {
        Response response = RestAssured
                .given()
                .redirects()
                .follow(false)
                .when()
                .get(url)
                .andReturn();
        return response;

    }


}
