package com.survey;

/**
 * Created by a.nigam on 16/04/17.
 */
public enum QuestionType {
    SINGLE_CHOICE("s"), MULTIPLE_CHOICE("mc");

    QuestionType(String mc) {
        type =mc;
    }

    public String getType() {
        return type;
    }

    private String type;
}
