/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.servicios;

import crud.ejb.IGestorEntidadesLocal;
import crud.entidades.Usuario;
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
 * Servicio REST para gestionar los usuarios. Ofrece operaciones de creación,
 * lectura, actualización y borrado, así como funcionalidades adicionales para
 * inicio de sesión, cambio y recuperación de contraseñas.
 */
@Path("usuario")
public class UsuarioFacadeREST {

    @EJB
    private IGestorEntidadesLocal ejb;

    private Logger LOGGER = Logger.getLogger(UsuarioFacadeREST.class.getName());

    /**
     * Crea un usuario en la base de datos.
     *
     * @param entity Objeto {@link Usuario} que se desea persistir.
     * @throws BadRequestException Si el objeto {@code entity} es nulo o está
     * incompleto.
     * @throws InternalServerErrorException Si ocurre algún problema al
     * persistir la entidad.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Usuario entity) {
        try {
            if (entity == null) {
                throw new BadRequestException("El Usuario esta incompleto o vacio");
            }
            LOGGER.log(Level.INFO, "Creando usuario con ID {0}", entity.getId());
            ejb.createUsuario(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Actualiza los datos de un usuario específico.
     *
     * @param entity Objeto {@link Usuario} que contiene la información
     * actualizada.
     * @throws InternalServerErrorException Si ocurre un problema al realizar la
     * actualización.
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(Usuario entity) {
        try {
            LOGGER.log(Level.INFO, "Actualizando usuario con ID {0}", entity.getId());
            ejb.updateUsuario(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Elimina un usuario de la base de datos según su ID.
     *
     * @param id Identificador del usuario a eliminar.
     * @throws InternalServerErrorException Si ocurre un problema durante la
     * eliminación.
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO, "Borrando usuario con ID {0}", id);
            Usuario usuario = ejb.findUsuario(id);
            ejb.removeUsuario(usuario);
        } catch (ReadException | RemoveException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Busca un usuario por su identificador.
     *
     * @param id Identificador del usuario a consultar.
     * @return Objeto {@link Usuario} correspondiente al ID proporcionado.
     * @throws InternalServerErrorException Si ocurre algún problema al buscar
     * los datos.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Usuario find(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO, "Buscando usuario con ID {0}", id);
            return ejb.findUsuario(id);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Inicia sesión con los datos del {@link Usuario} proporcionado. Devuelve
     * un objeto que puede ser de tipo Cliente o Trabajador.
     *
     * @param usuario Objeto {@link Usuario} con la información de login.
     * @return El objeto correspondiente (Cliente o Trabajador) si la
     * autenticación es válida.
     * @throws InternalServerErrorException Si ocurre algún problema en la
     * validación.
     */
    @POST
    @Path("sesion")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Object inicioSesion(Usuario usuario) {
        Object resultado = null;
        try {
            LOGGER.log(Level.INFO, "Buscando usuario: {0}", usuario.getCorreo());
            resultado = ejb.inicioSesion(usuario); // Devuelve Cliente o Trabajador
            ((Usuario) resultado).ocultarContraseña();
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
        return resultado;
    }

    /**
     * Cambia la contraseña de un usuario tras validar su contraseña anterior.
     *
     * @param usuario Objeto {@link Usuario} que contiene la contraseña antigua
     * y la nueva.
     * @throws InternalServerErrorException Si ocurre algún problema al
     * actualizar la contraseña.
     */
    @POST
    @Path("cambiar")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public void cambiar(Usuario usuario) {
        try {
            LOGGER.log(Level.INFO, "Cambiando contrasena");
            ejb.cambioPass(usuario);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Envía una nueva contraseña al correo del usuario, en caso de haber
     * olvidado la contraseña actual.
     *
     * @param usuario Objeto {@link Usuario} que contiene el correo registrado.
     * @throws InternalServerErrorException Si ocurre algún problema al procesar
     * la recuperación.
     */
    @POST
    @Path("recuperar")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public void recuperar(Usuario usuario) {
        try {
            LOGGER.log(Level.INFO, "Recuperando contrasena de usuario: {0}", usuario.getCorreo());
            ejb.recuperarPass(usuario);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Obtiene la lista completa de usuarios.
     *
     * @return Lista de objetos {@link Usuario}.
     * @throws InternalServerErrorException Si ocurre algún problema al obtener
     * los datos.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> findAll() {
        try {
            LOGGER.log(Level.INFO, "Buscando todos los usuarios.");
            return ejb.findAllUsuario();
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Retorna una sublista de usuarios en un rango específico (índice inicial y
     * final).
     *
     * @param from Índice inicial (inclusivo).
     * @param to Índice final (exclusivo).
     * @return Lista de objetos {@link Usuario} en el rango solicitado.
     * @throws InternalServerErrorException Si ocurre algún problema al obtener
     * los datos.
     */
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            LOGGER.log(Level.INFO, "Buscando usuarios desde {0} hasta {1}", new Object[]{from, to});
            List<Usuario> usuarios = ejb.findAllUsuario();
            int size = usuarios.size();
            int start = (from != null && from >= 0 && from < size) ? from : 0;
            int end = (to != null && to <= size && to > start) ? to : size;
            return usuarios.subList(start, end);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error inesperado: {0}", ex.getMessage());
            throw new InternalServerErrorException("Error interno del servidor.");
        }
    }

    /**
     * Devuelve el número total de usuarios registrados.
     *
     * @return Representación en cadena del total de usuarios en la base de
     * datos.
     * @throws InternalServerErrorException Si ocurre algún problema al contar
     * los registros.
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        try {
            LOGGER.info("Contando la cantidad total de usuarios.");
            int count = ejb.findAllUsuario().size();
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
