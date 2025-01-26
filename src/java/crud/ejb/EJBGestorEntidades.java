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
import crud.excepciones.RelationAlreadyExistsException;
import crud.excepciones.RemoveException;
import crud.excepciones.UpdateException;
import static crud.seguridad.UtilidadesCifrado.cargarClavePrivada;
import static crud.seguridad.UtilidadesCifrado.cargarClavePublica;
import static crud.seguridad.UtilidadesCifrado.desencriptarConClavePrivada;
import static crud.seguridad.UtilidadesCifrado.encriptarConClavePublica;
import static crud.seguridad.UtilidadesCifrado.generarContrasenaTemporal;
import static crud.seguridad.UtilidadesCifrado.hashearContraseña;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 2dam
 */
@Stateless
public class EJBGestorEntidades implements IGestorEntidadesLocal {

    private Logger LOGGER = Logger.getLogger(EJBGestorEntidades.class.getName());

    @PersistenceContext(unitName = "Reto2_CRUD_WebApplicationPU")
    private EntityManager em;

    @Override
    public void createUsuario(Usuario usuario) throws CreateException {
        try {
            em.persist(usuario);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

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

    @Override
    public void createPedido(Pedido pedido) throws CreateException {
        try {
            em.persist(pedido);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    @Override
    public void createPedidoArticulo(PedidoArticulo pedidoArticulo) throws CreateException {
        try {
            em.persist(pedidoArticulo);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    @Override
    public void createArticulo(Articulo articulo) throws CreateException {
        try {
            em.persist(articulo);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    @Override
    public void createAlmacen(Almacen almacen) throws CreateException {
        try {
            em.persist(almacen);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

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

    @Override
    public void updateArticulo(Articulo articulo) throws UpdateException {
        try {
            if (!em.contains(articulo)) {
                em.merge(articulo);
            }
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

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

    @Override
    public void removeUsuario(Usuario usuario) throws RemoveException {
        try {
            em.remove(em.merge(usuario));
        } catch (Exception e) {
            throw new RemoveException(e.getMessage());
        }
    }

    @Override
    public void removeTrabajador(Trabajador trabajador) throws RemoveException {
        try {
            em.remove(em.merge(trabajador));
        } catch (Exception e) {
            throw new RemoveException(e.getMessage());
        }
    }

    @Override
    public void removeCliente(Cliente cliente) throws RemoveException {
        try {
            em.remove(em.merge(cliente));
        } catch (Exception e) {
            throw new RemoveException(e.getMessage());
        }
    }

    @Override
    public void removePedido(Pedido pedido) throws RemoveException {
        try {
            em.remove(em.merge(pedido));
        } catch (Exception e) {
            throw new RemoveException(e.getMessage());
        }
    }

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

    @Override
    public void removeArticulo(Articulo articulo) throws RemoveException {
        try {
            em.remove(em.merge(articulo));
        } catch (Exception e) {
            throw new RemoveException(e.getMessage());
        }
    }

    @Override
    public void removeAlmacen(Almacen almacen) throws RemoveException {
        try {
            em.remove(em.merge(almacen));
        } catch (Exception e) {
            throw new RemoveException(e.getMessage());
        }
    }

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

    @Override
    public Object inicioSesion(Usuario usuario) throws ReadException {
        Object respuesta = null;
        Object respuesta2 = null;
        LOGGER.log(Level.INFO, "Buscando usuario EJB: {0}", usuario.getCorreo());

        try {

            // Cargar claves desde archivos
            PrivateKey clavePrivada = cargarClavePrivada();

            // Servidor desencripta la contraseña
            String contraseñaDesencriptada = desencriptarConClavePrivada(usuario.getContrasena(), clavePrivada);

            // Servidor hashea la contraseña antes de almacenarla
            String contraseñaHasheada = hashearContraseña(contraseñaDesencriptada);

            // 1. Recuperar el usuario desde la base de datos por correo
            Usuario usuarioBD = em.createNamedQuery("inicioSesion", Usuario.class)
                    .setParameter("correo", usuario.getCorreo())
                    .setParameter("contrasena", contraseñaHasheada)
                    .getSingleResult();

            if (usuarioBD == null) {
                throw new ReadException("Usuario no encontrado.");
            }

            // 8. Buscar si el usuario es Cliente o Trabajador
            LOGGER.log(Level.INFO, "Buscando si es cliente: {0}", usuarioBD.getId());
            respuesta2 = em.createNamedQuery("esCliente")
                    .setParameter("id", usuarioBD.getId())
                    .getSingleResult();

            if (respuesta2 == null) {
                respuesta2 = em.createNamedQuery("esTrabajador")
                        .setParameter("id", usuarioBD.getId())
                        .getSingleResult();
            }

            respuesta = respuesta2;

        } catch (Exception e) {
            throw new ReadException("Error en el inicio de sesión: " + e.getMessage());
        }

        LOGGER.log(Level.INFO, "Respuesta: {0}", respuesta != null ? respuesta.toString() : "Ninguna");
        return respuesta;
    }

    @Override
    public void cambioPass(Usuario usuario) throws ReadException {

        try {
            PrivateKey clavePrivada = null;
            clavePrivada = cargarClavePrivada();
            // Servidor desencripta la contraseña
            String contraseñaDesencriptadaVieja = desencriptarConClavePrivada(usuario.getContrasena(), clavePrivada);

            // Servidor hashea la contraseña antes de almacenarla
            String contraseñaHasheadaVieja = hashearContraseña(contraseñaDesencriptadaVieja);
            Usuario usuarioBD = em.createNamedQuery("inicioSesion", Usuario.class)
                    .setParameter("correo", usuario.getCorreo())
                    .setParameter("contrasena", contraseñaHasheadaVieja)
                    .getSingleResult();
            if (usuarioBD != null) {

                // Servidor desencripta la contraseña
                String contraseñaDesencriptadaNueva = desencriptarConClavePrivada(usuario.getCalle(), clavePrivada);

                // Servidor hashea la contraseña antes de almacenarla
                String contraseñaHasheadaNuevo = hashearContraseña(contraseñaDesencriptadaNueva);
                usuarioBD.setContrasena(contraseñaHasheadaNuevo);
                updateUsuario(usuarioBD);

                enviar("cambio", usuario.getCorreo(), "");
            }

        } catch (Exception ex) {
            Logger.getLogger(EJBGestorEntidades.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void recuperarPass(Usuario usuario) throws ReadException {
        Usuario usuarioBD = null;
        if (existeCorreo(usuario)) {
            String nueva = generarContrasenaTemporal();
            usuarioBD = em.createNamedQuery("recuperar", Usuario.class)
                    .setParameter("correo", usuario.getCorreo())
                    .getSingleResult();
            //Buscar usuario, y asignar nueva contraseña
            // Cargar claves desde archivos
            PublicKey clavePublica = null;
            PrivateKey clavePrivada = null;
            try {
                clavePublica = cargarClavePublica();
                clavePrivada = cargarClavePrivada();
                // Cliente encripta la contraseña
                String contraseñaEncriptada = encriptarConClavePublica(nueva, clavePublica);

                // Servidor desencripta la contraseña
                String contraseñaDesencriptada = desencriptarConClavePrivada(contraseñaEncriptada, clavePrivada);

                // Servidor hashea la contraseña antes de almacenarla
                String contraseñaHasheada = hashearContraseña(contraseñaDesencriptada);
                usuarioBD.setContrasena(contraseñaHasheada);

                //contraseñaHasheada <-- asignarla al usuario para editarla
            } catch (Exception ex) {
                Logger.getLogger(EJBGestorEntidades.class.getName()).log(Level.SEVERE, null, ex);
            }

            enviar("recupera", usuario.getCorreo(), nueva);
            try {
                updateUsuario(usuarioBD);
            } catch (UpdateException ex) {
                Logger.getLogger(EJBGestorEntidades.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

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

    public void procesarContrasenaUsuario(Usuario usuario) throws Exception {

        // Cargar claves desde archivos
        PrivateKey clavePrivada = cargarClavePrivada();

        // Servidor desencripta la contraseña
        String contraseñaDesencriptada = desencriptarConClavePrivada(usuario.getContrasena(), clavePrivada);

        // Servidor hashea la contraseña antes de almacenarla
        String contraseñaHasheada = hashearContraseña(contraseñaDesencriptada);
        usuario.setContrasena(contraseñaHasheada);
    }

    @Override
    public List<Almacen> findAllArticuloById(Long id) throws ReadException {
        List<Almacen> almacenes;
        try {
            LOGGER.log(Level.INFO, "Buscando almacenes para el articulo....");
            almacenes = em.createNamedQuery("findAlmacenesByArticuloId", Almacen.class)
                    .setParameter("articulo_id", id)
                    .getResultList();
            LOGGER.log(Level.INFO, "Almacenes encontrados");

        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return almacenes;
    }

    @Override
    public void updateAlmacenWithArticulo(Almacen almacen) throws UpdateException, CreateException, ReadException, RelationAlreadyExistsException {
        try {
            // Verifica si ya existe la relación en la tabla intermedia con una consulta SQL nativa
            Long count = ((Number) em.createNativeQuery(
                    "SELECT COUNT(*) FROM reto2.articulo_almacen "
                    + "WHERE articulos_articulo_id = ? AND almacenes_almacen_id = ?")
                    .setParameter(1, almacen.getArticuloId())
                    .setParameter(2, almacen.getId())
                    .getSingleResult()).longValue();

            if (count == 0) {
                // Inserta la relación si no existe
                LOGGER.info("Relación no encontrada. Insertando nueva relación.");
                em.createNativeQuery(
                        "INSERT INTO reto2.articulo_almacen (articulos_articulo_id, almacenes_almacen_id) VALUES (?, ?)")
                        .setParameter(1, almacen.getArticuloId())
                        .setParameter(2, almacen.getId())
                        .executeUpdate();
            } else {
                LOGGER.info("La relación entre el artículo y el almacén ya existe.");
                throw new RelationAlreadyExistsException("La relación entre el artículo y el almacén ya existe.");
            }

            // Confirma los cambios en la base de datos
            em.flush();

        } catch (javax.persistence.PersistenceException e) {
            LOGGER.severe("Error de persistencia al manejar la relación artículo-almacén: " + e.getMessage());
            throw new UpdateException("Error de persistencia al manejar la relación artículo-almacén.");
        } catch (IllegalArgumentException e) {
            LOGGER.severe("Parámetros inválidos: " + e.getMessage());
            throw new ReadException("Los parámetros proporcionados para la relación son inválidos.");
        } catch (Exception e) {
            LOGGER.severe("Error inesperado: " + e.getMessage());
            throw new UpdateException("Error inesperado al manejar la relación artículo-almacén.");
        }
    }

}
