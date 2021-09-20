package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.model.UuidLoanResponse;

import java.util.HashMap;
import java.util.UUID;

public interface LoanCalcRepository {

    UUID save();

    void cleanFile();

    void saveResponse(UuidLoanResponse response);

    HashMap<UUID, UuidLoanResponse> getResponses();

    void setStatusById(UUID uuid, LoanResponseType status);

}
