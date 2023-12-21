package com.microservices.accounts.repository;


import com.microservices.accounts.entity.Accounts;
import com.microservices.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository  extends JpaRepository<Accounts,Long> {

    Optional<Accounts> findAccountsByCustomerId(Long Id);

    void deleteAccountsByCustomerId(Long id);
}
