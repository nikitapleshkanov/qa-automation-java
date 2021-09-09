package com.tinkoff.edu.app.flow;

import com.tinkoff.edu.app.model.UuidLoanResponse;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.UUID;

public class LoanRequestFlow {

    public static UuidLoanResponse getLoanResponseByUUID(HashMap<UUID, UuidLoanResponse> responses, UUID uuid) {
        if (responses.get(uuid) != null){
            return responses.get(uuid);
        } else throw new NoSuchElementException("Элемент с полученным id не найден");
    }

}
