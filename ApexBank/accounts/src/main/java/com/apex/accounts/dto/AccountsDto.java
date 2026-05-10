package com.apex.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Accounts",
        description = "Schema to hold Customer and Account information")
public class AccountsDto {

    @Schema(description = "Account Number of the Customer",
            example = "3584346456" )

    @NotEmpty(message = "Account Number can not be null or empty")
    @Pattern(regexp = "($|[0-9]{10})", message = "Account Number must be 10 Digits")
    private Long accountNumber;

    @Schema(description = "Account Type of the Customer" ,
            example = "Savings")

    @NotEmpty(message = "Account Type can not be null or empty")
    private String accountType;

    @Schema(description = "Branch Address of the Customer" ,
            example = "Apex Bank")

    @NotEmpty(message = "Branch Address can not be null or empty")
    private String branchAddress;
}
