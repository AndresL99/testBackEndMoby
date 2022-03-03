package com.alerner.testBackEnd.utils;

public class Querys
{

    private Querys() {
        throw new IllegalStateException("Utility class");
    }

    public static final String CANDIDATE_FOR_TECHNOLOGY = "SELECT c.id_candidate, c.type_of_document, c.first_name,c.last_name , c.document, c.date_of_birth, t.name, cft.years_of_experience FROM candidate_for_technology AS cft " +
            "INNER JOIN technology AS t ON cft.id_technology = t.id_technology " +
            "INNER JOIN candidate AS c ON cft.id_candidate = c.id_candidate " +
            "WHERE t.name like '%?1%'";
}
