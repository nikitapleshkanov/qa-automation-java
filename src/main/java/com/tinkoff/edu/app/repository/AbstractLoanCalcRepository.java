package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.model.AbstractLoanRequest;
import com.tinkoff.edu.app.model.AbstractLoanResponse;

public abstract class AbstractLoanCalcRepository implements LoanCalcRepository {

    public abstract AbstractLoanResponse save(AbstractLoanRequest request);

}
