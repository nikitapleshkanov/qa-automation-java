package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.model.UuidLoanResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.UUID;

import static com.tinkoff.edu.app.flow.LoanRequestFlow.getLoanResponseByUUID;

/**
 * Describe data saving
 */
public class VariableLoanCalcRepository implements LoanCalcRepository {

    private HashMap<UUID, UuidLoanResponse> responses;

    File file = new File("responses.txt");
    FileOutputStream fileOutputStream;

    public VariableLoanCalcRepository() {
        this.responses = new HashMap<>();
    }

    public UUID save() {
        return UUID.randomUUID();
    }

    public void saveResponse(UuidLoanResponse response) {
        responses.put(response.getRequestId(), response);
        writeObjectToFile(response);
    }

    public HashMap<UUID, UuidLoanResponse> getResponses() {
        return responses;
    }

    public void setStatusById(UUID uuid, LoanResponseType status) throws NoSuchElementException {
        getLoanResponseByUUID(responses, uuid).setIsAccepted(status);
    }

    public void cleanFile() {
        file.delete();
    }

    private void writeObjectToFile(UuidLoanResponse response) {
        {
            try {
                fileOutputStream = new FileOutputStream(file, true);
                fileOutputStream.write(response.toString().getBytes());
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
