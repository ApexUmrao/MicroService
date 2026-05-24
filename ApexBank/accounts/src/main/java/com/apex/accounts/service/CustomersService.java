package com.apex.accounts.service;

import com.apex.accounts.dto.CustomerDetailsDto;

public interface CustomersService {

    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);

}


