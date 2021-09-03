package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.model.UuidLoanResponse;

import java.util.ArrayList;
import java.util.UUID;

public interface LoanCalcRepository {

    UUID save();

    void saveResponse(UuidLoanResponse response);

    ArrayList<UuidLoanResponse> getResponses();

    void setStatusById(UUID uuid, LoanResponseType status);

}
