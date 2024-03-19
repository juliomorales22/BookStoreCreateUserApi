package org.example;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.Random;
import com.google.gson.Gson;

import org.testng.annotations.Test;




public class createUser {
   String baseurl="https://8cfwtt3al2.execute-api.eu-central-1.amazonaws.com";
    public int randomNumber() {
        Random rand = new Random();
        int lowerBound = 10000;
        int upperBound = 1000000000;

        int randomNumber2 = rand.nextInt(upperBound - lowerBound + 1) + lowerBound;
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
        User user = User.builder()
                        .firstName(randomWord())
                        .lastName(randomWord())
                        .email(randomWord() + "@gmail.com")
                        .phone("+" + randomNumber())
                        .password("Aa!304940940")
                        .build();
        userCorrect( user);

    }
    public void userCorrect(User user) {
        String json = new Gson().toJson(user);
        System.out.println(json);
        System.out.println(baseurl+"/users/register");
        String endpoint = baseurl+"/users/register";
        given()
            .header("Content-Type", "application/json")
            .body(json)
            .when()
            .post(endpoint).
            then().
            assertThat().
            statusCode(201);
    }
    @Test
    public void incorrect_cases(){
        /* the wrong number */
        userIncorrect(User.builder()
                          .firstName("ss" + randomWord())
                          .lastName("ss" + randomWord())
                          .email("test@gmail.com")
                          .phone("+d" + randomNumber())
                          .password("Aa!304940940")
                          .build(),
            "Email is already in use","message");

        String email = randomWord();
        userCorrect(User.builder()
                        .firstName(randomWord())
                        .lastName(randomWord())
                        .email(email + "@gmail.com")
                        .phone("+" + randomNumber())
                        .password("Aa!304940940")
                        .build());

        userIncorrect(User.builder()
                          .firstName("ss" + randomWord())
                          .lastName("ss" + randomWord())
                          .email(email + "t@gmail.com")
                          .phone("+" + randomNumber())
                          .password("Aa!304940940")
                          .build(),
            "Email is already in use","message");

        int phone = randomNumber();
        userCorrect(User.builder()
                        .firstName(randomWord())
                        .lastName(randomWord())
                        .email(randomWord() + "@gmail.com")
                        .phone("+" + phone)
                        .password("Aa!304940940")
                        .build());

        userIncorrect(User.builder()
                          .firstName(randomWord())
                          .lastName(randomWord())
                          .email(randomWord() + "@gmail.com")
                          .phone("+" + phone)
                          .password("Aa!304940940")
                          .build(),
            "Phone is already in use","message");

        userIncorrect(User.builder()
                          .firstName("")
                          .lastName("dohertys")
                          .email(randomWord() + "@gmail.com")
                          .phone("")
                          .password("Aa!304940940")
                          .build(),
            "can't be empty","name");

        userIncorrect(User.builder()
                          .firstName("Virgina")
                          .lastName("")
                          .email(randomWord() + "@gmail.com")
                          .phone("")
                          .password("Aa!304940940")
                          .build(),
            "can't be null","email");

        userIncorrect(User.builder()
                          .firstName("Test")
                          .lastName("perez")
                          .email("")
                          .phone("+" + randomNumber())
                          .password("Aa!304940940")
                          .build(),
            "","message");

        userIncorrect(User.builder()
                          .firstName("Test")
                          .lastName("perez")
                          .email(randomWord() + "@gmail.com")
                          .phone("")
                          .password("Aa!304940940")
                          .build(),
            "","message");

        userIncorrect(User.builder()
                          .firstName("Test")
                          .lastName("perez")
                          .email(randomWord() + "@gmail.com")
                          .phone("+" + randomNumber())
                          .password("")
                          .build(),
            "","message");

        userIncorrect(User.builder()
                          .lastName("dohertys")
                          .email(randomWord() + "@gmail.com")
                          .phone("+" + randomNumber())
                          .password("Aa!304940940")
                          .build(),
            "can't be empty","name");

        userIncorrect(User.builder()
                          .firstName("Virgina")
                          .email(randomWord() + "@gmail.com")
                          .phone("+" + randomNumber())
                          .password("Aa!304940940")
                          .build(),
            "can't be null","email");

        userIncorrect(User.builder()
                          .firstName("Test")
                          .lastName("perez")
                          .phone("+" + randomNumber())
                          .password("Aa!304940940")
                          .build(),
            "","email");

        userIncorrect(User.builder()
                          .firstName("Test")
                          .lastName("perez")
                          .email(randomWord() + "@gmail.com")
                          .password("Aa!304940940")
                          .build(),
            "","phone");

        userIncorrect(User.builder()
                          .firstName("Test")
                          .lastName("perez")
                          .email(randomWord() + "@gmail.com")
                          .phone("+" + randomNumber())
                          .build(),
            "","password");

        userIncorrect(User.builder()
                          .firstName("alejandroalejandroalejadddddddddddssssssssssssssssssssssssssssssssssssssssssssssssssssssssssddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddndroalejandroalejandroalejandroalejandroalejandroalejandroalejandroalejandroalejandroalejandroalejandroalejandroalejandro")
                          .lastName("perez")
                          .email(randomWord() + "@gmail.com")
                          .phone("+" + randomNumber())
                          .password("Aa!304940940")
                          .build(),
            "","message");
        userIncorrect(User.builder()
                          .firstName("Test_2")
                          .lastName("alejandroalejandroalejadddddddddddssssssssssssssssssssssssssssssssssssssssssssssssssssssssssddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddndroalejandroalejandroalejandroalejandroalejandroalejandroalejandroalejandroalejandroalejandroalejandroalejandroalejandro")
                          .email(randomWord() + "@gmail.com")
                          .phone("+" + randomNumber())
                          .password("Aa!304940940")
                          .build(),
            "","message");

        userIncorrect(User.builder()
                          .firstName("Test_2")
                          .lastName("perez")
                          .email("email_Invalid_email_Invalidemail_Invalid_email_Invalidemail_Invalid_email_Invalidemail_Invalid_email_Invalidemail_Invalid_email_Invalidemail_Invalid_email_Invalid_email_Invalid_email_Invalid_email_Invalid_email_Invalid_email_Invalid_email_Invalid_email_Invalid_email_Invalid_email_Invalid_email_Invalid_email_Invalid_email_InvalidInvalid_email_Invalid_email_Invalid_email_InvalidInvalid_email_Invalid_email_Invalid_email_InvalidInvalid_email_Invalid_email_Invalid_email_Invalid@gmail.com")
                          .phone("+" + randomNumber())
                          .password("Aa!304940940")
                          .build(),
            "","message");

        userIncorrect(User.builder()
                          .firstName("Test_2")
                          .lastName("perez")
                          .email(randomWord() + "@gmail.com")
                          .phone("+" + randomNumber())
                          .password("")
                          .build(),
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
        userIncorrect(User.builder()
                        .firstName(randomWord())
                        .lastName(randomWord())
                        .email(randomWord() + "@gmail.com")
                        .phone("+"+randomNumber())
                        .password(password)
                        .build(),
            "", "message");

    }
    public void userPasswordTestWrong(String password)
    {
        userIncorrect(User.builder()
                          .firstName(randomWord())
                          .lastName(randomWord())
                          .email(randomWord() + "@gmail.com")
                          .phone(String.valueOf(randomNumber()))
                          .password(password)
                          .build(),
            "", "message");
    }
    public void userIncorrect(User user,String message,String titleMensage) {
        String endpoint = baseurl+"/users/register";
        System.out.println(user.toString());
            given()
            .header("Content-Type", "application/json")
            .body(user.toString())
            .when()
            .post(endpoint).
            then().
            assertThat().
            statusCode(400);

        //.body(titleMensage, equalTo(message))given()
        //    .contentType(ContentType.JSON) // Set the content type header to application/json
        //    .body(jsonBody.toString())
       //
    }



}
