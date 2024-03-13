package org.example;

import lombok.Builder;
import lombok.Data;
    @Data
    @Builder(toBuilder = true)
    public class UserDTO {
        String firstName;
        String lastName;
        String email;
        String password;
        String phone;
    }

