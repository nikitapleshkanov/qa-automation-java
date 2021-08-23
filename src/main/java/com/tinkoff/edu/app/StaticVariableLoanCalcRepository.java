package com.tinkoff.edu.app;

/**
 * Describe data saving
 */

public class StaticVariableLoanCalcRepository implements LoanCalcRepository {

    private static int requestId;

    public int save() {
        return ++requestId;
    }
}
