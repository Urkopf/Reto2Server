/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Urko
 */
@Entity
@Table(name = "pedido_articulo", schema = "reto2")
@NamedQuery(name = "findAllPedidoArticulo", query = "SELECT a FROM PedidoArticulo a ORDER BY a.id DESC")
@XmlRootElement
public class PedidoArticulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pedido_articulo_id")
    private Long id;

    @Column(name = "pedido_id")
    private Long pedidoId;

    @Column(name = "articulo_id")
    private Long articuloId;

    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @MapsId("articuloId")
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "precio_compra")
    private double precioCompra;

    public PedidoArticulo() {
    }

    public PedidoArticulo(Long id, Long pedidoId, Long articuloId, Pedido pedido, Articulo articulo, int cantidad, double precioCompra) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.articuloId = articuloId;
        this.pedido = pedido;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;

    }

    public Long getId() {
        return id;
    }

    public void setPedidoArticuloId(Long id) {
        this.id = id;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(Long articuloId) {
        this.articuloId = articuloId;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "crud.entidades.PedidoArticulo[ id=" + id + " ]";
    }

}
