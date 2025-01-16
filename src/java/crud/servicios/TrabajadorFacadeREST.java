/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.servicios;

import crud.ejb.IGestorEntidadesLocal;
import crud.entidades.Trabajador;
import crud.excepciones.CreateException;
import crud.excepciones.ReadException;
import crud.excepciones.RemoveException;
import crud.excepciones.UpdateException;
import java.util.List;
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
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

/**
 * Servicio REST para la entidad Trabajador.
 */
@Path("trabajador")
public class TrabajadorFacadeREST {

    @EJB
    private IGestorEntidadesLocal ejb;

    private Logger LOGGER = Logger.getLogger(TrabajadorFacadeREST.class.getName());

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Trabajador trabajador) {
        try {
            LOGGER.log(Level.INFO, "Creando Trabajador con ID {0}", trabajador.getId());
            //ejb.createUsuario(trabajador);
            ejb.createTrabajador(trabajador);
        } catch (CreateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(Trabajador trabajador) throws ReadException {
        try {
            LOGGER.log(Level.INFO, "Actualizando Trabajador con ID {0}", trabajador.getId());
            Trabajador trabajadorActual = ejb.findTrabajador(trabajador.getId());
            if (trabajadorActual == null) {
                throw new NotFoundException("Cliente no encontrado con ID: " + trabajador.getId());
            }

            // Mantener la contraseña existente
            trabajador.setContrasena(trabajadorActual.getContrasena());

            ejb.updateTrabajador(trabajador);
        } catch (UpdateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO, "Borrando Trabajador con ID {0}", id);
            // Primero encontramos el trabajador a eliminar
            Trabajador trabajador = ejb.findTrabajador(id);
            // Se asume que remover un Trabajador implica remover también al Usuario.
            //ejb.removeUsuario(trabajador);
            ejb.removeTrabajador(trabajador);
        } catch (ReadException | RemoveException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO, "Buscando Trabajador con ID {0}", id);
            Trabajador trabajador = ejb.findTrabajador(id);
            if (trabajador == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Trabajador con el ID: " + id)
                        .build();
            }
            return Response.ok(trabajador).build();
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Trabajador> findAll() {
        try {
            LOGGER.log(Level.INFO, "Buscando todos los Trabajadores");
            return ejb.findAllTrabajador();
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Trabajador> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            LOGGER.log(Level.INFO, "Buscando Trabajadores desde {0} hasta {1}", new Object[]{from, to});
            List<Trabajador> lista = ejb.findAllTrabajador();
            int size = lista.size();
            int start = (from != null && from >= 0 && from < size) ? from : 0;
            int end = (to != null && to <= size && to > start) ? to : size;
            return lista.subList(start, end);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        try {
            LOGGER.info("Contando la cantidad total de Trabajadores.");
            int count = ejb.findAllTrabajador().size();
            return String.valueOf(count);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

}
