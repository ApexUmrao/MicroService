package com.apex.accounts.service.impl;

import com.apex.accounts.constants.AccountsConst;
import com.apex.accounts.dto.AccountsDto;
import com.apex.accounts.dto.CustomerDto;
import com.apex.accounts.entity.Accounts;
import com.apex.accounts.entity.Customer;
import com.apex.accounts.exception.CustomerAlreadyExistsException;
import com.apex.accounts.exception.ResourceNotFoundException;
import com.apex.accounts.mapper.AccountMapper;
import com.apex.accounts.mapper.CustomerMapper;
import com.apex.accounts.repository.AccountsRepo;
import com.apex.accounts.repository.CustomerRepo;
import com.apex.accounts.service.AccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements AccountsService {

    private CustomerRepo customerRepo;

    private AccountsRepo accountsRepo;

    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> reqCustomer = customerRepo.findByMobileNumber(customerDto.getMobileNumber());
        if (reqCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer with mobile number " + customerDto.getMobileNumber() + " already exists");
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Apex");
        Customer savedCustomer= customerRepo.save(customer);
        accountsRepo.save(createNewAccount(savedCustomer));
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Apex");
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConst.SAVINGS);
        newAccount.setBranchAddress(AccountsConst.ADDRESS);
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepo.findByMobileNumber(mobileNumber)
                                            .orElseThrow(() -> new ResourceNotFoundException("Customer","MobileNumber",mobileNumber));
        Accounts accounts = accountsRepo.findByCustomerId(customer.getCustomerId())
                                            .orElseThrow(() -> new ResourceNotFoundException("Accounts","CustomerID",customer.getCustomerId().toString()));
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
        customerDto.setAccountsDto(AccountMapper.mapToAccountsDto(accounts,new AccountsDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto !=null ){
            Accounts accounts = accountsRepo.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepo.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepo.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepo.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

}
