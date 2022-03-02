package com.alerner.testBackEnd.utils;

public class Querys
{

    public Querys() {
    }

    public static final String CANDIDATE_FOR_TECHNOLOGY= "select c.id_candidate, c.type_of_document, c.first_name,c.last_name , c.document, c.date_of_birth, t.name, cft.years_of_experience from candidate_for_technology cft \n" +
            "inner join technology t on cft.id_technology = t.id_technology\n" +
            "inner join candidate c on cft.id_candidate = c.id_candidate\n" +
            "where t.name like %?1%";
}
