package com.tinkoff.edu.app;

/**
 * Describe data saving
 */

public class LoanCalcRepository {

    private static int requestId;

    public static int save() {
        return ++requestId;
    }
}
