package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.enums.LoanRequestType;
import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.model.UuidLoanRequest;
import com.tinkoff.edu.app.model.UuidLoanResponse;

import java.util.List;
import java.util.UUID;

public interface LoanCalcService {

    UuidLoanResponse createLoanRequest(UuidLoanRequest request);

    LoanResponseType checkIfLoanAccepted(UuidLoanRequest request);

    LoanResponseType getRequestById(UUID uuid);

    List<UuidLoanResponse> getAllRequestsWithType(LoanRequestType type);

    void setStatusRequestById(UUID uuid, LoanResponseType status);

}
