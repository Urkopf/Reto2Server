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
 * Entidad que representa un artículo dentro del sistema.
 *
 * <p>
 * Esta clase almacena la información esencial de un artículo, como su nombre,
 * precio, descripción, stock y relaciones con almacenes y pedidos. Se mapea a
 * la tabla <strong>articulo</strong> del esquema <strong>reto2</strong> en la
 * base de datos.</p>
 *
 * <p>
 * Consulta nombrada disponible:</p>
 * <ul>
 * <li><strong>findAllArticulo:</strong> Recupera todos los artículos.</li>
 * </ul>
 *
 * <p>
 * Esta clase está anotada como una entidad JPA, lo que permite su persistencia
 * en una base de datos relacional a través del mapeo objeto-relacional
 * proporcionado por el framework.</p>
 *
 * <p>
 * Los campos {@link #id}, {@link #nombre}, {@link #precio}, {@link #descripcion},
 * {@link #stock}, {@link #fechaReposicion}, {@link #pedidoArticulos} y
 * {@link #almacenes} son mapeados a sus respectivas columnas en la tabla
 * <strong>articulo</strong>.</p>
 *
 * <p>
 * El campo {@link #almacenTrump} es un campo transitorio y no se persiste en la
 * base de datos, utilizado para el manejo temporal de datos relacionados con
 * almacenes.</p>
 *
 * <p>
 * Esta clase proporciona los métodos estándar de acceso a datos como getters y
 * setters para todos los campos, así como métodos sobrescritos para
 * {@link #hashCode()}, {@link #equals(Object)} y {@link #toString()} para
 * facilitar la comparación y representación de instancias de
 * {@code Articulo}.</p>
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
     * Descripción detallada del artículo.
     */
    private String descripcion;

    /**
     * Stock disponible del artículo.
     */
    private int stock;

    /**
     * Lista de almacenes transitoria para el manejo temporal de datos.
     * <p>
     * Este campo no se persiste en la base de datos.</p>
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
     * Conjunto de relaciones con {@link PedidoArticulo}.
     */
    @OneToMany(mappedBy = "articulo")
    private Set<PedidoArticulo> pedidoArticulos = new HashSet<>();

    /**
     * Conjunto de almacenes relacionados con el artículo.
     */
    @ManyToMany
    @JoinTable(name = "articulo_almacen", schema = "reto2",
            joinColumns = @JoinColumn(name = "articulos_articulo_id", referencedColumnName = "articulo_id"),
            inverseJoinColumns = @JoinColumn(name = "almacenes_almacen_id", referencedColumnName = "almacen_id"))
    private Set<Almacen> almacenes = new HashSet<>();

    /**
     * Constructor vacío.
     */
    public Articulo() {
    }

    /**
     * Obtiene el identificador del artículo.
     *
     * @return el identificador del artículo.
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna el identificador del artículo.
     *
     * @param id el nuevo identificador.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del artículo.
     *
     * @return el nombre del artículo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre del artículo.
     *
     * @param nombre el nuevo nombre del artículo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el precio del artículo.
     *
     * @return el precio del artículo.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Asigna el precio del artículo.
     *
     * @param precio el nuevo precio.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la descripción del artículo.
     *
     * @return la descripción del artículo.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Asigna la descripción del artículo.
     *
     * @param descripcion la nueva descripción.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el stock disponible del artículo.
     *
     * @return el stock disponible.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Asigna el stock del artículo.
     *
     * @param stock el nuevo valor de stock.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Obtiene la fecha de reposición del artículo.
     *
     * @return la fecha de reposición.
     */
    public Date getFechaReposicion() {
        return fechaReposicion;
    }

    /**
     * Asigna la fecha de reposición del artículo.
     *
     * @param fechaReposicion la nueva fecha de reposición.
     */
    public void setFechaReposicion(Date fechaReposicion) {
        this.fechaReposicion = fechaReposicion;
    }

    /**
     * Obtiene el conjunto de pedidos asociados al artículo.
     *
     * @return el conjunto de {@link PedidoArticulo}.
     */
    @XmlTransient
    public Set<PedidoArticulo> getPedidoArticulos() {
        return pedidoArticulos;
    }

    /**
     * Asigna el conjunto de pedidos asociados al artículo.
     *
     * @param pedidoArticulos el nuevo conjunto de pedidos.
     */
    public void setPedidoArticulos(Set<PedidoArticulo> pedidoArticulos) {
        this.pedidoArticulos = pedidoArticulos;
    }

    /**
     * Obtiene el conjunto de almacenes asociados al artículo.
     *
     * @return el conjunto de almacenes.
     */
    @XmlTransient
    public Set<Almacen> getAlmacenes() {
        return almacenes;
    }

    /**
     * Asigna el conjunto de almacenes asociados al artículo.
     *
     * @param almacenes el nuevo conjunto de almacenes.
     */
    public void setAlmacenes(Set<Almacen> almacenes) {
        this.almacenes = almacenes;
    }

    /**
     * Obtiene la lista transitoria de almacenes.
     *
     * @return la lista transitoria.
     */
    public List<Almacen> getAlmacenTrump() {
        return almacenTrump;
    }

    /**
     * Asigna la lista transitoria de almacenes.
     *
     * @param almacenTrump la nueva lista transitoria.
     */
    public void setAlmacenTrump(List<Almacen> almacenTrump) {
        this.almacenTrump = almacenTrump;
    }

    /**
     * Calcula el código hash del artículo.
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
     * Compara este objeto con otro para verificar si son iguales.
     * <p>
     * Dos objetos de tipo {@code Articulo} se consideran iguales si sus
     * identificadores son iguales.</p>
     *
     * @param object el objeto a comparar.
     * @return {@code true} si los objetos son iguales; de lo contrario,
     * {@code false}.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    /**
     * Retorna una representación en cadena del artículo.
     *
     * @return una cadena que representa al artículo, incluyendo su
     * identificador único.
     */
    @Override
    public String toString() {
        return "crud.entidades.Articulo[ id=" + id + " ]";
    }

}
