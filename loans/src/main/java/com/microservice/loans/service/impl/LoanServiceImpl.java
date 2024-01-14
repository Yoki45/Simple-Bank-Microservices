package com.microservice.loans.service.impl;

import com.microservice.loans.Mapper.LoanMapper;
import com.microservice.loans.constants.LoanConstants;
import com.microservice.loans.dto.LoanDTO;
import com.microservice.loans.entity.Loan;
import com.microservice.loans.exception.LoanAlreadyExistException;
import com.microservice.loans.exception.ResourceNotFoundException;
import com.microservice.loans.repository.LoanRepository;
import com.microservice.loans.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;


@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private LoanRepository loanRepository;


    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loan> optionalLoans= loanRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()){
            throw new LoanAlreadyExistException("Loan already registered with given mobileNumber "+mobileNumber);
        }
        loanRepository.save(createNewLoan(mobileNumber));

    }


    private Loan createNewLoan(String mobileNumber) {
        Loan newLoan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }


    @Override
    public LoanDTO fetchLoan(String mobileNumber) {
        Loan loans = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        return LoanMapper.mapToLoansDto(loans, new LoanDTO());

    }


    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loan loans = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        loanRepository.deleteById(loans.getLoanId());
        return true;
    }



    @Override
    public boolean updateLoan(LoanDTO loansDto) {
        Loan loans = loanRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "LoanNumber", loansDto.getLoanNumber()));
        LoanMapper.mapToLoans(loansDto, loans);
        loanRepository.save(loans);
        return  true;
    }

}
