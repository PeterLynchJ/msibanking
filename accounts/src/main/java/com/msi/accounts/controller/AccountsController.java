package com.msi.accounts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.msi.accounts.config.AccountsServiceConfig;
import com.msi.accounts.model.Accounts;
import com.msi.accounts.model.Customer;
import com.msi.accounts.model.Properties;
import com.msi.accounts.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AccountsController {
    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private AccountsServiceConfig accountsConfig;

    @PostMapping("/myAccount")
    public Accounts getAccountDetails(@RequestBody Customer customer) {
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
        return accounts;
    }

    @GetMapping("/account/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(
                accountsConfig.getMsg(),
                accountsConfig.getBuildVersion(),
                accountsConfig.getMailDetails(),
                accountsConfig.getActiveBranches()
        );
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }
}
