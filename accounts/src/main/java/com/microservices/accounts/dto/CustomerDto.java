package com.microservices.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Customer",description = "Holds accounts and customer details")
public class CustomerDto {


    @Schema(description = "Name of customer", example = "Harry Jones")
    @NotEmpty(message = "Name cannot be empty or null.")
    private String name;
    @Schema(description = "Email  address of the customer", example = "Harry401@gmail.com")
    @NotEmpty(message = "Email cannot be empty or null.")
    @Email(message = "Enter a valid email value.")
    private String email;
    @Schema(description = "Phone number of the customer", example = "0712345678")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;
    @Schema(description = "Account details of the customer")
    private AccountsDto accountsDto;

}
