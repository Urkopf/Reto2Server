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
 * Clase de servicio REST para gestionar los artículos en la aplicación. Provee
 * operaciones de creación, lectura, actualización y borrado de registros de la
 * entidad {@link Articulo}.
 * <p>
 * Maneja internamente excepciones de creación, lectura, actualización y borrado
 * y las convierte en respuestas HTTP apropiadas.
 * </p>
 */
@Path("articulo")
public class ArticuloFacadeREST {

    @EJB
    private IGestorEntidadesLocal ejb;

    private Logger LOGGER = Logger.getLogger(ArticuloFacadeREST.class.getName());

    /**
     * Crea un nuevo artículo en la base de datos.
     *
     * @param entity Objeto {@link Articulo} que se desea persistir.
     * @throws BadRequestException Si el objeto {@code entity} es nulo o
     * incompleto.
     * @throws InternalServerErrorException Si ocurre un error interno durante
     * la creación.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Articulo entity) {
        try {
            if (entity == null) {
                throw new BadRequestException("El Articulo esta incompleto o vacio");
            }
            LOGGER.log(Level.INFO, "Creando articulo con ID {0}", entity.getId());
            ejb.createArticulo(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Actualiza un artículo existente en la base de datos.
     *
     * @param entity Objeto {@link Articulo} con los datos actualizados.
     * @throws InternalServerErrorException Si ocurre un error interno durante
     * la actualización.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(Articulo entity) {
        try {
            LOGGER.log(Level.INFO, "Actualizando articulo con ID {0}", entity.getId());
            // Aseguramos que el ID del entity coincida con el de la ruta

            ejb.updateArticulo(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Actualiza el detalle de un artículo existente. Se considera una
     * actualización distinta a la operación {@code edit(Articulo entity)}.
     *
     * @param entity Objeto {@link Articulo} que contiene la información
     * detallada a actualizar.
     * @throws InternalServerErrorException Si ocurre un error interno durante
     * la actualización.
     */
    @PUT
    @Path("detalle")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void editDetalles(Articulo entity) {
        try {
            LOGGER.log(Level.INFO, "Actualizando articulo DETALLES con ID {0}", entity.getId()
            );
            // Aseguramos que el ID del entity coincida con el de la ruta
            ejb.updateArticuloDetalle(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Elimina un artículo de la base de datos.
     *
     * @param id Identificador del artículo que se desea eliminar.
     * @throws InternalServerErrorException Si ocurre un error interno durante
     * la eliminación.
     */
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
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Busca un artículo por su identificador.
     *
     * @param id Identificador del artículo.
     * @return El objeto {@link Articulo} encontrado.
     * @throws InternalServerErrorException Si ocurre un error interno durante
     * la búsqueda.
     */
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
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Obtiene la lista de todos los artículos disponibles en la base de datos.
     *
     * @return Lista de objetos {@link Articulo}.
     * @throws InternalServerErrorException Si ocurre un error interno durante
     * la obtención.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Articulo> findAll() {
        try {
            LOGGER.log(Level.INFO, "Buscando todos los articulos.");
            return ejb.findAllArticulo();
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Obtiene un subconjunto de artículos en función de los índices
     * proporcionados. Utiliza la lista completa de artículos y retorna un rango
     * basado en posiciones.
     *
     * @param from Índice de inicio, inclusive.
     * @param to Índice de fin, exclusivo.
     * @return Lista de objetos {@link Articulo} que caen dentro del rango
     * solicitado.
     * @throws InternalServerErrorException Si ocurre un error interno durante
     * la obtención.
     */
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
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Cuenta el número total de artículos en la base de datos.
     *
     * @return Cadena que representa la cantidad total de artículos.
     * @throws InternalServerErrorException Si ocurre un error interno durante
     * la consulta.
     */
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
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

}
