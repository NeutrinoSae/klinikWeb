/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
import table.Booking;
import table.User;

/**
 *
 * @author SEED
 */
@Stateless
@Path("user")
public class UserFacadeREST extends AbstractFacade<User> {

    @PersistenceContext(unitName = "siklinikWebPU")
    private EntityManager em;

    public UserFacadeREST() {
        super(User.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createUser(User entity) {
        super.create(entity);
        return Response.status(Response.Status.CREATED).entity(entity).build();        
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, User entity) {
        super.edit(entity);
        return Response.status(Response.Status.ACCEPTED).entity(entity).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        User find = find(id);
        try {
            super.remove(find);            
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e).build();
        }
        return Response.status(Response.Status.GONE).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_XML)
    public Response loginREST(String body) {
        try {            
            String[] split = body.split("<xml>");
            String nama = split[0];
            System.out.println("nama = " + nama);
            String pass = split[1];
            System.out.println("pass = " + pass);
            Query createNativeQuery = 
                getEntityManager()
                        .createNativeQuery("SELECT * FROM `user` where username = '"
                                + nama
                                + "' and password = '"
                                + pass
                                + "'", User.class);
            User singleResult = (User) createNativeQuery.getSingleResult();
            
            if (singleResult.getLevel() == 2) {
                return Response
                        .status(403)
                        .header("level", singleResult.getLevel())
                        .header("userID", singleResult.getUserId())
                        .header("nama", singleResult.getNama())
                        .entity(singleResult)
                        .build();                   
            } else if (singleResult.getLevel() == 1) {
                return Response
                        .status(Response.Status.UNAUTHORIZED)
                        .header("level", singleResult.getLevel())
                        .header("userID", singleResult.getUserId())
                        .header("nama", singleResult.getNama())
                        .entity(singleResult)
                        .build();                   
            } else {
                return Response
                        .status(Response.Status.UNAUTHORIZED)
                        .header("level", singleResult.getLevel())
                        .header("userID", singleResult.getUserId())
                        .header("nama", singleResult.getNama())
                        .entity(singleResult)
                        .build();   
            }
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    

    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
