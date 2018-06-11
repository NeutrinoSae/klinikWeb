/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
import table.Rubrik;

/**
 *
 * @author SEED
 */
@Stateless
@Path("rubrik")
public class RubrikFacadeREST extends AbstractFacade<Rubrik> {

    @PersistenceContext(unitName = "siklinikWebPU")
    private EntityManager em;

    public RubrikFacadeREST() {
        super(Rubrik.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createRubrik(Rubrik entity) {
        entity.setTanggal(new Date());
        super.create(entity);
        return Response.status(Response.Status.CREATED).entity(entity).build();
    }

    
    

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id,@Context UriInfo uriInfo, Rubrik entity) {
            super.edit(entity);
            return Response.status(Response.Status.ACCEPTED).entity(entity).build();
    }
    
    

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        Rubrik find = find(id);
        try {
            super.remove(find);            
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e).build();
        }
        return Response.status(Response.Status.GONE).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Rubrik find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Rubrik> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Rubrik> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("random")
    @Produces(MediaType.TEXT_PLAIN)
    public String randomRubrik() {
        List<Rubrik> findAll = super.findAll();
        Collections.shuffle(findAll);
        Rubrik get = findAll.get(0);
        return get.toString();
    }
    @GET
    @Path("jadwal")
    @Produces(MediaType.TEXT_PLAIN)
    public String getJadwal() {
        Properties prop = new Properties();
	InputStream input = null;

	try {

		input = new FileInputStream("jadwal.properties");

		// load a properties file
		prop.load(input);

		// get the property value and print it out
		return (prop.getProperty("jadwal"));
	} catch (IOException ex) {
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
        return "null";
    }
    @PUT
    @Path("jadwal")
    @Consumes(MediaType.TEXT_PLAIN)
    public String setJadwal(String jadwal) {        
        Properties prop = new Properties();
	OutputStream output = null;

	try {

		output = new FileOutputStream("jadwal.properties");
		// set the properties value
		prop.setProperty("jadwal", jadwal);
		// save properties to project root folder
		prop.store(output, null);

	} catch (IOException io) {
		io.printStackTrace();
	} finally {
		if (output != null) {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
        return jadwal;
    }
    @GET
    @Path("pengumuman")
    @Produces(MediaType.TEXT_PLAIN)
    public String getPengumuman() {
        Properties prop = new Properties();
	InputStream input = null;

	try {

		input = new FileInputStream("pengumuman.properties");

		// load a properties file
		prop.load(input);

		// get the property value and print it out
		return (prop.getProperty("pengumuman"));
	} catch (IOException ex) {
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
        return "null";
    }
    @PUT
    @Path("pengumuman")
    @Consumes(MediaType.TEXT_PLAIN)
    public String setPengumuman(String body) {        
        Properties prop = new Properties();
	OutputStream output = null;

	try {

		output = new FileOutputStream("pengumuman.properties");
		// set the properties value
		prop.setProperty("pengumuman", body);
		// save properties to project root folder
		prop.store(output, null);

	} catch (IOException io) {
		io.printStackTrace();
	} finally {
		if (output != null) {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
        return body;
    }
    
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
