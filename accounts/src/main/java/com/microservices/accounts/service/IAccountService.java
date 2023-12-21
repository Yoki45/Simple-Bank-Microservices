package com.microservices.accounts.service;

import com.microservices.accounts.dto.CustomerDto;

public interface IAccountService {


    void createAccount(CustomerDto customerDto);

    CustomerDto fetchCustomerDetails(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);


}
