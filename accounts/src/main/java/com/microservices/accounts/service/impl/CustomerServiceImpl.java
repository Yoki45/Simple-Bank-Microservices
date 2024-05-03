package com.microservices.accounts.service.impl;

import com.microservices.accounts.dto.AccountsDto;
import com.microservices.accounts.dto.CardsDTO;
import com.microservices.accounts.dto.CustomerDetailsDTO;
import com.microservices.accounts.dto.LoanDTO;
import com.microservices.accounts.entity.Accounts;
import com.microservices.accounts.entity.Customer;
import com.microservices.accounts.exception.ResourceNotFoundException;
import com.microservices.accounts.mapper.AccountsMapper;
import com.microservices.accounts.mapper.CustomerMapper;
import com.microservices.accounts.repository.AccountsRepository;
import com.microservices.accounts.repository.CustomerRepository;
import com.microservices.accounts.service.ICustomerService;
import com.microservices.accounts.service.client.CardsFeignClient;
import com.microservices.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl  implements ICustomerService {


    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    //Since we are accessing both cards and loans from different services- communication between services.
    @Qualifier("com.microservices.accounts.service.client.CardsFeignClient")
    private  CardsFeignClient cardsFeignClient;
    @Qualifier("com.microservices.accounts.service.client.LoansFeignClient")
    private LoansFeignClient loansFeignClient;


    @Override
    public CustomerDetailsDTO fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findCustomerByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findAccountsByCustomerId(customer.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getId().toString())
        );

        CustomerDetailsDTO customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDTO());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoanDTO> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber,correlationId);

       if(loansDtoResponseEntity != null && loansDtoResponseEntity.getBody() != null) {
           customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
       }

        ResponseEntity<CardsDTO> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber,correlationId);
       if(cardsDtoResponseEntity != null && cardsDtoResponseEntity.getBody() != null) {
           customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
       }

        return customerDetailsDto;
    }
}
