package com.microservices.accounts.controller;

import com.microservices.accounts.dto.CustomerDetailsDTO;
import com.microservices.accounts.dto.ErrorResponseDto;
import com.microservices.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(
        name = "REST API for Customers in EazyBank",
        description = "REST APIs in EazyBank to FETCH customer details"
)
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {


    private final ICustomerService  iCustomersService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);



    public CustomerController(ICustomerService iCustomersService) {
        this.iCustomersService = iCustomersService;
    }

    @Operation(
            summary = "Fetch Customer Details REST API",
            description = "REST API to fetch Customer details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )

    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDTO> fetchCustomerDetails(@RequestHeader("correlation-id") String correlationId,
                                                                   @RequestParam("mobileNumber") @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
        logger.debug("fetch customer detail method starts");
        CustomerDetailsDTO customerDetailsDto = iCustomersService.fetchCustomerDetails(mobileNumber, correlationId);
        logger.debug("fetch customer detail method ends");
        return ResponseEntity.status(HttpStatus.SC_OK).body(customerDetailsDto);

    }



}
