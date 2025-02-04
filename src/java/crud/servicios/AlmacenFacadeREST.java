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
import javax.ws.rs.BadRequestException;
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
 * Servicio REST que gestiona operaciones CRUD sobre la entidad {@link Almacen}.
 * Provee métodos para crear, actualizar, eliminar y consultar almacenes.
 * También ofrece consultas de rangos y recuento de los registros existentes.
 */
@Path("almacen")
public class AlmacenFacadeREST {

    @EJB
    private IGestorEntidadesLocal ejb;

    private Logger LOGGER = Logger.getLogger(AlmacenFacadeREST.class.getName());

    /**
     * Crea un nuevo almacén en la base de datos.
     *
     * @param entity Objeto {@link Almacen} que se desea persistir.
     * @throws BadRequestException Si el objeto recibido es nulo o está
     * incompleto.
     * @throws InternalServerErrorException Si ocurre algún problema en la capa
     * de negocio o persistencia.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Almacen entity) {
        try {
            if (entity == null) {
                throw new BadRequestException("El Almacen esta incompleto o vacio");
            }
            LOGGER.log(Level.INFO, "Creando almacen {0}", entity.getId());
            ejb.createAlmacen(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Actualiza un almacén existente.
     *
     * @param entity Objeto {@link Almacen} con datos actualizados.
     * @throws InternalServerErrorException Si ocurre algún problema en la capa
     * de negocio o persistencia.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(Almacen entity) {
        try {
            LOGGER.log(Level.INFO, "Actualizando almacen {0}", entity.getId());
            ejb.updateAlmacen(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Elimina un almacén de la base de datos.
     *
     * @param id Identificador del almacén a eliminar.
     * @throws InternalServerErrorException Si ocurre algún problema en la capa
     * de negocio o persistencia.
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO, "Borrando almacen {0}", id);
            ejb.removeAlmacen(ejb.findAlmacen(id));
        } catch (ReadException | RemoveException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Busca un almacén por su identificador.
     *
     * @param id Identificador del almacén a buscar.
     * @return El objeto {@link Almacen} correspondiente al ID dado.
     * @throws InternalServerErrorException Si ocurre algún problema en la capa
     * de negocio o persistencia.
     */
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
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Obtiene la lista completa de almacenes.
     *
     * @return Lista de objetos {@link Almacen}.
     * @throws InternalServerErrorException Si ocurre algún problema en la capa
     * de negocio o persistencia.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Almacen> findAll() {
        try {
            LOGGER.log(Level.INFO, "Buscando todos los almacenes.");
            return ejb.findAllAlmacen();
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Obtiene la lista de almacenes que almacenan un artículo especificado por
     * ID.
     *
     * @param id Identificador del artículo cuyos almacenes se desean consultar.
     * @return Lista de objetos {@link Almacen} asociados al artículo.
     * @throws InternalServerErrorException Si ocurre algún problema en la capa
     * de negocio o persistencia.
     */
    @GET
    @Path("articulo/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Almacen> findAllById(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO, "Buscando todos los almacenes con id: {0}", id);
            return ejb.findAllArticuloById(id);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Obtiene una sublista de almacenes, determinada por los índices de inicio
     * (from) y fin (to).
     *
     * @param from Índice inicial (inclusivo).
     * @param to Índice final (exclusivo).
     * @return Sublista de objetos {@link Almacen} comprendidos en el rango
     * especificado.
     * @throws InternalServerErrorException Si ocurre algún problema en la capa
     * de negocio o persistencia.
     */
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
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Devuelve la cantidad total de almacenes existentes.
     *
     * @return Cadena de texto que representa el número total de almacenes.
     * @throws InternalServerErrorException Si ocurre algún problema en la capa
     * de negocio o persistencia.
     */
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
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

}
