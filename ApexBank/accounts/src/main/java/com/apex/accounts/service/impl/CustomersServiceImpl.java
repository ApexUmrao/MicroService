package com.apex.accounts.service.impl;

import com.apex.accounts.repository.AccountsRepo;
import com.apex.accounts.repository.CustomerRepo;
import com.apex.accounts.service.CustomersService;
import com.apex.accounts.dto.AccountsDto;
import com.apex.accounts.dto.CardsDto;
import com.apex.accounts.dto.CustomerDetailsDto;
import com.apex.accounts.dto.LoansDto;
import com.apex.accounts.entity.Accounts;
import com.apex.accounts.entity.Customer;
import com.apex.accounts.exception.ResourceNotFoundException;
import com.apex.accounts.mapper.AccountMapper;
import com.apex.accounts.mapper.CustomerMapper;

import com.apex.accounts.service.client.CardsFeignClient;
import com.apex.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements CustomersService {

    private AccountsRepo accountsRepository;
    private CustomerRepo customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber,String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId,mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId,mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;

    }
}