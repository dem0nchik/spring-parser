package com.test.spring.parser;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class ResultValidation {
    private boolean isValid;
    private String error;
}
