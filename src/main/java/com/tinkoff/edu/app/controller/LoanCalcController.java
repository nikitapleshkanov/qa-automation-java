package com.tinkoff.edu.app.controller;

import com.tinkoff.edu.app.logger.LoanCalcLogger;
import com.tinkoff.edu.app.model.AbstractLoanRequest;
import com.tinkoff.edu.app.model.AbstractLoanResponse;
import com.tinkoff.edu.app.service.LoanCalcService;

/**
 * Describe working with api
 */

public class LoanCalcController {

    private LoanCalcService service;

    public LoanCalcController(LoanCalcService service) {
        this.service = service;

    }

    public AbstractLoanResponse createRequest(AbstractLoanRequest request) {
        AbstractLoanResponse loanResponse = service.createLoanRequest(request);
        LoanCalcLogger.log(loanResponse);
        return loanResponse;
    }

}
