package com.desafio.restapi.dao;

import com.desafio.restapi.models.Person;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PersonDAO {

    @PersistenceContext(unitName = "restapi_PU")
    EntityManager em;

    public List<Person> getAll() {
        return em.createNamedQuery("Person.findAll", Person.class).getResultList();
    }

    public Person findById(Long id) {
        return em.find(Person.class, id);
    }

    public void update(Person person) {
        em.merge(person);
    }

    public void create(Person person) {
        em.persist(person);
    }

    public void delete(Person person) {
        if (!em.contains(person)) {
            person = em.merge(person);
        }

        em.remove(person);
    }
}
