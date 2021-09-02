package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.model.AbstractLoanRequest;
import com.tinkoff.edu.app.model.AbstractLoanResponse;

public interface LoanCalcRepository {

    AbstractLoanResponse save(AbstractLoanRequest request);
}
