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
 *
 * @author 2dam
 */
@Local
public interface IGestorEntidadesLocal {

    //CREATES-----------------------------
    public void createUsuario(Usuario usuario) throws CreateException;

    public void createTrabajador(Trabajador trabajador) throws CreateException;

    public void createCliente(Cliente cliente) throws CreateException;

    public void createPedido(Pedido pedido) throws CreateException;

    public void createPedidoArticulo(PedidoArticulo pedidoArticulo) throws CreateException;

    public void createArticulo(Articulo articulo) throws CreateException;

    public void createAlmacen(Almacen almacen) throws CreateException;

    //UPDATES---------------------------
    public void updateUsuario(Usuario usuario) throws UpdateException;

    public void updateTrabajador(Trabajador trabajador) throws UpdateException;

    public void updateCliente(Cliente cliente) throws UpdateException;

    public void updatePedido(Pedido pedido) throws UpdateException;

    public void updatePedidoArticulo(PedidoArticulo pedidoArticulo) throws UpdateException;

    public void updateArticulo(Articulo articulo) throws UpdateException;

    public void updateAlmacen(Almacen almacen) throws UpdateException;

    //REMOVE------------------------
    public void removeUsuario(Usuario usuario) throws RemoveException;

    public void removeTrabajador(Trabajador trabajador) throws RemoveException;

    public void removeCliente(Cliente cliente) throws RemoveException;

    public void removePedido(Pedido pedido) throws RemoveException;

    public void removePedidoArticulo(PedidoArticulo pedidoArticulo) throws RemoveException;

    public void removeArticulo(Articulo articulo) throws RemoveException;

    public void removeAlmacen(Almacen almacen) throws RemoveException;

    //FIND-----------------------
    public Usuario findUsuario(Long id) throws ReadException;

    public List<Usuario> findAllUsuario() throws ReadException;

    //AÑADIR PERSONALIZADAS
    public Trabajador findTrabajador(Long id) throws ReadException;

    public List<Trabajador> findAllTrabajador() throws ReadException;

    //AÑADIR PERSONALIZADAS
    public Cliente findCliente(Long id) throws ReadException;

    public List<Cliente> findAllCliente() throws ReadException;

    //AÑADIR PERSONALIZADAS
    public Pedido findPedido(Long id) throws ReadException;

    public List<Pedido> findAllPedido() throws ReadException;

    //AÑADIR PERSONALIZADAS
    public PedidoArticulo findPedidoArticulo(Long id) throws ReadException;

    public List<PedidoArticulo> findAllPedidoArticulo() throws ReadException;

    //AÑADIR PERSONALIZADAS
    public Articulo findArticulo(Long id) throws ReadException;

    public List<Articulo> findAllArticulo() throws ReadException;

    //AÑADIR PERSONALIZADAS
    public Almacen findAlmacen(Long id) throws ReadException;

    public List<Almacen> findAllAlmacen() throws ReadException;

    //AÑADIR PERSONALIZADAS
    public List<Usuario> inicioSesion(String correo) throws ReadException;

    public Usuario cambioPass(Usuario usuario) throws ReadException;

    public Boolean existeCorreo(Usuario usuario) throws ReadException;

    public Usuario recuperarPass(Usuario usuario) throws ReadException;
}
