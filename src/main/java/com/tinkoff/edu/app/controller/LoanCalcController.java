package com.tinkoff.edu.app.controller;

import com.tinkoff.edu.app.logger.LoanCalcLogger;
import com.tinkoff.edu.app.model.UuidLoanRequest;
import com.tinkoff.edu.app.model.UuidLoanResponse;
import com.tinkoff.edu.app.service.LoanCalcService;

/**
 * Describe working with api
 */
public class LoanCalcController {

    private LoanCalcService service;

    public LoanCalcController(LoanCalcService service) {
        this.service = service;

    }

    public UuidLoanResponse createRequest(UuidLoanRequest request) {
        UuidLoanResponse loanResponse = service.createLoanRequest(request);
        LoanCalcLogger.log(loanResponse);
        return loanResponse;
    }

}
