/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.servicios;

import crud.ejb.IGestorEntidadesLocal;
import crud.entidades.Articulo;
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
@Path("articulo")
public class ArticuloFacadeREST {

    @EJB
    private IGestorEntidadesLocal ejb;

    private Logger LOGGER = Logger.getLogger(ArticuloFacadeREST.class.getName());

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Articulo entity) {
        try {
            LOGGER.log(Level.INFO, "Creando articulo con ID {0}", entity.getId());
            ejb.createArticulo(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(Articulo entity) {
        try {
            LOGGER.log(Level.INFO, "Actualizando articulo con ID {0}", entity.getId());
            // Aseguramos que el ID del entity coincida con el de la ruta

            ejb.updateArticulo(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO, "Borrando articulo con ID {0}", id);
            Articulo articulo = ejb.findArticulo(id);
            ejb.removeArticulo(articulo);
        } catch (ReadException | RemoveException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Articulo find(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO, "Buscando articulo con ID {0}", id);
            return ejb.findArticulo(id);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Articulo> findAll() {
        try {
            LOGGER.log(Level.INFO, "Buscando todos los articulos.");
            return ejb.findAllArticulo();
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Articulo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            LOGGER.log(Level.INFO, "Buscando articulos desde {0} hasta {1}", new Object[]{from, to});
            List<Articulo> articulos = ejb.findAllArticulo();
            // Controlamos el rango, evitando índices fuera de los límites
            int size = articulos.size();
            int start = (from != null && from >= 0 && from < size) ? from : 0;
            int end = (to != null && to <= size && to > start) ? to : size;
            return articulos.subList(start, end);
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
            LOGGER.info("Contando la cantidad total de articulos.");
            int count = ejb.findAllArticulo().size();
            return String.valueOf(count);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

}
