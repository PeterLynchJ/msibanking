package com.msi.cards.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.msi.cards.model.Properties;
import com.msi.cards.config.CardsServiceConfig;
import com.msi.cards.model.Customer;
import com.msi.cards.model.Cards;
import com.msi.cards.repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardsController {
    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    private CardsServiceConfig cardsConfig;

    @PostMapping("/myCards")
    public List<Cards> getCards(@RequestBody Customer customer) {
        List<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
        return cards;
    }

    @GetMapping("/cards/properties")
    public String getCardsProperties() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(
                cardsConfig.getMsg(),
                cardsConfig.getBuildVersion(),
                cardsConfig.getMailDetails(),
                cardsConfig.getActiveBranches()
        );
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }
}
