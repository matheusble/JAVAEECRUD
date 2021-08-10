package com.desafio.restapi.models;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "person",uniqueConstraints = {@UniqueConstraint(columnNames={"cnpj"})} )@NamedQueries({
        @NamedQuery(name = "Person.findAll", query = "SELECT t FROM Person t")
})

public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String comment;
    private String cnpj;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}