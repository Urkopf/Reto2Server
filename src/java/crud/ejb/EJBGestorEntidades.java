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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 2dam
 */
@Stateless
public class EJBGestorEntidades implements IGestorEntidadesLocal {

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
            em.persist(trabajador);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    @Override
    public void createCliente(Cliente cliente) throws CreateException {
        try {
            em.persist(cliente);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
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
            em.remove(em.merge(pedidoArticulo));
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
            almacen = em.createNamedQuery("findAllAlmacen").getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return almacen;
    }

    @Override
    public Usuario iniciarSesion(Usuario usuario) throws ReadException {
        Usuario respuesta;
        try {
            //Hay que desencriptar la contraseña y demas......antes de usar esto
            respuesta = (Usuario) em.createNamedQuery("inicioSesion").setParameter("correo", usuario.getCorreo()).setParameter("contrasena", usuario.getContrasena()).getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return respuesta;
    }

    @Override
    public Usuario cambioPass(Usuario usuario) throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean existeCorreo(Usuario usuario) throws ReadException {
        try {
            if ((Integer) em.createNamedQuery("existeCorreo").setParameter("correo", usuario.getCorreo()).getSingleResult() == 0) {
                throw new ReadException();
            }
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return Boolean.TRUE;
    }

    @Override
    public Usuario recuperarPass(Usuario usuario) throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
