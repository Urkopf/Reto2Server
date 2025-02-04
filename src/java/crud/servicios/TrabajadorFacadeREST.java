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
import javax.ws.rs.core.Response;

/**
 * Servicio REST para la entidad Trabajador.
 * <p>
 * Este servicio expone operaciones CRUD (crear, actualizar, eliminar, buscar y
 * contar) para la entidad Trabajador. Los datos se intercambian en formatos XML
 * y JSON. Se utiliza inyección de dependencias (EJB) para interactuar con la
 * capa de negocio, y se emplea un Logger para registrar la actividad y
 * facilitar la depuración.
 * </p>
 *
 * @author Urko
 */
@Path("trabajador")
public class TrabajadorFacadeREST {

    @EJB
    private IGestorEntidadesLocal ejb;

    private Logger LOGGER = Logger.getLogger(TrabajadorFacadeREST.class.getName());

    /**
     * Crea un nuevo Trabajador en la base de datos.
     * <p>
     * Valida que la entidad no sea nula y luego llama al método del EJB para
     * crear el Trabajador. Se registra la operación y se manejan las
     * excepciones correspondientes.
     * </p>
     *
     * @param entity La entidad Trabajador a crear.
     * @throws BadRequestException si la entidad es nula o está incompleta.
     * @throws InternalServerErrorException si ocurre un error interno durante
     * la creación.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Trabajador entity) {
        try {
            if (entity == null) {
                throw new BadRequestException("El Trabajador esta incompleto o vacio");
            }
            LOGGER.log(Level.INFO, "Creando Trabajador con ID {0}", entity.getId());
            //ejb.createUsuario(trabajador);
            ejb.createTrabajador(entity);
        } catch (CreateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Actualiza un Trabajador existente en la base de datos.
     * <p>
     * Busca el Trabajador actual en la base de datos para mantener la
     * contraseña existente, luego actualiza la entidad utilizando el método del
     * EJB. Se registra la operación y se manejan las excepciones
     * correspondientes.
     * </p>
     *
     * @param trabajador La entidad Trabajador con los datos actualizados.
     * @throws NotFoundException si no se encuentra el Trabajador con el ID
     * proporcionado.
     * @throws InternalServerErrorException si ocurre un error interno durante
     * la actualización.
     * @throws ReadException si ocurre un error al leer la entidad.
     */
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
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Elimina un Trabajador de la base de datos.
     * <p>
     * Busca el Trabajador por su ID y, si es encontrado, lo elimina utilizando
     * el método del EJB. Se asume que remover un Trabajador implica remover
     * también al Usuario asociado. Se registra la operación y se manejan las
     * excepciones correspondientes.
     * </p>
     *
     * @param id El identificador del Trabajador a eliminar.
     * @throws InternalServerErrorException si ocurre un error interno durante
     * la eliminación.
     */
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
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Busca y retorna un Trabajador por su ID.
     * <p>
     * Utiliza el método del EJB para buscar el Trabajador. Si no se encuentra,
     * retorna una respuesta con el estado NOT_FOUND y un mensaje descriptivo.
     * Se registra la operación y se manejan las excepciones correspondientes.
     * </p>
     *
     * @param id El identificador del Trabajador a buscar.
     * @return Una respuesta HTTP que contiene el Trabajador encontrado o un
     * mensaje de error.
     * @throws InternalServerErrorException si ocurre un error interno durante
     * la búsqueda.
     */
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
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Retorna una lista de todos los Trabajadores almacenados en la base de
     * datos.
     * <p>
     * Utiliza el método del EJB para obtener todos los Trabajadores. Se
     * registra la operación y se manejan las excepciones correspondientes.
     * </p>
     *
     * @return Una lista de objetos Trabajador.
     * @throws InternalServerErrorException si ocurre un error interno al
     * obtener la lista.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Trabajador> findAll() {
        try {
            LOGGER.log(Level.INFO, "Buscando todos los Trabajadores");
            return ejb.findAllTrabajador();
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Retorna una sublista de Trabajadores definida por un rango de índices.
     * <p>
     * Obtiene todos los Trabajadores y retorna una sublista que va desde el
     * índice 'from' hasta el índice 'to'. Se registran los índices y se manejan
     * las excepciones correspondientes.
     * </p>
     *
     * @param from El índice inicial (inclusive) de la sublista.
     * @param to El índice final (exclusivo) de la sublista.
     * @return Una lista de objetos Trabajador en el rango especificado.
     * @throws InternalServerErrorException si ocurre un error interno al
     * obtener la sublista.
     */
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
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Retorna el número total de Trabajadores almacenados en la base de datos.
     * <p>
     * Cuenta la cantidad de Trabajadores utilizando el método del EJB y retorna
     * el conteo como una cadena de texto. Se registran las operaciones y se
     * manejan las excepciones correspondientes.
     * </p>
     *
     * @return El conteo total de Trabajadores en formato String.
     * @throws InternalServerErrorException si ocurre un error interno al contar
     * los Trabajadores.
     */
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
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

}
