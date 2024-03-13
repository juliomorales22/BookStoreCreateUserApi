package org.example.service;

import static org.example.configuration.RequestSpecificationConfig.getRequestSpecification;
import static io.restassured.RestAssured.given;

import org.example.UserDTO;
import io.restassured.response.Response;

public class UserService {

  public Response createUser(UserDTO user) {
    return given(getRequestSpecification()).
        body(user).post();
  }

   /*public Response readUser(UserDTO user) {
   return given(getRequestSpecification()).
        pathParam("username", user.getUsername()).
        get("{username}");
  }*/

  /*public Response updateUser(UserDTO user) {
    return given(getRequestSpecification()).
        body(user).
        pathParam("username", user.getUsername()).
        put("{username}");
  }*/

  /*public Response deleteUser(UserDTO user) {
    return given(getRequestSpecification()).
        pathParam("username", user.getUsername()).
        delete("{username}");
  }*/

  public static UserDTO parseJsonToUserDTO(Response response) {
    return response.as(UserDTO.class);
  }
}
