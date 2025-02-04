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
 * Representa una entidad Pedido en el sistema.
 * <p>
 * Esta clase se mapea a la tabla <strong>Pedido</strong> del esquema
 * <strong>reto2</strong>
 * y contiene información relativa a un pedido realizado por un cliente,
 * incluyendo la dirección, fecha del pedido, estado, total, identificación
 * fiscal del cliente, el cliente asociado y los artículos incluidos en el
 * pedido.
 * </p>
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     Pedido pedido = new Pedido();
 *     pedido.setDireccion("Calle Falsa 123");
 *     pedido.setFechaPedido(new Date());
 *     pedido.setEstado(Estado.PENDIENTE);
 *     pedido.setTotal(150.0);
 * </pre>
 * </p>
 *
 * @author Urko
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
     * Dirección asociada al pedido.
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
     * Estado actual del pedido.
     */
    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private Estado estado;

    /**
     * Total monetario del pedido.
     */
    @Column(name = "total")
    private double total;

    /**
     * CIF (Código de Identificación Fiscal) del cliente asociado.
     */
    @Column(name = "cif_cliente")
    private String cifCliente;

    /**
     * Cliente al que pertenece el pedido.
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Cliente cliente;

    /**
     * Conjunto de relaciones con PedidoArticulo.
     */
    @OneToMany(mappedBy = "pedido")
    private Set<PedidoArticulo> pedidoArticulos = new HashSet<>();

    /**
     * Constructor por defecto.
     */
    public Pedido() {
    }

    /**
     * Retorna el identificador único del pedido.
     *
     * @return el id del pedido.
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna el identificador único al pedido.
     *
     * @param id el nuevo id del pedido.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna la dirección asociada al pedido.
     *
     * @return la dirección del pedido.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Asigna la dirección al pedido.
     *
     * @param direccion la nueva dirección del pedido.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Retorna la fecha en que se realizó el pedido.
     *
     * @return la fecha del pedido.
     */
    public Date getFechaPedido() {
        return fechaPedido;
    }

    /**
     * Asigna la fecha en que se realizó el pedido.
     *
     * @param fechaPedido la nueva fecha del pedido.
     */
    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    /**
     * Retorna el estado actual del pedido.
     *
     * @return el estado del pedido.
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Asigna el estado actual al pedido.
     *
     * @param estado el nuevo estado del pedido.
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Retorna el total monetario del pedido.
     *
     * @return el total del pedido.
     */
    public double getTotal() {
        return total;
    }

    /**
     * Asigna el total monetario al pedido.
     *
     * @param total el nuevo total del pedido.
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Retorna el CIF del cliente asociado al pedido.
     *
     * @return el CIF del cliente.
     */
    public String getCifCliente() {
        return cifCliente;
    }

    /**
     * Asigna el CIF del cliente al pedido.
     *
     * @param cifCliente el nuevo CIF del cliente.
     */
    public void setCifCliente(String cifCliente) {
        this.cifCliente = cifCliente;
    }

    /**
     * Retorna el cliente asociado al pedido.
     *
     * @return el cliente.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Asigna el cliente al pedido.
     *
     * @param cliente el nuevo cliente.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Retorna el conjunto de PedidoArticulo asociados al pedido.
     *
     * @return un conjunto de {@code PedidoArticulo}.
     */
    @XmlTransient
    public Set<PedidoArticulo> getPedidoArticulos() {
        return pedidoArticulos;
    }

    /**
     * Asigna el conjunto de PedidoArticulo al pedido.
     *
     * @param pedidoArticulos el nuevo conjunto de {@code PedidoArticulo}.
     */
    public void setPedidoArticulos(Set<PedidoArticulo> pedidoArticulos) {
        this.pedidoArticulos = pedidoArticulos;
    }

    /**
     * Calcula el código hash basado en el identificador único del pedido.
     *
     * @return el código hash del pedido.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Compara el pedido actual con otro objeto para determinar si son iguales.
     * <p>
     * La comparación se basa en el identificador único del pedido.
     * </p>
     *
     * @param object el objeto con el que se realiza la comparación.
     * @return {@code true} si los objetos son iguales; de lo contrario,
     * {@code false}.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
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
     * Retorna una representación en forma de cadena del pedido.
     *
     * @return una cadena que contiene el identificador del pedido.
     */
    @Override
    public String toString() {
        return "crud.entidades.Pedido[ id=" + id + " ]";
    }

}
