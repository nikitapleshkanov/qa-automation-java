package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.model.UuidLoanRequest;
import com.tinkoff.edu.app.model.UuidLoanResponse;

import java.util.UUID;

public interface LoanCalcService {

    UuidLoanResponse createLoanRequest(UuidLoanRequest request);

    LoanResponseType checkIfLoanAccepted(UuidLoanRequest request);

    LoanResponseType getRequestById(UUID uuid);

    void setStatusRequestById(UUID uuid, LoanResponseType status);

}
