package com.tinkoff.edu.app;

public class LoanCalcRepository {

    public static int requestId;

    public static int save(){
        return ++requestId;
    }
}
