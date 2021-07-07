package com.test.spring;

import java.io.File;

public interface Validator {
    ResultValidation validate(File file);
}
