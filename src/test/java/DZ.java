import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;

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

}