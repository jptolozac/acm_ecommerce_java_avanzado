package com.acm.ecommerce.dto;

public class UserDTO {

    public record UserRequest(
            String name,
            String address,
            String email,
            String password
    ){}

    public record UserResponse(
            Long id,
            String name,
            String address,
            String email
    ){}
}
