package com.microservice.cards.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
@AllArgsConstructor
public class ResponseDTO {


    @Schema(
            description = "Status code in the response"
    )
    private String code;

    @Schema(
            description = "Status message in the response"
    )
    private String message;
}
