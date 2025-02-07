/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.servicios;

import crud.ejb.IGestorEntidadesLocal;
import crud.entidades.Cliente;
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
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * RESTful Web Service para operaciones CRUD sobre la entidad Cliente.
 * <p>
 * Este servicio expone endpoints para crear, actualizar, eliminar, buscar (por
 * ID y en rango) y contar los clientes almacenados en la base de datos. Los
 * datos se manejan en formatos XML y JSON.
 * </p>
 * <p>
 * Se utilizan excepciones específicas y se registra la actividad mediante un
 * Logger para facilitar la identificación de errores y el mantenimiento del
 * servicio.
 * </p>
 *
 * @author Urko
 */
@Path("cliente")
public class ClienteFacadeREST {

    @EJB
    private IGestorEntidadesLocal ejb;

    private Logger LOGGER = Logger.getLogger(ClienteFacadeREST.class.getName());

    /**
     * Crea un nuevo Cliente en la base de datos.
     *
     * @param entity La entidad Cliente a crear. Debe estar completa y no ser
     * nula.
     * @throws BadRequestException Si la entidad es nula o está incompleta.
     * @throws InternalServerErrorException Si ocurre un error interno al crear
     * el Cliente.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Cliente entity) {
        try {
            if (entity == null) {
                throw new BadRequestException("El Cliente esta incompleto o vacio");
            }
            LOGGER.log(Level.INFO, "Creando cliente con ID {0}", entity.getId());
            //ejb.createUsuario(entity);
            ejb.createCliente(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Actualiza un Cliente existente en la base de datos.
     *
     * @param entity La entidad Cliente con los datos actualizados. Se conserva
     * la contraseña existente.
     * @throws ReadException Si ocurre un error al leer el Cliente de la base de
     * datos.
     * @throws NotFoundException Si no se encuentra un Cliente con el ID
     * especificado.
     * @throws InternalServerErrorException Si ocurre un error interno al
     * actualizar el Cliente.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(Cliente entity) throws ReadException {
        try {
            LOGGER.log(Level.INFO, "Actualizando cliente con ID {0}", entity.getId());
            // Aseguramos que el ID del entity coincide con el de la ruta

            // Obtener el cliente actual de la base de datos
            Cliente clienteActual = ejb.findCliente(entity.getId());
            if (clienteActual == null) {
                throw new NotFoundException("Cliente no encontrado con ID: " + entity.getId());
            }

            // Mantener la contraseña existente
            entity.setContrasena(clienteActual.getContrasena());

            // Actualizar el cliente
            ejb.updateCliente(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Elimina un Cliente de la base de datos dado su ID.
     *
     * @param id El identificador del Cliente a eliminar.
     * @throws InternalServerErrorException Si ocurre un error interno al
     * eliminar el Cliente.
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO, "Borrando cliente con ID {0}", id);
            Cliente cliente = ejb.findCliente(id);
            ejb.removeCliente(cliente);
        } catch (ReadException | RemoveException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Busca y retorna un Cliente de la base de datos dado su ID.
     *
     * @param id El identificador del Cliente a buscar.
     * @return El Cliente encontrado.
     * @throws InternalServerErrorException Si ocurre un error interno al buscar
     * el Cliente.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Cliente find(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO, "Buscando cliente con ID {0}", id);
            return ejb.findCliente(id);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Retorna una lista con todos los Clientes almacenados en la base de datos.
     *
     * @return Una lista de Clientes.
     * @throws InternalServerErrorException Si ocurre un error interno al
     * obtener la lista de Clientes.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cliente> findAll() {
        try {
            LOGGER.log(Level.INFO, "Buscando todos los clientes.");
            return ejb.findAllCliente();
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Retorna una sublista de Clientes, definida por un rango de índices.
     *
     * @param from El índice inicial (inclusive).
     * @param to El índice final (exclusivo).
     * @return Una lista de Clientes dentro del rango especificado.
     * @throws InternalServerErrorException Si ocurre un error interno al
     * obtener la sublista.
     */
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cliente> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            LOGGER.log(Level.INFO, "Buscando clientes desde {0} hasta {1}", new Object[]{from, to});
            List<Cliente> clientes = ejb.findAllCliente();
            int size = clientes.size();
            int start = (from != null && from >= 0 && from < size) ? from : 0;
            int end = (to != null && to <= size && to > start) ? to : size;
            return clientes.subList(start, end);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Retorna el conteo total de Clientes almacenados en la base de datos.
     *
     * @return El número total de Clientes en formato de texto.
     * @throws InternalServerErrorException Si ocurre un error interno al contar
     * los Clientes.
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        try {
            LOGGER.info("Contando la cantidad total de clientes.");
            int count = ejb.findAllCliente().size();
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
