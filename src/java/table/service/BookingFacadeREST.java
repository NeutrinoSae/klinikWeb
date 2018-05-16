/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.service;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import table.Booking;
import table.Rubrik;

/**
 *
 * @author SEED
 */
@Stateless
@Path("booking")
public class BookingFacadeREST extends AbstractFacade<Booking> {

    @PersistenceContext(unitName = "siklinikWebPU")
    private EntityManager em;

    public BookingFacadeREST() {
        super(Booking.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createRubrik(Booking entity) {
        entity.setWaktuBooking(new Date());
        super.create(entity);
        return Response.status(Response.Status.CREATED).entity(entity).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id,@Context UriInfo uriInfo, Booking entity) {
            super.edit(entity);
            return Response.status(Response.Status.ACCEPTED).entity(entity).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        Booking find = find(id);
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
    public Booking find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Booking> findAll() {
        return super.findAll();
    }
    @GET
    @Path("today")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Booking> getTodayBooking() {        
        Query createNativeQuery = 
                getEntityManager()
                        .createNativeQuery("SELECT * FROM booking WHERE DATE(tanggal_booking) = DATE(curdate())"
                                + "ORDER BY booking.waktu_booking ASC"
                                , Booking.class);
        return createNativeQuery.getResultList();
    }
    @GET
    @Path("thisMonth")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Booking> getThisMonthBooking() {        
        Query createNativeQuery = 
                getEntityManager()
                        .createNativeQuery("SELECT * FROM booking WHERE MONTH(tanggal_booking) = MONTH(CURDATE())"
                                + "ORDER BY booking.waktu_booking ASC"
                                , Booking.class);
        return createNativeQuery.getResultList();
    }
    @GET
    @Path("thisWeek")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Booking> getThisWeekBooking() {
        Query createNativeQuery = 
                getEntityManager()
                        .createNativeQuery("SELECT * FROM booking WHERE WEEK(tanggal_booking) = WEEK(CURDATE())"
                                + "ORDER BY booking.waktu_booking ASC"
                                , Booking.class);
        return createNativeQuery.getResultList();
    }
    @GET
    @Path("myBooking/{dd}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Booking> getMyBooking(@PathParam("dd") String dd) {
        Query createNativeQuery = 
                getEntityManager()
                        .createNativeQuery("SELECT * FROM booking WHERE booking.id_user = "+dd
                                + " ORDER BY booking.waktu_booking ASC"
                                , Booking.class);
        return createNativeQuery.getResultList();
    }
    @GET
    @Path("antrian")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Booking> getAntrianBooking() {
        Query createNativeQuery = 
                getEntityManager()
                        .createNativeQuery("SELECT * FROM booking WHERE DATE(tanggal_booking) = DATE(curdate())"
                                + "AND status = 0 ORDER BY booking.waktu_booking ASC"
                                , Booking.class);
        return createNativeQuery.getResultList();
    }
    
    

    @GET
    @Path("{dd}/{mm}/{yyyy}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Booking> getDateBooking(
            @PathParam("dd") String dd,
            @PathParam("mm") String mm,
            @PathParam("yyyy") String yyyy
            ) {
        String tanggal = yyyy+"-"+mm+"-"+dd + " 00:00:00";
        
        System.out.println("tanggal = " + tanggal);
        Query createNativeQuery = 
                getEntityManager()
                        .createNativeQuery("SELECT * FROM booking WHERE  booking.tanggal_booking "
                                + "BETWEEN '"
                                + tanggal
                                + "' AND '"
                                + tanggal
                                + "'ORDER BY booking.waktu_booking ASC"
                                , Booking.class);
        return createNativeQuery.getResultList();    }
    
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Booking> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
