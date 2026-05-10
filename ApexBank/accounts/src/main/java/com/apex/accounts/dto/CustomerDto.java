package com.apex.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer",
        description = "Schema to hold Customer and Account information")
public class CustomerDto {

    @Schema(description = "Full Name of theCustomer" ,
            example = "Apex Uchiha")

    @NotEmpty(message = "Name can not be null or empty")
    @Size(min = 5, max = 30 , message = "Customer Name should be between 5 to 30")
    private String name;

    @Schema(description = "Email of the Customer" ,
            example = "Apex@Uchiha.com")

    @NotEmpty(message = "Email can not be null or empty")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(description = "Mobile Number of the Customer" ,
            example = "9876452311")

    @NotEmpty(message = "Mobile Number can not be null or empty")
    @Pattern(regexp = "($|[0-9]{10})", message = "Mobile Number must be 10 Digits")
    private String mobileNumber;

    private  AccountsDto accountsDto;
}
