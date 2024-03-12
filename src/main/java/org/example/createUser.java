import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.Random;


import org.testng.annotations.Test;


public class createUser {
   String baseurl="https://d2iwf0lpgyl88i.cloudfront.net/";
    public int randomNumber() {
        Random rand = new Random();
        int randomNumber2 = rand.nextInt(100000) + 1;
        return randomNumber2;
    }
    public String randomWord() {
        Random rand = new Random();
        int wordLength = 15+ rand.nextInt(25) + 1;

        // Create a Random object
        Random random = new Random();

        // Characters allowed in the "word"
        String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // Create an empty string to store the word
        StringBuilder word = new StringBuilder();

        // Loop to generate random characters
        for (int i = 0; i < wordLength; i++) {
            // Generate a random index within the allowed characters string
            int randomIndex = random.nextInt(allowedChars.length());

            // Get the character at the random index
            char randomChar = allowedChars.charAt(randomIndex);

            // Append the character to the string
            word.append(randomChar);

        }
        return  word.toString();

    }

    //String path;
    @Test
    public void correct_cases() {
        userPasswordTestCorrect("123a%b54");
        userPasswordTestCorrect("12a345#678");
        userPasswordTestCorrect("a2345!ad8");
        userPasswordTestCorrect("%2345aas8");
        user_correct( "{"
            + "\"firstName\":\""+randomWord()+"\""
            + ", \"lastName\":\""+randomWord()+"\", "
            + "\"email\":\""+randomWord()+"@gmail.com\", "
            + "\"phone\":\"+"+randomNumber()+"\""
            + ", \"password\":\"Aa!304940940\"}");

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
        /* the wrong number */
        user_incorrect("{"
                +"\"firstName\":\"ss"+randomWord()+"\""
                + ", \"lastName\":\"ss"+randomWord()+"\", "
                + "\"email\":\"test@gmail.com\", "
                + "\"phone\":\"+d"+randomNumber()+"\""
                + ", \"password\":\"Aa!304940940\"}",
            "","message");;
        /* the same email*/
        String email=randomWord();
        user_correct( "{"
            + "\"firstName\":\""+randomWord()+"\""
            + ", \"lastName\":\""+randomWord()+"\", "
            + "\"email\":\""+email+"@gmail.com\", "
            + "\"phone\":\"+"+randomNumber()+"\""
            + ", \"password\":\"Aa!304940940\"}");
        user_incorrect("{"
                +"\"firstName\":\"ss"+randomWord()+"\""
                + ", \"lastName\":\"ss"+randomWord()+"\", "
                + "\"email\":\""+email+"t@gmail.com\", "
                + "\"phone\":\"+"+randomNumber()+"\""
                + ", \"password\":\"Aa!304940940\"}",
            "Email is already in use","message");
        /* the same phone*/
        int phone=randomNumber();
        user_correct( "{"
            + "\"firstName\":\""+randomWord()+"\""
            + ", \"lastName\":\""+randomWord()+"\", "
            + "\"email\":\""+randomWord()+"@gmail.com\", "
            + "\"phone\":\"+"+phone+"\""
            + ", \"password\":\"Aa!304940940\"}");
        user_incorrect("{"
                +"\"firstName\":\""+randomWord()+"\""
                + ", \"lastName\":\""+randomWord()+"\", "
                + "\"email\":\""+randomWord()+"@gmail.com\", "
                + "\"phone\":\"+"+phone+"\""
                + ", \"password\":\"Aa!304940940\"}",
            "Phone is already in use","message");;
            /*empty cases*/
        user_incorrect("{"
                + "\"firstName\":\"\""
                + ", \"lastName\":\"dohertys\", "
                + "\"email\":\""+randomWord()+"@gmail.com\", "
                + "\"phone\":\"\"+"+randomNumber()+"\""
                + ", \"password\":\"Aa!304940940\"}",
            "can't be empty","name");
        user_incorrect("{"
                + "\"firstName\":\"Virgina\""
                + ", \"lastName\":\"\", "
                + "\"email\":\""+randomWord()+"@gmail.com\", "
                + "\"phone\":\"\"+"+randomNumber()+"\""
                + ", \"password\":\"Aa!304940940\"}",
            "can't be null","email");
        user_incorrect("{"
                + "\"firstName\":\"Test\""
                + ", \"lastName\":\"perez\", "
                + "\"email\":\"\", "
                + "\"phone\":\"+"+randomNumber()+"\""
                + ", \"password\":\"Aa!304940940\"}",
            "","message");
        user_incorrect("{"
                + "\"firstName\":\"Test\""
                + ", \"lastName\":\"perez\", "
                + "\"email\":\""+randomWord()+"@gmail.com\", "
                + "\"phone\":\"\""
                + ", \"password\":\"Aa!304940940\"}",
            "","message");
        user_incorrect("{"
                + "\"firstName\":\"Test\""
                + ", \"lastName\":\"perez\", "
                + "\"email\":\""+randomWord()+"@gmail.com\", "
                + "\"phone\":\"+"+randomNumber()+"\""
                + ", \"password\":\"\"}",
            "","message");
        /*without field*/
        user_incorrect("{"
                + ", \"lastName\":\"dohertys\", "
                + "\"email\":\""+randomWord()+"@gmail.com\", "
                + "\"phone\":\"\"+"+randomNumber()+"\""
                + ", \"password\":\"Aa!304940940\"}",
            "can't be empty","name");
        user_incorrect("{"
                + "\"firstName\":\"Virgina\""
                + "\"email\":\""+randomWord()+"@gmail.com\", "
                + "\"phone\":\"\"+"+randomNumber()+"\""
                + ", \"password\":\"Aa!304940940\"}",
            "can't be null","email");
        user_incorrect("{"
                + "\"firstName\":\"Test\""
                + ", \"lastName\":\"perez\", "
                + "\"phone\":\"+"+randomNumber()+"\""
                + ", \"password\":\"Aa!304940940\"}",
            "","email");
        user_incorrect("{"
                + "\"firstName\":\"Test\""
                + ", \"lastName\":\"perez\", "
                + "\"email\":\""+randomWord()+"@gmail.com\", "
                + ", \"password\":\"Aa!304940940\"}",
            "","phone");
        user_incorrect("{"
                + "\"firstName\":\"Test\""
                + ", \"lastName\":\"perez\", "
                + "\"email\":\""+randomWord()+"@gmail.com\", "
                + "\"phone\":\"+"+randomNumber()+"\""
                +"}",
            "","password");
        //extra lenght
        user_incorrect("{"
                + "\"firstName\":\"alejandroalejandroalejadddddddddddssssssssssssssssssssssssssssssssssssssssssssssssssssssssssddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddndroalejandroalejandroalejandroalejandroalejandroalejandroalejandroalejandroalejandroalejandroalejandroalejandroalejandro\""
                + ", \"lastName\":\"perez\", "
                + "\"email\":\""+randomWord()+"@gmail.com\", "
                + "\"phone\":\"+"+randomNumber()+"\""
                + ", \"password\":\"Aa!304940940\"}",
            "","message");
        user_incorrect("{"
                + "\"firstName\":\"Test_2\""
                + ", \"lastName\":\"alejandroalejandroalejadddddddddddssssssssssssssssssssssssssssssssssssssssssssssssssssssssssddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddndroalejandroalejandroalejandroalejandroalejandroalejandroalejandroalejandroalejandroalejandroalejandroalejandroalejandro\", "
                + "\"email\":\""+randomWord()+"@gmail.com\", "
                + "\"phone\":\"+"+randomNumber()+"\""
                + ", \"password\":\"Aa!304940940\"}",
            "","message");
        user_incorrect("{"
                + "\"firstName\":\"Test_2\""
                + ", \"lastName\":\"perez\", "
                + "\"email\":\"email_Invalid_email_Invalidemail_Invalid_email_Invalidemail_Invalid_email_Invalidemail_Invalid_email_Invalidemail_Invalid_email_Invalidemail_Invalid_email_Invalid_email_Invalid_email_Invalid_email_Invalid_email_Invalid_email_Invalid_email_Invalid_email_Invalid_email_Invalid_email_Invalid_email_Invalid_email_Invalid_email_InvalidInvalid_email_Invalid_email_Invalid_email_InvalidInvalid_email_Invalid_email_Invalid_email_InvalidInvalid_email_Invalid_email_Invalid_email_Invalid@gmail.com\", "
                + "\"phone\":\"+"+randomNumber()+"\""
                + ", \"password\":\"Aa!304940940\"}",
            "","message");


        user_incorrect("{"
                + "\"firstName\":\"Test_2\""
                + ", \"lastName\":\"perez\", "
                + "\"email\":\""+randomWord()+"@gmail.com\", "
                + "\"phone\":\"+123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890\""
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
            + "\"firstName\":\""+randomWord()+"\""
            + ", \"lastName\":\""+randomWord()+"\", "
            + "\"email\":\""+randomWord()+"@gmail.com\", "
            + "\"phone\":\""+randomNumber()+"\", "
            + ", \"password\":\""+password+"\"}");
    }
    public void userPasswordTestWrong(String password)
    {
        int numero=randomNumber();
        user_incorrect("{"
                + "\"firstName\":\""+randomWord()+"\""
                + ", \"lastName\":\""+randomWord()+"\", "
                + "\"email\":\""+randomWord()+"@gmail.com\", "
                + "\"phone\":\""+randomNumber()+"\" , "
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
            ;//.body(titleMensage, equalTo(message))
       //
    }



}
