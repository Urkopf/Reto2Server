/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entidad que representa un artículo en el sistema de inventario.
 * <p>
 * La clase se mapea a la tabla <strong>articulo</strong> del esquema
 * <strong>reto2</strong>
 * y define las propiedades de un artículo, tales como su nombre, precio,
 * descripción, stock, fecha de reposición y las relaciones con almacenes y los
 * pedidos asociados.
 * </p>
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     Articulo articulo = new Articulo();
 *     articulo.setNombre("Ejemplo de Artículo");
 *     articulo.setPrecio(99.99);
 *     // Configurar el resto de atributos según necesidad...
 * </pre>
 * </p>
 *
 * @author Urko
 */
@Entity
@Table(name = "articulo", schema = "reto2")
@NamedQuery(name = "findAllArticulo", query = "SELECT a FROM Articulo a")
@XmlRootElement
public class Articulo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único del artículo.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "articulo_id")
    private Long id;

    /**
     * Nombre del artículo.
     */
    private String nombre;

    /**
     * Precio del artículo.
     */
    private double precio;

    /**
     * Descripción del artículo.
     */
    private String descripcion;

    /**
     * Cantidad de stock disponible del artículo.
     */
    private int stock;

    /**
     * Lista transitoria de almacenes.
     * <p>
     * Este atributo no se persiste en la base de datos.
     * </p>
     */
    @Transient
    private List<Almacen> almacenTrump = new ArrayList<>();

    /**
     * Fecha de reposición del artículo.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_reposicion")
    private Date fechaReposicion;

    /**
     * Conjunto de entidades PedidoArticulo asociadas al artículo.
     */
    @OneToMany(mappedBy = "articulo")
    private Set<PedidoArticulo> pedidoArticulos = new HashSet<>();

    /**
     * Constructor por defecto.
     */
    public Articulo() {
    }

    /**
     * Retorna el identificador único del artículo.
     *
     * @return el id del artículo.
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna el identificador único al artículo.
     *
     * @param id el nuevo id del artículo.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna el nombre del artículo.
     *
     * @return el nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre al artículo.
     *
     * @param nombre el nuevo nombre del artículo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el precio del artículo.
     *
     * @return el precio.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Asigna el precio al artículo.
     *
     * @param precio el nuevo precio del artículo.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Retorna la descripción del artículo.
     *
     * @return la descripción.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Asigna la descripción al artículo.
     *
     * @param descripcion la nueva descripción del artículo.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Retorna la cantidad en stock del artículo.
     *
     * @return el stock.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Asigna la cantidad en stock del artículo.
     *
     * @param stock la nueva cantidad en stock.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Retorna la fecha de reposición del artículo.
     *
     * @return la fecha de reposición.
     */
    public Date getFechaReposicion() {
        return fechaReposicion;
    }

    /**
     * Asigna la fecha de reposición al artículo.
     *
     * @param fechaReposicion la nueva fecha de reposición.
     */
    public void setFechaReposicion(Date fechaReposicion) {
        this.fechaReposicion = fechaReposicion;
    }

    /**
     * Retorna el conjunto de pedidos asociados al artículo.
     *
     * @return un conjunto de {@code PedidoArticulo}.
     */
    @XmlTransient
    public Set<PedidoArticulo> getPedidoArticulos() {
        return pedidoArticulos;
    }

    /**
     * Asigna el conjunto de pedidos asociados al artículo.
     *
     * @param pedidoArticulos el nuevo conjunto de {@code PedidoArticulo}.
     */
    public void setPedidoArticulos(Set<PedidoArticulo> pedidoArticulos) {
        this.pedidoArticulos = pedidoArticulos;
    }

    /**
     * Conjunto de almacenes asociados al artículo.
     */
    @ManyToMany
    @JoinTable(name = "articulo_almacen", schema = "reto2",
            joinColumns = @JoinColumn(name = "articulos_articulo_id", referencedColumnName = "articulo_id"),
            inverseJoinColumns = @JoinColumn(name = "almacenes_almacen_id", referencedColumnName = "almacen_id"))
    private Set<Almacen> almacenes = new HashSet<>();

    /**
     * Retorna el conjunto de almacenes asociados al artículo.
     *
     * @return un conjunto de {@code Almacen}.
     */
    @XmlTransient
    public Set<Almacen> getAlmacenes() {
        return almacenes;
    }

    /**
     * Asigna el conjunto de almacenes asociados al artículo.
     *
     * @param almacenes el nuevo conjunto de {@code Almacen}.
     */
    public void setAlmacenes(Set<Almacen> almacenes) {
        this.almacenes = almacenes;
    }

    /**
     * Retorna la lista transitoria de almacenes.
     * <p>
     * Esta lista no se persiste en la base de datos.
     * </p>
     *
     * @return una lista de {@code Almacen}.
     */
    public List<Almacen> getAlmacenTrump() {
        return almacenTrump;
    }

    /**
     * Asigna la lista transitoria de almacenes.
     * <p>
     * Esta lista no se persiste en la base de datos.
     * </p>
     *
     * @param almacenTrump la nueva lista de {@code Almacen}.
     */
    public void setAlmacenTrump(List<Almacen> almacenTrump) {
        this.almacenTrump = almacenTrump;
    }

    /**
     * Calcula el código hash del artículo.
     * <p>
     * El código hash se basa en el identificador único del artículo.
     * </p>
     *
     * @return el código hash calculado.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Compara el artículo con otro objeto para determinar si son iguales.
     * <p>
     * La comparación se basa en el identificador único del artículo.
     * </p>
     *
     * @param object el objeto con el que se realiza la comparación.
     * @return {@code true} si ambos objetos son iguales; {@code false} en caso
     * contrario.
     */
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

    /**
     * Retorna una representación en forma de cadena del artículo.
     *
     * @return una cadena que contiene el id del artículo.
     */
    @Override
    public String toString() {
        return "crud.entidades.Articulo[ id=" + id + " ]";
    }

}
