/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.ejb;

import crud.entidades.Almacen;
import crud.entidades.Articulo;
import crud.entidades.Cliente;
import crud.entidades.Pedido;
import crud.entidades.PedidoArticulo;
import crud.entidades.Trabajador;
import crud.entidades.Usuario;
import crud.excepciones.CreateException;
import crud.excepciones.ReadException;
import crud.excepciones.RemoveException;
import crud.excepciones.UpdateException;
import java.util.List;
import javax.ejb.Local;

/**
 * Interfaz local para la gestión de entidades.
 * <p>
 * Esta interfaz define los métodos CRUD (crear, actualizar, eliminar y
 * consultar) para las siguientes entidades: Usuario, Trabajador, Cliente,
 * Pedido, PedidoArticulo, Articulo y Almacen. Además, incluye métodos
 * personalizados para operaciones como inicio de sesión, cambio y recuperación
 * de contraseñas, y verificación de la existencia de correos.
 * </p>
 * <p>
 * Cada método lanza una excepción específica en caso de error, de acuerdo a la
 * operación que se esté realizando.
 * </p>
 *
 * @author 2dam
 */
@Local
public interface IGestorEntidadesLocal {

    //CREATES-----------------------------
    /**
     * Crea y persiste una nueva entidad Usuario en la base de datos.
     *
     * @param usuario el objeto {@link Usuario} a crear.
     * @throws CreateException si ocurre un error durante la creación.
     */
    public void createUsuario(Usuario usuario) throws CreateException;

    /**
     * Crea y persiste una nueva entidad Trabajador en la base de datos.
     *
     * @param trabajador el objeto {@link Trabajador} a crear.
     * @throws CreateException si ocurre un error durante la creación.
     */
    public void createTrabajador(Trabajador trabajador) throws CreateException;

    /**
     * Crea y persiste una nueva entidad Cliente en la base de datos.
     *
     * @param cliente el objeto {@link Cliente} a crear.
     * @throws CreateException si ocurre un error durante la creación.
     */
    public void createCliente(Cliente cliente) throws CreateException;

    /**
     * Crea y persiste un nuevo objeto Pedido en la base de datos.
     *
     * @param pedido el objeto {@link Pedido} a crear.
     * @throws CreateException si ocurre un error durante la creación.
     */
    public void createPedido(Pedido pedido) throws CreateException;

    /**
     * Crea y persiste un nuevo objeto PedidoArticulo en la base de datos.
     *
     * @param pedidoArticulo el objeto {@link PedidoArticulo} a crear.
     * @throws CreateException si ocurre un error durante la creación.
     */
    public void createPedidoArticulo(PedidoArticulo pedidoArticulo) throws CreateException;

    /**
     * Crea y persiste un nuevo objeto Articulo en la base de datos.
     *
     * @param articulo el objeto {@link Articulo} a crear.
     * @throws CreateException si ocurre un error durante la creación.
     */
    public void createArticulo(Articulo articulo) throws CreateException;

    /**
     * Crea y persiste un nuevo objeto Almacen en la base de datos.
     *
     * @param almacen el objeto {@link Almacen} a crear.
     * @throws CreateException si ocurre un error durante la creación.
     */
    public void createAlmacen(Almacen almacen) throws CreateException;

    //UPDATES---------------------------
    /**
     * Actualiza un objeto Usuario existente en la base de datos.
     *
     * @param usuario el objeto {@link Usuario} con los datos actualizados.
     * @throws UpdateException si ocurre un error durante la actualización.
     */
    public void updateUsuario(Usuario usuario) throws UpdateException;

    /**
     * Actualiza un objeto Trabajador existente en la base de datos.
     *
     * @param trabajador el objeto {@link Trabajador} con los datos
     * actualizados.
     * @throws UpdateException si ocurre un error durante la actualización.
     */
    public void updateTrabajador(Trabajador trabajador) throws UpdateException;

    /**
     * Actualiza un objeto Cliente existente en la base de datos.
     *
     * @param cliente el objeto {@link Cliente} con los datos actualizados.
     * @throws UpdateException si ocurre un error durante la actualización.
     */
    public void updateCliente(Cliente cliente) throws UpdateException;

    /**
     * Actualiza un objeto Pedido existente en la base de datos.
     *
     * @param pedido el objeto {@link Pedido} con los datos actualizados.
     * @throws UpdateException si ocurre un error durante la actualización.
     */
    public void updatePedido(Pedido pedido) throws UpdateException;

