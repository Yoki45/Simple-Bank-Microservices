package com.microservices.accounts.service;

import com.microservices.accounts.dto.CustomerDetailsDTO;
import com.microservices.accounts.dto.CustomerDto;

public interface ICustomerService {

    CustomerDetailsDTO fetchCustomerDetails(String mobileNumber);

}
