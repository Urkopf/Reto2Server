/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.servicios;

import crud.ejb.IGestorEntidadesLocal;
import crud.entidades.PedidoArticulo;
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
 * RESTful Web Service para operaciones CRUD sobre la entidad PedidoArticulo.
 * <p>
 * Este servicio expone endpoints para crear, actualizar, eliminar, buscar (por
 * ID y en rango) y contar los registros de PedidoArticulo en la base de datos.
 * Los datos se manejan en formatos XML y JSON.
 * </p>
 * <p>
 * Se utiliza inyección de dependencias (EJB) para interactuar con la capa de
 * negocio a través de la interfaz IGestorEntidadesLocal, y se registra la
 * actividad mediante un Logger para facilitar el mantenimiento y la
 * identificación de errores.
 * </p>
 *
 * @author Urko
 */
@Path("pedidoarticulo")
public class PedidoArticuloFacadeREST {

    @EJB
    private IGestorEntidadesLocal ejb;

    private Logger LOGGER = Logger.getLogger(PedidoArticuloFacadeREST.class.getName());

    /**
     * Crea un nuevo registro de PedidoArticulo en la base de datos.
     *
     * @param entity La entidad PedidoArticulo a crear; no debe ser nula.
     * @throws BadRequestException Si la entidad es nula o está incompleta.
     * @throws InternalServerErrorException Si ocurre un error interno durante
     * la creación.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(PedidoArticulo entity) {
        try {
            if (entity == null) {
                throw new BadRequestException("El Pedido esta incompleto o vacio");
            }
            LOGGER.log(Level.INFO, "Creando pedido-artículo con ID {0}", entity.getId());
            ejb.createPedidoArticulo(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Actualiza un registro existente de PedidoArticulo en la base de datos.
     *
     * @param entity La entidad PedidoArticulo con los datos actualizados.
     * @throws InternalServerErrorException Si ocurre un error interno durante
     * la actualización.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(PedidoArticulo entity) {
        try {
            LOGGER.log(Level.INFO, "Actualizando pedido-artículo con ID {0}", entity.getId());
            // Ajustamos el ID del objeto al de la ruta
            ejb.updatePedidoArticulo(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Elimina un registro de PedidoArticulo de la base de datos.
     *
     * @param id El identificador del PedidoArticulo a eliminar.
     * @throws InternalServerErrorException Si ocurre un error interno durante
     * la eliminación.
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO, "Borrando pedido-artículo con ID {0}", id);
            PedidoArticulo pedidoArticulo = ejb.findPedidoArticulo(id);
            LOGGER.log(Level.INFO, "ENCONTRADO pedido-artículo con ID {0}", pedidoArticulo.getId());
            ejb.removePedidoArticulo(pedidoArticulo);
            LOGGER.log(Level.INFO, "BORRRADO pedido-artículo con ID {0}", id);
        } catch (ReadException | RemoveException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Busca y retorna un registro de PedidoArticulo por su ID.
     *
     * @param id El identificador del PedidoArticulo a buscar.
     * @return El objeto PedidoArticulo encontrado.
     * @throws InternalServerErrorException Si ocurre un error interno durante
     * la búsqueda.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public PedidoArticulo find(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO, "Buscando pedido-artículo con ID {0}", id);
            return ejb.findPedidoArticulo(id);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Retorna una lista de todos los registros de PedidoArticulo almacenados en
     * la base de datos.
     *
     * @return Una lista de objetos PedidoArticulo.
     * @throws InternalServerErrorException Si ocurre un error interno durante
     * la consulta.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PedidoArticulo> findAll() {
        try {
            LOGGER.log(Level.INFO, "Buscando todos los pedido-artículo.");
            return ejb.findAllPedidoArticulo();
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Retorna una sublista de registros de PedidoArticulo dentro de un rango de
     * índices.
     *
     * @param from El índice inicial (inclusive) de la sublista.
     * @param to El índice final (exclusivo) de la sublista.
     * @return Una lista de objetos PedidoArticulo en el rango especificado.
     * @throws InternalServerErrorException Si ocurre un error interno durante
     * la consulta.
     */
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PedidoArticulo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            LOGGER.log(Level.INFO, "Buscando pedido-artículo desde {0} hasta {1}", new Object[]{from, to});
            List<PedidoArticulo> lista = ejb.findAllPedidoArticulo();
            int size = lista.size();
            int start = (from != null && from >= 0 && from < size) ? from : 0;
            int end = (to != null && to <= size && to > start) ? to : size;
            return lista.subList(start, end);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Retorna el conteo total de registros de PedidoArticulo almacenados en la
     * base de datos.
     *
     * @return El número total de registros como String.
     * @throws InternalServerErrorException Si ocurre un error interno durante
     * el conteo.
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        try {
            LOGGER.info("Contando la cantidad total de pedido-artículo.");
            int count = ejb.findAllPedidoArticulo().size();
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