    /**
     * Actualiza un objeto PedidoArticulo existente en la base de datos.
     *
     * @param pedidoArticulo el objeto {@link PedidoArticulo} con los datos
     * actualizados.
     * @throws UpdateException si ocurre un error durante la actualización.
     */
    public void updatePedidoArticulo(PedidoArticulo pedidoArticulo) throws UpdateException;

    /**
     * Actualiza un objeto Articulo existente en la base de datos.
     *
     * @param articulo el objeto {@link Articulo} con los datos actualizados.
     * @throws UpdateException si ocurre un error durante la actualización.
     */
    public void updateArticulo(Articulo articulo) throws UpdateException;

    /**
     * Actualiza los detalles de un objeto Articulo en la base de datos.
     *
     * @param articulo el objeto {@link Articulo} con los detalles actualizados.
     * @throws UpdateException si ocurre un error durante la actualización.
     */
    public void updateArticuloDetalle(Articulo articulo) throws UpdateException;

    /**
     * Actualiza un objeto Almacen existente en la base de datos.
     *
     * @param almacen el objeto {@link Almacen} con los datos actualizados.
     * @throws UpdateException si ocurre un error durante la actualización.
     */
    public void updateAlmacen(Almacen almacen) throws UpdateException;

    //REMOVE------------------------
    /**
     * Elimina un objeto Usuario de la base de datos.
     *
     * @param usuario el objeto {@link Usuario} a eliminar.
     * @throws RemoveException si ocurre un error durante la eliminación.
     */
    public void removeUsuario(Usuario usuario) throws RemoveException;

    /**
     * Elimina un objeto Trabajador de la base de datos.
     *
     * @param trabajador el objeto {@link Trabajador} a eliminar.
     * @throws RemoveException si ocurre un error durante la eliminación.
     */
    public void removeTrabajador(Trabajador trabajador) throws RemoveException;

    /**
     * Elimina un objeto Cliente de la base de datos.
     *
     * @param cliente el objeto {@link Cliente} a eliminar.
     * @throws RemoveException si ocurre un error durante la eliminación.
     */
    public void removeCliente(Cliente cliente) throws RemoveException;

    /**
     * Elimina un objeto Pedido de la base de datos.
     *
     * @param pedido el objeto {@link Pedido} a eliminar.
     * @throws RemoveException si ocurre un error durante la eliminación.
     */
    public void removePedido(Pedido pedido) throws RemoveException;

    /**
     * Elimina un objeto PedidoArticulo de la base de datos.
     *
     * @param pedidoArticulo el objeto {@link PedidoArticulo} a eliminar.
     * @throws RemoveException si ocurre un error durante la eliminación.
     */
    public void removePedidoArticulo(PedidoArticulo pedidoArticulo) throws RemoveException;

    /**
     * Elimina un objeto Articulo de la base de datos.
     *
     * @param articulo el objeto {@link Articulo} a eliminar.
     * @throws RemoveException si ocurre un error durante la eliminación.
     */
    public void removeArticulo(Articulo articulo) throws RemoveException;

    /**
     * Elimina un objeto Almacen de la base de datos.
     *
     * @param almacen el objeto {@link Almacen} a eliminar.
     * @throws RemoveException si ocurre un error durante la eliminación.
     */
    public void removeAlmacen(Almacen almacen) throws RemoveException;

    //FIND-----------------------
    /**
     * Busca y retorna un objeto Usuario por su identificador.
     *
     * @param id el identificador del Usuario.
     * @return el objeto {@link Usuario} encontrado.
     * @throws ReadException si ocurre un error durante la consulta.
     */
    public Usuario findUsuario(Long id) throws ReadException;

    /**
     * Retorna una lista con todos los objetos Usuario de la base de datos.
     *
     * @return una lista de objetos {@link Usuario}.
     * @throws ReadException si ocurre un error durante la consulta.
     */
    public List<Usuario> findAllUsuario() throws ReadException;

    /**
     * Busca y retorna un objeto Trabajador por su identificador.
     *
     * @param id el identificador del Trabajador.
     * @return el objeto {@link Trabajador} encontrado.
     * @throws ReadException si ocurre un error durante la consulta.
     */
    public Trabajador findTrabajador(Long id) throws ReadException;

    /**
     * Retorna una lista con todos los objetos Trabajador de la base de datos.
     *
     * @return una lista de objetos {@link Trabajador}.
     * @throws ReadException si ocurre un error durante la consulta.
     */
    public List<Trabajador> findAllTrabajador() throws ReadException;

    /**
     * Busca y retorna un objeto Cliente por su identificador.
     *
     * @param id el identificador del Cliente.
     * @return el objeto {@link Cliente} encontrado.
     * @throws ReadException si ocurre un error durante la consulta.
     */
    public Cliente findCliente(Long id) throws ReadException;

