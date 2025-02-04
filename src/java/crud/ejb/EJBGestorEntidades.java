package crud.ejb;

import crud.entidades.Almacen;
import crud.entidades.Articulo;
import crud.entidades.Cliente;
import crud.entidades.Pedido;
import crud.entidades.PedidoArticulo;
import crud.entidades.Trabajador;
import crud.entidades.Usuario;
import static crud.enviocorreo.EnvioCorreos.enviar;
import crud.excepciones.CreateException;
import crud.excepciones.ReadException;
import crud.excepciones.RemoveException;
import crud.excepciones.UpdateException;
import static crud.seguridad.UtilidadesCifrado.cargarClavePrivada;
import static crud.seguridad.UtilidadesCifrado.descifrarConClavePrivada;
import static crud.seguridad.UtilidadesCifrado.generarContrasenaTemporal;
import static crud.seguridad.UtilidadesCifrado.hashearContraseña;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * Clase EJB que gestiona diversas operaciones CRUD sobre las entidades de la
 * aplicación, incluyendo usuarios, trabajadores, clientes, pedidos, artículos y
 * almacenes. Implementa la interfaz {@link IGestorEntidadesLocal}.
 * <p>
 * Provee métodos para crear, leer, actualizar y eliminar entidades, además de
 * operaciones específicas como el inicio de sesión, cambio y recuperación de
 * contraseñas.
 * </p>
 */
@Stateless
public class EJBGestorEntidades implements IGestorEntidadesLocal {

    /**
     * Logger para la clase.
     */
    private Logger LOGGER = Logger.getLogger(EJBGestorEntidades.class.getName());

    /**
     * EntityManager que administra las entidades en el contexto de
     * persistencia.
     */
    @PersistenceContext(unitName = "Reto2_CRUD_WebApplicationPU")
    private EntityManager em;

