package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.model.AbstractLoanRequest;
import com.tinkoff.edu.app.model.AbstractLoanResponse;

public interface LoanCalcService {

    AbstractLoanResponse createLoanRequest(AbstractLoanRequest request);

    LoanResponseType checkIfLoanAccepted(AbstractLoanRequest request);

}
