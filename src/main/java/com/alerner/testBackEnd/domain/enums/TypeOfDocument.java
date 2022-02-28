package com.alerner.testBackEnd.domain.enums;

public enum TypeOfDocument
{
    DNI("DNI"),
    LE("LE"),
    LC("LC");

    private String description;

    TypeOfDocument(String description) {
        this.description = description;
    }

    public String findType()
    {
        return description;
    }
}
