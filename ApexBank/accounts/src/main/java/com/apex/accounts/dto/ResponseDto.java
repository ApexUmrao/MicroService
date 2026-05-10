package com.apex.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Success Response",
        description = "Schema to hold Success Response information")
public class ResponseDto {

    @Schema(description = "Status Code of Response")
    private String statusCode;

    @Schema(description = "Status Message of Response")
    private String message;
}
