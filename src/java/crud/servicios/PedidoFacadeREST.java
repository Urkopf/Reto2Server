/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.servicios;

import crud.ejb.IGestorEntidadesLocal;
import crud.entidades.Pedido;
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
 * RESTful Web Service para operaciones CRUD sobre la entidad Pedido.
 * <p>
 * Este servicio expone endpoints para crear, actualizar, eliminar, buscar (por
 * ID y en rango) y contar los pedidos almacenados en la base de datos. Los
 * datos se manejan en formatos XML y JSON.
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
@Path("pedido")
public class PedidoFacadeREST {

    @EJB
    private IGestorEntidadesLocal ejb;

    private Logger LOGGER = Logger.getLogger(PedidoFacadeREST.class.getName());

    /**
     * Crea un nuevo Pedido en la base de datos.
     * <p>
     * Verifica que la entidad no sea nula y, en caso contrario, llama al método
     * de creación del EJB. Se registra la operación y se gestionan las
     * excepciones correspondientes.
     * </p>
     *
     * @param entity La entidad Pedido a crear.
     * @throws BadRequestException si la entidad es nula o está incompleta.
     * @throws InternalServerErrorException si ocurre un error interno al crear
     * el Pedido.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Pedido entity) {
        try {
            if (entity == null) {
                throw new BadRequestException("El Pedido esta incompleto o vacio");
            }
            LOGGER.log(Level.INFO, "Creando pedido con ID {0}", entity.getId());
            ejb.createPedido(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Actualiza un Pedido existente en la base de datos.
     * <p>
     * Llama al método de actualización del EJB para modificar los datos del
     * Pedido. Se registra la operación y se gestionan las excepciones
     * correspondientes.
     * </p>
     *
     * @param entity La entidad Pedido con los datos actualizados.
     * @throws InternalServerErrorException si ocurre un error interno al
     * actualizar el Pedido.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(Pedido entity) {
        try {
            LOGGER.log(Level.INFO, "Actualizando pedido con ID {0}", entity.getId());
            ejb.updatePedido(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Elimina un Pedido de la base de datos.
     * <p>
     * Busca el Pedido por su ID y, si se encuentra, lo elimina llamando al
     * método correspondiente del EJB. Se registra la operación y se gestionan
     * las excepciones correspondientes.
     * </p>
     *
     * @param id El identificador del Pedido a eliminar.
     * @throws InternalServerErrorException si ocurre un error interno al
     * eliminar el Pedido.
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO, "Borrando pedido con ID {0}", id);
            Pedido pedido = ejb.findPedido(id);
            ejb.removePedido(pedido);
        } catch (ReadException | RemoveException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Busca y retorna un Pedido por su ID.
     * <p>
     * Llama al método de búsqueda del EJB para obtener el Pedido
     * correspondiente al ID proporcionado. Se registra la operación y se
     * gestionan las excepciones correspondientes.
     * </p>
     *
     * @param id El identificador del Pedido a buscar.
     * @return El Pedido encontrado.
     * @throws InternalServerErrorException si ocurre un error interno al buscar
     * el Pedido.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Pedido find(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO, "Buscando pedido con ID {0}", id);
            return ejb.findPedido(id);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Retorna una lista de todos los Pedidos almacenados en la base de datos.
     * <p>
     * Llama al método del EJB que retorna todos los Pedidos, registrando la
     * operación y gestionando las excepciones correspondientes.
     * </p>
     *
     * @return Una lista con todos los Pedidos.
     * @throws InternalServerErrorException si ocurre un error interno al
     * obtener la lista de Pedidos.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Pedido> findAll() {
        try {
            LOGGER.log(Level.INFO, "Buscando todos los pedidos.");
            return ejb.findAllPedido();
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Retorna una sublista de Pedidos definida por un rango de índices.
     * <p>
     * Obtiene todos los Pedidos y retorna una sublista que va desde el índice
     * 'from' hasta el índice 'to'. Se registran los índices y se gestionan las
     * excepciones correspondientes.
     * </p>
     *
     * @param from El índice inicial (inclusive) de la sublista.
     * @param to El índice final (exclusivo) de la sublista.
     * @return Una lista de Pedidos en el rango especificado.
     * @throws InternalServerErrorException si ocurre un error interno al
     * obtener la sublista de Pedidos.
     */
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Pedido> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            LOGGER.log(Level.INFO, "Buscando pedidos desde {0} hasta {1}", new Object[]{from, to});
            List<Pedido> pedidos = ejb.findAllPedido();
            int size = pedidos.size();
            int start = (from != null && from >= 0 && from < size) ? from : 0;
            int end = (to != null && to <= size && to > start) ? to : size;
            return pedidos.subList(start, end);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Retorna el número total de Pedidos almacenados en la base de datos.
     * <p>
     * Llama al método del EJB que retorna todos los Pedidos, calcula el tamaño
     * de la lista y lo retorna como una cadena de texto. Se gestionan las
     * excepciones correspondientes.
     * </p>
     *
     * @return El conteo total de Pedidos en formato String.
     * @throws InternalServerErrorException si ocurre un error interno al contar
     * los Pedidos.
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        try {
            LOGGER.info("Contando la cantidad total de pedidos.");
            int count = ejb.findAllPedido().size();
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
