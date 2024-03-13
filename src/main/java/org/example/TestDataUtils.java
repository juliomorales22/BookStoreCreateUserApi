package org.example;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

public class TestDataUtils {
    public static String getRandomString(int length) {
        return randomAlphabetic(length);
    }

    public static String getRandomAlphanumeric(int length) {
        return randomAlphanumeric(length);
    }

    public static String getRandomInteger(int length) {
        return randomNumeric(length);
    }

    public static String createEmail(String firstName, String lastName) {
        return firstName + "_" + lastName + "@gmail.com";
    }
    public static UserDTO createUserDTO() {
        String firstName = getRandomString(15);
        String lastName = getRandomString(15);

        return UserDTO.builder().
                      firstName(firstName).
                      lastName(lastName).
                      email(createEmail(firstName, lastName)).
                      password(getRandomAlphanumeric(10)+"%").
                      phone("+"+getRandomInteger(8)).
                      build();
    }
    public static UserDTO createUserDTOOnwData(String firstName,String lastName,String email,String password,String phone) {
        return UserDTO.builder().
                      firstName(firstName).
                      lastName(lastName).
                      email(email).
                      password(password).
                      phone(phone).
                      build();
    }
}
