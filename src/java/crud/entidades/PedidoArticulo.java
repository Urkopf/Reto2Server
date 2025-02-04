/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Representa la entidad {@code PedidoArticulo} que modela la relación entre un
 * pedido y un artículo.
 * <p>
 * Esta entidad se utiliza para almacenar la información de cada artículo
 * contenido en un pedido, incluyendo la cantidad solicitada y el precio de
 * compra correspondiente. Se mapea a la tabla <strong>pedido_articulo</strong>
 * del esquema <strong>reto2</strong>.
 * </p>
 *
 * @author Urko
 */
@Entity
@Table(name = "pedido_articulo", schema = "reto2")
@NamedQuery(name = "findAllPedidoArticulo", query = "SELECT a FROM PedidoArticulo a")
@XmlRootElement
public class PedidoArticulo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único de la relación pedido-artículo.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pedido_articulo_id")
    private Long id;

    /**
     * Pedido asociado a esta relación.
     */
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    /**
     * Artículo asociado a esta relación.
     */
    @ManyToOne
    @JoinColumn(name = "articulo_id", nullable = false)
    private Articulo articulo;

    /**
     * Cantidad del artículo incluida en el pedido.
     */
    @Column(name = "cantidad")
    private int cantidad;

    /**
     * Precio de compra del artículo en el contexto del pedido.
     */
    @Column(name = "precio_compra")
    private double precioCompra;

    /**
     * Constructor por defecto.
     */
    public PedidoArticulo() {
    }

    /**
     * Constructor que inicializa la entidad con sus atributos.
     *
     * @param id el identificador único de la relación pedido-artículo.
     * @param pedido el pedido asociado.
     * @param articulo el artículo asociado.
     * @param cantidad la cantidad del artículo en el pedido.
     * @param precioCompra el precio de compra del artículo en el pedido.
     */
    public PedidoArticulo(Long id, Pedido pedido, Articulo articulo, int cantidad, double precioCompra) {
        this.id = id;
        this.pedido = pedido;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
    }

    /**
     * Retorna el identificador único de la relación pedido-artículo.
     *
     * @return el id de la relación.
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna el identificador único a la relación pedido-artículo.
     *
     * @param id el nuevo id a asignar.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna el pedido asociado.
     *
     * @return el pedido.
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * Asigna el pedido asociado.
     *
     * @param pedido el pedido a asignar.
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * Retorna el artículo asociado.
     *
     * @return el artículo.
     */
    public Articulo getArticulo() {
        return articulo;
    }

    /**
     * Asigna el artículo asociado.
     *
     * @param articulo el artículo a asignar.
     */
    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    /**
     * Retorna la cantidad del artículo en el pedido.
     *
     * @return la cantidad.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Asigna la cantidad del artículo en el pedido.
     *
     * @param cantidad la cantidad a asignar.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Retorna el precio de compra del artículo en el pedido.
     *
     * @return el precio de compra.
     */
    public double getPrecioCompra() {
        return precioCompra;
    }

    /**
     * Asigna el precio de compra del artículo en el pedido.
     *
     * @param precioCompra el precio a asignar.
     */
    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    /**
     * Calcula el código hash basado en el identificador único de la relación.
     *
     * @return el código hash.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Compara el objeto actual con otro para determinar si son iguales.
     * <p>
     * La comparación se basa en el identificador único de la relación.
     * </p>
     *
     * @param object el objeto con el que se realiza la comparación.
     * @return {@code true} si los objetos son iguales; de lo contrario,
     * {@code false}.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoArticulo)) {
            return false;
        }
        PedidoArticulo other = (PedidoArticulo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Retorna una representación en forma de cadena de la relación
     * pedido-artículo.
     *
     * @return una cadena que contiene el identificador de la relación.
     */
    @Override
    public String toString() {
        return "crud.entidades.PedidoArticulo[ id=" + id + " ]";
    }

}
