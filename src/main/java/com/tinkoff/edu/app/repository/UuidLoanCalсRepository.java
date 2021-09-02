package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.model.AbstractLoanRequest;
import com.tinkoff.edu.app.model.AbstractLoanResponse;
import com.tinkoff.edu.app.model.UuidLoanResponse;

import java.util.UUID;

public class UuidLoanCalсRepository extends AbstractLoanCalcRepository {

    @Override
    public AbstractLoanResponse save(AbstractLoanRequest request) {
        UUID requestUuid = UUID.randomUUID();
        UuidLoanResponse response = new UuidLoanResponse(UUID.randomUUID());
        return response;
    }

}
