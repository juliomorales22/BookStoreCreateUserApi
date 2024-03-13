package org.example;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.example.TestDataUtils.createUserDTO;
import static org.example.TestDataUtils.createUserDTOOnwData;
import static org.example.service.UserService.parseJsonToUserDTO;

import org.example.UserDTO;
import org.example.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApiTests {

  static UserDTO testUser = createUserDTO();
  static UserDTO userUpdated = testUser.toBuilder().
      email("ahuntoo@mail.com").
      password("98765").
      build();
  private final UserService userService = new UserService();


  @Order(1)
  @Test
  void createUserTest() {
    Assertions.assertThat(parseJsonToUserDTO(userService.createUser(testUser))).
              as("Tried to create new user on the API").
              isEqualTo(testUser);
    /* empty cases */
    comprobarIncorrecto(createUserDTOOnwData("",randomAlphabetic(10),randomAlphabetic(10)+"@gmail.com","1as2ae32%dsf","+"+randomNumeric(9)));
    comprobarIncorrecto(createUserDTOOnwData(randomAlphabetic(10),"",randomAlphabetic(10)+"@gmail.com","1as2ae32%dsf","+"+randomNumeric(9)));
    comprobarIncorrecto(createUserDTOOnwData(randomAlphabetic(10),randomAlphabetic(10),"","1as2ae32%dsf","+"+randomNumeric(9)));
    comprobarIncorrecto(createUserDTOOnwData(randomAlphabetic(10),randomAlphabetic(10),randomAlphabetic(10)+"@gmail.com","","+"+randomNumeric(9)));
    comprobarIncorrecto(createUserDTOOnwData(randomAlphabetic(10),randomAlphabetic(10),randomAlphabetic(10)+"@gmail.com","1as2ae32%dsf",""));
String varNull=null;/*null cases*/
    comprobarIncorrecto(createUserDTOOnwData(varNull,randomAlphabetic(10),randomAlphabetic(10)+"@gmail.com","1as2ae32%dsf","+"+randomNumeric(9)));
    comprobarIncorrecto(createUserDTOOnwData(randomAlphabetic(10),varNull,randomAlphabetic(10)+"@gmail.com","1as2ae32%dsf","+"+randomNumeric(9)));
    comprobarIncorrecto(createUserDTOOnwData(randomAlphabetic(10),randomAlphabetic(10),varNull,"1as2ae32%dsf","+"+randomNumeric(9)));
    comprobarIncorrecto(createUserDTOOnwData(randomAlphabetic(10),randomAlphabetic(10),randomAlphabetic(10)+"@gmail.com",varNull,"+"+randomNumeric(9)));
    comprobarIncorrecto(createUserDTOOnwData(randomAlphabetic(10),randomAlphabetic(10),randomAlphabetic(10)+"@gmail.com","1as2ae32%dsf",varNull));
    /* entra lenght cases */
    comprobarIncorrecto(createUserDTOOnwData(randomAlphabetic(256),randomAlphabetic(10),randomAlphabetic(10)+"@gmail.com","1as2ae32%dsf","+"+randomNumeric(9)));
    comprobarIncorrecto(createUserDTOOnwData(randomAlphabetic(10),randomAlphabetic(256),randomAlphabetic(10)+"@gmail.com","1as2ae32%dsf","+"+randomNumeric(9)));
    comprobarIncorrecto(createUserDTOOnwData(randomAlphabetic(10),randomAlphabetic(10),randomAlphabetic(256),"1as2ae32%dsf","+"+randomNumeric(9)));
    comprobarIncorrecto(createUserDTOOnwData(randomAlphabetic(10),randomAlphabetic(10),randomAlphabetic(10)+"@gmail.com",randomAlphabetic(256),"+"+randomNumeric(9)));
    comprobarIncorrecto(createUserDTOOnwData(randomAlphabetic(10),randomAlphabetic(10),randomAlphabetic(10)+"@gmail.com","1as2ae32%dsf",randomNumeric(256)));
    /* Wrong password*/
      comprobarIncorrecto(createUserDTOOnwData(randomAlphabetic(10),randomAlphabetic(10),randomAlphabetic(10)+"@gmail.com","1234","+"+randomNumeric(9)));
      comprobarIncorrecto(createUserDTOOnwData(randomAlphabetic(10),randomAlphabetic(10),randomAlphabetic(10)+"@gmail.com","12345678","+"+randomNumeric(9)));
      comprobarIncorrecto(createUserDTOOnwData(randomAlphabetic(10),randomAlphabetic(10),randomAlphabetic(10)+"@gmail.com","a2345678","+"+randomNumeric(9)));
      comprobarIncorrecto(createUserDTOOnwData(randomAlphabetic(10),randomAlphabetic(10),randomAlphabetic(10)+"@gmail.com","%2345678","+"+randomNumeric(9)));
      comprobarIncorrecto(createUserDTOOnwData(randomAlphabetic(10),randomAlphabetic(10),randomAlphabetic(10)+"@gmail.com","%2345a8","+"+randomNumeric(9)));
      comprobarIncorrecto(createUserDTOOnwData(randomAlphabetic(10),randomAlphabetic(10),randomAlphabetic(10)+"@gmail.com","123456&","+"+randomNumeric(9)));
      comprobarIncorrecto(createUserDTOOnwData(randomAlphabetic(10),randomAlphabetic(10),randomAlphabetic(10)+"@gmail.com","A12345&","+"+randomNumeric(9)));
      comprobarIncorrecto(createUserDTOOnwData(randomAlphabetic(10),randomAlphabetic(10),randomAlphabetic(10)+"@gmail.com","abcdef&","+"+randomNumeric(9)));
      comprobarIncorrecto(createUserDTOOnwData(randomAlphabetic(10),randomAlphabetic(10),randomAlphabetic(10)+"@gmail.com","&&!%%%7","+"+randomNumeric(9)));



  }
void comprobarIncorrecto(UserDTO testUser)
{
  Assertions.assertThat(parseJsonToUserDTO(userService.createUser(testUser))).
            as("Tried to create new user on the API").
            isEqualTo(testUser); //change
            //isEqualTo(testUser); //change
}
  @Order(2)
  @Test
  void readUserTest() {

   /* Assertions.assertThat(parseJsonToUserDTO(userService.readUser(testUser))).
        as("Tried to confirm data on created user").
        isEqualTo(testUser);*/
  }

  @Order(3)
  @Test
  void updateUserTest() {
/*
    Assertions.assertThat(parseJsonToUserDTO(userService.updateUser(userUpdated))).
        as("Tried to update data of the created user").
        isEqualTo(userUpdated);*/
  }

  @Order(4)
  @Test
  void deleteUserTest() {
/*
    Assertions.assertThat(userService.deleteUser(testUser).getStatusCode()).
        as("Tried to delete the created user").
        isEqualTo(200);*/
  }
}
