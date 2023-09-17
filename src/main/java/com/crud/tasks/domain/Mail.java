package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor
public class Mail {
    private final String mailTo;
    private final Optional<String> mailToCc;
    private final String subject;
    private final String message;

}