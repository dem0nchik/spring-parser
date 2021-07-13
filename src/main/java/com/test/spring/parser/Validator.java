package com.test.spring.parser;

import java.io.File;

public interface Validator {
    ResultValidation validate(File file);
}