    /**
     * Crea un nuevo usuario en la base de datos.
     *
     * @param usuario Objeto {@link Usuario} a persistir.
     * @throws CreateException Si ocurre algún error durante la creación.
     */
    @Override
    public void createUsuario(Usuario usuario) throws CreateException {
        try {
            em.persist(usuario);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    /**
     * Crea un nuevo trabajador en la base de datos. El proceso incluye
     * desencriptar y hashear la contraseña.
     *
     * @param trabajador Objeto {@link Trabajador} a persistir.
     * @throws CreateException Si ocurre algún error al crear el trabajador.
     */
    @Override
    public void createTrabajador(Trabajador trabajador) throws CreateException {
        try {
            // Proceso de desencriptación y hashing de la contraseña
            procesarContrasenaUsuario(trabajador);
            // Persistir el trabajador con la contraseña hasheada
            em.persist(trabajador);
        } catch (Exception e) {
            throw new CreateException("Error al crear el trabajador: " + e.getMessage());
        }
    }

    /**
     * Crea un nuevo cliente en la base de datos. El proceso incluye
     * desencriptar y hashear la contraseña.
     *
     * @param cliente Objeto {@link Cliente} a persistir.
     * @throws CreateException Si ocurre algún error al crear el cliente.
     */
    @Override
    public void createCliente(Cliente cliente) throws CreateException {
        try {
            // Proceso de desencriptación y hashing de la contraseña
            procesarContrasenaUsuario(cliente);
            // Persistir el cliente con la contraseña hasheada
            em.persist(cliente);
        } catch (Exception e) {
            throw new CreateException("Error al crear el cliente: " + e.getMessage());
        }
    }

    /**
     * Crea un nuevo pedido en la base de datos.
     *
     * @param pedido Objeto {@link Pedido} a persistir.
     * @throws CreateException Si ocurre algún error durante la creación.
     */
    @Override
    public void createPedido(Pedido pedido) throws CreateException {
        try {
            em.persist(pedido);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    /**
     * Crea un nuevo pedido-artículo en la base de datos. Representa la relación
     * entre un pedido y un artículo.
     *
     * @param pedidoArticulo Objeto {@link PedidoArticulo} a persistir.
     * @throws CreateException Si ocurre algún error durante la creación.
     */
    @Override
    public void createPedidoArticulo(PedidoArticulo pedidoArticulo) throws CreateException {
        try {
            em.persist(pedidoArticulo);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    /**
     * Crea un nuevo artículo en la base de datos.
     *
     * @param articulo Objeto {@link Articulo} a persistir.
     * @throws CreateException Si ocurre algún error durante la creación.
     */
    @Override
    public void createArticulo(Articulo articulo) throws CreateException {
        try {
            em.persist(articulo);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    /**
     * Crea un nuevo almacén en la base de datos.
     *
     * @param almacen Objeto {@link Almacen} a persistir.
     * @throws CreateException Si ocurre algún error durante la creación.
     */
    @Override
    public void createAlmacen(Almacen almacen) throws CreateException {
        try {
            em.persist(almacen);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    /**
     * Actualiza un usuario existente en la base de datos.
     *
     * @param usuario Objeto {@link Usuario} que contiene los datos
     * actualizados.
     * @throws UpdateException Si ocurre algún error durante la actualización.
     */
    @Override
    public void updateUsuario(Usuario usuario) throws UpdateException {
        try {
            if (!em.contains(usuario)) {
                em.merge(usuario);
            }
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    /**
     * Actualiza un trabajador existente en la base de datos.
     *
     * @param trabajador Objeto {@link Trabajador} que contiene los datos
     * actualizados.
     * @throws UpdateException Si ocurre algún error durante la actualización.
     */
    @Override
    public void updateTrabajador(Trabajador trabajador) throws UpdateException {
        try {
            if (!em.contains(trabajador)) {
                em.merge(trabajador);
            }
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    /**
     * Actualiza un cliente existente en la base de datos.
     *
     * @param cliente Objeto {@link Cliente} que contiene los datos
     * actualizados.
     * @throws UpdateException Si ocurre algún error durante la actualización.
     */
    @Override
    public void updateCliente(Cliente cliente) throws UpdateException {
        try {
            if (!em.contains(cliente)) {
                em.merge(cliente);
            }
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    /**
     * Actualiza un pedido existente en la base de datos.
     *
     * @param pedido Objeto {@link Pedido} que contiene los datos actualizados.
     * @throws UpdateException Si ocurre algún error durante la actualización.
     */
    @Override
    public void updatePedido(Pedido pedido) throws UpdateException {
        try {
            if (!em.contains(pedido)) {
                em.merge(pedido);
            }
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    /**
     * Actualiza un pedido-artículo existente en la base de datos.
     *
     * @param pedidoArticulo Objeto {@link PedidoArticulo} que contiene los
     * datos actualizados.
     * @throws UpdateException Si ocurre algún error durante la actualización.
     */
    @Override
    public void updatePedidoArticulo(PedidoArticulo pedidoArticulo) throws UpdateException {
        try {
            if (!em.contains(pedidoArticulo)) {
                em.merge(pedidoArticulo);
            }
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    /**
     * Actualiza un artículo existente en la base de datos, gestionando también
     * la relación con almacenes si es necesario.
     *
     * @param articulo Objeto {@link Articulo} que contiene los datos
     * actualizados.
     * @throws UpdateException Si ocurre algún error durante la actualización.
     */
    @Override
    public void updateArticulo(Articulo articulo) throws UpdateException {
        try {
            if (null != articulo.getAlmacenTrump()) {
                articulo.setAlmacenes(new HashSet<>(articulo.getAlmacenTrump()));
            }
            LOGGER.log(Level.INFO, "Articulo tiene {0} numero de almacenes", articulo.getAlmacenes().size());
            articulo.setAlmacenes(articulo.getAlmacenes());
            LOGGER.log(Level.INFO, "Actualizando almacenes");
            if (!em.contains(articulo)) {
                em.merge(articulo);
            }
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    /**
     * Actualiza los detalles de un artículo existente, gestionando las
     * relaciones con los almacenes asociados.
     *
     * @param articulo Objeto {@link Articulo} que contiene la información de
     * detalles a actualizar.
     * @throws UpdateException Si ocurre algún error durante la actualización.
     */
    @Override
    public void updateArticuloDetalle(Articulo articulo) throws UpdateException {
        try {
            LOGGER.log(Level.INFO, "Articulo tiene {0} numero de alamacenes", articulo.getAlmacenes().size());

            for (Almacen almacen : articulo.getAlmacenes()) {
                LOGGER.log(Level.INFO, "Articulo tiene Almacen {0}", almacen.getId());
                if (!almacen.getArticulos().contains(articulo)) {
                    LOGGER.log(Level.INFO, "Almacen " + almacen.getId() + " tiene asignados " + almacen.getArticulos().size() + " articulos");
                    almacen.getArticulos().add(articulo);
                }
            }

            em.merge(articulo);
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    /**
     * Actualiza un almacén existente en la base de datos.
     *
     * @param almacen Objeto {@link Almacen} que contiene los datos
     * actualizados.
     * @throws UpdateException Si ocurre algún error durante la actualización.
     */
    @Override
    public void updateAlmacen(Almacen almacen) throws UpdateException {
        try {
            if (!em.contains(almacen)) {
                em.merge(almacen);
            }
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    /**
     * Elimina un usuario existente de la base de datos.
     *
     * @param usuario Objeto {@link Usuario} a eliminar.
     * @throws RemoveException Si ocurre algún error durante la eliminación.
     */
    @Override
    public void removeUsuario(Usuario usuario) throws RemoveException {
        try {
            em.remove(em.merge(usuario));
        } catch (Exception e) {
            throw new RemoveException(e.getMessage());
        }
    }

    /**
     * Elimina un trabajador existente de la base de datos.
     *
     * @param trabajador Objeto {@link Trabajador} a eliminar.
     * @throws RemoveException Si ocurre algún error durante la eliminación.
     */
    @Override
    public void removeTrabajador(Trabajador trabajador) throws RemoveException {
        try {
            em.remove(em.merge(trabajador));
        } catch (Exception e) {
            throw new RemoveException(e.getMessage());
        }
    }

    /**
     * Elimina un cliente existente de la base de datos.
     *
     * @param cliente Objeto {@link Cliente} a eliminar.
     * @throws RemoveException Si ocurre algún error durante la eliminación.
     */
    @Override
    public void removeCliente(Cliente cliente) throws RemoveException {
        try {
            em.remove(em.merge(cliente));
        } catch (Exception e) {
            throw new RemoveException(e.getMessage());
        }
    }

    /**
     * Elimina un pedido existente de la base de datos.
     *
     * @param pedido Objeto {@link Pedido} a eliminar.
     * @throws RemoveException Si ocurre algún error durante la eliminación.
     */
    @Override
    public void removePedido(Pedido pedido) throws RemoveException {
        try {
            em.remove(em.merge(pedido));
        } catch (Exception e) {
            throw new RemoveException(e.getMessage());
        }
    }

    /**
     * Elimina un pedido-artículo existente de la base de datos.
     *
     * @param pedidoArticulo Objeto {@link PedidoArticulo} a eliminar.
     * @throws RemoveException Si ocurre algún error durante la eliminación.
     */
    @Override
    public void removePedidoArticulo(PedidoArticulo pedidoArticulo) throws RemoveException {
        try {
            if (!em.contains(pedidoArticulo)) {
                pedidoArticulo = em.merge(pedidoArticulo);
            }
            em.remove(pedidoArticulo);
            em.flush();
        } catch (Exception e) {
            throw new RemoveException(e.getMessage());
        }
    }

    /**
     * Elimina un artículo existente de la base de datos.
     *
     * @param articulo Objeto {@link Articulo} a eliminar.
     * @throws RemoveException Si ocurre algún error durante la eliminación.
     */
    @Override
    public void removeArticulo(Articulo articulo) throws RemoveException {
        try {
            em.remove(em.merge(articulo));
        } catch (Exception e) {
            throw new RemoveException(e.getMessage());
        }
    }

    /**
     * Elimina un almacén existente de la base de datos.
     *
     * @param almacen Objeto {@link Almacen} a eliminar.
     * @throws RemoveException Si ocurre algún error durante la eliminación.
     */
    @Override
    public void removeAlmacen(Almacen almacen) throws RemoveException {
        try {
            em.remove(em.merge(almacen));
        } catch (Exception e) {
            throw new RemoveException(e.getMessage());
        }
    }

    /**
     * Busca un usuario por su ID.
     *
     * @param id Identificador del usuario.
     * @return Objeto {@link Usuario} encontrado.
     * @throws ReadException Si ocurre algún error al leer los datos.
     */
    @Override
    public Usuario findUsuario(Long id) throws ReadException {
        Usuario usuario;
        try {
            usuario = em.find(Usuario.class, id);
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return usuario;
    }

    /**
     * Obtiene todos los usuarios de la base de datos.
     *
     * @return Lista de objetos {@link Usuario}.
     * @throws ReadException Si ocurre algún error al leer los datos.
     */
    @Override
    public List<Usuario> findAllUsuario() throws ReadException {
        List<Usuario> usuario;
        try {
            usuario = em.createNamedQuery("findAllUsuario").getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return usuario;
    }

    /**
     * Busca un trabajador por su ID.
     *
     * @param id Identificador del trabajador.
     * @return Objeto {@link Trabajador} encontrado.
     * @throws ReadException Si ocurre algún error al leer los datos.
     */
    @Override
    public Trabajador findTrabajador(Long id) throws ReadException {
        Trabajador trabajador;
        try {
            trabajador = em.find(Trabajador.class, id);
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return trabajador;
    }

    /**
     * Obtiene todos los trabajadores de la base de datos.
     *
     * @return Lista de objetos {@link Trabajador}.
     * @throws ReadException Si ocurre algún error al leer los datos.
     */
    @Override
    public List<Trabajador> findAllTrabajador() throws ReadException {
        List<Trabajador> trabajador;
        try {
            trabajador = em.createNamedQuery("findAllTrabajador").getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return trabajador;
    }

    /**
     * Busca un cliente por su ID.
     *
     * @param id Identificador del cliente.
     * @return Objeto {@link Cliente} encontrado.
     * @throws ReadException Si ocurre algún error al leer los datos.
     */
    @Override
    public Cliente findCliente(Long id) throws ReadException {
        Cliente cliente;
        try {
            cliente = em.find(Cliente.class, id);
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return cliente;
    }

    /**
     * Obtiene todos los clientes de la base de datos.
     *
     * @return Lista de objetos {@link Cliente}.
     * @throws ReadException Si ocurre algún error al leer los datos.
     */
    @Override
    public List<Cliente> findAllCliente() throws ReadException {
        List<Cliente> cliente;
        try {
            cliente = em.createNamedQuery("findAllCliente").getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return cliente;
    }

    /**
     * Busca un pedido por su ID.
     *
     * @param id Identificador del pedido.
     * @return Objeto {@link Pedido} encontrado.
     * @throws ReadException Si ocurre algún error al leer los datos.
     */
    @Override
    public Pedido findPedido(Long id) throws ReadException {
        Pedido pedido;
        try {
            pedido = em.find(Pedido.class, id);
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return pedido;
    }

    /**
     * Obtiene todos los pedidos de la base de datos.
     *
     * @return Lista de objetos {@link Pedido}.
     * @throws ReadException Si ocurre algún error al leer los datos.
     */
    @Override
    public List<Pedido> findAllPedido() throws ReadException {
        List<Pedido> pedido;
        try {
            pedido = em.createNamedQuery("findAllPedido").getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return pedido;
    }

    /**
     * Busca un pedido-artículo por su ID.
     *
     * @param id Identificador del pedido-artículo.
     * @return Objeto {@link PedidoArticulo} encontrado.
     * @throws ReadException Si ocurre algún error al leer los datos.
     */
    @Override
    public PedidoArticulo findPedidoArticulo(Long id) throws ReadException {
        PedidoArticulo pedidoArticulo;
        try {
            pedidoArticulo = em.find(PedidoArticulo.class, id);
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return pedidoArticulo;
    }

    /**
     * Obtiene todos los pedidos-artículo de la base de datos.
     *
     * @return Lista de objetos {@link PedidoArticulo}.
     * @throws ReadException Si ocurre algún error al leer los datos.
     */
    @Override
    public List<PedidoArticulo> findAllPedidoArticulo() throws ReadException {
        List<PedidoArticulo> pedidoArticulo;
        try {
            pedidoArticulo = em.createNamedQuery("findAllPedidoArticulo").getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return pedidoArticulo;
    }

    /**
     * Busca un artículo por su ID.
     *
     * @param id Identificador del artículo.
     * @return Objeto {@link Articulo} encontrado.
     * @throws ReadException Si ocurre algún error al leer los datos.
     */
    @Override
    public Articulo findArticulo(Long id) throws ReadException {
        Articulo articulo;
        try {
            articulo = em.find(Articulo.class, id);
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return articulo;
    }

    /**
     * Obtiene todos los artículos de la base de datos.
     *
     * @return Lista de objetos {@link Articulo}.
     * @throws ReadException Si ocurre algún error al leer los datos.
     */
    @Override
    public List<Articulo> findAllArticulo() throws ReadException {
        List<Articulo> articulo;
        try {
            articulo = em.createNamedQuery("findAllArticulo").getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return articulo;
    }

    /**
     * Busca un almacén por su ID.
     *
     * @param id Identificador del almacén.
     * @return Objeto {@link Almacen} encontrado.
     * @throws ReadException Si ocurre algún error al leer los datos.
     */
    @Override
    public Almacen findAlmacen(Long id) throws ReadException {
        Almacen almacen;
        try {
            almacen = em.find(Almacen.class, id);
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return almacen;
    }

    /**
     * Obtiene todos los almacenes de la base de datos.
     *
     * @return Lista de objetos {@link Almacen}.
     * @throws ReadException Si ocurre algún error al leer los datos.
     */
    @Override
    public List<Almacen> findAllAlmacen() throws ReadException {
        List<Almacen> almacen;
        try {
            LOGGER.log(Level.INFO, "Buscando almacenes .....");
            almacen = em.createNamedQuery("findAllAlmacen").getResultList();
            LOGGER.log(Level.INFO, "Todos los almacenes encontrados");
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return almacen;
    }

    /**
     * Maneja el proceso de inicio de sesión. Desencripta la contraseña
     * recibida, la hashea y verifica su coincidencia con la almacenada.
     *
     * @param usuario Objeto {@link Usuario} que contiene el correo y la
     * contraseña cifrada.
     * @return Objeto correspondiente al tipo de usuario (Cliente o Trabajador).
     * @throws ReadException Si ocurre algún error al leer los datos o validar
     * credenciales.
     */
    @Override
    public Object inicioSesion(Usuario usuario) throws ReadException {
        Object respuesta = null;
        Object respuesta2 = null;
        LOGGER.log(Level.INFO, "Buscando usuario EJB: {0}", usuario.getCorreo());

        try {
            // Cargar claves desde archivos
            PrivateKey clavePrivada = cargarClavePrivada();
            LOGGER.log(Level.INFO, "Carganda clave privada");
            // Desencriptar la contraseña
            String contraseñaDesencriptada = descifrarConClavePrivada(usuario.getContrasena(), clavePrivada);
            LOGGER.log(Level.INFO, "Desencripto");
            // Hashear la contraseña
            String contraseñaHasheada = hashearContraseña(contraseñaDesencriptada);
            LOGGER.log(Level.INFO, "Hasheo");

            // Recuperar el usuario desde la base de datos por correo y contraseña
            Usuario usuarioBD = em.createNamedQuery("inicioSesion", Usuario.class)
                    .setParameter("correo", usuario.getCorreo())
                    .setParameter("contrasena", contraseñaHasheada)
                    .getSingleResult();
            LOGGER.log(Level.INFO, "Query ejecutada");
            if (usuarioBD == null) {
                throw new ReadException("Usuario no encontrado.");
            }

            // Verificar si el usuario es Cliente o Trabajador
            LOGGER.log(Level.INFO, "Buscando si es cliente: {0}", usuarioBD.getId());
            try {
                respuesta2 = em.createNamedQuery("esCliente")
                        .setParameter("id", usuarioBD.getId())
                        .getSingleResult();
                ((Cliente) respuesta2).setContrasena(null);
            } catch (NoResultException e) {
                LOGGER.log(Level.WARNING, "El usuario no es un Cliente.");
                respuesta2 = null;
            }
            LOGGER.log(Level.INFO, "Buscando si es trabajador: {0}", usuarioBD.getId());
            if (respuesta2 == null) {
                respuesta2 = em.createNamedQuery("esTrabajador")
                        .setParameter("id", usuarioBD.getId())
                        .getSingleResult();
                ((Trabajador) respuesta2).setContrasena(null);
            }
            LOGGER.log(Level.INFO, "Buscando si es trabajador: {0}", usuarioBD.getId());
            respuesta = respuesta2;
            LOGGER.log(Level.INFO, "respuesta: " + respuesta.toString());
        } catch (Exception e) {
            throw new ReadException("Error en el inicio de sesión: " + e.getMessage());
        }

        LOGGER.log(Level.INFO, "Respuesta: {0}", respuesta != null ? respuesta.toString() : "Ninguna");
        return respuesta;
    }

    /**
     * Permite el cambio de contraseña de un usuario, verificando la contraseña
     * antigua y asignando la nueva. Se notifica por correo electrónico.
     *
     * @param usuario Objeto {@link Usuario} que contiene la antigua y la nueva
     * contraseña cifrada.
     * @throws ReadException Si ocurre algún error al validar la contraseña o al
     * actualizar el usuario.
     */
    @Override
    public void cambioPass(Usuario usuario) throws ReadException {
        try {
            PrivateKey clavePrivada = null;
            clavePrivada = cargarClavePrivada();
            // Desencripta y hashea la contraseña vieja
            String contraseñaDesencriptadaVieja = descifrarConClavePrivada(usuario.getContrasena(), clavePrivada);
            String contraseñaHasheadaVieja = hashearContraseña(contraseñaDesencriptadaVieja);
            // Busca el usuario con la contraseña antigua
            Usuario usuarioBD = em.createNamedQuery("inicioSesion", Usuario.class)
                    .setParameter("correo", usuario.getCorreo())
                    .setParameter("contrasena", contraseñaHasheadaVieja)
                    .getSingleResult();
            if (usuarioBD != null) {
                // Desencripta y hashea la contraseña nueva
                String contraseñaDesencriptadaNueva = descifrarConClavePrivada(usuario.getCalle(), clavePrivada);
                String contraseñaHasheadaNuevo = hashearContraseña(contraseñaDesencriptadaNueva);
                usuarioBD.setContrasena(contraseñaHasheadaNuevo);
                updateUsuario(usuarioBD);
                enviar("cambio", usuario.getCorreo(), "");
            }
        } catch (Exception ex) {
            Logger.getLogger(EJBGestorEntidades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Permite la recuperación de contraseña, generando una nueva aleatoria y
     * enviándola por correo electrónico al usuario.
     *
     * @param usuario Objeto {@link Usuario} que contiene el correo.
     * @throws ReadException Si el correo no existe o si ocurre algún error al
     * procesar la operación.
     */
    @Override
    public void recuperarPass(Usuario usuario) throws ReadException {
        Usuario usuarioBD = null;
        if (existeCorreo(usuario)) {
            String nueva = generarContrasenaTemporal();
            usuarioBD = em.createNamedQuery("recuperar", Usuario.class)
                    .setParameter("correo", usuario.getCorreo())
                    .getSingleResult();
            // Asignar la nueva contraseña hasheada
            try {
                String contraseñaHasheada = hashearContraseña(nueva);
                usuarioBD.setContrasena(contraseñaHasheada);
            } catch (Exception ex) {
                Logger.getLogger(EJBGestorEntidades.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Enviar correo con la nueva contraseña
            enviar("recupera", usuario.getCorreo(), nueva);
            try {
                updateUsuario(usuarioBD);
            } catch (UpdateException ex) {
                Logger.getLogger(EJBGestorEntidades.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Verifica si existe un correo electrónico en la base de datos.
     *
     * @param usuario Objeto {@link Usuario} que contiene el correo a verificar.
     * @return {@code Boolean.TRUE} si el correo existe, lanza excepción de
     * lectura en caso contrario.
     * @throws ReadException Si el correo no existe o si ocurre algún error al
     * consultar en la base de datos.
     */
    @Override
    public Boolean existeCorreo(Usuario usuario) throws ReadException {
        try {
            LOGGER.log(Level.INFO, "Verificando correo");
            if (((Long) em.createNamedQuery("existeCorreo").setParameter("correo", usuario.getCorreo()).getSingleResult()).intValue() == 0) {
                throw new ReadException();
            }
            LOGGER.log(Level.INFO, "Verificado correo OK");
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return Boolean.TRUE;
    }

    /**
     * Método de utilidad para desencriptar y hashear la contraseña de un
     * {@link Usuario} antes de persistirlo o actualizarlo.
     *
     * @param usuario Objeto {@link Usuario} cuya contraseña se desea procesar.
     * @throws Exception Si ocurre algún error durante el proceso de
     * desencriptación o hashing.
     */
    public void procesarContrasenaUsuario(Usuario usuario) throws Exception {
        // Cargar claves desde archivos
        PrivateKey clavePrivada = cargarClavePrivada();
        // Desencripta la contraseña
        String contraseñaDesencriptada = descifrarConClavePrivada(usuario.getContrasena(), clavePrivada);
        // Hashea la contraseña
        String contraseñaHasheada = hashearContraseña(contraseñaDesencriptada);
        usuario.setContrasena(contraseñaHasheada);
    }

    /**
     * Obtiene la lista de todos los almacenes relacionados con un artículo
     * específico, basado en su ID.
     *
     * @param id ID del artículo cuyo listado de almacenes se desea obtener.
     * @return Lista de objetos {@link Almacen} que almacenan el artículo
     * indicado.
     * @throws ReadException Si ocurre algún error al leer los datos.
     */
    @Override
    public List<Almacen> findAllArticuloById(Long id) throws ReadException {
        List<Almacen> listaAlmacenes;
        try {
            LOGGER.log(Level.INFO, "Buscando almacenes para el articulo....");
            Articulo articuloExistente = em.find(Articulo.class, id);
            Set<Almacen> setAlmacenes = articuloExistente.getAlmacenes(); // Obtienes un Set
            listaAlmacenes = new ArrayList<>(setAlmacenes); // Convierte el Set a List
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return listaAlmacenes;
    }

}
