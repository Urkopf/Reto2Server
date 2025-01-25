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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Urko
 */
@Entity
@Table(name = "articulo", schema = "reto2")
@NamedQuery(name = "findAllArticulo", query = "SELECT a FROM Articulo a")
@XmlRootElement
public class Articulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "articulo_id")
    private Long id;

    private String nombre;

    private double precio;

    private String descripcion;

    private int stock;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_reposicion")
    private Date fechaReposicion;

    @OneToMany(mappedBy = "articulo")
    private Set<PedidoArticulo> pedidoArticulos = new HashSet<>();

    public Articulo() {

    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getFechaReposicion() {
        return fechaReposicion;
    }

    public void setFechaReposicion(Date fechaReposicion) {
        this.fechaReposicion = fechaReposicion;
    }

    @XmlTransient
    public Set<PedidoArticulo> getPedidoArticulos() {
        return pedidoArticulos;
    }

    public void setPedidoArticulos(Set<PedidoArticulo> pedidoArticulos) {
        this.pedidoArticulos = pedidoArticulos;
    }

    @ManyToMany
    @JoinTable(name = "articulo_almacen", schema = "reto2",
            joinColumns = @JoinColumn(name = "articulos_articulo_id", referencedColumnName = "articulo_id"),
            inverseJoinColumns = @JoinColumn(name = "almacenes_almacen_id", referencedColumnName = "almacen_id"))
    private Set<Almacen> almacenes = new HashSet<>();

    @XmlTransient
    public Set<Almacen> getAlmacenes() {
        return almacenes;
    }

    public void setAlmacen(Set<Almacen> almacenes) {
        this.almacenes = almacenes;
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
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "crud.entidades.Articulo[ id=" + id + " ]";
    }

}
