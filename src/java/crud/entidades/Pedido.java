/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Clase que representa un Pedido en el sistema.
 *
 * <p>
 * Esta clase contiene los atributos y métodos necesarios para gestionar la
 * información de un pedido realizado por un cliente.</p>
 *
 * <p>
 * Los pedidos están asociados a un cliente específico, contienen una lista de
 * artículos solicitados y registran detalles como la dirección de envío, la
 * fecha del pedido, el estado actual del pedido, el total a pagar y el CIF del
 * cliente.</p>
 *
 * <p>
 * Además de los métodos de acceso para cada atributo, la clase incluye métodos
 * para calcular el hashcode, verificar la igualdad basada en el identificador
 * único del pedido y generar una representación en cadena del objeto para
 * propósitos de depuración.</p>
 *
 * <p>
 * Esta clase está anotada para ser utilizada como una entidad JPA, permitiendo
 * su persistencia en una base de datos relacional a través del mapeo
 * objeto-relacional proporcionado por el framework.</p>
 *
 * @author Urko
 * @version 1.0
 * @since 2025-02-04
 */
@Entity
@Table(name = "Pedido", schema = "reto2")
@NamedQuery(name = "findAllPedido", query = "SELECT a FROM Pedido a")
@XmlRootElement
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único del pedido.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pedido_id")
    private Long id;

    /**
     * Dirección de envío del pedido.
     */
    @Column(name = "direccion")
    private String direccion;

    /**
     * Fecha en la que se realizó el pedido.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_pedido")
    private Date fechaPedido;

    /**
     * Estado actual del pedido (pendiente, en proceso, completado, etc.).
     */
    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private Estado estado;

    /**
     * Total a pagar por el pedido.
     */
    @Column(name = "total")
    private double total;

    /**
     * CIF del cliente que realizó el pedido.
     */
    @Column(name = "cif_cliente")
    private String cifCliente;

    /**
     * Cliente asociado al pedido.
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Cliente cliente;

    /**
     * Conjunto de artículos incluidos en el pedido.
     */
    @OneToMany(mappedBy = "pedido")
    private Set<PedidoArticulo> pedidoArticulos = new HashSet<>();

    /**
     * Constructor por defecto de la clase Pedido.
     */
    public Pedido() {
    }

    /**
     * Obtiene el identificador único del pedido.
     *
     * @return El identificador único del pedido.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del pedido.
     *
     * @param id El identificador único del pedido.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la dirección de envío del pedido.
     *
     * @return La dirección de envío del pedido.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección de envío del pedido.
     *
     * @param direccion La dirección de envío del pedido.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene la fecha en la que se realizó el pedido.
     *
     * @return La fecha en la que se realizó el pedido.
     */
    public Date getFechaPedido() {
        return fechaPedido;
    }

    /**
     * Establece la fecha en la que se realizó el pedido.
     *
     * @param fechaPedido La fecha en la que se realizó el pedido.
     */
    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    /**
     * Obtiene el estado actual del pedido.
     *
     * @return El estado actual del pedido.
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Establece el estado actual del pedido.
     *
     * @param estado El estado actual del pedido.
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el total a pagar por el pedido.
     *
     * @return El total a pagar por el pedido.
     */
    public double getTotal() {
        return total;
    }

    /**
     * Establece el total a pagar por el pedido.
     *
     * @param total El total a pagar por el pedido.
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Obtiene el CIF del cliente que realizó el pedido.
     *
     * @return El CIF del cliente que realizó el pedido.
     */
    public String getCifCliente() {
        return cifCliente;
    }

    /**
     * Establece el CIF del cliente que realizó el pedido.
     *
     * @param cifCliente El CIF del cliente que realizó el pedido.
     */
    public void setCifCliente(String cifCliente) {
        this.cifCliente = cifCliente;
    }

    /**
     * Obtiene el cliente asociado al pedido.
     *
     * @return El cliente asociado al pedido.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Establece el cliente asociado al pedido.
     *
     * @param cliente El cliente asociado al pedido.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Obtiene el conjunto de artículos incluidos en el pedido.
     *
     * @return El conjunto de artículos incluidos en el pedido.
     */
    @XmlTransient
    public Set<PedidoArticulo> getPedidoArticulos() {
        return pedidoArticulos;
    }

    /**
     * Establece el conjunto de artículos incluidos en el pedido.
     *
     * @param pedidoArticulos El conjunto de artículos incluidos en el pedido.
     */
    public void setPedidoArticulos(Set<PedidoArticulo> pedidoArticulos) {
        this.pedidoArticulos = pedidoArticulos;
    }

    /**
     * Calcula el hashcode del objeto basado en el identificador único del
     * pedido.
     *
     * @return El hashcode calculado.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Compara este objeto con otro para determinar si son iguales.
     *
     * @param object El objeto con el que se compara.
     * @return true si los objetos son iguales; false en caso contrario.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Devuelve una representación en cadena del objeto.
     *
     * @return Una cadena que representa el objeto, incluyendo el identificador
     * único del pedido.
     */
    @Override
    public String toString() {
        return "crud.entidades.Pedido[ id=" + id + " ]";
    }

}
