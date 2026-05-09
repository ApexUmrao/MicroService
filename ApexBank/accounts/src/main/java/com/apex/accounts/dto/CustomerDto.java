package com.apex.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "Name can not be null or empty")
    @Size(min = 5, max = 30 , message = "Customer Name should be between 5 to 30")
    private String name;

    @NotEmpty(message = "Email can not be null or empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Mobile Number can not be null or empty")
    @Pattern(regexp = "($|[0-9]{10})", message = "Mobile Number must be 10 Digits")
    private String mobileNumber;

    private  AccountsDto accountsDto;
}
