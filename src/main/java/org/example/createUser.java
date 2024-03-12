import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.Random;


import org.testng.annotations.Test;


public class Pet {
   // String baseurl="https://petstore.swagger.io/v2/";
   String baseurl="https://d2iwf0lpgyl88i.cloudfront.net/";
    public int randomNumber() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(100000) + 1; // Add 1 to get 1-10 range (inclusive)
        return randomNumber;
    }
    //String path;
    @Test
    public void correct_cases() {
        user_correct("{"
            + "\"firstName\":\"tomas\""
            + ", \"lastName\":\"doherty\", "
            + "\"email\":\"tom@tom.com\", "
            + "\"phone\":\"+6325632563\""
            + ", \"password\":\"Aa!304940940\"}");
        user_correct( "{"
            + "\"firstName\":\"Juanjo\""
            + ", \"lastName\":\"doherty\", "
            + "\"email\":\"juanjo@tom.com\", "
            + "\"phone\":\"+634463\""
            + ", \"password\":\"Aa!304940940\"}");
        userPasswordTestCorrect("123a%b54");
        userPasswordTestCorrect("12a345#678");
        userPasswordTestCorrect("a2345!ad8");
        userPasswordTestCorrect("%2345aas8");
    }
    public void user_correct(String body) {
        String endpoint = baseurl+"user/register";
        given()
            .header("Content-Type", "application/json")
            .body(body)
            .when()
            .post(endpoint).
            then().
            assertThat().
            statusCode(201);
    }
    @Test
    public void incorrect_cases(){
        user_incorrect("{"
            + "\"firstName\":\"tomas\""
            + ", \"lastName\":\"doherty\", "
            + "\"email\":\"test@tom.com\", "
            + "\"phone\":\"+633325632563\""
            + ", \"password\":\"Aa!304940940\"}",
            "Email is already in use","message");;
        user_incorrect("{"
            + "\"firstName\":\"tomasd\""
            + ", \"lastName\":\"dohertys\", "
            + "\"email\":\"test22@tom.com\", "
            + "\"phone\":\"+633325632563\""
            + ", \"password\":\"Aa!304940940\"}",
            "Phone is already in use","message");;
        user_incorrect("{"
                + "\"firstName\":\"\""
                + ", \"lastName\":\"dohertys\", "
                + "\"email\":\"test5555@tom.com\", "
                + "\"phone\":\"+6325622\""
                + ", \"password\":\"Aa!304940940\"}",
            "can't be empty","phone");
        user_incorrect("{"
                + "\"firstName\":\"Virgina\""
                + ", \"lastName\":\"\", "
                + "\"email\":\"test2222@tom.com\", "
                + "\"phone\":\"+633325633\""
                + ", \"password\":\"Aa!304940940\"}",
            "can't be null","email");
        user_incorrect("{"
                + "\"firstName\":\"Test\""
                + ", \"lastName\":\"perez\", "
                + "\"email\":\"\", "
                + "\"phone\":\"+63335633\""
                + ", \"password\":\"Aa!304940940\"}",
            "","message");
        user_incorrect("{"
                + "\"firstName\":\"Test\""
                + ", \"lastName\":\"perez\", "
                + "\"email\":\"test22@test.com\", "
                + "\"phone\":\"\""
                + ", \"password\":\"Aa!304940940\"}",
            "","message");
        user_incorrect("{"
                + "\"firstName\":\"Test\""
                + ", \"lastName\":\"perez\", "
                + "\"email\":\"test22@test.com\", "
                + "\"phone\":\"+232323\""
                + ", \"password\":\"\"}",
            "","message");

        userPasswordTestWrong("1234");
        userPasswordTestWrong("12345678");
        userPasswordTestWrong("a2345678");
        userPasswordTestWrong("%2345678");
        userPasswordTestWrong("%2345a8");
        userPasswordTestWrong("123456&");
        userPasswordTestWrong("A12345&");
        userPasswordTestWrong("abcdef&");
        userPasswordTestWrong("&&!%%%7");
    }

    public void userPasswordTestCorrect(String password)
    {
        int numero=randomNumber();
        user_correct("{"
            + "\"firstName\":\"Test\""
            + ", \"lastName\":\"perez\", "
            + "\"email\":\"test"+numero+"@test.com\", "
            + "\"phone\":\"+23"+numero+"2323\""
            + ", \"password\":\""+password+"\"}");
    }
    public void userPasswordTestWrong(String password)
    {
        int numero=randomNumber();
        user_incorrect("{"
                + "\"firstName\":\"Test\""
                + ", \"lastName\":\"perez\", "
                + "\"email\":\"test"+numero+"@test.com\", "
                + "\"phone\":\"+23"+numero+"2323\""
                + ", \"password\":\""+password+"\"}",
            "","message");
    }
    public void user_incorrect(String body,String message,String titleMensage) {
        String endpoint = baseurl+"user/register";
        given()
            .header("Content-Type", "application/json")
            .body(body)
            .when()
            .post(endpoint).
            then().
            assertThat().
            statusCode(400)
            .body(titleMensage, equalTo(message));
       //
    }



}
