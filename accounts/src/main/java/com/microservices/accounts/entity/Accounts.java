package com.microservices.accounts.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Accounts extends BaseEntity{

    @Column(name="account_number")
    @Id
    private Long accountNumber;

    @Column(name="customer_id")
    private Long customerId;


    @Column(name="account_type")
    private String accountType;

    @Column(name="branch_address")
    private String branchAddress;


}
