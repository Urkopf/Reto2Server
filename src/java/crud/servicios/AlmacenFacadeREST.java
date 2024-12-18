/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.servicios;

import crud.ejb.IGestorEntidadesLocal;
import crud.entidades.Almacen;
import crud.excepciones.CreateException;
import crud.excepciones.ReadException;
import crud.excepciones.RemoveException;
import crud.excepciones.UpdateException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author 2dam
 */
@Path("almacen")
public class AlmacenFacadeREST {

    @EJB
    private IGestorEntidadesLocal ejb;

    private Logger LOGGER = Logger.getLogger(AlmacenFacadeREST.class.getName());

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Almacen entity) {
        try {
            LOGGER.log(Level.INFO, "Creando almacen {0}", entity.getId());
            ejb.createAlmacen(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(Almacen entity) {
        try {
            LOGGER.log(Level.INFO, "Actualizando almacen {0}", entity.getId());
            ejb.updateAlmacen(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO, "Borrando almacen {0}", id);
            ejb.removeAlmacen(ejb.findAlmacen(id));
        } catch (ReadException | RemoveException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Almacen find(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO, "Buscando almacen {0}", id);
            return ejb.findAlmacen(id);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Almacen> findAll() {
        try {
            LOGGER.log(Level.INFO, "Buscando todos los almacenes.");
            return ejb.findAllAlmacen();
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Almacen> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            LOGGER.log(Level.INFO, "Buscando almacenes desde {0} hasta {1}", new Object[]{from, to});
            List<Almacen> almacenes = ejb.findAllAlmacen();
            // Controlamos el rango, evitando indices fuera de los límites.
            int size = almacenes.size();
            int start = (from != null && from >= 0 && from < size) ? from : 0;
            int end = (to != null && to <= size && to > start) ? to : size;
            return almacenes.subList(start, end);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        try {
            LOGGER.info("Contando la cantidad total de almacenes.");
            int count = ejb.findAllAlmacen().size();
            return String.valueOf(count);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

}
