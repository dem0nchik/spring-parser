package com.test.spring;

public class ResultValidation {
    private boolean isValid;
    private String error;

    public ResultValidation(boolean isValid, String error) {
        this.isValid = isValid;
        this.error = error;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getError() {
        return error;
    }
}
