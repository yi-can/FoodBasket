package com.voco_task.service;

import com.voco_task.repository.ICreditCardRepository;
import com.voco_task.repository.entity.CreditCard;
import com.voco_task.utility.JwtTokenManager;
import com.voco_task.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService extends ServiceManager<CreditCard, String> {

    private final ICreditCardRepository creditCardRepository;
    private final JwtTokenManager jwtTokenManager;

    public CreditCardService(ICreditCardRepository creditCardRepository, JwtTokenManager jwtTokenManager) {
        super(creditCardRepository);
        this.creditCardRepository = creditCardRepository;
        this.jwtTokenManager = jwtTokenManager;
    }
}
