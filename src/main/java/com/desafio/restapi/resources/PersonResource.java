package com.desafio.restapi.resources;

import com.desafio.restapi.dao.PersonDAO;
import com.desafio.restapi.models.Person;
import com.desafio.restapi.resources.Exceptions.ResourceNotFoundException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("todos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    PersonDAO personDAO;

    @GET
    public Response getAll() {
        return Response.ok(personDAO.getAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getTodo(@PathParam("id") Long id) {
        Person person = personDAO.findById(id);

        return Response.ok(person).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Person person) {
        try {
            Person updatePerson = personDAO.findById(id);
            updatePerson.setName(person.getName());
            updatePerson.setCnpj(person.getCnpj());
            updatePerson.setComment(person.getComment());
            updatePerson.setEmail(person.getEmail());
            personDAO.update(updatePerson);
            personDAO.update(updatePerson);

            return Response.ok().build();
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @POST
    public Response create(Person person) {
        personDAO.create(person);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            Person getPerson = personDAO.findById(id);

            personDAO.delete(getPerson);

            return Response.ok().build();
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }

    }
}