    /**
     * Retorna una lista con todos los objetos Cliente de la base de datos.
     *
     * @return una lista de objetos {@link Cliente}.
     * @throws ReadException si ocurre un error durante la consulta.
     */
    public List<Cliente> findAllCliente() throws ReadException;

    /**
     * Busca y retorna un objeto Pedido por su identificador.
     *
     * @param id el identificador del Pedido.
     * @return el objeto {@link Pedido} encontrado.
     * @throws ReadException si ocurre un error durante la consulta.
     */
    public Pedido findPedido(Long id) throws ReadException;

    /**
     * Retorna una lista con todos los objetos Pedido de la base de datos.
     *
     * @return una lista de objetos {@link Pedido}.
     * @throws ReadException si ocurre un error durante la consulta.
     */
    public List<Pedido> findAllPedido() throws ReadException;

    /**
     * Busca y retorna un objeto PedidoArticulo por su identificador.
     *
     * @param id el identificador del PedidoArticulo.
     * @return el objeto {@link PedidoArticulo} encontrado.
     * @throws ReadException si ocurre un error durante la consulta.
     */
    public PedidoArticulo findPedidoArticulo(Long id) throws ReadException;

    /**
     * Retorna una lista con todos los objetos PedidoArticulo de la base de
     * datos.
     *
     * @return una lista de objetos {@link PedidoArticulo}.
     * @throws ReadException si ocurre un error durante la consulta.
     */
    public List<PedidoArticulo> findAllPedidoArticulo() throws ReadException;

    /**
     * Busca y retorna un objeto Articulo por su identificador.
     *
     * @param id el identificador del Articulo.
     * @return el objeto {@link Articulo} encontrado.
     * @throws ReadException si ocurre un error durante la consulta.
     */
    public Articulo findArticulo(Long id) throws ReadException;

    /**
     * Retorna una lista con todos los objetos Articulo de la base de datos.
     *
     * @return una lista de objetos {@link Articulo}.
     * @throws ReadException si ocurre un error durante la consulta.
     */
    public List<Articulo> findAllArticulo() throws ReadException;

    /**
     * Busca y retorna un objeto Almacen por su identificador.
     *
     * @param id el identificador del Almacen.
     * @return el objeto {@link Almacen} encontrado.
     * @throws ReadException si ocurre un error durante la consulta.
     */
    public Almacen findAlmacen(Long id) throws ReadException;

    /**
     * Retorna una lista con todos los objetos Almacen de la base de datos.
     *
     * @return una lista de objetos {@link Almacen}.
     * @throws ReadException si ocurre un error durante la consulta.
     */
    public List<Almacen> findAllAlmacen() throws ReadException;

    //PERSONALIZADAS-------------------
    /**
     * Realiza el inicio de sesión para un Usuario.
     *
     * @param usuario el objeto {@link Usuario} que contiene las credenciales de
     * inicio de sesión.
     * @return el objeto autenticado, que puede ser de tipo Cliente o
     * Trabajador.
     * @throws ReadException si ocurre un error durante la autenticación.
     */
    public Object inicioSesion(Usuario usuario) throws ReadException;

    /**
     * Cambia la contraseña de un Usuario.
     *
     * @param usuario el objeto {@link Usuario} que contiene la nueva
     * contraseña.
     * @throws ReadException si ocurre un error al cambiar la contraseña.
     */
    public void cambioPass(Usuario usuario) throws ReadException;

    /**
     * Verifica si el correo del Usuario ya existe en la base de datos.
     *
     * @param usuario el objeto {@link Usuario} cuyo correo se desea verificar.
     * @return {@code true} si el correo existe, {@code false} en caso
     * contrario.
     * @throws ReadException si ocurre un error durante la verificación.
     */
    public Boolean existeCorreo(Usuario usuario) throws ReadException;

    /**
     * Recupera la contraseña de un Usuario y la envía al correo registrado.
     *
     * @param usuario el objeto {@link Usuario} que contiene el correo
     * registrado.
     * @throws ReadException si ocurre un error durante el proceso de
     * recuperación.
     */
    public void recuperarPass(Usuario usuario) throws ReadException;

    /**
     * Retorna una lista de objetos Almacen asociados a un Articulo específico.
     *
     * @param id el identificador del Articulo.
     * @return una lista de objetos {@link Almacen} relacionados con el
     * Articulo.
     * @throws ReadException si ocurre un error durante la consulta.
     */
    public List<Almacen> findAllArticuloById(Long id) throws ReadException;

}
