package com.tinkoff.edu.app;

/**
 * Describe data saving
 */
public class VariableLoanCalcRepository implements LoanCalcRepository {

    private int requestId;

    public VariableLoanCalcRepository(int requestId) {
        this.requestId = requestId;
    }

    public VariableLoanCalcRepository() {
        this(0);
    }

    public int save() {
        return ++requestId;
    }
}
