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

/*
 * Esta clase representa la entidad PedidoArticulo dentro del sistema.
 *
 * <p>Un objeto PedidoArticulo almacena la información de un artículo
 * específico incluido en un pedido, como la cantidad y el precio de
 * compra. Esta entidad está mapeada a la tabla 'pedido_articulo' del
 * esquema 'reto2' en la base de datos.</p>
 *
 * <p>Consulta nombrada disponible:</p>
 * <ul>
 *   <li><strong>findAllPedidoArticulo:</strong> Recupera todos los pedidos de artículos.</li>
 * </ul>
 *
 * <p>Esta clase está anotada como una entidad JPA, lo que permite su
 * persistencia en una base de datos relacional utilizando el mapeo
 * objeto-relacional proporcionado por el framework.</p>
 *
 * <p>Los campos {@link #id}, {@link #pedido}, {@link #articulo},
 * {@link #cantidad} y {@link #precioCompra} son mapeados a sus respectivas
 * columnas en la tabla 'pedido_articulo'.</p>
 *
 * <p>Esta clase proporciona métodos estándar de acceso a datos como
 * getters y setters para todos los campos, así como métodos sobrescritos
 * para {@link #hashCode()}, {@link #equals(Object)} y {@link #toString()}
 * para facilitar la comparación y representación de instancias de
 * {@code PedidoArticulo}.</p>
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
     * Identificador único del PedidoArticulo.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pedido_articulo_id")
    private Long id;

    /**
     * Pedido al que pertenece el PedidoArticulo.
     */
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    /**
     * Artículo asociado al PedidoArticulo.
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
     * Precio de compra del artículo en el pedido.
     */
    @Column(name = "precio_compra")
    private double precioCompra;

    /**
     * Constructor vacío de la clase PedidoArticulo.
     */
    public PedidoArticulo() {
    }

    /**
     * Constructor de la clase PedidoArticulo que inicializa todos los campos.
     *
     * @param id Identificador único del PedidoArticulo.
     * @param pedido Pedido al que pertenece el PedidoArticulo.
     * @param articulo Artículo asociado al PedidoArticulo.
     * @param cantidad Cantidad del artículo incluida en el pedido.
     * @param precioCompra Precio de compra del artículo en el pedido.
     */
    public PedidoArticulo(Long id, Pedido pedido, Articulo articulo, int cantidad, double precioCompra) {
        this.id = id;
        this.pedido = pedido;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
    }

    /**
     * Obtiene el identificador único del PedidoArticulo.
     *
     * @return El identificador único del PedidoArticulo.
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna el identificador único del PedidoArticulo.
     *
     * @param id El nuevo identificador único del PedidoArticulo.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el pedido asociado al PedidoArticulo.
     *
     * @return El pedido asociado al PedidoArticulo.
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * Asigna el pedido asociado al PedidoArticulo.
     *
     * @param pedido El nuevo pedido asociado al PedidoArticulo.
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * Obtiene el artículo asociado al PedidoArticulo.
     *
     * @return El artículo asociado al PedidoArticulo.
     */
    public Articulo getArticulo() {
        return articulo;
    }

    /**
     * Asigna el artículo asociado al PedidoArticulo.
     *
     * @param articulo El nuevo artículo asociado al PedidoArticulo.
     */
    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    /**
     * Obtiene la cantidad del artículo incluida en el pedido.
     *
     * @return La cantidad del artículo incluida en el pedido.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Asigna la cantidad del artículo incluida en el pedido.
     *
     * @param cantidad La nueva cantidad del artículo incluida en el pedido.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el precio de compra del artículo en el pedido.
     *
     * @return El precio de compra del artículo en el pedido.
     */
    public double getPrecioCompra() {
        return precioCompra;
    }

    /**
     * Asigna el precio de compra del artículo en el pedido.
     *
     * @param precioCompra El nuevo precio de compra del artículo en el pedido.
     */
    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    /**
     * Calcula el código hash del PedidoArticulo.
     *
     * @return El código hash calculado.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Compara este objeto con otro para verificar si son iguales.
     *
     * @param object El objeto a comparar.
     * @return {@code true} si los objetos son iguales; de lo contrario,
     * {@code false}.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PedidoArticulo)) {
            return false;
        }
        PedidoArticulo other = (PedidoArticulo) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    /**
     * Retorna una representación en cadena del PedidoArticulo.
     *
     * @return Una cadena que representa al PedidoArticulo, incluyendo su
     * identificador único.
     */
    @Override
    public String toString() {
        return "crud.entidades.PedidoArticulo[ id=" + id + " ]";
    }
}
